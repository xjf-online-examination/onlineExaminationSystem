package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AchievementDTO implements Serializable {
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 成绩
     */
    private float achievement;
}
