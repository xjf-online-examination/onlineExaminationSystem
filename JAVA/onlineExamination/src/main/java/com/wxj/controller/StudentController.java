package com.wxj.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
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
     * @param jsonObject 条件
     * @return PageBean<StudentVO>
     */
    @RequestMapping(method = RequestMethod.GET, consumes = "application/json;charset=utf-8")
    public Object listStudentByParams(@RequestBody JSONObject jsonObject) {
        try {
            RequestBean<StudentParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<StudentParamsDTO>>(){});
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
     * @param id studentID
     * @return StudentVO
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = "application/json;charset=utf-8")
    public Object getStudentById(@PathVariable("id") Integer id) {
        try {
            StudentParamsDTO studentParamsDTO = new StudentParamsDTO();
            studentParamsDTO.setId(id);
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
     * @param jsonObject 学生信息
     * @return 201
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object save(@RequestBody JSONObject jsonObject) {
        try {
            RequestBean<StudentParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<StudentParamsDTO>>(){});
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
     * @param id studentId
     * @param jsonObject 要修改的学生信息
     * @return 201
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json;charset=utf-8")
    public Object modify(@PathVariable("id") Integer id, @RequestBody JSONObject jsonObject) {
        try {
            RequestBean<StudentParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<StudentParamsDTO>>(){});
            StudentParamsDTO studentParamsDTO = requestBean.getData();
            studentService.modify(id, studentParamsDTO);
            return ResponseUtils.success("201");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 删除
     * @param id studentId
     * @return 204
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = "application/json;charset=utf-8")
    public Object delete(@PathVariable("id") Integer id) {
        try {
            studentService.delete(id);
            return ResponseUtils.success("204");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 成绩
     * @param id studentId
     * @return 200
     */
    @RequestMapping(value = "/achievement/{id}", method = RequestMethod.GET, consumes = "application/json;charset=utf-8")
    public Object achievement(@PathVariable("id") Integer id) {
        List<AchievementVO> achievementVOList = studentService.achievement(id);
        return ResponseUtils.success("200", achievementVOList);
    }

    /**
     * 重置密码
     * @param id studentId
     * @return 201
     */
    @RequestMapping("/resetPassword/{id}")
    public Object resetPassword(@PathVariable("id") Integer id) {
        try {
            studentService.resetPassword(id);
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