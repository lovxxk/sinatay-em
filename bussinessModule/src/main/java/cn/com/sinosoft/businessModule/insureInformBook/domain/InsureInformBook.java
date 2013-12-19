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
 * POJO类PaymentAccount
 */
@Entity
@Table(name = "INSUREINFORMBOOK")
public class InsureInformBook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -596612957503780966L;
	
	/** 属性序号 */
	private String serialNo;

	/** 属性告知代码 */
	private String informCode;

	/** 属性告知类型 */
	private Integer informType;
	
	/** 属性告知类型名称 */
	private String informTypeName;

	/** 属性告知顺序 */
	private Integer informOrder;
	
	/** 属性告知内容 */
	private String informContent;
	
	/** 属性保单 */
	private InsurancePolicy insurancePolicy;
	
	/** 属性 告知版别 固定为02 */
	private String tellVersion;
	
	/** 属性 告知备注 */
	private String tellRemark;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();

	/**
	 * 类PaymentAccount的默认构造方法
	 */
	public InsureInformBook() {
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
	 * 属性告知代码的getter方法
	 */
	@Column(name = "INFORMCODE")
	public String getInformCode() {
		return informCode;
	}

	/**
	 * 属性告知代码的setter方法
	 */
	public void setInformCode(String informCode) {
		this.informCode = informCode;
	}

	/**
	 * 属性告知类型的getter方法
	 */
	@Column(name = "INFORMTYPE")
	public Integer getInformType() {
		return informType;
	}
	
	/**
	 * 属性投保告知类型枚举类的getter方法
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
	 * 属性投保告知类型核心值的getter方法
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
	 * 属性投保告知类型商家值的getter方法
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
	 * 属性告知类型的setter方法
	 */
	public void setInformType(Integer informType) {
		this.informType = informType;
	}

	/**
	 * 属性投保告知类型赋值
	 */
	public void setEnumInformType(InformType  informType) {
		if (informType != null) {
			this.informType = informType.getValue();
		}
	}
	
	/**
	 * 属性核心投保告知类型赋值
	 */
	public void setInformTypeByCoreValue(String coreValue) {
		InformType  informType = (InformType) EnumDataUtils.getEnumDictionaryByCoreValue(InformType.class, coreValue);
		if (informType != null) {
			this.informType = informType.getValue();
		}
	}
	
	/**
	 * 属性商家投保告知类型赋值
	 */
	public void setInformTypeByMerchantValue(String merchantValue) {
		InformType  informType = (InformType) EnumDataUtils.getEnumDictionaryByMerchantValue(InformType.class, merchantValue);
		if (informType != null) {
			this.informType = informType.getValue();
		}
	}
	
	/**
	 * 属性告知类型名称的getter方法
	 */
	@Column(name = "INFORMTYPENAME")
	public String getInformTypeName() {
		return informTypeName;
	}

	/**
	 * 属性告知类型名称的setter方法
	 */
	public void setInformTypeName(String informTypeName) {
		this.informTypeName = informTypeName;
	}

	/**
	 * 属性告知顺序的getter方法
	 */
	@Column(name = "INFORMORDER")
	public Integer getInformOrder() {
		return informOrder;
	}

	/**
	 * 属性告知顺序的setter方法
	 */
	public void setInformOrder(Integer informOrder) {
		this.informOrder = informOrder;
	}

	/**
	 * 属性告知内容的getter方法
	 */
	@Column(name = "INFORMCONTENT")
	public String getInformContent() {
		return informContent;
	}

	/**
	 * 属性告知内容的setter方法
	 */
	public void setInformContent(String informContent) {
		this.informContent = informContent;
	}

	/**
	 * 属性保单的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "POLICYSERIALNO")
	public InsurancePolicy getInsurancePolicy() {
		return this.insurancePolicy;
	}

	/**
	 * 属性保单的setter方法
	 */
	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}

	/**
	 * 添加保单时同时将投保告知信息赋值给保单对象
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && !getInsurancePolicy().getInsureInformBooks().contains(this)) {
			getInsurancePolicy().getInsureInformBooks().add(this);
		}
	}
	
	
	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 属性更新时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 属性更新时间的setter方法
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 属性 告知版别 的getter方法
	 */
	@Column(name = "TELLVERSION")
	public String getTellVersion() {
		return tellVersion;
	}

	/**
	 * 属性 告知版别 的setter方法
	 */
	public void setTellVersion(String tellVersion) {
		this.tellVersion = tellVersion;
	}

	/**
	 * 属性 告知备注 的getter方法
	 */
	@Column(name = "TELLREMARK")
	public String getTellRemark() {
		return tellRemark;
	}

	/**
	 * 属性 告知备注 的setter方法
	 */
	public void setTellRemark(String tellRemark) {
		this.tellRemark = tellRemark;
	}

}
