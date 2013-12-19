package cn.com.sinosoft.businessModule.universalRate.domain;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类UniversalRate
 */
@Entity
@Table(name = "GE_UNIVERSAL_RATE")
public class UniversalRate implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String serialNo;
	/** 险种名称 */
	private String riskName;
	/** 结算日期 */
	private Date culDate;
	/** 结算日利率 */
	private BigDecimal dateRate;
	/** 折合年结算利率 */
	private BigDecimal yearRate;
	
	/**
	 * 类UniversalRate的默认构造方法
	 */
	public UniversalRate() {
	}

	/**
	 * 属性序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO", unique = false, nullable = false, length = 32)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name = "RISKNAME")
	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	@Column(name = "CULDATE")
	public Date getCulDate() {
		return culDate;
	}

	public void setCulDate(Date culDate) {
		this.culDate = culDate;
	}

	@Column(name = "DATERATE")
	public BigDecimal getDateRate() {
		return dateRate;
	}

	public void setDateRate(BigDecimal dateRate) {
		this.dateRate = dateRate;
	}

	@Column(name = "YEARRATE")
	public BigDecimal getYearRate() {
		return yearRate;
	}

	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}
}
