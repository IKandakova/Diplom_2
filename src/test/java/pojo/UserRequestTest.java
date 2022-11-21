package pojo;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import pageobject.UserPageObject;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static testdata.UserRequestTestData.getUserRequestAllRequiredField;
import static testdata.UserRequestTestData.getUserRequestWithoutRequiredField;

public class UserRequestTest {
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
	@DisplayName("Creating a unique user")
	@Description("Checking the user creation: the request returns the response code 200 and success: true")
	public void createUser() {
		userPageObject = new UserPageObject();
		userRequest = getUserRequestAllRequiredField();
		accessToken = userPageObject.create(userRequest)
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
	@DisplayName("Creating a user who is already registered")
	@Description("To check the creation of a user who is already registered: the request returns the response code 403 and message: User already exists")
	public void createUserDuplicate() {
		userPageObject = new UserPageObject();
		userRequest = getUserRequestAllRequiredField();
		accessToken = userPageObject.create(userRequest)
				.then()
				.statusCode(200)
				.and()
				.assertThat().body("success", equalTo(true))
				.and()
				.body("accessToken", notNullValue())
				.extract()
				.path("accessToken");

		Response response = userPageObject.create(userRequest);
		response.then()
				.statusCode(403)
				.and()
				.assertThat().body("message", equalTo("User already exists"));
	}

	@Test
	@DisplayName("Creating a user and not filling in one of the required fields")
	@Description("We check the creation of the user, if you do not fill in the required field: the request returns the 403 response code and message: Email, password and name are required fields")
	public void createUserWithoutRequiredField() {
		userPageObject = new UserPageObject();
		userRequest = getUserRequestWithoutRequiredField();
		Response response = userPageObject.create(userRequest);
		response.then()
				.statusCode(403)
				.and()
				.assertThat().body("message", equalTo("Email, password and name are required fields"));
	}
}