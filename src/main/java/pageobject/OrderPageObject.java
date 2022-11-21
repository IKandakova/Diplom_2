package pageobject;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.OrderCreateRequest;

import static config.Config.getBaseUri;
import static io.restassured.RestAssured.given;

public class OrderPageObject {
	@Step("Creating an order with authorization")
	public Response createOrderWithAuth(OrderCreateRequest orderCreateRequest, String accessToken) {
		return given()
				.header("Authorization", accessToken)
				.header("Content-type", "application/json")
				.baseUri(getBaseUri())
				.body(orderCreateRequest)
				.post("orders");
	}

	@Step("Creating an order without authorization")
	public Response createOrderWithoutAuth(OrderCreateRequest orderCreateRequest) {
		return given()
				.header("Content-type", "application/json")
				.baseUri(getBaseUri())
				.body(orderCreateRequest)
				.post("orders");
	}

	@Step("Get a list of orders with authorization")
	public Response getOrdersUserWithAuth(String accessToken) {
		return given()
				.baseUri(getBaseUri())
				.header("Authorization", accessToken)
				.header("Content-type", "application/json")
				.get("orders");
	}

	@Step("Get a list of orders without authorization")
	public Response getOrdersUserWithoutAuth() {
		return given()
				.baseUri(getBaseUri())
				.header("Content-type", "application/json")
				.get("orders");
	}
}
