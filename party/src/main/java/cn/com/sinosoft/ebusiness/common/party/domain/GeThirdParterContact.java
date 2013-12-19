package cn.com.sinosoft.ebusiness.common.party.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeThirdParterContact
 */
@Entity
@Table(name = "GE_THIRDPARTER_CONTACT")
public class GeThirdParterContact implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性联系人ID */
	private String contactID;

	/** 属性第三方合作伙伴信息表-GE_ThirdParter_Info */
	private GeThirdParterInfo geThirdParterInfo;

	/** 属性姓名 */
	private String userName;

	/** 属性证件类型 */
	private String identifyType;

	/** 属性证件号码 */
	private String identifyNumber;

	/** 属性性别 */
	private String sex;

	/** 属性出生日期 */
	private Date birthday;

	/** 属性电话 */
	private String telePhone;

	/** 属性联系地址 */
	private String contactAddress;

	/** 属性电子邮箱 */
	private String email;

	/** 属性标识位 */
	private String flag;

	/**
	 * 类GeThirdParterContact的默认构造方法
	 */
	public GeThirdParterContact() {
	}

	/**
	 * 属性联系人ID的getter方法
	 */
	@Id
	@Column(name = "CONTACTID", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getContactID() {
		return this.contactID;
	}

	/**
	 * 属性联系人ID的setter方法
	 */
	public void setContactID(String contactID) {
		this.contactID = contactID;
	}

	/**
	 * 属性第三方合作伙伴信息表-GE_ThirdParter_Info的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "THIRDPARTERID")
	public GeThirdParterInfo getGeThirdParterInfo() {
		return this.geThirdParterInfo;
	}

	/**
	 * 属性第三方合作伙伴信息表-GE_ThirdParter_Info的setter方法
	 */
	public void setGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo) {
		this.geThirdParterInfo = geThirdParterInfo;
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
	public Date getBirthday() {
		return this.birthday;
	}

	/**
	 * 属性出生日期的setter方法
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 属性电话的getter方法
	 */

	@Column(name = "TELEPHONE")
	public String getTelePhone() {
		return this.telePhone;
	}

	/**
	 * 属性电话的setter方法
	 */
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	/**
	 * 属性联系地址的getter方法
	 */

	@Column(name = "CONTACTADDRESS")
	public String getContactAddress() {
		return this.contactAddress;
	}

	/**
	 * 属性联系地址的setter方法
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	/**
	 * 属性电子邮箱的getter方法
	 */

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	/**
	 * 属性电子邮箱的setter方法
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 属性标识位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标识位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
