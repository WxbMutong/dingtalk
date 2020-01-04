package com.wxb.dingtalk.common;

import com.wxb.dingtalk.enums.ResultCode;

/**
 * @program: dingtalk
 * @description: 返回公共类
 * @author: 木同
 * @create: 2019-12-26 11:30
 **/
public class Result {

    private Integer status;
    private String message;
    private Object data;

    public Result() {
        super();
    }

    public Result(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(Object data) {
        this.status = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    public Result(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Result(ResultCode resultCode){
        this.status = resultCode.getCode();
        this.message = resultCode.getMsg();
    }

    public static Result ok(){
        return new Result(ResultCode.SUCCESS);
    }

    public static Result fail(){
        return new Result(ResultCode.FAIL);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
