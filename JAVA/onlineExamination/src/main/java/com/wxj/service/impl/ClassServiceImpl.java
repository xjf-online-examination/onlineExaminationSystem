package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.OperationException;
import com.wxj.exception.ParamInvalidException;
import com.wxj.mapper.ClassMapper;
import com.wxj.mapper.StudentMapper;
import com.wxj.model.DTO.ClassParamsDTO;
import com.wxj.model.DTO.StudentParamsDTO;
import com.wxj.model.PO.Class;
import com.wxj.model.PO.ClassExample;
import com.wxj.model.VO.ClassSelectVO;
import com.wxj.model.VO.ClassVO;
import com.wxj.service.ClassServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: ClassServiceImpl</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-12 22:59
 */
@Service
public class ClassServiceImpl implements ClassServiceI {
    @Autowired
    ClassMapper classMapper;
    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<ClassVO> listClassByParams(ClassParamsDTO classParamsDTO) {
        Class c = new Class();
        BeanUtils.copyProperties(classParamsDTO, c);
        PageBounds pageBounds = new PageBounds(classParamsDTO.getCurrentPage(), classParamsDTO.getPageSize());
        List<Class> classList = classMapper.selectClassByParams(c, pageBounds);

        List<ClassVO> classVOList = Lists.newArrayList();
        ClassVO classVO;
        StudentParamsDTO studentParamsDTO;
        for (int i=0,size=classList.size(); i<size; i++) {
            classVO = new ClassVO();
            classVO.setId(classList.get(i).getId());
            classVO.setCode(classList.get(i).getCode());
            classVO.setName(classList.get(i).getName());

            studentParamsDTO = new StudentParamsDTO();
            studentParamsDTO.setClassId(classList.get(i).getId());
            Long number = studentMapper.countStudentVOByParams(studentParamsDTO);

            classVO.setNumber(number);
            classVOList.add(classVO);
        }
        return classVOList;
    }

    @Override
    public Long countClassByParams(ClassParamsDTO classParamsDTO) {
        Class c = new Class();
        BeanUtils.copyProperties(classParamsDTO, c);
        return classMapper.countClassByParams(c);
    }

    @Override
    public int save(ClassParamsDTO classParamsDTO) {
        Date date = new Date();

        Class c = new Class();
        BeanUtils.copyProperties(classParamsDTO, c);
        c.setCreateTime(date);
        c.setModifyTime(date);
        c.setDelFlag(SystemConstant.NOUGHT);
        int i = 0;
        try {
            i = classMapper.insertSelective(c);
            if (SystemConstant.ZERO == i) {
                throw new OperationException(" 插入失败");
            }
        } catch (DuplicateKeyException e) {
            ClassExample classExample = new ClassExample();
            classExample.createCriteria().andCodeEqualTo(classParamsDTO.getCode());
            Long size = classMapper.countByExample(classExample);
            if (size > 0) {
                throw new ParamInvalidException("code重复");
            }
        }
        return i;
    }

    @Override
    public int modify(ClassParamsDTO classParamsDTO) {
        ClassExample classExample = new ClassExample();
        classExample.createCriteria().andIdNotEqualTo(classParamsDTO.getId()).andCodeEqualTo(classParamsDTO.getCode());
        Long size = classMapper.countByExample(classExample);
        if (size > 0) {
            throw new ParamInvalidException("code重复");
        }
        Class c = new Class();
        BeanUtils.copyProperties(classParamsDTO, c);
        c.setModifyTime(new Date());
        int i = classMapper.updateByPrimaryKeySelective(c);
        if (SystemConstant.ZERO == i) {
            throw new OperationException(" 修改失败");
        }

        return i;
    }

    @Override
    public int delete(Integer id) {
        //删除班级信息

        //删除班级和课程对应表信息

        //删除班级对应的学生

        //删除学生对应的考试信息

        //删除教师与班级与课程对应关系
        return 0;
    }

    @Override
    public List<ClassSelectVO> listClassAll() {
        return classMapper.selectClassAll();
    }
}