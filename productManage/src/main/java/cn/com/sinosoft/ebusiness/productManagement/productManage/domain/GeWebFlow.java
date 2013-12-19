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
 * POJO类GeWebFlow
 */
@Entity
@Table(name = "GE_WEBFLOW")
public class GeWebFlow implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// 绑定产品
	
	/** 属性序号 */
	private String serialNo;

	/** 属性产品 */
	private GeProductMain geProductMain;

	/** 属性流程配置 */
	private GeFlowConfig geFlowConfig;

	/** 属性处理状态（'0'未定制，'1'已定制） */
	private String status;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性更新时间 */
	private Date updateTime;

	/** 属性geWebFlowPages */
	private List<GeWebFlowPage> geWebFlowPages = new ArrayList<GeWebFlowPage>(0);

	/**
	 * 类GeWebFlow的默认构造方法
	 */
	public GeWebFlow() {
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
	 * 属性流程配置的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLOWCODE")
	public GeFlowConfig getGeFlowConfig() {
		return this.geFlowConfig;
	}
	/**
	 * 属性流程配置的setter方法
	 */
	public void setGeFlowConfig(GeFlowConfig geFlowConfig) {
		this.geFlowConfig = geFlowConfig;
	}
	/**
	 * 属性处理状态（'0'未定制，'1'已定制）的getter方法
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}
	/**
	 * 属性处理状态（'0'未定制，'1'已定制）的setter方法
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * 属性geWebFlowPages的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geWebFlow")
	public List<GeWebFlowPage> getGeWebFlowPages() {
		return this.geWebFlowPages;
	}
	/**
	 * 属性geWebFlowPages的setter方法
	 */
	public void setGeWebFlowPages(List<GeWebFlowPage> geWebFlowPages) {
		this.geWebFlowPages = geWebFlowPages;
	}

}
