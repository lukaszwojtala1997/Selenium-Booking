package seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumdemo.pages.HotelSearchPage;
import seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;

public class ChangeLanguageTest extends BaseTest{

    @Test
    public void changeLanguageToSpanishTest() {
        ExtentTest test = extentReports.createTest("Change language to Spanish");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(webDriver)
                .changeLanguage();

        Assert.assertEquals(hotelSearchPage.getHomeTextInSpanish(), "CASA");

        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
