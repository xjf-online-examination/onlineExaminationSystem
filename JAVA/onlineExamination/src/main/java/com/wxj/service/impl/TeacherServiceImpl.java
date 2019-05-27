package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.OperationException;
import com.wxj.exception.ParamInvalidException;
import com.wxj.mapper.TeacherClassCourseMapper;
import com.wxj.mapper.TeacherMapper;
import com.wxj.model.DTO.TaughtSaveDTO;
import com.wxj.model.DTO.TeacherParamsDTO;
import com.wxj.model.PO.Teacher;
import com.wxj.model.PO.TeacherClassCourse;
import com.wxj.model.PO.TeacherClassCourseExample;
import com.wxj.model.PO.TeacherExample;
import com.wxj.model.VO.TeacherTaughtVO;
import com.wxj.model.VO.TeacherVO;
import com.wxj.service.TeacherServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: TeacherServiceImpl</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-13 23:14
 */
@Service
public class TeacherServiceImpl implements TeacherServiceI {
    private Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    TeacherClassCourseMapper teacherClassCourseMapper;

    @Override
    public List<TeacherVO> listTeacherByParams(TeacherParamsDTO teacherParamsDTO) {
        PageBounds pageBounds = new PageBounds(teacherParamsDTO.getCurrentPage(), teacherParamsDTO.getPageSize());
        return teacherMapper.selectTeacherByParams(teacherParamsDTO, pageBounds);
    }

    @Override
    public Long countTeacherByParams(TeacherParamsDTO teacherParamsDTO) {
        return teacherMapper.countTeacherByParams(teacherParamsDTO);
    }

    @Override
    public int save(TeacherParamsDTO teacherParamsDTO) {
        Date date = new Date();

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherParamsDTO, teacher);
        teacher.setLoginPassword("123456");
        teacher.setCreateTime(date);
        teacher.setModifyTime(date);
        teacher.setDelFlag(SystemConstant.NOUGHT);
        int i = 0;
        try {
            i = teacherMapper.insertSelective(teacher);
            if (SystemConstant.ZERO == i) {
                throw new OperationException(" 插入失败");
            }
        } catch (DuplicateKeyException e) {
            TeacherExample teacherExample = new TeacherExample();
            teacherExample.createCriteria().andJobNoEqualTo(teacher.getJobNo());
            Long size = teacherMapper.countByExample(teacherExample);
            if (size > 0) {
                throw new ParamInvalidException("jobNo重复");
            }
        }
        return i;
    }

    @Override
    public int modify(TeacherParamsDTO teacherParamsDTO) {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andIdNotEqualTo(teacherParamsDTO.getId()).andJobNoEqualTo(teacherParamsDTO.getJobNo());
        Long size = teacherMapper.countByExample(teacherExample);
        if (size > 0) {
            throw new ParamInvalidException("jobNo重复");
        }

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherParamsDTO, teacher);
        teacher.setModifyTime(new Date());
        int i = teacherMapper.updateByPrimaryKeySelective(teacher);
        if (SystemConstant.ZERO == i) {
            throw new OperationException(" 修改失败");
        }

        return i;
    }

    @Override
    public void delete(Integer id) {
        //删除教师
        try {
            int teacherDelteSize = teacherMapper.deleteByPrimaryKey(id);
            if (teacherDelteSize > 0) {
                //删除教师课程对应表
                TeacherClassCourseExample teacherClassCourseExample = new TeacherClassCourseExample();
                teacherClassCourseExample.createCriteria().andTeacherIdEqualTo(id);
                teacherClassCourseMapper.deleteByExample(teacherClassCourseExample);
            }
        } catch (Exception e) {
            logger.error("com.wxj.service.impl.TeacherServiceImpl.delete", e);
            throw new OperationException(" 修改失败");
        }
    }

    @Override
    public List<TeacherTaughtVO> listTaughtByTeacherId(Integer id) {
        return teacherMapper.selectTaughtByTeacherId(id);
    }

    @Transactional
    @Override
    public int saveTaught(TaughtSaveDTO taughtSaveDTO) {
        Date date = new Date();

        int i = 0;
        TeacherClassCourse teacherClassCourse;
        for (Integer classCourseId : taughtSaveDTO.getClassCourseIdList()) {
            teacherClassCourse = new TeacherClassCourse();
            teacherClassCourse.setTeacherId(taughtSaveDTO.getTeacherId());
            teacherClassCourse.setClassCourseId(classCourseId);
            teacherClassCourse.setCreateTime(date);
            teacherClassCourse.setModifyTime(date);
            teacherClassCourse.setDelFlag(SystemConstant.NOUGHT);
            i += teacherClassCourseMapper.insertSelective(teacherClassCourse);
        }
        if (SystemConstant.ZERO == i) {
            throw new OperationException(" 插入失败");
        }
        return i;
    }

    @Override
    public int deleteTaught(Integer id) {
        return teacherClassCourseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int resetPassword(Integer id) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setLoginPassword("123456");
        teacher.setModifyTime(new Date());
        int i = teacherMapper.updateByPrimaryKeySelective(teacher);
        if (SystemConstant.ZERO == i) {
            throw new OperationException(" 重置密码失败");
        }

        return i;
    }
}