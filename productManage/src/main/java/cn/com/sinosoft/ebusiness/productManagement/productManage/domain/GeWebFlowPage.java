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
 * POJO类GeWebFlowPage
 */
@Entity
@Table(name = "GE_WEBFLOWPAGE")
public class GeWebFlowPage implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// 根据产品找gePageConfig
	/** 属性序号 */
	private String serialNo;

	/** 属性产品 */
	private GeProductMain geProductMain;

	/** 属性页面流程配置 */
	private GeWebFlow geWebFlow;

	/** 属性页面配置 */
	private GePageConfig gePageConfig;

	/** 属性处理状态（'0'未定制，'1'已定制） */
	private String status;

	/** 属性显示顺序 */
	private Integer seqIndex;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性更新时间 */
	private Date updateTime;

	/** 属性显示状态 */
	private String isShow;

	/** 属性geWebFlowPageElements */
	private List<GeWebFlowPageElement> geWebFlowPageElements = new ArrayList<GeWebFlowPageElement>(
			0);

	/**
	 * 类GeWebFlowPage的默认构造方法
	 */
	public GeWebFlowPage() {
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
	 * 属性页面流程配置的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WEBFLOWSERCIALNO")
	public GeWebFlow getGeWebFlow() {
		return this.geWebFlow;
	}
	/**
	 * 属性页面流程配置的setter方法
	 */
	public void setGeWebFlow(GeWebFlow geWebFlow) {
		this.geWebFlow = geWebFlow;
	}
	/**
	 * 属性页面配置的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAGECODE")
	public GePageConfig getGePageConfig() {
		return this.gePageConfig;
	}
	/**
	 * 属性页面配置的setter方法
	 */
	public void setGePageConfig(GePageConfig gePageConfig) {
		this.gePageConfig = gePageConfig;
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
	 * 属性显示状态的getter方法
	 */

	@Column(name = "ISSHOW")
	public String getIsShow() {
		return this.isShow;
	}
	/**
	 * 属性显示状态的setter方法
	 */
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	/**
	 * 属性geWebFlowPageElements的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geWebFlowPage")
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

}
