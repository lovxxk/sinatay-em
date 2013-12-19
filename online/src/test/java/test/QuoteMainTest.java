package test;

import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;

import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.BizCommonService;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteApplicant;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteBeneficiary;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteInsured;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteLiability;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteMainService;
import cn.com.sinosoft.ebusiness.sale.service.facade.VerificationService;

public class QuoteMainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load(new String[]{
				"classpath:/spring/applicationContext.xml",
				"classpath:/spring/applicationContext-hibernate.xml",
				"classpath:/spring/applicationContext-dataAccess.xml",
				"classpath:/spring/applicationContext-sale.xml",
				"classpath:/spring/applicationContext-interfacePortal.xml",
				"classpath:/spring/applicationContext-basicBizInfo.xml"
				});
		ctx.refresh();
		QuoteMainService quoteMainService = ctx.getBean("quoteMainService", QuoteMainService.class);
		BizCommonService bizCommonService = ctx.getBean("bizCommonService", BizCommonService.class);
		String quoteNo =  bizCommonService.takeSerialNo("04",new Date(), QuoteMain.class);
		System.out.println("### quoteNo: "+quoteNo);
		QuoteMain quoteMain = new QuoteMain();
		quoteMain.setQuoteNo(quoteNo);
		
		QuoteApplicant quoteApplicant = new QuoteApplicant();
		quoteApplicant.setFullName("Test1");
		
		QuoteInsured quoteInsured = new QuoteInsured();
		quoteInsured.setFullName("Test1");
		
		QuoteBeneficiary quoteBeneficiary = new QuoteBeneficiary();
		quoteBeneficiary.setFullName("Test1");
		
		QuoteLiability quoteLiability = new QuoteLiability();
		quoteLiability.setCoreCode("1002");
		
		quoteApplicant.addQuoteMain(quoteMain);
		quoteInsured.addQuoteMain(quoteMain);
		quoteBeneficiary.addQuoteMain(quoteMain);
		quoteLiability.addQuoteMain(quoteMain);
		
		System.out.println(quoteMain.getQuoteInsureds().size());
		System.out.println(quoteMain.getQuoteBeneficiaries().size());
		
//		System.out.println(quoteMainService.saveQuoteMain(quoteMain));
		
		List<QuoteMain> lists = new ArrayList<QuoteMain>();
		QueryRule queryRule = QueryRule.getInstance();
//		lists = quoteMainService.findAllQuoteMain(queryRule);
//		System.out.println("### lists.size(): "+lists.size());
		
		
		VerificationService verificationService = ctx.getBean("verificationService", VerificationService.class);
		String idNumber = "370921198605231516";
		String fullName = "¡ı≥ø";
		String takes_place = "330226";
		String businessType = "WEB";
		String idType = "0";
		String mobilePhoneNumber = "15800760896";
		Map<String, String> map = verificationService.verificationIdCard(idNumber, fullName, takes_place, businessType);
		
		System.out.println("verificationIdCard: "+map.get("flag")+", "+map.get("desc"));
		
		map = verificationService.verificationMobilePhoneNumber(fullName, idType, idNumber, mobilePhoneNumber);
		
		System.out.println("verificationMobilePhoneNumber: "+map.get("flag")+", "+map.get("desc"));
		
	}

}
