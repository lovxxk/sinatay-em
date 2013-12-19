package cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��GeProductCorrelation
 */
@Entity
@Table(name = "GE_PRODUCT_CORRELATION")
public class GeProductCorrelation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Ե��������Ʒ */
	private GeDirectory geDirectoryByproduct;

	/** ���Ե��������Ʒ�Ƽ���Ʒ */
	private GeDirectory geDirectoryByrecommendProduct;

	/** ���Լ�¼���ֲ�Ʒ�����ȵȼ� ����������1-10��ʾ ��1��������ߣ�10��������ͣ� */
	private Integer correclation;

	/**
	 * ��GeProductCorrelation��Ĭ�Ϲ��췽��
	 */
	public GeProductCorrelation() {
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
	 * ���Ե��������Ʒ��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT")
	public GeDirectory getGeDirectoryByproduct() {
		return this.geDirectoryByproduct;
	}
	/**
	 * ���Ե��������Ʒ��setter����
	 */
	public void setGeDirectoryByproduct(GeDirectory geDirectoryByproduct) {
		this.geDirectoryByproduct = geDirectoryByproduct;
	}
	/**
	 * ���Ե��������Ʒ�Ƽ���Ʒ��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECOMMENDPRODUCT")
	public GeDirectory getGeDirectoryByrecommendProduct() {
		return this.geDirectoryByrecommendProduct;
	}
	/**
	 * ���Ե��������Ʒ�Ƽ���Ʒ��setter����
	 */
	public void setGeDirectoryByrecommendProduct(
			GeDirectory geDirectoryByrecommendProduct) {
		this.geDirectoryByrecommendProduct = geDirectoryByrecommendProduct;
	}
	/**
	 * ���Լ�¼���ֲ�Ʒ�����ȵȼ� ����������1-10��ʾ ��1��������ߣ�10��������ͣ���getter����
	 */

	@Column(name = "CORRECLATION")
	public Integer getCorreclation() {
		return this.correclation;
	}
	/**
	 * ���Լ�¼���ֲ�Ʒ�����ȵȼ� ����������1-10��ʾ ��1��������ߣ�10��������ͣ���setter����
	 */
	public void setCorreclation(Integer correclation) {
		this.correclation = correclation;
	}

}
