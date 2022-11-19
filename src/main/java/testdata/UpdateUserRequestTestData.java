package testdata;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.UpdateUserRequest;
import pojo.UserRequest;

public class UpdateUserRequestTestData {

    public static UpdateUserRequest getUserRequestAllUpdateRequiredField(){
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setEmail(RandomStringUtils.randomAlphabetic(10)+"@yandex.ru");
        updateUserRequest.setPassword("password");
        updateUserRequest.setName(RandomStringUtils.randomAlphabetic(5));
        return updateUserRequest;
    }
}
