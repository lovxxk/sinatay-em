package cn.com.sinosoft.ebusiness.service.spring;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.order.service.facade.OrderFormService;
import cn.com.sinosoft.ebusiness.service.facade.InsuranceService;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

public class PaymentStateQueryServiceSpringImpl extends InsuranceService{
	
	@Autowired
	private OrderFormService orderFormService;

	@Override
	protected InsuranceVerifiable savaInfoBeforeRequest(InsuranceVerifiable ins) {
		
		return null;
	}

	@Override
	protected InsuranceVerifiable savaInfoBeforeResponse(InsuranceVerifiable ins) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected InsuranceVerifiable handleRequest(InsuranceVerifiable ins) {
		TXInsurance txIns = (TXInsurance) ins;
		//È¡µÃtradeno
		String outTradeNo = ((TranResponse)txIns.getBusinessDatum().get(0)).getOutTradeNo();
		OrderForm orderForm = null;
		orderForm = orderFormService.findOrderFormByOrderFormSerialNumber(outTradeNo);
		TranResponse tranRes = new TranResponse();
		tranRes.setFlag(orderForm.getPayStatus().toString());
		//???????????????????????????????????????????????????
		tranRes.setDesc(orderForm.getPayStatusDesc());
		txIns.getBusinessDatum().add(0, tranRes);
		return txIns;
	}
	
	
}
