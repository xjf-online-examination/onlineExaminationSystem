package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ExamPaperDetailsVO implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 试卷编号
     */
    private String code;
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 题量
     */
    private Byte questionsAmount;
    /**
     * 所属课程
     */
    private String courseName;
    /**
     * 出卷人
     */
    private String teacherName;
    /**
     * 试题
     */
    private List<ExamQuestionsTypeVO> examQuestionsTypeVOList;
}
