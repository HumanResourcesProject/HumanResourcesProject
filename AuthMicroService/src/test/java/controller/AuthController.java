package controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AuthController {
    @Test
    void findAllTest() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .url("http://localhost:7070/auth/getall")
                .method("GET",null)
                .build();
        Response response =  client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
