package pojo;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import pageobject.OrderPageObject;
import pageobject.UserPageObject;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static testdata.UserRequestTestData.getUserRequestAllRequiredField;

public class ListOrdersRequestTest {
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
	@DisplayName("Getting a list of orders with authorization")
	@Description("To check that the list of orders is returned to the response body and the request returns the correct response code 200")
	public void getListOrderWithAuth() {
		userPageObject = new UserPageObject();
		userRequest = getUserRequestAllRequiredField();
		accessToken = userPageObject.create(userRequest)
				.then()
				.assertThat()
				.statusCode(200)
				.and()
				.body("accessToken", notNullValue())
				.extract()
				.path("accessToken");

		OrderPageObject orderPageObject = new OrderPageObject();
		Response response = orderPageObject.getOrdersUserWithAuth(accessToken);
		response.then().statusCode(200)
				.and()
				.assertThat().body("success", equalTo(true));
	}

	@Test
	@DisplayName("Getting a list of orders without authorization")
	@Description("To check the receipt of orders from an unauthorized user: the request returns the 401 response code and message: You should be authorized")
	public void getListOrderWithoutAuth() {
		OrderPageObject orderPageObject = new OrderPageObject();
		Response response = orderPageObject.getOrdersUserWithoutAuth();
		response.then().statusCode(401)
				.and()
				.assertThat().body("message", equalTo("You should be authorised"));
	}
}
