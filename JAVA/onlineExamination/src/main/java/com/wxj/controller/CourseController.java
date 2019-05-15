package com.wxj.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.CourseParamsDTO;
import com.wxj.model.VO.CourseVO;
import com.wxj.service.CourseServiceI;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.ValidateParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Title: CourseController</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-12 22:47
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseServiceI courseService;

    /**
     * 条件查询分页列表
     * @param jsonObject
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, consumes = "application/json;charset=utf-8")
    public Object listCourseByParams(@RequestBody JSONObject jsonObject) {
        try {
            RequestBean<CourseParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<CourseParamsDTO>>() {
            });
            CourseParamsDTO teacherParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(teacherParamsDTO, "currentPage", "pageSize");
            List<CourseVO> studentVOList = courseService.listCourseByParams(teacherParamsDTO);

            Long count = courseService.countCourseByParams(teacherParamsDTO);

            PageBean<CourseVO> pageBean = new PageBean(count, teacherParamsDTO.getCurrentPage(), teacherParamsDTO.getPageSize(), studentVOList);
            return ResponseUtils.success("200", pageBean);
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = "application/json;charset=utf-8")
    public Object getTeacherById(@PathVariable("id") Integer id) {
        try {
            CourseParamsDTO courseParamsDTO = new CourseParamsDTO();
            courseParamsDTO.setId(id);
            courseParamsDTO.setCurrentPage(1);
            courseParamsDTO.setPageSize(1);
            List<CourseVO> studentVOList = courseService.listCourseByParams(courseParamsDTO);

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
            RequestBean<CourseParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<CourseParamsDTO>>(){});
            CourseParamsDTO courseParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(courseParamsDTO, "code", "name", "classId");
            courseService.save(courseParamsDTO);
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
            RequestBean<CourseParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<CourseParamsDTO>>(){});
            CourseParamsDTO courseParamsDTO = requestBean.getData();
            courseService.modify(id, courseParamsDTO);
            return ResponseUtils.success("201");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 删除课程相关的信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = "application/json;charset=utf-8")
    public Object delete(@PathVariable("id") Integer id) {
        try {
            courseService.delete(id);
            return ResponseUtils.success("204");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

}