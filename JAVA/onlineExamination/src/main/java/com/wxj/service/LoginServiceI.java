package com.wxj.service;

import com.wxj.exception.BusinessException;
import com.wxj.model.DTO.LoginDTO;
import com.wxj.model.VO.UserInfoVO;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: LoginServiceI</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-08 20:39
 */
public interface LoginServiceI {
    /**
     *  用户登录，登录信息保存在session
     * @param loginDTO
     * @return
     */
    UserInfoVO login(HttpServletRequest request, LoginDTO loginDTO) throws BusinessException;

    int modifyPassword(LoginDTO loginDTO, String userCode);
}