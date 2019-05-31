package com.wxj.controller;

import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.exception.ParamEmptyException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.ExamPaperParamsDTO;
import com.wxj.model.DTO.ExamPaperSaveModifyDTO;
import com.wxj.model.VO.ExamPaperDetailsVO;
import com.wxj.model.VO.ExamPaperVO;
import com.wxj.model.VO.StudentExamQuestionsTypeVO;
import com.wxj.model.VO.StudentExamQuestionsVO;
import com.wxj.service.ExamPaperServiceI;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.ValidateParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: ExamPaperController</p >
 * <p>Description: 试卷管理</p >
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

    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object listExamPaperByParams(HttpServletRequest request, @RequestBody RequestBean<ExamPaperParamsDTO> requestBean) {
        try {
            ExamPaperParamsDTO examPaperParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(examPaperParamsDTO,"currentPage", "pageSize");
            List<ExamPaperVO> examPaperVOList = examPaperService.listExamPaperByParams(examPaperParamsDTO);

            int count = examPaperService.countExamPaperByParams(examPaperParamsDTO);

            PageBean<ExamPaperVO> pageBean = new PageBean(count, examPaperParamsDTO.getCurrentPage(), examPaperParamsDTO.getPageSize(), examPaperVOList);
            return ResponseUtils.success("200",pageBean);
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 获取详情根据ID(学生考试用)
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/studentExamPaper", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object getStudentExamPaperDetailsById(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            List<StudentExamQuestionsTypeVO> studentExamQuestionsTypeVOList = examPaperService.getStudentExamPaperDetailsById(requestBean.getData());
            return ResponseUtils.success("200", studentExamQuestionsTypeVOList);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 获取详情根据ID(教师端用)
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/teacherExamPaper", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object getTeacherExamPaperDetailsById(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            ExamPaperDetailsVO examQuestionsDetailsVO = examPaperService.getTeacherExamPaperDetailsById(requestBean.getData());
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
    public Object save(HttpServletRequest request, @RequestBody RequestBean<ExamPaperSaveModifyDTO> requestBean) {
        try {
            ExamPaperSaveModifyDTO examPaperSaveModifyDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(examPaperSaveModifyDTO, "jobNo", "name");
            examPaperService.save(examPaperSaveModifyDTO, requestBean.getUserCode());
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
    public Object modify(HttpServletRequest request, @RequestBody RequestBean<ExamPaperSaveModifyDTO> requestBean) {
        try {
            ExamPaperSaveModifyDTO examPaperSaveModifyDTO = requestBean.getData();
            examPaperService.modify(examPaperSaveModifyDTO, requestBean.getUserCode());
            return ResponseUtils.success("201");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 删除
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object delete(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            examPaperService.delete(requestBean.getData());
            return ResponseUtils.success("204");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }
}