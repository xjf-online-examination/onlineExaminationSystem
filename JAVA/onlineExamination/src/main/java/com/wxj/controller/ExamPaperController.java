package com.wxj.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.ExamPaperParamsDTO;
import com.wxj.model.VO.ExamPaperVO;
import com.wxj.model.VO.ExamQuestionsVO;
import com.wxj.service.ExamPaperServiceI;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.ValidateParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Title: ExamPaperController</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-15 22:35
 */
@RestController
@RequestMapping("/examPaper")
public class ExamPaperController {
    @Autowired
    ExamPaperServiceI examPaperService;

    @RequestMapping(method = RequestMethod.GET,consumes = "applocation/json;charset=utf-8")
    public Object listExamPaperByParams(@RequestBody JSONObject jsonObject) {
        try {
            RequestBean<ExamPaperParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<ExamPaperParamsDTO>>(){});
            ExamPaperParamsDTO examPaperParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(examPaperParamsDTO,"currentPage", "pageSize");
            List<ExamPaperVO> examPaperVOList = examPaperService.listExamPaperByParams(examPaperParamsDTO);

            Long count = examPaperService.countExamPaperByParams(examPaperParamsDTO);

            PageBean<ExamQuestionsVO> pageBean = new PageBean(count, examPaperParamsDTO.getCurrentPage(), examPaperParamsDTO.getPageSize(), examPaperVOList);
            return ResponseUtils.success("200",pageBean);
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }
}