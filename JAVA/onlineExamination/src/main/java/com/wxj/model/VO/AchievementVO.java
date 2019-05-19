package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AchievementVO implements Serializable {
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 试卷名称
     */
    private String examPaperName;
    /**
     * 考试时间
     */
    private String startTime;
    /**
     * 考试安排title
     */
    private String examScheduleTitle;
    /**
     * 成绩
     */
    private float achievement;
}
