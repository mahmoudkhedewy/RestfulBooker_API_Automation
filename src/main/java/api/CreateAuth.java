package api;

import body.CreateAuthBody;
import builder.RequestBuilderSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateAuth {
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
}
