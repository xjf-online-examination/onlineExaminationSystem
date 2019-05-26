package com.wxj.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxj.constant.LoginConstant;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.exception.ParamEmptyException;
import com.wxj.exception.PermissionException;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.Bean.ResponseBean;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: LoginValidateAspect</p>
 * <p>Description: 校验securityKey</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: wxj</p>
 * @author wangxiaojun
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
     * @param requestBean
     */
    public void before(HttpServletRequest request, RequestBean<?> requestBean) {
//        logger.info("[#requestUrl#]:{}\t[#requestJson#]:{}", request.getRequestURI(), StringUtil.formatJson(JSONObject.fromObject(obj).toString()));
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        if (url.startsWith("/open")) { //如果是不拦截的地址直接放行
            return;
        }
        RequestBean<?> baseDTO = null;
        //是HttpServletRequest request, @RequestBody JSONObject jsonObject
        if (requestBean instanceof RequestBean) {
            // 校验参数
            validateParamter(requestBean);
            //校验securityKey
            validateSecurityKey(request, requestBean);
        }

    }

    /**
     * 校验securityKey并刷新
     * @param baseQUERY
     */
    private void validateSecurityKey(HttpServletRequest request, RequestBean<?> baseQUERY) {
        String loginKey = LoginConstant.SECURITY_KEY+baseQUERY.getUserCode();
        String securityKey = (String)request.getSession().getAttribute(loginKey);
        if (null == securityKey || !securityKey.equals(baseQUERY.getSecurityKey())) {
            logger.info("securityKey校验失败,userId={}", baseQUERY.getUserCode());
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
            ResponseBean responseBean = new ResponseBean("422", "请求参数不能为空");
            throw new ParamEmptyException(JSONObject.fromObject(responseBean).toString());
        }
        if (StringUtils.isEmpty(baseQUERY.getSecurityKey())) {
            logger.warn("统一aop拦截,请求参数securityKey不能为空");
            ResponseBean responseBean = new ResponseBean("422", "请求参数securityKey不能为空");
            throw new ParamEmptyException(JSONObject.fromObject(responseBean).toString());
        }
        if (StringUtils.isEmpty(baseQUERY.getUserCode())) {
            logger.warn("统一aop拦截,请求参数userCode不能为空");
            ResponseBean responseBean = new ResponseBean("422", "请求参数userCode不能为空");
            throw new ParamEmptyException(JSONObject.fromObject(responseBean).toString());
        }
    }

    public void after(HttpServletRequest request, RequestBean<?> requestBean) {
        System.out.println(requestBean);
        logger.info("[#RequestUrl##]:{},[#RequestJson#]:{}", request.getRequestURI(), requestBean);
    }

    public void afterReturning(HttpServletRequest request, Object response, JSONObject jsonObject)throws JsonProcessingException {
        String reponseStr = mapper.writeValueAsString(response);
        //logger.info("[#requestUrl#]:{},\t[#requestJson#]:{},\t[#reponseJson#]:{}", request.getRequestURI(), StringUtil.formatJson(jsonObject.toString()), StringUtil.formatJson(reponseStr));
        logger.info("[#RequestUrl##]:{},[#RequestJson#]:{},[#reponseStr#]:{}", request.getRequestURI(), jsonObject, reponseStr);
    }
}
