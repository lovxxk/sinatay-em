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
 * POJO类GeProInsuredOccupation
 */
@Entity
@Table(name = "GE_PRO_INSURED_OCCUPATION")
public class GeProInsuredOccupation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性被保人配置 */
	private GeProductInsuredConfig geProductInsuredConfig;

	/** 属性职业代码 */
	private String occupationCode;

	/**
	 * 类GeProInsuredOccupation的默认构造方法
	 */
	public GeProInsuredOccupation() {
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
	 * 属性被保人配置的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSUREDSERIALNO")
	public GeProductInsuredConfig getGeProductInsuredConfig() {
		return this.geProductInsuredConfig;
	}
	/**
	 * 属性被保人配置的setter方法
	 */
	public void setGeProductInsuredConfig(
			GeProductInsuredConfig geProductInsuredConfig) {
		this.geProductInsuredConfig = geProductInsuredConfig;
	}
	/**
	 * 属性职业代码的getter方法
	 */

	@Column(name = "OCCUPATIONCODE")
	public String getOccupationCode() {
		return this.occupationCode;
	}
	/**
	 * 属性职业代码的setter方法
	 */
	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}

}
