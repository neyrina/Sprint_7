import client.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.CourierPojo;
import pojo.LoginPojo;

import static generator.CourierRequestGenerator.getRandomCourier;
import static generator.LoginRequestGenerator.getLogin;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginTest {
    private CourierClient courierClient;
    private CourierPojo randomCourier;
    private Integer id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        randomCourier = getRandomCourier();
        courierClient.createCourier(randomCourier).assertThat().statusCode(SC_CREATED).and().body("ok", equalTo(true));
    }

    @After
    public void clearUp() {
        if (id != null) {
            courierClient.deleteCourier(id).assertThat().statusCode(SC_OK).and().body("ok", equalTo(true));
        }
    }

    @Test
    @DisplayName("Авторизация курьера с корректными параметрами")
    public void courierShouldBeAuthorizedTest() {
        LoginPojo login = getLogin(randomCourier);
        id = courierClient.loginCourier(login).assertThat().statusCode(SC_OK).and().body("id", notNullValue()).extract().path("id");
    }

    @Test
    @DisplayName("Авторизация курьера с пустым login")
    public void courierShouldNotBeAuthorizedWithLoginIsEmptyTest() {
        LoginPojo login = getLogin(randomCourier);
        login.setLogin("");
        courierClient.loginCourier(login).assertThat().statusCode(SC_BAD_REQUEST).and().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера с пустым password")
    public void courierShouldNotBeAuthorizedWithPasswordIsEmptyTest() {
        LoginPojo loginRequest = getLogin(randomCourier);
        loginRequest.setPassword("");
        courierClient.loginCourier(loginRequest).assertThat().statusCode(SC_BAD_REQUEST).and().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера с неверным login")
    public void courierShouldNotBeAuthorizedWithLoginIsNotCorrectTest() {
        LoginPojo loginRequest = getLogin(randomCourier);
        loginRequest.setLogin("Tanya");
        courierClient.loginCourier(loginRequest).assertThat().statusCode(SC_NOT_FOUND).and().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера c неверным password")
    public void courierShouldNotBeAuthorizedWithPasswordIsNotCorrectTest() {
        LoginPojo loginRequest = getLogin(randomCourier);
        loginRequest.setPassword("999");
        courierClient.loginCourier(loginRequest).assertThat().statusCode(SC_NOT_FOUND).and().body("message", equalTo("Учетная запись не найдена"));
    }
}
