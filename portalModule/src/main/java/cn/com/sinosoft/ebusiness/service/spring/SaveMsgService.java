package cn.com.sinosoft.ebusiness.service.spring;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.enumUtil.RequestProcessStatus;
import cn.com.sinosoft.portalModule.enumUtil.SaveMessageType;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceRuleFactorService;
import cn.com.sinosoft.portalModule.portalTransaction.service.fascade.PortalTransactionService;

public class SaveMsgService {

	@Autowired
	private PortalInterfaceRuleFactorService portalInterfaceRuleFactorService;

	@Autowired
	private PortalTransactionService portalTransactionService;

	// �ڷ���������֮ǰ�洢��InsuranceVerifiableʵ���������õ���xml���ģ����洢�������ĵı���
	@Async
	public void saveXmlMessage(String requestXml, MessageType messageType, RequestProcessStatus status) {
		try {
			Element baseInfo = DocumentHelper.parseText(requestXml).getRootElement().element("BaseInfo");
			String transType = baseInfo.elementText("FunctionFlag");
			String source = baseInfo.elementText("Source");
			String transRefGUID = baseInfo.elementText("TransrNo");
			
			// ��ѯ�洢���ĵ�����
			SaveMessageType saveMsgType = portalInterfaceRuleFactorService.findPortalInterfaceRuleFactorSaveMessageType(transType, source);

			if (saveMsgType == SaveMessageType.DATABASE) {
				portalTransactionService.insertAndUpdatePortalTransaction(source, transType, requestXml, transRefGUID, null, null, messageType, status);
			} else if (saveMsgType == SaveMessageType.FILESYSTEM) {

			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
