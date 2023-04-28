package com.uvaliavaty.postservice.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uvaliavaty.postservice.model.UpdateUserRequest;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClient {

    @Value("${user-service.base-url}")
    private String baseUrl;


    public void updateUser(long id, int amountOfPosts) {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setAmountOfPosts(amountOfPosts);

        OkHttpClient client = new OkHttpClient();

        try {
                RequestBody requestBody = RequestBody.create(new ObjectMapper().writeValueAsString(updateUserRequest),
                MediaType.parse("application/json; charset=utf-8"));

            System.out.println(baseUrl + "/users");
            Request request = new Request.Builder()
                .url(baseUrl + "/users/" + id)
                .put(requestBody)
                .build();

            Response response = client.newCall(request).execute();
            System.out.println("--------");
            System.out.println(response.body().string());
            System.out.println(response.code());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
