package com.wxj.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.model.Bean.PageBean;
import com.wxj.model.Bean.RequestBean;
import com.wxj.model.DTO.ClassParamsDTO;
import com.wxj.model.VO.ClassVO;
import com.wxj.service.ClassServiceI;
import com.wxj.utils.ResponseUtils;
import com.wxj.utils.ValidateParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Title: ClassController</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-12 22:55
 */
@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    ClassServiceI classService;

    /**
     * 条件查询分页列表
     * @param jsonObject
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, consumes = "application/json;charset=utf-8")
    public Object listClassByParams(@RequestBody JSONObject jsonObject) {
        try {
            RequestBean<ClassParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<ClassParamsDTO>>(){});
            ClassParamsDTO classParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(classParamsDTO,"currentPage", "pageSize");
            List<ClassVO> classVOList = classService.listClassByParams(classParamsDTO);

            Long count = classService.countClassByParams(classParamsDTO);

            PageBean<ClassVO> pageBean = new PageBean(count, classParamsDTO.getCurrentPage(), classParamsDTO.getPageSize(), classVOList);
            return ResponseUtils.success("200",pageBean);
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
    public Object getClassById(@PathVariable("id") Integer id) {
        ClassParamsDTO classParamsDTO = new ClassParamsDTO();
        classParamsDTO.setId(id);
        classParamsDTO.setCurrentPage(1);
        classParamsDTO.setPageSize(1);
        List<ClassVO> classVOList = classService.listClassByParams(classParamsDTO);

        return ResponseUtils.success("200", classVOList.get(0));

    }

    /**
     * 新增
     * @param jsonObject
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public Object save(@RequestBody JSONObject jsonObject) {
        try {
            RequestBean<ClassParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<ClassParamsDTO>>(){});
            ClassParamsDTO classParamsDTO = requestBean.getData();
            new ValidateParamsUtil().vaildParams(classParamsDTO,"code", "name");
            classService.save(classParamsDTO);
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
            RequestBean<ClassParamsDTO> requestBean = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<RequestBean<ClassParamsDTO>>(){});
            ClassParamsDTO classParamsDTO = requestBean.getData();
            classService.modify(id, classParamsDTO);
            return ResponseUtils.success("201");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

    /**
     *  删除这个班级相关学生的所有信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = "application/json;charset=utf-8")
    public Object delete(@PathVariable("id") Integer id) {
        try {
            classService.delete(id);
            return ResponseUtils.success("204");
        } catch (BusinessRuntimeException e) {
            return ResponseUtils.error(e);
        }
    }

}