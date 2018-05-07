package com.zeng.ocs.po;

import java.util.ArrayList;
import java.util.List;
/**
 * 改单条件实体类
 * @author cxs
 *
 */
public class DtChgOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DtChgOrderExample() {
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

        public Criteria andAccdateIsNull() {
            addCriterion("accdate is null");
            return (Criteria) this;
        }

        public Criteria andAccdateIsNotNull() {
            addCriterion("accdate is not null");
            return (Criteria) this;
        }

        public Criteria andAccdateEqualTo(String value) {
            addCriterion("accdate =", value, "accdate");
            return (Criteria) this;
        }

        public Criteria andAccdateNotEqualTo(String value) {
            addCriterion("accdate <>", value, "accdate");
            return (Criteria) this;
        }

        public Criteria andAccdateGreaterThan(String value) {
            addCriterion("accdate >", value, "accdate");
            return (Criteria) this;
        }

        public Criteria andAccdateGreaterThanOrEqualTo(String value) {
            addCriterion("accdate >=", value, "accdate");
            return (Criteria) this;
        }

        public Criteria andAccdateLessThan(String value) {
            addCriterion("accdate <", value, "accdate");
            return (Criteria) this;
        }

        public Criteria andAccdateLessThanOrEqualTo(String value) {
            addCriterion("accdate <=", value, "accdate");
            return (Criteria) this;
        }

        public Criteria andAccdateLike(String value) {
            addCriterion("accdate like", value, "accdate");
            return (Criteria) this;
        }

        public Criteria andAccdateNotLike(String value) {
            addCriterion("accdate not like", value, "accdate");
            return (Criteria) this;
        }

        public Criteria andAccdateIn(List<String> values) {
            addCriterion("accdate in", values, "accdate");
            return (Criteria) this;
        }

        public Criteria andAccdateNotIn(List<String> values) {
            addCriterion("accdate not in", values, "accdate");
            return (Criteria) this;
        }

        public Criteria andAccdateBetween(String value1, String value2) {
            addCriterion("accdate between", value1, value2, "accdate");
            return (Criteria) this;
        }

        public Criteria andAccdateNotBetween(String value1, String value2) {
            addCriterion("accdate not between", value1, value2, "accdate");
            return (Criteria) this;
        }

        public Criteria andModeIsNull() {
            addCriterion("mode is null");
            return (Criteria) this;
        }

        public Criteria andModeIsNotNull() {
            addCriterion("mode is not null");
            return (Criteria) this;
        }

        public Criteria andModeEqualTo(String value) {
            addCriterion("mode =", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotEqualTo(String value) {
            addCriterion("mode <>", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThan(String value) {
            addCriterion("mode >", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThanOrEqualTo(String value) {
            addCriterion("mode >=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThan(String value) {
            addCriterion("mode <", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThanOrEqualTo(String value) {
            addCriterion("mode <=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLike(String value) {
            addCriterion("mode like", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotLike(String value) {
            addCriterion("mode not like", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeIn(List<String> values) {
            addCriterion("mode in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotIn(List<String> values) {
            addCriterion("mode not in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeBetween(String value1, String value2) {
            addCriterion("mode between", value1, value2, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotBetween(String value1, String value2) {
            addCriterion("mode not between", value1, value2, "mode");
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

        public Criteria andAcceptidIsNull() {
            addCriterion("acceptid is null");
            return (Criteria) this;
        }

        public Criteria andAcceptidIsNotNull() {
            addCriterion("acceptid is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptidEqualTo(String value) {
            addCriterion("acceptid =", value, "acceptid");
            return (Criteria) this;
        }

        public Criteria andAcceptidNotEqualTo(String value) {
            addCriterion("acceptid <>", value, "acceptid");
            return (Criteria) this;
        }

        public Criteria andAcceptidGreaterThan(String value) {
            addCriterion("acceptid >", value, "acceptid");
            return (Criteria) this;
        }

        public Criteria andAcceptidGreaterThanOrEqualTo(String value) {
            addCriterion("acceptid >=", value, "acceptid");
            return (Criteria) this;
        }

        public Criteria andAcceptidLessThan(String value) {
            addCriterion("acceptid <", value, "acceptid");
            return (Criteria) this;
        }

        public Criteria andAcceptidLessThanOrEqualTo(String value) {
            addCriterion("acceptid <=", value, "acceptid");
            return (Criteria) this;
        }

        public Criteria andAcceptidLike(String value) {
            addCriterion("acceptid like", value, "acceptid");
            return (Criteria) this;
        }

        public Criteria andAcceptidNotLike(String value) {
            addCriterion("acceptid not like", value, "acceptid");
            return (Criteria) this;
        }

        public Criteria andAcceptidIn(List<String> values) {
            addCriterion("acceptid in", values, "acceptid");
            return (Criteria) this;
        }

        public Criteria andAcceptidNotIn(List<String> values) {
            addCriterion("acceptid not in", values, "acceptid");
            return (Criteria) this;
        }

        public Criteria andAcceptidBetween(String value1, String value2) {
            addCriterion("acceptid between", value1, value2, "acceptid");
            return (Criteria) this;
        }

        public Criteria andAcceptidNotBetween(String value1, String value2) {
            addCriterion("acceptid not between", value1, value2, "acceptid");
            return (Criteria) this;
        }

        public Criteria andCaseorderIsNull() {
            addCriterion("caseorder is null");
            return (Criteria) this;
        }

        public Criteria andCaseorderIsNotNull() {
            addCriterion("caseorder is not null");
            return (Criteria) this;
        }

        public Criteria andCaseorderEqualTo(String value) {
            addCriterion("caseorder =", value, "caseorder");
            return (Criteria) this;
        }

        public Criteria andCaseorderNotEqualTo(String value) {
            addCriterion("caseorder <>", value, "caseorder");
            return (Criteria) this;
        }

        public Criteria andCaseorderGreaterThan(String value) {
            addCriterion("caseorder >", value, "caseorder");
            return (Criteria) this;
        }

        public Criteria andCaseorderGreaterThanOrEqualTo(String value) {
            addCriterion("caseorder >=", value, "caseorder");
            return (Criteria) this;
        }

        public Criteria andCaseorderLessThan(String value) {
            addCriterion("caseorder <", value, "caseorder");
            return (Criteria) this;
        }

        public Criteria andCaseorderLessThanOrEqualTo(String value) {
            addCriterion("caseorder <=", value, "caseorder");
            return (Criteria) this;
        }

        public Criteria andCaseorderLike(String value) {
            addCriterion("caseorder like", value, "caseorder");
            return (Criteria) this;
        }

        public Criteria andCaseorderNotLike(String value) {
            addCriterion("caseorder not like", value, "caseorder");
            return (Criteria) this;
        }

        public Criteria andCaseorderIn(List<String> values) {
            addCriterion("caseorder in", values, "caseorder");
            return (Criteria) this;
        }

        public Criteria andCaseorderNotIn(List<String> values) {
            addCriterion("caseorder not in", values, "caseorder");
            return (Criteria) this;
        }

        public Criteria andCaseorderBetween(String value1, String value2) {
            addCriterion("caseorder between", value1, value2, "caseorder");
            return (Criteria) this;
        }

        public Criteria andCaseorderNotBetween(String value1, String value2) {
            addCriterion("caseorder not between", value1, value2, "caseorder");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNull() {
            addCriterion("typeid is null");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNotNull() {
            addCriterion("typeid is not null");
            return (Criteria) this;
        }

        public Criteria andTypeidEqualTo(String value) {
            addCriterion("typeid =", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotEqualTo(String value) {
            addCriterion("typeid <>", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThan(String value) {
            addCriterion("typeid >", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThanOrEqualTo(String value) {
            addCriterion("typeid >=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThan(String value) {
            addCriterion("typeid <", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThanOrEqualTo(String value) {
            addCriterion("typeid <=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLike(String value) {
            addCriterion("typeid like", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotLike(String value) {
            addCriterion("typeid not like", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidIn(List<String> values) {
            addCriterion("typeid in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotIn(List<String> values) {
            addCriterion("typeid not in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidBetween(String value1, String value2) {
            addCriterion("typeid between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotBetween(String value1, String value2) {
            addCriterion("typeid not between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andDttypeIsNull() {
            addCriterion("dttype is null");
            return (Criteria) this;
        }

        public Criteria andDttypeIsNotNull() {
            addCriterion("dttype is not null");
            return (Criteria) this;
        }

        public Criteria andDttypeEqualTo(String value) {
            addCriterion("dttype =", value, "dttype");
            return (Criteria) this;
        }

        public Criteria andDttypeNotEqualTo(String value) {
            addCriterion("dttype <>", value, "dttype");
            return (Criteria) this;
        }

        public Criteria andDttypeGreaterThan(String value) {
            addCriterion("dttype >", value, "dttype");
            return (Criteria) this;
        }

        public Criteria andDttypeGreaterThanOrEqualTo(String value) {
            addCriterion("dttype >=", value, "dttype");
            return (Criteria) this;
        }

        public Criteria andDttypeLessThan(String value) {
            addCriterion("dttype <", value, "dttype");
            return (Criteria) this;
        }

        public Criteria andDttypeLessThanOrEqualTo(String value) {
            addCriterion("dttype <=", value, "dttype");
            return (Criteria) this;
        }

        public Criteria andDttypeLike(String value) {
            addCriterion("dttype like", value, "dttype");
            return (Criteria) this;
        }

        public Criteria andDttypeNotLike(String value) {
            addCriterion("dttype not like", value, "dttype");
            return (Criteria) this;
        }

        public Criteria andDttypeIn(List<String> values) {
            addCriterion("dttype in", values, "dttype");
            return (Criteria) this;
        }

        public Criteria andDttypeNotIn(List<String> values) {
            addCriterion("dttype not in", values, "dttype");
            return (Criteria) this;
        }

        public Criteria andDttypeBetween(String value1, String value2) {
            addCriterion("dttype between", value1, value2, "dttype");
            return (Criteria) this;
        }

        public Criteria andDttypeNotBetween(String value1, String value2) {
            addCriterion("dttype not between", value1, value2, "dttype");
            return (Criteria) this;
        }

        public Criteria andTypedemandIsNull() {
            addCriterion("typedemand is null");
            return (Criteria) this;
        }

        public Criteria andTypedemandIsNotNull() {
            addCriterion("typedemand is not null");
            return (Criteria) this;
        }

        public Criteria andTypedemandEqualTo(String value) {
            addCriterion("typedemand =", value, "typedemand");
            return (Criteria) this;
        }

        public Criteria andTypedemandNotEqualTo(String value) {
            addCriterion("typedemand <>", value, "typedemand");
            return (Criteria) this;
        }

        public Criteria andTypedemandGreaterThan(String value) {
            addCriterion("typedemand >", value, "typedemand");
            return (Criteria) this;
        }

        public Criteria andTypedemandGreaterThanOrEqualTo(String value) {
            addCriterion("typedemand >=", value, "typedemand");
            return (Criteria) this;
        }

        public Criteria andTypedemandLessThan(String value) {
            addCriterion("typedemand <", value, "typedemand");
            return (Criteria) this;
        }

        public Criteria andTypedemandLessThanOrEqualTo(String value) {
            addCriterion("typedemand <=", value, "typedemand");
            return (Criteria) this;
        }

        public Criteria andTypedemandLike(String value) {
            addCriterion("typedemand like", value, "typedemand");
            return (Criteria) this;
        }

        public Criteria andTypedemandNotLike(String value) {
            addCriterion("typedemand not like", value, "typedemand");
            return (Criteria) this;
        }

        public Criteria andTypedemandIn(List<String> values) {
            addCriterion("typedemand in", values, "typedemand");
            return (Criteria) this;
        }

        public Criteria andTypedemandNotIn(List<String> values) {
            addCriterion("typedemand not in", values, "typedemand");
            return (Criteria) this;
        }

        public Criteria andTypedemandBetween(String value1, String value2) {
            addCriterion("typedemand between", value1, value2, "typedemand");
            return (Criteria) this;
        }

        public Criteria andTypedemandNotBetween(String value1, String value2) {
            addCriterion("typedemand not between", value1, value2, "typedemand");
            return (Criteria) this;
        }

        public Criteria andDtdemandIsNull() {
            addCriterion("dtdemand is null");
            return (Criteria) this;
        }

        public Criteria andDtdemandIsNotNull() {
            addCriterion("dtdemand is not null");
            return (Criteria) this;
        }

        public Criteria andDtdemandEqualTo(String value) {
            addCriterion("dtdemand =", value, "dtdemand");
            return (Criteria) this;
        }

        public Criteria andDtdemandNotEqualTo(String value) {
            addCriterion("dtdemand <>", value, "dtdemand");
            return (Criteria) this;
        }

        public Criteria andDtdemandGreaterThan(String value) {
            addCriterion("dtdemand >", value, "dtdemand");
            return (Criteria) this;
        }

        public Criteria andDtdemandGreaterThanOrEqualTo(String value) {
            addCriterion("dtdemand >=", value, "dtdemand");
            return (Criteria) this;
        }

        public Criteria andDtdemandLessThan(String value) {
            addCriterion("dtdemand <", value, "dtdemand");
            return (Criteria) this;
        }

        public Criteria andDtdemandLessThanOrEqualTo(String value) {
            addCriterion("dtdemand <=", value, "dtdemand");
            return (Criteria) this;
        }

        public Criteria andDtdemandLike(String value) {
            addCriterion("dtdemand like", value, "dtdemand");
            return (Criteria) this;
        }

        public Criteria andDtdemandNotLike(String value) {
            addCriterion("dtdemand not like", value, "dtdemand");
            return (Criteria) this;
        }

        public Criteria andDtdemandIn(List<String> values) {
            addCriterion("dtdemand in", values, "dtdemand");
            return (Criteria) this;
        }

        public Criteria andDtdemandNotIn(List<String> values) {
            addCriterion("dtdemand not in", values, "dtdemand");
            return (Criteria) this;
        }

        public Criteria andDtdemandBetween(String value1, String value2) {
            addCriterion("dtdemand between", value1, value2, "dtdemand");
            return (Criteria) this;
        }

        public Criteria andDtdemandNotBetween(String value1, String value2) {
            addCriterion("dtdemand not between", value1, value2, "dtdemand");
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