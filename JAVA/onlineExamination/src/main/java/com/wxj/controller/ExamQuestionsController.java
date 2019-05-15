package com.wxj.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.ExamQuestionsParamsDTO;
import com.wxj.model.DTO.ExamQuestionsSaveDTO;
import com.wxj.model.VO.ExamQuestionsVO;
import com.wxj.service.ExamQuestionsServiceI;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.ValidateParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Title: ExamQuestionsController</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-14 23:21
 */
@RestController
@RequestMapping("/achievement")
public class ExamQuestionsController {
    @Autowired
    ExamQuestionsServiceI examQuestionsService;

    @RequestMapping(method = RequestMethod.GET, consumes = "application/json;charset=utf-8")
    public Object listExamQuestionsByParams(@RequestBody JSONObject jsonObject) {
        try {
            RequestBean<ExamQuestionsParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<ExamQuestionsParamsDTO>>(){});
            ExamQuestionsParamsDTO examQuestionsParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(examQuestionsParamsDTO,"currentPage", "pageSize");
            List<ExamQuestionsVO> examQuestionsVOList = examQuestionsService.listExamQuestionsByParams(examQuestionsParamsDTO);

            Long count = examQuestionsService.countExamQuestionsByParams(examQuestionsParamsDTO);

            PageBean<ExamQuestionsVO> pageBean = new PageBean(count, examQuestionsParamsDTO.getCurrentPage(), examQuestionsParamsDTO.getPageSize(), examQuestionsVOList);
            return ResponseUtils.success("200",pageBean);
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = "application/json;charset=utf-8")
    public Object getExamQuestionsDetailsById(@PathVariable("id") Integer id) {
        ExamQuestionsVO examQuestionsVO = examQuestionsService.getExamQuestionsDetailsById(id);
        return ResponseUtils.success("200", examQuestionsVO);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object save(@RequestBody JSONObject jsonObject) {
        try {
            RequestBean<ExamQuestionsSaveDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<ExamQuestionsSaveDTO>>(){});
            ExamQuestionsSaveDTO examQuestionsSaveDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(examQuestionsSaveDTO,"courseCode", "type", "title");
            examQuestionsService.save(examQuestionsSaveDTO);
            return ResponseUtils.success("201");
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }
}