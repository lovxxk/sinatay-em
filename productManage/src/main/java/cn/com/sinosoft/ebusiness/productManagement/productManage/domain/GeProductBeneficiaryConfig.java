package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��GeProductBeneficiaryConfig
 */
@Entity
@Table(name = "GE_PRODUCT_BENEFICIARYCONFIG")
public class GeProductBeneficiaryConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Բ�Ʒ */
	private GeProductMain geProductMain;

	/** �������������� */
	private String benName;

	/** �����������Ա� */
	private String benGender;

	/** ���������˳������� */
	private String benBirthday;

	/** ����������֤������ */
	private String benIdType;

	/** ����������֤������  */
	private String benIdNumber;

	/** ������������ϵ��ַ */
	private String benAddress;

	/** ������������������ */
	private String benZipCode;

	/** ���������˵�������  */
	private String benEmail;

	/** ���������˹�˾�绰 */
	private String benComPhone;

	/** ���������˼�ͥ�绰 */
	private String benHomePhone;

	/** �����������ƶ��绰 */
	private String benMobilePhone;

	/** ��������˳�� */
	private String benOrder;

	/** ����������� */
	private String benRate;

	/** ������������ */
	private String benType;

	/** �������������������˹�ϵ */
	private String benRelationToPIns;

	/** �������������������˹�ϵ������ */
	private String benRelationToPInsConfig;

	/** �����������Ա����� */
	private String benGenderConfig;

	/** ����bendTypeConfig */
	private String bendTypeConfig;

	/**
	 * ��GeProductBeneficiaryConfig��Ĭ�Ϲ��췽��
	 */
	public GeProductBeneficiaryConfig() {
	}

	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}
	/**
	 * ������ŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	/**
	 * ���Բ�Ʒ��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}
	/**
	 * ���Բ�Ʒ��setter����
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
	}
	/**
	 * ����������������getter����
	 */

	@Column(name = "BENNAME")
	public String getBenName() {
		return this.benName;
	}
	/**
	 * ����������������setter����
	 */
	public void setBenName(String benName) {
		this.benName = benName;
	}
	/**
	 * �����������Ա��getter����
	 */

	@Column(name = "BENGENDER")
	public String getBenGender() {
		return this.benGender;
	}
	/**
	 * �����������Ա��setter����
	 */
	public void setBenGender(String benGender) {
		this.benGender = benGender;
	}
	/**
	 * ���������˳������ڵ�getter����
	 */

	@Column(name = "BENBIRTHDAY")
	public String getBenBirthday() {
		return this.benBirthday;
	}
	/**
	 * ���������˳������ڵ�setter����
	 */
	public void setBenBirthday(String benBirthday) {
		this.benBirthday = benBirthday;
	}
	/**
	 * ����������֤�����͵�getter����
	 */

	@Column(name = "BENIDTYPE")
	public String getBenIdType() {
		return this.benIdType;
	}
	/**
	 * ����������֤�����͵�setter����
	 */
	public void setBenIdType(String benIdType) {
		this.benIdType = benIdType;
	}
	/**
	 * ����������֤������ ��getter����
	 */

	@Column(name = "BENIDNUMBER")
	public String getBenIdNumber() {
		return this.benIdNumber;
	}
	/**
	 * ����������֤������ ��setter����
	 */
	public void setBenIdNumber(String benIdNumber) {
		this.benIdNumber = benIdNumber;
	}
	/**
	 * ������������ϵ��ַ��getter����
	 */

	@Column(name = "BENADDRESS")
	public String getBenAddress() {
		return this.benAddress;
	}
	/**
	 * ������������ϵ��ַ��setter����
	 */
	public void setBenAddress(String benAddress) {
		this.benAddress = benAddress;
	}
	/**
	 * �������������������getter����
	 */

	@Column(name = "BENZIPCODE")
	public String getBenZipCode() {
		return this.benZipCode;
	}
	/**
	 * �������������������setter����
	 */
	public void setBenZipCode(String benZipCode) {
		this.benZipCode = benZipCode;
	}
	/**
	 * ���������˵������� ��getter����
	 */

	@Column(name = "BENEMAIL")
	public String getBenEmail() {
		return this.benEmail;
	}
	/**
	 * ���������˵������� ��setter����
	 */
	public void setBenEmail(String benEmail) {
		this.benEmail = benEmail;
	}
	/**
	 * ���������˹�˾�绰��getter����
	 */

	@Column(name = "BENCOMPHONE")
	public String getBenComPhone() {
		return this.benComPhone;
	}
	/**
	 * ���������˹�˾�绰��setter����
	 */
	public void setBenComPhone(String benComPhone) {
		this.benComPhone = benComPhone;
	}
	/**
	 * ���������˼�ͥ�绰��getter����
	 */

	@Column(name = "BENHOMEPHONE")
	public String getBenHomePhone() {
		return this.benHomePhone;
	}
	/**
	 * ���������˼�ͥ�绰��setter����
	 */
	public void setBenHomePhone(String benHomePhone) {
		this.benHomePhone = benHomePhone;
	}
	/**
	 * �����������ƶ��绰��getter����
	 */

	@Column(name = "BENMOBILEPHONE")
	public String getBenMobilePhone() {
		return this.benMobilePhone;
	}
	/**
	 * �����������ƶ��绰��setter����
	 */
	public void setBenMobilePhone(String benMobilePhone) {
		this.benMobilePhone = benMobilePhone;
	}
	/**
	 * ��������˳���getter����
	 */

	@Column(name = "BENORDER")
	public String getBenOrder() {
		return this.benOrder;
	}
	/**
	 * ��������˳���setter����
	 */
	public void setBenOrder(String benOrder) {
		this.benOrder = benOrder;
	}
	/**
	 * �������������getter����
	 */

	@Column(name = "BENRATE")
	public String getBenRate() {
		return this.benRate;
	}
	/**
	 * �������������setter����
	 */
	public void setBenRate(String benRate) {
		this.benRate = benRate;
	}
	/**
	 * �����������͵�getter����
	 */

	@Column(name = "BENTYPE")
	public String getBenType() {
		return this.benType;
	}
	/**
	 * �����������͵�setter����
	 */
	public void setBenType(String benType) {
		this.benType = benType;
	}
	/**
	 * �������������������˹�ϵ��getter����
	 */

	@Column(name = "BENRELATIONTOPINS")
	public String getBenRelationToPIns() {
		return this.benRelationToPIns;
	}
	/**
	 * �������������������˹�ϵ��setter����
	 */
	public void setBenRelationToPIns(String benRelationToPIns) {
		this.benRelationToPIns = benRelationToPIns;
	}
	/**
	 * �������������������˹�ϵ�������getter����
	 */

	@Column(name = "BENRELATIONTOPINSCONFIG")
	public String getBenRelationToPInsConfig() {
		return this.benRelationToPInsConfig;
	}
	/**
	 * �������������������˹�ϵ�������setter����
	 */
	public void setBenRelationToPInsConfig(String benRelationToPInsConfig) {
		this.benRelationToPInsConfig = benRelationToPInsConfig;
	}
	/**
	 * �����������Ա����õ�getter����
	 */

	@Column(name = "BENGENDERCONFIG")
	public String getBenGenderConfig() {
		return this.benGenderConfig;
	}
	/**
	 * �����������Ա����õ�setter����
	 */
	public void setBenGenderConfig(String benGenderConfig) {
		this.benGenderConfig = benGenderConfig;
	}
	/**
	 * ����bendTypeConfig��getter����
	 */

	@Column(name = "BENIDTYPECONFIG")
	public String getBendTypeConfig() {
		return this.bendTypeConfig;
	}
	/**
	 * ����bendTypeConfig��setter����
	 */
	public void setBendTypeConfig(String bendTypeConfig) {
		this.bendTypeConfig = bendTypeConfig;
	}

}
