package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class OrderStatus extends EnumDictionary {


	/**
	 * ����״̬
	 */
	private static final long serialVersionUID = -4597647754756125385L;

	public static final OrderStatus INPUTED_QUOTE_INFO = new OrderStatus("INPUTED_QUOTE_INFO", "��¼��������Ϣ", 10);
	
	public static final OrderStatus INPUTED_APPLICANT_INFO = new OrderStatus("INPUTED_APPLICANT_INFO", "��¼��Ͷ������Ϣ", 20);
	
	public static final OrderStatus INPUTED_INSURED_INFO = new OrderStatus("INPUTED_INSURED_INFO", "��¼�뱻��������Ϣ", 30);
	
	public static final OrderStatus INPUTED_BENEFICIARY_INFO = new OrderStatus("INPUTED_BENEFICIARY_INFO", "��¼����������Ϣ", 40);
	
	public static final OrderStatus INPUTED_LOGISTICDISTRIBUTION_INFO = new OrderStatus("INPUTED_LOGISTICDISTRIBUTION_INFO", "��¼������������Ϣ", 50);
	
	public static final OrderStatus UNDERWRITING_SUBMIT = new OrderStatus("UNDERWRITING_SUBMIT", "�ύ�˱�", 60);
	
	public static final OrderStatus UNDERWRITING_PROCESS = new OrderStatus("UNDERWRITING_PROCESS", "�˱�������", 61);
	
	public static final OrderStatus UNDERWRITING_SUCCESS = new OrderStatus("UNDERWRITING_SUCCESS", "�˱��ɹ�", 62);
	
	public static final OrderStatus UNDERWRITING_FAILURE = new OrderStatus("UNDERWRITING_FAILURE", "�˱�ʧ��", 63);
	
	public static final OrderStatus INSURED_SUBMIT = new OrderStatus("INSURED_SUBMIT", "�ύ�б�", 70);
	
	public static final OrderStatus INSURED_PROCESS = new OrderStatus("INSURED_PROCESS", "�б�������", 71);
	
	public static final OrderStatus INSURED_SUCCESS = new OrderStatus("INSURED_SUCCESS", "�б��ɹ�", 72);
	
	public static final OrderStatus INSURED_FAILURE = new OrderStatus("INSURED_FAILURE", "�б�ʧ��", 73);
	
	public static final OrderStatus PAYMENTFAILURE = new OrderStatus("PAYMENTFAILURE", "֧��ʧ��", 80);
	
	public static final OrderStatus PAYMENTSUCCESS = new OrderStatus("PAYMENTSUCCESS", "֧���ɹ�", 81);
	
	public static final OrderStatus UNPAID = new OrderStatus("UNPAID", "δ֧��", 82);
	
	public static final OrderStatus PAYMENT = new OrderStatus("PAYMENT", "֧����", 83);
	
	public static final OrderStatus PAID = new OrderStatus("PAID", "��֧��", 84);

	public static final OrderStatus GENERATED_EPOLICY = new OrderStatus("GENERATED_EPOLICY", "���ӱ���", 101);
	
	public OrderStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
		// TODO Auto-generated constructor stub
	}
}
