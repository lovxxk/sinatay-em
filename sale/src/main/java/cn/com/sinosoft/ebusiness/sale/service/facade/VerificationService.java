package cn.com.sinosoft.ebusiness.sale.service.facade;

import java.util.Map;

public interface VerificationService {

	/**
	 * 身份证校验
	 * @param idNumber 身份证
	 * @param fullName 姓名
	 * @param takes_place 发生地
	 * @param businessType 业务类型
	 * @return
	 */
	public Map<String, String> verificationIdCard(String idNumber, String fullName,String takes_place,String businessType);
	
	/**
	 * 手机号校验
	 * @param fullName 姓名
	 * @param idType 证件类型
	 * @param idNumber 证件号码
	 * @param mobilePhoneNumber 手机号
	 * @return
	 */
	public Map<String, String> verificationMobilePhoneNumber(String fullName, String idType, String idNumber, String mobilePhoneNumber);
	
}
