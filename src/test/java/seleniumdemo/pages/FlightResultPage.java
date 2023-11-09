package seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightResultPage {

    WebDriver webDriver;

    public FlightResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.webDriver = driver;
    }

    @FindBy(xpath = "//div[@class='panel-heading' and contains(text(), 'Dates Availability')]")
    private WebElement datesAvailability;

    @FindBy(xpath = "//h2[@class='destination-title go-right ttu']")
    private WebElement featuredHotel;


    public String getDatesAvailability(){
        return datesAvailability.getText();
    }

    public String getFeaturedHotel(){
        return featuredHotel.getText();
    }
}
