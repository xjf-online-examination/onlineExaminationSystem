package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.exception.InnerDataErrorException;
import com.wxj.mapper.ExamScheduleMapper;
import com.wxj.model.DTO.ExamScheduleParamsDTO;
import com.wxj.model.VO.ExamScheduleVO;
import com.wxj.service.ExamScheduleServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
}