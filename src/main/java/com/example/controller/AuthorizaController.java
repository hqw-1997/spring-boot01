package com.example.controller;

import com.example.dto.AccessTokenDto;
import com.example.dto.GithubUser;
import com.example.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizaController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientid;

    @Value("${githun.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.url}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request){
        AccessTokenDto accessTokenDto=new AccessTokenDto();
        accessTokenDto.setClient_id(clientid);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        String token=githubProvider.getAccessToken(accessTokenDto);
        GithubUser user=githubProvider.getUser(token);
       // System.out.println(user.getName());
        if(user!=null){
            //写cookie和session
            request.getSession().setAttribute("user", user);
            System.out.println(user.getName());
            return "redirect:/";//返回根目录
        }else {
            return "redirect:/";
        }
    }
}
