package cn.wxj.common.handler;


import cn.wxj.common.bean.Result;
import cn.wxj.common.enumeration.ExceptionType;
import cn.wxj.common.exception.TceException;
import cn.wxj.common.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author WangJinbo
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final static String CONTENT_TYPE = "application/json;charset=UTF-8";

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(TceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public void serverError(TceException e) {
        log.error("业务异常:", e);
        process(Result.fail(e.getExceptionEnum()));
    }
    /**
     * 请求参数异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public void serverError(MethodArgumentTypeMismatchException e) {
        log.error("参数异常:", e);
        process(Result.fail(ExceptionType.BAD_REQUEST));
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public void runtime(RuntimeException e) {
        log.error("系统异常:", e);
        process(Result.fail(ExceptionType.SERVER_ERROR));
    }

    /**
     * 拦截请求错误时异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public void badRequest(HttpMessageNotReadableException e) {
        log.error("错误请求异常:", e);
        process(Result.fail(ExceptionType.BAD_REQUEST));
    }

    private void process(String result) {
        HttpUtils.getResponse().setContentType(CONTENT_TYPE);
        try {
            HttpUtils.getResponse().getWriter().write(result);
        } catch (IOException e) {
            log.error("网络通信异常:", e);
        }
    }
}
