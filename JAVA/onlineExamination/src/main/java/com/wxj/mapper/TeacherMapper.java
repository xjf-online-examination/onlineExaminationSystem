package com.wxj.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.model.DTO.LoginDTO;
import com.wxj.model.DTO.TeacherParamsDTO;
import com.wxj.model.PO.Teacher;
import com.wxj.model.PO.TeacherExample;
import com.wxj.model.PO.UserInfo;
import com.wxj.model.VO.TeacherTaughtVO;
import com.wxj.model.VO.TeacherVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(TeacherExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(TeacherExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(Teacher record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Teacher record);

    /**
     *
     * @mbg.generated
     */
    List<Teacher> selectByExample(TeacherExample example);

    /**
     *
     * @mbg.generated
     */
    Teacher selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Teacher record, @Param("example") TeacherExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Teacher record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Teacher record);

    /**
     * 根据用户名密码查询用户
     * @param loginDTO
     * @return
     */
    List<UserInfo> selectUserByUserNameAndPassword(@Param("record") LoginDTO loginDTO);

    /**
     * 条件查询分页列表
     * @param teacherParamsDTO
     * @param pageBounds
     * @return
     */
    List<TeacherVO> selectTeacherByParams(@Param("record")TeacherParamsDTO teacherParamsDTO, PageBounds pageBounds);

    /**
     * 统计条件查询
     * @param teacherParamsDTO
     * @return
     */
    Long countTeacherByParams(@Param("record")TeacherParamsDTO teacherParamsDTO);

    List<TeacherTaughtVO> selectTaughtByTeacherId(Integer id);
}