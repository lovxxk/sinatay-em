package cn.com.sinosoft.portalModule.interfacePortal.client.axis;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;

import cn.com.sinosoft.portalModule.interfacePortal.client.PortalClient;

public class AxisClient implements PortalClient {
	
	private long timeOutMilliSeconds = 1 * 60 * 1000;
	/***
	 * RPC以字符串形式发送请求数据
	 * 
	 * @param requestURL 请求地址
	 * @param namespaceURL 命名空间
	 * @param localPart 本地方法
	 * @param requestXML 请求处理报文
	 * @return 应答报文
	 */
	@SuppressWarnings("rawtypes")
	public String sendRPCRequestXML(String requestURL, String namespaceURL, String localPart, String requestXML) throws  AxisFault{
		String responseXML = null;
		RPCServiceClient serviceClient = new RPCServiceClient();
		EndpointReference targetEPR = new EndpointReference(requestURL);
		QName method = new QName(namespaceURL, localPart);
		Options options = serviceClient.getOptions();
		options.setTimeOutInMilliSeconds(timeOutMilliSeconds);
		options.setTo(targetEPR);
		Object[] opAddEntryArgs = new Object[]{requestXML};
		Class[] classes = new Class[]{String.class};
		responseXML = (String) serviceClient.invokeBlocking(method, opAddEntryArgs, classes)[0];
		serviceClient.cleanupTransport();
		this.cleanAxis2TempFiles();
		return responseXML;
	}

	/***
	 * RPC以字符串形式发送请求数据
	 * 
	 * @param requestURL 请求地址
	 * @param namespaceURL 命名空间
	 * @param localPart 本地方法
	 * @param requestXML 请求处理报文
	 * @return 应答报文
	 */
	@SuppressWarnings("rawtypes")
	public String sendRPCRequestXML(String requestURL, String namespaceURL, String localPart, List obj) throws  AxisFault{
		String responseXML = null;
		RPCServiceClient serviceClient = new RPCServiceClient();
		EndpointReference targetEPR = new EndpointReference(requestURL);
		QName method = new QName(namespaceURL, localPart);
		Options options = serviceClient.getOptions();
		options.setTimeOutInMilliSeconds(timeOutMilliSeconds);
		options.setTo(targetEPR);
		Class[] classes = new Class[obj.size()];
		for (int i =0; i < obj.size(); i++) {
			classes[i] = obj.get(i).getClass();
		}
		responseXML = (String) serviceClient.invokeBlocking(method, obj.toArray(), classes)[0];
		serviceClient.cleanupTransport();
		this.cleanAxis2TempFiles();
		return responseXML;
	}
	
	@SuppressWarnings("rawtypes")
	public Object[] sendRPCRequestMethod(String requestURL, String namespaceURL, String localPart, List param) throws  AxisFault{
			Object[] response = null;
			long startTime = System.currentTimeMillis();
			RPCServiceClient serviceClient = new RPCServiceClient();
			EndpointReference targetEPR = new EndpointReference(requestURL);
			QName method = new QName(namespaceURL, localPart);
			Options options = serviceClient.getOptions();
			options.setTimeOutInMilliSeconds(timeOutMilliSeconds);
			options.setTo(targetEPR);
			Class[] classes = new Class[param.size()];
			for (int i =0; i < param.size(); i++) {
				classes[i] = param.get(i).getClass();
			}
			System.out.println("请求服务器开始时间===========" + dateTimeFormat.format(new Date()) );
			response = serviceClient.invokeBlocking(method, param.toArray(), classes);
			System.out.println("服务器处理返回时间===========" + dateTimeFormat.format(new Date()) );
			long endTime = System.currentTimeMillis();
			System.out.println("服务器处理时间（毫秒）：========================" + (endTime - startTime));
			serviceClient.cleanupTransport();
			this.cleanAxis2TempFiles();
			return response;
		}
	
		/**
		 * 
		 *清除Axis2临时文件
		 * 
		 */
		public void cleanAxis2TempFiles(){
			String tmpdir = System.getProperty("java.io.tmpdir");
			System.out.println("当前操作系统临时文件目录=================" + tmpdir);
			try {
				File dir = new File(tmpdir);
				FileFilter fileFilter = new RegexFileFilter("^axis2[-\\w]*.tmp[.lck]*$");
				File[] files = dir.listFiles(fileFilter);
				for (int i = 0; i < files.length; i++) {
					FileUtils.deleteQuietly(files[i]);
				}
			} catch (Exception e) {
				System.out.println("当前操作系统临时文件目录=================" + tmpdir);
				System.out.println("Axis2临时文件清除异常！");
				e.printStackTrace();
			}
		}
		
}
