package cn.com.sinosoft.businessModule.insureInformBook.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.businessModule.enums.dictionary.InformType;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.enums.EnumDataUtils;
/**
 * POJO��PaymentAccount
 */
@Entity
@Table(name = "INSUREINFORMBOOK")
public class InsureInformBook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -596612957503780966L;
	
	/** ������� */
	private String serialNo;

	/** ���Ը�֪���� */
	private String informCode;

	/** ���Ը�֪���� */
	private Integer informType;
	
	/** ���Ը�֪�������� */
	private String informTypeName;

	/** ���Ը�֪˳�� */
	private Integer informOrder;
	
	/** ���Ը�֪���� */
	private String informContent;
	
	/** ���Ա��� */
	private InsurancePolicy insurancePolicy;
	
	/** ���� ��֪��� �̶�Ϊ02 */
	private String tellVersion;
	
	/** ���� ��֪��ע */
	private String tellRemark;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();

	/**
	 * ��PaymentAccount��Ĭ�Ϲ��췽��
	 */
	public InsureInformBook() {
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
	 * ���Ը�֪�����getter����
	 */
	@Column(name = "INFORMCODE")
	public String getInformCode() {
		return informCode;
	}

	/**
	 * ���Ը�֪�����setter����
	 */
	public void setInformCode(String informCode) {
		this.informCode = informCode;
	}

	/**
	 * ���Ը�֪���͵�getter����
	 */
	@Column(name = "INFORMTYPE")
	public Integer getInformType() {
		return informType;
	}
	
	/**
	 * ����Ͷ����֪����ö�����getter����
	 */
	@Transient
	public InformType getEnumInformType() {
		if (getInformType() == null) {
			return null;
		}
		InformType  informType = (InformType) EnumDataUtils.getEnumDictionaryByValue(InformType.class, getInformType());
		return informType;
	}
	
	/**
	 * ����Ͷ����֪���ͺ���ֵ��getter����
	 */
	@Transient
	public String getInformTypeByCoreValue() {
		if (getInformType() == null) {
			return "";
		}
		InformType  informType = (InformType) EnumDataUtils.getEnumDictionaryByValue(InformType.class, getInformType());
		return informType.getCoreValue();
	}
	
	/**
	 * ����Ͷ����֪�����̼�ֵ��getter����
	 */
	@Transient
	public String getInformTypeByMerchantValue() {
		if (getInformType() == null) {
			return "";
		}
		InformType  informType = (InformType) EnumDataUtils.getEnumDictionaryByValue(InformType.class, getInformType());
		return informType.getMerchantValue();
	}
	
	
	/**
	 * ���Ը�֪���͵�setter����
	 */
	public void setInformType(Integer informType) {
		this.informType = informType;
	}

	/**
	 * ����Ͷ����֪���͸�ֵ
	 */
	public void setEnumInformType(InformType  informType) {
		if (informType != null) {
			this.informType = informType.getValue();
		}
	}
	
	/**
	 * ���Ժ���Ͷ����֪���͸�ֵ
	 */
	public void setInformTypeByCoreValue(String coreValue) {
		InformType  informType = (InformType) EnumDataUtils.getEnumDictionaryByCoreValue(InformType.class, coreValue);
		if (informType != null) {
			this.informType = informType.getValue();
		}
	}
	
	/**
	 * �����̼�Ͷ����֪���͸�ֵ
	 */
	public void setInformTypeByMerchantValue(String merchantValue) {
		InformType  informType = (InformType) EnumDataUtils.getEnumDictionaryByMerchantValue(InformType.class, merchantValue);
		if (informType != null) {
			this.informType = informType.getValue();
		}
	}
	
	/**
	 * ���Ը�֪�������Ƶ�getter����
	 */
	@Column(name = "INFORMTYPENAME")
	public String getInformTypeName() {
		return informTypeName;
	}

	/**
	 * ���Ը�֪�������Ƶ�setter����
	 */
	public void setInformTypeName(String informTypeName) {
		this.informTypeName = informTypeName;
	}

	/**
	 * ���Ը�֪˳���getter����
	 */
	@Column(name = "INFORMORDER")
	public Integer getInformOrder() {
		return informOrder;
	}

	/**
	 * ���Ը�֪˳���setter����
	 */
	public void setInformOrder(Integer informOrder) {
		this.informOrder = informOrder;
	}

	/**
	 * ���Ը�֪���ݵ�getter����
	 */
	@Column(name = "INFORMCONTENT")
	public String getInformContent() {
		return informContent;
	}

	/**
	 * ���Ը�֪���ݵ�setter����
	 */
	public void setInformContent(String informContent) {
		this.informContent = informContent;
	}

	/**
	 * ���Ա�����getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "POLICYSERIALNO")
	public InsurancePolicy getInsurancePolicy() {
		return this.insurancePolicy;
	}

	/**
	 * ���Ա�����setter����
	 */
	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}

	/**
	 * ��ӱ���ʱͬʱ��Ͷ����֪��Ϣ��ֵ����������
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && !getInsurancePolicy().getInsureInformBooks().contains(this)) {
			getInsurancePolicy().getInsureInformBooks().add(this);
		}
	}
	
	
	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * ���Ը���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * ���� ��֪��� ��getter����
	 */
	@Column(name = "TELLVERSION")
	public String getTellVersion() {
		return tellVersion;
	}

	/**
	 * ���� ��֪��� ��setter����
	 */
	public void setTellVersion(String tellVersion) {
		this.tellVersion = tellVersion;
	}

	/**
	 * ���� ��֪��ע ��getter����
	 */
	@Column(name = "TELLREMARK")
	public String getTellRemark() {
		return tellRemark;
	}

	/**
	 * ���� ��֪��ע ��setter����
	 */
	public void setTellRemark(String tellRemark) {
		this.tellRemark = tellRemark;
	}

}
