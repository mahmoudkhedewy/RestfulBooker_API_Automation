package tests;

import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class GetBookingCreatedByID extends TestBase{
    int bookingID;
    @Test(priority = 1)
    public void createBooking() throws Exception {
        Response response = createBooking.createBook(
                createBookingBodyData.getData("firstname"),
                createBookingBodyData.getData("lastname"),
                Integer.parseInt(createBookingBodyData.getData("totalprice")),
                Boolean.parseBoolean(createBookingBodyData.getData("depositpaid")),
                createBookingBodyData.getData("checkin"),
                createBookingBodyData.getData("checkout"),
                createBookingBodyData.getData("additionalneeds")
        ).then().extract().response();

        System.out.println(response.asString());

        String firstname = response.jsonPath().getString("booking.firstname");
        bookingID = response.jsonPath().getInt("bookingid");

        MatcherAssert.assertThat(response.statusCode(), Matchers.equalTo(200));
        MatcherAssert.assertThat(firstname,
                Matchers.equalTo(createBookingBodyData.getData("firstname")));
    }

    @Test(priority = 2)
    public void getCreatedBookingById_functionalTest() throws FileNotFoundException {
        Response response = getBookingCreatedByID.getBookingCreatedById(bookingID)
                .then().extract().response();

        System.out.println(response.asString());
        String firstname = response.jsonPath().getString("firstname");


        MatcherAssert.assertThat(response.statusCode(), Matchers.equalTo(200));
        MatcherAssert.assertThat(firstname,
                Matchers.equalTo(createBookingBodyData.getData("firstname")));
    }

    @Test(priority = 2)
    public void getCreatedBookingById_performanceTest() throws FileNotFoundException {
        Response response = getBookingCreatedByID.getBookingCreatedById(bookingID)
                .then().extract().response();


        MatcherAssert.assertThat(response.time(), Matchers.lessThan(2000L));
    }

    @Test(priority = 2)
    public void getCreatedBookingById_contractTest() {
        Response response = getBookingCreatedByID.getBookingCreatedById(bookingID)
                .then().extract().response();


        MatcherAssert.assertThat(response.getHeader("Content-Type"),
                Matchers.equalTo("application/json; charset=utf-8"));
    }
}
