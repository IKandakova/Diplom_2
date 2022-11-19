package testdata;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.UserRequest;

public class UserRequestTestData {
    public static UserRequest getUserRequestAllRequiredField(){
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(RandomStringUtils.randomAlphabetic(11)+"@yandex.ru");
        userRequest.setPassword("password");
        userRequest.setName("Name");
        return userRequest;
    }

    public static UserRequest getUserRequestWithoutRequiredField(){
        UserRequest userRequest = new UserRequest();
        userRequest.setPassword("password");
        userRequest.setName("Name");
        return userRequest;
    }

}
