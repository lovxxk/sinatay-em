package cn.com.sinosoft.businessModule.bankAccount.service.spring;

import java.util.List;

import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.businessModule.bankAccount.domain.PaymentAccount;
import cn.com.sinosoft.businessModule.bankAccount.service.facade.PaymentAccountService;

public class PaymentAccountServiceSpringImpl extends GenericDaoHibernate<PaymentAccount, String> implements PaymentAccountService {
	
	/***
	 * 添加付款账户信息
	 * @param paymentAccount
	 */
	public void addPaymentAccount(PaymentAccount paymentAccount) {
		super.save(paymentAccount);
	}
	
	/***
	 * 删除付款账户信息
	 * @param paymentAccount
	 */
	public void deletePaymentAccount(PaymentAccount paymentAccount) {
		super.delete(paymentAccount);
	}

	@Override
	public boolean findPaymentAccountByPolicySerial(String serialNo) {

		String sql = "from " + PaymentAccount.class.getName() + " where policySerialNo = ?";
		
		List<PaymentAccount> list = this.findByHql(sql, serialNo);
		
		return list.isEmpty() ? false : true;
	}
	
}
