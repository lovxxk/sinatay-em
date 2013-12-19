package cn.com.sinosoft.ebusiness.payment.service.facade;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.sinosoft.businessModule.order.domain.OrderForm;

public interface PaymentService {
	public Map<String, String> alipay(OrderForm orderForm);
	
	public Map<String, Object> alipayMobileReturnMessage(HttpServletRequest request);
	
	public Map<String, Object> alipayReturnMessage(HttpServletRequest request);

	public Map<String, String> paymentToBank(OrderForm orderForm);

	public Map<String, Object> processBankPayMesage(HttpServletRequest request);
	
	public Map<String, String> directUnderwriting(OrderForm orderForm);
}
