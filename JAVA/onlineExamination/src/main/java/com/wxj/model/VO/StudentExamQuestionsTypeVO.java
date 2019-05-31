package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: StudentExamQuestionsTypeVO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-31 10:56
 */
@Getter
@Setter
public class StudentExamQuestionsTypeVO implements Serializable {
    private String type;

    List<StudentExamQuestionsVO> studentExamQuestionsVOList;
}