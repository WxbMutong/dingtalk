package com.wxb.dingtalk.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: dingtalk
 * @description: 配置文件转化类
 * @author: 木同
 * @create: 2019-12-26 13:27
 **/
@Component
public class EnvProtites {

    @Value("${dingtalk.AppKey}")
    private String appKey;

    @Value("${dingtalk.AppSecret}")
    private String appSecrect;

    @Value("${dingtalk.Host}")
    private String host;

    @Value("${dingtalk.AppId}")
    private String appId;

    @Value("${dingtalk.LoginAppSecret}")
    private String loginAppSecrect;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecrect() {
        return appSecrect;
    }

    public void setAppSecrect(String appSecrect) {
        this.appSecrect = appSecrect;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getLoginAppSecrect() {
        return loginAppSecrect;
    }

    public void setLoginAppSecrect(String loginAppSecrect) {
        this.loginAppSecrect = loginAppSecrect;
    }

}
