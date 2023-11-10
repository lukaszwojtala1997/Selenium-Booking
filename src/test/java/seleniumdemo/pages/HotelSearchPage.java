package seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import seleniumdemo.utils.SeleniumHelper;

import java.util.List;

public class HotelSearchPage {

    private WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger();

    public HotelSearchPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(css = "[class=select2-choice]")
    private WebElement searchHotelSpan;

    @FindBy(css = "#select2-drop .select2-input")
    private WebElement searchHotelInput;

    @FindBy(name = "checkin")
    private WebElement checkIn;

    @FindBy(name = "checkout")
    private WebElement checkOut;

    @FindBy(id = "travellersInput")
    private WebElement travellersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//li[@id='li_myaccount']")
    private List<WebElement> myAccountLink;

    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> signUpLink;

    @FindBy(css = "[class='thm-btn btn-block']")
    private List<WebElement> bookingHotel;

    @FindBy(xpath = "//a[text()=' Login']")
    private List<WebElement> loginLink;

    @FindBy(css = "[class='go-text-right dropdown-toggle']")
    private List<WebElement> languageList;

    @FindBy(id = "es")
    private WebElement spanishLanguage;

    @FindBy(css = "[class='text-center go-right']")
    private WebElement homeButton;

    public HotelSearchPage setCity(String cityName) {
        logger.info("Setting city " + cityName);
        SeleniumHelper.waitForElementToBeClickable(webDriver, searchHotelSpan);
        searchHotelSpan.click();
        SeleniumHelper.waitForElementToBeClickable(webDriver, searchHotelInput);
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        SeleniumHelper.waitForElementToExist(webDriver, By.xpath(xpath));
        webDriver.findElement(By.xpath(xpath)).click();
        logger.info("Setting city done");
        return this;
    }

    public HotelSearchPage setDates(String checkin, String chekout) {
        logger.info("Setting dates check-in:" + checkin + " check-out " + chekout);
        checkIn.sendKeys(checkin);
        checkOut.sendKeys(chekout);
        logger.info("Setting dates done");
        return this;
    }

    public HotelSearchPage setTravellers(int adultsToAdd, int childToAdd) {
        logger.info("Adding adults: " + adultsToAdd + " and kids: " + childToAdd);
        travellersInput.click();
        SeleniumHelper.waitForElementToBeClickable(webDriver, adultPlusBtn);
        addTraveller(adultPlusBtn, adultsToAdd);
        SeleniumHelper.waitForElementToBeClickable(webDriver, childPlusBtn);
        addTraveller(childPlusBtn, childToAdd);
        logger.info("Adding travelers done");
        return this;
    }

    private void addTraveller(WebElement travellerBtn, int numberOfTravellers) {
        for (int i = 0; i < numberOfTravellers; i++) {
            ExpectedConditions.visibilityOfElementLocated(By.id("adultPlusBtn"));
            ExpectedConditions.visibilityOfElementLocated(By.id("childPlusBtn"));
            travellerBtn.click();
        }
    }

    public ResultsPage performSearch() {
        logger.info("Performing search");
        searchButton.click();
        logger.info("Performing searching done");
        return new ResultsPage(webDriver);
    }

    public SignUpPage openSignUpForm() {
        myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        signUpLink.get(1).click();
        return new SignUpPage(webDriver);
    }

    public BookingHotelPage bookingHotel(){
        bookingHotel.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        return new BookingHotelPage(webDriver);
    }

    public LoginUserPage openLoginForm() {
        myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        loginLink.get(1).click();
        return new LoginUserPage(webDriver);
    }

    public HotelSearchPage changeLanguage() {
        languageList.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);

        try {
            webDriver.findElement(By.id("es")).click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("arguments[0].click();", webDriver.findElement(By.id("es")));
        }

        return this;
    }

    public String getHomeTextInSpanish(){
        return homeButton.getText();
    }

}
