package cn.com.sinosoft.ebusiness.service.spring;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.bankAccount.domain.PaymentAccount;
import cn.com.sinosoft.businessModule.bankAccount.service.facade.PaymentAccountService;
import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyService;
import cn.com.sinosoft.ebusiness.service.facade.InsuranceService;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.sinatay.PayInfo;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

public class PaymentServiceSpringImpl extends InsuranceService {
	
	@Autowired
	private InsurancePolicyService insPolService;
	
	@Autowired
	private PaymentAccountService paymentAccountService;
	
	private OrderForm orderForm = new OrderForm();
	
	@Override
	protected InsuranceVerifiable handleRequest(InsuranceVerifiable ins) {
		
		TXInsurance txIns = (TXInsurance) ins;
		TranRequest tranReq = (TranRequest) txIns.getBusinessDatum().get(0);
		PayInfo payInfo = tranReq.getPayInfo();
		String proposalContNo = payInfo.getProposalContNo();
		Map<String,String> params = new HashMap<String,String>();
		params.put("applicationNumber",proposalContNo);
		//根据投保单号取保单对象
		InsurancePolicy insPol = insPolService.findInsurancePolicyByQueryMap(params);
		//取得保单对象关联的订单对象,不处理inspol null
		orderForm = insPol.getOrderForm();
		BigDecimal orderAmount = orderForm.getOrderAmount();
		
		//比较该单总费记录与报文中的总费是否一致。
		BigDecimal prem = payInfo.getPrem();
		
		TranResponse tranRes = new TranResponse();
		//txIns.getBusinessDatum().set(0, tranRes);
		if(!prem.equals(orderAmount)){
			tranRes.setFlag("0");
			tranRes.setDesc(String.format("投保单号%s的订单待支付的总保费应为%s",proposalContNo,orderAmount.toString()));
			txIns.getBusinessDatum().set(0, tranRes);
			return txIns;
		}
			
		String payResult = "success";
		if(!payResult.equals("success")){
			tranRes.setFlag("0");
			tranRes.setDesc("支付失败！");
			txIns.getBusinessDatum().set(0, tranRes);
			return txIns;
		}
		tranRes.setFlag("1");
		tranRes.setOutTradeNo(orderForm.getSerialNo());
		
		savaInfoBeforeResponse(txIns);
		
		txIns.getBusinessDatum().set(0, tranRes);
		return txIns;
	}

	@Override
	protected InsuranceVerifiable savaInfoBeforeRequest(InsuranceVerifiable ins) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected InsuranceVerifiable savaInfoBeforeResponse(InsuranceVerifiable ins) {
		TXInsurance txIns = (TXInsurance) ins;
		TranRequest tranReq = (TranRequest) txIns.getBusinessDatum().get(0);
		PayInfo payInfo = tranReq.getPayInfo();
		
		//支付报文存 paymentaccount
		PaymentAccount paymentAccount = new PaymentAccount();
		paymentAccount.setAccCity(payInfo.getAccCity());
		paymentAccount.setAccountNumber(payInfo.getBankAccNo());
		paymentAccount.setAccountNumberConfirm(payInfo.getBankAccNo());
		
		//属性对账日期？？？？？？？
		paymentAccount.setAccountTime(null);
		paymentAccount.setAccProvince(payInfo.getAccProvince());
		
		/** 属性帐号持有人Id ????????????????*/
		paymentAccount.setAcctHolderId("");
		paymentAccount.setAcctHolderName(payInfo.getAccName());
		paymentAccount.setAcctIdNumber(payInfo.getAppntIDNo());
		paymentAccount.setAcctMobilePhone(payInfo.getAppntMobile());
		
		/** 属性银行帐号类型 *??????????????*/
		paymentAccount.setBankAcctType(Integer.parseInt(payInfo.getAccBankCode()));
		
		paymentAccount.setBankAcctTypeByCoreValue(payInfo.getAccType());
		
		//银行帐号类型商家值???????????????????
		paymentAccount.setBankAcctTypeByMerchantValue("");
		paymentAccount.setBankBranchCode("");
		paymentAccount.setBankBranchName("");
		paymentAccount.setBankCardCVTwo(payInfo.getCreditCvv2());
		paymentAccount.setBankCode(payInfo.getAccBankCode());
		
		//查询bankname;
		paymentAccount.setBankName("");
		//paymentAccount.setBody("");
		paymentAccount.setAccProvince(payInfo.getAccProvince());
		paymentAccount.setAccCity(payInfo.getAccCity());
		paymentAccount.setCardEffectiveDate(payInfo.getCreditValid());
		paymentAccount.setPaymentType(payInfo.getPayType());
		
		//订单号
		paymentAccount.setPayOrderSerialNumber(orderForm.getOrderSerialNumber());
		paymentAccountService.addPaymentAccount(paymentAccount);
		
		return txIns;
	}

}
