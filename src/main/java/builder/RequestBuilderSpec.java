package builder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RequestBuilderSpec {

    public static RequestSpecification buildRequest(Map<String, String> extraHeaders) {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.addHeader("Content-Type", "application/json");
        builder.addHeader("Accept", "application/json");

        if (extraHeaders != null) {
            for (Map.Entry<String, String> entry : extraHeaders.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }
}
