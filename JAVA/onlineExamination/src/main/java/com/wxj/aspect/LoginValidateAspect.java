package com.wxj.aspect;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
//    /**
//     *
//     * @param request
//     * @param obj
//     */
//    public void before(HttpServletRequest request, Object obj) throws WiseException {
//        logger.info("[#requestUrl#]:{}\t[#requestJson#]:{}", request.getRequestURI(), StringUtil.formatJson(JSONObject.fromObject(obj).toString()));
//        String requestUri = request.getRequestURI();
//        String contextPath = request.getContextPath();
//        String url = requestUri.substring(contextPath.length());
//        if (url.startsWith("/open/user/loginByLoginName") || url.startsWith("/open")) { //如果是不拦截的地址直接放行
//            return;
//        }
//        BaseDTO<?> baseDTO = null;
//        //是HttpServletRequest request, @RequestBody JSONObject jsonObject
//        if (obj instanceof JSONObject) {
//            //            JSONObject jsonObject = (JSONObject) obj;
//            BaseQUERY<JSONObject> baseQUERY = null;
////            JsonConfig jsonConfig = new JsonConfig();
//            Map<String, Object> classMap = new HashMap<>();
////            classMap.put("data",JSONObject.class);
////            jsonConfig.setClassMap(classMap);
////            baseQUERY = (BaseQUERY) JSONObject.toBean(jsonObject, new BaseQUERY(), jsonConfig);
//            baseQUERY= JSON.parseObject(JSON.toJSONString(obj), BaseQUERY.class);
//            // 校验参数
//            validateParamter(baseQUERY);
//            //校验securityKey
//            validateSecurityKey(baseQUERY);
//        } else if (obj instanceof BaseQUERY) {
//            BaseQUERY requestBean = (BaseQUERY) obj;
//            // 校验参数
//            validateParamter(requestBean);
//            //校验securityKey
//            validateSecurityKey(requestBean);
//        }
//
//    }
//
//    /**
//     * 校验securityKey并刷新
//     * @param baseQUERY
//     */
//    private void validateSecurityKey(BaseQUERY<?> baseQUERY) {
//        BaseDTO<?> baseDTO;
//        String loginKey = AspectConstant.SECURITYKEY_RTMART_CENTER + baseQUERY.getMerchantId() + "-" + baseQUERY.getUserId();
//        String securityKey = redisService.getString(loginKey);
//        if (null == securityKey || !securityKey.equals(baseQUERY.getSecurityKey())) {
//            logger.info("securityKey校验失败,userId={},requestId={}", baseQUERY.getUserId(), baseQUERY.getRequestId());
//            baseDTO = BaseDTO.genErrBaseDTO(baseQUERY, BaseError.E99_SECURITY_ERROR);
//            throw new WiseException(JSONObject.fromObject(baseDTO).toString());
//        } else {
//            flushLoginInfo(baseQUERY, loginKey);
//        }
//    }
//
//    /**
//     * 校验参数
//     * @param baseQUERY
//     */
//    private void validateParamter(BaseQUERY<?> baseQUERY) {
//        BaseDTO<?> baseDTO;
//        if (baseQUERY == null) {
//            logger.info("统一aop拦截,请求参数为空");
//            baseDTO = BaseDTO.genErrBaseDTO(baseQUERY, CenterError.E12_ACL_ARG_IS_EMPTY,"UnifiedInterception:baseQUERY");
//            if (baseDTO.getRequestId() == null) {
//                baseDTO.setRequestId(String.valueOf(new Date().getTime()));
//            }
//            throw new WiseException(JSONObject.fromObject(baseDTO).toString());
//        }
//        // BaseQUERYHead baseQUERYHead = new BaseQUERYHead();
//        //if (StringUtils.isEmpty((String) jsonObject.get(BaseQUERYHead.REQUEST_ID))) {
//        if (StringUtils.isEmpty(baseQUERY.getRequestId())) {
//            logger.warn("统一aop拦截,请求参数requestId不能为空");
//            baseDTO = BaseDTO.genErrBaseDTO(baseQUERY, CenterError.E12_ACL_ARG_IS_EMPTY, "UnifiedInterception:requestId");
//            if (baseDTO.getRequestId() == null) {
//                baseDTO.setRequestId(String.valueOf(new Date().getTime()));
//            }
//            throw new WiseException(JSONObject.fromObject(baseDTO).toString());
//        }
//        //if (StringUtils.isEmpty((String) jsonObject.get(BaseQUERYHead.CLIENT_TYPE))) {
//        if (StringUtils.isEmpty(baseQUERY.getClientType())) {
//            logger.warn("统一aop拦截,请求参数clientType不能为空");
//            baseDTO = BaseDTO.genErrBaseDTO(baseQUERY, CenterError.E12_ACL_ARG_IS_EMPTY, "UnifiedInterception:clientType");
//            if (baseDTO.getRequestId() == null) {
//                baseDTO.setRequestId(String.valueOf(new Date().getTime()));
//            }
//            throw new WiseException(JSONObject.fromObject(baseDTO).toString());
//        }
//        //if (StringUtils.isEmpty((String) jsonObject.get(BaseQUERYHead.TIMESTAMP))) {
//        if (StringUtils.isEmpty(baseQUERY.getTimestamp())) {
//            logger.warn("统一aop拦截,请求参数timestamp不能为空");
//            baseDTO = BaseDTO.genErrBaseDTO(baseQUERY, CenterError.E12_ACL_ARG_IS_EMPTY, "UnifiedInterception:timestamp");
//            if (baseDTO.getRequestId() == null) {
//                baseDTO.setRequestId(String.valueOf(new Date().getTime()));
//            }
//            throw new WiseException(JSONObject.fromObject(baseDTO).toString());
//        }
//        //if (StringUtils.isEmpty((String) jsonObject.get(BaseQUERYHead.MERCHANT_ID))) {
//        if (StringUtils.isEmpty(baseQUERY.getMerchantId())) {
//            logger.warn("统一aop拦截,请求参数merchantId不能为空");
//            baseDTO = BaseDTO.genErrBaseDTO(baseQUERY, CenterError.E12_ACL_ARG_IS_EMPTY, "UnifiedInterception:merchantId");
//            if (baseDTO.getRequestId() == null) {
//                baseDTO.setRequestId(String.valueOf(new Date().getTime()));
//            }
//            throw new WiseException(JSONObject.fromObject(baseDTO).toString());
//        }
//        //if (StringUtils.isEmpty((String) jsonObject.get(BaseQUERYHead.USER_ID))) {
//        if (StringUtils.isEmpty(baseQUERY.getUserId())) {
//            logger.warn("统一aop拦截,请求参数userId不能为空");
//            baseDTO = BaseDTO.genErrBaseDTO(baseQUERY, CenterError.E12_ACL_ARG_IS_EMPTY, "UnifiedInterception:userId");
//            if (baseDTO.getRequestId() == null) {
//                baseDTO.setRequestId(String.valueOf(new Date().getTime()));
//            }
//            throw new WiseException(JSONObject.fromObject(baseDTO).toString());
//        }
//        //if (StringUtils.isEmpty((String) jsonObject.get(BaseQUERYHead.SECURITY_KEY))) {
//        if (StringUtils.isEmpty(baseQUERY.getSecurityKey())) {
//            logger.warn("统一aop拦截,请求参数securityKey不能为空");
//            baseDTO = BaseDTO.genErrBaseDTO(baseQUERY, CenterError.E12_ACL_ARG_IS_EMPTY, "UnifiedInterception:securityKey");
//            if (baseDTO.getRequestId() == null) {
//                baseDTO.setRequestId(String.valueOf(new Date().getTime()));
//            }
//            throw new WiseException(JSONObject.fromObject(baseDTO).toString());
//        }
//    }
//
//    /**
//     * 刷新用户登陆时长,刷新用户信息有效时长,刷新权限树,刷新权限集合
//     * @param baseQUERY
//     * @param loginKey
//     */
//    public void flushLoginInfo(BaseQUERY baseQUERY, String loginKey) {
//        Integer securityKeyExpire = 1800;//半小时
//        Integer privilegesSaveTime = 1800;//半小时
//        try {
//            securityKeyExpire = Integer.parseInt(ConfigUtils.getType("center.login.expire"));
//            privilegesSaveTime = Integer.parseInt(ConfigUtils.getType("privileges.resource.expire"));
//        } catch (Exception e) {
//            logger.warn("获取系统配置参数失败,center.login.expire privileges.resource.expire ");
//        }
//        //刷新用户登陆时长
//        boolean flushResult = redisService.expire(loginKey, securityKeyExpire) == 1 ? true : false;
//        logger.info("刷新securityKey时长={},userId={}", flushResult, baseQUERY.getUserId());
//        //刷新用户信息有效时长
//        String userInfoKey = AspectConstant.SECURITYKEY_RTMART_CENTER + baseQUERY.getMerchantId() + "-" + baseQUERY.getUserId();
//        flushResult = redisService.expire(userInfoKey, securityKeyExpire) == 1 ? true : false;
//        logger.info("刷新用户信息时长={},userId={}", flushResult, baseQUERY.getUserId());
//        //刷新用户权限树信息时长
//        String privilegesKey = AspectConstant.PRIVILEGES_RTMART_CENTER_TREE + baseQUERY.getMerchantId() + "-" + baseQUERY.getUserId();
//        boolean result1 = redisService.expire(privilegesKey, privilegesSaveTime) == 1 ? true : false;
//        logger.info("刷新用户权限树信息时长={},userId={}", result1, baseQUERY.getUserId());
//        //刷新用户权限集合时长
//        String privilegesSimpleKey = AspectConstant.PRIVILEGES_RTMART_CENTER_SIMPLE + baseQUERY.getMerchantId() + "-" + baseQUERY.getUserId();
//        boolean result2 = redisService.expire(privilegesSimpleKey, privilegesSaveTime) == 1 ? true : false;
//        logger.info("刷新用户权限集合时长={},userId={}", result2, baseQUERY.getUserId());
//    }
//
//    public void after(HttpServletRequest request, JSONObject jsonObject) {
//        logger.info("[#RequestUrl##]:{},[#RequestJson#]:{}", request.getRequestURI(), jsonObject);
//    }
//
//    public void afterReturning(HttpServletRequest request, Object response, JSONObject jsonObject)throws JsonProcessingException {
//        String reponseStr = mapper.writeValueAsString(response);
//        //logger.info("[#requestUrl#]:{},\t[#requestJson#]:{},\t[#reponseJson#]:{}", request.getRequestURI(), StringUtil.formatJson(jsonObject.toString()), StringUtil.formatJson(reponseStr));
//        logger.info("[#RequestUrl##]:{},[#RequestJson#]:{},[#reponseStr#]:{}", request.getRequestURI(), jsonObject, reponseStr);
//    }
}
