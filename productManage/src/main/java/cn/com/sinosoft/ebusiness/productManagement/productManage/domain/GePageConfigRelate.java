package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;
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

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GePageConfigRelate
 */
@Entity
@Table(name = "GE_PAGECONFIGRELATE")
public class GePageConfigRelate implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// 主（流程）页面--属性（子页面）中间表
	
	/** 属性序号 */
	private String serialNo;

	/** 属性元素配置 */
	private GePageElementConfig gePageElementConfig;

	/** 属性页面配置 */
	private GePageConfig gePageConfig;

	/** 属性默认顺序 */
	private Integer defaultSeqIndex;

	/** 属性是否允许编辑（Y允许编辑，N不允许编辑） */
	private String editable;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性更新时间 */
	private Date updateTime;

	/** 属性备注 */
	private String remark;

	/** 属性是否必需 */
	private String required;

	/**
	 * 类GePageConfigRelate的默认构造方法
	 */
	public GePageConfigRelate() {
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
	 * 属性元素配置的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ELEMENTCODE")
	public GePageElementConfig getGePageElementConfig() {
		return this.gePageElementConfig;
	}
	/**
	 * 属性元素配置的setter方法
	 */
	public void setGePageElementConfig(GePageElementConfig gePageElementConfig) {
		this.gePageElementConfig = gePageElementConfig;
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
	 * 属性是否允许编辑（Y允许编辑，N不允许编辑）的getter方法
	 */

	@Column(name = "EDITABLE")
	public String getEditable() {
		return this.editable;
	}
	/**
	 * 属性是否允许编辑（Y允许编辑，N不允许编辑）的setter方法
	 */
	public void setEditable(String editable) {
		this.editable = editable;
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
	 * 属性是否必需的getter方法
	 */

	@Column(name = "REQUIRED")
	public String getRequired() {
		return this.required;
	}
	/**
	 * 属性是否必需的setter方法
	 */
	public void setRequired(String required) {
		this.required = required;
	}

}
