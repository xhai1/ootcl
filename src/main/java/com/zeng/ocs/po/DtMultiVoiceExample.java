package com.zeng.ocs.po;

import java.util.ArrayList;
import java.util.List;
/**
 * 多媒体条件类
 * @author cxs
 *
 */
public class DtMultiVoiceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DtMultiVoiceExample() {
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

        public Criteria andCusidIsNull() {
            addCriterion("cusid is null");
            return (Criteria) this;
        }

        public Criteria andCusidIsNotNull() {
            addCriterion("cusid is not null");
            return (Criteria) this;
        }

        public Criteria andCusidEqualTo(String value) {
            addCriterion("cusid =", value, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidNotEqualTo(String value) {
            addCriterion("cusid <>", value, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidGreaterThan(String value) {
            addCriterion("cusid >", value, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidGreaterThanOrEqualTo(String value) {
            addCriterion("cusid >=", value, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidLessThan(String value) {
            addCriterion("cusid <", value, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidLessThanOrEqualTo(String value) {
            addCriterion("cusid <=", value, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidLike(String value) {
            addCriterion("cusid like", value, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidNotLike(String value) {
            addCriterion("cusid not like", value, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidIn(List<String> values) {
            addCriterion("cusid in", values, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidNotIn(List<String> values) {
            addCriterion("cusid not in", values, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidBetween(String value1, String value2) {
            addCriterion("cusid between", value1, value2, "cusid");
            return (Criteria) this;
        }

        public Criteria andCusidNotBetween(String value1, String value2) {
            addCriterion("cusid not between", value1, value2, "cusid");
            return (Criteria) this;
        }

        public Criteria andTraidIsNull() {
            addCriterion("traid is null");
            return (Criteria) this;
        }

        public Criteria andTraidIsNotNull() {
            addCriterion("traid is not null");
            return (Criteria) this;
        }

        public Criteria andTraidEqualTo(String value) {
            addCriterion("traid =", value, "traid");
            return (Criteria) this;
        }

        public Criteria andTraidNotEqualTo(String value) {
            addCriterion("traid <>", value, "traid");
            return (Criteria) this;
        }

        public Criteria andTraidGreaterThan(String value) {
            addCriterion("traid >", value, "traid");
            return (Criteria) this;
        }

        public Criteria andTraidGreaterThanOrEqualTo(String value) {
            addCriterion("traid >=", value, "traid");
            return (Criteria) this;
        }

        public Criteria andTraidLessThan(String value) {
            addCriterion("traid <", value, "traid");
            return (Criteria) this;
        }

        public Criteria andTraidLessThanOrEqualTo(String value) {
            addCriterion("traid <=", value, "traid");
            return (Criteria) this;
        }

        public Criteria andTraidLike(String value) {
            addCriterion("traid like", value, "traid");
            return (Criteria) this;
        }

        public Criteria andTraidNotLike(String value) {
            addCriterion("traid not like", value, "traid");
            return (Criteria) this;
        }

        public Criteria andTraidIn(List<String> values) {
            addCriterion("traid in", values, "traid");
            return (Criteria) this;
        }

        public Criteria andTraidNotIn(List<String> values) {
            addCriterion("traid not in", values, "traid");
            return (Criteria) this;
        }

        public Criteria andTraidBetween(String value1, String value2) {
            addCriterion("traid between", value1, value2, "traid");
            return (Criteria) this;
        }

        public Criteria andTraidNotBetween(String value1, String value2) {
            addCriterion("traid not between", value1, value2, "traid");
            return (Criteria) this;
        }

        public Criteria andCallingnoIsNull() {
            addCriterion("callingno is null");
            return (Criteria) this;
        }

        public Criteria andCallingnoIsNotNull() {
            addCriterion("callingno is not null");
            return (Criteria) this;
        }

        public Criteria andCallingnoEqualTo(String value) {
            addCriterion("callingno =", value, "callingno");
            return (Criteria) this;
        }

        public Criteria andCallingnoNotEqualTo(String value) {
            addCriterion("callingno <>", value, "callingno");
            return (Criteria) this;
        }

        public Criteria andCallingnoGreaterThan(String value) {
            addCriterion("callingno >", value, "callingno");
            return (Criteria) this;
        }

        public Criteria andCallingnoGreaterThanOrEqualTo(String value) {
            addCriterion("callingno >=", value, "callingno");
            return (Criteria) this;
        }

        public Criteria andCallingnoLessThan(String value) {
            addCriterion("callingno <", value, "callingno");
            return (Criteria) this;
        }

        public Criteria andCallingnoLessThanOrEqualTo(String value) {
            addCriterion("callingno <=", value, "callingno");
            return (Criteria) this;
        }

        public Criteria andCallingnoLike(String value) {
            addCriterion("callingno like", value, "callingno");
            return (Criteria) this;
        }

        public Criteria andCallingnoNotLike(String value) {
            addCriterion("callingno not like", value, "callingno");
            return (Criteria) this;
        }

        public Criteria andCallingnoIn(List<String> values) {
            addCriterion("callingno in", values, "callingno");
            return (Criteria) this;
        }

        public Criteria andCallingnoNotIn(List<String> values) {
            addCriterion("callingno not in", values, "callingno");
            return (Criteria) this;
        }

        public Criteria andCallingnoBetween(String value1, String value2) {
            addCriterion("callingno between", value1, value2, "callingno");
            return (Criteria) this;
        }

        public Criteria andCallingnoNotBetween(String value1, String value2) {
            addCriterion("callingno not between", value1, value2, "callingno");
            return (Criteria) this;
        }

        public Criteria andCallnoIsNull() {
            addCriterion("callno is null");
            return (Criteria) this;
        }

        public Criteria andCallnoIsNotNull() {
            addCriterion("callno is not null");
            return (Criteria) this;
        }

        public Criteria andCallnoEqualTo(String value) {
            addCriterion("callno =", value, "callno");
            return (Criteria) this;
        }

        public Criteria andCallnoNotEqualTo(String value) {
            addCriterion("callno <>", value, "callno");
            return (Criteria) this;
        }

        public Criteria andCallnoGreaterThan(String value) {
            addCriterion("callno >", value, "callno");
            return (Criteria) this;
        }

        public Criteria andCallnoGreaterThanOrEqualTo(String value) {
            addCriterion("callno >=", value, "callno");
            return (Criteria) this;
        }

        public Criteria andCallnoLessThan(String value) {
            addCriterion("callno <", value, "callno");
            return (Criteria) this;
        }

        public Criteria andCallnoLessThanOrEqualTo(String value) {
            addCriterion("callno <=", value, "callno");
            return (Criteria) this;
        }

        public Criteria andCallnoLike(String value) {
            addCriterion("callno like", value, "callno");
            return (Criteria) this;
        }

        public Criteria andCallnoNotLike(String value) {
            addCriterion("callno not like", value, "callno");
            return (Criteria) this;
        }

        public Criteria andCallnoIn(List<String> values) {
            addCriterion("callno in", values, "callno");
            return (Criteria) this;
        }

        public Criteria andCallnoNotIn(List<String> values) {
            addCriterion("callno not in", values, "callno");
            return (Criteria) this;
        }

        public Criteria andCallnoBetween(String value1, String value2) {
            addCriterion("callno between", value1, value2, "callno");
            return (Criteria) this;
        }

        public Criteria andCallnoNotBetween(String value1, String value2) {
            addCriterion("callno not between", value1, value2, "callno");
            return (Criteria) this;
        }

        public Criteria andCallednoIsNull() {
            addCriterion("calledno is null");
            return (Criteria) this;
        }

        public Criteria andCallednoIsNotNull() {
            addCriterion("calledno is not null");
            return (Criteria) this;
        }

        public Criteria andCallednoEqualTo(String value) {
            addCriterion("calledno =", value, "calledno");
            return (Criteria) this;
        }

        public Criteria andCallednoNotEqualTo(String value) {
            addCriterion("calledno <>", value, "calledno");
            return (Criteria) this;
        }

        public Criteria andCallednoGreaterThan(String value) {
            addCriterion("calledno >", value, "calledno");
            return (Criteria) this;
        }

        public Criteria andCallednoGreaterThanOrEqualTo(String value) {
            addCriterion("calledno >=", value, "calledno");
            return (Criteria) this;
        }

        public Criteria andCallednoLessThan(String value) {
            addCriterion("calledno <", value, "calledno");
            return (Criteria) this;
        }

        public Criteria andCallednoLessThanOrEqualTo(String value) {
            addCriterion("calledno <=", value, "calledno");
            return (Criteria) this;
        }

        public Criteria andCallednoLike(String value) {
            addCriterion("calledno like", value, "calledno");
            return (Criteria) this;
        }

        public Criteria andCallednoNotLike(String value) {
            addCriterion("calledno not like", value, "calledno");
            return (Criteria) this;
        }

        public Criteria andCallednoIn(List<String> values) {
            addCriterion("calledno in", values, "calledno");
            return (Criteria) this;
        }

        public Criteria andCallednoNotIn(List<String> values) {
            addCriterion("calledno not in", values, "calledno");
            return (Criteria) this;
        }

        public Criteria andCallednoBetween(String value1, String value2) {
            addCriterion("calledno between", value1, value2, "calledno");
            return (Criteria) this;
        }

        public Criteria andCallednoNotBetween(String value1, String value2) {
            addCriterion("calledno not between", value1, value2, "calledno");
            return (Criteria) this;
        }

        public Criteria andTotalsecondsIsNull() {
            addCriterion("totalseconds is null");
            return (Criteria) this;
        }

        public Criteria andTotalsecondsIsNotNull() {
            addCriterion("totalseconds is not null");
            return (Criteria) this;
        }

        public Criteria andTotalsecondsEqualTo(Integer value) {
            addCriterion("totalseconds =", value, "totalseconds");
            return (Criteria) this;
        }

        public Criteria andTotalsecondsNotEqualTo(Integer value) {
            addCriterion("totalseconds <>", value, "totalseconds");
            return (Criteria) this;
        }

        public Criteria andTotalsecondsGreaterThan(Integer value) {
            addCriterion("totalseconds >", value, "totalseconds");
            return (Criteria) this;
        }

        public Criteria andTotalsecondsGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalseconds >=", value, "totalseconds");
            return (Criteria) this;
        }

        public Criteria andTotalsecondsLessThan(Integer value) {
            addCriterion("totalseconds <", value, "totalseconds");
            return (Criteria) this;
        }

        public Criteria andTotalsecondsLessThanOrEqualTo(Integer value) {
            addCriterion("totalseconds <=", value, "totalseconds");
            return (Criteria) this;
        }

        public Criteria andTotalsecondsIn(List<Integer> values) {
            addCriterion("totalseconds in", values, "totalseconds");
            return (Criteria) this;
        }

        public Criteria andTotalsecondsNotIn(List<Integer> values) {
            addCriterion("totalseconds not in", values, "totalseconds");
            return (Criteria) this;
        }

        public Criteria andTotalsecondsBetween(Integer value1, Integer value2) {
            addCriterion("totalseconds between", value1, value2, "totalseconds");
            return (Criteria) this;
        }

        public Criteria andTotalsecondsNotBetween(Integer value1, Integer value2) {
            addCriterion("totalseconds not between", value1, value2, "totalseconds");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("_date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("_date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(String value) {
            addCriterion("_date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(String value) {
            addCriterion("_date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(String value) {
            addCriterion("_date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(String value) {
            addCriterion("_date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(String value) {
            addCriterion("_date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(String value) {
            addCriterion("_date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLike(String value) {
            addCriterion("_date like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotLike(String value) {
            addCriterion("_date not like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<String> values) {
            addCriterion("_date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<String> values) {
            addCriterion("_date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(String value1, String value2) {
            addCriterion("_date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(String value1, String value2) {
            addCriterion("_date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andNtypeIsNull() {
            addCriterion("ntype is null");
            return (Criteria) this;
        }

        public Criteria andNtypeIsNotNull() {
            addCriterion("ntype is not null");
            return (Criteria) this;
        }

        public Criteria andNtypeEqualTo(String value) {
            addCriterion("ntype =", value, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeNotEqualTo(String value) {
            addCriterion("ntype <>", value, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeGreaterThan(String value) {
            addCriterion("ntype >", value, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeGreaterThanOrEqualTo(String value) {
            addCriterion("ntype >=", value, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeLessThan(String value) {
            addCriterion("ntype <", value, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeLessThanOrEqualTo(String value) {
            addCriterion("ntype <=", value, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeLike(String value) {
            addCriterion("ntype like", value, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeNotLike(String value) {
            addCriterion("ntype not like", value, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeIn(List<String> values) {
            addCriterion("ntype in", values, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeNotIn(List<String> values) {
            addCriterion("ntype not in", values, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeBetween(String value1, String value2) {
            addCriterion("ntype between", value1, value2, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeNotBetween(String value1, String value2) {
            addCriterion("ntype not between", value1, value2, "ntype");
            return (Criteria) this;
        }

        public Criteria andSystemsrcIsNull() {
            addCriterion("systemsrc is null");
            return (Criteria) this;
        }

        public Criteria andSystemsrcIsNotNull() {
            addCriterion("systemsrc is not null");
            return (Criteria) this;
        }

        public Criteria andSystemsrcEqualTo(String value) {
            addCriterion("systemsrc =", value, "systemsrc");
            return (Criteria) this;
        }

        public Criteria andSystemsrcNotEqualTo(String value) {
            addCriterion("systemsrc <>", value, "systemsrc");
            return (Criteria) this;
        }

        public Criteria andSystemsrcGreaterThan(String value) {
            addCriterion("systemsrc >", value, "systemsrc");
            return (Criteria) this;
        }

        public Criteria andSystemsrcGreaterThanOrEqualTo(String value) {
            addCriterion("systemsrc >=", value, "systemsrc");
            return (Criteria) this;
        }

        public Criteria andSystemsrcLessThan(String value) {
            addCriterion("systemsrc <", value, "systemsrc");
            return (Criteria) this;
        }

        public Criteria andSystemsrcLessThanOrEqualTo(String value) {
            addCriterion("systemsrc <=", value, "systemsrc");
            return (Criteria) this;
        }

        public Criteria andSystemsrcLike(String value) {
            addCriterion("systemsrc like", value, "systemsrc");
            return (Criteria) this;
        }

        public Criteria andSystemsrcNotLike(String value) {
            addCriterion("systemsrc not like", value, "systemsrc");
            return (Criteria) this;
        }

        public Criteria andSystemsrcIn(List<String> values) {
            addCriterion("systemsrc in", values, "systemsrc");
            return (Criteria) this;
        }

        public Criteria andSystemsrcNotIn(List<String> values) {
            addCriterion("systemsrc not in", values, "systemsrc");
            return (Criteria) this;
        }

        public Criteria andSystemsrcBetween(String value1, String value2) {
            addCriterion("systemsrc between", value1, value2, "systemsrc");
            return (Criteria) this;
        }

        public Criteria andSystemsrcNotBetween(String value1, String value2) {
            addCriterion("systemsrc not between", value1, value2, "systemsrc");
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