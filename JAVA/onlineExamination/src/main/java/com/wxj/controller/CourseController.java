package com.wxj.controller;

import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.exception.ParamEmptyException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.CourseParamsDTO;
import com.wxj.model.DTO.CourseSaveDTO;
import com.wxj.model.VO.CourseVO;
import com.wxj.service.CourseServiceI;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.ValidateParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>Title: CourseController</p >
 * <p>Description: 课程管理</p >
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
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object listCourseByParams(HttpServletRequest request, @RequestBody RequestBean<CourseParamsDTO> requestBean) {
        try {
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
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object getTeacherById(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            CourseParamsDTO courseParamsDTO = new CourseParamsDTO();
            courseParamsDTO.setId(requestBean.getData());
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
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object save(HttpServletRequest request, @RequestBody RequestBean<CourseSaveDTO> requestBean) {
        try {
            CourseSaveDTO courseSaveDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(courseSaveDTO, "code", "name", "classIdList");
            courseService.save(courseSaveDTO);
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
    public Object modify(HttpServletRequest request, @RequestBody RequestBean<CourseSaveDTO> requestBean) {
        try {
            CourseSaveDTO courseSaveDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(courseSaveDTO, "id");
            courseService.modify(courseSaveDTO);
            return ResponseUtils.success("201");
        }  catch (BusinessException e) {
            return ResponseUtils.error(e);
        }  catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 删除课程相关的信息
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object delete(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            courseService.delete(requestBean.getData());
            return ResponseUtils.success("204");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 所有课程ID，className, courseName供教师模块下拉菜单用
     * @param request
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/listClassCourse", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object listClassCourseALL(HttpServletRequest request, @RequestBody RequestBean requestBean) {
        return ResponseUtils.success("200", courseService.listClassCourseAll());
    }

    /**
     * 所有课程ID，className, courseName供教师模块下拉菜单用
     * @param request
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/listCourse", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object listCourseALL(HttpServletRequest request, @RequestBody RequestBean requestBean) {
        return ResponseUtils.success("200", courseService.listCourseAll());
    }

}