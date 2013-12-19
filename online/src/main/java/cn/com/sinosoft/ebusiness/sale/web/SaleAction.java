package cn.com.sinosoft.ebusiness.sale.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.bindPolicy.service.facade.BindPolicyService;
import cn.com.sinosoft.businessModule.enums.dictionary.OrderStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.PayStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.PaymentMethod;
import cn.com.sinosoft.businessModule.enums.dictionary.PolicyStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.TransType;
import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.order.service.facade.OrderFormService;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.InsuranceApplicant;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.Insured;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyService;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.BizCommonService;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.SendEmailService;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeOccupation;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeFunctionSwitchService;
import cn.com.sinosoft.ebusiness.marketing.domain.GeActivitiesRule;
import cn.com.sinosoft.ebusiness.marketing.domain.GeAddserviceConditionDto;
import cn.com.sinosoft.ebusiness.marketing.service.facade.AddServiceActivityService;
import cn.com.sinosoft.ebusiness.occupation.service.facade.OccupationService;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectory;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePayment;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeProductProDeptService;
import cn.com.sinosoft.ebusiness.productManagement.productPictureManage.domain.GeProductPicture;
import cn.com.sinosoft.ebusiness.productManagement.productPictureManage.service.facade.GeProductPictureDetailService;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProApplicantConfig;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProAttrAllowValues;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProBeneficiaryConfig;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProInsuredConfig;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProInsuredOccupation;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProPolicyDisplayConfig;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProduct;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProductDuty;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProductRisk;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade.GeSaleProInformBookService;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade.GeSaleProInsuredOccupationService;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade.GeSaleProWebFlowPageElementService;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade.GeSaleProductService;
import cn.com.sinosoft.ebusiness.sale.domain.InsureFlowDto;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteApplicant;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteInsured;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteMainService;
import cn.com.sinosoft.ebusiness.sale.service.facade.SaleService;
import cn.com.sinosoft.ebusiness.sale.service.facade.SmsSendService;
import cn.com.sinosoft.ebusiness.sale.service.facade.UnderwritingService;
import cn.com.sinosoft.ebusiness.sale.service.facade.VerificationService;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.AutoUserDto;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.TopInsured;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.TopInsuredService;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.UserService;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.SaleException;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.util.GeneratorTransSerialNumber;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.util.encode.JsonBinder;
import cn.com.sinosoft.util.io.FilePathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;
import cn.com.sinosoft.util.string.StringUtils;
import cn.com.sinosoft.util.time.DateUtils;

public class SaleAction extends Struts2Action {

	private static final long serialVersionUID = 4661075874288228258L;

	private static Logger log = LoggerFactory.getLogger(SaleAction.class);
	
	@Autowired
	private OrderFormService orderFormService;
	
	@Autowired
	private InsurancePolicyService insurancePolicyService;
	
	@Autowired
	private QuoteMainService quoteMainService;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private GeCodeService geCodeService;// �����ֵ�service
	
	@Autowired
	private GeDirectoryService geDirectoryService;
	
	@Autowired
	private AddServiceActivityService addServiceActivityService;//����service
	
	@Autowired
	private GeSaleProInformBookService geSaleProInformBookService;//�������˸�֪����
	
	@Autowired
	private GeSaleProWebFlowPageElementService geSaleProWebFlowPageElementService;
	
	@Autowired
	private GeSaleProductService geSaleProductService;
	
	@Autowired
	private GeProductProDeptService geProductProDeptService;
	
	@Autowired
	private GeSaleProInsuredOccupationService geSaleProInsuredOccupationService;
	
	@Autowired
	private GeUserPersonalService geUserPersonalService;
	
	@Autowired
	private BizCommonService bizCommonService;
	
	@Autowired
	private GeProductPictureDetailService geProductPictureDetailService;
	
	@Autowired
	private OccupationService occupationService;
	
	@Autowired
	private TopInsuredService topInsuredService;
	
	@Autowired
	private VerificationService verificationService; 
	
	@Autowired
	private SmsSendService smsSendService;
	
	@Autowired
	private UnderwritingService underwritingService;
	
	/**���ܿ���*/
	@Autowired
	private GeFunctionSwitchService geFunctionSwitchService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	public BindPolicyService bindPolicyService;
	
	@Autowired
	private SendEmailService sendEmailService;
	
	private InsureFlowDto insureFlowDto;

	public List<OrderForm> saleRecords = new ArrayList<OrderForm>(0);
	
	private List<TopInsured> topInsureds = new ArrayList<TopInsured>(0);
	
	public Integer status;
	
	public String orderSerialNo;
	
	private String isOpenDivForIdType;
	private String isOpenDivForBirthday;
	private String isOpenDivForSex;
	private long pageCount;
	
	public List<OrderForm> getSaleRecords() {
		return saleRecords;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public List<TopInsured> getTopInsureds() {
		return topInsureds;
	}
	public void setTopInsureds(List<TopInsured> topInsureds) {
		this.topInsureds = topInsureds;
	}
	public String getIsOpenDivForIdType() {
		return isOpenDivForIdType;
	}
	public void setIsOpenDivForIdType(String isOpenDivForIdType) {
		this.isOpenDivForIdType = isOpenDivForIdType;
	}
	public String getIsOpenDivForBirthday() {
		return isOpenDivForBirthday;
	}
	public void setIsOpenDivForBirthday(String isOpenDivForBirthday) {
		this.isOpenDivForBirthday = isOpenDivForBirthday;
	}
	public String getIsOpenDivForSex() {
		return isOpenDivForSex;
	}
	public void setIsOpenDivForSex(String isOpenDivForSex) {
		this.isOpenDivForSex = isOpenDivForSex;
	}
	public InsureFlowDto getInsureFlowDto() {
		return insureFlowDto;
	}
	public void setInsureFlowDto(InsureFlowDto insureFlowDto) {
		this.insureFlowDto = insureFlowDto;
	}
	
	
	/**
	 * ��������
	 * @return
	 */
	public String toQuote(){
		String eid = super.getRequest().getParameter("eid");
		String type = super.getRequest().getParameter("type");
		String day = super.getRequest().getParameter("day");
		String moreBuyFlag = "0";
		String moreBuyProposalSID = "";
		String productCode = "";
		String methodString = super.getRequest().getMethod();
		if(methodString.equalsIgnoreCase("post")){
			moreBuyFlag = super.getRequest().getParameter("moreBuyFlag");
			moreBuyProposalSID =  super.getRequest().getParameter("moreBuyProposalSID");
			super.getRequest().setAttribute("moreBuyFlag", moreBuyFlag);
			super.getRequest().setAttribute("moreBuyProposalSID", moreBuyProposalSID);
		}
		
		if(StringUtils.isNotBlank(eid)){
			try {
				GeDirectory gd = geDirectoryService.findGeDirectoryByEId(eid);
				productCode = gd==null?"":gd.getCoreProductCode();
//				obtainDiscountData(eid);//������Ϣ
				super.getRequest().setAttribute("geDirectory",gd);
			} catch (Exception e1) {
				e1.printStackTrace();
				super.getRequest().setAttribute("productCode","");
			}
		
		}else{
			return "productCenter";
		}
		JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
		jsonBinder.setDateFormat("yyyy-MM-dd");
		Page page = new Page();
		List<GeSaleProductRisk> saleProductRisks = null;
		if(StringUtils.isNotBlank(productCode)){
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("productCode", productCode);
			queryRule.addEqual("payStatus", 1);
			queryRule.addDescOrder("updateTime");
			page = orderFormService.findOrderFormByQueryRule(queryRule, pageNo, pageSize);
			saleRecords = page.getResult();
			obtainExtraRuleFlag(productCode);
			super.getRequest().setAttribute("productCode",productCode);
			GeSaleProduct geSaleProduct = geSaleProductService.findByCode(productCode);
			if(geSaleProduct != null){
				saleProductRisks = geSaleProduct.getGeSaleProductRisks();
			}
		}else{
			return "productCenter";
		}
		
		super.getRequest().setAttribute("eid", eid);
		super.getRequest().setAttribute("type", type);
		super.getRequest().setAttribute("day", day);
		super.getRequest().setAttribute("productRisks", jsonBinder.toJson(saleProductRisks));
		super.getRequest().setAttribute("pageSize", pageSize);
		super.getRequest().setAttribute("totalPage", page.getTotalPageCount()==0?1:page.getTotalPageCount());
		super.getRequest().setAttribute("pageFlag", "1");

		return SUCCESS;
	}
	
	public String saleRecords() {
		String productCode = super.getRequest().getParameter("productCode");
		if(StringUtils.isNotBlank(productCode)){
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("productCode", productCode);
			queryRule.addEqual("payStatus", 1);
			queryRule.addDescOrder("updateTime");
			Object obj = getRequest().getParameter("page");
			pageNo = obj == null ? 0 : new Integer((String)obj);
			Page page = orderFormService.findOrderFormByQueryRule(queryRule, pageNo, 10);
			saleRecords = page.getResult();
			pageCount = page.getTotalPageCount();
		}
		return SUCCESS;
	}
	
	private void obtainExtraRuleFlag(String productCode){
		if(!StringUtils.isBlank(productCode)){
			boolean isExtraRuleFlag = geSaleProductService.getStatusBycode(productCode);
			if(isExtraRuleFlag){
				String filePathString = "/upload/publishedProduct/"+productCode+"/"+productCode+"_productRule.js";
				filePathString = super.getRequest().getRealPath(filePathString);
				java.io.File f = new java.io.File(filePathString);
				if(!f.exists()) {
					super.getRequest().setAttribute("isExtraRuleFlag", "false");
				}else{
					super.getRequest().setAttribute("isExtraRuleFlag", "true");
				}
			}else{
				super.getRequest().setAttribute("isExtraRuleFlag", isExtraRuleFlag);
			}
		}
	}
	
	/**
	 * ��ȡ����ҳ����Ϣ
	 * @param geSaleProduct
	 */
	
	private void obtainQutoeData(String productCode,GeSaleProduct geSaleProduct){
		/**������Ч���ں���ֹ����*/
		String specifyStartDate = "";
		String specifyEndDate = "";
		String effectDateType = "";/**��Ч�������ͣ�01-Ĭ����Ч��02-ָ����Ч��03-�Զ�����Ч��04-�ӳ���Ч��*/
		if (geSaleProduct != null)effectDateType = geSaleProduct.getEffectDateType();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if ("01".equals(effectDateType)) {
			specifyStartDate = sdf.format(new Date(new Date().getTime() + 24*60*60*1000));
			specifyEndDate = specifyStartDate;
		}else if ("02".equals(effectDateType)) {
			if(geSaleProduct.getSpecifyStartDate() != null)specifyStartDate = sdf.format(geSaleProduct.getSpecifyStartDate());
			if(geSaleProduct.getSpecifyEndDate() != null)specifyEndDate = sdf.format(geSaleProduct.getSpecifyEndDate());
		}else if ("03".equals(effectDateType)) {
			if(StringUtils.isNotBlank(geSaleProduct.getMinEffectDateLimited())){
				int dateNum = Integer.parseInt(geSaleProduct.getMinEffectDateLimited());
				specifyStartDate = sdf.format(new Date(new Date().getTime() + dateNum*24*60*60*1000));
			}
			if(StringUtils.isNotBlank(geSaleProduct.getMaxEffectDateLimited())){
				int dateNum = Integer.parseInt(geSaleProduct.getMaxEffectDateLimited());
				specifyEndDate = sdf.format(new Date(new Date().getTime() + dateNum*24*60*60*1000));
			}
		}else if ("04".equals(effectDateType)) {
			if(StringUtils.isNotBlank(geSaleProduct.getDelayDays())){
				int dateNum = Integer.parseInt(geSaleProduct.getDelayDays());
				specifyStartDate = sdf.format(new Date(new Date().getTime() + dateNum*24*60*60*1000));
				specifyEndDate = specifyStartDate;
			}
		}
		super.getRequest().setAttribute("specifyStartDate", specifyStartDate);
		super.getRequest().setAttribute("specifyEndDate", specifyEndDate);
		
		/**��ʼ�������������ڵ�ʡ*/ 
		List<String[]> provinceList = geProductProDeptService.findProductSaleDepartmet(productCode,"3","2");
		super.getRequest().setAttribute("provinceList",provinceList);
		/**��ʼ�������ڼ�*/
		List<GeSaleProAttrAllowValues> geProductAttrAllowValues=geSaleProduct.getGeSaleProAttrAllowValueses();
		// ����
		if(geProductAttrAllowValues.size()>1)
			geProductAttrAllowValues =  sortAllowValue(geProductAttrAllowValues);
		
		super.getRequest().setAttribute("geProductAttrAllowValues",geProductAttrAllowValues);
		
		/**�����ֵ�-�����ڼ䵥λ*/
		Map periodUnitMap = geCodeService.findAllCodeAndName("PeriodType");
		super.getRequest().setAttribute("periodUnitMap",periodUnitMap);
		
		/**��ʼ��ҳ�汣���ڼ�ֵ*/
		super.getRequest().setAttribute("geProductAttrAllowValue", geProductAttrAllowValues.get(0));
		
		/**��ʼ��������Ϣ*/
		List<GeSaleProductDuty> geSaleProductDuties=geSaleProduct.getGeSaleProductDuties();
		super.getRequest().setAttribute("geSaleProductDuties",geSaleProductDuties);
		
		/**���λ*/
		Map amountUnitMap  = geCodeService.findAllCodeAndName("InsuredAmountUnit");
		super.getRequest().setAttribute("amountUnitMap",amountUnitMap);
		
		/**��ȡ������������Ϣ*/
		List<GeSaleProInsuredConfig> geProductInsuredConfigs = geSaleProduct.getGeSaleProInsuredConfigs();
		
		if(geProductInsuredConfigs.size() > 0) {
			
			/**��ȡ��������������*/
			if("0".equals(geProductInsuredConfigs.get(0).getInAgeFlag())){
				int insuredAgeStart = 0;
				int insuredAgeEnd = 0;
				String insuredAgeDesc = "�������������������";
				String insuredStartAgeString = geProductInsuredConfigs.get(0).getInAgeStart();
				String insuredStartUnit = geProductInsuredConfigs.get(0).getInAgeStartAttr();
				String insuredEndAgeString = geProductInsuredConfigs.get(0).getInAgeEnd();
				String insuredEndUnit = geProductInsuredConfigs.get(0).getInAgeEndAttr();
				if(StringUtils.isNotBlank(insuredStartAgeString)){
					insuredAgeStart = - (Integer.parseInt(insuredStartAgeString)-1);
					if(! StringUtils.isBlank(insuredStartUnit)){
						String unit =  "Y".equals(insuredStartUnit)?"����":("M".equals(insuredStartUnit)?"����":"��");
						insuredAgeDesc += insuredStartAgeString+unit;
					}
				}
				if(StringUtils.isNotBlank(insuredEndAgeString)){
					insuredAgeEnd = - (Integer.parseInt(insuredEndAgeString)+1);
				}

				String insuredMaxBirthday = dateAdd(new Date(),"yyyy-MM-dd", insuredAgeStart, insuredStartUnit);
				String insuredMinBirthday = dateAddNoCriticality(new Date(),"yyyy-MM-dd", insuredAgeEnd, insuredEndUnit);
				super.getRequest().setAttribute("insuredAgeDesc", "("+insuredAgeDesc+")");
				super.getRequest().setAttribute("isLimitInsuredAge", geProductInsuredConfigs.get(0).getInAgeFlag());
				super.getRequest().setAttribute("insuredMinBirthday", insuredMinBirthday);
				super.getRequest().setAttribute("insuredMaxBirthday", insuredMaxBirthday);
				
//				System.out.println("insuredAgeStart: "+insuredAgeStart);
//				System.out.println("insuredAgeEnd: "+insuredAgeEnd);
//				System.out.println("isLimitInsuredAge: "+geProductInsuredConfigs.get(0).getInAgeFlag());
//				System.out.println("insuredMinBirthday: "+insuredMinBirthday);
//				System.out.println("insuredMaxBirthday: "+insuredMaxBirthday);
			}
		}
	}
	
	
	private void obtainDiscountData(String eid){
		/**����*/
		String existDiscountFlag = "false";/**��־�Ƿ����*/
		List<GeActivitiesRule> geActivitiesRuleList  = new ArrayList<GeActivitiesRule>();
		GeActivitiesRule geActivitiesRule = new GeActivitiesRule();
		geActivitiesRule.setDeptID("2");//�����ܹ�˾ID
		geActivitiesRule.setEid(eid);//eid
		geActivitiesRuleList.add(geActivitiesRule);

		GeAddserviceConditionDto geAddserviceConditionDto = new GeAddserviceConditionDto();
		geAddserviceConditionDto.setSystemFlowId("02");
		geAddserviceConditionDto.setPictureFlag(true);//�Ƿ�Ҫ���ͼƬ
		
		List<String> wantedActivityPatterns = new ArrayList<String>();
		wantedActivityPatterns.add("4");//���۷�ʽ
		geAddserviceConditionDto.setWantedActivityPatterns(wantedActivityPatterns);
		try {
			Map map = addServiceActivityService.findAddGeAddServiceActivityCheckRule(geActivitiesRuleList, geAddserviceConditionDto);
			existDiscountFlag = (String)map.get("isDiscountFlag");
			if("true".equals(existDiscountFlag)){
				Map discountAndJumpUrl=(Map)map.get("discountAndJumpUrl");
				String discountID=(String)discountAndJumpUrl.get("newDiscountID");
				super.getRequest().setAttribute("discountID",discountID);//�ۿ�ID
			}
			
			map = addServiceActivityService.activityBoola("2",eid, null);
			
		    if((Boolean)map.get("falg")){
				String pic1 = (String)map.get("pic1");
				String pic2 = (String)map.get("pic2");
				super.getRequest().setAttribute("topImg",pic1);
				super.getRequest().setAttribute("rightImg",pic2);
				getRequest().setAttribute("jump1", (String)map.get("jump1"));
				getRequest().setAttribute("jump2", (String)map.get("jump2"));
			} else {
				Map<Integer,GeProductPicture> pictureMap = geProductPictureDetailService.getProductPictures();
				if(pictureMap.get(0) != null){
					super.getRequest().setAttribute("topImg",pictureMap.get(0).getPictureurl());
				}
				if(pictureMap.get(1) != null){
					super.getRequest().setAttribute("rightImg",pictureMap.get(1).getPictureurl());
				}
			}
				
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		super.getRequest().setAttribute("existDiscountFlag", existDiscountFlag);
		
		/**�Ƿ���ڳ齱*/
		super.getRequest().setAttribute("existLuckDrawFlag", (Boolean)addServiceActivityService.activityBoola("2",eid, new String[]{"1"}).get("falg"));
	}
	
	
	/**
	 * ��ȡ����ҳ��������Ϣ
	 * @param productCode
	 * @param GeSaleProduct
	 * @param pageCode
	 */
	
	private void obtainInsuranceData(String productCode,GeSaleProduct geSaleProduct,String pageCode){
		HttpServletRequest request = super.getRequest();
		
		JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
		jsonBinder.setDateFormat("yyyy-MM-dd");
		
		/**��ȡ������������Ϣ*/
		List<GeSaleProInsuredConfig> geProductInsuredConfigs = geSaleProduct.getGeSaleProInsuredConfigs();
		List<GeSaleProPolicyDisplayConfig> geSaleProPolicyDisConfigs = geSaleProduct.getGeSaleProPolicyDisConfigs();
		
		/**��ȡҳ������������Ϣ*/
		List<String> elementCodes = geSaleProWebFlowPageElementService.getElementCodesByOrder(productCode, "noCarNetSaleFlow", pageCode);
		List informList = geSaleProInformBookService.findInformBooks(productCode);//�б���ÿ��Ԫ��Ϊ���δ�Ÿ�֪˳�򡢸�֪���ݡ���֪ѡ�������
		request.setAttribute("elementCodes", elementCodes);
		request.setAttribute("geInformBooks", informList);
		request.setAttribute("geInformBookNum", informList.size());
		
		/**��ȡ֤������������Ϣ*/
		List<GeCode> identifyTypeList = geCodeService.findAllByGeCodeType("IdentifyType");//֤������
		request.setAttribute("identifyTypeList",identifyTypeList);
		
		/**��ȡְҵ����*/
		List<GeOccupation> geOccupations = new ArrayList<GeOccupation>();
		List<GeSaleProInsuredOccupation> list = new ArrayList<GeSaleProInsuredOccupation>();
		list = geSaleProInsuredOccupationService.findGeOccupations(geProductInsuredConfigs.get(0).getSerialNo());
		request.setAttribute("geOccupations",list);
		
		/**��ȡ��ϵ����*/
		List<GeCode> insRelaToAppList = geCodeService.findAllByGeCodeType("applicantAndInsured");
		request.setAttribute("insRelaToAppList", insRelaToAppList);
		
		/**��ȡ�Ա�����*/
		List<GeCode> insSexList = geCodeService.findAllByGeCodeType("Sex");//������/�������Ա�
		request.setAttribute("insSexList",insSexList);
		
		String insOccupTypes = null;		// ְҵ�������
		String insRelaToApps = null;		// ��������Ͷ���˹�ϵ������
		String insType=null;				// ������֤����������
		String rInsOccupTypes = null;		// ����������ְҵ�������
		String rInsRelaToApps = null;		// ������������Ͷ���˹�ϵ������
		String rInsType=null;				// ����������֤����������
		String finsRelaToApps = null;		// �������������������˹�ϵ������
		
		StringBuilder insRelationToApp_opts = new StringBuilder();
		StringBuilder insIdType_opts = new StringBuilder();
		if(geProductInsuredConfigs.size() > 0) {
			insOccupTypes = geProductInsuredConfigs.get(0).getInsOccupationTypeConfig();
			insRelaToApps = geProductInsuredConfigs.get(0).getInsRelationToAppConfig();
			insType=geProductInsuredConfigs.get(0).getInsIdTypeConfig();
			finsRelaToApps = geProductInsuredConfigs.get(0).getFinsRelationToAppConfig();
			
			if(insRelaToApps.equals("0")){
				super.getRequest().setAttribute("autoIns", "1");
			}
			insRelationToApp_opts.append("[");
			insRelationToApp_opts.append("{name:'��ѡ��',value:'-1'}");
			List<String> insRelaToAppsList = new ArrayList<String>(0);
			if(StringUtils.isNotBlank(insRelaToApps)){
				if(insRelaToApps.indexOf(", ") != -1)
					insRelaToAppsList = Arrays.asList(insRelaToApps.split(", "));
				else{
					insRelaToAppsList.add(insRelaToApps);
				}
			}
			for(GeCode code : insRelaToAppList){
				if(insRelaToAppsList.contains(code.getId().getCodeCode())){
					insRelationToApp_opts.append(",{name:'"+code.getCodeCName()+"',value:'"+code.getId().getCodeCode()+"'}");
				}
			}
			insRelationToApp_opts.append("]");
			
			insIdType_opts.append("[");
			insIdType_opts.append("{name:'��ѡ��',value:'-1'}");
			List<String> insTypeList = new ArrayList<String>(0);
			if(StringUtils.isNotBlank(insType)){
				if(insType.indexOf(", ") != -1)
					insTypeList = Arrays.asList(insType.split(", "));
				else
					insTypeList.add(insType);
			}
			for(GeCode code : identifyTypeList){
				if(insTypeList.contains(code.getId().getCodeCode())){
					insIdType_opts.append(",{name:'"+code.getCodeCName()+"',value:'"+code.getId().getCodeCode()+"'}");
				}
			}
			insIdType_opts.append("]");
			
			
			/**��ȡ��������������*/
			if("0".equals(geProductInsuredConfigs.get(0).getInAgeFlag())){
				int insuredAgeStart = 0;
				int insuredAgeEnd = 0;
				String insuredAgeDesc = "�������������������";
				String insuredStartAgeString = geProductInsuredConfigs.get(0).getInAgeStart();
				String insuredStartUnit = geProductInsuredConfigs.get(0).getInAgeStartAttr();
				String insuredEndAgeString = geProductInsuredConfigs.get(0).getInAgeEnd();
				String insuredEndUnit = geProductInsuredConfigs.get(0).getInAgeEndAttr();
				if(StringUtils.isNotBlank(insuredStartAgeString)){
					insuredAgeStart = - (Integer.parseInt(insuredStartAgeString)-1);
					if(StringUtils.isNotBlank(insuredStartUnit)){
						String unit =  "Y".equals(insuredStartUnit)?"����":("M".equals(insuredStartUnit)?"����":"��");
						insuredAgeDesc += insuredStartAgeString+unit;
					}
				}
				if(StringUtils.isNotBlank(insuredEndAgeString)){
					insuredAgeEnd = - (Integer.parseInt(insuredEndAgeString)+1);
					if(StringUtils.isNotBlank(insuredEndUnit)){
						String unit =  "Y".equals(insuredEndUnit)?"����":("M".equals(insuredEndUnit)?"����":"��");
						insuredAgeDesc += " - "+insuredEndAgeString+unit;
					}
				}

				String insuredMaxBirthday = dateAdd(new Date(),"yyyy-MM-dd", insuredAgeStart, insuredStartUnit);
				String insuredMinBirthday = dateAddNoCriticality(new Date(),"yyyy-MM-dd", insuredAgeEnd, insuredEndUnit);
				request.setAttribute("insuredAgeDesc", insuredAgeDesc);
				request.setAttribute("isLimitInsuredAge", geProductInsuredConfigs.get(0).getInAgeFlag());
				request.setAttribute("insuredMinBirthday", insuredMinBirthday);
				request.setAttribute("insuredMaxBirthday", insuredMaxBirthday);
				
//				System.out.println("insuredStartAgeString: "+insuredStartAgeString);
//				System.out.println("insuredEndAgeString: "+insuredEndAgeString);
//				System.out.println("insuredAgeStart: "+insuredAgeStart);
//				System.out.println("insuredAgeEnd: "+insuredAgeEnd);
//				System.out.println("insuredMinBirthday: "+insuredMinBirthday);
//				System.out.println("insuredMaxBirthday: "+insuredMaxBirthday);

			}
		}
		StringBuilder benRelaToInss_opts = new StringBuilder();
		StringBuilder benIdType_opts = new StringBuilder();
		/**��ȡ������������Ϣ*/
		List<GeSaleProBeneficiaryConfig> geProductBeneficiaryConfigs = geSaleProduct.getGeSaleProBeneficiaryConfigs();	//������
		String benRelaToInss = null;/**���������������˹�ϵ*/
		String benIdType = null;/**������֤������*/
		if (geProductBeneficiaryConfigs.size() > 0) {
			benRelaToInss = geProductBeneficiaryConfigs.get(0).getBenRelationToPInsConfig();
			benIdType = geProductBeneficiaryConfigs.get(0).getBendTypeConfig();
			
			
			benIdType_opts.append("[");
			benIdType_opts.append("{name:'��ѡ��',value:'-1'}");
			List<String> benIdTypeList = new ArrayList<String>(0);
			if(StringUtils.isNotBlank(benIdType)){
				if(benIdType.indexOf(", ") != -1)
					benIdTypeList = Arrays.asList(benIdType.split(", "));
				else
					benIdTypeList.add(benIdType);
			}
			for(GeCode code : identifyTypeList){
				if(benIdTypeList.contains(code.getId().getCodeCode())){
					benIdType_opts.append(",{name:'"+code.getCodeCName()+"',value:'"+code.getId().getCodeCode()+"'}");
				}
			}
			benIdType_opts.append("]");
			
			
			benRelaToInss_opts.append("[");
			benRelaToInss_opts.append("{name:'��ѡ��',value:'-1'}");
			List<String> benRelaToInsList = new ArrayList<String>(0);
			if(StringUtils.isNotBlank(benRelaToInss)){
				if(benRelaToInss.indexOf(", ") != -1)
					benRelaToInsList = Arrays.asList(benRelaToInss.split(", "));
				else
					benRelaToInsList.add(benRelaToInss);
			}
			for(GeCode code : insRelaToAppList){
				if(benRelaToInsList.contains(code.getId().getCodeCode())){
					benRelaToInss_opts.append(",{name:'"+code.getCodeCName()+"',value:'"+code.getId().getCodeCode()+"'}");
				}
			}
			benRelaToInss_opts.append("]");
		}
		request.setAttribute("benRelaToInss", benRelaToInss);
		request.setAttribute("benIdType",benIdType);

		/**��ȡͶ������������*/
		GeSaleProApplicantConfig geSaleProApplicantConfig = geSaleProduct.getGeSaleProApplicantConfigs().get(0);
		if("0".equals(geSaleProApplicantConfig.getAppAgeFlag())){
			int appAgeStart = 0;
			int appAgeEnd = 0;
			String appStartAgeString = geSaleProApplicantConfig.getAppAgeStart();
			String appStartAgeUnit = geSaleProApplicantConfig.getAppAgeStartAttr();
			String appEndAgeString = geSaleProApplicantConfig.getAppAgeEnd();
			String appEndAgeUnit = geSaleProApplicantConfig.getAppAgeEndAttr();
			String appAgeDesc = "Ͷ���������������";
			if(StringUtils.isNotBlank(appStartAgeString)){
				appAgeStart = - (Integer.parseInt(appStartAgeString)-1);
				if(StringUtils.isNotBlank(appStartAgeUnit)){
					String unit =  "Y".equals(appStartAgeUnit)?"����":("M".equals(appStartAgeUnit)?"����":"��");
					appAgeDesc += appStartAgeString+unit;
				}
			}
			if(StringUtils.isNotBlank(appEndAgeString)){
				appAgeEnd = - (Integer.parseInt(appEndAgeString)+1);
				if(StringUtils.isNotBlank(appEndAgeUnit)){
					String unit =  "Y".equals(appEndAgeUnit)?"����":("M".equals(appEndAgeUnit)?"����":"��");
					appAgeDesc += " - "+appEndAgeString+unit;
				}
			}
			
			String appMaxBirthday = dateAdd(new Date(),"yyyy-MM-dd", appAgeStart, appStartAgeUnit);
			String appMinBirthday = dateAddNoCriticality(new Date(),"yyyy-MM-dd", appAgeEnd, appEndAgeUnit);
			request.setAttribute("appAgeDesc", appAgeDesc);
			request.setAttribute("isLimitAppAge", geSaleProApplicantConfig.getAppAgeFlag());
			request.setAttribute("appMinBirthday", appMinBirthday);
			request.setAttribute("appMaxBirthday", appMaxBirthday);
		}
		
		StringBuilder appIdType_opts = new StringBuilder();
		appIdType_opts.append("[");
		appIdType_opts.append("{name:'��ѡ��',value:'-1'}");
		String appTypes = geSaleProApplicantConfig.getAppIdTypeConfig();
		List<String> appTypeList = new ArrayList<String>(0);
		if(StringUtils.isNotBlank(appTypes)){
			if(appTypes.indexOf(", ") != -1)
				appTypeList = Arrays.asList(appTypes.split(", "));
			else
				appTypeList.add(appTypes);
		}
		for(GeCode code : identifyTypeList){
			if(appTypeList.contains(code.getId().getCodeCode())){
				appIdType_opts.append(",{name:'"+code.getCodeCName()+"',value:'"+code.getId().getCodeCode()+"'}");
			}
		}
		appIdType_opts.append("]");
		
		//����ǰ��Ʒ��Ч����
		String inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), 1),"yyyy-MM-dd");
		String effectDateType = geSaleProduct.getEffectDateType()==null?"01":geSaleProduct.getEffectDateType();
		String delayDays = geSaleProduct.getDelayDays()==null?"1":geSaleProduct.getDelayDays();
		String minEffectDateLimited = geSaleProduct.getMinEffectDateLimited()==null?"1":geSaleProduct.getMinEffectDateLimited();
		String maxEffectDateLimited = geSaleProduct.getMaxEffectDateLimited()==null?"1":geSaleProduct.getMaxEffectDateLimited();
		
		if(effectDateType.equals("01")){ //�����賿��Ч
			inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), 1),"yyyy-MM-dd");
		}else if(effectDateType.equals("02")){ //ָ����Ч��ʼ����
			inceptionDate = DateUtils.formatDate(geSaleProduct.getSpecifyStartDate(),"yyyy-MM-dd");
		}else if(effectDateType.equals("03")){ //��С�������� -- �����������
			inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), Integer.parseInt(minEffectDateLimited)),"yyyy-MM-dd")+"|"+DateUtils.formatDate(DateUtils.addDays(new Date(), Integer.parseInt(maxEffectDateLimited)),"yyyy-MM-dd");;
		}else if(effectDateType.equals("04")){ //�ӳ���Ч����
			inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), Integer.parseInt(delayDays)),"yyyy-MM-dd"); 
		}
		
		List<GeSaleProductRisk> saleProductRisks = null;
		if(geSaleProduct != null){
			saleProductRisks = geSaleProduct.getGeSaleProductRisks();
		}
		
		super.getRequest().setAttribute("productRisks", jsonBinder.toJson(saleProductRisks));
		
		request.setAttribute("pageCode", pageCode);
		request.setAttribute("insOccupTypes", insOccupTypes);
		request.setAttribute("insRelaToApps", insRelaToApps);
		
		request.setAttribute("rInsOccupTypes", rInsOccupTypes);
		request.setAttribute("rInsRelaToApps", rInsRelaToApps);
		request.setAttribute("rInsType", rInsType);
		request.setAttribute("insType", insType);
	
		request.setAttribute("finsRelaToApps", finsRelaToApps);
		
		request.setAttribute("appIdType_opts", appIdType_opts);
		request.setAttribute("insIdType_opts", insIdType_opts);
		request.setAttribute("benIdType_opts", benIdType_opts);
		request.setAttribute("insRelationToApp_opts", insRelationToApp_opts);
		request.setAttribute("benRelaToInss_opts", benRelaToInss_opts);
		
		request.setAttribute("inceptionDate", inceptionDate);
	}
	
	/**
	 * ��������
	 * @return
	 */
	
	public String obtainQuote(){
		JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
		try {
			String existDiscountFlag = insureFlowDto.getExistDiscountFlag();/**��־�Ƿ����*/
			jsonBinder.setDateFormat("yyyy-MM-dd");
			
			if(insureFlowDto.getProductCode() == null){
				super.render(jsonBinder.toJson("false"), "application/json;charset=GBK");
				return NONE;
			}
		} catch (Exception e) {
			super.render(jsonBinder.toJson("false"), "application/json;charset=GBK");
		}
		return NONE;
	}
	
	
	/**
	 * �������ڣ������ٽ�ֵ��
	 * @param targetDate
	 * @param format
	 * @param dateNum
	 * @param unit
	 * @return
	 */
	private String dateAdd(Date targetDate,String format,int dateNum,String unit){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		cal.setTime(targetDate);
		if("Y".equals(unit)){
			cal.add(Calendar.YEAR, dateNum);
		}else if("M".equals(unit)){
			cal.add(Calendar.MONTH, dateNum);
		}else if("D".equals(unit)){
			cal.add(Calendar.DATE, dateNum);
		}
		return df.format(cal.getTime());
	}
	
	/**
	 * �������ڣ��������ٽ�ֵ��
	 * @param targetDate
	 * @param format
	 * @param dateNum
	 * @param unit
	 * @return
	 */
	private String dateAddNoCriticality(Date targetDate,String format,int dateNum,String unit){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		cal.setTime(targetDate);
		if("Y".equals(unit)){
			cal.add(Calendar.YEAR, dateNum);
		}else if("M".equals(unit)){
			cal.add(Calendar.MONTH, dateNum);
		}else if("D".equals(unit)){
			cal.add(Calendar.DATE, dateNum);
		}
		cal.add(Calendar.DATE, 1);
		return df.format(cal.getTime());
	}
	
	
	/**
	 * ����Ͷ����д����
	 * @return
	 */
	public String toInputInsuranceInfo(){
		try {
			JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
			jsonBinder.setDateFormat("yyyy-MM-dd");
			
			/**set userId*/
			GeUserPersonal geUserPersonal = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
			if(geUserPersonal != null){
				insureFlowDto.getQuoteMain().setUserId(geUserPersonal.getUserID());
			}
			super.render(jsonBinder.toJson(insureFlowDto), "application/json;charset=GBK");
		} catch (Exception e) {
			e.printStackTrace();
			super.render(JsonBinder.buildNonNullBinder().toJson("false"), "application/json;charset=GBK");
		}
		
		return NONE;
	}
	
	/**
	 * ��ȡͶ����д�����������
	 * @return
	 */
	
	public String obtainDataForInput(){
//		System.out.println(this.getClass().getName()+" obtainDataForInput()...");
		try {
			JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
			jsonBinder.setDateFormat("yyyy-MM-dd");
			String productCode = super.getRequest().getParameter("productCode");
			String quoteNo = super.getRequest().getParameter("quoteNo");
			String proposalSID = super.getRequest().getParameter("proposalSID");
			String JsonSTR = super.getRequest().getParameter("quoteJsonSTR");
			String insuranceType = super.getRequest().getParameter("insuranceType");
			String proposalContNo = super.getRequest().getParameter("proposalContNo");
			String isLogin = "1";
			boolean isBind = false;
			if(StringUtils.isBlank(productCode)){
				if(insureFlowDto != null){
					productCode = insureFlowDto.getProductCode();
				}
			}
			GeSaleProduct geSaleProduct = geSaleProductService.findByCode(productCode);
			if(geSaleProduct != null){
				obtainInsuranceData(productCode,geSaleProduct,"insurePage");
				GeUserPersonal geUserPersonal = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
				if(geUserPersonal != null){
					isBind = bindPolicyService.hasPolicy(geUserPersonal.getUserID());
					if(insureFlowDto != null){
						insureFlowDto.getQuoteMain().setUserId(geUserPersonal.getUserID());
						insureFlowDto.getQuoteMain().setQuoteNo(quoteNo);
						insureFlowDto.getQuoteMain().setProposalSID(proposalSID);
					}
					obtainTopInsuredData(geSaleProduct, geUserPersonal);
				}else{
					isLogin = "0";
				}
				super.getRequest().setAttribute("geSaleProduct", geSaleProduct);
				super.getRequest().setAttribute("geUserPersonal", geUserPersonal);
			}else{
				return "productCenter";
			}
			
//			System.out.println(JsonSTR);
			super.getRequest().setAttribute("JsonSTR", JsonSTR==null?"":JsonSTR.replaceAll("\"", "\\\\\""));
			super.getRequest().setAttribute("isLogin", isLogin);
			super.getRequest().setAttribute("quoteNo", quoteNo);
			super.getRequest().setAttribute("proposalSID", proposalSID);
			super.getRequest().setAttribute("proposalContNo", proposalContNo);
			super.getRequest().setAttribute("isBind", isBind);
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	//��ѯ��ǰ�û��¹����ı���������Ϣ
	
	public void obtainTopInsuredData(GeSaleProduct geSaleProduct, GeUserPersonal geUserPersonal){
		System.out.println(this.getClass().getName()+" obtainTopInsuredData()...");
		QueryRule queryRule = QueryRule.getInstance();
		
		List<GeSaleProInsuredConfig> geProductInsuredConfigs = geSaleProduct.getGeSaleProInsuredConfigs();
		String insRelaToApps = "";		// ��������Ͷ���˹�ϵ������
		String insType = "";				// ������֤����������
		List<Integer> insRelaToAppList =new ArrayList<Integer>(); 
		if(geProductInsuredConfigs.size() > 0) {
			insRelaToApps = geProductInsuredConfigs.get(0).getInsRelationToAppConfig();
			insType = geProductInsuredConfigs.get(0).getInsIdTypeConfig();
			if(insRelaToApps.indexOf(",") > 0){
				for(int i=0;i<insRelaToApps.split(",").length;i++){
					insRelaToAppList.add(Integer.parseInt(insRelaToApps.split(",")[i].trim()));
				}
			}
			List<Integer> insTypeList = new ArrayList<Integer>(); 
			if(insType.indexOf(",") > 0){
				for(int i=0;i<insType.split(",").length;i++){
					insTypeList.add(Integer.parseInt(insType.split(",")[i].trim()));
				}
			}else{
				insTypeList.add(Integer.parseInt(insType));
			}
			queryRule.addIn("relatedToApplicant", insRelaToAppList);
			queryRule.addIn("idType", insTypeList);
			queryRule.addEqual("userPersonal.userID", geUserPersonal.getUserID());
			queryRule.addDescOrder("updateTime");
		}
		Page page = topInsuredService.findTopInsured(queryRule,1,5);
		topInsureds = page.getResult();
		if(insRelaToApps.equals("0")){
			super.getRequest().setAttribute("topInsuredNum", 0);
		}else{
			super.getRequest().setAttribute("topInsuredNum", topInsureds.size());
		}
	}
	
	/**
	 * ��ȡͶ��ȷ�Ͻ����������
	 * @return
	 */
	public String obtainDataForConfirm(){
//		System.out.println(this.getClass().getName()+" obtainDataForConfirm()...");
		String productCode = super.getRequest().getParameter("productCode");
		String JsonSTR = super.getRequest().getParameter("toConfimJsonSTR");
		String quoteNo = super.getRequest().getParameter("quoteNo");
		String proposalSID = super.getRequest().getParameter("proposalSID");
		String proposalContNo = super.getRequest().getParameter("proposalContNo");
//		System.out.println("quoteNo: "+quoteNo+", proposalSID: "+proposalSID);
		GeUserPersonal geUserPersonal = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		GeSaleProduct geSaleProduct = geSaleProductService.findByCode(productCode);
		String isLogin = "1";
		if(geUserPersonal != null && geSaleProduct != null){
			if(insureFlowDto != null){
				insureFlowDto.getQuoteMain().setUserId(geUserPersonal.getUserID());
				insureFlowDto.getQuoteMain().setQuoteNo(quoteNo);
				insureFlowDto.getQuoteMain().setProposalSID(proposalSID);
			}
			obtainTopInsuredData(geSaleProduct, geUserPersonal);
			obtainQutoeData(productCode,geSaleProduct);
			obtainInsuranceData(productCode,geSaleProduct,"insureConfirmPage");
		}else{
			isLogin = "0";
			return "productCenter";
		}
		//System.out.println("JsonSTR: "+JsonSTR);
		//System.out.println(JsonSTR.replaceAll("\"", "\\\\\""));
		super.getRequest().setAttribute("productCode", productCode);
		super.getRequest().setAttribute("geSaleProduct", geSaleProduct);
		super.getRequest().setAttribute("insureFlowDto", insureFlowDto);
		super.getRequest().setAttribute("isLogin", isLogin);
		super.getRequest().setAttribute("quoteNo", quoteNo);
		super.getRequest().setAttribute("proposalSID", proposalSID);
		super.getRequest().setAttribute("proposalContNo", proposalContNo);
		super.getRequest().setAttribute("geUserPersonal", geUserPersonal);
		super.getRequest().setAttribute("JsonSTR", JsonSTR==null?"":JsonSTR.replaceAll("\"", "\\\\\""));
		return SUCCESS;
	}
	
	/**
	 * ���뵽ȷ��ҳ��
	 * @return
	 */
	public String toConfirmInsuranceInfo(){
		System.out.println(this.getClass().getName()+" toConfirmInsuranceInfo()...");
		try {
			JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
			jsonBinder.setDateFormat("yyyy-MM-dd");
			super.render(jsonBinder.toJson(insureFlowDto), "application/json;charset=GBK");
		} catch (Exception e) {
			super.render(JsonBinder.buildNonNullBinder().toJson("false"), "application/json;charset=GBK");
		}
		return NONE;
	}
	
	/**
	 * �����޸�
	 * @return
	 */
	public String toReturnEditInsuranceInfo(){
		System.out.println(this.getClass().getName()+" toReturnEditInsuranceInfo()...");
		try {
			JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
			jsonBinder.setDateFormat("yyyy-MM-dd");
			String quoteNo = super.getRequest().getParameter("quoteNo");
			String proposalSID = super.getRequest().getParameter("proposalSID");
			System.out.println("quoteNo: "+quoteNo+", proposalSID: "+proposalSID);
			/**set userId*/
			GeUserPersonal geUserPersonal = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
			if(geUserPersonal != null){
				insureFlowDto.getQuoteMain().setUserId(geUserPersonal.getUserID());
				insureFlowDto.getQuoteMain().setQuoteNo(quoteNo);
				insureFlowDto.getQuoteMain().setProposalSID(proposalSID);
			}
			insureFlowDto.setInsuranceType("1");
			System.out.println("###: "+jsonBinder.toJson(insureFlowDto));
			super.render(jsonBinder.toJson(insureFlowDto), "application/json;charset=GBK");
		} catch (Exception e) {
			e.printStackTrace();
			super.render(JsonBinder.buildNonNullBinder().toJson("false"), "application/json;charset=GBK");
		}
		return NONE;
	}
	
	private List<GeSaleProAttrAllowValues> sortAllowValue(List<GeSaleProAttrAllowValues> productAttrAllowValueList){
		List<GeSaleProAttrAllowValues> listD = new ArrayList<GeSaleProAttrAllowValues>();
		List<GeSaleProAttrAllowValues> listM = new ArrayList<GeSaleProAttrAllowValues>();
		List<GeSaleProAttrAllowValues> listY = new ArrayList<GeSaleProAttrAllowValues>();
		
		for(GeSaleProAttrAllowValues paav : productAttrAllowValueList){
			if(paav.getAttributeTypeValue().equals("Y")){
				listY.add(paav);
			}else if(paav.getAttributeTypeValue().equals("M")){
				listM.add(paav);
			}else{
				listD.add(paav);
			}
		}
		
		sortForAllowValue(listD);
		sortForAllowValue(listM);
		sortForAllowValue(listY);
		
		listD.addAll(listM);
		listD.addAll(listY);
		
		return listD;
	}
	
	// ð������AllowValue
	private static void sortForAllowValue(List<GeSaleProAttrAllowValues> list) {
		GeSaleProAttrAllowValues temp;
		if(list!=null && list.size()>1){
			for (int i = 0; i < list.size(); ++i) {
				for (int j = 0; j < list.size() - i - 1; ++j) {
					if (Integer.parseInt(list.get(j).getAttributeValue()) >  Integer.parseInt(list.get(j+1).getAttributeValue())) {
						temp = list.get(j);
						list.set(j, list.get(j+1));
						list.set(j + 1, temp);
					}
				}
			}
		}
	}
	
	/**
	 * ����Ͷ��
	 * @return
	 */
	
	public String obtainContinueInsuranceData(){
		String quoteNo = super.getRequest().getParameter("quoteNo");
		String proposalSID = super.getRequest().getParameter("proposalSID");
		String productCode = super.getRequest().getParameter("productCode");
		String proposalContNo = super.getRequest().getParameter("proposalContNo")==null?"":super.getRequest().getParameter("proposalContNo");
		String source = getRequest().getParameter("source");
		if(StringUtils.isNotBlank(source) && source.equals("email")){
			quoteNo = new String(Base64.decodeBase64(quoteNo));
			proposalSID = new String(Base64.decodeBase64(proposalSID));
			productCode = new String(Base64.decodeBase64(productCode));
		}
//		System.out.println("source: "+source+", quoteNo: "+quoteNo+", productCode: "+productCode+", proposalSID: "+proposalSID);
		JSONObject jsonObject = new JSONObject();
		JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
		jsonBinder.setDateFormat("yyyy-MM-dd");
		try {
			boolean isBind = false;
			insureFlowDto = new InsureFlowDto();
			QuoteMain quoteMain = new QuoteMain();
			quoteMain.setQuoteNo(quoteNo);
			quoteMain.setProductCode(productCode);
			quoteMain.setProposalSID(proposalSID);
			insureFlowDto.setQuoteMain(quoteMain);
			if(copyTemporary(quoteNo)){
				GeDirectory geDirectory = geDirectoryService.findGeDirectoryByEId(insureFlowDto.getQuoteMain().getEid());
				if (geDirectory != null) {
					insureFlowDto.setProductCode(geDirectory.getCoreProductCode());
				}
				/**set userId*/
				GeUserPersonal geUserPersonal = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
				if(StringUtils.isBlank(productCode)){
					if(insureFlowDto != null){
						productCode = insureFlowDto.getProductCode();
					}
				}
				GeSaleProduct geSaleProduct = geSaleProductService.findByCode(productCode);
				String isLogin = "1";
				if(geUserPersonal != null && geSaleProduct != null){
					isBind = bindPolicyService.hasPolicy(geUserPersonal.getUserID());
					if(insureFlowDto != null){
						insureFlowDto.getQuoteMain().setUserId(geUserPersonal.getUserID());
						insureFlowDto.getQuoteMain().setQuoteNo(quoteNo);
						insureFlowDto.getQuoteMain().setProposalSID(proposalSID);
					}
					obtainTopInsuredData(geSaleProduct, geUserPersonal);
					obtainInsuranceData(productCode,geSaleProduct,"insurePage");
				}else{
					isLogin = "0";
					return "productCenter";
				}
				insureFlowDto.setInsuranceType("1");
//				System.out.println("###: "+jsonBinder.toJson(insureFlowDto));
				String JsonSTR = jsonBinder.toJson(insureFlowDto);
				super.getRequest().setAttribute("JsonSTR", JsonSTR==null?"":JsonSTR.replaceAll("\"", "\\\\\""));
				super.getRequest().setAttribute("isLogin", isLogin);
				super.getRequest().setAttribute("quoteNo", quoteNo);
				super.getRequest().setAttribute("proposalSID", proposalSID);
				super.getRequest().setAttribute("geSaleProduct", geSaleProduct);
				super.getRequest().setAttribute("geUserPersonal", geUserPersonal);
				super.getRequest().setAttribute("proposalContNo", proposalContNo);
				super.getRequest().setAttribute("isBind", isBind);
				return SUCCESS;
			}else{
				return "productCenter";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}
	
	public boolean copyTemporary(String quoteNo){
		InsureFlowDto temporary = saleService.getInsureFlowDtoForConInsu(quoteNo);  //���ݿ�
		
		/**���㵥����*/
		QuoteMain quoteMain = insureFlowDto.getQuoteMain();
		QuoteMain temQuoteMain = temporary.getQuoteMain();
		
		if(temQuoteMain!=null){
			quoteMain.setAgeInterval(temQuoteMain.getAgeInterval());
			quoteMain.setQuoteNo(quoteNo);
			quoteMain.setHxssProposalNo(temQuoteMain.getHxssProposalNo());
			quoteMain.setQuoteStatus(temQuoteMain.getQuoteStatus());
			quoteMain.setQuoteStatusName(temQuoteMain.getQuoteStatusName());
			quoteMain.setQuoteStatusDesc(temQuoteMain.getQuoteStatusDesc());
			quoteMain.setInsuredAmount(temQuoteMain.getInsuredAmount());
			quoteMain.setPremium(temQuoteMain.getPremium());
			quoteMain.setDiscountPremium(temQuoteMain.getDiscountPremium());
			quoteMain.setGrossPremium(temQuoteMain.getGrossPremium());
			quoteMain.setFaceAmount(temQuoteMain.getFaceAmount());
			quoteMain.setProductCode(temQuoteMain.getProductCode());
			quoteMain.setProductName(temQuoteMain.getProductName());
			quoteMain.setFirstPremium(temQuoteMain.getFirstPremium());
			quoteMain.setInitialPremAmt(temQuoteMain.getInitialPremAmt());
			quoteMain.setBenefitPeriod(temQuoteMain.getBenefitPeriod());
			quoteMain.setBenefitPeriodType(temQuoteMain.getBenefitPeriodType());
			quoteMain.setPaymentMode(temQuoteMain.getPaymentMode());
			quoteMain.setPaymentDuration(temQuoteMain.getPaymentDuration());
			quoteMain.setPaymentDurationMode(temQuoteMain.getPaymentDurationMode());
			quoteMain.setPaymentMethod(temQuoteMain.getPaymentMethod());
			quoteMain.setBenefitMode(temQuoteMain.getBenefitMode());
			quoteMain.setDivType(temQuoteMain.getDivType());
			quoteMain.setAnnuityPayoutDuration(temQuoteMain.getAnnuityPayoutDuration());
			quoteMain.setAnnuityPayoutDurationMode(temQuoteMain.getAnnuityPayoutDurationMode());
			quoteMain.setPayoutStart(temQuoteMain.getPayoutStart());
			quoteMain.setPayoutEnd(temQuoteMain.getPayoutEnd());
			quoteMain.setExcessPremAmt(temQuoteMain.getExcessPremAmt());
			quoteMain.setPolicyDeliveryFee(temQuoteMain.getPolicyDeliveryFee());
			quoteMain.setPolicyStatusMessage(temQuoteMain.getPolicyStatusMessage());
			quoteMain.setInputDate(temQuoteMain.getInputDate());
			quoteMain.setApplicationDate(temQuoteMain.getApplicationDate());
			quoteMain.setInsuranceStartPeriod(temQuoteMain.getInsuranceStartPeriod());
			quoteMain.setInsuranceEndPeriod(temQuoteMain.getInsuranceEndPeriod());
			quoteMain.setBeneficiaryMode(temQuoteMain.getBeneficiaryMode());
			quoteMain.setUnitCount(temQuoteMain.getUnitCount());
			quoteMain.setSpecialStatement(temQuoteMain.getSpecialStatement());
			quoteMain.setContractNames(temQuoteMain.getContractNames());
			quoteMain.setBankCode(temQuoteMain.getBankCode());
			quoteMain.setDeliveryInvoice(temQuoteMain.getDeliveryInvoice());
			quoteMain.setDeliveryHardCopy(temQuoteMain.getDeliveryHardCopy());
			quoteMain.setDeliveryEPolicy(temQuoteMain.getDeliveryEPolicy());
			quoteMain.setCampaignCode(temQuoteMain.getCampaignCode());
			quoteMain.setCampaignName(temQuoteMain.getCampaignName());
			quoteMain.setDiscountTypeCode(temQuoteMain.getDiscountTypeCode());
			quoteMain.setDiscountRate(temQuoteMain.getDiscountRate());
			quoteMain.setAutoRenewable(temQuoteMain.getAutoRenewable());
			quoteMain.setComboCode(temQuoteMain.getComboCode());
			quoteMain.setComboName(temQuoteMain.getComboName());
			quoteMain.setUserId(temQuoteMain.getUserId());
			quoteMain.setUserType(temQuoteMain.getUserType());
			quoteMain.setAgentCode(temQuoteMain.getAgentCode());
			quoteMain.setAgreementNo(temQuoteMain.getAgreementNo());
			quoteMain.setAgentName(temQuoteMain.getAgentName());
			quoteMain.setDepartmentNo(temQuoteMain.getDepartmentNo());
			quoteMain.setDepartmentName(temQuoteMain.getDepartmentName());
			quoteMain.setIntermediaryCode(temQuoteMain.getIntermediaryCode());
			quoteMain.setIntermediaryName(temQuoteMain.getIntermediaryName());
			quoteMain.setBranchCode(temQuoteMain.getBranchCode());
			quoteMain.setBranchName(temQuoteMain.getBranchName());
			quoteMain.setOrganizationCode(temQuoteMain.getOrganizationCode());
			quoteMain.setOrganizationName(temQuoteMain.getOrganizationName());
			quoteMain.setInstitutionCode(temQuoteMain.getInstitutionCode());
			quoteMain.setInstitutionName(temQuoteMain.getInstitutionName());
			quoteMain.setAreaCodePro(temQuoteMain.getAreaCodePro());
			quoteMain.setAreaCode(temQuoteMain.getAreaCode());
			quoteMain.setLeaveSegment(temQuoteMain.getLeaveSegment());
			quoteMain.setLeaveWay(temQuoteMain.getLeaveWay());
			quoteMain.setRenewalFlag(temQuoteMain.getRenewalFlag());
			quoteMain.setOldlPolicyNo(temQuoteMain.getOldlPolicyNo());
			quoteMain.setInputHour(temQuoteMain.getInputHour());
			quoteMain.setInceptionDate(temQuoteMain.getInceptionDate());
			quoteMain.setEndDate(temQuoteMain.getEndDate());
			quoteMain.setInvalidDate(temQuoteMain.getInvalidDate());
			quoteMain.setBusinessSource(temQuoteMain.getBusinessSource());
			quoteMain.setGroupChannel(temQuoteMain.getGroupChannel());
			quoteMain.setSellType(temQuoteMain.getSellType());
			quoteMain.setRecommendType(temQuoteMain.getRecommendType());
			quoteMain.setRecommend(temQuoteMain.getRecommend());
			quoteMain.setQuoteApplicant(temQuoteMain.getQuoteApplicant());
			quoteMain.setQuoteInsuredNumber(temQuoteMain.getQuoteInsuredNumber());
			quoteMain.getQuoteInsureds().addAll(temQuoteMain.getQuoteInsureds());
			quoteMain.setQuoteBeneficiaryNumber(temQuoteMain.getQuoteBeneficiaryNumber());
			quoteMain.getQuoteBeneficiaries().addAll(temQuoteMain.getQuoteBeneficiaries());
			quoteMain.setQuoteLiabilityNumber(temQuoteMain.getQuoteLiabilityNumber());
			quoteMain.getQuoteLiabilities().addAll(temQuoteMain.getQuoteLiabilities());
			quoteMain.setExPayMode(temQuoteMain.getExPayMode());
			quoteMain.setGetPolMode(temQuoteMain.getGetPolMode());
			quoteMain.setPassword(temQuoteMain.getPassword());
			quoteMain.setSpecContent(temQuoteMain.getSpecContent());
			quoteMain.setTempFeeNo(temQuoteMain.getTempFeeNo());
			quoteMain.setAgentGroup(temQuoteMain.getAgentGroup());
			quoteMain.setEid(temQuoteMain.getEid());
			quoteMain.setCombDesc(temQuoteMain.getCombDesc());
			quoteMain.setBenefitDesc(temQuoteMain.getBenefitDesc());
			quoteMain.setQuoteInsureInformBookNumber(temQuoteMain.getQuoteInsureInformBookNumber());
			quoteMain.setQuoteInsureInformBooks(temQuoteMain.getQuoteInsureInformBooks());
			quoteMain.setProposalSID(temQuoteMain.getProposalSID());
			quoteMain.setStep(temQuoteMain.getStep());
			quoteMain.setFlag(temQuoteMain.getFlag());
			quoteMain.setCurrency(temQuoteMain.getCurrency());

			insureFlowDto.setQuoteMain(quoteMain);
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * ��ѯְҵ
	 * @return
	 */
	
	public String obtainOccupation(){
		String keyword = super.getRequest().getParameter("keyword");
		List occupations = occupationService.findOccupationInfoByLikeName(keyword);
		super.render(JsonBinder.buildNonNullBinder().toJson(occupations), "application/json;charset=GBK");
		return NONE;
	}
	
	/**
	 * �����뿪
	 * @return
	 */
	public String saveQuoteAndExit(){
		String result = "";
		try {
			QuoteApplicant qa = insureFlowDto.getQuoteMain().getQuoteApplicant();
			/**set userId*/
			GeUserPersonal geUserPersonal = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
			if(geUserPersonal != null){
				insureFlowDto.getQuoteMain().setUserId(geUserPersonal.getUserID());
				if(geUserPersonal != null){
					if(StringUtils.isBlank(geUserPersonal.getUserName())){
						geUserPersonal.setUserName(qa.getFullName());
					}
					if(StringUtils.isBlank(geUserPersonal.getSex())){
						geUserPersonal.setSex(qa.getGender()==null?"0":qa.getGender().toString());
					}
					if(geUserPersonal.getBirthday() == null){
						geUserPersonal.setBirthday(qa.getBirthDate());
					}
					if(StringUtils.isBlank(geUserPersonal.getIdentifyType())){
						geUserPersonal.setIdentifyType(qa.getIdType()==null?"0":qa.getIdType().toString());
					}
					if(StringUtils.isBlank(geUserPersonal.getIdentifyNumber())){
						geUserPersonal.setIdentifyNumber(qa.getIdNumber());
					}
					geUserPersonalService.updateUserPersonal(geUserPersonal);
				}
			}else{
				result = "ϵͳ��æ�����Ժ����ԣ�";
			}
			if (StringUtils.isBlank(result)) {
				String save_topInsured = super.getRequest().getParameter("save_topInsured");
				String quoteNo = super.getRequest().getParameter("quoteNo");
				String proposalSID = super.getRequest().getParameter("proposalSID");
				String proposalContNo = super.getRequest().getParameter("proposalContNo");
				String step = super.getRequest().getParameter("step");
				if(StringUtils.isBlank(quoteNo)){
					quoteNo =  bizCommonService.takeSerialNo("04",new Date(), QuoteMain.class);
				}
				QuoteMain tempQuoteMain = quoteMainService.findQuoteMainByPk(quoteNo);
				if(tempQuoteMain != null){
					quoteMainService.deleteQuoteMain(quoteNo);
				}
				boolean bl = saleService.saveQuote(insureFlowDto,step,quoteNo,geUserPersonal.getUserID(),save_topInsured);
				InsurancePolicy insurancePolicy = null;
				if(bl){
					if (StringUtils.isNotBlank(proposalSID)) {
						insurancePolicy = insurancePolicyService.findInsurancePolicyBySerialNo(proposalSID);
					}
					if(insurancePolicy != null){
						insurancePolicyService.deleteInsurancePolicy(insurancePolicy);
					}
					//����ҵ����Դ��WEB��
					insureFlowDto.getQuoteMain().setBusinessSource("WEB_PERSON");
					//�����������루W����������
					insureFlowDto.getQuoteMain().setGroupChannel("W");
					insureFlowDto.getQuoteMain().setSellType("20");
					insurancePolicy = saleService.createInsurancePolicy(insureFlowDto);
					insurancePolicy.setQuoteNo(quoteNo);
					insureFlowDto.getQuoteMain().setHxssProposalNo(proposalContNo);
					insureFlowDto.getQuoteMain().setProposalSID(insurancePolicy.getSerialNo());
					insureFlowDto.setProposalSID(insurancePolicy.getSerialNo());
					OrderForm order = createOrderOfPolicy(insurancePolicy,"0");
					result = "success";
					sendEmail(1, order, insurancePolicy);
				}else{
					result = "�ܱ�Ǹ���뿪����ʧ�ܣ�";
				}
			}
		} catch (Exception e) {
			log.error(StringUtils.exception2String(e));
		}
		super.render(JsonBinder.buildNonNullBinder().toJson(result), "application/json;charset=GBK");
		return NONE;
	}
	
	/**
	 * �������㵥����
	 * @return
	 */
	public String updateQuoteStep(){
		JSONObject jsonObject = new JSONObject();
		JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
		jsonBinder.setDateFormat("yyyy-MM-dd");
		String result = "";
		try {
			String step = super.getRequest().getParameter("step");
			String quoteNo = super.getRequest().getParameter("quoteNo");
			QuoteMain quoteMain = quoteMainService.findQuoteMainByPk(quoteNo);
			quoteMain.setStep(Integer.parseInt(step));
			boolean bl = quoteMainService.updateQuoteMain(quoteMain);
			if(insureFlowDto == null)
				insureFlowDto = new InsureFlowDto();
			insureFlowDto.setQuoteMain(quoteMain);
			if(bl){
				String proposalSID = quoteMain.getProposalSID()==null?"":quoteMain.getProposalSID();
				System.out.println("###proposalSID: "+proposalSID);
				InsurancePolicy insurancePolicy = insurancePolicyService.findInsurancePolicyBySerialNo(proposalSID);
				if(insurancePolicy != null){
					//�˱�_����
					insurancePolicy.setTransIdentify(TransType.ST000034.getCoreValue());
					Map<String, Object> resultMap = underwritingService.underwriting(insurancePolicy, TransType.ST000022.getCoreValue());
//					if (resultMap != null) {
//						if (resultMap.get("flag").equals("1")) {
//							result = "success";
//						} else {
//							result = "���Ĵ���ʧ�ܣ�����ϵ����Ա";
//						}
//					} else {
//						result = "ϵͳ��æ�����Ժ����ԣ�";
//					}

					boolean updateFlag = false;
					//���¿�
					if (resultMap != null) {
						LCCont lcCont = resultMap.get("LCCont")==null?null:(LCCont)resultMap.get("LCCont");
						updateFlag = true;
						if (resultMap.get("flag").equals("1")) {
							if(lcCont != null){
								if(saleService.saveQuote(lcCont, insureFlowDto, insurancePolicy)){
									jsonObject.put("proposalContNo", lcCont.getProposalContNo());
									insurancePolicy.setApplicationNumber(lcCont.getProposalContNo());
//									insurancePolicy.setPolicyStatus(PolicyStatus.PROPOSAL_SUCC.getValue());
//									insurancePolicy.setPolicyStatusName(PolicyStatus.PROPOSAL_SUCC.getDataName());
//									insurancePolicy.setPolicyStatusDesc(PolicyStatus.PROPOSAL_SUCC.getDataName());
//									insurancePolicy.setPrecheckDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//									insurancePolicy.setReason("�˱��ɹ����˱����ڣ�" + new Date().toString());
									result = "success";
								}
							}else{
								result = "ϵͳ��æ�����Ժ����ԣ�";
							}
						} else {
							jsonObject.put("proposalContNo", lcCont.getProposalContNo());
							insurancePolicy.setApplicationNumber(lcCont.getProposalContNo());
//							insurancePolicy.setPolicyStatus(PolicyStatus.PROPOSAL_FAIL.getValue());
//							insurancePolicy.setPolicyStatusName(PolicyStatus.PROPOSAL_FAIL.getDataName());
//							insurancePolicy.setPolicyStatusDesc(PolicyStatus.PROPOSAL_FAIL.getDataName());
//							insurancePolicy.setReason("�˱�ʧ�ܣ��˱����ڣ�" + new Date().toString() + "��ʧ��ԭ�򣺣�" + resultMap.get("desc") + "��");
							result = "";
							if(resultMap.get("desc") != null && !resultMap.get("desc").equals("")){
								result += "��"+resultMap.get("desc")+"��";
							}
						}
						if(result.equals("success") || updateFlag){
							updateOrderOfPolicy(insurancePolicy);
							insurancePolicyService.updateInsurancePolicy(insurancePolicy);
						}
					} else {
						result = "ϵͳ��æ�����Ժ����ԣ�";
					}
				
				}
			}else{
				result = "�ܱ�Ǹ���뿪����ʧ�ܣ�";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(StringUtils.isBlank(result)){
			result = "ϵͳ��æ�����Ժ����ԣ�";
		}
		jsonObject.put("result", result);
		super.render(jsonObject.toString(), "application/json;charset=GBK");
		return NONE;
	}
	
	/**
	 * �����˱�
	 * @return
	 */
	public String underwriting(){
		String result = "";
		boolean isCreate = true;
		QuoteApplicant qa = null;
		if(insureFlowDto.getQuoteMain() != null && insureFlowDto.getQuoteMain().getQuoteApplicant() !=null)
			qa = insureFlowDto.getQuoteMain().getQuoteApplicant();
		//�Զ�ע���¼
		//�Ƿ���Ҫ�Զ�ע���־��1��ʾ��Ҫ�Զ�ע��
		JSONObject jsonObject = new JSONObject();
		AutoUserDto au = new AutoUserDto();
		try {
			if(qa != null){
				au.setFullName(qa.getFullName());
				au.setGender(qa.getGender());
				au.setBirthDate(qa.getBirthDate());
				au.setIdType(qa.getIdType());
				au.setIdNumber(qa.getIdNumber());
				au.setEmail(qa.getEmail());
				au.setMobilePhone(qa.getMobilePhoneNumber());
				au = userService.autoRegisterAndLogin(au,super.getSession(false));
				if("-1".equals(au.getRegFlag())){
					jsonObject.put("regFlag",au.getRegFlag());
					JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
					jsonBinder.setDateFormat("yyyy-MM-dd");
					jsonObject.put("JsonSTR", jsonBinder.toJson(insureFlowDto));
					super.render(jsonObject.toString(), "application/json;charset=GBK");
					return NONE;
				}else if("1".equals(au.getRegFlag())){
					List<String> params = new ArrayList<String>();
					//���ŷ�����
					String sender = "9005";
					//��������
					String msgComment = "ϵͳ��Ϊ���Զ�ע�ᣬ����Ϊ" + au.getPlaintextPassword() + "��";
					params.add(au.getMobilePhone());
					params.add(au.getPlaintextPassword());
					smsSendService.smsSend(true, "8", params, "1", au.getMobilePhone(), msgComment, sender, null);
				}
				GeUserPersonal geUserPersonal = (GeUserPersonal)super.getSession(false).getAttribute("geUserPersonal");
				insureFlowDto.getQuoteMain().setUserId(geUserPersonal.getUserID());
				if(geUserPersonal != null){
					if(StringUtils.isBlank(geUserPersonal.getUserName())){
						geUserPersonal.setUserName(qa.getFullName());
					}
					if(StringUtils.isBlank(geUserPersonal.getSex())){
						geUserPersonal.setSex(qa.getGender()==null?"0":qa.getGender().toString());
					}
					if(geUserPersonal.getBirthday() == null){
						geUserPersonal.setBirthday(qa.getBirthDate());
					}
					if(StringUtils.isBlank(geUserPersonal.getIdentifyType())){
						geUserPersonal.setIdentifyType(qa.getIdType()==null?"0":qa.getIdType().toString());
					}
					if(StringUtils.isBlank(geUserPersonal.getIdentifyNumber())){
						geUserPersonal.setIdentifyNumber(qa.getIdNumber());
					}
					geUserPersonal.setProvinces(qa.getProvince());
					geUserPersonal.setCity(qa.getCity());
					geUserPersonal.setArea(qa.getCounty());
					geUserPersonal.setContactAddress(qa.getAddressLines());
					geUserPersonal.setZipCode(qa.getPostalCode());
					geUserPersonalService.updateUserPersonal(geUserPersonal);
				}
			}else{
				result = "ϵͳ��æ�����Ժ����ԣ�";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "ϵͳ��æ�����Ժ����ԣ�";
		}
		
		//У��Ͷ�����ֻ���
		Map<String, String> verificationMobileMap = null;
		if(geFunctionSwitchService.isSwitchOpen("verificationMobilePhoneNumber")){
			verificationMobileMap = verificationService.verificationMobilePhoneNumber(qa.getFullName(), qa.getIdType()==null?"":qa.getIdType().toString(), qa.getIdNumber(), qa.getMobilePhoneNumber());
		}
		if(verificationMobileMap != null){
			if(!verificationMobileMap.get("flag").equals("1")){
				result = verificationMobileMap.get("desc");
			}
		}
		
		List<QuoteInsured> quoteInsureds = insureFlowDto.getQuoteMain().getQuoteInsureds();
		QuoteInsured quoteInsured = null;
		if(quoteInsureds != null && !quoteInsureds.isEmpty()){
			quoteInsured = quoteInsureds.get(0);
		}
		//У�鱻���������֤
		Map<String, String> verificationIdCardMap = null;
		if(geFunctionSwitchService.isSwitchOpen("verificationIdCard") && quoteInsured != null && StringUtils.isBlank(result)){
			String fsd = quoteInsured.getIdNumber().length()<6?quoteInsured.getIdNumber():quoteInsured.getIdNumber().substring(0,6);
			verificationIdCardMap = verificationService.verificationIdCard(quoteInsured.getIdNumber(), quoteInsured.getFullName(), fsd, "WEB");
		}
		if(verificationIdCardMap != null){
			if(!verificationIdCardMap.get("flag").equals("1")){
				result = verificationIdCardMap.get("desc");
			}
		}
				
		String save_topInsured = super.getRequest().getParameter("save_topInsured");
		//���ݴ�������ֵ����һЩ����
		GeUserPersonal geUserPersonal = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		String userID = insureFlowDto.getQuoteMain().getUserId();
		if (geUserPersonal == null) {
			result = "ϵͳ��æ�����Ժ����ԣ�";
		}
		String quoteNo = super.getRequest().getParameter("quoteNo");
		String proposalSID = super.getRequest().getParameter("proposalSID");
		String proposalContNo = super.getRequest().getParameter("proposalContNo");
		if(StringUtils.isNotBlank(quoteNo)){
			QuoteMain quoteMain = quoteMainService.findQuoteMainByPk(quoteNo);
			if(quoteMain != null){
				insureFlowDto.getQuoteMain().setHxssProposalNo(quoteMain.getHxssProposalNo());
				insureFlowDto.getQuoteMain().setProposalSID(quoteMain.getProposalSID()==null?proposalSID:quoteMain.getProposalSID());
				isCreate = false;
			}
		}
		//���㵥���
		if (StringUtils.isBlank(result)) {
			boolean bl = false;
			try {
				if(StringUtils.isBlank(quoteNo)){
					quoteNo =  bizCommonService.takeSerialNo("04",new Date(), QuoteMain.class);
				}
				QuoteMain tempQuoteMain = quoteMainService.findQuoteMainByPk(quoteNo);
				if(tempQuoteMain != null){
					quoteMainService.deleteQuoteMain(quoteNo);
				}
				bl = saleService.saveQuote(insureFlowDto,"3",quoteNo,geUserPersonal.getUserID(),save_topInsured);
				super.getSession().setAttribute(userID+"_quoteNo", quoteNo);
				if(bl){
					insureFlowDto.getQuoteMain().setQuoteNo(quoteNo);
					insureFlowDto.getQuoteMain().setHxssProposalNo(proposalContNo);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("���㵥�������쳣���û��ţ�" + geUserPersonal.getUserID() + "���쳣��ջ��Ϣ��" + e.getMessage());
				result = "ϵͳ��æ�����Ժ����ԣ�";
			}
			if(!bl){
				result = "ϵͳ��æ�����Ժ����ԣ�";
			}
		}
		
		InsurancePolicy insurancePolicy = null;
		//��⣬�Ȳ�ѯ�����û����ֱ����⣬����ɾ�������������
		if (StringUtils.isBlank(result)) {
			try {
				if(StringUtils.isBlank(proposalSID)){
					insureFlowDto.getQuoteMain().getProposalSID();//Ͷ�������
				}
				if (StringUtils.isNotBlank(proposalSID)) {
					insurancePolicy = insurancePolicyService.findInsurancePolicyBySerialNo(proposalSID);
				}
				if(insurancePolicy != null){
					insurancePolicyService.deleteInsurancePolicy(insurancePolicy);
				}
				//����ҵ����Դ��WEB��
				insureFlowDto.getQuoteMain().setBusinessSource("WEB_PERSON");
				//�����������루W����������
				insureFlowDto.getQuoteMain().setGroupChannel("W");
				insureFlowDto.getQuoteMain().setSellType("20");
				insurancePolicy = saleService.createInsurancePolicy(insureFlowDto);
				insurancePolicy.setQuoteNo(quoteNo);
				insureFlowDto.getQuoteMain().setProposalSID(insurancePolicy.getSerialNo());
				insureFlowDto.setProposalSID(insurancePolicy.getSerialNo());
				//�ӹ�
				if ("1".equals(insureFlowDto.getMoreBuyFlag())) {
					//��Ӽӹ��������
					String moreBuyNo = bizCommonService.takeSerialNo("05",new Date(), InsurancePolicy.class);
					insurancePolicy.setMoreBuyNo(moreBuyNo);
					String moreBuyProposalSID = insureFlowDto.getMoreBuyProposalSID();
					if (StringUtils.isNotBlank(moreBuyProposalSID)) {
						List<InsurancePolicy> insurancePolicies = insurancePolicyService.findInsurancePoliciesByIds(moreBuyProposalSID);
						for (InsurancePolicy _insurancePolicy : insurancePolicies) {
							_insurancePolicy.setMoreBuyNo(moreBuyNo);
						}
						insureFlowDto.setMoreBuyProposalSID(moreBuyProposalSID + "," + insurancePolicy.getSerialNo());
					}
				}
			} catch (SaleException e) {
				result = "ϵͳ��æ�����Ժ����ԣ���" + e.showMessage() + "��";
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("Ͷ�����������쳣���û��ţ�" + geUserPersonal.getUserID() + "���쳣��Ϣ��" + e.getMessage());
				result = "ϵͳ��æ�����Ժ����ԣ�";
			}
		}
		if (StringUtils.isBlank(result)) {
			try {
				//�˱�
				insurancePolicy.setTransIdentify(TransType.ST000034.getCoreValue());
				Map<String, Object> resultMap = underwritingService.underwriting(insureFlowDto, insurancePolicy, geUserPersonal);
				
				//���¿�
				if (resultMap != null) {
					LCCont lcCont = resultMap.get("LCCont")==null?null:(LCCont)resultMap.get("LCCont");
					if (resultMap.get("flag").equals("1")) {
						if(lcCont != null){
							if(saleService.saveQuote(lcCont, insureFlowDto, insurancePolicy)){
								jsonObject.put("proposalContNo", lcCont.getProposalContNo());
//								insurancePolicy.setApplicationNumber(lcCont.getProposalContNo());
								insurancePolicy.setPolicyStatus(PolicyStatus.PROPOSAL_SUCC.getValue());
								insurancePolicy.setPolicyStatusName(PolicyStatus.PROPOSAL_SUCC.getDataName());
								insurancePolicy.setPolicyStatusDesc(PolicyStatus.PROPOSAL_SUCC.getDataName());
								insurancePolicy.setPrecheckDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
								insurancePolicy.setReason("�˱��ɹ����˱����ڣ�" + new Date().toString());
								result = "success";
							}
						}else{
							result = "ϵͳ��æ�����Ժ����ԣ�";
						}
					} else {
						String lcContProposalContNo = "";
						if(lcCont != null){
							lcContProposalContNo = lcCont.getProposalContNo();
						}
						jsonObject.put("proposalContNo", lcContProposalContNo);
//						insurancePolicy.setApplicationNumber(lcCont.getProposalContNo());
						insurancePolicy.setPolicyStatus(PolicyStatus.PROPOSAL_FAIL.getValue());
						insurancePolicy.setPolicyStatusName(PolicyStatus.PROPOSAL_FAIL.getDataName());
						insurancePolicy.setPolicyStatusDesc(PolicyStatus.PROPOSAL_FAIL.getDataName());
						insurancePolicy.setReason("�˱�ʧ�ܣ��˱����ڣ�" + new Date().toString() + "��ʧ��ԭ�򣺣�" + resultMap.get("desc") + "��");
						result = "����д����Ϣ�����ϸò�ƷͶ��������������޸�";
						if(resultMap.get("desc") != null && !resultMap.get("desc").equals("")){
							result += "��"+resultMap.get("desc")+"��";
						}
					}
					//�˱��ɹ��������ʼ������û�ȥ֧��
					if(result.equals("success")){
						OrderForm order = createOrderOfPolicy(insurancePolicy,"1");
						insurancePolicyService.updateInsurancePolicy(insurancePolicy);
						sendEmail(2,order,insurancePolicy);
					}
				} else {
					createOrderOfPolicy(insurancePolicy,"0");
					result = "ϵͳ��æ�����Ժ����ԣ�";
				}
			} catch (SaleException e) {
				result = "ϵͳ��æ�����Ժ����ԣ���" + e.showMessage() + "��";
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("�˱������������쳣���û��ţ�" + geUserPersonal.getUserID() + "���쳣��Ϣ��" + e.getMessage());
				result = "ϵͳ��æ�����Ժ����ԣ�";
			}
		}

		if(StringUtils.isBlank(result)){
			result = "ϵͳ��æ�����Ժ����ԣ�";
		}
		jsonObject.put("result", result);
		jsonObject.put("regFlag",au.getRegFlag());
		jsonObject.put("quoteNo", quoteNo);
		if(StringUtils.isBlank(proposalSID) && insurancePolicy != null){
			proposalSID = insurancePolicy.getSerialNo();
		}
		if ("1".equals(insureFlowDto.getMoreBuyFlag())) {
			jsonObject.put("proposalSID", insureFlowDto.getMoreBuyProposalSID());
		} else {
			jsonObject.put("proposalSID", proposalSID);
		}
		JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
		jsonBinder.setDateFormat("yyyy-MM-dd");
//		System.out.println("unw: "+jsonBinder.toJson(insureFlowDto));
		jsonObject.put("JsonSTR", jsonBinder.toJson(insureFlowDto));
		super.render(jsonObject.toString(), "application/json;charset=GBK");
		return NONE;
	}
	
	/**
	 * ���֤У��
	 * @return
	 */
	public String verificationIdCard(){
		String result = "ϵͳ��æ�����Ժ����ԣ�";
		JSONObject jsonObject = new JSONObject();
		try {
			String idNumber = super.getRequest().getParameter("idNumber");
			String fullName = super.getRequest().getParameter("fullName");
			String takes_place = super.getRequest().getParameter("takes_place");
			String businessType = super.getRequest().getParameter("businessType")==null?"WEB_PERSON":super.getRequest().getParameter("businessType");
			Map<String, String> map = null;
			if(geFunctionSwitchService.isSwitchOpen("verificationIdCard")){
				map = verificationService.verificationIdCard(idNumber, fullName, takes_place, businessType);
			}else{
				map = new HashMap<String, String>();
				map.put("flag", "0");
			}
			
			if(map != null){
				if(StringUtils.isNotBlank(map.get("flag")) && (map.get("flag").equals("0") || map.get("flag").equals("2"))){
					result = "success";
				}else{
					result = map.get("desc");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("���֤У��ʧ�ܣ��쳣��Ϣ��"+StringUtils.exception2String(e));
		}
		jsonObject.put("result", result);
		super.render(jsonObject.toString(), "application/json;charset=GBK");
		return NONE;
	}
	
	/**
	 * �Ƽ����ֻ�����֤
	 * @return
	 */
	public String countMobilePhoneNumber(){
		JSONObject jsonObject = new JSONObject();
		try {
			String referencePhone = super.getRequest().getParameter("referencePhone");
			System.out.println("referencePhone: "+referencePhone);
			int count = geUserPersonalService.countUserPersonalByMobile(referencePhone);
			System.out.println("count: "+count);
			jsonObject.put("count", count+"");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("�������ֻ�����֤ʧ�ܣ��쳣��Ϣ��"+StringUtils.exception2String(e));
		}
		super.render(jsonObject.toString(), "application/json;charset=GBK");
		return NONE;
	}
	
	
	/**
	 * �ֻ���У��
	 * @return
	 */
	public String verificationMobilePhoneNumber(){
		String result = "ϵͳ��æ�����Ժ����ԣ�";
		JSONObject jsonObject = new JSONObject();
		try {
			String fullName = super.getRequest().getParameter("fullName");
			String idType = super.getRequest().getParameter("idType");
			String idNumber = super.getRequest().getParameter("idNumber");
			String mobilePhoneNumber = super.getRequest().getParameter("mobilePhoneNumber");
			Map<String, String> map = null;
			GeUserPersonal geUserPersonal = (GeUserPersonal)super.getSession(false).getAttribute("geUserPersonal");
			if(geUserPersonal != null){
				if(geFunctionSwitchService.isSwitchOpen("verificationIdCard")){
					map = verificationService.verificationMobilePhoneNumber(fullName, idType, idNumber, mobilePhoneNumber);
				}else{
					map = new HashMap<String, String>();
					map.put("flag", "1");
				}
			}else{
				int count = geUserPersonalService.countUserPersonalByMobile(mobilePhoneNumber);
				map = new HashMap<String, String>();
				jsonObject.put("count", count+"");
			}
			if(map != null){
				if(StringUtils.isNotBlank(map.get("flag")) && map.get("flag").equals("1")){
					result = "success";
				}else{
					result = map.get("desc");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("�ֻ���У��ʧ�ܣ��쳣��Ϣ��"+StringUtils.exception2String(e));
		}
		jsonObject.put("result", result);
		super.render(jsonObject.toString(), "application/json;charset=GBK");
		return NONE;
	}
	
	/**
	 * ���뵽ѡ��֧��ҳ��
	 * @return
	 */
	public String toPayConfirmInfo(){
		String proposalSID = super.getRequest().getParameter("proposalSID");
		String eId = super.getRequest().getParameter("eId");
		try {
			List<InsurancePolicy> insurancePolicys = null;
			if(proposalSID != null && proposalSID.indexOf(",") != -1){
				insurancePolicys = insurancePolicyService.findInsurancePoliciesByIds(proposalSID);
			}else{
				InsurancePolicy insurancePolicy = insurancePolicyService.findInsurancePolicyBySerialNo(proposalSID);
				if(insurancePolicy!=null){
					insurancePolicys = new ArrayList<InsurancePolicy>();
					insurancePolicys.add(insurancePolicy);
				}
			}
			
			if (insurancePolicys == null || insurancePolicys.isEmpty()) {
				return ERROR;
			}
			BigDecimal sumPremium = new BigDecimal("0.00");
			StringBuffer proposalNoBuffer = new StringBuffer();
			List<String> deptIds = new ArrayList<String>();
			for (InsurancePolicy insurancePolicy : insurancePolicys) {
				deptIds.add(insurancePolicy.getOrganizationCode());
				sumPremium = sumPremium.add(insurancePolicy.getGrossPremium() != null ?insurancePolicy.getGrossPremium() : new BigDecimal("0"));
				proposalNoBuffer.append("," + insurancePolicy.getSerialNo());
			}
			List<GePayment> payments = null;
			if(deptIds.size() == 1){
//				payments = gePaymentService.findPaymentsByDeptId(deptIds.get(0));
			}else{
//				payments = gePaymentService.findPaymentsByDeptIds(deptIds);
			}

			/**��ȡ֤������������Ϣ*/
			List<GeCode> identifyTypeList = geCodeService.findAllByGeCodeType("IdentifyType");//֤������
			
			GeDirectory geDirectory = null;
			if(StringUtils.isNotBlank(eId)){
				geDirectory = geDirectoryService.findGeDirectoryByEId(eId);
			}else{
				if (insurancePolicys != null || !insurancePolicys.isEmpty()) {
					QueryRule queryRule = QueryRule.getInstance();
					queryRule.addEqual("coreProductCode", insurancePolicys.get(0).getProductCode());
					List<GeDirectory> geDirectorys = geDirectoryService.findGeDirectoryByQueryRule(queryRule);
					if(geDirectorys != null && !geDirectorys.isEmpty()){
						geDirectory = geDirectorys.get(0);
					}
				}
			}
			InsurancePolicy insurancePolicy = insurancePolicys.get(0);
			
			super.getRequest().setAttribute("identifyTypeList",identifyTypeList);
			super.getRequest().setAttribute("insurancePolicy", insurancePolicy);
			super.getRequest().setAttribute("geDirectory", geDirectory);
			super.getRequest().setAttribute("proposalSID", proposalSID);
			super.getRequest().setAttribute("eId", eId);
			super.getRequest().setAttribute("proposalNo", proposalNoBuffer.substring(1));
			super.getRequest().setAttribute("payments", payments);
			super.getRequest().setAttribute("sumPremium", sumPremium.setScale(2, RoundingMode.HALF_UP).toPlainString());
			super.getRequest().setAttribute("insurancePolicys", insurancePolicys);
			super.getRequest().setAttribute("pageName", "pay_detailed");
			//�Ƿ�Ϊ����ղ�Ʒ
			if ("00115600".equals(insurancePolicy.getProductCode())) {
				super.getRequest().setAttribute("financialRisk", true);
			} else {
				super.getRequest().setAttribute("financialRisk", false);
			}
			
		} catch (SaleException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void setSaleRecords(List<OrderForm> saleRecords) {
		this.saleRecords = saleRecords;
	}
	
	/**
	 * ajax��ѯ���ñ�������Ϣ
	 */
	public void obtainTopInsured(){
		JSONObject jsonObject = new JSONObject();
		String result = "ϵͳ��æ�����Ժ����ԣ�";
		try {
			String topId = super.getRequest().getParameter("topId");
			TopInsured topInsured = topInsuredService.getTopInsuredById(topId);
			if(topInsured != null){
				result = "success";
				jsonObject.put("topinsId", topInsured.getSerialNo());
				jsonObject.put("relationToApp", topInsured.getRelatedToApplicant());
				jsonObject.put("name", topInsured.getFullName());
				jsonObject.put("idType", topInsured.getIdType());
				jsonObject.put("idNo", topInsured.getIdNumber());
				jsonObject.put("birthday", topInsured.getBirthDate()==null?"":DateUtils.formatDate(topInsured.getBirthDate(),"yyyy-MM-dd"));
				jsonObject.put("sex", topInsured.getGender());
				jsonObject.put("email", topInsured.getEmail());
				jsonObject.put("mobilePhone", topInsured.getMobilePhoneNumber());
				jsonObject.put("address", topInsured.getAddressLines());
				jsonObject.put("zipCode", topInsured.getPostalCode());
				jsonObject.put("comPhone", topInsured.getOfficePhoneNumber());
				jsonObject.put("homePhone", topInsured.getPhoneNumber());
				jsonObject.put("province", topInsured.getProvince());
				jsonObject.put("city", topInsured.getCity());
				jsonObject.put("county", topInsured.getCounty());
				jsonObject.put("idExpDate", topInsured.getIdExpDate()==null?"":DateUtils.formatDate(topInsured.getIdExpDate(),"yyyy-MM-dd"));
				jsonObject.put("occupationCode", topInsured.getOccupationCode());
				jsonObject.put("occupationDescription", topInsured.getOccupationDescription());
				jsonObject.put("annualIncome", topInsured.getAnnualIncome());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("��ѯ���ñ�������Ϣʧ�ܣ��쳣��Ϣ��"+StringUtils.exception2String(e));
		}
		jsonObject.put("result",result);
		super.render(jsonObject.toString(), "application/json;charset=GBK");
	}
	
	/**����Ͷ������������*/
	public OrderForm createOrderOfPolicy(InsurancePolicy insurancePolicy, String flag){
		OrderForm orderForm = new OrderForm();
		String orderNo = GeneratorTransSerialNumber.generatorTransSerialNumber();
		if(insurancePolicy!=null){
			BigDecimal sumPremium = new BigDecimal("0.00").add(insurancePolicy.getGrossPremium()==null?new BigDecimal(0):insurancePolicy.getGrossPremium());
			if(geFunctionSwitchService.isSwitchOpen("payTest")){
				sumPremium = new BigDecimal("0.01");
			}
			orderForm.setOrderAmount(sumPremium);
			orderForm.setProductCode(insurancePolicy.getProductCode());
			orderForm.setProductName(insurancePolicy.getProductName());
			orderForm.setProductDesc(insurancePolicy.getProductName());
			orderForm.setProductNumber(insurancePolicy.getUnitCount());
			orderForm.setPersonalUserSerialNo(insurancePolicy.getPersonalUserSerialNo());
			orderForm.setOrderSerialNumber(orderNo);
			orderForm.setTransSerialNumber(GeneratorTransSerialNumber.generatorTransSerialNumber());
			if(flag.equals("0")){
				orderForm.setOrderStatusName(OrderStatus.INPUTED_QUOTE_INFO.getDataName());
				orderForm.setOrderStatus(OrderStatus.INPUTED_QUOTE_INFO.getValue());
			}else{
				orderForm.setOrderStatusName(OrderStatus.UNPAID.getDataName());
				orderForm.setOrderStatus(OrderStatus.UNPAID.getValue());
			}
			orderForm.setPaymentMethod(PaymentMethod.ALIPAY.getValue());
			orderForm.setPayStatus(PayStatus.UNPAID.getValue());
			orderForm.setPayStatusName(PayStatus.UNPAID.getDataName());
			orderForm.setInsurancePolicy(insurancePolicy);
			orderFormService.addOrderForm(orderForm);
		}
		
		return orderForm;
	}
	
	public OrderForm updateOrderOfPolicy(InsurancePolicy insurancePolicy){
		OrderForm orderForm = insurancePolicy.getOrderForm();
		if(insurancePolicy != null && orderForm != null){
			BigDecimal sumPremium = new BigDecimal("0.00").add(insurancePolicy.getGrossPremium()==null?new BigDecimal(0):insurancePolicy.getGrossPremium());
			orderForm.setOrderAmount(sumPremium);
		}
		return orderForm;
	}
	
	
	public String obtainContinueInsuranceDataEmail(){
		String result = "fail";
		try {
			String quoteNo = new String(Base64.decodeBase64(super.getRequest().getParameter("quoteNo")==null?"".getBytes():super.getRequest().getParameter("quoteNo").getBytes()));
			String productCode = new String(Base64.decodeBase64(super.getRequest().getParameter("productCode")==null?"".getBytes():super.getRequest().getParameter("productCode").getBytes()));
			String proposalSID = new String(Base64.decodeBase64(super.getRequest().getParameter("proposalSID")==null?"".getBytes():super.getRequest().getParameter("proposalSID").getBytes()));
			String userId = new String(Base64.decodeBase64(super.getRequest().getParameter("userId")==null?"".getBytes():super.getRequest().getParameter("userId").getBytes()));
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("userID", userId);
			GeUserPersonal user = geUserPersonalService.findUsers(queryRule);
			super.getRequest().setAttribute("quoteNo", quoteNo);
			super.getRequest().setAttribute("productCode", productCode);
			super.getRequest().setAttribute("proposalSID", proposalSID);
			super.getRequest().setAttribute("userId", userId);
			if(user != null){
				userService.setCurrentUser(super.getSession(), user);
				result = "succ";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * У��Ͷ��������
	 */
	public void checkAppAgeByQuote(){
		JSONObject jsonObject = new JSONObject();
		String result = "success";
		try {
			String ageInterval = super.getRequest().getParameter("ageInterval");
			String appBirthday = super.getRequest().getParameter("appBirthday");
//			System.out.println("ageInterval: "+ageInterval);
			if(StringUtils.isNotBlank(ageInterval) && StringUtils.isNotBlank(appBirthday)){
				int age = DateUtils.getAgeOfCalendar(appBirthday);
				String minAge = "0";
				String maxAge = "0";
				if(ageInterval.indexOf("-") != -1){
					minAge = ageInterval.split("-")[0];
					maxAge = ageInterval.split("-")[1];
				}
//				System.out.println("age: "+age+", minAge: "+minAge+", maxAge: "+maxAge);
				jsonObject.put("age", age);
				jsonObject.put("minAge", minAge);
				jsonObject.put("maxAge", maxAge);
				if(age < Integer.parseInt(minAge) || age > Integer.parseInt(maxAge)){
					result = "error";
				}
			}
		} catch (Exception e) {
			logger.error("У��Ͷ��������ʧ�ܣ��쳣��Ϣ��"+StringUtils.exception2String(e));
		}
		jsonObject.put("result",result);
		super.render(jsonObject.toString(), "application/json;charset=GBK");
	}
	
	/**
	 * ����Ͷ�������䡢�ֻ�����֤�Ƿ�Ϊ��ע��ͻ�
	 */
	public void checkIsCustomer(){
		JSONObject jsonObject = new JSONObject();
		try {
			int count = 0;
			GeUserPersonal geUserPersonal = (GeUserPersonal)super.getSession(false).getAttribute("geUserPersonal");
			if(geUserPersonal == null){
				String data = super.getRequest().getParameter("data");
				if(StringUtils.isNotBlank(data) && StringUtils.isNotBlank(data)){
					count = geUserPersonalService.checkIsCustomer(data);
//					System.out.println("count: "+count);
				}
			}
			jsonObject.put("count", count+"");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("У��Ͷ��������ʧ�ܣ��쳣��Ϣ��"+StringUtils.exception2String(e));
		}
		super.render(jsonObject.toString(), "application/json;charset=GBK");
	}
	
	//�Ӷ����˱�
	public void underwritingByorder(){
		JSONObject jsonObject = new JSONObject();
		JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
		jsonBinder.setDateFormat("yyyy-MM-dd");
		String result = "";
		
		String proposalSID = super.getRequest().getParameter("proposalSID");
		InsurancePolicy insurancePolicy = insurancePolicyService.findInsurancePolicyBySerialNo(proposalSID);
		
		if(insurancePolicy != null){
			InsuranceApplicant insuranceApplicant = insurancePolicy.getInsuranceApplicant();
			List<Insured> insureds = insurancePolicy.getInsureds();
			Insured insured = new Insured();
			if(insureds != null && !insureds.isEmpty()) insured = insureds.get(0);
			
			//У��Ͷ�����ֻ���
			Map<String, String> verificationMobileMap = null;
			if(geFunctionSwitchService.isSwitchOpen("verificationMobilePhoneNumber")){
				verificationMobileMap = verificationService.verificationMobilePhoneNumber(insuranceApplicant.getFullName(), insuranceApplicant.getIdType()==null?"":insuranceApplicant.getIdType().toString(), insuranceApplicant.getIdNumber(), insuranceApplicant.getMobilePhoneNumber());
			}
			if(verificationMobileMap != null){
				if(!verificationMobileMap.get("flag").equals("1")){
					result = verificationMobileMap.get("desc");
				}
			}
			
			//У�鱻���������֤
			Map<String, String> verificationIdCardMap = null;
			if(geFunctionSwitchService.isSwitchOpen("verificationIdCard") && insured != null && StringUtils.isBlank(result)){
				String fsd = insured.getIdNumber().length()<6?insured.getIdNumber():insured.getIdNumber().substring(0,6);
				verificationIdCardMap = verificationService.verificationIdCard(insured.getIdNumber(), insured.getFullName(), fsd, "WEB");
			}
			if(verificationIdCardMap != null){
				if(!verificationIdCardMap.get("flag").equals("1")){
					result = verificationIdCardMap.get("desc");
				}
			}
			
			GeUserPersonal geUserPersonal = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
			if (geUserPersonal == null) {
				result = "ϵͳ��æ�����Ժ����ԣ�";
			}
			
			if (StringUtils.isBlank(result)) {
				try {
					//�˱�
					Map<String, Object> resultMap = underwritingService.underwriting(insurancePolicy, TransType.ST000034.getCoreValue());
					
					boolean updateFlag = false;
					//���¿�
					if (resultMap != null) {
						LCCont lcCont = resultMap.get("LCCont")==null?null:(LCCont)resultMap.get("LCCont");
						updateFlag = true;
						if (resultMap.get("flag").equals("1")) {
							if(lcCont != null){
								if(saleService.saveQuote(lcCont, insureFlowDto, insurancePolicy)){
//									insurancePolicy.setApplicationNumber(lcCont.getProposalContNo());
									insurancePolicy.setPolicyStatus(PolicyStatus.PROPOSAL_SUCC.getValue());
									insurancePolicy.setPolicyStatusName(PolicyStatus.PROPOSAL_SUCC.getDataName());
									insurancePolicy.setPolicyStatusDesc(PolicyStatus.PROPOSAL_SUCC.getDataName());
									insurancePolicy.setPrecheckDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
									insurancePolicy.setReason("�˱��ɹ����˱����ڣ�" + new Date().toString());
									result = "success";
								}
							}else{
								result = "ϵͳ��æ�����Ժ����ԣ�";
							}
						} else {
//							insurancePolicy.setApplicationNumber(lcCont.getProposalContNo());
							insurancePolicy.setPolicyStatus(PolicyStatus.PROPOSAL_FAIL.getValue());
							insurancePolicy.setPolicyStatusName(PolicyStatus.PROPOSAL_FAIL.getDataName());
							insurancePolicy.setPolicyStatusDesc(PolicyStatus.PROPOSAL_FAIL.getDataName());
							insurancePolicy.setReason("�˱�ʧ�ܣ��˱����ڣ�" + new Date().toString() + "��ʧ��ԭ�򣺣�" + resultMap.get("desc") + "��");
							result = "";
							if(resultMap.get("desc") != null && !resultMap.get("desc").equals("")){
								result += "��"+resultMap.get("desc")+"��";
							}
						}
						if(result.equals("success") || updateFlag){
							insurancePolicyService.updateInsurancePolicy(insurancePolicy);
						}
					} else {
						result = "ϵͳ��æ�����Ժ����ԣ�";
					}
				} catch (SaleException e) {
					result = "ϵͳ��æ�����Ժ����ԣ���" + e.showMessage() + "��";
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("�˱������������쳣���û��ţ�" + geUserPersonal.getUserID() + "���쳣��Ϣ��" + e.getMessage());
					result = "ϵͳ��æ�����Ժ����ԣ�";
				}
			}
			
		}

		if(StringUtils.isBlank(result)){
			result = "ϵͳ��æ�����Ժ����ԣ�";
		}
		jsonObject.put("result", result);
		super.render(jsonObject.toString(), "application/json;charset=GBK");
	}
	
	/**
	 * ���������ʼ�
	 * @param flag
	 * @param order
	 * @param policy
	 */
	public void sendEmail(int flag, OrderForm order, InsurancePolicy policy){
		try{
			Map<String,String> map = new HashMap<String, String>();
			PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
			Properties properties = PropertyFileUtils.getProperties();
			if(flag == 1){
				//�������ƶ��������ʼ�
				if(policy.getInsuranceApplicant() != null && StringUtils.isNotBlank(policy.getInsuranceApplicant().getEmail())){
					int appGender = policy.getInsuranceApplicant()==null?0:policy.getInsuranceApplicant().getGender();
					map.put("email", policy.getInsuranceApplicant().getEmail());
					map.put("date", DateUtils.formatDate(policy.getUpdateTime(), DateUtils.ZHCN_DATATIMEF_STR_YYYYMMDDHHMM));
					map.put("fullName", policy.getInsuranceApplicant()==null?"":policy.getInsuranceApplicant().getFullName());
					map.put("genderTag", appGender == 0?"����":"Ůʿ");
					map.put("productName", policy.getProductName());
					map.put("continueToInsure", properties.getProperty("sinatayUrl")+"/sale/showOrderList.do?status=10&source=email&orderNo="+Base64.encodeBase64String(order.getSerialNo().getBytes())+"&proposalSID="+Base64.encodeBase64String(policy.getSerialNo().getBytes())+"&userId="+Base64.encodeBase64String(policy.getPersonalUserSerialNo().getBytes()));
					sendEmailService.sendOrderCompleteEmail(policy.getProductName(),map);
				}
			}else if(flag == 2){
				if(geFunctionSwitchService.isSwitchOpen("sendOrderPayEmail")){
					//���ʹ�֧�����������ʼ�
					if(policy != null && policy.getInsuranceApplicant() != null && StringUtils.isNotBlank(policy.getInsuranceApplicant().getEmail())){
						map.put("email", policy.getInsuranceApplicant().getEmail());
						map.put("fullName", policy.getInsuranceApplicant()==null?"":policy.getInsuranceApplicant().getFullName());
						map.put("date", DateUtils.formatDate(order.getUpdateTime(), DateUtils.ZHCN_DATATIMEF_STR_YYYYMMDDHHMM));
						String insName = policy.getInsuranceApplicant()==null?"":policy.getInsuranceApplicant().getFullName();
						if(policy.getInsureds() != null && !policy.getInsureds().isEmpty() && policy.getInsureds().get(0) != null){
							insName = policy.getInsureds().get(0).getFullName();
						}
						int appGender = policy.getInsuranceApplicant()==null?0:policy.getInsuranceApplicant().getGender();
						map.put("insFullName", insName);
						map.put("genderTag", appGender == 0?"����":"Ůʿ");
						map.put("productName", policy.getProductName());
						map.put("policyNo", policy.getApplicationNumber());
						map.put("count", policy.getUnitCount()==null?"1":policy.getUnitCount().toString());
						map.put("premium", policy.getPremium()==null?"0":policy.getPremium().toString());
						map.put("grossPremium", order.getOrderAmount()==null?"0":order.getOrderAmount().toString());
						map.put("orderPay", properties.getProperty("sinatayUrl")+"/sale/showOrderList.do?status=82&source=email&orderNo="+Base64.encodeBase64String(order.getSerialNo().getBytes())+"&proposalSID="+Base64.encodeBase64String(policy.getSerialNo().getBytes())+"&userId="+Base64.encodeBase64String(policy.getPersonalUserSerialNo().getBytes()));
						sendEmailService.sendOrderPayEmail("",map);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * �ʼ�����ת�붩���б�
	 * @return
	 */
	public String showOrderList(){
		String result = "fail";
		try {
			status = super.getRequest().getParameter("status")==null?null:Integer.parseInt(super.getRequest().getParameter("status"));
			orderSerialNo = new String(Base64.decodeBase64(super.getRequest().getParameter("orderNo")==null?"".getBytes():super.getRequest().getParameter("orderNo").getBytes()));
			String proposalSID = new String(Base64.decodeBase64(super.getRequest().getParameter("proposalSID")==null?"".getBytes():super.getRequest().getParameter("proposalSID").getBytes()));
			String userId = new String(Base64.decodeBase64(super.getRequest().getParameter("userId")==null?"".getBytes():super.getRequest().getParameter("userId").getBytes()));
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("userID", userId);
			GeUserPersonal user = geUserPersonalService.findUsers(queryRule);
			super.getRequest().setAttribute("userId", userId);
			if(StringUtils.isNotBlank(orderSerialNo)){
				OrderForm orderForm = orderFormService.getOrderFormBySerialNo(orderSerialNo);
				if(orderForm == null || (orderForm.getOrderStatus()!=null && !orderForm.getOrderStatus().equals("82"))){
					orderSerialNo = null;
				}
			}
			if(user != null){
				userService.setCurrentUser(super.getSession(), user);
				result = "succ";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getOrderSerialNo() {
		return orderSerialNo;
	}


	public void setOrderSerialNo(String orderSerialNo) {
		this.orderSerialNo = orderSerialNo;
	}
	
}
