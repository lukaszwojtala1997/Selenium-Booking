package seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumdemo.model.GuestUser;
import seleniumdemo.pages.BookingHotelPage;
import seleniumdemo.pages.BookingHotelResultPage;
import seleniumdemo.pages.HotelSearchPage;
import seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;

public class BookingHotelTest extends BaseTest {

    @Test
    public void setBookingHotelTest(){
        ExtentTest test = extentReports.createTest("Set booking hotel");

        int randomNumber = (int) (Math.random() * 1000);
        String email = "sprawdzanieaplikacji" + randomNumber + "@tester.pl";

        GuestUser guestUser = new GuestUser("Bartek", "Testowy", email, email,
                "111111111", "Katowice", "Poland");
        BookingHotelPage bookingHotelPage = new HotelSearchPage(webDriver)
                .bookingHotel()
                .setDate()
                .setPassengers("2", "2", "1")
                .bookNow()
                .setFirstName(guestUser.getFirstName())
                .setLastName(guestUser.getLastName())
                .setEmail(guestUser.getEmail())
                .setConfirmEmail(guestUser.getConfirmEmail())
                .setPhone(guestUser.getPhone())
                .setAddress(guestUser.getAddress())
                .setCountry(guestUser.getCountry());

        bookingHotelPage.confirmBooking();
        BookingHotelResultPage bookingHotelResultPage = new BookingHotelResultPage(webDriver);


        Assert.assertEquals(bookingHotelResultPage.unpaid(), "UNPAID");

        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setBookingHotelBySignInTest() throws InterruptedException {
        ExtentTest test = extentReports.createTest("Set booking hotel by sign in");

        BookingHotelPage bookingHotelPage = new HotelSearchPage(webDriver)
                .bookingHotel()
                .setDate()
                .setPassengers("2", "2", "1")
                .bookNow()
                .signIn()
                .fillPersonalDetails("testowy1@testowy.pl", "Testowy123@");

        bookingHotelPage.confirmBookingSignIn();
        BookingHotelResultPage bookingHotelResultPage = new BookingHotelResultPage(webDriver);

        Assert.assertEquals(bookingHotelResultPage.unpaid(), "UNPAID");

        try {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(webDriver));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
