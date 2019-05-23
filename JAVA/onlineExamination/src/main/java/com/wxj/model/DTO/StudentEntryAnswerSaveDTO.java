package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Title: StudentEntryAnswerSaveDTO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-24 00:26
 */
@Getter
@Setter
public class StudentEntryAnswerSaveDTO implements Serializable {
    /**
     * 第几行
     */
    private Integer row;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 一级科目
     */
    private Integer subject1;
    /**
     * 二级科目
     */
    private String subject2;
    /**
     * 借方金额
     */
    private Integer debitAmount;
    /**
     * 贷方金额
     */
    private Integer creditAmount;
    /**
     * 合计
     */
    private String total;
    /**
     * 借方合计
     */
    private Integer debitTotal;
    /**
     * 贷方合计
     */
    private Integer creditTotal;
}