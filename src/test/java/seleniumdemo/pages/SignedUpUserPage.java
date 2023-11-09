package seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumdemo.utils.SeleniumHelper;

public class SignedUpUserPage {

    private WebDriver webDriver;

    public SignedUpUserPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//h3[@class='RTL']")
    private WebElement headingText;

    public String getHeadingText() {
        SeleniumHelper.waitForElementToBeVisible(webDriver, headingText);
        return headingText.getText();
    }
}
