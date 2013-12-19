package cn.com.sinosoft.portalModule.transport.sinatay;

import java.math.BigDecimal;

/**
 * LCCont -> LCAppnt -> Risk -> Account�ڵ�DTO
 *
 */
public class Account implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** ���� �����˻�����  */
	private String accNo;

	/** ���� �����˻���� */
	private BigDecimal accMoney;
	
	/** ���� �����˻����� */
	private BigDecimal accRate;

	/**
	 * ���� accNo �� getter ����
	 * @return the accNo
	 */
	public String getAccNo() {
		return accNo;
	}

	/**
	 * ���� accNo �� setter ����
	 * @param accNo the accNo to set
	 */
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	/**
	 * ���� accMoney �� getter ����
	 * @return the accMoney
	 */
	public BigDecimal getAccMoney() {
		return accMoney;
	}

	/**
	 * ���� accMoney �� setter ����
	 * @param accMoney the accMoney to set
	 */
	public void setAccMoney(BigDecimal accMoney) {
		this.accMoney = accMoney;
	}

	/**
	 * ���� accRate �� getter ����
	 * @return the accRate
	 */
	public BigDecimal getAccRate() {
		return accRate;
	}

	/**
	 * ���� accRate �� setter ����
	 * @param accRate the accRate to set
	 */
	public void setAccRate(BigDecimal accRate) {
		this.accRate = accRate;
	}
	
}
