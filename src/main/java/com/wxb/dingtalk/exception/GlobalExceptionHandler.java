package com.wxb.dingtalk.exception;

import com.wxb.dingtalk.common.Result;
import com.wxb.dingtalk.enums.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: dingtalk
 * @description: 全局异常拦截
 * @author: 木同
 * @create: 2019-12-26 13:39
 **/
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局拦截参数化注解异常
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result paramException(MethodArgumentNotValidException exception) {
        Result response = new Result(ResultCode.PARAM_FAIL.getCode(),exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        return response;
    }

    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public Result errorHandler(HttpServletRequest request, HttpServletResponse response, HttpSession session, Exception e){
        try{
            LOGGER.error("请求地址："+request.getRequestURL());
            Map<String, String> map = new HashMap<>();
            map = getHeadersInfo(request);
            LOGGER.error("请求token："+map.get("accesstoken"));
            LOGGER.error("请求浏览器及版本号："+map.get("browserinfo"));
            LOGGER.error("***捕获到全局未处理异常***异常方法类：",e);
            LOGGER.error("异常：",e.getMessage());
        }catch (Exception e1){
            LOGGER.error("参数进方法前异常无法捕获：",e1);
        }finally
        {
            return new Result(ResultCode.REQUEST_ERROR);
        }

    }

    private static Map<String, String> getHeadersInfo(HttpServletRequest request)
    {
        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements())
        {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

}
