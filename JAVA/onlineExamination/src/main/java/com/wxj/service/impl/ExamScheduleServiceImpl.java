package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.InnerDataErrorException;
import com.wxj.exception.OperationException;
import com.wxj.mapper.*;
import com.wxj.model.DTO.ExamScheduleParamsDTO;
import com.wxj.model.DTO.ExamScheduleSaveDTO;
import com.wxj.model.PO.*;
import com.wxj.model.PO.Class;
import com.wxj.model.VO.ExamScheduleDetailsVO;
import com.wxj.model.VO.ExamScheduleVO;
import com.wxj.model.VO.StudentExamScheduleVO;
import com.wxj.service.ExamScheduleServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: ExamScheduleServiceImpl</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-17 00:12
 */
@Service
public class ExamScheduleServiceImpl implements ExamScheduleServiceI {
    Logger logger = LoggerFactory.getLogger(ExamScheduleServiceImpl.class);
    @Autowired
    ExamScheduleMapper examScheduleMapper;

    @Autowired
    ExamPaperMapper examPaperMapper;
    @Autowired
    StudentAnswerMapper studentAnswerMapper;
    @Autowired
    EntryAnswerDetailsMapper entryAnswerDetailsMapper;
    @Autowired
    StudentScheduleMapper studentScheduleMapper;

    @Override
    public List<ExamScheduleVO> listExamScheduleByParams(ExamScheduleParamsDTO examScheduleParamsDTO) {
        PageBounds pageBounds = new PageBounds(examScheduleParamsDTO.getCurrentPage(), examScheduleParamsDTO.getPageSize());
        List<ExamScheduleVO> examScheduleVOList = examScheduleMapper.listExamScheduleByParams(examScheduleParamsDTO, pageBounds);
        for (ExamScheduleVO examScheduleVO : examScheduleVOList) {

            Course course = examScheduleMapper.selectCourse(examScheduleVO.getId());
            List<Class> cLassList = examScheduleMapper.selectClassName(course.getId());
            examScheduleVO.setClassName(cLassList.stream().map(Class::getName).collect(Collectors.toList()));

            Date startTime = null;
            try {
                startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(examScheduleVO.getStartTime());
            } catch (ParseException e) {
                logger.error("com.wxj.service.impl.ExamScheduleServiceImpl.listExamScheduleByParams" + "数据库start_time转换错误");
                throw new InnerDataErrorException("时间转换错误");
            }
            if (new Date().getTime() < startTime.getTime()) {
                examScheduleVO.setStatus("1");
            }
            if (new Date().getTime() >= startTime.getTime() && new Date().getTime() <= startTime.getTime()+Integer.valueOf(examScheduleVO.getDuration())*60000) {
                examScheduleVO.setStatus("2");
            }
            if (startTime.getTime()+Integer.valueOf(examScheduleVO.getDuration())*60000 < new Date().getTime()) {
                examScheduleVO.setStatus("3");
            }
        }
        return examScheduleVOList;
    }

    @Override
    public Long countExamScheduleByParams(ExamScheduleParamsDTO examScheduleParamsDTO) {
        return examScheduleMapper.countExamScheduleByParams(examScheduleParamsDTO);
    }

    @Override
    public ExamScheduleDetailsVO getExamScheduleDetailsById(Integer id) {
        return examScheduleMapper.selectExamScheduleDetailsById(id);
    }

    @Override
    public int save(ExamScheduleSaveDTO examScheduleSaveDTO) {
        Date date = new Date();
        ExamPaperExample examPaperExample = new ExamPaperExample();
        examPaperExample.createCriteria().andCodeEqualTo(examScheduleSaveDTO.getExamPaperCode());
        List<ExamPaper> examPaperList = examPaperMapper.selectByExample(examPaperExample);
        if (examPaperList.isEmpty()) {
            throw new InnerDataErrorException("不存在"+examScheduleSaveDTO.getExamPaperCode()+"试卷");
        }

        ExamSchedule examSchedule = new ExamSchedule();
        BeanUtils.copyProperties(examScheduleSaveDTO, examSchedule);
        try {
            examSchedule.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(examScheduleSaveDTO.getStartTime()));
        } catch (ParseException e) {
            logger.error("com.wxj.service.impl.ExamScheduleServiceImpl.save 时间转换错误", e);
            throw new InnerDataErrorException("时间转换错误");
        }
        examSchedule.setDuration(examScheduleSaveDTO.getDuration().byteValue());
        examSchedule.setExamPaperId(examPaperList.get(0).getId());
        examSchedule.setExamPaperName(examPaperList.get(0).getName());
        examSchedule.setCreateTime(date);
        examSchedule.setModifyTime(date);
        examSchedule.setDelFlag(SystemConstant.NOUGHT);
        int i = examScheduleMapper.insertSelective(examSchedule);
        if (SystemConstant.ZERO == i) {
            throw new OperationException(" 插入失败");
        }

        return i;
    }

    @Override
    public int modify(ExamScheduleSaveDTO examScheduleSaveDTO) {
        ExamPaperExample examPaperExample = new ExamPaperExample();
        examPaperExample.createCriteria().andCodeEqualTo(examScheduleSaveDTO.getExamPaperCode());
        List<ExamPaper> examPaperList = examPaperMapper.selectByExample(examPaperExample);
        if (examPaperList.isEmpty()) {
            throw new InnerDataErrorException("不存在"+examScheduleSaveDTO.getExamPaperCode()+"试卷");
        }

        ExamSchedule examSchedule = new ExamSchedule();
        BeanUtils.copyProperties(examScheduleSaveDTO, examSchedule);
        examSchedule.setExamPaperId(examPaperList.get(0).getId());
        examSchedule.setExamPaperName(examPaperList.get(0).getName());
        try {
            examSchedule.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(examScheduleSaveDTO.getStartTime()));
        } catch (ParseException e) {
            logger.error("com.wxj.service.impl.ExamScheduleServiceImpl.modify 时间转换错误", e);
            throw new InnerDataErrorException("时间转换错误");
        }
        examSchedule.setDuration(examScheduleSaveDTO.getDuration().byteValue());
        examSchedule.setModifyTime(new Date());
        int i = examScheduleMapper.updateByPrimaryKeySelective(examSchedule);
        if (SystemConstant.ZERO == i) {
            throw new OperationException(" 修改失败");
        }

        return i;
    }

    @Override
    public void delete(Integer id) {
        //删除该考试安排会将该考试安排以及答题信息删除，确定删除？
        //删除这个试卷的考试安排
        try {
            int examScheduleDeleteSize = examScheduleMapper.deleteByPrimaryKey(id);
            if (examScheduleDeleteSize > 0) {
                //删除考生答案
                StudentAnswerExample studentAnswerExample = new StudentAnswerExample();
                studentAnswerExample.createCriteria().andExamScheduleIdEqualTo(id);
                List<Integer> studentAnswerIdList = studentAnswerMapper.selectByExample(studentAnswerExample).stream().map(StudentAnswer::getId).collect(Collectors.toList());
                studentAnswerMapper.deleteByExample(studentAnswerExample);
                if (null != studentAnswerIdList && studentAnswerIdList.size() > 0) {
                    EntryAnswerDetailsExample entryAnswerDetailsExample = new EntryAnswerDetailsExample();
                    entryAnswerDetailsExample.createCriteria().andEntryAnswerIdIn(studentAnswerIdList);
                    entryAnswerDetailsMapper.deleteByExample(entryAnswerDetailsExample);
                }
            }
        } catch (Exception e) {
            logger.error("com.wxj.service.impl.ExamScheduleServiceImpl.delete", e);
            throw new OperationException(" 删除失败");
        }
    }

    @Override
    public List<StudentExamScheduleVO> getStudentExamScheduleBySno(String sno) {
        List<StudentExamScheduleVO> studentExamScheduleVOList = examScheduleMapper.selectStudentExamScheduleBySno(sno);

        StudentScheduleExample studentScheduleExample = new StudentScheduleExample();
        studentScheduleExample.createCriteria().andSnoEqualTo(sno);
        List<StudentSchedule> studentScheduleList = studentScheduleMapper.selectByExample(studentScheduleExample);
        if (null == studentScheduleList || studentScheduleList.size() == 0) {
            return studentExamScheduleVOList;
        }
        List<Integer> examScheduleId = studentScheduleList.stream().map(StudentSchedule::getExamScheduleId).collect(Collectors.toList());

        Iterator it = studentExamScheduleVOList.iterator();
        while (it.hasNext()) {
            StudentExamScheduleVO studentExamScheduleVO = (StudentExamScheduleVO) it.next();
            if (examScheduleId.contains(studentExamScheduleVO.getExamScheduleId())) {
                it.remove();
            }
        }
        return studentExamScheduleVOList;
    }
}