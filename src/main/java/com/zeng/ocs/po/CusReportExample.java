package com.zeng.ocs.po;

import java.util.ArrayList;
import java.util.List;
/**
 * 月报表的条件查询实体类
 * @author cxs
 *
 */
public class CusReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CusReportExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

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

        public Criteria andCosidIsNull() {
            addCriterion("cosid is null");
            return (Criteria) this;
        }

        public Criteria andCosidIsNotNull() {
            addCriterion("cosid is not null");
            return (Criteria) this;
        }

        public Criteria andCosidEqualTo(String value) {
            addCriterion("cosid =", value, "cosid");
            return (Criteria) this;
        }

        public Criteria andCosidNotEqualTo(String value) {
            addCriterion("cosid <>", value, "cosid");
            return (Criteria) this;
        }

        public Criteria andCosidGreaterThan(String value) {
            addCriterion("cosid >", value, "cosid");
            return (Criteria) this;
        }

        public Criteria andCosidGreaterThanOrEqualTo(String value) {
            addCriterion("cosid >=", value, "cosid");
            return (Criteria) this;
        }

        public Criteria andCosidLessThan(String value) {
            addCriterion("cosid <", value, "cosid");
            return (Criteria) this;
        }

        public Criteria andCosidLessThanOrEqualTo(String value) {
            addCriterion("cosid <=", value, "cosid");
            return (Criteria) this;
        }

        public Criteria andCosidLike(String value) {
            addCriterion("cosid like", value, "cosid");
            return (Criteria) this;
        }

        public Criteria andCosidNotLike(String value) {
            addCriterion("cosid not like", value, "cosid");
            return (Criteria) this;
        }

        public Criteria andCosidIn(List<String> values) {
            addCriterion("cosid in", values, "cosid");
            return (Criteria) this;
        }

        public Criteria andCosidNotIn(List<String> values) {
            addCriterion("cosid not in", values, "cosid");
            return (Criteria) this;
        }

        public Criteria andCosidBetween(String value1, String value2) {
            addCriterion("cosid between", value1, value2, "cosid");
            return (Criteria) this;
        }

        public Criteria andCosidNotBetween(String value1, String value2) {
            addCriterion("cosid not between", value1, value2, "cosid");
            return (Criteria) this;
        }

        public Criteria andMonthIsNull() {
            addCriterion("month is null");
            return (Criteria) this;
        }

        public Criteria andMonthIsNotNull() {
            addCriterion("month is not null");
            return (Criteria) this;
        }

        public Criteria andMonthEqualTo(String value) {
            addCriterion("month =", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotEqualTo(String value) {
            addCriterion("month <>", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThan(String value) {
            addCriterion("month >", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThanOrEqualTo(String value) {
            addCriterion("month >=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThan(String value) {
            addCriterion("month <", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThanOrEqualTo(String value) {
            addCriterion("month <=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLike(String value) {
            addCriterion("month like", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotLike(String value) {
            addCriterion("month not like", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthIn(List<String> values) {
            addCriterion("month in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotIn(List<String> values) {
            addCriterion("month not in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthBetween(String value1, String value2) {
            addCriterion("month between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotBetween(String value1, String value2) {
            addCriterion("month not between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andIncountIsNull() {
            addCriterion("incount is null");
            return (Criteria) this;
        }

        public Criteria andIncountIsNotNull() {
            addCriterion("incount is not null");
            return (Criteria) this;
        }

        public Criteria andIncountEqualTo(Integer value) {
            addCriterion("incount =", value, "incount");
            return (Criteria) this;
        }

        public Criteria andIncountNotEqualTo(Integer value) {
            addCriterion("incount <>", value, "incount");
            return (Criteria) this;
        }

        public Criteria andIncountGreaterThan(Integer value) {
            addCriterion("incount >", value, "incount");
            return (Criteria) this;
        }

        public Criteria andIncountGreaterThanOrEqualTo(Integer value) {
            addCriterion("incount >=", value, "incount");
            return (Criteria) this;
        }

        public Criteria andIncountLessThan(Integer value) {
            addCriterion("incount <", value, "incount");
            return (Criteria) this;
        }

        public Criteria andIncountLessThanOrEqualTo(Integer value) {
            addCriterion("incount <=", value, "incount");
            return (Criteria) this;
        }

        public Criteria andIncountIn(List<Integer> values) {
            addCriterion("incount in", values, "incount");
            return (Criteria) this;
        }

        public Criteria andIncountNotIn(List<Integer> values) {
            addCriterion("incount not in", values, "incount");
            return (Criteria) this;
        }

        public Criteria andIncountBetween(Integer value1, Integer value2) {
            addCriterion("incount between", value1, value2, "incount");
            return (Criteria) this;
        }

        public Criteria andIncountNotBetween(Integer value1, Integer value2) {
            addCriterion("incount not between", value1, value2, "incount");
            return (Criteria) this;
        }

        public Criteria andInsecondsIsNull() {
            addCriterion("inseconds is null");
            return (Criteria) this;
        }

        public Criteria andInsecondsIsNotNull() {
            addCriterion("inseconds is not null");
            return (Criteria) this;
        }

        public Criteria andInsecondsEqualTo(Integer value) {
            addCriterion("inseconds =", value, "inseconds");
            return (Criteria) this;
        }

        public Criteria andInsecondsNotEqualTo(Integer value) {
            addCriterion("inseconds <>", value, "inseconds");
            return (Criteria) this;
        }

        public Criteria andInsecondsGreaterThan(Integer value) {
            addCriterion("inseconds >", value, "inseconds");
            return (Criteria) this;
        }

        public Criteria andInsecondsGreaterThanOrEqualTo(Integer value) {
            addCriterion("inseconds >=", value, "inseconds");
            return (Criteria) this;
        }

        public Criteria andInsecondsLessThan(Integer value) {
            addCriterion("inseconds <", value, "inseconds");
            return (Criteria) this;
        }

        public Criteria andInsecondsLessThanOrEqualTo(Integer value) {
            addCriterion("inseconds <=", value, "inseconds");
            return (Criteria) this;
        }

        public Criteria andInsecondsIn(List<Integer> values) {
            addCriterion("inseconds in", values, "inseconds");
            return (Criteria) this;
        }

        public Criteria andInsecondsNotIn(List<Integer> values) {
            addCriterion("inseconds not in", values, "inseconds");
            return (Criteria) this;
        }

        public Criteria andInsecondsBetween(Integer value1, Integer value2) {
            addCriterion("inseconds between", value1, value2, "inseconds");
            return (Criteria) this;
        }

        public Criteria andInsecondsNotBetween(Integer value1, Integer value2) {
            addCriterion("inseconds not between", value1, value2, "inseconds");
            return (Criteria) this;
        }

        public Criteria andOutcountIsNull() {
            addCriterion("outcount is null");
            return (Criteria) this;
        }

        public Criteria andOutcountIsNotNull() {
            addCriterion("outcount is not null");
            return (Criteria) this;
        }

        public Criteria andOutcountEqualTo(Integer value) {
            addCriterion("outcount =", value, "outcount");
            return (Criteria) this;
        }

        public Criteria andOutcountNotEqualTo(Integer value) {
            addCriterion("outcount <>", value, "outcount");
            return (Criteria) this;
        }

        public Criteria andOutcountGreaterThan(Integer value) {
            addCriterion("outcount >", value, "outcount");
            return (Criteria) this;
        }

        public Criteria andOutcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("outcount >=", value, "outcount");
            return (Criteria) this;
        }

        public Criteria andOutcountLessThan(Integer value) {
            addCriterion("outcount <", value, "outcount");
            return (Criteria) this;
        }

        public Criteria andOutcountLessThanOrEqualTo(Integer value) {
            addCriterion("outcount <=", value, "outcount");
            return (Criteria) this;
        }

        public Criteria andOutcountIn(List<Integer> values) {
            addCriterion("outcount in", values, "outcount");
            return (Criteria) this;
        }

        public Criteria andOutcountNotIn(List<Integer> values) {
            addCriterion("outcount not in", values, "outcount");
            return (Criteria) this;
        }

        public Criteria andOutcountBetween(Integer value1, Integer value2) {
            addCriterion("outcount between", value1, value2, "outcount");
            return (Criteria) this;
        }

        public Criteria andOutcountNotBetween(Integer value1, Integer value2) {
            addCriterion("outcount not between", value1, value2, "outcount");
            return (Criteria) this;
        }

        public Criteria andOutsencondsIsNull() {
            addCriterion("outsenconds is null");
            return (Criteria) this;
        }

        public Criteria andOutsencondsIsNotNull() {
            addCriterion("outsenconds is not null");
            return (Criteria) this;
        }

        public Criteria andOutsencondsEqualTo(Integer value) {
            addCriterion("outsenconds =", value, "outsenconds");
            return (Criteria) this;
        }

        public Criteria andOutsencondsNotEqualTo(Integer value) {
            addCriterion("outsenconds <>", value, "outsenconds");
            return (Criteria) this;
        }

        public Criteria andOutsencondsGreaterThan(Integer value) {
            addCriterion("outsenconds >", value, "outsenconds");
            return (Criteria) this;
        }

        public Criteria andOutsencondsGreaterThanOrEqualTo(Integer value) {
            addCriterion("outsenconds >=", value, "outsenconds");
            return (Criteria) this;
        }

        public Criteria andOutsencondsLessThan(Integer value) {
            addCriterion("outsenconds <", value, "outsenconds");
            return (Criteria) this;
        }

        public Criteria andOutsencondsLessThanOrEqualTo(Integer value) {
            addCriterion("outsenconds <=", value, "outsenconds");
            return (Criteria) this;
        }

        public Criteria andOutsencondsIn(List<Integer> values) {
            addCriterion("outsenconds in", values, "outsenconds");
            return (Criteria) this;
        }

        public Criteria andOutsencondsNotIn(List<Integer> values) {
            addCriterion("outsenconds not in", values, "outsenconds");
            return (Criteria) this;
        }

        public Criteria andOutsencondsBetween(Integer value1, Integer value2) {
            addCriterion("outsenconds between", value1, value2, "outsenconds");
            return (Criteria) this;
        }

        public Criteria andOutsencondsNotBetween(Integer value1, Integer value2) {
            addCriterion("outsenconds not between", value1, value2, "outsenconds");
            return (Criteria) this;
        }

        public Criteria andMsgcountIsNull() {
            addCriterion("msgcount is null");
            return (Criteria) this;
        }

        public Criteria andMsgcountIsNotNull() {
            addCriterion("msgcount is not null");
            return (Criteria) this;
        }

        public Criteria andMsgcountEqualTo(Integer value) {
            addCriterion("msgcount =", value, "msgcount");
            return (Criteria) this;
        }

        public Criteria andMsgcountNotEqualTo(Integer value) {
            addCriterion("msgcount <>", value, "msgcount");
            return (Criteria) this;
        }

        public Criteria andMsgcountGreaterThan(Integer value) {
            addCriterion("msgcount >", value, "msgcount");
            return (Criteria) this;
        }

        public Criteria andMsgcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("msgcount >=", value, "msgcount");
            return (Criteria) this;
        }

        public Criteria andMsgcountLessThan(Integer value) {
            addCriterion("msgcount <", value, "msgcount");
            return (Criteria) this;
        }

        public Criteria andMsgcountLessThanOrEqualTo(Integer value) {
            addCriterion("msgcount <=", value, "msgcount");
            return (Criteria) this;
        }

        public Criteria andMsgcountIn(List<Integer> values) {
            addCriterion("msgcount in", values, "msgcount");
            return (Criteria) this;
        }

        public Criteria andMsgcountNotIn(List<Integer> values) {
            addCriterion("msgcount not in", values, "msgcount");
            return (Criteria) this;
        }

        public Criteria andMsgcountBetween(Integer value1, Integer value2) {
            addCriterion("msgcount between", value1, value2, "msgcount");
            return (Criteria) this;
        }

        public Criteria andMsgcountNotBetween(Integer value1, Integer value2) {
            addCriterion("msgcount not between", value1, value2, "msgcount");
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