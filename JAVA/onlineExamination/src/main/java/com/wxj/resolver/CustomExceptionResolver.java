package com.wxj.resolver;

import com.alibaba.fastjson.JSONObject;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.model.Bean.ResponseBean;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: CustomExceptionResolver</p>
 * <p>Description: 全局异常处理类</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: wxj</p>
 * @author wangxiaojun
 * @version 1.0
 * @date 2018-06-09 14:10
 */
@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(CustomExceptionResolver.class);
    //wise自定义错误 throw 后需要截取exception.message中有效的json信息
    private static final int SUBSTRLENG="com.hanshow.wise.common.exception.WiseException: ".length();
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        response.setStatus(HttpStatus.OK.value()); //设置状态码
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        response.setCharacterEncoding("UTF-8"); //避免乱码
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            if (exception instanceof BusinessRuntimeException) {
                response.getWriter().write(JSONObject.toJSONString(ResponseUtils.error((BusinessRuntimeException)exception)));
                logger.error("The-global-exception-handler-class-catches-exceptions-hand:", exception);
            }
        } catch (Exception e) {
            logger.error("全局异常处理失败", e);
        }
        return modelAndView;
    }
}
