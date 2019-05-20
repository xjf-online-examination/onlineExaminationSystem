package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: EntryStandardAnswerVO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-21 00:39
 */
@Getter
@Setter
public class EntryStandardAnswerVO implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 类型 1:记 2:收 3:付 4:转
     */
    private String type;
    /**
     * 号
     */
    private String number;
    /**
     * 详情答案
     */
    private List<EntryStandardAnswerDetailsVO> entryStandardAnswerDetailsVOList;
}