package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class OrderType extends EnumDictionary {


	/**
	 * 订单类型
	 */
	private static final long serialVersionUID = -4597647754756125385L;
	
	public static final OrderType QUOTE_INFO = new OrderType("QUOTE_INFO", "试算信息", 1);
	
	public static final OrderType APPLICANT_INFO = new OrderType("APPLICANT_INFO", "投保人信息", 2);
	
	public static final OrderType INSURED_INFO = new OrderType("INSURED_INFO", "被保险人信息", 3);
	
	public static final OrderType BENEFICIARY_INFO = new OrderType("BENEFICIARY_INFO", "受益人信息", 4);
	
	public static final OrderType LOGISTICDISTRIBUTION = new OrderType("LOGISTICDISTRIBUTION", "物流配送信息", 5);
	
	public static final OrderType UNDERWRITING = new OrderType("UNDERWRITING", "核保", 6);
	
	public static final OrderType INSURED = new OrderType("INSURED", "承保", 7);
	
	public static final OrderType PAY = new OrderType("PAY", "支付", 8);
	
	public static final OrderType PAID = new OrderType("PAID", "已支付", 9);
	
	public static final OrderType EPOLICY = new OrderType("EPOLICY", "电子保单", 10);
	
	public OrderType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
