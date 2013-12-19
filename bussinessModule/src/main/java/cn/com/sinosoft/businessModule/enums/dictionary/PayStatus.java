package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class PayStatus extends EnumDictionary {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4597647754756125385L;
	
	public static final PayStatus PAYMENTFAILURE = new PayStatus("PAYMENTFAILURE", "支付失败", 0);
	
	public static final PayStatus PAYMENTSUCCESS = new PayStatus("PAYMENTSUCCESS", "支付成功", 1);
	
	public static final PayStatus UNPAID = new PayStatus("UNPAID", "未支付", 2);
	
	public static final PayStatus PAYMENT = new PayStatus("PAYMENT", "支付中", 3);
	
	public static final PayStatus PAID = new PayStatus("PAID", "已支付", 4);
	
	public PayStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
