package tests;

import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class EndToEndTest extends TestBase {
    int bookingID;
    String token;


    @Test
    public void createToken() throws Exception {
        Response response = createAuth.getAuth(
                createAuthBodyData.getData("username"),
                createAuthBodyData.getData("password")
        ).then().extract().response();

        token = response.jsonPath().getString("token");
        System.out.println(response.asString());

        MatcherAssert.assertThat(token, Matchers.notNullValue());
        MatcherAssert.assertThat(response.statusCode(), Matchers.equalTo(200));
    }

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
    public void getCreatedBookingById() throws FileNotFoundException {
        Response response = getBookingCreatedByID.getBookingCreatedById(bookingID)
                .then().extract().response();

        System.out.println(response.asString());
        String firstname = response.jsonPath().getString("firstname");


        MatcherAssert.assertThat(response.statusCode(), Matchers.equalTo(200));
        MatcherAssert.assertThat(firstname,
                Matchers.equalTo(createBookingBodyData.getData("firstname")));
    }
    @Test(priority = 3)
    public void putCreatedBooking() throws FileNotFoundException {
        Response response = putCreatedBooking.putCreatedBooking(
                putBookingBodyData.getData("firstname"),
                putBookingBodyData.getData("lastname"),
                Integer.parseInt(putBookingBodyData.getData("totalprice")),
                Boolean.parseBoolean(putBookingBodyData.getData("depositpaid")),
                putBookingBodyData.getData("checkin"),
                putBookingBodyData.getData("checkout"),
                putBookingBodyData.getData("additionalneeds"),
                token,
                bookingID
        ).then().extract().response();

        String firstname = response.jsonPath().getString("firstname");
        MatcherAssert.assertThat(response.statusCode(), Matchers.equalTo(200));
        MatcherAssert.assertThat(firstname,
                Matchers.equalTo(putBookingBodyData.getData("firstname")));
    }

    @Test(priority = 4)
    public void deleteCreatedBooking() {
        Response response = deleteBookingCreated.deleteBookingCreated(bookingID, token)
                .then().extract().response();
        MatcherAssert.assertThat(response.statusCode(), Matchers.equalTo(201));
    }

    @Test(priority = 5)
    public void getDeletedBookingById() {
        Response response = getBookingCreatedByID.getBookingCreatedById(bookingID)
                .then().extract().response();

        System.out.println(response.asString());
        MatcherAssert.assertThat(response.statusCode(), Matchers.equalTo(404));
        MatcherAssert.assertThat(response.asString(),
                Matchers.equalTo("Not Found"));
    }
}
