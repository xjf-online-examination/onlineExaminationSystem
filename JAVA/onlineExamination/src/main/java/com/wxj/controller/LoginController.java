package com.wxj.controller;

import com.wxj.exception.BusinessException;
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
        request.getSession().setAttribute(requestBean.getSecurityKey(), "");
        return ResponseUtils.success("200");

    }

    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object modifyPassword(HttpServletRequest request, @RequestBody RequestBean<String> requestBean) {

        return null;
    }
}
