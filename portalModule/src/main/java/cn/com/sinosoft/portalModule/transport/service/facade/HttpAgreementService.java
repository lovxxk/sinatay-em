package cn.com.sinosoft.portalModule.transport.service.facade;

import java.util.Map;

public interface HttpAgreementService {

	/**
	 * 基于http post请求 发送\接收
	 * @param REQUEST_URI 请求地址
	 * @param codeType 编码类型，如：GBK
	 * @param parameterMap 参数Map
	 * @return 返回响应信息
	 * @throws Exception
	 */
	public String sendHttpPostMessageAndgetHttpPostMessage(String REQUEST_URI, String codeType, Map parameterMap) throws Exception;
	
	/**
     * Post 方式发送 XML
     * @param requestUrl 请求地址
     * @param xmlData XML数据
     * @param contentType such as "application/xml"
     * @param charset such as "UTF-8" or "GBK"
     * @return
     */
	public String postXmlRequest(String requestUrl, String xmlData, String contentType, String charset);
}
