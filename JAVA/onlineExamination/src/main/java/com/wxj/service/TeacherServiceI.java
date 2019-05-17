package com.wxj.service;

import com.wxj.model.DTO.TeacherParamsDTO;
import com.wxj.model.VO.TeacherTaughtVO;
import com.wxj.model.VO.TeacherVO;

import java.util.List;

/**
 * <p>Title: TeacherServiceI</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-13 23:09
 */
public interface TeacherServiceI {

    /**
     * 条件查询分页列表
     * @param teacherParamsDTO
     * @return
     */
    List<TeacherVO> listTeacherByParams(TeacherParamsDTO teacherParamsDTO);

    /**
     * 统计条件查询
     * @param teacherParamsDTO
     * @return
     */
    Long countTeacherByParams(TeacherParamsDTO teacherParamsDTO);

    /**
     * 新增
     * @param teacherParamsDTO
     * @return
     */
    int save(TeacherParamsDTO teacherParamsDTO);

    /**
     * 修改
     * @param teacherParamsDTO
     * @return
     */
    int modify(TeacherParamsDTO teacherParamsDTO);

    /**
     * 删除教师相关信息
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 所授课程
     * @param id
     * @return
     */
    List<TeacherTaughtVO> listTaughtByTeacherId(Integer id);

    /**
     * 修改密码
     * @param id
     * @return
     */
    int resetPassword(Integer id);
}