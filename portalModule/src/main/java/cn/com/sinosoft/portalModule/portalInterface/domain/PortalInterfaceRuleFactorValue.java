package cn.com.sinosoft.portalModule.portalInterface.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PORTAL_INTERFACE_RULEFACTORVAL")
public class PortalInterfaceRuleFactorValue implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 属性序号 */
	private String serialNo;
	
	/** 属性 因子值代码 */
	private String valueCode;
	
	/** 属性 因子值名称 */
	private String valueName;
	
	/** 属性 因子值类型名称 */
	private Integer factorValueType;
	
	/** 属性 因子值 */
	private String cvalue;
	
	/** 属性 最小值 */
	private String minValue;
	
	/** 属性 最大值 */
	private String maxValue;
	
	/** 属性 状态(0-未开通  1 -开通) */
	private String status;
	
	/** 属性 操作员 */
	private String operatorID;
	
	/** 属性 创建时间 */
	private Date createTime = new Date();

	/** 属性 更新时间 */
	private Date updateTime = new Date();
	
	private PortalInterfaceRuleFactor portalInterfaceRuleFactor;

	/**
	 * @return the serialNo
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 32)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * @return the valueCode
	 */
	@Column(name = "VALUECODE")
	public String getValueCode() {
		return valueCode;
	}

	/**
	 * @param valueCode the valueCode to set
	 */
	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}

	/**
	 * @return the valueName
	 */
	@Column(name = "VALUENAME")
	public String getValueName() {
		return valueName;
	}

	/**
	 * @param valueName the valueName to set
	 */
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
	
	/**
	 * @return the factorValueType
	 */
	@Column(name = "FACTORVALUETYPE")
	public Integer getFactorValueType() {
		return factorValueType;
	}

	/**
	 * @param factorValueType the factorValueType to set
	 */
	public void setFactorValueType(Integer factorValueType) {
		this.factorValueType = factorValueType;
	}

	/**
	 * @return the cvalue
	 */
	@Column(name = "CVALUE")
	public String getCvalue() {
		return cvalue;
	}

	/**
	 * @param cvalue the cvalue to set
	 */
	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}

	/**
	 * @return the minValue
	 */
	@Column(name = "MINVALUE")
	public String getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue the minValue to set
	 */
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return the maxValue
	 */
	@Column(name = "MAXVALUE")
	public String getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue the maxValue to set
	 */
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the status
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the operatorID
	 */
	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return operatorID;
	}

	/**
	 * @param operatorID the operatorID to set
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	/**
	 * @return the createTime
	 */
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FACTORSERIALNO")
	public PortalInterfaceRuleFactor getPortalInterfaceRuleFactor() {
		return portalInterfaceRuleFactor;
	}

	public void setPortalInterfaceRuleFactor(PortalInterfaceRuleFactor portalInterfaceRuleFactor) {
		this.portalInterfaceRuleFactor = portalInterfaceRuleFactor;
	}

	
}
