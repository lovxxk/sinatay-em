package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeProductRisk
 */
@Entity
@Table(name = "GE_PRODUCTRISK")
public class GeProductRisk implements java.io.Serializable {
	// 险种
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性产品 */
	private GeProductMain geProductMain;

	/** 属性保险险种 */
	private GeRiskConfig geRiskConfig;

	/** 属性产品险种代码 */
	private String productRiskCode;

	/** 属性产品险种名称 */
	private String productRiskName;

	/** 属性显示顺序 */
	private Integer seqIndex;

	/** 属性是否显示产品责任 */
	private String isshowProductDuty;

	/** 属性销售标示 */
	private String saleFlag;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性更新时间 */
	private Date updateTime;

	/** 属性产品责任 */
	private List<GeProductDuty> geProductDuties = new ArrayList<GeProductDuty>(
			0);

	/**
	 * 类GeProductRisk的默认构造方法
	 */
	public GeProductRisk() {
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
	 * 属性产品的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE")
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}
	/**
	 * 属性产品的setter方法
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
	}
	/**
	 * 属性保险险种的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RISKCODE")
	public GeRiskConfig getGeRiskConfig() {
		return this.geRiskConfig;
	}
	/**
	 * 属性保险险种的setter方法
	 */
	public void setGeRiskConfig(GeRiskConfig geRiskConfig) {
		this.geRiskConfig = geRiskConfig;
	}
	/**
	 * 属性产品险种代码的getter方法
	 */

	@Column(name = "PRODUCTRISKCODE")
	public String getProductRiskCode() {
		return this.productRiskCode;
	}
	/**
	 * 属性产品险种代码的setter方法
	 */
	public void setProductRiskCode(String productRiskCode) {
		this.productRiskCode = productRiskCode;
	}
	/**
	 * 属性产品险种名称的getter方法
	 */

	@Column(name = "PRODUCTRISKNAME")
	public String getProductRiskName() {
		return this.productRiskName;
	}
	/**
	 * 属性产品险种名称的setter方法
	 */
	public void setProductRiskName(String productRiskName) {
		this.productRiskName = productRiskName;
	}
	/**
	 * 属性显示顺序的getter方法
	 */

	@Column(name = "SEQINDEX")
	public Integer getSeqIndex() {
		return this.seqIndex;
	}
	/**
	 * 属性显示顺序的setter方法
	 */
	public void setSeqIndex(Integer seqIndex) {
		this.seqIndex = seqIndex;
	}
	/**
	 * 属性是否显示产品责任的getter方法
	 */

	@Column(name = "ISSHOWPRODUCTDUTY")
	public String getIsshowProductDuty() {
		return this.isshowProductDuty;
	}
	/**
	 * 属性是否显示产品责任的setter方法
	 */
	public void setIsshowProductDuty(String isshowProductDuty) {
		this.isshowProductDuty = isshowProductDuty;
	}
	/**
	 * 属性销售标示的getter方法
	 */

	@Column(name = "SALEFLAG")
	public String getSaleFlag() {
		return this.saleFlag;
	}
	/**
	 * 属性销售标示的setter方法
	 */
	public void setSaleFlag(String saleFlag) {
		this.saleFlag = saleFlag;
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
	 * 属性产品责任的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductRisk")
	public List<GeProductDuty> getGeProductDuties() {
		return this.geProductDuties;
	}
	/**
	 * 属性产品责任的setter方法
	 */
	public void setGeProductDuties(List<GeProductDuty> geProductDuties) {
		this.geProductDuties = geProductDuties;
	}

}
