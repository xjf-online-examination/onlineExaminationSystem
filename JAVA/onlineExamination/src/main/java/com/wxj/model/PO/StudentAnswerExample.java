package com.wxj.model.PO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentAnswerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     *
     * @mbg.generated
     */
    public StudentAnswerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStudentSnoIsNull() {
            addCriterion("student_sno is null");
            return (Criteria) this;
        }

        public Criteria andStudentSnoIsNotNull() {
            addCriterion("student_sno is not null");
            return (Criteria) this;
        }

        public Criteria andStudentSnoEqualTo(String value) {
            addCriterion("student_sno =", value, "studentSno");
            return (Criteria) this;
        }

        public Criteria andStudentSnoNotEqualTo(String value) {
            addCriterion("student_sno <>", value, "studentSno");
            return (Criteria) this;
        }

        public Criteria andStudentSnoGreaterThan(String value) {
            addCriterion("student_sno >", value, "studentSno");
            return (Criteria) this;
        }

        public Criteria andStudentSnoGreaterThanOrEqualTo(String value) {
            addCriterion("student_sno >=", value, "studentSno");
            return (Criteria) this;
        }

        public Criteria andStudentSnoLessThan(String value) {
            addCriterion("student_sno <", value, "studentSno");
            return (Criteria) this;
        }

        public Criteria andStudentSnoLessThanOrEqualTo(String value) {
            addCriterion("student_sno <=", value, "studentSno");
            return (Criteria) this;
        }

        public Criteria andStudentSnoLike(String value) {
            addCriterion("student_sno like", value, "studentSno");
            return (Criteria) this;
        }

        public Criteria andStudentSnoNotLike(String value) {
            addCriterion("student_sno not like", value, "studentSno");
            return (Criteria) this;
        }

        public Criteria andStudentSnoIn(List<String> values) {
            addCriterion("student_sno in", values, "studentSno");
            return (Criteria) this;
        }

        public Criteria andStudentSnoNotIn(List<String> values) {
            addCriterion("student_sno not in", values, "studentSno");
            return (Criteria) this;
        }

        public Criteria andStudentSnoBetween(String value1, String value2) {
            addCriterion("student_sno between", value1, value2, "studentSno");
            return (Criteria) this;
        }

        public Criteria andStudentSnoNotBetween(String value1, String value2) {
            addCriterion("student_sno not between", value1, value2, "studentSno");
            return (Criteria) this;
        }

        public Criteria andExamScheduleIdIsNull() {
            addCriterion("exam_schedule_id is null");
            return (Criteria) this;
        }

        public Criteria andExamScheduleIdIsNotNull() {
            addCriterion("exam_schedule_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamScheduleIdEqualTo(Integer value) {
            addCriterion("exam_schedule_id =", value, "examScheduleId");
            return (Criteria) this;
        }

        public Criteria andExamScheduleIdNotEqualTo(Integer value) {
            addCriterion("exam_schedule_id <>", value, "examScheduleId");
            return (Criteria) this;
        }

        public Criteria andExamScheduleIdGreaterThan(Integer value) {
            addCriterion("exam_schedule_id >", value, "examScheduleId");
            return (Criteria) this;
        }

        public Criteria andExamScheduleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_schedule_id >=", value, "examScheduleId");
            return (Criteria) this;
        }

        public Criteria andExamScheduleIdLessThan(Integer value) {
            addCriterion("exam_schedule_id <", value, "examScheduleId");
            return (Criteria) this;
        }

        public Criteria andExamScheduleIdLessThanOrEqualTo(Integer value) {
            addCriterion("exam_schedule_id <=", value, "examScheduleId");
            return (Criteria) this;
        }

        public Criteria andExamScheduleIdIn(List<Integer> values) {
            addCriterion("exam_schedule_id in", values, "examScheduleId");
            return (Criteria) this;
        }

        public Criteria andExamScheduleIdNotIn(List<Integer> values) {
            addCriterion("exam_schedule_id not in", values, "examScheduleId");
            return (Criteria) this;
        }

        public Criteria andExamScheduleIdBetween(Integer value1, Integer value2) {
            addCriterion("exam_schedule_id between", value1, value2, "examScheduleId");
            return (Criteria) this;
        }

        public Criteria andExamScheduleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_schedule_id not between", value1, value2, "examScheduleId");
            return (Criteria) this;
        }

        public Criteria andQuestionsNoIsNull() {
            addCriterion("questions_no is null");
            return (Criteria) this;
        }

        public Criteria andQuestionsNoIsNotNull() {
            addCriterion("questions_no is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionsNoEqualTo(Byte value) {
            addCriterion("questions_no =", value, "questionsNo");
            return (Criteria) this;
        }

        public Criteria andQuestionsNoNotEqualTo(Byte value) {
            addCriterion("questions_no <>", value, "questionsNo");
            return (Criteria) this;
        }

        public Criteria andQuestionsNoGreaterThan(Byte value) {
            addCriterion("questions_no >", value, "questionsNo");
            return (Criteria) this;
        }

        public Criteria andQuestionsNoGreaterThanOrEqualTo(Byte value) {
            addCriterion("questions_no >=", value, "questionsNo");
            return (Criteria) this;
        }

        public Criteria andQuestionsNoLessThan(Byte value) {
            addCriterion("questions_no <", value, "questionsNo");
            return (Criteria) this;
        }

        public Criteria andQuestionsNoLessThanOrEqualTo(Byte value) {
            addCriterion("questions_no <=", value, "questionsNo");
            return (Criteria) this;
        }

        public Criteria andQuestionsNoIn(List<Byte> values) {
            addCriterion("questions_no in", values, "questionsNo");
            return (Criteria) this;
        }

        public Criteria andQuestionsNoNotIn(List<Byte> values) {
            addCriterion("questions_no not in", values, "questionsNo");
            return (Criteria) this;
        }

        public Criteria andQuestionsNoBetween(Byte value1, Byte value2) {
            addCriterion("questions_no between", value1, value2, "questionsNo");
            return (Criteria) this;
        }

        public Criteria andQuestionsNoNotBetween(Byte value1, Byte value2) {
            addCriterion("questions_no not between", value1, value2, "questionsNo");
            return (Criteria) this;
        }

        public Criteria andQuestionsTypeIsNull() {
            addCriterion("questions_type is null");
            return (Criteria) this;
        }

        public Criteria andQuestionsTypeIsNotNull() {
            addCriterion("questions_type is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionsTypeEqualTo(String value) {
            addCriterion("questions_type =", value, "questionsType");
            return (Criteria) this;
        }

        public Criteria andQuestionsTypeNotEqualTo(String value) {
            addCriterion("questions_type <>", value, "questionsType");
            return (Criteria) this;
        }

        public Criteria andQuestionsTypeGreaterThan(String value) {
            addCriterion("questions_type >", value, "questionsType");
            return (Criteria) this;
        }

        public Criteria andQuestionsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("questions_type >=", value, "questionsType");
            return (Criteria) this;
        }

        public Criteria andQuestionsTypeLessThan(String value) {
            addCriterion("questions_type <", value, "questionsType");
            return (Criteria) this;
        }

        public Criteria andQuestionsTypeLessThanOrEqualTo(String value) {
            addCriterion("questions_type <=", value, "questionsType");
            return (Criteria) this;
        }

        public Criteria andQuestionsTypeLike(String value) {
            addCriterion("questions_type like", value, "questionsType");
            return (Criteria) this;
        }

        public Criteria andQuestionsTypeNotLike(String value) {
            addCriterion("questions_type not like", value, "questionsType");
            return (Criteria) this;
        }

        public Criteria andQuestionsTypeIn(List<String> values) {
            addCriterion("questions_type in", values, "questionsType");
            return (Criteria) this;
        }

        public Criteria andQuestionsTypeNotIn(List<String> values) {
            addCriterion("questions_type not in", values, "questionsType");
            return (Criteria) this;
        }

        public Criteria andQuestionsTypeBetween(String value1, String value2) {
            addCriterion("questions_type between", value1, value2, "questionsType");
            return (Criteria) this;
        }

        public Criteria andQuestionsTypeNotBetween(String value1, String value2) {
            addCriterion("questions_type not between", value1, value2, "questionsType");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNull() {
            addCriterion("answer is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNotNull() {
            addCriterion("answer is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerEqualTo(String value) {
            addCriterion("answer =", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotEqualTo(String value) {
            addCriterion("answer <>", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThan(String value) {
            addCriterion("answer >", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("answer >=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThan(String value) {
            addCriterion("answer <", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThanOrEqualTo(String value) {
            addCriterion("answer <=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLike(String value) {
            addCriterion("answer like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotLike(String value) {
            addCriterion("answer not like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerIn(List<String> values) {
            addCriterion("answer in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotIn(List<String> values) {
            addCriterion("answer not in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerBetween(String value1, String value2) {
            addCriterion("answer between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotBetween(String value1, String value2) {
            addCriterion("answer not between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Float value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Float value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Float value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Float value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Float value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Float value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Float> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Float> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Float value1, Float value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Float value1, Float value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andFinishFlagIsNull() {
            addCriterion("finish_flag is null");
            return (Criteria) this;
        }

        public Criteria andFinishFlagIsNotNull() {
            addCriterion("finish_flag is not null");
            return (Criteria) this;
        }

        public Criteria andFinishFlagEqualTo(String value) {
            addCriterion("finish_flag =", value, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNotEqualTo(String value) {
            addCriterion("finish_flag <>", value, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagGreaterThan(String value) {
            addCriterion("finish_flag >", value, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagGreaterThanOrEqualTo(String value) {
            addCriterion("finish_flag >=", value, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagLessThan(String value) {
            addCriterion("finish_flag <", value, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagLessThanOrEqualTo(String value) {
            addCriterion("finish_flag <=", value, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagLike(String value) {
            addCriterion("finish_flag like", value, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNotLike(String value) {
            addCriterion("finish_flag not like", value, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagIn(List<String> values) {
            addCriterion("finish_flag in", values, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNotIn(List<String> values) {
            addCriterion("finish_flag not in", values, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagBetween(String value1, String value2) {
            addCriterion("finish_flag between", value1, value2, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNotBetween(String value1, String value2) {
            addCriterion("finish_flag not between", value1, value2, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNull() {
            addCriterion("finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNotNull() {
            addCriterion("finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeEqualTo(Date value) {
            addCriterion("finish_time =", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotEqualTo(Date value) {
            addCriterion("finish_time <>", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThan(Date value) {
            addCriterion("finish_time >", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("finish_time >=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThan(Date value) {
            addCriterion("finish_time <", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("finish_time <=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIn(List<Date> values) {
            addCriterion("finish_time in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotIn(List<Date> values) {
            addCriterion("finish_time not in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeBetween(Date value1, Date value2) {
            addCriterion("finish_time between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("finish_time not between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(Integer value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(Integer value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(Integer value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(Integer value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(Integer value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<Integer> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<Integer> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(Integer value1, Integer value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(Integer value1, Integer value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("del_flag like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("del_flag not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}