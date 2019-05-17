package com.wxj.model.PO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Title: Achievement</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-12 18:18
 */
@Getter
@Setter
public class Achievement implements Serializable {
    //ss.exam_schedule_id, c.name, es.title, eq.type,ss.finish_flag, ss.score
    /**
     * 考试安排ID
     */
    private Integer examScheduleId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 考试安排title
     */
    private String examScheduleTitle;
    /**
     * 试题类型1:单选题 2:多选题 3:不定项选择 4:判断题 5:简答题 6:分录
     */
    private String questionsType;
    /**
     * 完成标志1:完成 0:未完成
     */
    private String finisFlag;
    /**
     * 分数
     */
    private Float score;
}