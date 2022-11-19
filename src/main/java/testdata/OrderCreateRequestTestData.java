package testdata;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.OrderCreateRequest;
import pojo.UserRequest;

public class OrderCreateRequestTestData {
    public static OrderCreateRequest getOrderWithIngredients(){
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest.setIngredients(new String[]{"61c0c5a71d1f82001bdaaa72", "61c0c5a71d1f82001bdaaa74", "61c0c5a71d1f82001bdaaa6c"});
        return orderCreateRequest;
    }

    public static OrderCreateRequest getOrderWithoutIngredients(){
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest.setIngredients(new String[]{});
        return orderCreateRequest;
    }

    public static OrderCreateRequest getOrderWithIncorrectIngredients(){
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest.setIngredients(new String[]{"Incorrect61c0c5a71d1f82001bdaaa72", "Incorrect61c0c5a71d1f82001bdaaa74", "Incorrect61c0c5a71d1f82001bdaaa6c"});
        return orderCreateRequest;
    }
}
