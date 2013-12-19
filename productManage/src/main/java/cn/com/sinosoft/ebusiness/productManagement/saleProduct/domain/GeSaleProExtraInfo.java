package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeSaleProExtraInfo
 */
@Entity
@Table(name = "GE_SALE_PRO_EXTRAINFO")
public class GeSaleProExtraInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ����geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** ���Է������� */
	private String legalNotices;

	/** ����Ͷ������  */
	private String proposalNotices;

	/**
	 * ��GeSaleProExtraInfo��Ĭ�Ϲ��췽��
	 */
	public GeSaleProExtraInfo() {
	}

	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}
	/**
	 * ������ŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	/**
	 * ����geSaleProduct��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeSaleProduct getGeSaleProduct() {
		return this.geSaleProduct;
	}
	/**
	 * ����geSaleProduct��setter����
	 */
	public void setGeSaleProduct(GeSaleProduct geSaleProduct) {
		this.geSaleProduct = geSaleProduct;
	}
	/**
	 * ���Է���������getter����
	 */

	@Column(name = "LEGALNOTICES")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getLegalNotices() {
		return this.legalNotices;
	}
	/**
	 * ���Է���������setter����
	 */
	public void setLegalNotices(String legalNotices) {
		this.legalNotices = legalNotices;
	}
	/**
	 * ����Ͷ������ ��getter����
	 */

	@Column(name = "PROPOSALNOTICES")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getProposalNotices() {
		return this.proposalNotices;
	}
	/**
	 * ����Ͷ������ ��setter����
	 */
	public void setProposalNotices(String proposalNotices) {
		this.proposalNotices = proposalNotices;
	}

}
