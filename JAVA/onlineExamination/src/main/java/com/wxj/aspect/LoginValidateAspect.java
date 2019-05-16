package com.wxj.aspect;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxj.constant.LoginConstant;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.exception.ParamEmptyException;
import com.wxj.exception.PermissionException;
import com.wxj.model.Bean.RequestBean;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: LoginValidateAspect</p>
 * <p>Description: 校验securityKey</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 * 通过aop拦截所有的请求方法,获取请求的json字符串,对json串进行校验合法则继续通行,不合法则直接抛出WiseException异常<br/>
 * springmvc自动捕捉到该异常根据异常,直接将校验结果返回前端
 * @author guolin
 * @version 1.0
 * @date 2018-06-09 11:48
 */
@Component
public class LoginValidateAspect {
    private static Logger logger = LoggerFactory.getLogger(LoginValidateAspect.class);
    private final static ObjectMapper mapper = new ObjectMapper();
    /**
     *
     * @param request
     * @param obj
     */
    public void before(Object obj) throws BusinessRuntimeException {
//        logger.info("[#requestUrl#]:{}\t[#requestJson#]:{}", request.getRequestURI(), StringUtil.formatJson(JSONObject.fromObject(obj).toString()));
//        String requestUri = request.getRequestURI();
//        String contextPath = request.getContextPath();
//        String url = requestUri.substring(contextPath.length());
//        if (url.startsWith("/open/user/loginByLoginName") || url.startsWith("/open")) { //如果是不拦截的地址直接放行
//            return;
//        }
        RequestBean<?> baseDTO = null;
        //是HttpServletRequest request, @RequestBody JSONObject jsonObject
        if (obj instanceof JSONObject) {
            //            JSONObject jsonObject = (JSONObject) obj;
            RequestBean<JSONObject> baseQUERY = null;
//            JsonConfig jsonConfig = new JsonConfig();
            Map<String, Object> classMap = new HashMap<>();
//            classMap.put("data",JSONObject.class);
//            jsonConfig.setClassMap(classMap);
//            baseQUERY = (BaseQUERY) JSONObject.toBean(jsonObject, new BaseQUERY(), jsonConfig);
            baseQUERY= JSON.parseObject(JSON.toJSONString(obj), RequestBean.class);
            // 校验参数
            validateParamter(baseQUERY);
            //校验securityKey
//            validateSecurityKey(request, baseQUERY);
        } else if (obj instanceof RequestBean) {
            RequestBean requestBean = (RequestBean) obj;
            // 校验参数
            validateParamter(requestBean);
            //校验securityKey
//            validateSecurityKey(request, requestBean);
        }

    }

    /**
     * 校验securityKey并刷新
     * @param baseQUERY
     */
    private void validateSecurityKey(HttpServletRequest request, RequestBean<?> baseQUERY) {
        String loginKey = LoginConstant.SECURITY_KEY+baseQUERY.getUsername();
        String securityKey = (String)request.getSession().getAttribute(loginKey);
        if (null == securityKey || !securityKey.equals(baseQUERY.getSecurityKey())) {
            logger.info("securityKey校验失败,userId={}", baseQUERY.getUsername());
            request.getSession().setAttribute(loginKey,"");
            throw new PermissionException("登陆过期");
        }
    }

    /**
     * 校验参数
     * @param baseQUERY
     */
    private void validateParamter(RequestBean<?> baseQUERY) {
        if (baseQUERY == null) {
            logger.info("统一aop拦截,请求参数为空");
            throw new ParamEmptyException("请求参数不能为空");
        }
        if (StringUtils.isEmpty(baseQUERY.getSecurityKey())) {
            logger.warn("统一aop拦截,请求参数securityKey不能为空");
            throw new ParamEmptyException("请求参数securityKey不能为空");
        }
        if (StringUtils.isEmpty(baseQUERY.getUsername())) {
            logger.warn("统一aop拦截,请求参数username不能为空");
            throw new ParamEmptyException("请求参数username不能为空");
        }
    }

    public void after(JSONObject jsonObject) {
        System.out.println(jsonObject);
//        logger.info("[#RequestUrl##]:{},[#RequestJson#]:{}", request.getRequestURI(), jsonObject);
    }

    public void afterReturning(HttpServletRequest request, Object response, JSONObject jsonObject)throws JsonProcessingException {
        String reponseStr = mapper.writeValueAsString(response);
        //logger.info("[#requestUrl#]:{},\t[#requestJson#]:{},\t[#reponseJson#]:{}", request.getRequestURI(), StringUtil.formatJson(jsonObject.toString()), StringUtil.formatJson(reponseStr));
        logger.info("[#RequestUrl##]:{},[#RequestJson#]:{},[#reponseStr#]:{}", request.getRequestURI(), jsonObject, reponseStr);
    }
}
