package info.novatec.testit.webtester.pageobjects;

import info.novatec.testit.webtester.api.annotations.Mapping;


/**
 * Represents a HTML password field. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> password</li>
 * </ul>
 *
 * @since 0.9.0
 */
@Mapping(tag = "input", attribute = "type", values = { "password" })
public class PasswordField extends TextField {

    @Override
    public PasswordField clearText() {
        // Overridden for fluent API use
        super.clearText();
        return this;
    }

    @Override
    public PasswordField setText(String textToSet) {
        // Overridden for fluent API use
        super.setText(textToSet);
        return this;
    }

    @Override
    public PasswordField appendText(String textToAppend) {
        // Overridden for fluent API use
        super.appendText(textToAppend);
        return this;
    }

}
