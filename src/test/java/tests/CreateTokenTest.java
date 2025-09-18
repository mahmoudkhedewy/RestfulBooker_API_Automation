package tests;

import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class CreateTokenTest extends TestBase{
    String token;
    @Test
    public void createToken_functionalTest() throws Exception {
        Response response = createAuth.getAuth(
                createAuthBodyData.getData("username"),
                createAuthBodyData.getData("password")
        ).then().extract().response();

        token = response.jsonPath().getString("token");
        System.out.println(response.asString());

        MatcherAssert.assertThat(token, Matchers.notNullValue());
        MatcherAssert.assertThat(response.statusCode(), Matchers.equalTo(200));
    }

    @Test
    public void createToken_performanceTest() throws Exception {
        Response response = createAuth.getAuth(
                createAuthBodyData.getData("username"),
                createAuthBodyData.getData("password")
        ).then().extract().response();
        MatcherAssert.assertThat(response.time(), Matchers.lessThan(2000L));
    }
}
