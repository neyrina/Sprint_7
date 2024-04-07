package client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import config.Config;

public class InitialRequest {
    public RequestSpecification getDefaultRequestSpec() {
        return new RequestSpecBuilder().setBaseUri(Config.getBaseUrl()).setContentType(ContentType.JSON).build();
    }
}
