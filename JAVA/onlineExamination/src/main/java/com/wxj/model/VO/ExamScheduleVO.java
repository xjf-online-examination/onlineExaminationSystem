package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class ExamScheduleVO implements Serializable {
    /**
     * ID
     */
    private Integer id;
    /**
     * 考试名称
     */
    private String examScheduleName;
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
    private List<String> className;
    /**
     * 状态1:未开始 2：进行中 3：已结束
     */
    private String status;
}
