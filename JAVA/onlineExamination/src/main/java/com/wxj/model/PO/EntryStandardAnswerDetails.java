package com.wxj.model.PO;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: EntryStandardAnswerDetails</p>
 * <p>Description:分录试题标准答案 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.hanshow.com</p>
 * @author wangxiaojun
 * @date 2019-05-21 23:51:55
 * @version 1.0
 */
public class EntryStandardAnswerDetails implements Serializable {
    /**
    * 分页查询条件
    */
    private String whereStr;

    /**
     * ID
     */
    private Integer id;

    /**
     * 分录答案ID
     */
    private Integer entryAnswerId;

    /**
     * 行
     */
    private Byte row;

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
    private Byte subject1;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 删除标志
     */
    private String delFlag;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntryAnswerId() {
        return entryAnswerId;
    }

    public void setEntryAnswerId(Integer entryAnswerId) {
        this.entryAnswerId = entryAnswerId;
    }

    public Byte getRow() {
        return row;
    }

    public void setRow(Byte row) {
        this.row = row;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Float getSummaryScore() {
        return summaryScore;
    }

    public void setSummaryScore(Float summaryScore) {
        this.summaryScore = summaryScore;
    }

    public Byte getSubject1() {
        return subject1;
    }

    public void setSubject1(Byte subject1) {
        this.subject1 = subject1;
    }

    public Float getSubject1Score() {
        return subject1Score;
    }

    public void setSubject1Score(Float subject1Score) {
        this.subject1Score = subject1Score;
    }

    public String getSubject2() {
        return subject2;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2 == null ? null : subject2.trim();
    }

    public Float getSubject2Score() {
        return subject2Score;
    }

    public void setSubject2Score(Float subject2Score) {
        this.subject2Score = subject2Score;
    }

    public Integer getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Integer debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Float getDebitAmountScore() {
        return debitAmountScore;
    }

    public void setDebitAmountScore(Float debitAmountScore) {
        this.debitAmountScore = debitAmountScore;
    }

    public Integer getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Integer creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Float getCreditAmountScore() {
        return creditAmountScore;
    }

    public void setCreditAmountScore(Float creditAmountScore) {
        this.creditAmountScore = creditAmountScore;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total == null ? null : total.trim();
    }

    public Float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getDebitTotal() {
        return debitTotal;
    }

    public void setDebitTotal(Integer debitTotal) {
        this.debitTotal = debitTotal;
    }

    public Float getDebitTotalScore() {
        return debitTotalScore;
    }

    public void setDebitTotalScore(Float debitTotalScore) {
        this.debitTotalScore = debitTotalScore;
    }

    public Integer getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(Integer creditTotal) {
        this.creditTotal = creditTotal;
    }

    public Float getCreditTotalScore() {
        return creditTotalScore;
    }

    public void setCreditTotalScore(Float creditTotalScore) {
        this.creditTotalScore = creditTotalScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", entryAnswerId=").append(entryAnswerId);
        sb.append(", row=").append(row);
        sb.append(", summary=").append(summary);
        sb.append(", summaryScore=").append(summaryScore);
        sb.append(", subject1=").append(subject1);
        sb.append(", subject1Score=").append(subject1Score);
        sb.append(", subject2=").append(subject2);
        sb.append(", subject2Score=").append(subject2Score);
        sb.append(", debitAmount=").append(debitAmount);
        sb.append(", debitAmountScore=").append(debitAmountScore);
        sb.append(", creditAmount=").append(creditAmount);
        sb.append(", creditAmountScore=").append(creditAmountScore);
        sb.append(", total=").append(total);
        sb.append(", totalScore=").append(totalScore);
        sb.append(", debitTotal=").append(debitTotal);
        sb.append(", debitTotalScore=").append(debitTotalScore);
        sb.append(", creditTotal=").append(creditTotal);
        sb.append(", creditTotalScore=").append(creditTotalScore);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getWhereStr() {
        return whereStr;
    }

    public void setWhereStr(String whereStr) {
        this.whereStr = whereStr;
    }
}