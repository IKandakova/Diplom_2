package testdata;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import pojo.LoginRequest;
import pojo.UserRequest;

public class LoginRequestTestData {
	@Step("Authorization of a registered client")
	public static LoginRequest from(UserRequest userRequest) {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(userRequest.getEmail());
		loginRequest.setPassword(userRequest.getPassword());
		return loginRequest;
	}

	@Step("Authorization with an incorrect username and password")
	public static LoginRequest invalidLoginPassword() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(RandomStringUtils.randomAlphanumeric(5));
		loginRequest.setPassword(RandomStringUtils.randomAlphanumeric(5));
		return loginRequest;
	}
}
