package seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import seleniumdemo.pages.HotelSearchPage;
import seleniumdemo.pages.LoggedUserPage;
import seleniumdemo.pages.ResultsPage;
import seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;

public class LoginTest extends BaseTest{

    @DataProvider
    public Object[][] getData(){
        Object[][] data = new Object[2][2];
        data[0][0] = "testowy1@testowy.pl";
        data[0][1] = "Testowy123@";

        data[1][0] = "testowy2@testowy.pl";
        data[1][1] = "Testowy1234@";

        return data;
    }



    @Test(dataProvider = "getData")
    public void logInTest(String username, String password){
        ExtentTest test = extentReports.createTest("LogIn");

        LoggedUserPage loginUserPage = new HotelSearchPage(webDriver)
                .openLoginForm()
                .fillLoginPersonalDetails(username, password);

        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addHotelToWishlistWhenUserIsLoggedTest(){
        ExtentTest test = extentReports.createTest("Add hotel to wish list when user is logged");

        LoggedUserPage loginUserPage = new HotelSearchPage(webDriver)
                .openLoginForm()
                .fillLoginPersonalDetails("testowy1@testowy.pl", "Testowy123@")
                .backToHomePage()
                .setCity("Dubai")
                .setDates("25/05/2023", "27/05/2023")
                .setTravellers(1, 2)
                .performSearch()
                .addHotelToWishlist()
                .openLoginForm()
                .getWishlist();

        Assert.assertEquals(loginUserPage.getHotelName(), "Jumeirah Beach Hotel");

        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addHotelToWishlistWhenUserIsNoLoggedTest() {
        ExtentTest test = extentReports.createTest("Add hotel to wish when user is no logged");

        ResultsPage loginUserPage = new HotelSearchPage(webDriver)
                .setCity("Dubai")
                .setDates("25/05/2023", "27/05/2023")
                .setTravellers(1, 2)
                .performSearch()
                .addHotelToWishlist();

                String alertText = loginUserPage.getAlertText();

        Assert.assertEquals(alertText, "Please Login to add to wishlist.");

        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
