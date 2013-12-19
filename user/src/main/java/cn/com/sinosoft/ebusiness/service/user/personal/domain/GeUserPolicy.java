package cn.com.sinosoft.ebusiness.service.user.personal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeUserPolicy
 */
@Entity
@Table(name = "GE_USER_POLICY")
public class GeUserPolicy implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性处理序号 */
	private String serialNo;

	/** 属性用户号 */
	private String userID;

	/** 属性用户类型 */
	private String userType;

	/** 属性保单号 */
	private String policyNo;

	/** 属性业务领域 */
	private String businessArea;

	/** 属性绑定类型 */
	private String bindType;

	/** 属性bindpersontype */
	private String bindPersonType;

	/**
	 * 类GeUserPolicy的默认构造方法
	 */
	public GeUserPolicy() {
	}

	/**
	 * 属性处理序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO")
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * 属性处理序号的setter方法
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * 属性用户号的getter方法
	 */

	@Column(name = "USERID")
	public String getUserID() {
		return this.userID;
	}

	/**
	 * 属性用户号的setter方法
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * 属性用户类型的getter方法
	 */

	@Column(name = "USERTYPE")
	public String getUserType() {
		return this.userType;
	}

	/**
	 * 属性用户类型的setter方法
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * 属性保单号的getter方法
	 */

	@Column(name = "POLICYNO")
	public String getPolicyNo() {
		return this.policyNo;
	}

	/**
	 * 属性保单号的setter方法
	 */
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
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
	 * 属性绑定类型的getter方法
	 */

	@Column(name = "BINDTYPE")
	public String getBindType() {
		return this.bindType;
	}

	/**
	 * 属性绑定类型的setter方法
	 */
	public void setBindType(String bindType) {
		this.bindType = bindType;
	}

	/**
	 * 属性bindPersonType的getter方法
	 */

	@Column(name = "BINDPERSONTYPE")
	public String getBindPersonType() {
		return this.bindPersonType;
	}

	/**
	 * 属性bindpersontype的setter方法
	 */
	public void setBindPersonType(String bindPersonType) {
		this.bindPersonType = bindPersonType;
	}

}
