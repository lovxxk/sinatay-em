package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProWebFlowPage;
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
 * POJO类GePageConfig
 */
@Entity
@Table(name = "GE_PAGECONFIG")
public class GePageConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	//主页面（不显示）
	
	
	/** 属性页面代码 */
	private String pageCode;

	/** 属性页面名称 */
	private String pageName;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性更新时间 */
	private Date updateTime;

	/** 属性备注 */
	private String remark;

	/** 属性gePageConfigRelates */
	private List<GePageConfigRelate> gePageConfigRelates = new ArrayList<GePageConfigRelate>(
			0);

	/** 属性geWebFlowPages */
	private List<GeWebFlowPage> geWebFlowPages = new ArrayList<GeWebFlowPage>(0);

	/** 属性geSaleProWebFlowPages */
	private List<GeSaleProWebFlowPage> geSaleProWebFlowPages = new ArrayList<GeSaleProWebFlowPage>(
			0);

	/** 属性geFlowConfigRelates */
	private List<GeFlowConfigRelate> geFlowConfigRelates = new ArrayList<GeFlowConfigRelate>(
			0);

	/**
	 * 类GePageConfig的默认构造方法
	 */
	public GePageConfig() {
	}

	/**
	 * 属性页面代码的getter方法
	 */
	@Id
	@Column(name = "PAGECODE")
	public String getPageCode() {
		return this.pageCode;
	}
	/**
	 * 属性页面代码的setter方法
	 */
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
	/**
	 * 属性页面名称的getter方法
	 */

	@Column(name = "PAGENAME")
	public String getPageName() {
		return this.pageName;
	}
	/**
	 * 属性页面名称的setter方法
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
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
	 * 属性gePageConfigRelates的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePageConfig")
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
	/**
	 * 属性geWebFlowPages的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePageConfig")
	public List<GeWebFlowPage> getGeWebFlowPages() {
		return this.geWebFlowPages;
	}
	/**
	 * 属性geWebFlowPages的setter方法
	 */
	public void setGeWebFlowPages(List<GeWebFlowPage> geWebFlowPages) {
		this.geWebFlowPages = geWebFlowPages;
	}
	/**
	 * 属性geSaleProWebFlowPages的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePageConfig")
	public List<GeSaleProWebFlowPage> getGeSaleProWebFlowPages() {
		return this.geSaleProWebFlowPages;
	}
	/**
	 * 属性geSaleProWebFlowPages的setter方法
	 */
	public void setGeSaleProWebFlowPages(
			List<GeSaleProWebFlowPage> geSaleProWebFlowPages) {
		this.geSaleProWebFlowPages = geSaleProWebFlowPages;
	}
	/**
	 * 属性geFlowConfigRelates的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePageConfig")
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

}
