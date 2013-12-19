package cn.com.sinosoft.ebusiness.sale.web;

import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.sinosoft.util.string.StringUtils;

public class JsonstatusAction extends Struts2Action {
	
	private static Logger log = LoggerFactory.getLogger(JsonstatusAction.class);
	
	static final private String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	private static final long serialVersionUID = 1L;
	
	public String doSendJSON(){
		try{
			ServletActionContext.getResponse().setContentType(CONTENT_TYPE);
			HttpServletRequest request = super.getRequest();
			
			String tagFlag = (request.getParameter("tagFlag")==null)?"":request.getParameter("tagFlag").toString();
			String urlFlag = (request.getParameter("urlFlag")==null)?"":request.getParameter("urlFlag").toString();
			String JsonSTR = (request.getParameter("JsonSTR")==null)?"":request.getParameter("JsonSTR").toString();
			String productCode = (request.getParameter("productCode")==null)?"":request.getParameter("productCode").toString();
			
//			System.out.println(this.getClass().getName()+" JsonSTR=="+JsonSTR);
//			System.out.println(Data.getCurrentDateTime("yyyy-MM-dd HH:mm:ss")+"=="+JsonSTR);
			
			request.setAttribute("tagFlag", tagFlag);
			request.setAttribute("urlFlag", urlFlag);
			request.setAttribute("JsonSTR", JsonSTR.replaceAll("\"", "\\\\\""));
			request.setAttribute("productCode", productCode);
			
			return urlFlag;
		}catch(Exception e){
			log.info(StringUtils.exception2String(e));
		}
		return NONE;
	}
	
	public String doSendURL(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			ServletActionContext.getResponse().setContentType(CONTENT_TYPE);
			HttpServletRequest request = super.getRequest();
			
			String JsonSTR = (request.getParameter("JsonSTR")==null)?"":request.getParameter("JsonSTR").toString();
			
			request.getSession().setAttribute("JsonSTR", JsonSTR.replaceAll("\"", "\\\\\""));
			
		}catch(Exception e){
			log.info(StringUtils.exception2String(e));
		}finally{
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
		return NONE;
	}
}
