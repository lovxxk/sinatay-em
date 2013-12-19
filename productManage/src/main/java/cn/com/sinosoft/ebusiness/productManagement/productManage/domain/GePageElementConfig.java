package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProWebFlowPageElement;
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
 * POJO类GePageElementConfig
 */
@Entity
@Table(name = "GE_PAGEELEMENTCONFIG")
public class GePageElementConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// 属性
	/** 属性元素代码 */
	private String elementCode;

	/** 属性元素名称 */
	private String elementName;

	/** 属性处理URL */
	private String handleURL;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性更新时间 */
	private Date updateTime;

	/** 属性备注 */
	private String remark;

	/** 属性默认顺序 */
	private Integer defaultSeqIndex;

	/** 属性geSaleProWebFlowPageElements */
	private List<GeSaleProWebFlowPageElement> geSaleProWebFlowPageElements = new ArrayList<GeSaleProWebFlowPageElement>(
			0);

	/** 属性geWebFlowPageElements */
	private List<GeWebFlowPageElement> geWebFlowPageElements = new ArrayList<GeWebFlowPageElement>(
			0);

	/** 属性gePageConfigRelates */
	private List<GePageConfigRelate> gePageConfigRelates = new ArrayList<GePageConfigRelate>(
			0);

	/**
	 * 类GePageElementConfig的默认构造方法
	 */
	public GePageElementConfig() {
	}

	/**
	 * 属性元素代码的getter方法
	 */
	@Id
	@Column(name = "ELEMENTCODE")
	public String getElementCode() {
		return this.elementCode;
	}
	/**
	 * 属性元素代码的setter方法
	 */
	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}
	/**
	 * 属性元素名称的getter方法
	 */

	@Column(name = "ELEMENTNAME")
	public String getElementName() {
		return this.elementName;
	}
	/**
	 * 属性元素名称的setter方法
	 */
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	/**
	 * 属性处理URL的getter方法
	 */

	@Column(name = "HANDLEURL")
	public String getHandleURL() {
		return this.handleURL;
	}
	/**
	 * 属性处理URL的setter方法
	 */
	public void setHandleURL(String handleURL) {
		this.handleURL = handleURL;
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
	 * 属性默认顺序的getter方法
	 */

	@Column(name = "DEFAULTSEQINDEX")
	public Integer getDefaultSeqIndex() {
		return this.defaultSeqIndex;
	}
	/**
	 * 属性默认顺序的setter方法
	 */
	public void setDefaultSeqIndex(Integer defaultSeqIndex) {
		this.defaultSeqIndex = defaultSeqIndex;
	}
	/**
	 * 属性geSaleProWebFlowPageElements的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePageElementConfig")
	public List<GeSaleProWebFlowPageElement> getGeSaleProWebFlowPageElements() {
		return this.geSaleProWebFlowPageElements;
	}
	/**
	 * 属性geSaleProWebFlowPageElements的setter方法
	 */
	public void setGeSaleProWebFlowPageElements(
			List<GeSaleProWebFlowPageElement> geSaleProWebFlowPageElements) {
		this.geSaleProWebFlowPageElements = geSaleProWebFlowPageElements;
	}
	/**
	 * 属性geWebFlowPageElements的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePageElementConfig")
	public List<GeWebFlowPageElement> getGeWebFlowPageElements() {
		return this.geWebFlowPageElements;
	}
	/**
	 * 属性geWebFlowPageElements的setter方法
	 */
	public void setGeWebFlowPageElements(
			List<GeWebFlowPageElement> geWebFlowPageElements) {
		this.geWebFlowPageElements = geWebFlowPageElements;
	}
	/**
	 * 属性gePageConfigRelates的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePageElementConfig")
	public List<GePageConfigRelate> getGePageConfigRelates() {
		return this.gePageConfigRelates;
	}
	/**
	 * 属性gePageConfigRelates的setter方法
	 */
	public void setGePageConfigRelates(
			List<GePageConfigRelate> gePageConfigRelates) {
		this.gePageConfigRelates = gePageConfigRelates;
	}

}
