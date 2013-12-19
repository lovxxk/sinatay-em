package test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;

public class GeUserPersonalTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	
	public void test() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load(new String[]{
				"classpath:/spring/applicationContext.xml",
				"classpath:/spring/applicationContext-user.xml",
				"classpath:/spring/applicationContext-dataAccess.xml",
				"classpath:/spring/applicationContext-hibernate.xml"});
		ctx.refresh();
		GeUserPersonalService userPersonalService = ctx.getBean("geUserPersonalService", GeUserPersonalService.class);
		
		GeUserPersonal user = new GeUserPersonal();
		user.setUserName("Test");
		user.setUserAccount("Test1");
		try {
			userPersonalService.saveUser(user);	
			System.out.println("success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
