package testdata;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import pojo.UserRequest;

public class UserRequestTestData {
	@Step("Creating a client with filling in all required fields in the registration form")
	public static UserRequest getUserRequestAllRequiredField() {
		UserRequest userRequest = new UserRequest();
		userRequest.setEmail("irinakandakova225@yandex.ru");
		userRequest.setPassword("password");
		userRequest.setName("Name");
		return userRequest;
	}

	@Step("Creating a client without filling in all required fields in the registration form")
	public static UserRequest getUserRequestWithoutRequiredField() {
		UserRequest userRequest = new UserRequest();
		userRequest.setPassword("password");
		userRequest.setName("Name");
		return userRequest;
	}
}
