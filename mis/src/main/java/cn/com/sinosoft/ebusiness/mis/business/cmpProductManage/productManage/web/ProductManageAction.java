package cn.com.sinosoft.ebusiness.mis.business.cmpProductManage.productManage.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.FileUtils;
import ins.framework.web.Struts2Action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.activiti.engine.ActivitiException;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCodeId;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeOccupation;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeOccupationService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.mis.business.cmpProductManage.productDirectory.web.ProductDirectoryAction;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectory;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeAddresseeConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeDutyConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeFlowConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeFlowConfigRelate;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePageConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePageConfigRelate;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePageElementConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePayment;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePaymentCity;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePaymentCityId;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProDutyAttrAllowVal;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProInsuredOccupation;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductApplicantConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductAttrAllowValues;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductBeneficiaryConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductDuty;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductEmergencyConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductExtraInfo;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductInformBook;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductInsuredConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductMain;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductMainProcess;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductPolicyDisplayConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductProDept;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductRisk;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductSaleDepPayment;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeRiskConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeWebFlow;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeWebFlowPage;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeWebFlowPageElement;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeDutyAttrAllowValueService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeDutyConfigService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeFlowConfigService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GePageConfigRelateService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GePaymentService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeProInsuredOccupationService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeProductAttrAllowValuesService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeProductDutyService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeProductProDeptService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeProductRiskService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeRiskConfigService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeWebFlowPageElementService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeWebFlowPageService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.ProductManageService;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProAddresseeConfig;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProApplicantConfig;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProAttrAllowValues;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProBeneficiaryConfig;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProDutyAttrAllowVal;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProEmergencyConfig;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProExtraInfo;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProInformbook;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProInsuredConfig;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProInsuredOccupation;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProPolicyDisplayConfig;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProProDept;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProSaleDepPayment;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProWebFlow;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProWebFlowPage;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProWebFlowPageElement;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProduct;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProductDuty;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProductRisk;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade.GeSaleProductService;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BizConfigCommonException;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;
import cn.com.sinosoft.util.encode.JsonBinder;
import cn.com.sinosoft.util.io.FilePathUtil;
import cn.com.sinosoft.util.reflect.ConvertUtils;

public class ProductManageAction extends Struts2Action {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6960198105602348836L;
//	@Autowired
//	private GeWorkFlowServiceSpringImpl geWorkFlowService;
	@Autowired
	private GeDirectoryService geDirectoryService;
	@Autowired
	private GeSaleProductService geSaleProductService;
	@Autowired
	private GeDutyAttrAllowValueService geDutyAttrAllowValueService;
	@Autowired
	private GeProductDutyService geProductDutyService;
	@Autowired
	private ProductManageService productManageService;
	@Autowired
	private GeOccupationService geOccupationService; // 职业类别
	@Autowired
	private GeFlowConfigService geFlowConfigService;
	@Autowired
	private GeProductProDeptService geProductProDeptService;
	@Autowired
	private GeWebFlowPageService geWebFlowPageService;
	@Autowired
	private GePageConfigRelateService gePageConfigRelateService;
	@Autowired
	private GeWebFlowPageElementService geWebFlowPageElementService;
//	@Autowired
//	private WorkFlowNewService<EntityWorkFlow> workFlowServiceImpl;
	@Autowired
	private GePaymentService gePaymentService;
	@Autowired
	private GeDutyConfigService geDutyConfigService;
	@Autowired
	private GeProductRiskService geProductRiskService;
	@Autowired
	private GeRiskConfigService geRiskConfigService;
	@Autowired
	private GeProductAttrAllowValuesService geProductAttrAllowValuesService;
	@Autowired
	private GeProInsuredOccupationService geProInsuredOccupationService;
	
	private String[] insOccupType; // 被保人职业类别配置项
	private String[] insRelaToApp; // 被保人与投保人关系配置项
	private String[] finsRelaToApp; // 连带被保人与主被保人关系配置型
	private String[] insSexConfig;// 被保人性别配置项
	private String[] appSexConfig;// 投保人性别配置项
	private String[] benGenderConfig;// 受益人性别配置项
	private String[] insIdTypeConfig;// 被保人身份证配置项
	private String[] appIdTypeConfig;// 投保人身份证配置项
	private String[] bendTypeConfig;// 受益人身份证配置项
	
	private String taskID;
	private String workFlowID;
	private String allRisk;
	private String allDuty;
	private String attributes;
	private GeProductDuty geProductDuty;
	private GeProductRisk geProductRisk;
	private GePayment gePayment;
	private List<GeOccupation> geOccupations; // 职业类型信息
	private GeProductMain geProductMain;
	private Page page;
	private String[] flow;
	private String fileName;
	private File doc;
	private String legalNotice;
	private String proposalNotice;
	private List<GeCode> businessAreaList;
	List<GeProductAttrAllowValues> productAttrAllowValueList;
	private GeRiskConfig geRiskConfig;
	private String suggestion;// 处理意见
	private GeProductInformBook informBook;// 投保告知
	List<GeProductInformBook> informBookList;// 投保告知list
	private List<GeCode> AttributeTypeValueCode;  //数据字典保险期间属性值编码
	private List<GeCode> IdentifyTypeCode;  //数据字典证件类型编码
	private List<GeCode> SexCode;  //数据字典性别编码
	private String[] benRelaToIns; // 受益人与主被保人关系配置项
	private GeProductEmergencyConfig geProductEmergencyConfig; // 紧急联系人配置项
	private GeCodeService geCodeService; //
	private List<GeCode> code; // 数据字典
	private String deptidSave;
	private File logoImg;
	private String logoImgFileName;
	private File productUploadFile;	
	private String productUploadFileFileName;
	private static final String imagePath = "upload/images/imageL/";
	private static final String pageElementFileUpdatePath = FilePathUtil.getProjectLocalPath(ProductDirectoryAction.class, "/mis.war", "/src/main/webapp") + "//upload/product";
	private static final String imageAbsolutePath = FilePathUtil.getProjectLocalPath(ProductDirectoryAction.class, "/mis.war", "/src/main/webapp") + "/upload/images/imageL/";
	
	
	// 推送目录
	private static final String publishedProductPath = FilePathUtil.getProjectLocalPath(ProductDirectoryAction.class, "/online.war", "/src/main/webapp") + "/upload/publishedProduct";
	
	private GeProductApplicantConfig geProductApplicantConfig;
	private GeProductInsuredConfig geProductInsuredConfig;
	private List<GeProductInsuredConfig> geProductInsuredConfigList;
	private GeProductBeneficiaryConfig geProductBeneficiaryConfig;
	private GeAddresseeConfig geAddresseeConfig;
	private GeProductAttrAllowValues geProductAttrAllowValues;
	private GeProductPolicyDisplayConfig geProductPolicyDisplayConfig;
	private String message = "";
	private int flag;
	private String checkFlows;// 页面选中的流程
	
	private String addResult;
	
	
	
	/**
	 * 获取项目物理路径
	 * 
	 * @return 项目路径
	 * @throws Exception
	 *             未找到路径
	 */
	public static String getProjectLocalPath(final String dir) {
		try {
			String path = ProductDirectoryAction.class.getResource("").getFile();
			path = URLDecoder.decode(path, "UTF-8");
			if (path.lastIndexOf("/WEB-INF") > -1) {
				path = path.substring(0, path.lastIndexOf("/WEB-INF"));
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				String temp = "";
				if (StringUtils.isNotBlank(path) && path.length() >= 5)
					temp = path.substring(0, 5);
				if ("file:".equalsIgnoreCase(temp)) {
					if (path.lastIndexOf("/") > -1) {
						path = path.substring(5);
					}
				}
			}
			
			Properties props=System.getProperties(); //获得系统属性集  
			String osName = props.getProperty("os.name"); //操作系统名称  
			if (osName.startsWith("Windows")) {
				path = props.getProperty("user.dir").replace("\\", "/") + "/src/main/webapp";
			} else {
				path += dir;
			}
			return path;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String toCreateProduct() {
		/**查询数据字典的业务领域*/
		try {
			businessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");
			Map map = (Map) super.getSession().getAttribute("permission");
			String authorityids = (String) map.get("ROLE_B_CRPR_I");//拿到该功能的所有机构权限
			
			for (int i = businessAreaList.size() -1; i >= 0; i--) {
				String baStr = businessAreaList.get(i).getId().getCodeCode();
				if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
					continue;
				}else {
					businessAreaList.remove(i);
				}
			}
		} catch (BizConfigCommonException e) {
			e.printStackTrace();
			System.out.println("查询数据字典出现异常，异常信息："+e.getMsg());
		} catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 创建产品基本信息
	 * @return
	 */
	public String createProduct() {
		try {
			GeFlowConfig flowConfig = geFlowConfigService.findGeFlowConfigByFlowCode("noCarNetSaleFlow");
			List<GeWebFlow> webFlowList = new ArrayList<GeWebFlow>();
			GeWebFlow webflow = new GeWebFlow();
			webflow.setCreateTime(new Date());
			webflow.setGeFlowConfig(flowConfig);
			List<GeWebFlowPage> webFlowPageList = new ArrayList<GeWebFlowPage>();
			if (flowConfig != null) {
				for (GeFlowConfigRelate flowConfigRelate:flowConfig.getGeFlowConfigRelates()) {
					GeWebFlowPage webFlowPage = new GeWebFlowPage();
					webFlowPage.setGePageConfig(flowConfigRelate.getGePageConfig());
					webFlowPage.setGeWebFlow(webflow);
					webFlowPage.setCreateTime(new Date());
					webFlowPage.setSeqIndex(flowConfigRelate.getDefaultSeqIndex());
					webFlowPage.setGeProductMain(geProductMain);
					webFlowPage.setIsShow(flowConfigRelate.getIsShow());
					if ("0".equals(flowConfigRelate.getIsShow())) {
						List<GeWebFlowPageElement>  geWebFlowPageElementList = new ArrayList<GeWebFlowPageElement>();
						GePageConfig gePageConfig = flowConfigRelate.getGePageConfig();
						for (GePageConfigRelate gePageConfigRelate:gePageConfig.getGePageConfigRelates()) {
							GeWebFlowPageElement geWebFlowPageElement = new GeWebFlowPageElement();
							geWebFlowPageElement.setGeProductMain(geProductMain);
							geWebFlowPageElement.setSeqIndex(gePageConfigRelate.getDefaultSeqIndex());
							geWebFlowPageElement.setGePageElementConfig(gePageConfigRelate.getGePageElementConfig());
							geWebFlowPageElement.setGeWebFlowPage(webFlowPage);
							geWebFlowPageElement.setStatus("0");
							geWebFlowPageElementList.add(geWebFlowPageElement);
						}
						webFlowPage.setGeWebFlowPageElements(geWebFlowPageElementList);
					}
					webFlowPageList.add(webFlowPage);
				}
			}
			webflow.setGeWebFlowPages(webFlowPageList);
			webFlowList.add(webflow);
			geProductMain.setGeWebFlows(webFlowList);
			webflow.setGeProductMain(geProductMain);
			productManageService.createProduct(geProductMain);
			
			// 保存轨迹
			saveProcess(geProductMain);
			super.getRequest().setAttribute("coreProductCode", geProductMain.getCoreProductCode());
			super.getRequest().setAttribute("addResult", "success");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("addResult", "failure");
		}
		return SUCCESS;
	}
	
	public void isHaveGeProduct(){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String coreProductCode = super.getRequest().getParameter("coreProductCode");
			GeProductMain geProduct = productManageService.searchProductByProductCode(coreProductCode);
			if (geProduct == null) {
				resultMap.put("resultFlag", "error");
			} else {
				resultMap.put("resultFlag", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
	}
	
	/**
	 * 判断险种代码是否已经存在
	 * @throws Exception 
	 */
	public void isExistRiskCode() throws Exception{
		
		List<GeDirectory> list2 =	productManageService.findEntitys(GeDirectory.class, QueryRule.getInstance());
		for (GeDirectory geDirectory : list2) {
			this.geDirectoryService.deleteGeDirectory(geDirectory.getEid());
		}
		
		List<GeProductMain> list = productManageService.findEntitys(GeProductMain.class, QueryRule.getInstance() );
		for (GeProductMain geProductMain : list) {
			productManageService.deleteProduct(geProductMain.getCoreProductCode());
		}
		
//		Map<String, String> resultMap = new HashMap<String, String>();
//		try {
//			String riskCode = super.getRequest().getParameter("riskCode");
//			GeRiskConfig geRiskConfig = geRiskConfigService.findGeRiskConfigByRiskCode(riskCode);
//			if (geRiskConfig == null) {
//				resultMap.put("resultFlag", "error");
//			} else {
//				resultMap.put("resultFlag", "success");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultMap.put("resultFlag", "error");
//		} finally {
//			JSONObject jsonObject = JSONObject.fromObject(resultMap);
//			renderText(jsonObject.toString());
//		}
	}
	
	/**
	 * 判断同一业务领域相同责任代码是否已经存在
	 */
	public void isExistDutyCode(){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String dutyCode0 = super.getRequest().getParameter("dutyCode0");
			String businessArea = super.getRequest().getParameter("businessArea");
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("businessArea", businessArea);
			queryRule.addEqual("dutyCode", dutyCode0);
			if(geDutyConfigService.findDutyKindConfig(queryRule).size() != 0){
				resultMap.put("resultFlag", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
	}

	public String toSearchProduct() throws Exception {
		getRequest().setAttribute("fm", getRequest().getParameter("fm"));
		return SUCCESS;
	}
	
	
	// 数据推送
	public boolean copyData(String productCode){
//      删除全部geSale数据
//		List<GeSaleProduct> allSaleData = geSaleProductService.findByQuery(QueryRule.getInstance());
//		for (GeSaleProduct geSaleProduct : allSaleData) {
//			geSaleProductService.deleteByCode(geSaleProduct.getCoreProductCode());
//		}
		//String productCode = "010";
		
		boolean suc = true;
		try {
			GeProductMain product = productManageService.findProductMainByCoreProductCode(productCode);
			
			geSaleProductService.deleteByCode(productCode);

			GeSaleProduct saveEntity = new GeSaleProduct();
			BeanUtils.copyProperties(product, saveEntity);
			
			// GeSaleProExtraInfo 
			List<GeSaleProExtraInfo> list_GeSaleProExtraInfo = new ArrayList<GeSaleProExtraInfo>();
			List<GeProductExtraInfo> list_GeProductExtraInfos = product.getGeProductExtraInfos();
			for (GeProductExtraInfo geProductExtraInfo :list_GeProductExtraInfos) {
				GeSaleProExtraInfo info = new GeSaleProExtraInfo();
				BeanUtils.copyProperties(geProductExtraInfo, info);
				info.setSerialNo(null);
				
				info.setGeSaleProduct(saveEntity);
				list_GeSaleProExtraInfo.add(info);
			}
			saveEntity.setGeSaleProExtraInfos(list_GeSaleProExtraInfo);

			//GeSaleProInformbook
			List<GeSaleProInformbook> list_GeSaleProInformbook = new ArrayList<GeSaleProInformbook>();
			for (GeProductInformBook geProductInformBook : product.getGeProductInformBooks()) {
				GeSaleProInformbook info = new GeSaleProInformbook();
				BeanUtils.copyProperties(geProductInformBook, info);
				info.setSerialNo(null);
				info.setGeSaleProduct(saveEntity);
				list_GeSaleProInformbook.add(info);
			}
			saveEntity.setGeSaleProInformbooks(list_GeSaleProInformbook);
			
			//GeSaleProPolicyDisplayConfig
			List<GeSaleProPolicyDisplayConfig> list_GeSaleProPolicyDisplayConfig = new ArrayList<GeSaleProPolicyDisplayConfig>();
			for (GeProductPolicyDisplayConfig copy : product.getGeProductPolicyDisplayConfigs()) {
				GeSaleProPolicyDisplayConfig target = new GeSaleProPolicyDisplayConfig();
				BeanUtils.copyProperties(copy, target);
				target.setSerialNo(null);
				target.setGeSaleProduct(saveEntity);
				list_GeSaleProPolicyDisplayConfig.add(target);
			}
			saveEntity.setGeSaleProPolicyDisConfigs(list_GeSaleProPolicyDisplayConfig);
			//GeSaleProApplicantConfig 
			List<GeSaleProApplicantConfig> list_GeSaleProApplicantConfig = new ArrayList<GeSaleProApplicantConfig>();
			for (GeProductApplicantConfig copy : product.getGeProductApplicantConfigs()) {
				GeSaleProApplicantConfig target = new GeSaleProApplicantConfig();
				BeanUtils.copyProperties(copy, target);
				target.setSerialNo(null);
				target.setGeSaleProduct(saveEntity);
				list_GeSaleProApplicantConfig.add(target);
			}
			saveEntity.setGeSaleProApplicantConfigs(list_GeSaleProApplicantConfig);
			//GeSaleProEmergencyConfig  
			List<GeSaleProEmergencyConfig> list_GeSaleProEmergencyConfig = new ArrayList<GeSaleProEmergencyConfig>();
			for (GeProductEmergencyConfig copy : product.getGeProductEmergencyConfigs()) {
				GeSaleProEmergencyConfig target = new GeSaleProEmergencyConfig();
				BeanUtils.copyProperties(copy, target);
				target.setSerialNo(null);
				target.setGeSaleProduct(saveEntity);
				list_GeSaleProEmergencyConfig.add(target);
			}
			saveEntity.setGeSaleProEmergencyConfigs(list_GeSaleProEmergencyConfig);
			//GeSaleProAddresseeConfig     
			List<GeSaleProAddresseeConfig> list_GeSaleProAddresseeConfig = new ArrayList<GeSaleProAddresseeConfig>();
			for (GeAddresseeConfig copy : product.getGeAddresseeConfigs()) {
				GeSaleProAddresseeConfig target = new GeSaleProAddresseeConfig();
				BeanUtils.copyProperties(copy, target);
				target.setSerialNo(null);
				target.setGeSaleProduct(saveEntity);
				list_GeSaleProAddresseeConfig.add(target);
			}
			saveEntity.setGeSaleProAddresseeConfigs(list_GeSaleProAddresseeConfig);
			//GeSaleProBeneficiaryConfig  
			List<GeSaleProBeneficiaryConfig> list_GeSaleProBeneficiaryConfig = new ArrayList<GeSaleProBeneficiaryConfig>();
			for (GeProductBeneficiaryConfig copy : product.getGeProductBeneficiaryConfigs()) {
				GeSaleProBeneficiaryConfig target = new GeSaleProBeneficiaryConfig();
				BeanUtils.copyProperties(copy, target);
				target.setSerialNo(null);
				target.setGeSaleProduct(saveEntity);
				list_GeSaleProBeneficiaryConfig.add(target);
			}
			saveEntity.setGeSaleProBeneficiaryConfigs(list_GeSaleProBeneficiaryConfig);
			//GeSaleProAttrAllowValues
			List<GeSaleProAttrAllowValues> list_GeSaleProAttrAllowValues = new ArrayList<GeSaleProAttrAllowValues>();
			for (GeProductAttrAllowValues copy : product.getGeProductAttrAllowValueses()) {
				GeSaleProAttrAllowValues target = new GeSaleProAttrAllowValues();
				BeanUtils.copyProperties(copy, target);
				target.setSerialNo(null);
				target.setGeSaleProduct(saveEntity);
				list_GeSaleProAttrAllowValues.add(target);
			}
			saveEntity.setGeSaleProAttrAllowValueses(list_GeSaleProAttrAllowValues);
			
			//GeSaleProWebFlow - GeSaleProWebFlowPage - GeSaleProWebFlowPageElement
			List<GeWebFlow> list_GeWebFlow = product.getGeWebFlows();
			List<GeSaleProWebFlow> list_GeSaleProWebFlow = new ArrayList<GeSaleProWebFlow>();
			for (GeWebFlow copy : list_GeWebFlow) {
				GeSaleProWebFlow target = new GeSaleProWebFlow();
				BeanUtils.copyProperties(copy, target);
				target.setGeSaleProduct(saveEntity);
				target.setSerialNo(null);
				target.setGeFlowConfig(copy.getGeFlowConfig());
				
				List<GeSaleProWebFlowPage> list_GeSaleProWebFlowPage = new ArrayList<GeSaleProWebFlowPage>();
				for (GeWebFlowPage GeWebFlowPage : copy.getGeWebFlowPages()) {
					GeSaleProWebFlowPage geSaleProWebFlowPage = new GeSaleProWebFlowPage();
					BeanUtils.copyProperties(GeWebFlowPage, geSaleProWebFlowPage);
					geSaleProWebFlowPage.setSerialNo(null);
					geSaleProWebFlowPage.setGeSaleProduct(saveEntity);
					geSaleProWebFlowPage.setGePageConfig(GeWebFlowPage.getGePageConfig());
					geSaleProWebFlowPage.setGeSaleProWebFlow(target);
					
					List<GeSaleProWebFlowPageElement> list_GeSaleProWebFlowPageElement = new ArrayList<GeSaleProWebFlowPageElement>();
					for (GeWebFlowPageElement geWebFlowPageElement : GeWebFlowPage.getGeWebFlowPageElements()) {
						GeSaleProWebFlowPageElement geSaleProWebFlowPageElement = new GeSaleProWebFlowPageElement();
						BeanUtils.copyProperties(geWebFlowPageElement,geSaleProWebFlowPageElement );
						geSaleProWebFlowPageElement.setSerialNo(null);
						geSaleProWebFlowPageElement.setGeSaleProduct(saveEntity);
						geSaleProWebFlowPageElement.setGePageElementConfig(geWebFlowPageElement.getGePageElementConfig());
						geSaleProWebFlowPageElement.setGeSaleProWebFlowPage(geSaleProWebFlowPage);
						
						// 文件推送
						fileCopy(product.getCoreProductCode(),geWebFlowPageElement.getGePageElementConfig().getElementCode());
						list_GeSaleProWebFlowPageElement.add(geSaleProWebFlowPageElement);
					}
					
					geSaleProWebFlowPage.setGeSaleProWebFlowPageElements(list_GeSaleProWebFlowPageElement);
					list_GeSaleProWebFlowPage.add(geSaleProWebFlowPage);
				}
				
				target.setGeSaleProWebFlowPages(list_GeSaleProWebFlowPage);
				list_GeSaleProWebFlow.add(target);
			}
			saveEntity.setGeSaleProWebFlows(list_GeSaleProWebFlow);
			
			//GeSaleProInsuredConfig - GeSaleProInsuredOccupation
			List<GeProductInsuredConfig> list_GeProductInsuredConfig = product.getGeProductInsuredConfigs();
			List<GeSaleProInsuredConfig> list_GeSaleProInsuredConfig = new ArrayList<GeSaleProInsuredConfig>();
			for (GeProductInsuredConfig copy : list_GeProductInsuredConfig) {
				GeSaleProInsuredConfig target = new GeSaleProInsuredConfig();
				BeanUtils.copyProperties(copy, target);
				target.setSerialNo(null);
				target.setGeSaleProduct(saveEntity);
				
				List<GeProInsuredOccupation> list_GeProInsuredOccupation = copy.getGeProInsuredOccupations();
				List<GeSaleProInsuredOccupation> list_GeSaleProInsuredOccupation = new ArrayList<GeSaleProInsuredOccupation>();
				for (GeProInsuredOccupation geProInsuredOccupation : list_GeProInsuredOccupation) {
					GeSaleProInsuredOccupation occ = new GeSaleProInsuredOccupation();
					BeanUtils.copyProperties(geProInsuredOccupation, occ);
					occ.setSerialNo(null);
					occ.setGeSaleProInsuredConfig(target);
					
					list_GeSaleProInsuredOccupation.add(occ);
				}
				
				target.setGeSaleProInsuredOccupations(list_GeSaleProInsuredOccupation);
				list_GeSaleProInsuredConfig.add(target);
			}
			saveEntity.setGeSaleProInsuredConfigs(list_GeSaleProInsuredConfig);
			
			//GeSaleProProDept- GeSaleProSaleDepPayment
			List<GeProductProDept> list_GeProductProDept = product.getGeProductProDepts();
			List<GeSaleProProDept> list_GeSaleProProDept = new ArrayList<GeSaleProProDept>();
			for (GeProductProDept copy : list_GeProductProDept) {
				GeSaleProProDept target = new GeSaleProProDept();
				BeanUtils.copyProperties(copy, target);
				target.setSerialNo(null);
				target.setGeSaleProduct(saveEntity);
				
				List<GeProductSaleDepPayment> list_GeProductSaleDepPayment = copy.getGeProductSaleDepPayments();
				List<GeSaleProSaleDepPayment> list_GeSaleProSaleDepPayment = new ArrayList<GeSaleProSaleDepPayment>();
				for (GeProductSaleDepPayment gePayment : list_GeProductSaleDepPayment) {
					GeSaleProSaleDepPayment occ = new GeSaleProSaleDepPayment();
					BeanUtils.copyProperties(gePayment, occ);
					occ.setSerialNo(null);
					occ.setGeSaleProProDept(target);
					
					list_GeSaleProSaleDepPayment.add(occ);
				}
				
				target.setGeSaleProSaleDepPayments(list_GeSaleProSaleDepPayment);
				list_GeSaleProProDept.add(target);
			}
			saveEntity.setGeSaleProProDepts(list_GeSaleProProDept);
			
			//GeSaleProductRisk - GeSaleProductDuty - GeSaleProDutyAttrAllowVal
			List<GeProductRisk> list_GeProductRisk = product.getGeProductRisks();
			List<GeSaleProductRisk> list_GeSaleProductRisk = new ArrayList<GeSaleProductRisk>();
			for (GeProductRisk copy : list_GeProductRisk) {
				GeSaleProductRisk target = new GeSaleProductRisk();
				BeanUtils.copyProperties(copy, target);
				target.setSerialNo(null);
				target.setGeSaleProduct(saveEntity);
				
				List<GeProductDuty> list_GeProductDuty = copy.getGeProductDuties();
				List<GeSaleProductDuty> list_GeSaleProductDuty = new ArrayList<GeSaleProductDuty>();
				for (GeProductDuty GeProductDuty : list_GeProductDuty) {
					GeSaleProductDuty geSaleProductDuty = new GeSaleProductDuty();
					BeanUtils.copyProperties(GeProductDuty, geSaleProductDuty);
					geSaleProductDuty.setSerialNo(null);
					geSaleProductDuty.setGeSaleProduct(saveEntity);
					geSaleProductDuty.setGeSaleProductRisk(target);
					
					List<GeProDutyAttrAllowVal> list_GeProDutyAttrAllowVal = GeProductDuty.getGeProDutyAttrAllowVals();
					List<GeSaleProDutyAttrAllowVal> list_GeSaleProDutyAttrAllowVal = new ArrayList<GeSaleProDutyAttrAllowVal>();
					for (GeProDutyAttrAllowVal geProDutyAttrAllowVal : list_GeProDutyAttrAllowVal) {
						GeSaleProDutyAttrAllowVal geSaleProDutyAttrAllowVal = new GeSaleProDutyAttrAllowVal();
						BeanUtils.copyProperties(geProDutyAttrAllowVal, geSaleProDutyAttrAllowVal);
						geSaleProDutyAttrAllowVal.setSerialNo(null);
						geSaleProDutyAttrAllowVal.setGeSaleProductDuty(geSaleProductDuty);
						
						list_GeSaleProDutyAttrAllowVal.add(geSaleProDutyAttrAllowVal);
					}
					
					geSaleProductDuty.setGeSaleProDutyAttrAllowVals(list_GeSaleProDutyAttrAllowVal);
					list_GeSaleProductDuty.add(geSaleProductDuty);
				}
				
				target.setGeSaleProductDuties(list_GeSaleProductDuty);
				list_GeSaleProductRisk.add(target);
			}
			saveEntity.setGeSaleProductRisks(list_GeSaleProductRisk);
			
			productManageService.saveEntity(saveEntity);
		} catch (Exception e) {
			suc = false;
			e.printStackTrace();
		}
		return suc;
	}
	
	
	// 文件推送
	public boolean fileCopy(String coreProductCode,String elementCode) {
		boolean suc = true;
		try {
			String pageElementFileDirPath = pageElementFileUpdatePath + "/" + coreProductCode;
			File pageElementFileDir = new File(pageElementFileDirPath);
			
			String publishDirPath = publishedProductPath + "/" + coreProductCode;
			File publishDirPathFileDir = new File(publishDirPath);
			
			if (pageElementFileDir.exists()) {
				File[] copyFiles = pageElementFileDir.listFiles();
				if(copyFiles.length > 0){
					if(!publishDirPathFileDir.exists()){
						publishDirPathFileDir.mkdirs();
					}
					
					DirCopy.fileCopy(new File(pageElementFileDir.getPath()), new File(publishDirPathFileDir.getPath()));
				}
			}
		} catch (Exception e) {
			suc = false;
			e.printStackTrace();
		}
		return suc;
	}
	
	
	// 显示总览
	public String toProductDetailOverview () {
		String productCode = getRequest().getParameter("coreProductCode");
		getRequest().setAttribute("coreProductCode", productCode);
		
		List<GePageElementConfig>  list = geWebFlowPageService.findGePageElementConfigBycoreProductCode(productCode);
		getRequest().setAttribute("list", list);
		return SUCCESS;
	}
	
	// 保存总览
	public void saveDetailOverview(){
		try {
			String productCode = getRequest().getParameter("coreProductCode");
			geProductMain = productManageService.findProductMainByCoreProductCode(productCode);
			List objlist = geWebFlowPageService.findGePageElementConfigBycoreProductCode(productCode);
			boolean suc = true;
			for (Object obj : objlist) {
				Object[] val = (Object[])obj;
				String status = (String)val[3];
				String elementCode = (String)val[0];
				
				// 不校验产品规则
				if("productRule".equals(elementCode)){
					continue;
				}
				if("0".equals(status)){
					suc = false;
					break;
				}
			}

			if(suc){
				geProductMain.setProductStatus("03");
				productManageService.updatePro(geProductMain);
				
				// 启动工作流
//				EntityWorkFlow<GeProductMain> wf = new EntityWorkFlow<GeProductMain>();
//				wf.setWorkFlowID("productSale" + geProductMain.getBusinessArea());  // 业务领域动态拼接
				
				// 产品的销售地区
				List<String> list = geProductProDeptService.findProductSaleDept(geProductMain.getCoreProductCode());
				StringBuffer allCitys = new StringBuffer();
				for (int i = 0; i < list.size(); i++) {
					if(i == list.size() -1)
						allCitys.append(list.get(i));
					else
						allCitys.append(list.get(i)).append(",");
				}

				Map<String,Object> params = new HashMap<String, Object>();
				params.put("productStatus", geProductMain.getProductStatus());
				params.put("productCode", geProductMain.getCoreProductCode());
				params.put("productName", geProductMain.getProductName());
				params.put("busArea", geProductMain.getBusinessArea());
				
				// 轨迹
				GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
				GeProductMainProcess process = new GeProductMainProcess();
				process.setGeProductMain(geProductMain);
				process.setHandleDate(new Date());
				process.setHandleStatus(geProductMain.getProductStatus());
				process.setOperatorID(geOperator.getOperatorid());
				process.setHandleDept(geOperator.getDeptid());
				geProductMain.getGeProductMainProcesses().add(process);
				
//				wf.setEntity(geProductMain);
//				// "2110000,2120000"
//				workFlowServiceImpl.startTask(wf,params,allCitys.toString());
				
				// 保存状态和轨迹
				productManageService.updatePro(geProductMain);
				
				getResponse().getWriter().write("suc");
			}else{
				getResponse().getWriter().write("edit");
			}
			getResponse().getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 得到待办事务条数
	public String getWaitDoingCount(){
		long totalCount = 0;
		try {
//			GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
//			Map<String, Set<String>> setMap = workFlowServiceImpl.getWorkFlowGroup(operator.getOperatorid(),"productSale");// 得到用户所在工作流的节点的用户组 ，与流程
//			
//			//没有在页面选择机构权限，则从session中获取当前登录用户的机构权限
//			String authorityid = super.getRequest().getParameter("authorityid");
//			if(StringUtils.isBlank(authorityid)){
//				Map map = (Map) super.getSession().getAttribute("permission");
//				authorityid = (String) map.get("ROLE_B_CRPR_S"); 
//			}
//			
//			Set<String> inGroups = setMap==null?null:setMap.get("group");
//			Set<String> inTasks = setMap==null?null:setMap.get("taskID");
//			if(inGroups!=null && !inGroups.isEmpty() && inTasks!=null && !inTasks.isEmpty() && authorityid!=null && !"".equals(authorityid)){
//
//				//获取查询机构
//				StringBuffer stringBuffer = new StringBuffer();
//				String[] arrayAuth = authorityid.split(",");
//				for(String str : (Set<String>)setMap.get("group")){
//					for(int i = 0; i < arrayAuth.length; i++){
//						stringBuffer.append(str + arrayAuth[i] + ",");
//					}
//				}
//	
//				// 组+组织机构编码 的拼接字符串
//				String groupIDsString = stringBuffer.substring(0,stringBuffer.lastIndexOf(","));
//				// 流程id 的拼接字符串
//				StringBuffer workFlowBuffer = new StringBuffer();
//				
//				Set<String> sets = setMap.get("taskID");
//				if( !sets.isEmpty()){
//					for(String str : (Set<String>)setMap.get("taskID")){
//						workFlowBuffer.append(str + ",");
//					}
//					String workFlowID = workFlowBuffer.substring(0,workFlowBuffer.lastIndexOf(","));
//					
//					// 查询条件
//					TaskQuery tq = taskService.createTaskQuery();
//					totalCount = this.workFlowServiceImpl.getTotalCount(groupIDsString, workFlowID, tq);
//				}
//			}
			String countStr = JsonBinder.buildNonNullBinder().toJson(totalCount);
			super.render(countStr, "application/json;charset=utf-8");
			return NONE;
		} catch (Exception e) {
			e.printStackTrace();
			totalCount = -1;
		}
		
		return null;
	}
	
	// 保存险种责任配置
	public void addRiskDuty(){
		try {
			String productId = getRequest().getParameter("productId");
			geProductMain = productManageService.findProductMainByCoreProductCode(productId);
			
			// 校验是否是已发布后，过来编辑的
			valProductStatus(geProductMain);
			
			// 新
			List<String> riskNowList = new ArrayList<String>();
			List<String> dutyNowList = new ArrayList<String>();
			if(attributes!=null && !attributes.equals("")){
				
				GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
				
				String[] allRisk_old = allRisk.split(",");  // 已选的险种代码
				String[] allDuty_old = allDuty.split(",");  // 已选的责任(配置表里的id)
				
				String[] allAttributes = attributes.split(",");
				for (int i = 0; i < allAttributes.length; i++) {
					String s = allAttributes[i];
					if(s.indexOf("#") !=-1){
						// 产品id
					}else if(s.indexOf("@-") !=-1){
						dutyNowList.add(s);
					}else{
						riskNowList.add(s);
					}
				}

				int index = attributes.indexOf("#-");
				String duty = attributes.substring(0,index);
				String risk = attributes.substring(index,attributes.length());

				// 旧
				List<String> allRisk_list = Arrays.asList(allRisk_old);
				List<String> allDuty_list = Arrays.asList(allDuty_old);
				
				allRisk_list = new ArrayList(allRisk_list);
				allDuty_list = new ArrayList(allDuty_list);
				
				// 添加险种
				for (String code : riskNowList) {
					if(!allRisk_list.contains(code)){
						QueryRule q = QueryRule.getInstance();
						q.addEqual("riskCode", code);
						List<GeRiskConfig> list = geRiskConfigService.findGeRiskConfigByQueryRule(q);
						if(list!=null && list.size()>0){
							GeRiskConfig riskConfig = list.get(0);
							GeProductRisk geProductRisk = new GeProductRisk();
							
							geProductRisk.setCreateTime(new Date());
							geProductRisk.setGeProductMain(geProductMain);
							geProductRisk.setGeRiskConfig(riskConfig);
							geProductRisk.setProductRiskCode(code);
							geProductRisk.setProductRiskName(riskConfig.getRiskName());
								
							geProductRiskService.addGeProductRisk(geProductRisk);
						}
					}
				}
				// 添加责任(每个新的在旧的里没有)
				for (String id : dutyNowList) {
					index = id.indexOf("@-");
					String belongRiskID = id.substring(0, index);
					String realID = id.substring(index+2,id.length());
					
					if(!allDuty_list.contains(id)){
						QueryRule q = QueryRule.getInstance();
						q.addEqual("serialNo", realID);
						List<GeDutyConfig> list = geDutyConfigService.findDutyKindConfig(q);
						if(list!=null && list.size()>0){
							GeDutyConfig config = list.get(0);
							GeProductDuty geProductDuty = new GeProductDuty();
							geProductDuty.setCreateTime(new Date());
							geProductDuty.setGeDutyConfig(config);
							geProductDuty.setGeProductMain(geProductMain);
							//geProductDuty.setProductDutyCode(config.getDutyCode());
							geProductDuty.setProductDutyName(config.getDutyName());
							geProductDuty.setOperatorID(geOperator.getOperatorid());
							
							q = QueryRule.getInstance();
							q.addEqual("productRiskCode", belongRiskID);
							q.addEqual("geProductMain.coreProductCode", productId);
							GeProductRisk geRisk = this.geProductRiskService.findByQueryRule(q).get(0);
							geProductDuty.setGeProductRisk(geRisk);
							
							geProductDutyService.addGeProductDuty(geProductDuty);
						}
					}
				}
				
				// 每个旧的在新的里没有做删除操作
				for (String id : allDuty_list) {
					if(!"".equals(id)){
						if( ! dutyNowList.contains(id)){
							String dutyId = id.substring(id.indexOf("@-")+2, id.length());
							
							// 删除责任
							QueryRule q = QueryRule.getInstance();
							q.addEqual("geDutyConfig.serialNo", dutyId);
							q.addEqual("geProductMain.coreProductCode", productId);
							GeProductDuty dutyDel = geProductDutyService.findGeProductDutyByQueryRule(q).get(0);
							if(dutyDel != null)
								geProductDutyService.deleteGeProductDuty(dutyDel);
						}
					}
				}
				
				for (String code : allRisk_list) {
					if(!"".equals(code)){
						if( ! riskNowList.contains(code)){
							// 删除险种
							QueryRule q = QueryRule.getInstance();
							q = QueryRule.getInstance();
							q.addEqual("productRiskCode", code);
							q.addEqual("geProductMain.coreProductCode", productId);
							GeProductRisk geRisk = this.geProductRiskService.findByQueryRule(q).get(0);
							geProductRiskService.deleteGeProductRisk(geRisk);
						}
					}
				}
			}else{
				// 根据产品id全部删除
				geProductDutyService.deleteByProductId(productId);
				geProductRiskService.deleteByProductId(productId);
			}
			
			// 置标签页状态(没有责任时，状态需要置回)
			if(dutyNowList.size()>0){
				geWebFlowPageElementService.updateGeWebFlowPageElementStatus(geProductMain.getCoreProductCode(),"dutyKindConfig");
			}else{
				geWebFlowPageElementService.updateGeWebFlowPageElementStatus("0",geProductMain.getCoreProductCode(),"dutyKindConfig");
			}
			
			getResponse().getWriter().write("suc");
			getResponse().getWriter().flush();
			getResponse().getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// (详细定义)编辑每个标签页时校验产品状态，如果是刚发布的，产品状态改为已定制流程
	public boolean valProductStatus(GeProductMain geProductMain){
		boolean suc = true;
		try {
			geProductMain = productManageService.findProductMainByCoreProductCode(geProductMain.getCoreProductCode());
			if(geProductMain.getProductStatus().equals("05")){
				geProductMain.setProductStatus("02");
				
				productManageService.updatePro(geProductMain);
			}
		} catch (Exception e) {
			suc = false;
			e.printStackTrace();
		}
		return suc;
	}
	
	// (详细定义)编辑每个标签页时校验产品状态，如果是刚发布的，产品状态改为已定制流程
	public boolean valProductStatus(String coreProductCode){
		boolean suc = true;
		try {
			geProductMain = productManageService.findProductMainByCoreProductCode(coreProductCode);
			if(geProductMain.getProductStatus().equals("05")){
				geProductMain.setProductStatus("02");
				
				productManageService.updatePro(geProductMain);
			}
		} catch (Exception e) {
			suc = false;
			e.printStackTrace();
		}
		return suc;
	}
	
	// 详细定义-险种配置-添加产品险种和责任
	public String toAddProductRisk() {
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		geProductMain = productManageService.findProductMainByCoreProductCode(coreProductCode);
		String areaId = geProductMain.getBusinessArea();
		
		List result = geProductRiskService.findDutyRiskTree(coreProductCode,areaId);
		String productCompontTree =	(String)result.get(0);
		List<GeProductDuty> dutys = (List)result.get(1);
		List<GeProductRisk> risks = (List)result.get(2);
		
		StringBuffer allRisk = new StringBuffer();
		for (GeProductRisk geProductRisk : risks) {
			allRisk.append(geProductRisk.getProductRiskCode()).append(",");
		}
		StringBuffer allduty = new StringBuffer();
		for (GeProductDuty duty : dutys) {
			allduty.append(duty.getGeProductRisk().getGeRiskConfig().getRiskCode()+"@-"+ duty.getGeDutyConfig().getSerialNo()).append(",");//存的是配置表的id
		}
		
		// allDuty 为险种编码-责任配置表id
		// allRisk 为险种编码
		super.getRequest().setAttribute("productCompontTree", productCompontTree);
		super.getRequest().setAttribute("allRisk", allRisk.toString());
		super.getRequest().setAttribute("allDuty", allduty.toString());
		return SUCCESS;
	}
	
	public String toUpdateRisk(){
		String serialNo = getRequest().getParameter("serialNo");

		QueryRule q = QueryRule.getInstance();
		q.addEqual("serialNo", serialNo);
		this.geProductRisk = geProductRiskService.findByQueryRule(q).get(0);
		return SUCCESS;
	}
	
	// 保存编辑险种
	public String updateRisk(){
		try {
			GeProductRisk saveRisk = geProductRiskService.findByserialNo(geProductRisk.getSerialNo());
			saveRisk.setProductRiskName(geProductRisk.getProductRiskName());
			saveRisk.setSeqIndex(geProductRisk.getSeqIndex());
			saveRisk.setIsshowProductDuty(geProductRisk.getIsshowProductDuty());
			saveRisk.setSaleFlag(geProductRisk.getSaleFlag());
			geProductRiskService.updateGeProductRisk(saveRisk);
			super.getRequest().setAttribute("addReslut", "success");
		} catch (Exception e) {
			super.getRequest().setAttribute("addReslut", "failure");
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String toUpdateDuty(){
		String configSerialNo = getRequest().getParameter("configSerialNo");
		QueryRule q = QueryRule.getInstance();
		q.addEqual("serialNo", configSerialNo);
		this.geProductDuty = geProductDutyService.findGeProductDutyByQueryRule(q).get(0);
		
		// 排序
		geProductDuty.setGeProDutyAttrAllowVals(sortGeProDutyAttrAllowVal(geProductDuty.getGeProDutyAttrAllowVals()));
		getRequest().setAttribute("productAttrAllowValueListSize", geProductDuty.getGeProDutyAttrAllowVals().size());
		
		List<GeCode> periodTypeList = geCodeService.findAllByGeCodeType("InsuredAmountUnit");//保额单位
		super.getRequest().setAttribute("periodTypeList", periodTypeList);
		
		return SUCCESS;
	}
	
	// 保存编辑责任
	public String updateDuty(){
		try {
			// 更新保额
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("geProductDuty.serialNo", geProductDuty.getSerialNo());
			queryRule.addEqual("attributeKind", "givePrice");
			
			List<GeProDutyAttrAllowVal>  productAttrAllowValueList = geDutyAttrAllowValueService.findByQuery(queryRule);
			for (GeProDutyAttrAllowVal geProDutyAttrAllowVal : productAttrAllowValueList) {
				geDutyAttrAllowValueService.delete(geProDutyAttrAllowVal);
			}
			
			int insertCount = 0;
			List<GeProDutyAttrAllowVal> saveList = geProductDuty.getGeProDutyAttrAllowVals();
			for (GeProDutyAttrAllowVal geProDutyAttrAllowVal : saveList) {
				if(geProDutyAttrAllowVal.getAttributeTypeValue()!=null && geProDutyAttrAllowVal.getAttributeValue()!=null
					&& !geProDutyAttrAllowVal.getAttributeTypeValue().equals("") && !geProDutyAttrAllowVal.getAttributeValue().equals("")){
					
					geDutyAttrAllowValueService.add(geProDutyAttrAllowVal);
					++ insertCount;
				}
			}
			
			GeProductDuty saveDuty = geProductDutyService.findBySerialNo(geProductDuty.getSerialNo());
			saveDuty.setProductDutyName(geProductDuty.getProductDutyName());
			saveDuty.setSeqIndex(geProductDuty.getSeqIndex());
			saveDuty.setIsEditable(geProductDuty.getIsEditable());
			saveDuty.setSaleFlag(geProductDuty.getSaleFlag());
			
			geProductDutyService.updateGeProductDuty(saveDuty);
			
			// 排序
			geProductDuty.setGeProDutyAttrAllowVals(sortGeProDutyAttrAllowVal(geProductDuty.getGeProDutyAttrAllowVals()));
			
			List<GeCode> periodTypeList = geCodeService.findAllByGeCodeType("InsuredAmountUnit");//保额单位
			super.getRequest().setAttribute("periodTypeList", periodTypeList);
			super.getRequest().setAttribute("addReslut", "success");
			
			// 保额长度
			getRequest().setAttribute("productAttrAllowValueList", productAttrAllowValueList);
			getRequest().setAttribute("allowValueSize", insertCount);
		} catch (Exception e) {
			super.getRequest().setAttribute("addReslut", "failure");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String toDetailRisk(){
		String serialNo = getRequest().getParameter("serialNo");

		QueryRule q = QueryRule.getInstance();
		q.addEqual("serialNo", serialNo);
		this.geProductRisk = geProductRiskService.findByQueryRule(q).get(0);
		return SUCCESS;
	}
	
	public String toDetailDuty(){
		String configSerialNo = getRequest().getParameter("configSerialNo");
		QueryRule q = QueryRule.getInstance();
		q.addEqual("serialNo", configSerialNo);
		this.geProductDuty = geProductDutyService.findGeProductDutyByQueryRule(q).get(0);
		
		// 排序
		geProductDuty.setGeProDutyAttrAllowVals(sortGeProDutyAttrAllowVal(geProductDuty.getGeProDutyAttrAllowVals()));
		getRequest().setAttribute("allowValueSize", geProductDuty.getGeProDutyAttrAllowVals().size());
		
		return SUCCESS;
	}
	
	// 删除险种
	public void delRisk(){
		try {
			String serialNo = getRequest().getParameter("id");

			QueryRule q = QueryRule.getInstance();
			q.addEqual("serialNo", serialNo);
			GeProductRisk risk = geProductRiskService.findByQueryRule(q).get(0);
			
			geProductRiskService.deleteGeProductRisk(risk);
			
			// 如果一条责任都没有，状态置回
			q = QueryRule.getInstance();
			q.addEqual("geProductMain.coreProductCode", risk.getGeProductMain().getCoreProductCode());
			List<GeProductDuty> list = geProductDutyService.findGeProductDutyByQueryRule(q);
			if(list.size()<=0){
				geWebFlowPageElementService.updateGeWebFlowPageElementStatus("0",risk.getGeProductMain().getCoreProductCode(),"dutyKindConfig");
			}
			
			getResponse().getWriter().write("suc");
			getResponse().getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	// 删除责任
	public void delDuty(){
		try {
			String writeStr ="suc";
			String id = getRequest().getParameter("id");
			QueryRule q = QueryRule.getInstance();
			q.addEqual("serialNo", id);
			GeProductDuty duty = geProductDutyService.findGeProductDutyByQueryRule(q).get(0);
			
			// 如果是该险种的最后一个责任，把险种删除
			q = QueryRule.getInstance();
			q.addEqual("geProductRisk.serialNo", duty.getGeProductRisk().getSerialNo());
			q.addEqual("geProductMain.coreProductCode", duty.getGeProductMain().getCoreProductCode());
			List<GeProductDuty> dutyList = geProductDutyService.findGeProductDutyByQueryRule(q);
			if(dutyList.size()==1){
				writeStr = "del";
				geProductRiskService.deleteGeProductRisk(dutyList.get(0).getGeProductRisk());
			}else{
				geProductDutyService.deleteGeProductDuty(duty);
			}
			
			// 如果一条责任都没有，状态置回
			q = QueryRule.getInstance();
			q.addEqual("geProductMain.coreProductCode", duty.getGeProductMain().getCoreProductCode());
			List<GeProductDuty> list = geProductDutyService.findGeProductDutyByQueryRule(q);
			if(list.size()<=0){
				geWebFlowPageElementService.updateGeWebFlowPageElementStatus("0",duty.getGeProductMain().getCoreProductCode(),"dutyKindConfig");
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("re", writeStr);
			jsonObject.put("id", dutyList.get(0).getGeProductRisk().getSerialNo());
			renderText(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询本机构下的任务(查工作流的表)
	 * @throws Exception 
	 */
 	public String searchFromWorkFlow()  {
		super.getRequest().setAttribute("type", super.getRequest().getParameter("type"));
		pageSize = 20;
		long totalCount = 0L;
		long totalPage = 0L;
//		List<EntityWorkFlow> result = new ArrayList<EntityWorkFlow>();
//		
//		try{
//			productCodeType("productStatus");
//			GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
//			Map<String, Set<String>> setMap = workFlowServiceImpl.getWorkFlowGroup(operator.getOperatorid(),"productSale");// 得到用户所在工作流的节点的用户组 ，与流程
//			//没有在页面选择机构权限，则从session中获取当前登录用户的机构权限
//			String authorityid = super.getRequest().getParameter("authorityid");
//			if(StringUtils.isBlank(authorityid)){
//				Map map = (Map) super.getSession().getAttribute("permission");
//				authorityid = (String) map.get("ROLE_B_CRPR_S"); 
//			}
//			//authorityid = "2110000";
//			
//			Set<String> inGroups = setMap.get("group");
//			Set<String> inTasks = setMap.get("taskID");
//			if(inGroups!=null && !inGroups.isEmpty() && inTasks!=null && !inTasks.isEmpty() && authorityid!=null && !"".equals(authorityid)){
//
//				//获取查询机构
//				StringBuffer stringBuffer = new StringBuffer();
//				String[] arrayAuth = authorityid.split(",");
//				for(String str : (Set<String>)setMap.get("group")){
//					for(int i = 0; i < arrayAuth.length; i++){
//						stringBuffer.append(str + arrayAuth[i] + ",");
//					}
//				}
//	
//				// 组+组织机构编码 的拼接字符串
//				String groupIDsString = stringBuffer.substring(0,stringBuffer.lastIndexOf(","));
//				// 流程id 的拼接字符串
//				StringBuffer workFlowBuffer = new StringBuffer();
//				
//				Set<String> sets = setMap.get("taskID");
//				if( !sets.isEmpty()){
//					for(String str : (Set<String>)setMap.get("taskID")){
//						workFlowBuffer.append(str + ",");
//					}
//					String workFlowID = workFlowBuffer.substring(0,workFlowBuffer.lastIndexOf(","));
//					
//					// 添加其他查询条件(名字为放入map中的键)
//					TaskQuery tq = taskService.createTaskQuery();
//					if (StringUtils.isNotEmpty(geProductMain.getCoreProductCode())
//							&& StringUtils.isNotBlank(geProductMain.getCoreProductCode())) {
//						tq.processVariableValueEquals("productCode", geProductMain.getCoreProductCode());
//					}
//					if (StringUtils.isNotEmpty(geProductMain.getProductName())
//							&& StringUtils.isNotBlank(geProductMain.getProductName())) {
//						//tq.processVariableValueLike("productName", geProductMain.getProductName());
//						tq.processVariableValueEquals("productName", geProductMain.getProductName());
//					}
//					if (StringUtils.isNotEmpty(geProductMain.getBusinessArea())
//							&& StringUtils.isNotBlank(geProductMain.getBusinessArea())) {
//						tq.processVariableValueEquals("busArea", geProductMain.getBusinessArea());
//					}
//					if (StringUtils.isNotEmpty(geProductMain.getProductStatus())
//							&& StringUtils.isNotBlank(geProductMain.getProductStatus())) {
//						tq.processVariableValueEquals("productStatus", geProductMain.getProductStatus());
//					}
//	
//					totalCount = this.workFlowServiceImpl.getTotalCount(groupIDsString, workFlowID, tq);
//					totalPage = totalCount % pageSize != 0 ? totalCount / pageSize + 1L : totalCount / pageSize;
//					result = workFlowServiceImpl.getCandidateTaskByPage(groupIDsString, workFlowID, tq, pageNo, pageSize);
//				}
//			}else{
//				totalCount = 0L;
//				totalPage = 0L;
//				result = new ArrayList<EntityWorkFlow>();
//			}
//		}catch(Exception ex){
//			ex.printStackTrace();
//			totalCount = 0L;
//			totalPage = 0L;
//			result = new ArrayList<EntityWorkFlow>();
//		}
//		
//		super.getRequest().setAttribute("workFlowList", result);
		super.getRequest().setAttribute("curr_search_status", geProductMain.getProductStatus());
		super.getRequest().setAttribute("totalCount", totalCount);
		super.getRequest().setAttribute("totalPage", totalPage);
		super.getRequest().setAttribute("pageNo", pageNo);
		super.getRequest().setAttribute("pageSize", pageSize);
		return SUCCESS;
	}
	
	// 保存轨迹
	public void saveProcess(GeProductMain pro) {
		if(pro!=null){
			GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
			GeProductMainProcess process = new GeProductMainProcess();
			process.setGeProductMain(pro);
			process.setHandleDate(new Date());
			process.setHandleStatus(pro.getProductStatus());
			process.setOperatorID(geOperator.getOperatorid());
			process.setHandleDept(geOperator.getDeptid());
			process.setOptions(suggestion);//处理意见
			
			pro.getGeProductMainProcesses().add(process);
			productManageService.updatePro(pro);
		}
	}
	
	/**
	 * 查询产品定制流程
	 * @return
	 */
	public String findProductWebFlow() {
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		List<GeWebFlowPage> webFlowPageList = geWebFlowPageService.findGeWebFlowPageCoreProductCode(coreProductCode);
		for (int i = 0; i < webFlowPageList.size(); i++) {
			GeWebFlowPage webFlowPage = webFlowPageList.get(i);
			String pageCode = webFlowPage.getGePageConfig().getPageCode();
			List<GePageConfigRelate> pageConfigRelateList = gePageConfigRelateService.findGePageConfigByPageCode(pageCode);
			webFlowPage.getGePageConfig().setGePageConfigRelates(pageConfigRelateList);
			List<GeWebFlowPageElement> webFlowPageElementList = geWebFlowPageElementService.findGeWebFlowPageCoreProductCode(coreProductCode, webFlowPage.getSerialNo());
			if (webFlowPageElementList.size() <=0 ) {
				for (int j = 0; j < pageConfigRelateList.size(); j++) {
					GePageConfigRelate pageConfigRelate = pageConfigRelateList.get(j);
					if ("02".equals(pageConfigRelate.getRequired())) {
						GeWebFlowPageElement webFlowPageElement = new  GeWebFlowPageElement();
						webFlowPageElement.setGePageElementConfig(pageConfigRelate.getGePageElementConfig());
						webFlowPageElement.setSeqIndex(pageConfigRelate.getDefaultSeqIndex());
						webFlowPageElementList.add(webFlowPageElement);
					}
				}
			}
			webFlowPage.setGeWebFlowPageElements(webFlowPageElementList);
		}
		super.getRequest().setAttribute("webFlowPageList", webFlowPageList);
		super.getRequest().setAttribute("coreProductCode", coreProductCode);
		return SUCCESS;
	}
	
	/**
	 * 保存产品定制流程
	 * @return
	 */
	public String saveProductWebFlow() {
		String webFlowPageElementJSONString = super.getRequest().getParameter("webFlowPageElementList");
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		JSONArray jsonArray = JSONArray.fromObject(webFlowPageElementJSONString);
		List<GeWebFlowPageElement> geWebFlowPageElementList= new ArrayList<GeWebFlowPageElement>();
		 for (int i = 0 ; i < jsonArray.size(); i++) {
			 GeWebFlowPageElement wfpe = (GeWebFlowPageElement) JSONObject.toBean(jsonArray.getJSONObject(i), GeWebFlowPageElement.class);
			 geWebFlowPageElementList.add(wfpe);
		 }
		geWebFlowPageElementService.addAllGeWebFlowPageElement(geWebFlowPageElementList, coreProductCode);
		productManageService.updateProductStatus(coreProductCode, "02");
		
		// 保存轨迹
		geProductMain = productManageService.searchProductByProductCode(coreProductCode);
		saveProcess(geProductMain);
			
		return NONE;
	}
	
	/**
	 * 查询产品**********
	 * @return
	 * @throws Exception
	 */
	public String searchAllProduct() throws Exception {
		productCodeType("productStatus");
		QueryRule queryRule = QueryRule.getInstance();
		pageNo = pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == 0 ? 20 : pageSize;
		
		// 无查询权限
		if(geProductMain.getProductStatus()==null ||"".equals(geProductMain.getProductStatus())){
			page = new Page();
			getRequest().setAttribute("totalCount", page.getTotalCount());
			return SUCCESS;
		}

		if (StringUtils.isNotEmpty(geProductMain.getCoreProductCode())
				&& StringUtils.isNotBlank(geProductMain.getCoreProductCode())) {
			queryRule.addLike("coreProductCode","%" + geProductMain.getCoreProductCode().replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(geProductMain.getProductName())
				&& StringUtils.isNotBlank(geProductMain.getProductName())) {
			queryRule.addLike("productName", "%" + geProductMain.getProductName().replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(geProductMain.getBusinessArea())
				&& StringUtils.isNotBlank(geProductMain.getBusinessArea())) {
			queryRule.addEqual("businessArea", geProductMain.getBusinessArea());
		}
		
		// 添加状态查询
		if(!"all".equals(geProductMain.getProductStatus())){
			queryRule.addEqual("productStatus", geProductMain.getProductStatus());
		}
		queryRule.addDescOrder("createDate");
		queryRule.addDescOrder("updateDate");
		page = productManageService.searchProduct(queryRule, pageNo, pageSize);
		
		if(page!=null && page.getResult().size()>0){
			for (Object Object : page.getResult()) {
				GeProductMain main = (GeProductMain)Object;
				// 查询是否上线
				queryRule = QueryRule.getInstance();
				queryRule.addEqual("coreProductCode", main.getCoreProductCode());
				List<GeDirectory> list = geDirectoryService.findGeDirectoryByQueryRule(queryRule);
				
				if(! "1".equals(main.getIsOnline())){
					if(list.size()==1){
						if(list.get(0).getIsProductShelf().equals("01")){
							main.setIsOnline("1");
							productManageService.updatePro(main);
						}
					}
				}else{
					if(list.size()==1){
						if(! list.get(0).getIsProductShelf().equals("01")){
							main.setIsOnline("0");
							productManageService.updatePro(main);
						}
					}
				}
			}
		}
		getRequest().setAttribute("totalCount", page.getTotalCount());
		return SUCCESS;
	}
	/**
	 * 查询产品所有页面元素
	 * @return
	 */
	public String findProductWebFlowElement() {
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		List<GePageElementConfig>  productWebFlowElementList = geWebFlowPageService.findGePageElementConfigBycoreProductCode(coreProductCode);
		super.getRequest().setAttribute("productWebFlowElementList", productWebFlowElementList);
		super.getRequest().setAttribute("coreProductCode", coreProductCode);
		return SUCCESS;
	}
	
	// 详细定义
	public String toEditDetail() {
		boolean flag = productManageService.isConfigProductSaleFlow(geProductMain.getCoreProductCode());
		geProductMain = productManageService.searchProductByProductCode(geProductMain.getCoreProductCode());
		
		// 查询得到流程
		QueryRule q = QueryRule.getInstance();
		q.addEqual("geProductMain.coreProductCode", geProductMain.getCoreProductCode());
		List<GeWebFlowPageElement> list = geWebFlowPageElementService.findGeWebFlowPageElementByQueryRule(q);
		getRequest().setAttribute("allElements", list);
		
		// 取合集
		for (int i = 0; i < list.size(); i++) {
			GeWebFlowPageElement el = list.get(i);
			for (int j = 0; j < list.size(); j++) {
				GeWebFlowPageElement el_two = list.get(j);
				if(el.getGePageElementConfig().getElementCode().equals(el_two.getGePageElementConfig().getElementCode())){
					list.remove(i);
					break;
				}
			}
		}

		if (!flag) {
			return NONE;
		} else {
			return SUCCESS;
		}
	}
	
	// 详细定义-基本信息-编辑
	public String toConfigProductDetail() {
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		geProductMain = productManageService.searchProductByProductCode(coreProductCode);
		List<GeCode> periodTypeList = geCodeService.findAllByGeCodeType("PeriodType");//产品推荐评分
		List<GeProductAttrAllowValues> productAttrAllowValueList = geProductMain.getGeProductAttrAllowValueses();
		
		// 排序
		if(productAttrAllowValueList.size()>1)
			productAttrAllowValueList = sortAllowValue(productAttrAllowValueList);
		
		super.getRequest().setAttribute("periodTypeList", periodTypeList);
		super.getRequest().setAttribute("productAttrAllowValueList", productAttrAllowValueList);
		super.getRequest().setAttribute("productAttrAllowValueListSize", productAttrAllowValueList.size());
		return SUCCESS;
	}
	
	//对保额进行排序
	private List<GeProDutyAttrAllowVal> sortGeProDutyAttrAllowVal(List<GeProDutyAttrAllowVal> productAttrAllowValueList){
		List<GeProDutyAttrAllowVal> listY = new ArrayList<GeProDutyAttrAllowVal>();
		List<GeProDutyAttrAllowVal> listYD = new ArrayList<GeProDutyAttrAllowVal>();
		List<GeProDutyAttrAllowVal> listWY = new ArrayList<GeProDutyAttrAllowVal>();
		
		for(GeProDutyAttrAllowVal paav : productAttrAllowValueList){
			if(paav.getAttributeTypeValue().equals("Y")){
				listY.add(paav);
			}else if(paav.getAttributeTypeValue().equals("YD")){
				listYD.add(paav);
			}else{
				listWY.add(paav);
			}
		}
		
		sortForGeProDutyAttrAllowVal(listY);
		sortForGeProDutyAttrAllowVal(listYD);
		sortForGeProDutyAttrAllowVal(listWY);
		
		listY.addAll(listYD);
		listY.addAll(listWY);
		
		return listY;
	}
	
	// 冒泡排序AllowValue
	private static void sortForGeProDutyAttrAllowVal(List<GeProDutyAttrAllowVal> list) {
		GeProDutyAttrAllowVal temp;
		if(list!=null && list.size()>1){
			for (int i = 0; i < list.size(); ++i) {
				for (int j = 0; j < list.size() - i - 1; ++j) {
					if (Double.parseDouble(list.get(j).getAttributeValue()) <  Double.parseDouble(list.get(j+1).getAttributeValue())) {
						temp = list.get(j);
						list.set(j, list.get(j+1));
						list.set(j + 1, temp);
					}
				}
			}
		}
	}
	
	//对保险期间进行排序
	private List<GeProductAttrAllowValues> sortAllowValue(List<GeProductAttrAllowValues> productAttrAllowValueList){
		List<GeProductAttrAllowValues> listD = new ArrayList<GeProductAttrAllowValues>();
		List<GeProductAttrAllowValues> listM = new ArrayList<GeProductAttrAllowValues>();
		List<GeProductAttrAllowValues> listY = new ArrayList<GeProductAttrAllowValues>();
		
		for(GeProductAttrAllowValues paav : productAttrAllowValueList){
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
	private static void sortForAllowValue(List<GeProductAttrAllowValues> list) {
		GeProductAttrAllowValues temp;
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
	
	/***
	 * 详细定义-基本信息-险种配置
	 * @return
	 */
	public String toConfigProductRisk() {
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		String productCompontTree = geProductRiskService.findGeProductRiskTree(coreProductCode);
		System.out.println(productCompontTree);
		super.getRequest().setAttribute("productCompontTree", productCompontTree);
		super.getRequest().setAttribute("productCode", coreProductCode);
		return SUCCESS;
	}
	
	// 详细定义-基本信息-更新
	public String saveOrUpdateProductDetail() {
		try {
			// 校验是否是已发布后，过来编辑的
			valProductStatus(geProductMain);
			
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("geProductMain.coreProductCode", geProductMain.getCoreProductCode());
			queryRule.addEqual("attributeKind", "period");
			List<GeProductAttrAllowValues>  productAttrAllowValueList = geProductAttrAllowValuesService.findPAAValuesList(queryRule);
			geProductAttrAllowValuesService.deletePAAValuesList(productAttrAllowValueList);
			geProductAttrAllowValuesService.savePAAValuesList(geProductMain.getGeProductAttrAllowValueses());
			productManageService.saveDept(geProductMain.getCoreProductCode(),deptidSave);
			productManageService.configProductDetail(geProductMain);
			geWebFlowPageElementService.updateGeWebFlowPageElementStatus(geProductMain.getCoreProductCode(),"productInfo");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String toConfigQuestion() {
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		geProductMain = productManageService.searchProductByProductCode(coreProductCode);
		return SUCCESS;
	}

	// 调查问卷
	public String questionPageDoFileUpload(){
		return SUCCESS;
	}

	public String toConfigProductQuote() {
		geProductMain = productManageService.searchProductByProductCode(geProductMain.getCoreProductCode());
		return SUCCESS;
	}


	// 详细定义-法律声明-编辑
	public String toConfigLegalNotices() {
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		geProductMain = productManageService.searchProductByProductCode(coreProductCode);
		if (geProductMain.getGeProductExtraInfos().size() > 0) {
			if (StringUtils.isNotEmpty(geProductMain.getGeProductExtraInfos()
					.get(0).getLegalNotices())) {
				legalNotice = geProductMain.getGeProductExtraInfos().get(0)
						.getLegalNotices();
			} else {
				legalNotice = "";
			}
		} else {
			legalNotice = "";
		}
		return SUCCESS;
	}

	// 详细定义-法律声明-保存
	public String saveOrUpdateLegalNotice() {
		// 校验是否是已发布后，过来编辑的
		valProductStatus(geProductMain);
		
		productManageService.configLegalNotices(
				geProductMain.getCoreProductCode(), legalNotice);
		geWebFlowPageElementService.updateGeWebFlowPageElementStatus(geProductMain.getCoreProductCode(),"legalNotices");
		
		return SUCCESS;
	}

	// 详细定义-投保告知-编辑
	public String toConfigInformBook() {
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		geProductMain = productManageService.searchProductByProductCode(coreProductCode);
		informBookList = productManageService.searchProductByProductCode(coreProductCode).getGeProductInformBooks();
		return SUCCESS;
	}
	// 保存文本
	@SuppressWarnings({"rawtypes"})
	public String toShowContent() {
		Enumeration parameterNames = super.getRequest().getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = (String)parameterNames.nextElement();
			String parameterValue = super.getRequest().getParameter(parameterName);
			super.getRequest().setAttribute(parameterName, parameterValue);
		}
		return SUCCESS;
	}

	// 详细定义-投保告知-保存
	public String saveOrUpdateConfigInformBook() {
		// 校验是否是已发布后，过来编辑的
		valProductStatus(geProductMain);
		
		List<GeProductInformBook> geProductInformBookList = new ArrayList<GeProductInformBook>();
		GeProductInformBook geProductInformBook = null;
		String[] order = informBook.getInformOrder().split(",");
		String[] content = informBook.getInformContent().split(",");
		String[] option = informBook.getInformOption().split(",");
		for (int i = 1; i < option.length; i++) {
			geProductInformBook = new GeProductInformBook();
			geProductInformBook.setInformOrder(order[i].trim());
			geProductInformBook.setInformContent(content[i].trim());
			geProductInformBook.setInformOption(option[i].trim());
			geProductInformBookList.add(geProductInformBook);
		}
		productManageService.configInformBook(
				geProductMain.getCoreProductCode(), geProductInformBookList);
		geWebFlowPageElementService.updateGeWebFlowPageElementStatus(geProductMain.getCoreProductCode(),"inform");
		return SUCCESS;
	}

	public String toConfigProposalNotices() {
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		geProductMain = productManageService.searchProductByProductCode(coreProductCode);
		if (geProductMain.getGeProductExtraInfos().size() > 0) {
			if (StringUtils.isNotEmpty(geProductMain.getGeProductExtraInfos()
					.get(0).getProposalNotices())) {
				proposalNotice = geProductMain.getGeProductExtraInfos().get(0)
						.getProposalNotices();
			} else {
				proposalNotice = "";
			}
		} else {
			proposalNotice = "";
		}
		return SUCCESS;
	}

	public String saveOrUpdateProposalNotice() {
		// 校验是否是已发布后，过来编辑的
		valProductStatus(geProductMain);
		
		productManageService.configProposalNotices(
				geProductMain.getCoreProductCode(), proposalNotice);
		geWebFlowPageElementService.updateGeWebFlowPageElementStatus(geProductMain.getCoreProductCode(),"proposalNotices");
		return SUCCESS;
	}

	// 详细定义-保单配置-编辑'
	public String toConfigPolicy() throws Exception {
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		geProductMain = productManageService.searchProductByProductCode(coreProductCode);
		return SUCCESS;
	}

	//收件人配置
	public String toConfigAddressee() {
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		geProductMain = productManageService.searchProductByProductCode(coreProductCode);
		geAddresseeConfig = productManageService.searchProductAddresseeConfig(geProductMain.getCoreProductCode());
		return SUCCESS;
	}
	
	public String saveOrUpdateAddresseeConfig(){
		// 校验是否是已发布后，过来编辑的
		valProductStatus(geProductMain);
		productManageService.configPolicy(geProductMain.getCoreProductCode(),
				null, null,null, null, geAddresseeConfig,null);
		geWebFlowPageElementService.updateGeWebFlowPageElementStatus(geProductMain.getCoreProductCode(),"policyConfig");
		return SUCCESS;
	}
	//紧急联系人配置
	public String toConfigEmergency(){
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		geProductMain = productManageService.searchProductByProductCode(coreProductCode);
		geProductEmergencyConfig = productManageService
				.searchProductEmergencyConfig(geProductMain.getCoreProductCode());
		return SUCCESS;
	}
	
	public String saveOrUpdateEmergencyConfig(){
		valProductStatus(geProductMain);
		productManageService.configPolicy(geProductMain.getCoreProductCode(),
				null, null,null, geProductEmergencyConfig, null, null);
		geWebFlowPageElementService.updateGeWebFlowPageElementStatus(geProductMain.getCoreProductCode(),"policyConfig");
		return SUCCESS;
	}
	//投保人配置
	public String toConfigApplicant(){
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		geProductMain = productManageService.searchProductByProductCode(coreProductCode);
		geProductApplicantConfig = productManageService
					.searchProductApplicantConfig(geProductMain.getCoreProductCode());
		String appSexConfigs = geProductApplicantConfig.getAppSexConfig();
		String appIdTypeConfigs = geProductApplicantConfig.getAppIdTypeConfig();
		if (StringUtils.isNotBlank(appSexConfigs)) {
			appSexConfig = trimStr(appSexConfigs.split(",")); 
		}
		if (StringUtils.isNotBlank(appIdTypeConfigs)) {
			appIdTypeConfig = trimStr(appIdTypeConfigs.split(",")); 
		}
		
		//从数据字典读取证件类型编码
		QueryRule queryRule2 = QueryRule.getInstance();
		queryRule2.addEqual("id.codeType", "IdentifyType");
		IdentifyTypeCode = geCodeService.findAll(queryRule2);
		
		//从数据字典读取性别编码
		QueryRule queryRule3 = QueryRule.getInstance();
		queryRule3.addEqual("id.codeType", "Sex");
		SexCode = geCodeService.findAll(queryRule3);
		
		List<GeCode> periodTypeList = geCodeService.findAllByGeCodeType("PeriodType");
		super.getRequest().setAttribute("periodTypeList", periodTypeList);
		return SUCCESS;
	}
	public String saveOrUpdateApplicantConfig(){
		valProductStatus(geProductMain);
		productManageService.configPolicy(geProductMain.getCoreProductCode(),
				geProductApplicantConfig, null,null, null, null,null);
		geWebFlowPageElementService.updateGeWebFlowPageElementStatus(geProductMain.getCoreProductCode(),"policyConfig");
		return SUCCESS;
	}
	
	
	//保单展示配置
	public String toConfigPolicyDisplay(){
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		geProductMain = productManageService.searchProductByProductCode(coreProductCode);
		geProductPolicyDisplayConfig = productManageService.searchProductPolicyDisplayConfig(geProductMain.getCoreProductCode());
		return SUCCESS;
	}
	
	public String saveOrUpdatePolicyDisplayConfig(){
		valProductStatus(geProductMain);
		productManageService.configPolicy(geProductMain.getCoreProductCode(),
				null, null,null, null, null, geProductPolicyDisplayConfig);
		geWebFlowPageElementService.updateGeWebFlowPageElementStatus(geProductMain.getCoreProductCode(),"policyConfig");
		return SUCCESS;
	}
	
	//被保人配置
	public String toConfigInsured() throws Exception{
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		geProductMain = productManageService.searchProductByProductCode(coreProductCode);
		if (productManageService.searchProductInsuredConfig(
				geProductMain.getCoreProductCode()).size() > 0) {
			geProductInsuredConfigList = productManageService
					.searchProductInsuredConfig(geProductMain.getCoreProductCode());
			geProductInsuredConfig = geProductInsuredConfigList.get(0); // 被保人配置
		} else {
			geProductInsuredConfig = new GeProductInsuredConfig();
		}
		String insuredOccupationTree = geProInsuredOccupationService.findGeProInsuredOccupationTree(geProductInsuredConfig);
		System.out.println(insuredOccupationTree);
		String insOccupTypes = geProductInsuredConfig
				.getInsOccupationTypeConfig();
		String insRelaToApps = geProductInsuredConfig
				.getInsRelationToAppConfig();
		String finsRelaToApps = geProductInsuredConfig
				.getFinsRelationToAppConfig();
		String insSexConfigs = geProductInsuredConfig.getInsSexConfig();
		String insIdTypeConfigs = geProductInsuredConfig.getInsIdTypeConfig();
		if (StringUtils.isNotBlank(insOccupTypes)) {
			insOccupType = trimStr(insOccupTypes.split(",")); // 被保人职业类别配置项
		}
		if (StringUtils.isNotBlank(insRelaToApps)) {
			insRelaToApp = trimStr(insRelaToApps.split(",")); // 被保人与投保人关系配置项
		}
		if (StringUtils.isNotBlank(finsRelaToApps)) {
			finsRelaToApp = trimStr(finsRelaToApps.split(",")); // 连带被保人与主被保人关系配置型
		}
		if (StringUtils.isNotBlank(insSexConfigs)) {
			insSexConfig = trimStr(insSexConfigs.split(",")); 
		}
		if (StringUtils.isNotBlank(insIdTypeConfigs)) {
			insIdTypeConfig = trimStr(insIdTypeConfigs.split(",")); 
		}
		
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addIsNull("parentOccupationCode");
		// queryRule.addIsEmpty("parentOccupationCode");
		geOccupations = geOccupationService.getGeOccupations(queryRule); // 职业类型配置
		productCodeType("applicantAndInsured");
		
		//从数据字典读取证件类型编码
		QueryRule queryRule2 = QueryRule.getInstance();
		queryRule2.addEqual("id.codeType", "IdentifyType");
		IdentifyTypeCode = geCodeService.findAll(queryRule2);
		
		//从数据字典读取性别编码
		QueryRule queryRule3 = QueryRule.getInstance();
		queryRule3.addEqual("id.codeType", "Sex");
		SexCode = geCodeService.findAll(queryRule3);
		
		List<GeCode> periodTypeList = geCodeService.findAllByGeCodeType("PeriodType");
		super.getRequest().setAttribute("periodTypeList", periodTypeList);
		super.getRequest().setAttribute("insuredOccupationTree", insuredOccupationTree);
		
		return SUCCESS;
	}
	
	public String saveOrUpdateInsuredConfig(){
		valProductStatus(geProductMain);
		geProductInsuredConfigList = new ArrayList<GeProductInsuredConfig>();
		if (StringUtils.equals("0", geProductInsuredConfig.getIsMoreIns())) {
			geProductInsuredConfigList.add(geProductInsuredConfig);
			geProductInsuredConfigList.add(geProductInsuredConfig);
		} else {
			geProductInsuredConfigList.add(geProductInsuredConfig);
		}
		productManageService.configPolicy(geProductMain.getCoreProductCode(),
				null, geProductInsuredConfigList ,null, null, null, null);
		geWebFlowPageElementService.updateGeWebFlowPageElementStatus(geProductMain.getCoreProductCode(),"policyConfig");
		return SUCCESS;
	}
	
	//受益人配置
	public String toConfigBeneficiary() throws Exception{
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		geProductMain = productManageService.searchProductByProductCode(coreProductCode);
		geProductBeneficiaryConfig = productManageService
			.searchProductBeneficiaryConfig(geProductMain.getCoreProductCode());//受益人
		String benRelaToInss = geProductBeneficiaryConfig
			.getBenRelationToPInsConfig();
		String benGenderConfigs = geProductBeneficiaryConfig.getBenGenderConfig();
		String bendTypeConfigs = geProductBeneficiaryConfig.getBendTypeConfig();
		if (StringUtils.isNotBlank(benRelaToInss)) {
			benRelaToIns = trimStr(benRelaToInss.split(",")); // 受益人与主被保人关系配置项
		}
		if (StringUtils.isNotBlank(benGenderConfigs)) {
			benGenderConfig = trimStr(benGenderConfigs.split(",")); 
		}
		if (StringUtils.isNotBlank(bendTypeConfigs)) {
			bendTypeConfig = trimStr(bendTypeConfigs.split(",")); 
		}
		
		productCodeType("applicantAndInsured");
		
		//从数据字典读取证件类型编码
		QueryRule queryRule2 = QueryRule.getInstance();
		queryRule2.addEqual("id.codeType", "IdentifyType");
		IdentifyTypeCode = geCodeService.findAll(queryRule2);
		
		//从数据字典读取性别编码
		QueryRule queryRule3 = QueryRule.getInstance();
		queryRule3.addEqual("id.codeType", "Sex");
		SexCode = geCodeService.findAll(queryRule3);
		
		return SUCCESS;
	}
	public String saveOrUpdateBeneficiaryConfig(){
		valProductStatus(geProductMain);
		productManageService.configPolicy(geProductMain.getCoreProductCode(),
				null, null,geProductBeneficiaryConfig, null, null, null);
		geWebFlowPageElementService.updateGeWebFlowPageElementStatus(geProductMain.getCoreProductCode(),"policyConfig");
		return SUCCESS;
	}
	
	// 详细定义-总览
	public String toPreview() throws Exception {
		productCodeType("salesProcess");
		geProductMain = productManageService
				.searchProductByProductCode(geProductMain.getCoreProductCode());
		String flows = geProductMain.getProductFlow();
		if (StringUtils.isNotBlank(flows)) {
			flow = flows.split(",");
		}
		return SUCCESS;
	}

	// 去审核
	public String toAudit() throws Exception {
		productCodeType("productStatus");
		geProductMain = productManageService.searchProductByProductCode(geProductMain.getCoreProductCode());
		sort(geProductMain.getGeProductMainProcesses());
		return SUCCESS;
	}

	// 审核和发布****************
	public String updateProductStatus() {
		try {
			// 节点不存在
//			if(! geWorkFlowService.isExistTask(taskID)){
//				message = "该任务已被处理或者不存在！";  
//				return "fail";
//			}
			
			String dotype = getRequest().getParameter("dotype");
			String from = getRequest().getParameter("from");
			
			String status = geProductMain.getProductStatus();
			geProductMain = productManageService.searchProductByProductCode(geProductMain.getCoreProductCode());
			geProductMain.setProductStatus(status);
			
			// 轨迹
			GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
			GeProductMainProcess process = new GeProductMainProcess();
			process.setGeProductMain(geProductMain);
			process.setHandleDate(new Date());
			process.setHandleStatus(geProductMain.getProductStatus());
			process.setOperatorID(geOperator.getOperatorid());
			process.setHandleDept(geOperator.getDeptid());
			process.setOptions(suggestion);//处理意见
			
			if (dotype.equals("1")) { // 通过
				geProductMain.getGeProductMainProcesses().add(process);
				// 调用工作流
				if (taskID != null && !"".equals(taskID)) {
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("productStatus", geProductMain.getProductStatus());
					
					// 产品的销售地区
					List<String> list = geProductProDeptService.findProductSaleDept(geProductMain.getCoreProductCode());
					StringBuffer allCitys = new StringBuffer();
					for (int i = 0; i < list.size(); i++) {
						if(i == list.size() -1)
							allCitys.append(list.get(i));
						else
							allCitys.append(list.get(i)).append(",");
					}
					
//					workFlowServiceImpl.completeTask(taskID, workFlowID,geProductMain, param,allCitys.toString());
				}
				
				if("audit".equals(from)){
					message = "产品审核成功!";
				}else{
					// 推送数据到geSale
					boolean suc = copyData(geProductMain.getCoreProductCode());
					if(suc)
						message = "产品发布成功!";
					else
						message = "-1";
				}
			} else if (dotype.equals("2")) { // 回退到集团（状态改为已定制流程）
				geProductMain.setProductStatus("02");
				process.setHandleStatus(geProductMain.getProductStatus());
				geProductMain.getGeProductMainProcesses().add(process);
				
//				workFlowServiceImpl.rollBackTask(taskID);
				message = "操作成功!";
				
				// 轨迹
				geProductMain.setProductStatus("03");
				process.setHandleStatus("03");
				geProductMain.getGeProductMainProcesses().add(process);
				//#########回退到上一级#########
//				boolean result = workFlowServiceImpl.isFirstTask(taskID);
//				if(result){
//					message = "-2";
//				}else{
//					// 产品的销售地区
//					List<String> list = geProductProDeptService.findProductSaleDept(geProductMain.getCoreProductCode());
//					StringBuffer allCitys = new StringBuffer();
//					for (int i = 0; i < list.size(); i++) {
//						if(i == list.size() -1)
//							allCitys.append(list.get(i));
//						else
//							allCitys.append(list.get(i)).append(",");
//					}
//					Map<String, Object> param = new HashMap<String, Object>();
//					param.put("productStatus", "03");
//					param.put("workFlow_entity", geProductMain);
//					workFlowServiceImpl.rollBackTaskID(taskID,  param, allCitys.toString());
//					message = "操作成功!";
//				}
			}
			
			if("-2".equals(message)){
				message = "第一个节点不能回退!";
				flag = -1;
			}else{
				// 保存状态和轨迹
				productManageService.updatePro(geProductMain);
				flag = 1;
				
				if("-1".equals(message)){
					message = "数据推送到geSale表出现异常!";
					flag = -1;
				}
			}
			
			return "AuditSuccess";
		}catch (ActivitiException e) {
			message = "工作流出现异常："+e.getMessage();  
			e.printStackTrace();
			return "fail";
		} catch (Exception e) {
			e.printStackTrace();
			message = "操作失败!";  
			return "fail";
		}
	}

	public String toPublish() throws Exception {
		productCodeType("productStatus");
		geProductMain = productManageService.searchProductByProductCode(geProductMain.getCoreProductCode());
		sort(geProductMain.getGeProductMainProcesses());
		return SUCCESS;
	}

	// 删除
	public String deleteOneProduct() {
		try {
			// 如果状态是已定义，从工作流中移除
			geProductMain = productManageService.searchProductByProductCode(geProductMain.getCoreProductCode());
//			if("03".equals(geProductMain.getProductStatus()) && taskID!=null && !taskID.equals(""))
//				workFlowServiceImpl.rollBackTask(taskID);
			
			productManageService.deleteProduct(geProductMain.getCoreProductCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 去除数组空格
	public String[] trimStr(String[] strs) {
		String reStr[] = new String[strs.length];
		for (int i = 0; i < strs.length; i++) {
			reStr[i] = strs[i].trim();
		}
		return reStr;
	}
	
	/**
	 * 初始化新建产品责任页面
	 * @return
	 */
	@LocusTrace(setCode = "ProductManageAction_initAddRiskPage")
	public String initAddRiskPage() throws Exception{
		/**查询数据字典的业务领域*/
		try {
			List<GeCode> businessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");
			Map map = (Map) super.getSession().getAttribute("permission");
			String authorityids = (String) map.get("ROLE_ADDDUTYKIND");//拿到该功能的所有机构权限
//			for (int i = businessAreaList.size() -1; i >= 0; i--) {
//				String baStr = businessAreaList.get(i).getId().getCodeCode();
//				if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
//					continue;
//				}else {
//					businessAreaList.remove(i);
//				}
//			}
			super.getRequest().setAttribute("businessAreaList", businessAreaList);
		} catch (BizConfigCommonException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 添加产品责任
	 * @return
	 */
	@LocusTrace(setCode = "ProductManageAction_addGeRiskConfig")
	public String addGeRiskConfig() {
		try {
			GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
			geRiskConfig.setCreateTime(new Date());
			geRiskConfig.setOperatorID(geOperator.getOperatorid());
			List<GeDutyConfig> geDutyConfigList = geRiskConfig.getGeDutyConfigs();
			for (GeDutyConfig geDutyConfig:geDutyConfigList) {
				geDutyConfig.setCreateTime(new Date());
				geDutyConfig.setGeRiskConfig(geRiskConfig);
				geDutyConfig.setOperatorID(geOperator.getOperatorid());
				geDutyConfig.setBusinessArea(geRiskConfig.getBusinessArea());
			}
			geRiskConfigService.addGeRiskConfig(geRiskConfig);
			addResult = "success";
		} catch (Exception e) {
			e.printStackTrace();
			addResult = "failure";
		}
		return SUCCESS;
	}
	
	/**
	 * 查询险种责任
	 * @return
	 */
	@LocusTrace(setCode = "ProductManageAction_initAddDutyKindPage")
	@SuppressWarnings({"rawtypes", "unchecked"})
	public String findGeRiskConfigByRiskCode() {
		String riskCode = super.getRequest().getParameter("riskCode");
		geRiskConfig = geRiskConfigService.findGeRiskConfigByRiskCode(riskCode);
		System.out.println(geRiskConfig.getGeDutyConfigs().size());
		super.getRequest().setAttribute("geRiskConfig", geRiskConfig);
		List businessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");
		List<GeCodeId> idList = ConvertUtils.convertElementPropertyToList(businessAreaList, "id");
		List<String> codeList = ConvertUtils.convertElementPropertyToList(idList, "codeCode");
    	Map map = (Map) super.getSession().getAttribute("permission");
    	//拿到该功能的所有机构权限
		String authorityids = (String) map.get("ROLE_DUTYKINDMAINTAIN");
		
		for (int i = codeList.size() -1; i >= 0; i--) {
			String baStr = codeList.get(i);
			if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
				continue;
			}else {
				codeList.remove(i);
			}
		}
		super.getRequest().setAttribute("businessAreaList", businessAreaList);
		return SUCCESS;
	}
	
	/**
	 * 查询险种责任
	 * @return
	 */
	@LocusTrace(setCode = "ProductManageAction_findGeRiskConfig")
	@SuppressWarnings({"unchecked", "rawtypes"})
	public String findGeRiskConfig() {
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addDescOrder("createTime");
			
			if (StringUtils.isNotBlank(geRiskConfig.getRiskCode())) {
				queryRule.addLike("riskCode","%" + geRiskConfig.getRiskCode() + "%");
			}
			
			if (StringUtils.isNotBlank(geRiskConfig.getRiskName())) {
				queryRule.addLike("riskName","%" + geRiskConfig.getRiskName().replaceAll("\\s", "%") + "%");
			}
			
			if (StringUtils.isNotBlank(geRiskConfig.getCoreRiskCode())) {
				queryRule.addLike("coreRiskCode","%" + geRiskConfig.getCoreRiskCode().replaceAll("\\s", "%") + "%");
			}
			
			List businessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");
			List<GeCodeId> idList = ConvertUtils.convertElementPropertyToList(businessAreaList, "id");
			List<String> codeList = ConvertUtils.convertElementPropertyToList(idList, "codeCode");
	    	Map map = (Map) super.getSession().getAttribute("permission");
			String authorityids = (String) map.get("ROLE_DUTYKINDMAINTAIN");//拿到该功能的所有机构权限
			
			for (int i = codeList.size() -1; i >= 0; i--) {
				String baStr = codeList.get(i);
				if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
					continue;
				}else {
					codeList.remove(i);
				}
			}
			
			if (StringUtils.isNotBlank(geRiskConfig.getBusinessArea())  && codeList.contains(geRiskConfig.getBusinessArea())) {
				queryRule.addEqual("businessArea", geRiskConfig.getBusinessArea());
			}else{
				queryRule.addIn("businessArea", codeList);
			}
			super.getRequest().setAttribute("businessAreaList", businessAreaList);
			pageNo = pageNo == 0 ? 1 : pageNo;
			pageSize = pageSize == 0 ? 10 : pageSize;
			
			if (codeList != null && codeList.size() >0) {
				page = geRiskConfigService.findGeRiskConfig(queryRule, pageNo, pageSize);
			}else {
				page = new Page(pageNo, 0, pageSize, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 更新险种责任
	 * @return
	 */
	@LocusTrace(setCode = "ProductManageAction_updateGeRiskConfig")
	public String updateGeRiskConfig() {
		try {
			GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
			geRiskConfig.setUpdateTime(new Date());
			geRiskConfig.setOperatorID(geOperator.getOperatorid());
			geRiskConfigService.updateGeRiskConfig(geRiskConfig);
			super.getRequest().setAttribute("flag", "1");
			super.getRequest().setAttribute("message", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("flag", "0");
			super.getRequest().setAttribute("message", "操作失败！");
		}
		return SUCCESS;
	}
	
	/***
	 * 判断险种责任代码是否已经被使用
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_isHaveGeRiskConfig")
	public void isHaveGeRiskConfig(){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String riskCode = super.getRequest().getParameter("riskCode");
			GeRiskConfig geRiskConfig = geRiskConfigService.findGeRiskConfigByRiskCode(riskCode);
			if (geRiskConfig == null) {
				resultMap.put("resultFlag", "error");
			} else {
				resultMap.put("resultFlag", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
		
	}
	
	/***
	 * 根据枚举值类型和菜单编码查询，查询当前拥有拥有的枚举值
	 * 
	 */
	public  void findDataFromDic() {
		String codeType = super.getRequest().getParameter("codeType");
		String menuCode = super.getRequest().getParameter("menuCode");
		List<GeCode> codeList = geCodeService.findAllByGeCodeType(codeType);
		if (StringUtils.isNotBlank(menuCode)) {
			Map map = (Map) super.getSession().getAttribute("permission");
			String authorityids = (String) map.get(menuCode);//拿到该功能的所有机构权限
			for (int i = codeList.size() -1; i >= 0; i--) {
				String baStr = codeList.get(i).getId().getCodeCode();
				if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
					continue;
				} else {
					codeList.remove(i);
				}
			}
		}
		
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < codeList.size(); i++) {
			Map<String, String> keyValueMap = new HashMap<String, String>();
			GeCode geCode = codeList.get(i);
			keyValueMap.put("codeCode", geCode.getId().getCodeCode());
			keyValueMap.put("codeName", geCode.getCodeCName());
			mapList.add(keyValueMap);
		}
		Map jsonMap = new HashMap();
		JSONObject jsonObject = new JSONObject();
		jsonMap.put("mapList", mapList);
		jsonObject.putAll(jsonMap);
		renderText(jsonObject.toString());
	}
	// 产品状态
	public void productCodeType(String codeType) throws Exception {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id.codeType", codeType);
		queryRule.addEqual("validInd", "1");
		code = geCodeService.findAll(queryRule);
	}
	
	public void authoritySetDeptAuthTree(){
		String treeID = "0";
		String coreProductCode = super.getRequest().getParameter("coreProductCode")== null?"":super.getRequest().getParameter("coreProductCode").trim();
		String businessArea = productManageService.findProductMainByCoreProductCode(coreProductCode).getBusinessArea();
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setContentType("text/xml; charset=GBK");  
	    response.setHeader("Cache-Control", "no-cache");  
	    PrintWriter pw = null;  
	    
		Map map = (Map) super.getSession().getAttribute("permission");
		String operatorDeptAuths = (String) map.get("ROLE_B_CRPR_X");
		List productOldDepts = geProductProDeptService.findProductSaleDept(coreProductCode);
		String temp = geProductProDeptService.getAllVirtualParent(operatorDeptAuths);
		operatorDeptAuths += "," + temp;
		
		Map orgMap=(Map)super.getServletContext().getAttribute("orgTreeData");
		
		StringBuffer str = new StringBuffer();
		str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		try {
			str.append("<tree id=\"" + treeID + "\">");
			authoritySetDeptAuthItem(str,treeID, operatorDeptAuths, productOldDepts,orgMap,businessArea);
			str.append("</tree>");
			pw = response.getWriter();  
		    pw.write(str.toString());  
		    pw.flush();  
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			  pw.close();
		}
	}  
	
	private void authoritySetDeptAuthItem(StringBuffer str,String treeID,String operatorDeptAuths,List productOldDepts,Map orgMap,String businessArea){
		List<GeDepartment> list =(List<GeDepartment>) orgMap.get(treeID);
		if(list != null && list.size() > 0){
			for (int i=0; i < list.size(); i++) {
				GeDepartment geDepartment = (GeDepartment)list.get(i);
				String deptIdString = geDepartment.getDeptid();
				/**判断是否选中*/
				String checked = "";
				if(productOldDepts != null){
					if(productOldDepts.contains(deptIdString)) checked ="checked=\"1\"";
				}
				if(orgMap.containsKey(deptIdString)){
					if((","+operatorDeptAuths+",").indexOf(",parent_"+deptIdString+",") == -1)continue;/**判断是否拥有该机构权限*/
					
					if("0".equals(treeID)){
						str.append("<item id=\"parent_" + geDepartment.getDeptid() + "\" open=\"1\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
					}else{
						if ("1".equals(businessArea) || "1".equals(geDepartment.getBusinessarea()) || geDepartment.getBusinessarea().equals(businessArea)) {
							str.append("<item id=\"parent_" + geDepartment.getDeptid() + "\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
						}
					}
					if ("1".equals(businessArea) || "1".equals(geDepartment.getBusinessarea()) || geDepartment.getBusinessarea().equals(businessArea)) {
						authoritySetDeptAuthItem(str,geDepartment.getDeptid(),operatorDeptAuths,productOldDepts,orgMap,businessArea);
					}
				} else {
					if((","+operatorDeptAuths+",").indexOf(","+deptIdString+",") == -1)continue;/**判断是否拥有该机构权限*/
					
					if ("1".equals(businessArea) || "1".equals(geDepartment.getBusinessarea()) || geDepartment.getBusinessarea().equals(businessArea)) {
						str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"0\" "+checked+" text=\"" + geDepartment.getDeptname() + "\">");
					}
					
				}
				if ("1".equals(businessArea) || "1".equals(geDepartment.getBusinessarea()) || geDepartment.getBusinessarea().equals(businessArea)) {
					str.append("</item>");
				}
			}
			
			/**虚拟本级节点*/
			if(!"0".equals(treeID) && (","+operatorDeptAuths+",").indexOf(","+treeID+",") != -1){
				String checked = "";/**判断是否选中*/
				if(productOldDepts != null){
					if(productOldDepts.contains(treeID)) checked ="checked=\"1\"";
				}
				//屏蔽了集团“本级”节点
				if(!"1".equals(treeID)){
				str.append("<item id=\"" + treeID + "\" child=\"0\" "+checked+"  text=\"本级\"></item>");
				}
			}
		}
	}
	
	public String createPayment(){
		try {
			gePayment.setCreateTime(new Date());
			gePaymentService.createPayment(gePayment);
			
			// 入中间表
			List<GePaymentCity> list = new ArrayList<GePaymentCity>();
			if(StringUtils.isNotBlank(deptidSave)){
				String[] citys = deptidSave.split(",");
				for (String city : citys) {
					GePaymentCity gePaymentCity = new GePaymentCity();
					GePaymentCityId gePaymentCityId = new GePaymentCityId();
					gePaymentCityId.setCityId(city);
					gePaymentCityId.setPaymentId(gePayment.getPaymentId());
					
					gePaymentCity.setId(gePaymentCityId);
					list.add(gePaymentCity);
				}
			}
			
			if(logoImg != null){
				String logoImgfileName = gePayment.getPaymentId() + "_BankLogo" + FileUtils.getFileNameExt(logoImgFileName);
				File logoImgPictureFile = new File(imageAbsolutePath + logoImgfileName);
				FileUtils.write(FileUtils.readBytes(logoImg), logoImgPictureFile);
				gePayment.setLogoImg(imagePath + logoImgfileName);
			}
			
			gePayment.setGePaymentCities(list);
			gePaymentService.updatePayment(gePayment);
			super.getRequest().setAttribute("addReslut", "success");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("addReslut", "failure");
		}
		return SUCCESS;
	}
	
	public void isHavePayment(){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String paymentCode = super.getRequest().getParameter("paymentCode");
			int count = gePaymentService.findCountByPaymentCode(paymentCode);
			if (count <=0) {
				resultMap.put("resultFlag", "error");
			} else {
				resultMap.put("resultFlag", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
	}
	
	public String findPayment(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			
			// 添加机构权限条件
			List<String> payMentIds = new ArrayList<String>();
			Map map = (Map) super.getSession().getAttribute("permission");
			String authorityid = (String) map.get("ROLE_PAYMENTMAINTAIN");
			if(StringUtils.isNotBlank(authorityid)){
				List<String> values = Arrays.asList(authorityid.split(","));
				
				String[] authorityIdAll = authorityid.split(",");
				if(values.size() > 0){
					String sql="(c.cityid in";
					if(values!=null && authorityIdAll.length > 0){
						int authorityIdSize = authorityIdAll.length;
						int authorityIdNumber = (authorityIdSize-1)/1000+1;
						String nextParentIdString = "";
						for(int j = 0; j < authorityIdNumber; j++){
							if(nextParentIdString.length()>0){
								 nextParentIdString = "";
								sql+="or c.cityid in";
							}
							int loopNum = 1000;
							if(j == authorityIdNumber-1){
								loopNum = authorityIdSize - (authorityIdNumber-1)*1000;
							}
							for (int i = 0; i < loopNum; i++) {
								String childDeptId =(String)authorityIdAll[1000*j+i];
								if(i == 0){
									nextParentIdString += "'"+childDeptId+"'";
								}else{
									nextParentIdString += ",'"+childDeptId+"'";
								}
							}
							sql += "("+nextParentIdString+")";
						}
						sql+=")";
					}
					
					// 添加权限条件（in payMentId）
					payMentIds = gePaymentService.getPayMentIdsByCity(sql);
					if(payMentIds!=null && payMentIds.size()>0){
						StringBuffer bf = new StringBuffer("paymentId in( ");
						for (int i = 0; i < payMentIds.size(); i++) {
							if(i!=payMentIds.size()-1)
								bf.append("'").append(payMentIds.get(i)).append("'").append(",");
							else
								bf.append("'").append(payMentIds.get(i)).append("'");
						}
						bf.append(")");
						queryRule.addSql(bf.toString());
					}
				}
			}

			queryRule.addDescOrder("createTime");
			if (StringUtils.isNotBlank(gePayment.getPaymentCode())) {
				queryRule.addLike("paymentCode","%" + gePayment.getPaymentCode().replaceAll("\\s", "%") + "%");
			}
			if (StringUtils.isNotBlank(gePayment.getPaymentName())) {
				queryRule.addLike("paymentName","%" + gePayment.getPaymentName().replaceAll("\\s", "%") + "%");
			}
			
			pageNo = pageNo == 0 ? 1 : pageNo;
			pageSize = pageSize == 0 ? 10 : pageSize;
			page = gePaymentService.findPayment(queryRule, pageNo, pageSize);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String findPaymentDetailByPaymentCode(){
		String paymentId = super.getRequest().getParameter("paymentId");
		gePayment = gePaymentService.findPaymentById(paymentId);
		super.getRequest().setAttribute("paymentId", paymentId);
		super.getRequest().setAttribute("gePayment", gePayment);
		return SUCCESS;
	}
	
	public String delPaymentByPaymentCode(){
		String paymentId = super.getRequest().getParameter("paymentId");
		try {
			gePaymentService.delPaymentByPaymentId(paymentId);
			flag=1;
			message= "删除成功！";
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message="操作失败！";
			return ERROR;
		}
	}
	public String updateGePayment() {
		try {
			GePayment del = gePaymentService.findPaymentById(this.gePayment.getPaymentId());
			
			if(logoImg != null){
				String logoImgfileName = gePayment.getPaymentId() + "_BankLogo" + FileUtils.getFileNameExt(logoImgFileName);
				File logoImgPictureFile = new File(imageAbsolutePath + logoImgfileName);
				FileUtils.write(FileUtils.readBytes(logoImg), logoImgPictureFile);
				gePayment.setLogoImg(imagePath + logoImgfileName);
			}
			gePayment.setUpdateTime(new Date());

			// 先删除城市
			gePaymentService.clearByPayMentId(gePayment.getPaymentId());
			
			// 入中间表
			List<GePaymentCity> list = new ArrayList<GePaymentCity>();
			if(StringUtils.isNotBlank(deptidSave)){
				String[] citys = deptidSave.split(",");
				for (String city : citys) {
					GePaymentCity gePaymentCity = new GePaymentCity();
					GePaymentCityId gePaymentCityId = new GePaymentCityId();
					gePaymentCityId.setCityId(city);
					gePaymentCityId.setPaymentId(gePayment.getPaymentId());
					
					gePaymentCity.setId(gePaymentCityId);
					list.add(gePaymentCity);
				}
			}
			
			gePayment.setGePaymentCities(list);
			
			gePaymentService.updatePayment(gePayment);
			super.getRequest().setAttribute("flag", "1");
			super.getRequest().setAttribute("message", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("flag", "0");
			super.getRequest().setAttribute("message", "操作失败！");
		}
		return SUCCESS;
	}
	//新建支付方式/编辑支付方式树####
	public void paymentAreaTree(){
		String treeID = "0";
		//String coreProductCode = super.getRequest().getParameter("coreProductCode")== null?"":super.getRequest().getParameter("coreProductCode").trim();
		String paymentId = super.getRequest().getParameter("paymentId")== null?"":super.getRequest().getParameter("paymentId").trim();
		this.gePayment = this.gePaymentService.findPaymentById(paymentId);
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setContentType("text/xml; charset=GBK");  
	    response.setHeader("Cache-Control", "no-cache");  
	    PrintWriter pw = null;  
	   
//	    String paymentOldDepts = "";
//	    if(paymentCode.length()!=0){
//	    	paymentOldDepts = gePaymentService.findPaymentByPaymentCode(paymentCode).getUseRegion();
//	    }
	    
	    Map map = (Map) super.getSession().getAttribute("permission");
		String operatorDeptAuths = (String) map.get("ROLE_PYAMENT_EDIT");
		
		String temp = geProductProDeptService.getAllVirtualParent(operatorDeptAuths);
		operatorDeptAuths += "," + temp;
	    
		//List productOldDepts = geProductProDeptService.findProductSaleDept(coreProductCode);
		Map orgMap=(Map)super.getServletContext().getAttribute("orgTreeData");
		
		List<String> curr = new ArrayList<String>();
		if(this.gePayment != null)
		for (GePaymentCity gePaymentCity : this.gePayment.getGePaymentCities()) {
			curr.add(gePaymentCity.getId().getCityId());
		}
		
		StringBuffer str = new StringBuffer();
		str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		try {
			str.append("<tree id=\"" + treeID + "\">");
			paymentAreaTreeItem(str,treeID, operatorDeptAuths,orgMap,curr);
			str.append("</tree>");
			pw = response.getWriter();  
		    pw.write(str.toString());  
		    pw.flush();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			  pw.close();
		}
	}
	
	private void paymentAreaTreeItem(StringBuffer str,String treeID, String paymentOldDepts,Map orgMap,List<String> curr){
		
		List<GeDepartment> list =(List<GeDepartment>) orgMap.get(treeID);
		if(list != null && list.size() > 0){
			for (int i=0; i < list.size(); i++) {
				GeDepartment geDepartment = (GeDepartment)list.get(i);
				String deptIdString = geDepartment.getDeptid();
				
				/**判断是否选中*/
				String checked = "";
				if(curr != null && curr.size()>0){
					if(curr.contains(deptIdString)){
						checked ="checked=\"1\"";
					}
				}
				if(orgMap.containsKey(deptIdString)){
					if((","+paymentOldDepts+",").indexOf(",parent_"+geDepartment.getDeptid()+",") == -1)continue;/**判断是否拥有该机构权限*/
					
					if("0".equals(treeID)){
						str.append("<item id=\"parent_" + geDepartment.getDeptid() + "\" open=\"1\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
					}else{
						str.append("<item id=\"parent_" + geDepartment.getDeptid() + "\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
					}
					paymentAreaTreeItem(str,geDepartment.getDeptid(),paymentOldDepts,orgMap,curr);
				}else{
					if((","+paymentOldDepts+",").indexOf(","+geDepartment.getDeptid()+",") == -1)continue;/**判断是否拥有该机构权限*/
					str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"0\" "+checked+" text=\"" + geDepartment.getDeptname() + "\">");
				}
				str.append("</item>");
			}
			
			/**虚拟本级节点*/
			if(!"0".equals(treeID)){
				String checked = "";/**判断是否选中*/
				if(curr != null){
					if(curr.contains(treeID)) checked ="checked=\"1\"";
				}
				
				//str.append("<item id=\"" + treeID + "\" child=\"0\" "+checked+"  text=\"本级\"></item>");
			}
		}
	}
	//支付方式详细树
	public void paymentAreaDetailTree(){
		String treeID = "0";
		String paymentId = super.getRequest().getParameter("paymentId")== null?"":super.getRequest().getParameter("paymentId").trim();
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setContentType("text/xml; charset=GBK");  
	    response.setHeader("Cache-Control", "no-cache");  
	    PrintWriter pw = null;  
	    
	    // 得到城市
	    StringBuffer bf = new StringBuffer();
	    List<GePaymentCity> list = gePaymentService.findPaymentById(paymentId).getGePaymentCities();
	    for (int i = 0; i < list.size(); i++) {
			if(i!=list.size()-1)
				bf.append(list.get(i).getId().getCityId()).append(",");
			else
				bf.append(list.get(i).getId().getCityId());
		}
	    
	    String paymentOldDepts = bf.toString();
	    if(StringUtils.isNotBlank(paymentOldDepts)){
	    	String temp = geProductProDeptService.getAllVirtualParent(paymentOldDepts);
		    paymentOldDepts += "," + temp;
	    }
		
		Map orgMap=(Map)super.getServletContext().getAttribute("orgTreeData");
		StringBuffer str = new StringBuffer();
		str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		try {
			str.append("<tree id=\"" + treeID + "\">");
			paymentAreaDetailTreeItem(str,treeID, paymentOldDepts,orgMap);
			str.append("</tree>");
			pw = response.getWriter();  
		    pw.write(str.toString());  
		    pw.flush();  
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			  pw.close();
		}
	}  
	
	private void paymentAreaDetailTreeItem(StringBuffer str,String treeID, String paymentOldDepts,Map orgMap){
		List<GeDepartment> list =(List<GeDepartment>) orgMap.get(treeID);
		if(list != null && list.size() > 0){
			for (int i=0; i < list.size(); i++) {
				GeDepartment geDepartment = (GeDepartment)list.get(i);
				String deptIdString = geDepartment.getDeptid();
				
				if(orgMap.containsKey(deptIdString)){
					if((","+paymentOldDepts+",").indexOf(",parent_"+geDepartment.getDeptid()+",") == -1)continue;/**判断是否拥有该机构权限*/
					if("0".equals(treeID)){
						str.append("<item id=\"" + geDepartment.getDeptid() + "\" open=\"1\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
					}else{
						str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
					}
					paymentAreaDetailTreeItem(str,geDepartment.getDeptid(),paymentOldDepts,orgMap);
				}else{
					if((","+paymentOldDepts+",").indexOf(","+geDepartment.getDeptid()+",") == -1)continue;/**判断是否拥有该机构权限*/
					str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"0\" text=\"" + geDepartment.getDeptname() + "\">");
				}
				str.append("</item>");
			}
			
			/**虚拟本级节点*/
			if(!"0".equals(treeID) && (","+paymentOldDepts+",").indexOf(","+treeID+",") != -1){
				str.append("<item id=\"" + treeID + "\" child=\"0\" text=\"本级\"></item>");
			}
		}
	}
	
	// 上传保费试算
	public String pageElementFileUpload() {

		try {
			// 如果是空文件，返回页面提示
			if(productUploadFile.length()==0 || productUploadFile.length()==-1){
				getRequest().setAttribute("message", "nullFile");
				return SUCCESS;
			}
			
			String coreProductCode = super.getRequest().getParameter("coreProductCode");
			String elementCode = super.getRequest().getParameter("elementCode");
			// 校验是否是已发布后，过来编辑的
			valProductStatus(coreProductCode);
			String pageElementFileDirPath = pageElementFileUpdatePath + "/" + coreProductCode;
			File pageElementFileDir = new File(pageElementFileDirPath);
			if (!pageElementFileDir.exists()) {
				pageElementFileDir.mkdirs();
			}
		
			File pageElementFile = new File(pageElementFileDirPath + "/" + coreProductCode + "_" + elementCode + FileUtils.getFileNameExt(productUploadFileFileName));
			if (productUploadFile != null) {
				FileUtils.write(FileUtils.readBytes(productUploadFile), pageElementFile);
				super.getRequest().setAttribute("message", "success");	
				geWebFlowPageElementService.updateGeWebFlowPageElementStatus(coreProductCode,elementCode);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("message", "failure");
		}
		return SUCCESS;
	}
	

	// 根据时间冒泡排序轨迹
	public static void sort(List<GeProductMainProcess> list) {
		GeProductMainProcess temp;
		if(list!=null && list.size()>1){
			for (int i = 0; i < list.size(); ++i) {
				for (int j = 0; j < list.size() - i - 1; ++j) {
					if (list.get(j).getHandleDate().after(list.get(j+1).getHandleDate())) {
						temp = list.get(j);
						list.set(j, list.get(j+1));
						list.set(j + 1, temp);
					}
				}
			}
		}
	}
	
	public void setProductManageService(ProductManageService productManageService) {
		this.productManageService = productManageService;
	}

	public GeProductMain getGeProductMain() {
		return geProductMain;
	}

	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setDoc(File file) {
		this.doc = file;
	}

	public void setDocFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getFlow() {
		return flow;
	}

	public void setFlow(String[] flow) {
		this.flow = flow;
	}

	public String getLegalNotice() {
		return legalNotice;
	}

	public void setLegalNotice(String legalNotice) {
		this.legalNotice = legalNotice;
	}

	public String getProposalNotice() {
		return proposalNotice;
	}

	public void setProposalNotice(String proposalNotice) {
		this.proposalNotice = proposalNotice;
	}

	public GeProductApplicantConfig getGeProductApplicantConfig() {
		return geProductApplicantConfig;
	}

	public void setGeProductApplicantConfig(
			GeProductApplicantConfig geProductApplicantConfig) {
		this.geProductApplicantConfig = geProductApplicantConfig;
	}

	public GeProductPolicyDisplayConfig getGeProductPolicyDisplayConfig() {
		return geProductPolicyDisplayConfig;
	}

	public void setGeProductPolicyDisplayConfig(
			GeProductPolicyDisplayConfig geProductPolicyDisplayConfig) {
		this.geProductPolicyDisplayConfig = geProductPolicyDisplayConfig;
	}

	public GeProductInsuredConfig getGeProductInsuredConfig() {
		return geProductInsuredConfig;
	}

	public void setGeProductInsuredConfig(
			GeProductInsuredConfig geProductInsuredConfig) {
		this.geProductInsuredConfig = geProductInsuredConfig;
	}

	public List<GeProductInsuredConfig> getGeProductInsuredConfigList() {
		return geProductInsuredConfigList;
	}

	public void setGeProductInsuredConfigList(
			List<GeProductInsuredConfig> geProductInsuredConfigList) {
		this.geProductInsuredConfigList = geProductInsuredConfigList;
	}

	public GeProductBeneficiaryConfig getGeProductBeneficiaryConfig() {
		return geProductBeneficiaryConfig;
	}

	public void setGeProductBeneficiaryConfig(
			GeProductBeneficiaryConfig geProductBeneficiaryConfig) {
		this.geProductBeneficiaryConfig = geProductBeneficiaryConfig;
	}

	public GeProductInformBook getInformBook() {
		return informBook;
	}

	public void setInformBook(GeProductInformBook informBook) {
		this.informBook = informBook;
	}

	public List<GeProductInformBook> getInformBookList() {
		return informBookList;
	}

	public void setInformBookList(List<GeProductInformBook> informBookList) {
		this.informBookList = informBookList;
	}

	public String[] getInsOccupType() {
		return insOccupType;
	}

	public void setInsOccupType(String[] insOccupType) {
		this.insOccupType = insOccupType;
	}

	public String[] getInsRelaToApp() {
		return insRelaToApp;
	}

	public void setInsRelaToApp(String[] insRelaToApp) {
		this.insRelaToApp = insRelaToApp;
	}

	public String[] getFinsRelaToApp() {
		return finsRelaToApp;
	}

	public void setFinsRelaToApp(String[] finsRelaToApp) {
		this.finsRelaToApp = finsRelaToApp;
	}

	public String[] getBenRelaToIns() {
		return benRelaToIns;
	}

	public void setBenRelaToIns(String[] benRelaToIns) {
		this.benRelaToIns = benRelaToIns;
	}

	public GeProductEmergencyConfig getGeProductEmergencyConfig() {
		return geProductEmergencyConfig;
	}

	public void setGeProductEmergencyConfig(GeProductEmergencyConfig geProductEmergencyConfig) {
		this.geProductEmergencyConfig = geProductEmergencyConfig;
	}

	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}

	public List<GeCode> getCode() {
		return code;
	}

	public void setCode(List<GeCode> code) {
		this.code = code;
	}

	public void setGeOccupationService(GeOccupationService geOccupationService) {
		this.geOccupationService = geOccupationService;
	}

	public List<GeOccupation> getGeOccupations() {
		return geOccupations;
	}

	public void setGeOccupations(List<GeOccupation> geOccupations) {
		this.geOccupations = geOccupations;
	}

	public List<GeCode> getBusinessAreaList() {
		return businessAreaList;
	}

	public void setBusinessAreaList(List<GeCode> businessAreaList) {
		this.businessAreaList = businessAreaList;
	}


	public GeFlowConfigService getGeFlowConfigService() {
		return geFlowConfigService;
	}

	public void setGeFlowConfigService(GeFlowConfigService geFlowConfigService) {
		this.geFlowConfigService = geFlowConfigService;
	}
	
	public List<GeCode> getSexCode() {
		return SexCode;
	}

	public void setSexCode(List<GeCode> sexCode) {
		SexCode = sexCode;
	}

	public List<GeCode> getIdentifyTypeCode() {
		return IdentifyTypeCode;
	}

	public void setIdentifyTypeCode(List<GeCode> identifyTypeCode) {
		IdentifyTypeCode = identifyTypeCode;
	}
	

	public GePayment getGePayment() {
		return gePayment;
	}

	public void setGePayment(GePayment gePayment) {
		this.gePayment = gePayment;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getInsSexConfig() {
		return insSexConfig;
	}

	public void setInsSexConfig(String[] insSexConfig) {
		this.insSexConfig = insSexConfig;
	}

	public String[] getAppSexConfig() {
		return appSexConfig;
	}

	public void setAppSexConfig(String[] appSexConfig) {
		this.appSexConfig = appSexConfig;
	}

	public String[] getBenGenderConfig() {
		return benGenderConfig;
	}

	public void setBenGenderConfig(String[] benGenderConfig) {
		this.benGenderConfig = benGenderConfig;
	}

	public String[] getInsIdTypeConfig() {
		return insIdTypeConfig;
	}

	public void setInsIdTypeConfig(String[] insIdTypeConfig) {
		this.insIdTypeConfig = insIdTypeConfig;
	}

	public String[] getAppIdTypeConfig() {
		return appIdTypeConfig;
	}

	public void setAppIdTypeConfig(String[] appIdTypeConfig) {
		this.appIdTypeConfig = appIdTypeConfig;
	}

	public String[] getBendTypeConfig() {
		return bendTypeConfig;
	}

	public void setBendTypeConfig(String[] bendTypeConfig) {
		this.bendTypeConfig = bendTypeConfig;
	}

	public String getDeptidSave() {
		return deptidSave;
	}

	public void setDeptidSave(String deptidSave) {
		this.deptidSave = deptidSave;
	}

	public File getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(File logoImg) {
		this.logoImg = logoImg;
	}

	public String getLogoImgFileName() {
		return logoImgFileName;
	}

	public void setLogoImgFileName(String logoImgFileName) {
		this.logoImgFileName = logoImgFileName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getCheckFlows() {
		return checkFlows;
	}

	public void setCheckFlows(String checkFlows) {
		this.checkFlows = checkFlows;
	}

	public File getDoc() {
		return doc;
	}

	public File getProductUploadFile() {
		return productUploadFile;
	}

	public void setProductUploadFile(File productUploadFile) {
		this.productUploadFile = productUploadFile;
	}

	public String getProductUploadFileFileName() {
		return productUploadFileFileName;
	}

	public void setProductUploadFileFileName(String productUploadFileFileName) {
		this.productUploadFileFileName = productUploadFileFileName;
	}
	
	public GeRiskConfig getGeRiskConfig() {
		return geRiskConfig;
	}

	public void setGeRiskConfig(GeRiskConfig geRiskConfig) {
		this.geRiskConfig = geRiskConfig;
	}

	public List<GeCode> getAttributeTypeValueCode() {
		return AttributeTypeValueCode;
	}

	public void setAttributeTypeValueCode(List<GeCode> attributeTypeValueCode) {
		AttributeTypeValueCode = attributeTypeValueCode;
	}
	
	
	public List<GeProductAttrAllowValues> getProductAttrAllowValueList() {
		return productAttrAllowValueList;
	}

	public void setProductAttrAllowValueList(
			List<GeProductAttrAllowValues> productAttrAllowValueList) {
		this.productAttrAllowValueList = productAttrAllowValueList;
	}

	public String getAllRisk() {
		return allRisk;
	}

	public void setAllRisk(String allRisk) {
		this.allRisk = allRisk;
	}

	public String getAllDuty() {
		return allDuty;
	}

	public void setAllDuty(String allDuty) {
		this.allDuty = allDuty;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public GeProductDuty getGeProductDuty() {
		return geProductDuty;
	}
	
	public void setGeProductDuty(GeProductDuty geProductDuty) {
		this.geProductDuty = geProductDuty;
	}
	
	public GeProductRisk getGeProductRisk() {
		return geProductRisk;
	}
	
	public void setGeProductRisk(GeProductRisk geProductRisk) {
		this.geProductRisk = geProductRisk;
	}
	
	public GeAddresseeConfig getGeAddresseeConfig() {
		return geAddresseeConfig;
	}

	public void setGeAddresseeConfig(GeAddresseeConfig geAddresseeConfig) {
		this.geAddresseeConfig = geAddresseeConfig;
	}

	public GeProductAttrAllowValues getGeProductAttrAllowValues() {
		return geProductAttrAllowValues;
	}

	public void setGeProductAttrAllowValues(GeProductAttrAllowValues geProductAttrAllowValues) {
		this.geProductAttrAllowValues = geProductAttrAllowValues;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getWorkFlowID() {
		return workFlowID;
	}

	public void setWorkFlowID(String workFlowID) {
		this.workFlowID = workFlowID;
	}
	
	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getAddResult() {
		return addResult;
	}

	public void setAddResult(String addResult) {
		this.addResult = addResult;
	}
	
	

}

