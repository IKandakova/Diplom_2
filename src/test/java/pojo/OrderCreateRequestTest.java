package pojo;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import object.OrderObject;
import org.junit.Test;
import object.UserObject;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static testdata.OrderCreateRequestTestData.*;
import static testdata.UserRequestTestData.getUserRequestAllRequiredField;

public class OrderCreateRequestTest {

    @Test
    @DisplayName("Creating an order with authorization and with ingredients")
    @Step("To check the creation of an order under an existing user and with ingredients: the request returns the response code 200 and success: true")
    public void createOrderWithAuthAndIngredients(){
        UserObject userObject = new UserObject();
        UserRequest userRequest = getUserRequestAllRequiredField();

        String accessToken = userObject.create(userRequest)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken", notNullValue())
                .extract()
                .path("accessToken");

        OrderCreateRequest orderCreateRequest = getOrderWithIngredients();
        OrderObject orderObject = new OrderObject();
        Response response = orderObject.createOrderWithAuth(orderCreateRequest, accessToken);

        response.then()
                .statusCode(200)
                .and()
                .assertThat().body("success", equalTo(true));

        userObject.delete(accessToken);
    }

    @Test
    @DisplayName("Creating an order without authorization and with ingredients")
    @Step("To check the creation of an order under an existing user and with ingredients: the request returns the response code 200 and success: true")
    public void createOrderWithoutAuth(){

        OrderCreateRequest orderCreateRequest = getOrderWithIngredients();
        OrderObject orderObject = new OrderObject();
        Response response = orderObject.createOrderWithoutAuth(orderCreateRequest);

        response.then()
                .statusCode(200)
                .and()
                .assertThat().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Creating an order with authorization and without ingredients")
    @Step("To check the creation of an order under an existing user without ingredients: the request returns the response code 400 and message: Ingredient ids must be provided")
    public void createOrderWithoutIngredients(){
        UserObject userObject = new UserObject();
        UserRequest userRequest = getUserRequestAllRequiredField();
        Response responseCreate = userObject.create(userRequest);

        String accessToken = responseCreate
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken", notNullValue())
                .extract()
                .path("accessToken");

        OrderCreateRequest orderCreateRequest = getOrderWithoutIngredients();
        OrderObject orderObject = new OrderObject();
        Response response = orderObject.createOrderWithAuth(orderCreateRequest, accessToken);

        response.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Ingredient ids must be provided"));

        userObject.delete(accessToken);
    }

    @Test
    @DisplayName("Creating an order with authorization and with an incorrect hash of ingredients")
    @Step("To check the creation of an order under an existing user with an invalid hash of ingredients: the request returns a response code of 500")
    public void createOrderWithIncorrectIngredients(){
        UserObject userObject = new UserObject();
        UserRequest userRequest = getUserRequestAllRequiredField();
        Response responseCreate = userObject.create(userRequest);

        String accessToken = responseCreate
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken", notNullValue())
                .extract()
                .path("accessToken");

        OrderCreateRequest orderCreateRequest = getOrderWithIncorrectIngredients();
        OrderObject orderObject = new OrderObject();
        Response response = orderObject.createOrderWithAuth(orderCreateRequest, accessToken);

        response.then()
                .statusCode(500);

        userObject.delete(accessToken);
    }

}