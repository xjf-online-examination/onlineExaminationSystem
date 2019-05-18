package com.wxj.service.impl;

import com.wxj.constant.LoginConstant;
import com.wxj.exception.BusinessException;
import com.wxj.exception.InnerDataErrorException;
import com.wxj.mapper.StudentMapper;
import com.wxj.mapper.TeacherMapper;
import com.wxj.model.DTO.LoginDTO;
import com.wxj.model.PO.UserInfo;
import com.wxj.model.VO.UserInfoVO;
import com.wxj.service.LoginServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * <p>Title: LoginServiceImpl</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-08 20:43
 */

@Service
public class LoginServiceImpl implements LoginServiceI {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public UserInfoVO login(HttpServletRequest request, LoginDTO loginDTO) throws BusinessException {
        List<UserInfo> userInfoList;
        UserInfoVO userInfoVO = new UserInfoVO();
        switch (loginDTO.getUserType()) {
            case 1:
                userInfoList = studentMapper.selectUserByUserNameAndPassword(loginDTO);
                userInfoVO.setUserType(LoginConstant.USER_TYPE_ONE);
                break;
            case 2:
                userInfoList = teacherMapper.selectUserByUserNameAndPassword(loginDTO);
                userInfoVO.setUserType(LoginConstant.USER_TYPE_TWO);
                break;
            default:{
                userInfoList = null;
            }
        }

        if (userInfoList.size() > 0 && userInfoList != null) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            userInfoVO.setUserCode(userInfoList.get(0).getCode());
            userInfoVO.setUsername(userInfoList.get(0).getName());
            userInfoVO.setSecurityKey(uuid);

            request.getSession().setAttribute(LoginConstant.SECURITY_KEY + userInfoList.get(0).getCode(), uuid);
        } else {
            throw new InnerDataErrorException("用户不存在");
        }
        return userInfoVO;
    }
}