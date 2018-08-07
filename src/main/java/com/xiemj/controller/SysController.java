package com.xiemj.controller;

import com.xiemj.common.annotation.Log;
import com.xiemj.common.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录 controller
 */
@Controller
public class SysController extends BaseController{

    /**
     * 跳转到登录页面
     * @param model
     * @return
     */
    @GetMapping({ "/login", "" })
    @Log("重定向到登录")
    String toLogin(Model model) {
        return "login";
    }

    /**
     * 登录实现
     * @param params
     * @return
     */
    @PostMapping({"/index",""})
    @Log("登录实现")
    public ResponseEntity<String>doLogin(String params)
   {
       Map<String,Object> resultMap = new HashMap<>();
       Map<String,Object> paramsMap = new HashMap<>();
       paramsMap = super.dealParamsToMap(params);
       String username = (String) paramsMap.get("username");
       String password = (String) paramsMap.get("password");
       resultMap.put("resultCode",true);
       UsernamePasswordToken token = new UsernamePasswordToken(username, password);
       Subject subject = SecurityUtils.getSubject();
       try {
           subject.login(token);
           return super.toJsonResponse(resultMap);
       } catch (AuthenticationException e) {
           resultMap.put("resultCode",false);
           return super.toJsonResponse(e.toString());
       }
   }

    /**
     * 跳转到主页面
     * @param model
     * @return
     */
    @GetMapping({ "/main", "" })
    @Log("重定向到主页面")
    String toIndex(Model model) {
        return "index";
    }

    /**
     * 跳转到登录页面
     * @param model
     * @return
     */
    @GetMapping({ "/logout", "" })
    @Log("重定向到主页面")
    String logout(Model model) {
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }



}
