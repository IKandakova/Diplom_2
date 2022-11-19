package object;

import io.restassured.response.Response;
import pojo.LoginRequest;
import pojo.UpdateUserRequest;
import pojo.UserRequest;

import static config.Config.getBaseUri;
import static io.restassured.RestAssured.given;

public class UserObject {
    //create
    public Response create(UserRequest userRequest){
        return given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(userRequest)
                .post("auth/register");
    }

    //login
    public Response login(LoginRequest loginRequest){
        return given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(loginRequest)
                .post("auth/login");
    }

    //delete
    public Response delete(String accessToken){
        return given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .delete("auth/user");
    }

    //update with auth
    public Response updateWithAuth(UpdateUserRequest updateUserRequest, String accessToken){
        return given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(updateUserRequest)
                .patch("auth/user");
    }

}
