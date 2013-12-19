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
	
	private String message;//����ҳ�����ʾ��Ϣ
	private int flag;//ҳ����ʾѡ����
	private List<GeRisk> geRiskList;
	private List<GeCode> geCodeActivityPatternList;//�����
	private List<GeCode> geCodeBusinessAreaList;//ҵ������
	private List<GeCode> geCodePremiumTypeList;//���M���
	private List<GeDepartment> geDepartmentTypeList;//ʡ
	private List<GeDepartment> cityList;//��
	private List<GeThirdParterInfo> geThirdParterInfoList;//��Ӧ�̹�˾ 
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
	private String xrule;//�ڼ�������
	private String yaddShopping;//�ڼ����ӹ���Ʒ
	private String oldProductEid;//��ƷEid���ڸ���ҳ��
	private GeDirectory geDirectory;
	private String rulds;//����ID
	private String deptId ;//������
	private String selectType;//�������� ��,�ǳ�,��
	private String eids;//eids ��","���
	//�����ϴ�ʱ�õ�
	private List<File> uploadPicture;
	private List<String> uploadPictureFileName;
	private List<String> uploadPictureContentType;
	

	//˫����ѡ�����Ȩ��start
	/**���ڲ������ܵ�Ȩ�ޱ���*/
	private String receivedObj;
	private String type;/**2-input��ֵ  ����-js��ֵ*/
	private String openType;/**iframe-iframe ����-window*/
	private String checkType;/**��ѡ��ѡ*/
	private String receivedObjName;/**ѡ�л�������*/
	private String deptIdCount;/**��λ��־,���ڶ�����д��ʱ��*/
	//˫����ѡ�����Ȩ��end

	//service
	private GeCodeTypeService geCodeTypeService;
	private AddServiceActivityService addServiceActivityService;
	private BizCommonService bizCommonService;
	private GeCodeService geCodeService;/**�����ֵ������*/
	private ThirdParterService thirdParterService;
	private ProductManageService productManageService;//��ƷĿ¼
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
	 * ѡ�����Ȩ��  Ҳ�Ѷ�λ�����ù���
	 * @return
	 */
	public String selectDeptAuthId(){
		String operation =  super.getRequest().getParameter("operation");
		if(operation!=null&&!"".equals(operation)){
			if("marketing".equals(operation)){//��Ӫ�������������
				
			}
			if("thirdPartterInfo".equals(operation)){//�ӵ���������������
				return "thirdPartterInfo";
			}
				
		}
		return SUCCESS;
	}
	/**
	 * ׼�������ѯ��������Ʒ�Ľ���
	 * @return
	 */
	public String prepareFindGeThirdParterService(){
		deptId = super.getRequest().getParameter("deptId");
		//ҵ������(1-���ţ�2-���գ�3-���գ�4-�����գ�9-����)
		return SUCCESS;
	}
	/**
	 * ׼�����ӻ
	 * @return
	 * @throws Exception 
	 */
	public String prepareAddGeAddServiceActivity() throws Exception{
		geRiskList = bizCommonService.findAllRiskCode(null);//��ѯ���������ֲ�Ʒ
		geCodeActivityPatternList = geCodeService.findAllByGeCodeType("ActivityPattern");//��ѯ���ʽ����
		geCodeBusinessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");//��ѯҵ������
		geCodePremiumTypeList = geCodeService.findAllByGeCodeType("PremiumType");//��ѯ��������premiumType
		date = new Date();
		//�õ� ��¼�˵� ��Ӫ����е� ҵ������   start
		Map map = (Map) super.getSession().getAttribute("permission");
		String authorityids = (String) map.get("ROLE_B_AAGA_I");//�õ��ù��ܵ����л���Ȩ��
		for (int i = geCodeBusinessAreaList.size() -1; i >= 0; i--) {
			String baStr = geCodeBusinessAreaList.get(i).getId().getCodeCode();
			if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
				continue;
			}else {
				geCodeBusinessAreaList.remove(i);
			}
		}
		//�õ� ��¼�˵� ��Ӫ����е� ҵ������   end
		return SUCCESS;
	}
	
	/**
	 * ����ʡ
	 * @return
	 */
	public String findProvince(){
		
		Map map = (Map) super.getSession().getAttribute("permission");//ʡ��
		String authorityid = (String) map.get("ROLE_B_AAGA_I");
		QueryRule queryRule  = QueryRule.getInstance();
		queryRule.addEqual("businessarea", businessArea);//ҵ������
		queryRule.addEqual("deptlevel", "2");//ʡ
		List<GeDepartment> geDepartmentList =geDepartmentService.findAllDepartments(queryRule);
		List<GeDepartment> geDepartmentExist  = new ArrayList<GeDepartment>(); //���ڷ��ص�ʡ
		if(geDepartmentList!=null&&geDepartmentList.size()>0){
			for (GeDepartment geDepartment : geDepartmentList) {
				if(authorityid.indexOf(","+geDepartment.getDeptid()+",")>-1){
					geDepartmentExist.add(geDepartment);//���ʡ����
				}
			}
		}
		JSONObject backJson = new JSONObject();
		backJson.put("backdata", geDepartmentExist);
		super.render(backJson.toString(), "application/json;charset=GBK");
		return NONE;
	}
	/**
	 * ������
	 * select * from ge_department t where t.businessarea = '2' and t.deptlevel = '3' and t.parentid = ''
	 * @return
	 */
	public String findCity(){
		
		Map map = (Map) super.getSession().getAttribute("permission");//ʡ��
		String authorityid = (String) map.get("ROLE_B_AAGA_I");
		QueryRule queryRule  = QueryRule.getInstance();
		queryRule.addEqual("businessarea", businessArea);//ҵ������
		queryRule.addEqual("deptlevel", "3");//��
		queryRule.addEqual("parentid", city);//��
		List<GeDepartment> geDepartmentList =geDepartmentService.findAllDepartments(queryRule);
		List<GeDepartment> geDepartmentExist  = new ArrayList<GeDepartment>(); //���ڷ��ص�ʡ
		boolean departFlag = true;//Ĭ����������ʡ,��ҳ��ʹ��
		if(geDepartmentList!=null&&geDepartmentList.size()>0){
			
			for (GeDepartment geDepartment : geDepartmentList) {
				if(authorityid.indexOf(","+geDepartment.getDeptid()+",")>-1){
					geDepartmentExist.add(geDepartment);//���ʡ����
				}
			}
			
		}else{
			departFlag =false;//false�������ʡ��������������
			GeDepartment geDepartmentTemp=geDepartmentService.findGeDepartmentByPK(city);
			if(geDepartmentTemp!=null){
				if(authorityid.indexOf(","+geDepartmentTemp.getDeptid()+",")>-1){
					geDepartmentExist.add(geDepartmentTemp);//���ʡ����
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
	 * ��ѯ����Ʒ
	 * @return
	 */
	public String findCarProduct() {
		QueryRule queryRule = QueryRule.getInstance();
		String riskCode = super.getRequest().getParameter("riskCode");
		String riskCName = super.getRequest().getParameter("riskCName");
		queryRule.addEqual("riskType", "11");//11������
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
	 * ��ȡ������Ʒ��Ϣ
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
			//��ѯҵ������
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
	
	//�ж��Ƿ����ظ��Ļ
	public String isProductAddServiceExist(){
		String startDate = getRequest().getParameter("startDate");
		String endDate = getRequest().getParameter("endDate");
		String coreProductCode  = getRequest().getParameter("coreProductNames");
		String activityPattern = getRequest().getParameter("activityPatterns");
		String deptID = getRequest().getParameter("deptID"); //����
		String updatePage = getRequest().getParameter("updatePage");//����ҳ��
		String activityId = getRequest().getParameter("activityId");//�ID
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
		geAddServiceActivity.setFlag(updatePage!=null&&!"".equals(updatePage) ? updatePage :null);  //�����ֶ�
		//set ��Ʒ����
		String[] coreProductCodes = coreProductCode.split(",");
		List<GeActivitiesProduct> geActivitiesProductList= new ArrayList<GeActivitiesProduct>();
		for(int i=0;i<coreProductCodes.length;i++){
			GeActivitiesProduct geActivitiesProduct = new GeActivitiesProduct();
			geActivitiesProduct.setEid(coreProductCodes[i]);
			geActivitiesProductList.add(geActivitiesProduct);
		}
		geAddServiceActivity.setGeActivitiesProducts(geActivitiesProductList);//set  productCode
		//set ���ʽ
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
		
		//��ǰ��У����ʽ  ��ʱע��
//		List<GeProductAndPattern> geProductAndPatternList= getProductAndPatternList(geCustomAddServiceActivityList,geActivitiesRuleList);
//		JSONObject backJson = new JSONObject();
//		backJson.put("geProductAndPatternList", geProductAndPatternList);
//		super.render(backJson.toString(), "application/json;charset=GBK");
		return null;
	}
	
	
	
	
	private List<GeProductAndPattern> getProductAndPatternList(List<GeCustomAddServiceActivity> geCustomAddServiceActivityList,List<GeActivitiesRule> geActivitiesRuleList){
		List<GeProductAndPattern> geProductAndPatternList = new ArrayList<GeProductAndPattern>();//Ҫ���صĽ����
		
		if(geCustomAddServiceActivityList!=null&&geCustomAddServiceActivityList.size()>0){
			Map<String,List<GeCustomAddServiceActivity>> map = new HashMap<String, List<GeCustomAddServiceActivity>>();
			//�ӹ�����
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
			//�����for ������װ geProductAndPatternList �ľ����ֵ
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
		geAddServiceActivity.setUploadPicture(uploadPicture);//����Ҫ�ϴ����ļ�
		geAddServiceActivity.setUploadPictureContentType(uploadPictureContentType);//����Ҫ�ϴ����ļ�����
		geAddServiceActivity.setUploadPictureFileName(uploadPictureFileName);//����Ҫ�ϴ����ļ����� 
		geAddServiceActivity.setUploadSerialNoList(getUploadSerialNoList(uploadPictureSerialNos));//����Ҫ�ϴ����ļ����к�
		geAddServiceActivity.setUploadImagePath(super.getRequest().getRealPath("/upload/images/imageP/"));
		geAddServiceActivity.setUploadSavePath(imagePath);
		geAddServiceActivity.setCreateDeptId(getCreateDeptId());
//		geAddServiceActivity.setValidInd("1");//1Ϊ��Ч
		setGeActivitiesRuleDiscountRemarkText(geAddServiceActivity);
		addServiceActivityService.addAddGeAddServiceActivityAndRule(geAddServiceActivity);
		message = "��ӳɹ�";
		String mas="��ӳɹ�"+"@"+geAddServiceActivity.getActivityId()+"@"+geAddServiceActivity.getActivityName();
		mas = geAddServiceActivity.getActivityName()+"Ӫ���"+message;
		super.getRequest().setAttribute("message", mas);
		flag = 1;
		return SUCCESS;
	}
	
	//addAddGeAddServiceActivityAndRule() ����  updateAddGeAddServiceActivity()������Ҫʹ��
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
	 * prepareUpdateAddGeAddServiceActivity() ������������˱�����
	 * view()  ������������˱�����
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
	//�õ�ÿ��Ҫ�ϴ���ͼƬ��Ӧ��ͼƬ���
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
			 authorityid = (String) map.get("ROLE_B_AAGA_S");//������������Ȩ������
		}else{
			 authorityid = (String) map.get("ROLE_ROLE_B_AAGA_SEE");//������������Ȩ������
		}
		//----------------
		if(status == null ||"".equals(status)){
			   page = new Page();
			}else{
				// ��Ӳ�ѯ״̬(�Ѵ�����߲�ȫ������Ҫ��Ϊ��ѯ����)
				if("all".equals(status) || "4".equals(status)){
					geAddServiceActivity.setStatus(null);
				}
				if("4".equals(status)){
					//�Ѵ����ѯ�켣��
					System.out.println(geAddServiceActivity.getActivityName());
					page=addServiceActivityService.queryHasProcessed(pageNo, pageSize, operator.getOperatorid(),geAddServiceActivity.getActivityName());
					
				}else{
				//###############################
				//**��ѯ������¼��ֻ��ӳ��л���Ȩ�ޣ��͹���������޹�
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
//						// ��+��֯�������� ��ƴ���ַ���
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
//						//**��Ҫҵ��
//						List<String> canSerceIds = new ArrayList<String>();
//						if(!"look".equals(bizType)){ // ά��ģʽ��Ҫ�ӻ���+������
//							
//							if("all".equals(status)){ // ȫ��
//								List<String> workFlowInquryIds = findIDsFormWorkFlow(); // �����������еļ�¼
//								String str[] = {"1"};
//								canSerceIds = addServiceActivityService.getInquiryIdByGroupCity(groupIDsString, null, str,null);// ���������ѯ���أ�״̬�ǷǴ����еļ�¼
//								canSerceIds.addAll(workFlowInquryIds);
//							}else{
//								canSerceIds = addServiceActivityService.getInquiryIdByGroupCity(groupIDsString, null, null,null);
//							}
//						}else{
//							// �鿴ģʽ
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
		//��ѯ�����
		geAddServiceActivity = addServiceActivityService.findAddGeAddServiceActivityByActivityId(activityId);
		String businessAreaDept = null;
		String splitDeptName[] =geAddServiceActivity.getDeptName().split("/");
		for (int i = 0; i < splitDeptName.length; i++) {
			if(splitDeptName[i].equals("����")){
				businessAreaDept="1";
			}
			if(splitDeptName[i].equals("����")){
				businessAreaDept="3";
			}
			if(splitDeptName[i].equals("����")){
				businessAreaDept="2";
			}
			if(splitDeptName[i].equals("������")){
				businessAreaDept="4";
			}
			
		}
		decodeGeActivitiesRuleDiscountRemarkText(geAddServiceActivity);
		viePictureForUploadPage(geAddServiceActivity);//����ҳ����ͼƬ����ʾ
		oldProductEid = (getOldProductEid(geAddServiceActivity)).trim();
		geCodeActivityPatternList = geCodeService.findAllByGeCodeType("ActivityPattern");//��ѯ���ʽ����
		geCodePremiumTypeList = geCodeService.findAllByGeCodeType("PremiumType");//��ѯ��������premiumType
		geCodeBusinessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");//��ѯҵ������
		date = new Date();
		//�õ� ��¼�˵� ��Ӫ����е� ҵ������   start
		Map map = (Map) super.getSession().getAttribute("permission");
		String authorityids = (String) map.get("ROLE_B_AAGA_I");//�õ��ù��ܵ����л���Ȩ��
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
			authorityids = (String) map.get("ROLE_B_AAGA_U");//�õ��ù��ܵ����л���Ȩ��
			for (int i = geCodeBusinessAreaList.size() -1; i >= 0; i--) {
				String baStr = geCodeBusinessAreaList.get(i).getId().getCodeCode();
				if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
					continue;
				}else {
					geCodeBusinessAreaList.remove(i);
				}
			}
		}
		super.getRequest().setAttribute("businessAreaDept", businessAreaDept);// ��������
		return SUCCESS;
	}
	
	//�鿴��ϸ��������������
	public String view() throws Exception{
		//getDiscountData("G200053");
		//����һ���������ı�־λ���ǹ������ı�־λ����ô������ʾ   ͨ��  ���˵İ�ť
		//String workFlowType = "workFlow";//Ϊ�ӹ��������в�ѯ������������
		String workFlowType = super.getRequest().getParameter("workFlowType");
		String bizType = super.getRequest().getParameter("bizType");
		//
		geAddServiceActivity = addServiceActivityService.findAddGeAddServiceActivityByActivityId(activityId);
		String createDeptName= addServiceActivityService.getDeptName(geAddServiceActivity.getCreateDeptId());
		decodeGeActivitiesRuleDiscountRemarkText(geAddServiceActivity);
		viePictureForUploadPage(geAddServiceActivity);
		geCodeActivityPatternList = geCodeService.findAllByGeCodeType("ActivityPattern");//��ѯ���ʽ����
		geCodePremiumTypeMap = geCodeService.findAllCodeAndName("PremiumType");//��ѯ���ѷ�ʽ����
		//if(workFlowType!=null&&!workFlowType.equals("")){//�����Ĺ����������������� ��Ҫ���ư�ť�Ƿ���ʾ
			getRequest().setAttribute("taskId",getRequest().getParameter("taskId"));
			getRequest().setAttribute("workflowId", getRequest().getParameter("workflowId"));
	//	}//else{//���������������� ���ǹ����� ���������Ǵӱ������������� 
//			
//		}
		//super.getRequest().setAttribute("searchType", super.getRequest().getParameter("searchType"));//��������
		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
		super.getRequest().setAttribute("createDeptName", createDeptName);
		super.getRequest().setAttribute("operatorCode",operator.getDeptid());
		super.getRequest().setAttribute("operator",operator);
		super.getRequest().setAttribute("workFlowType", workFlowType);// ��������
		super.getRequest().setAttribute("bizType", bizType);
		return SUCCESS;
	}
	//���մ��۲���
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
	 * ��ѯ�����ʹ�õĲ�Ʒ 
	 * @return
	 * @throws Exception
	 */
	public String findGeActivitiesProduct() throws Exception{
		List<GeDirectory> geDirectorys = geDirectoryService.findGeDirectoryByEids(eids.split(","));
		addServiceActivityService.setGeDirectoryType(geDirectorys);//���ó� �ǳ� �� 
		getRequest().setAttribute("geDirectorys", geDirectorys);
		return SUCCESS;
	}
	//����������ڸ���ҳ���ͼƬ��չʾ
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
		
		//����ruleIds
		List<GeActivitiesRule> geActivitiesRuleList=  geAddServiceActivity.getGeActivitiesRules();
		if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
			rulds = "";
			for (GeActivitiesRule geActivitiesRule : geActivitiesRuleList) {
				rulds +=geActivitiesRule.getRuleId()+",";
			}
			rulds =rulds.substring(0,rulds.length()-1);
		}
	}
	//����������õ���Ʒ��EID
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
//		//��ѯ����start
//		Map map = (Map) super.getSession().getAttribute("permission");//ʡ��
//		String authorityid = (String) map.get("ROLE_B_AAGA_I");
//		setGeDepartmentTypeList(authorityid);//��ʡ��geDepartmentTypeList��ֵ
//		setCityPropertyList(list,authorityid,geAddServiceActivity.getBusinessArea());//���и�ֵ
//		//��ѯ����end
//		
//		//areaList  ��ʾ
//		geCodeActivityPatternList = geCodeService.findAllByGeCodeType("ActivityPattern");//��ѯ���ʽ����
//		geCodeBusinessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");//��ѯҵ������
//		getRequest().setAttribute("type", type);
		return SUCCESS;
	}
	
	/***
	 * ���ͨ��
	 * @return
	 */
	public String auditActivity(){
		String type = getRequest().getParameter("type");
		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
		addServiceActivityService.auditAcvity(taskID, workFlowID,operator.getOperatorid());
		flag = 1;
		message = "�����ɹ�";
		return SUCCESS;
	}
	
	/**��
	 * ���
	 * @return
	 */
	public String failureAutit(){
		String type = getRequest().getParameter("type");
		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
		addServiceActivityService.failureAutit(taskID, workFlowID,operator.getOperatorid());
		flag = 1;
		message = "�·��޸ĳɹ�";
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
		geAddServiceActivity.setUpdateDeptId(getCreateDeptId());//�����߻���ID
		setGeActivitiesRuleDiscountRemarkText(geAddServiceActivity);
		String uploadPictureSerialNos = super.getRequest().getParameter("uploadPictureSerialNos");
		addServiceActivityService.updateAddGeAddServiceActivityAndGeActivitiesRule(geAddServiceActivity,uploadPictureSerialNos,rulds);
		flag = 1;
		message = "�޸ĳɹ�";
		return SUCCESS;
	}
	public String deleteAddGeAddServiceActivity(){
		addServiceActivityService.deleteAddGeAddServiceActivity(activityId);
		flag = 1;
		message = "ɾ���ɹ���";
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
	 * �����޸Ļ��Ʒ��Ϣҳ��
	 * @return
	 */
	public String UpdateProductInfo(){
		return SUCCESS;
	}
	/**
	 * ׼������ӹ���Ʒ��ѯ��ҳ��
	 * @return
	 */
	public String prepareSelectAddShopping(){		
		//��ѯҵ������
		//deptId=${deptId}&xRule=${param.xRule}&yAddShopping=${param.yAddShopping}
		GeDepartment geDepartment= geDepartmentService.findGeDepartmentByPK(deptId);
		String businessAreaFlag = geDepartment.getBusinessarea();
		geCodeBusinessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");//��ѯҵ������
		//�ò�ѯ����deptID�Ǹ���㻹���ӽ��
		QueryRule queryRule  = QueryRule.getInstance();
		queryRule.addEqual("parentid", deptId);
		List<GeDepartment>  geDepartmentList= geDepartmentService.findAllDepartments(queryRule);
		if(geDepartmentList!=null&&geDepartmentList.size()>0){
			super.getRequest().setAttribute("isParentFlag", "yes");//�����
		}else{
			super.getRequest().setAttribute("isParentFlag", "no");//�ӽ��
			super.getRequest().setAttribute("deptName", geDepartment.getDeptname());//�ӽ��
		}
		
		super.getRequest().setAttribute("businessAreaFlag", businessAreaFlag);//ҵ������
		super.getRequest().setAttribute("xRule", super.getRequest().getParameter("xRule"));//X����
		super.getRequest().setAttribute("yAddShopping", super.getRequest().getParameter("yAddShopping"));//Y����
		super.getRequest().setAttribute("saleDept", deptId);//���۵���
		
		String operation = super.getRequest().getParameter("operation");
		if(operation!=null&&!"".equals(operation)){
			if(operation.equals("addProduct")){//���Ӳ�Ʒ  ��������
				return "addProduct";
			}
			if(operation.equals("addShoppingroduct")){//�Ӽӹ���Ʒ��������
				return "addShoppingroduct";
			}
		}
		return SUCCESS;//Ĭ�ϴӼӹ���Ʒ���� 
	}
	/**
	 * ׼������ӹ���Ʒ��ѯ��ҳ��
	 * @return
	 */
	public String prepareSelectAddShopping1(){
		
		//��ѯҵ������
		//deptId=${deptId}&xRule=${param.xRule}&yAddShopping=${param.yAddShopping}
		GeDepartment geDepartment= geDepartmentService.findGeDepartmentByPK(deptId);
		String businessAreaFlag = geDepartment.getBusinessarea();
		geCodeBusinessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");//��ѯҵ������
		//�ò�ѯ����deptID�Ǹ���㻹���ӽ��
		QueryRule queryRule  = QueryRule.getInstance();
		queryRule.addEqual("parentid", deptId);
		List<GeDepartment>  geDepartmentList= geDepartmentService.findAllDepartments(queryRule);
		if(geDepartmentList!=null&&geDepartmentList.size()>0){
			super.getRequest().setAttribute("isParentFlag", "yes");//�����
		}else{
			super.getRequest().setAttribute("isParentFlag", "no");//�ӽ��
			super.getRequest().setAttribute("deptName", geDepartment.getDeptname());//�ӽ��
		}
		
		super.getRequest().setAttribute("businessAreaFlag", businessAreaFlag);//ҵ������
		super.getRequest().setAttribute("xRule", super.getRequest().getParameter("xRule"));//X����
		super.getRequest().setAttribute("yAddShopping", super.getRequest().getParameter("yAddShopping"));//Y����
		super.getRequest().setAttribute("saleDept", deptId);//���۵���
		
		String operation = super.getRequest().getParameter("operation");
		if(operation!=null&&!"".equals(operation)){
			if(operation.equals("addProduct")){//���Ӳ�Ʒ  ��������
				return "addProduct";
			}
//			if(operation.equals("addShoppingProduct")){//�Ӽӹ���Ʒ��������
//				
//			}
		}

		return SUCCESS;//Ĭ�ϴӼӹ���Ʒ���� 
	}
	
	/**
	 * ��ѯ���ֱ��е����ݣ������ܹ�����eid
	 * @return
	 */
	public String findGeRisk(){
		
		String forwardType = setForwardTypeValue();//�ʹ�ò�Ʒ:activityProduct    �ӹ���ƷaddProduct
		businessArea = super.getRequest().getParameter("businessArea");
		String riskCode = super.getRequest().getParameter("riskCode");
		String riskCName = super.getRequest().getParameter("riskCName");
		
		GeRisk geRisk = new GeRisk();
		geRisk.setRiskCode((riskCode!=null||!riskCode.equals(""))?riskCode:null);
		geRisk.setBusinessArea("3");
		geRisk.setRiskCName((riskCName!=null||!riskCName.equals(""))?riskCName:null);
		if(forwardType.equals("activityProduct")){//�ʹ�ò�Ʒ
			selectType = SelectType.Car.name();
			List<GeRisk>  geRiskList= bizCommonService.findGeRiskListWithEid(geRisk);//ֱ�Ӳ�ѯ����ҳ
			super.getRequest().setAttribute("geRiskList", geRiskList);
			//�ʹ�ò�Ʒ end
			
		}else{//�ӹ���Ʒ
			
			if (pageNo == 0) {
				pageNo = 1;
			}
			if (pageSize == 0) {
				pageSize = 10;
			}
		page = bizCommonService.findGeRiskListWithEid(geRisk,pageNo, pageSize);
		//businessAreaList =
		geCodeBusinessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");//��ѯҵ������
		if(page!=null){
			super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
			super.getRequest().setAttribute("pageSize", page.getPageSize());
			super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
			super.getRequest().setAttribute("totalCount", page.getTotalCount());
			super.getRequest().setAttribute("xrule", super.getRequest().getParameter("xrule"));
			super.getRequest().setAttribute("yAddShopping", super.getRequest().getParameter("yAddShopping"));
			
			}
		}//�ӹ���Ʒ end
		//return forwardType.equals("activityProduct") ? SUCCESS : "addProduct";
		if(forwardType.equals("activityProduct"))
			return SUCCESS;
		else
			return  "addProduct";
	}
	private String setForwardTypeValue(){
		String operation = super.getRequest().getParameter("operation");
		String forwardType = "activityProduct";//�ʹ�ò�Ʒ
		if(operation!=null&&!"".equals(operation)&&operation.equals("addProduct")){
				forwardType = "addProduct";//��ӹ���Ʒ
		}
		return forwardType;
	}
	/**
	 * ��ѯ�ǳ���������ݣ������ܹ�����eid
	 * @return
	 */
	public String findGeProductMain(){
		String forwardType = setForwardTypeValue();//�ʹ�ò�Ʒ:activityProduct    �ӹ���ƷaddProduct
		//********common   start
		String coreProductCode = super.getRequest().getParameter("coreProductCode");
		String coreProductSimpleName = super.getRequest().getParameter("coreProductSimpleName");
		String saleDept = super.getRequest().getParameter("saleDept");
		
		
		//�жϸ���㻹���ӽ��Ĵ��� start
		boolean isParentFlag ;
		isParentFlag = setIsParentFlagProperty(saleDept);//
		//�жϸ���㻹���ӽ��Ĵ��� end
		
		String sqlForward = "";  //1.����� ��������Ϊ in('deptId')  2.������parentId = id  3.�ӽ���departId=
		String saleDeptSelectCode = super.getRequest().getParameter("saleDeptSelectCode");//��������
		sqlForward = setSqlForwardValue(isParentFlag,saleDeptSelectCode);
		String useDeptIdForSql = setUseDeptIdForSqlValue(sqlForward,saleDeptSelectCode);
		
		GeProductMain geProductMain = new GeProductMain();
		geProductMain.setBusinessArea(setBusinessAreaValue(saleDeptSelectCode,saleDept));
		geProductMain.setCoreProductCode(coreProductCode==null||coreProductCode.equals("")?null:coreProductCode);
		geProductMain.setCoreProductSimpleName(coreProductSimpleName==null||coreProductSimpleName.equals("")?null:coreProductSimpleName);
		
		if(forwardType.equals("activityProduct")){//�ʹ�ò�Ʒ:activityProduct 
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
			
		}else{//�ӹ���ƷaddProduct
			
			if (pageNo == 0) {
				pageNo = 1;
			}
			if (pageSize == 0) {
				pageSize = 10;
			}
		page = productManageService.findProductMainBySaleDept(geProductMain, sqlForward, pageNo, pageSize, useDeptIdForSql);//useDeptIdForSql
		geCodeBusinessAreaList = geCodeService.findAllByGeCodeType("BusinessArea");//��ѯҵ������
		if(page!=null){
			super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
			super.getRequest().setAttribute("pageSize", page.getPageSize());
			super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
			super.getRequest().setAttribute("totalCount", page.getTotalCount());
			super.getRequest().setAttribute("xrule", super.getRequest().getParameter("xrule"));
			super.getRequest().setAttribute("yAddShopping", super.getRequest().getParameter("yAddShopping"));
		}
		
		
		}//end �ӹ���ƷaddProduct
		
		
		return forwardType.equals("activityProduct") ? SUCCESS : "addProduct";
	}
	
	//�����Ƿ�Ϊ����
	//findGeCardProduct  findGeProductMain �����Ϸ��� ��ʹ�õ���������ĵ�ʱ��Ҫע��
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
	//����SQL��ת��
	private String setSqlForwardValue(boolean isParentFlag,String saleDeptSelectCode){
		String sqlForward = "";
		if(isParentFlag){//�����Ļ�
			if(saleDeptSelectCode!=null&&!saleDeptSelectCode.equals("")){//������������ֵ  ����д
				//�� deptId in('');
				//1.in
				sqlForward = "1";
			}else{// ����������û��ֵ  ��Ĭ�ϵ�    ������ saleDept
				// 2 
				sqlForward = "2";
			}
			
		}else{//�ӽ��
			
			//ֱ�Ӵ�һ��  ֻ��һ�����  saleDept
			// 3 
			sqlForward = "3";
		}
		return sqlForward;
	}
	private String setUseDeptIdForSqlValue(String sqlForward ,String saleDeptSelectCode){
		String result = "";
		if(sqlForward.equals("1")){//1.����� ��������Ϊ in('deptId')
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
		}else if(sqlForward.equals("2")){// 2.������parentId = id
			String saleDept = super.getRequest().getParameter("saleDept");
			result = (  "".equals(saleDeptSelectCode)  ) ?  saleDept :saleDeptSelectCode;
		}else if(sqlForward.equals("3")){
			
			result = saleDeptSelectCode;
		};//3.�ӽ���departId=
		return result;
	}
	//����ҵ������
	private String setBusinessAreaValue(String saleDeptSelectCode,String saleDept){
		String result = null;
		if(saleDeptSelectCode==null||saleDeptSelectCode.equals("")){//��������Ϊ��,˵��һ���Ǹ���� ����Ǽ��ŵĻ���������ֵ
			
			String temp = saleDept.substring(0,1);
			if(!temp.equals("1")){//���ų������������
				result = "'"+temp+"'";
			}
			
		}else{//�������벻Ϊ��,˵�������Ǳ��� Ҳ������ѡ��һ�ѳ��п��ܰ����ٱ������Ʊ���
			//���� businessArea in()
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
	
	//���·��� Ϊ������ʹ�õ�   start
	//*********************************************************************************************************************
	//*********************************************************************************************************************
	//*********************************************************************************************************************
	/**
	 * ��ѯ�������µ�����(�鹤�����ı�)
	 */
	public String findGeAddServiceActivityWorkFlow() {
		//remove super.getRequest().setAttribute("type", super.getRequest().getParameter("type"));   ���ư�ť������ʾ
//		long totalCount = 0L;
//		long totalPage = 0L;
//		String status = this.getRequest().getParameter("status");
//		List<EntityWorkFlow> result = new ArrayList<EntityWorkFlow>();
//		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
//		Map<String, Set<String>> setMap = getWorkFlowGroup(operator.getOperatorid());// �õ��û����ڹ������Ľڵ���û��� ��������
//		try{
//			Set<String> inGroups = setMap.get("group");
//			Set<String> inTasks = setMap.get("taskID");
//			//û����ҳ��ѡ�����Ȩ�ޣ����session�л�ȡ��ǰ��¼�û��Ļ���Ȩ��
//			String authorityid = super.getRequest().getParameter("authorityid");
//			if(StringUtils.isBlank(authorityid)){//ROLE_B_AAGA_S
//				Map map = (Map) super.getSession().getAttribute("permission");
//				authorityid = (String) map.get("ROLE_B_AAGA_S");   //��֯����Ȩ��ΪӪ����Ĵ��� 
//			}
//			if(inGroups!=null && !inGroups.isEmpty() && inTasks!=null && !inTasks.isEmpty() && authorityid!=null && !"".equals(authorityid)){
//			//��ȡ��ѯ����
//			StringBuffer stringBuffer = new StringBuffer();
//			String[] arrayAuth = authorityid.split(",");
//			for(String str : (Set<String>)setMap.get("group")){
//				for(int i = 0; i < arrayAuth.length; i++){
//					stringBuffer.append(str + arrayAuth[i] + ",");
//				}
//			}
//
//			// ��+��֯�������� ��ƴ���ַ���
//			String groupIDsString = stringBuffer.substring(0,stringBuffer.lastIndexOf(","));
//			// �û�����  ��ʱ����Ϊ��
//			//remove String agencyAuthority = geProposalIntention.getUserType();
//			String agencyAuthority = "";
//			// ����id ��ƴ���ַ���
//			StringBuffer workFlowBuffer = new StringBuffer();
//			
//			Set<String> sets = setMap.get("taskID");
//			if( !sets.isEmpty()){
//				for(String str : (Set<String>)setMap.get("taskID")){
//					workFlowBuffer.append(str + ",");
//				}
//				String workFlowID = workFlowBuffer.substring(0,workFlowBuffer.lastIndexOf(","));
//				TaskQuery tq = taskService.createTaskQuery();
//				/*//�û�����
//					if(agencyAuthority==null){
//						agencyAuthority = "";
//		             }*/
//				// ״̬
//				if(status!=null && !status.equals("")){
//						tq.processVariableValueEquals("entity_Status", status);
//				}
//				if(geAddServiceActivity.getActivityName()!=null && !geAddServiceActivity.getActivityName().equals("")){
//					//tq.processVariableValueLike("entity_activityName", geAddServiceActivity.getActivityName());
//					tq.processVariableValueEquals("entity_activityName", geAddServiceActivity.getActivityName());
//				}
//				totalCount = workFlowServiceImpl.getTotalCount(groupIDsString, workFlowID, tq);
//				totalPage = totalCount % pageSize != 0 ? totalCount / pageSize + 1L : totalCount / pageSize;
//				//result = markingWorkFlowService.getCandidateTaskByPage(groupIDsString, workFlowID, agencyAuthority, pageNo, pageSize);  //�����������ǰС��д��
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
	 *��
	 * ��ѯ�������µ�����(�鹤�����ı�)  findGeAddServiceActivityWorkFlow()  Ϊ�������������
	 *
	 * 
	 * �����û����ڹ��������û��� ,���س���Ϊ2����ļ��ϼ��ϣ�Ԫ��1Ϊ�����飬Ԫ��2Ϊ������
	 * @param @param userID		��¼�û�ID
	 */
	private Map<String, Set<String>> getWorkFlowGroup(String userID){
//		GeWorkflowId id = new GeWorkflowId();
//		id.setFuncitontype("WORKFLOWCONFIG");
//		id.setArea("CC");
//		id.setFiletype("0");
//		GeWorkflow geWorkflow =geWorkFlowService.findGeWorkflowByPK(id);//��ȡ��������Ա������Ϣ
//		if(geWorkflow == null)return null;
//		InputStream ins = new ByteArrayInputStream(geWorkflow.getFilecontent());
//		
//		Map<String, Object> map = new WorkFlowUtil().getGroupAll(ins);
//		Set<String> set = (Set<String>)map.get("groupID");//���й���������
//		Set<String> setGroup = new HashSet<String>();
//		
//		for(String str : set){//ɸѡ����½��Ա������
//			if(geGroupService.isExistUser(userID, str)){
//				setGroup.add(str);
//			}
//		}
//		
//		Set<String> setArray = new HashSet<String>(); //����Ա�������Ӧ��������key
//		for(Entry<String, Object> entry : map.entrySet()){
//			if(!entry.getKey().equals("groupID")){
//				Map<String ,Object> mm = (Map<String ,Object>)entry.getValue();
//				for(Entry<String, Object> e : mm.entrySet()){
//					for(String str : setGroup){
//						if(str.equals(e.getValue())){
//							// ɸѡ������id��"geProposalIntention"��
//							//if(entry.getKey().indexOf("geProposalIntention") >= 0) //�����Ҫ�ĵ�   ��Ӧ�ļ��ı�
//							if(entry.getKey().indexOf("marketingWorkFlow") >= 0) //�����Ҫ�ĵ�   workFlowID  ΪӪ�����
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
	 * �ύ���  ��  ������ ״̬
	 *  
	 * ���·ַ��������� ״̬
	 * ����Ͷ����Ϣ
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String submitApplyMarketing() throws IOException, Exception{
		String activityId = super.getRequest().getParameter("activityId");
		//���洦��켣
		String taskId = super.getRequest().getParameter("taskId");//��ҳ�������
		String workflowId = super.getRequest().getParameter("workflowId");//��ҳ�������
		GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
		//�û��켣�Ĳ�ѯ
		GeAddServiceProcess geAddServiceProcess = new GeAddServiceProcess();//
		geAddServiceProcess.setHandleDept(geOperator.getDeptid());//��ǰ�������
		geAddServiceProcess.setOperatorID(geOperator.getOperatorid());//�����˱��
		geAddServiceProcess.setHandleDate(new Date());//��������
		geAddServiceProcess.setHandleStatus("1");//����״̬  ͬ�� ����   ��ͬ��
		geAddServiceProcess.setOptions("ͬ��");//�������    
		
		
		addServiceActivityService.startTask(geAddServiceProcess,activityId,taskId,workflowId);//����������״̬
		JSONObject backJson = new JSONObject();
		backJson.put("backdata", "01");
		this.renderText("01");
		return NONE;  //ת��ҳ�滹û��Ū
	} 
	
	// �������Ĵ������
	public String doFinish() throws IOException, Exception{
    	String taskId = super.getRequest().getParameter("taskId");//��ҳ�������
		String workflowId = super.getRequest().getParameter("workflowId");//��ҳ�������
		String activityId = super.getRequest().getParameter("activityId");//��ҳ�������
		
		//��ǰ�Ĵ������
		GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
		//�û��켣�Ĳ�ѯ
		GeAddServiceProcess geAddServiceProcess = new GeAddServiceProcess();//
		geAddServiceProcess.setHandleDept(geOperator.getDeptid());//��ǰ�������
		geAddServiceProcess.setOperatorID(geOperator.getOperatorid());//�����˱��
		geAddServiceProcess.setHandleDate(new Date());//��������
		geAddServiceProcess.setHandleStatus("1");//����״̬  ͬ�� ����   ��ͬ��
		geAddServiceProcess.setOptions("ͨ��");//�������    
		addServiceActivityService.completeTask(geAddServiceProcess, activityId, taskId, workflowId);//������������
		this.renderText("01");
		return NONE; 
	}
	
	/**
	 * ������  ����  ״̬(���ڷ������ڳ�������)
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	// ����������(���ڷ������ڳ�������)
	public String doRoolBack() throws IOException, Exception{
		// taskId,������������
		// workflowId  ������������
		String taskId = super.getRequest().getParameter("taskId");
		String workflowId = super.getRequest().getParameter("workflowId");
		String activityId = super.getRequest().getParameter("activityId");
		String status ="5";// ����״̬��Ϊ5������״̬
		//��ǰ�Ĵ������
		GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
		//�û��켣�Ĳ�ѯ
		GeAddServiceProcess geAddServiceProcess = new GeAddServiceProcess();//
		geAddServiceProcess.setHandleDept(geOperator.getDeptid());//��ǰ�������
		geAddServiceProcess.setOperatorID(geOperator.getOperatorid());//�����˱��
		geAddServiceProcess.setHandleDate(new Date());//��������
		geAddServiceProcess.setHandleStatus("2");//����״̬  ͬ�� ����   ��ͬ��
		geAddServiceProcess.setOptions("��ͬ��");//�������    
		//addServiceActivityService.completeTask(geAddServiceProcess, activityId, taskId, workflowId);//������������
		addServiceActivityService.doRoolBack(geAddServiceProcess, activityId, taskId, workflowId,status);
		this.renderText("01");
		return null;
		
	}
	/**
	 * ������  ����  ״̬
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	// ����������(���ڷ������ڳ�������)
	public String doGiveUp() throws IOException, Exception{
		// taskId,������������
		// workflowId  ������������
//		String taskId = super.getRequest().getParameter("taskId");
//		String workflowId = super.getRequest().getParameter("workflowId");
//		String activityId = super.getRequest().getParameter("activityId");
//		String status="2";// ����״̬��Ϊ0�������״̬
//		boolean result = workFlowServiceImpl.isFirstTask(taskId);
//		if(result){
//			this.renderText("02");
//			return NONE;
//		}
////		��ǰ�Ĵ������
//		GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
//		//�û��켣�Ĳ�ѯ
//		GeAddServiceProcess geAddServiceProcess = new GeAddServiceProcess();//
//		geAddServiceProcess.setHandleDept(geOperator.getDeptid());//��ǰ�������
//		geAddServiceProcess.setOperatorID(geOperator.getOperatorid());//�����˱��
//		geAddServiceProcess.setHandleDate(new Date());//��������
//		geAddServiceProcess.setHandleStatus("2");//����״̬  ͬ�� ����   ��ͬ��
//		geAddServiceProcess.setOptions("��ͬ��");//�������    
//		//addServiceActivityService.completeTask(geAddServiceProcess, activityId, taskId, workflowId);//������������
//		addServiceActivityService.doRoolBack(geAddServiceProcess, activityId, taskId, workflowId,status);
//		this.renderText("01");
		return null;
		
	}
	/**
	 * ������  ����  ״̬
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	// ����������(���ڷ������ڷ�������)
	public String doQuit() throws IOException, Exception{
		// taskId,������������
		// workflowId  ������������
//		String taskId = super.getRequest().getParameter("taskId");
//		String workflowId = super.getRequest().getParameter("workflowId");
//		String activityId = super.getRequest().getParameter("activityId");
//		String status="6";// ����״̬��Ϊ0�������״̬
//		// ����taskID�õ�EntityWorkFlow
//		EntityWorkFlow c = workFlowServiceImpl.getSingleTask(taskId);
////		��ǰ�Ĵ������
//		GeOperator geOperator = (GeOperator) super.getSession().getAttribute("geOperator");
//		//�û��켣�Ĳ�ѯ
//		GeAddServiceProcess geAddServiceProcess = new GeAddServiceProcess();//
//		geAddServiceProcess.setHandleDept(geOperator.getDeptid());//��ǰ�������
//		geAddServiceProcess.setOperatorID(geOperator.getOperatorid());//�����˱��
//		geAddServiceProcess.setHandleDate(new Date());//��������
//		geAddServiceProcess.setHandleStatus("2");//����״̬  ͬ�� ����   ��ͬ��
//		geAddServiceProcess.setOptions("��ͬ��");//�������    
//		//addServiceActivityService.completeTask(geAddServiceProcess, activityId, taskId, workflowId);//������������
//		addServiceActivityService.doRoolBack(geAddServiceProcess, activityId, taskId, workflowId,status);
//		this.renderText("01");
		return null;
		
	}
	//���Ϸ��� Ϊ������ʹ�õ�   end 
	//*********************************************************************************************************************
	//*********************************************************************************************************************
	//*********************************************************************************************************************
	
	
	//���¸�������Ϊaction����װ����ʱ��Ҫ�õ��ķ���
	private String  getArea(GeAddServiceActivity geAddServiceActivity){
		String deptId = geAddServiceActivity.getDeptID();
		deptId = deptId.substring(0, 1);
		return deptId;
		
		
		
	}
	/**
	 * ��geCode�ķ�����װ��һ��
	 * @param codeType
	 * @return
	 * @throws Exception
	 */
	private List<GeCode> getGecode(String codeType) throws Exception{
		List<GeCode> geCodes = new ArrayList<GeCode>();
		QueryRule queryRuleTemp  = QueryRule.getInstance();
		queryRuleTemp.addEqual("businessArea", "3");//ҵ������(1-���ţ�2-���գ�3-���գ�4-�����գ�9-����)
		queryRuleTemp.addEqual("validInd", "1");//0��Ч1��Ч
		queryRuleTemp.addEqual("codeType", codeType);
		GeCodeType geCodeTypeSex  = geCodeTypeService.findGeCodeType(queryRuleTemp);
		geCodes  =  removeInValidInd(geCodeTypeSex.getGeCodes());
		Collections.sort(geCodes, new OrderComparator());//����
		return geCodes;
	}
	/**
	 *	��״̬ΪValidInd=0��Ч��ȥ��
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
	 * ��������
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
	
	//ȡǰ̨ͼƬ��
	public String testPicture() throws Exception{
		//List<> addServiceActivityService.getGeActivitiesPictureList(eid, "3440300");
		
		List<GeActivitiesPicture> geActivitiesPictureList = addServiceActivityService.getGeActivitiesPictureList("G300009", "3440300");
		return NONE;
	}
	
	/**
	 * ��ѯʡ
	 * @return
	 * @throws Exception 
	 */
	public String selectProvince() throws Exception{
		/**
		 * 1.businessID  ��ʲô����ʲô
		 * 2.parentId    ���Ų��õ�����    �ٲ�����ʱparentID=1
		 * 3.���վ��е�       �Ǹ��˱��˾��еĻ���Ȩ��
		 */
		Map map = (Map) super.getSession().getAttribute("permission");//ʡ��
		String authorityid = (String) map.get("ROLE_B_AAGA_I");
		String parentId = getRequest().getParameter("BusinessArea");//��ҳ����ȡ��
		List<String[]> geDepartmentList = new ArrayList<String[]>();//getAllChildAuthDepart();
		List<String[]> geDepartmentListUpdept = new ArrayList<String[]>();//getAllChildAuthDepart();
		String activityId= getRequest().getParameter("actId");
		String activtyNum="";
		String deptBusinessArea="";
		//
		if(authorityid!=null&&!authorityid.equals("")){
			geDepartmentList = geDepartmentService.getAllChildAuthDept(parentId,authorityid,true);//trueҪȫ���������б�	
		}else{//���û���½�Ȩ���룬���ұ༭Ȩ����
			authorityid = (String) map.get("ROLE_B_AAGA_U");
			geDepartmentList = geDepartmentService.getAllChildAuthDept(parentId,authorityid,true);//trueҪȫ���������б�
		}
		if(activityId!=null&&!activityId.equals("")&&!activityId.equals("null")){
			geAddServiceActivity = addServiceActivityService.findAddGeAddServiceActivityByActivityId(activityId);
			if(geAddServiceActivity!=null&&!geAddServiceActivity.equals("")){
				authorityid=geAddServiceActivity.getDeptID();
				if(authorityid.equals("1")||authorityid.equals("2")||authorityid.equals("3")||authorityid.equals("4")){
					deptBusinessArea=authorityid;
				}
			}
			geDepartmentListUpdept = geDepartmentService.getAllChildAuthDept(parentId,authorityid,true);//trueҪȫ���������б�
		}
		JSONObject backJson = new JSONObject();
		backJson.put("backdata", geDepartmentList);
		backJson.put("geDepartmentListUpdept", geDepartmentListUpdept);
		backJson.put("deptBusinessArea", deptBusinessArea);
		super.render(backJson.toString(), "application/json;charset=GBK");
		return NONE;
	}
	
	/**
	 * ��ѯ����
	 * @return
	 * @throws Exception 
	 */
	public String selectCity() throws Exception{
		String authorityidDept="";
		Map map = (Map) super.getSession().getAttribute("permission");//ʡ��
		String authorityid = (String) map.get("ROLE_B_AAGA_I");
		String parentId = getRequest().getParameter("ProvinceId");//��ҳ����ȡ��
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
		if(geDepartmentList.size()==0){//ֱϽ�� ��������
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
	 * �鹤�����ı�,����id����
	 */
	public List<String> findIDsFormWorkFlow() {
		List<String> returnList = new ArrayList<String>();
//		List<EntityWorkFlow> result = new ArrayList<EntityWorkFlow>();
//		GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
//		Map<String, Set<String>> setMap = getWorkFlowGroup(operator.getOperatorid());// �õ��û����ڹ������Ľڵ���û��� ��������
//		
//		try{
//			Set<String> inGroups = setMap.get("group");
//			Set<String> inTasks = setMap.get("taskID");
//			//û����ҳ��ѡ�����Ȩ�ޣ����session�л�ȡ��ǰ��¼�û��Ļ���Ȩ��
//			String authorityid = super.getRequest().getParameter("authorityid");
//			if(StringUtils.isBlank(authorityid)){
//				Map map = (Map) super.getSession().getAttribute("permission");
//				authorityid = (String) map.get("ROLE_B_AAGA_S");
//			}
//			
//			if(inGroups!=null && !inGroups.isEmpty() && inTasks!=null && !inTasks.isEmpty() && authorityid!=null && !"".equals(authorityid)){
//
//				//��ȡ��ѯ����
//				StringBuffer stringBuffer = new StringBuffer();
//				String[] arrayAuth = authorityid.split(",");
//				for(String str : (Set<String>)setMap.get("group")){
//					for(int i = 0; i < arrayAuth.length; i++){
//						stringBuffer.append(str + arrayAuth[i] + ",");
//					}
//				}
//	
//				// ��+��֯�������� ��ƴ���ַ���
//				String groupIDsString = stringBuffer.substring(0,stringBuffer.lastIndexOf(","));
//
//				// ����id ��ƴ���ַ���
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
	 * ��ѯ�������õ�������������
	 * @return
	 */
	public String getWaitMarketDoCount(){
		long totalCount = 0;
		try{
//			GeOperator operator = (GeOperator)super.getSession().getAttribute("geOperator");
//			// ��session�л�ȡ��ǰ��¼�û��Ļ���Ȩ��
//			Map map = (Map) super.getSession().getAttribute("permission");
//			String authorityid = (String) map.get("ROLE_B_AAGA_S");
//			Set<String> set = map.keySet();
//			
//			// ��Ͷ���������ά��Ȩ��
//			if(set.contains("ROLE_B_AAGA_S")){
//				// �����·ַ�Ȩ�ޣ��鳷������
//				if (set.contains("ROLE_ROLE_B_AAGA_AGAIN")) {
//					
//					StringBuffer stringBuffer = new StringBuffer();
//					Map<String, Set<String>> setMap = workFlowServiceImpl.getWorkFlowGroup(operator.getOperatorid(),"marketingWorkFlow");
//					Set<String> inGroups = setMap.get("group");
//					Set<String> inTasks = setMap.get("taskID");
//					if(! inGroups.isEmpty() && ! inTasks.isEmpty()){
//						// ��+��֯�������� ��ƴ���ַ���
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
//				// �з������������ˡ�����Ȩ�ޣ��鴦�����������������ˣ�
//				if( set.contains("ROLE_ROLE_B_AAGA_GU") || set.contains("ROLE_ROLE_B_AAGA_DEAL")||set.contains("ROLE_ROLE_B_AAGA_REPEAL")||set.contains("ROLE_ROLE_B_AAGA_C")||set.contains("ROLE_ROLE_B_AAGA_BACK")){
//					Map<String, Set<String>> setMap = getWorkFlowGroup(operator.getOperatorid());// �õ��û����ڹ������Ľڵ���û��� ��������
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
//							// ��+��֯�������� ��ƴ���ַ���
//							String groupIDsString = stringBuffer.substring(0,stringBuffer.lastIndexOf(","));
//							// ����id ��ƴ���ַ���
//							StringBuffer workFlowBuffer = new StringBuffer();
//							Set<String> sets = setMap.get("taskID");
//							
//							if( !sets.isEmpty()){
//								for(String str : sets){
//									workFlowBuffer.append(str + ",");
//								}
//								String workFlowID = workFlowBuffer.substring(0,workFlowBuffer.lastIndexOf(","));
//								// �����м�¼
//								TaskQuery tq = taskService.createTaskQuery();
//								tq.processVariableValueEquals("entity_Status", "1");
//								totalCount += workFlowServiceImpl.getTotalCount(groupIDsString, workFlowID, tq);
//								// ���˼�¼
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

