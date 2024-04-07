package generator;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import pojo.OrderPojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderRequestGenerator {
    public static OrderPojo getCreateOrder(String[] color) {
        OrderPojo orderPojo = new OrderPojo();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        orderPojo.setFirstName(RandomStringUtils.randomAlphabetic(10));
        orderPojo.setLastName(RandomStringUtils.randomAlphabetic(10));
        orderPojo.setAddress(RandomStringUtils.randomAlphabetic(10));
        orderPojo.setMetroStation(RandomStringUtils.randomAlphabetic(10));
        orderPojo.setPhone("+7 900 355 35 35");
        orderPojo.setRentTime(RandomUtils.nextInt(1, 20));
        orderPojo.setDeliveryDate("2020-06-06");
        orderPojo.setComment(RandomStringUtils.randomAlphabetic(10));
        orderPojo.setColor(color);

        return orderPojo;
    }
}
