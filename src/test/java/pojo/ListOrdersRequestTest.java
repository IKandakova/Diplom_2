package pojo;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import object.OrderObject;
import object.UserObject;
import org.junit.Test;

import static config.Config.getBaseUri;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static testdata.UserRequestTestData.getUserRequestAllRequiredField;

public class ListOrdersRequestTest {
    @Test
    @DisplayName("Getting a list of orders with authorization")
    @Step("To check that the list of orders is returned to the response body and the request returns the correct response code 200")
    public void getListOrderWithAuth(){
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

        OrderObject orderObject = new OrderObject();
        Response response = orderObject.getOrdersUserWithAuth(accessToken);
        response.then().statusCode(200)
                .and()
                .assertThat().body("success", equalTo(true));

        userObject.delete(accessToken);
    }

    @Test
    @DisplayName("Getting a list of orders without authorization")
    @Step("To check the receipt of orders from an unauthorized user: the request returns the 401 response code and message: You should be authorized")
    public void getListOrderWithoutAuth(){
        // отправл€ем запрос и сохран€ем ответ в переменную response, экземпл€р класса Response
        OrderObject orderObject = new OrderObject();
        Response response = orderObject.getOrdersUserWithoutAuth();
        response.then().statusCode(401)
                .and()
                .assertThat().body("message", equalTo("You should be authorised"));

    }

}
