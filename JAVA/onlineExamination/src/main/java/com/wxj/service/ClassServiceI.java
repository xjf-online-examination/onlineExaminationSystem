package com.wxj.service;

import com.wxj.model.DTO.ClassParamsDTO;
import com.wxj.model.VO.ClassSelectVO;
import com.wxj.model.VO.ClassVO;

import java.util.List;

/**
 * <p>Title: ClassServiceI</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-12 22:57
 */
public interface ClassServiceI {
    /**
     * 条件查询分页班级
     * @param classParamsDTO
     * @return
     */
    List<ClassVO> listClassByParams(ClassParamsDTO classParamsDTO);

    /**
     * 统计条件查询
     * @param classParamsDTO
     * @return
     */
    Long countClassByParams(ClassParamsDTO classParamsDTO);

    /**
     * 新增
     * @param classParamsDTO
     * @return
     */
    int save(ClassParamsDTO classParamsDTO);

    /**
     * 新增
     * @param classParamsDTO
     * @return
     */
    int modify(ClassParamsDTO classParamsDTO);

    int delete(Integer id);

    /**
     * 获取所有班级ID，name供下拉菜单用
     * @return
     */
    List<ClassSelectVO> listClassAll();
}