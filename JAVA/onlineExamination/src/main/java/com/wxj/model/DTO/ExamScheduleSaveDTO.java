package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ExamScheduleSaveDTO implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * title
     */
    private String title;
    /**
     * 试卷编号
     */
    private String examPaperCode;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 时长
     */
    private Integer duration;
}
