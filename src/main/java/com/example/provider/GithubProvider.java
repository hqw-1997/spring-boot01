package com.example.provider;

import com.alibaba.fastjson.JSON;
import com.example.dto.AccessTokenDto;
import com.example.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class GithubProvider {
    private AccessTokenDto accessTokenDto;

    public String getAccessToken(AccessTokenDto accessTokenDto){
         MediaType mediaType= MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String rtvalue= response.body().string();
            rtvalue=rtvalue.split("&")[0].split("=")[1];
            System.out.println(rtvalue);
            return rtvalue;
        }catch (Exception e){
            return null;
        }
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String rtvalue= response.body().string();
            GithubUser githubUser=JSON.parseObject(rtvalue,GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            return null;
        }
    }
}
