package seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumdemo.pages.FlightResultPage;
import seleniumdemo.pages.FlightSearchPage;
import seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;

public class FlightSearchTest extends BaseTest {

    @Test
    public void oneWayFlightSearchWithBusinessClassTest() {
        ExtentTest test = extentReports.createTest("Change language to Spanish");

        FlightResultPage flightResultPage = new FlightSearchPage(webDriver)
                .setFlight()
                .setDepartureAirport("Dubai")
                .setArrivalAirport("Kato")
                .setDepartureDate()
                .setPassengers("1", "1", "1")
                .setCabinClass("Business")
                .performSearch();

        FlightSearchPage flightSearchPage = new FlightSearchPage(webDriver);
        boolean checkReturn = flightSearchPage.checkReturnInput();

        Assert.assertEquals(flightResultPage.getDatesAvailability(), "DATES AVAILABILITY");
        Assert.assertFalse(checkReturn);

        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void flightSearchWithoutDepartureDateTest() {
        ExtentTest test = extentReports.createTest("Flight search without departure date");

        FlightResultPage flightResultPage = new FlightSearchPage(webDriver)
                .setFlight()
                .setDepartureAirport("Dubai")
                .setArrivalAirport("Kato")
                .performSearch();

        Assert.assertEquals(flightResultPage.getFeaturedHotel(), "FEATURED HOTELS");

        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
