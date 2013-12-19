package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProductDuty;
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
 * POJO类GeDutyConfig
 */
@Entity
@Table(name = "GE_DUTYCONFIG")
public class GeDutyConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// 责任配置
	
	/** 属性序号 */
	private String serialNo;

	/** 属性保险险种 */
	private GeRiskConfig geRiskConfig;

	/** 属性核心责任代码 */
	private String dutyCode;

	/** 属性责任名称 */
	private String dutyName;

	/** 属性险种责任简称 */
	private String simpleName;

	/** 属性业务领域 */
	private String businessArea;

	/** 属性操作员编号 */
	private String operatorID;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性更新时间 */
	private Date updateTime;

	/** 属性产品责任 */
	private List<GeProductDuty> geProductDuties = new ArrayList<GeProductDuty>(
			0);

	/** 属性geSaleProductDuties */
	private List<GeSaleProductDuty> geSaleProductDuties = new ArrayList<GeSaleProductDuty>(
			0);

	/**
	 * 类GeDutyConfig的默认构造方法
	 */
	public GeDutyConfig() {
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
	 * 属性责任代码的getter方法
	 */

	@Column(name = "DUTYCODE")
	public String getDutyCode() {
		return this.dutyCode;
	}
	/**
	 * 属性责任代码的setter方法
	 */
	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}
	/**
	 * 属性责任名称的getter方法
	 */

	@Column(name = "DUTYNAME")
	public String getDutyName() {
		return this.dutyName;
	}
	/**
	 * 属性责任名称的setter方法
	 */
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	/**
	 * 属性险种责任简称的getter方法
	 */

	@Column(name = "SIMPLENAME")
	public String getSimpleName() {
		return this.simpleName;
	}
	/**
	 * 属性险种责任简称的setter方法
	 */
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
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
	 * 属性产品责任的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDutyConfig")
	public List<GeProductDuty> getGeProductDuties() {
		return this.geProductDuties;
	}
	/**
	 * 属性产品责任的setter方法
	 */
	public void setGeProductDuties(List<GeProductDuty> geProductDuties) {
		this.geProductDuties = geProductDuties;
	}
	/**
	 * 属性geSaleProductDuties的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geDutyConfig")
	public List<GeSaleProductDuty> getGeSaleProductDuties() {
		return this.geSaleProductDuties;
	}
	/**
	 * 属性geSaleProductDuties的setter方法
	 */
	public void setGeSaleProductDuties(
			List<GeSaleProductDuty> geSaleProductDuties) {
		this.geSaleProductDuties = geSaleProductDuties;
	}

}
