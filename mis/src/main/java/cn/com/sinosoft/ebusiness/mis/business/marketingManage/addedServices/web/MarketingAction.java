package cn.com.sinosoft.ebusiness.mis.business.marketingManage.addedServices.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeRisk;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.BizCommonService;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCodeType;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeTypeService;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterInfo;
import cn.com.sinosoft.ebusiness.common.party.service.facade.ThirdParterService;
import cn.com.sinosoft.ebusiness.marketing.domain.GeActivitiesConfig;
import cn.com.sinosoft.ebusiness.marketing.domain.GeActivitiesPicture;
import cn.com.sinosoft.ebusiness.marketing.domain.GeActivitiesProduct;
import cn.com.sinosoft.ebusiness.marketing.domain.GeActivitiesRule;
import cn.com.sinosoft.ebusiness.marketing.domain.GeAddServiceActivity;
import cn.com.sinosoft.ebusiness.marketing.domain.GeAddServiceProcess;
import cn.com.sinosoft.ebusiness.marketing.domain.GeCustomAddServiceActivity;
import cn.com.sinosoft.ebusiness.marketing.domain.GeProductAndPattern;
import cn.com.sinosoft.ebusiness.marketing.domain.SelectType;
import cn.com.sinosoft.ebusiness.marketing.service.facade.AddServiceActivityService;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectory;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductMain;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.ProductManageService;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDepartmentService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeGroupService;
import cn.com.sinosoft.util.encode.JsonBinder;

public class MarketingAction extends Struts2Action {
	private static final long serialVersionUID = MarketingAction.class.hashCode();
	
	List<GeAddServiceActivity> listAdd;
	private GeAddServiceActivity geAddServiceActivity;
//	private MarktingWrokFlow marktingWrokFlow;
	
	private String message;//返回页面的提示信息
	private int flag;//页面提示选择器
	private List<GeRisk> geRiskList;
	private List<GeCode> geCodeActivityPatternList;//活动类型
	private List<GeCode> geCodeBusinessAreaList;//业务领域
	private List<GeCode> geCodePremiumTypeList;//保費類型
	private List<GeDepartment> geDepartmentTypeList;//省
	private List<GeDepartment> cityList;//市
	private List<GeThirdParterInfo> geThirdParterInfoList;//供应商公司 
	private Page page;
	private String activityId;
	private List areaList;
	private Date date;
	private static final String imagePath = "upload/images/imageP/";
	private File attrPictureOne;;
	private String attrPictureOneFileName;
	private File attrPictureTwo;;
	private String attrPictureTwoFileName;
	private String businessArea;
	private String city;
	private GeActivitiesConfig geActivitiesConfig;
	private Map<String,String> productTypeMap;
	private Map<String,String> businessAreaMap;
	private Map<String,String> geCodePremiumTypeMap;
	private String count;
	private String forward;
	private String xrule;//第几个归则
	private String yaddShopping;//第几个加购产品
	private String oldProductEid;//产品Eid用于更新页面
	private GeDirectory geDirectory;
	private String rulds;//归则ID
	private String deptId ;//机构树
	private String selectType;//条件类型 车,非车,卡
	private String eids;//eids 以","间隔
	//批量上传时用的
	private List<File> uploadPicture;
	private List<String> uploadPictureFileName;
	private List<String> uploadPictureContentType;
	

	//双击域选择机构权限start
	/**正在操作功能的权限编码*/
	private String receivedObj;
	private String type;/**2-input赋值  不填-js赋值*/
	private String openType;/**iframe-iframe 不填-window*/
	private String checkType;/**单选多选*/
	private String receivedObjName;/**选中机构名称*/
	private String deptIdCount;/**定位标志,用于多条回写的时候*/
	//双击域选择机构权限end

	//service
	private GeCodeTypeService geCodeTypeService;
	private AddServiceActivityService addServiceActivityService;
	private BizCommonService bizCommonService;
	private GeCodeService geCodeService;/**数据字典服务类*/
	private ThirdParterService thirdParterService;
	private ProductManageService productManageService;//产品目录
//	@Autowired
//	private MarktingWorkFlowServiceImpl  markingWorkFlowService ;
	@Autowired
	private GeDirectoryService geDirectoryService;
	@Autowired
	private GeGroupService geGroupService;
	private String taskID;
	private String workFlowID;
	private GeDepartmentService geDepartmentService;
//	private GeWorkFlowService geWorkFlowService;
//	@Autowired
//	private TaskService taskService;
//	@Autowired
//	private WorkFlowNewService<EntityWorkFlow> workFlowServiceImpl;
	/**
	 * 选择机构权限  也把定位光标给拿过来
	 * @return
	 */
	public String selectDeptAuthId(){
		String operation =  super.getRequest().getParameter("operation");
		if(operation!=null&&!"".equals(operation)){
			if("marketing".equals(operation)){//从营销活动的渠道来的
				
			}
			if("thirdPartterInfo".equals(operation)){//从第三方的渠道来的
				return "thirdPartterInfo";
			}
				
		}
		return SUCCESS;
	}
	/**
	 * 准备进入查询第三方产品的界面
	 * @return
	 */
	public String prepareFindGeThirdParterService(){
		deptId = super.getRequest().getParameter("deptId");
		//业务领域(1-集团；2-寿险；3-财险；4-养老险；9-其他)
		return SUCCESS;
	}
	/**
	 * 准备增加活动
	 * @return
	 * @throws Exception 
	 */
	public String prepareAddGeAddServiceActivity() throws Exception{
		geRiskList = bizCommonService.findAllRiskCode(null);//查询出所有险种产品
		geCodeActivityPatternList = geCodeService.findAllByGeCodeType("ActivityPattern");//查询活动方式类型
		geCodeBusinessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");//查询业务领域
		geCodePremiumTypeList = geCodeService.findAllByGeCodeType("PremiumType");//查询保费类型premiumType
		date = new Date();
		//拿到 登录人的 在营销活动中的 业务领域   start
		Map map = (Map) super.getSession().getAttribute("permission");
		String authorityids = (String) map.get("ROLE_B_AAGA_I");//拿到该功能的所有机构权限
		for (int i = geCodeBusinessAreaList.size() -1; i >= 0; i--) {
			String baStr = geCodeBusinessAreaList.get(i).getId().getCodeCode();
			if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
				continue;
			}else {
				geCodeBusinessAreaList.remove(i);
			}
		}
		//拿到 登录人的 在营销活动中的 业务领域   end
		return SUCCESS;
	}
	
	/**
	 * 查找省
	 * @return
	 */
	public String findProvince(){
		
		Map map = (Map) super.getSession().getAttribute("permission");//省市
		String authorityid = (String) map.get("ROLE_B_AAGA_I");
		QueryRule queryRule  = QueryRule.getInstance();
		queryRule.addEqual("businessarea", businessArea);//业务领域
		queryRule.addEqual("deptlevel", "2");//省
		List<GeDepartment> geDepartmentList =geDepartmentService.findAllDepartments(queryRule);
		List<GeDepartment> geDepartmentExist  = new ArrayList<GeDepartment>(); //用于返回的省
		if(geDepartmentList!=null&&geDepartmentList.size()>0){
			for (GeDepartment geDepartment : geDepartmentList) {
				if(authorityid.indexOf(","+geDepartment.getDeptid()+",")>-1){
					geDepartmentExist.add(geDepartment);//这个省存在
				}
			}
		}
		JSONObject backJson = new JSONObject();
		backJson.put("backdata", geDepartmentExist);
		super.render(backJson.toString(), "application/json;charset=GBK");
		return NONE;
	}
	/**
	 * 查找市
	 * select * from ge_department t where t.businessarea = '2' and t.deptlevel = '3' and t.parentid = ''
	 * @return
	 */
	public String findCity(){
		
		Map map = (Map) super.getSession().getAttribute("permission");//省市
		String authorityid = (String) map.get("ROLE_B_AAGA_I");
		QueryRule queryRule  = QueryRule.getInstance();
		queryRule.addEqual("businessarea", businessArea);//业务领域
		queryRule.addEqual("deptlevel", "3");//市
		queryRule.addEqual("parentid", city);//市
		List<GeDepartment> geDepartmentList =geDepartmentService.findAllDepartments(queryRule);
		List<GeDepartment> geDepartmentExist  = new ArrayList<GeDepartment>(); //用于返回的省
		boolean departFlag = true;//默认是正常的省,给页面使用
		if(geDepartmentList!=null&&geDepartmentList.size()>0){
			
			for (GeDepartment geDepartment : geDepartmentList) {
				if(authorityid.indexOf(","+geDepartment.getDeptid()+",")>-1){
					geDepartmentExist.add(geDepartment);//这个省存在
				}
			}
			
		}else{
			departFlag =false;//false是特殊的省，比如是深圳市
			GeDepartment geDepartmentTemp=geDepartmentService.findGeDepartmentByPK(city);
			if(geDepartmentTemp!=null){
				if(authorityid.indexOf(","+geDepartmentTemp.getDeptid()+",")>-1){
					geDepartmentExist.add(geDepartmentTemp);//这个省存在
				}
			}
			
		}
		
			
		JSONObject backJson = new JSONObject();
		backJson.put("backdata", geDepartmentExist);
		backJson.put("departFlag", departFlag);
		super.render(backJson.toString(), "application/json;charset=GBK");
		return NONE;
	}
	/**
	 * 查询车产品
	 * @return
	 */
	public String findCarProduct() {
		QueryRule queryRule = QueryRule.getInstance();
		String riskCode = super.getRequest().getParameter("riskCode");
		String riskCName = super.getRequest().getParameter("riskCName");
		queryRule.addEqual("riskType", "11");//11代表车险
		queryRule.addEqual("validInd", "1");
		if (StringUtils.isNotBlank(riskCode)) {
			queryRule.addEqual("riskCode", riskCode);
		}

		if (StringUtils.isNotBlank(riskCName)) {
			queryRule.addLike("riskCName", "%" + riskCName.replaceAll("\\s", "%") + "%");
		}
		
		pageNo = pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == 0 ? 20 : pageSize;
		page = bizCommonService.findGeRiskList(queryRule, pageNo, pageSize);
		
		return forward;
	}
	
	/***
	 * 获取网销产品信息
	 * @return
	 */
	public String findNetSalesProduct() {
		try {
			QueryRule queryRule = QueryRule.getInstance();
			String coreProductCode = super.getRequest().getParameter("coreProductCode");
			String coreProductSimpleName = super.getRequest().getParameter("coreProductSimpleName");
			String businessArea =  super.getRequest().getParameter("businessArea");
			queryRule.addEqual("productStatus", "05");
			if (StringUtils.isNotBlank(coreProductCode)) {
				queryRule.addEqual("coreProductCode", coreProductCode);
			}

			if (StringUtils.isNotBlank(coreProductSimpleName)) {
				queryRule.addEqual("coreProductSimpleName","%" + coreProductSimpleName.replaceAll("\\s", "%") + "%");
			}
			if(StringUtils.isNotBlank(businessArea)){
				queryRule.addEqual("businessArea", businessArea);
			}
			//查询业务领域
			businessAreaMap = geCodeService.findAllCodeAndName("BusinessArea");
			geCodeService.findAllCodeAndName("BusinessArea");
			pageNo = pageNo == 0 ? 1 : pageNo;
			pageSize = pageSize == 0 ? 20 : pageSize;
			page = productManageService.searchProduct(queryRule, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//判断是否有重复的活动
	public String isProductAddServiceExist(){
		String startDate = getRequest().getParameter("startDate");
		String endDate = getRequest().getParameter("endDate");
		String coreProductCode  = getRequest().getParameter("coreProductNames");
		String activityPattern = getRequest().getParameter("activityPatterns");
		String deptID = getRequest().getParameter("deptID"); //地域
		String updatePage = getRequest().getParameter("updatePage");//更新页面
		String activityId = getRequest().getParameter("activityId");//活动ID
		if(updatePage!=null&&!"".equals(updatePage)){
			if(updatePage.equals("updatePage")){
				updatePage="updatePage";
			}
			if(updatePage.equals("finishPage")){
				updatePage="finishPage";
			}
		}
		geAddServiceActivity = new GeAddServiceActivity();
		geAddServiceActivity.setActivityId(activityId);
		geAddServiceActivity.setActivityStartDate(getStringToDate(startDate));
		geAddServiceActivity.setActivityEndDate(getStringToDate(endDate));
		geAddServiceActivity.setDeptID(deptID);
		geAddServiceActivity.setFlag(updatePage!=null&&!"".equals(updatePage) ? updatePage :null);  //借用字段
		//set 产品代码
		String[] coreProductCodes = coreProductCode.split(",");
		List<GeActivitiesProduct> geActivitiesProductList= new ArrayList<GeActivitiesProduct>();
		for(int i=0;i<coreProductCodes.length;i++){
			GeActivitiesProduct geActivitiesProduct = new GeActivitiesProduct();
			geActivitiesProduct.setEid(coreProductCodes[i]);
			geActivitiesProductList.add(geActivitiesProduct);
		}
		geAddServiceActivity.setGeActivitiesProducts(geActivitiesProductList);//set  productCode
		//set 活动方式
		String[] activityPatterns = activityPattern.split(",");
		List<GeActivitiesRule> geActivitiesRuleList= new ArrayList<GeActivitiesRule>();
		for(int i=0;i<activityPatterns.length;i++){
			GeActivitiesRule geActivitiesRule = new  GeActivitiesRule();
			geActivitiesRule.setActivityPattern(activityPatterns[i]);
			geActivitiesRuleList.add(geActivitiesRule);
		}
		geAddServiceActivity.setGeActivitiesRules(geActivitiesRuleList);// set activityPattern
		List<GeCustomAddServiceActivity> geCustomAddServiceActivityList = addServiceActivityService.isRepeatAddServiceActivity(geAddServiceActivity);
		JSONObject backJson = new JSONObject();
		backJson.put("geCustomAddServiceActivityList", geCustomAddServiceActivityList);
		super.render(backJson.toString(), "application/json;charset=GBK");
		
		//以前的校验活动方式  暂时注掉
//		List<GeProductAndPattern> geProductAndPatternList= getProductAndPatternList(geCustomAddServiceActivityList,geActivitiesRuleList);
//		JSONObject backJson = new JSONObject();
//		backJson.put("geProductAndPatternList", geProductAndPatternList);
//		super.render(backJson.toString(), "application/json;charset=GBK");
		return null;
	}
	
	
	
	
	private List<GeProductAndPattern> getProductAndPatternList(List<GeCustomAddServiceActivity> geCustomAddServiceActivityList,List<GeActivitiesRule> geActivitiesRuleList){
		List<GeProductAndPattern> geProductAndPatternList = new ArrayList<GeProductAndPattern>();//要返回的结果集
		
		if(geCustomAddServiceActivityList!=null&&geCustomAddServiceActivityList.size()>0){
			Map<String,List<GeCustomAddServiceActivity>> map = new HashMap<String, List<GeCustomAddServiceActivity>>();
			//加工数据
			for(int i=0;i<geCustomAddServiceActivityList.size();i++){
				String productName = geCustomAddServiceActivityList.get(i).getProductName();
				String activitypatternTemp = geCustomAddServiceActivityList.get(i).getActivitypattern();
				if(map.get(productName)!=null){
					for(int j=0;j<geActivitiesRuleList.size();j++){
						if(geActivitiesRuleList.get(j).getActivityPattern().equals(activitypatternTemp)){
							map.get(productName).add(geCustomAddServiceActivityList.get(i));
						}
					}
				}else{
					for(int j=0;j<geActivitiesRuleList.size();j++){
						if(geActivitiesRuleList.get(j).getActivityPattern().equals(activitypatternTemp)){
							List<GeCustomAddServiceActivity>  geCustomAddServiceActivityForMapList=  new ArrayList<GeCustomAddServiceActivity>();
							geCustomAddServiceActivityForMapList.add(geCustomAddServiceActivityList.get(i));
							map.put(productName, geCustomAddServiceActivityForMapList);
						}
					}
				}
			}
			
			geCodeActivityPatternList = geCodeService.findAllByGeCodeType("ActivityPattern");
			//在这个for 里面组装 geProductAndPatternList 的具体的值
			for (Map.Entry<String, List<GeCustomAddServiceActivity>> entry: map.entrySet()) {
				GeProductAndPattern geProductAndPattern = new GeProductAndPattern();
				geProductAndPattern.setProductName(entry.getKey());
				List<String>  activityPatternList = new ArrayList<String>();
				//set activityPatternList value
				List<GeCustomAddServiceActivity> geCustomAddServiceActivityForMapList = entry.getValue();
				for (GeCustomAddServiceActivity geCustomAddServiceActivity : geCustomAddServiceActivityForMapList) {
					String temp = geCustomAddServiceActivity.getActivitypattern();
					for(GeCode gecode:geCodeActivityPatternList){
						if(temp.equals(gecode.getId().getCodeCode())){
							activityPatternList.add(gecode.getCodeCName());
						}
					}
				}
				//
				geProductAndPattern.setActivityPatternList(activityPatternList);
				geProductAndPatternList.add(geProductAndPattern);
			}
		}// end if(geCustomAddServiceActivityList!=null&&geCustomAddServiceActivityList.size()>0){
		
		return geProductAndPatternList;
	}
	private Date getStringToDate(String dateString){
		SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	} 
	
	public String addAddGeAddServiceActivityAndRule() throws UnsupportedEncodingException{
		String uploadPictureSerialNos = super.getRequest().getParameter("uploadPictureSerialNos");
		geAddServiceActivity.setUploadPicture(uploadPicture);//真正要上传的文件
		geAddServiceActivity.setUploadPictureContentType(uploadPictureContentType);//真正要上传的文件类型
		geAddServiceActivity.setUploadPictureFileName(uploadPictureFileName);//真正要上传的文件名称 
		geAddServiceActivity.setUploadSerialNoList(getUploadSerialNoList(uploadPictureSerialNos));//真正要上传的文件序列号
		geAddServiceActivity.setUploadImagePath(super.getRequest().getRealPath("/upload/images/imageP/"));
		geAddServiceActivity.setUploadSavePath(imagePath);
		geAddServiceActivity.setCreateDeptId(getCreateDeptId());
//		geAddServiceActivity.setValidInd("1");//1为有效
		setGeActivitiesRuleDiscountRemarkText(geAddServiceActivity);
		addServiceActivityService.addAddGeAddServiceActivityAndRule(geAddServiceActivity);
		message = "添加成功";
		String mas="添加成功"+"@"+geAddServiceActivity.getActivityId()+"@"+geAddServiceActivity.getActivityName();
		mas = geAddServiceActivity.getActivityName()+"营销活动"+message;
		super.getRequest().setAttribute("message", mas);
		flag = 1;
		return SUCCESS;
	}
	
	//addAddGeAddServiceActivityAndRule() 方法  updateAddGeAddServiceActivity()方法都要使用
	private void setGeActivitiesRuleDiscountRemarkText(GeAddServiceActivity geAddServiceActivity) throws UnsupportedEncodingException{
		List<GeActivitiesRule> geActivitiesRules = geAddServiceActivity.getGeActivitiesRules();
		if(geActivitiesRules!=null&&geActivitiesRules.size()>0){
			for (GeActivitiesRule geActivitiesRule : geActivitiesRules) {
				String discountRemarkText = geActivitiesRule.getDiscountRemarkText();
				if(discountRemarkText!=null&&!"".equals(discountRemarkText)){
					geActivitiesRule.setDiscountRemark(discountRemarkText.getBytes("GBK"));
				}
			}
		}
	}
	/**
	 * prepareUpdateAddGeAddServiceActivity() 这个方法调用了本方法
	 * view()  这个方法调用了本方法
	 * @param geAddServiceActivity
	 * @throws UnsupportedEncodingException
	 */
	private void decodeGeActivitiesRuleDiscountRemarkText(GeAddServiceActivity geAddServiceActivity) throws UnsupportedEncodingException{
		List<GeActivitiesRule> geActivitiesRules = geAddServiceActivity.getGeActivitiesRules();
		if(geActivitiesRules!=null&&geActivitiesRules.size()>0){
			for (GeActivitiesRule geActivitiesRule : geActivitiesRules) {
				byte[] a = geActivitiesRule.getDiscountRemark();
				if(a!=null&&a.length>0){
					geActivitiesRule.setDiscountRemarkText(new String(a, "GBK"));
				}
			}
		}
	}
	//得到每个要上传的图片对应的图片序号
	private List<String> getUploadSerialNoList(String uploadPictureSerialNos){
		List<String> uploadPictureSerialNoList = new ArrayList<String>();
		if(uploadPictureSerialNos!=null&&!uploadPictureSerialNos.equals("")){
			String[] temps = uploadPictureSerialNos.split(",");
			if(temps!=null&&temps.length>0){
				for(int i=0;i<temps.length;i++){
					uploadPictureSerialNoList.add(temps[i]);
				}
			}
		}
		
		return uploadPictureSerialNoList;
	}
	
	private String getCreateDeptId(){
		 GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
		 return operator.getDeptid();

	}
	
	
	public String findAddGeAddServiceActivity(){
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		/*if(geAddServiceActivity.getStatus()!=null&&geAddServiceActivity.getStatus().equals("")){
			geAddServiceActivity.setStatus(null);
		}*/
		
		String status = geAddServiceActivity.getStatus();
		String bizType = this.getRequest().getParameter("bizType");
		Map map = (Map) super.getSession().getAttribute("permission");
		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
		String authorityid = "";
		if(bizType!=null&&!"look".equals(bizType)){
			 authorityid = (String) map.get("ROLE_B_AAGA_S");//第三方最外层的权限名字
		}else{
			 authorityid = (String) map.get("ROLE_ROLE_B_AAGA_SEE");//第三方最外层的权限名字
		}
		//----------------
		if(status == null ||"".equals(status)){
			   page = new Page();
			}else{
				// 添加查询状态(已处理或者查全部不需要作为查询条件)
				if("all".equals(status) || "4".equals(status)){
					geAddServiceActivity.setStatus(null);
				}
				if("4".equals(status)){
					//已处理查询轨迹表
					System.out.println(geAddServiceActivity.getActivityName());
					page=addServiceActivityService.queryHasProcessed(pageNo, pageSize, operator.getOperatorid(),geAddServiceActivity.getActivityName());
					
				}else{
				//###############################
				//**查询撤销记录，只需加城市机构权限，和工作流组等无关
				if(!"5".equals(status)){
					StringBuffer stringBuffer = new StringBuffer();
					
//					Map<String, Set<String>> setMap = workFlowServiceImpl.getWorkFlowGroup(operator.getOperatorid(),"marketingWorkFlow");
//					Set<String> inGroups = setMap.get("group");
//					Set<String> inTasks = setMap.get("taskID");
//					if(!"look".equals(bizType) && (inGroups.isEmpty() || inTasks.isEmpty())){
//						 if(!"5".equals(geAddServiceActivity.getStatus()) &&( null == status || "all".equals(status))){
//						         geAddServiceActivity.setStatus("all");
//								 page = addServiceActivityService.findAddGeAddServiceActivityByDefaultPermession(super.getSession(),geAddServiceActivity,pageNo,pageSize,null,bizType);
//							     
//						   }else{
//							   if("0".equals(geAddServiceActivity.getStatus())){
//								   page = addServiceActivityService.findAddGeAddServiceActivityByDefaultPermession(super.getSession(),geAddServiceActivity,pageNo,pageSize,null,bizType);
//							   }else{
//						    	page = new Page();
//							   }
//							}
//					}else{
//						if(authorityid!=null){
//						// 组+组织机构编码 的拼接字符串
//						String[] arrayAuth = authorityid.split(",");
//						for(String str : (Set<String>)setMap.get("group")){
//							for(int i = 0; i < arrayAuth.length; i++){
//								stringBuffer.append("'"+str + arrayAuth[i] + "',");
//							}
//						}
//						String groupIDsString ="";
//						if(!stringBuffer.toString().equals("")){
//							groupIDsString = stringBuffer.substring(0,stringBuffer.lastIndexOf(","));
//						}
//						//**主要业务
//						List<String> canSerceIds = new ArrayList<String>();
//						if(!"look".equals(bizType)){ // 维护模式需要加机构+组条件
//							
//							if("all".equals(status)){ // 全部
//								List<String> workFlowInquryIds = findIDsFormWorkFlow(); // 工作流处理中的记录
//								String str[] = {"1"};
//								canSerceIds = addServiceActivityService.getInquiryIdByGroupCity(groupIDsString, null, str,null);// 机构和组查询本地，状态是非处理中的记录
//								canSerceIds.addAll(workFlowInquryIds);
//							}else{
//								canSerceIds = addServiceActivityService.getInquiryIdByGroupCity(groupIDsString, null, null,null);
//							}
//						}else{
//							// 查看模式
//							page = addServiceActivityService.findAddGeAddServiceActivityByDefaultPermession(super.getSession(),geAddServiceActivity,pageNo,pageSize,null,bizType);
//						}
//						if(canSerceIds.size()==0||null==canSerceIds){
//							page = addServiceActivityService.findAddGeAddServiceActivityByDefaultPermession(super.getSession(),geAddServiceActivity,pageNo,pageSize,null,bizType);
//						}else{
//								String sql="";
//								if(canSerceIds.size()>0){
//									if(canSerceIds.size() >= 1000){
//										sql += " and (ACTIVITYID in";
//				
//											int authorityIdSize = canSerceIds.size();
//											int authorityIdNumber = (authorityIdSize-1)/1000+1;
//											String nextParentIdString = "";
//											for(int j = 0; j < authorityIdNumber; j++){
//												if(nextParentIdString.length()>0){
//													 nextParentIdString = "";
//													 sql+="or ACTIVITYID in";
//												}
//												int loopNum = 1000;
//												if(j == authorityIdNumber-1){
//													loopNum = authorityIdSize - (authorityIdNumber-1)*1000;
//												}
//												for (int i = 0; i < loopNum; i++) {
//													String childDeptId = (String)canSerceIds.get(1000*j+i);
//													if(i == 0){
//														nextParentIdString += "'"+childDeptId+"'";
//													}else{
//														nextParentIdString += ",'"+childDeptId+"'";
//													}
//												}
//												sql += "("+nextParentIdString+")";
//											}
//											sql+=")";
//									}
//									page = addServiceActivityService.findAddGeAddServiceActivityByDefaultPermession(super.getSession(),geAddServiceActivity,pageNo,pageSize,sql,bizType);
//								}
//							}
//						
//						}else{
//							page = new Page();
//						}
//					}
				}else{
					page = addServiceActivityService.findAddGeAddServiceActivityByDefaultPermession(super.getSession(),geAddServiceActivity,pageNo,pageSize,null,bizType);
				}
			}
		}
		//---
		page = addServiceActivityService.findAddGeAddServiceActivityByDefaultPermession(super.getSession(),geAddServiceActivity,pageNo,pageSize,null,bizType);
		if(page!=null){
			super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
			super.getRequest().setAttribute("pageSize", page.getPageSize());
			super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
			super.getRequest().setAttribute("totalCount", page.getTotalCount());
			super.getRequest().setAttribute("bizType", super.getRequest().getParameter("bizType"));
			super.getRequest().setAttribute("searchType", super.getRequest().getParameter("searchType"));
		}
		return SUCCESS;
	}
	
	
	public String prepareUpdateAddGeAddServiceActivity() throws Exception{
		//查询活动主表
		geAddServiceActivity = addServiceActivityService.findAddGeAddServiceActivityByActivityId(activityId);
		String businessAreaDept = null;
		String splitDeptName[] =geAddServiceActivity.getDeptName().split("/");
		for (int i = 0; i < splitDeptName.length; i++) {
			if(splitDeptName[i].equals("集团")){
				businessAreaDept="1";
			}
			if(splitDeptName[i].equals("财险")){
				businessAreaDept="3";
			}
			if(splitDeptName[i].equals("寿险")){
				businessAreaDept="2";
			}
			if(splitDeptName[i].equals("养老险")){
				businessAreaDept="4";
			}
			
		}
		decodeGeActivitiesRuleDiscountRemarkText(geAddServiceActivity);
		viePictureForUploadPage(geAddServiceActivity);//设置页面上图片的显示
		oldProductEid = (getOldProductEid(geAddServiceActivity)).trim();
		geCodeActivityPatternList = geCodeService.findAllByGeCodeType("ActivityPattern");//查询活动方式类型
		geCodePremiumTypeList = geCodeService.findAllByGeCodeType("PremiumType");//查询保费类型premiumType
		geCodeBusinessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");//查询业务领域
		date = new Date();
		//拿到 登录人的 在营销活动中的 业务领域   start
		Map map = (Map) super.getSession().getAttribute("permission");
		String authorityids = (String) map.get("ROLE_B_AAGA_I");//拿到该功能的所有机构权限
		if(authorityids!=null){
			for (int i = geCodeBusinessAreaList.size() -1; i >= 0; i--) {
				String baStr = geCodeBusinessAreaList.get(i).getId().getCodeCode();
				if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
					continue;
				}else {
					geCodeBusinessAreaList.remove(i);
				}
			}
		}else{
			authorityids = (String) map.get("ROLE_B_AAGA_U");//拿到该功能的所有机构权限
			for (int i = geCodeBusinessAreaList.size() -1; i >= 0; i--) {
				String baStr = geCodeBusinessAreaList.get(i).getId().getCodeCode();
				if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
					continue;
				}else {
					geCodeBusinessAreaList.remove(i);
				}
			}
		}
		super.getRequest().setAttribute("businessAreaDept", businessAreaDept);// 搜索类型
		return SUCCESS;
	}
	
	//查看详细正常渠道进来的
	public String view() throws Exception{
		//getDiscountData("G200053");
		//得有一个工作流的标志位若是工作流的标志位的那么他会显示   通过  回退的按钮
		//String workFlowType = "workFlow";//为从工作流表中查询的渠道而来的
		String workFlowType = super.getRequest().getParameter("workFlowType");
		String bizType = super.getRequest().getParameter("bizType");
		//
		geAddServiceActivity = addServiceActivityService.findAddGeAddServiceActivityByActivityId(activityId);
		String createDeptName= addServiceActivityService.getDeptName(geAddServiceActivity.getCreateDeptId());
		decodeGeActivitiesRuleDiscountRemarkText(geAddServiceActivity);
		viePictureForUploadPage(geAddServiceActivity);
		geCodeActivityPatternList = geCodeService.findAllByGeCodeType("ActivityPattern");//查询活动方式类型
		geCodePremiumTypeMap = geCodeService.findAllCodeAndName("PremiumType");//查询保费方式类型
		//if(workFlowType!=null&&!workFlowType.equals("")){//真正的工作流的渠道而来的 需要控制按钮是否显示
			getRequest().setAttribute("taskId",getRequest().getParameter("taskId"));
			getRequest().setAttribute("workflowId", getRequest().getParameter("workflowId"));
	//	}//else{//从其它渠道而来的 即非工作流 渠道，而是从本地渠道而来的 
//			
//		}
		//super.getRequest().setAttribute("searchType", super.getRequest().getParameter("searchType"));//搜索类型
		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
		super.getRequest().setAttribute("createDeptName", createDeptName);
		super.getRequest().setAttribute("operatorCode",operator.getDeptid());
		super.getRequest().setAttribute("operator",operator);
		super.getRequest().setAttribute("workFlowType", workFlowType);// 搜索类型
		super.getRequest().setAttribute("bizType", bizType);
		return SUCCESS;
	}
	//寿险打折测试
//	  private void getDiscountData(String eid)
//	  {
//	    String existDiscountFlag = "false";
//	    List geActivitiesRuleList = new ArrayList();
//	    GeActivitiesRule geActivitiesRule = new GeActivitiesRule();
//	    geActivitiesRule.setDeptID("2");
//	    geActivitiesRule.setEid(eid);
//	    geActivitiesRule.setPeremiumValue("3");
//	    geActivitiesRuleList.add(geActivitiesRule);
//
//	    GeAddserviceConditionDto geAddserviceConditionDto = new GeAddserviceConditionDto();
//	    geAddserviceConditionDto.setSystemFlowId("02");
//	    geAddserviceConditionDto.setPictureFlag(true);
//
//	    List wantedActivityPatterns = new ArrayList();
//	    wantedActivityPatterns.add("4");
//	    geAddserviceConditionDto.setWantedActivityPatterns(wantedActivityPatterns);
//	    try {
//	      Map map = this.addServiceActivityService.findAddGeAddServiceActivityCheckRule(geActivitiesRuleList, geAddserviceConditionDto);
//	      existDiscountFlag = (String)map.get("isDiscountFlag");
//	      if ("true".equals(existDiscountFlag)) {
//	        Map discountAndJumpUrl = (Map)map.get("discountAndJumpUrl");
//	        String discountID = (String)discountAndJumpUrl.get("newDiscountID");
//	        super.getRequest().setAttribute("discountID", discountID);
//	      }
//
//	      map = this.addServiceActivityService.activityBoola("2", eid, null);
//
//	      if (((Boolean)map.get("falg")).booleanValue()) {
//	        String pic1 = (String)map.get("pic1");
//	        String pic2 = (String)map.get("pic2");
//	        super.getRequest().setAttribute("topImg", pic1);
//	        super.getRequest().setAttribute("rightImg", pic2);
//	        getRequest().setAttribute("jump1", (String)map.get("jump1"));
//	        getRequest().setAttribute("jump2", (String)map.get("jump2"));
//	      } else {
//	    	/*  
//	        Map pictureMap = this.geProductPictureDetailService.getProductPictures();
//	        if (pictureMap.get(Integer.valueOf(0)) != null) {
//	          super.getRequest().setAttribute("topImg", ((GeProductPicture)pictureMap.get(Integer.valueOf(0))).getPictureurl());
//	        }
//	        if (pictureMap.get(Integer.valueOf(1)) != null) {
//	          super.getRequest().setAttribute("rightImg", ((GeProductPicture)pictureMap.get(Integer.valueOf(1))).getPictureurl());
//	        }*/
//	      }
//
//	    }
//	    catch (ParseException e)
//	    {
//	      e.printStackTrace();
//	    }
//
//	    super.getRequest().setAttribute("existDiscountFlag", existDiscountFlag);
//
//	    super.getRequest().setAttribute("existLuckDrawFlag", (Boolean)this.addServiceActivityService.activityBoola("2", eid, new String[] { "1" }).get("falg"));
//	  }
	
	/**
	 * 查询出活动所使用的产品 
	 * @return
	 * @throws Exception
	 */
	public String findGeActivitiesProduct() throws Exception{
		List<GeDirectory> geDirectorys = geDirectoryService.findGeDirectoryByEids(eids.split(","));
		addServiceActivityService.setGeDirectoryType(geDirectorys);//设置车 非车 卡 
		getRequest().setAttribute("geDirectorys", geDirectorys);
		return SUCCESS;
	}
	//这个方法用于更新页面的图片的展示
	public void viePictureForUploadPage(GeAddServiceActivity geAddServiceActivity){
		List<GeActivitiesPicture> geActivitiesPictureList = geAddServiceActivity.getGeActivitiesPictures();
		if(geActivitiesPictureList!=null&&geActivitiesPictureList.size()>0){
			java.util.Map<String, String> map = new HashMap<String, String>();
			for(int i=0;i<5;i++){
				map.put((i+1)+"", "no");
			}
			
			for (GeActivitiesPicture  geActivitiesPictureTemp : geActivitiesPictureList) {
				map.put(geActivitiesPictureTemp.getSerialNo(),"yes");
			}
			//
			List<GeActivitiesPicture> geActivitiesPictureTempList= new ArrayList<GeActivitiesPicture>();
			
			for(int i=1;i<=5;i++){
				if(map.get(i+"").equals("no")){
					GeActivitiesPicture geActivitiesPictureTemp = new GeActivitiesPicture();
					geActivitiesPictureTemp.setNooryes("no");
					geActivitiesPictureTempList.add(geActivitiesPictureTemp);
				}else{
					String serialNoTemp = i+"";
					for(GeActivitiesPicture geActivitiesPictureTemp:geActivitiesPictureList){
						if(geActivitiesPictureTemp.getSerialNo().equals(serialNoTemp)){
							geActivitiesPictureTemp.setNooryes("yes");
							geActivitiesPictureTempList.add(geActivitiesPictureTemp);
						}
					}
				}
			}
			geAddServiceActivity.setGeActivitiesPictures(geActivitiesPictureTempList);
		}//end if(geActivitiesPictureList!=null&&geActivitiesPictureList.size()>0){
		
		//设置ruleIds
		List<GeActivitiesRule> geActivitiesRuleList=  geAddServiceActivity.getGeActivitiesRules();
		if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
			rulds = "";
			for (GeActivitiesRule geActivitiesRule : geActivitiesRuleList) {
				rulds +=geActivitiesRule.getRuleId()+",";
			}
			rulds =rulds.substring(0,rulds.length()-1);
		}
	}
	//这个是用于拿到产品的EID
	public String getOldProductEid(GeAddServiceActivity geAddServiceActivity){
		String temp = "";
		List<GeActivitiesProduct> geActivitiesProductList = geAddServiceActivity.getGeActivitiesProducts();
		for (GeActivitiesProduct geActivitiesProduct : geActivitiesProductList) {
			temp = temp+geActivitiesProduct.getEid()+",";
		}
		if(temp.length()>0){
		temp = temp.substring(0,temp.length()-1);
		}
		return temp;
	}
	
	public String selectSingleTask(){
//		String type = getRequest().getParameter("type");
//		String taskID = getRequest().getParameter("taskID");
//		String workFlowID = getRequest().getParameter("workFlowID");
//		marktingWrokFlow = addServiceActivityService.findAddGeAddServiceByTaskId(taskID, workFlowID);
//		geAddServiceActivity = (GeAddServiceActivity)marktingWrokFlow.getEntity();
//		List<GeActivitiesRule> list = geAddServiceActivity.getGeActivitiesRules();
//		for(GeActivitiesRule geActivitiesRuleTemp :list){
//			List<GeThirdParterService> serviceList = geActivitiesRuleTemp.getGeThirdParterServiceList();
//			if(serviceList!=null&&serviceList.size()>0){
//				for(GeThirdParterService geThirdParterServiceTemp:serviceList){
//					geThirdParterServiceTemp.getItemName();
//				}
//			}
//			
//		}
//		
//		//查询地区start
//		Map map = (Map) super.getSession().getAttribute("permission");//省市
//		String authorityid = (String) map.get("ROLE_B_AAGA_I");
//		setGeDepartmentTypeList(authorityid);//给省的geDepartmentTypeList赋值
//		setCityPropertyList(list,authorityid,geAddServiceActivity.getBusinessArea());//给市赋值
//		//查询地区end
//		
//		//areaList  显示
//		geCodeActivityPatternList = geCodeService.findAllByGeCodeType("ActivityPattern");//查询活动方式类型
//		geCodeBusinessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");//查询业务领域
//		getRequest().setAttribute("type", type);
		return SUCCESS;
	}
	
	/***
	 * 审核通过
	 * @return
	 */
	public String auditActivity(){
		String type = getRequest().getParameter("type");
		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
		addServiceActivityService.auditAcvity(taskID, workFlowID,operator.getOperatorid());
		flag = 1;
		message = "发布成功";
		return SUCCESS;
	}
	
	/**×
	 * 打回
	 * @return
	 */
	public String failureAutit(){
		String type = getRequest().getParameter("type");
		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
		addServiceActivityService.failureAutit(taskID, workFlowID,operator.getOperatorid());
		flag = 1;
		message = "下发修改成功";
		return SUCCESS;
	}
	
	public String updateAddGeAddServiceActivity() throws UnsupportedEncodingException{
		geAddServiceActivity.setUploadPicture(uploadPicture);
		geAddServiceActivity.setUploadPictureContentType(uploadPictureContentType);
		geAddServiceActivity.setUploadPictureFileName(uploadPictureFileName);
		geAddServiceActivity.setUploadImagePath(super.getRequest().getRealPath("/upload/images/imageP/"));
		geAddServiceActivity.setUploadSavePath(imagePath);
		if(cn.com.sinosoft.util.string.StringUtils.isBlank(geAddServiceActivity.getDeptID())){
			geAddServiceActivity.setDeptID(geAddServiceActivity.getProvince());
		}
		geAddServiceActivity.setUpdateDeptId(getCreateDeptId());//更新者机构ID
		setGeActivitiesRuleDiscountRemarkText(geAddServiceActivity);
		String uploadPictureSerialNos = super.getRequest().getParameter("uploadPictureSerialNos");
		addServiceActivityService.updateAddGeAddServiceActivityAndGeActivitiesRule(geAddServiceActivity,uploadPictureSerialNos,rulds);
		flag = 1;
		message = "修改成功";
		return SUCCESS;
	}
	public String deleteAddGeAddServiceActivity(){
		addServiceActivityService.deleteAddGeAddServiceActivity(activityId);
		flag = 1;
		message = "删除成功！";
		return SUCCESS;
	}
	
	public String findGeActivitiesConfig(){
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		page = addServiceActivityService.queryGeActivitiesConfig(geActivitiesConfig, pageNo, pageSize);
		if(page!=null){
			super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
			super.getRequest().setAttribute("pageSize", page.getPageSize());
			super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
			super.getRequest().setAttribute("totalCount", page.getTotalCount());
		}
		productTypeMap = geCodeService.findAllCodeAndName("ProductType");
		return SUCCESS;
	}
	/**
	 * 进入修改活动产品信息页面
	 * @return
	 */
	public String UpdateProductInfo(){
		return SUCCESS;
	}
	/**
	 * 准备进入加购产品查询的页面
	 * @return
	 */
	public String prepareSelectAddShopping(){		
		//查询业务领域
		//deptId=${deptId}&xRule=${param.xRule}&yAddShopping=${param.yAddShopping}
		GeDepartment geDepartment= geDepartmentService.findGeDepartmentByPK(deptId);
		String businessAreaFlag = geDepartment.getBusinessarea();
		geCodeBusinessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");//查询业务领域
		//得查询出该deptID是父结点还是子结点
		QueryRule queryRule  = QueryRule.getInstance();
		queryRule.addEqual("parentid", deptId);
		List<GeDepartment>  geDepartmentList= geDepartmentService.findAllDepartments(queryRule);
		if(geDepartmentList!=null&&geDepartmentList.size()>0){
			super.getRequest().setAttribute("isParentFlag", "yes");//父结点
		}else{
			super.getRequest().setAttribute("isParentFlag", "no");//子结点
			super.getRequest().setAttribute("deptName", geDepartment.getDeptname());//子结点
		}
		
		super.getRequest().setAttribute("businessAreaFlag", businessAreaFlag);//业务领域
		super.getRequest().setAttribute("xRule", super.getRequest().getParameter("xRule"));//X坐标
		super.getRequest().setAttribute("yAddShopping", super.getRequest().getParameter("yAddShopping"));//Y坐标
		super.getRequest().setAttribute("saleDept", deptId);//销售地区
		
		String operation = super.getRequest().getParameter("operation");
		if(operation!=null&&!"".equals(operation)){
			if(operation.equals("addProduct")){//增加产品  渠道来的
				return "addProduct";
			}
			if(operation.equals("addShoppingroduct")){//从加购产品渠道来的
				return "addShoppingroduct";
			}
		}
		return SUCCESS;//默认从加购产品来的 
	}
	/**
	 * 准备进入加购产品查询的页面
	 * @return
	 */
	public String prepareSelectAddShopping1(){
		
		//查询业务领域
		//deptId=${deptId}&xRule=${param.xRule}&yAddShopping=${param.yAddShopping}
		GeDepartment geDepartment= geDepartmentService.findGeDepartmentByPK(deptId);
		String businessAreaFlag = geDepartment.getBusinessarea();
		geCodeBusinessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");//查询业务领域
		//得查询出该deptID是父结点还是子结点
		QueryRule queryRule  = QueryRule.getInstance();
		queryRule.addEqual("parentid", deptId);
		List<GeDepartment>  geDepartmentList= geDepartmentService.findAllDepartments(queryRule);
		if(geDepartmentList!=null&&geDepartmentList.size()>0){
			super.getRequest().setAttribute("isParentFlag", "yes");//父结点
		}else{
			super.getRequest().setAttribute("isParentFlag", "no");//子结点
			super.getRequest().setAttribute("deptName", geDepartment.getDeptname());//子结点
		}
		
		super.getRequest().setAttribute("businessAreaFlag", businessAreaFlag);//业务领域
		super.getRequest().setAttribute("xRule", super.getRequest().getParameter("xRule"));//X坐标
		super.getRequest().setAttribute("yAddShopping", super.getRequest().getParameter("yAddShopping"));//Y坐标
		super.getRequest().setAttribute("saleDept", deptId);//销售地区
		
		String operation = super.getRequest().getParameter("operation");
		if(operation!=null&&!"".equals(operation)){
			if(operation.equals("addProduct")){//增加产品  渠道来的
				return "addProduct";
			}
//			if(operation.equals("addShoppingProduct")){//从加购产品渠道来的
//				
//			}
		}

		return SUCCESS;//默认从加购产品来的 
	}
	
	/**
	 * 查询险种表中的数据，并且能够代出eid
	 * @return
	 */
	public String findGeRisk(){
		
		String forwardType = setForwardTypeValue();//活动使用产品:activityProduct    加购产品addProduct
		businessArea = super.getRequest().getParameter("businessArea");
		String riskCode = super.getRequest().getParameter("riskCode");
		String riskCName = super.getRequest().getParameter("riskCName");
		
		GeRisk geRisk = new GeRisk();
		geRisk.setRiskCode((riskCode!=null||!riskCode.equals(""))?riskCode:null);
		geRisk.setBusinessArea("3");
		geRisk.setRiskCName((riskCName!=null||!riskCName.equals(""))?riskCName:null);
		if(forwardType.equals("activityProduct")){//活动使用产品
			selectType = SelectType.Car.name();
			List<GeRisk>  geRiskList= bizCommonService.findGeRiskListWithEid(geRisk);//直接查询不分页
			super.getRequest().setAttribute("geRiskList", geRiskList);
			//活动使用产品 end
			
		}else{//加购产品
			
			if (pageNo == 0) {
				pageNo = 1;
			}
			if (pageSize == 0) {
				pageSize = 10;
			}
		page = bizCommonService.findGeRiskListWithEid(geRisk,pageNo, pageSize);
		//businessAreaList =
		geCodeBusinessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");//查询业务领域
		if(page!=null){
			super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
			super.getRequest().setAttribute("pageSize", page.getPageSize());
			super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
			super.getRequest().setAttribute("totalCount", page.getTotalCount());
			super.getRequest().setAttribute("xrule", super.getRequest().getParameter("xrule"));
			super.getRequest().setAttribute("yAddShopping", super.getRequest().getParameter("yAddShopping"));
			
			}
		}//加购产品 end
		//return forwardType.equals("activityProduct") ? SUCCESS : "addProduct";
		if(forwardType.equals("activityProduct"))
			return SUCCESS;
		else
			return  "addProduct";
	}
	private String setForwardTypeValue(){
		String operation = super.getRequest().getParameter("operation");
		String forwardType = "activityProduct";//活动使用产品
		if(operation!=null&&!"".equals(operation)&&operation.equals("addProduct")){
				forwardType = "addProduct";//活动加购产品
		}
		return forwardType;
	}
	/**
	 * 查询非车表里的数据，并且能够代出eid
	 * @return
	 */
	public String findGeProductMain(){
		String forwardType = setForwardTypeValue();//活动使用产品:activityProduct    加购产品addProduct
		//********common   start
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		String coreProductSimpleName = super.getRequest().getParameter("coreProductSimpleName");
		String saleDept = super.getRequest().getParameter("saleDept");
		
		
		//判断父结点还是子结点的代码 start
		boolean isParentFlag ;
		isParentFlag = setIsParentFlagProperty(saleDept);//
		//判断父结点还是子结点的代码 end
		
		String sqlForward = "";  //1.父结点 地区代码为 in('deptId')  2.父结点的parentId = id  3.子结点的departId=
		String saleDeptSelectCode = super.getRequest().getParameter("saleDeptSelectCode");//地区代码
		sqlForward = setSqlForwardValue(isParentFlag,saleDeptSelectCode);
		String useDeptIdForSql = setUseDeptIdForSqlValue(sqlForward,saleDeptSelectCode);
		
		GeProductMain geProductMain = new GeProductMain();
		geProductMain.setBusinessArea(setBusinessAreaValue(saleDeptSelectCode,saleDept));
		geProductMain.setCoreProductCode(coreProductCode==null||coreProductCode.equals("")?null:coreProductCode);
		geProductMain.setCoreProductSimpleName(coreProductSimpleName==null||coreProductSimpleName.equals("")?null:coreProductSimpleName);
		
		if(forwardType.equals("activityProduct")){//活动使用产品:activityProduct 
			if(saleDept.equals("2440300")||saleDept.equals("2500000")||saleDept.equals("2310000")||saleDept.equals("2330200")||saleDept.equals("2350200")||saleDept.equals("2370200")||saleDept.equals("2110000")||saleDept.equals("2120000")||saleDept.equals("2210200")){
				GeDepartment geDepartment= geDepartmentService.findGeDepartmentByPK(saleDept);
				String businessAreaFlag = geDepartment.getBusinessarea();
				sqlForward=businessAreaFlag;	
				useDeptIdForSql=businessAreaFlag;
				geProductMain.setBusinessArea(businessAreaFlag);
				selectType = SelectType.ProductMain.name();
				List<GeProductMain> geProductMainList= productManageService.findProductMainWithEidList(geProductMain, sqlForward, useDeptIdForSql);
				super.getRequest().setAttribute("geProductMainList", geProductMainList);
			}else{
				selectType = SelectType.ProductMain.name();
				List<GeProductMain> geProductMainList= productManageService.findProductMainWithEidList(geProductMain, sqlForward, useDeptIdForSql);
				super.getRequest().setAttribute("geProductMainList", geProductMainList);
			}
			
		}else{//加购产品addProduct
			
			if (pageNo == 0) {
				pageNo = 1;
			}
			if (pageSize == 0) {
				pageSize = 10;
			}
		page = productManageService.findProductMainBySaleDept(geProductMain, sqlForward, pageNo, pageSize, useDeptIdForSql);//useDeptIdForSql
		geCodeBusinessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");//查询业务领域
		if(page!=null){
			super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
			super.getRequest().setAttribute("pageSize", page.getPageSize());
			super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
			super.getRequest().setAttribute("totalCount", page.getTotalCount());
			super.getRequest().setAttribute("xrule", super.getRequest().getParameter("xrule"));
			super.getRequest().setAttribute("yAddShopping", super.getRequest().getParameter("yAddShopping"));
		}
		
		
		}//end 加购产品addProduct
		
		
		return forwardType.equals("activityProduct") ? SUCCESS : "addProduct";
	}
	
	//设置是否为你结点
	//findGeCardProduct  findGeProductMain 这两上方法 都使用到这个方法改的时候要注意
	private boolean setIsParentFlagProperty(String saleDept){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("parentid", saleDept);
		List<GeDepartment> geDepartmentList = geDepartmentService.findAllDepartments(queryRule);
		if(geDepartmentList!=null&&geDepartmentList.size()>0){
			return  true;
		}else{
			return false;
		}
	}
	//设置SQL的转向
	private String setSqlForwardValue(boolean isParentFlag,String saleDeptSelectCode){
		String sqlForward = "";
		if(isParentFlag){//父结点的话
			if(saleDeptSelectCode!=null&&!saleDeptSelectCode.equals("")){//地区代码里有值  可以写
				//走 deptId in('');
				//1.in
				sqlForward = "1";
			}else{// 地区代码里没有值  走默认的    传的是 saleDept
				// 2 
				sqlForward = "2";
			}
			
		}else{//子结点
			
			//直接传一个  只有一个结点  saleDept
			// 3 
			sqlForward = "3";
		}
		return sqlForward;
	}
	private String setUseDeptIdForSqlValue(String sqlForward ,String saleDeptSelectCode){
		String result = "";
		if(sqlForward.equals("1")){//1.父结点 地区代码为 in('deptId')
			String[] deptids = saleDeptSelectCode.split(",");
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<deptids.length;i++){
				sb.append("'");
				sb.append(deptids[i]);
				sb.append("'");
				sb.append(",");
			}
			String  a = sb.toString();
			result = a.substring(0, a.length()-1);
		}else if(sqlForward.equals("2")){// 2.父结点的parentId = id
			String saleDept = super.getRequest().getParameter("saleDept");
			result = (  "".equals(saleDeptSelectCode)  ) ?  saleDept :saleDeptSelectCode;
		}else if(sqlForward.equals("3")){
			
			result = saleDeptSelectCode;
		};//3.子结点的departId=
		return result;
	}
	//设置业务领域
	private String setBusinessAreaValue(String saleDeptSelectCode,String saleDept){
		String result = null;
		if(saleDeptSelectCode==null||saleDeptSelectCode.equals("")){//地区代码为空,说明一定是父结点 如果是集团的话，不设置值
			
			String temp = saleDept.substring(0,1);
			if(!temp.equals("1")){//即排除集团这种情况
				result = "'"+temp+"'";
			}
			
		}else{//地区代码不为空,说明可能是北京 也可能是选了一堆城市可能包括寿北京，财北京
			//所以 businessArea in()
			String[] deptIds = saleDeptSelectCode.split(",");
			Set<String> set = new HashSet<String>();
			for(int i=0;i<deptIds.length;i++){
				String a = deptIds[i].substring(0,1);
				set.add(a);
			}
			Iterator iterator = set.iterator();
			StringBuilder sb = new StringBuilder();
			while(iterator.hasNext()){
				String b = iterator.next().toString();
				sb.append("'");
				sb.append(b);
				sb.append("'");
				sb.append(",");
			}
			
			if(sb.toString()!=null&&!sb.toString().equals("")){
				String c = sb.toString();
				c = c.substring(0, c.length()-1);
				result = c;
			}
		}
		return result;
	}
	
	//以下方法 为作流而使用的   start
	//*********************************************************************************************************************
	//*********************************************************************************************************************
	//*********************************************************************************************************************
	/**
	 * 查询本机构下的任务(查工作流的表)
	 */
	public String findGeAddServiceActivityWorkFlow() {
		//remove super.getRequest().setAttribute("type", super.getRequest().getParameter("type"));   控制按钮是事显示
//		long totalCount = 0L;
//		long totalPage = 0L;
//		String status = this.getRequest().getParameter("status");
//		List<EntityWorkFlow> result = new ArrayList<EntityWorkFlow>();
//		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
//		Map<String, Set<String>> setMap = getWorkFlowGroup(operator.getOperatorid());// 得到用户所在工作流的节点的用户组 ，与流程
//		try{
//			Set<String> inGroups = setMap.get("group");
//			Set<String> inTasks = setMap.get("taskID");
//			//没有在页面选择机构权限，则从session中获取当前登录用户的机构权限
//			String authorityid = super.getRequest().getParameter("authorityid");
//			if(StringUtils.isBlank(authorityid)){//ROLE_B_AAGA_S
//				Map map = (Map) super.getSession().getAttribute("permission");
//				authorityid = (String) map.get("ROLE_B_AAGA_S");   //组织机构权限为营销活动的代码 
//			}
//			if(inGroups!=null && !inGroups.isEmpty() && inTasks!=null && !inTasks.isEmpty() && authorityid!=null && !"".equals(authorityid)){
//			//获取查询机构
//			StringBuffer stringBuffer = new StringBuffer();
//			String[] arrayAuth = authorityid.split(",");
//			for(String str : (Set<String>)setMap.get("group")){
//				for(int i = 0; i < arrayAuth.length; i++){
//					stringBuffer.append(str + arrayAuth[i] + ",");
//				}
//			}
//
//			// 组+组织机构编码 的拼接字符串
//			String groupIDsString = stringBuffer.substring(0,stringBuffer.lastIndexOf(","));
//			// 用户类型  暂时存在为空
//			//remove String agencyAuthority = geProposalIntention.getUserType();
//			String agencyAuthority = "";
//			// 流程id 的拼接字符串
//			StringBuffer workFlowBuffer = new StringBuffer();
//			
//			Set<String> sets = setMap.get("taskID");
//			if( !sets.isEmpty()){
//				for(String str : (Set<String>)setMap.get("taskID")){
//					workFlowBuffer.append(str + ",");
//				}
//				String workFlowID = workFlowBuffer.substring(0,workFlowBuffer.lastIndexOf(","));
//				TaskQuery tq = taskService.createTaskQuery();
//				/*//用户类型
//					if(agencyAuthority==null){
//						agencyAuthority = "";
//		             }*/
//				// 状态
//				if(status!=null && !status.equals("")){
//						tq.processVariableValueEquals("entity_Status", status);
//				}
//				if(geAddServiceActivity.getActivityName()!=null && !geAddServiceActivity.getActivityName().equals("")){
//					//tq.processVariableValueLike("entity_activityName", geAddServiceActivity.getActivityName());
//					tq.processVariableValueEquals("entity_activityName", geAddServiceActivity.getActivityName());
//				}
//				totalCount = workFlowServiceImpl.getTotalCount(groupIDsString, workFlowID, tq);
//				totalPage = totalCount % pageSize != 0 ? totalCount / pageSize + 1L : totalCount / pageSize;
//				//result = markingWorkFlowService.getCandidateTaskByPage(groupIDsString, workFlowID, agencyAuthority, pageNo, pageSize);  //这个代码是以前小罗写的
//				result = workFlowServiceImpl.getCandidateTaskByPage(groupIDsString, workFlowID, tq,  pageNo, pageSize);
//			}else{
//				totalCount = 0L;
//				totalPage = 0L;
//				result = new ArrayList<EntityWorkFlow>();
//			}
//		}
//		}catch(Exception ex){
//			ex.printStackTrace();
//			totalCount = 0L;
//			totalPage = 0L;
//			result = new ArrayList<EntityWorkFlow>();
//		}
//		super.getRequest().setAttribute("workFlowList", result);
//		super.getRequest().setAttribute("totalCount", totalCount);
//		super.getRequest().setAttribute("totalPage", totalPage);
//		super.getRequest().setAttribute("pageNo", pageNo);
//		super.getRequest().setAttribute("pageSize", pageSize);
		return SUCCESS;
	}
	

	/**
	 *、
	 * 查询本机构下的任务(查工作流的表)  findGeAddServiceActivityWorkFlow()  为这个方法而服务
	 *
	 * 
	 * 返回用户所在工作流的用户组 ,返回长度为2数组的集合集合，元素1为所属组，元素2为工作流
	 * @param @param userID		登录用户ID
	 */
	private Map<String, Set<String>> getWorkFlowGroup(String userID){
//		GeWorkflowId id = new GeWorkflowId();
//		id.setFuncitontype("WORKFLOWCONFIG");
//		id.setArea("CC");
//		id.setFiletype("0");
//		GeWorkflow geWorkflow =geWorkFlowService.findGeWorkflowByPK(id);//获取工作流人员配置信息
//		if(geWorkflow == null)return null;
//		InputStream ins = new ByteArrayInputStream(geWorkflow.getFilecontent());
//		
//		Map<String, Object> map = new WorkFlowUtil().getGroupAll(ins);
//		Set<String> set = (Set<String>)map.get("groupID");//所有工作流的组
//		Set<String> setGroup = new HashSet<String>();
//		
//		for(String str : set){//筛选出登陆人员所在组
//			if(geGroupService.isExistUser(userID, str)){
//				setGroup.add(str);
//			}
//		}
//		
//		Set<String> setArray = new HashSet<String>(); //操作员所在组对应工作流的key
//		for(Entry<String, Object> entry : map.entrySet()){
//			if(!entry.getKey().equals("groupID")){
//				Map<String ,Object> mm = (Map<String ,Object>)entry.getValue();
//				for(Entry<String, Object> e : mm.entrySet()){
//					for(String str : setGroup){
//						if(str.equals(e.getValue())){
//							// 筛选工作流id带"geProposalIntention"的
//							//if(entry.getKey().indexOf("geProposalIntention") >= 0) //这个是要改的   对应文件的表
//							if(entry.getKey().indexOf("marketingWorkFlow") >= 0) //这个是要改的   workFlowID  为营销活动的
//								setArray.add(entry.getKey());
//						}
//					}
//				}
//			}
//		}
		Map<String, Set<String>> setMap = new HashMap<String, Set<String>>();
//		setMap.put("group", setGroup);
//		setMap.put("taskID", setArray);
		return setMap;
	}
	
	
	
	/**
	 * 
	 * 提交审核  到  工作流 状态
	 *  
	 * 重新分发到工作流 状态
	 * 保存投诉信息
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String submitApplyMarketing() throws IOException, Exception{
		String activityId = super.getRequest().getParameter("activityId");
		//保存处理轨迹
		String taskId = super.getRequest().getParameter("taskId");//从页面过来的
		String workflowId = super.getRequest().getParameter("workflowId");//从页面过来的
		GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
		//用户轨迹的查询
		GeAddServiceProcess geAddServiceProcess = new GeAddServiceProcess();//
		geAddServiceProcess.setHandleDept(geOperator.getDeptid());//当前处理机构
		geAddServiceProcess.setOperatorID(geOperator.getOperatorid());//操作人编号
		geAddServiceProcess.setHandleDate(new Date());//处理日期
		geAddServiceProcess.setHandleStatus("1");//处理状态  同意 还是   不同意
		geAddServiceProcess.setOptions("同意");//处理意见    
		
		
		addServiceActivityService.startTask(geAddServiceProcess,activityId,taskId,workflowId);//开启工作流状态
		JSONObject backJson = new JSONObject();
		backJson.put("backdata", "01");
		this.renderText("01");
		return NONE;  //转向页面还没有弄
	} 
	
	// 工作流的处理完成
	public String doFinish() throws IOException, Exception{
    	String taskId = super.getRequest().getParameter("taskId");//从页面过来的
		String workflowId = super.getRequest().getParameter("workflowId");//从页面过来的
		String activityId = super.getRequest().getParameter("activityId");//从页面过来的
		
		//当前的处理机构
		GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
		//用户轨迹的查询
		GeAddServiceProcess geAddServiceProcess = new GeAddServiceProcess();//
		geAddServiceProcess.setHandleDept(geOperator.getDeptid());//当前处理机构
		geAddServiceProcess.setOperatorID(geOperator.getOperatorid());//操作人编号
		geAddServiceProcess.setHandleDate(new Date());//处理日期
		geAddServiceProcess.setHandleStatus("1");//处理状态  同意 还是   不同意
		geAddServiceProcess.setOptions("通过");//处理意见    
		addServiceActivityService.completeTask(geAddServiceProcess, activityId, taskId, workflowId);//工作流做处理
		this.renderText("01");
		return NONE; 
	}
	
	/**
	 * 工作流  回退  状态(现在方法用于撤销功能)
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	// 工作流回退(现在方法用于撤销功能)
	public String doRoolBack() throws IOException, Exception{
		// taskId,带过来的数据
		// workflowId  带过来的数据
		String taskId = super.getRequest().getParameter("taskId");
		String workflowId = super.getRequest().getParameter("workflowId");
		String activityId = super.getRequest().getParameter("activityId");
		String status ="5";// 回退状态设为5，撤销状态
		//当前的处理机构
		GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
		//用户轨迹的查询
		GeAddServiceProcess geAddServiceProcess = new GeAddServiceProcess();//
		geAddServiceProcess.setHandleDept(geOperator.getDeptid());//当前处理机构
		geAddServiceProcess.setOperatorID(geOperator.getOperatorid());//操作人编号
		geAddServiceProcess.setHandleDate(new Date());//处理日期
		geAddServiceProcess.setHandleStatus("2");//处理状态  同意 还是   不同意
		geAddServiceProcess.setOptions("不同意");//处理意见    
		//addServiceActivityService.completeTask(geAddServiceProcess, activityId, taskId, workflowId);//工作流做处理
		addServiceActivityService.doRoolBack(geAddServiceProcess, activityId, taskId, workflowId,status);
		this.renderText("01");
		return null;
		
	}
	/**
	 * 工作流  回退  状态
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	// 工作流回退(现在方法用于撤销功能)
	public String doGiveUp() throws IOException, Exception{
		// taskId,带过来的数据
		// workflowId  带过来的数据
//		String taskId = super.getRequest().getParameter("taskId");
//		String workflowId = super.getRequest().getParameter("workflowId");
//		String activityId = super.getRequest().getParameter("activityId");
//		String status="2";// 回退状态设为0，待审核状态
//		boolean result = workFlowServiceImpl.isFirstTask(taskId);
//		if(result){
//			this.renderText("02");
//			return NONE;
//		}
////		当前的处理机构
//		GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
//		//用户轨迹的查询
//		GeAddServiceProcess geAddServiceProcess = new GeAddServiceProcess();//
//		geAddServiceProcess.setHandleDept(geOperator.getDeptid());//当前处理机构
//		geAddServiceProcess.setOperatorID(geOperator.getOperatorid());//操作人编号
//		geAddServiceProcess.setHandleDate(new Date());//处理日期
//		geAddServiceProcess.setHandleStatus("2");//处理状态  同意 还是   不同意
//		geAddServiceProcess.setOptions("不同意");//处理意见    
//		//addServiceActivityService.completeTask(geAddServiceProcess, activityId, taskId, workflowId);//工作流做处理
//		addServiceActivityService.doRoolBack(geAddServiceProcess, activityId, taskId, workflowId,status);
//		this.renderText("01");
		return null;
		
	}
	/**
	 * 工作流  放弃  状态
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	// 工作流放弃(现在方法用于放弃功能)
	public String doQuit() throws IOException, Exception{
		// taskId,带过来的数据
		// workflowId  带过来的数据
//		String taskId = super.getRequest().getParameter("taskId");
//		String workflowId = super.getRequest().getParameter("workflowId");
//		String activityId = super.getRequest().getParameter("activityId");
//		String status="6";// 回退状态设为0，待审核状态
//		// 根据taskID得到EntityWorkFlow
//		EntityWorkFlow c = workFlowServiceImpl.getSingleTask(taskId);
////		当前的处理机构
//		GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
//		//用户轨迹的查询
//		GeAddServiceProcess geAddServiceProcess = new GeAddServiceProcess();//
//		geAddServiceProcess.setHandleDept(geOperator.getDeptid());//当前处理机构
//		geAddServiceProcess.setOperatorID(geOperator.getOperatorid());//操作人编号
//		geAddServiceProcess.setHandleDate(new Date());//处理日期
//		geAddServiceProcess.setHandleStatus("2");//处理状态  同意 还是   不同意
//		geAddServiceProcess.setOptions("不同意");//处理意见    
//		//addServiceActivityService.completeTask(geAddServiceProcess, activityId, taskId, workflowId);//工作流做处理
//		addServiceActivityService.doRoolBack(geAddServiceProcess, activityId, taskId, workflowId,status);
//		this.renderText("01");
		return null;
		
	}
	//以上方法 为作流而使用的   end 
	//*********************************************************************************************************************
	//*********************************************************************************************************************
	//*********************************************************************************************************************
	
	
	//以下各方法均为action在组装数据时所要用到的方法
	private String  getArea(GeAddServiceActivity geAddServiceActivity){
		String deptId = geAddServiceActivity.getDeptID();
		deptId = deptId.substring(0, 1);
		return deptId;
		
		
		
	}
	/**
	 * 将geCode的方法封装了一下
	 * @param codeType
	 * @return
	 * @throws Exception
	 */
	private List<GeCode> getGecode(String codeType) throws Exception{
		List<GeCode> geCodes = new ArrayList<GeCode>();
		QueryRule queryRuleTemp  = QueryRule.getInstance();
		queryRuleTemp.addEqual("businessArea", "3");//业务领域(1-集团；2-寿险；3-财险；4-养老险；9-其他)
		queryRuleTemp.addEqual("validInd", "1");//0无效1有效
		queryRuleTemp.addEqual("codeType", codeType);
		GeCodeType geCodeTypeSex  = geCodeTypeService.findGeCodeType(queryRuleTemp);
		geCodes  =  removeInValidInd(geCodeTypeSex.getGeCodes());
		Collections.sort(geCodes, new OrderComparator());//排序
		return geCodes;
	}
	/**
	 *	将状态为ValidInd=0无效的去掉
	 * @return
	 */
	private List<GeCode> removeInValidInd(List<GeCode> geCodeList){
		if(geCodeList!=null&&geCodeList.size()>0){
			Iterator iterator = geCodeList.iterator();
			while(iterator.hasNext()){
				GeCode geCode =(GeCode)iterator.next();
				if(geCode.getValidInd()!=null&&geCode.getValidInd().equals("0")){
					iterator.remove();
				}
			}
		}
		return geCodeList;
	}
	
	/**
	 * 内容排序
	 *  
	 *
	 */
	class OrderComparator implements Comparator<GeCode>{
		@Override
		public int compare(GeCode o1, GeCode o2) {
			// TODO Auto-generated method stub
			return o1.getOrderNo().compareTo(o2.getOrderNo());
		}
	}
	
	//取前台图片的
	public String testPicture() throws Exception{
		//List<> addServiceActivityService.getGeActivitiesPictureList(eid, "3440300");
		
		List<GeActivitiesPicture> geActivitiesPictureList = addServiceActivityService.getGeActivitiesPictureList("G300009", "3440300");
		return NONE;
	}
	
	/**
	 * 查询省
	 * @return
	 * @throws Exception 
	 */
	public String selectProvince() throws Exception{
		/**
		 * 1.businessID  是什么就是什么
		 * 2.parentId    集团不用调用你    寿财养老时parentID=1
		 * 3.寿险具有的       那个人本人具有的机构权限
		 */
		Map map = (Map) super.getSession().getAttribute("permission");//省市
		String authorityid = (String) map.get("ROLE_B_AAGA_I");
		String parentId = getRequest().getParameter("BusinessArea");//从页面上取的
		List<String[]> geDepartmentList = new ArrayList<String[]>();//getAllChildAuthDepart();
		List<String[]> geDepartmentListUpdept = new ArrayList<String[]>();//getAllChildAuthDepart();
		String activityId= getRequest().getParameter("actId");
		String activtyNum="";
		String deptBusinessArea="";
		//
		if(authorityid!=null&&!authorityid.equals("")){
			geDepartmentList = geDepartmentService.getAllChildAuthDept(parentId,authorityid,true);//true要全部的下拉列表	
		}else{//如果没有新建权限码，就找编辑权限码
			authorityid = (String) map.get("ROLE_B_AAGA_U");
			geDepartmentList = geDepartmentService.getAllChildAuthDept(parentId,authorityid,true);//true要全部的下拉列表
		}
		if(activityId!=null&&!activityId.equals("")&&!activityId.equals("null")){
			geAddServiceActivity = addServiceActivityService.findAddGeAddServiceActivityByActivityId(activityId);
			if(geAddServiceActivity!=null&&!geAddServiceActivity.equals("")){
				authorityid=geAddServiceActivity.getDeptID();
				if(authorityid.equals("1")||authorityid.equals("2")||authorityid.equals("3")||authorityid.equals("4")){
					deptBusinessArea=authorityid;
				}
			}
			geDepartmentListUpdept = geDepartmentService.getAllChildAuthDept(parentId,authorityid,true);//true要全部的下拉列表
		}
		JSONObject backJson = new JSONObject();
		backJson.put("backdata", geDepartmentList);
		backJson.put("geDepartmentListUpdept", geDepartmentListUpdept);
		backJson.put("deptBusinessArea", deptBusinessArea);
		super.render(backJson.toString(), "application/json;charset=GBK");
		return NONE;
	}
	
	/**
	 * 查询城市
	 * @return
	 * @throws Exception 
	 */
	public String selectCity() throws Exception{
		String authorityidDept="";
		Map map = (Map) super.getSession().getAttribute("permission");//省市
		String authorityid = (String) map.get("ROLE_B_AAGA_I");
		String parentId = getRequest().getParameter("ProvinceId");//从页面上取的
		String activityId= getRequest().getParameter("actId");
		if(activityId!=null&&!activityId.equals("")&&!activityId.equals("null")){
			geAddServiceActivity = addServiceActivityService.findAddGeAddServiceActivityByActivityId(activityId);
			if(geAddServiceActivity!=null&&!geAddServiceActivity.equals("")){
				authorityidDept=geAddServiceActivity.getDeptID();
			}
		}
		
		List<String[]> geDepartmentList = new ArrayList<String[]>();//getAllChildAuthDepart();
		geDepartmentList = geDepartmentService.getAllChildAuthDept(parentId,authorityid,true);
	
		boolean municipalityFlag = false;
		if(geDepartmentList.size()==0){//直辖市 北京天津等
			municipalityFlag = true;
			GeDepartment  geDepartment= geDepartmentService.findGeDepartmentByPK(parentId);
			geDepartmentList.add(new String[]{parentId,geDepartment.getDeptname()});
		}
		
		JSONObject backJson = new JSONObject();
		backJson.put("backdata", geDepartmentList);
		backJson.put("municipalityFlag",municipalityFlag);
		backJson.put("authorityidDept",authorityidDept);
		super.render(backJson.toString(), "application/json;charset=GBK");
		return NONE;
	}
	//set get method
	public GeCodeTypeService getGeCodeTypeService() {
		return geCodeTypeService;
	}
	public void setGeCodeTypeService(GeCodeTypeService geCodeTypeService) {
		this.geCodeTypeService = geCodeTypeService;
	}
	public GeAddServiceActivity getGeAddServiceActivity() {
		return geAddServiceActivity;
	}
	public void setGeAddServiceActivity(GeAddServiceActivity geAddServiceActivity) {
		this.geAddServiceActivity = geAddServiceActivity;
	}
	public AddServiceActivityService getAddServiceActivityService() {
		return addServiceActivityService;
	}
	public void setAddServiceActivityService(
			AddServiceActivityService addServiceActivityService) {
		this.addServiceActivityService = addServiceActivityService;
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
	public BizCommonService getBizCommonService() {
		return bizCommonService;
	}
	public void setBizCommonService(BizCommonService bizCommonService) {
		this.bizCommonService = bizCommonService;
	}
	public List<GeRisk> getGeRiskList() {
		return geRiskList;
	}
	public void setGeRiskList(List<GeRisk> geRiskList) {
		this.geRiskList = geRiskList;
	}
	public GeCodeService getGeCodeService() {
		return geCodeService;
	}
	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}
	public List<GeCode> getGeCodeActivityPatternList() {
		return geCodeActivityPatternList;
	}
	public void setGeCodeActivityPatternList(List<GeCode> geCodeActivityPatternList) {
		this.geCodeActivityPatternList = geCodeActivityPatternList;
	}
	public List<GeThirdParterInfo> getGeThirdParterInfoList() {
		return geThirdParterInfoList;
	}
	public void setGeThirdParterInfoList(
			List<GeThirdParterInfo> geThirdParterInfoList) {
		this.geThirdParterInfoList = geThirdParterInfoList;
	}
	public ThirdParterService getThirdParterService() {
		return thirdParterService;
	}
	public void setThirdParterService(ThirdParterService thirdParterService) {
		this.thirdParterService = thirdParterService;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public List getAreaList() {
		return areaList;
	}
	public void setAreaList(List areaList) {
		this.areaList = areaList;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<GeCode> getGeCodeBusinessAreaList() {
		return geCodeBusinessAreaList;
	}
	public void setGeCodeBusinessAreaList(List<GeCode> geCodeBusinessAreaList) {
		this.geCodeBusinessAreaList = geCodeBusinessAreaList;
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
	public void setGeDepartmentService(GeDepartmentService geDepartmentService) {
		this.geDepartmentService = geDepartmentService;
	}
	public File getAttrPictureOne() {
		return attrPictureOne;
	}
	public void setAttrPictureOne(File attrPictureOne) {
		this.attrPictureOne = attrPictureOne;
	}
	public String getAttrPictureOneFileName() {
		return attrPictureOneFileName;
	}
	public void setAttrPictureOneFileName(String attrPictureOneFileName) {
		this.attrPictureOneFileName = attrPictureOneFileName;
	}
	public File getAttrPictureTwo() {
		return attrPictureTwo;
	}
	public void setAttrPictureTwo(File attrPictureTwo) {
		this.attrPictureTwo = attrPictureTwo;
	}
	public String getAttrPictureTwoFileName() {
		return attrPictureTwoFileName;
	}
	public void setAttrPictureTwoFileName(String attrPictureTwoFileName) {
		this.attrPictureTwoFileName = attrPictureTwoFileName;
	}
//	public void setWorkFlowService(WorkFlowService workFlowService) {
//		this.workFlowService = workFlowService;
//	}
	
	public String getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<GeDepartment> getGeDepartmentTypeList() {
		return geDepartmentTypeList;
	}

	public void setGeDepartmentTypeList(List<GeDepartment> geDepartmentTypeList) {
		this.geDepartmentTypeList = geDepartmentTypeList;
	}
	public List<GeDepartment> getCityList() {
		return cityList;
	}

	public void setCityList(List<GeDepartment> cityList) {
		this.cityList = cityList;
	}
	private String authorityid;public String getAuthorityid() {
		return authorityid;
	}
	public void setAuthorityid(String authorityid) {
		this.authorityid = authorityid;
	}
	public String getReceivedObj() {
		return receivedObj;
	}
	public void setReceivedObj(String receivedObj) {
		this.receivedObj = receivedObj;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOpenType() {
		return openType;
	}
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getReceivedObjName() {
		return receivedObjName;
	}
	public void setReceivedObjName(String receivedObjName) {
		this.receivedObjName = receivedObjName;
	}
	public String getDeptIdCount() {
		return deptIdCount;
	}
	public void setDeptIdCount(String deptIdCount) {
		this.deptIdCount = deptIdCount;
	}
	public GeActivitiesConfig getGeActivitiesConfig() {
		return geActivitiesConfig;
	}
	public void setGeActivitiesConfig(GeActivitiesConfig geActivitiesConfig) {
		this.geActivitiesConfig = geActivitiesConfig;
	}
	public List<File> getUploadPicture() {
		return uploadPicture;
	}
	public void setUploadPicture(List<File> uploadPicture) {
		this.uploadPicture = uploadPicture;
	}
	public List<String> getUploadPictureFileName() {
		return uploadPictureFileName;
	}
	public void setUploadPictureFileName(List<String> uploadPictureFileName) {
		this.uploadPictureFileName = uploadPictureFileName;
	}
	public List<String> getUploadPictureContentType() {
		return uploadPictureContentType;
	}
	public void setUploadPictureContentType(List<String> uploadPictureContentType) {
		this.uploadPictureContentType = uploadPictureContentType;
	}
	public Map<String, String> getProductTypeMap() {
		return productTypeMap;
	}
	public void setProductTypeMap(Map<String, String> productTypeMap) {
		this.productTypeMap = productTypeMap;
	}
	public ProductManageService getProductManageService() {
		return productManageService;
	}
	public void setProductManageService(ProductManageService productManageService) {
		this.productManageService = productManageService;
	}
	public Map<String, String> getBusinessAreaMap() {
		return businessAreaMap;
	}
	public void setBusinessAreaMap(Map<String, String> businessAreaMap) {
		this.businessAreaMap = businessAreaMap;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	public String getYaddShopping() {
		return yaddShopping;
	}
	public void setYaddShopping(String yaddShopping) {
		this.yaddShopping = yaddShopping;
	}
	public String getXrule() {
		return xrule;
	}
	public void setXrule(String xrule) {
		this.xrule = xrule;
	}
	public GeDirectory getGeDirectory() {
		return geDirectory;
	}
	public void setGeDirectory(GeDirectory geDirectory) {
		this.geDirectory = geDirectory;
	}
	public String getOldProductEid() {
		return oldProductEid;
	}
	public void setOldProductEid(String oldProductEid) {
		this.oldProductEid = oldProductEid;
	}
	public String getRulds() {
		return rulds;
	}
	public void setRulds(String rulds) {
		this.rulds = rulds;
	}
	public List<GeCode> getGeCodePremiumTypeList() {
		return geCodePremiumTypeList;
	}
	public void setGeCodePremiumTypeList(List<GeCode> geCodePremiumTypeList) {
		this.geCodePremiumTypeList = geCodePremiumTypeList;
	}
	public Map<String, String> getGeCodePremiumTypeMap() {
		return geCodePremiumTypeMap;
	}
	public void setGeCodePremiumTypeMap(Map<String, String> geCodePremiumTypeMap) {
		this.geCodePremiumTypeMap = geCodePremiumTypeMap;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getSelectType() {
		return selectType;
	}
	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}
	public String getEids() {
		return eids;
	}
	public void setEids(String eids) {
		this.eids = eids;
	}
	
	public static void main(String[] args) throws InterruptedException {
		
//		BigDecimal b = new BigDecimal("33.3");
//		System.out.println(b.intValue());
//		System.out.println("aaa");
//		Date d1 =new Date();
//		long a = d1.getTime();
//		System.out.println("startTime="+a);
//		Thread.sleep(5000);
//		Date d2 =new Date();
//		long b = d2.getTime();
//		System.out.println("endTimpe="+b);
//		System.out.println(b-a);
	}
	public List<GeAddServiceActivity> getListAdd() {
		return listAdd;
	}

	public void setListAdd(List<GeAddServiceActivity> listAdd) {
		this.listAdd = listAdd;
	}
	
	/**
	 * 查工作流的表,返回id集合
	 */
	public List<String> findIDsFormWorkFlow() {
		List<String> returnList = new ArrayList<String>();
//		List<EntityWorkFlow> result = new ArrayList<EntityWorkFlow>();
//		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
//		Map<String, Set<String>> setMap = getWorkFlowGroup(operator.getOperatorid());// 得到用户所在工作流的节点的用户组 ，与流程
//		
//		try{
//			Set<String> inGroups = setMap.get("group");
//			Set<String> inTasks = setMap.get("taskID");
//			//没有在页面选择机构权限，则从session中获取当前登录用户的机构权限
//			String authorityid = super.getRequest().getParameter("authorityid");
//			if(StringUtils.isBlank(authorityid)){
//				Map map = (Map) super.getSession().getAttribute("permission");
//				authorityid = (String) map.get("ROLE_B_AAGA_S");
//			}
//			
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
//
//				// 流程id 的拼接字符串
//				StringBuffer workFlowBuffer = new StringBuffer();
//				
//				for(String str : (Set<String>)setMap.get("taskID")){
//					workFlowBuffer.append(str + ",");
//				}
//				String workFlowID = workFlowBuffer.substring(0,workFlowBuffer.lastIndexOf(","));
//				
//				TaskQuery tq = taskService.createTaskQuery();
//				
//				result = workFlowServiceImpl.getCandidateTaskByPage(groupIDsString, workFlowID, tq);
//				if(result.size()>0){
//					for (EntityWorkFlow entityWorkFlow : result) {
//						GeAddServiceActivity in = (GeAddServiceActivity)entityWorkFlow.getEntity();
//						if(in!=null)
//							returnList.add(in.getActivityId());
//					}
//				}
//			}
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
		return returnList;
	}

	/**
	 * 查询工作流得到代办事务条数
	 * @return
	 */
	public String getWaitMarketDoCount(){
		long totalCount = 0;
		try{
//			GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
//			// 从session中获取当前登录用户的机构权限
//			Map map = (Map) super.getSession().getAttribute("permission");
//			String authorityid = (String) map.get("ROLE_B_AAGA_S");
//			Set<String> set = map.keySet();
//			
//			// 有投保意向管理维护权限
//			if(set.contains("ROLE_B_AAGA_S")){
//				// 有重新分发权限，查撤销条数
//				if (set.contains("ROLE_ROLE_B_AAGA_AGAIN")) {
//					
//					StringBuffer stringBuffer = new StringBuffer();
//					Map<String, Set<String>> setMap = workFlowServiceImpl.getWorkFlowGroup(operator.getOperatorid(),"marketingWorkFlow");
//					Set<String> inGroups = setMap.get("group");
//					Set<String> inTasks = setMap.get("taskID");
//					if(! inGroups.isEmpty() && ! inTasks.isEmpty()){
//						// 组+组织机构编码 的拼接字符串
//						String[] arrayAuth = authorityid.split(",");
//						for(String str : (Set<String>)setMap.get("group")){
//							for(int i = 0; i < arrayAuth.length; i++){
//								stringBuffer.append("'"+str + arrayAuth[i] + "',");
//							}
//						}
//						String groupIDsString = stringBuffer.substring(0,stringBuffer.lastIndexOf(","));
//						List<String> canSerceIds = addServiceActivityService.getInquiryIdByGroupCity(groupIDsString,"5", null,null); 
//						totalCount += canSerceIds.size();
//					}
//				}
//				
//				// 有放弃、处理、回退、撤销权限，查处理中条数（包括回退）
//				if( set.contains("ROLE_ROLE_B_AAGA_GU") || set.contains("ROLE_ROLE_B_AAGA_DEAL")||set.contains("ROLE_ROLE_B_AAGA_REPEAL")||set.contains("ROLE_ROLE_B_AAGA_C")||set.contains("ROLE_ROLE_B_AAGA_BACK")){
//					Map<String, Set<String>> setMap = getWorkFlowGroup(operator.getOperatorid());// 得到用户所在工作流的节点的用户组 ，与流程
//					Set<String> inGroups = setMap.get("group");
//					Set<String> inTasks = setMap.get("taskID");
//					if(inGroups!=null && !inGroups.isEmpty() && inTasks!=null && !inTasks.isEmpty() && authorityid!=null && !"".equals(authorityid)){
//						StringBuffer stringBuffer = new StringBuffer();
//						String[] arrayAuth = authorityid.split(",");
//						for(String str : (Set<String>)setMap.get("group")){
//							for(int i = 0; i < arrayAuth.length; i++){
//								stringBuffer.append(str + arrayAuth[i] + ",");
//							}
//						}
//						if( !"".equals(stringBuffer.toString())){
//							// 组+组织机构编码 的拼接字符串
//							String groupIDsString = stringBuffer.substring(0,stringBuffer.lastIndexOf(","));
//							// 流程id 的拼接字符串
//							StringBuffer workFlowBuffer = new StringBuffer();
//							Set<String> sets = setMap.get("taskID");
//							
//							if( !sets.isEmpty()){
//								for(String str : sets){
//									workFlowBuffer.append(str + ",");
//								}
//								String workFlowID = workFlowBuffer.substring(0,workFlowBuffer.lastIndexOf(","));
//								// 处理中记录
//								TaskQuery tq = taskService.createTaskQuery();
//								tq.processVariableValueEquals("entity_Status", "1");
//								totalCount += workFlowServiceImpl.getTotalCount(groupIDsString, workFlowID, tq);
//								// 回退记录
//								tq = taskService.createTaskQuery();
//								tq.processVariableValueEquals("entity_Status", "2");
//								totalCount += workFlowServiceImpl.getTotalCount(groupIDsString, workFlowID, tq);
//							}
//						}
//					}
//				}
//			}
			String inquiryNum = JsonBinder.buildNonNullBinder().toJson(totalCount);
			super.render(inquiryNum, "application/json;charset=utf-8");
			return NONE;
		}catch(Exception ex){
			ex.printStackTrace();
			return NONE;
		}
	}
}

