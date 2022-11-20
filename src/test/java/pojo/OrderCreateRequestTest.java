package pojo;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import pageobject.OrderPageObject;
import org.junit.Test;
import pageobject.UserPageObject;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static testdata.OrderCreateRequestTestData.*;
import static testdata.UserRequestTestData.getUserRequestAllRequiredField;

public class OrderCreateRequestTest {
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
	@DisplayName("Creating an order with authorization and with ingredients")
	@Description("To check the creation of an order under an existing user and with ingredients: the request returns the response code 200 and success: true")
	public void createOrderWithAuthAndIngredients() {
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

		OrderCreateRequest orderCreateRequest = getOrderWithIngredients();
		OrderPageObject orderPageObject = new OrderPageObject();
		Response response = orderPageObject.createOrderWithAuth(orderCreateRequest, accessToken);
		response.then()
				.statusCode(200)
				.and()
				.assertThat().body("success", equalTo(true));
	}

	@Test
	@DisplayName("Creating an order without authorization and with ingredients")
	@Description("To check the creation of an order under an existing user and with ingredients: the request returns the response code 200 and success: true")
	public void createOrderWithoutAuth() {
		OrderCreateRequest orderCreateRequest = getOrderWithIngredients();
		OrderPageObject orderPageObject = new OrderPageObject();
		Response response = orderPageObject.createOrderWithoutAuth(orderCreateRequest);
		response.then()
				.statusCode(200)
				.and()
				.assertThat().body("success", equalTo(true));
	}

	@Test
	@DisplayName("Creating an order with authorization and without ingredients")
	@Description("To check the creation of an order under an existing user without ingredients: the request returns the response code 400 and message: Ingredient ids must be provided")
	public void createOrderWithoutIngredients() {
		userPageObject = new UserPageObject();
		userRequest = getUserRequestAllRequiredField();
		Response responseCreate = userPageObject.create(userRequest);
		accessToken = responseCreate
				.then()
				.assertThat()
				.statusCode(200)
				.and()
				.body("accessToken", notNullValue())
				.extract()
				.path("accessToken");

		OrderCreateRequest orderCreateRequest = getOrderWithoutIngredients();
		OrderPageObject orderPageObject = new OrderPageObject();
		Response response = orderPageObject.createOrderWithAuth(orderCreateRequest, accessToken);
		response.then()
				.statusCode(400)
				.and()
				.assertThat().body("message", equalTo("Ingredient ids must be provided"));
	}

	@Test
	@DisplayName("Creating an order with authorization and with an incorrect hash of ingredients")
	@Description("To check the creation of an order under an existing user with an invalid hash of ingredients: the request returns a response code of 500")
	public void createOrderWithIncorrectIngredients() {
		userPageObject = new UserPageObject();
		userRequest = getUserRequestAllRequiredField();
		Response responseCreate = userPageObject.create(userRequest);
		accessToken = responseCreate
				.then()
				.assertThat()
				.statusCode(200)
				.and()
				.body("accessToken", notNullValue())
				.extract()
				.path("accessToken");

		OrderCreateRequest orderCreateRequest = getOrderWithIncorrectIngredients();
		OrderPageObject orderPageObject = new OrderPageObject();
		Response response = orderPageObject.createOrderWithAuth(orderCreateRequest, accessToken);
		response.then()
				.statusCode(500);
	}
}