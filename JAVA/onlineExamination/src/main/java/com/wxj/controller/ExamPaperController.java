package com.wxj.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.ExamPaperParamsDTO;
import com.wxj.model.DTO.ExamPaperSaveModifyDTO;
import com.wxj.model.VO.ExamPaperDetailsVO;
import com.wxj.model.VO.ExamPaperVO;
import com.wxj.model.VO.ExamQuestionsVO;
import com.wxj.service.ExamPaperServiceI;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.ValidateParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET, consumes = "application/json;charset=utf-8")
    public Object listExamPaperByParams(@RequestBody JSONObject jsonObject) {
        try {
            RequestBean<ExamPaperParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<ExamPaperParamsDTO>>(){});
            ExamPaperParamsDTO examPaperParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(examPaperParamsDTO,"currentPage", "pageSize");
            List<ExamPaperVO> examPaperVOList = examPaperService.listExamPaperByParams(examPaperParamsDTO);

            int count = examPaperService.countExamPaperByParams(examPaperParamsDTO);

            PageBean<ExamQuestionsVO> pageBean = new PageBean(count, examPaperParamsDTO.getCurrentPage(), examPaperParamsDTO.getPageSize(), examPaperVOList);
            return ResponseUtils.success("200",pageBean);
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 获取详情根据ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = "application/json;charset=utf-8")
    public Object getExamQuestionsDetailsById(@PathVariable("id") Integer id) {
        ExamPaperDetailsVO examQuestionsDetailsVO = examPaperService.getExamPaperDetailsById(id);
        return ResponseUtils.success("200", examQuestionsDetailsVO);
    }

    /**
     * 新增
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/automatic", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object save(@RequestBody JSONObject jsonObject) {
        try {
            RequestBean<ExamPaperSaveModifyDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<ExamPaperSaveModifyDTO>>(){});
            ExamPaperSaveModifyDTO examPaperSaveModifyDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(examPaperSaveModifyDTO, "jobNo", "name");
            examPaperService.save(examPaperSaveModifyDTO);
            return ResponseUtils.success("201");
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 修改
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object modify(@PathVariable("id")Integer id, @RequestBody JSONObject jsonObject) {
        try {
            RequestBean<ExamPaperSaveModifyDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<ExamPaperSaveModifyDTO>>(){});
            ExamPaperSaveModifyDTO examPaperSaveModifyDTO = requestBean.getData();
            examPaperService.modify(id, examPaperSaveModifyDTO);
            return ResponseUtils.success("201");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }
}