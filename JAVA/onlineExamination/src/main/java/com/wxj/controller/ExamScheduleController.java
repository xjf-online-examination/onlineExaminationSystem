package com.wxj.controller;

import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.exception.ParamEmptyException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.ExamQuestionsSaveDTO;
import com.wxj.model.DTO.ExamScheduleParamsDTO;
import com.wxj.model.DTO.ExamScheduleSaveDTO;
import com.wxj.model.VO.ExamScheduleDetailsVO;
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
 * <p>Description: 考试安排</p >
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

    /**
     * 获取详情根据ID
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object getExamQuestionsDetailsById(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            ExamScheduleDetailsVO examQuestionsDetailsVO = examScheduleService.getExamScheduleDetailsById(requestBean.getData());
            return ResponseUtils.success("200", examQuestionsDetailsVO);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 新增
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object save(HttpServletRequest request, @RequestBody RequestBean<ExamScheduleSaveDTO> requestBean) {
        try {
            ExamScheduleSaveDTO examScheduleSaveDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(examScheduleSaveDTO,"examPaperCode", "startTime", "duration");
            examScheduleService.save(examScheduleSaveDTO);
            return ResponseUtils.success("201");
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 新增
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object modify(HttpServletRequest request, @RequestBody RequestBean<ExamScheduleSaveDTO> requestBean) {
        try {
            ExamScheduleSaveDTO examScheduleSaveDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(examScheduleSaveDTO,"id");
            examScheduleService.modify(examScheduleSaveDTO);
            return ResponseUtils.success("201");
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 新增
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object delete(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            examScheduleService.delete(requestBean.getData());
            return ResponseUtils.success("201");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }
}