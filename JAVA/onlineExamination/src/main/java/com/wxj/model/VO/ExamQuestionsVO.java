package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ExamQuestionsVO implements Serializable {
    private int id;

    private String code;

    private String title;

    private String courseName;

    private String type;
}
