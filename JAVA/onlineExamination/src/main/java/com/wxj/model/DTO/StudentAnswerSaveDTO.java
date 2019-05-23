package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: StudentAnswerSaveDTO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-24 00:21
 */
@Setter
@Getter
public class StudentAnswerSaveDTO implements Serializable {
    private String sno;

    private Integer examScheduleId;

    private Integer questionsNo;

    private String questionsType;

    private String answer;

    private List<StudentEntryAnswerSaveDTO> studentEntryAnswerSaveDTOList;
}