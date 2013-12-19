package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * POJO类GeProductExtraInfo
 */
@Entity
@Table(name = "GE_PRODUCT_EXTRAINFO")
public class GeProductExtraInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性产品 */
	private GeProductMain geProductMain;

	/** 属性法律声明 */
	private String legalNotices;

	/** 属性投保声明  */
	private String proposalNotices;

	/**
	 * 类GeProductExtraInfo的默认构造方法
	 */
	public GeProductExtraInfo() {
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
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
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
	 * 属性法律声明的getter方法
	 */

	@Column(name = "LEGALNOTICES")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getLegalNotices() {
		return this.legalNotices;
	}
	/**
	 * 属性法律声明的setter方法
	 */
	public void setLegalNotices(String legalNotices) {
		this.legalNotices = legalNotices;
	}
	/**
	 * 属性投保声明 的getter方法
	 */

	@Column(name = "PROPOSALNOTICES")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getProposalNotices() {
		return this.proposalNotices;
	}
	/**
	 * 属性投保声明 的setter方法
	 */
	public void setProposalNotices(String proposalNotices) {
		this.proposalNotices = proposalNotices;
	}

}
