package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
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
 * POJO��GeSaleProApplicantConfig
 */
@Entity
@Table(name = "GE_SALE_PRO_APPLICANTCONFIG")
public class GeSaleProApplicantConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;
	
	// Ͷ���������Ƿ�����(0 ���� 1����)
	private String appAgeFlag;
	
	// Ͷ�������俪ʼ
	private String appAgeStart;
	// Ͷ�����������
	private String appAgeEnd;
	
	// Ͷ�������俪ʼ��λ
	private String appAgeStartAttr;
	
	// Ͷ�������������λ
	private String appAgeEndAttr;

	/** ����geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** ����Ͷ�������� */
	private String appName;

	/** ����Ͷ�����Ա� */
	private String appSex;

	/** ����Ͷ���˳������� */
	private String appBirthday;

	/** ����Ͷ����֤������ */
	private String appIdType;

	/** ����Ͷ����֤������ */
	private String appIdNo;

	/** ����Ͷ������ϵ��ַ */
	private String appAddress;

	/** ����Ͷ������������ */
	private String appZipCode;

	/** ����Ͷ���˵�������  */
	private String appEmail;

	/** ����Ͷ���˹�˾�绰 */
	private String appComPhone;

	/** ����Ͷ���˼�ͥ�绰  */
	private String appHomePhone;

	/** ����Ͷ�����ƶ��绰 */
	private String appMobilePhone;

	/** ����Ͷ�����Ա����� */
	private String appSexConfig;

	/** ����Ͷ����֤���������� */
	private String appIdTypeConfig;
	
	/** ����Ͷ����ְҵ��� */
	private String appOccupation;

	/** ����Ͷ����ְҵ��������� */
	private String appOccupationTypeConfig;

	/**
	 * ��GeSaleProApplicantConfig��Ĭ�Ϲ��췽��
	 */
	public GeSaleProApplicantConfig() {
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
	 * ����geSaleProduct��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeSaleProduct getGeSaleProduct() {
		return this.geSaleProduct;
	}
	/**
	 * ����geSaleProduct��setter����
	 */
	public void setGeSaleProduct(GeSaleProduct geSaleProduct) {
		this.geSaleProduct = geSaleProduct;
	}
	/**
	 * ����Ͷ����������getter����
	 */

	@Column(name = "APPNAME")
	public String getAppName() {
		return this.appName;
	}
	/**
	 * ����Ͷ����������setter����
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	/**
	 * ����Ͷ�����Ա��getter����
	 */

	@Column(name = "APPSEX")
	public String getAppSex() {
		return this.appSex;
	}
	/**
	 * ����Ͷ�����Ա��setter����
	 */
	public void setAppSex(String appSex) {
		this.appSex = appSex;
	}
	/**
	 * ����Ͷ���˳������ڵ�getter����
	 */

	@Column(name = "APPBIRTHDAY")
	public String getAppBirthday() {
		return this.appBirthday;
	}
	/**
	 * ����Ͷ���˳������ڵ�setter����
	 */
	public void setAppBirthday(String appBirthday) {
		this.appBirthday = appBirthday;
	}
	/**
	 * ����Ͷ����֤�����͵�getter����
	 */

	@Column(name = "APPIDTYPE")
	public String getAppIdType() {
		return this.appIdType;
	}
	/**
	 * ����Ͷ����֤�����͵�setter����
	 */
	public void setAppIdType(String appIdType) {
		this.appIdType = appIdType;
	}
	/**
	 * ����Ͷ����֤�������getter����
	 */

	@Column(name = "APPIDNO")
	public String getAppIdNo() {
		return this.appIdNo;
	}
	/**
	 * ����Ͷ����֤�������setter����
	 */
	public void setAppIdNo(String appIdNo) {
		this.appIdNo = appIdNo;
	}
	/**
	 * ����Ͷ������ϵ��ַ��getter����
	 */

	@Column(name = "APPADDRESS")
	public String getAppAddress() {
		return this.appAddress;
	}
	/**
	 * ����Ͷ������ϵ��ַ��setter����
	 */
	public void setAppAddress(String appAddress) {
		this.appAddress = appAddress;
	}
	/**
	 * ����Ͷ�������������getter����
	 */

	@Column(name = "APPZIPCODE")
	public String getAppZipCode() {
		return this.appZipCode;
	}
	/**
	 * ����Ͷ�������������setter����
	 */
	public void setAppZipCode(String appZipCode) {
		this.appZipCode = appZipCode;
	}
	/**
	 * ����Ͷ���˵������� ��getter����
	 */

	@Column(name = "APPEMAIL")
	public String getAppEmail() {
		return this.appEmail;
	}
	/**
	 * ����Ͷ���˵������� ��setter����
	 */
	public void setAppEmail(String appEmail) {
		this.appEmail = appEmail;
	}
	/**
	 * ����Ͷ���˹�˾�绰��getter����
	 */

	@Column(name = "APPCOMPHONE")
	public String getAppComPhone() {
		return this.appComPhone;
	}
	/**
	 * ����Ͷ���˹�˾�绰��setter����
	 */
	public void setAppComPhone(String appComPhone) {
		this.appComPhone = appComPhone;
	}
	/**
	 * ����Ͷ���˼�ͥ�绰 ��getter����
	 */

	@Column(name = "APPHOMEPHONE")
	public String getAppHomePhone() {
		return this.appHomePhone;
	}
	/**
	 * ����Ͷ���˼�ͥ�绰 ��setter����
	 */
	public void setAppHomePhone(String appHomePhone) {
		this.appHomePhone = appHomePhone;
	}
	/**
	 * ����Ͷ�����ƶ��绰��getter����
	 */

	@Column(name = "APPMOBILEPHONE")
	public String getAppMobilePhone() {
		return this.appMobilePhone;
	}
	/**
	 * ����Ͷ�����ƶ��绰��setter����
	 */
	public void setAppMobilePhone(String appMobilePhone) {
		this.appMobilePhone = appMobilePhone;
	}
	/**
	 * ����Ͷ�����Ա����õ�getter����
	 */

	@Column(name = "APPSEXCONFIG")
	public String getAppSexConfig() {
		return this.appSexConfig;
	}
	/**
	 * ����Ͷ�����Ա����õ�setter����
	 */
	public void setAppSexConfig(String appSexConfig) {
		this.appSexConfig = appSexConfig;
	}
	/**
	 * ����Ͷ����֤���������õ�getter����
	 */

	@Column(name = "APPIDTYPECONFIG")
	public String getAppIdTypeConfig() {
		return this.appIdTypeConfig;
	}
	/**
	 * ����Ͷ����֤���������õ�setter����
	 */
	public void setAppIdTypeConfig(String appIdTypeConfig) {
		this.appIdTypeConfig = appIdTypeConfig;
	}

	@Column(name = "APPAGESTART")
	public String getAppAgeStart() {
		return appAgeStart;
	}

	public void setAppAgeStart(String appAgeStart) {
		this.appAgeStart = appAgeStart;
	}

	
	@Column(name = "APPAGEEND")
	public String getAppAgeEnd() {
		return appAgeEnd;
	}

	public void setAppAgeEnd(String appAgeEnd) {
		this.appAgeEnd = appAgeEnd;
	}

	@Column(name = "APPAGESTARTATTR")
	public String getAppAgeStartAttr() {
		return appAgeStartAttr;
	}

	public void setAppAgeStartAttr(String appAgeStartAttr) {
		this.appAgeStartAttr = appAgeStartAttr;
	}

	@Column(name = "APPAGEENDATTR")
	public String getAppAgeEndAttr() {
		return appAgeEndAttr;
	}

	public void setAppAgeEndAttr(String appAgeEndAttr) {
		this.appAgeEndAttr = appAgeEndAttr;
	}

	@Column(name = "APPAGEFLAG")
	public String getAppAgeFlag() {
		return appAgeFlag;
	}

	public void setAppAgeFlag(String appAgeFlag) {
		this.appAgeFlag = appAgeFlag;
	}

	/**
	 * ����Ͷ����ְҵ����getter����
	 */
	@Column(name = "APPOCCUPATION")
	public String getAppOccupation() {
		return this.appOccupation;
	}
	/**
	 * ����Ͷ����ְҵ����setter����
	 */
	public void setAppOccupation(String appOccupation) {
		this.appOccupation = appOccupation;
	}
	
	/**
	 * ����Ͷ����ְҵ����������getter����
	 */
	@Column(name = "APPOCCUPATIONTYPECONFIG")
	public String getAppOccupationTypeConfig() {
		return this.appOccupationTypeConfig;
	}
	/**
	 * ����Ͷ����ְҵ����������setter����
	 */
	public void setAppOccupationTypeConfig(String appOccupationTypeConfig) {
		this.appOccupationTypeConfig = appOccupationTypeConfig;
	}
}
