package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Title: StudentExamScheduleVO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-19 16:20
 */
@Getter
@Setter
public class StudentExamScheduleVO implements Serializable {
    /**
     * 考试安排ID
     */
    private Integer examScheduleId;
    /**
     * 开始安排title
     */
    private String examScheduleTitle;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 时长
     */
    private String duration;
    /**
     * 试卷ID
     */
    private Integer examPaperId;
    /**
     * 试卷名称
     */
    private String examPaperName;
}