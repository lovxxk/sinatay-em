package cn.com.sinosoft.ebusiness.marketing.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * POJO类GeActivitiesRule
 */
@Entity
@Table(name = "GE_ACTIVITIES_RULE")
public class GeActivitiesRule implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性规则代码 */
	private String ruleId;

	/** 属性增值服务活动-GE_AddService_Activity */
	private GeAddServiceActivity geAddServiceActivity;

	/** 属性商品代码 */
	private String itemID;

	/** 属性活动方式 */
	private String activityPattern;

	/** 属性活动方式N的值 */
	private String nvalue;

	/** 属性标志位 */
	private String flag;
	
	/** 属性保费类型 1不限  2小于 3大于 4两者之间*/
	private String premiumType;
	
	/** 属性保费值  当保费类型是两者之前时用@间隔这两个值的范围*/
	private String peremiumValue;
	
	/** 属性打折类型描述*/
	private String discountType;
	
	/** 属性折扣ID供寿险核心描述*/
	private String discountID;
	
	/** 属性保费值  打折因子的描述*/
	private byte[] discountRemark;
	
	/** 属性geActivitiesShoppingProducts */
	private List<GeActivitiesShoppingProduct> geActivitiesShoppingProducts = new ArrayList<GeActivitiesShoppingProduct>(
			0);
	
	//业务上使用的字段start
	private String itemName;//商品名称
	private String deptID;//部门ID  用于去规则引擎去查询
	private String proposalArea;//用于存在投保地区的
	private String riskCode;//险种代码
	private String eid;//产品代码
	private String proposalNo;//投保单号
	private String premiumRange1;//保费范围1  用于保费的值为两者之间
	private String premiumRange2;//保费范围2 用于保费的值为两者之间
	private String discountRemarkText ;//打折因子描述字段
	//业务上使用的字段end
	
	//险别标识
	@Transient
	private List<String> kindCode ;
	
	//关联标识
	@Transient
	private String connectFlag ;
	/**
	 * 类GeActivitiesRule的默认构造方法
	 */
	public GeActivitiesRule() {
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
	 * 属性增值服务活动-GE_AddService_Activity的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITYID")
	public GeAddServiceActivity getGeAddServiceActivity() {
		return this.geAddServiceActivity;
	}

	/**
	 * 属性增值服务活动-GE_AddService_Activity的setter方法
	 */
	public void setGeAddServiceActivity(
			GeAddServiceActivity geAddServiceActivity) {
		this.geAddServiceActivity = geAddServiceActivity;
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
	 * 属性活动方式N的值的getter方法
	 */

	@Column(name = "NVALUE")
	public String getNvalue() {
		return this.nvalue;
	}

	/**
	 * 属性活动方式N的值的setter方法
	 */
	public void setNvalue(String nvalue) {
		this.nvalue = nvalue;
	}

	/**
	 * 属性标志位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标志位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name="PREMIUMTYPE")
	public String getPremiumType() {
		return premiumType;
	}

	public void setPremiumType(String premiumType) {
		this.premiumType = premiumType;
	}
	
	@Column(name="PEREMIUMVALUE")
	public String getPeremiumValue() {
		return peremiumValue;
	}

	public void setPeremiumValue(String peremiumValue) {
		this.peremiumValue = peremiumValue;
	}
	
	@Column(name = "DISCOUNTTYPE")
	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	
	@Column(name = "DISCOUNTID")
	public String getDiscountID() {
		return discountID;
	}

	public void setDiscountID(String discountID) {
		this.discountID = discountID;
	}
	
	@Column(name = "DISCOUNTREMARK")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.BlobByteArrayType")
	public byte[] getDiscountRemark() {
		return discountRemark;
	}

	public void setDiscountRemark(byte[] discountRemark) {
		this.discountRemark = discountRemark;
	}
	
	/**
	 * 属性geActivitiesShoppingProducts的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geActivitiesRule")
	public List<GeActivitiesShoppingProduct> getGeActivitiesShoppingProducts() {
		return this.geActivitiesShoppingProducts;
	}

	/**
	 * 属性geActivitiesShoppingProducts的setter方法
	 */
	public void setGeActivitiesShoppingProducts(
			List<GeActivitiesShoppingProduct> geActivitiesShoppingProducts) {
		this.geActivitiesShoppingProducts = geActivitiesShoppingProducts;
	}

	//业务上使用的字段start
	@Transient
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Transient
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	@Transient
	public String getProposalArea() {
		return proposalArea;
	}

	public void setProposalArea(String proposalArea) {
		this.proposalArea = proposalArea;
	}
	@Transient
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	@Transient
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	@Transient
	public String getPremiumRange1() {
		return premiumRange1;
	}

	public void setPremiumRange1(String premiumRange1) {
		this.premiumRange1 = premiumRange1;
	}
	@Transient
	public String getPremiumRange2() {
		return premiumRange2;
	}

	public void setPremiumRange2(String premiumRange2) {
		this.premiumRange2 = premiumRange2;
	}
	@Transient
	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}
	@Transient
	public String getDiscountRemarkText() {
		return discountRemarkText;
	}

	public void setDiscountRemarkText(String discountRemarkText) {
		this.discountRemarkText = discountRemarkText;
	}
	
	
	@Transient
	public List<String> getKindCode() {
		return kindCode;
	}

	public void setKindCode(List<String> kindCode) {
		this.kindCode = kindCode;
	}
	@Transient
	public String getConnectFlag() {
		return connectFlag;
	}

	public void setConnectFlag(String connectFlag) {
		this.connectFlag = connectFlag;
	}
	//业务上使用的字段start
	
}

