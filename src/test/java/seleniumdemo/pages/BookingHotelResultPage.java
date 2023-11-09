package seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumdemo.tests.BaseTest;
import seleniumdemo.utils.SeleniumHelper;

public class BookingHotelResultPage extends BaseTest {

    WebDriver webDriver;

    public BookingHotelResultPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//div[contains(text(), '                    Unpaid                ')]")
    private WebElement unpaidText;

    public String unpaid(){
        SeleniumHelper.waitForElementToBeVisible(webDriver, unpaidText);
        return unpaidText.getText();
    }
}
