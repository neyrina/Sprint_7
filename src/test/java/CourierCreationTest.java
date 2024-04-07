import client.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.CourierPojo;
import pojo.LoginPojo;

import static generator.CourierRequestGenerator.*;
import static generator.LoginRequestGenerator.getLogin;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;


public class CourierCreationTest {

    private CourierClient courierClient;
    private Integer id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void cleanUp() {
        if (id != null) {
            courierClient.deleteCourier(id).assertThat().statusCode(SC_OK).and().body("ok", equalTo(true));
        }
    }

    @Test
    @DisplayName("Проверка создания курьера")
    public void courierShouldBeCreatedTest() {
        CourierPojo randomCourier = getCourierWithoutFirstName();
        courierClient.createCourier(randomCourier).assertThat().statusCode(SC_CREATED).and().body("ok", equalTo(true));
        LoginPojo login = getLogin(randomCourier);
        id = courierClient.loginCourier(login).assertThat().statusCode(SC_OK).and().body("id", notNullValue()).extract().path("id");
    }

    @Test
    @DisplayName("Проверка дублирования при создании курьера")
    public void courierShouldNotBeCreatedWithNotUniqueLoginTest() {
        CourierPojo randomCourier = getRandomCourier();
        courierClient.createCourier(randomCourier).assertThat().statusCode(SC_CREATED).and().body("ok", equalTo(true));

        LoginPojo login = getLogin(randomCourier);
        id = courierClient.loginCourier(login).assertThat().statusCode(SC_OK).and().body("id", notNullValue()).extract().path("id");

        courierClient.createCourier(randomCourier).assertThat().statusCode(SC_CONFLICT).and().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Создание курьера с пустым login")
    public void courierShouldNotBeCreatedWithLoginIsNullTest() {

        CourierPojo courierPojo = getCourierWithoutLogin();
        courierClient.createCourier(courierPojo).assertThat().statusCode(SC_BAD_REQUEST).and().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера с пустым password")
    public void courierShouldNotBeCreatedWithPasswordIsNullTest() {

        CourierPojo courierPojo = getCourierWithoutPassword();
        courierClient.createCourier(courierPojo).assertThat().statusCode(SC_BAD_REQUEST).and().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера с пустым firstName")
    public void courierShouldBeCreatedWithFirstNameIsNullTest() {
        CourierPojo randomCourier = getRandomCourier();
        courierClient.createCourier(randomCourier).assertThat().statusCode(SC_CREATED).and().body("ok", equalTo(true));

        LoginPojo login = getLogin(randomCourier);
        id = courierClient.loginCourier(login).assertThat().statusCode(SC_OK).and().body("id", notNullValue()).extract().path("id");
    }
}
