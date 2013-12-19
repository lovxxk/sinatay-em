package cn.com.sinosoft.ebusiness.sale.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.enums.dictionary.PolicyStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.QuoteStatus;
import cn.com.sinosoft.businessModule.insureInformBook.domain.InsureInformBook;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.Beneficiary;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.InsuranceApplicant;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.Insured;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicyLiability;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyService;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.BizCommonService;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeProductProDeptService;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade.GeSaleProductService;
import cn.com.sinosoft.ebusiness.sale.domain.InsureFlowDto;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteApplicant;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteBeneficiary;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteInsureInformBook;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteInsured;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteLiability;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteInsureInformBookService;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteLiabilityService;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteMainService;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteRoleService;
import cn.com.sinosoft.ebusiness.sale.service.facade.SaleService;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.TopInsured;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.TopInsuredService;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.SaleException;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.portalModule.transport.sinatay.Risk;
import cn.com.sinosoft.util.spring.BeanUtils;
import cn.com.sinosoft.util.string.StringUtils;
import cn.com.sinosoft.util.time.DateUtils;

public class SaleServiceSpringImpl extends GenericDaoHibernate implements SaleService {
	@Autowired
	private QuoteMainService quoteMainService;
	@Autowired
	private BizCommonService bizCommonService;
	@Autowired
	private GeSaleProductService geSaleProductService;
	@Autowired
	private GeUserPersonalService geUserPersonalService;
	@Autowired
	private GeProductProDeptService geProductProDeptService;
	@Autowired
	private GeDirectoryService geDirectoryService;
	@Autowired
	private InsurancePolicyService insurancePolicyService;
	@Autowired
	private TopInsuredService topInsuredService;
	@Autowired
	private QuoteRoleService quoteRoleService;
	@Autowired
	private QuoteInsureInformBookService quoteInsureInformBookService;
	@Autowired
	private QuoteLiabilityService quoteLiabilityService;
	
	@Override
	public boolean saveQuote(InsureFlowDto insureFlowDto, String step, String quoteNo, String userId, String save_topInsured) {
		try {
			String insuranceType = insureFlowDto.getInsuranceType();
			Date currentDate = new Date();
			
			/**获取试算单号*/
			QuoteMain quoteMain = insureFlowDto.getQuoteMain();
			quoteMain.setQuoteNo(quoteNo);//试算单号
			quoteMain.setInputDate(currentDate);//录入日期/签单日期
			quoteMain.setInputHour(DateUtils.formatDate(currentDate,"HHmm"));//录入小时
			quoteMain.setStep(Integer.parseInt(step));
			
			boolean isFirst = "1".equals(step) ? true : false;
			saveQuoteMain(quoteMain,quoteNo,isFirst,insuranceType,userId,save_topInsured);
			return true;
		} catch (Exception e) {
			logger.info("保存试算单异常，异常信息为：" + e.getMessage());
			logger.error(StringUtils.exception2String(e));
			return false;
		}
	}
	
	@Override
	public QuoteMain saveQuote(InsureFlowDto insureFlowDto, String step, String userId, String quoteNo) {
		QuoteMain quoteMain = insureFlowDto.getQuoteMain();
		try {
			String insuranceType = insureFlowDto.getInsuranceType();
			Date currentDate = new Date();
			
			if(quoteMain == null){
				quoteMain = new QuoteMain();
			}
			
			/**获取试算单号*/
			quoteMain.setQuoteNo(quoteNo);//试算单号
			quoteMain.setInputDate(currentDate);//录入日期/签单日期
			quoteMain.setInputHour(DateUtils.formatDate(currentDate,"HHmm"));//录入小时
			quoteMain.setCreateTime(currentDate);//创建时间
			quoteMain.setStep(Integer.parseInt(step));
			
			boolean isFirst = "1".equals(step) ? true : false;
			saveQuoteMain(quoteMain,quoteNo,isFirst,insuranceType,userId,"0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quoteMain;
	}
	
	public QuoteMain updateQuote(InsureFlowDto insureFlowDto, String step, String save_topInsured){
		QuoteMain quoteMain = insureFlowDto.getQuoteMain();
		try {
			QuoteMain tager = null;
			if(quoteMain != null && StringUtils.isNotBlank(quoteMain.getQuoteNo())){
				quoteMainService.updateQuoteMain(tager);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quoteMain;
	}
	
	/**
	 * 保存试算单主表信息
	 * @param quoteMain
	 * @param quoteNo
	 * @param isFirst
	 * @param insuranceType
	 * @return
	 */
	private void saveQuoteMain(QuoteMain quoteMain,String quoteNo,boolean isFirst,String insuranceType, String userId, String save_topInsured) throws Exception{
		String topInsId = "";
		QuoteApplicant quoteApplicant = quoteMain.getQuoteApplicant();
		if(quoteApplicant != null){
			quoteApplicant.setQuoteMain(quoteMain);
		}
		
		List<QuoteInsured> quoteInsureds = quoteMain.getQuoteInsureds();
		List<QuoteBeneficiary> quoteBeneficiaries = quoteMain.getQuoteBeneficiaries();
		List<QuoteLiability> quoteLiabilities = quoteMain.getQuoteLiabilities();
		List<QuoteInsureInformBook> quoteInsureInformBooks = quoteMain.getQuoteInsureInformBooks();
		
		if(quoteInsureds != null && !quoteInsureds.isEmpty()){
			for(QuoteInsured quoteInsured : quoteInsureds){
				quoteInsured.addQuoteMain(quoteMain);
				if(save_topInsured.equals("1")){
					TopInsured topInsured = topInsuredService.getTopInsuredById(quoteInsured.getTopInsuredId());
					if(topInsured != null){
						try {
							copyData(topInsured,quoteInsured);
							topInsuredService.updateTopInsured(topInsured);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						try {
							topInsured = new TopInsured();
							copyData(topInsured,quoteInsured);
							topInsuredService.saveInsued(topInsured, userId);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		if(quoteBeneficiaries != null && !quoteBeneficiaries.isEmpty()){
			for(QuoteBeneficiary quoteBeneficiary : quoteBeneficiaries){
				quoteBeneficiary.addQuoteMain(quoteMain);
			}
		}
		if(quoteLiabilities != null && !quoteLiabilities.isEmpty()){
			for(QuoteLiability quoteLiability : quoteLiabilities){
				quoteLiability.setInceptionDate(quoteMain.getInceptionDate());
				quoteLiability.addQuoteMain(quoteMain);
			}
		}
		if(quoteInsureInformBooks != null && !quoteInsureInformBooks.isEmpty()){
			for(QuoteInsureInformBook quoteInsureInformBook : quoteInsureInformBooks){
				quoteInsureInformBook.addQuoteMain(quoteMain);
			}
		}
		quoteMainService.saveQuoteMain(quoteMain);
	}
	
	@Override
	public InsureFlowDto getInsureFlowDtoForConInsu(String quoteNo){
		InsureFlowDto insureFlowDto = new InsureFlowDto();
		
		/**试算单主表*/
		QuoteMain quoteMain = quoteMainService.findQuoteMainByPk(quoteNo);
		insureFlowDto.setQuoteMain(quoteMain);
		
		return insureFlowDto;
	}
	

	@Override
	public boolean updateInforOrNot(String userid) {
		boolean flag = true;
		try {
			String querySql1 = "select * from ge_user_policy r where r.businessarea = '3' " +
								"and r.bindtype = '01' and r.userid = ?";
			List caiPolicy = super.findBySql(querySql1, new Object[]{userid});
			
			String querySql2 = "select * from ge_user_policy r where r.businessarea = '4' and r.userid = ?";
			List shouPolicy = super.findBySql(querySql2, new Object[]{userid});
			
			if((caiPolicy!=null&&caiPolicy.size()>0) || (shouPolicy!=null&&shouPolicy.size()>0)){
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	
	/**
	 * 继续填写保单删除
	 */
	public boolean deleteMyQuoteMain(String quoteNo){
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("quoteNo", quoteNo);
		QuoteMain quoteMain = super.findUnique(QuoteMain.class, rule);
		quoteMain.setQuoteStatus(QuoteStatus.DELETED.getValue());
		boolean flag = quoteMainService.updateQuoteMain(quoteMain);
		return flag;
	}

	/**
	 * 把核保返回信息更新到试算单、投保单中
	 * @param uw
	 * @return map--key--quoteMain;
	 * @throws SaleException
	 */
	public boolean saveQuote(LCCont lcCont,InsureFlowDto insureFlowDto, InsurancePolicy insurancePolicy) throws SaleException {
		boolean flag = false;
		try {
			if(lcCont != null && insureFlowDto != null && insurancePolicy != null){
				insurancePolicy.setApplicationNumber(lcCont.getProposalContNo());
				insurancePolicy.setApplicationSerialNumber(lcCont.getProposalContNo());
				insurancePolicy.setGrossPremium(lcCont.getPrem());
				List<InsurancePolicyLiability> insurancePolicyLiabilities = insurancePolicy.getInsurancePolicyLiabilities();
				List<Risk> risks = lcCont.getLcInsureds()==null?null:lcCont.getLcInsureds().isEmpty()?null:lcCont.getLcInsureds().get(0).getRisks();
				for(InsurancePolicyLiability insurancePolicyLiability : insurancePolicyLiabilities){
					for(Risk risk : risks){
						if(insurancePolicyLiability.getLiabilityCode().equals(risk.getRiskCode())){
							insurancePolicyLiability.setGrossPremium(risk.getPrem());
							insurancePolicyLiability.setGrossInsuredAmount(risk.getAmnt());
						}
					}
				}
				
				QuoteMain quoteMain = quoteMainService.findQuoteMainByPk(insureFlowDto.getQuoteMain().getQuoteNo());
				if(quoteMain != null){
					quoteMain.setProposalSID(insurancePolicy.getSerialNo());
					quoteMain.setGrossPremium(lcCont.getPrem());
					quoteMain.setHxssProposalNo(lcCont.getProposalContNo());
					List<QuoteLiability> quoteLiabilities = quoteMain.getQuoteLiabilities();
					for(QuoteLiability quoteLiability : quoteLiabilities){
						for(Risk risk : risks){
							if(quoteLiability.getLiabilityCode().equals(risk.getRiskCode())){
								quoteLiability.setGrossPremium(risk.getPrem());
								quoteLiability.setGrossInsuredAmount(risk.getAmnt());
							}
						}
					}
					quoteMainService.updateQuoteMain(quoteMain);
				}
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	/**
	 * 根据试算单创建投保单
	 * @param insureFlowDto
	 * @return
	 */
	public InsurancePolicy createInsurancePolicy(InsureFlowDto insureFlowDto) {
		InsurancePolicy insurancePolicy = new InsurancePolicy();
		QuoteMain quoteMain = insureFlowDto.getQuoteMain();
		List<QuoteInsured> quoteInsureds = quoteMain.getQuoteInsureds(); //被保人
		List<QuoteBeneficiary> quoteBeneficiaries = quoteMain.getQuoteBeneficiaries(); //受益人
		List<QuoteLiability> quoteLiabilities = quoteMain.getQuoteLiabilities(); //责任
		QuoteApplicant quoteApplicant = quoteMain.getQuoteApplicant(); //投保人
		List<QuoteInsureInformBook> quoteInsureInformBooks = quoteMain.getQuoteInsureInformBooks(); //投保告知
		
		insurancePolicy = composePolicy(quoteMain,insurancePolicy);
		InsuranceApplicant insuranceApplicant = composeApplicant(quoteApplicant,insurancePolicy);
		List<Insured> insureds = composeInsured(quoteInsureds,insurancePolicy);
		List<Beneficiary> beneficiaries = composeBeneficiary(quoteBeneficiaries,insurancePolicy);
		List<InsurancePolicyLiability> insurancePolicyLiabilities = composePolicyLiability(quoteLiabilities,insurancePolicy);
		List<InsureInformBook> insureInformBooks = composeInsureInformBook(quoteInsureInformBooks,insurancePolicy);
		
		insurancePolicy.setInsuredNumber(insureds.size());
		insurancePolicy.setBeneficiaryNumber(beneficiaries.size());
		insurancePolicy.setInsurancePolicyLiabilityNumber(insurancePolicyLiabilities.size());
		insurancePolicy.setInsureInformBookNumber(insureInformBooks.size());
		
//		System.out.println("### insurancePolicy: "+insurancePolicy);
//		System.out.println("### insuranceApplicant: "+insuranceApplicant);
//		System.out.println("### insureds.size(): "+insureds.size());
//		System.out.println("### beneficiaries.size(): "+beneficiaries.size());
//		System.out.println("### insurancePolicyLiabilities.size(): "+insurancePolicyLiabilities.size());
//		System.out.println("### insureInformBooks.size(): "+insureInformBooks.size());
		
		return insurancePolicyService.createInsurancePolicy(insurancePolicy);
	}
	
	public InsurancePolicy composePolicy(QuoteMain quoteMain, InsurancePolicy insurancePolicy){
		insurancePolicy.setTransIdentify("");
		insurancePolicy.setTransSerialNumber("");
		insurancePolicy.setOrderSerialNumber("");
		insurancePolicy.setPolicySerialNumber("");
		insurancePolicy.setApplicationNumber(quoteMain.getHxssProposalNo()==null?"":quoteMain.getHxssProposalNo());
		insurancePolicy.setApplicationSerialNumber(quoteMain.getHxssProposalNo()==null?"":quoteMain.getHxssProposalNo());
		insurancePolicy.setPolicyStatus(PolicyStatus.WAITING_PROPOSAL.getValue());
		insurancePolicy.setPolicyStatusName(PolicyStatus.WAITING_PROPOSAL.getDataName());
		insurancePolicy.setPolicyStatusDesc(PolicyStatus.WAITING_PROPOSAL.getDataName());
		insurancePolicy.setInsuredAmount(quoteMain.getInsuredAmount());
		insurancePolicy.setPremium(quoteMain.getPremium());
		insurancePolicy.setDiscountPremium(quoteMain.getDiscountPremium());
		insurancePolicy.setGrossPremium(quoteMain.getGrossPremium());
		insurancePolicy.setFaceAmount(quoteMain.getFaceAmount());
		insurancePolicy.setFirstPremium(quoteMain.getFirstPremium());
		insurancePolicy.setInitialPremAmt(quoteMain.getInitialPremAmt());
		insurancePolicy.setBenefitPeriod(quoteMain.getBenefitPeriod());
		insurancePolicy.setBenefitPeriodType(quoteMain.getBenefitPeriodType());
		insurancePolicy.setPaymentMode(quoteMain.getPaymentMode());
		insurancePolicy.setPaymentDuration(quoteMain.getPaymentDuration());
		insurancePolicy.setPaymentDurationMode(quoteMain.getPaymentDurationMode());
		insurancePolicy.setPaymentMethod(quoteMain.getPaymentMethod());
		insurancePolicy.setBenefitMode(quoteMain.getBenefitMode());
		insurancePolicy.setDivType(quoteMain.getDivType());
		insurancePolicy.setAnnuityPayoutDuration(quoteMain.getAnnuityPayoutDuration());
		insurancePolicy.setAnnuityPayoutDurationMode(quoteMain.getAnnuityPayoutDurationMode());
		insurancePolicy.setPayoutStart(quoteMain.getPayoutStart());
		insurancePolicy.setPayoutEnd(quoteMain.getPayoutEnd());
		insurancePolicy.setExcessPremAmt(quoteMain.getExcessPremAmt());
		insurancePolicy.setPolicyDeliveryFee(quoteMain.getPolicyDeliveryFee());
		insurancePolicy.setPolicyStatusMessage(quoteMain.getPolicyStatusMessage());
		insurancePolicy.setSignedDate(quoteMain.getInputDate());
		insurancePolicy.setInceptionDate(quoteMain.getInceptionDate());
		insurancePolicy.setApplicationDate(quoteMain.getApplicationDate());
		insurancePolicy.setInsuranceStartPeriod(quoteMain.getInsuranceStartPeriod());
		insurancePolicy.setInsuranceEndPeriod(quoteMain.getInsuranceEndPeriod());
		insurancePolicy.setBeneficiaryMode(quoteMain.getBeneficiaryMode());
		insurancePolicy.setSubmissionDate(new Date());
		insurancePolicy.setUnitCount(quoteMain.getUnitCount());
		insurancePolicy.setContractNames(quoteMain.getContractNames());
		insurancePolicy.setSpecialStatement(quoteMain.getSpecialStatement());
		insurancePolicy.setBankCode(quoteMain.getBankCode());
		insurancePolicy.setDeliveryInvoice(quoteMain.getDeliveryInvoice());
		insurancePolicy.setDeliveryHardCopy(quoteMain.getDeliveryHardCopy());
		insurancePolicy.setDeliveryEPolicy(quoteMain.getDeliveryEPolicy());
		insurancePolicy.setCampaignCode(quoteMain.getCampaignCode());
		insurancePolicy.setCampaignName(quoteMain.getCampaignName());
		insurancePolicy.setDiscountTypeCode(quoteMain.getDiscountTypeCode());
		insurancePolicy.setDiscountRate(quoteMain.getDiscountRate());
		insurancePolicy.setAutoRenewable(quoteMain.getAutoRenewable());
		insurancePolicy.setComboCode(quoteMain.getComboCode());
		insurancePolicy.setComboName(quoteMain.getComboName());
		insurancePolicy.setPersonalUserSerialNo(quoteMain.getUserId());
		insurancePolicy.setProductCode(quoteMain.getProductCode());
		insurancePolicy.setProductName(quoteMain.getProductName());
		insurancePolicy.setAgentCode(quoteMain.getAgentCode());
		insurancePolicy.setAgreementNo(quoteMain.getAgreementNo());
		insurancePolicy.setAgentName(quoteMain.getAgentName());
		insurancePolicy.setDepartmentNo(quoteMain.getDepartmentNo());
		insurancePolicy.setDepartmentName(quoteMain.getDepartmentName());
		insurancePolicy.setIntermediaryCode(quoteMain.getIntermediaryCode());
		insurancePolicy.setIntermediaryName(quoteMain.getIntermediaryName());
		insurancePolicy.setBranchCode(quoteMain.getBranchCode());
		insurancePolicy.setBranchName(quoteMain.getBranchName());
		insurancePolicy.setOrganizationCode(quoteMain.getOrganizationCode());
		insurancePolicy.setOrganizationName(quoteMain.getOrganizationName());
		insurancePolicy.setInstitutionCode(quoteMain.getInstitutionCode());
		insurancePolicy.setInstitutionName(quoteMain.getInstitutionName());
		insurancePolicy.setExPayMode(quoteMain.getExPayMode());
		insurancePolicy.setGetPolMode(quoteMain.getGetPolMode());
		insurancePolicy.setPassword(quoteMain.getPassword());
		insurancePolicy.setSpecContent(quoteMain.getSpecContent());
		insurancePolicy.setTempFeeNo(quoteMain.getTempFeeNo());
		insurancePolicy.setAgentGroup(quoteMain.getAgentGroup());
		insurancePolicy.setQuoteNo(quoteMain.getQuoteNo());
		insurancePolicy.setRenewalFlag(quoteMain.getRenewalFlag());
		insurancePolicy.setOldlPolicyNo(quoteMain.getOldlPolicyNo());
		insurancePolicy.setInputHour(quoteMain.getInputHour());
		insurancePolicy.setBusinessSource(quoteMain.getBusinessSource());
		insurancePolicy.setGroupChannel(quoteMain.getGroupChannel());
		insurancePolicy.setCurrency(quoteMain.getCurrency());
		insurancePolicy.setSellType(quoteMain.getSellType());
		
		return insurancePolicy;
	}
	
	public InsuranceApplicant composeApplicant(QuoteApplicant quoteApplicant, InsurancePolicy insurancePolicy){
		InsuranceApplicant insuranceApplicant = new InsuranceApplicant();
		List<String> ignorePropertyList = new ArrayList<String>();
		ignorePropertyList.add("serialNo");
		ignorePropertyList.add("createTime");
		String[] ignoreProperties = new String[ignorePropertyList.size()];
		BeanUtils.copyProperties(quoteApplicant, insuranceApplicant, ignorePropertyList.toArray(ignoreProperties));
		insuranceApplicant.addInsurancePolicy(insurancePolicy);
		return insuranceApplicant;
	}
	
	public List<Insured> composeInsured(List<QuoteInsured> quoteInsureds, InsurancePolicy insurancePolicy){
		List<Insured> insureds = new ArrayList<Insured>();
		if(quoteInsureds!=null && !quoteInsureds.isEmpty()){
			for(QuoteInsured quoteInsured : quoteInsureds){
				Insured insured = new Insured();
				List<String> ignorePropertyList = new ArrayList<String>();
				ignorePropertyList.add("serialNo");
				ignorePropertyList.add("createTime");
				String[] ignoreProperties = new String[ignorePropertyList.size()];
				BeanUtils.copyProperties(quoteInsured, insured, ignorePropertyList.toArray(ignoreProperties));
				insured.addInsurancePolicy(insurancePolicy);
				insureds.add(insured);
			}
		}
		return insureds;
	}
	
	public List<Beneficiary> composeBeneficiary(List<QuoteBeneficiary> quoteBeneficiaries, InsurancePolicy insurancePolicy){
		List<Beneficiary> beneficiaries = new ArrayList<Beneficiary>();
		if(quoteBeneficiaries!=null && !quoteBeneficiaries.isEmpty()){
			for(QuoteBeneficiary quoteBeneficiary : quoteBeneficiaries){
				Beneficiary beneficiary = new Beneficiary();
				List<String> ignorePropertyList = new ArrayList<String>();
				ignorePropertyList.add("serialNo");
				ignorePropertyList.add("createTime");
				String[] ignoreProperties = new String[ignorePropertyList.size()];
				BeanUtils.copyProperties(quoteBeneficiary, beneficiary, ignorePropertyList.toArray(ignoreProperties));
				beneficiary.addInsurancePolicy(insurancePolicy);
				beneficiaries.add(beneficiary);
			}
		}
		return beneficiaries;
	}
	
	public List<InsurancePolicyLiability> composePolicyLiability(List<QuoteLiability> quoteLiabilities, InsurancePolicy insurancePolicy){
		List<InsurancePolicyLiability> insurancePolicyLiabilities = new ArrayList<InsurancePolicyLiability>();
		if(quoteLiabilities!=null && !quoteLiabilities.isEmpty()){
			for(QuoteLiability quoteLiability : quoteLiabilities){
				InsurancePolicyLiability insurancePolicyLiability = new InsurancePolicyLiability();
				List<String> ignorePropertyList = new ArrayList<String>();
				ignorePropertyList.add("serialNo");
				ignorePropertyList.add("createTime");
				String[] ignoreProperties = new String[ignorePropertyList.size()];
				BeanUtils.copyProperties(quoteLiability, insurancePolicyLiability, ignorePropertyList.toArray(ignoreProperties));
				insurancePolicyLiability.addInsurancePolicy(insurancePolicy);
				insurancePolicyLiabilities.add(insurancePolicyLiability);
			}
		}
		return insurancePolicyLiabilities;
	}
	
	public List<InsureInformBook> composeInsureInformBook(List<QuoteInsureInformBook> quoteInsureInformBooks, InsurancePolicy insurancePolicy){
		List<InsureInformBook> insureInformBooks = new ArrayList<InsureInformBook>();
		if(quoteInsureInformBooks!=null && !quoteInsureInformBooks.isEmpty()){
			for(QuoteInsureInformBook quoteInsureInformBook : quoteInsureInformBooks){
				InsureInformBook insureInformBook = new InsureInformBook();
				List<String> ignorePropertyList = new ArrayList<String>();
				ignorePropertyList.add("serialNo");
				ignorePropertyList.add("createTime");
				String[] ignoreProperties = new String[ignorePropertyList.size()];
				BeanUtils.copyProperties(quoteInsureInformBook, insureInformBook, ignorePropertyList.toArray(ignoreProperties));
				insureInformBook.addInsurancePolicy(insurancePolicy);
				insureInformBooks.add(insureInformBook);
			}
		}
		return insureInformBooks;
	}
	
	public TopInsured copyData(TopInsured topInsured,QuoteInsured quoteInsured){
		topInsured.setUpdateTime(new Date());
		topInsured.setRoleSerialNo(quoteInsured.getRoleSerialNo());
		topInsured.setFullName(quoteInsured.getFullName());
		topInsured.setFirstName(quoteInsured.getFirstName());
		topInsured.setLastName(quoteInsured.getLastName());
		topInsured.setFullENName(quoteInsured.getFullENName());
		topInsured.setIdType(quoteInsured.getIdType());
		topInsured.setIdNumber(quoteInsured.getIdNumber());
		topInsured.setIdExpDate(quoteInsured.getIdExpDate());
		topInsured.setGender(quoteInsured.getGender());
		topInsured.setBirthDate(quoteInsured.getBirthDate());
		topInsured.setAgeInDay(quoteInsured.getAgeInDay());
		topInsured.setAge(quoteInsured.getAge());
		topInsured.setBirthWeight(quoteInsured.getBirthWeight());
		topInsured.setHeight(quoteInsured.getHeight());
		topInsured.setMobilePhoneNumber(quoteInsured.getMobilePhoneNumber());
		topInsured.setPhoneNumber(quoteInsured.getPhoneNumber());
		topInsured.setOfficePhoneNumber(quoteInsured.getOfficePhoneNumber());
		topInsured.setEmail(quoteInsured.getEmail());
		topInsured.setPostalCode(quoteInsured.getPostalCode());
		topInsured.setProvince(quoteInsured.getProvince());
		topInsured.setCity(quoteInsured.getCity());
		topInsured.setHomeAddress(quoteInsured.getHomeAddress());
		topInsured.setOfficeAddress(quoteInsured.getOfficeAddress());
		topInsured.setAddressLines(quoteInsured.getAddressLines());
		topInsured.setCitizenShip(quoteInsured.getCitizenShip());
		topInsured.setOccupationClass(quoteInsured.getOccupationClass());
		topInsured.setOccupationCode(quoteInsured.getOccupationCode());
		topInsured.setOccupationDescription(quoteInsured.getOccupationDescription());
		topInsured.setMaritalStatus(quoteInsured.getMaritalStatus());
		topInsured.setAnnualIncome(quoteInsured.getAnnualIncome());
		topInsured.setEmployerFullName(quoteInsured.getEmployerFullName());
		topInsured.setDrinkerAmountByYear(quoteInsured.getDrinkerAmountByYear());
		topInsured.setDrinkerStatus(quoteInsured.getDrinkerStatus());
		topInsured.setDrinkerYears(quoteInsured.getDrinkerYears());
		topInsured.setSmokerAmountByYear(quoteInsured.getSmokerAmountByYear());
		topInsured.setSmokerStatus(quoteInsured.getSmokerStatus());
		topInsured.setSmokerYears(quoteInsured.getSmokerYears());
		topInsured.setAcceptSMS(quoteInsured.getAcceptSMS());
		topInsured.setCustomerID(quoteInsured.getCustomerID());
		topInsured.setCustomFlag(quoteInsured.getCustomFlag());
		topInsured.setiDValidFlag(quoteInsured.getiDValidFlag());
		topInsured.setiDStartDate(quoteInsured.getiDStartDate());
		topInsured.setiDEndDate(quoteInsured.getiDEndDate());
		topInsured.setHousehold(quoteInsured.getHousehold());
		topInsured.setCounty(quoteInsured.getCounty());
		topInsured.setHomeZipCode(quoteInsured.getHomeZipCode());
		topInsured.setGrpName(quoteInsured.getGrpName());
		topInsured.setCitizenShipName(quoteInsured.getCitizenShipName());
		topInsured.setFax(quoteInsured.getFax());
		topInsured.setLicenseType(quoteInsured.getLicenseType());
		topInsured.setLicenseTypeView(quoteInsured.getLicenseTypeView());
		topInsured.setAlias(quoteInsured.getAlias());
		topInsured.setHobby(quoteInsured.getHobby());
		topInsured.setBloodType(quoteInsured.getBloodType());
		topInsured.setRelatedToApplicant(quoteInsured.getRelatedToApplicant());
		topInsured.setRelatedToInsured(quoteInsured.getRelatedToInsured());
		topInsured.setMainInsuredFlag(quoteInsured.getMainInsuredFlag());
		topInsured.setInsuredOrder(quoteInsured.getInsuredOrder());
		topInsured.setInsuredAmountPercent(quoteInsured.getInsuredAmountPercent());
		topInsured.setIndicatorMessage(quoteInsured.getIndicatorMessage());
		topInsured.setSameIndustryInsuredAmount(quoteInsured.getSameIndustryInsuredAmount());
		topInsured.setWeight(quoteInsured.getWeight());
		return topInsured;
	}
}
