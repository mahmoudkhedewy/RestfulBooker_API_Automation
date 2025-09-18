package api;

import body.BookingDates;
import body.CreateBookingBody;
import body.PutBookingBody;
import body.PutBookingDates;
import builder.RequestBuilderSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class PutCreatedBooking {

    public Response putCreatedBooking(String firstname, String lastname, int totalprice, boolean
            depositapid, String checkin, String checkout, String additionalneeds,String token, int id){

        PutBookingBody putBookingBody = new PutBookingBody();
        putBookingBody.setFirstname(firstname);
        putBookingBody.setLastname(lastname);
        putBookingBody.setTotalprice(totalprice);
        putBookingBody.setDepositpaid(depositapid);
        putBookingBody.setAdditionalneeds(additionalneeds);
        PutBookingDates putBookingDates = new PutBookingDates();
        putBookingDates.setCheckin(checkin);
        putBookingDates.setCheckout(checkout);
        putBookingBody.setBookingdates(putBookingDates);


        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "token=" + token);
        return RestAssured.given().log().all()
                .pathParams("id",id)
                .spec(RequestBuilderSpec.buildRequest(headers))
                .body(putBookingBody).when().put("/booking/{id}");

    }
}
