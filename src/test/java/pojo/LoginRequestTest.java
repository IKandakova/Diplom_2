package pojo;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import testdata.LoginRequestTestData;
import object.UserObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static testdata.UserRequestTestData.getUserRequestAllRequiredField;

public class LoginRequestTest {

    @Test
    @DisplayName("Login under an existing user")
    @Step("To check the authorization under an existing user: the request returns the response code 200 and success: true")
    public void loginUnderExistingUser() {
        UserObject userObject = new UserObject();
        UserRequest userRequest = getUserRequestAllRequiredField();
        Response response = userObject.create(userRequest);
        LoginRequest loginRequest = LoginRequestTestData.from(userRequest);

        String accessToken = userObject.login(loginRequest)
                .then()
                .statusCode(200)
                .and()
                .assertThat().body("success", equalTo(true))
                .and()
                .body("accessToken", notNullValue())
                .extract()
                .path("accessToken");

        userObject.delete(accessToken);
    }

        @Test
        @DisplayName("Login with invalid username and password")
        @Step("To check the authorization under the wrong username and password: the request returns the 401 response code and message: email or password are incorrect")
        public void loginWithInvalidUsernameAndPassword() {
            LoginRequest loginRequest = LoginRequestTestData.invalidLoginPassword();
            UserObject userObject = new UserObject();

            Response response = userObject.login(loginRequest);
            response.then()
                .statusCode(401)
                .and()
                .assertThat().body("message", equalTo("email or password are incorrect"));


    }

}