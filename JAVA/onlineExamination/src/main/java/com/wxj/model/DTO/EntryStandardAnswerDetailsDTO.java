package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Title: EntryStandardAnswerDetailsDTO</p >
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
public class EntryStandardAnswerDetailsDTO implements Serializable {
    private Integer id;
    /**
     * 分录答案ID
     */
    private Integer entryAnswerId;

    /**
     * 行
     */
    private Integer row;

    /**
     * 摘要key
     */
    private String summary;

    /**
     * 摘要分数
     */
    private Integer summaryScore;

    /**
     * 一级科目
     */
    private Integer subject1;

    /**
     * 一级科目分数
     */
    private Integer subject1Score;

    /**
     * 二级科目
     */
    private String subject2;

    /**
     * 二级科目分数
     */
    private Integer subject2Score;

    /**
     * 借方金额
     */
    private Integer debitAmount;

    /**
     * 借方金额分数
     */
    private Integer debitAmountScore;

    /**
     * 贷方金额
     */
    private Integer creditAmount;

    /**
     * 贷方金额分数
     */
    private Integer creditAmountScore;

    /**
     * 合计大写
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