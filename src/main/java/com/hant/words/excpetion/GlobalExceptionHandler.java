package com.hant.words.excpetion;

import com.hant.words.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　＞　　　＜　┃
 * ┃　　　　　　　┃
 * ┃...　⌒　...　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * ┃　　　┃
 * ┃　　　┃
 * ┃　　　┃
 * ┃　　　┃  神兽保佑
 * ┃　　　┃  代码无bug
 * ┃　　　┃
 * ┃　　　┗━━━┓
 * ┃　　　　　　　┣┓
 * ┃　　　　　　　┏┛
 * ┗┓┓┏━┳┓┏┛
 * ┃┫┫　┃┫┫
 * ┗┻┛　┗┻┛
 *
 * @author ：Hant
 * @date ：Created in 2019/5/28 9:22
 * @description：在切面完成后一定要在 web.xml 的DispatcherServlet中配置
 *
 *            <init-param>
 *               <param-name>throwExceptionIfNoHandlerFound</param-name>
 *               <param-value>true</param-value>
 *           </init-param>
 *           关闭 springMVC 中的
 *           使用默认的Servlet来响应静态文件
 *           <mvc:default-servlet-handler/>
 */
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*
     *  400-Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonResult handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        log.error("无法读取JSON..."+ e.getMessage());
        return new JsonResult(HttpStatus.BAD_REQUEST.value(),"无法读取JSON");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult handleValidationException(MethodArgumentNotValidException e)
    {
        log.error("参数验证异常...");
        return new JsonResult(HttpStatus.BAD_REQUEST.value(),e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 404-NOT_FOUND
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public JsonResult handlerNotFoundException(NoHandlerFoundException e)
    {
        log.error("请求的资源不可用"+ e.getMessage());
        return new JsonResult(HttpStatus.NOT_FOUND.value(),"请求的资源不可用");
    }

    /*
     * 405 - method Not allowed
     * HttpRequestMethodNotSupportedException 是ServletException 的子类，需要Servlet API 支持
     *
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        log.error("不合法的请求方法..."+ e.getMessage());
        return new JsonResult(HttpStatus.METHOD_NOT_ALLOWED.value(),"不合法的请求方法");
    }

    /**
     * 415-Unsupported Media Type.HttpMediaTypeNotSupportedException是ServletException的子类，需要Serlet API支持
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
    public JsonResult handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("内容类型不支持..."+ e.getMessage());
        return new JsonResult(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),"内容类型不支持");
    }

    /**
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public JsonResult handleMyException(Exception e) {
        log.error("未授权..."+ e.getMessage());
        return new JsonResult(HttpStatus.UNAUTHORIZED.value(),e.getMessage());
    }

    /* *//**
     * 500 - Internal Server Error
     *//*
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TokenException.class)
    public Response handleTokenException(Exception e) {
        log.error("令牌无效...", e);
        return new Response().failure("令牌无效");
    }*/

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception e) {
        log.error("内部服务错误..."+ e.getMessage());
        return new JsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),"内部服务错误");
    }

}
