package api;

import body.BookingDates;
import body.CreateBookingBody;
import builder.RequestBuilderSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateBooking {

    public Response createBook(String firstname, String lastname, int totalprice, boolean
            depositapid, String checkin, String checkout, String additionalneeds){

        CreateBookingBody createBookingBody = new CreateBookingBody();
        createBookingBody.setFirstname(firstname);
        createBookingBody.setLastname(lastname);
        createBookingBody.setTotalprice(totalprice);
        createBookingBody.setDepositpaid(depositapid);
        createBookingBody.setAdditionalneeds(additionalneeds);
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(checkin);
        bookingDates.setCheckout(checkout);
        createBookingBody.setBookingdates(bookingDates);

        return RestAssured.given().log().all()
                .spec(RequestBuilderSpec.buildRequest(null))
                .body(createBookingBody).when().post("/booking");

    }
}
