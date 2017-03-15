package info.novatec.testit.webtester.testng.listener;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;

import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.internal.ReflectionUtils;
import info.novatec.testit.webtester.testng.annotations.ConfigurationValue;
import info.novatec.testit.webtester.testng.annotations.CreateUsing;
import info.novatec.testit.webtester.testng.annotations.EntryPoint;
import info.novatec.testit.webtester.testng.annotations.Primary;
import info.novatec.testit.webtester.testng.exceptions.NoManagedBrowserException;
import info.novatec.testit.webtester.testng.exceptions.NoPrimaryBrowserException;
import info.novatec.testit.webtester.testng.exceptions.NoUniquePrimaryBrowserException;
import info.novatec.testit.webtester.testng.listener.internal.AbstractTestBrowser;
import info.novatec.testit.webtester.testng.listener.internal.ClassTestBrowser;
import info.novatec.testit.webtester.testng.listener.internal.ConfigurationValueInjector;
import info.novatec.testit.webtester.testng.listener.internal.MethodTestBrowser;
import info.novatec.testit.webtester.testng.listener.internal.TestClassPlausibilityChecker;
import info.novatec.testit.webtester.testng.listener.internal.WebTesterTestNGListenerAdapter;


/**
 * This {@link org.testng.ITestNGListener} takes care of the following
 * life cycle management issues:
 * <ul>
 * <li>Creating {@link Browser browsers} for tests.</li>
 * <li>Injecting {@link Browser browser} instances into static and instance
 * fields of the test.</li>
 * <li>Injection of configuration properties into static and instance fields.
 * </li>
 * <li>Closing {@link Browser browsers} at the end of their natural scope.</li>
 * </ul>
 * More precisely, any {@link Field field} of type {@link Browser browser}
 * annotated with {@link Resource @Resource} and a value of <code>null</code>
 * will have a new {@link Browser browser} instance injected. Non
 * <code>null</code> fields which are annotated with {@link Resource @Resource}
 * will still be managed, but the original {@link Browser browser} instance will
 * be used. The {@link Browser browser} instances are created by using the
 * factory provided with the {@link CreateUsing @CreateUsing} annotation.
 * <p>
 * Fields annotated with {@link ConfigurationValue @ConfigurationValue} will be
 * injected with values from the primary browser's configuration. In case you
 * are using more then one browser, {@link Primary @Primary} has to be used to
 * declare one of them as the primary browser.
 * <p>
 * All of these operations are done regardless of field visibility (private,
 * default, protected or public). Reflection is used to break open all
 * visibility modifiers.
 * <p>
 * <b>Exaple test class:</b>
 * <pre>
 * <code>
 * public class FooTest {
 *
 * &#64;Resource
 * &#64;CreateUsing(FooBrowserFactory.class)
 * &#64;EntryPoint("http://localhost:8080/login")
 * private static Browser browser;
 *
 * &#64;ConfigurationValue("auth.username")
 * private String username;
 * &#64;ConfigurationValue("auth.password")
 * private String password;
 *
 * private HomePage home;
 *
 * &#64;Before
 * void executeLogin(){
 * home = browser.create(LoginPage.class).login(username, password);
 * }
 *
 * &#64;Test
 * void testFooOnHomePage(){
 * ...
 * }
 *
 * ...
 *
 * }
 * </code>
 * </pre>
 *
 * @see Browser
 * @see ConfigurationValue
 * @see CreateUsing
 * @see EntryPoint
 * @see Primary
 * @see Resource
 * @since 1.2
 */
public class WebTesterTestNGListener extends WebTesterTestNGListenerAdapter {

    private List<ClassTestBrowser> classBrowsers = new ArrayList<>();
    private List<Field> classBrowserFields = new ArrayList<>();
    private List<MethodTestBrowser> methodBrowsers = new ArrayList<>();

    public WebTesterTestNGListener() {
    }

    public WebTesterTestNGListener(Class<?> testClass) {
        new TestClassPlausibilityChecker(testClass).assertPlausibilityOfTestClass();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        initializeClassLevel(iTestContext);
        executeBeforeClassForAllBrowsers();
        injectConfigurationValuesIntoStaticFields(iTestContext);
    }

    private void initializeClassLevel(ITestContext iTestContext) {
        classBrowsers.clear();
        ITestNGMethod[] testMethods = iTestContext.getAllTestMethods();
        for (ITestNGMethod method : testMethods) {
            Class<?> testClass = method.getRealClass();
            for (Field field : ReflectionUtils.getAllFieldsOfClassHierarchy(testClass)) {
                boolean fieldIsStatic = Modifier.isStatic(field.getModifiers());
                boolean fieldIsABrowser = Browser.class.isAssignableFrom(field.getType());
                boolean fieldIsAnnotatedAsResource = field.getAnnotation(Resource.class) != null;
                boolean isNoDuplicate = fieldIsNoDuplicateOfAnExistingClassBrowserField(field);
                if (fieldIsStatic && fieldIsABrowser && fieldIsAnnotatedAsResource && isNoDuplicate) {
                    classBrowserFields.add(field);
                    classBrowsers.add(new ClassTestBrowser(field));
                }
            }
        }
    }

    /**
     * Because every method have the same browser instances, there would be duplicates of all Browsers
     * which would result in an `NoUniquePrimaryBrowserException`. This method compares the given
     * field with all existing classBrowserFields and returns true if it is no duplicate of an existing field.
     *
     * @param field the given browser candidate.
     * @return true in case that field is no duplicate, false if it is a duplicate.
     */
    private boolean fieldIsNoDuplicateOfAnExistingClassBrowserField(Field field) {
        boolean isNoDuplicate = true;
        for (Field classBrowserField : classBrowserFields) {
            if (classBrowserField.equals(field)) {
                isNoDuplicate = false;
            }
        }
        return isNoDuplicate;
    }

    private void executeBeforeClassForAllBrowsers() {
        for (ClassTestBrowser browser : classBrowsers) {
            try {
                browser.beforeClass();
            } catch (ReflectiveOperationException e) {
                throw new UndeclaredThrowableException(e);
            }
        }
    }

    private void injectConfigurationValuesIntoStaticFields(ITestContext iTestContext) {
        ITestNGMethod[] testMethods = iTestContext.getAllTestMethods();
        for (ITestNGMethod method : testMethods) {
            Class<?> testClass = method.getRealClass();
            if (configurationValuesAnnotationIsUsedOnClassLevel(testClass)) {
                Configuration configuration = getPrimaryBrowser().getBrowser().getConfiguration();
                ConfigurationValueInjector.injectStatics(configuration, testClass);
            }
        }
    }

    private boolean configurationValuesAnnotationIsUsedOnClassLevel(Class<?> testClass) {
        boolean isUsed = false;
        for (Field field : ReflectionUtils.getAllFieldsOfClassHierarchy(testClass)) {
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            boolean isAnnotated = field.isAnnotationPresent(ConfigurationValue.class);
            if (isStatic && isAnnotated) {
                isUsed = true;
            }
        }
        return isUsed;
    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        Method method = iInvokedMethod.getTestMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(BeforeMethod.class) || method.isAnnotationPresent(Test.class)) {
            initializeMethodLevel(iTestResult);
            executeBeforeTestForAllBrowsers();
            injectConfigurationValuesIntoInstanceFields(iTestResult);
        }
    }

    private void initializeMethodLevel(ITestResult iTestResult) {
        methodBrowsers.clear();
        Class<?> testClass = iTestResult.getTestClass().getRealClass();
        for (Field field : ReflectionUtils.getAllFieldsOfClassHierarchy(testClass)) {
            boolean fieldIsNonStatic = !Modifier.isStatic(field.getModifiers());
            boolean fieldIsABrowser = Browser.class.isAssignableFrom(field.getType());
            boolean fieldIsAnnotatedAsResource = field.getAnnotation(Resource.class) != null;
            if (fieldIsNonStatic && fieldIsABrowser && fieldIsAnnotatedAsResource) {
                methodBrowsers.add(new MethodTestBrowser(field, iTestResult.getInstance()));
            }
        }
    }

    private void executeBeforeTestForAllBrowsers() {
        for (ClassTestBrowser browser : classBrowsers) {
            browser.beforeTest();
        }
        for (MethodTestBrowser browser : methodBrowsers) {
            try {
                browser.beforeTest();
            } catch (ReflectiveOperationException e) {
                throw new UndeclaredThrowableException(e);
            }
        }
    }

    private void injectConfigurationValuesIntoInstanceFields(ITestResult iTestResult) {
        if (configurationValuesAnnotationIsUsedOnMethodLevel(iTestResult)) {
            Configuration configuration = getPrimaryBrowser().getBrowser().getConfiguration();
            ConfigurationValueInjector.inject(configuration, iTestResult.getInstance());
        }
    }

    private boolean configurationValuesAnnotationIsUsedOnMethodLevel(ITestResult iTestResult) {
        boolean isUsed = false;
        Class<?> testClass = iTestResult.getTestClass().getRealClass();
        for (Field field : ReflectionUtils.getAllFieldsOfClassHierarchy(testClass)) {
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            boolean isAnnotated = field.isAnnotationPresent(ConfigurationValue.class);
            if (!isStatic && isAnnotated) {
                isUsed = true;
            }
        }
        return isUsed;
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        Method method = iInvokedMethod.getTestMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(AfterMethod.class) || method.isAnnotationPresent(Test.class)) {
            executeAfterTestForAllBrowsers();
        }
    }

    private void executeAfterTestForAllBrowsers() {
        for (MethodTestBrowser browser : methodBrowsers) {
            browser.afterTest();
        }
        for (ClassTestBrowser browser : classBrowsers) {
            browser.afterTest();
        }
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        executeAfterClassForAllBrowsers();
    }

    private void executeAfterClassForAllBrowsers() {
        for (ClassTestBrowser browser : classBrowsers) {
            browser.afterClass();
        }
    }

    private AbstractTestBrowser getPrimaryBrowser() {
        assertThatAtLeastOneBrowserIsManaged();
        if (numberOfManagedBrowsers() == 1) {
            return getAllBrowsersRegardlessOfScope().get(0);
        }
        return getUniquePrimaryBrowserCandidate();
    }

    private void assertThatAtLeastOneBrowserIsManaged() {
        if (numberOfManagedBrowsers() == 0) {
            throw new NoManagedBrowserException();
        }
    }

    private int numberOfManagedBrowsers() {
        return classBrowsers.size() + methodBrowsers.size();
    }

    private AbstractTestBrowser getUniquePrimaryBrowserCandidate() {
        AbstractTestBrowser primaryBrowserCandidate = null;
        for (AbstractTestBrowser browser : getAllBrowsersRegardlessOfScope()) {
            if (browser.isPrimaryCandidate()) {
                if (primaryBrowserCandidate == null) {
                    primaryBrowserCandidate = browser;
                } else {
                    throw new NoUniquePrimaryBrowserException();
                }
            }
        }
        if (primaryBrowserCandidate == null) {
            throw new NoPrimaryBrowserException();
        }
        return primaryBrowserCandidate;
    }

    private List<AbstractTestBrowser> getAllBrowsersRegardlessOfScope() {
        List<AbstractTestBrowser> allBrowser = new LinkedList<AbstractTestBrowser>();
        allBrowser.addAll(classBrowsers);
        allBrowser.addAll(methodBrowsers);
        return allBrowser;
    }

}
