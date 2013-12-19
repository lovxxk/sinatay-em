package cn.com.sinosoft.ebusiness.init.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.sinosoft.util.spring.SpringContextUtil;

public class Init extends HttpServlet {

	private static final long serialVersionUID = -6554120143322007112L;
	
	private static Logger log = LoggerFactory.getLogger(Init.class);
	
	public void init() throws ServletException {
		try{
			ServletContext sc = getServletContext();
			SpringContextUtil.setAc(WebApplicationContextUtils.getRequiredWebApplicationContext(sc));
		}catch(Exception ex){
			log.error("ϵͳ����spring����������ʧ�ܣ�ʧ��ԭ��"+ex);
		}
	}

}
