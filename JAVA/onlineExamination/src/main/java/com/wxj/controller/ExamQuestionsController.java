package com.wxj.controller;

import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.exception.ParamEmptyException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.ExamQuestionsParamsDTO;
import com.wxj.model.DTO.ExamQuestionsSaveDTO;
import com.wxj.model.VO.ExamQuestionsDetailsVO;
import com.wxj.model.VO.ExamQuestionsVO;
import com.wxj.service.ExamQuestionsServiceI;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.ValidateParamsUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * <p>Title: ExamQuestionsController</p >
 * <p>Description: 试题管理</p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-14 23:21
 */
@RestController
@RequestMapping("/examQuestions")
public class ExamQuestionsController {
    Logger logger = LoggerFactory.getLogger(ExamQuestionsController.class);
    @Autowired
    ExamQuestionsServiceI examQuestionsService;

    /**
     * 条件查询分页列表
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object listExamQuestionsByParams(HttpServletRequest request, @RequestBody RequestBean<ExamQuestionsParamsDTO> requestBean) {
        try {
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
            ExamQuestionsDetailsVO examQuestionsDetailsVO = examQuestionsService.getExamQuestionsDetailsById(requestBean.getData());
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
    public Object save(HttpServletRequest request, @RequestBody RequestBean<ExamQuestionsSaveDTO> requestBean) {
        try {
            ExamQuestionsSaveDTO examQuestionsSaveDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(examQuestionsSaveDTO,"courseCode", "type", "title", "score");
            examQuestionsService.save(examQuestionsSaveDTO);
            return ResponseUtils.success("201");
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 修改
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object modify(HttpServletRequest request, RequestBean<ExamQuestionsSaveDTO> requestBean) {
        try {
            ExamQuestionsSaveDTO examQuestionsSaveDTO = requestBean.getData();
            examQuestionsService.modify(examQuestionsSaveDTO);
            return ResponseUtils.success("201");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 删除试题相关的信息
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object delete(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            examQuestionsService.delete(requestBean.getData());
            return ResponseUtils.success("204");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }


    public Object examQuestionsImport() {
        return null;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) {
        OutputStream sos = null;
        // 模板名称
        String filename = "examQuestionsTemplate";
        response.setContentType("application/msexcel;charset=UTF-8");
        try {
            sos = response.getOutputStream();
            // 设置编码
            response.addHeader("Content-Disposition", "attachment;filename=\"" + new String((filename + ".xlsx").getBytes("GBK"), "ISO8859_1") + "\"");
            // 查找模板
            XSSFWorkbook templatewb = new XSSFWorkbook(StudentController.class.getClassLoader().getResource("template/examQuestionsTemplate.xlsx").openStream());
            templatewb.setSheetName(0, "试题信息");
            templatewb.write(sos);
            templatewb.close();
        } catch (Exception e) {
            logger.error("下载试题模版模板失败", e);
        } finally {
            if (null != sos) {
                try {
                    sos.flush();
                    sos.close();
                } catch (Exception e2) {
                }
            }
        }
    }
}