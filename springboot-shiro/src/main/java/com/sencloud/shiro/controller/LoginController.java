package com.sencloud.shiro.controller;

import com.sencloud.shiro.entity.JsonData;
import com.sencloud.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author: jason
 * @Description:
 * @Date: Created in 14:01 2019/5/27
 */
@RestController
public class LoginController {


    @GetMapping("/login")
    public JsonData login(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpSession session){

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
           subject.login(token);
            User user = (User)subject.getPrincipal();
            session.setAttribute("user",user);
        }catch (AuthenticationException e){
           token.clear();
           return JsonData.buildError("登陆失败，用户名或者密码错误");
       }
       return JsonData.buildSuccess();
    }
}
