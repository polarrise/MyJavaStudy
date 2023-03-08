package com.jinbiao.cloud.mbg.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaseCenterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CaseCenterExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCaseTypeIsNull() {
            addCriterion("case_type is null");
            return (Criteria) this;
        }

        public Criteria andCaseTypeIsNotNull() {
            addCriterion("case_type is not null");
            return (Criteria) this;
        }

        public Criteria andCaseTypeEqualTo(String value) {
            addCriterion("case_type =", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNotEqualTo(String value) {
            addCriterion("case_type <>", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeGreaterThan(String value) {
            addCriterion("case_type >", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("case_type >=", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeLessThan(String value) {
            addCriterion("case_type <", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeLessThanOrEqualTo(String value) {
            addCriterion("case_type <=", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeLike(String value) {
            addCriterion("case_type like", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNotLike(String value) {
            addCriterion("case_type not like", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeIn(List<String> values) {
            addCriterion("case_type in", values, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNotIn(List<String> values) {
            addCriterion("case_type not in", values, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeBetween(String value1, String value2) {
            addCriterion("case_type between", value1, value2, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNotBetween(String value1, String value2) {
            addCriterion("case_type not between", value1, value2, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseStageIsNull() {
            addCriterion("case_stage is null");
            return (Criteria) this;
        }

        public Criteria andCaseStageIsNotNull() {
            addCriterion("case_stage is not null");
            return (Criteria) this;
        }

        public Criteria andCaseStageEqualTo(String value) {
            addCriterion("case_stage =", value, "caseStage");
            return (Criteria) this;
        }

        public Criteria andCaseStageNotEqualTo(String value) {
            addCriterion("case_stage <>", value, "caseStage");
            return (Criteria) this;
        }

        public Criteria andCaseStageGreaterThan(String value) {
            addCriterion("case_stage >", value, "caseStage");
            return (Criteria) this;
        }

        public Criteria andCaseStageGreaterThanOrEqualTo(String value) {
            addCriterion("case_stage >=", value, "caseStage");
            return (Criteria) this;
        }

        public Criteria andCaseStageLessThan(String value) {
            addCriterion("case_stage <", value, "caseStage");
            return (Criteria) this;
        }

        public Criteria andCaseStageLessThanOrEqualTo(String value) {
            addCriterion("case_stage <=", value, "caseStage");
            return (Criteria) this;
        }

        public Criteria andCaseStageLike(String value) {
            addCriterion("case_stage like", value, "caseStage");
            return (Criteria) this;
        }

        public Criteria andCaseStageNotLike(String value) {
            addCriterion("case_stage not like", value, "caseStage");
            return (Criteria) this;
        }

        public Criteria andCaseStageIn(List<String> values) {
            addCriterion("case_stage in", values, "caseStage");
            return (Criteria) this;
        }

        public Criteria andCaseStageNotIn(List<String> values) {
            addCriterion("case_stage not in", values, "caseStage");
            return (Criteria) this;
        }

        public Criteria andCaseStageBetween(String value1, String value2) {
            addCriterion("case_stage between", value1, value2, "caseStage");
            return (Criteria) this;
        }

        public Criteria andCaseStageNotBetween(String value1, String value2) {
            addCriterion("case_stage not between", value1, value2, "caseStage");
            return (Criteria) this;
        }

        public Criteria andRemarkValueIsNull() {
            addCriterion("remark_value is null");
            return (Criteria) this;
        }

        public Criteria andRemarkValueIsNotNull() {
            addCriterion("remark_value is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkValueEqualTo(String value) {
            addCriterion("remark_value =", value, "remarkValue");
            return (Criteria) this;
        }

        public Criteria andRemarkValueNotEqualTo(String value) {
            addCriterion("remark_value <>", value, "remarkValue");
            return (Criteria) this;
        }

        public Criteria andRemarkValueGreaterThan(String value) {
            addCriterion("remark_value >", value, "remarkValue");
            return (Criteria) this;
        }

        public Criteria andRemarkValueGreaterThanOrEqualTo(String value) {
            addCriterion("remark_value >=", value, "remarkValue");
            return (Criteria) this;
        }

        public Criteria andRemarkValueLessThan(String value) {
            addCriterion("remark_value <", value, "remarkValue");
            return (Criteria) this;
        }

        public Criteria andRemarkValueLessThanOrEqualTo(String value) {
            addCriterion("remark_value <=", value, "remarkValue");
            return (Criteria) this;
        }

        public Criteria andRemarkValueLike(String value) {
            addCriterion("remark_value like", value, "remarkValue");
            return (Criteria) this;
        }

        public Criteria andRemarkValueNotLike(String value) {
            addCriterion("remark_value not like", value, "remarkValue");
            return (Criteria) this;
        }

        public Criteria andRemarkValueIn(List<String> values) {
            addCriterion("remark_value in", values, "remarkValue");
            return (Criteria) this;
        }

        public Criteria andRemarkValueNotIn(List<String> values) {
            addCriterion("remark_value not in", values, "remarkValue");
            return (Criteria) this;
        }

        public Criteria andRemarkValueBetween(String value1, String value2) {
            addCriterion("remark_value between", value1, value2, "remarkValue");
            return (Criteria) this;
        }

        public Criteria andRemarkValueNotBetween(String value1, String value2) {
            addCriterion("remark_value not between", value1, value2, "remarkValue");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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

        public Criteria andServiceTypeIsNull() {
            addCriterion("service_type is null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNotNull() {
            addCriterion("service_type is not null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeEqualTo(Long value) {
            addCriterion("service_type =", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotEqualTo(Long value) {
            addCriterion("service_type <>", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThan(Long value) {
            addCriterion("service_type >", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("service_type >=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThan(Long value) {
            addCriterion("service_type <", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThanOrEqualTo(Long value) {
            addCriterion("service_type <=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIn(List<Long> values) {
            addCriterion("service_type in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotIn(List<Long> values) {
            addCriterion("service_type not in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeBetween(Long value1, Long value2) {
            addCriterion("service_type between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotBetween(Long value1, Long value2) {
            addCriterion("service_type not between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andUserDemandTypeIsNull() {
            addCriterion("user_demand_type is null");
            return (Criteria) this;
        }

        public Criteria andUserDemandTypeIsNotNull() {
            addCriterion("user_demand_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserDemandTypeEqualTo(Byte value) {
            addCriterion("user_demand_type =", value, "userDemandType");
            return (Criteria) this;
        }

        public Criteria andUserDemandTypeNotEqualTo(Byte value) {
            addCriterion("user_demand_type <>", value, "userDemandType");
            return (Criteria) this;
        }

        public Criteria andUserDemandTypeGreaterThan(Byte value) {
            addCriterion("user_demand_type >", value, "userDemandType");
            return (Criteria) this;
        }

        public Criteria andUserDemandTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("user_demand_type >=", value, "userDemandType");
            return (Criteria) this;
        }

        public Criteria andUserDemandTypeLessThan(Byte value) {
            addCriterion("user_demand_type <", value, "userDemandType");
            return (Criteria) this;
        }

        public Criteria andUserDemandTypeLessThanOrEqualTo(Byte value) {
            addCriterion("user_demand_type <=", value, "userDemandType");
            return (Criteria) this;
        }

        public Criteria andUserDemandTypeIn(List<Byte> values) {
            addCriterion("user_demand_type in", values, "userDemandType");
            return (Criteria) this;
        }

        public Criteria andUserDemandTypeNotIn(List<Byte> values) {
            addCriterion("user_demand_type not in", values, "userDemandType");
            return (Criteria) this;
        }

        public Criteria andUserDemandTypeBetween(Byte value1, Byte value2) {
            addCriterion("user_demand_type between", value1, value2, "userDemandType");
            return (Criteria) this;
        }

        public Criteria andUserDemandTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("user_demand_type not between", value1, value2, "userDemandType");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOriginIsNull() {
            addCriterion("origin is null");
            return (Criteria) this;
        }

        public Criteria andOriginIsNotNull() {
            addCriterion("origin is not null");
            return (Criteria) this;
        }

        public Criteria andOriginEqualTo(Byte value) {
            addCriterion("origin =", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotEqualTo(Byte value) {
            addCriterion("origin <>", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginGreaterThan(Byte value) {
            addCriterion("origin >", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginGreaterThanOrEqualTo(Byte value) {
            addCriterion("origin >=", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginLessThan(Byte value) {
            addCriterion("origin <", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginLessThanOrEqualTo(Byte value) {
            addCriterion("origin <=", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginIn(List<Byte> values) {
            addCriterion("origin in", values, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotIn(List<Byte> values) {
            addCriterion("origin not in", values, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginBetween(Byte value1, Byte value2) {
            addCriterion("origin between", value1, value2, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotBetween(Byte value1, Byte value2) {
            addCriterion("origin not between", value1, value2, "origin");
            return (Criteria) this;
        }

        public Criteria andSubmitUseridIsNull() {
            addCriterion("submit_userid is null");
            return (Criteria) this;
        }

        public Criteria andSubmitUseridIsNotNull() {
            addCriterion("submit_userid is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitUseridEqualTo(Long value) {
            addCriterion("submit_userid =", value, "submitUserid");
            return (Criteria) this;
        }

        public Criteria andSubmitUseridNotEqualTo(Long value) {
            addCriterion("submit_userid <>", value, "submitUserid");
            return (Criteria) this;
        }

        public Criteria andSubmitUseridGreaterThan(Long value) {
            addCriterion("submit_userid >", value, "submitUserid");
            return (Criteria) this;
        }

        public Criteria andSubmitUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("submit_userid >=", value, "submitUserid");
            return (Criteria) this;
        }

        public Criteria andSubmitUseridLessThan(Long value) {
            addCriterion("submit_userid <", value, "submitUserid");
            return (Criteria) this;
        }

        public Criteria andSubmitUseridLessThanOrEqualTo(Long value) {
            addCriterion("submit_userid <=", value, "submitUserid");
            return (Criteria) this;
        }

        public Criteria andSubmitUseridIn(List<Long> values) {
            addCriterion("submit_userid in", values, "submitUserid");
            return (Criteria) this;
        }

        public Criteria andSubmitUseridNotIn(List<Long> values) {
            addCriterion("submit_userid not in", values, "submitUserid");
            return (Criteria) this;
        }

        public Criteria andSubmitUseridBetween(Long value1, Long value2) {
            addCriterion("submit_userid between", value1, value2, "submitUserid");
            return (Criteria) this;
        }

        public Criteria andSubmitUseridNotBetween(Long value1, Long value2) {
            addCriterion("submit_userid not between", value1, value2, "submitUserid");
            return (Criteria) this;
        }

        public Criteria andCaseSessionIdIsNull() {
            addCriterion("case_session_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseSessionIdIsNotNull() {
            addCriterion("case_session_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseSessionIdEqualTo(Long value) {
            addCriterion("case_session_id =", value, "caseSessionId");
            return (Criteria) this;
        }

        public Criteria andCaseSessionIdNotEqualTo(Long value) {
            addCriterion("case_session_id <>", value, "caseSessionId");
            return (Criteria) this;
        }

        public Criteria andCaseSessionIdGreaterThan(Long value) {
            addCriterion("case_session_id >", value, "caseSessionId");
            return (Criteria) this;
        }

        public Criteria andCaseSessionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("case_session_id >=", value, "caseSessionId");
            return (Criteria) this;
        }

        public Criteria andCaseSessionIdLessThan(Long value) {
            addCriterion("case_session_id <", value, "caseSessionId");
            return (Criteria) this;
        }

        public Criteria andCaseSessionIdLessThanOrEqualTo(Long value) {
            addCriterion("case_session_id <=", value, "caseSessionId");
            return (Criteria) this;
        }

        public Criteria andCaseSessionIdIn(List<Long> values) {
            addCriterion("case_session_id in", values, "caseSessionId");
            return (Criteria) this;
        }

        public Criteria andCaseSessionIdNotIn(List<Long> values) {
            addCriterion("case_session_id not in", values, "caseSessionId");
            return (Criteria) this;
        }

        public Criteria andCaseSessionIdBetween(Long value1, Long value2) {
            addCriterion("case_session_id between", value1, value2, "caseSessionId");
            return (Criteria) this;
        }

        public Criteria andCaseSessionIdNotBetween(Long value1, Long value2) {
            addCriterion("case_session_id not between", value1, value2, "caseSessionId");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andLawFirmIdsIsNull() {
            addCriterion("law_firm_ids is null");
            return (Criteria) this;
        }

        public Criteria andLawFirmIdsIsNotNull() {
            addCriterion("law_firm_ids is not null");
            return (Criteria) this;
        }

        public Criteria andLawFirmIdsEqualTo(String value) {
            addCriterion("law_firm_ids =", value, "lawFirmIds");
            return (Criteria) this;
        }

        public Criteria andLawFirmIdsNotEqualTo(String value) {
            addCriterion("law_firm_ids <>", value, "lawFirmIds");
            return (Criteria) this;
        }

        public Criteria andLawFirmIdsGreaterThan(String value) {
            addCriterion("law_firm_ids >", value, "lawFirmIds");
            return (Criteria) this;
        }

        public Criteria andLawFirmIdsGreaterThanOrEqualTo(String value) {
            addCriterion("law_firm_ids >=", value, "lawFirmIds");
            return (Criteria) this;
        }

        public Criteria andLawFirmIdsLessThan(String value) {
            addCriterion("law_firm_ids <", value, "lawFirmIds");
            return (Criteria) this;
        }

        public Criteria andLawFirmIdsLessThanOrEqualTo(String value) {
            addCriterion("law_firm_ids <=", value, "lawFirmIds");
            return (Criteria) this;
        }

        public Criteria andLawFirmIdsLike(String value) {
            addCriterion("law_firm_ids like", value, "lawFirmIds");
            return (Criteria) this;
        }

        public Criteria andLawFirmIdsNotLike(String value) {
            addCriterion("law_firm_ids not like", value, "lawFirmIds");
            return (Criteria) this;
        }

        public Criteria andLawFirmIdsIn(List<String> values) {
            addCriterion("law_firm_ids in", values, "lawFirmIds");
            return (Criteria) this;
        }

        public Criteria andLawFirmIdsNotIn(List<String> values) {
            addCriterion("law_firm_ids not in", values, "lawFirmIds");
            return (Criteria) this;
        }

        public Criteria andLawFirmIdsBetween(String value1, String value2) {
            addCriterion("law_firm_ids between", value1, value2, "lawFirmIds");
            return (Criteria) this;
        }

        public Criteria andLawFirmIdsNotBetween(String value1, String value2) {
            addCriterion("law_firm_ids not between", value1, value2, "lawFirmIds");
            return (Criteria) this;
        }

        public Criteria andFollowerIdsIsNull() {
            addCriterion("follower_ids is null");
            return (Criteria) this;
        }

        public Criteria andFollowerIdsIsNotNull() {
            addCriterion("follower_ids is not null");
            return (Criteria) this;
        }

        public Criteria andFollowerIdsEqualTo(String value) {
            addCriterion("follower_ids =", value, "followerIds");
            return (Criteria) this;
        }

        public Criteria andFollowerIdsNotEqualTo(String value) {
            addCriterion("follower_ids <>", value, "followerIds");
            return (Criteria) this;
        }

        public Criteria andFollowerIdsGreaterThan(String value) {
            addCriterion("follower_ids >", value, "followerIds");
            return (Criteria) this;
        }

        public Criteria andFollowerIdsGreaterThanOrEqualTo(String value) {
            addCriterion("follower_ids >=", value, "followerIds");
            return (Criteria) this;
        }

        public Criteria andFollowerIdsLessThan(String value) {
            addCriterion("follower_ids <", value, "followerIds");
            return (Criteria) this;
        }

        public Criteria andFollowerIdsLessThanOrEqualTo(String value) {
            addCriterion("follower_ids <=", value, "followerIds");
            return (Criteria) this;
        }

        public Criteria andFollowerIdsLike(String value) {
            addCriterion("follower_ids like", value, "followerIds");
            return (Criteria) this;
        }

        public Criteria andFollowerIdsNotLike(String value) {
            addCriterion("follower_ids not like", value, "followerIds");
            return (Criteria) this;
        }

        public Criteria andFollowerIdsIn(List<String> values) {
            addCriterion("follower_ids in", values, "followerIds");
            return (Criteria) this;
        }

        public Criteria andFollowerIdsNotIn(List<String> values) {
            addCriterion("follower_ids not in", values, "followerIds");
            return (Criteria) this;
        }

        public Criteria andFollowerIdsBetween(String value1, String value2) {
            addCriterion("follower_ids between", value1, value2, "followerIds");
            return (Criteria) this;
        }

        public Criteria andFollowerIdsNotBetween(String value1, String value2) {
            addCriterion("follower_ids not between", value1, value2, "followerIds");
            return (Criteria) this;
        }

        public Criteria andHasMarkIsNull() {
            addCriterion("has_mark is null");
            return (Criteria) this;
        }

        public Criteria andHasMarkIsNotNull() {
            addCriterion("has_mark is not null");
            return (Criteria) this;
        }

        public Criteria andHasMarkEqualTo(Integer value) {
            addCriterion("has_mark =", value, "hasMark");
            return (Criteria) this;
        }

        public Criteria andHasMarkNotEqualTo(Integer value) {
            addCriterion("has_mark <>", value, "hasMark");
            return (Criteria) this;
        }

        public Criteria andHasMarkGreaterThan(Integer value) {
            addCriterion("has_mark >", value, "hasMark");
            return (Criteria) this;
        }

        public Criteria andHasMarkGreaterThanOrEqualTo(Integer value) {
            addCriterion("has_mark >=", value, "hasMark");
            return (Criteria) this;
        }

        public Criteria andHasMarkLessThan(Integer value) {
            addCriterion("has_mark <", value, "hasMark");
            return (Criteria) this;
        }

        public Criteria andHasMarkLessThanOrEqualTo(Integer value) {
            addCriterion("has_mark <=", value, "hasMark");
            return (Criteria) this;
        }

        public Criteria andHasMarkIn(List<Integer> values) {
            addCriterion("has_mark in", values, "hasMark");
            return (Criteria) this;
        }

        public Criteria andHasMarkNotIn(List<Integer> values) {
            addCriterion("has_mark not in", values, "hasMark");
            return (Criteria) this;
        }

        public Criteria andHasMarkBetween(Integer value1, Integer value2) {
            addCriterion("has_mark between", value1, value2, "hasMark");
            return (Criteria) this;
        }

        public Criteria andHasMarkNotBetween(Integer value1, Integer value2) {
            addCriterion("has_mark not between", value1, value2, "hasMark");
            return (Criteria) this;
        }

        public Criteria andPhoneUuidIsNull() {
            addCriterion("phone_uuid is null");
            return (Criteria) this;
        }

        public Criteria andPhoneUuidIsNotNull() {
            addCriterion("phone_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneUuidEqualTo(String value) {
            addCriterion("phone_uuid =", value, "phoneUuid");
            return (Criteria) this;
        }

        public Criteria andPhoneUuidNotEqualTo(String value) {
            addCriterion("phone_uuid <>", value, "phoneUuid");
            return (Criteria) this;
        }

        public Criteria andPhoneUuidGreaterThan(String value) {
            addCriterion("phone_uuid >", value, "phoneUuid");
            return (Criteria) this;
        }

        public Criteria andPhoneUuidGreaterThanOrEqualTo(String value) {
            addCriterion("phone_uuid >=", value, "phoneUuid");
            return (Criteria) this;
        }

        public Criteria andPhoneUuidLessThan(String value) {
            addCriterion("phone_uuid <", value, "phoneUuid");
            return (Criteria) this;
        }

        public Criteria andPhoneUuidLessThanOrEqualTo(String value) {
            addCriterion("phone_uuid <=", value, "phoneUuid");
            return (Criteria) this;
        }

        public Criteria andPhoneUuidLike(String value) {
            addCriterion("phone_uuid like", value, "phoneUuid");
            return (Criteria) this;
        }

        public Criteria andPhoneUuidNotLike(String value) {
            addCriterion("phone_uuid not like", value, "phoneUuid");
            return (Criteria) this;
        }

        public Criteria andPhoneUuidIn(List<String> values) {
            addCriterion("phone_uuid in", values, "phoneUuid");
            return (Criteria) this;
        }

        public Criteria andPhoneUuidNotIn(List<String> values) {
            addCriterion("phone_uuid not in", values, "phoneUuid");
            return (Criteria) this;
        }

        public Criteria andPhoneUuidBetween(String value1, String value2) {
            addCriterion("phone_uuid between", value1, value2, "phoneUuid");
            return (Criteria) this;
        }

        public Criteria andPhoneUuidNotBetween(String value1, String value2) {
            addCriterion("phone_uuid not between", value1, value2, "phoneUuid");
            return (Criteria) this;
        }

        public Criteria andHelpIntentionIsNull() {
            addCriterion("help_intention is null");
            return (Criteria) this;
        }

        public Criteria andHelpIntentionIsNotNull() {
            addCriterion("help_intention is not null");
            return (Criteria) this;
        }

        public Criteria andHelpIntentionEqualTo(Byte value) {
            addCriterion("help_intention =", value, "helpIntention");
            return (Criteria) this;
        }

        public Criteria andHelpIntentionNotEqualTo(Byte value) {
            addCriterion("help_intention <>", value, "helpIntention");
            return (Criteria) this;
        }

        public Criteria andHelpIntentionGreaterThan(Byte value) {
            addCriterion("help_intention >", value, "helpIntention");
            return (Criteria) this;
        }

        public Criteria andHelpIntentionGreaterThanOrEqualTo(Byte value) {
            addCriterion("help_intention >=", value, "helpIntention");
            return (Criteria) this;
        }

        public Criteria andHelpIntentionLessThan(Byte value) {
            addCriterion("help_intention <", value, "helpIntention");
            return (Criteria) this;
        }

        public Criteria andHelpIntentionLessThanOrEqualTo(Byte value) {
            addCriterion("help_intention <=", value, "helpIntention");
            return (Criteria) this;
        }

        public Criteria andHelpIntentionIn(List<Byte> values) {
            addCriterion("help_intention in", values, "helpIntention");
            return (Criteria) this;
        }

        public Criteria andHelpIntentionNotIn(List<Byte> values) {
            addCriterion("help_intention not in", values, "helpIntention");
            return (Criteria) this;
        }

        public Criteria andHelpIntentionBetween(Byte value1, Byte value2) {
            addCriterion("help_intention between", value1, value2, "helpIntention");
            return (Criteria) this;
        }

        public Criteria andHelpIntentionNotBetween(Byte value1, Byte value2) {
            addCriterion("help_intention not between", value1, value2, "helpIntention");
            return (Criteria) this;
        }

        public Criteria andFoundLawyerFlagIsNull() {
            addCriterion("found_lawyer_flag is null");
            return (Criteria) this;
        }

        public Criteria andFoundLawyerFlagIsNotNull() {
            addCriterion("found_lawyer_flag is not null");
            return (Criteria) this;
        }

        public Criteria andFoundLawyerFlagEqualTo(Byte value) {
            addCriterion("found_lawyer_flag =", value, "foundLawyerFlag");
            return (Criteria) this;
        }

        public Criteria andFoundLawyerFlagNotEqualTo(Byte value) {
            addCriterion("found_lawyer_flag <>", value, "foundLawyerFlag");
            return (Criteria) this;
        }

        public Criteria andFoundLawyerFlagGreaterThan(Byte value) {
            addCriterion("found_lawyer_flag >", value, "foundLawyerFlag");
            return (Criteria) this;
        }

        public Criteria andFoundLawyerFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("found_lawyer_flag >=", value, "foundLawyerFlag");
            return (Criteria) this;
        }

        public Criteria andFoundLawyerFlagLessThan(Byte value) {
            addCriterion("found_lawyer_flag <", value, "foundLawyerFlag");
            return (Criteria) this;
        }

        public Criteria andFoundLawyerFlagLessThanOrEqualTo(Byte value) {
            addCriterion("found_lawyer_flag <=", value, "foundLawyerFlag");
            return (Criteria) this;
        }

        public Criteria andFoundLawyerFlagIn(List<Byte> values) {
            addCriterion("found_lawyer_flag in", values, "foundLawyerFlag");
            return (Criteria) this;
        }

        public Criteria andFoundLawyerFlagNotIn(List<Byte> values) {
            addCriterion("found_lawyer_flag not in", values, "foundLawyerFlag");
            return (Criteria) this;
        }

        public Criteria andFoundLawyerFlagBetween(Byte value1, Byte value2) {
            addCriterion("found_lawyer_flag between", value1, value2, "foundLawyerFlag");
            return (Criteria) this;
        }

        public Criteria andFoundLawyerFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("found_lawyer_flag not between", value1, value2, "foundLawyerFlag");
            return (Criteria) this;
        }

        public Criteria andUnresolvedReasonIsNull() {
            addCriterion("unresolved_reason is null");
            return (Criteria) this;
        }

        public Criteria andUnresolvedReasonIsNotNull() {
            addCriterion("unresolved_reason is not null");
            return (Criteria) this;
        }

        public Criteria andUnresolvedReasonEqualTo(String value) {
            addCriterion("unresolved_reason =", value, "unresolvedReason");
            return (Criteria) this;
        }

        public Criteria andUnresolvedReasonNotEqualTo(String value) {
            addCriterion("unresolved_reason <>", value, "unresolvedReason");
            return (Criteria) this;
        }

        public Criteria andUnresolvedReasonGreaterThan(String value) {
            addCriterion("unresolved_reason >", value, "unresolvedReason");
            return (Criteria) this;
        }

        public Criteria andUnresolvedReasonGreaterThanOrEqualTo(String value) {
            addCriterion("unresolved_reason >=", value, "unresolvedReason");
            return (Criteria) this;
        }

        public Criteria andUnresolvedReasonLessThan(String value) {
            addCriterion("unresolved_reason <", value, "unresolvedReason");
            return (Criteria) this;
        }

        public Criteria andUnresolvedReasonLessThanOrEqualTo(String value) {
            addCriterion("unresolved_reason <=", value, "unresolvedReason");
            return (Criteria) this;
        }

        public Criteria andUnresolvedReasonLike(String value) {
            addCriterion("unresolved_reason like", value, "unresolvedReason");
            return (Criteria) this;
        }

        public Criteria andUnresolvedReasonNotLike(String value) {
            addCriterion("unresolved_reason not like", value, "unresolvedReason");
            return (Criteria) this;
        }

        public Criteria andUnresolvedReasonIn(List<String> values) {
            addCriterion("unresolved_reason in", values, "unresolvedReason");
            return (Criteria) this;
        }

        public Criteria andUnresolvedReasonNotIn(List<String> values) {
            addCriterion("unresolved_reason not in", values, "unresolvedReason");
            return (Criteria) this;
        }

        public Criteria andUnresolvedReasonBetween(String value1, String value2) {
            addCriterion("unresolved_reason between", value1, value2, "unresolvedReason");
            return (Criteria) this;
        }

        public Criteria andUnresolvedReasonNotBetween(String value1, String value2) {
            addCriterion("unresolved_reason not between", value1, value2, "unresolvedReason");
            return (Criteria) this;
        }

        public Criteria andPartyLocationIsNull() {
            addCriterion("party_location is null");
            return (Criteria) this;
        }

        public Criteria andPartyLocationIsNotNull() {
            addCriterion("party_location is not null");
            return (Criteria) this;
        }

        public Criteria andPartyLocationEqualTo(String value) {
            addCriterion("party_location =", value, "partyLocation");
            return (Criteria) this;
        }

        public Criteria andPartyLocationNotEqualTo(String value) {
            addCriterion("party_location <>", value, "partyLocation");
            return (Criteria) this;
        }

        public Criteria andPartyLocationGreaterThan(String value) {
            addCriterion("party_location >", value, "partyLocation");
            return (Criteria) this;
        }

        public Criteria andPartyLocationGreaterThanOrEqualTo(String value) {
            addCriterion("party_location >=", value, "partyLocation");
            return (Criteria) this;
        }

        public Criteria andPartyLocationLessThan(String value) {
            addCriterion("party_location <", value, "partyLocation");
            return (Criteria) this;
        }

        public Criteria andPartyLocationLessThanOrEqualTo(String value) {
            addCriterion("party_location <=", value, "partyLocation");
            return (Criteria) this;
        }

        public Criteria andPartyLocationLike(String value) {
            addCriterion("party_location like", value, "partyLocation");
            return (Criteria) this;
        }

        public Criteria andPartyLocationNotLike(String value) {
            addCriterion("party_location not like", value, "partyLocation");
            return (Criteria) this;
        }

        public Criteria andPartyLocationIn(List<String> values) {
            addCriterion("party_location in", values, "partyLocation");
            return (Criteria) this;
        }

        public Criteria andPartyLocationNotIn(List<String> values) {
            addCriterion("party_location not in", values, "partyLocation");
            return (Criteria) this;
        }

        public Criteria andPartyLocationBetween(String value1, String value2) {
            addCriterion("party_location between", value1, value2, "partyLocation");
            return (Criteria) this;
        }

        public Criteria andPartyLocationNotBetween(String value1, String value2) {
            addCriterion("party_location not between", value1, value2, "partyLocation");
            return (Criteria) this;
        }

        public Criteria andProsecutorLocationIsNull() {
            addCriterion("prosecutor_location is null");
            return (Criteria) this;
        }

        public Criteria andProsecutorLocationIsNotNull() {
            addCriterion("prosecutor_location is not null");
            return (Criteria) this;
        }

        public Criteria andProsecutorLocationEqualTo(String value) {
            addCriterion("prosecutor_location =", value, "prosecutorLocation");
            return (Criteria) this;
        }

        public Criteria andProsecutorLocationNotEqualTo(String value) {
            addCriterion("prosecutor_location <>", value, "prosecutorLocation");
            return (Criteria) this;
        }

        public Criteria andProsecutorLocationGreaterThan(String value) {
            addCriterion("prosecutor_location >", value, "prosecutorLocation");
            return (Criteria) this;
        }

        public Criteria andProsecutorLocationGreaterThanOrEqualTo(String value) {
            addCriterion("prosecutor_location >=", value, "prosecutorLocation");
            return (Criteria) this;
        }

        public Criteria andProsecutorLocationLessThan(String value) {
            addCriterion("prosecutor_location <", value, "prosecutorLocation");
            return (Criteria) this;
        }

        public Criteria andProsecutorLocationLessThanOrEqualTo(String value) {
            addCriterion("prosecutor_location <=", value, "prosecutorLocation");
            return (Criteria) this;
        }

        public Criteria andProsecutorLocationLike(String value) {
            addCriterion("prosecutor_location like", value, "prosecutorLocation");
            return (Criteria) this;
        }

        public Criteria andProsecutorLocationNotLike(String value) {
            addCriterion("prosecutor_location not like", value, "prosecutorLocation");
            return (Criteria) this;
        }

        public Criteria andProsecutorLocationIn(List<String> values) {
            addCriterion("prosecutor_location in", values, "prosecutorLocation");
            return (Criteria) this;
        }

        public Criteria andProsecutorLocationNotIn(List<String> values) {
            addCriterion("prosecutor_location not in", values, "prosecutorLocation");
            return (Criteria) this;
        }

        public Criteria andProsecutorLocationBetween(String value1, String value2) {
            addCriterion("prosecutor_location between", value1, value2, "prosecutorLocation");
            return (Criteria) this;
        }

        public Criteria andProsecutorLocationNotBetween(String value1, String value2) {
            addCriterion("prosecutor_location not between", value1, value2, "prosecutorLocation");
            return (Criteria) this;
        }

        public Criteria andHasLegalAffairsFlagIsNull() {
            addCriterion("has_legal_affairs_flag is null");
            return (Criteria) this;
        }

        public Criteria andHasLegalAffairsFlagIsNotNull() {
            addCriterion("has_legal_affairs_flag is not null");
            return (Criteria) this;
        }

        public Criteria andHasLegalAffairsFlagEqualTo(Byte value) {
            addCriterion("has_legal_affairs_flag =", value, "hasLegalAffairsFlag");
            return (Criteria) this;
        }

        public Criteria andHasLegalAffairsFlagNotEqualTo(Byte value) {
            addCriterion("has_legal_affairs_flag <>", value, "hasLegalAffairsFlag");
            return (Criteria) this;
        }

        public Criteria andHasLegalAffairsFlagGreaterThan(Byte value) {
            addCriterion("has_legal_affairs_flag >", value, "hasLegalAffairsFlag");
            return (Criteria) this;
        }

        public Criteria andHasLegalAffairsFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("has_legal_affairs_flag >=", value, "hasLegalAffairsFlag");
            return (Criteria) this;
        }

        public Criteria andHasLegalAffairsFlagLessThan(Byte value) {
            addCriterion("has_legal_affairs_flag <", value, "hasLegalAffairsFlag");
            return (Criteria) this;
        }

        public Criteria andHasLegalAffairsFlagLessThanOrEqualTo(Byte value) {
            addCriterion("has_legal_affairs_flag <=", value, "hasLegalAffairsFlag");
            return (Criteria) this;
        }

        public Criteria andHasLegalAffairsFlagIn(List<Byte> values) {
            addCriterion("has_legal_affairs_flag in", values, "hasLegalAffairsFlag");
            return (Criteria) this;
        }

        public Criteria andHasLegalAffairsFlagNotIn(List<Byte> values) {
            addCriterion("has_legal_affairs_flag not in", values, "hasLegalAffairsFlag");
            return (Criteria) this;
        }

        public Criteria andHasLegalAffairsFlagBetween(Byte value1, Byte value2) {
            addCriterion("has_legal_affairs_flag between", value1, value2, "hasLegalAffairsFlag");
            return (Criteria) this;
        }

        public Criteria andHasLegalAffairsFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("has_legal_affairs_flag not between", value1, value2, "hasLegalAffairsFlag");
            return (Criteria) this;
        }

        public Criteria andCaseLevelIsNull() {
            addCriterion("case_level is null");
            return (Criteria) this;
        }

        public Criteria andCaseLevelIsNotNull() {
            addCriterion("case_level is not null");
            return (Criteria) this;
        }

        public Criteria andCaseLevelEqualTo(String value) {
            addCriterion("case_level =", value, "caseLevel");
            return (Criteria) this;
        }

        public Criteria andCaseLevelNotEqualTo(String value) {
            addCriterion("case_level <>", value, "caseLevel");
            return (Criteria) this;
        }

        public Criteria andCaseLevelGreaterThan(String value) {
            addCriterion("case_level >", value, "caseLevel");
            return (Criteria) this;
        }

        public Criteria andCaseLevelGreaterThanOrEqualTo(String value) {
            addCriterion("case_level >=", value, "caseLevel");
            return (Criteria) this;
        }

        public Criteria andCaseLevelLessThan(String value) {
            addCriterion("case_level <", value, "caseLevel");
            return (Criteria) this;
        }

        public Criteria andCaseLevelLessThanOrEqualTo(String value) {
            addCriterion("case_level <=", value, "caseLevel");
            return (Criteria) this;
        }

        public Criteria andCaseLevelLike(String value) {
            addCriterion("case_level like", value, "caseLevel");
            return (Criteria) this;
        }

        public Criteria andCaseLevelNotLike(String value) {
            addCriterion("case_level not like", value, "caseLevel");
            return (Criteria) this;
        }

        public Criteria andCaseLevelIn(List<String> values) {
            addCriterion("case_level in", values, "caseLevel");
            return (Criteria) this;
        }

        public Criteria andCaseLevelNotIn(List<String> values) {
            addCriterion("case_level not in", values, "caseLevel");
            return (Criteria) this;
        }

        public Criteria andCaseLevelBetween(String value1, String value2) {
            addCriterion("case_level between", value1, value2, "caseLevel");
            return (Criteria) this;
        }

        public Criteria andCaseLevelNotBetween(String value1, String value2) {
            addCriterion("case_level not between", value1, value2, "caseLevel");
            return (Criteria) this;
        }

        public Criteria andCaseLevelValueIsNull() {
            addCriterion("case_level_value is null");
            return (Criteria) this;
        }

        public Criteria andCaseLevelValueIsNotNull() {
            addCriterion("case_level_value is not null");
            return (Criteria) this;
        }

        public Criteria andCaseLevelValueEqualTo(Integer value) {
            addCriterion("case_level_value =", value, "caseLevelValue");
            return (Criteria) this;
        }

        public Criteria andCaseLevelValueNotEqualTo(Integer value) {
            addCriterion("case_level_value <>", value, "caseLevelValue");
            return (Criteria) this;
        }

        public Criteria andCaseLevelValueGreaterThan(Integer value) {
            addCriterion("case_level_value >", value, "caseLevelValue");
            return (Criteria) this;
        }

        public Criteria andCaseLevelValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_level_value >=", value, "caseLevelValue");
            return (Criteria) this;
        }

        public Criteria andCaseLevelValueLessThan(Integer value) {
            addCriterion("case_level_value <", value, "caseLevelValue");
            return (Criteria) this;
        }

        public Criteria andCaseLevelValueLessThanOrEqualTo(Integer value) {
            addCriterion("case_level_value <=", value, "caseLevelValue");
            return (Criteria) this;
        }

        public Criteria andCaseLevelValueIn(List<Integer> values) {
            addCriterion("case_level_value in", values, "caseLevelValue");
            return (Criteria) this;
        }

        public Criteria andCaseLevelValueNotIn(List<Integer> values) {
            addCriterion("case_level_value not in", values, "caseLevelValue");
            return (Criteria) this;
        }

        public Criteria andCaseLevelValueBetween(Integer value1, Integer value2) {
            addCriterion("case_level_value between", value1, value2, "caseLevelValue");
            return (Criteria) this;
        }

        public Criteria andCaseLevelValueNotBetween(Integer value1, Integer value2) {
            addCriterion("case_level_value not between", value1, value2, "caseLevelValue");
            return (Criteria) this;
        }

        public Criteria andCaseFollowStatusIsNull() {
            addCriterion("case_follow_status is null");
            return (Criteria) this;
        }

        public Criteria andCaseFollowStatusIsNotNull() {
            addCriterion("case_follow_status is not null");
            return (Criteria) this;
        }

        public Criteria andCaseFollowStatusEqualTo(Integer value) {
            addCriterion("case_follow_status =", value, "caseFollowStatus");
            return (Criteria) this;
        }

        public Criteria andCaseFollowStatusNotEqualTo(Integer value) {
            addCriterion("case_follow_status <>", value, "caseFollowStatus");
            return (Criteria) this;
        }

        public Criteria andCaseFollowStatusGreaterThan(Integer value) {
            addCriterion("case_follow_status >", value, "caseFollowStatus");
            return (Criteria) this;
        }

        public Criteria andCaseFollowStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_follow_status >=", value, "caseFollowStatus");
            return (Criteria) this;
        }

        public Criteria andCaseFollowStatusLessThan(Integer value) {
            addCriterion("case_follow_status <", value, "caseFollowStatus");
            return (Criteria) this;
        }

        public Criteria andCaseFollowStatusLessThanOrEqualTo(Integer value) {
            addCriterion("case_follow_status <=", value, "caseFollowStatus");
            return (Criteria) this;
        }

        public Criteria andCaseFollowStatusIn(List<Integer> values) {
            addCriterion("case_follow_status in", values, "caseFollowStatus");
            return (Criteria) this;
        }

        public Criteria andCaseFollowStatusNotIn(List<Integer> values) {
            addCriterion("case_follow_status not in", values, "caseFollowStatus");
            return (Criteria) this;
        }

        public Criteria andCaseFollowStatusBetween(Integer value1, Integer value2) {
            addCriterion("case_follow_status between", value1, value2, "caseFollowStatus");
            return (Criteria) this;
        }

        public Criteria andCaseFollowStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("case_follow_status not between", value1, value2, "caseFollowStatus");
            return (Criteria) this;
        }

        public Criteria andCaseSourceIsNull() {
            addCriterion("case_source is null");
            return (Criteria) this;
        }

        public Criteria andCaseSourceIsNotNull() {
            addCriterion("case_source is not null");
            return (Criteria) this;
        }

        public Criteria andCaseSourceEqualTo(String value) {
            addCriterion("case_source =", value, "caseSource");
            return (Criteria) this;
        }

        public Criteria andCaseSourceNotEqualTo(String value) {
            addCriterion("case_source <>", value, "caseSource");
            return (Criteria) this;
        }

        public Criteria andCaseSourceGreaterThan(String value) {
            addCriterion("case_source >", value, "caseSource");
            return (Criteria) this;
        }

        public Criteria andCaseSourceGreaterThanOrEqualTo(String value) {
            addCriterion("case_source >=", value, "caseSource");
            return (Criteria) this;
        }

        public Criteria andCaseSourceLessThan(String value) {
            addCriterion("case_source <", value, "caseSource");
            return (Criteria) this;
        }

        public Criteria andCaseSourceLessThanOrEqualTo(String value) {
            addCriterion("case_source <=", value, "caseSource");
            return (Criteria) this;
        }

        public Criteria andCaseSourceLike(String value) {
            addCriterion("case_source like", value, "caseSource");
            return (Criteria) this;
        }

        public Criteria andCaseSourceNotLike(String value) {
            addCriterion("case_source not like", value, "caseSource");
            return (Criteria) this;
        }

        public Criteria andCaseSourceIn(List<String> values) {
            addCriterion("case_source in", values, "caseSource");
            return (Criteria) this;
        }

        public Criteria andCaseSourceNotIn(List<String> values) {
            addCriterion("case_source not in", values, "caseSource");
            return (Criteria) this;
        }

        public Criteria andCaseSourceBetween(String value1, String value2) {
            addCriterion("case_source between", value1, value2, "caseSource");
            return (Criteria) this;
        }

        public Criteria andCaseSourceNotBetween(String value1, String value2) {
            addCriterion("case_source not between", value1, value2, "caseSource");
            return (Criteria) this;
        }

        public Criteria andCaseSourceCodeIsNull() {
            addCriterion("case_source_code is null");
            return (Criteria) this;
        }

        public Criteria andCaseSourceCodeIsNotNull() {
            addCriterion("case_source_code is not null");
            return (Criteria) this;
        }

        public Criteria andCaseSourceCodeEqualTo(Integer value) {
            addCriterion("case_source_code =", value, "caseSourceCode");
            return (Criteria) this;
        }

        public Criteria andCaseSourceCodeNotEqualTo(Integer value) {
            addCriterion("case_source_code <>", value, "caseSourceCode");
            return (Criteria) this;
        }

        public Criteria andCaseSourceCodeGreaterThan(Integer value) {
            addCriterion("case_source_code >", value, "caseSourceCode");
            return (Criteria) this;
        }

        public Criteria andCaseSourceCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_source_code >=", value, "caseSourceCode");
            return (Criteria) this;
        }

        public Criteria andCaseSourceCodeLessThan(Integer value) {
            addCriterion("case_source_code <", value, "caseSourceCode");
            return (Criteria) this;
        }

        public Criteria andCaseSourceCodeLessThanOrEqualTo(Integer value) {
            addCriterion("case_source_code <=", value, "caseSourceCode");
            return (Criteria) this;
        }

        public Criteria andCaseSourceCodeIn(List<Integer> values) {
            addCriterion("case_source_code in", values, "caseSourceCode");
            return (Criteria) this;
        }

        public Criteria andCaseSourceCodeNotIn(List<Integer> values) {
            addCriterion("case_source_code not in", values, "caseSourceCode");
            return (Criteria) this;
        }

        public Criteria andCaseSourceCodeBetween(Integer value1, Integer value2) {
            addCriterion("case_source_code between", value1, value2, "caseSourceCode");
            return (Criteria) this;
        }

        public Criteria andCaseSourceCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("case_source_code not between", value1, value2, "caseSourceCode");
            return (Criteria) this;
        }

        public Criteria andImCaseCardFlagIsNull() {
            addCriterion("im_case_card_flag is null");
            return (Criteria) this;
        }

        public Criteria andImCaseCardFlagIsNotNull() {
            addCriterion("im_case_card_flag is not null");
            return (Criteria) this;
        }

        public Criteria andImCaseCardFlagEqualTo(Byte value) {
            addCriterion("im_case_card_flag =", value, "imCaseCardFlag");
            return (Criteria) this;
        }

        public Criteria andImCaseCardFlagNotEqualTo(Byte value) {
            addCriterion("im_case_card_flag <>", value, "imCaseCardFlag");
            return (Criteria) this;
        }

        public Criteria andImCaseCardFlagGreaterThan(Byte value) {
            addCriterion("im_case_card_flag >", value, "imCaseCardFlag");
            return (Criteria) this;
        }

        public Criteria andImCaseCardFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("im_case_card_flag >=", value, "imCaseCardFlag");
            return (Criteria) this;
        }

        public Criteria andImCaseCardFlagLessThan(Byte value) {
            addCriterion("im_case_card_flag <", value, "imCaseCardFlag");
            return (Criteria) this;
        }

        public Criteria andImCaseCardFlagLessThanOrEqualTo(Byte value) {
            addCriterion("im_case_card_flag <=", value, "imCaseCardFlag");
            return (Criteria) this;
        }

        public Criteria andImCaseCardFlagIn(List<Byte> values) {
            addCriterion("im_case_card_flag in", values, "imCaseCardFlag");
            return (Criteria) this;
        }

        public Criteria andImCaseCardFlagNotIn(List<Byte> values) {
            addCriterion("im_case_card_flag not in", values, "imCaseCardFlag");
            return (Criteria) this;
        }

        public Criteria andImCaseCardFlagBetween(Byte value1, Byte value2) {
            addCriterion("im_case_card_flag between", value1, value2, "imCaseCardFlag");
            return (Criteria) this;
        }

        public Criteria andImCaseCardFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("im_case_card_flag not between", value1, value2, "imCaseCardFlag");
            return (Criteria) this;
        }

        public Criteria andCallOutFlagIsNull() {
            addCriterion("call_out_flag is null");
            return (Criteria) this;
        }

        public Criteria andCallOutFlagIsNotNull() {
            addCriterion("call_out_flag is not null");
            return (Criteria) this;
        }

        public Criteria andCallOutFlagEqualTo(Integer value) {
            addCriterion("call_out_flag =", value, "callOutFlag");
            return (Criteria) this;
        }

        public Criteria andCallOutFlagNotEqualTo(Integer value) {
            addCriterion("call_out_flag <>", value, "callOutFlag");
            return (Criteria) this;
        }

        public Criteria andCallOutFlagGreaterThan(Integer value) {
            addCriterion("call_out_flag >", value, "callOutFlag");
            return (Criteria) this;
        }

        public Criteria andCallOutFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("call_out_flag >=", value, "callOutFlag");
            return (Criteria) this;
        }

        public Criteria andCallOutFlagLessThan(Integer value) {
            addCriterion("call_out_flag <", value, "callOutFlag");
            return (Criteria) this;
        }

        public Criteria andCallOutFlagLessThanOrEqualTo(Integer value) {
            addCriterion("call_out_flag <=", value, "callOutFlag");
            return (Criteria) this;
        }

        public Criteria andCallOutFlagIn(List<Integer> values) {
            addCriterion("call_out_flag in", values, "callOutFlag");
            return (Criteria) this;
        }

        public Criteria andCallOutFlagNotIn(List<Integer> values) {
            addCriterion("call_out_flag not in", values, "callOutFlag");
            return (Criteria) this;
        }

        public Criteria andCallOutFlagBetween(Integer value1, Integer value2) {
            addCriterion("call_out_flag between", value1, value2, "callOutFlag");
            return (Criteria) this;
        }

        public Criteria andCallOutFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("call_out_flag not between", value1, value2, "callOutFlag");
            return (Criteria) this;
        }

        public Criteria andDisableIsNull() {
            addCriterion("disable is null");
            return (Criteria) this;
        }

        public Criteria andDisableIsNotNull() {
            addCriterion("disable is not null");
            return (Criteria) this;
        }

        public Criteria andDisableEqualTo(Integer value) {
            addCriterion("disable =", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotEqualTo(Integer value) {
            addCriterion("disable <>", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableGreaterThan(Integer value) {
            addCriterion("disable >", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableGreaterThanOrEqualTo(Integer value) {
            addCriterion("disable >=", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableLessThan(Integer value) {
            addCriterion("disable <", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableLessThanOrEqualTo(Integer value) {
            addCriterion("disable <=", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableIn(List<Integer> values) {
            addCriterion("disable in", values, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotIn(List<Integer> values) {
            addCriterion("disable not in", values, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableBetween(Integer value1, Integer value2) {
            addCriterion("disable between", value1, value2, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotBetween(Integer value1, Integer value2) {
            addCriterion("disable not between", value1, value2, "disable");
            return (Criteria) this;
        }

        public Criteria andCaseCirculateStatusIsNull() {
            addCriterion("case_circulate_status is null");
            return (Criteria) this;
        }

        public Criteria andCaseCirculateStatusIsNotNull() {
            addCriterion("case_circulate_status is not null");
            return (Criteria) this;
        }

        public Criteria andCaseCirculateStatusEqualTo(Integer value) {
            addCriterion("case_circulate_status =", value, "caseCirculateStatus");
            return (Criteria) this;
        }

        public Criteria andCaseCirculateStatusNotEqualTo(Integer value) {
            addCriterion("case_circulate_status <>", value, "caseCirculateStatus");
            return (Criteria) this;
        }

        public Criteria andCaseCirculateStatusGreaterThan(Integer value) {
            addCriterion("case_circulate_status >", value, "caseCirculateStatus");
            return (Criteria) this;
        }

        public Criteria andCaseCirculateStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_circulate_status >=", value, "caseCirculateStatus");
            return (Criteria) this;
        }

        public Criteria andCaseCirculateStatusLessThan(Integer value) {
            addCriterion("case_circulate_status <", value, "caseCirculateStatus");
            return (Criteria) this;
        }

        public Criteria andCaseCirculateStatusLessThanOrEqualTo(Integer value) {
            addCriterion("case_circulate_status <=", value, "caseCirculateStatus");
            return (Criteria) this;
        }

        public Criteria andCaseCirculateStatusIn(List<Integer> values) {
            addCriterion("case_circulate_status in", values, "caseCirculateStatus");
            return (Criteria) this;
        }

        public Criteria andCaseCirculateStatusNotIn(List<Integer> values) {
            addCriterion("case_circulate_status not in", values, "caseCirculateStatus");
            return (Criteria) this;
        }

        public Criteria andCaseCirculateStatusBetween(Integer value1, Integer value2) {
            addCriterion("case_circulate_status between", value1, value2, "caseCirculateStatus");
            return (Criteria) this;
        }

        public Criteria andCaseCirculateStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("case_circulate_status not between", value1, value2, "caseCirculateStatus");
            return (Criteria) this;
        }

        public Criteria andOldCaseIdIsNull() {
            addCriterion("old_case_id is null");
            return (Criteria) this;
        }

        public Criteria andOldCaseIdIsNotNull() {
            addCriterion("old_case_id is not null");
            return (Criteria) this;
        }

        public Criteria andOldCaseIdEqualTo(Long value) {
            addCriterion("old_case_id =", value, "oldCaseId");
            return (Criteria) this;
        }

        public Criteria andOldCaseIdNotEqualTo(Long value) {
            addCriterion("old_case_id <>", value, "oldCaseId");
            return (Criteria) this;
        }

        public Criteria andOldCaseIdGreaterThan(Long value) {
            addCriterion("old_case_id >", value, "oldCaseId");
            return (Criteria) this;
        }

        public Criteria andOldCaseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("old_case_id >=", value, "oldCaseId");
            return (Criteria) this;
        }

        public Criteria andOldCaseIdLessThan(Long value) {
            addCriterion("old_case_id <", value, "oldCaseId");
            return (Criteria) this;
        }

        public Criteria andOldCaseIdLessThanOrEqualTo(Long value) {
            addCriterion("old_case_id <=", value, "oldCaseId");
            return (Criteria) this;
        }

        public Criteria andOldCaseIdIn(List<Long> values) {
            addCriterion("old_case_id in", values, "oldCaseId");
            return (Criteria) this;
        }

        public Criteria andOldCaseIdNotIn(List<Long> values) {
            addCriterion("old_case_id not in", values, "oldCaseId");
            return (Criteria) this;
        }

        public Criteria andOldCaseIdBetween(Long value1, Long value2) {
            addCriterion("old_case_id between", value1, value2, "oldCaseId");
            return (Criteria) this;
        }

        public Criteria andOldCaseIdNotBetween(Long value1, Long value2) {
            addCriterion("old_case_id not between", value1, value2, "oldCaseId");
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