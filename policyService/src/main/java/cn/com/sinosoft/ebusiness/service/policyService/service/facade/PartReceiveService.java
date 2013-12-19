package cn.com.sinosoft.ebusiness.service.policyService.service.facade;

import java.util.Map;

import cn.com.sinosoft.ebusiness.service.policyService.domain.PayAccInfo;

public interface PartReceiveService {

	PayAccInfo findPayAccInfoByContNo(String contNo);

	Map<String, Object> calculate(String tbFlag, String contNo, String applyMoney);
	
	Map<String, Object> confirm(String tbFlag, String contNo, String applyMoney);
	
	String getPhoneCheckNo(String phone);

}