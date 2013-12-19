package cn.com.sinosoft.portalModule.transport.service.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import cn.com.sinosoft.portalModule.transport.service.facade.HttpAgreementService;


public class HttpAgreementServiceSpringImpl implements HttpAgreementService {

	/**
	 * 基于http post请求 发送\接收
	 * @param REQUEST_URI 请求地址
	 * @param codeType 编码类型，如：GBK
	 * @param parameterMap 参数Map
	 * @return 返回响应信息
	 * @throws Exception
	 */
	public String sendHttpPostMessageAndgetHttpPostMessage(String REQUEST_URI, String codeType, Map parameterMap) throws Exception {
		HttpClient httpclient = new HttpClient();
		PostMethod postMethod = new PostMethod(REQUEST_URI);// serviceURL
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);	//10s
		httpclient.getHttpConnectionManager().getParams().setSoTimeout(10000);    //10s

		httpclient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, codeType);
		
		if(parameterMap != null && parameterMap.size()>0){
			Set<Map.Entry<String, String>> set = parameterMap.entrySet();
			NameValuePair[] postData = new NameValuePair[set.size()];
			int count = 0;
	        for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
	            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
//	            System.out.println(entry.getKey() + " ---> " + entry.getValue());
	            NameValuePair par = new NameValuePair(entry.getKey(),entry.getValue());
	            postData[count] = par;
	            count++;
	        }
	        postMethod.setRequestBody(postData);
		}
		
//		System.out.println("URI: "+postMethod.getURI());
//		System.out.println("Parameters: "+Arrays.asList(postMethod.getParameters()));
//		postMethod.setRequestBody(parsexml);// requestMsg
		// 返回状态
		int statusCode = httpclient.executeMethod(postMethod);
//		System.out.println("statusCode: "+statusCode);
		// 返回数据
		String responseMsg = postMethod.getResponseBodyAsString();
//		System.out.println("responseMsg: "+responseMsg);
		String str = responseMsg;
		BufferedReader sr = new BufferedReader(new StringReader(str));
		StringBuilder ss = new StringBuilder();
		while (true) {
			String reader = sr.readLine();
			if (reader == null) {
				break;
			}
			ss.append(reader);
		}
//		System.out.println(ss.toString());
		
		return ss.toString();
	}
	
	
	/**
     * Post 方式发送 XML
     * @param requestUrl 请求地址
     * @param xmlData XML数据
     * @param contentType such as "application/xml"
     * @param charset such as "UTF-8" or "GBK"
     * @return
     */
    public String postXmlRequest(String requestUrl, String xmlData, String contentType, String charset){
        //init PostMethod object.
        PostMethod post = new PostMethod(requestUrl);
        String bodyContent = "";
        try {
            //wrape the request entity.
            RequestEntity requestEntity = new StringRequestEntity(xmlData, contentType, charset);
            post.setRequestEntity(requestEntity);
            HttpClient httpClient = new HttpClient();
            // send the post http request and reture status code.
            int statusCode = httpClient.executeMethod(post);
            // get reture content from server side.
            bodyContent = post.getResponseBodyAsString();
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            //close the connection.
            post.releaseConnection();
        }
        
        
        return bodyContent;
    }

}
