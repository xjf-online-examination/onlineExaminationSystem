package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: EntryStandardAnswerDTO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-20 23:44
 */
@Getter
@Setter
public class EntryStandardAnswerDTO implements Serializable {
    /**
     * 类型 1:记 2:收 3:付 4:转
     */
    private String type;
    /**
     * 号
     */
    private String number;

    private List<EntryStandardAnswerDetailsDTO> entryStandardAnswerDetailsDTOList;
}