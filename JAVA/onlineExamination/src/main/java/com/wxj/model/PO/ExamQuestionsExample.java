package com.wxj.model.PO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamQuestionsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     *
     * @mbg.generated
     */
    public ExamQuestionsExample() {
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

        public Criteria andCourseCodeIsNull() {
            addCriterion("course_code is null");
            return (Criteria) this;
        }

        public Criteria andCourseCodeIsNotNull() {
            addCriterion("course_code is not null");
            return (Criteria) this;
        }

        public Criteria andCourseCodeEqualTo(String value) {
            addCriterion("course_code =", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeNotEqualTo(String value) {
            addCriterion("course_code <>", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeGreaterThan(String value) {
            addCriterion("course_code >", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("course_code >=", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeLessThan(String value) {
            addCriterion("course_code <", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeLessThanOrEqualTo(String value) {
            addCriterion("course_code <=", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeLike(String value) {
            addCriterion("course_code like", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeNotLike(String value) {
            addCriterion("course_code not like", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeIn(List<String> values) {
            addCriterion("course_code in", values, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeNotIn(List<String> values) {
            addCriterion("course_code not in", values, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeBetween(String value1, String value2) {
            addCriterion("course_code between", value1, value2, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeNotBetween(String value1, String value2) {
            addCriterion("course_code not between", value1, value2, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andOptionaIsNull() {
            addCriterion("optionA is null");
            return (Criteria) this;
        }

        public Criteria andOptionaIsNotNull() {
            addCriterion("optionA is not null");
            return (Criteria) this;
        }

        public Criteria andOptionaEqualTo(String value) {
            addCriterion("optionA =", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaNotEqualTo(String value) {
            addCriterion("optionA <>", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaGreaterThan(String value) {
            addCriterion("optionA >", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaGreaterThanOrEqualTo(String value) {
            addCriterion("optionA >=", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaLessThan(String value) {
            addCriterion("optionA <", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaLessThanOrEqualTo(String value) {
            addCriterion("optionA <=", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaLike(String value) {
            addCriterion("optionA like", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaNotLike(String value) {
            addCriterion("optionA not like", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaIn(List<String> values) {
            addCriterion("optionA in", values, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaNotIn(List<String> values) {
            addCriterion("optionA not in", values, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaBetween(String value1, String value2) {
            addCriterion("optionA between", value1, value2, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaNotBetween(String value1, String value2) {
            addCriterion("optionA not between", value1, value2, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionbIsNull() {
            addCriterion("optionB is null");
            return (Criteria) this;
        }

        public Criteria andOptionbIsNotNull() {
            addCriterion("optionB is not null");
            return (Criteria) this;
        }

        public Criteria andOptionbEqualTo(String value) {
            addCriterion("optionB =", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbNotEqualTo(String value) {
            addCriterion("optionB <>", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbGreaterThan(String value) {
            addCriterion("optionB >", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbGreaterThanOrEqualTo(String value) {
            addCriterion("optionB >=", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbLessThan(String value) {
            addCriterion("optionB <", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbLessThanOrEqualTo(String value) {
            addCriterion("optionB <=", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbLike(String value) {
            addCriterion("optionB like", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbNotLike(String value) {
            addCriterion("optionB not like", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbIn(List<String> values) {
            addCriterion("optionB in", values, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbNotIn(List<String> values) {
            addCriterion("optionB not in", values, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbBetween(String value1, String value2) {
            addCriterion("optionB between", value1, value2, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbNotBetween(String value1, String value2) {
            addCriterion("optionB not between", value1, value2, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptioncIsNull() {
            addCriterion("optionC is null");
            return (Criteria) this;
        }

        public Criteria andOptioncIsNotNull() {
            addCriterion("optionC is not null");
            return (Criteria) this;
        }

        public Criteria andOptioncEqualTo(String value) {
            addCriterion("optionC =", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncNotEqualTo(String value) {
            addCriterion("optionC <>", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncGreaterThan(String value) {
            addCriterion("optionC >", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncGreaterThanOrEqualTo(String value) {
            addCriterion("optionC >=", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncLessThan(String value) {
            addCriterion("optionC <", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncLessThanOrEqualTo(String value) {
            addCriterion("optionC <=", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncLike(String value) {
            addCriterion("optionC like", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncNotLike(String value) {
            addCriterion("optionC not like", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncIn(List<String> values) {
            addCriterion("optionC in", values, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncNotIn(List<String> values) {
            addCriterion("optionC not in", values, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncBetween(String value1, String value2) {
            addCriterion("optionC between", value1, value2, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncNotBetween(String value1, String value2) {
            addCriterion("optionC not between", value1, value2, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptiondIsNull() {
            addCriterion("optionD is null");
            return (Criteria) this;
        }

        public Criteria andOptiondIsNotNull() {
            addCriterion("optionD is not null");
            return (Criteria) this;
        }

        public Criteria andOptiondEqualTo(String value) {
            addCriterion("optionD =", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondNotEqualTo(String value) {
            addCriterion("optionD <>", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondGreaterThan(String value) {
            addCriterion("optionD >", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondGreaterThanOrEqualTo(String value) {
            addCriterion("optionD >=", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondLessThan(String value) {
            addCriterion("optionD <", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondLessThanOrEqualTo(String value) {
            addCriterion("optionD <=", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondLike(String value) {
            addCriterion("optionD like", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondNotLike(String value) {
            addCriterion("optionD not like", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondIn(List<String> values) {
            addCriterion("optionD in", values, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondNotIn(List<String> values) {
            addCriterion("optionD not in", values, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondBetween(String value1, String value2) {
            addCriterion("optionD between", value1, value2, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondNotBetween(String value1, String value2) {
            addCriterion("optionD not between", value1, value2, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptioneIsNull() {
            addCriterion("optionE is null");
            return (Criteria) this;
        }

        public Criteria andOptioneIsNotNull() {
            addCriterion("optionE is not null");
            return (Criteria) this;
        }

        public Criteria andOptioneEqualTo(String value) {
            addCriterion("optionE =", value, "optione");
            return (Criteria) this;
        }

        public Criteria andOptioneNotEqualTo(String value) {
            addCriterion("optionE <>", value, "optione");
            return (Criteria) this;
        }

        public Criteria andOptioneGreaterThan(String value) {
            addCriterion("optionE >", value, "optione");
            return (Criteria) this;
        }

        public Criteria andOptioneGreaterThanOrEqualTo(String value) {
            addCriterion("optionE >=", value, "optione");
            return (Criteria) this;
        }

        public Criteria andOptioneLessThan(String value) {
            addCriterion("optionE <", value, "optione");
            return (Criteria) this;
        }

        public Criteria andOptioneLessThanOrEqualTo(String value) {
            addCriterion("optionE <=", value, "optione");
            return (Criteria) this;
        }

        public Criteria andOptioneLike(String value) {
            addCriterion("optionE like", value, "optione");
            return (Criteria) this;
        }

        public Criteria andOptioneNotLike(String value) {
            addCriterion("optionE not like", value, "optione");
            return (Criteria) this;
        }

        public Criteria andOptioneIn(List<String> values) {
            addCriterion("optionE in", values, "optione");
            return (Criteria) this;
        }

        public Criteria andOptioneNotIn(List<String> values) {
            addCriterion("optionE not in", values, "optione");
            return (Criteria) this;
        }

        public Criteria andOptioneBetween(String value1, String value2) {
            addCriterion("optionE between", value1, value2, "optione");
            return (Criteria) this;
        }

        public Criteria andOptioneNotBetween(String value1, String value2) {
            addCriterion("optionE not between", value1, value2, "optione");
            return (Criteria) this;
        }

        public Criteria andSingleAnswerIsNull() {
            addCriterion("single_answer is null");
            return (Criteria) this;
        }

        public Criteria andSingleAnswerIsNotNull() {
            addCriterion("single_answer is not null");
            return (Criteria) this;
        }

        public Criteria andSingleAnswerEqualTo(String value) {
            addCriterion("single_answer =", value, "singleAnswer");
            return (Criteria) this;
        }

        public Criteria andSingleAnswerNotEqualTo(String value) {
            addCriterion("single_answer <>", value, "singleAnswer");
            return (Criteria) this;
        }

        public Criteria andSingleAnswerGreaterThan(String value) {
            addCriterion("single_answer >", value, "singleAnswer");
            return (Criteria) this;
        }

        public Criteria andSingleAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("single_answer >=", value, "singleAnswer");
            return (Criteria) this;
        }

        public Criteria andSingleAnswerLessThan(String value) {
            addCriterion("single_answer <", value, "singleAnswer");
            return (Criteria) this;
        }

        public Criteria andSingleAnswerLessThanOrEqualTo(String value) {
            addCriterion("single_answer <=", value, "singleAnswer");
            return (Criteria) this;
        }

        public Criteria andSingleAnswerLike(String value) {
            addCriterion("single_answer like", value, "singleAnswer");
            return (Criteria) this;
        }

        public Criteria andSingleAnswerNotLike(String value) {
            addCriterion("single_answer not like", value, "singleAnswer");
            return (Criteria) this;
        }

        public Criteria andSingleAnswerIn(List<String> values) {
            addCriterion("single_answer in", values, "singleAnswer");
            return (Criteria) this;
        }

        public Criteria andSingleAnswerNotIn(List<String> values) {
            addCriterion("single_answer not in", values, "singleAnswer");
            return (Criteria) this;
        }

        public Criteria andSingleAnswerBetween(String value1, String value2) {
            addCriterion("single_answer between", value1, value2, "singleAnswer");
            return (Criteria) this;
        }

        public Criteria andSingleAnswerNotBetween(String value1, String value2) {
            addCriterion("single_answer not between", value1, value2, "singleAnswer");
            return (Criteria) this;
        }

        public Criteria andMultipleAnswerIsNull() {
            addCriterion("multiple_answer is null");
            return (Criteria) this;
        }

        public Criteria andMultipleAnswerIsNotNull() {
            addCriterion("multiple_answer is not null");
            return (Criteria) this;
        }

        public Criteria andMultipleAnswerEqualTo(String value) {
            addCriterion("multiple_answer =", value, "multipleAnswer");
            return (Criteria) this;
        }

        public Criteria andMultipleAnswerNotEqualTo(String value) {
            addCriterion("multiple_answer <>", value, "multipleAnswer");
            return (Criteria) this;
        }

        public Criteria andMultipleAnswerGreaterThan(String value) {
            addCriterion("multiple_answer >", value, "multipleAnswer");
            return (Criteria) this;
        }

        public Criteria andMultipleAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("multiple_answer >=", value, "multipleAnswer");
            return (Criteria) this;
        }

        public Criteria andMultipleAnswerLessThan(String value) {
            addCriterion("multiple_answer <", value, "multipleAnswer");
            return (Criteria) this;
        }

        public Criteria andMultipleAnswerLessThanOrEqualTo(String value) {
            addCriterion("multiple_answer <=", value, "multipleAnswer");
            return (Criteria) this;
        }

        public Criteria andMultipleAnswerLike(String value) {
            addCriterion("multiple_answer like", value, "multipleAnswer");
            return (Criteria) this;
        }

        public Criteria andMultipleAnswerNotLike(String value) {
            addCriterion("multiple_answer not like", value, "multipleAnswer");
            return (Criteria) this;
        }

        public Criteria andMultipleAnswerIn(List<String> values) {
            addCriterion("multiple_answer in", values, "multipleAnswer");
            return (Criteria) this;
        }

        public Criteria andMultipleAnswerNotIn(List<String> values) {
            addCriterion("multiple_answer not in", values, "multipleAnswer");
            return (Criteria) this;
        }

        public Criteria andMultipleAnswerBetween(String value1, String value2) {
            addCriterion("multiple_answer between", value1, value2, "multipleAnswer");
            return (Criteria) this;
        }

        public Criteria andMultipleAnswerNotBetween(String value1, String value2) {
            addCriterion("multiple_answer not between", value1, value2, "multipleAnswer");
            return (Criteria) this;
        }

        public Criteria andYesNoAnswerIsNull() {
            addCriterion("yes_no_answer is null");
            return (Criteria) this;
        }

        public Criteria andYesNoAnswerIsNotNull() {
            addCriterion("yes_no_answer is not null");
            return (Criteria) this;
        }

        public Criteria andYesNoAnswerEqualTo(String value) {
            addCriterion("yes_no_answer =", value, "yesNoAnswer");
            return (Criteria) this;
        }

        public Criteria andYesNoAnswerNotEqualTo(String value) {
            addCriterion("yes_no_answer <>", value, "yesNoAnswer");
            return (Criteria) this;
        }

        public Criteria andYesNoAnswerGreaterThan(String value) {
            addCriterion("yes_no_answer >", value, "yesNoAnswer");
            return (Criteria) this;
        }

        public Criteria andYesNoAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("yes_no_answer >=", value, "yesNoAnswer");
            return (Criteria) this;
        }

        public Criteria andYesNoAnswerLessThan(String value) {
            addCriterion("yes_no_answer <", value, "yesNoAnswer");
            return (Criteria) this;
        }

        public Criteria andYesNoAnswerLessThanOrEqualTo(String value) {
            addCriterion("yes_no_answer <=", value, "yesNoAnswer");
            return (Criteria) this;
        }

        public Criteria andYesNoAnswerLike(String value) {
            addCriterion("yes_no_answer like", value, "yesNoAnswer");
            return (Criteria) this;
        }

        public Criteria andYesNoAnswerNotLike(String value) {
            addCriterion("yes_no_answer not like", value, "yesNoAnswer");
            return (Criteria) this;
        }

        public Criteria andYesNoAnswerIn(List<String> values) {
            addCriterion("yes_no_answer in", values, "yesNoAnswer");
            return (Criteria) this;
        }

        public Criteria andYesNoAnswerNotIn(List<String> values) {
            addCriterion("yes_no_answer not in", values, "yesNoAnswer");
            return (Criteria) this;
        }

        public Criteria andYesNoAnswerBetween(String value1, String value2) {
            addCriterion("yes_no_answer between", value1, value2, "yesNoAnswer");
            return (Criteria) this;
        }

        public Criteria andYesNoAnswerNotBetween(String value1, String value2) {
            addCriterion("yes_no_answer not between", value1, value2, "yesNoAnswer");
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