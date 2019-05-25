package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.wxj.constant.ExamConstant;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.exception.OperationException;
import com.wxj.exception.ParamInvalidException;
import com.wxj.logic.ExamQuestionsLogic;
import com.wxj.logic.StudentLogic;
import com.wxj.mapper.*;
import com.wxj.model.DTO.StudentAnswerSaveDTO;
import com.wxj.model.DTO.StudentAnswerSaveDetailsDTO;
import com.wxj.model.DTO.StudentEntryAnswerSaveDTO;
import com.wxj.model.DTO.StudentParamsDTO;
import com.wxj.model.PO.*;
import com.wxj.model.PO.Class;
import com.wxj.model.VO.AchievementVO;
import com.wxj.model.VO.StudentVO;
import com.wxj.service.StudentServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    @Autowired
    EntryAnswerDetailsMapper entryAnswerDetailsMapper;
    @Autowired
    StudentAnswerMapper studentAnswerMapper;
    @Autowired
    ExamQuestionsLogic examQuestionsLogic;


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
        int i = 0;
        Student student = studentMapper.selectByPrimaryKey(id);
        i = studentMapper.deleteByPrimaryKey(id);
        StudentAnswerExample studentAnswerExample = new StudentAnswerExample();
        studentAnswerExample.createCriteria().andStudentSnoEqualTo(student.getSno());
        List<Integer> studentAnswerIdList = studentAnswerMapper.selectByExample(studentAnswerExample).stream().map(StudentAnswer::getId).collect(Collectors.toList());

        i = studentAnswerMapper.deleteByExample(studentAnswerExample);

        EntryAnswerDetailsExample entryAnswerDetailsExample = new EntryAnswerDetailsExample();
        entryAnswerDetailsExample.createCriteria().andEntryAnswerIdIn(studentAnswerIdList);
        i = entryAnswerDetailsMapper.deleteByExample(entryAnswerDetailsExample);
        if (SystemConstant.ZERO == i) {
            throw new OperationException("删除失败");
        }
        return i;
    }

    @Override
    public List<AchievementVO> achievement(String sno) {
        List<AchievementVO> achievementVOList = Lists.newArrayList();
        List<Achievement> achievementList = studentMapper.selectAchievementByStudentId(sno);
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
    public int studentAnswer(StudentAnswerSaveDTO studentAnswerSaveDTO) throws BusinessRuntimeException {
        int studentAnswerInsertSize = 0;
        Date date = new Date();
        StudentAnswer studentAnswer;
        //判卷
        Map<Integer, Float> scoreMap = examQuestionsLogic.getScore(studentAnswerSaveDTO);
        //保存数据
        for (int i=0,size=studentAnswerSaveDTO.getAnswerSaveDetailsDTOList().size(); i<size; i++) {
            StudentAnswerSaveDetailsDTO studentAnswerSaveDetailsDTO = studentAnswerSaveDTO.getAnswerSaveDetailsDTOList().get(i);

            studentAnswer = new StudentAnswer();
            BeanUtils.copyProperties(studentAnswerSaveDetailsDTO, studentAnswer);
            studentAnswer.setQuestionsNo(studentAnswerSaveDetailsDTO.getQuestionsNo().byteValue());

            float score = 0f;
            if (null != scoreMap.get(studentAnswerSaveDetailsDTO.getQuestionsNo())) {
                score = scoreMap.get(studentAnswerSaveDetailsDTO.getQuestionsNo());
            }
            studentAnswer.setScore(score);
            studentAnswer.setFinishFlag("1");
            studentAnswer.setFinishTime(date);
            studentAnswer.setCreateTime(date);
            studentAnswer.setModifyTime(date);
            studentAnswer.setDelFlag(SystemConstant.NOUGHT);
            studentAnswerInsertSize = studentAnswerMapper.insertSelective(studentAnswer);
            if (SystemConstant.ZERO == studentAnswerInsertSize) {
                throw new OperationException("删除失败");
            }

            if (ExamConstant.EXAM_QUESTIONS_TYPE_SIX.equals(studentAnswerSaveDetailsDTO.getQuestionsType())) {
                EntryAnswerDetails entryAnswerDetails;
                int entryAnswerDetailsInsertSize = 0;
                for (StudentEntryAnswerSaveDTO studentEntryAnswerSaveDTO : studentAnswerSaveDetailsDTO.getStudentEntryAnswerSaveDTOList()) {
                    entryAnswerDetails = new EntryAnswerDetails();
                    BeanUtils.copyProperties(studentEntryAnswerSaveDTO, entryAnswerDetails);
                    entryAnswerDetails.setEntryAnswerId(studentAnswer.getId());
                    entryAnswerDetails.setRow(studentEntryAnswerSaveDTO.getRow().byteValue());
                    entryAnswerDetails.setSubject1(studentEntryAnswerSaveDTO.getSubject1());
                    entryAnswerDetails.setCreateTime(date);
                    entryAnswerDetails.setModifyTime(date);
                    entryAnswerDetails.setDelFlag(SystemConstant.NOUGHT);
                    entryAnswerDetailsInsertSize = entryAnswerDetailsMapper.insertSelective(entryAnswerDetails);
                }
                if (SystemConstant.ZERO == entryAnswerDetailsInsertSize) {
                    throw new OperationException("删除失败");
                }
            }
        }
        return studentAnswerInsertSize;
    }

    @Transactional
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