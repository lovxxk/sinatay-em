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
	private GeCodeService geCodeService;// 数据字典service
	
	@Autowired
	private GeDirectoryService geDirectoryService;
	
	@Autowired
	private AddServiceActivityService addServiceActivityService;//打折service
	
	@Autowired
	private GeSaleProInformBookService geSaleProInformBookService;//被保险人告知服务
	
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
	
	/**功能开关*/
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
	 * 进入试算
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
//				obtainDiscountData(eid);//打折信息
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
	 * 获取试算页面信息
	 * @param geSaleProduct
	 */
	
	private void obtainQutoeData(String productCode,GeSaleProduct geSaleProduct){
		/**计算生效日期和终止日期*/
		String specifyStartDate = "";
		String specifyEndDate = "";
		String effectDateType = "";/**生效日期类型（01-默认生效，02-指定生效，03-自定义生效，04-延迟生效）*/
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
		
		/**初始化被保险人所在地省*/ 
		List<String[]> provinceList = geProductProDeptService.findProductSaleDepartmet(productCode,"3","2");
		super.getRequest().setAttribute("provinceList",provinceList);
		/**初始化保险期间*/
		List<GeSaleProAttrAllowValues> geProductAttrAllowValues=geSaleProduct.getGeSaleProAttrAllowValueses();
		// 排序
		if(geProductAttrAllowValues.size()>1)
			geProductAttrAllowValues =  sortAllowValue(geProductAttrAllowValues);
		
		super.getRequest().setAttribute("geProductAttrAllowValues",geProductAttrAllowValues);
		
		/**数据字典-保险期间单位*/
		Map periodUnitMap = geCodeService.findAllCodeAndName("PeriodType");
		super.getRequest().setAttribute("periodUnitMap",periodUnitMap);
		
		/**初始化页面保险期间值*/
		super.getRequest().setAttribute("geProductAttrAllowValue", geProductAttrAllowValues.get(0));
		
		/**初始化责任信息*/
		List<GeSaleProductDuty> geSaleProductDuties=geSaleProduct.getGeSaleProductDuties();
		super.getRequest().setAttribute("geSaleProductDuties",geSaleProductDuties);
		
		/**保额单位*/
		Map amountUnitMap  = geCodeService.findAllCodeAndName("InsuredAmountUnit");
		super.getRequest().setAttribute("amountUnitMap",amountUnitMap);
		
		/**获取被保人配置信息*/
		List<GeSaleProInsuredConfig> geProductInsuredConfigs = geSaleProduct.getGeSaleProInsuredConfigs();
		
		if(geProductInsuredConfigs.size() > 0) {
			
			/**获取被保人年龄限制*/
			if("0".equals(geProductInsuredConfigs.get(0).getInAgeFlag())){
				int insuredAgeStart = 0;
				int insuredAgeEnd = 0;
				String insuredAgeDesc = "被保险人年龄必须年满";
				String insuredStartAgeString = geProductInsuredConfigs.get(0).getInAgeStart();
				String insuredStartUnit = geProductInsuredConfigs.get(0).getInAgeStartAttr();
				String insuredEndAgeString = geProductInsuredConfigs.get(0).getInAgeEnd();
				String insuredEndUnit = geProductInsuredConfigs.get(0).getInAgeEndAttr();
				if(StringUtils.isNotBlank(insuredStartAgeString)){
					insuredAgeStart = - (Integer.parseInt(insuredStartAgeString)-1);
					if(! StringUtils.isBlank(insuredStartUnit)){
						String unit =  "Y".equals(insuredStartUnit)?"周岁":("M".equals(insuredStartUnit)?"个月":"天");
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
		/**打折*/
		String existDiscountFlag = "false";/**标志是否打折*/
		List<GeActivitiesRule> geActivitiesRuleList  = new ArrayList<GeActivitiesRule>();
		GeActivitiesRule geActivitiesRule = new GeActivitiesRule();
		geActivitiesRule.setDeptID("2");//寿险总公司ID
		geActivitiesRule.setEid(eid);//eid
		geActivitiesRuleList.add(geActivitiesRule);

		GeAddserviceConditionDto geAddserviceConditionDto = new GeAddserviceConditionDto();
		geAddserviceConditionDto.setSystemFlowId("02");
		geAddserviceConditionDto.setPictureFlag(true);//是否要广告图片
		
		List<String> wantedActivityPatterns = new ArrayList<String>();
		wantedActivityPatterns.add("4");//打折方式
		geAddserviceConditionDto.setWantedActivityPatterns(wantedActivityPatterns);
		try {
			Map map = addServiceActivityService.findAddGeAddServiceActivityCheckRule(geActivitiesRuleList, geAddserviceConditionDto);
			existDiscountFlag = (String)map.get("isDiscountFlag");
			if("true".equals(existDiscountFlag)){
				Map discountAndJumpUrl=(Map)map.get("discountAndJumpUrl");
				String discountID=(String)discountAndJumpUrl.get("newDiscountID");
				super.getRequest().setAttribute("discountID",discountID);//折扣ID
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
		
		/**是否存在抽奖*/
		super.getRequest().setAttribute("existLuckDrawFlag", (Boolean)addServiceActivityService.activityBoola("2",eid, new String[]{"1"}).get("falg"));
	}
	
	
	/**
	 * 获取保单页面配置信息
	 * @param productCode
	 * @param GeSaleProduct
	 * @param pageCode
	 */
	
	private void obtainInsuranceData(String productCode,GeSaleProduct geSaleProduct,String pageCode){
		HttpServletRequest request = super.getRequest();
		
		JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
		jsonBinder.setDateFormat("yyyy-MM-dd");
		
		/**获取被保人配置信息*/
		List<GeSaleProInsuredConfig> geProductInsuredConfigs = geSaleProduct.getGeSaleProInsuredConfigs();
		List<GeSaleProPolicyDisplayConfig> geSaleProPolicyDisConfigs = geSaleProduct.getGeSaleProPolicyDisConfigs();
		
		/**获取页面流程配置信息*/
		List<String> elementCodes = geSaleProWebFlowPageElementService.getElementCodesByOrder(productCode, "noCarNetSaleFlow", pageCode);
		List informList = geSaleProInformBookService.findInformBooks(productCode);//列表中每个元素为依次存放告知顺序、告知内容、告知选项的数组
		request.setAttribute("elementCodes", elementCodes);
		request.setAttribute("geInformBooks", informList);
		request.setAttribute("geInformBookNum", informList.size());
		
		/**获取证件类型描述信息*/
		List<GeCode> identifyTypeList = geCodeService.findAllByGeCodeType("IdentifyType");//证件类型
		request.setAttribute("identifyTypeList",identifyTypeList);
		
		/**获取职业配置*/
		List<GeOccupation> geOccupations = new ArrayList<GeOccupation>();
		List<GeSaleProInsuredOccupation> list = new ArrayList<GeSaleProInsuredOccupation>();
		list = geSaleProInsuredOccupationService.findGeOccupations(geProductInsuredConfigs.get(0).getSerialNo());
		request.setAttribute("geOccupations",list);
		
		/**获取关系描述*/
		List<GeCode> insRelaToAppList = geCodeService.findAllByGeCodeType("applicantAndInsured");
		request.setAttribute("insRelaToAppList", insRelaToAppList);
		
		/**获取性别描述*/
		List<GeCode> insSexList = geCodeService.findAllByGeCodeType("Sex");//被保人/受益人性别
		request.setAttribute("insSexList",insSexList);
		
		String insOccupTypes = null;		// 职业类别配置
		String insRelaToApps = null;		// 被保人与投保人关系配置项
		String insType=null;				// 被保人证件类型配置
		String rInsOccupTypes = null;		// 连带被保人职业类别配置
		String rInsRelaToApps = null;		// 连带被保人与投保人关系配置项
		String rInsType=null;				// 连带被保人证件类型配置
		String finsRelaToApps = null;		// 连带被保人与主被保人关系配置型
		
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
			insRelationToApp_opts.append("{name:'请选择',value:'-1'}");
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
			insIdType_opts.append("{name:'请选择',value:'-1'}");
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
			
			
			/**获取被保人年龄限制*/
			if("0".equals(geProductInsuredConfigs.get(0).getInAgeFlag())){
				int insuredAgeStart = 0;
				int insuredAgeEnd = 0;
				String insuredAgeDesc = "被保险人年龄必须年满";
				String insuredStartAgeString = geProductInsuredConfigs.get(0).getInAgeStart();
				String insuredStartUnit = geProductInsuredConfigs.get(0).getInAgeStartAttr();
				String insuredEndAgeString = geProductInsuredConfigs.get(0).getInAgeEnd();
				String insuredEndUnit = geProductInsuredConfigs.get(0).getInAgeEndAttr();
				if(StringUtils.isNotBlank(insuredStartAgeString)){
					insuredAgeStart = - (Integer.parseInt(insuredStartAgeString)-1);
					if(StringUtils.isNotBlank(insuredStartUnit)){
						String unit =  "Y".equals(insuredStartUnit)?"周岁":("M".equals(insuredStartUnit)?"个月":"天");
						insuredAgeDesc += insuredStartAgeString+unit;
					}
				}
				if(StringUtils.isNotBlank(insuredEndAgeString)){
					insuredAgeEnd = - (Integer.parseInt(insuredEndAgeString)+1);
					if(StringUtils.isNotBlank(insuredEndUnit)){
						String unit =  "Y".equals(insuredEndUnit)?"周岁":("M".equals(insuredEndUnit)?"个月":"天");
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
		/**获取受益人配置信息*/
		List<GeSaleProBeneficiaryConfig> geProductBeneficiaryConfigs = geSaleProduct.getGeSaleProBeneficiaryConfigs();	//受益人
		String benRelaToInss = null;/**受益人与主被保人关系*/
		String benIdType = null;/**受益人证件类型*/
		if (geProductBeneficiaryConfigs.size() > 0) {
			benRelaToInss = geProductBeneficiaryConfigs.get(0).getBenRelationToPInsConfig();
			benIdType = geProductBeneficiaryConfigs.get(0).getBendTypeConfig();
			
			
			benIdType_opts.append("[");
			benIdType_opts.append("{name:'请选择',value:'-1'}");
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
			benRelaToInss_opts.append("{name:'请选择',value:'-1'}");
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

		/**获取投保人年龄限制*/
		GeSaleProApplicantConfig geSaleProApplicantConfig = geSaleProduct.getGeSaleProApplicantConfigs().get(0);
		if("0".equals(geSaleProApplicantConfig.getAppAgeFlag())){
			int appAgeStart = 0;
			int appAgeEnd = 0;
			String appStartAgeString = geSaleProApplicantConfig.getAppAgeStart();
			String appStartAgeUnit = geSaleProApplicantConfig.getAppAgeStartAttr();
			String appEndAgeString = geSaleProApplicantConfig.getAppAgeEnd();
			String appEndAgeUnit = geSaleProApplicantConfig.getAppAgeEndAttr();
			String appAgeDesc = "投保人年龄必须年满";
			if(StringUtils.isNotBlank(appStartAgeString)){
				appAgeStart = - (Integer.parseInt(appStartAgeString)-1);
				if(StringUtils.isNotBlank(appStartAgeUnit)){
					String unit =  "Y".equals(appStartAgeUnit)?"周岁":("M".equals(appStartAgeUnit)?"个月":"天");
					appAgeDesc += appStartAgeString+unit;
				}
			}
			if(StringUtils.isNotBlank(appEndAgeString)){
				appAgeEnd = - (Integer.parseInt(appEndAgeString)+1);
				if(StringUtils.isNotBlank(appEndAgeUnit)){
					String unit =  "Y".equals(appEndAgeUnit)?"周岁":("M".equals(appEndAgeUnit)?"个月":"天");
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
		appIdType_opts.append("{name:'请选择',value:'-1'}");
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
		
		//处理当前产品生效日期
		String inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), 1),"yyyy-MM-dd");
		String effectDateType = geSaleProduct.getEffectDateType()==null?"01":geSaleProduct.getEffectDateType();
		String delayDays = geSaleProduct.getDelayDays()==null?"1":geSaleProduct.getDelayDays();
		String minEffectDateLimited = geSaleProduct.getMinEffectDateLimited()==null?"1":geSaleProduct.getMinEffectDateLimited();
		String maxEffectDateLimited = geSaleProduct.getMaxEffectDateLimited()==null?"1":geSaleProduct.getMaxEffectDateLimited();
		
		if(effectDateType.equals("01")){ //次日凌晨生效
			inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), 1),"yyyy-MM-dd");
		}else if(effectDateType.equals("02")){ //指定生效起始日期
			inceptionDate = DateUtils.formatDate(geSaleProduct.getSpecifyStartDate(),"yyyy-MM-dd");
		}else if(effectDateType.equals("03")){ //最小限制天数 -- 最大限制天数
			inceptionDate = DateUtils.formatDate(DateUtils.addDays(new Date(), Integer.parseInt(minEffectDateLimited)),"yyyy-MM-dd")+"|"+DateUtils.formatDate(DateUtils.addDays(new Date(), Integer.parseInt(maxEffectDateLimited)),"yyyy-MM-dd");;
		}else if(effectDateType.equals("04")){ //延迟生效天数
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
	 * 保费试算
	 * @return
	 */
	
	public String obtainQuote(){
		JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
		try {
			String existDiscountFlag = insureFlowDto.getExistDiscountFlag();/**标志是否打折*/
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
	 * 计算日期（包含临界值）
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
	 * 计算日期（不包含临界值）
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
	 * 进入投保填写界面
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
	 * 获取投保填写界面所需参数
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
	
	//查询当前用户下关联的被保险人信息
	
	public void obtainTopInsuredData(GeSaleProduct geSaleProduct, GeUserPersonal geUserPersonal){
		System.out.println(this.getClass().getName()+" obtainTopInsuredData()...");
		QueryRule queryRule = QueryRule.getInstance();
		
		List<GeSaleProInsuredConfig> geProductInsuredConfigs = geSaleProduct.getGeSaleProInsuredConfigs();
		String insRelaToApps = "";		// 被保人与投保人关系配置项
		String insType = "";				// 被保人证件类型配置
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
	 * 获取投保确认界面所需参数
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
	 * 进入到确认页面
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
	 * 返回修改
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
	
	// 冒泡排序AllowValue
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
	 * 继续投保
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
		InsureFlowDto temporary = saleService.getInsureFlowDtoForConInsu(quoteNo);  //数据库
		
		/**试算单主表*/
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
	 * 查询职业
	 * @return
	 */
	
	public String obtainOccupation(){
		String keyword = super.getRequest().getParameter("keyword");
		List occupations = occupationService.findOccupationInfoByLikeName(keyword);
		super.render(JsonBinder.buildNonNullBinder().toJson(occupations), "application/json;charset=GBK");
		return NONE;
	}
	
	/**
	 * 保存离开
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
				result = "系统繁忙，请稍后再试！";
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
					//集团业务来源（WEB）
					insureFlowDto.getQuoteMain().setBusinessSource("WEB_PERSON");
					//集团渠道代码（W——网销）
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
					result = "很抱歉，离开保存失败！";
				}
			}
		} catch (Exception e) {
			log.error(StringUtils.exception2String(e));
		}
		super.render(JsonBinder.buildNonNullBinder().toJson(result), "application/json;charset=GBK");
		return NONE;
	}
	
	/**
	 * 更新试算单步骤
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
					//核保_存数
					insurancePolicy.setTransIdentify(TransType.ST000034.getCoreValue());
					Map<String, Object> resultMap = underwritingService.underwriting(insurancePolicy, TransType.ST000022.getCoreValue());
//					if (resultMap != null) {
//						if (resultMap.get("flag").equals("1")) {
//							result = "success";
//						} else {
//							result = "核心存数失败，请联系管理员";
//						}
//					} else {
//						result = "系统繁忙，请稍后再试！";
//					}

					boolean updateFlag = false;
					//更新库
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
//									insurancePolicy.setReason("核保成功，核保日期：" + new Date().toString());
									result = "success";
								}
							}else{
								result = "系统繁忙，请稍后再试！";
							}
						} else {
							jsonObject.put("proposalContNo", lcCont.getProposalContNo());
							insurancePolicy.setApplicationNumber(lcCont.getProposalContNo());
//							insurancePolicy.setPolicyStatus(PolicyStatus.PROPOSAL_FAIL.getValue());
//							insurancePolicy.setPolicyStatusName(PolicyStatus.PROPOSAL_FAIL.getDataName());
//							insurancePolicy.setPolicyStatusDesc(PolicyStatus.PROPOSAL_FAIL.getDataName());
//							insurancePolicy.setReason("核保失败，核保日期：" + new Date().toString() + "，失败原因：（" + resultMap.get("desc") + "）");
							result = "";
							if(resultMap.get("desc") != null && !resultMap.get("desc").equals("")){
								result += "（"+resultMap.get("desc")+"）";
							}
						}
						if(result.equals("success") || updateFlag){
							updateOrderOfPolicy(insurancePolicy);
							insurancePolicyService.updateInsurancePolicy(insurancePolicy);
						}
					} else {
						result = "系统繁忙，请稍后再试！";
					}
				
				}
			}else{
				result = "很抱歉，离开保存失败！";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(StringUtils.isBlank(result)){
			result = "系统繁忙，请稍后再试！";
		}
		jsonObject.put("result", result);
		super.render(jsonObject.toString(), "application/json;charset=GBK");
		return NONE;
	}
	
	/**
	 * 保单核保
	 * @return
	 */
	public String underwriting(){
		String result = "";
		boolean isCreate = true;
		QuoteApplicant qa = null;
		if(insureFlowDto.getQuoteMain() != null && insureFlowDto.getQuoteMain().getQuoteApplicant() !=null)
			qa = insureFlowDto.getQuoteMain().getQuoteApplicant();
		//自动注册登录
		//是否需要自动注册标志，1表示需要自动注册
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
					//短信发送人
					String sender = "9005";
					//短信内容
					String msgComment = "系统已为您自动注册，密码为" + au.getPlaintextPassword() + "。";
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
				result = "系统繁忙，请稍后再试！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "系统繁忙，请稍后再试！";
		}
		
		//校验投保人手机号
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
		//校验被保险人身份证
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
		//把暂存对象里的值进行一些处理
		GeUserPersonal geUserPersonal = (GeUserPersonal)super.getSession().getAttribute("geUserPersonal");
		String userID = insureFlowDto.getQuoteMain().getUserId();
		if (geUserPersonal == null) {
			result = "系统繁忙，请稍后再试！";
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
		//试算单入库
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
				logger.error("试算单入库出现异常，用户号：" + geUserPersonal.getUserID() + "，异常堆栈信息：" + e.getMessage());
				result = "系统繁忙，请稍后再试！";
			}
			if(!bl){
				result = "系统繁忙，请稍后再试！";
			}
		}
		
		InsurancePolicy insurancePolicy = null;
		//入库，先查询，如果没有则直接入库，有则删除后再重新入库
		if (StringUtils.isBlank(result)) {
			try {
				if(StringUtils.isBlank(proposalSID)){
					insureFlowDto.getQuoteMain().getProposalSID();//投保单序号
				}
				if (StringUtils.isNotBlank(proposalSID)) {
					insurancePolicy = insurancePolicyService.findInsurancePolicyBySerialNo(proposalSID);
				}
				if(insurancePolicy != null){
					insurancePolicyService.deleteInsurancePolicy(insurancePolicy);
				}
				//集团业务来源（WEB）
				insureFlowDto.getQuoteMain().setBusinessSource("WEB_PERSON");
				//集团渠道代码（W——网销）
				insureFlowDto.getQuoteMain().setGroupChannel("W");
				insureFlowDto.getQuoteMain().setSellType("20");
				insurancePolicy = saleService.createInsurancePolicy(insureFlowDto);
				insurancePolicy.setQuoteNo(quoteNo);
				insureFlowDto.getQuoteMain().setProposalSID(insurancePolicy.getSerialNo());
				insureFlowDto.setProposalSID(insurancePolicy.getSerialNo());
				//加购
				if ("1".equals(insureFlowDto.getMoreBuyFlag())) {
					//添加加购关联标记
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
				result = "系统繁忙，请稍后再试！（" + e.showMessage() + "）";
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("投保单入库出现异常，用户号：" + geUserPersonal.getUserID() + "，异常信息：" + e.getMessage());
				result = "系统繁忙，请稍后再试！";
			}
		}
		if (StringUtils.isBlank(result)) {
			try {
				//核保
				insurancePolicy.setTransIdentify(TransType.ST000034.getCoreValue());
				Map<String, Object> resultMap = underwritingService.underwriting(insureFlowDto, insurancePolicy, geUserPersonal);
				
				//更新库
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
								insurancePolicy.setReason("核保成功，核保日期：" + new Date().toString());
								result = "success";
							}
						}else{
							result = "系统繁忙，请稍后再试！";
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
						insurancePolicy.setReason("核保失败，核保日期：" + new Date().toString() + "，失败原因：（" + resultMap.get("desc") + "）");
						result = "您填写的信息不符合该产品投保条件，请进行修改";
						if(resultMap.get("desc") != null && !resultMap.get("desc").equals("")){
							result += "（"+resultMap.get("desc")+"）";
						}
					}
					//核保成功，发送邮件提醒用户去支付
					if(result.equals("success")){
						OrderForm order = createOrderOfPolicy(insurancePolicy,"1");
						insurancePolicyService.updateInsurancePolicy(insurancePolicy);
						sendEmail(2,order,insurancePolicy);
					}
				} else {
					createOrderOfPolicy(insurancePolicy,"0");
					result = "系统繁忙，请稍后再试！";
				}
			} catch (SaleException e) {
				result = "系统繁忙，请稍后再试！（" + e.showMessage() + "）";
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("核保结果处理出现异常，用户号：" + geUserPersonal.getUserID() + "，异常信息：" + e.getMessage());
				result = "系统繁忙，请稍后再试！";
			}
		}

		if(StringUtils.isBlank(result)){
			result = "系统繁忙，请稍后再试！";
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
	 * 身份证校验
	 * @return
	 */
	public String verificationIdCard(){
		String result = "系统繁忙，请稍后再试！";
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
			logger.info("身份证校验失败，异常信息："+StringUtils.exception2String(e));
		}
		jsonObject.put("result", result);
		super.render(jsonObject.toString(), "application/json;charset=GBK");
		return NONE;
	}
	
	/**
	 * 推荐人手机号验证
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
			logger.info("代理人手机号验证失败，异常信息："+StringUtils.exception2String(e));
		}
		super.render(jsonObject.toString(), "application/json;charset=GBK");
		return NONE;
	}
	
	
	/**
	 * 手机号校验
	 * @return
	 */
	public String verificationMobilePhoneNumber(){
		String result = "系统繁忙，请稍后再试！";
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
			logger.info("手机号校验失败，异常信息："+StringUtils.exception2String(e));
		}
		jsonObject.put("result", result);
		super.render(jsonObject.toString(), "application/json;charset=GBK");
		return NONE;
	}
	
	/**
	 * 进入到选择支付页面
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

			/**获取证件类型描述信息*/
			List<GeCode> identifyTypeList = geCodeService.findAllByGeCodeType("IdentifyType");//证件类型
			
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
			//是否为理财险产品
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
	 * ajax查询常用被保人信息
	 */
	public void obtainTopInsured(){
		JSONObject jsonObject = new JSONObject();
		String result = "系统繁忙，请稍后再试！";
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
			logger.info("查询常用被保人信息失败，异常信息："+StringUtils.exception2String(e));
		}
		jsonObject.put("result",result);
		super.render(jsonObject.toString(), "application/json;charset=GBK");
	}
	
	/**根据投保单创建订单*/
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
	 * 校验投保人年龄
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
			logger.error("校验投保人年龄失败，异常信息："+StringUtils.exception2String(e));
		}
		jsonObject.put("result",result);
		super.render(jsonObject.toString(), "application/json;charset=GBK");
	}
	
	/**
	 * 根据投保人邮箱、手机号验证是否为已注册客户
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
			logger.info("校验投保人年龄失败，异常信息："+StringUtils.exception2String(e));
		}
		super.render(jsonObject.toString(), "application/json;charset=GBK");
	}
	
	//从订单核保
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
			
			//校验投保人手机号
			Map<String, String> verificationMobileMap = null;
			if(geFunctionSwitchService.isSwitchOpen("verificationMobilePhoneNumber")){
				verificationMobileMap = verificationService.verificationMobilePhoneNumber(insuranceApplicant.getFullName(), insuranceApplicant.getIdType()==null?"":insuranceApplicant.getIdType().toString(), insuranceApplicant.getIdNumber(), insuranceApplicant.getMobilePhoneNumber());
			}
			if(verificationMobileMap != null){
				if(!verificationMobileMap.get("flag").equals("1")){
					result = verificationMobileMap.get("desc");
				}
			}
			
			//校验被保险人身份证
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
				result = "系统繁忙，请稍后再试！";
			}
			
			if (StringUtils.isBlank(result)) {
				try {
					//核保
					Map<String, Object> resultMap = underwritingService.underwriting(insurancePolicy, TransType.ST000034.getCoreValue());
					
					boolean updateFlag = false;
					//更新库
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
									insurancePolicy.setReason("核保成功，核保日期：" + new Date().toString());
									result = "success";
								}
							}else{
								result = "系统繁忙，请稍后再试！";
							}
						} else {
//							insurancePolicy.setApplicationNumber(lcCont.getProposalContNo());
							insurancePolicy.setPolicyStatus(PolicyStatus.PROPOSAL_FAIL.getValue());
							insurancePolicy.setPolicyStatusName(PolicyStatus.PROPOSAL_FAIL.getDataName());
							insurancePolicy.setPolicyStatusDesc(PolicyStatus.PROPOSAL_FAIL.getDataName());
							insurancePolicy.setReason("核保失败，核保日期：" + new Date().toString() + "，失败原因：（" + resultMap.get("desc") + "）");
							result = "";
							if(resultMap.get("desc") != null && !resultMap.get("desc").equals("")){
								result += "（"+resultMap.get("desc")+"）";
							}
						}
						if(result.equals("success") || updateFlag){
							insurancePolicyService.updateInsurancePolicy(insurancePolicy);
						}
					} else {
						result = "系统繁忙，请稍后再试！";
					}
				} catch (SaleException e) {
					result = "系统繁忙，请稍后再试！（" + e.showMessage() + "）";
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("核保结果处理出现异常，用户号：" + geUserPersonal.getUserID() + "，异常信息：" + e.getMessage());
					result = "系统繁忙，请稍后再试！";
				}
			}
			
		}

		if(StringUtils.isBlank(result)){
			result = "系统繁忙，请稍后再试！";
		}
		jsonObject.put("result", result);
		super.render(jsonObject.toString(), "application/json;charset=GBK");
	}
	
	/**
	 * 发送提醒邮件
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
				//发送完善订单提醒邮件
				if(policy.getInsuranceApplicant() != null && StringUtils.isNotBlank(policy.getInsuranceApplicant().getEmail())){
					int appGender = policy.getInsuranceApplicant()==null?0:policy.getInsuranceApplicant().getGender();
					map.put("email", policy.getInsuranceApplicant().getEmail());
					map.put("date", DateUtils.formatDate(policy.getUpdateTime(), DateUtils.ZHCN_DATATIMEF_STR_YYYYMMDDHHMM));
					map.put("fullName", policy.getInsuranceApplicant()==null?"":policy.getInsuranceApplicant().getFullName());
					map.put("genderTag", appGender == 0?"先生":"女士");
					map.put("productName", policy.getProductName());
					map.put("continueToInsure", properties.getProperty("sinatayUrl")+"/sale/showOrderList.do?status=10&source=email&orderNo="+Base64.encodeBase64String(order.getSerialNo().getBytes())+"&proposalSID="+Base64.encodeBase64String(policy.getSerialNo().getBytes())+"&userId="+Base64.encodeBase64String(policy.getPersonalUserSerialNo().getBytes()));
					sendEmailService.sendOrderCompleteEmail(policy.getProductName(),map);
				}
			}else if(flag == 2){
				if(geFunctionSwitchService.isSwitchOpen("sendOrderPayEmail")){
					//发送待支付订单提醒邮件
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
						map.put("genderTag", appGender == 0?"先生":"女士");
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
	 * 邮件链接转入订单列表
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
