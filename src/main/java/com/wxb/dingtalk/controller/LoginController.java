package com.wxb.dingtalk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: dingtalk
 * @description: 扫码登录
 * @author: 木同
 * @create: 2019-12-25 17:16
 **/
@Controller
@RequestMapping(value = "/dingtalk/html/")
public class LoginController {

    /**
     * @Description: 进入扫码登录页面
     * @Author: 木同
     * @Date: 2019/12/25
     */
    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

}
