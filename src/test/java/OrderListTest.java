import client.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pojo.OrderPojo;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {

    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Получение списка заказов")
    public void orderListNotBeEmpty() {
        OrderPojo order = new OrderPojo();
        orderClient.getOrderList(order).assertThat().statusCode(SC_OK).and().body("orders", notNullValue());
    }
}
