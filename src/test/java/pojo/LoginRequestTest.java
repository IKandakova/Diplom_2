package pojo;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import testdata.LoginRequestTestData;
import pageobject.UserPageObject;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static testdata.UserRequestTestData.getUserRequestAllRequiredField;

public class LoginRequestTest {
	UserPageObject userPageObject;
	UserRequest userRequest;
	String accessToken;

	@After
	public void deleteUser() {
		if (accessToken != null) {
			userPageObject.delete(accessToken);
		}
	}

	@Test
	@DisplayName("Login under an existing user")
	@Description("To check the authorization under an existing user: the request returns the response code 200 and success: true")
	public void loginUnderExistingUser() {
		userPageObject = new UserPageObject();
		userRequest = getUserRequestAllRequiredField();
		userPageObject.create(userRequest);
		LoginRequest loginRequest = LoginRequestTestData.from(userRequest);
		accessToken = userPageObject.login(loginRequest)
				.then()
				.statusCode(200)
				.and()
				.assertThat().body("success", equalTo(true))
				.and()
				.body("accessToken", notNullValue())
				.extract()
				.path("accessToken");
	}

	@Test
	@DisplayName("Login with invalid username and password")
	@Description("To check the authorization under the wrong username and password: the request returns the 401 response code and message: email or password are incorrect")
	public void loginWithInvalidUsernameAndPassword() {
		LoginRequest loginRequest = LoginRequestTestData.invalidLoginPassword();
		userPageObject = new UserPageObject();
		Response response = userPageObject.login(loginRequest);
		response.then()
				.statusCode(401)
				.and()
				.assertThat().body("message", equalTo("email or password are incorrect"));
	}
}