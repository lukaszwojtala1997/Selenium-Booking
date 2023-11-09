package seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import seleniumdemo.pages.HotelSearchPage;
import seleniumdemo.pages.ResultsPage;
import seleniumdemo.utils.ExcelReader;
import seleniumdemo.utils.SeleniumHelper;
import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest {


    @Test
    public void searchHotelTest() {
        ExtentTest test = extentReports.createTest("Search hotel test");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(webDriver);

        List<String> hotelNames = hotelSearchPage
                .setCity("Dubai")
                .setDates("25/05/2023", "27/05/2023")
                .setTravellers(1, 2)
                .performSearch().getHotelNames();

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");

        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void hotelSearchWithoutNameTest(){
        ExtentTest test = extentReports.createTest("Search hotel without city test");

        ResultsPage resultsPage = new HotelSearchPage(webDriver)
                .setDates("17/05/2023", "30/05/2023")
                .setTravellers(1, 2)
                .performSearch();


        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");

        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "data")
    public void searchHotelTestWithDataProviderTest(String city, String hotel) {
        ExtentTest test = extentReports.createTest("Search hotel test");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(webDriver);

        List<String> hotelNames = hotelSearchPage
                .setCity(city)
                .setDates("25/05/2023", "27/05/2023")
                .setTravellers(1, 2)
                .performSearch().getHotelNames();

        Assert.assertEquals(hotelNames.get(0), hotel);

        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchHotelTestWithPropertiesTest(){
        ExtentTest test = extentReports.createTest("Search hotel test");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(webDriver);

        List<String> hotelNames = hotelSearchPage
                .setCity("Dubai")
                .setDates("25/05/2023", "27/05/2023")
                .setTravellers(1, 2)
                .performSearch()
                .setProperties()
                .getHotelNames();

        Assert.assertEquals(hotelNames.get(0), "Oasis Beach Tower");

        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcel("testData.xlsx");
    }
    }

