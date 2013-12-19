package cn.com.sinosoft.ebusiness.sale.service.spring;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.bankAccount.domain.PaymentAccount;
import cn.com.sinosoft.businessModule.enums.dictionary.LockStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.PayMethod;
import cn.com.sinosoft.businessModule.enums.dictionary.ProcessStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.TransType;
import cn.com.sinosoft.businessModule.insureInformBook.domain.InsureInformBook;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.Beneficiary;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.InsuranceApplicant;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.Insured;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicyLiability;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicyProcess;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyProcessService;
import cn.com.sinosoft.ebusiness.sale.domain.InsureFlowDto;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;
import cn.com.sinosoft.ebusiness.sale.service.facade.UnderwritingService;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.enums.EnumDataUtils;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.util.GeneratorTransSerialNumber;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalService;
import cn.com.sinosoft.portalModule.transport.service.InterfaceTransportService;
import cn.com.sinosoft.portalModule.transport.sinatay.Account;
import cn.com.sinosoft.portalModule.transport.sinatay.LCAppnt;
import cn.com.sinosoft.portalModule.transport.sinatay.LCBnf;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.portalModule.transport.sinatay.LCInsured;
import cn.com.sinosoft.portalModule.transport.sinatay.RetData;
import cn.com.sinosoft.portalModule.transport.sinatay.Risk;
import cn.com.sinosoft.portalModule.transport.sinatay.TellInfo;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.util.string.StringUtils;
import cn.com.sinosoft.util.time.DateUtils;

public class UnderwritingServiceSpringImpl implements UnderwritingService, PortalService {

	@Autowired
	private InterfaceTransportService interfaceTransportService;
	
	@Autowired
	private InsurancePolicyProcessService insurancePolicyProcessService;

	private Map<String,String> propertiesConfig;
	
	public Map<String, String> getPropertiesConfig() {
		return propertiesConfig;
	}

	public void setPropertiesConfig(Map<String, String> propertiesConfig) {
		this.propertiesConfig = propertiesConfig;
	}
	
	/**
	 * 从订单核保
	 */
	public Map<String, Object> underwriting(InsurancePolicy insurancePolicy, String transType) {
		InsureFlowDto insureFlowDto = new InsureFlowDto();
		GeUserPersonal geUserPersonal = new GeUserPersonal();
		QuoteMain quoteMain = new QuoteMain();
		quoteMain.setHxssProposalNo(insurancePolicy.getApplicationNumber()==null?"":insurancePolicy.getApplicationNumber());
		insureFlowDto.setQuoteMain(quoteMain);
		return underwriting(insureFlowDto,insurancePolicy,geUserPersonal,transType);
	}
	
	/**
	 * 核保
	 * 由ST000022核保接口改为ST000034核保（调用规则引擎）
	 */
	@Override
	public Map<String, Object> underwriting(InsureFlowDto insureFlowDto, InsurancePolicy insurancePolicy, GeUserPersonal geUserPersonal) {
		return underwriting(insureFlowDto, insurancePolicy, geUserPersonal, "ST000034");
	}
	
	/**
	 * 核保
	 */
	@SuppressWarnings("finally")
	@Override
	public Map<String, Object> underwriting(InsureFlowDto insureFlowDto, InsurancePolicy insurancePolicy, GeUserPersonal geUserPersonal, String transType){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String flag = "0";
		String desc = "";
		InsurancePolicyProcess insurancePolicyProcess = new InsurancePolicyProcess();//insurancePolicyProcessService.findInsurancePolicyProcessByMerchantOrderNumber(insurancePolicy.getQuoteNo());
		try {
			if(insurancePolicyProcess==null){
				insurancePolicyProcessService.addInsurancePolicyProcessByInsurancePolicy(insurancePolicy);
				insurancePolicyProcess = insurancePolicyProcessService.findInsurancePolicyProcessByMerchantOrderNumber(insurancePolicy.getQuoteNo());
			}
			insurancePolicyProcess.setEnumLockStatus(LockStatus.LOCKED);
			insurancePolicyProcess.setLockedReason("数据库写入操作未处理完成,请稍后重试!");
//			insurancePolicyProcessService.updateInsurancePolicyProcess(insurancePolicyProcess);
			
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID(GeneratorTransSerialNumber.generatorTransSerialNumber());
			txInsurance.setTransType(transType);
			txInsurance.setTransExeDate(DateUtils.formatDate(new Date(), "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(new Date(), "HHmmss"));
			txInsurance.setTellerNo("86010000");
			txInsurance.setSaleChannel(insurancePolicy.getGroupChannel());
			txInsurance.setSellType(insurancePolicy.getSellType());
			txInsurance.setSource(insurancePolicy.getBusinessSource());
			
			String proposalContNo = "";
			if(StringUtils.isNotBlank(insureFlowDto.getQuoteMain().getHxssProposalNo()) && !"null".equals(insureFlowDto.getQuoteMain().getHxssProposalNo())){
				proposalContNo = insureFlowDto.getQuoteMain().getHxssProposalNo();
			}
			TranRequest tranRequest = new TranRequest();
			LCCont lcCont = new LCCont();
			
			lcCont.setProposalContNo(proposalContNo);
			lcCont.setPolApplyDate(new Date());
			lcCont.setDisputedFlag(insurancePolicy.getDisputedFlag()==null?"":insurancePolicy.getDisputedFlag().toString());
			lcCont.setManageCom("86010000");
			lcCont.setAgentCode("86010000");
			//告知
			List<TellInfo> tellInfos = new ArrayList<TellInfo>();
			List<Risk> risks = new ArrayList<Risk>();
			List<LCBnf> lcBnfs = new ArrayList<LCBnf>();
			List<Account> riskAccounts = new ArrayList<Account>();
			
			InsuranceApplicant insuranceApplicant = insurancePolicy.getInsuranceApplicant();
			List<Insured> insureds = insurancePolicy.getInsureds();
			List<Beneficiary> beneficiaries = insurancePolicy.getBeneficiaries();
			List<InsurancePolicyLiability> insurancePolicyLiabilities = insurancePolicy.getInsurancePolicyLiabilities();
			List<InsureInformBook> insureInformBooks = insurancePolicy.getInsureInformBooks();
			PaymentAccount paymentAccount = insurancePolicy.getPaymentAccount();
			
			String provinceName = "";
			String cityName = "";
			String countyName = "";
			String address = "";
			
			if(beneficiaries!=null && !beneficiaries.isEmpty()){
				for(Beneficiary beneficiary : beneficiaries){
					LCBnf lcBnf = new LCBnf();
					lcBnf.setFullName(beneficiary.getFullName());
					lcBnf.setIdType(beneficiary.getIdType()==null?"":beneficiary.getIdType().toString());
					lcBnf.setIdNumber(beneficiary.getIdNumber());
					lcBnf.setBirthDate(beneficiary.getBirthDate()==null?null:beneficiary.getBirthDate());
					lcBnf.setGender(beneficiary.getGender()==null?"":beneficiary.getGender().toString());
					lcBnf.setMobilePhoneNumber(beneficiary.getMobilePhoneNumber());
					lcBnf.setEmail(beneficiary.getEmail());
					lcBnf.setProvince(beneficiary.getProvince());
					lcBnf.setCity(beneficiary.getCity());
					if(StringUtils.isNotBlank(beneficiary.getProvinceName())) provinceName = beneficiary.getProvinceName();
					if(StringUtils.isNotBlank(beneficiary.getCityName())) cityName = beneficiary.getCityName();
					if(StringUtils.isNotBlank(beneficiary.getCountyName())) countyName = beneficiary.getCountyName();
					if(StringUtils.isNotBlank(beneficiary.getAddressLines())) address = beneficiary.getAddressLines();
					lcBnf.setHomeAddress(provinceName+cityName+countyName+address);
					lcBnf.setHomeAddress(beneficiary.getHomeAddress());
					lcBnf.setHomeZipCode(beneficiary.getHomeZipCode());
					lcBnf.setIdEndDate(beneficiary.getIdExpDate()==null?"":DateUtils.formatDate(beneficiary.getIdExpDate(),"yyyy-MM-dd"));
					lcBnf.setPhoneNumber(beneficiary.getPhoneNumber());
					lcBnf.setOfficePhoneNumber(beneficiary.getOfficePhoneNumber());
					lcBnf.setHousehold(beneficiary.getHousehold());
					lcBnf.setCounty(beneficiary.getCounty());
					lcBnf.setGrpName(beneficiary.getGrpName());
					lcBnf.setJobCode(beneficiary.getOccupationCode());
					lcBnf.setNationality(beneficiary.getHousehold());
					lcBnf.setInCome(beneficiary.getAnnualIncome());
					lcBnf.setBnfType(beneficiary.getBeneficiaryTypeByCoreValue());
					lcBnf.setBnfGrade(beneficiary.getBeneficiaryOrder()==null?"":beneficiary.getBeneficiaryOrder().toString());
					lcBnf.setRelationToInsured(beneficiary.getRelatedToInsured()==null?"":beneficiary.getRelatedToInsured().toString());
					lcBnf.setBnfLot(beneficiary.getInterestPercent()==null?new BigDecimal(0):beneficiary.getInterestPercent().divide(new BigDecimal(100)));
					lcBnfs.add(lcBnf);
				}
			}
			
			if(insureInformBooks!=null && !insureInformBooks.isEmpty()){
				for(InsureInformBook insureInformBook : insureInformBooks){
					TellInfo tellInfo = new TellInfo();
					tellInfo.setTellVersion(insureInformBook.getTellVersion()==null?"":insureInformBook.getTellVersion());
					tellInfo.setTellCode(insureInformBook.getInformCode());
					tellInfo.setTellContent(insureInformBook.getInformContent());
					tellInfo.setTellRemark(insureInformBook.getTellRemark());
					tellInfos.add(tellInfo);
				}
			}
			
			if(insurancePolicyLiabilities!=null && !insurancePolicyLiabilities.isEmpty()){
				for(InsurancePolicyLiability insurancePolicyLiability : insurancePolicyLiabilities){
					Risk risk = new Risk();
					risk.setRiskCode(insurancePolicyLiability.getCoreCode());
					risk.setMainRiskCode(insurancePolicyLiability.getMainRiskCode());
					risk.setRiskName(insurancePolicyLiability.getLiabilityName());
					if(insurancePolicyLiability.getInsuredAmount()!=null && insurancePolicyLiability.getUnitCount() != null)
						risk.setAmnt(insurancePolicyLiability.getInsuredAmount().multiply(new BigDecimal(insurancePolicyLiability.getUnitCount())));
					else 
						risk.setAmnt(insurancePolicyLiability.getInsuredAmount());
					risk.setCvaliDate(insurancePolicy.getInceptionDate());
					risk.setPrem(insurancePolicyLiability.getPremium());
//					risk.setMult(1);
					risk.setPayIntv(insurancePolicyLiability.getPaymentDurationModeByMerchantValue()==null?"0":insurancePolicyLiability.getPaymentDurationModeByMerchantValue());
					risk.setYears(insurancePolicyLiability.getBenefitPeriod()==null?"":insurancePolicyLiability.getBenefitPeriod().toString());
					risk.setSpecContent(insurancePolicyLiability.getSpecContent());
					risk.setPayEndYearFlag(insurancePolicyLiability.getPaymentDurationModeByCoreValue());
					risk.setPayEndYear(insurancePolicyLiability.getPaymentDuration()==null?"":insurancePolicyLiability.getPaymentDuration().toString());
					risk.setGetYearFlag(insurancePolicyLiability.getGetYearFlag()==null?"":insurancePolicyLiability.getGetYearFlag().toString());
					risk.setGetYear(insurancePolicyLiability.getGetYear()==null?"":insurancePolicyLiability.getGetYear().toString());
					risk.setInsuYearFlag(insurancePolicyLiability.getBenefitPeriodTypeByCoreValue());
					risk.setInsuYear(insurancePolicyLiability.getBenefitPeriod()==null?"":insurancePolicyLiability.getBenefitPeriod().toString());
					risk.setAutoPayFlag(insurancePolicyLiability.getAutoPayFlag()==null?"":insurancePolicyLiability.getAutoPayFlag().toString());
					risk.setBonusPayMode(insurancePolicyLiability.getBonusPayMode()==null?"":insurancePolicyLiability.getBonusPayMode().toString());
					risk.setSubFlag(insurancePolicyLiability.getSubRiskFlag()==null?"":insurancePolicyLiability.getSubRiskFlag().toString());
					risk.setBonusGetMode(insurancePolicyLiability.getBonusGetMode()==null?"":insurancePolicyLiability.getBonusGetMode().toString());
					risk.setAutoRNewFlag(insurancePolicyLiability.getAutoRNewFlag()==null?"":insurancePolicyLiability.getAutoRNewFlag().toString());
					risk.setHealthFlag(insurancePolicyLiability.getHealthFlag()==null?"":insurancePolicyLiability.getHealthFlag().toString());
					risk.setFullBonusGetMode(insurancePolicyLiability.getFullBonusGetMode()==null?"":insurancePolicyLiability.getFullBonusGetMode().toString());
					risk.setFirstRate(insurancePolicyLiability.getFirstRate());
					risk.setSureRate(insurancePolicyLiability.getSureRate());
					risk.setRank(insurancePolicyLiability.getRank());
					risk.setAccounts(riskAccounts);
					risk.setLcBnfs(lcBnfs);
					risk.setLcBnfCount(lcBnfs.size());
					risks.add(risk);
				}
			}
			
			//投保人
			LCAppnt lcAppnt = new LCAppnt();
			lcAppnt.setFullName(insuranceApplicant.getFullName());
			lcAppnt.setIdType(insuranceApplicant.getIdType()==null?"":insuranceApplicant.getIdType().toString());
			lcAppnt.setIdNumber(insuranceApplicant.getIdNumber());
			lcAppnt.setBirthDate(insuranceApplicant.getBirthDate());
			lcAppnt.setGender(insuranceApplicant.getGender()==null?"":insuranceApplicant.getGender().toString());
			lcAppnt.setMobilePhoneNumber(insuranceApplicant.getMobilePhoneNumber());
			lcAppnt.setEmail(insuranceApplicant.getEmail());
			lcAppnt.setProvince(insuranceApplicant.getProvince());
			lcAppnt.setCity(insuranceApplicant.getCity());
			if(StringUtils.isNotBlank(insuranceApplicant.getProvinceName())) provinceName = insuranceApplicant.getProvinceName();
			if(StringUtils.isNotBlank(insuranceApplicant.getCityName())) cityName = insuranceApplicant.getCityName();
			if(StringUtils.isNotBlank(insuranceApplicant.getCountyName())) countyName = insuranceApplicant.getCountyName();
			if(StringUtils.isNotBlank(insuranceApplicant.getAddressLines())) address = insuranceApplicant.getAddressLines();
			lcAppnt.setHomeAddress(provinceName+cityName+countyName+address);
			lcAppnt.setHomeZipCode(insuranceApplicant.getPostalCode());
			lcAppnt.setTellInfos(tellInfos);
			lcAppnt.setTellInfoCount(tellInfos.size());
			lcAppnt.setIdEndDate(insuranceApplicant.getiDEndDate()==null?"":DateUtils.formatDate(insuranceApplicant.getiDEndDate(),"yyyy-MM-dd"));
			lcAppnt.setPhoneNumber(insuranceApplicant.getPhoneNumber());
			lcAppnt.setOfficePhoneNumber(insuranceApplicant.getOfficePhoneNumber());
			lcAppnt.setHousehold(insuranceApplicant.getHousehold());
			lcAppnt.setCounty(insuranceApplicant.getCounty());
			lcAppnt.setGrpName(insuranceApplicant.getGrpName());
			lcAppnt.setJobType(insuranceApplicant.getOccupationClass());
			lcAppnt.setJobCode(insuranceApplicant.getOccupationCode());
			lcAppnt.setNationality(insuranceApplicant.getHousehold());
			lcAppnt.setInCome(insuranceApplicant.getAnnualIncome());
			String related = "";
			if(insuranceApplicant.getRelatedToInsured() != null && insuranceApplicant.getRelatedToInsured() <10){
				related = "0"+insuranceApplicant.getRelatedToInsured();
			}else{
				related = insuranceApplicant.getRelatedToInsured()==null?"":insuranceApplicant.getRelatedToInsured().toString();
			}
			lcAppnt.setRelaToInsured(related);
			
			//被保人
			List<LCInsured> icInsureds = lcCont.getLcInsureds();
			if(insureds!=null && !insureds.isEmpty()){
				for(Insured insured : insureds){
					LCInsured lcInsured = new LCInsured();
					lcInsured.setRelaToMain("00");
					if(insured.getRelatedToApplicant() != null && insured.getRelatedToApplicant() <10){
						related = "0"+insured.getRelatedToApplicant();
					}else{
						related = insured.getRelatedToApplicant()==null?"":insured.getRelatedToApplicant().toString();
					}
					lcInsured.setRelaToAppnt(related);
					lcInsured.setFullName(insured.getFullName());
					lcInsured.setIdType(insured.getIdType()==null?"":insured.getIdType().toString());
					lcInsured.setIdNumber(insured.getIdNumber());
					lcInsured.setBirthDate(insured.getBirthDate());
					lcInsured.setGender(insured.getGender()==null?"":insured.getGender().toString());
					lcInsured.setMobilePhoneNumber(insured.getMobilePhoneNumber());
					lcInsured.setEmail(insured.getEmail());
					lcInsured.setProvince(insured.getProvince());
					lcInsured.setCity(insured.getCity());
					if(StringUtils.isNotBlank(insured.getProvinceName())) provinceName = insured.getProvinceName();
					if(StringUtils.isNotBlank(insured.getCityName())) cityName = insured.getCityName();
					if(StringUtils.isNotBlank(insured.getCountyName())) countyName = insured.getCountyName();
					if(StringUtils.isNotBlank(insured.getAddressLines())) address = insured.getAddressLines();
					lcInsured.setHomeAddress(provinceName+cityName+countyName+address);
					lcInsured.setHomeZipCode(insured.getPostalCode());
					lcInsured.setTellInfos(tellInfos);
					lcInsured.setTellInfoCount(tellInfos.size());
					lcInsured.setIdEndDate(insured.getiDEndDate()==null?"":DateUtils.formatDate(insured.getiDEndDate(),"yyyy-MM-dd"));
					lcInsured.setPhoneNumber(insured.getPhoneNumber());
					lcInsured.setOfficePhoneNumber(insured.getOfficePhoneNumber());
					lcInsured.setHousehold(insured.getHousehold());
					lcInsured.setCounty(insured.getCounty());
					lcInsured.setGrpName(insured.getGrpName());
					lcInsured.setJobType(insured.getOccupationClass());
					lcInsured.setJobCode(insured.getOccupationCode());
					lcInsured.setNationality(insured.getHousehold());
					lcInsured.setInCome(insured.getAnnualIncome());
					lcInsured.setRisks(risks);
					lcInsured.setRiskCount(risks.size());
					icInsureds.add(lcInsured);
				}
			}
			
			lcCont.setLcAppnt(lcAppnt);
			lcCont.setLcInsureds(icInsureds);
			lcCont.setLcInsuredCount(lcCont.getLcInsureds().size());
			
			tranRequest.setLcCont(lcCont);
			
			List<Object> businessDatum = new ArrayList<Object>();
			businessDatum.add(tranRequest);
			txInsurance.setBusinessDatum(businessDatum);
//			resultMap.put("LCCont", lcCont); //测试
			Object object = interfaceTransportService.sendServletRequestXML(tranRequest, txInsurance, transType);
			if(object != null){
				if (object.getClass().getName().equals(TranResponse.class.getName())){
					TranResponse tranResponse = (TranResponse) object;
					flag = tranResponse.getFlag();
					desc = tranResponse.getDesc();
					resultMap.put("LCCont", tranResponse.getLcCont());
				}else if (object.getClass().getName().equals(RetData.class.getName())){
					RetData retData = (RetData) object;
					flag = retData.getFlag();
					desc = retData.getDesc();
				}
			}
//			flag = "1"; //测试
			
		} catch (Exception e) {
			e.printStackTrace();
//			desc = StringUtils.exception2String(e);
//			flag = "1"; //测试
//			desc = ""; //测试
			insurancePolicyProcess.setEnumProcessStatus(ProcessStatus.FAILED);
			insurancePolicyProcess.setProcessStatusDesc(e.getMessage());
		}finally{
			insurancePolicyProcess.setEnumLockStatus(LockStatus.UNLOCK);
			insurancePolicyProcess.setLockedReason("");
			insurancePolicyProcess.setProcessEndTime(new Date());
//			insurancePolicyProcessService.updateInsurancePolicyProcess(insurancePolicyProcess);
			resultMap.put("flag", flag);
			resultMap.put("desc", desc);
			return resultMap;
		}
	}

	/**
	 * 承保
	 */
	@SuppressWarnings("finally")
	@Override
	public Map<String, Object> insured(InsurancePolicy insurancePolicy) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String flag = "0";
		String desc = "";
		InsurancePolicyProcess insurancePolicyProcess = new InsurancePolicyProcess();//insurancePolicyProcessService.findInsurancePolicyProcessByMerchantOrderNumber(insurancePolicy.getQuoteNo());
		try {
			if(insurancePolicyProcess==null){
				insurancePolicyProcessService.addInsurancePolicyProcessByInsurancePolicy(insurancePolicy);
				insurancePolicyProcess = insurancePolicyProcessService.findInsurancePolicyProcessByMerchantOrderNumber(insurancePolicy.getQuoteNo());
			}
			insurancePolicyProcess.setEnumLockStatus(LockStatus.LOCKED);
			insurancePolicyProcess.setLockedReason("数据库写入操作未处理完成,请稍后重试!");
//			insurancePolicyProcessService.updateInsurancePolicyProcess(insurancePolicyProcess);
			
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID(GeneratorTransSerialNumber.generatorTransSerialNumber());
			txInsurance.setTransType(TransType.ST000023.getCoreValue());
			txInsurance.setTransExeDate(DateUtils.formatDate(new Date(), "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(new Date(), "HHmmss"));
			txInsurance.setTellerNo("");
			txInsurance.setSaleChannel(insurancePolicy.getGroupChannel());
			txInsurance.setSellType(insurancePolicy.getSellType());
			txInsurance.setSource(insurancePolicy.getBusinessSource());
			
			TranRequest tranRequest = new TranRequest();
			LCCont lcCont = new LCCont();
			lcCont.setProposalContNo(insurancePolicy.getApplicationSerialNumber());
			lcCont.setPayMode(insurancePolicy.getOrderForm()==null?"":insurancePolicy.getOrderForm().getPayStatus()==null?"":((PayMethod) EnumDataUtils.getEnumDictionaryByValue(PayMethod.class, insurancePolicy.getOrderForm().getPayStatus())).getCoreValue());
			lcCont.setPrem(insurancePolicy.getGrossPremium());
			lcCont.setAccName(insurancePolicy.getInsuranceApplicant()==null?"":insurancePolicy.getInsuranceApplicant().getFullName());
			lcCont.setIsPaySucc(insurancePolicy.getOrderForm()==null?"":insurancePolicy.getOrderForm().getPayStatus()==null?"0":insurancePolicy.getOrderForm().getPayStatus().toString());
			lcCont.setPayMessage(insurancePolicy.getOrderForm()==null?"":insurancePolicy.getOrderForm().getPayStatusDesc());
			
			if(insurancePolicy.getPaymentAccount() != null){
				lcCont.setAccProvince(insurancePolicy.getPaymentAccount().getAccProvince());
				lcCont.setAccCity(insurancePolicy.getPaymentAccount().getAccCity());
				lcCont.setAccBankCode(insurancePolicy.getPaymentAccount().getSecBankAccNo());
				lcCont.setBankAccNo(insurancePolicy.getPaymentAccount().getSecBankCode());
				lcCont.setAccType(insurancePolicy.getPaymentAccount().getBankAcctTypeByCoreValue());
			}
			LCAppnt lcAppnt = new LCAppnt();
			lcAppnt.setIdType(insurancePolicy.getInsuranceApplicant()==null?"":insurancePolicy.getInsuranceApplicant().getIdType()==null?"":insurancePolicy.getInsuranceApplicant().getIdType().toString());
			lcAppnt.setIdNumber(insurancePolicy.getInsuranceApplicant()==null?"":insurancePolicy.getInsuranceApplicant().getIdNumber());
			lcAppnt.setMobilePhoneNumber(insurancePolicy.getInsuranceApplicant()==null?"":insurancePolicy.getInsuranceApplicant().getMobilePhoneNumber());
			
			lcCont.setLcAppnt(lcAppnt);
			tranRequest.setLcCont(lcCont);
			
			Object object = interfaceTransportService.sendServletRequestXML(tranRequest, txInsurance, TransType.ST000023.getCoreValue());
			if(object != null){
				if (object.getClass().getName().equals(TranResponse.class.getName())){
					TranResponse tranResponse = (TranResponse) object;
					flag = tranResponse.getFlag();
					desc = tranResponse.getDesc();
					resultMap.put("LCCont", tranResponse.getLcCont());
				}else if (object.getClass().getName().equals(RetData.class.getName())){
					RetData retData = (RetData) object;
					flag = retData.getFlag();
					desc = retData.getDesc();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("flag", flag);
		resultMap.put("desc", desc);
		return resultMap;
	}
	
}
