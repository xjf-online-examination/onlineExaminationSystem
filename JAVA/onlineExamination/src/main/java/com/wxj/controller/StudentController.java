package com.wxj.controller;

import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.exception.ParamEmptyException;
import com.wxj.exception.ParamInvalidException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.StudentAnswerSaveDTO;
import com.wxj.model.DTO.StudentParamsDTO;
import com.wxj.model.VO.AchievementVO;
import com.wxj.model.VO.StudentVO;
import com.wxj.service.StudentServiceI;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: StudentController</p >
 * <p>Description: 学生管理</p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-09 23:41
 */
@RestController
@RequestMapping("/students")
public class StudentController {
    Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    StudentServiceI studentService;

    /**
     * 条件查询列表分页
     * @param requestBean 条件
     * @return PageBean<StudentVO>
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object listStudentByParams(HttpServletRequest request, @RequestBody RequestBean<StudentParamsDTO> requestBean) {
        try {
            StudentParamsDTO studentParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(studentParamsDTO,"currentPage", "pageSize");
            List<StudentVO> studentVOList = studentService.listStudentVOByParams(studentParamsDTO);

            Long count = studentService.countStudentVOByParams(studentParamsDTO);

            PageBean<StudentVO> pageBean = new PageBean(count, studentParamsDTO.getCurrentPage(), studentParamsDTO.getPageSize(), studentVOList);
            return ResponseUtils.success("200",pageBean);
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 根据id查询
     * @param requestBean studentID
     * @return StudentVO
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object getStudentById(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            StudentParamsDTO studentParamsDTO = new StudentParamsDTO();
            studentParamsDTO.setId(requestBean.getData());
            studentParamsDTO.setCurrentPage(1);
            studentParamsDTO.setPageSize(1);
            List<StudentVO> studentVOList = studentService.listStudentVOByParams(studentParamsDTO);

            return ResponseUtils.success("200", studentVOList.get(0));
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 新增
     * @param requestBean 学生信息
     * @return 201
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object save(HttpServletRequest request, @RequestBody RequestBean<StudentParamsDTO> requestBean) {
        try {
            StudentParamsDTO studentParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(studentParamsDTO, "sno", "name", "classId");
            studentService.save(studentParamsDTO);
            return ResponseUtils.success("201");
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 修改
     * @param requestBean 要修改的学生信息
     * @return 201
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object modify(HttpServletRequest request, @RequestBody RequestBean<StudentParamsDTO> requestBean) {
        try {
            StudentParamsDTO studentParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(studentParamsDTO, "id");
            studentService.modify(studentParamsDTO);
            return ResponseUtils.success("201");
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        }  catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 删除
     * @param requestBean studentId
     * @return 204
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object delete(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            studentService.delete(requestBean.getData());
            return ResponseUtils.success("204");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 成绩
     * @param requestBean studentId
     * @return 200
     */
    @RequestMapping(value = "/achievement", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object achievement(HttpServletRequest request, @RequestBody RequestBean<String> requestBean) {
        try {
            if (StringUtil.isEmptyTrim(requestBean.getData())) {
                throw new ParamEmptyException("data不能为空");
            }
            List<AchievementVO> achievementVOList = studentService.achievement(requestBean.getData());
            return ResponseUtils.success("200", achievementVOList);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 提交考试答案
     * @param request
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/studentAnswer", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object studentAnswer(HttpServletRequest request, @RequestBody RequestBean<StudentAnswerSaveDTO> requestBean) {
        try {
            StudentAnswerSaveDTO studentAnswerSaveDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(studentAnswerSaveDTO,"sno", "examScheduleId", "answerSaveDetailsDTOList");

            studentService.studentAnswer(studentAnswerSaveDTO);
            return ResponseUtils.success("200");
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 重置密码
     * @param requestBean studentId
     * @return 201
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object resetPassword(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            studentService.resetPassword(requestBean.getData());
            return ResponseUtils.success("201");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public Object studentImport(MultipartHttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {
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
                List<StudentParamsDTO> studentParamsDTOList = new ArrayList<>();
                Row rowZero = rs.getRow(0);
                Map<String,Integer> map = new HashMap<String,Integer>();
                for (int i = 0; i < rowZero.getLastCellNum() ; i++) {
                    rowZero.getCell(i).setCellType(CellType.STRING);
                    map.put(rowZero.getCell(i).toString(),i);
                }
                //获取Excel中的数据
                int sum = rs.getLastRowNum();
                Pattern character=Pattern.compile("^([0-9a-zA-Z]{0,13})$");// 判断只能是数字和字母
                Matcher match=null;
                for (int i = 1; i <= sum; i++) {
                    StudentParamsDTO studentParamsDTO = new StudentParamsDTO();
                    Row row = rs.getRow(i);
                    //读区数据
                    if(row.getCell(map.get("学号")) !=null){
                        row.getCell(map.get("学号")).setCellType(CellType.STRING);
                        String sno = row.getCell(map.get("学号")).toString();
                        if (sno == null || "".equals(sno) || "null".equals(sno)) {
                            throw new ParamInvalidException("学号不能为空");
                        }
                        sno = sno.trim();
                        match=character.matcher(sno);
                        if(match.matches()==false){
                            throw new ParamInvalidException("学号格式错误");
                        }else{
                            studentParamsDTO.setSno(sno);
                        }
                    }else{
                        throw new ParamEmptyException("学号不能为空");
                    }
                    if (row.getCell(map.get("学生姓名")) != null) {
                        row.getCell(map.get("学生姓名")).setCellType(CellType.STRING);
                        String name = row.getCell(map.get("学生姓名")).toString();
                        if (name == null || "".equals(name) || "null".equals(name)) {
                            throw new ParamInvalidException("学生姓名不能为空");
                        }
                        if (name.length() > 8) {
                            throw new ParamInvalidException("学生姓名格式错误");
                        }
                        studentParamsDTO.setName(name);
                    }else{
                        throw new ParamEmptyException("学生姓名不能为空");
                    }
                    if (row.getCell(map.get("班级编号")) != null) {
                        row.getCell(map.get("班级编号")).setCellType(CellType.STRING);
                        String classCode = row.getCell(map.get("班级编号")).toString();
                        if (classCode == null || "".equals(classCode) || "null".equals(classCode)) {
                            throw new ParamInvalidException("班级编号不能为空");
                        }
                        studentParamsDTO.setClassCode(classCode);
                    }
                    studentParamsDTOList.add(studentParamsDTO);
                }
                studentService.studentImport(studentParamsDTOList);
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

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) {
        OutputStream sos = null;
        // 模板名称
        String filename = "studentTemplate";
        response.setContentType("application/msexcel;charset=UTF-8");
        try {
            sos = response.getOutputStream();
            // 设置编码
            response.addHeader("Content-Disposition", "attachment;filename=\"" + new String((filename + ".xlsx").getBytes("GBK"), "ISO8859_1") + "\"");
            // 查找模板
            XSSFWorkbook templatewb = new XSSFWorkbook(StudentController.class.getClassLoader().getResource("template/studentTemplate.xlsx").openStream());
            templatewb.setSheetName(0, "学生信息");
            templatewb.write(sos);
            templatewb.close();
        } catch (Exception e) {
             logger.error("下载学生模版模板失败", e);
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