package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeProductInsuredConfig
 */
@Entity
@Table(name = "GE_PRODUCT_INSUREDCONFIG")
public class GeProductInsuredConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;
	
	// 被保人年龄是否启用(0启用  1禁用)
	private String inAgeFlag;
	
	// 被保人年龄开始
	private String inAgeStart;
	// 被保人年龄结束
	private String inAgeEnd;
	
	// 被保人年龄开始单位
	private String inAgeStartAttr;
	
	// 被保人年龄结束单位
	private String inAgeEndAttr;

	/** 属性产品 */
	private GeProductMain geProductMain;

	/** 属性被保人姓名 */
	private String insName;

	/** 属性被保人性别 */
	private String insSex;

	/** 属性被保人出生日期 */
	private String insBirthday;

	/** 属性被保人证件类型 */
	private String insIdType;

	/** 属性被保人证件号码  */
	private String insIdNo;

	/** 属性被保人联系地址 */
	private String insAddress;

	/** 属性被保人邮政编码 */
	private String insZipCode;

	/** 属性被保人电子邮箱 */
	private String insEmail;

	/** 属性被保人公司电话 */
	private String insComPhone;

	/** 属性被保人家庭电话 */
	private String insHomePhone;

	/** 属性被保人移动电话 */
	private String insMobilePhone;

	/** 属性被保人职业类别 */
	private String insOccupation;

	/** 属性被保人职业类别配置项 */
	private String insOccupationTypeConfig;

	/** 属性被保人与投保人关系 */
	private String insRelationToApp;

	/** 属性被保人与投保人关系配置项 */
	private String insRelationToAppConfig;

	/** 属性连带被保人与主被保人关系 */
	private String isMoreIns;

	/** 属性连带被保人与主被保人关系配置型 */
	private String finsRelationToAppConfig;

	/** 属性同业保额 */
	private BigDecimal sameIndInsuredAmount;

	/** 属性被保人证件类型配置 */
	private String insIdTypeConfig;

	/** 属性被保人性别配置 */
	private String insSexConfig;

	/** 属性insuredtype */
	private String insuredtype;

	/** 属性geProInsuredOccupations */
	private List<GeProInsuredOccupation> geProInsuredOccupations = new ArrayList<GeProInsuredOccupation>(
			0);

	/**
	 * 类GeProductInsuredConfig的默认构造方法
	 */
	public GeProductInsuredConfig() {
	}

	/**
	 * 属性序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}
	/**
	 * 属性序号的setter方法
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	/**
	 * 属性产品的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}
	/**
	 * 属性产品的setter方法
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
	}
	/**
	 * 属性被保人姓名的getter方法
	 */

	@Column(name = "INSNAME")
	public String getInsName() {
		return this.insName;
	}
	/**
	 * 属性被保人姓名的setter方法
	 */
	public void setInsName(String insName) {
		this.insName = insName;
	}
	/**
	 * 属性被保人性别的getter方法
	 */

	@Column(name = "INSSEX")
	public String getInsSex() {
		return this.insSex;
	}
	/**
	 * 属性被保人性别的setter方法
	 */
	public void setInsSex(String insSex) {
		this.insSex = insSex;
	}
	/**
	 * 属性被保人出生日期的getter方法
	 */

	@Column(name = "INSBIRTHDAY")
	public String getInsBirthday() {
		return this.insBirthday;
	}
	/**
	 * 属性被保人出生日期的setter方法
	 */
	public void setInsBirthday(String insBirthday) {
		this.insBirthday = insBirthday;
	}
	/**
	 * 属性被保人证件类型的getter方法
	 */

	@Column(name = "INSIDTYPE")
	public String getInsIdType() {
		return this.insIdType;
	}
	/**
	 * 属性被保人证件类型的setter方法
	 */
	public void setInsIdType(String insIdType) {
		this.insIdType = insIdType;
	}
	/**
	 * 属性被保人证件号码 的getter方法
	 */

	@Column(name = "INSIDNO")
	public String getInsIdNo() {
		return this.insIdNo;
	}
	/**
	 * 属性被保人证件号码 的setter方法
	 */
	public void setInsIdNo(String insIdNo) {
		this.insIdNo = insIdNo;
	}
	/**
	 * 属性被保人联系地址的getter方法
	 */

	@Column(name = "INSADDRESS")
	public String getInsAddress() {
		return this.insAddress;
	}
	/**
	 * 属性被保人联系地址的setter方法
	 */
	public void setInsAddress(String insAddress) {
		this.insAddress = insAddress;
	}
	/**
	 * 属性被保人邮政编码的getter方法
	 */

	@Column(name = "INSZIPCODE")
	public String getInsZipCode() {
		return this.insZipCode;
	}
	/**
	 * 属性被保人邮政编码的setter方法
	 */
	public void setInsZipCode(String insZipCode) {
		this.insZipCode = insZipCode;
	}
	/**
	 * 属性被保人电子邮箱的getter方法
	 */

	@Column(name = "INSEMAIL")
	public String getInsEmail() {
		return this.insEmail;
	}
	/**
	 * 属性被保人电子邮箱的setter方法
	 */
	public void setInsEmail(String insEmail) {
		this.insEmail = insEmail;
	}
	/**
	 * 属性被保人公司电话的getter方法
	 */

	@Column(name = "INSCOMPHONE")
	public String getInsComPhone() {
		return this.insComPhone;
	}
	/**
	 * 属性被保人公司电话的setter方法
	 */
	public void setInsComPhone(String insComPhone) {
		this.insComPhone = insComPhone;
	}
	/**
	 * 属性被保人家庭电话的getter方法
	 */

	@Column(name = "INSHOMEPHONE")
	public String getInsHomePhone() {
		return this.insHomePhone;
	}
	/**
	 * 属性被保人家庭电话的setter方法
	 */
	public void setInsHomePhone(String insHomePhone) {
		this.insHomePhone = insHomePhone;
	}
	/**
	 * 属性被保人移动电话的getter方法
	 */

	@Column(name = "INSMOBILEPHONE")
	public String getInsMobilePhone() {
		return this.insMobilePhone;
	}
	/**
	 * 属性被保人移动电话的setter方法
	 */
	public void setInsMobilePhone(String insMobilePhone) {
		this.insMobilePhone = insMobilePhone;
	}
	/**
	 * 属性被保人职业类别的getter方法
	 */

	@Column(name = "INSOCCUPATION")
	public String getInsOccupation() {
		return this.insOccupation;
	}
	/**
	 * 属性被保人职业类别的setter方法
	 */
	public void setInsOccupation(String insOccupation) {
		this.insOccupation = insOccupation;
	}
	/**
	 * 属性被保人职业类别配置项的getter方法
	 */

	@Column(name = "INSOCCUPATIONTYPECONFIG")
	public String getInsOccupationTypeConfig() {
		return this.insOccupationTypeConfig;
	}
	/**
	 * 属性被保人职业类别配置项的setter方法
	 */
	public void setInsOccupationTypeConfig(String insOccupationTypeConfig) {
		this.insOccupationTypeConfig = insOccupationTypeConfig;
	}
	/**
	 * 属性被保人与投保人关系的getter方法
	 */

	@Column(name = "INSRELATIONTOAPP")
	public String getInsRelationToApp() {
		return this.insRelationToApp;
	}
	/**
	 * 属性被保人与投保人关系的setter方法
	 */
	public void setInsRelationToApp(String insRelationToApp) {
		this.insRelationToApp = insRelationToApp;
	}
	/**
	 * 属性被保人与投保人关系配置项的getter方法
	 */

	@Column(name = "INSRELATIONTOAPPCONFIG")
	public String getInsRelationToAppConfig() {
		return this.insRelationToAppConfig;
	}
	/**
	 * 属性被保人与投保人关系配置项的setter方法
	 */
	public void setInsRelationToAppConfig(String insRelationToAppConfig) {
		this.insRelationToAppConfig = insRelationToAppConfig;
	}
	/**
	 * 属性连带被保人与主被保人关系的getter方法
	 */

	@Column(name = "ISMOREINS")
	public String getIsMoreIns() {
		return this.isMoreIns;
	}
	/**
	 * 属性连带被保人与主被保人关系的setter方法
	 */
	public void setIsMoreIns(String isMoreIns) {
		this.isMoreIns = isMoreIns;
	}
	/**
	 * 属性连带被保人与主被保人关系配置型的getter方法
	 */

	@Column(name = "FINSRELATIONTOAPPCONFIG")
	public String getFinsRelationToAppConfig() {
		return this.finsRelationToAppConfig;
	}
	/**
	 * 属性连带被保人与主被保人关系配置型的setter方法
	 */
	public void setFinsRelationToAppConfig(String finsRelationToAppConfig) {
		this.finsRelationToAppConfig = finsRelationToAppConfig;
	}
	/**
	 * 属性同业保额的getter方法
	 */

	@Column(name = "SAMEINDINSUREDAMOUNT")
	public BigDecimal getSameIndInsuredAmount() {
		return this.sameIndInsuredAmount;
	}
	/**
	 * 属性同业保额的setter方法
	 */
	public void setSameIndInsuredAmount(BigDecimal sameIndInsuredAmount) {
		this.sameIndInsuredAmount = sameIndInsuredAmount;
	}
	/**
	 * 属性被保人证件类型配置的getter方法
	 */

	@Column(name = "INSIDTYPECONFIG")
	public String getInsIdTypeConfig() {
		return this.insIdTypeConfig;
	}
	/**
	 * 属性被保人证件类型配置的setter方法
	 */
	public void setInsIdTypeConfig(String insIdTypeConfig) {
		this.insIdTypeConfig = insIdTypeConfig;
	}
	/**
	 * 属性被保人性别配置的getter方法
	 */

	@Column(name = "INSSEXCONFIG")
	public String getInsSexConfig() {
		return this.insSexConfig;
	}
	/**
	 * 属性被保人性别配置的setter方法
	 */
	public void setInsSexConfig(String insSexConfig) {
		this.insSexConfig = insSexConfig;
	}
	/**
	 * 属性insuredtype的getter方法
	 */

	@Column(name = "INSUREDTYPE")
	public String getInsuredtype() {
		return this.insuredtype;
	}
	/**
	 * 属性insuredtype的setter方法
	 */
	public void setInsuredtype(String insuredtype) {
		this.insuredtype = insuredtype;
	}
	/**
	 * 属性geProInsuredOccupations的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductInsuredConfig")
	public List<GeProInsuredOccupation> getGeProInsuredOccupations() {
		return this.geProInsuredOccupations;
	}
	/**
	 * 属性geProInsuredOccupations的setter方法
	 */
	public void setGeProInsuredOccupations(
			List<GeProInsuredOccupation> geProInsuredOccupations) {
		this.geProInsuredOccupations = geProInsuredOccupations;
	}
	
	
	@Column(name = "INAGESTART")
	public String getInAgeStart() {
		return inAgeStart;
	}

	public void setInAgeStart(String inAgeStart) {
		this.inAgeStart = inAgeStart;
	}

	@Column(name = "INAGEEND")
	public String getInAgeEnd() {
		return inAgeEnd;
	}

	public void setInAgeEnd(String inAgeEnd) {
		this.inAgeEnd = inAgeEnd;
	}

	@Column(name = "INAGESTARTATTR")
	public String getInAgeStartAttr() {
		return inAgeStartAttr;
	}

	public void setInAgeStartAttr(String inAgeStartAttr) {
		this.inAgeStartAttr = inAgeStartAttr;
	}

	@Column(name = "INAGEENDATTR")
	public String getInAgeEndAttr() {
		return inAgeEndAttr;
	}

	public void setInAgeEndAttr(String inAgeEndAttr) {
		this.inAgeEndAttr = inAgeEndAttr;
	}
	
	@Column(name = "INAGEFLAG")
	public String getInAgeFlag() {
		return inAgeFlag;
	}

	public void setInAgeFlag(String inAgeFlag) {
		this.inAgeFlag = inAgeFlag;
	}


	
}
