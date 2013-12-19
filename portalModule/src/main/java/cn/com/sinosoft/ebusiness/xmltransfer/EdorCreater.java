package cn.com.sinosoft.ebusiness.xmltransfer;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.sinosoft.ebusiness.infomanage.domain.Edor;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

public class EdorCreater extends XmlCreater implements
		Creater<Map<String, Object>> {
	
	private final static String sTranRequest = "/TranData/TranRequest";
	private final static String sTranResponse = "/TranData/TranResponse";
	private static Logger log = LoggerFactory
			.getLogger(EdorCreater.class);
	// 交易码设置
	private static String functionFlag = "ST000045";

	

	@Override
	public Document createXml(Map<String, Object> map) {
		Document doc = null;
		doc = super.createXml(functionFlag);
		Element tTranRequest = (Element) doc.selectSingleNode(sTranRequest);
		Element tLCCont = tTranRequest.addElement("LCCont");
		Element tContNo = tLCCont.addElement("ContNo");
		// 增加值
		tContNo.setText((String) map.get("policyNo"));

		return doc;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> Xml2Object(Document doc) {
		try {
			log.info("----保单详细页面报文转化开始-------");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("flag", doc.selectSingleNode(sTranResponse + "/Flag")
					.getText());
			Element desc = (Element) doc.selectSingleNode(sTranResponse
					+ "/Desc");
			result.put("desc", desc.getText());
			Element tLPEdorItems = (Element) doc.selectSingleNode(sTranResponse
					+ "/LPEdorItems");

			
			
			Element tEdorCount = tLPEdorItems.element("EdorCount");
		
			//保全信息没有 1 ：EdorCount=0；
			String edorCount=tEdorCount.getText();
			List<Edor> edors = new ArrayList<Edor>();
			//与保全信息
			if(!edorCount.equals("0")&&result.get("flag").equals("1")){
				
				DecimalFormat df = new DecimalFormat();
				df = new DecimalFormat("0.00");
				List<Element> tEdorItems = tLPEdorItems.elements("EdorItem");
				for (Element tEdorItem : tEdorItems) {
					Edor edor = new Edor();
					edor.setAcceptNo(tEdorItem.element("AcceptNo").getText());
					edor.setEdorSource(tEdorItem.element("EdorSource").getText());
					edor.setEdorType(tEdorItem.element("EdorType").getText());

					edor.setGetMoney(df.format(df.parse(tEdorItem.element(
							"GetMoney").getText())));

					edor.setEdorValiDate(tEdorItem.element("EdorValiDate")
							.getText());
					edors.add(edor);
				}
			}
			//排序
			Collections.sort(edors);
			result.put("edors", edors);
			return result;

		} catch (ParseException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return null;

	}

}
