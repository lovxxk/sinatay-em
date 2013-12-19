package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class PolicyStatus extends EnumDictionary {

	private static final long serialVersionUID = 2768087728634705993L;

	public static final PolicyStatus NO_SUCH_ORDER = new PolicyStatus("NO_SUCH_ORDER", "无记录", 0, "0" ,"0");

	public static final PolicyStatus WAITING_PROPOSAL = new PolicyStatus("WAITING_PROPOSAL", "待核保", 1, "11" ,"1");

	public static final PolicyStatus PROPOSAL_SUCC = new PolicyStatus("PROPOSAL_SUCC", "核保通过", 2, "2" ,"2");

	public static final PolicyStatus PROPOSAL_FAIL = new PolicyStatus("PROPOSAL_FAIL", "核保失败", 3, "3" ,"3");

	public static final PolicyStatus WAITING_POLICY = new PolicyStatus("WAITING_POLICY", "出单中", 4, "41" ,"4");

	public static final PolicyStatus POLICY_SUCC = new PolicyStatus("POLICY_SUCC", "出单成功", 5, "5" ,"5");

	public static final PolicyStatus POLICY_FAIL = new PolicyStatus("POLICY_FAIL", "出单失败", 6, "6" ,"6");

	public static final PolicyStatus POLICY_VALID = new PolicyStatus("POLICY_VALID", "有效保单", 7, "1" ,"7");

	public static final PolicyStatus POLICY_INVALID = new PolicyStatus("POLICY_INVALID", "无效保单", 8, "4" ,"8");
	
	public static final PolicyStatus SURRENDER_INSURANCE = new PolicyStatus("SURRENDER_INSURANCE", "退保", 81, "81" ,"81");
	
	public static final PolicyStatus HESITATE_PERIOD_SURRENDER_INSURANCE = new PolicyStatus("HESITATE_PERIOD_SURRENDER_INSURANCE", "犹豫期退保", 82, "82" ,"82");
	
	public PolicyStatus(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
		// TODO Auto-generated constructor stub
	}


	public PolicyStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

}
