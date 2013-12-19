package cn.com.sinosoft.ebusiness.sale.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import cn.com.sinosoft.businessModule.enums.dictionary.TransType;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeSmsConfigService;
import cn.com.sinosoft.ebusiness.sale.service.facade.SmsSendService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.util.GeneratorTransSerialNumber;
import cn.com.sinosoft.portalModule.transport.service.InterfaceTransportService;
import cn.com.sinosoft.portalModule.transport.sinatay.RetData;
import cn.com.sinosoft.portalModule.transport.sinatay.SMS;
import cn.com.sinosoft.portalModule.transport.sinatay.SMSResult;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

public class SmsSendServiceSpringImpl implements SmsSendService {

	private static Logger log = LoggerFactory.getLogger(SmsSendServiceSpringImpl.class);

	@Autowired
	private GeSmsConfigService geSmsConfigService;
	
	@Autowired
	private InterfaceTransportService interfaceTransportService;
	
	/**
	 * 发送手机短信
	 * @param isUseTemplate 是否使用系统内置短信模板
	 * @param businessType 业务类型
	 * @param id 标示id（1、2、3、4、5...）
	 * @param phoneNo 手机号
	 * @param msgComment 内容
	 * @param sender 发送者(9005)
	 * @param planTime
	 * @return ({'flag','1'},{'desc',''})
	 */
	
	@Async
	public Map<String, String> smsSend(boolean isUseTemplate, String businessType, List<String> params, String id, String phoneNo, String msgComment, String sender, String planTime) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String flag = "0";
		String desc = "";
		String content = "";
		try {
			if(isUseTemplate){
				if(params!=null && !params.isEmpty()){
					String[] paramsArrays = new String[params.size()];
					content = geSmsConfigService.getSmsContent(businessType, params.toArray(paramsArrays));
				}
			}else{
				content = msgComment;
			}
			
			TXInsurance txInsurance = new TXInsurance();
			TranRequest tranRequest = new TranRequest();
			
			SMS sms = new SMS();
			sms.setId(id);
			sms.setPhoneNo(phoneNo);
			sms.setMsgComment(content);
			sms.setSender(sender);
			sms.setBusiSerialNo(GeneratorTransSerialNumber.generatorTransSerialNumber());
			
			tranRequest.getMessages().add(sms);
			
			List<Object> businessDatum = new ArrayList<Object>();
			businessDatum.add(tranRequest);
			txInsurance.setBusinessDatum(businessDatum);
			
			Object object = interfaceTransportService.sendServletRequestXML(tranRequest, txInsurance, TransType.ST020002.getCoreValue());
			if(object != null){
				if (object.getClass().getName().equals(TranResponse.class.getName())){
					TranResponse tranResponse = (TranResponse) object;
					SMSResult smsResult = tranResponse.getSmsResult();
					if(smsResult!=null){
						flag = smsResult.getFlag();
						desc = smsResult.getDesc();
					}
				}else if (object.getClass().getName().equals(RetData.class.getName())){
					RetData retData = (RetData) object;
					flag = retData.getFlag();
					desc = retData.getDesc();
				}
			}
		} catch (Exception e) {
			log.error("send phone code failed. phone number is " + phoneNo + ";" + e.getMessage());
			e.printStackTrace();
		}
		resultMap.put("flag", flag);
		resultMap.put("desc", desc);
		return resultMap;
	}

}
