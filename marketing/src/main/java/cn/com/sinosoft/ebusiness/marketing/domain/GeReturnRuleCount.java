package cn.com.sinosoft.ebusiness.marketing.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
/**
 * POJO类GeReturnRuleCount
 */
@Entity
@Table(name = "GE_RETURNRULE_COUNT")
public class GeReturnRuleCount implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性规则代码 */
	private String ruleId;

	/** 属性活动起始时间 */
	private Date activityStartDate;

	/** 属性活动结束时间 */
	private Date activityEndDate;

	/** 属性地区 */
	private String deptID;

	/** 属性险种编码 */
	private String riskCode;

	/** 属性商品代码 */
	private String itemID;

	/** 属性活动方式 */
	private String activityPattern;

	/** 属性商品计数器 */
	private String itemCount;

	/**
	 * 类GeReturnRuleCount的默认构造方法
	 */
	public GeReturnRuleCount() {
	}

	/**
	 * 属性规则代码的getter方法
	 */
	@Id
	@Column(name = "RULEID", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getRuleId() {
		return this.ruleId;
	}

	/**
	 * 属性规则代码的setter方法
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * 属性活动起始时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVITYSTARTDATE")
	public Date getActivityStartDate() {
		return this.activityStartDate;
	}

	/**
	 * 属性活动起始时间的setter方法
	 */
	public void setActivityStartDate(Date activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	/**
	 * 属性活动结束时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVITYENDDATE")
	public Date getActivityEndDate() {
		return this.activityEndDate;
	}

	/**
	 * 属性活动结束时间的setter方法
	 */
	public void setActivityEndDate(Date activityEndDate) {
		this.activityEndDate = activityEndDate;
	}

	/**
	 * 属性地区的getter方法
	 */

	@Column(name = "DEPTID")
	public String getDeptID() {
		return this.deptID;
	}

	/**
	 * 属性地区的setter方法
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	/**
	 * 属性险种编码的getter方法
	 */

	@Column(name = "RISKCODE")
	public String getRiskCode() {
		return this.riskCode;
	}

	/**
	 * 属性险种编码的setter方法
	 */
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	/**
	 * 属性商品代码的getter方法
	 */

	@Column(name = "ITEMID")
	public String getItemID() {
		return this.itemID;
	}

	/**
	 * 属性商品代码的setter方法
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	/**
	 * 属性活动方式的getter方法
	 */

	@Column(name = "ACTIVITYPATTERN")
	public String getActivityPattern() {
		return this.activityPattern;
	}

	/**
	 * 属性活动方式的setter方法
	 */
	public void setActivityPattern(String activityPattern) {
		this.activityPattern = activityPattern;
	}

	/**
	 * 属性商品计数器的getter方法
	 */

	@Column(name = "ITEMCOUNT")
	public String getItemCount() {
		return this.itemCount;
	}

	/**
	 * 属性商品计数器的setter方法
	 */
	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}

}
