package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
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
 * POJO类GeSaleProExtraInfo
 */
@Entity
@Table(name = "GE_SALE_PRO_EXTRAINFO")
public class GeSaleProExtraInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** 属性法律声明 */
	private String legalNotices;

	/** 属性投保声明  */
	private String proposalNotices;

	/**
	 * 类GeSaleProExtraInfo的默认构造方法
	 */
	public GeSaleProExtraInfo() {
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
	 * 属性geSaleProduct的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeSaleProduct getGeSaleProduct() {
		return this.geSaleProduct;
	}
	/**
	 * 属性geSaleProduct的setter方法
	 */
	public void setGeSaleProduct(GeSaleProduct geSaleProduct) {
		this.geSaleProduct = geSaleProduct;
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
