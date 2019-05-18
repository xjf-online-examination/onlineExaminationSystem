package com.wxj.controller;

import com.wxj.constant.LoginConstant;
import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.exception.ParamEmptyException;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.LoginDTO;
import com.wxj.model.VO.UserInfoVO;
import com.wxj.service.LoginServiceI;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.ValidateParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
/**
 * <p>Title: LoginController</p >
 * <p>Description: 登陆管理</p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-16 23:09
 */
@RestController
@RequestMapping("/open/user")
public class LoginController {

    @Autowired
    LoginServiceI loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object login(HttpServletRequest request, @RequestBody RequestBean<LoginDTO> requestBean) {
        try {
            LoginDTO loginDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(loginDTO, "userType", "username", "password");

            UserInfoVO userInfoVO = loginService.login(request, loginDTO);
            return ResponseUtils.success("200", userInfoVO);
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object logout(HttpServletRequest request, @RequestBody RequestBean<String> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            request.getSession().setAttribute(LoginConstant.SECURITY_KEY + requestBean.getData(), "");
            return ResponseUtils.success("200");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }

    }

    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object modifyPassword(HttpServletRequest request, @RequestBody RequestBean<LoginDTO> requestBean) {
        try {
            LoginDTO loginDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(loginDTO, "userType", "username", "password");

            loginService.modifyPassword(loginDTO);
            return ResponseUtils.success("201");
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        }
    }
}
