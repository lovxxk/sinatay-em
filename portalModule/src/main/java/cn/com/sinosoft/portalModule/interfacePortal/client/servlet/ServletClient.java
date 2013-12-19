package cn.com.sinosoft.portalModule.interfacePortal.client.servlet;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import cn.com.sinosoft.portalModule.interfacePortal.client.PortalClient;
import cn.com.sinosoft.util.string.StringUtils;

public class ServletClient implements PortalClient {
	
	public ServletClient(){}
	
	/**
	 * 调用中间接口平台Servlet客户端
	 * @param requestURL 请求URL
	 * @param requestXML 请求报文
	 * @param charset 编码,不传默认GBK
	 * @return 响应报文String
	 * @throws Exception
	 */
	public static String sendRequestXML(String requestURL, String requestXML, String charset) throws Exception {
		InputStream inputStream = null;
		try {
			if(StringUtils.isBlank(charset))charset = "GBK";
			URL url = new URL(requestURL);
			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			OutputStream outputStream = urlConn.getOutputStream();

			byte[] bytes = requestXML.getBytes(charset);
			outputStream.write(bytes);
			outputStream.flush();
			outputStream.close();
			inputStream = urlConn.getInputStream();
			byte[] rBytes = IOUtils.toByteArray(inputStream);
			return new String(rBytes, charset);
		} catch (Exception e) {
			throw e;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

}
