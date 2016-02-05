package info.novatec.testit.webtester.pageobjects;

import info.novatec.testit.webtester.api.annotations.Mapping;
import info.novatec.testit.webtester.api.annotations.Mappings;


/**
 * Represents a headline. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> h1</li>
 * <li><b>tag:</b> h2</li>
 * <li><b>tag:</b> h3</li>
 * <li><b>tag:</b> h4</li>
 * <li><b>tag:</b> h5</li>
 * <li><b>tag:</b> h6</li>
 * </ul>
 *
 * @since 0.9.0
 */
@Mappings({ @Mapping(tag = "h1"), @Mapping(tag = "h2"), @Mapping(tag = "h3"), @Mapping(tag = "h4"), @Mapping(tag = "h5"),
    @Mapping(tag = "h6") })
public class Headline extends PageObject {

}
