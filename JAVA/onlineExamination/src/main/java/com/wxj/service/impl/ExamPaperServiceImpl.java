package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.wxj.constant.ExamConstant;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.exception.OperationException;
import com.wxj.exception.SystemErrorException;
import com.wxj.logic.ExamPaperLogic;
import com.wxj.mapper.*;
import com.wxj.model.DTO.ExamPaperParamsDTO;
import com.wxj.model.DTO.ExamPaperSaveModifyDTO;
import com.wxj.model.PO.*;
import com.wxj.model.VO.*;
import com.wxj.service.ExamPaperServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>Title: ExamPaperServiceImpl</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-15 22:42
 */
@Service
public class ExamPaperServiceImpl implements ExamPaperServiceI {
    private Logger logger = LoggerFactory.getLogger(ExamPaperServiceImpl.class);
    @Autowired
    ExamPaperMapper examPaperMapper;
    @Autowired
    ExamPaperLogic examPaperLogic;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    ExamPaperQuestionsMapper examPaperQuestionsMapper;
    @Autowired
    ExamScheduleMapper examScheduleMapper;
    @Autowired
    StudentAnswerMapper studentAnswerMapper;
    @Autowired
    EntryAnswerDetailsMapper entryAnswerDetailsMapper;
    @Autowired
    ExamQuestionsMapper examQuestionsMapper;
    @Autowired
    EntryStandardAnswerDetailsMapper entryStandardAnswerDetailsMapper;

    @Override
    public List<ExamPaperVO> listExamPaperByParams(ExamPaperParamsDTO examPaperParamsDTO) {
        PageBounds pageBounds = new PageBounds(examPaperParamsDTO.getCurrentPage(), examPaperParamsDTO.getPageSize());
        return examPaperMapper.selectExamPaperByParams(examPaperParamsDTO, pageBounds);
    }

    @Override
    public int countExamPaperByParams(ExamPaperParamsDTO examPaperParamsDTO) {
        return examPaperMapper.countExamPaperByParams(examPaperParamsDTO);
    }

    @Override
    public List<StudentExamQuestionsTypeVO> getStudentExamPaperDetailsById(Integer id) {
        List<StudentExamQuestionsTypeVO> studentExamQuestionsTypeVOList = Lists.newArrayList();
        try {
            List<StudentExamQuestionsVO> studentExamQuestionsVOList = examQuestionsMapper.selectStudentExamPaperDetailsById(id);

            List<StudentExamQuestionsVO> studentExamQuestionsVOList1 = studentExamQuestionsVOList.stream().filter(obj->obj.getType().equals(ExamConstant.EXAM_QUESTIONS_TYPE_ONE)).collect(Collectors.toList());
            List<StudentExamQuestionsVO> studentExamQuestionsVOList2 = studentExamQuestionsVOList.stream().filter(obj->obj.getType().equals(ExamConstant.EXAM_QUESTIONS_TYPE_TWE)).collect(Collectors.toList());
            List<StudentExamQuestionsVO> studentExamQuestionsVOList3 = studentExamQuestionsVOList.stream().filter(obj->obj.getType().equals(ExamConstant.EXAM_QUESTIONS_TYPE_THREE)).collect(Collectors.toList());
            List<StudentExamQuestionsVO> studentExamQuestionsVOList4 = studentExamQuestionsVOList.stream().filter(obj->obj.getType().equals(ExamConstant.EXAM_QUESTIONS_TYPE_FOUR)).collect(Collectors.toList());
            List<StudentExamQuestionsVO> studentExamQuestionsVOList5 = studentExamQuestionsVOList.stream().filter(obj->obj.getType().equals(ExamConstant.EXAM_QUESTIONS_TYPE_FIVE)).collect(Collectors.toList());
            List<StudentExamQuestionsVO> studentExamQuestionsVOList6 = studentExamQuestionsVOList.stream().filter(obj->obj.getType().equals(ExamConstant.EXAM_QUESTIONS_TYPE_SIX)).collect(Collectors.toList());

            StudentExamQuestionsTypeVO studentExamQuestionsTypeVO;
            if (null != studentExamQuestionsVOList1 && studentExamQuestionsVOList1.size() > 0) {
                studentExamQuestionsTypeVO = new StudentExamQuestionsTypeVO();
                studentExamQuestionsTypeVO.setType("1");
                studentExamQuestionsTypeVO.setStudentExamQuestionsVOList(studentExamQuestionsVOList1.stream().sorted(Comparator.comparing(StudentExamQuestionsVO::getType)).collect(Collectors.toList()));
                studentExamQuestionsTypeVOList.add(studentExamQuestionsTypeVO);
            }

            if (null != studentExamQuestionsVOList2 && studentExamQuestionsVOList2.size() > 0) {
                studentExamQuestionsTypeVO = new StudentExamQuestionsTypeVO();
                studentExamQuestionsTypeVO.setType("2");
                studentExamQuestionsTypeVO.setStudentExamQuestionsVOList(studentExamQuestionsVOList2.stream().sorted(Comparator.comparing(StudentExamQuestionsVO::getType)).collect(Collectors.toList()));
                studentExamQuestionsTypeVOList.add(studentExamQuestionsTypeVO);
            }

            if (null != studentExamQuestionsVOList3 && studentExamQuestionsVOList3.size() > 0) {
                studentExamQuestionsTypeVO = new StudentExamQuestionsTypeVO();
                studentExamQuestionsTypeVO.setType("3");
                studentExamQuestionsTypeVO.setStudentExamQuestionsVOList(studentExamQuestionsVOList3.stream().sorted(Comparator.comparing(StudentExamQuestionsVO::getType)).collect(Collectors.toList()));
                studentExamQuestionsTypeVOList.add(studentExamQuestionsTypeVO);
            }

            if (null != studentExamQuestionsVOList4 && studentExamQuestionsVOList4.size() > 0) {
                studentExamQuestionsTypeVO = new StudentExamQuestionsTypeVO();
                studentExamQuestionsTypeVO.setType("4");
                studentExamQuestionsTypeVO.setStudentExamQuestionsVOList(studentExamQuestionsVOList4.stream().sorted(Comparator.comparing(StudentExamQuestionsVO::getType)).collect(Collectors.toList()));
                studentExamQuestionsTypeVOList.add(studentExamQuestionsTypeVO);
            }

            if (null != studentExamQuestionsVOList5 && studentExamQuestionsVOList5.size() > 0) {
                studentExamQuestionsTypeVO = new StudentExamQuestionsTypeVO();
                studentExamQuestionsTypeVO.setType("5");
                studentExamQuestionsTypeVO.setStudentExamQuestionsVOList(studentExamQuestionsVOList5.stream().sorted(Comparator.comparing(StudentExamQuestionsVO::getType)).collect(Collectors.toList()));
                studentExamQuestionsTypeVOList.add(studentExamQuestionsTypeVO);
            }
            if (null != studentExamQuestionsVOList6 && studentExamQuestionsVOList6.size() > 0) {
                studentExamQuestionsTypeVO = new StudentExamQuestionsTypeVO();
                List<StudentExamQuestionsVO> studentExamQuestionsVOS = studentExamQuestionsVOList6.stream().sorted(Comparator.comparing(StudentExamQuestionsVO::getType)).collect(Collectors.toList());
                for (StudentExamQuestionsVO studentExamQuestionsVO : studentExamQuestionsVOS) {
                    Map<String, Object> map = new HashMap<>(2);
                    map.put("examPaperId", id);
                    map.put("questionsNo", studentExamQuestionsVO.getQuestionsNo());
                    Integer row = examQuestionsMapper.selectQuestionsEntryRow(map);
                    studentExamQuestionsVO.setRow(row);
                }
                studentExamQuestionsTypeVO.setType("6");
                studentExamQuestionsTypeVO.setStudentExamQuestionsVOList(studentExamQuestionsVOS);
                studentExamQuestionsTypeVOList.add(studentExamQuestionsTypeVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentExamQuestionsTypeVOList;
    }

    @Override
    public ExamPaperDetailsVO getTeacherExamPaperDetailsById(Integer id) {
        ExamPaperDetailsVO examPaperDetailsVO = examPaperMapper.selectExamPaperDetailsById(id);

        try {
            List<ExamQuestionsDetailsVO> examQuestionsDetailsVOList = examPaperMapper.selectExamQuestions(id);
            if (null == examQuestionsDetailsVOList) {
                throw new SystemErrorException("试卷与试题对应关系不存在");
            }

            List<ExamQuestionsTypeVO> examQuestionsTypeVOList = Lists.newArrayList();
            ExamQuestionsTypeVO examQuestionsTypeVO;

            List<ExamQuestionsDetailsVO> examQuestionsDetailsVOList1 =  examQuestionsDetailsVOList.stream().filter(obj->obj.getType().equals(ExamConstant.EXAM_QUESTIONS_TYPE_ONE)).collect(Collectors.toList());

            if (null != examQuestionsDetailsVOList1 && examQuestionsDetailsVOList1.size() > 0) {
                examQuestionsTypeVO = new ExamQuestionsTypeVO();
                examQuestionsTypeVO.setType("1");
                examQuestionsTypeVO.setExamQuestionsDetailsVOList(examQuestionsDetailsVOList1.stream().sorted(Comparator.comparing(ExamQuestionsDetailsVO::getType)).collect(Collectors.toList()));
                examQuestionsTypeVOList.add(examQuestionsTypeVO);
            }
            List<ExamQuestionsDetailsVO> examQuestionsDetailsVOList2 =  examQuestionsDetailsVOList.stream().filter(obj->obj.getType().equals(ExamConstant.EXAM_QUESTIONS_TYPE_TWE)).collect(Collectors.toList());
            if (null != examQuestionsDetailsVOList2 && examQuestionsDetailsVOList2.size() > 0) {
                examQuestionsTypeVO = new ExamQuestionsTypeVO();
                examQuestionsTypeVO.setType("2");
                examQuestionsTypeVO.setExamQuestionsDetailsVOList(examQuestionsDetailsVOList2.stream().sorted(Comparator.comparing(ExamQuestionsDetailsVO::getType)).collect(Collectors.toList()));
                examQuestionsTypeVOList.add(examQuestionsTypeVO);
            }
            List<ExamQuestionsDetailsVO> examQuestionsDetailsVOList3 =  examQuestionsDetailsVOList.stream().filter(obj->obj.getType().equals(ExamConstant.EXAM_QUESTIONS_TYPE_THREE)).collect(Collectors.toList());
            if (null != examQuestionsDetailsVOList3 && examQuestionsDetailsVOList3.size() > 0) {
                examQuestionsTypeVO = new ExamQuestionsTypeVO();
                examQuestionsTypeVO.setType("3");
                examQuestionsTypeVO.setExamQuestionsDetailsVOList(examQuestionsDetailsVOList3.stream().sorted(Comparator.comparing(ExamQuestionsDetailsVO::getType)).collect(Collectors.toList()));
                examQuestionsTypeVOList.add(examQuestionsTypeVO);
            }
            List<ExamQuestionsDetailsVO> examQuestionsDetailsVOList4 =  examQuestionsDetailsVOList.stream().filter(obj->obj.getType().equals(ExamConstant.EXAM_QUESTIONS_TYPE_THREE)).collect(Collectors.toList());
            if (null != examQuestionsDetailsVOList4 && examQuestionsDetailsVOList4.size() > 0) {
                examQuestionsTypeVO = new ExamQuestionsTypeVO();
                examQuestionsTypeVO.setType("4");
                examQuestionsTypeVO.setExamQuestionsDetailsVOList(examQuestionsDetailsVOList4.stream().sorted(Comparator.comparing(ExamQuestionsDetailsVO::getType)).collect(Collectors.toList()));
                examQuestionsTypeVOList.add(examQuestionsTypeVO);
            }
            List<ExamQuestionsDetailsVO> examQuestionsDetailsVOList5 =  examQuestionsDetailsVOList.stream().filter(obj->obj.getType().equals(ExamConstant.EXAM_QUESTIONS_TYPE_FIVE)).collect(Collectors.toList());
            if (null != examQuestionsDetailsVOList5 && examQuestionsDetailsVOList5.size() > 0) {
                examQuestionsTypeVO = new ExamQuestionsTypeVO();
                examQuestionsTypeVO.setType("5");
                examQuestionsTypeVO.setExamQuestionsDetailsVOList(examQuestionsDetailsVOList5.stream().sorted(Comparator.comparing(ExamQuestionsDetailsVO::getType)).collect(Collectors.toList()));
                examQuestionsTypeVOList.add(examQuestionsTypeVO);
            }
            List<ExamQuestionsDetailsVO> examQuestionsDetailsVOList6 = examQuestionsDetailsVOList.stream().filter(obj->obj.getType().equals(ExamConstant.EXAM_QUESTIONS_TYPE_SIX)).collect(Collectors.toList());
            if (null != examQuestionsDetailsVOList6 && examQuestionsDetailsVOList6.size() > 0) {
                examQuestionsTypeVO = new ExamQuestionsTypeVO();
                examQuestionsTypeVO.setType("6");
                examQuestionsTypeVO.setExamQuestionsDetailsVOList(examQuestionsDetailsVOList6.stream().sorted(Comparator.comparing(ExamQuestionsDetailsVO::getType)).collect(Collectors.toList()));
                examQuestionsTypeVOList.add(examQuestionsTypeVO);
            }
            examPaperDetailsVO.setExamQuestionsTypeVOList(examQuestionsTypeVOList);

            for (ExamQuestionsDetailsVO examQuestionsDetailsVO : examQuestionsDetailsVOList6) {
                List<EntryStandardAnswerDetailsVO> entryStandardAnswerDetailsVOList = examPaperMapper.selectEntryAnswer(examQuestionsDetailsVO.getId());
                examQuestionsDetailsVO.setEntryStandardAnswerDetailsVOList(entryStandardAnswerDetailsVOList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return examPaperDetailsVO;
    }

    @Override
    public int save(ExamPaperSaveModifyDTO examPaperSaveModifyDTO, String userCode) throws BusinessRuntimeException {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andJobNoEqualTo(userCode);
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        //1:自动 2:手动
        int i = 0;
        switch (examPaperSaveModifyDTO.getType()) {
            case 1:
                i = examPaperLogic.automaticSave(teacherList.get(0).getId(), examPaperSaveModifyDTO);
                break;
            case 2:
                i = examPaperLogic.manualSave(teacherList.get(0).getId(), examPaperSaveModifyDTO);
        }
        return i;
    }

    @Transactional
    @Override
    public int modify(ExamPaperSaveModifyDTO examPaperSaveModifyDTO, String userCode) {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andJobNoEqualTo(userCode);
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        //修改试卷
        int examPaperUpdateSize;
        int examPaperQuestionsDeleteSize;
        int examPaperQuestionsInsertSize;
        try {
            ExamPaper examPaper = new ExamPaper();
            BeanUtils.copyProperties(examPaperSaveModifyDTO, examPaper);
            Integer size = examPaperSaveModifyDTO.getExamPaperQuestionsDTOList().size();
            if (size == null) {
                size = 0;
            }
            examPaper.setQuestionsAmount(size.byteValue());
            examPaper.setOpeator(teacherList.get(0).getId());
            examPaper.setModifyTime(new Date());
            examPaperUpdateSize = examPaperMapper.updateByPrimaryKeySelective(examPaper);
            //删除试卷试题对应关系
            ExamPaperQuestionsExample examPaperQuestionsExample = new ExamPaperQuestionsExample();
            examPaperQuestionsExample.createCriteria().andExamPaperIdEqualTo(examPaper.getId());
            examPaperQuestionsDeleteSize = examPaperQuestionsMapper.deleteByExample(examPaperQuestionsExample);
            //重新插入
            List<ExamPaperQuestions> examPaperQuestionsList = examPaperLogic.assembleExamPaperQuestionsList(examPaper.getId(), examPaperSaveModifyDTO.getExamPaperQuestionsDTOList());
            examPaperQuestionsInsertSize = examPaperQuestionsMapper.batchInsert(examPaperQuestionsList);
        } catch (Exception e) {
            logger.error("com.wxj.service.impl.ExamPaperServiceImpl.modify", e);
            throw new OperationException("修改试卷失败");
        }
        if (examPaperUpdateSize == 0 || examPaperQuestionsInsertSize != examPaperSaveModifyDTO.getExamPaperQuestionsDTOList().size()) {
            throw new OperationException("修改试卷失败");
        }
        return 0;
    }

    @Transactional
    @Override
    public void delete(Integer examPaperId) {
        //删除试卷会将改试卷相关的考试信息以及答题信息删除，确定删除？
        //删除试卷与试题对应关系
        ExamPaper examPaper = examPaperMapper.selectByPrimaryKey(examPaperId);
        if (null == examPaper) {
            throw new OperationException("试卷不存在");
        }
        try {
            ExamPaperQuestionsExample examPaperQuestionsExample = new ExamPaperQuestionsExample();
            examPaperQuestionsExample.createCriteria().andExamPaperIdEqualTo(examPaperId);
            examPaperQuestionsMapper.deleteByExample(examPaperQuestionsExample);
            //删除这个课程的试卷
            examPaperMapper.deleteByPrimaryKey(examPaperId);
            //删除这个试卷的考试安排
            ExamScheduleExample examScheduleExample = new ExamScheduleExample();
            examScheduleExample.createCriteria().andExamPaperIdEqualTo(examPaperId);
            List<Integer> examScheduleIdList = examScheduleMapper.selectByExample(examScheduleExample).stream().map(ExamSchedule::getId).collect(Collectors.toList());
            if (null != examScheduleIdList && examScheduleIdList.size() > 0) {
                examScheduleMapper.deleteByExample(examScheduleExample);
                //删除考生答案
                StudentAnswerExample studentAnswerExample = new StudentAnswerExample();
                studentAnswerExample.createCriteria().andExamScheduleIdIn(examScheduleIdList);
                List<Integer> studentAnswerIdList = studentAnswerMapper.selectByExample(studentAnswerExample).stream().map(StudentAnswer::getId).collect(Collectors.toList());
                studentAnswerMapper.deleteByExample(studentAnswerExample);
                if (null != studentAnswerIdList && studentAnswerIdList.size() > 0) {
                    EntryAnswerDetailsExample entryAnswerDetailsExample = new EntryAnswerDetailsExample();
                    entryAnswerDetailsExample.createCriteria().andEntryAnswerIdIn(studentAnswerIdList);
                    entryAnswerDetailsMapper.deleteByExample(entryAnswerDetailsExample);
                }
            }
        } catch (Exception e) {
            logger.error("com.wxj.service.impl.ExamPaperServiceImpl.delete", e);
            throw new OperationException("删除失败");
        }
    }
}