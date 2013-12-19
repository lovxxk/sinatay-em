package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class OrderType extends EnumDictionary {


	/**
	 * ��������
	 */
	private static final long serialVersionUID = -4597647754756125385L;
	
	public static final OrderType QUOTE_INFO = new OrderType("QUOTE_INFO", "������Ϣ", 1);
	
	public static final OrderType APPLICANT_INFO = new OrderType("APPLICANT_INFO", "Ͷ������Ϣ", 2);
	
	public static final OrderType INSURED_INFO = new OrderType("INSURED_INFO", "����������Ϣ", 3);
	
	public static final OrderType BENEFICIARY_INFO = new OrderType("BENEFICIARY_INFO", "��������Ϣ", 4);
	
	public static final OrderType LOGISTICDISTRIBUTION = new OrderType("LOGISTICDISTRIBUTION", "����������Ϣ", 5);
	
	public static final OrderType UNDERWRITING = new OrderType("UNDERWRITING", "�˱�", 6);
	
	public static final OrderType INSURED = new OrderType("INSURED", "�б�", 7);
	
	public static final OrderType PAY = new OrderType("PAY", "֧��", 8);
	
	public static final OrderType PAID = new OrderType("PAID", "��֧��", 9);
	
	public static final OrderType EPOLICY = new OrderType("EPOLICY", "���ӱ���", 10);
	
	public OrderType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
