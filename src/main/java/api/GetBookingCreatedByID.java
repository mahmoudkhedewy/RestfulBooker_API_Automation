package api;

import builder.RequestBuilderSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingCreatedByID {

    public Response getBookingCreatedById(int id){
        return RestAssured.given().log().all()
                .spec(RequestBuilderSpec.buildRequest(null))
                .when().pathParam("id",id).get("/booking/{id}");
    }
}
