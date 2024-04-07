import client.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pojo.OrderPojo;

import static generator.OrderRequestGenerator.getCreateOrder;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreationTest {
    private OrderClient orderClient;
    private String[] color;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    public OrderCreationTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getColor() {
        return new Object[][]{{new String[]{"BLACK"}}, {new String[]{"GRAY"}}, {new String[]{"BLACK", "GRAY"}}, {new String[]{""}}};
    }

    @Test
    @DisplayName("Создание заказа с выбором цветов и без")
    public void createOrdersTest() {
        OrderPojo order = getCreateOrder(color);
        orderClient.createOrder(order).assertThat().statusCode(SC_CREATED).and().body("track", notNullValue());
    }
}
