package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.InnerDataErrorException;
import com.wxj.exception.OperationException;
import com.wxj.mapper.ExamPaperMapper;
import com.wxj.mapper.ExamScheduleMapper;
import com.wxj.model.DTO.ExamScheduleParamsDTO;
import com.wxj.model.DTO.ExamScheduleSaveDTO;
import com.wxj.model.PO.ExamPaper;
import com.wxj.model.PO.ExamPaperExample;
import com.wxj.model.PO.ExamSchedule;
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
import java.util.List;

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

    @Override
    public List<ExamScheduleVO> listExamScheduleByParams(ExamScheduleParamsDTO examScheduleParamsDTO) {
        PageBounds pageBounds = new PageBounds(examScheduleParamsDTO.getCurrentPage(), examScheduleParamsDTO.getPageSize());
        List<ExamScheduleVO> examScheduleVOList = examScheduleMapper.listExamScheduleByParams(examScheduleParamsDTO, pageBounds);
        for (ExamScheduleVO examScheduleVO : examScheduleVOList) {
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
    public int delete(Integer id) {
        //TODO:
        return 0;
    }

    @Override
    public List<StudentExamScheduleVO> getStudentExamScheduleBySno(String sno) {
        return examScheduleMapper.selectStudentExamScheduleBySno(sno);
    }
}