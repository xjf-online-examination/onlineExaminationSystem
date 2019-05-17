package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ExamScheduleVO implements Serializable {
    /**
     * ID
     */
    private String id;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 时长
     */
    private String duration;
    /**
     * 试卷编号
     */
    private String examPaperCode;
    /**
     * 试卷名称
     */
    private String examPaperName;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 状态
     */
    private String status;
}
