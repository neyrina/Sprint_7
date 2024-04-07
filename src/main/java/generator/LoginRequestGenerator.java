package generator;

import pojo.CourierPojo;
import pojo.LoginPojo;

public class LoginRequestGenerator {

    public static LoginPojo getLogin(CourierPojo courierRequest) {
        LoginPojo loginPojo = new LoginPojo();
        loginPojo.setLogin(courierRequest.getLogin());
        loginPojo.setPassword(courierRequest.getPassword());
        return loginPojo;
    }
}
