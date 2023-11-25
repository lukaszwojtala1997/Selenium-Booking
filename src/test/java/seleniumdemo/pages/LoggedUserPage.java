package seleniumdemo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumdemo.tests.BaseTest;
import seleniumdemo.utils.SeleniumHelper;

import java.util.List;

public class LoggedUserPage extends BaseTest {

    private WebDriver webDriver;

    public LoggedUserPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "[class='RTL']")
    private WebElement headingText;

    @FindBy(css = "[class='text-center go-right']")
    private WebElement homeButton;

    @FindBy(xpath = "//a[@onclick='mySelectUpdate()']")
    private List<WebElement> wishlistIcon;

    @FindBy(css = "[class='dark']")
    private WebElement hotelName;

    public String getText(){
        SeleniumHelper.waitForElementToBeVisible(webDriver, headingText);
        return headingText.getText();
    }

    public HotelSearchPage backToHomePage() {
        SeleniumHelper.waitForElementToBeClickable(webDriver, homeButton);
        try {
            webDriver.findElement(By.xpath("//li[@class='text-center go-right']")).click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("arguments[0].click();", webDriver.findElement(By.xpath("//li[@class='text-center go-right']")));
        }
        homeButton.click();
        return new HotelSearchPage(webDriver);
    }

    public LoggedUserPage getWishlist(){
        wishlistIcon.get(1).click();
        return this;
    }

    public String getHotelName(){
        SeleniumHelper.waitForElementToBeVisible(webDriver, hotelName);
        return hotelName.getText();
    }

}
