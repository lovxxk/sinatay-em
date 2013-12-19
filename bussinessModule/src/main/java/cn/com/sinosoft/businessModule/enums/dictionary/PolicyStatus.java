package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class PolicyStatus extends EnumDictionary {

	private static final long serialVersionUID = 2768087728634705993L;

	public static final PolicyStatus NO_SUCH_ORDER = new PolicyStatus("NO_SUCH_ORDER", "�޼�¼", 0, "0" ,"0");

	public static final PolicyStatus WAITING_PROPOSAL = new PolicyStatus("WAITING_PROPOSAL", "���˱�", 1, "11" ,"1");

	public static final PolicyStatus PROPOSAL_SUCC = new PolicyStatus("PROPOSAL_SUCC", "�˱�ͨ��", 2, "2" ,"2");

	public static final PolicyStatus PROPOSAL_FAIL = new PolicyStatus("PROPOSAL_FAIL", "�˱�ʧ��", 3, "3" ,"3");

	public static final PolicyStatus WAITING_POLICY = new PolicyStatus("WAITING_POLICY", "������", 4, "41" ,"4");

	public static final PolicyStatus POLICY_SUCC = new PolicyStatus("POLICY_SUCC", "�����ɹ�", 5, "5" ,"5");

	public static final PolicyStatus POLICY_FAIL = new PolicyStatus("POLICY_FAIL", "����ʧ��", 6, "6" ,"6");

	public static final PolicyStatus POLICY_VALID = new PolicyStatus("POLICY_VALID", "��Ч����", 7, "1" ,"7");

	public static final PolicyStatus POLICY_INVALID = new PolicyStatus("POLICY_INVALID", "��Ч����", 8, "4" ,"8");
	
	public static final PolicyStatus SURRENDER_INSURANCE = new PolicyStatus("SURRENDER_INSURANCE", "�˱�", 81, "81" ,"81");
	
	public static final PolicyStatus HESITATE_PERIOD_SURRENDER_INSURANCE = new PolicyStatus("HESITATE_PERIOD_SURRENDER_INSURANCE", "��ԥ���˱�", 82, "82" ,"82");
	
	public PolicyStatus(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
		// TODO Auto-generated constructor stub
	}


	public PolicyStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

}
