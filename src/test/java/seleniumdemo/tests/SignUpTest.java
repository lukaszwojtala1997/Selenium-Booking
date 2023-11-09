package seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import seleniumdemo.model.RegisterUser;
import seleniumdemo.pages.HotelSearchPage;
import seleniumdemo.pages.SignedUpUserPage;
import seleniumdemo.pages.SignUpPage;
import seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class SignUpTest extends BaseTest {


    @Test
    public void signUpTest() {

        ExtentTest test = extentReports.createTest("Sign up test");
        int randomNumber = (int) (Math.random() * 10000);
        String email = "sprawdzanieaplikacji" + randomNumber + "@tester.pl";
        RegisterUser registerUser = new RegisterUser("Janusz", "Testowy", "111111111",
                email, "Test123", "Test123");

        String lastName = "Testowy";

        registerUser.setFirstName("Bartek");

        SignedUpUserPage hotelSearchPage = new HotelSearchPage(webDriver)
                .openSignUpForm()
                .fillSignUpForm(registerUser)
                .clickSignUp();

        Assert.assertTrue(hotelSearchPage.getHeadingText().contains(lastName));
        Assert.assertEquals(hotelSearchPage.getHeadingText(), "Hi, Bartek Testowy");
        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void signUpEmptyFormTest() {
        ExtentTest test = extentReports.createTest("Sign up empty form test");

        SignUpPage signUpPage = new HotelSearchPage(webDriver)
                .openSignUpForm();
        signUpPage.clickSignUp();

        List<String> errors = signUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void signUpInvalidEmailTest() {

        ExtentTest test = extentReports.createTest("Sign up with invalid email test");
        String email = "sprawdzanieaplikacji";

        RegisterUser registerUser = new RegisterUser("Janusz", "Testowy", "111111111",
                email, "Test123", "Test123");


        registerUser.setEmail(email);

        SignUpPage signUpPage = new HotelSearchPage(webDriver)
                .openSignUpForm()
                .fillSignUpForm(registerUser);

        signUpPage.clickSignUp();

        SeleniumHelper.waitForNotEmptyList(webDriver, By.xpath("//div[@class='alert alert-danger']//p"));
        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));
        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void signUpTestWithNoFirstName() {

        ExtentTest test = extentReports.createTest("Sign up test");
        int randomNumber = (int) (Math.random() * 10000);
        String email = "sprawdzanieaplikacji" + randomNumber + "@tester.pl";
        RegisterUser registerUser = new RegisterUser("Janusz", "Testowy", "111111111",
                email, "Test123", "Test123");

        registerUser.setFirstName("");


        SignedUpUserPage hotelSearchPage = new HotelSearchPage(webDriver)
                .openSignUpForm()
                .fillSignUpForm(registerUser)
                .clickSignUp();

        SignUpPage signUpPage = new SignUpPage(webDriver);
        List<String> errors = signUpPage.getErrors();


        Assert.assertTrue(errors.contains("The First name field is required."));
        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void signUpTestWithNoDifferentConfirmPassword() {

        ExtentTest test = extentReports.createTest("Sign up test");
        int randomNumber = (int) (Math.random() * 10000);
        String email = "sprawdzanieaplikacji" + randomNumber + "@tester.pl";
        RegisterUser registerUser = new RegisterUser("Janusz", "Testowy", "111111111",
                email, "Test123", "Test321");


        SignedUpUserPage hotelSearchPage = new HotelSearchPage(webDriver)
                .openSignUpForm()
                .fillSignUpForm(registerUser)
                .clickSignUp();

        SignUpPage signUpPage = new SignUpPage(webDriver);
        List<String> errors = signUpPage.getErrors();


        Assert.assertTrue(errors.contains("Password not matching with confirm password."));
        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
