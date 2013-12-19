package cn.com.sinosoft.ebusiness.sale.service.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.enums.dictionary.TransType;
import cn.com.sinosoft.ebusiness.sale.service.facade.VerificationService;
import cn.com.sinosoft.portalModule.transport.service.InterfaceTransportService;
import cn.com.sinosoft.portalModule.transport.sinatay.IdCardCheck;
import cn.com.sinosoft.portalModule.transport.sinatay.IdCardCheckResult;
import cn.com.sinosoft.portalModule.transport.sinatay.LCAppnt;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.portalModule.transport.sinatay.RetData;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.util.string.StringUtils;

public class VerificationServiceSpringImpl implements VerificationService {

	@Autowired
	private InterfaceTransportService interfaceTransportService;
	
	/**
	 * 身份证校验
	 * @param idNumber 身份证
	 * @param fullName 姓名
	 * @param takes_place 发生地
	 * @param businessType 业务类型
	 * @return
	 */
	public Map<String, String> verificationIdCard(String idNumber, String fullName, String takes_place, String businessType) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String flag = "0";
		String desc = "";
		StringBuilder results = new StringBuilder();
		try {
			TXInsurance txInsurance = new TXInsurance();
			TranRequest tranRequest = new TranRequest();
			
			tranRequest.setIdCardCheckCode("2");
			
			IdCardCheck idCardCheck = new IdCardCheck();
			idCardCheck.setGmsfhm(idNumber);
			idCardCheck.setFsd(takes_place);
			idCardCheck.setXm(fullName);
			idCardCheck.setYwlx(businessType);
			
			tranRequest.getIdCardChecks().add(idCardCheck);
			
			Object object = interfaceTransportService.sendServletRequestXML(tranRequest, txInsurance, TransType.ST010001.getCoreValue());
			if(object != null){
				if (object.getClass().getName().equals(TranResponse.class.getName())){
					TranResponse tranResponse = (TranResponse) object;
					flag = tranResponse.getFlag();
					desc = tranResponse.getDesc();
					List<IdCardCheck> idCardChecks = tranResponse.getIdCardChecks();
					if(tranResponse.getFlag().equals("0") && idCardChecks!=null && !idCardChecks.isEmpty()){
						IdCardCheck _idCardCheck = idCardChecks.get(0);
						for(IdCardCheckResult _idCardCheckResult : _idCardCheck.getItems()){
							
							if(_idCardCheckResult.getResult_gmsfhm() != null){
								results.append(_idCardCheckResult.getResult_gmsfhm());
							}
							if(_idCardCheckResult.getResult_xm() != null){
								results.append(_idCardCheckResult.getResult_xm());
							}
						}
						if("一致一致".equals(results.toString())){
							flag = "1";
						}else{
							desc="身份证不匹配！";
						}
					}
				}else if (object.getClass().getName().equals(RetData.class.getName())){
					RetData retData = (RetData) object;
					//flag = retData.getFlag();
					desc = retData.getDesc();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("flag", flag);
		resultMap.put("desc", desc);
		return resultMap;
	}

	/**
	 * 手机号校验
	 * @param fullName 姓名
	 * @param idType 证件类型
	 * @param idNumber 证件号码
	 * @param mobilePhoneNumber 手机号
	 * @return
	 */
	public Map<String, String> verificationMobilePhoneNumber(String fullName, String idType, String idNumber, String mobilePhoneNumber) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String flag = "0";
		String desc = "";
		try {
			TXInsurance txInsurance = new TXInsurance();
			TranRequest tranRequest = new TranRequest();
			LCCont lcCont = new LCCont();
			LCAppnt lcAppnt = new LCAppnt();
			lcAppnt.setFullName(fullName);
			lcAppnt.setIdType(idType);
			lcAppnt.setIdNumber(idNumber);
			lcAppnt.setMobilePhoneNumber(mobilePhoneNumber);
			
			lcCont.setLcAppnt(lcAppnt);
			tranRequest.setLcCont(lcCont);
			
			Object object = interfaceTransportService.sendServletRequestXML(tranRequest, txInsurance, TransType.ST000028.getCoreValue());
			if(object != null){
				if (object.getClass().getName().equals(TranResponse.class.getName())){
					TranResponse tranResponse = (TranResponse) object;
					flag = tranResponse.getFlag();
					desc = tranResponse.getDesc();
				}else if (object.getClass().getName().equals(RetData.class.getName())){
					RetData retData = (RetData) object;
					flag = retData.getFlag();
					desc = retData.getDesc();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("flag", flag);
		resultMap.put("desc", desc);
		return resultMap;
	}

}
