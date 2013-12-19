package test;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.context.support.GenericXmlApplicationContext;

import cn.com.sinosoft.portalModule.transport.service.InterfaceTransportService;
import cn.com.sinosoft.portalModule.transport.sinatay.Account;
import cn.com.sinosoft.portalModule.transport.sinatay.LCAppnt;
import cn.com.sinosoft.portalModule.transport.sinatay.LCBnf;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.portalModule.transport.sinatay.LCInsured;
import cn.com.sinosoft.portalModule.transport.sinatay.Risk;
import cn.com.sinosoft.portalModule.transport.sinatay.TellInfo;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsuranceExtension;
import cn.com.sinosoft.portalModule.transport.transactionObject.TransMessage;

public class transportTest {
	public static void main(String[] args) {
		try {
			GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
			ctx.load(new String[]{
					"classpath:/spring/applicationContext.xml",
					"classpath:/spring/applicationContext-interfacePortal.xml",
					"classpath:/spring/applicationContext-dataAccess.xml",
					"classpath:/spring/applicationContext-hibernate.xml"});
			ctx.refresh();
			InterfaceTransportService interfaceTransportService = ctx.getBean("interfaceTransportService", InterfaceTransportService.class);
			
			TransMessage transMessage = new TransMessage();
			TXInsuranceExtension insuranceExtension = new TXInsuranceExtension();
			transMessage.setInsuranceExtension(insuranceExtension);
			
			TXInsurance txInsurance = new TXInsurance();
			
			TranRequest tranRequest = new TranRequest();
			LCCont lcCont = new LCCont();
			lcCont.setPolApplyDate(new Date());
			lcCont.setDisputedFlag("1");
			
			//告知
			TellInfo tellInfo = new TellInfo();
			tellInfo.setTellVersion("01");
			tellInfo.setTellCode("001");
			
			//投保人
			LCAppnt lcAppnt = new LCAppnt();
			lcAppnt.setFullName("Test");
			lcAppnt.setGender("1");
			lcAppnt.setBirthDate(new Date());
			lcAppnt.setIdType("0");
			lcAppnt.setIdNumber("410105199001090103");
			lcAppnt.setIdEndDate("21130822");
			lcAppnt.setMobilePhoneNumber("13588856884");
			lcAppnt.setHousehold("330000");
			lcAppnt.setProvince("330000");
			lcAppnt.setCity("330100");
			lcAppnt.setCounty("330102");
			lcAppnt.setHomeAddress("浙江省杭州市上城区定安路68号");
			lcAppnt.setHomeZipCode("310001");
			lcAppnt.setMailAddress("浙江省杭州市上城区定安路68号");
			lcAppnt.setMailZipCode("310001");
			lcAppnt.setGrpName("职业中学");
			lcAppnt.setEmail("abcd@126.com");
			lcAppnt.setJobType("1");
			lcAppnt.setJobCode("3010101");
			lcAppnt.setNationality("CHN");
			lcAppnt.setRelaToInsured("00");
			
			lcAppnt.getTellInfos().add(tellInfo);
			lcAppnt.setTellInfoCount(lcAppnt.getTellInfos().size());
			
			//被保人
			LCInsured lcInsured = new LCInsured();
			lcInsured.setFullName("Test");
			lcInsured.setGender("1");
			lcInsured.setBirthDate(new Date());
			lcInsured.setIdType("0");
			lcInsured.setIdNumber("410105199001090103");
			lcInsured.setIdEndDate("21130822");
			lcInsured.setMobilePhoneNumber("13588856884");
			lcInsured.setHousehold("330000");
			lcInsured.setProvince("330000");
			lcInsured.setCity("330100");
			lcInsured.setCounty("330102");
			lcInsured.setHomeAddress("浙江省杭州市上城区定安路68号");
			lcInsured.setHomeZipCode("310001");
			lcInsured.setMailAddress("浙江省杭州市上城区定安路68号");
			lcInsured.setMailZipCode("310001");
			lcInsured.setGrpName("职业中学");
			lcInsured.setEmail("abcd@126.com");
			lcInsured.setJobType("1");
			lcInsured.setJobCode("3010101");
			lcInsured.setNationality("CHN");
			lcInsured.setRelaToMain("00");
			
			lcInsured.getTellInfos().add(tellInfo);
			lcInsured.setTellInfoCount(lcInsured.getTellInfos().size());
			
			//险种
			Risk risk = new Risk();
			risk.setRiskCode("00116600");
			risk.setMainRiskCode("00116600");
			risk.setAmnt(new BigDecimal(100000.0));
			risk.setPrem(new BigDecimal(2390.0));
			risk.setMult(1);
			risk.setPayIntv("12");
			risk.setPayEndYearFlag("Y");
			risk.setPayEndYear("5");
			risk.setInsuYearFlag("Y");
			risk.setInsuYear("30");
			risk.setAutoPayFlag("0");
			risk.setAutoRNewFlag("-1");
			
			//险种账户
			Account riskAccount = new Account();
			
			//受益人
			LCBnf lcBnf = new LCBnf();
			
			risk.getLcBnfs().add(lcBnf);
			risk.setLcBnfCount(risk.getLcBnfs().size());
			
			risk.getAccounts().add(riskAccount);
			risk.setAccountCount(risk.getAccounts().size());
			
			lcInsured.getRisks().add(risk);
			lcInsured.setRiskCount(lcInsured.getRisks().size());
			
			lcCont.setLcAppnt(lcAppnt);
			lcCont.getLcInsureds().add(lcInsured);
			lcCont.setLcInsuredCount(lcCont.getLcInsureds().size());
			
			tranRequest.setLcCont(lcCont);
			
			Object tranResponse = interfaceTransportService.sendRPCRequestXML(tranRequest, txInsurance, "ST000021");
			System.out.println(tranResponse);
			System.out.println(tranResponse.getClass());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
}