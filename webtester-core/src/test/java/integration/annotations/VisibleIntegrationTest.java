package integration.annotations;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.annotations.Visible;
import info.novatec.testit.webtester.api.enumerations.Method;
import info.novatec.testit.webtester.api.exceptions.PageObjectFactoryException.VisiblePageObjectFieldException;
import info.novatec.testit.webtester.api.exceptions.PageObjectFactoryException.VisiblePageObjectListFieldException;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;
import info.novatec.testit.webtester.utils.Asserts;
import integration.AbstractWebTesterIntegrationTest;

import java.util.List;

import org.junit.Test;

public class VisibleIntegrationTest extends AbstractWebTesterIntegrationTest {

    @Override
    protected String getHTMLFilePath() {
        return "html/annotations/visible.html";
    }

    @Test
    public void testThatVisibleAnnotationIsUsedToCheckVisibilityOfPageObject() {
        SingleVisiblePage singleVisiblePage = getBrowser().create(SingleVisiblePage.class);
        Asserts.assertVisible(singleVisiblePage);
    }

    @Test
    public void testThatVisibleAnnotationIsUsedToCheckVisibilityOfPageObjectList() {
        MultiVisiblePage multiVisiblePage = getBrowser().create(MultiVisiblePage.class);
        Asserts.assertVisible(multiVisiblePage);
    }

    @Test(expected = VisiblePageObjectFieldException.class)
    public void testThatVisibleAnnotationOnNonExistingPageObjectLeadsToException() {
        getBrowser().create(InvisiblePage.class);
        
    }

    @Test(expected = VisiblePageObjectListFieldException.class)
    public void testThatVisibleAnnotationOnPageObjectListLeadsToExceptionIfThereAreNotEnoughVisibleObjects() {
        getBrowser().create(MultiVisiblePageWrongCount.class);
    }

    @Test
    public void testThatAnnotationOnContentsOfInvisibleContainerBoNotLeadToException(){
       getBrowser().create(PageWithInvisibleContainer.class);
        
    }
    
    public static class SingleVisiblePage extends PageObject {

        @Visible
        @IdentifyUsing("textfield")
        TextField textfield;

    }
    
    public static class InvisiblePage extends PageObject{
        @Visible
        @IdentifyUsing(method = Method.ID , value="non:existing:field")
        TextField invisibleTextField;
    }
    
    public static class MultiVisiblePage extends PageObject {

        @Visible(2)
        @IdentifyUsing(method = Method.ID_STARTS_WITH, value = "multi:textfield")
        List<TextField> multipleTextFields;

    }

    public static class MultiVisiblePageWrongCount extends PageObject {

        @Visible(3)
        @IdentifyUsing(method = Method.ID_STARTS_WITH, value = "multi:textfield")
        List<TextField> multipleTextFields;

    }
    
    public static class InvisibleContainer extends PageObject{
        @Visible
        @IdentifyUsing("invisible:textfield")
        TextField invisibleTextField;
    }
    
    public static class PageWithInvisibleContainer extends PageObject{
        
        @IdentifyUsing("invisibleContainer")
        InvisibleContainer invisibleContainer;
    }

}
