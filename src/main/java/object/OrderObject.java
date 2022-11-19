package object;

import io.restassured.response.Response;
import pojo.OrderCreateRequest;
import pojo.UserRequest;

import static config.Config.getBaseUri;
import static io.restassured.RestAssured.given;

public class OrderObject {
    //create order
    public Response createOrderWithAuth(OrderCreateRequest orderCreateRequest, String accessToken){
        return given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(orderCreateRequest)
                .post("orders");
    }

    public Response createOrderWithoutAuth(OrderCreateRequest orderCreateRequest){
        return given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(orderCreateRequest)
                .post("orders");
    }

    public Response getOrdersUserWithAuth(String accessToken){
        return given()
                .baseUri(getBaseUri())
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .get("orders");
    }

    public Response getOrdersUserWithoutAuth(){
        return given()
                .baseUri(getBaseUri())
                .header("Content-type", "application/json")
                .get("orders");
    }

}
