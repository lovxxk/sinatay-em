package cn.com.sinosoft.ebusiness.common.party.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeThirdParterContact
 */
@Entity
@Table(name = "GE_THIRDPARTER_CONTACT")
public class GeThirdParterContact implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������ϵ��ID */
	private String contactID;

	/** ���Ե��������������Ϣ��-GE_ThirdParter_Info */
	private GeThirdParterInfo geThirdParterInfo;

	/** �������� */
	private String userName;

	/** ����֤������ */
	private String identifyType;

	/** ����֤������ */
	private String identifyNumber;

	/** �����Ա� */
	private String sex;

	/** ���Գ������� */
	private Date birthday;

	/** ���Ե绰 */
	private String telePhone;

	/** ������ϵ��ַ */
	private String contactAddress;

	/** ���Ե������� */
	private String email;

	/** ���Ա�ʶλ */
	private String flag;

	/**
	 * ��GeThirdParterContact��Ĭ�Ϲ��췽��
	 */
	public GeThirdParterContact() {
	}

	/**
	 * ������ϵ��ID��getter����
	 */
	@Id
	@Column(name = "CONTACTID", unique = true,nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getContactID() {
		return this.contactID;
	}

	/**
	 * ������ϵ��ID��setter����
	 */
	public void setContactID(String contactID) {
		this.contactID = contactID;
	}

	/**
	 * ���Ե��������������Ϣ��-GE_ThirdParter_Info��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "THIRDPARTERID")
	public GeThirdParterInfo getGeThirdParterInfo() {
		return this.geThirdParterInfo;
	}

	/**
	 * ���Ե��������������Ϣ��-GE_ThirdParter_Info��setter����
	 */
	public void setGeThirdParterInfo(GeThirdParterInfo geThirdParterInfo) {
		this.geThirdParterInfo = geThirdParterInfo;
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
	public Date getBirthday() {
		return this.birthday;
	}

	/**
	 * ���Գ������ڵ�setter����
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * ���Ե绰��getter����
	 */

	@Column(name = "TELEPHONE")
	public String getTelePhone() {
		return this.telePhone;
	}

	/**
	 * ���Ե绰��setter����
	 */
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	/**
	 * ������ϵ��ַ��getter����
	 */

	@Column(name = "CONTACTADDRESS")
	public String getContactAddress() {
		return this.contactAddress;
	}

	/**
	 * ������ϵ��ַ��setter����
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	/**
	 * ���Ե��������getter����
	 */

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	/**
	 * ���Ե��������setter����
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * ���Ա�ʶλ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ���Ա�ʶλ��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
