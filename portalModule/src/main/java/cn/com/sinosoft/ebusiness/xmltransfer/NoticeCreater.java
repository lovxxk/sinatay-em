package cn.com.sinosoft.ebusiness.xmltransfer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.infomanage.domain.NoticeInfo;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

public class NoticeCreater extends XmlCreater implements
		Creater<Map<String, Object>> {

	private final static String sTranRequest = "/TranData/TranRequest";
	private final static String sTranResponse = "/TranData/TranResponse";
	private static Logger log = LoggerFactory.getLogger(EmailCreater.class);
	// 交易码设置
	private final static String functionFlag = "ST000050";
	@Autowired
	private GeCodeService geCodeService;

	@SuppressWarnings("unchecked")
	@Override
	public Document createXml(Map<String, Object> map) {

		Document doc = null;
		doc = super.createXml(functionFlag);
		Element tTranRequest = (Element) doc.selectSingleNode(sTranRequest);
		Element tQueryData = tTranRequest.addElement("QueryData");
		Element tParams = tQueryData.addElement("Params");
		// 多个绑定的保单号
		List<String> policyNos = (List<String>) map.get("policyNos");
		for (String policyNo : policyNos) {
			Element tParam = tParams.addElement("Param");
			Element Param = tParam.addElement("Key");
			Param.setText("ContNo");
			Element Value = tParam.addElement("Value");
			Value.setText(policyNo);

		}
		// 通知书类型
		Element tNoticeTypeParam = tParams.addElement("Param");
		Element noticeTypeKey = tNoticeTypeParam.addElement("Key");
		noticeTypeKey.setText("NoticeType");
		Element noticeTypeValue = tNoticeTypeParam.addElement("Value");
		noticeTypeValue.setText((String) map.get("noticeType"));

		// 开始日期
		Element tStartDateParam = tParams.addElement("Param");
		Element startDateParam = tStartDateParam.addElement("Key");
		startDateParam.setText("StartDate");
		Element startDateValue = tStartDateParam.addElement("Value");
		startDateValue.setText((String) map.get("startDate"));

		// 结束日期
		Element tEndDateParam = tParams.addElement("Param");
		Element endDateParam = tEndDateParam.addElement("Key");
		endDateParam.setText("EndDate");
		Element endDateValue = tEndDateParam.addElement("Value");
		endDateValue.setText((String) map.get("endDate"));

		return doc;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> Xml2Object(Document doc) {
		log.info("提示信息：解析通知书报文！");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("flag", doc.selectSingleNode(sTranResponse + "/Flag")
				.getText());
		Element desc = (Element) doc.selectSingleNode(sTranResponse + "/Desc");
		result.put("desc", desc.getText());
		List<NoticeInfo> noticeInfos = new ArrayList<NoticeInfo>();
		if (doc.selectSingleNode(sTranResponse + "/Flag").getText().equals("1")) {

			Element tNoticeList = (Element) doc.selectSingleNode(sTranResponse
					+ "/NoticeList");

			List<Element> tNotices = tNoticeList.elements("Notice");

			for (Element tNotice : tNotices) {
				NoticeInfo noticeInfo = new NoticeInfo();
				noticeInfo.setPolicyNo(tNotice.element("ContNo").getText());
				String noticeType = tNotice.element("NoticeType").getText();
				// 数据字典 找到通知书字段名称
				String noticeTypeName = geCodeService.findCodeCName(noticeType,
						"MailSend");
				noticeInfo.setNoticeTypeName(noticeTypeName);
				noticeInfo.setNoticeType(noticeType);

				noticeInfo.setNoticeNo(tNotice.element("NoticeNo").getText());
				noticeInfo.setSendDate(tNotice.element("SendTime").getText());
				noticeInfo.setRiskName(tNotice.element("RiskName").getText());
				noticeInfos.add(noticeInfo);
			}
		}
		//排序
		Collections.sort(noticeInfos);
		result.put("noticeInfos", noticeInfos);

		return result;

	}

}
