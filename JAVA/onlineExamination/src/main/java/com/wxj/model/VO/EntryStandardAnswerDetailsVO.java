package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Title: EntryStandardAnswerDetailsVO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-21 00:42
 */
@Getter
@Setter
public class EntryStandardAnswerDetailsVO implements Serializable {
    /**
     * id
     */
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
    private Float summaryScore;

    /**
     * 一级科目
     */
    private Integer subject1;

    /**
     * 一级科目分数
     */
    private Float subject1Score;

    /**
     * 二级科目
     */
    private String subject2;

    /**
     * 二级科目分数
     */
    private Float subject2Score;

    /**
     * 借方金额
     */
    private Integer debitAmount;

    /**
     * 借方金额分数
     */
    private Float debitAmountScore;

    /**
     * 贷方金额
     */
    private Integer creditAmount;

    /**
     * 贷方金额分数
     */
    private Float creditAmountScore;

    /**
     * 合计大写
     */
    private String total;

    /**
     * 合计大写分数
     */
    private Float totalScore;

    /**
     * 借方合计
     */
    private Integer debitTotal;

    /**
     * 借方合计分数
     */
    private Float debitTotalScore;

    /**
     * 贷方合计
     */
    private Integer creditTotal;

    /**
     * 贷方合计分数
     */
    private Float creditTotalScore;
}