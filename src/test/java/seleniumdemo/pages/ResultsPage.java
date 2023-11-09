package seleniumdemo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumdemo.utils.SeleniumHelper;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage {

    private WebDriver webDriver;

    public ResultsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//h4[contains(@class, 'list_title')]//b")
    private List<WebElement> hotelList;

    @FindBy(xpath = "//h2[@class='text-center']")
    public WebElement resultHeading;

    @FindBy(css = "[class='tooltip_flip tooltip-effect-1']")
    private WebElement wishlistButton;

    @FindBy(xpath = "//a[@class='go-text-right' and text()='  Account']")
    private List<WebElement> loginLink;

    @FindBy(css = "[class='dropdown-toggle go-text-right']")
    private List<WebElement> myAccountLink;

    @FindBy(id = "4")
    private WebElement starGrade;

    @FindBy(id = "Hotel")
    private WebElement propertyTypes;

    @FindBy(id = "Airport Transport")
    private WebElement amenities;

    @FindBy(id = "searchform")
    private WebElement searchButton;

    public List<String> getHotelNames() {
        return hotelList.stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
    }

    public String getHeadingText() {
        return resultHeading.getText();
    }

    public ResultsPage addHotelToWishlist() {
        SeleniumHelper.waitForElementToBeClickable(webDriver, wishlistButton);
        try {
            webDriver.findElement(By.cssSelector("[class='tooltip_flip tooltip-effect-1']")).click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("arguments[0].click();", webDriver.findElement(By.cssSelector("[class='tooltip_flip tooltip-effect-1']")));
        }
        return new ResultsPage(webDriver);
    }

    public LoggedUserPage openLoginForm() {
        myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        loginLink.get(1).click();
        return new LoggedUserPage(webDriver);
    }

    public String getAlertText(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();
        return alert.getText();
    }

    public ResultsPage setProperties() {
        Actions actions = new Actions(webDriver);

        actions.moveToElement(webDriver.findElement(By.id("4"))).click().perform();
        actions.moveToElement(webDriver.findElement(By.id("Hotel"))).click().perform();
        actions.moveToElement(webDriver.findElement(By.id("Airport Transport"))).click().perform();

        searchButton.click();
        return this;
    }
}
