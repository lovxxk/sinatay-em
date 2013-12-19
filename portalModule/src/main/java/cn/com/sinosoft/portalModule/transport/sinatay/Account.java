package cn.com.sinosoft.portalModule.transport.sinatay;

import java.math.BigDecimal;

/**
 * LCCont -> LCAppnt -> Risk -> Account节点DTO
 *
 */
public class Account implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 属性 交费账户编码  */
	private String accNo;

	/** 属性 交费账户金额 */
	private BigDecimal accMoney;
	
	/** 属性 交费账户比率 */
	private BigDecimal accRate;

	/**
	 * 属性 accNo 的 getter 方法
	 * @return the accNo
	 */
	public String getAccNo() {
		return accNo;
	}

	/**
	 * 属性 accNo 的 setter 方法
	 * @param accNo the accNo to set
	 */
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	/**
	 * 属性 accMoney 的 getter 方法
	 * @return the accMoney
	 */
	public BigDecimal getAccMoney() {
		return accMoney;
	}

	/**
	 * 属性 accMoney 的 setter 方法
	 * @param accMoney the accMoney to set
	 */
	public void setAccMoney(BigDecimal accMoney) {
		this.accMoney = accMoney;
	}

	/**
	 * 属性 accRate 的 getter 方法
	 * @return the accRate
	 */
	public BigDecimal getAccRate() {
		return accRate;
	}

	/**
	 * 属性 accRate 的 setter 方法
	 * @param accRate the accRate to set
	 */
	public void setAccRate(BigDecimal accRate) {
		this.accRate = accRate;
	}
	
}
