package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import pojo.OrderPojo;

import static io.restassured.RestAssured.given;

public class OrderClient extends InitialRequest {
    public static final String ORDER = "/api/v1/orders";

    @Step("Размещение заказа")
    public ValidatableResponse createOrder(OrderPojo order) {
        return given().spec(getDefaultRequestSpec()).body(order).post(ORDER).then();
    }

    @Step("Получить заказ по его номеру")
    public ValidatableResponse getOrderList(OrderPojo order) {
        return given().spec(getDefaultRequestSpec()).body(order).get(ORDER).then();
    }
}
