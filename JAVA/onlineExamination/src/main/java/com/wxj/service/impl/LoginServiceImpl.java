package com.wxj.service.impl;

import com.wxj.constant.LoginConstant;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.BusinessException;
import com.wxj.exception.InnerDataErrorException;
import com.wxj.exception.OperationException;
import com.wxj.mapper.StudentMapper;
import com.wxj.mapper.TeacherMapper;
import com.wxj.model.DTO.LoginDTO;
import com.wxj.model.PO.*;
import com.wxj.model.VO.UserInfoVO;
import com.wxj.service.LoginServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.OpenDataException;
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
    public UserInfoVO login(HttpServletRequest request, LoginDTO loginDTO) {
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
                throw new InnerDataErrorException("请选择正确的用户类型");
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

    @Override
    public int modifyPassword(LoginDTO loginDTO, String userCode) {
        int i = 0;
        switch (loginDTO.getUserType()) {
            case 1:
                Student student = new Student();
                student.setLoginPassword(loginDTO.getPassword());
                StudentExample studentExample = new StudentExample();
                studentExample.createCriteria().andSnoEqualTo(userCode);
                i = studentMapper.updateByExampleSelective(student, studentExample);
                break;
            case 2:
                Teacher teacher = new Teacher();
                teacher.setLoginPassword(loginDTO.getPassword());
                TeacherExample teacherExample = new TeacherExample();
                teacherExample.createCriteria().andJobNoEqualTo(userCode);
                i = teacherMapper.updateByExampleSelective(teacher, teacherExample);
                break;
            default:{
                throw new InnerDataErrorException("请选择正确的用户类型");
            }
        }
        if (SystemConstant.ZERO == i) {
            throw new OperationException("修改密码失败");
        }
        return i;
    }
}