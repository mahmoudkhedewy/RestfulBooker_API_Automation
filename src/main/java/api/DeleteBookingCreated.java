package api;

import builder.RequestBuilderSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class DeleteBookingCreated {

    public Response deleteBookingCreated(int id, String token){

        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "token=" + token);
        return RestAssured.given().log().all()
                .pathParams("id",id)
                .spec(RequestBuilderSpec.buildRequest(headers))
                .when().delete("/booking/{id}");
    }
}
