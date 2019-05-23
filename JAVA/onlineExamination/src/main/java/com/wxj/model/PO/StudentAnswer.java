package com.wxj.model.PO;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: StudentAnswer</p>
 * <p>Description: 考生答案 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.hanshow.com</p>
 * @author wangxiaojun
 * @date 2019-05-24 01:21:05
 * @version 1.0
 */
public class StudentAnswer implements Serializable {
    /**
    * 分页查询条件
    */
    private String whereStr;

    /**
     * ID
     */
    private Integer id;

    /**
     * 考生学号
     */
    private String studentSno;

    /**
     * 考试安排ID
     */
    private Integer examScheduleId;

    /**
     * 题号
     */
    private Byte questionsNo;

    /**
     * 试题类型1:单选题 2:多选题 3:不定项选择 4:判断题 5:简答题 6:分录
     */
    private String questionsType;

    /**
     * 答案
     */
    private String answer;

    /**
     * 分数
     */
    private Float score;

    /**
     * 完成标志1:完成 0:未完成
     */
    private String finishFlag;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 批卷人ID
     */
    private Integer operator;

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

    public String getStudentSno() {
        return studentSno;
    }

    public void setStudentSno(String studentSno) {
        this.studentSno = studentSno == null ? null : studentSno.trim();
    }

    public Integer getExamScheduleId() {
        return examScheduleId;
    }

    public void setExamScheduleId(Integer examScheduleId) {
        this.examScheduleId = examScheduleId;
    }

    public Byte getQuestionsNo() {
        return questionsNo;
    }

    public void setQuestionsNo(Byte questionsNo) {
        this.questionsNo = questionsNo;
    }

    public String getQuestionsType() {
        return questionsType;
    }

    public void setQuestionsType(String questionsType) {
        this.questionsType = questionsType == null ? null : questionsType.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getFinishFlag() {
        return finishFlag;
    }

    public void setFinishFlag(String finishFlag) {
        this.finishFlag = finishFlag == null ? null : finishFlag.trim();
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
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
        sb.append(", studentSno=").append(studentSno);
        sb.append(", examScheduleId=").append(examScheduleId);
        sb.append(", questionsNo=").append(questionsNo);
        sb.append(", questionsType=").append(questionsType);
        sb.append(", answer=").append(answer);
        sb.append(", score=").append(score);
        sb.append(", finishFlag=").append(finishFlag);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", operator=").append(operator);
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