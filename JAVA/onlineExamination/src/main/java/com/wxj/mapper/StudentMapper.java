package com.wxj.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.model.DTO.LoginDTO;
import com.wxj.model.DTO.StudentParamsDTO;
import com.wxj.model.PO.Achievement;
import com.wxj.model.PO.Student;
import com.wxj.model.PO.StudentExample;
import com.wxj.model.PO.UserInfo;
import com.wxj.model.VO.StudentExamScheduleVO;
import com.wxj.model.VO.StudentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(StudentExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(StudentExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(Student record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Student record);

    /**
     *
     * @mbg.generated
     */
    List<Student> selectByExample(StudentExample example);

    /**
     *
     * @mbg.generated
     */
    Student selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Student record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Student record);

    /**
     * 根据用户名密码查询用户
     * @param loginDTO
     * @return
     */
    List<UserInfo> selectUserByUserNameAndPassword(@Param("record")LoginDTO loginDTO);

    /**
     * 根据参数查询列表分页
     * @param record
     * @param pageBounds
     * @return
     */
    List<StudentVO> selectStudentVOByParams(@Param("record") StudentParamsDTO record, PageBounds pageBounds);

    /**
     * 根据参数查询列表count
     * @param record
     * @return
     */
    long countStudentVOByParams(@Param("record") StudentParamsDTO record);

    /**
     * 根据参数查询列表
     * @param map
     * @return
     */
    List<Student> selectStudentByParams(Map<String, Object> map);

    /**
     * 查询成绩
     * @param id
     * @return
     */
    List<Achievement> selectAchievementByStudentId(Integer id);

    /**
     * 批量插入
     * @param studentList
     * @return
     */
    int bathInsert(@Param("list") List<Student> studentList);


}