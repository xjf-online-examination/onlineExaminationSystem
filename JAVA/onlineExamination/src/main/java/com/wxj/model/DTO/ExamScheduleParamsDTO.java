package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ExamScheduleParamsDTO implements Serializable {
    /**
     * 试卷编号
     */
    private String examPaperCode;
    /**
     * 状态 1：未开始 2：进行中 3：已结束
     */
    private Integer status;
}
