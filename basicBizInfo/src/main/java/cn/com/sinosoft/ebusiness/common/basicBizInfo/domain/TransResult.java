package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

/**
 * ���׽������
 *
 */
public class TransResult {
	
	/**
	 * 0 	���׳ɹ�	
	 * 1 	����ʧ�ܣ��쳣�� 	
	 * 2	���ͱ��ı�������	
	 * 3	���ĸ�ʽ����	
	 */
	private String resultCode;

	
	private String resultInfoDesc;
	
	public String getResultInfoDesc() {
		return resultInfoDesc;
	}

	public void setResultInfoDesc(String resultInfoDesc) {
		this.resultInfoDesc = resultInfoDesc;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}


	
	
	
	
}
