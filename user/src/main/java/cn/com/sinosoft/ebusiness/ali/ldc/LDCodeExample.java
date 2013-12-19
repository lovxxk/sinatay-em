package cn.com.sinosoft.ebusiness.ali.ldc;

import java.util.ArrayList;
import java.util.List;

public class LDCodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LDCodeExample() {
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

        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIsNull() {
            addCriterion("CODETYPE is null");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIsNotNull() {
            addCriterion("CODETYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeTypeEqualTo(String value) {
            addCriterion("CODETYPE =", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotEqualTo(String value) {
            addCriterion("CODETYPE <>", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeGreaterThan(String value) {
            addCriterion("CODETYPE >", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CODETYPE >=", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLessThan(String value) {
            addCriterion("CODETYPE <", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLessThanOrEqualTo(String value) {
            addCriterion("CODETYPE <=", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLike(String value) {
            addCriterion("CODETYPE like", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotLike(String value) {
            addCriterion("CODETYPE not like", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIn(List<String> values) {
            addCriterion("CODETYPE in", values, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotIn(List<String> values) {
            addCriterion("CODETYPE not in", values, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeBetween(String value1, String value2) {
            addCriterion("CODETYPE between", value1, value2, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotBetween(String value1, String value2) {
            addCriterion("CODETYPE not between", value1, value2, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeNameIsNull() {
            addCriterion("CODENAME is null");
            return (Criteria) this;
        }

        public Criteria andCodeNameIsNotNull() {
            addCriterion("CODENAME is not null");
            return (Criteria) this;
        }

        public Criteria andCodeNameEqualTo(String value) {
            addCriterion("CODENAME =", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotEqualTo(String value) {
            addCriterion("CODENAME <>", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameGreaterThan(String value) {
            addCriterion("CODENAME >", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CODENAME >=", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameLessThan(String value) {
            addCriterion("CODENAME <", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameLessThanOrEqualTo(String value) {
            addCriterion("CODENAME <=", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameLike(String value) {
            addCriterion("CODENAME like", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotLike(String value) {
            addCriterion("CODENAME not like", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameIn(List<String> values) {
            addCriterion("CODENAME in", values, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotIn(List<String> values) {
            addCriterion("CODENAME not in", values, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameBetween(String value1, String value2) {
            addCriterion("CODENAME between", value1, value2, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotBetween(String value1, String value2) {
            addCriterion("CODENAME not between", value1, value2, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeEAliasIsNull() {
            addCriterion("CODEALIAS is null");
            return (Criteria) this;
        }

        public Criteria andCodeEAliasIsNotNull() {
            addCriterion("CODEALIAS is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEAliasEqualTo(String value) {
            addCriterion("CODEALIAS =", value, "codeEAlias");
            return (Criteria) this;
        }

        public Criteria andCodeEAliasNotEqualTo(String value) {
            addCriterion("CODEALIAS <>", value, "codeEAlias");
            return (Criteria) this;
        }

        public Criteria andCodeEAliasGreaterThan(String value) {
            addCriterion("CODEALIAS >", value, "codeEAlias");
            return (Criteria) this;
        }

        public Criteria andCodeEAliasGreaterThanOrEqualTo(String value) {
            addCriterion("CODEALIAS >=", value, "codeEAlias");
            return (Criteria) this;
        }

        public Criteria andCodeEAliasLessThan(String value) {
            addCriterion("CODEALIAS <", value, "codeEAlias");
            return (Criteria) this;
        }

        public Criteria andCodeEAliasLessThanOrEqualTo(String value) {
            addCriterion("CODEALIAS <=", value, "codeEAlias");
            return (Criteria) this;
        }

        public Criteria andCodeEAliasLike(String value) {
            addCriterion("CODEALIAS like", value, "codeEAlias");
            return (Criteria) this;
        }

        public Criteria andCodeEAliasNotLike(String value) {
            addCriterion("CODEALIAS not like", value, "codeEAlias");
            return (Criteria) this;
        }

        public Criteria andCodeEAliasIn(List<String> values) {
            addCriterion("CODEALIAS in", values, "codeEAlias");
            return (Criteria) this;
        }

        public Criteria andCodeEAliasNotIn(List<String> values) {
            addCriterion("CODEALIAS not in", values, "codeEAlias");
            return (Criteria) this;
        }

        public Criteria andCodeEAliasBetween(String value1, String value2) {
            addCriterion("CODEALIAS between", value1, value2, "codeEAlias");
            return (Criteria) this;
        }

        public Criteria andCodeEAliasNotBetween(String value1, String value2) {
            addCriterion("CODEALIAS not between", value1, value2, "codeEAlias");
            return (Criteria) this;
        }

        public Criteria andComCodeIsNull() {
            addCriterion("COMCODE is null");
            return (Criteria) this;
        }

        public Criteria andComCodeIsNotNull() {
            addCriterion("COMCODE is not null");
            return (Criteria) this;
        }

        public Criteria andComCodeEqualTo(String value) {
            addCriterion("COMCODE =", value, "comCode");
            return (Criteria) this;
        }

        public Criteria andComCodeNotEqualTo(String value) {
            addCriterion("COMCODE <>", value, "comCode");
            return (Criteria) this;
        }

        public Criteria andComCodeGreaterThan(String value) {
            addCriterion("COMCODE >", value, "comCode");
            return (Criteria) this;
        }

        public Criteria andComCodeGreaterThanOrEqualTo(String value) {
            addCriterion("COMCODE >=", value, "comCode");
            return (Criteria) this;
        }

        public Criteria andComCodeLessThan(String value) {
            addCriterion("COMCODE <", value, "comCode");
            return (Criteria) this;
        }

        public Criteria andComCodeLessThanOrEqualTo(String value) {
            addCriterion("COMCODE <=", value, "comCode");
            return (Criteria) this;
        }

        public Criteria andComCodeLike(String value) {
            addCriterion("COMCODE like", value, "comCode");
            return (Criteria) this;
        }

        public Criteria andComCodeNotLike(String value) {
            addCriterion("COMCODE not like", value, "comCode");
            return (Criteria) this;
        }

        public Criteria andComCodeIn(List<String> values) {
            addCriterion("COMCODE in", values, "comCode");
            return (Criteria) this;
        }

        public Criteria andComCodeNotIn(List<String> values) {
            addCriterion("COMCODE not in", values, "comCode");
            return (Criteria) this;
        }

        public Criteria andComCodeBetween(String value1, String value2) {
            addCriterion("COMCODE between", value1, value2, "comCode");
            return (Criteria) this;
        }

        public Criteria andComCodeNotBetween(String value1, String value2) {
            addCriterion("COMCODE not between", value1, value2, "comCode");
            return (Criteria) this;
        }

        public Criteria andOtherSignIsNull() {
            addCriterion("OTHERSIGN is null");
            return (Criteria) this;
        }

        public Criteria andOtherSignIsNotNull() {
            addCriterion("OTHERSIGN is not null");
            return (Criteria) this;
        }

        public Criteria andOtherSignEqualTo(String value) {
            addCriterion("OTHERSIGN =", value, "otherSign");
            return (Criteria) this;
        }

        public Criteria andOtherSignNotEqualTo(String value) {
            addCriterion("OTHERSIGN <>", value, "otherSign");
            return (Criteria) this;
        }

        public Criteria andOtherSignGreaterThan(String value) {
            addCriterion("OTHERSIGN >", value, "otherSign");
            return (Criteria) this;
        }

        public Criteria andOtherSignGreaterThanOrEqualTo(String value) {
            addCriterion("OTHERSIGN >=", value, "otherSign");
            return (Criteria) this;
        }

        public Criteria andOtherSignLessThan(String value) {
            addCriterion("OTHERSIGN <", value, "otherSign");
            return (Criteria) this;
        }

        public Criteria andOtherSignLessThanOrEqualTo(String value) {
            addCriterion("OTHERSIGN <=", value, "otherSign");
            return (Criteria) this;
        }

        public Criteria andOtherSignLike(String value) {
            addCriterion("OTHERSIGN like", value, "otherSign");
            return (Criteria) this;
        }

        public Criteria andOtherSignNotLike(String value) {
            addCriterion("OTHERSIGN not like", value, "otherSign");
            return (Criteria) this;
        }

        public Criteria andOtherSignIn(List<String> values) {
            addCriterion("OTHERSIGN in", values, "otherSign");
            return (Criteria) this;
        }

        public Criteria andOtherSignNotIn(List<String> values) {
            addCriterion("OTHERSIGN not in", values, "otherSign");
            return (Criteria) this;
        }

        public Criteria andOtherSignBetween(String value1, String value2) {
            addCriterion("OTHERSIGN between", value1, value2, "otherSign");
            return (Criteria) this;
        }

        public Criteria andOtherSignNotBetween(String value1, String value2) {
            addCriterion("OTHERSIGN not between", value1, value2, "otherSign");
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