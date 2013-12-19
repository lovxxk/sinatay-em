package cn.com.sinosoft.ebusiness.xmltransfer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.sinosoft.ebusiness.infomanage.domain.Appnt;
import cn.com.sinosoft.ebusiness.infomanage.domain.Beneficiary;
import cn.com.sinosoft.ebusiness.infomanage.domain.Insured;
import cn.com.sinosoft.ebusiness.infomanage.domain.Policy;
import cn.com.sinosoft.ebusiness.infomanage.domain.Risk;
import cn.com.sinosoft.ebusiness.infomanage.domain.RiskAccount;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;
import cn.com.sinosoft.lang.math.IntegerToChinese;

public class PolicyDetailCreater extends XmlCreater implements
		Creater<Map<String, Object>> {
	private final static String sTranRequest = "/TranData/TranRequest";
	private final static String sTranResponse = "/TranData/TranRequest";
	private static Logger log = LoggerFactory
			.getLogger(PolicyDetailCreater.class);

	// 交易码设置
	private static String functionFlag = "ST000029";

	@Override
	public Document createXml(Map<String, Object> map) {
		log.info("系统调用类PolicyDetailCreater中createXml方法创建请求报文....");
		Document doc = null;
		doc = super.createXml(functionFlag);
		Element tTranRequest = (Element) doc.selectSingleNode(sTranRequest);
		Element tLCCont = tTranRequest.addElement("LCCont");
		Element tContNo = tLCCont.addElement("ContNo");
		// 增加值
		tContNo.setText((String) map.get("policyNo"));

		return doc;

	}

	@Override
	public Map<String, Object> Xml2Object(Document doc) {
		log.info("系统调用类PolicyDetailCreater中Xml2Object方法创建对象....");

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("flag", doc.selectSingleNode(sTranResponse + "/Flag")
					.getText());
			Element desc = (Element) doc.selectSingleNode(sTranResponse
					+ "/Desc");
			result.put("desc", desc.getText());
			// 保单信息，封装后台报文查询获得数据
			Policy policy = new Policy();
			if (doc.selectSingleNode(sTranResponse + "/Flag").getText()
					.equals("1")) {

				Element tLccont = (Element) doc.selectSingleNode(sTranResponse
						+ "/LCCont");

				// 设置保单号
				String policyNo = tLccont.element("ContNo").getText();
				policy.setPolicyNo(policyNo);
				// 设置保单来源 NETS
				String souseFlag = tLccont.element("SouseFlag").getText();
				policy.setSource(souseFlag);
				// 获得账户价值
				policy.setRiskAccount(getRiskAccount(tLccont));
				// 设置保单信息
				getPolicyInfo(tLccont,policy);

				// 设置投保人信息
				Element tLCAppnt = (Element) doc.selectSingleNode(sTranResponse
						+ "/LCCont/LCAppnt");
				policy.setAppnt(getAppntByPolicyNo(tLCAppnt));
				// 设置被保人信息
				Element tLCInsureds = (Element) doc
						.selectSingleNode(sTranResponse + "/LCCont/LCInsureds");
				// 目前系统被保人只能是一个
				Element tLCInsured = (Element) tLCInsureds
						.elements("LCInsured").get(0);
				policy.setInsured(getInsuredByPolicyNo(tLCInsured,policy));
				result.put("policy", policy);
			}

		} catch (Exception e) {
			log.info("系统异常："+e.getMessage());
			result.put("flag", "0");
			result.put("desc", "系统异常....");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 从返回报文中获取账户价值信息
	 * 
	 * @param tLccont
	 * @return
	 * @throws Exception
	 */
	private RiskAccount getRiskAccount(Element tLccont) throws Exception {
		
		DecimalFormat df = new DecimalFormat();
		df = new DecimalFormat("0.00");
		RiskAccount riskAccount = new RiskAccount();
		// 险种名称
		riskAccount.setRiskName(tLccont.element("RiskName").getText());
		// 有问题
		// 险种是否为账户型

		riskAccount.setInsuaccFlag(tLccont.element("WNFlag").getText());
		// 险种账户余额
		riskAccount.setCashValue(df.format(df.parse(tLccont
				.element("CountBala").getText())));
		// 险种来源即保单来源
		riskAccount.setRiskScource(tLccont.element("SouseFlag").getText());

		return riskAccount;
	}

	/**
	 * 设置保单的基本信息
	 * 
	 * @param policyNo
	 */
	private void getPolicyInfo(Element tLccont,Policy policy) {
		// 特殊处理，未失效改为有效
		String contState = tLccont.element("ContState").getText();
		if("未失效".equals(contState)){
			policy.setState("有效");
		}else{
			policy.setState(contState);
		}		
		policy.setValiDate(tLccont.element("CValiDate").getText());
		policy.setComName(tLccont.element("ComName").getText());
		policy.setComAddress(tLccont.element("ComAddress").getText());
		String isVisit = tLccont.element("IsVisit").getText();
		if (isVisit.equals("0")) {
			isVisit = "否";
		} else {
			isVisit = "是";
		}
		policy.setIsVisit(isVisit);
	}

	/**
	 * 设置险种信息
	 * 
	 * @param tRisks
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<Risk> getRisksByPolicyNo(Element tRisks,Policy policy) throws Exception {
		
		DecimalFormat df = new DecimalFormat();
		df = new DecimalFormat("0.00");
		List<Element> eRisks = tRisks.elements("Risk");
		// 所有险种
		List<Risk> risks = new ArrayList<Risk>();
		for (Element eRisk : eRisks) {
			Risk risk = new Risk();
			risk.setRiskCode(eRisk.element("RiskCode").getText());
			risk.setPolNo(eRisk.element("PolNo").getText());
			risk.setCvaliDate(eRisk.element("CValiDate").getText());
			risk.setRiskName(eRisk.element("RiskName").getText());
			risk.setPrem(df.format(df.parse(eRisk.element("Prem").getText())));
			risk.setAmnt(df.format(df.parse(eRisk.element("Amnt").getText())));
			risk.setInsuYear(eRisk.element("InsuYear").getText());
			risk.setInsuYearFlag(eRisk.element("InsuYearFlag").getText());
			risk.setState(eRisk.element("State").getText());
			// 判断该险种是否为主险
			String mainRiskCode = eRisk.element("MainRiskCode").getText();
			if (risk.getRiskCode().equals(mainRiskCode)) {
				risk.setSubRiskFlag("M");
				// 如果是主险，设置受益人信息
				policy.setBnfs(getBnfsByPolicy(eRisk));
				// 设置主险的
				policy.setmRisk(risk);
			} else {
				risk.setSubRiskFlag("S");
			}

			risk.setPolicyNo(policy.getPolicyNo());
			risks.add(risk);
		}
		// 设置排序
		Collections.sort(risks);
		return risks;
	}

	/**
	 * 设置受益人信息
	 * 
	 * @param tLCAppnt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Beneficiary> getBnfsByPolicy(Element eRisk) {
		NumberFormat nt = NumberFormat.getPercentInstance();
		nt = NumberFormat.getPercentInstance();
		nt.setMaximumIntegerDigits(3);
		nt.setMaximumFractionDigits(2);
		List<Beneficiary> beneficiarys = new ArrayList<Beneficiary>();
		Element eLCBnfs = eRisk.element("LCBnfs");
		Element tLCBnfCount = eLCBnfs.element("LCBnfCount");
		if (Integer.parseInt(tLCBnfCount.getText()) > 0) {
			List<Element> tLCBnfs = eLCBnfs.elements("LCBnf");
			for (Element tLCBnf : tLCBnfs) {
				Beneficiary beneficiary = new Beneficiary();
				// 将受益人顺序数组转换为中文
				beneficiary.setBeneficiaryOrder(IntegerToChinese
						.ConvertToChinese(Integer.parseInt(tLCBnf.element(
								"BnfGrade").getText())));
				beneficiary.setName(tLCBnf.element("Name").getText());
				double bnfPercent = Double.parseDouble(tLCBnf.element("BnfLot")
						.getText());
				beneficiary.setInterestPercent(nt.format(bnfPercent));
				beneficiarys.add(beneficiary);
			}
		}

		return beneficiarys;
	}

	/**
	 * 设置被保信息
	 * 
	 * @param tLCAppnt
	 * @return
	 * @throws Exception
	 */
	private Insured getInsuredByPolicyNo(Element tLCInsured,Policy policy) throws Exception {

		Insured insured = new Insured();
		insured.setName(tLCInsured.element("Name").getText());
		// 格式化日期
		insured.setBirthDay(tLCInsured.element("Birthday").getText());
		insured.setIdType(tLCInsured.element("IDType").getText());
		insured.setIdTypeName(tLCInsured.element("IDTypeName").getText());
		insured.setIdNo(tLCInsured.element("IDNo").getText());
		insured.setMarriage(tLCInsured.element("Marriage").getText());
		insured.setNationality(tLCInsured.element("Nationality").getText());
		insured.setLicenseType(tLCInsured.element("LicenseType").getText());
		insured.setJobType(tLCInsured.element("JobName").getText());
		insured.setJobCode(tLCInsured.element("JobCode").getText());
		insured.setGrpName(tLCInsured.element("GrpName").getText());
		insured.setProvince(tLCInsured.element("Province").getText());
		insured.setCity(tLCInsured.element("City").getText());
		insured.setCounty(tLCInsured.element("County").getText());
		insured.setHomeZipCode(tLCInsured.element("MailZipCode").getText());
		insured.setHomeAddress(tLCInsured.element("MailAddress").getText());
		insured.setEmail(tLCInsured.element("Email").getText());
		insured.setMobile(tLCInsured.element("InsuredMobile").getText());
		insured.setOfficePhone(tLCInsured.element("InsuredOfficePhone")
				.getText());
		insured.setFax(tLCInsured.element("InsuredFax").getText());
		insured.setPhone(tLCInsured.element("InsuredHomePhone").getText());
		String sex = tLCInsured.element("Sex").getText();
		insured.setSex(sex);
		// 设置险种信息
		Element tRisks = (Element) tLCInsured.element("Risks");
		policy.setRisks(getRisksByPolicyNo(tRisks,policy));

		return insured;

	}

	/**
	 * 设置投保信息
	 * 
	 * @param tLCAppnt
	 * @return
	 */
	private Appnt getAppntByPolicyNo(Element tLCAppnt) {
		Appnt appnt = new Appnt();
		appnt.setName(tLCAppnt.element("AppntName").getText());
		appnt.setBirthDay(tLCAppnt.element("AppntBirthday").getText());
		appnt.setIdType(tLCAppnt.element("AppntIDType").getText());
		appnt.setIdTypeName(tLCAppnt.element("AppntIDTypeName").getText());
		appnt.setIdNo(tLCAppnt.element("AppntIDNo").getText());
		appnt.setMarriage(tLCAppnt.element("Marriage").getText());
		appnt.setNationality(tLCAppnt.element("Nationality").getText());
		appnt.setLicenseType(tLCAppnt.element("LicenseType").getText());
		appnt.setJobType(tLCAppnt.element("JobName").getText());
		appnt.setJobCode(tLCAppnt.element("JobCode").getText());
		appnt.setGrpName(tLCAppnt.element("GrpName").getText());
		appnt.setProvince(tLCAppnt.element("Province").getText());
		appnt.setCity(tLCAppnt.element("City").getText());
		appnt.setCounty(tLCAppnt.element("County").getText());
		appnt.setHomeZipCode(tLCAppnt.element("MailZipCode").getText());
		appnt.setHomeAddress(tLCAppnt.element("MailAddress").getText());
		appnt.setEmail(tLCAppnt.element("Email").getText());
		String mobile = tLCAppnt.element("AppntMobile").getText();
		int index = mobile.indexOf("(");
		if (index > 0) {
			mobile = mobile.substring(0, index);
		}
		appnt.setMobile(mobile);
		appnt.setOfficePhone(tLCAppnt.element("AppntOfficePhone").getText());
		appnt.setFax(tLCAppnt.element("AppntFax").getText());
		appnt.setPhone(tLCAppnt.element("AppntHomePhone").getText());
		String sex = tLCAppnt.element("AppntSex").getText();
		appnt.setSex(sex);

		return appnt;
	}

}