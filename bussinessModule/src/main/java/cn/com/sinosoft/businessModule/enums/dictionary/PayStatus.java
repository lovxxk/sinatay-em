package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class PayStatus extends EnumDictionary {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4597647754756125385L;
	
	public static final PayStatus PAYMENTFAILURE = new PayStatus("PAYMENTFAILURE", "֧��ʧ��", 0);
	
	public static final PayStatus PAYMENTSUCCESS = new PayStatus("PAYMENTSUCCESS", "֧���ɹ�", 1);
	
	public static final PayStatus UNPAID = new PayStatus("UNPAID", "δ֧��", 2);
	
	public static final PayStatus PAYMENT = new PayStatus("PAYMENT", "֧����", 3);
	
	public static final PayStatus PAID = new PayStatus("PAID", "��֧��", 4);
	
	public PayStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
