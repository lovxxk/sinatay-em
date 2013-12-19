package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类GeProDutyAttrAllowVal
 */
@Entity
@Table(name = "GE_PRODUTY_ATTR_ALLOWVAL")
public class GeProDutyAttrAllowVal implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性产品责任 */
	private GeProductDuty geProductDuty;

	/** 属性属性Id */
	private String attributeKind;

	/** 属性属性名称 */
	private String attributeName;

	/** 属性允许值类型 */
	private String allowValuesType;

	/** 属性属性类型 */
	private String attributeType;

	/** 属性属性类型允许值 */
	private String attributeTypeValue;  //

	/** 属性属性值 */
	private String attributeValue;//

	/** 属性最大值 */
	private String maxValue;

	/** 属性最小值 */
	private String minValue;

	/**
	 * 类GeProDutyAttrAllowVal的默认构造方法
	 */
	public GeProDutyAttrAllowVal() {
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
	 * 属性产品责任的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTDUTYSERIALNO")
	public GeProductDuty getGeProductDuty() {
		return this.geProductDuty;
	}
	/**
	 * 属性产品责任的setter方法
	 */
	public void setGeProductDuty(GeProductDuty geProductDuty) {
		this.geProductDuty = geProductDuty;
	}
	/**
	 * 属性属性Id的getter方法
	 */

	@Column(name = "ATTRIBUTEKIND")
	public String getAttributeKind() {
		return this.attributeKind;
	}
	/**
	 * 属性属性Id的setter方法
	 */
	public void setAttributeKind(String attributeKind) {
		this.attributeKind = attributeKind;
	}
	/**
	 * 属性属性名称的getter方法
	 */

	@Column(name = "ATTRIBUTENAME")
	public String getAttributeName() {
		return this.attributeName;
	}
	/**
	 * 属性属性名称的setter方法
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	/**
	 * 属性允许值类型的getter方法
	 */

	@Column(name = "ALLOWVALUESTYPE")
	public String getAllowValuesType() {
		return this.allowValuesType;
	}
	/**
	 * 属性允许值类型的setter方法
	 */
	public void setAllowValuesType(String allowValuesType) {
		this.allowValuesType = allowValuesType;
	}
	/**
	 * 属性属性类型的getter方法
	 */

	@Column(name = "ATTRIBUTETYPE")
	public String getAttributeType() {
		return this.attributeType;
	}
	/**
	 * 属性属性类型的setter方法
	 */
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}
	/**
	 * 属性属性类型允许值的getter方法
	 */

	@Column(name = "ATTRIBUTETYPEVALUE")
	public String getAttributeTypeValue() {
		return this.attributeTypeValue;
	}
	/**
	 * 属性属性类型允许值的setter方法
	 */
	public void setAttributeTypeValue(String attributeTypeValue) {
		this.attributeTypeValue = attributeTypeValue;
	}
	/**
	 * 属性属性值的getter方法
	 */

	@Column(name = "ATTRIBUTEVALUE")
	public String getAttributeValue() {
		return this.attributeValue;
	}
	/**
	 * 属性属性值的setter方法
	 */
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	/**
	 * 属性最大值的getter方法
	 */

	@Column(name = "MAXVALUE")
	public String getMaxValue() {
		return this.maxValue;
	}
	/**
	 * 属性最大值的setter方法
	 */
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	/**
	 * 属性最小值的getter方法
	 */

	@Column(name = "MINVALUE")
	public String getMinValue() {
		return this.minValue;
	}
	/**
	 * 属性最小值的setter方法
	 */
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

}
