package com.wxj.controller;

import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.StudentParamsDTO;
import com.wxj.model.VO.AchievementVO;
import com.wxj.model.VO.StudentVO;
import com.wxj.service.StudentServiceI;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.ValidateParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>Title: StudentController</p >
 * <p>Description: </p >
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
    public Object achievement(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        List<AchievementVO> achievementVOList = studentService.achievement(requestBean.getData());
        return ResponseUtils.success("200", achievementVOList);
    }

    /**
     * 重置密码
     * @param requestBean studentId
     * @return 201
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object resetPassword(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            studentService.resetPassword(requestBean.getData());
            return ResponseUtils.success("201");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    @RequestMapping(value = "/import")
    public Object studentImport() {
        return null;
    }

    public Object download() {
        return null;
    }
}