package cn.com.sinosoft.ebusiness.service.user.personal.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeBlacklist
 */
@Entity
@Table(name = "GE_BLACKLIST")
public class GeBlackList implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����id */
	private String id;

	/** ����01 �����û�02 ��ҵ�û� */
	private String userType;

	/** �������� */
	private String userName;

	/** �����Ա� */
	private String sex;

	/** ���Գ������� */
	private Date birthDay;

	/** ����֤������ */
	private String identifyType;

	/** ����֤������ */
	private String identifyNumber;

	/** ����ҵ������ */
	private String businessArea;

	/** ����ԭ�� */
	private String reason;

	/**
	 * ��GeBlacklist��Ĭ�Ϲ��췽��
	 */
	public GeBlackList() {
	}

	/**
	 * ����id��getter����
	 */
	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@GeneratedValue(generator = "UUIDGenerator")
	public String getId() {
		return this.id;
	}

	/**
	 * ����id��setter����
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * ����01 �����û�02 ��ҵ�û���getter����
	 */

	@Column(name = "USERTYPE")
	public String getUserType() {
		return this.userType;
	}

	/**
	 * ����01 �����û�02 ��ҵ�û���setter����
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * ����������getter����
	 */

	@Column(name = "USERNAME")
	public String getUserName() {
		return this.userName;
	}

	/**
	 * ����������setter����
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * �����Ա��getter����
	 */

	@Column(name = "SEX")
	public String getSex() {
		return this.sex;
	}

	/**
	 * �����Ա��setter����
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * ���Գ������ڵ�getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY")
	public Date getBirthDay() {
		return this.birthDay;
	}

	/**
	 * ���Գ������ڵ�setter����
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * ����֤�����͵�getter����
	 */

	@Column(name = "IDENTIFYTYPE")
	public String getIdentifyType() {
		return this.identifyType;
	}

	/**
	 * ����֤�����͵�setter����
	 */
	public void setIdentifyType(String identifyType) {
		this.identifyType = identifyType;
	}

	/**
	 * ����֤�������getter����
	 */

	@Column(name = "IDENTIFYNUMBER")
	public String getIdentifyNumber() {
		return this.identifyNumber;
	}

	/**
	 * ����֤�������setter����
	 */
	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}

	/**
	 * ����ҵ�������getter����
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return this.businessArea;
	}

	/**
	 * ����ҵ�������setter����
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	/**
	 * ����ԭ���getter����
	 */

	@Column(name = "REASON")
	public String getReason() {
		return this.reason;
	}

	/**
	 * ����ԭ���setter����
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

}
