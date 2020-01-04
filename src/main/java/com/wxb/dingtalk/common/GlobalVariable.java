package com.wxb.dingtalk.common;

import org.springframework.stereotype.Component;

/**
 * @program: dingtalk
 * @description: 全局变量
 * @author: 木同
 * @create: 2019-12-30 11:51
 **/
@Component
public class GlobalVariable {

    public String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
