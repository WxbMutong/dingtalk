package com.wxb.dingtalk.enums;

public enum ResultCode {

    SUCCESS(200,"成功"),
    REQUEST_ERROR(400,"服务器不理解请求语法"),
    FAIL(500,"失败，服务器异常"),
    PARAM_FAIL(100001,"请求参数有误"),
    DATA_IS_NOT_EXIST(100002,"响应数据不存在");

    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
