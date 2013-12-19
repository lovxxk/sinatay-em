package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;

import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeSmsConfigService;
import cn.com.sinosoft.ebusiness.sale.service.facade.SmsSendService;

public class sendSMSTest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    try {
	    	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
			ctx.load(new String[]{
					"classpath:/spring/applicationContext.xml",
					"classpath:/spring/applicationContext-hibernate.xml",
					"classpath:/spring/applicationContext-dataAccess.xml",
					"classpath:/spring/applicationContext-sale.xml",
					"classpath:/spring/applicationContext-interfacePortal.xml",
					"classpath:/spring/applicationContext-bizConfig.xml"
					});
			ctx.refresh();
			SmsSendService smsSendService = ctx.getBean("smsSendService", SmsSendService.class);
			GeSmsConfigService geSmsConfigService = ctx.getBean("geSmsConfigService", GeSmsConfigService.class);
			List<String> params = new ArrayList<String>();
			params.add("≤‚ ‘”√ªß");
			Map<String,String> map = smsSendService.smsSend(true, "1", params, "1", "15800760896", "≤‚ ‘∂Ã–≈", "9005", null);
			System.out.println(map.get("flag")+", "+map.get("desc"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
