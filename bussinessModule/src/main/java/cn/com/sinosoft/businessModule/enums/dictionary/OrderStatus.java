package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class OrderStatus extends EnumDictionary {


	/**
	 * 订单状态
	 */
	private static final long serialVersionUID = -4597647754756125385L;

	public static final OrderStatus INPUTED_QUOTE_INFO = new OrderStatus("INPUTED_QUOTE_INFO", "已录入试算信息", 10);
	
	public static final OrderStatus INPUTED_APPLICANT_INFO = new OrderStatus("INPUTED_APPLICANT_INFO", "已录入投保人信息", 20);
	
	public static final OrderStatus INPUTED_INSURED_INFO = new OrderStatus("INPUTED_INSURED_INFO", "已录入被保险人信息", 30);
	
	public static final OrderStatus INPUTED_BENEFICIARY_INFO = new OrderStatus("INPUTED_BENEFICIARY_INFO", "已录入受益人信息", 40);
	
	public static final OrderStatus INPUTED_LOGISTICDISTRIBUTION_INFO = new OrderStatus("INPUTED_LOGISTICDISTRIBUTION_INFO", "已录入物流配送信息", 50);
	
	public static final OrderStatus UNDERWRITING_SUBMIT = new OrderStatus("UNDERWRITING_SUBMIT", "提交核保", 60);
	
	public static final OrderStatus UNDERWRITING_PROCESS = new OrderStatus("UNDERWRITING_PROCESS", "核保处理中", 61);
	
	public static final OrderStatus UNDERWRITING_SUCCESS = new OrderStatus("UNDERWRITING_SUCCESS", "核保成功", 62);
	
	public static final OrderStatus UNDERWRITING_FAILURE = new OrderStatus("UNDERWRITING_FAILURE", "核保失败", 63);
	
	public static final OrderStatus INSURED_SUBMIT = new OrderStatus("INSURED_SUBMIT", "提交承保", 70);
	
	public static final OrderStatus INSURED_PROCESS = new OrderStatus("INSURED_PROCESS", "承保处理中", 71);
	
	public static final OrderStatus INSURED_SUCCESS = new OrderStatus("INSURED_SUCCESS", "承保成功", 72);
	
	public static final OrderStatus INSURED_FAILURE = new OrderStatus("INSURED_FAILURE", "承保失败", 73);
	
	public static final OrderStatus PAYMENTFAILURE = new OrderStatus("PAYMENTFAILURE", "支付失败", 80);
	
	public static final OrderStatus PAYMENTSUCCESS = new OrderStatus("PAYMENTSUCCESS", "支付成功", 81);
	
	public static final OrderStatus UNPAID = new OrderStatus("UNPAID", "未支付", 82);
	
	public static final OrderStatus PAYMENT = new OrderStatus("PAYMENT", "支付中", 83);
	
	public static final OrderStatus PAID = new OrderStatus("PAID", "已支付", 84);

	public static final OrderStatus GENERATED_EPOLICY = new OrderStatus("GENERATED_EPOLICY", "电子保单", 101);
	
	public OrderStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
		// TODO Auto-generated constructor stub
	}
}
