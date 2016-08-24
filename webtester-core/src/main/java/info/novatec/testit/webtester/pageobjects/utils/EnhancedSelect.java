package info.novatec.testit.webtester.pageobjects.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


/**
 * This implementation of Selenium's {@link Select} helper class fixes some issues with the original implementation.
 * <p>
 * <b>Fixed Issues:</b>
 * <ul>
 * <li>{@link #getFirstSelectedOption()} has very bad performance in case of great number of options</li>
 * <li>{@link #getAllSelectedOptions()} has very bad performance in case of great number of options</li>
 * </ul>
 *
 * @since 1.2
 */
public class EnhancedSelect extends Select {

    private final WebElement element;

    public EnhancedSelect(WebElement element) {
        super(element);
        this.element = element;
    }

    @Override
    public WebElement getFirstSelectedOption() {
        return element.findElement(By.cssSelector("option:checked"));
    }

    @Override
    public List<WebElement> getAllSelectedOptions() {
        return element.findElements(By.cssSelector("option:checked"));
    }

}
