package seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import seleniumdemo.utils.SeleniumHelper;

import java.util.Set;

public class FlightSearchPage {

    WebDriver webDriver;

    public FlightSearchPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//span[contains(text(), 'Flights  ')]")
    private WebElement flightsButton;

    @FindBy(xpath = "//*[@id='s2id_location_from']/a/span[1]")
    private WebElement cityFromButton;

    @FindBy(xpath = "//*[@id='select2-drop']/div/input")
    private WebElement cityFromInput;

    @FindBy(xpath = "//*[@id='select2-drop']/ul/li[1]/div")
    private WebElement selectedCityButton;

    @FindBy(xpath = "//*[@id='s2id_location_to']/a/span[1]")
    private WebElement cityToButton;

    @FindBy(xpath = "//*[@id='select2-drop']/div/input")
    private WebElement cityToInput;

    @FindBy(xpath = "//*[@id='select2-drop']/ul/li[1]/div")
    private WebElement selectedCityToButton;

    @FindBy(name = "departure")
    private WebElement depart;

    @FindBy(xpath = "//*[@id='flights']/form/div[6]/button")
    private WebElement searchButton;

    @FindBy(name = "arrival")
    private WebElement returnInput;

    @FindBy(name = "madult")
    private WebElement adultsSelect;

    @FindBy(name = "mchildren")
    private WebElement childSelect;

    @FindBy(name = "minfant")
    private WebElement infantSelect;

    @FindBy(name = "cabinclass")
    private WebElement cabinClass;

    @FindBy(id = "round")
    private WebElement roundTrip;

    @FindBy(xpath = "//div[@class='iradio_square-grey checked']")
    private WebElement oneWayTrip;

    @FindBy(id = "sumManualPassenger")
    private WebElement passengerButton;

    @FindBy(xpath = "/html/body/div[14]/div[1]/table/thead/tr[1]/th[3]")
    private WebElement nextMonth;


    public FlightSearchPage setFlight() {
        flightsButton.click();
        return this;
    }


    public FlightSearchPage setDepartureAirport(String city) {
        SeleniumHelper.waitForElementToBeClickable(webDriver, cityFromButton);
        cityFromButton.click();
        SeleniumHelper.waitForElementToBeClickable(webDriver, cityFromInput);
        cityFromInput.sendKeys(city);
        SeleniumHelper.waitForElementToBeClickable(webDriver, selectedCityButton);
        selectedCityButton.click();
        SeleniumHelper.waitForElementToBeClickable(webDriver, cityToButton);
        cityToButton.click();
        return this;
    }

    public FlightSearchPage setArrivalAirport(String city) {
        SeleniumHelper.waitForElementToBeClickable(webDriver, cityToInput);
        cityToInput.sendKeys(city);
        SeleniumHelper.waitForElementToBeClickable(webDriver, selectedCityToButton);
        selectedCityToButton.click();
        SeleniumHelper.waitForElementToBeClickable(webDriver, depart);
        depart.click();
        return this;
    }

    public FlightSearchPage setDepartureDate() {
        nextMonth.click();
        webDriver.findElements(By.xpath("//td[@class='day ' and contains(text(), '29')]"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        return this;
    }

    public boolean checkReturnInput() {
        return returnInput.isEnabled();
    }

    public FlightSearchPage setRoundTrip() throws InterruptedException {
   //     SeleniumHelper.waitForElementToBeClickable(webDriver, roundTrip);
        Actions actions = new Actions(webDriver);
        Thread.sleep(1000);
        actions.moveToElement(webDriver.findElement(By.xpath("//input[@value='round']"))).click().perform();
    //    try {
    //        webDriver.findElement(By.xpath("//input[@value='round']")).click();
    //    } catch (Exception e) {
     //       JavascriptExecutor executor = (JavascriptExecutor) webDriver;
    //        executor.executeScript("arguments[0].click();", webDriver.findElement(By.id("round")));
    //        Thread.sleep(4000);
     //   }
        return this;
    }

    public FlightSearchPage setPassengers(String adultsToAdd, String childToAdd, String infant) {
        String currentWindow = webDriver.getWindowHandle();

        try {
            webDriver.findElement(By.name("totalManualPassenger")).click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("arguments[0].click();", webDriver.findElement(By.name("totalManualPassenger")));
        }
        Set<String> windowNames = webDriver.getWindowHandles();
        for (String window : windowNames
        ) {
            if (!window.equals(currentWindow)) {
                webDriver.switchTo().window(window);
            }
        }
        Select select1 = new Select(adultsSelect);
        select1.selectByVisibleText(adultsToAdd);
        Select select2 = new Select(childSelect);
        select2.selectByVisibleText(childToAdd);
        Select select3 = new Select(infantSelect);
        select3.selectByVisibleText(infant);
        passengerButton.click();
        return this;
    }

    public FlightSearchPage setCabinClass(String cabin){
        cabinClass.click();
        Select select = new Select(cabinClass);
        select.selectByVisibleText(cabin);
        return this;
    }

    public FlightResultPage performSearch(){
        searchButton.click();
        return new FlightResultPage(webDriver);
    }

    public FlightSearchPage setNextMonth(){
        flightsButton.click();
        depart.click();

        return this;
    }
}
