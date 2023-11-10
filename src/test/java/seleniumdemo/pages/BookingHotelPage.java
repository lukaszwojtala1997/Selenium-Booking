package seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import seleniumdemo.model.GuestUser;
import seleniumdemo.utils.SeleniumHelper;

public class BookingHotelPage {

    WebDriver webDriver;

    public BookingHotelPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(css = "[class='panel-heading panel-green hidden-xs']")
    private WebElement details;

    @FindBy(name = "date")
    private WebElement dateInput;

    @FindBy(css = "[class='next']")
    private WebElement nextMonth;

    @FindBy(name = "adults")
    private WebElement adultsSelect;

    @FindBy(name = "child")
    private WebElement childSelect;

    @FindBy(name = "infant")
    private WebElement infantSelect;

    @FindBy(css = "[class='totalCost']")
    private WebElement totalCost;

    @FindBy(id = "signintab")
    private WebElement signIn;

    @FindBy(css = "[class='btn btn-block btn-lg btn-danger pull-right loader']")
    private WebElement changeDateButton;

    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "confirmemail")
    private WebElement confirmEmailInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "address")
    private WebElement addressInput;

    @FindBy(id = "s2id_autogen2")
    private WebElement selectCountry;

    @FindBy(name = "extras[]")
    private WebElement returnAirTicketCheckbox;

    @FindBy(name = "guest")
    private WebElement confirmButton;

    @FindBy(css = "[class='btn btn-block btn-action btn-lg loader']")
    private WebElement bookNowButton;

    @FindBy(xpath = "//span[@class='select2-match']")
    private WebElement confirmCountry;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(name = "login")
    private WebElement confirmSignInButton;

    public BookingHotelPage setDate(){
        dateInput.click();
        nextMonth.click();
        webDriver.findElements(By.xpath("//td[@class='day ' and contains(text(), '29')]"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        changeDateButton.click();
        return this;
    }

    public BookingHotelPage setPassengers(String adultsToAdd, String childToAdd, String infant) {
        Select select1 = new Select(adultsSelect);
        select1.selectByVisibleText(adultsToAdd);
        Select select2 = new Select(childSelect);
        select2.selectByVisibleText(childToAdd);
        Select select3 = new Select(infantSelect);
        select3.selectByVisibleText(infant);
        return this;
    }

    public BookingHotelPage bookNow(){
        bookNowButton.click();
        return this;
    }

    public BookingHotelPage setFirstName(String firstName){
        firstNameInput.sendKeys(firstName);
        return this;
    }
    public BookingHotelPage setLastName(String lastName){
        lastNameInput.sendKeys(lastName);
        return this;
    }
    public BookingHotelPage setEmail(String email){
        emailInput.sendKeys(email);
        return this;
    }
    public BookingHotelPage setConfirmEmail(String confirmEmail){
        SeleniumHelper.waitForElementToBeClickable(webDriver, confirmEmailInput);
        confirmEmailInput.sendKeys(confirmEmail);
        return this;
    }
    public BookingHotelPage setPhone(String phone){
        phoneInput.sendKeys(phone);
        return this;
    }
    public BookingHotelPage setAddress(String address) {
        addressInput.sendKeys(address);
        return this;
    }
    public BookingHotelPage setCountry(String country){
        selectCountry.sendKeys(country);
        confirmCountry.click();
        return this;
    }

    public BookingHotelResultPage confirmBooking() {
        confirmButton.click();
        return new BookingHotelResultPage(webDriver);
    }

    public BookingHotelPage signIn(){
        signIn.click();
        return this;
    }

    public BookingHotelPage fillPersonalDetails(String usernameData, String passwordData){
        SeleniumHelper.waitForElementToBeClickable(webDriver, username);
        username.sendKeys(usernameData);
        SeleniumHelper.waitForElementToBeClickable(webDriver, password);
        password.sendKeys(passwordData);
        return this;
    }

    public BookingHotelResultPage confirmBookingSignIn() {
        confirmSignInButton.click();
        return new BookingHotelResultPage(webDriver);
    }

    public void fillPersonalForm(GuestUser guestUser){
        firstNameInput.sendKeys(guestUser.getFirstName());
        lastNameInput.sendKeys(guestUser.getLastName());
        emailInput.sendKeys(guestUser.getEmail());
        confirmEmailInput.sendKeys(guestUser.getConfirmEmail());
        phoneInput.sendKeys(guestUser.getPhone());
        addressInput.sendKeys(guestUser.getAddress());
        selectCountry.sendKeys(guestUser.getCountry());

    }

}
