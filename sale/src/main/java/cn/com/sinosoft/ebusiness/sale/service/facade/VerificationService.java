package cn.com.sinosoft.ebusiness.sale.service.facade;

import java.util.Map;

public interface VerificationService {

	/**
	 * ���֤У��
	 * @param idNumber ���֤
	 * @param fullName ����
	 * @param takes_place ������
	 * @param businessType ҵ������
	 * @return
	 */
	public Map<String, String> verificationIdCard(String idNumber, String fullName,String takes_place,String businessType);
	
	/**
	 * �ֻ���У��
	 * @param fullName ����
	 * @param idType ֤������
	 * @param idNumber ֤������
	 * @param mobilePhoneNumber �ֻ���
	 * @return
	 */
	public Map<String, String> verificationMobilePhoneNumber(String fullName, String idType, String idNumber, String mobilePhoneNumber);
	
}
