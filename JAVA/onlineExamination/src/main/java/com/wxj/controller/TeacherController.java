package com.wxj.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.TeacherParamsDTO;
import com.wxj.model.VO.TeacherTaughtVO;
import com.wxj.model.VO.TeacherVO;
import com.wxj.service.TeacherServiceI;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.ValidateParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Title: TeacherController</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-13 21:46
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherServiceI teacherService;

    /**
     * 条件查询分页列表
     * @param jsonObject
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, consumes = "application/json;charset=utf-8")
    public Object listTeacherByParams(@RequestBody JSONObject jsonObject) {
        try {
            RequestBean<TeacherParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<TeacherParamsDTO>>(){});
            TeacherParamsDTO teacherParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(teacherParamsDTO,"currentPage", "pageSize");
            List<TeacherVO> studentVOList = teacherService.listTeacherByParams(teacherParamsDTO);

            Long count = teacherService.countTeacherByParams(teacherParamsDTO);

            PageBean<TeacherVO> pageBean = new PageBean(count, teacherParamsDTO.getCurrentPage(), teacherParamsDTO.getPageSize(), studentVOList);
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
    public Object getTeacherById(@PathVariable("id") Integer id) {
        try {
            TeacherParamsDTO teacherParamsDTO = new TeacherParamsDTO();
            teacherParamsDTO.setId(id);
            teacherParamsDTO.setCurrentPage(1);
            teacherParamsDTO.setPageSize(1);
            List<TeacherVO> studentVOList = teacherService.listTeacherByParams(teacherParamsDTO);

            return ResponseUtils.success("200", studentVOList.get(0));
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 新增
     * @param jsonObject
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object save(@RequestBody JSONObject jsonObject) {
        try {
            RequestBean<TeacherParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<TeacherParamsDTO>>(){});
            TeacherParamsDTO teacherParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(teacherParamsDTO, "jobNo", "name");
            teacherService.save(teacherParamsDTO);
            return ResponseUtils.success("201");
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 修改
     * @param id
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json;charset=utf-8")
    public Object modify(@PathVariable("id") Integer id, @RequestBody JSONObject jsonObject) {
        try {
            RequestBean<TeacherParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<TeacherParamsDTO>>(){});
            TeacherParamsDTO teacherParamsDTO = requestBean.getData();
            teacherService.modify(id, teacherParamsDTO);
            return ResponseUtils.success("201");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 删除教师相关的信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = "application/json;charset=utf-8")
    public Object delete(@PathVariable("id") Integer id) {
        try {
            teacherService.delete(id);
            return ResponseUtils.success("204");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    @RequestMapping(value = "taught/{id}", method = RequestMethod.GET, consumes = "application/json;charset=utf-8")
    public Object listTaughtByTeacherId(@PathVariable("id") Integer id) {
        try {
            List<TeacherTaughtVO> teacherTaughtVOList = teacherService.listTaughtByTeacherId(id);
            return ResponseUtils.success("200", teacherTaughtVOList);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 重置密码
     * @param id studentId
     * @return 201
     */
    @RequestMapping("/resetPassword/{id}")
    public Object resetPassword(@PathVariable("id") Integer id) {
        try {
            teacherService.resetPassword(id);
            return ResponseUtils.success("201");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }
}