package generator;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.CourierPojo;

public class CourierRequestGenerator {
    public static CourierPojo getRandomCourier() {
        CourierPojo courierRequest = new CourierPojo();
        courierRequest.setLogin(RandomStringUtils.randomAlphabetic(10));
        courierRequest.setPassword("1234");
        courierRequest.setFirstName("Vasya");
        return courierRequest;
    }

    public static CourierPojo getCourierWithoutLogin() {
        CourierPojo courierRequest = new CourierPojo();
        courierRequest.setLogin("");
        courierRequest.setPassword("1234");
        courierRequest.setFirstName("Vasya");
        return courierRequest;
    }

    public static CourierPojo getCourierWithoutPassword() {
        CourierPojo courierRequest = new CourierPojo();
        courierRequest.setLogin(RandomStringUtils.randomAlphabetic(10));
        courierRequest.setPassword("");
        courierRequest.setFirstName("Vasya");
        return courierRequest;
    }

    public static CourierPojo getCourierWithoutFirstName() {
        CourierPojo courierRequest = new CourierPojo();
        courierRequest.setLogin(RandomStringUtils.randomAlphabetic(10));
        courierRequest.setPassword("1234");
        return courierRequest;
    }
}
