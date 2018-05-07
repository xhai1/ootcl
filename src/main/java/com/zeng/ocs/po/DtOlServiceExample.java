package com.zeng.ocs.po;

import java.util.ArrayList;
import java.util.List;
/**
 * 在线服务的条件
 * @author cxs
 *
 */
public class DtOlServiceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DtOlServiceExample() {
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

        public Criteria andMediaidIsNull() {
            addCriterion("mediaid is null");
            return (Criteria) this;
        }

        public Criteria andMediaidIsNotNull() {
            addCriterion("mediaid is not null");
            return (Criteria) this;
        }

        public Criteria andMediaidEqualTo(String value) {
            addCriterion("mediaid =", value, "mediaid");
            return (Criteria) this;
        }

        public Criteria andMediaidNotEqualTo(String value) {
            addCriterion("mediaid <>", value, "mediaid");
            return (Criteria) this;
        }

        public Criteria andMediaidGreaterThan(String value) {
            addCriterion("mediaid >", value, "mediaid");
            return (Criteria) this;
        }

        public Criteria andMediaidGreaterThanOrEqualTo(String value) {
            addCriterion("mediaid >=", value, "mediaid");
            return (Criteria) this;
        }

        public Criteria andMediaidLessThan(String value) {
            addCriterion("mediaid <", value, "mediaid");
            return (Criteria) this;
        }

        public Criteria andMediaidLessThanOrEqualTo(String value) {
            addCriterion("mediaid <=", value, "mediaid");
            return (Criteria) this;
        }

        public Criteria andMediaidLike(String value) {
            addCriterion("mediaid like", value, "mediaid");
            return (Criteria) this;
        }

        public Criteria andMediaidNotLike(String value) {
            addCriterion("mediaid not like", value, "mediaid");
            return (Criteria) this;
        }

        public Criteria andMediaidIn(List<String> values) {
            addCriterion("mediaid in", values, "mediaid");
            return (Criteria) this;
        }

        public Criteria andMediaidNotIn(List<String> values) {
            addCriterion("mediaid not in", values, "mediaid");
            return (Criteria) this;
        }

        public Criteria andMediaidBetween(String value1, String value2) {
            addCriterion("mediaid between", value1, value2, "mediaid");
            return (Criteria) this;
        }

        public Criteria andMediaidNotBetween(String value1, String value2) {
            addCriterion("mediaid not between", value1, value2, "mediaid");
            return (Criteria) this;
        }

        public Criteria andMediatypeIsNull() {
            addCriterion("mediatype is null");
            return (Criteria) this;
        }

        public Criteria andMediatypeIsNotNull() {
            addCriterion("mediatype is not null");
            return (Criteria) this;
        }

        public Criteria andMediatypeEqualTo(String value) {
            addCriterion("mediatype =", value, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeNotEqualTo(String value) {
            addCriterion("mediatype <>", value, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeGreaterThan(String value) {
            addCriterion("mediatype >", value, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeGreaterThanOrEqualTo(String value) {
            addCriterion("mediatype >=", value, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeLessThan(String value) {
            addCriterion("mediatype <", value, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeLessThanOrEqualTo(String value) {
            addCriterion("mediatype <=", value, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeLike(String value) {
            addCriterion("mediatype like", value, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeNotLike(String value) {
            addCriterion("mediatype not like", value, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeIn(List<String> values) {
            addCriterion("mediatype in", values, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeNotIn(List<String> values) {
            addCriterion("mediatype not in", values, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeBetween(String value1, String value2) {
            addCriterion("mediatype between", value1, value2, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediatypeNotBetween(String value1, String value2) {
            addCriterion("mediatype not between", value1, value2, "mediatype");
            return (Criteria) this;
        }

        public Criteria andMediasrcIsNull() {
            addCriterion("mediasrc is null");
            return (Criteria) this;
        }

        public Criteria andMediasrcIsNotNull() {
            addCriterion("mediasrc is not null");
            return (Criteria) this;
        }

        public Criteria andMediasrcEqualTo(String value) {
            addCriterion("mediasrc =", value, "mediasrc");
            return (Criteria) this;
        }

        public Criteria andMediasrcNotEqualTo(String value) {
            addCriterion("mediasrc <>", value, "mediasrc");
            return (Criteria) this;
        }

        public Criteria andMediasrcGreaterThan(String value) {
            addCriterion("mediasrc >", value, "mediasrc");
            return (Criteria) this;
        }

        public Criteria andMediasrcGreaterThanOrEqualTo(String value) {
            addCriterion("mediasrc >=", value, "mediasrc");
            return (Criteria) this;
        }

        public Criteria andMediasrcLessThan(String value) {
            addCriterion("mediasrc <", value, "mediasrc");
            return (Criteria) this;
        }

        public Criteria andMediasrcLessThanOrEqualTo(String value) {
            addCriterion("mediasrc <=", value, "mediasrc");
            return (Criteria) this;
        }

        public Criteria andMediasrcLike(String value) {
            addCriterion("mediasrc like", value, "mediasrc");
            return (Criteria) this;
        }

        public Criteria andMediasrcNotLike(String value) {
            addCriterion("mediasrc not like", value, "mediasrc");
            return (Criteria) this;
        }

        public Criteria andMediasrcIn(List<String> values) {
            addCriterion("mediasrc in", values, "mediasrc");
            return (Criteria) this;
        }

        public Criteria andMediasrcNotIn(List<String> values) {
            addCriterion("mediasrc not in", values, "mediasrc");
            return (Criteria) this;
        }

        public Criteria andMediasrcBetween(String value1, String value2) {
            addCriterion("mediasrc between", value1, value2, "mediasrc");
            return (Criteria) this;
        }

        public Criteria andMediasrcNotBetween(String value1, String value2) {
            addCriterion("mediasrc not between", value1, value2, "mediasrc");
            return (Criteria) this;
        }

        public Criteria andBusinesstypeIsNull() {
            addCriterion("businesstype is null");
            return (Criteria) this;
        }

        public Criteria andBusinesstypeIsNotNull() {
            addCriterion("businesstype is not null");
            return (Criteria) this;
        }

        public Criteria andBusinesstypeEqualTo(String value) {
            addCriterion("businesstype =", value, "businesstype");
            return (Criteria) this;
        }

        public Criteria andBusinesstypeNotEqualTo(String value) {
            addCriterion("businesstype <>", value, "businesstype");
            return (Criteria) this;
        }

        public Criteria andBusinesstypeGreaterThan(String value) {
            addCriterion("businesstype >", value, "businesstype");
            return (Criteria) this;
        }

        public Criteria andBusinesstypeGreaterThanOrEqualTo(String value) {
            addCriterion("businesstype >=", value, "businesstype");
            return (Criteria) this;
        }

        public Criteria andBusinesstypeLessThan(String value) {
            addCriterion("businesstype <", value, "businesstype");
            return (Criteria) this;
        }

        public Criteria andBusinesstypeLessThanOrEqualTo(String value) {
            addCriterion("businesstype <=", value, "businesstype");
            return (Criteria) this;
        }

        public Criteria andBusinesstypeLike(String value) {
            addCriterion("businesstype like", value, "businesstype");
            return (Criteria) this;
        }

        public Criteria andBusinesstypeNotLike(String value) {
            addCriterion("businesstype not like", value, "businesstype");
            return (Criteria) this;
        }

        public Criteria andBusinesstypeIn(List<String> values) {
            addCriterion("businesstype in", values, "businesstype");
            return (Criteria) this;
        }

        public Criteria andBusinesstypeNotIn(List<String> values) {
            addCriterion("businesstype not in", values, "businesstype");
            return (Criteria) this;
        }

        public Criteria andBusinesstypeBetween(String value1, String value2) {
            addCriterion("businesstype between", value1, value2, "businesstype");
            return (Criteria) this;
        }

        public Criteria andBusinesstypeNotBetween(String value1, String value2) {
            addCriterion("businesstype not between", value1, value2, "businesstype");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNull() {
            addCriterion("begintime is null");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNotNull() {
            addCriterion("begintime is not null");
            return (Criteria) this;
        }

        public Criteria andBegintimeEqualTo(String value) {
            addCriterion("begintime =", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotEqualTo(String value) {
            addCriterion("begintime <>", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThan(String value) {
            addCriterion("begintime >", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThanOrEqualTo(String value) {
            addCriterion("begintime >=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThan(String value) {
            addCriterion("begintime <", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThanOrEqualTo(String value) {
            addCriterion("begintime <=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLike(String value) {
            addCriterion("begintime like", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotLike(String value) {
            addCriterion("begintime not like", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeIn(List<String> values) {
            addCriterion("begintime in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotIn(List<String> values) {
            addCriterion("begintime not in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeBetween(String value1, String value2) {
            addCriterion("begintime between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotBetween(String value1, String value2) {
            addCriterion("begintime not between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endtime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endtime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(String value) {
            addCriterion("endtime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(String value) {
            addCriterion("endtime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(String value) {
            addCriterion("endtime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(String value) {
            addCriterion("endtime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(String value) {
            addCriterion("endtime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(String value) {
            addCriterion("endtime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLike(String value) {
            addCriterion("endtime like", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotLike(String value) {
            addCriterion("endtime not like", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<String> values) {
            addCriterion("endtime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<String> values) {
            addCriterion("endtime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(String value1, String value2) {
            addCriterion("endtime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(String value1, String value2) {
            addCriterion("endtime not between", value1, value2, "endtime");
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