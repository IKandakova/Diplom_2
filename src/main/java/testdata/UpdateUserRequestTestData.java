package testdata;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import pojo.UpdateUserRequest;

public class UpdateUserRequestTestData {
	@Step("Changing user data")
	public static UpdateUserRequest getUserRequestAllUpdateRequiredField() {
		UpdateUserRequest updateUserRequest = new UpdateUserRequest();
		updateUserRequest.setEmail(RandomStringUtils.randomAlphabetic(10) + "@yandex.ru");
		updateUserRequest.setPassword("password");
		updateUserRequest.setName(RandomStringUtils.randomAlphabetic(5));
		return updateUserRequest;
	}
}
