package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import pojo.CourierPojo;
import pojo.LoginPojo;

import static io.restassured.RestAssured.given;

public class CourierClient extends InitialRequest {
    private static final String COURIER = "/api/v1/courier";
    private static final String COURIER_LOGIN = "/api/v1/courier/login";
    private static final String COURIER_DELETE = "/api/v1/courier/{id}";

    @Step("Создаем курьера")
    public ValidatableResponse createCourier(CourierPojo courierPojo) {
        return given().spec(getDefaultRequestSpec()).body(courierPojo).post(COURIER).then();
    }

    @Step("Логинимся под курьером")
    public ValidatableResponse loginCourier(LoginPojo loginPojo) {
        return given().spec(getDefaultRequestSpec()).body(loginPojo).post(COURIER_LOGIN).then();
    }

    @Step("Удаляем курьера")
    public ValidatableResponse deleteCourier(int id) {
        return given().spec(getDefaultRequestSpec()).delete(COURIER_DELETE, id).then();
    }
}
