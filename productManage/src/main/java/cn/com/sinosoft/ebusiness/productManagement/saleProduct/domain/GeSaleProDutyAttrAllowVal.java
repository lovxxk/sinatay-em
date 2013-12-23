package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
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
 * POJO��GeSaleProDutyAttrAllowVal
 */
@Entity
@Table(name = "GE_SALE_PRO_DUTY_ATTR_ALLOWVAL")
public class GeSaleProDutyAttrAllowVal implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���� */
	private GeSaleProductDuty geSaleProductDuty;

	/** ��������Id */
	private String attributeKind;

	/** ������������ */
	private String attributeName;

	/** ��������ֵ���� */
	private String allowValuesType;

	/** ������������ */
	private String attributeType;

	/** ����������������ֵ */
	private String attributeTypeValue;

	/** ��������ֵ */
	private String attributeValue;

	/** �������ֵ */
	private String maxValue;

	/** ������Сֵ */
	private String minValue;

	/**
	 * ��GeSaleProDutyAttrAllowVal��Ĭ�Ϲ��췽��
	 */
	public GeSaleProDutyAttrAllowVal() {
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
	 * ���Ե�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTDUTYSERIALNO")
	public GeSaleProductDuty getGeSaleProductDuty() {
		return this.geSaleProductDuty;
	}
	/**
	 * ���Ե�setter����
	 */
	public void setGeSaleProductDuty(GeSaleProductDuty geSaleProductDuty) {
		this.geSaleProductDuty = geSaleProductDuty;
	}
	/**
	 * ��������Id��getter����
	 */

	@Column(name = "ATTRIBUTEKIND")
	public String getAttributeKind() {
		return this.attributeKind;
	}
	/**
	 * ��������Id��setter����
	 */
	public void setAttributeKind(String attributeKind) {
		this.attributeKind = attributeKind;
	}
	/**
	 * �����������Ƶ�getter����
	 */

	@Column(name = "ATTRIBUTENAME")
	public String getAttributeName() {
		return this.attributeName;
	}
	/**
	 * �����������Ƶ�setter����
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	/**
	 * ��������ֵ���͵�getter����
	 */

	@Column(name = "ALLOWVALUESTYPE")
	public String getAllowValuesType() {
		return this.allowValuesType;
	}
	/**
	 * ��������ֵ���͵�setter����
	 */
	public void setAllowValuesType(String allowValuesType) {
		this.allowValuesType = allowValuesType;
	}
	/**
	 * �����������͵�getter����
	 */

	@Column(name = "ATTRIBUTETYPE")
	public String getAttributeType() {
		return this.attributeType;
	}
	/**
	 * �����������͵�setter����
	 */
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}
	/**
	 * ����������������ֵ��getter����
	 */

	@Column(name = "ATTRIBUTETYPEVALUE")
	public String getAttributeTypeValue() {
		return this.attributeTypeValue;
	}
	/**
	 * ����������������ֵ��setter����
	 */
	public void setAttributeTypeValue(String attributeTypeValue) {
		this.attributeTypeValue = attributeTypeValue;
	}
	/**
	 * ��������ֵ��getter����
	 */

	@Column(name = "ATTRIBUTEVALUE")
	public String getAttributeValue() {
		return this.attributeValue;
	}
	/**
	 * ��������ֵ��setter����
	 */
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	/**
	 * �������ֵ��getter����
	 */

	@Column(name = "MAXVALUE")
	public String getMaxValue() {
		return this.maxValue;
	}
	/**
	 * �������ֵ��setter����
	 */
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	/**
	 * ������Сֵ��getter����
	 */

	@Column(name = "MINVALUE")
	public String getMinValue() {
		return this.minValue;
	}
	/**
	 * ������Сֵ��setter����
	 */
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

}