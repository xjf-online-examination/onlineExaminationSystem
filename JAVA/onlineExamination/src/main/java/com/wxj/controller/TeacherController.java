package com.wxj.controller;

import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.exception.ParamEmptyException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.TaughtSaveDTO;
import com.wxj.model.DTO.TeacherParamsDTO;
import com.wxj.model.VO.TeacherTaughtVO;
import com.wxj.model.VO.TeacherVO;
import com.wxj.service.TeacherServiceI;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.ValidateParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>Title: TeacherController</p >
 * <p>Description: 教师管理</p >
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
    Logger logger = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    TeacherServiceI teacherService;

    /**
     * 条件查询分页列表
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object listTeacherByParams(HttpServletRequest request, @RequestBody RequestBean<TeacherParamsDTO> requestBean) {
        try {
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
     * @param requestBean studentID
     * @return StudentVO
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object getTeacherById(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            TeacherParamsDTO teacherParamsDTO = new TeacherParamsDTO();
            teacherParamsDTO.setId(requestBean.getData());
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
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object save(HttpServletRequest request, @RequestBody RequestBean<TeacherParamsDTO> requestBean) {
        try {
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
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object modify(HttpServletRequest request, @RequestBody RequestBean<TeacherParamsDTO> requestBean) {
        try {
            TeacherParamsDTO teacherParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(teacherParamsDTO, "id");
            teacherService.modify(teacherParamsDTO);
            return ResponseUtils.success("201");
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        }  catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 删除教师相关的信息
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object delete(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            teacherService.delete(requestBean.getData());
            return ResponseUtils.success("204");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 所授课程
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "taught", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object listTaughtByTeacherId(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            List<TeacherTaughtVO> teacherTaughtVOList = teacherService.listTaughtByTeacherId(requestBean.getData());
            return ResponseUtils.success("200", teacherTaughtVOList);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 添加授课
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "saveTaught", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object saveTaught(HttpServletRequest request, @RequestBody RequestBean<TaughtSaveDTO> requestBean) {
        try {
            TaughtSaveDTO taughtSaveDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(taughtSaveDTO, "teacherId");
            if (taughtSaveDTO.getClassCourseIdList() == null || taughtSaveDTO.getClassCourseIdList().size() == 0) {
                throw new ParamEmptyException("classCourseIdList");
            }
            teacherService.saveTaught(taughtSaveDTO);
            return ResponseUtils.success("201");
        } catch (BusinessException e) {
            return ResponseUtils.error(e);
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     * 删除教师相关的信息
     * @param requestBean
     * @return
     */
    @RequestMapping(value = "/deleteTaught", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object deleteTaught(HttpServletRequest request, @RequestBody RequestBean<Integer> requestBean) {
        try {
            if (null == requestBean.getData()) {
                throw new ParamEmptyException("data不能为空");
            }
            teacherService.deleteTaught(requestBean.getData());
            return ResponseUtils.success("204");
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
            teacherService.resetPassword(requestBean.getData());
            return ResponseUtils.success("201");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }
}