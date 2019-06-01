package com.wxj.model.PO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntryStandardAnswerDetailsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     *
     * @mbg.generated
     */
    public EntryStandardAnswerDetailsExample() {
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

        public Criteria andEntryAnswerIdIsNull() {
            addCriterion("entry_answer_id is null");
            return (Criteria) this;
        }

        public Criteria andEntryAnswerIdIsNotNull() {
            addCriterion("entry_answer_id is not null");
            return (Criteria) this;
        }

        public Criteria andEntryAnswerIdEqualTo(Integer value) {
            addCriterion("entry_answer_id =", value, "entryAnswerId");
            return (Criteria) this;
        }

        public Criteria andEntryAnswerIdNotEqualTo(Integer value) {
            addCriterion("entry_answer_id <>", value, "entryAnswerId");
            return (Criteria) this;
        }

        public Criteria andEntryAnswerIdGreaterThan(Integer value) {
            addCriterion("entry_answer_id >", value, "entryAnswerId");
            return (Criteria) this;
        }

        public Criteria andEntryAnswerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("entry_answer_id >=", value, "entryAnswerId");
            return (Criteria) this;
        }

        public Criteria andEntryAnswerIdLessThan(Integer value) {
            addCriterion("entry_answer_id <", value, "entryAnswerId");
            return (Criteria) this;
        }

        public Criteria andEntryAnswerIdLessThanOrEqualTo(Integer value) {
            addCriterion("entry_answer_id <=", value, "entryAnswerId");
            return (Criteria) this;
        }

        public Criteria andEntryAnswerIdIn(List<Integer> values) {
            addCriterion("entry_answer_id in", values, "entryAnswerId");
            return (Criteria) this;
        }

        public Criteria andEntryAnswerIdNotIn(List<Integer> values) {
            addCriterion("entry_answer_id not in", values, "entryAnswerId");
            return (Criteria) this;
        }

        public Criteria andEntryAnswerIdBetween(Integer value1, Integer value2) {
            addCriterion("entry_answer_id between", value1, value2, "entryAnswerId");
            return (Criteria) this;
        }

        public Criteria andEntryAnswerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("entry_answer_id not between", value1, value2, "entryAnswerId");
            return (Criteria) this;
        }

        public Criteria andRowIsNull() {
            addCriterion("row is null");
            return (Criteria) this;
        }

        public Criteria andRowIsNotNull() {
            addCriterion("row is not null");
            return (Criteria) this;
        }

        public Criteria andRowEqualTo(Byte value) {
            addCriterion("row =", value, "row");
            return (Criteria) this;
        }

        public Criteria andRowNotEqualTo(Byte value) {
            addCriterion("row <>", value, "row");
            return (Criteria) this;
        }

        public Criteria andRowGreaterThan(Byte value) {
            addCriterion("row >", value, "row");
            return (Criteria) this;
        }

        public Criteria andRowGreaterThanOrEqualTo(Byte value) {
            addCriterion("row >=", value, "row");
            return (Criteria) this;
        }

        public Criteria andRowLessThan(Byte value) {
            addCriterion("row <", value, "row");
            return (Criteria) this;
        }

        public Criteria andRowLessThanOrEqualTo(Byte value) {
            addCriterion("row <=", value, "row");
            return (Criteria) this;
        }

        public Criteria andRowIn(List<Byte> values) {
            addCriterion("row in", values, "row");
            return (Criteria) this;
        }

        public Criteria andRowNotIn(List<Byte> values) {
            addCriterion("row not in", values, "row");
            return (Criteria) this;
        }

        public Criteria andRowBetween(Byte value1, Byte value2) {
            addCriterion("row between", value1, value2, "row");
            return (Criteria) this;
        }

        public Criteria andRowNotBetween(Byte value1, Byte value2) {
            addCriterion("row not between", value1, value2, "row");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNull() {
            addCriterion("summary is null");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNotNull() {
            addCriterion("summary is not null");
            return (Criteria) this;
        }

        public Criteria andSummaryEqualTo(String value) {
            addCriterion("summary =", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotEqualTo(String value) {
            addCriterion("summary <>", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThan(String value) {
            addCriterion("summary >", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("summary >=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThan(String value) {
            addCriterion("summary <", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThanOrEqualTo(String value) {
            addCriterion("summary <=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLike(String value) {
            addCriterion("summary like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotLike(String value) {
            addCriterion("summary not like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryIn(List<String> values) {
            addCriterion("summary in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotIn(List<String> values) {
            addCriterion("summary not in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryBetween(String value1, String value2) {
            addCriterion("summary between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotBetween(String value1, String value2) {
            addCriterion("summary not between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryScoreIsNull() {
            addCriterion("summary_score is null");
            return (Criteria) this;
        }

        public Criteria andSummaryScoreIsNotNull() {
            addCriterion("summary_score is not null");
            return (Criteria) this;
        }

        public Criteria andSummaryScoreEqualTo(BigDecimal value) {
            addCriterion("summary_score =", value, "summaryScore");
            return (Criteria) this;
        }

        public Criteria andSummaryScoreNotEqualTo(BigDecimal value) {
            addCriterion("summary_score <>", value, "summaryScore");
            return (Criteria) this;
        }

        public Criteria andSummaryScoreGreaterThan(BigDecimal value) {
            addCriterion("summary_score >", value, "summaryScore");
            return (Criteria) this;
        }

        public Criteria andSummaryScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("summary_score >=", value, "summaryScore");
            return (Criteria) this;
        }

        public Criteria andSummaryScoreLessThan(BigDecimal value) {
            addCriterion("summary_score <", value, "summaryScore");
            return (Criteria) this;
        }

        public Criteria andSummaryScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("summary_score <=", value, "summaryScore");
            return (Criteria) this;
        }

        public Criteria andSummaryScoreIn(List<BigDecimal> values) {
            addCriterion("summary_score in", values, "summaryScore");
            return (Criteria) this;
        }

        public Criteria andSummaryScoreNotIn(List<BigDecimal> values) {
            addCriterion("summary_score not in", values, "summaryScore");
            return (Criteria) this;
        }

        public Criteria andSummaryScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("summary_score between", value1, value2, "summaryScore");
            return (Criteria) this;
        }

        public Criteria andSummaryScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("summary_score not between", value1, value2, "summaryScore");
            return (Criteria) this;
        }

        public Criteria andSubject1IsNull() {
            addCriterion("subject1 is null");
            return (Criteria) this;
        }

        public Criteria andSubject1IsNotNull() {
            addCriterion("subject1 is not null");
            return (Criteria) this;
        }

        public Criteria andSubject1EqualTo(String value) {
            addCriterion("subject1 =", value, "subject1");
            return (Criteria) this;
        }

        public Criteria andSubject1NotEqualTo(String value) {
            addCriterion("subject1 <>", value, "subject1");
            return (Criteria) this;
        }

        public Criteria andSubject1GreaterThan(String value) {
            addCriterion("subject1 >", value, "subject1");
            return (Criteria) this;
        }

        public Criteria andSubject1GreaterThanOrEqualTo(String value) {
            addCriterion("subject1 >=", value, "subject1");
            return (Criteria) this;
        }

        public Criteria andSubject1LessThan(String value) {
            addCriterion("subject1 <", value, "subject1");
            return (Criteria) this;
        }

        public Criteria andSubject1LessThanOrEqualTo(String value) {
            addCriterion("subject1 <=", value, "subject1");
            return (Criteria) this;
        }

        public Criteria andSubject1Like(String value) {
            addCriterion("subject1 like", value, "subject1");
            return (Criteria) this;
        }

        public Criteria andSubject1NotLike(String value) {
            addCriterion("subject1 not like", value, "subject1");
            return (Criteria) this;
        }

        public Criteria andSubject1In(List<String> values) {
            addCriterion("subject1 in", values, "subject1");
            return (Criteria) this;
        }

        public Criteria andSubject1NotIn(List<String> values) {
            addCriterion("subject1 not in", values, "subject1");
            return (Criteria) this;
        }

        public Criteria andSubject1Between(String value1, String value2) {
            addCriterion("subject1 between", value1, value2, "subject1");
            return (Criteria) this;
        }

        public Criteria andSubject1NotBetween(String value1, String value2) {
            addCriterion("subject1 not between", value1, value2, "subject1");
            return (Criteria) this;
        }

        public Criteria andSubject1ScoreIsNull() {
            addCriterion("subject1_score is null");
            return (Criteria) this;
        }

        public Criteria andSubject1ScoreIsNotNull() {
            addCriterion("subject1_score is not null");
            return (Criteria) this;
        }

        public Criteria andSubject1ScoreEqualTo(BigDecimal value) {
            addCriterion("subject1_score =", value, "subject1Score");
            return (Criteria) this;
        }

        public Criteria andSubject1ScoreNotEqualTo(BigDecimal value) {
            addCriterion("subject1_score <>", value, "subject1Score");
            return (Criteria) this;
        }

        public Criteria andSubject1ScoreGreaterThan(BigDecimal value) {
            addCriterion("subject1_score >", value, "subject1Score");
            return (Criteria) this;
        }

        public Criteria andSubject1ScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("subject1_score >=", value, "subject1Score");
            return (Criteria) this;
        }

        public Criteria andSubject1ScoreLessThan(BigDecimal value) {
            addCriterion("subject1_score <", value, "subject1Score");
            return (Criteria) this;
        }

        public Criteria andSubject1ScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("subject1_score <=", value, "subject1Score");
            return (Criteria) this;
        }

        public Criteria andSubject1ScoreIn(List<BigDecimal> values) {
            addCriterion("subject1_score in", values, "subject1Score");
            return (Criteria) this;
        }

        public Criteria andSubject1ScoreNotIn(List<BigDecimal> values) {
            addCriterion("subject1_score not in", values, "subject1Score");
            return (Criteria) this;
        }

        public Criteria andSubject1ScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("subject1_score between", value1, value2, "subject1Score");
            return (Criteria) this;
        }

        public Criteria andSubject1ScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("subject1_score not between", value1, value2, "subject1Score");
            return (Criteria) this;
        }

        public Criteria andSubject2IsNull() {
            addCriterion("subject2 is null");
            return (Criteria) this;
        }

        public Criteria andSubject2IsNotNull() {
            addCriterion("subject2 is not null");
            return (Criteria) this;
        }

        public Criteria andSubject2EqualTo(String value) {
            addCriterion("subject2 =", value, "subject2");
            return (Criteria) this;
        }

        public Criteria andSubject2NotEqualTo(String value) {
            addCriterion("subject2 <>", value, "subject2");
            return (Criteria) this;
        }

        public Criteria andSubject2GreaterThan(String value) {
            addCriterion("subject2 >", value, "subject2");
            return (Criteria) this;
        }

        public Criteria andSubject2GreaterThanOrEqualTo(String value) {
            addCriterion("subject2 >=", value, "subject2");
            return (Criteria) this;
        }

        public Criteria andSubject2LessThan(String value) {
            addCriterion("subject2 <", value, "subject2");
            return (Criteria) this;
        }

        public Criteria andSubject2LessThanOrEqualTo(String value) {
            addCriterion("subject2 <=", value, "subject2");
            return (Criteria) this;
        }

        public Criteria andSubject2Like(String value) {
            addCriterion("subject2 like", value, "subject2");
            return (Criteria) this;
        }

        public Criteria andSubject2NotLike(String value) {
            addCriterion("subject2 not like", value, "subject2");
            return (Criteria) this;
        }

        public Criteria andSubject2In(List<String> values) {
            addCriterion("subject2 in", values, "subject2");
            return (Criteria) this;
        }

        public Criteria andSubject2NotIn(List<String> values) {
            addCriterion("subject2 not in", values, "subject2");
            return (Criteria) this;
        }

        public Criteria andSubject2Between(String value1, String value2) {
            addCriterion("subject2 between", value1, value2, "subject2");
            return (Criteria) this;
        }

        public Criteria andSubject2NotBetween(String value1, String value2) {
            addCriterion("subject2 not between", value1, value2, "subject2");
            return (Criteria) this;
        }

        public Criteria andSubject2ScoreIsNull() {
            addCriterion("subject2_score is null");
            return (Criteria) this;
        }

        public Criteria andSubject2ScoreIsNotNull() {
            addCriterion("subject2_score is not null");
            return (Criteria) this;
        }

        public Criteria andSubject2ScoreEqualTo(BigDecimal value) {
            addCriterion("subject2_score =", value, "subject2Score");
            return (Criteria) this;
        }

        public Criteria andSubject2ScoreNotEqualTo(BigDecimal value) {
            addCriterion("subject2_score <>", value, "subject2Score");
            return (Criteria) this;
        }

        public Criteria andSubject2ScoreGreaterThan(BigDecimal value) {
            addCriterion("subject2_score >", value, "subject2Score");
            return (Criteria) this;
        }

        public Criteria andSubject2ScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("subject2_score >=", value, "subject2Score");
            return (Criteria) this;
        }

        public Criteria andSubject2ScoreLessThan(BigDecimal value) {
            addCriterion("subject2_score <", value, "subject2Score");
            return (Criteria) this;
        }

        public Criteria andSubject2ScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("subject2_score <=", value, "subject2Score");
            return (Criteria) this;
        }

        public Criteria andSubject2ScoreIn(List<BigDecimal> values) {
            addCriterion("subject2_score in", values, "subject2Score");
            return (Criteria) this;
        }

        public Criteria andSubject2ScoreNotIn(List<BigDecimal> values) {
            addCriterion("subject2_score not in", values, "subject2Score");
            return (Criteria) this;
        }

        public Criteria andSubject2ScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("subject2_score between", value1, value2, "subject2Score");
            return (Criteria) this;
        }

        public Criteria andSubject2ScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("subject2_score not between", value1, value2, "subject2Score");
            return (Criteria) this;
        }

        public Criteria andDebitAmountIsNull() {
            addCriterion("debit_amount is null");
            return (Criteria) this;
        }

        public Criteria andDebitAmountIsNotNull() {
            addCriterion("debit_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDebitAmountEqualTo(Integer value) {
            addCriterion("debit_amount =", value, "debitAmount");
            return (Criteria) this;
        }

        public Criteria andDebitAmountNotEqualTo(Integer value) {
            addCriterion("debit_amount <>", value, "debitAmount");
            return (Criteria) this;
        }

        public Criteria andDebitAmountGreaterThan(Integer value) {
            addCriterion("debit_amount >", value, "debitAmount");
            return (Criteria) this;
        }

        public Criteria andDebitAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("debit_amount >=", value, "debitAmount");
            return (Criteria) this;
        }

        public Criteria andDebitAmountLessThan(Integer value) {
            addCriterion("debit_amount <", value, "debitAmount");
            return (Criteria) this;
        }

        public Criteria andDebitAmountLessThanOrEqualTo(Integer value) {
            addCriterion("debit_amount <=", value, "debitAmount");
            return (Criteria) this;
        }

        public Criteria andDebitAmountIn(List<Integer> values) {
            addCriterion("debit_amount in", values, "debitAmount");
            return (Criteria) this;
        }

        public Criteria andDebitAmountNotIn(List<Integer> values) {
            addCriterion("debit_amount not in", values, "debitAmount");
            return (Criteria) this;
        }

        public Criteria andDebitAmountBetween(Integer value1, Integer value2) {
            addCriterion("debit_amount between", value1, value2, "debitAmount");
            return (Criteria) this;
        }

        public Criteria andDebitAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("debit_amount not between", value1, value2, "debitAmount");
            return (Criteria) this;
        }

        public Criteria andDebitAmountScoreIsNull() {
            addCriterion("debit_amount_score is null");
            return (Criteria) this;
        }

        public Criteria andDebitAmountScoreIsNotNull() {
            addCriterion("debit_amount_score is not null");
            return (Criteria) this;
        }

        public Criteria andDebitAmountScoreEqualTo(BigDecimal value) {
            addCriterion("debit_amount_score =", value, "debitAmountScore");
            return (Criteria) this;
        }

        public Criteria andDebitAmountScoreNotEqualTo(BigDecimal value) {
            addCriterion("debit_amount_score <>", value, "debitAmountScore");
            return (Criteria) this;
        }

        public Criteria andDebitAmountScoreGreaterThan(BigDecimal value) {
            addCriterion("debit_amount_score >", value, "debitAmountScore");
            return (Criteria) this;
        }

        public Criteria andDebitAmountScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("debit_amount_score >=", value, "debitAmountScore");
            return (Criteria) this;
        }

        public Criteria andDebitAmountScoreLessThan(BigDecimal value) {
            addCriterion("debit_amount_score <", value, "debitAmountScore");
            return (Criteria) this;
        }

        public Criteria andDebitAmountScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("debit_amount_score <=", value, "debitAmountScore");
            return (Criteria) this;
        }

        public Criteria andDebitAmountScoreIn(List<BigDecimal> values) {
            addCriterion("debit_amount_score in", values, "debitAmountScore");
            return (Criteria) this;
        }

        public Criteria andDebitAmountScoreNotIn(List<BigDecimal> values) {
            addCriterion("debit_amount_score not in", values, "debitAmountScore");
            return (Criteria) this;
        }

        public Criteria andDebitAmountScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("debit_amount_score between", value1, value2, "debitAmountScore");
            return (Criteria) this;
        }

        public Criteria andDebitAmountScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("debit_amount_score not between", value1, value2, "debitAmountScore");
            return (Criteria) this;
        }

        public Criteria andCreditAmountIsNull() {
            addCriterion("credit_amount is null");
            return (Criteria) this;
        }

        public Criteria andCreditAmountIsNotNull() {
            addCriterion("credit_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCreditAmountEqualTo(Integer value) {
            addCriterion("credit_amount =", value, "creditAmount");
            return (Criteria) this;
        }

        public Criteria andCreditAmountNotEqualTo(Integer value) {
            addCriterion("credit_amount <>", value, "creditAmount");
            return (Criteria) this;
        }

        public Criteria andCreditAmountGreaterThan(Integer value) {
            addCriterion("credit_amount >", value, "creditAmount");
            return (Criteria) this;
        }

        public Criteria andCreditAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("credit_amount >=", value, "creditAmount");
            return (Criteria) this;
        }

        public Criteria andCreditAmountLessThan(Integer value) {
            addCriterion("credit_amount <", value, "creditAmount");
            return (Criteria) this;
        }

        public Criteria andCreditAmountLessThanOrEqualTo(Integer value) {
            addCriterion("credit_amount <=", value, "creditAmount");
            return (Criteria) this;
        }

        public Criteria andCreditAmountIn(List<Integer> values) {
            addCriterion("credit_amount in", values, "creditAmount");
            return (Criteria) this;
        }

        public Criteria andCreditAmountNotIn(List<Integer> values) {
            addCriterion("credit_amount not in", values, "creditAmount");
            return (Criteria) this;
        }

        public Criteria andCreditAmountBetween(Integer value1, Integer value2) {
            addCriterion("credit_amount between", value1, value2, "creditAmount");
            return (Criteria) this;
        }

        public Criteria andCreditAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("credit_amount not between", value1, value2, "creditAmount");
            return (Criteria) this;
        }

        public Criteria andCreditAmountScoreIsNull() {
            addCriterion("credit_amount_score is null");
            return (Criteria) this;
        }

        public Criteria andCreditAmountScoreIsNotNull() {
            addCriterion("credit_amount_score is not null");
            return (Criteria) this;
        }

        public Criteria andCreditAmountScoreEqualTo(BigDecimal value) {
            addCriterion("credit_amount_score =", value, "creditAmountScore");
            return (Criteria) this;
        }

        public Criteria andCreditAmountScoreNotEqualTo(BigDecimal value) {
            addCriterion("credit_amount_score <>", value, "creditAmountScore");
            return (Criteria) this;
        }

        public Criteria andCreditAmountScoreGreaterThan(BigDecimal value) {
            addCriterion("credit_amount_score >", value, "creditAmountScore");
            return (Criteria) this;
        }

        public Criteria andCreditAmountScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("credit_amount_score >=", value, "creditAmountScore");
            return (Criteria) this;
        }

        public Criteria andCreditAmountScoreLessThan(BigDecimal value) {
            addCriterion("credit_amount_score <", value, "creditAmountScore");
            return (Criteria) this;
        }

        public Criteria andCreditAmountScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("credit_amount_score <=", value, "creditAmountScore");
            return (Criteria) this;
        }

        public Criteria andCreditAmountScoreIn(List<BigDecimal> values) {
            addCriterion("credit_amount_score in", values, "creditAmountScore");
            return (Criteria) this;
        }

        public Criteria andCreditAmountScoreNotIn(List<BigDecimal> values) {
            addCriterion("credit_amount_score not in", values, "creditAmountScore");
            return (Criteria) this;
        }

        public Criteria andCreditAmountScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit_amount_score between", value1, value2, "creditAmountScore");
            return (Criteria) this;
        }

        public Criteria andCreditAmountScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit_amount_score not between", value1, value2, "creditAmountScore");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(String value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(String value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(String value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(String value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(String value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(String value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLike(String value) {
            addCriterion("total like", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotLike(String value) {
            addCriterion("total not like", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<String> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<String> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(String value1, String value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(String value1, String value2) {
            addCriterion("total not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNull() {
            addCriterion("total_score is null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNotNull() {
            addCriterion("total_score is not null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreEqualTo(BigDecimal value) {
            addCriterion("total_score =", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotEqualTo(BigDecimal value) {
            addCriterion("total_score <>", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThan(BigDecimal value) {
            addCriterion("total_score >", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_score >=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThan(BigDecimal value) {
            addCriterion("total_score <", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_score <=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIn(List<BigDecimal> values) {
            addCriterion("total_score in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotIn(List<BigDecimal> values) {
            addCriterion("total_score not in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_score between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_score not between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andDebitTotalIsNull() {
            addCriterion("debit_total is null");
            return (Criteria) this;
        }

        public Criteria andDebitTotalIsNotNull() {
            addCriterion("debit_total is not null");
            return (Criteria) this;
        }

        public Criteria andDebitTotalEqualTo(Integer value) {
            addCriterion("debit_total =", value, "debitTotal");
            return (Criteria) this;
        }

        public Criteria andDebitTotalNotEqualTo(Integer value) {
            addCriterion("debit_total <>", value, "debitTotal");
            return (Criteria) this;
        }

        public Criteria andDebitTotalGreaterThan(Integer value) {
            addCriterion("debit_total >", value, "debitTotal");
            return (Criteria) this;
        }

        public Criteria andDebitTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("debit_total >=", value, "debitTotal");
            return (Criteria) this;
        }

        public Criteria andDebitTotalLessThan(Integer value) {
            addCriterion("debit_total <", value, "debitTotal");
            return (Criteria) this;
        }

        public Criteria andDebitTotalLessThanOrEqualTo(Integer value) {
            addCriterion("debit_total <=", value, "debitTotal");
            return (Criteria) this;
        }

        public Criteria andDebitTotalIn(List<Integer> values) {
            addCriterion("debit_total in", values, "debitTotal");
            return (Criteria) this;
        }

        public Criteria andDebitTotalNotIn(List<Integer> values) {
            addCriterion("debit_total not in", values, "debitTotal");
            return (Criteria) this;
        }

        public Criteria andDebitTotalBetween(Integer value1, Integer value2) {
            addCriterion("debit_total between", value1, value2, "debitTotal");
            return (Criteria) this;
        }

        public Criteria andDebitTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("debit_total not between", value1, value2, "debitTotal");
            return (Criteria) this;
        }

        public Criteria andDebitTotalScoreIsNull() {
            addCriterion("debit_total_score is null");
            return (Criteria) this;
        }

        public Criteria andDebitTotalScoreIsNotNull() {
            addCriterion("debit_total_score is not null");
            return (Criteria) this;
        }

        public Criteria andDebitTotalScoreEqualTo(BigDecimal value) {
            addCriterion("debit_total_score =", value, "debitTotalScore");
            return (Criteria) this;
        }

        public Criteria andDebitTotalScoreNotEqualTo(BigDecimal value) {
            addCriterion("debit_total_score <>", value, "debitTotalScore");
            return (Criteria) this;
        }

        public Criteria andDebitTotalScoreGreaterThan(BigDecimal value) {
            addCriterion("debit_total_score >", value, "debitTotalScore");
            return (Criteria) this;
        }

        public Criteria andDebitTotalScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("debit_total_score >=", value, "debitTotalScore");
            return (Criteria) this;
        }

        public Criteria andDebitTotalScoreLessThan(BigDecimal value) {
            addCriterion("debit_total_score <", value, "debitTotalScore");
            return (Criteria) this;
        }

        public Criteria andDebitTotalScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("debit_total_score <=", value, "debitTotalScore");
            return (Criteria) this;
        }

        public Criteria andDebitTotalScoreIn(List<BigDecimal> values) {
            addCriterion("debit_total_score in", values, "debitTotalScore");
            return (Criteria) this;
        }

        public Criteria andDebitTotalScoreNotIn(List<BigDecimal> values) {
            addCriterion("debit_total_score not in", values, "debitTotalScore");
            return (Criteria) this;
        }

        public Criteria andDebitTotalScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("debit_total_score between", value1, value2, "debitTotalScore");
            return (Criteria) this;
        }

        public Criteria andDebitTotalScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("debit_total_score not between", value1, value2, "debitTotalScore");
            return (Criteria) this;
        }

        public Criteria andCreditTotalIsNull() {
            addCriterion("credit_total is null");
            return (Criteria) this;
        }

        public Criteria andCreditTotalIsNotNull() {
            addCriterion("credit_total is not null");
            return (Criteria) this;
        }

        public Criteria andCreditTotalEqualTo(Integer value) {
            addCriterion("credit_total =", value, "creditTotal");
            return (Criteria) this;
        }

        public Criteria andCreditTotalNotEqualTo(Integer value) {
            addCriterion("credit_total <>", value, "creditTotal");
            return (Criteria) this;
        }

        public Criteria andCreditTotalGreaterThan(Integer value) {
            addCriterion("credit_total >", value, "creditTotal");
            return (Criteria) this;
        }

        public Criteria andCreditTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("credit_total >=", value, "creditTotal");
            return (Criteria) this;
        }

        public Criteria andCreditTotalLessThan(Integer value) {
            addCriterion("credit_total <", value, "creditTotal");
            return (Criteria) this;
        }

        public Criteria andCreditTotalLessThanOrEqualTo(Integer value) {
            addCriterion("credit_total <=", value, "creditTotal");
            return (Criteria) this;
        }

        public Criteria andCreditTotalIn(List<Integer> values) {
            addCriterion("credit_total in", values, "creditTotal");
            return (Criteria) this;
        }

        public Criteria andCreditTotalNotIn(List<Integer> values) {
            addCriterion("credit_total not in", values, "creditTotal");
            return (Criteria) this;
        }

        public Criteria andCreditTotalBetween(Integer value1, Integer value2) {
            addCriterion("credit_total between", value1, value2, "creditTotal");
            return (Criteria) this;
        }

        public Criteria andCreditTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("credit_total not between", value1, value2, "creditTotal");
            return (Criteria) this;
        }

        public Criteria andCreditTotalScoreIsNull() {
            addCriterion("credit_total_score is null");
            return (Criteria) this;
        }

        public Criteria andCreditTotalScoreIsNotNull() {
            addCriterion("credit_total_score is not null");
            return (Criteria) this;
        }

        public Criteria andCreditTotalScoreEqualTo(BigDecimal value) {
            addCriterion("credit_total_score =", value, "creditTotalScore");
            return (Criteria) this;
        }

        public Criteria andCreditTotalScoreNotEqualTo(BigDecimal value) {
            addCriterion("credit_total_score <>", value, "creditTotalScore");
            return (Criteria) this;
        }

        public Criteria andCreditTotalScoreGreaterThan(BigDecimal value) {
            addCriterion("credit_total_score >", value, "creditTotalScore");
            return (Criteria) this;
        }

        public Criteria andCreditTotalScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("credit_total_score >=", value, "creditTotalScore");
            return (Criteria) this;
        }

        public Criteria andCreditTotalScoreLessThan(BigDecimal value) {
            addCriterion("credit_total_score <", value, "creditTotalScore");
            return (Criteria) this;
        }

        public Criteria andCreditTotalScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("credit_total_score <=", value, "creditTotalScore");
            return (Criteria) this;
        }

        public Criteria andCreditTotalScoreIn(List<BigDecimal> values) {
            addCriterion("credit_total_score in", values, "creditTotalScore");
            return (Criteria) this;
        }

        public Criteria andCreditTotalScoreNotIn(List<BigDecimal> values) {
            addCriterion("credit_total_score not in", values, "creditTotalScore");
            return (Criteria) this;
        }

        public Criteria andCreditTotalScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit_total_score between", value1, value2, "creditTotalScore");
            return (Criteria) this;
        }

        public Criteria andCreditTotalScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit_total_score not between", value1, value2, "creditTotalScore");
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