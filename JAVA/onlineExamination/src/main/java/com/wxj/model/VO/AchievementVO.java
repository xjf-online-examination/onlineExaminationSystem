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
     * 考试安排title
     */
    private String examScheduleTitile;
    /**
     * 成绩
     */
    private float achievement;
}
