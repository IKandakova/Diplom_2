package pojo;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import object.UserObject;
import static config.Config.getBaseUri;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static testdata.UpdateUserRequestTestData.getUserRequestAllUpdateRequiredField;
import static testdata.UserRequestTestData.getUserRequestAllRequiredField;


public class UpdateUserRequestTest {


    @Test
    @DisplayName("Changing user data with authorization")
    @Step("Checking the user data change: the request returns the response code 200 and success: true")
    public void updateUserWithAuth() {
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

        UpdateUserRequest updateUserRequest = getUserRequestAllUpdateRequiredField();
        Response response= userObject.updateWithAuth(updateUserRequest, accessToken);
        response.then()
                .statusCode(200)
                .and()
                .assertThat().body("success", equalTo(true));

        userObject.delete(accessToken);
    }


        @Test
        @DisplayName("Changing user data without authorization")
        @Step("Checking the user data change: the request returns the 401 response code and message: You should be authorized")
        public void updateUserWithoutAuth() {
            UpdateUserRequest updateUserRequest = getUserRequestAllUpdateRequiredField();

            Response response =
                    given()
                            .header("Content-type", "application/json")
                            .baseUri(getBaseUri())
                            .body(updateUserRequest)
                            .patch("auth/user");
            response.then()
                    .statusCode(401)
                    .and()
                    .assertThat().body("message", equalTo("You should be authorised"));
        }

}