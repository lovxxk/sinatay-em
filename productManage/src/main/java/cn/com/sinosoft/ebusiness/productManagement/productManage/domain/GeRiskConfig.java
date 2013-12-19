package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProductRisk;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO类GeRiskConfig
 */
@Entity
@Table(name = "GE_RISKCONFIG")
public class GeRiskConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// 险种配置
	/** 属性险种代码 */
	private String riskCode;

	/** 属性险种名称 */
	private String riskName;

	/** 属性险种简称 */
	private String riskSimpleName;

	/** 属性核心险种代码 */
	private String coreRiskCode;

	/** 属性销售渠道 */
	private String saleChannel;

	/** 属性销售类型 */
	private String saleType;

	/** 属性险种类型 */
	private String riskType;

	/** 属性险种性质 */
	private String riskNature;

	/** 属性险种细分 */
	private String riskTypeDetail;

	/** 属性主附险标志 */
	private String subRiskFlag;

	/** 属性业务领域 */
	private String businessArea;

	/** 属性操作员编号 */
	private String operatorID;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性更新时间 */
	private Date updateTime;

	/** 属性geDutyConfigs */
	private List<GeDutyConfig> geDutyConfigs = new ArrayList<GeDutyConfig>(0);

	/** 属性geSaleProductRisks */
	private List<GeSaleProductRisk> geSaleProductRisks = new ArrayList<GeSaleProductRisk>(
			0);

	/** 属性geProductRisks */
	private List<GeProductRisk> geProductRisks = new ArrayList<GeProductRisk>(0);

	/**
	 * 类GeRiskConfig的默认构造方法
	 */
	public GeRiskConfig() {
	}

	/**
	 * 属性险种代码的getter方法
	 */
	@Id
	@Column(name = "RISKCODE")
	public String getRiskCode() {
		return this.riskCode;
	}
	/**
	 * 属性险种代码的setter方法
	 */
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	/**
	 * 属性险种名称的getter方法
	 */

	@Column(name = "RISKNAME")
	public String getRiskName() {
		return this.riskName;
	}
	/**
	 * 属性险种名称的setter方法
	 */
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	/**
	 * 属性险种简称的getter方法
	 */

	@Column(name = "RISKSIMPLENAME")
	public String getRiskSimpleName() {
		return this.riskSimpleName;
	}
	/**
	 * 属性险种简称的setter方法
	 */
	public void setRiskSimpleName(String riskSimpleName) {
		this.riskSimpleName = riskSimpleName;
	}
	/**
	 * 属性核心险种代码的getter方法
	 */

	@Column(name = "CORERISKCODE")
	public String getCoreRiskCode() {
		return this.coreRiskCode;
	}
	/**
	 * 属性核心险种代码的setter方法
	 */
	public void setCoreRiskCode(String coreRiskCode) {
		this.coreRiskCode = coreRiskCode;
	}
	/**
	 * 属性销售渠道的getter方法
	 */

	@Column(name = "SALECHANNEL")
	public String getSaleChannel() {
		return this.saleChannel;
	}
	/**
	 * 属性销售渠道的setter方法
	 */
	public void setSaleChannel(String saleChannel) {
		this.saleChannel = saleChannel;
	}
	/**
	 * 属性销售类型的getter方法
	 */

	@Column(name = "SALETYPE")
	public String getSaleType() {
		return this.saleType;
	}
	/**
	 * 属性销售类型的setter方法
	 */
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	/**
	 * 属性险种类型的getter方法
	 */

	@Column(name = "RISKTYPE")
	public String getRiskType() {
		return this.riskType;
	}
	/**
	 * 属性险种类型的setter方法
	 */
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}
	/**
	 * 属性险种性质的getter方法
	 */

	@Column(name = "RISKNATURE")
	public String getRiskNature() {
		return this.riskNature;
	}
	/**
	 * 属性险种性质的setter方法
	 */
	public void setRiskNature(String riskNature) {
		this.riskNature = riskNature;
	}
	/**
	 * 属性险种细分的getter方法
	 */

	@Column(name = "RISKTYPEDETAIL")
	public String getRiskTypeDetail() {
		return this.riskTypeDetail;
	}
	/**
	 * 属性险种细分的setter方法
	 */
	public void setRiskTypeDetail(String riskTypeDetail) {
		this.riskTypeDetail = riskTypeDetail;
	}
	/**
	 * 属性主附险标志的getter方法
	 */

	@Column(name = "SUBRISKFLAG")
	public String getSubRiskFlag() {
		return this.subRiskFlag;
	}
	/**
	 * 属性主附险标志的setter方法
	 */
	public void setSubRiskFlag(String subRiskFlag) {
		this.subRiskFlag = subRiskFlag;
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
	 * 属性操作员编号的getter方法
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}
	/**
	 * 属性操作员编号的setter方法
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
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
	 * 属性geDutyConfigs的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geRiskConfig")
	public List<GeDutyConfig> getGeDutyConfigs() {
		return this.geDutyConfigs;
	}
	/**
	 * 属性geDutyConfigs的setter方法
	 */
	public void setGeDutyConfigs(List<GeDutyConfig> geDutyConfigs) {
		this.geDutyConfigs = geDutyConfigs;
	}
	/**
	 * 属性geSaleProductRisks的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geRiskConfig")
	public List<GeSaleProductRisk> getGeSaleProductRisks() {
		return this.geSaleProductRisks;
	}
	/**
	 * 属性geSaleProductRisks的setter方法
	 */
	public void setGeSaleProductRisks(List<GeSaleProductRisk> geSaleProductRisks) {
		this.geSaleProductRisks = geSaleProductRisks;
	}
	/**
	 * 属性geProductRisks的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geRiskConfig")
	public List<GeProductRisk> getGeProductRisks() {
		return this.geProductRisks;
	}
	/**
	 * 属性geProductRisks的setter方法
	 */
	public void setGeProductRisks(List<GeProductRisk> geProductRisks) {
		this.geProductRisks = geProductRisks;
	}

}
