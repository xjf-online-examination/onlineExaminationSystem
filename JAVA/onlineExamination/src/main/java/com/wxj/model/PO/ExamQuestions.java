package com.wxj.model.PO;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: ExamQuestions</p>
 * <p>Description:试题 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.hanshow.com</p>
 * @author wangxiaojun
 * @date 2019-05-17 23:30:52
 * @version 1.0
 */
public class ExamQuestions implements Serializable {
    /**
    * 分页查询条件
    */
    private String whereStr;

    /**
     * ID
     */
    private Integer id;

    /**
     * 课程编号
     */
    private String courseCode;

    /**
     * 试题编号
     */
    private String code;

    /**
     * 试题类型1:单选题 2:多选题 3:不定项选择 4:判断题 5:简答题 6:分录
     */
    private String type;

    /**
     * 试题题目
     */
    private String title;

    /**
     * 选项A
     */
    private String optiona;

    /**
     * 选项B
     */
    private String optionb;

    /**
     * 选项C
     */
    private String optionc;

    /**
     * 选项D
     */
    private String optiond;

    /**
     * 选项E
     */
    private String optione;

    /**
     * 单选答案
     */
    private String singleAnswer;

    /**
     * 多选答案
     */
    private String multipleAnswer;

    /**
     * 判断题答案
     */
    private String yesNoAnswer;

    /**
     * 分数
     */
    private Integer score;

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

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode == null ? null : courseCode.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getOptiona() {
        return optiona;
    }

    public void setOptiona(String optiona) {
        this.optiona = optiona == null ? null : optiona.trim();
    }

    public String getOptionb() {
        return optionb;
    }

    public void setOptionb(String optionb) {
        this.optionb = optionb == null ? null : optionb.trim();
    }

    public String getOptionc() {
        return optionc;
    }

    public void setOptionc(String optionc) {
        this.optionc = optionc == null ? null : optionc.trim();
    }

    public String getOptiond() {
        return optiond;
    }

    public void setOptiond(String optiond) {
        this.optiond = optiond == null ? null : optiond.trim();
    }

    public String getOptione() {
        return optione;
    }

    public void setOptione(String optione) {
        this.optione = optione == null ? null : optione.trim();
    }

    public String getSingleAnswer() {
        return singleAnswer;
    }

    public void setSingleAnswer(String singleAnswer) {
        this.singleAnswer = singleAnswer == null ? null : singleAnswer.trim();
    }

    public String getMultipleAnswer() {
        return multipleAnswer;
    }

    public void setMultipleAnswer(String multipleAnswer) {
        this.multipleAnswer = multipleAnswer == null ? null : multipleAnswer.trim();
    }

    public String getYesNoAnswer() {
        return yesNoAnswer;
    }

    public void setYesNoAnswer(String yesNoAnswer) {
        this.yesNoAnswer = yesNoAnswer == null ? null : yesNoAnswer.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
        sb.append(", courseCode=").append(courseCode);
        sb.append(", code=").append(code);
        sb.append(", type=").append(type);
        sb.append(", title=").append(title);
        sb.append(", optiona=").append(optiona);
        sb.append(", optionb=").append(optionb);
        sb.append(", optionc=").append(optionc);
        sb.append(", optiond=").append(optiond);
        sb.append(", optione=").append(optione);
        sb.append(", singleAnswer=").append(singleAnswer);
        sb.append(", multipleAnswer=").append(multipleAnswer);
        sb.append(", yesNoAnswer=").append(yesNoAnswer);
        sb.append(", score=").append(score);
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