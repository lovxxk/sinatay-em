package cn.com.sinosoft.ebusiness.service.user.personal.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeBlacklist
 */
@Entity
@Table(name = "GE_BLACKLIST")
public class GeBlackList implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性id */
	private String id;

	/** 属性01 个人用户02 企业用户 */
	private String userType;

	/** 属性姓名 */
	private String userName;

	/** 属性性别 */
	private String sex;

	/** 属性出生日期 */
	private Date birthDay;

	/** 属性证件类型 */
	private String identifyType;

	/** 属性证件号码 */
	private String identifyNumber;

	/** 属性业务领域 */
	private String businessArea;

	/** 属性原因 */
	private String reason;

	/**
	 * 类GeBlacklist的默认构造方法
	 */
	public GeBlackList() {
	}

	/**
	 * 属性id的getter方法
	 */
	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@GeneratedValue(generator = "UUIDGenerator")
	public String getId() {
		return this.id;
	}

	/**
	 * 属性id的setter方法
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 属性01 个人用户02 企业用户的getter方法
	 */

	@Column(name = "USERTYPE")
	public String getUserType() {
		return this.userType;
	}

	/**
	 * 属性01 个人用户02 企业用户的setter方法
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * 属性姓名的getter方法
	 */

	@Column(name = "USERNAME")
	public String getUserName() {
		return this.userName;
	}

	/**
	 * 属性姓名的setter方法
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 属性性别的getter方法
	 */

	@Column(name = "SEX")
	public String getSex() {
		return this.sex;
	}

	/**
	 * 属性性别的setter方法
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 属性出生日期的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY")
	public Date getBirthDay() {
		return this.birthDay;
	}

	/**
	 * 属性出生日期的setter方法
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * 属性证件类型的getter方法
	 */

	@Column(name = "IDENTIFYTYPE")
	public String getIdentifyType() {
		return this.identifyType;
	}

	/**
	 * 属性证件类型的setter方法
	 */
	public void setIdentifyType(String identifyType) {
		this.identifyType = identifyType;
	}

	/**
	 * 属性证件号码的getter方法
	 */

	@Column(name = "IDENTIFYNUMBER")
	public String getIdentifyNumber() {
		return this.identifyNumber;
	}

	/**
	 * 属性证件号码的setter方法
	 */
	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}

	/**
	 * 属性业务领域的getter方法
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return this.businessArea;
	}

	/**
	 * 属性业务领域的setter方法
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	/**
	 * 属性原因的getter方法
	 */

	@Column(name = "REASON")
	public String getReason() {
		return this.reason;
	}

	/**
	 * 属性原因的setter方法
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

}
