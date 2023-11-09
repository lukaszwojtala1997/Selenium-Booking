package seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumdemo.utils.SeleniumHelper;

public class LoginUserPage {

    WebDriver webDriver;

    public LoginUserPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(css = "[type='submit']")
    private WebElement loginButton;

    public LoggedUserPage fillLoginPersonalDetails(String usernameData, String passwordData){
        SeleniumHelper.waitForElementToBeClickable(webDriver, username);
        username.sendKeys(usernameData);
        SeleniumHelper.waitForElementToBeClickable(webDriver, password);
        password.sendKeys(passwordData);
        SeleniumHelper.waitForElementToBeClickable(webDriver, loginButton);
        loginButton.click();
        return new LoggedUserPage(webDriver);
    }


}
