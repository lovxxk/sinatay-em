package cn.com.sinosoft.businessModule.bankAccount.service.facade;

import cn.com.sinosoft.businessModule.bankAccount.domain.PaymentAccount;

public interface PaymentAccountService {

	/***
	 * 添加付款账户信息
	 * @param paymentAccount
	 */
	public abstract void addPaymentAccount(PaymentAccount paymentAccount);
	
	/***
	 * 删除付款账户信息
	 * @param paymentAccount
	 */
	public abstract void deletePaymentAccount(PaymentAccount paymentAccount);

	public abstract boolean findPaymentAccountByPolicySerial(String serialNo);

}