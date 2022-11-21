package pageobject;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.LoginRequest;
import pojo.UpdateUserRequest;
import pojo.UserRequest;

import static config.Config.getBaseUri;
import static io.restassured.RestAssured.given;

public class UserPageObject {
	@Step("Client registration")
	public Response create(UserRequest userRequest) {
		return given()
				.header("Content-type", "application/json")
				.baseUri(getBaseUri())
				.body(userRequest)
				.post("auth/register");
	}

	@Step("Client authorization")
	public Response login(LoginRequest loginRequest) {
		return given()
				.header("Content-type", "application/json")
				.baseUri(getBaseUri())
				.body(loginRequest)
				.post("auth/login");
	}

	@Step("Deleting a client")
	public void delete(String accessToken) {
		given()
				.header("Authorization", accessToken)
				.header("Content-type", "application/json")
				.baseUri(getBaseUri())
				.delete("auth/user");
	}

	@Step("Changing a client with authorization")
	public Response updateWithAuth(UpdateUserRequest updateUserRequest, String accessToken) {
		return given()
				.header("Authorization", accessToken)
				.header("Content-type", "application/json")
				.baseUri(getBaseUri())
				.body(updateUserRequest)
				.patch("auth/user");
	}
}
