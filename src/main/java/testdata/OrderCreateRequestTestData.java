package testdata;

import io.qameta.allure.Step;
import pojo.OrderCreateRequest;

public class OrderCreateRequestTestData {
	@Step("Creating an order with the correct ingredients")
	public static OrderCreateRequest getOrderWithIngredients() {
		OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
		orderCreateRequest.setIngredients(new String[]{"61c0c5a71d1f82001bdaaa72", "61c0c5a71d1f82001bdaaa74", "61c0c5a71d1f82001bdaaa6c"});
		return orderCreateRequest;
	}

	@Step("Creating an order without ingredients")
	public static OrderCreateRequest getOrderWithoutIngredients() {
		OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
		orderCreateRequest.setIngredients(new String[]{});
		return orderCreateRequest;
	}

	@Step("Creating an order with incorrect ingredients")
	public static OrderCreateRequest getOrderWithIncorrectIngredients() {
		OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
		orderCreateRequest.setIngredients(new String[]{"Incorrect61c0c5a71d1f82001bdaaa72", "Incorrect61c0c5a71d1f82001bdaaa74", "Incorrect61c0c5a71d1f82001bdaaa6c"});
		return orderCreateRequest;
	}
}
