package cn.com.sinosoft.businessModule.bankAccount.service.facade;

import cn.com.sinosoft.businessModule.bankAccount.domain.PaymentAccount;

public interface PaymentAccountService {

	/***
	 * ��Ӹ����˻���Ϣ
	 * @param paymentAccount
	 */
	public abstract void addPaymentAccount(PaymentAccount paymentAccount);
	
	/***
	 * ɾ�������˻���Ϣ
	 * @param paymentAccount
	 */
	public abstract void deletePaymentAccount(PaymentAccount paymentAccount);

	public abstract boolean findPaymentAccountByPolicySerial(String serialNo);

}