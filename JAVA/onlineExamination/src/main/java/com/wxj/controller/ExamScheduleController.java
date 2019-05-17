package com.wxj.controller;

import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.ExamQuestionsParamsDTO;
import com.wxj.model.DTO.ExamScheduleParamsDTO;
import com.wxj.model.VO.ExamScheduleVO;
import com.wxj.service.ExamScheduleServiceI;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.ValidateParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>Title: ExamScheduleController</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-16 23:09
 */
@RestController
@RequestMapping("/examSchedule")
public class ExamScheduleController {

    @Autowired
    ExamScheduleServiceI examScheduleService;

    /**
     * 条件查询分页列表
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object listExamScheduleByParams(HttpServletRequest request, @RequestBody RequestBean<ExamScheduleParamsDTO> requestBean) {
        try {
            ExamScheduleParamsDTO examScheduleParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(examScheduleParamsDTO,"currentPage", "pageSize");
            List<ExamScheduleVO> examQuestionsVOList = examScheduleService.listExamScheduleByParams(examScheduleParamsDTO);

            Long count = examScheduleService.countExamScheduleByParams(examScheduleParamsDTO);

            PageBean<ExamScheduleVO> pageBean = new PageBean(count, examScheduleParamsDTO.getCurrentPage(), examScheduleParamsDTO.getPageSize(), examQuestionsVOList);
            return ResponseUtils.success("200",pageBean);
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }
}