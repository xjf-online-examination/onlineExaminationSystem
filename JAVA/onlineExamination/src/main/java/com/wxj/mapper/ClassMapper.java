package com.wxj.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.model.PO.Class;
import com.wxj.model.PO.ClassExample;
import com.wxj.model.VO.ClassSelectVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(ClassExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(ClassExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(Class record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Class record);

    /**
     *
     * @mbg.generated
     */
    List<Class> selectByExample(ClassExample example);

    /**
     *
     * @mbg.generated
     */
    Class selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Class record, @Param("example") ClassExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Class record, @Param("example") ClassExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Class record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Class record);

    /**
     * 条件查询分页班级
     * @param record
     * @param pageBounds
     * @return
     */
    List<Class> selectClassByParams(@Param("record")Class record, PageBounds pageBounds);

    /**
     * 条件查询统计
     * @param record
     * @return
     */
    Long countClassByParams(@Param("record")Class record);

    /**
     * 获取所有班级ID，name供下拉菜单用
     * @return
     */
    List<ClassSelectVO> listClassAll();
}