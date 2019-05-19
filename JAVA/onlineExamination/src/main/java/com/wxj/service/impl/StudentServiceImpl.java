package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.OperationException;
import com.wxj.exception.ParamInvalidException;
import com.wxj.logic.StudentLogic;
import com.wxj.mapper.ClassMapper;
import com.wxj.mapper.StudentMapper;
import com.wxj.model.DTO.StudentParamsDTO;
import com.wxj.model.PO.Achievement;
import com.wxj.model.PO.Class;
import com.wxj.model.PO.ClassExample;
import com.wxj.model.PO.Student;
import com.wxj.model.VO.AchievementVO;
import com.wxj.model.VO.StudentExamScheduleVO;
import com.wxj.model.VO.StudentVO;
import com.wxj.service.StudentServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * <p>Title: StudentServiceImpl</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-09 23:54
 */
@Service
public class StudentServiceImpl implements StudentServiceI {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    StudentLogic studentLogic;
    @Autowired
    ClassMapper classMapper;

    @Override
    public List<StudentVO> listStudentVOByParams(StudentParamsDTO studentParamsDTO) {
        PageBounds pageBounds = new PageBounds(studentParamsDTO.getCurrentPage(), studentParamsDTO.getPageSize());
        return studentMapper.selectStudentVOByParams(studentParamsDTO, pageBounds);
    }

    @Override
    public Long countStudentVOByParams(StudentParamsDTO studentParamsDTO) {
        return studentMapper.countStudentVOByParams(studentParamsDTO);
    }


    @Override
    public int save(StudentParamsDTO studentParamsDTO) {
        Map<String, Object> paramsMap = new HashMap<>(1);
        paramsMap.put("sno", studentParamsDTO.getSno());
        List<Student> studentList = studentMapper.selectStudentByParams(paramsMap);
        if (studentList.size() > 0) {
            throw new ParamInvalidException("sno重复");
        }

        Date date = new Date();

        Student student = new Student();

        BeanUtils.copyProperties(studentParamsDTO, student);
        student.setCreateTime(date);
        student.setModifyTime(date);
        student.setLoginPassword("123456");
        student.setDelFlag(SystemConstant.NOUGHT);

        int i = studentMapper.insertSelective(student);
        if (SystemConstant.ZERO == i) {
            throw new OperationException(" 插入失败");
        }

        return i;
    }

    @Override
    public int modify(StudentParamsDTO studentParamsDTO) {
        Map<String, Object> paramsMap = new HashMap<>(2);
        paramsMap.put("id", studentParamsDTO.getId());
        paramsMap.put("sno", studentParamsDTO.getSno());
        List<Student> studentList = studentMapper.selectStudentByParams(paramsMap);
        if (studentList.size() > 0) {
            throw new ParamInvalidException("sno重复");
        }

        Student student = new Student();

        BeanUtils.copyProperties(studentParamsDTO, student);
        student.setModifyTime(new Date());

        int i = studentMapper.updateByPrimaryKeySelective(student);
        if (SystemConstant.ZERO == i) {
            throw new OperationException("修改失败");
        }
        return i;
    }

    @Override
    public int delete(Integer id) {
        //TODO:删除学生相关的
        int i = studentMapper.deleteByPrimaryKey(id);
        if (SystemConstant.ZERO == i) {
            throw new OperationException("删除失败");
        }
        return i;
    }

    @Override
    public List<AchievementVO> achievement(Integer id) {
        List<AchievementVO> achievementVOList = Lists.newArrayList();
        List<Achievement> achievementList = studentMapper.selectAchievementByStudentId(id);
        if (achievementList.size() > 0) {
            //按考试安排分组
            Multimap<Integer, Achievement> achievementMultimap = ArrayListMultimap.create();
            for (int i=0,size=achievementList.size(); i<size; i++) {
                achievementMultimap.put(achievementList.get(i).getExamScheduleId(),achievementList.get(i));
            }
            //遍历achievementMultimap
            AchievementVO achievementVO;
            Map<Integer, Collection<Achievement>> map = achievementMultimap.asMap();
            for (Map.Entry<Integer, Collection<Achievement>> entry : map.entrySet()) {
                //判断是否有没判的题目
                if (!studentLogic.noScore(entry.getValue())) {
                    achievementVO = new AchievementVO();
                    achievementVO.setCourseName(entry.getValue().iterator().next().getCourseName());
                    achievementVO.setExamScheduleTitle(entry.getValue().iterator().next().getExamScheduleTitle());
                    achievementVO.setAchievement(studentLogic.countScore(entry.getValue()));
                    achievementVOList.add(achievementVO);
                } else {
                    continue;
                }
            }
        }
        return achievementVOList;
    }

    @Override
    public int resetPassword(Integer id) {
        Student student = new Student();
        student.setId(id);
        student.setLoginPassword("123456");
        int i = studentMapper.updateByPrimaryKeySelective(student);
        if (SystemConstant.ZERO == i) {
            throw new OperationException("重置密码失败");
        }
        return i;
    }

    @Override
    public int studentImport(List<StudentParamsDTO> studentParamsDTOList) {
        Date date = new Date();
        ClassExample classExample = new ClassExample();
        List<Class> classList = classMapper.selectByExample(classExample);
        Map<String, Integer> classMap = classList.stream().collect(Collectors.toMap(Class::getCode, item -> item.getId()));

        List<Student> studentList = Lists.newArrayList();
        Student student;
        for (int i=0,size=studentParamsDTOList.size(); i<size; i++) {
            StudentParamsDTO studentParamsDTO = studentParamsDTOList.get(i);
            student = new Student();
            student.setSno(studentParamsDTO.getSno());
            student.setName(studentParamsDTO.getName());
            student.setLoginPassword(SystemConstant.LOGIN_PASSWORD);
            student.setClassId(classMap.get(studentParamsDTO.getClassCode()));
            student.setCreateTime(date);
            student.setModifyTime(date);
            student.setDelFlag(SystemConstant.NOUGHT);
            studentList.add(student);
        }
        return studentMapper.bathInsert(studentList);
    }

}