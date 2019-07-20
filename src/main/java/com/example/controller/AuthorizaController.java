package com.example.controller;

import com.example.dto.AccessTokenDto;
import com.example.dto.GithubUser;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletResponse response){
        AccessTokenDto accessTokenDto=new AccessTokenDto();
        accessTokenDto.setClient_id(clientid);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        String githunToken=githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser=githubProvider.getUser(githunToken);

       // System.out.println(user.getName());
        if(githubUser!=null){
            //写cookie和session
            User user=new User();
            String token=UUID.randomUUID().toString();
            user.setName(githubUser.getName());
            user.setPhoto(githubUser.getAvatar_url());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setToken(token);//产出随机数作为用户token
            user.setGmt_create(System.currentTimeMillis());//用当前毫秒数作为用户创建时间
            user.setGmt_modified(user.getGmt_create());
            response.addCookie(new Cookie("token", token));
            userMapper.insertUser(user);
            return "redirect:/";//返回根目
        }else {
            return "redirect:/";
        }
    }
}
