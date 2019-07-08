package com.example.controller;

import com.example.dto.AccessTokenDto;
import com.example.dto.GithubUser;
import com.example.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizaController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                            @RequestParam(name="state") String state){
        AccessTokenDto accessTokenDto=new AccessTokenDto();
        accessTokenDto.setClient_id("47c368246d423c34df47");
        accessTokenDto.setClient_secret("345eaf9ddee4d905550635bccce058b619c9b337");
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8081/callback");
        accessTokenDto.setState(state);
        String token=githubProvider.getAccessToken(accessTokenDto);
        GithubUser user=githubProvider.getUser(token);
        System.out.println(user.getName());
        return "index";
    }
}
