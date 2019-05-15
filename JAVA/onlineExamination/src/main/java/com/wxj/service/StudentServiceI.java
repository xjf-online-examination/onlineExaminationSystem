package com.wxj.service;

import com.wxj.model.DTO.StudentParamsDTO;
import com.wxj.model.VO.AchievementVO;
import com.wxj.model.VO.StudentVO;

import java.util.List;

/**
 * <p>Title: StudentServiceI</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-09 23:52
 */
public interface StudentServiceI {
    /**
     *  分页条件查询学生管理列表
     * @param studentParamsDTO
     * @return
     */
    List<StudentVO> listStudentVOByParams(StudentParamsDTO studentParamsDTO);
    /**
     * 统计条件查询学生管理列表
     * @param studentParamsDTO
     * @return
     */
    Long countStudentVOByParams(StudentParamsDTO studentParamsDTO);
    /**
     * 新增学生
     * @param studentParamsDTO
     * @return
     */
    int save(StudentParamsDTO studentParamsDTO);
    /**
     * 修改学生信息
     * @param id
     * @param studentParamsDTO
     * @return
     */
    int modify(Integer id, StudentParamsDTO studentParamsDTO);
    /**
     *  删除学生信息
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 成绩
     * @param id
     * @return
     */
    List<AchievementVO> achievement(Integer id);

    /**
     * 重置密码
     * @param id
     * @return
     */
    int resetPassword(Integer id);
}