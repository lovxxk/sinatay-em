package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeSaleProInsuredConfig
 */
@Entity
@Table(name = "GE_SALE_PRO_INSUREDCONFIG")
public class GeSaleProInsuredConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;
	
	// �����������Ƿ�����(0����  1����)
	private String inAgeFlag;
	// ���������俪ʼ
	private String inAgeStart;
	// �������������
	private String inAgeEnd;
	
	// ���������俪ʼ��λ
	private String inAgeStartAttr;
	
	// ���������������λ
	private String inAgeEndAttr;

	/** ����geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** ���Ա��������� */
	private String insName;

	/** ���Ա������Ա� */
	private String insSex;

	/** ���Ա����˳������� */
	private String insBirthday;

	/** ���Ա�����֤������ */
	private String insIdType;

	/** ���Ա�����֤������  */
	private String insIdNo;

	/** ���Ա�������ϵ��ַ */
	private String insAddress;

	/** ���Ա������������� */
	private String insZipCode;

	/** ���Ա����˵������� */
	private String insEmail;

	/** ���Ա����˹�˾�绰 */
	private String insComPhone;

	/** ���Ա����˼�ͥ�绰 */
	private String insHomePhone;

	/** ���Ա������ƶ��绰 */
	private String insMobilePhone;

	/** ���Ա�����ְҵ��� */
	private String insOccupation;

	/** ���Ա�����ְҵ��������� */
	private String insOccupationTypeConfig;

	/** ���Ա�������Ͷ���˹�ϵ */
	private String insRelationToApp;

	/** ���Ա�������Ͷ���˹�ϵ������ */
	private String insRelationToAppConfig;

	/** �����������������������˹�ϵ */
	private String isMoreIns;

	/** �����������������������˹�ϵ������ */
	private String finsRelationToAppConfig;

	/** ����ͬҵ���� */
	private BigDecimal sameIndInsuredAmount;

	/** ���Ա�����֤���������� */
	private String insIdTypeConfig;

	/** ���Ա������Ա����� */
	private String insSexConfig;

	/** ����insuredtype */
	private String insuredtype;

	/** ����geSaleProInsuredOccupations */
	private List<GeSaleProInsuredOccupation> geSaleProInsuredOccupations = new ArrayList<GeSaleProInsuredOccupation>(
			0);

	/**
	 * ��GeSaleProInsuredConfig��Ĭ�Ϲ��췽��
	 */
	public GeSaleProInsuredConfig() {
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
	 * ���Ա�����������getter����
	 */

	@Column(name = "INSNAME")
	public String getInsName() {
		return this.insName;
	}
	/**
	 * ���Ա�����������setter����
	 */
	public void setInsName(String insName) {
		this.insName = insName;
	}
	/**
	 * ���Ա������Ա��getter����
	 */

	@Column(name = "INSSEX")
	public String getInsSex() {
		return this.insSex;
	}
	/**
	 * ���Ա������Ա��setter����
	 */
	public void setInsSex(String insSex) {
		this.insSex = insSex;
	}
	/**
	 * ���Ա����˳������ڵ�getter����
	 */

	@Column(name = "INSBIRTHDAY")
	public String getInsBirthday() {
		return this.insBirthday;
	}
	/**
	 * ���Ա����˳������ڵ�setter����
	 */
	public void setInsBirthday(String insBirthday) {
		this.insBirthday = insBirthday;
	}
	/**
	 * ���Ա�����֤�����͵�getter����
	 */

	@Column(name = "INSIDTYPE")
	public String getInsIdType() {
		return this.insIdType;
	}
	/**
	 * ���Ա�����֤�����͵�setter����
	 */
	public void setInsIdType(String insIdType) {
		this.insIdType = insIdType;
	}
	/**
	 * ���Ա�����֤������ ��getter����
	 */

	@Column(name = "INSIDNO")
	public String getInsIdNo() {
		return this.insIdNo;
	}
	/**
	 * ���Ա�����֤������ ��setter����
	 */
	public void setInsIdNo(String insIdNo) {
		this.insIdNo = insIdNo;
	}
	/**
	 * ���Ա�������ϵ��ַ��getter����
	 */

	@Column(name = "INSADDRESS")
	public String getInsAddress() {
		return this.insAddress;
	}
	/**
	 * ���Ա�������ϵ��ַ��setter����
	 */
	public void setInsAddress(String insAddress) {
		this.insAddress = insAddress;
	}
	/**
	 * ���Ա��������������getter����
	 */

	@Column(name = "INSZIPCODE")
	public String getInsZipCode() {
		return this.insZipCode;
	}
	/**
	 * ���Ա��������������setter����
	 */
	public void setInsZipCode(String insZipCode) {
		this.insZipCode = insZipCode;
	}
	/**
	 * ���Ա����˵��������getter����
	 */

	@Column(name = "INSEMAIL")
	public String getInsEmail() {
		return this.insEmail;
	}
	/**
	 * ���Ա����˵��������setter����
	 */
	public void setInsEmail(String insEmail) {
		this.insEmail = insEmail;
	}
	/**
	 * ���Ա����˹�˾�绰��getter����
	 */

	@Column(name = "INSCOMPHONE")
	public String getInsComPhone() {
		return this.insComPhone;
	}
	/**
	 * ���Ա����˹�˾�绰��setter����
	 */
	public void setInsComPhone(String insComPhone) {
		this.insComPhone = insComPhone;
	}
	/**
	 * ���Ա����˼�ͥ�绰��getter����
	 */

	@Column(name = "INSHOMEPHONE")
	public String getInsHomePhone() {
		return this.insHomePhone;
	}
	/**
	 * ���Ա����˼�ͥ�绰��setter����
	 */
	public void setInsHomePhone(String insHomePhone) {
		this.insHomePhone = insHomePhone;
	}
	/**
	 * ���Ա������ƶ��绰��getter����
	 */

	@Column(name = "INSMOBILEPHONE")
	public String getInsMobilePhone() {
		return this.insMobilePhone;
	}
	/**
	 * ���Ա������ƶ��绰��setter����
	 */
	public void setInsMobilePhone(String insMobilePhone) {
		this.insMobilePhone = insMobilePhone;
	}
	/**
	 * ���Ա�����ְҵ����getter����
	 */

	@Column(name = "INSOCCUPATION")
	public String getInsOccupation() {
		return this.insOccupation;
	}
	/**
	 * ���Ա�����ְҵ����setter����
	 */
	public void setInsOccupation(String insOccupation) {
		this.insOccupation = insOccupation;
	}
	/**
	 * ���Ա�����ְҵ����������getter����
	 */

	@Column(name = "INSOCCUPATIONTYPECONFIG")
	public String getInsOccupationTypeConfig() {
		return this.insOccupationTypeConfig;
	}
	/**
	 * ���Ա�����ְҵ����������setter����
	 */
	public void setInsOccupationTypeConfig(String insOccupationTypeConfig) {
		this.insOccupationTypeConfig = insOccupationTypeConfig;
	}
	/**
	 * ���Ա�������Ͷ���˹�ϵ��getter����
	 */

	@Column(name = "INSRELATIONTOAPP")
	public String getInsRelationToApp() {
		return this.insRelationToApp;
	}
	/**
	 * ���Ա�������Ͷ���˹�ϵ��setter����
	 */
	public void setInsRelationToApp(String insRelationToApp) {
		this.insRelationToApp = insRelationToApp;
	}
	/**
	 * ���Ա�������Ͷ���˹�ϵ�������getter����
	 */

	@Column(name = "INSRELATIONTOAPPCONFIG")
	public String getInsRelationToAppConfig() {
		return this.insRelationToAppConfig;
	}
	/**
	 * ���Ա�������Ͷ���˹�ϵ�������setter����
	 */
	public void setInsRelationToAppConfig(String insRelationToAppConfig) {
		this.insRelationToAppConfig = insRelationToAppConfig;
	}
	/**
	 * �����������������������˹�ϵ��getter����
	 */

	@Column(name = "ISMOREINS")
	public String getIsMoreIns() {
		return this.isMoreIns;
	}
	/**
	 * �����������������������˹�ϵ��setter����
	 */
	public void setIsMoreIns(String isMoreIns) {
		this.isMoreIns = isMoreIns;
	}
	/**
	 * �����������������������˹�ϵ�����͵�getter����
	 */

	@Column(name = "FINSRELATIONTOAPPCONFIG")
	public String getFinsRelationToAppConfig() {
		return this.finsRelationToAppConfig;
	}
	/**
	 * �����������������������˹�ϵ�����͵�setter����
	 */
	public void setFinsRelationToAppConfig(String finsRelationToAppConfig) {
		this.finsRelationToAppConfig = finsRelationToAppConfig;
	}
	/**
	 * ����ͬҵ�����getter����
	 */

	@Column(name = "SAMEINDINSUREDAMOUNT")
	public BigDecimal getSameIndInsuredAmount() {
		return this.sameIndInsuredAmount;
	}
	/**
	 * ����ͬҵ�����setter����
	 */
	public void setSameIndInsuredAmount(BigDecimal sameIndInsuredAmount) {
		this.sameIndInsuredAmount = sameIndInsuredAmount;
	}
	/**
	 * ���Ա�����֤���������õ�getter����
	 */

	@Column(name = "INSIDTYPECONFIG")
	public String getInsIdTypeConfig() {
		return this.insIdTypeConfig;
	}
	/**
	 * ���Ա�����֤���������õ�setter����
	 */
	public void setInsIdTypeConfig(String insIdTypeConfig) {
		this.insIdTypeConfig = insIdTypeConfig;
	}
	/**
	 * ���Ա������Ա����õ�getter����
	 */

	@Column(name = "INSSEXCONFIG")
	public String getInsSexConfig() {
		return this.insSexConfig;
	}
	/**
	 * ���Ա������Ա����õ�setter����
	 */
	public void setInsSexConfig(String insSexConfig) {
		this.insSexConfig = insSexConfig;
	}
	/**
	 * ����insuredtype��getter����
	 */

	@Column(name = "INSUREDTYPE")
	public String getInsuredtype() {
		return this.insuredtype;
	}
	/**
	 * ����insuredtype��setter����
	 */
	public void setInsuredtype(String insuredtype) {
		this.insuredtype = insuredtype;
	}
	/**
	 * ����geSaleProInsuredOccupations��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProInsuredConfig")
	public List<GeSaleProInsuredOccupation> getGeSaleProInsuredOccupations() {
		return this.geSaleProInsuredOccupations;
	}
	/**
	 * ����geSaleProInsuredOccupations��setter����
	 */
	public void setGeSaleProInsuredOccupations(
			List<GeSaleProInsuredOccupation> geSaleProInsuredOccupations) {
		this.geSaleProInsuredOccupations = geSaleProInsuredOccupations;
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
