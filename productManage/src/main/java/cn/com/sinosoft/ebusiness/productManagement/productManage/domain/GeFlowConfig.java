package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProWebFlow;
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
 * POJO类GeFlowConfig
 */
@Entity
@Table(name = "GE_FLOWCONFIG")
public class GeFlowConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// 总流程
	
	/** 属性流程代码 */
	private String flowCode;

	/** 属性流程名称 */
	private String flowName;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性更新时间 */
	private Date updateTime;

	/** 属性备注 */
	private String remark;

	/** 属性geFlowConfigRelates */
	private List<GeFlowConfigRelate> geFlowConfigRelates = new ArrayList<GeFlowConfigRelate>(
			0);

	/** 属性geWebFlows */
	private List<GeWebFlow> geWebFlows = new ArrayList<GeWebFlow>(0);

	/** 属性geSaleProWebFlows */
	private List<GeSaleProWebFlow> geSaleProWebFlows = new ArrayList<GeSaleProWebFlow>(
			0);

	/**
	 * 类GeFlowConfig的默认构造方法
	 */
	public GeFlowConfig() {
	}

	/**
	 * 属性流程代码的getter方法
	 */
	@Id
	@Column(name = "FLOWCODE")
	public String getFlowCode() {
		return this.flowCode;
	}
	/**
	 * 属性流程代码的setter方法
	 */
	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}
	/**
	 * 属性流程名称的getter方法
	 */

	@Column(name = "FLOWNAME")
	public String getFlowName() {
		return this.flowName;
	}
	/**
	 * 属性流程名称的setter方法
	 */
	public void setFlowName(String flowName) {
		this.flowName = flowName;
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
	 * 属性备注的getter方法
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}
	/**
	 * 属性备注的setter方法
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 属性geFlowConfigRelates的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geFlowConfig")
	public List<GeFlowConfigRelate> getGeFlowConfigRelates() {
		return this.geFlowConfigRelates;
	}
	/**
	 * 属性geFlowConfigRelates的setter方法
	 */
	public void setGeFlowConfigRelates(
			List<GeFlowConfigRelate> geFlowConfigRelates) {
		this.geFlowConfigRelates = geFlowConfigRelates;
	}
	/**
	 * 属性geWebFlows的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geFlowConfig")
	public List<GeWebFlow> getGeWebFlows() {
		return this.geWebFlows;
	}
	/**
	 * 属性geWebFlows的setter方法
	 */
	public void setGeWebFlows(List<GeWebFlow> geWebFlows) {
		this.geWebFlows = geWebFlows;
	}
	/**
	 * 属性geSaleProWebFlows的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geFlowConfig")
	public List<GeSaleProWebFlow> getGeSaleProWebFlows() {
		return this.geSaleProWebFlows;
	}
	/**
	 * 属性geSaleProWebFlows的setter方法
	 */
	public void setGeSaleProWebFlows(List<GeSaleProWebFlow> geSaleProWebFlows) {
		this.geSaleProWebFlows = geSaleProWebFlows;
	}

}
