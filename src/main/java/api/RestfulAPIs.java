package api;

import body.BookingDates;
import body.CreateAuthBody;
import body.CreateBookingBody;
import builder.RequestBuilderSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class RestfulAPIs {
    CreateAuthBody createAuthBody;


    public Response getAuth(String username, String password) {
        createAuthBody = new CreateAuthBody();
        createAuthBody.setUsername(username);
        createAuthBody.setPassword(password);

        return RestAssured.given().log().all()
                .spec(RequestBuilderSpec.buildRequest(null))
                .body(createAuthBody)
                .when().post("/auth");
    }

    public Response createBook(String firstname, String lastname,int totalprice,boolean
            depositapid,String checkin,String checkout,String additionalneeds){

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

    public Response getBookingCreatedById(int id){
        return RestAssured.given().log().all()
                .spec(RequestBuilderSpec.buildRequest(null))
                .when().pathParam("id",id).get("/booking/{id}");
    }
}
