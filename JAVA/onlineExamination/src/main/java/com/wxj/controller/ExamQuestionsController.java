package com.wxj.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.exception.ParamEmptyException;
import com.wxj.exception.ParamInvalidException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.ExamQuestionsParamsDTO;
import com.wxj.model.DTO.ExamQuestionsSaveDTO;
import com.wxj.model.DTO.QuestionsPageDTO;
import com.wxj.model.PO.ExamQuestions;
import com.wxj.model.VO.ExamQuestionsDetailsVO;
import com.wxj.model.VO.ExamQuestionsVO;
import com.wxj.model.VO.SubjectOneVO;
import com.wxj.service.ExamQuestionsServiceI;
import com.wxj.utils.ExcelUtil;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.StringUtil;
import com.wxj.utils.ValidateParamsUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

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
            new ValidateParamsUtil().vaildParams(examQuestionsSaveDTO,"courseCode", "type", "title");
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

    /**
     * 导入
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public Object examQuestionsImport(MultipartHttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            // 读取上传的文件
            CommonsMultipartFile cf = (CommonsMultipartFile) file;
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
            InputStream inputStream = fi.getInputStream();
            Workbook wb = null;
            wb = new XSSFWorkbook(inputStream);// 解析xlsx格式
            Sheet rs = wb.getSheetAt(0);
            if (rs != null) {
                // 创建集合list
                List<ExamQuestions> examQuestionsList = Lists.newArrayList();
                Row rowZero = rs.getRow(0);
                Map<String,Integer> map = Maps.newHashMap();
                for (int i = 0; i < rowZero.getLastCellNum() ; i++) {
                    rowZero.getCell(i).setCellType(CellType.STRING);
                    map.put(rowZero.getCell(i).toString(),i);
                }
                //获取Excel中的数据
                int sum = rs.getLastRowNum();
                for (int i = 1; i <= sum; i++) {
                    ExamQuestions examQuestions = new ExamQuestions();
                    Row row = rs.getRow(i);
                    if (ExcelUtil.isRowEmpty(row)) {
                        continue;
                    }
                    //读区数据
                    if(row.getCell(map.get("课程编号")) !=null){
                        row.getCell(map.get("课程编号")).setCellType(CellType.STRING);
                        String courseCode = row.getCell(map.get("课程编号")).toString();
                        if (courseCode == null || "".equals(courseCode) || "null".equals(courseCode)) {
                            throw new ParamInvalidException("课程编号不能为空");
                        }
                        examQuestions.setCourseCode(courseCode.trim());
                    }else{
                        throw new ParamEmptyException("课程编号不能为空");
                    }
                    if (row.getCell(map.get("试题类型")) != null) {
                        row.getCell(map.get("试题类型")).setCellType(CellType.STRING);
                        String type = row.getCell(map.get("试题类型")).toString();
                        if (type == null || "".equals(type) || "null".equals(type)) {
                            throw new ParamInvalidException("试题类型不能为空");
                        }
                        examQuestions.setType(type.trim());
                    }else{
                        throw new ParamEmptyException("试题类型不能为空");
                    }
                    if (row.getCell(map.get("题目")) != null) {
                        row.getCell(map.get("题目")).setCellType(CellType.STRING);
                        String title = row.getCell(map.get("题目")).toString();
                        if (title == null || "".equals(title) || "null".equals(title)) {
                            throw new ParamInvalidException("题目不能为空");
                        }
                        examQuestions.setTitle(title.trim());
                    } else {
                        throw new ParamEmptyException("题目不能为空");
                    }

                    row.getCell(map.get("选项A")).setCellType(CellType.STRING);
                    String optiona = row.getCell(map.get("选项A")).toString();
                    examQuestions.setOptiona(optiona);

                    row.getCell(map.get("选项B")).setCellType(CellType.STRING);
                    String optionb = row.getCell(map.get("选项B")).toString();
                    examQuestions.setOptionb(optionb);

                    row.getCell(map.get("选项C")).setCellType(CellType.STRING);
                    String optionc = row.getCell(map.get("选项C")).toString();
                    examQuestions.setOptionc(optionc);

                    row.getCell(map.get("选项D")).setCellType(CellType.STRING);
                    String optiond = row.getCell(map.get("选项D")).toString();
                    examQuestions.setOptiond(optiond);

                    row.getCell(map.get("选项E")).setCellType(CellType.STRING);
                    String optione = row.getCell(map.get("选项E")).toString();
                    examQuestions.setOptione(optione);

                    row.getCell(map.get("单选答案")).setCellType(CellType.STRING);
                    String singleAnswer = row.getCell(map.get("单选答案")).toString();
                    examQuestions.setSingleAnswer(singleAnswer);

                    row.getCell(map.get("多选或不定项选择答案")).setCellType(CellType.STRING);
                    String multipleAnswer1 = row.getCell(map.get("多选或不定项选择答案")).toString();
                    examQuestions.setMultipleAnswer(multipleAnswer1);

                    row.getCell(map.get("判断题答案")).setCellType(CellType.STRING);
                    String yesNoAnswer = row.getCell(map.get("判断题答案")).toString();
                    examQuestions.setYesNoAnswer(yesNoAnswer);

                    row.getCell(map.get("分值")).setCellType(CellType.STRING);
                    Integer score = Integer.valueOf(row.getCell(map.get("分值")).toString());
                    examQuestions.setScore(score);

                    examQuestions.setCode(StringUtil.getRandom());
                    Date date = new Date();
                    examQuestions.setCreateTime(date);
                    examQuestions.setModifyTime(date);
                    examQuestions.setDelFlag(SystemConstant.NOUGHT);

                    examQuestionsList.add(examQuestions);
                }
                examQuestionsService.examQuestionsImport(examQuestionsList);
            }
            return ResponseUtils.success("200");
        } catch (FileNotFoundException e) {
            logger.error("com.wxj.controller.StudentController.studentImport数据解析错误", e);
            return ResponseUtils.error(e.getMessage());
        } catch (IOException e) {
            logger.error("com.wxj.controller.StudentController.studentImport数据解析错误", e);
            return ResponseUtils.error(e.getMessage());
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 下载模版
     * @param response
     */
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
                    logger.error("com.wxj.controller.ExamQuestionsController.download", e2);
                }
            }
        }
    }

    /**
     * 查询分页列表(供新增试卷时使用)
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/listPage", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object listExamQuestions(HttpServletRequest request, @RequestBody RequestBean<QuestionsPageDTO> requestBean) {
        try {
            QuestionsPageDTO pageDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(pageDTO,"courseCode", "currentPage", "pageSize");
            List<ExamQuestionsDetailsVO> examQuestionsDetailsVOList = examQuestionsService.listExamQuestions(pageDTO);

            Long count = examQuestionsService.countExamQuestions(pageDTO);

            PageBean<ExamQuestionsDetailsVO> pageBean = new PageBean(count, pageDTO.getCurrentPage(), pageDTO.getPageSize(), examQuestionsDetailsVOList);
            return ResponseUtils.success("200",pageBean);
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 查询一级科目列表(供新增试题使用)
     * @return
     */
    @RequestMapping(value = "/listSubjectOne", method = RequestMethod.GET)
    public Object listSubjectOne() {
        try {
            List<SubjectOneVO> subjectOneVOList = examQuestionsService.listSubjectOne();

            return ResponseUtils.success("200",subjectOneVOList);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

}