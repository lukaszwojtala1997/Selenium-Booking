package seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumdemo.model.RegisterUser;
import seleniumdemo.utils.SeleniumHelper;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {

    private WebDriver webDriver;

    public SignUpPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> errors;

    public SignUpPage setFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public SignUpPage setLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public SignUpPage setPhone(String phone) {
        phoneInput.sendKeys(phone);
        return this;
    }

    public SignUpPage setEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public SignUpPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public SignUpPage setConfirmPassword(String confirmPassword) {
        confirmPasswordInput.sendKeys(confirmPassword);
        return this;
    }

    public SignedUpUserPage clickSignUp() {
        signUpButton.click();
        return new SignedUpUserPage(webDriver);
    }

    public List<String> getErrors() {
        SeleniumHelper.waitForNotEmptyList(webDriver, By.xpath("//div[@class='alert alert-danger']//p"));
        return errors.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public SignUpPage fillSignUpForm(RegisterUser user) {
        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        phoneInput.sendKeys(user.getPhone());
        emailInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        confirmPasswordInput.sendKeys(user.getConfirmPassword());
        signUpButton.click();
        return this;
    }



}
