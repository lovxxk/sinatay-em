package cn.com.sinosoft.portalModule.transport.service.facade;

import java.util.Map;

public interface HttpAgreementService {

	/**
	 * ����http post���� ����\����
	 * @param REQUEST_URI �����ַ
	 * @param codeType �������ͣ��磺GBK
	 * @param parameterMap ����Map
	 * @return ������Ӧ��Ϣ
	 * @throws Exception
	 */
	public String sendHttpPostMessageAndgetHttpPostMessage(String REQUEST_URI, String codeType, Map parameterMap) throws Exception;
	
	/**
     * Post ��ʽ���� XML
     * @param requestUrl �����ַ
     * @param xmlData XML����
     * @param contentType such as "application/xml"
     * @param charset such as "UTF-8" or "GBK"
     * @return
     */
	public String postXmlRequest(String requestUrl, String xmlData, String contentType, String charset);
}
