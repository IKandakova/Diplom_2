package testdata;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.LoginRequest;
import pojo.UserRequest;

public class LoginRequestTestData {

    public static LoginRequest from(UserRequest userRequest) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(userRequest.getEmail());
        loginRequest.setPassword(userRequest.getPassword());
        return loginRequest;

    }

    public static LoginRequest invalidLoginPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(RandomStringUtils.randomAlphanumeric(5));
        loginRequest.setPassword(RandomStringUtils.randomAlphanumeric(5));
        return loginRequest;
    }

}
