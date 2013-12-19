package cn.com.sinosoft.ebusiness.service.policyService.service.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.sinosoft.ebusiness.sale.service.facade.SmsSendService;
import cn.com.sinosoft.ebusiness.service.policyService.domain.PayAccInfo;
import cn.com.sinosoft.ebusiness.service.policyService.service.facade.PartReceiveService;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.service.facade.MaxNoService;

public class PartReceiveServiceSpringImpl implements PartReceiveService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MaxNoService maxNoService;
	
	@Autowired
	private WSClientHelper wsclientHelper;
	
	@Autowired
	SmsSendService smsSendService;

	public PayAccInfo findPayAccInfoByContNo(String contNo) {		
		
		Document docRequest;
		Document docResponse = null;
		String sRequest;
		String sResponse;
		
		docRequest = createXml("ST000025");
		Element tTranRequest = (Element) docRequest
				.selectSingleNode("/TranData/TranRequest");
		Element tQueryData = tTranRequest.addElement("QueryData");
		Element tQueryType = tQueryData.addElement("QueryType");
		tQueryType.setText("08");
		Element tParams = tQueryData.addElement("Params");
		Element tParamCount = tParams.addElement("ParamCount");
		tParamCount.setText("1");
		Element tParam = tParams.addElement("Param");
		Element tKey = tParam.addElement("Key");
		Element tValue = tParam.addElement("Value");
		tKey.setText("ContNo");
		tValue.setText(contNo);
		try {
			sRequest = wsclientHelper.doc2String(docRequest);
			sResponse = wsclientHelper.submitGBK(sRequest);
			docResponse = wsclientHelper.string2Doc(sResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PayAccInfo payAccInfo = new PayAccInfo();
		
		String flag = docResponse.selectSingleNode("/TranData/TranResponse/Flag").getText();
		String desc = docResponse.selectSingleNode("/TranData/TranResponse/Desc").getText();
		if(!"1".equals(flag)){
			throw new RuntimeException(desc);
		}
		
		payAccInfo.setAccType(docResponse.selectSingleNode("/TranData/TranResponse/QueryData/Datas/Data/Col01").getText());
		payAccInfo.setBankType(docResponse.selectSingleNode("/TranData/TranResponse/QueryData/Datas/Data/Col02").getText());
		payAccInfo.setBankProvince(docResponse.selectSingleNode("/TranData/TranResponse/QueryData/Datas/Data/Col03").getText());
		payAccInfo.setBankCity(docResponse.selectSingleNode("/TranData/TranResponse/QueryData/Datas/Data/Col04").getText());
		payAccInfo.setCardType(docResponse.selectSingleNode("/TranData/TranResponse/QueryData/Datas/Data/Col05").getText());
		payAccInfo.setAccNo(docResponse.selectSingleNode("/TranData/TranResponse/QueryData/Datas/Data/Col06").getText());
		payAccInfo.setAccName(docResponse.selectSingleNode("/TranData/TranResponse/QueryData/Datas/Data/Col07").getText());
		payAccInfo.setCvv(docResponse.selectSingleNode("/TranData/TranResponse/QueryData/Datas/Data/Col08").getText());
		payAccInfo.setCvdate(docResponse.selectSingleNode("/TranData/TranResponse/QueryData/Datas/Data/Col09").getText());
		payAccInfo.setBranchName(docResponse.selectSingleNode("/TranData/TranResponse/QueryData/Datas/Data/Col10").getText());
		payAccInfo.setPhone(docResponse.selectSingleNode("/TranData/TranResponse/QueryData/Datas/Data/Col11").getText());
		
		return payAccInfo;
	}

	@Override
	public Map<String, Object> calculate(String tbFlag, String contNo,
			String applyMoney) {
		
		Document docRequest;
		Document docResponse = null;
		String sRequest;
		String sResponse;
		Map<String, Object> result = new HashMap<String, Object>();

		if ("1".equals(tbFlag)) {
			docRequest = createXml("ST000038");
			Element tTranRequest = (Element) docRequest
					.selectSingleNode("/TranData/TranRequest");
			Element tLCCont = tTranRequest.addElement("LCCont");
			Element tContNo = tLCCont.addElement("ContNo");
			tContNo.setText(contNo);
		} else {
			docRequest = createXml("ST000036");
			Element tTranRequest = (Element) docRequest
					.selectSingleNode("/TranData/TranRequest");
			Element tLCCont = tTranRequest.addElement("LCCont");
			Element tContNo = tLCCont.addElement("ContNo");
			tContNo.setText(contNo);
			Element tApplyMoney = tLCCont.addElement("ApplyMoney");
			tApplyMoney.setText(applyMoney);
		}

		try {
			sRequest = wsclientHelper.doc2String(docRequest);
			sResponse = wsclientHelper.submitGBK(sRequest);
			docResponse = wsclientHelper.string2Doc(sResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.put("flag",
				docResponse.selectSingleNode("/TranData/TranResponse/Flag")
						.getText());
		result.put("desc",
				docResponse.selectSingleNode("/TranData/TranResponse/Desc")
						.getText());

		// 处理成功的场合
		if ("1".equals(docResponse.selectSingleNode(
				"/TranData/TranResponse/Flag").getText())) {
			if ("1".equals(tbFlag)) {
				result.put(
						"detailArray",
						docResponse.selectSingleNode(
								"/TranData/TranResponse/DetailArray").getText());
			} else {
				result.put("getMoney",
						docResponse.selectSingleNode("/TranData/TranResponse/LCCont/GetMoney").getText());
				result.put("pdSxFee",
						docResponse.selectSingleNode("/TranData/TranResponse/LCCont/PdSxFee").getText());
				result.put("loanBJ",
						docResponse.selectSingleNode("/TranData/TranResponse/LCCont/LoanBJ").getText());
				result.put("loanLX",
						docResponse.selectSingleNode("/TranData/TranResponse/LCCont/LoanLX").getText());
				result.put("leavaAccBala",
						docResponse.selectSingleNode("/TranData/TranResponse/LCCont/LeavaAccBala").getText());
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> confirm(String tbFlag, String contNo,
			String applyMoney) {

		Document docRequest;
		Document docResponse = null;
		String sRequest;
		String sResponse;
		Map<String, Object> result = new HashMap<String, Object>();

		if ("1".equals(tbFlag)) {
			docRequest = createXml("ST000039");
			Element tTranRequest = (Element) docRequest
					.selectSingleNode("/TranData/TranRequest");
			Element tLCCont = tTranRequest.addElement("LCCont");
			Element tContNo = tLCCont.addElement("ContNo");
			tContNo.setText(contNo);
		} else {
			docRequest = createXml("ST000037");
			Element tTranRequest = (Element) docRequest
					.selectSingleNode("/TranData/TranRequest");
			Element tLCCont = tTranRequest.addElement("LCCont");
			Element tContNo = tLCCont.addElement("ContNo");
			tContNo.setText(contNo);
			Element tApplyMoney = tLCCont.addElement("ApplyMoney");
			tApplyMoney.setText(applyMoney);
		}

		try {
			sRequest = wsclientHelper.doc2String(docRequest);
			sResponse = wsclientHelper.submitGBK(sRequest);
			docResponse = wsclientHelper.string2Doc(sResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.put("flag",
				docResponse.selectSingleNode("/TranData/TranResponse/Flag")
						.getText());
		result.put("desc",
				docResponse.selectSingleNode("/TranData/TranResponse/Desc")
						.getText());

		// 处理成功的场合
		if ("1".equals(docResponse.selectSingleNode(
				"/TranData/TranResponse/Flag").getText())) {
			if ("1".equals(tbFlag)) {
				result.put(
						"detailArray",
						docResponse.selectSingleNode(
								"/TranData/TranResponse/DetailArray").getText());
			} else {
				result.put("getMoney",
						docResponse.selectSingleNode("/TranData/TranResponse/LCCont/GetMoney").getText());
				result.put("pdSxFee",
						docResponse.selectSingleNode("/TranData/TranResponse/LCCont/PdSxFee").getText());
				result.put("loanBJ",
						docResponse.selectSingleNode("/TranData/TranResponse/LCCont/LoanBJ").getText());
				result.put("loanLX",
						docResponse.selectSingleNode("/TranData/TranResponse/LCCont/LoanLX").getText());
				result.put("leavaAccBala",
						docResponse.selectSingleNode("/TranData/TranResponse/LCCont/LeavaAccBala").getText());
			}
		}
		return result;
	}

	@Override
	public String getPhoneCheckNo(String phone) {
		String checkNo = createCheckNo();
		String msgComment = "尊敬的用户,您正在办理保单剩余价值领取服务,验证码为：" + checkNo + "。该验证码有效期为30分钟，请及时输入该验证码进行保单剩余价值领取服务。";
		smsSendService.smsSend(false, "", null, "123", phone, msgComment, "9005", "");
		return checkNo;
	}	
	
	private String createCheckNo(){
		String number = (""+(Math.random()*10000000)).substring(0, 6).replace(".", "8");
		return number;
	}
	

	protected Document createXml(String functionFlag) {
		Document doc = null;
		doc = DocumentHelper.createDocument();
		Element tTranData = doc.addElement("TranData");
		Element tBaseInfo = tTranData.addElement("BaseInfo");
		Element tTransrDate = tBaseInfo.addElement("TransrDate");
		Element tTransrTime = tBaseInfo.addElement("TransrTime");
		Element tTellerNo = tBaseInfo.addElement("TellerNo");
		Element tTransrNo = tBaseInfo.addElement("TransrNo");
		Element tSaleChannel = tBaseInfo.addElement("SaleChannel");
		Element tSellType = tBaseInfo.addElement("SellType");
		Element tFunctionFlag = tBaseInfo.addElement("FunctionFlag");
		Element tSource = tBaseInfo.addElement("Source");
		tTransrDate.addText(getCurrentDateNoGap());
		tTransrTime.addText(getCurrentTimeNoGap());
		tTransrNo.addText(createTellerNo());
		tFunctionFlag.addText(functionFlag);
		tSaleChannel.addText("W");
		tSellType.addText("20");
		tSource.addText("MALL");
		Element tTranRequest = tTranData.addElement("TranRequest");

		return doc;
	}

	/**
	 * 得到当前系统日期 author: YT
	 * 
	 * @return 当前日期的格式字符串,日期格式为"yyyyMMdd"
	 */
	private String getCurrentDateNoGap() {
		GregorianCalendar tGCalendar = new GregorianCalendar();
		StringBuffer tStringBuffer = new StringBuffer(10);
		int sYears = tGCalendar.get(Calendar.YEAR);
		tStringBuffer.append(sYears);
		int sMonths = tGCalendar.get(Calendar.MONTH) + 1;
		if (sMonths < 10) {
			tStringBuffer.append('0');
		}
		tStringBuffer.append(sMonths);
		int sDays = tGCalendar.get(Calendar.DAY_OF_MONTH);
		if (sDays < 10) {
			tStringBuffer.append('0');
		}
		tStringBuffer.append(sDays);
		String tString = tStringBuffer.toString();
		return tString;
	}

	/**
	 * 得到当前系统时间 author: YT
	 * 
	 * @return 当前时间的格式字符串，时间格式为"HH:mm:ss"
	 */
	private String getCurrentTimeNoGap() {
		GregorianCalendar tGCalendar = new GregorianCalendar();
		StringBuffer tStringBuffer = new StringBuffer(8);
		int sHOUR = tGCalendar.get(Calendar.HOUR_OF_DAY);
		if (sHOUR < 10) {
			tStringBuffer.append('0');
		}
		tStringBuffer.append(sHOUR);
		int sMINUTE = tGCalendar.get(Calendar.MINUTE);
		tStringBuffer.append(":");
		if (sMINUTE < 10) {
			tStringBuffer.append('0');
		}
		tStringBuffer.append(sMINUTE);
		int sSECOND = tGCalendar.get(Calendar.SECOND);
		tStringBuffer.append(":");
		if (sSECOND < 10) {
			tStringBuffer.append('0');
		}
		tStringBuffer.append(sSECOND);
		String tString = tStringBuffer.toString();
		return tString;
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 按时间生成独一无二的文件名，不包括后缀 数据库唯一，产生编码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createTellerNo() {
		String tDate = sdf.format(new Date());
		String idx = maxNoService.findMaxNo("MALL");
		String pattern = "000000000";
		java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
		return "MAL" + tDate + df.format(Long.parseLong(idx));
	}

	public static void main(String[] args) {
		PartReceiveServiceSpringImpl r = new PartReceiveServiceSpringImpl();
		r.confirm("1", "100001931622", "500");

		// new PartReceiveServiceSpringImpl().createTellerNo();
		// System.out.println(ConfigUtil.getString("sinatayUrl"));
	}

}
