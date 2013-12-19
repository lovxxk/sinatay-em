package cn.com.sinosoft.ebusiness.sale.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.marketing.domain.GeActivitiesRule;
import cn.com.sinosoft.ebusiness.marketing.domain.GeAddserviceConditionDto;
import cn.com.sinosoft.ebusiness.marketing.service.facade.AddServiceActivityService;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectory;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectoryAttributeInfo;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectoryAttributeRelate;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryAttributeInfoService;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryAttributeRelateService;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryService;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeProductCorrelationService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductMain;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.ProductManageService;
import cn.com.sinosoft.ebusiness.sale.web.formBean.ProductDisplay;
import cn.com.sinosoft.util.encode.JsonBinder;
import cn.com.sinosoft.util.json.JSONValueProcessorImpl;

public class ProductDisplayAction extends Struts2Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2156785553645263348L;

	private GeDirectoryAttributeInfoService geDirectoryAttributeInfoService;

	private GeDirectoryService geDirectoryService;

	private GeDirectoryAttributeRelateService geDirectoryAttributeRelateService;

	private ProductManageService productManageService;

	private GeDirectory geDirectory;

	private GeDirectoryAttributeInfo geDirectoryAttributeInfo;
	
	private GeDirectoryAttributeRelate geDirectoryAttributeRelate;
	
	@Autowired
	private GeProductCorrelationService geProductCorrelationService;
	
	private AddServiceActivityService addServiceActivityService;//打折service

	
	/**记录的总条数*/
	private int totalCount;
	
	/**记录的总页数*/
	private int totalPage;
	
	public List<GeDirectory> productDirectoryList;

	private ArrayList<GeProductMain> productMains; // 根据业务领域取得产品信息

	private Page page;
	
	private GeCodeService geCodeService;

	/**
	 * json格式化配置
	 */
	private JsonConfig jc ;

	public AddServiceActivityService getAddServiceActivityService() {
		return addServiceActivityService;
	}

	public void setAddServiceActivityService(
			AddServiceActivityService addServiceActivityService) {
		this.addServiceActivityService = addServiceActivityService;
	}
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public GeCodeService getGeCodeService() {
		return geCodeService;
	}

	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}

	public GeDirectoryAttributeRelate getGeDirectoryAttributeRelate() {
		return geDirectoryAttributeRelate;
	}

	public void setGeDirectoryAttributeRelate(GeDirectoryAttributeRelate geDirectoryAttributeRelate) {
		this.geDirectoryAttributeRelate = geDirectoryAttributeRelate;
	}

	public GeDirectoryAttributeInfoService getGeDirectoryAttributeInfoService() {
		return geDirectoryAttributeInfoService;
	}

	public void setGeDirectoryAttributeInfoService(GeDirectoryAttributeInfoService geDirectoryAttributeInfoService) {
		this.geDirectoryAttributeInfoService = geDirectoryAttributeInfoService;
	}

	public GeDirectoryService getGeDirectoryService() {
		return geDirectoryService;
	}

	public void setGeDirectoryService(GeDirectoryService geDirectoryService) {
		this.geDirectoryService = geDirectoryService;
	}

	public GeDirectoryAttributeRelateService getGeDirectoryAttributeRelateService() {
		return geDirectoryAttributeRelateService;
	}

	public void setGeDirectoryAttributeRelateService(GeDirectoryAttributeRelateService geDirectoryAttributeRelateService) {
		this.geDirectoryAttributeRelateService = geDirectoryAttributeRelateService;
	}

	public ProductManageService getProductManageService() {
		return productManageService;
	}

	public void setProductManageService(
			ProductManageService productManageService) {
		this.productManageService = productManageService;
	}

	public GeDirectory getGeDirectory() {
		return geDirectory;
	}

	public void setGeDirectory(GeDirectory geDirectory) {
		this.geDirectory = geDirectory;
	}

	public GeDirectoryAttributeInfo getGeDirectoryAttributeInfo() {
		return geDirectoryAttributeInfo;
	}

	public void setGeDirectoryAttributeInfo(
			GeDirectoryAttributeInfo geDirectoryAttributeInfo) {
		this.geDirectoryAttributeInfo = geDirectoryAttributeInfo;
	}

	public List<GeDirectory> getProductDirectoryList() {
		return productDirectoryList;
	}

	public void setProductDirectoryList(List<GeDirectory> productDirectoryList) {
		this.productDirectoryList = productDirectoryList;
	}

	public ArrayList<GeProductMain> getProductMains() {
		return productMains;
	}

	public void setProductMains(ArrayList<GeProductMain> productMains) {
		this.productMains = productMains;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * 查询 产品信息表ge_directory_attribute_info
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String findDirectoryAttributeInfo() {
		// 对入参attrID进行校验
		String attrID = super.getRequest().getParameter("attrID");
		String topNum = super.getRequest().getParameter("topNum");
		if(StringUtils.isNotBlank(topNum)){
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(topNum);
			if(!isNum.matches()){
				topNum = "5";
			}
		}else{
			topNum = "5";
		}
		if(StringUtils.isNotBlank(attrID)){
			if(attrID.indexOf("http:")<0)
				getRequest().setAttribute("attrID", null);
		}
		
		String[] parentAttrID = super.getRequest().getParameterValues("parentAttrID");
		if(parentAttrID != null){
			for (int i = 0; i < parentAttrID.length; i++) {
				List attrList = findIndProdcutType(parentAttrID[i]);
				super.getRequest().setAttribute( parentAttrID[i] + "List", attrList);
				super.getRequest().setAttribute( parentAttrID[i] + "ListSize", attrList.size());
			}
		}
		List<GeDirectory> geDirectoryList = geDirectoryService.findGeDirectoryTopNNetsale(Integer.parseInt(topNum));
		super.getRequest().setAttribute("geDirectoryTopNByRecommendDescList", geDirectoryList);
		super.getRequest().setAttribute("geDirectoryTopNByRecommendDescListSize", geDirectoryList.size());
		return SUCCESS;
	}
	
	/***
	 * 查询属性及其子属性产品信息
	 * @return
	*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String findDirectoryAttributeInfoAndProductInfo(){
		String[] parentAttrID = super.getRequest().getParameterValues("parentAttrID");
		String[] productParentAttrID = super.getRequest().getParameterValues("productParentAttrID");
		String productNumber = super.getRequest().getParameter("productNumber");
		String isNetSale = super.getRequest().getParameter("isNetSale");
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("isProductShelf", "01");
		if (isNetSale != null) {
			paraMap.put("isNetSale", isNetSale);
		}
		int pageSize = super.getPageSize();
		if (productNumber != null) {
			pageSize = Integer.parseInt(productNumber);
		}
		if (parentAttrID != null) {
			for (int i = 0; i < parentAttrID.length; i++) {
				List attrList = findIndProdcutType(parentAttrID[i]);
				super.getRequest().setAttribute( parentAttrID[i] + "List", attrList);
				super.getRequest().setAttribute( parentAttrID[i] + "ListSize", attrList.size());
			}
		}
		if (productParentAttrID != null) {
			for (int i = 0; i < productParentAttrID.length; i++) {
				List attrList = findIndProdcutType(productParentAttrID[i]);
				List<ProductDisplay> productDisplayList = new ArrayList<ProductDisplay>();
				for (int j = 0; j < attrList.size(); j++) {
					ProductDisplay productDisplay = new ProductDisplay();
					Date queryDate = new Date();
					GeDirectoryAttributeInfo directoryAttributeInfo = (GeDirectoryAttributeInfo) attrList.get(j);
					List<GeDirectory> directoryAttributeRelateList = geDirectoryService.findGeDirectoryByAttrIDAndQueryDate(directoryAttributeInfo.getAttrID(), paraMap, queryDate, super.getPageNo(), pageSize).getResult();
					productDisplay.setDirectoryAttributeInfo(directoryAttributeInfo);
					productDisplay.setProductDirectoryList(directoryAttributeRelateList);
					productDisplayList.add(productDisplay);
					System.out.println("=============" + directoryAttributeInfo.getAttrID() + "=============List size=========" + directoryAttributeRelateList.size());
				}
				super.getRequest().setAttribute(productParentAttrID[i] + "List", productDisplayList);
				super.getRequest().setAttribute(productParentAttrID[i] + "ListSize", productDisplayList.size());
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 按父属性查询产品信息表ge_directory_attribute_info 父属性=type;
	 * 
	 * @return
	 */
	public List<GeDirectoryAttributeInfo> findIndProdcutType(String type) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geDirectoryAttributeInfo.attrID", type);
		return geDirectoryAttributeInfoService.searchGeDirectoryAttributeInfo(queryRule);
	}

	/**
	 * 查询所有选定类型的产品目录基本信息
	 * 添加条件：1.检索时间要在产品上下架时间区间内；2.该产品上架状态为“上架”；01上架，02下架；
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findDirectoryByEids() {
		String attrID = super.getRequest().getParameter("attrID");
		String productSection = super.getRequest().getParameter("productSection");
		String isNetSale = super.getRequest().getParameter("isNetSale");
		Date queryDate = new Date();
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("isProductShelf", "01");
		if (StringUtils.isNotBlank(productSection)) {
			paraMap.put("productSection", productSection);
		}
		if (StringUtils.isNotBlank(isNetSale)) {
			paraMap.put("isNetSale", isNetSale);
		}
		page = geDirectoryService.findGeDirectoryByAttrIDAndQueryDate(attrID, paraMap, queryDate, pageNo, pageSize);
		List<GeDirectory> directoryAttributeRelateList = page.getResult();
		GeDirectoryAttributeInfo geDirectoryAttributeInfo = geDirectoryAttributeInfoService.findGeDirectoryAttributeInfoByAttrID(attrID);
		super.getRequest().setAttribute("geDirectoryAttributeInfo", geDirectoryAttributeInfo);
		super.getRequest().setAttribute("directoryList", directoryAttributeRelateList);
		super.getRequest().setAttribute("listSize", directoryAttributeRelateList.size());
		super.getRequest().setAttribute("totalCount", page.getTotalCount());
		super.getRequest().setAttribute("attrID", attrID);
		return SUCCESS;
	}
	
	/***
	 * 根据父属性ID查询子属性信息及与子属性关联的产品信息
	 * @return
	 */
	public String findCompanyProductDirectoryByEids(){
		String attrID = super.getRequest().getParameter("attrID");
		String productSection = super.getRequest().getParameter("productSection");
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("isProductShelf", "01");
		if (productSection != null) {
			paraMap.put("productSection", productSection);
		}
		Date queryDate = new Date();
		GeDirectoryAttributeInfo geDirectoryAttributeInfo = geDirectoryAttributeInfoService.findGeDirectoryAttributeInfoByAttrID(attrID);
		List childDirectoryAttributeInfoList = geDirectoryAttributeInfoService.findGeDirectoryAttributeInfoByParentAttrID(attrID);
		int childDirectoryAttributeInfoListSize = childDirectoryAttributeInfoList.size();
		if (childDirectoryAttributeInfoListSize > 0) {
			GeDirectoryAttributeInfo firstAttribute = (GeDirectoryAttributeInfo)childDirectoryAttributeInfoList.get(0);
			page = geDirectoryService.findGeDirectoryByAttrIDAndQueryDate(firstAttribute.getAttrID(), paraMap, queryDate, pageNo, pageSize);
		}else{
			page = geDirectoryService.findGeDirectoryByAttrIDAndQueryDate(attrID, paraMap, queryDate,pageNo,pageSize);
		}
		super.getRequest().setAttribute("attrID", attrID);
		super.getRequest().setAttribute("geDirectoryAttributeInfo", geDirectoryAttributeInfo);
		super.getRequest().setAttribute("totalCount", page.getTotalCount());
		super.getRequest().setAttribute("listSize", page.getResult().size());
		super.getRequest().setAttribute("directoryList", page.getResult());
		super.getRequest().setAttribute("childDirectoryAttributeInfoList", childDirectoryAttributeInfoList);
		super.getRequest().setAttribute("childDirectoryAttributeInfoListSize", childDirectoryAttributeInfoListSize);
		return SUCCESS;
	}
	
	/***
	 * 根据子属性ID查询该属性下的产品信息及该属性的父属性信息
	 * @return
	 */
	public String findCompanyProductDirectoryAndParentAttributeByChildEids(){
		String attrID = super.getRequest().getParameter("attrID");
		String parentAttrID = super.getRequest().getParameter("parentAttrID");
		//添加条件：1.检索时间要在产品上下架时间区间内；2.该产品上架状态为“上架”；01上架，02下架；
		String productSection = super.getRequest().getParameter("productSection");
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("isProductShelf", "01");
		if (StringUtils.isNotBlank(productSection)) {
			paraMap.put("productSection", productSection);
		}
		Date queryDate = new Date();
		GeDirectoryAttributeInfo parentDirectoryAttributeInfo = geDirectoryAttributeInfoService.findGeDirectoryAttributeInfoByAttrID(parentAttrID);
		List<GeDirectoryAttributeInfo> childDirectoryAttributeInfoList = geDirectoryAttributeInfoService.findGeDirectoryAttributeInfoByParentAttrID(parentAttrID);
		int childDirectoryAttributeInfoListSize = childDirectoryAttributeInfoList.size();
		page = geDirectoryService.findGeDirectoryByAttrIDAndQueryDate(attrID, paraMap, queryDate, pageNo, pageSize);
		List<GeDirectory> directoryAttributeRelateList = page.getResult();
		super.getRequest().setAttribute("totalCount", page.getTotalCount());
		super.getRequest().setAttribute("geDirectoryAttributeInfo", parentDirectoryAttributeInfo);
		super.getRequest().setAttribute("clickAttrbuteId", attrID);
		super.getRequest().setAttribute("directoryList", directoryAttributeRelateList);
		super.getRequest().setAttribute("listSize", directoryAttributeRelateList.size());
		super.getRequest().setAttribute("childDirectoryAttributeInfoList", childDirectoryAttributeInfoList);
		super.getRequest().setAttribute("childDirectoryAttributeInfoListSize", childDirectoryAttributeInfoListSize);
		super.getRequest().setAttribute("attrID", attrID);
		super.getRequest().setAttribute("parentAttrID", parentAttrID);
		return SUCCESS;
	}
	
	/***
	 * 根据产品目录EID及产品目录父属性attrID查询该产品所关联的该父属性下的子属性 
	 * @return
	 */
	public String findDirectoryByEidAndParent() {
		try {
			String eid = super.getRequest().getParameter("eid");
			String parentAttrID = super.getRequest().getParameter("pid");
			List<GeDirectoryAttributeInfo> geDirectoryAttributeInfoListForDiv = geDirectoryAttributeInfoService.findGeDirectoryAttributeInfoByEidAndParentID(eid, parentAttrID);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("geDirectoryAttributeInfoListForDiv", geDirectoryAttributeInfoListForDiv);
			JSONObject jsonObject = getResponseJSONObject(map, getJc());
			renderText(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return NONE;
	}
	
	/**
	 * 拼装response 用到的json字符串
	 * @param map
	 * @param jc
	 * @return
	 */
	private JSONObject getResponseJSONObject(Map<String, Object> map,JsonConfig jc){
		JSONObject jsonObject = new JSONObject();
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd"}) );
		if(jc==null){
			jc = getJc();
		}
		jsonObject.putAll(map,jc);
		System.out.println(jsonObject);
		return jsonObject;
	}
	
	/**
	 * json格式化配置
	 */
	public JsonConfig getJc() {
		if(jc==null){
			jc = new JsonConfig();
			jc.setJsonPropertyFilter(new PropertyFilter(){

				@Override
				public boolean apply(Object source, String name, Object value) {
					if ("attrID".equals(name) || "attrName".equals(name)|| "attrSmallPictureOne".equals(name)||
							"attrSmallPictureTwo".equals(name)||"attrMiddlePictureOne".equals(name)||
							"attrMiddlePictureTwo".equals(name)||"attrBigPictureOne".equals(name)||"attrBigPictureTwo".equals(name))
						return false;
					else 
						return true;
				}
				
			});
			jc.registerJsonValueProcessor(Date.class, new JSONValueProcessorImpl());
			jc.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());
		}
		return jc;
	}
	
	public String findGeDirectoryTopTwoByType(){
		try{
			Map<String,String> rebateMap = new HashMap<String,String>();
			rebateMap.put("isProductShelf", "01");
			rebateMap.put("isNetSale", "01");
			List<GeDirectory> rebate = geDirectoryService.findTopNumberDirectoryByParams(rebateMap, 2);
			Map<String,String> folkMap = new HashMap<String,String>();
			folkMap.put("isProductShelf", "01");
			folkMap.put("businessArea", "2");
			folkMap.put("productSection", "01");
			List<GeDirectory> folk = geDirectoryService.findTopNumberDirectoryByParams(folkMap, 2);
			Map<String,String> assetsMap = new HashMap<String,String>();
			assetsMap.put("isProductShelf", "01");
			assetsMap.put("businessArea", "3");
			assetsMap.put("productSection", "01");
			List<GeDirectory> assets = geDirectoryService.findTopNumberDirectoryByParams(assetsMap, 2);
			Map<String,String> businessMap = new HashMap<String,String>();
			businessMap.put("isProductShelf", "01");
			businessMap.put("productSection", "02");
			List<GeDirectory> business = geDirectoryService.findTopNumberDirectoryByParams(businessMap, 2);
			super.getRequest().setAttribute("rebate", rebate);
			super.getRequest().setAttribute("folk", folk);
			super.getRequest().setAttribute("assets", assets);
			super.getRequest().setAttribute("business", business);
			super.getRequest().setAttribute("rebateSize", rebate.size());
			super.getRequest().setAttribute("folkSize", folk.size());
			super.getRequest().setAttribute("assetsSize", assets.size());
			super.getRequest().setAttribute("businessSize", business.size());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	/***
	 * 查询产品表推荐度最高的前4条记录
	 * @return
	 */
	public String findGeDirectoryTop4ByRecommendDesc() {
		//属性产品推荐评分 productRecommend;
		try {
			String productSection = super.getRequest().getParameter("productSection");
			List<GeDirectory> geDirectoryList = geDirectoryService.findGeDirectoryTopNRecommendDesc(4, productSection);
			super.getRequest().setAttribute("geDirectoryTopNByRecommendDescList", geDirectoryList);
			super.getRequest().setAttribute("geDirectoryTopNByRecommendDescListSize", geDirectoryList.size());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	/***
	 * 查询产品表推荐度最高的前4条记录
	 * @return
	 */
	public String getTopNCorrelationProductByEid() {
		//属性产品推荐评分 productRecommend;
		try {
			String eid =  super.getRequest().getParameter("eid");
			
			//#########标识近期浏览读的是哪种类型(个人、企业、混合(必须是网销))
			HttpServletRequest request = super.getRequest();
			String fromType = request.getParameter("fromType");
			// 来自产品超市
			if(fromType!=null && fromType.equals("shop")){
				request.setAttribute("proType", "3");
			}else{
				// 来自个人或者企业
				GeDirectory ge = geDirectoryService.findGeDirectoryByEId(eid);
				if("01".equals(ge.getProductSection()))
					request.setAttribute("proType", "1");
				else if("02".equals(ge.getProductSection()))
					request.setAttribute("proType", "2");
			}
			//##################
			
			List<GeDirectory> topNCorrelationProductsList = geProductCorrelationService.findTopNCorrelationProductByEid(5,eid);
			if(topNCorrelationProductsList != null){
				super.getRequest().setAttribute("topNCorrelationProductsList", topNCorrelationProductsList);
				super.getRequest().setAttribute("topNCorrelationProductsListSize", topNCorrelationProductsList.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public String findUniqueGeDirectory(){
		try {
			String attrID = super.getRequest().getParameter("attrID");
			if(StringUtils.isNotBlank(attrID)){
				if(attrID.indexOf("http:")<0)
					getRequest().setAttribute("attrID", null);
			}
			
			String eid =  super.getRequest().getParameter("eid");
			GeDirectory geDirectory = geDirectoryService.findGeDirectoryByEId(eid);
			if (geDirectory != null && (!StringUtils.isBlank(geDirectory.getBusinessArea()))) {
				String codeCName = geCodeService.findCodeCName(geDirectory.getBusinessArea(), "AreaToCompany");
				String companyName = geCodeService.findCodeCName(codeCName, "MemberCompany");
				geDirectory.setCompanyName(companyName);
			}
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

			Map map = addServiceActivityService.findAddGeAddServiceActivityCheckRule(geActivitiesRuleList, geAddserviceConditionDto);
			existDiscountFlag = (String)map.get("isDiscountFlag");
			if("true".equals(existDiscountFlag)){
				super.getRequest().setAttribute("existDiscountFlag", existDiscountFlag);
			}
			super.getRequest().setAttribute("geDirectory", geDirectory);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String lift_HotProduct(){
		List occupations = geDirectoryService.findGeDirectoryBySaleTop();
		super.render(JsonBinder.buildNonNullBinder().toJson(occupations), "application/json;charset=GBK");
		return NONE;
	}
}