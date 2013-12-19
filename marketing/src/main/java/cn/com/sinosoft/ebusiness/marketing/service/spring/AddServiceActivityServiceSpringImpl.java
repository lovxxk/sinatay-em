package cn.com.sinosoft.ebusiness.marketing.service.spring;


import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeRisk;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.BizCommonService;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterSerialNumber;
import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterService;
import cn.com.sinosoft.ebusiness.common.party.service.facade.ThirdParterService;
import cn.com.sinosoft.ebusiness.marketing.domain.GeActivitiesConfig;
import cn.com.sinosoft.ebusiness.marketing.domain.GeActivitiesPicture;
import cn.com.sinosoft.ebusiness.marketing.domain.GeActivitiesProduct;
import cn.com.sinosoft.ebusiness.marketing.domain.GeActivitiesRule;
import cn.com.sinosoft.ebusiness.marketing.domain.GeActivitiesShoppingProduct;
import cn.com.sinosoft.ebusiness.marketing.domain.GeAddServiceActivity;
import cn.com.sinosoft.ebusiness.marketing.domain.GeAddServiceProcess;
import cn.com.sinosoft.ebusiness.marketing.domain.GeAddserviceConditionDto;
import cn.com.sinosoft.ebusiness.marketing.domain.GeCustomAddServiceActivity;
import cn.com.sinosoft.ebusiness.marketing.domain.GeReturnRuleCount;
import cn.com.sinosoft.ebusiness.marketing.domain.GeWorkflowActivity;
import cn.com.sinosoft.ebusiness.marketing.domain.SelectType;
import cn.com.sinosoft.ebusiness.marketing.service.facade.AddServiceActivityService;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectory;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductMain;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.ProductManageService;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.MarketingException;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDepartmentService;
import cn.com.sinosoft.ruleEngine.domain.ActivityInputBOM;
import cn.com.sinosoft.ruleEngine.domain.ActivityResultBOM;
import cn.com.sinosoft.ruleEngine.service.facade.ActivityRuleService;

/**
 * 对增值服务活动进行维护
 * 
 */
public class AddServiceActivityServiceSpringImpl extends
		GenericDaoHibernate<GeAddServiceActivity,String> implements AddServiceActivityService {
	
	private static final Log logger = LogFactory.getLog(AddServiceActivityServiceSpringImpl.class);
	//service
	private ActivityRuleService  activityRuleService ; //规则引型
	private ThirdParterService thirdParterService; // 第三方服务
//	private WorkFlowService workFlowService;   //工作流
//	private GeWorkFlowService geWorkFlowService; //工作流组
	private GeDepartmentService geDepartmentService; //服务地区
	private GeDirectoryService  geDirectoryService ;//产品
	@Autowired
	private ProductManageService productManageService;
	@Autowired
	private BizCommonService bizCommonService;
//	@Autowired
//	private MarktingWorkFlowServiceImpl  markingWorkFlowService;
//	@Autowired
//	private WorkFlowNewService<EntityWorkFlow> workFlowServiceImpl;
	
	//一共保存这些表
	//保存主表  GeAddServiceActivity
	//保存子表 geActivitiesRules
	//保存子表 geActivitiesProducts
	//保存子表中的子表  geActivitiesPictures
	public void addAddGeAddServiceActivityAndRule(GeAddServiceActivity geAddServiceActivity){
		logger.info("*****************addAddGeAddServiceActivityAndRule method start**********************");
		//给图片表做设置   单独进行
		List<String> uploadPictureFileNames = geAddServiceActivity.getUploadPictureFileName();
		List<String> uploadSerialNoList= geAddServiceActivity.getUploadSerialNoList();
		if(uploadPictureFileNames!=null&&uploadPictureFileNames.size()>0){
			List<GeActivitiesPicture> geActivitiesPictureList = new ArrayList<GeActivitiesPicture>();
			for(int i=0;i<uploadPictureFileNames.size();i++){
				String fileName = uploadPictureFileNames.get(i);
				GeActivitiesPicture geActivitiesPicture = new GeActivitiesPicture();
				geActivitiesPicture.setSerialNo(uploadSerialNoList.get(i));
				GeActivitiesPicture pic = geAddServiceActivity.getGeActivitiesPictures().get(Integer.parseInt(uploadSerialNoList.get(i))-1);
				geActivitiesPicture.setJumpUrl(pic.getJumpUrl());
//				geActivitiesPicture.setJumpUrl("1");
				geActivitiesPicture.setGeAddServiceActivity(geAddServiceActivity);
				geActivitiesPictureList.add(geActivitiesPicture);
			}
			geAddServiceActivity.setGeActivitiesPictures(geActivitiesPictureList);
		}
		geAddServiceActivity.setCreateDate(new Date());//创建日期
//		geAddServiceActivity.setStatus("4");//活动状态0:初始 1处理中 2未通过 3通过 4已发布
		super.save(geAddServiceActivity);
		
		//设置上传图片
		try {
			uploadPicture(geAddServiceActivity);//设置上传图片
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info("*****************Picture Error IOException="+sw.toString()+"**********************");
			//需要向market模块抛异常
			throw MarketingException.newInstanceMsg("Picture Error IOException="+sw.toString());
		} catch (Exception e) {				
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info("*****************Picture Error Exception="+sw.toString()+"**********************");
			throw MarketingException.newInstanceMsg("Picture Error Exception="+sw.toString());
			//需要向market模块抛异常
		}//根据页面收集的上传图片的信息来决定图片表是否有信息来存储
		
		//产生UUID与子表互通 
		//给子表geActivitiesRules
		List<GeActivitiesRule> geActivitiesRuleList= geAddServiceActivity.getGeActivitiesRules();
		if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
			for (GeActivitiesRule geActivitiesRuleTemp : geActivitiesRuleList) {
				geActivitiesRuleTemp.setGeAddServiceActivity(geAddServiceActivity);
				if(geActivitiesRuleTemp.getActivityPattern()!=null
						&&!geActivitiesRuleTemp.getActivityPattern().equals("")){
					if(geActivitiesRuleTemp.getActivityPattern().equals("1")){//面向所有客户
						geActivitiesRuleTemp.setNvalue(null);
					}
					if(geActivitiesRuleTemp.getActivityPattern().equals("5")){//加购产品
						List<GeActivitiesShoppingProduct> geActivitiesShoppingProductList = geActivitiesRuleTemp.getGeActivitiesShoppingProducts();
						if(geActivitiesShoppingProductList!=null&&geActivitiesShoppingProductList.size()>0){
							for (GeActivitiesShoppingProduct geActivitiesShoppingProduct : geActivitiesShoppingProductList) {
								geActivitiesShoppingProduct.setGeActivitiesRule(geActivitiesRuleTemp);
							}
						}
					}
					
				}
				//子表加购产品
				
			}
		}
		//给子表geActivitiesProducts
		List<GeActivitiesProduct> geActivitiesProductList=  geAddServiceActivity.getGeActivitiesProducts();
		if(geActivitiesProductList!=null&&geActivitiesProductList.size()>0){
			for (GeActivitiesProduct geActivitiesProductTemp : geActivitiesProductList) {
				geActivitiesProductTemp.setGeAddServiceActivity(geAddServiceActivity);
			}
		}
		
		//存储机构表
		String activityId = geAddServiceActivity.getActivityId();
		String parentDeptId = geAddServiceActivity.getDeptID();
		String sql = "insert into ge_activity_dept select '"+activityId+
				"',deptId from ge_department t where t.deptid not in(select d.parentid from ge_department d where to_number(d.deptlevel) < 5) and to_number(t.deptlevel) < 5"+
		" start with t.parentid = '"+parentDeptId+"' connect by prior t.deptid = t.parentid";
		int result = getSession().createSQLQuery(sql).executeUpdate();
		if(result==0){
			String citysql = "insert into ge_activity_dept select '"+activityId+"', deptId  from ge_department t where t.deptid not in" +
					" (select d.parentid from ge_department d where to_number(d.deptlevel) < 5)" +
					" and to_number(t.deptlevel) < 5 and t.deptid='"+parentDeptId+"'";
			getSession().createSQLQuery(citysql).executeUpdate();
		}
		logger.info("*****************addAddGeAddServiceActivityAndRule method end**********************");
		
		
		
	}
	
	private void  uploadPicture(GeAddServiceActivity geAddServiceActivity) throws IOException, Exception{
		if(geAddServiceActivity!=null
				&&geAddServiceActivity.getUploadPicture()!=null
				&&geAddServiceActivity.getUploadPicture().size()>0){//证明是有图片的
			
			List<GeActivitiesPicture> geActivitiesPictures = new ArrayList<GeActivitiesPicture>();
			List<String> uploadSerialNoList= geAddServiceActivity.getUploadSerialNoList();
			//开始上传文件
			logger.info("*****************addServiceActivities upload**********************");
			List<File> uploadPicture = geAddServiceActivity.getUploadPicture();
			List<String> uploadPictureFileName = geAddServiceActivity.getUploadPictureFileName();
			for(int i =0;i<uploadPicture.size();i++){
				//上传图片的处理
				String attrPictureName = geAddServiceActivity.getActivityId()+"_"+uploadSerialNoList.get(i)+"_"+uploadPictureFileName.get(i);//文件名字：eid+序号+文件名字
				logger.info("*****************attrPictureName="+attrPictureName+"**********************");
				String uploadImageDirectoryPath = geAddServiceActivity.getUploadImagePath()+java.io.File.separator+attrPictureName;
				logger.info("*****************attrPictureName upload Path="+uploadImageDirectoryPath+"**********************");
				//uploadImageDirectoryPaths.add(uploadImageDirectoryPath);//收集每张图片要上传的位置
				File wantUploadPictureFile = new File(uploadImageDirectoryPath);//要上传的图片的物理位置
				FileUtils.write(FileUtils.readBytes(geAddServiceActivity.getUploadPicture().get(i)), wantUploadPictureFile);//两个参数一个是File类型，一个是图片上传位置
				logger.info("*****************picture upload success**********************");
				
				//然后开始组装GeActivitiesProduct表的子表产品图片表
				String savePath  = geAddServiceActivity.getUploadSavePath()+attrPictureName;
				logger.info("*****************want save picture path savePath="+savePath+"**********************");
				geAddServiceActivity.getGeActivitiesPictures().get(i).setPictureUrl(savePath);//图片的URL
				geAddServiceActivity.getGeActivitiesPictures().get(i).setGeAddServiceActivity(geAddServiceActivity);
			}
		}
	}

	private String getEid(GeAddServiceActivity geAddServiceActivity){
		String eids = "";
		List<GeActivitiesProduct> geActivitiesProductList = geAddServiceActivity.getGeActivitiesProducts();
		if(geActivitiesProductList!=null&&geActivitiesProductList.size()>0){
			for(GeActivitiesProduct geActivitiesProduct:geActivitiesProductList){
				eids = eids+ "'"+geActivitiesProduct.getEid()+"'"+",";
			}
			eids = eids.substring(0, eids.length()-1);
		}
		
		return eids;
	}
	//判断是否有重复的活动，若该活动的同一产品在起始时间存在交集，那么就任为是存在活动
	public List<GeCustomAddServiceActivity> isRepeatAddServiceActivity(GeAddServiceActivity geAddServiceActivity){
		List<GeCustomAddServiceActivity> geAddServiceActivitys = new ArrayList<GeCustomAddServiceActivity>();//要返回的结果集
		String eid = getEid(geAddServiceActivity);
		//活动的产品 
		//活动的起始时间
		//活动的结果时间
		//super.getHibernateTemplate().getSessionFactory().
		StringBuilder sb = new StringBuilder();
		sb.append("   select distinct addservice.activityId   as     activityId,        ");//活动ID
		sb.append("          addservice.activityName   as activityName,        ");//活动名称
		sb.append("          addservice.activityStartDate as startDate,        ");//起始时间
	    sb.append("          addservice.activityEndDate as endDate,            ");//结束时间
	    sb.append("          product.eid as eid,                               ");//产品代码
	    sb.append("          geDirectory.productName as productName,           ");//产品名称
	    sb.append("          addservice.status as addStatus                    ");//活动状态
//	    sb.append("          activitiesRule.activityPattern as activityPattern ");//活动方式
	    sb.append("   from   GeAddServiceActivity addservice,                  ");//产品表
	    sb.append("          GeActivitiesProduct product,                      ");//活动表   //geActivitiesRule rule  rule.activitparter in('','')
	    sb.append("          GeActivitiesRule activitiesRule,                  ");//归则表  
	    sb.append("          GeDirectory geDirectory                           ");//产品表
	    sb.append("   where  product.eid in("+eid+")                           ");//产品ID  //in('','','');
        sb.append("   and    addservice.activityId = product.geAddServiceActivity.activityId            ");//查询条件
        sb.append("   and    addservice.activityId = activitiesRule.geAddServiceActivity.activityId     ");//查询条件
        sb.append("   and    product.eid = geDirectory.eid           ");//查询条件
        sb.append("   and    addservice.status not in('2') ");//活动状态不等于未通过的
        sb.append("   and    addservice.deptID = '"+geAddServiceActivity.getDeptID()+"' ");//地域代码
        sb.append("   and    addservice.validInd ='1' ");//是否有效标志位 都是有效的
        if(geAddServiceActivity.getFlag()!=null&&!"".equals(geAddServiceActivity.getFlag())&&"update".equals(geAddServiceActivity.getFlag())){
        sb.append("   and    addservice.activityId!='"+geAddServiceActivity.getActivityId()+"' ");//更新去掉自己的
        }
        if(geAddServiceActivity.getFlag()!=null&&!"".equals(geAddServiceActivity.getFlag())&&"finishPage".equals(geAddServiceActivity.getFlag())){
        sb.append("   and    addservice.activityId!='"+geAddServiceActivity.getActivityId()+"' ");//发布去掉自己的
        sb.append("   and    addservice.status ='3' ");//发布活动时看是否存在同类型活动
        }
        
		Query query = (Query)getSession().createQuery(sb.toString());
		//query.setString(0, geAddServiceActivity.getDeptID());//地域代码.传值不成功，把机构代码直接拼上了
		List list = query.list();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				if(obj!=null&&obj.length>0){
					if(isMixed((Date)obj[2],(Date)obj[3],geAddServiceActivity)){//参数1起始时间   //参数2结束时间  是否存在交集
						//如果存在交集 那么将重复的活动返回
						GeCustomAddServiceActivity geAddServiceActivityTemp = new GeCustomAddServiceActivity();
						geAddServiceActivityTemp.setActivityId((String)obj[0]);//活动ID
						geAddServiceActivityTemp.setActivityName((String)obj[1]);//活动名称
						geAddServiceActivityTemp.setEid((String)obj[4]);//产品代码
						geAddServiceActivityTemp.setProductName((String)obj[5]);//产品名称 
//						geAddServiceActivityTemp.setActivitypattern((String)obj[6]);//活动方式
						geAddServiceActivityTemp.setStartDate(getDateToString((Date)obj[2]));//startDate
						geAddServiceActivityTemp.setEndDate(getDateToString((Date)obj[3]));//endDate
						geAddServiceActivityTemp.setStatus((String)obj[6]);
						geAddServiceActivitys.add(geAddServiceActivityTemp);//添加进去
					}
				}
			}//end for
		}
		return geAddServiceActivitys;
	}
	private String getDateToString(Date date){
		SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}
	//是否存在交集
	private boolean isMixed(Date startDate,Date endDate,GeAddServiceActivity geAddServiceActivity){
		boolean flag = false;
		//表中查询出来的该险种对应的活动的起始日期
		long dbStartTime  = startDate.getTime();
		long dbEndTime = endDate.getTime();
		
		long pageStartTime = geAddServiceActivity.getActivityStartDate().getTime();
		long pageEndTime = geAddServiceActivity.getActivityEndDate().getTime();
		
		//
		//判断是否存在交集 第一种情况
		if(dbStartTime<=pageStartTime&&dbEndTime>=pageEndTime){
			flag = true;
		}
		//判断是否存在交集 第二种情况
		if(dbStartTime<=pageStartTime&&dbEndTime<=pageEndTime&&dbEndTime>=pageStartTime){
			flag = true;
		}
		//判断是否存在交集 第三种情况
		if(pageStartTime<=dbStartTime&&pageEndTime<=dbEndTime&&pageEndTime>=dbStartTime){
			flag = true;
		}
		//判断是否存在交集 第四种情况
		if(pageStartTime<=dbStartTime&&pageEndTime>=dbEndTime){
			flag = true;
		}
		return flag;
	}
	
	
	
	
	
	/**
	 * 查询活动
	 * 按主键查询
	 * @return
	 * @throws Exception 
	 */
	public GeAddServiceActivity findAddGeAddServiceActivityByActivityId(String activityId) throws Exception{
		
		GeAddServiceActivity geAddServiceActivity = super.get(activityId);
		//通过地区代码求地域名称
		geAddServiceActivity.setDeptName(getDeptName(geAddServiceActivity.getDeptID()));
		//设置归则
		List<GeActivitiesRule> geActivitiesRuleList=  geAddServiceActivity.getGeActivitiesRules();
		if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
			for (GeActivitiesRule geActivitiesRuleTemp : geActivitiesRuleList) {
				if(geActivitiesRuleTemp.getPremiumType()!=null){
				if(geActivitiesRuleTemp.getPremiumType().equals("6")){//两者之间
					String peremiumValue = geActivitiesRuleTemp.getPeremiumValue();
					String[] temps = peremiumValue.split("@");
					geActivitiesRuleTemp.setPremiumRange1(temps[0]);
					geActivitiesRuleTemp.setPremiumRange2(temps[1]);
				}
				}
				if(geActivitiesRuleTemp.getActivityPattern()!=null){
				if(geActivitiesRuleTemp.getActivityPattern().equals("5")){//设置归则   加购产品
					List<GeActivitiesShoppingProduct> geActivitiesShoppingProductList =  geActivitiesRuleTemp.getGeActivitiesShoppingProducts();
					for (GeActivitiesShoppingProduct geActivitiesShoppingProduct : geActivitiesShoppingProductList) {
						GeDirectory geDirectory =  geDirectoryService.findGeDirectoryByEId(geActivitiesShoppingProduct.getEid());
						if(geDirectory!=null){
							geActivitiesShoppingProduct.setProductName(geDirectory.getProductName());
						}
					}
				}//end if
				}
			}//end for 
		}//end if
		//通过产品代码查询出产品名称
		List<GeActivitiesProduct> geActivitiesProductList = geAddServiceActivity.getGeActivitiesProducts();
		if(geActivitiesProductList!=null&&geActivitiesProductList.size()>0){
			for (GeActivitiesProduct geActivitiesProductTemp : geActivitiesProductList) {
				GeDirectory geDirectory =  geDirectoryService.findGeDirectoryByEId(geActivitiesProductTemp.getEid());
				if(geDirectory!=null){
					geActivitiesProductTemp.setProductName(geDirectory.getProductName());
					geActivitiesProductTemp.setCoreProductCode(geDirectory.getCoreProductCode());
				}
			}
		}
		//通过商品ID查询出商品名称
		List<GeActivitiesRule> geActivitiesRules = geAddServiceActivity.getGeActivitiesRules();
		if(geActivitiesRules!=null&&geActivitiesRules.size()>0){
			for (GeActivitiesRule geActivitiesRuleTemp : geActivitiesRules) {
				if(geActivitiesRuleTemp.getActivityPattern()!=null){
				if(geActivitiesRuleTemp.getActivityPattern().equals("1")
						||geActivitiesRuleTemp.getActivityPattern().equals("2")
						||geActivitiesRuleTemp.getActivityPattern().equals("3")){
					String s[] = geActivitiesRuleTemp.getItemID().split(",");
					StringBuffer sb = new StringBuffer("");
					for(int i=0;i<s.length;i++){
						GeThirdParterService geThirdParterService = thirdParterService.findGeThirdParterServiceByItemID(s[i]);
						sb.append((i+1)+".").append(geThirdParterService.getItemName()).append("<br/>");
					}
					geActivitiesRuleTemp.setItemName(sb.toString());
				}
				}
			}
		}
		return geAddServiceActivity;
	}
	
	public String getDeptName(String deptId){
		GeDepartment geDepartment = geDepartmentService.findGeDepartmentByPK(deptId);
		String comName = "";
		String pId =deptId;
		for(int i=Integer.parseInt(geDepartment.getDeptlevel());i>0;i--){
			boolean isFlag ;
			if(i==Integer.parseInt(geDepartment.getDeptlevel())){
				comName = geDepartment.getDeptname();
				GeDepartment geDepartmentTempNew = geDepartmentService.findGeDepartmentByPK(pId);
				pId  = geDepartmentTempNew.getParentid();
			}else{
				GeDepartment geDepartmentTemp = geDepartmentService.findGeDepartmentByPK(pId);
				comName = geDepartmentTemp.getDeptname()+"/" + comName;
				pId  = geDepartmentTemp.getParentid();
			}
		}
		return comName;
	}
	/**
	 * 查询活动
	 * 按主键查询
	 * @return
	 */
//	public MarktingWrokFlow findAddGeAddServiceByTaskId(String taskID,String workFlowID){
////		GeAddServiceActivity geAddServiceActivity = super.get(activityId);
//		MarktingWrokFlow marktingWorkFlow = (MarktingWrokFlow)workFlowService.getSingleTask(taskID, workFlowID);
//		marktingWorkFlow.setWorkFlowID(workFlowID);
//		GeAddServiceActivity geAddServiceActivity = (GeAddServiceActivity)marktingWorkFlow.getEntity();
//		if(geAddServiceActivity!=null&&geAddServiceActivity.getGeActivitiesRules()!=null){
//			//并且查询出每个供应商对应的商品信息
//			List<GeActivitiesRule> geActivitiesRuleList = geAddServiceActivity.getGeActivitiesRules();
//			//这个if方法是单独为了活动更新的特殊业务来写的，如果仅仅是按主键来查询一个GeAddServiceActivity活动是不需要以下代码的
//			if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
//				for(GeActivitiesRule geActivitiesRule:geActivitiesRuleList){
//					if(geActivitiesRule.getThirdParterID()!=null&&!geActivitiesRule.getThirdParterID().equals("")){
//						//通过供应商thirdParterID来查询对应的商品
//						GeThirdParterInfo thirdParterInfo = new GeThirdParterInfo();
//						thirdParterInfo.setThirdParterID(geActivitiesRule.getThirdParterID());
//						//
//						
//						GeThirdParterService geThirdParterServiceTemp = new GeThirdParterService();
//						geThirdParterServiceTemp.setGeThirdParterInfo(thirdParterInfo);
//						//
//						List<GeThirdParterService> thirdParterServiceList = new ArrayList<GeThirdParterService>();
//						thirdParterServiceList = thirdParterService.findGeThirdParterService(geThirdParterServiceTemp);
//						geActivitiesRule.setGeThirdParterServiceList(thirdParterServiceList);
//					}
//				}
//			}
//		}
//		return marktingWorkFlow;
//	}
	
	private String getUploadPictureSize(String uploadPictureSerialNos ,int i){
		String count = "";
		String[] uploadSerialNos = uploadPictureSerialNos.split(",");
		for(int k=0;k<uploadSerialNos.length;k++){
			if(uploadSerialNos[k].equals(i+"")){//相等
				count = k+"";
			}
		}
		return count;
	}
	private GeActivitiesPicture getGeActivitiesPictureForUpdate(List<GeActivitiesPicture> geActivitiesPictureTempList,int i){
		if(geActivitiesPictureTempList!=null&&geActivitiesPictureTempList.size()>0){
			for (GeActivitiesPicture geActivitiesPictureTemp: geActivitiesPictureTempList) {
				if(geActivitiesPictureTemp.getSerialNo().equals(i+"")){//以前存在
					geActivitiesPictureTemp.setAutoPictureId(null);//自动生成的图片ID暂时设置为空
					return geActivitiesPictureTemp;
				}
			}// end for 
		}// end if
		return null;
	}
	
	//为了更新方法而组织的上传图片
	private List<GeActivitiesPicture> getGeActivitiesPictureUpdate(String uploadPictureSerialNos,GeAddServiceActivity geAddServiceActivity,List<GeActivitiesPicture> geActivitiesPictureList) throws IOException, Exception{
		List<GeActivitiesPicture> updatePicture = new ArrayList<GeActivitiesPicture>();
		for(int i=1;i<=5;i++){
			//图片1
			if(uploadPictureSerialNos!=null
					&&!uploadPictureSerialNos.equals("")
					&&uploadPictureSerialNos.contains(i+"")){//一定有要上传的图片
				//开始上传文件
				logger.info("*****************addServiceActivities upload i="+i+ "**********************");
				String count = getUploadPictureSize(uploadPictureSerialNos,i);//得拿到List的序号
				File file = geAddServiceActivity.getUploadPicture().get(Integer.parseInt(count));//这个能够取到要上传的图片
				String attrPictureName = geAddServiceActivity.getActivityId()+"_"+i+"_"+geAddServiceActivity.getUploadPictureFileName().get(Integer.parseInt(count));//文件名字：eid+序号+文件名字
				logger.info("*****************attrPictureName="+attrPictureName+"**********************");
				String uploadImageDirectoryPath = geAddServiceActivity.getUploadImagePath()+java.io.File.separator+attrPictureName;//文件上传物理路径
				logger.info("*****************attrPictureName upload Path="+uploadImageDirectoryPath+"**********************");
				File wantUploadPictureFile = new File(uploadImageDirectoryPath);//要上传的图片的物理位置
				FileUtils.write(FileUtils.readBytes(file), wantUploadPictureFile);//两个参数一个是File类型，一个是图片上传位置
				logger.info("*****************picture upload success**********************");
				//组装List
				GeActivitiesPicture geActivitiesPictureUpdate = new GeActivitiesPicture();
				geActivitiesPictureUpdate.setGeAddServiceActivity(geAddServiceActivity);//活动代码
				geActivitiesPictureUpdate.setSerialNo(String.valueOf(i));//图片序号
				String savePath  = geAddServiceActivity.getUploadSavePath()+attrPictureName;
				logger.info("*****************want save picture path savePath="+savePath+"**********************");
				geActivitiesPictureUpdate.setPictureUrl(savePath);
				updatePicture.add(geActivitiesPictureUpdate);
				
			}else{//没有要上传的图片 ，那么看看数据库里原来 是否存在
				GeActivitiesPicture geActivitiesPictureUpdate = getGeActivitiesPictureForUpdate(geActivitiesPictureList,i);
				if(geActivitiesPictureUpdate!=null){//以前数据库里存在图片
					GeActivitiesPicture geActivitiesPictureNew  = new GeActivitiesPicture();
					BeanUtils.copyProperties(geActivitiesPictureUpdate, geActivitiesPictureNew);
					geActivitiesPictureNew.setAutoPictureId(null);
					updatePicture.add(geActivitiesPictureNew);
				}
			}// end if(uploadPictureSerialNos!=null
		}//end for
		return updatePicture;
	}
	
	private void deleteGeActivitPicture(List<GeActivitiesPicture> geActivitiesPictureList){
		super.deleteAll(geActivitiesPictureList);
	}
	//删除子表
	private void deleteGeActivitiesShoppingProduct(){
		StringBuilder sbForRule = new StringBuilder();
		sbForRule.append("delete from GeActivitiesShoppingProduct geActivitiesShoppingProduct where geActivitiesShoppingProduct.geActivitiesRule.ruleId in('ff808081351a715601351a7306380008')");
		Query query = getSession().createQuery(sbForRule.toString());
		query.executeUpdate();
	}
	
	private void deleteGeActivitiesRule(String rulds){
		if(rulds!=null&&!rulds.equals("")){
			String[] ids = rulds.split(",");
			if(ids!=null&&ids.length>0){
				for (String id : ids) {
					super.deleteByPK(GeActivitiesRule.class, id);
				}
			}
		}
	}
	
	private void deleteGeActivitiesProduct(String activityId){
		StringBuilder sb = new StringBuilder();
		sb.append("delete from GeActivitiesProduct geActivitiesProduct where geActivitiesProduct.geAddServiceActivity.activityId = ?");
		Query query = getSession().createQuery(sb.toString());
		query.setString(0, activityId);
		query.executeUpdate();
	}
	//更新活动
	/**
	 *  第二个参数为要修改的图片参数
	 *  
	 *  //定义，所有的图片序号都是以1为开始的
	 *  
	 * 对于多表的更新的 整体思路是先把子表删除掉然后 再进行插入操作
	 * 而主表就是直接更新就可以了
	 * @throws Exception 
	 * @throws IOException 
	 */
	public void updateAddGeAddServiceActivityAndGeActivitiesRule(GeAddServiceActivity geAddServiceActivity,String uploadPictureSerialNos,String rulds){
		GeAddServiceActivity geActPic=new GeAddServiceActivity();
		geActPic=geAddServiceActivity;
		
//		geActPic.getGeActivitiesPictures().get(0).
		//通过活动ID 来查询图片
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geAddServiceActivity", geAddServiceActivity);
		List<GeActivitiesPicture> geActivitiesPictureList = super.find(GeActivitiesPicture.class, queryRule);
		try{
			// 一共就5张图片
			//要更新图片的List 是重新组装
			List<GeActivitiesPicture> updatePicture = getGeActivitiesPictureUpdate(uploadPictureSerialNos,geAddServiceActivity,geActivitiesPictureList);
			
			if(updatePicture!=null&&updatePicture.size()>0){
				for (int i = 0; i < updatePicture.size(); i++) {
					//String str[]=updatePicture.get(i).getPictureUrl().split("_");
					updatePicture.get(i).setJumpUrl(geActPic.getGeActivitiesPictures().get(Integer.parseInt(updatePicture.get(i).getSerialNo())-1).getJumpUrl());//Integer.parseInt(str[1])-1
				}
				/*for (GeActivitiesPicture geActivitiesPicture : updatePicture) {
					geActivitiesPicture.setGeAddServiceActivity(geAddServiceActivity);
				}*/
			}
			geAddServiceActivity.setGeActivitiesPictures(updatePicture);
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info("*****************(update)Picture Error IOException="+sw.toString()+"**********************");
			//需要向market模块抛异常
			throw MarketingException.newInstanceMsg("(update)Picture Error IOException="+sw.toString());
		} catch (Exception e) {				
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info("*****************(update)Picture Error Exception="+sw.toString()+"**********************");
			throw MarketingException.newInstanceMsg("(update)Picture Error Exception="+sw.toString());
			//需要向market模块抛异常
		}
		//删除子表GeActivityPicture 
		deleteGeActivitPicture(geActivitiesPictureList);
		//删除子表 GeActivitiesRule GeActivitiesShoppingProduct
		deleteGeActivitiesRule(rulds);
		//删除子表   GeActivitiesProduct
		deleteGeActivitiesProduct(geAddServiceActivity.getActivityId());
		//删除增值服务活动机构 按主键
		deleteAddGeactivitydept(geAddServiceActivity.getActivityId());
		//然后在进行更新
		//存储机构表
		String activityId = geAddServiceActivity.getActivityId();
		String parentDeptId = geAddServiceActivity.getDeptID();
		String sql = "insert into ge_activity_dept select '"+activityId+
				"',deptId from ge_department t where t.deptid not in(select d.parentid from ge_department d where to_number(d.deptlevel) < 5) and to_number(t.deptlevel) < 5"+
		" start with t.parentid = '"+parentDeptId+"' connect by prior t.deptid = t.parentid";
		int result = getSession().createSQLQuery(sql).executeUpdate();
		if(result==0){
			String citysql = "insert into ge_activity_dept select '"+activityId+"', deptId  from ge_department t where t.deptid not in" +
					" (select d.parentid from ge_department d where to_number(d.deptlevel) < 5)" +
					" and to_number(t.deptlevel) < 5 and t.deptid='"+parentDeptId+"'";
			getSession().createSQLQuery(citysql).executeUpdate();
		}
		
		//设置 UUID
		setGeActivitiesProductUUID(geAddServiceActivity);
		super.save(geAddServiceActivity);
		//设置GeActivitiesRule GeActivitiesShoppingProduct表的UUID
		setGeActivitiesRuleAndGeActivitiesShoppingProductUUID(geAddServiceActivity);

	}
	
	private void setGeActivitiesRuleAndGeActivitiesShoppingProductUUID(GeAddServiceActivity geAddServiceActivity){
		List<GeActivitiesRule> geActivitiesRuleList = geAddServiceActivity.getGeActivitiesRules();
		if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
			for (GeActivitiesRule geActivitiesRule : geActivitiesRuleList) {
				geActivitiesRule.setGeAddServiceActivity(geAddServiceActivity);
				String  activityPattern = geActivitiesRule.getActivityPattern();
				if((activityPattern!=null&&!activityPattern.equals(""))
						&&(activityPattern.equals("1")||activityPattern.equals("5"))){//面向所有用户  加购
					geActivitiesRule.setNvalue(null);
					
				}
				if(activityPattern!=null&&!activityPattern.equals("")&&activityPattern.equals("5")){//加购产品 
					List<GeActivitiesShoppingProduct> geActivitiesShoppingProducts = geActivitiesRule.getGeActivitiesShoppingProducts();
					if(geActivitiesShoppingProducts!=null&&geActivitiesShoppingProducts.size()>0){
						for (GeActivitiesShoppingProduct geActivitiesShoppingProduct : geActivitiesShoppingProducts) {
							geActivitiesShoppingProduct.setGeActivitiesRule(geActivitiesRule);
						}
					}//end if(geActivitiesShoppingProducts!=null
				}// end if(activityPattern!=null
			}// end for 
		}// end if(geActivitiesRuleList!=null
	}// end 
	
	private void setGeActivitiesProductUUID(GeAddServiceActivity geAddServiceActivity){
		List<GeActivitiesProduct> geActivitiesProductList = geAddServiceActivity.getGeActivitiesProducts();
		if(geActivitiesProductList!=null&&geActivitiesProductList.size()>0){
			for (GeActivitiesProduct geActivitiesProduct : geActivitiesProductList) {
				geActivitiesProduct.setGeAddServiceActivity(geAddServiceActivity);
			}
		}
	}
	//先将规则表中的数据删除,目的是在更新的时候会重新插入
	public void deleteGeActivitiesRulesByActivityId(GeAddServiceActivity geAddServiceActivity){
		//按照活动的ID将规则表中的数据给删除掉
		String deleteSql = "delete GeActivitiesRule as geActivitiesRule where geActivitiesRule.geAddServiceActivity.activityId=:activityId";
		Query   query2 = getSession().createQuery(deleteSql);
		query2.setParameter("activityId", geAddServiceActivity.getActivityId()); 
		query2.executeUpdate();
		//
	}
	//更新图片表
	public void updateGeActivitiesPictures(GeAddServiceActivity geAddServiceActivity){
		if(!geAddServiceActivity.isSameProductFlag()){//不是同一款产品
			//将原来的那个产品流水号对应的图片给删除掉
			String deleteSql = "delete GeActivitiesPicture as geActivitiesPicture where geActivitiesPicture.geActivitiesProduct.activitiesProductId=:activitiesProductId";
			Query   query2 = getSession().createQuery(deleteSql);
			//因为现在是单活动  单产品 所以直接用geAddServiceActivity.getGeActivitiesProducts().get(0)
			query2.setParameter("activitiesProductId", geAddServiceActivity.getGeActivitiesProducts().get(0).getAutoActivitiesProductId()); 
			query2.executeUpdate();
		}
	}
	
	//更新状态 
	public GeAddServiceActivity updateAddGeAddServiceActivity(GeAddServiceActivity geAddServiceActivity) throws IOException, Exception{
		GeAddServiceActivity update =   super.get(geAddServiceActivity.getActivityId());
		update.setStatus(geAddServiceActivity.getStatus());//修改状态
		
		//
		super.update(update);
		return update;
	}
	
	//更新状态   供给 最后的进度   进行修改   即 Finial(工作流)  使用的
	public GeAddServiceActivity updateAddGeAddServiceActivity(GeAddServiceActivity geAddServiceActivity,boolean finialFlag) throws IOException, Exception{
		if(finialFlag){
			GeAddServiceActivity update =   super.get(geAddServiceActivity.getActivityId());
			update.setStatus(geAddServiceActivity.getStatus());//修改状态
			List<GeAddServiceProcess>  geAddServiceProcessList = geAddServiceActivity.getGeAddServiceProcesses();
			if(geAddServiceProcessList!=null){
				for (GeAddServiceProcess geAddServiceProcess : geAddServiceProcessList) {
					geAddServiceProcess.setHandleStatus("3");  //已发布
				}
			}
			super.update(update);
			return update;
		}else{
			return null;
		}
	}

	//在更新之前判断是否有其他的活动也有该险种
	public GeAddServiceActivity isRepeatForUpdate(GeAddServiceActivity geAddServiceActivity){
		StringBuilder sb = new StringBuilder();
		sb.append("   select addservice.activityId   as     activityId,  ");//活动ID
		sb.append("          addservice.activityName   as activityName,  ");//活动名称
		sb.append("          addservice.activityStartDate as startDate,  ");//起始时间
	    sb.append("          addservice.activityEndDate as endDate,      ");//结束时间
	    sb.append("          product.eid as eid                          ");//产品代码
	    sb.append("   from   GeAddServiceActivity addservice,            ");//产品表
	    sb.append("          GeActivitiesProduct product                 ");//活动表
	    sb.append("   where  product.eid = ?                             ");//产品ID
        sb.append("   and    addservice.activityId = product.geAddServiceActivity.activityId  ");//查询条件	   
        sb.append("   and    addservice.status not in('2') ");//活动状态不等于未通过的
        sb.append("   and    addservice.deptID =? ");//地域代码
		Query query = (Query)getSession().createQuery(sb.toString());
		query.setString(0, geAddServiceActivity.getGeActivitiesProducts().get(0).getEid());//产品表
		query.setString(1, geAddServiceActivity.getDeptID());//地域代码
		List list = query.list();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				if(obj!=null&&obj.length>0){
					if(!((String)obj[0]).equals(geAddServiceActivity.getActivityId())){//查询的活动与本次的活动不一致
						if(isMixed((Date)obj[2],(Date)obj[3],geAddServiceActivity)){//参数1起始时间   //参数2结束时间
							//如果存在交集 那么将重复的活动返回
							GeAddServiceActivity geAddServiceActivityTemp = new GeAddServiceActivity();
							geAddServiceActivityTemp.setActivityId((String)obj[0]);//活动ID
							geAddServiceActivityTemp.setActivityName((String)obj[1]);//活动名称
							return geAddServiceActivityTemp;//存在重复的
						}
					}
					
				}
			}
		}
		return null;
	}
	public List<GeActivitiesPicture> queryGeActivitiesPictureByFK(GeActivitiesProduct geActivitiesProduct){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geActivitiesProduct", geActivitiesProduct);
		return super.find(GeActivitiesPicture.class, queryRule);
	}
	/**
	 * 审核通过
	 * @param taskID
	 * @param userID
	 * @param obj
	 */
	public void auditAcvity(String taskID,String workFlowId,String userID){
//		MarktingWrokFlow marktingWorkFlow = (MarktingWrokFlow)workFlowService.getSingleTask(taskID, workFlowId);
//		marktingWorkFlow.setType("2");
//		marktingWorkFlow.setWorkFlowID(workFlowId);
//		workFlowService.completeTask(taskID, userID, marktingWorkFlow);
//		GeAddServiceActivity geAddServiceActivity = (GeAddServiceActivity)marktingWorkFlow.getEntity();
//		//处理中
//		geAddServiceActivity.setStatus("2");
//		super.update(geAddServiceActivity);
	}
	
	/**
	 * 打回 
	 * @param taskID
	 * @param workFlowId
	 * @param userID
	 * @param type
	 */
	public void failureAutit(String taskID,String workFlowId,String userID){
//		MarktingWrokFlow marktingWorkFlow = (MarktingWrokFlow)workFlowService.getSingleTask(taskID, workFlowId);
//		marktingWorkFlow.setType("2");
//		GeAddServiceActivity geAddServiceActivity = (GeAddServiceActivity)marktingWorkFlow.getEntity();
//		geAddServiceActivity.setStatus("2");
//		workFlowService.completeTask(taskID, userID, marktingWorkFlow);
//		super.update(geAddServiceActivity);
	}
	
	/**
	 * 
	 * 用于支付回调，将商品状态置为有效
	 */
	public List updateGeThirdParterServiceValidInd(){
		return null;
	}
	
	public List<GeAddServiceActivity> queryAddServiceActivityBySQL(GeActivitiesRule geActivitiesRule) throws ParseException{
		StringBuilder sb = new StringBuilder();
		sb.append("   select distinct addservice.activityId,             ");//活动ID
		sb.append("          addservice.activityStartDate as startDate,  ");//开始时间
	    sb.append("          addservice.activityEndDate as endDate,      ");//结束时间
	    sb.append("          addservice.deptID                    ,      ");//地区
	    
	    sb.append("          geActivitiesRule.premiumType         ,      ");//保费类型
	    sb.append("          geActivitiesRule.peremiumValue       ,      ");//保费的值
	    sb.append("          geActivitiesRule.nvalue                     ");//n的值
	    
	    sb.append("   from   GeAddServiceActivity addservice,            ");//活动表
	    sb.append("          GeActivitiesProduct product,                 ");//产品表
	    sb.append("          GeActivitiesRule geActivitiesRule           ");//规则表
	    sb.append("   where  addservice.activityStartDate <= to_date('"+getDateToDateString(new Date())+"','yyyy-MM-dd')");//起 始日期小于等于当前日期
	    sb.append("   and    addservice.activityEndDate >= to_date('"+getDateToDateString(new Date())+"','yyyy-mm-dd')");//结束日期大于等于当前日期
        sb.append("   and    addservice.deptID = ?");//查询条件地域
        sb.append("   and    addservice.status = '3'");//查询条件状态为3为已处理
        sb.append("   and    addservice.validInd = '1'");//查询条件状态为1为有效
        sb.append("   and    geActivitiesRule.activityPattern in ('1','2','3','4','5')");//查询条件活动方式为1或2或3的即为白给赠送的或抽奖打折加购
       
        sb.append("   and    addservice.activityId = geActivitiesRule.geAddServiceActivity.activityId");
        sb.append("   and    addservice.activityId = product.geAddServiceActivity.activityId");
        sb.append("   and    product.eid = ?");//查询条件险种代码
        
        Query query = getSession().createQuery(sb.toString());
        query.setString(0, geActivitiesRule.getDeptID());//机构代码
        query.setString(1, geActivitiesRule.getEid());//险种代码   应该变为eid
        List list =  query.list();
        List<GeAddServiceActivity> geAddServiceActivitys= new ArrayList<GeAddServiceActivity>();
        if(list!=null&&list.size()>0){
        	for (int i = 0; i < list.size(); i++) {
        	GeAddServiceActivity geAddServiceActivity = new GeAddServiceActivity();
        	Object[] objs = (Object[])list.get(i);
        	geAddServiceActivity.setActivityId((String)objs[0]);
        	geAddServiceActivity.setDeptID((String)objs[3]);
        	geAddServiceActivity.setPremiumType((String)objs[4]);
        	geAddServiceActivity.setPeremiumValue((String)objs[5]);
        	geAddServiceActivitys.add(geAddServiceActivity);
        	}
        	return geAddServiceActivitys;
        }else{//没有活动的
        	return null;
        }
        
		
	}
	//通过条件查询增值服务活动，如果本机构没有，查询他的父级机构，用于递归查询活动的 
	public List<GeAddServiceActivity> queryAddServiceActivityRecursion(GeActivitiesRule geActivitiesRule) throws ParseException{
		//geActivitiesRule.setEid(getRiskCodeToEid(geActivitiesRule.getRiskCode()));
		List<GeAddServiceActivity>  geAddServiceActivity = queryAddServiceActivityBySQL(geActivitiesRule);
		if(geAddServiceActivity==null){//该机构下没有找到对应的活动，那么取他的上一级机
			GeDepartment geDepartment= geDepartmentService.findGeDepartmentByPK(geActivitiesRule.getDeptID());//以前用过的
			if(!"0".equals(geDepartment.getParentid())){//
				GeDepartment geDepartmentTemp= geDepartmentService.findGeDepartmentByPK(geDepartment.getParentid());//以前用过的
				geActivitiesRule.setDeptID(geDepartmentTemp.getDeptid());
				geAddServiceActivity = (List<GeAddServiceActivity>) queryAddServiceActivityRecursion(geActivitiesRule);//用他的你机构递归
			}
		}
		return  geAddServiceActivity;
	}
	//通过险种代码   查询 产品的eid
	public String getRiskCodeToEid(String riskCode){
		//车险的险种代码  去eid中查询
		StringBuffer sb = new StringBuffer();
		sb.append("select    g.eid                         ");//产品的eid  
		sb.append("from      ge_directory g                ");//产品目录表
		sb.append("where     g.coreproductcode = ?         ");//险种代码
		sb.append("and       g.salechannel in ('01', '04') ");//销售渠道 为网销 或电网销
		sb.append("and       g.isproductshelf = '01'       ");//是否上架   01为上架
		SQLQuery sqlQuery = super.getSession().createSQLQuery(sb.toString());
		sqlQuery.setString(0, riskCode);
		
		List list = sqlQuery.list();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				String eid = (String)list.get(0);
				return eid;
			}
		}
		return "";
	}
	//去查询是否有活动，若存在，则组装成规则引擎List<GeActivitiesRule>
	public List<GeActivitiesRule> queryAddServiceActivityForProduct(List<GeActivitiesRule> geActivitiesRuleList) throws ParseException{
		List<GeActivitiesRule> geActivitiesRuleTempList = new ArrayList<GeActivitiesRule>();//要返回的List
		//
		if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
			GeActivitiesRule geActivitiesRuleTemp = new GeActivitiesRule();
			
			for (GeActivitiesRule geActivitiesRuleNewTemp : geActivitiesRuleList) {
				//根据地区，险种代码 去查询出 符合条件的活动
				List<GeAddServiceActivity>  geAddServiceActivityTemp = queryAddServiceActivityRecursion(geActivitiesRuleNewTemp);//一个产品对应的营销活动
				if(geAddServiceActivityTemp!=null){//拿到了营销活动
					//在看看这个归则是否在一个固定的保费范围内 若在则  组装归则
					if(isPeremiumRange(geActivitiesRuleNewTemp,geAddServiceActivityTemp)){//在保费范围内则
						//返回的规则
						//通过增值的
						List<GeActivitiesRule> geActivitiesRules= queryGeActivitiesRuleList(geAddServiceActivityTemp.get(0).getActivityId());
						if(geActivitiesRules!=null&&geActivitiesRules.size()>0){
							for (GeActivitiesRule geActivitiesRule : geActivitiesRules) {
								geActivitiesRule.setDeptID(geAddServiceActivityTemp.get(0).getDeptID());//Temp是从库里查询出来的
								geActivitiesRule.setEid(geActivitiesRuleNewTemp.getEid());//NewTemp是从页面传过来的值
								geActivitiesRule.setProposalNo(geActivitiesRuleNewTemp.getProposalNo());//NewTemp是从页面传过来的值
								geActivitiesRule.setProposalArea(geActivitiesRuleNewTemp.getProposalArea());//NewTemp是从页面传过来的值
							}
							return geActivitiesRules;
						}
						
						
//						GeActivitiesRule geActivitiesRuleReturn = new GeActivitiesRule();
//						BeanUtils.copyProperties(geActivitiesRuleNewTemp, geActivitiesRuleReturn);
//						geActivitiesRuleReturn.setDeptID(geAddServiceActivityTemp.getDeptID());//用于规则引擎的机构代码
//						geActivitiesRuleTempList.add(geActivitiesRuleReturn);//返回LIST开始用来装值
					}
				}
			}
		}
		//
		//return geActivitiesRuleTempList;
		return null;
	}
	private List<GeActivitiesRule> queryGeActivitiesRuleList(String activityId){
		//通过
		//List<GeActivitiesRule> geActivitiesRules  =  new ArrayList<GeActivitiesRule>();
		GeAddServiceActivity geAddServiceActivity = new GeAddServiceActivity();
		geAddServiceActivity.setActivityId(activityId);
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geAddServiceActivity", geAddServiceActivity);
		
		List<GeActivitiesRule> geActivitiesRuleList = super.find(GeActivitiesRule.class, queryRule);
		return geActivitiesRuleList;
	}
	
	/**
	 * @param geAddServiceActivityFromPage 第一个参数是 长虎从页过来的带过来的数据
	 * @param geAddServiceActivity  第二个参数是从数据库里查询出来的
	 * @return
	 * 
	 * 然后比较 长虎从页面过来的数据 是否在 数据库中是先配好的范围
	 */
	/*
	<option value="1">不限</option>
	<option value="2">小于</option>
	<option value="3">小于等于</option>
	<option value="4">大于</option>
	<option value="5">大于等于</option>
	<option value="6">两者之间</option>
	*/
	private boolean isPeremiumRange(GeActivitiesRule geAddServiceActivityFromPage,List<GeAddServiceActivity> geAddServiceActivity){
	
		if(geAddServiceActivity.get(0).getPremiumType()!=null&&geAddServiceActivity.get(0).getPremiumType().equals("1")){//不限
			return true;
		}
		//营销活动后台规则配置了保费，前台未送来保费值，直接return false;
		if(geAddServiceActivity.get(0).getPremiumType().equals("2")||geAddServiceActivity.get(0).getPremiumType().equals("3")||geAddServiceActivity.get(0).getPremiumType().equals("4")||geAddServiceActivity.get(0).getPremiumType().equals("5")||geAddServiceActivity.get(0).getPremiumType().equals("6")){
			if(geAddServiceActivityFromPage.getPeremiumValue()==null||geAddServiceActivityFromPage.getPeremiumValue().equals("")){
				return false;
			}
		}
		if(geAddServiceActivity.get(0).getPremiumType()!=null&&geAddServiceActivity.get(0).getPremiumType().equals("2")){//小于
			String premiumFromPage = geAddServiceActivityFromPage.getPeremiumValue();
			String premiumFromDB  = geAddServiceActivity.get(0).getPeremiumValue();
			if(Double.parseDouble(premiumFromPage)<Double.parseDouble(premiumFromDB)){//小于
				return true;
			}
		}
		if(geAddServiceActivity.get(0).getPremiumType()!=null&&geAddServiceActivity.get(0).getPremiumType().equals("3")){//小于等于
			String premiumFromPage = geAddServiceActivityFromPage.getPeremiumValue();
			String premiumFromDB  = geAddServiceActivity.get(0).getPeremiumValue();
			if(Double.parseDouble(premiumFromPage)<=Double.parseDouble(premiumFromDB)){//小于等于
				return true;
			}
		}
		if(geAddServiceActivity.get(0).getPremiumType()!=null&&geAddServiceActivity.get(0).getPremiumType().equals("4")){//大于
			String premiumFromPage = geAddServiceActivityFromPage.getPeremiumValue();
			String premiumFromDB  = geAddServiceActivity.get(0).getPeremiumValue();
			if(Double.parseDouble(premiumFromPage)>Double.parseDouble(premiumFromDB)){//大于
				return true;
			}
		}
		if(geAddServiceActivity.get(0).getPremiumType()!=null&&geAddServiceActivity.get(0).getPremiumType().equals("5")){//大于等于
			String premiumFromPage = geAddServiceActivityFromPage.getPeremiumValue();
			String premiumFromDB  = geAddServiceActivity.get(0).getPeremiumValue();
			if(Double.parseDouble(premiumFromPage)>=Double.parseDouble(premiumFromDB)){//大于等于
				return true;
			}
		}
		if(geAddServiceActivity.get(0).getPremiumType()!=null&&geAddServiceActivity.get(0).getPremiumType().equals("6")){//两者之间
			
			for (int i = 0; i < geAddServiceActivity.size(); i++) {
				String premiumFromPage = geAddServiceActivityFromPage.getPeremiumValue();
				String premiumFromDB  = geAddServiceActivity.get(i).getPeremiumValue();
				String[] ranges = premiumFromDB.split("@");
				String range0 = ranges[0];
				String range1 = ranges[1];
				if(Double.parseDouble(premiumFromPage)>=Double.parseDouble(range0)
						&&Double.parseDouble(premiumFromPage)<Double.parseDouble(range1)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 *   
	 * 需要传的值为
	 * geActivitiesRuleList  eid 投保地区  geAddServiceActivityTemp.getDeptID()
	 *  
	 * 
	 * 整个赠送，抽奖，1  走归则 返回  商品的List
	 *                打折   3 
	 *                
	 * 加购   2 加购产品List 
	 * 
	 * 图片   4 图片List  两套长虎有可能出现两个险种，车险流程显示哪套图片
	 * 
	 * 查询增值服务活动，按规则引擎来查询
	 * 返回一个商品
	 * 直接返回为空，那么没有赠送的商品
	 * 如果不为空，得看活动类型来判断是直接显示该商品
	 * 还是直接去抽奖该商品
	 * 
	 * Map  key="GeThirdParterServices"   返回白送的商品和抽奖的商品   需要走规则引擎
	 * Map  key="GeDirectorys"   返回返回要加购的产品目录
	 * 
	 * 
	 * 长虎直接调用的
	 * GeActivitiesRule
	 * @throws ParseException 
	 */
	///public List<GeThirdParterService> findAddGeAddServiceActivityCheckRule(List<GeActivitiesRule> geActivitiesRuleList,String userId) throws ParseException{
	public java.util.Map findAddGeAddServiceActivityCheckRule(List<GeActivitiesRule> geActivitiesRuleList,GeAddserviceConditionDto geAddserviceConditionDto) throws ParseException{
//		//长虎的入参的组装
//		List<GeActivitiesRule> geActivitiesRules = new ArrayList<GeActivitiesRule>();
//		GeActivitiesRule geActivitiesRule = new GeActivitiesRule();
//		geActivitiesRule.setDeptID(deptID);
//		geActivitiesRule.setEid("");
//		geActivitiesRules.add(geActivitiesRule);
//		//
//		GeAddserviceConditionDto geAddserviceConditionDto = new  GeAddserviceConditionDto();
//		geAddserviceConditionDto.setUserId("");
//		geAddserviceConditionDto.setPictureFlag(false);//不要图片
//		geAddserviceConditionDto.setSystemFlowId("01");//流程页面
		//要返回的结果集    
		java.util.Map map = new HashMap();//要返回的结果集
		List<GeDirectory> geDirectorys  = new ArrayList<GeDirectory>();//要加购的结果集
		List<GeThirdParterService> returnGeThirdParterServiceList = new ArrayList<GeThirdParterService>();//用于返回的白给赠送抽奖 的结果集 
		List<GeActivitiesPicture> geActivitiesPictures = new ArrayList<GeActivitiesPicture>();//用于返回营销活动的广告图片
		String isDiscountFlag = "false";//打折:用于告诉 是否调用规则引擎
		String discountID = "";//寿险的折扣ID    老的
		String newDiscountID = "";//寿险的折扣ID    老的
		
		//传入的查询条件
		String systemFlowId = geAddserviceConditionDto.getSystemFlowId();//流程ID 系统流水ID 01 车险 02 寿险 
		String userId = geAddserviceConditionDto.getUserId();//userId  送给谁 用户ID
		boolean isPictureFlag = geAddserviceConditionDto.isPictureFlag(); //是否要图片
		List<String>  wantedActivityPatterns = geAddserviceConditionDto.getWantedActivityPatterns(); //选择活动方式
		
		GeActivitiesPicture	pictureJumpUrl=null;
		java.util.Map discountAndJumpUrl = new HashMap();//返回跳转的url地址和图片
		
		//setProposalAreaProperty(geActivitiesRuleList);   //就是一个简单的赋值操作 通过deptId 赋给proposalArea
		List<GeActivitiesRule> geActivitiesRuleListForRule = queryAddServiceActivityForProduct(geActivitiesRuleList);//能得到所有的要调用的规则  获取活动规则
		
		if(geActivitiesRuleListForRule!=null&&geActivitiesRuleListForRule.size()>0){
			//是否要返回用于返回营销活动的广告图片
			geActivitiesPictures = isPictureFlag ?   geActivitiesRuleListForRule.get(0).getGeAddServiceActivity().getGeActivitiesPictures()  :   null;
			
			//活动方式 
			List<ActivityInputBOM> activityInputBOMList = new ArrayList<ActivityInputBOM>();//归则引擎要用的DTO
			for(GeActivitiesRule geActivitiesRuleTemp:geActivitiesRuleListForRule){
				//activityPattern = 1,2,3 走规则引擎   5是加购产品
				String activityPattern = geActivitiesRuleTemp.getActivityPattern();
				if(activityPattern!=null&&!activityPattern.equals("")){

					//白送  前N名  抽奖 
					//System.out.println("前台传来保费："+geActivitiesRuleList.get(0).getPeremiumValue());
					if(activityPattern.equals("1")||activityPattern.equals("2")||activityPattern.equals("3")){//1，2，3，白给商品的
						
						if(systemFlowId.equals("01")){//深圳北京车险页面流程
//							if(geActivitiesRuleTemp.getPremiumType().equals("6")){
//								setActivityInputBOMProperty(geActivitiesRuleTemp,activityInputBOMList,geActivitiesRuleList.get(0).getPeremiumValue());
//							}else{
							setActivityInputBOMProperty(geActivitiesRuleTemp,activityInputBOMList,geActivitiesRuleList.get(0).getPeremiumValue());//第一个是活动规则  第二个是规则引型
//							}
						}
						
						if(systemFlowId.equals("02")){//寿险页面流程
							if(wanted(activityPattern,wantedActivityPatterns)){//他想要  1:赠送  2:前N名 3抽奖
								setActivityInputBOMProperty(geActivitiesRuleTemp,activityInputBOMList,null);
							}
						}
					}//白送 前N名 N%的情况 
					
					
					
					//打折
					if(activityPattern.equals("4")){//打折
						if(systemFlowId.equals("02")){//寿险产品页面流程
							
							if(wanted(activityPattern,wantedActivityPatterns)){//打折  想不想要打着
								geActivitiesPictures = geActivitiesRuleTemp.getGeAddServiceActivity().getGeActivitiesPictures();
								discountID = geActivitiesRuleTemp.getDiscountID();
								GeActivitiesProduct pro=new GeActivitiesProduct();
									GeActivitiesRule  geActivitiesRule=geActivitiesRuleList.get(0);
									for (int i = 0; i < geActivitiesRuleTemp.getGeAddServiceActivity().getGeActivitiesProducts().size(); i++) {
										pro=geActivitiesRuleTemp.getGeAddServiceActivity().getGeActivitiesProducts().get(i);
										if(pro.getEid().equals(geActivitiesRule.getEid())){
											
											newDiscountID=pro.getDiscountID();
											discountAndJumpUrl.put("newDiscountID", newDiscountID);
										}
										
									}
									for (int i = 0; i < geActivitiesRuleTemp.getGeAddServiceActivity().getGeActivitiesPictures().size(); i++) {
										pictureJumpUrl=geActivitiesRuleTemp.getGeAddServiceActivity().getGeActivitiesPictures().get(i);
										discountAndJumpUrl.put("pictureUrl"+i, pictureJumpUrl.getPictureUrl());
										discountAndJumpUrl.put("jumpUrl"+i, pictureJumpUrl.getJumpUrl());
									}
								//discountID = geActivitiesRuleTemp.getGeAddServiceActivity().getGeActivitiesProducts();//"aaaaaaaaaaa";//geActivitiesRuleTemp.getDiscountID();
								isDiscountFlag = "true";//告诉小彬
							}
						}
						//财还没处理
						
					}//打折  结束 
					
					
					
					//加购
					if(activityPattern.equals("5")){//加购产品
						
						
						if(systemFlowId.equals("01")
								||(systemFlowId.equals("02")&&wanted(activityPattern, wantedActivityPatterns))){
							
							List<GeActivitiesShoppingProduct> geActivitiesShoppingProducts = geActivitiesRuleTemp.getGeActivitiesShoppingProducts();
							if(geActivitiesShoppingProducts!=null&&geActivitiesShoppingProducts.size()>0){
								
								String[] eids = new String[geActivitiesShoppingProducts.size()];
								for(int i=0;i<geActivitiesShoppingProducts.size();i++){
									eids[i] = geActivitiesShoppingProducts.get(i).getEid();
								}
								List<GeDirectory> geDirectoryTemps = geDirectoryService.findGeDirectoryByEids(eids);//
								if(geDirectoryTemps!=null&&geDirectoryTemps.size()>0){
									for (GeDirectory geDirectory : geDirectoryTemps) {
										//看看List中是否已存在重复的加购产品
										if(geDirectorys!=null&&geDirectorys.size()>0){//若存在了加购产品得判断该产品是否已在List中存在了
											boolean flag = true;
											for (GeDirectory geDirectoryExist : geDirectorys) {
												if(geDirectoryExist.getEid().equals(geDirectory.getEid())){
													flag =  false;
												}
											}
											
											if(flag){
												geDirectorys.add(geDirectory);
											}
											
										}else{
											geDirectorys.add(geDirectory);//第一次加购产品 
										}
									}
								}// end if(geDirectoryTemps!=null&&geDirectoryTemps.size()>0){..
								
							}
						}
						
						
						
					}// end if(activityPattern.equals("5")){  加购产品
				}//end if(activityPattern!=null&&!activityPattern.equals("")){
			}//end for
			
			
			
			for(int i=0; i<geActivitiesRuleList.size();i++){
				if(!"".equals(geActivitiesRuleList.get(i).getConnectFlag())&& null != geActivitiesRuleList.get(i).getKindCode() ){
					activityInputBOMList.get(i).setConnectFlag(geActivitiesRuleList.get(i).getConnectFlag());
					activityInputBOMList.get(i).setKindCode(geActivitiesRuleList.get(i).getKindCode());
				}
			}
			//调用规则引擎
			List<ActivityResultBOM> activityResultBOMList = activityRuleService.getActivityInfo(activityInputBOMList);
			
			//测试使用
//			List<ActivityResultBOM> activityResultBOMList = new ArrayList<ActivityResultBOM>();
			//for update
//			ActivityResultBOM activityResultBOM1 = new ActivityResultBOM();
//			activityResultBOM1.setDeptID("3210000");//辽宁
//			activityResultBOM1.setStartDate("2012-01-01 00:01:02");
//			activityResultBOM1.setEndDate("2012-01-10 00:01:02");
//			activityResultBOM1.setRiskCode("0510");
//			activityResultBOM1.setItemID("8a80c4f7349d617b01349d6976e40001");
//			activityResultBOM1.setActivityPattern("1");
//			activityResultBOM1.setProposalNo("testssss");
//			activityResultBOM1.setProposalArea("3210600");//丹东
//			activityResultBOM1.setnValue("3");
//			activityResultBOMList.add(activityResultBOM1);
//			
//			ActivityResultBOM activityResultBOM2 = new ActivityResultBOM();
//			activityResultBOM2.setDeptID("3210000");//辽宁
//			activityResultBOM2.setStartDate("2012-01-01 00:01:02");
//			activityResultBOM2.setEndDate("2012-01-10 00:01:02");
//			activityResultBOM2.setRiskCode("0507");
//			activityResultBOM2.setItemID("8a80c4f7349d617b01349d6976e40001");
//			activityResultBOM2.setActivityPattern("1");
//			activityResultBOM2.setProposalNo("testssss1");
//			activityResultBOM2.setProposalArea("3210100");//沈阳
//			activityResultBOM2.setnValue("3");
//			activityResultBOMList.add(activityResultBOM2);
			
			
			if(activityResultBOMList!=null&&activityResultBOMList.size()>0){//处理从规则引擎返回的数据
				//我们的DTO的组装
				for(ActivityResultBOM activityResultBOMTemp:activityResultBOMList){
					
					if(activityResultBOMTemp.getActivityPattern()!=null
						&&!"".equals(activityResultBOMTemp.getActivityPattern())){//活动方式 
						
						
						//第一种活动方式
						if(activityResultBOMTemp.getActivityPattern().equals("1")){//面向所有客户
							//这个方法是保存流水表的信息 同时 把页面上需要的商品给返回
							GeThirdParterService geThirdParterService = saveGeThirdParterSerialNumberAndReturnGethirdParterService(userId,activityResultBOMTemp);
							returnGeThirdParterServiceList.add(geThirdParterService);
						}
						
						
						
						//第二种活动方式
						if(activityResultBOMTemp.getActivityPattern().equals("2")){//面向前N名客户
							if(activityResultBOMTemp.getnValue()!=null
								&&!activityResultBOMTemp.getnValue().equals("")){
								
								if(findItecmCount(activityResultBOMTemp)){//属于前N名客户
									//这个方法是保存流水表的信息 同时 把页面上需要的商品给返回
									GeThirdParterService geThirdParterService = saveGeThirdParterSerialNumberAndReturnGethirdParterService(userId,activityResultBOMTemp);
									returnGeThirdParterServiceList.add(geThirdParterService);
								}else{
									//不属于前N名客户     不生成商品流水号
									//return null;
								}
							}else{
								throw MarketingException.newInstanceCode("002");//当活动方式tActivityPattern=2,面向前N名客户时，N的值不能为空
							}
						}//第二种活动 end
						
						
						//第三种活动方式 
						if(activityResultBOMTemp.getActivityPattern().equals("3")){//面向N%客户
							//返回商品的活动方式 ，提醒用户进行抽奖
							//将规则里的所有要素给返回
							GeThirdParterService gethirdParterService = new GeThirdParterService();
							gethirdParterService.setActivityPattern(activityResultBOMTemp.getActivityPattern());
							gethirdParterService.setItemID(activityResultBOMTemp.getItemID());
							gethirdParterService.setnValue(activityResultBOMTemp.getnValue());
							gethirdParterService.setStartDate(activityResultBOMTemp.getStartDate());
							gethirdParterService.setEndDate(activityResultBOMTemp.getEndDate());
							gethirdParterService.setDeptID(activityResultBOMTemp.getDeptID());//规则引擎用于的机构代码
							gethirdParterService.setRiskCode(activityResultBOMTemp.getRiskCode());
							gethirdParterService.setProposalNo(activityResultBOMTemp.getProposalNo());
							gethirdParterService.setProposalArea(activityResultBOMTemp.getProposalArea());//投保地区
							returnGeThirdParterServiceList.add(gethirdParterService);
						}
					}else{
						throw MarketingException.newInstanceCode("001");//规则引擎中返回的数据中，活动类型为空
					}//if(activityResultBOMTemp.getActivityPattern()!=null end
				}//for(ActivityResultBOM activityResultBOMTemp:activityResultBOMList){  end
			}else{
				//若从规则引擎未返回任何东西，那么直接返回NULL
			}
		}
		
		map.put("GeDirectorys", (geDirectorys!=null||geDirectorys.size()>0)?geDirectorys:null);
		map.put("returnGeThirdParterServiceList", returnGeThirdParterServiceList);
		map.put("GeActivitiesPictures", geActivitiesPictures);
		map.put("isDiscountFlag", isDiscountFlag);
		map.put("discountID", discountID);//寿险用的折扣ID
		map.put("discountAndJumpUrl", discountAndJumpUrl); //寿险用的折扣ID和图片跳转的URL地址<map>
		return map;
	}
	
	/**
	 * 组装 规则引擎DTO
	 * @param geActivitiesRuleTemp
	 * @param activityInputBOMList
	 */
	public void setActivityInputBOMProperty(GeActivitiesRule geActivitiesRuleTemp,List<ActivityInputBOM> activityInputBOMList,String Premium ){
	
		String deptId = geActivitiesRuleTemp.getDeptID();
		String eid = geActivitiesRuleTemp.getEid();
		
		if(activityInputBOMList!=null&&activityInputBOMList.size()>0){//先看是否有重复的  DeptID,RiskCode
			boolean flag = true;
			for (ActivityInputBOM activityInputBOM : activityInputBOMList) {
				if(activityInputBOM.getRiskCode().equals(eid)
						&&activityInputBOM.getDeptID().equals(deptId)){
					flag = false;
				}
			}
			
			if(flag){
				ActivityInputBOM activityInputBOM = new ActivityInputBOM();
				activityInputBOM.setActivityDate(new Date());
				activityInputBOM.setDeptID(geActivitiesRuleTemp.getDeptID());//地区
				activityInputBOM.setRiskCode(geActivitiesRuleTemp.getEid());//现在变为产品代码Eid了
				activityInputBOM.setProposalNo(geActivitiesRuleTemp.getProposalNo());
				activityInputBOM.setProposalArea(geActivitiesRuleTemp.getProposalArea());
				if(Premium!=null){
					activityInputBOM.setPeremiumValue(Double.parseDouble(Premium));
				}
				activityInputBOMList.add(activityInputBOM);
			}
			
		}else{
			ActivityInputBOM activityInputBOM = new ActivityInputBOM();
			activityInputBOM.setActivityDate(new Date());
			activityInputBOM.setDeptID(geActivitiesRuleTemp.getDeptID());//地区
			activityInputBOM.setRiskCode(geActivitiesRuleTemp.getEid());//现在变为产品代码Eid了
			activityInputBOM.setProposalNo(geActivitiesRuleTemp.getProposalNo());
			activityInputBOM.setProposalArea(geActivitiesRuleTemp.getProposalArea());
			if(Premium!=null){
				activityInputBOM.setPeremiumValue(Double.parseDouble(Premium));
			}
			activityInputBOMList.add(activityInputBOM);
		}
		
		
		
	}
	
	/**
	 * 要想哪种活动方式 
	 * @param activityPattern
	 * @param wantedActivityPatterns
	 * @return
	 */
	private boolean wanted(String activityPattern,List<String> wantedActivityPatterns){
		if(wantedActivityPatterns!=null&&wantedActivityPatterns.size()>0){
			for (String temp : wantedActivityPatterns) {
				if(temp.equals(activityPattern)){
					return true;
				}
			}
		}
		return false;
	}
	//设置投保地区
	private void setProposalAreaProperty(List<GeActivitiesRule> geActivitiesRuleList){
		if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
			for (GeActivitiesRule geActivitiesRule : geActivitiesRuleList) {
				geActivitiesRule.setProposalArea(geActivitiesRule.getDeptID());//投保地区
			}
		}
	}
	//保存商品的流水 ，然后返回那个需求的商品
	private GeThirdParterService saveGeThirdParterSerialNumberAndReturnGethirdParterService(String userId,ActivityResultBOM activityResultBOMTemp){
		//set thirdParterServer
		GeThirdParterService gethirdParterService = new GeThirdParterService();
		gethirdParterService = thirdParterService.findGeThirdParterServiceByItemID(activityResultBOMTemp.getItemID());
		
		GeThirdParterSerialNumber geThirdParterSerialNumber = new GeThirdParterSerialNumber();
		geThirdParterSerialNumber.setGeThirdParterService(gethirdParterService);
		geThirdParterSerialNumber.setUserID(userId);//set User
		geThirdParterSerialNumber.setProposalNo(activityResultBOMTemp.getProposalNo());//设置投保单号
		geThirdParterSerialNumber.setOpertionDate(new Date());//操作时间
		geThirdParterSerialNumber.setValidInd("0");//设置初始无效状态
		geThirdParterSerialNumber.setCount("1");//商品数量
		geThirdParterSerialNumber.setProposalArea(activityResultBOMTemp.getProposalArea());
		thirdParterService.addGeThirdParterSerialNumber(geThirdParterSerialNumber);//保存geThirdParterSerialNumber
		
		//直接将商品 添加进去
		gethirdParterService.setActivityPattern(activityResultBOMTemp.getActivityPattern());
		return gethirdParterService;
	}
	/**
	 * 即活动类型为2的这种情况
	 * 判断是否是前N名
	 * @param geReturnRuleCount
	 * @return
	 */
	public synchronized boolean findItecmCount(ActivityResultBOM activityResultBOM){
		QueryRule queryRule =  QueryRule.getInstance();
		String startDate = activityResultBOM.getStartDate();
		String endDate = activityResultBOM.getEndDate();
		Date activityStartDate = null ;
		Date activityEndDate = null; 
		try{
			activityStartDate = getToDateSecond(startDate);
			activityEndDate = getToDateSecond(endDate);
			//set activityStartDate
			queryRule.addEqual("activityStartDate", activityStartDate);
			//set activityEndDate
			queryRule.addEqual("activityEndDate", activityEndDate);
		}catch (Exception e) {
			MarketingException.newInstanceCode("003");//字符串日期转化为Date日期错误
		}
		//set deptID
		if(activityResultBOM.getDeptID()!=null
			&&!activityResultBOM.getDeptID().equals("")){
			queryRule.addEqual("deptID", activityResultBOM.getDeptID());
		}else{
			MarketingException.newInstanceCode("004");//规则引擎返回的参数当中地域deptID不能为空
		}
		//set riskCode
		if(activityResultBOM.getRiskCode()!=null
			&&!"".equals(activityResultBOM.getRiskCode())){
			queryRule.addEqual("riskCode", activityResultBOM.getRiskCode());
		}else{
			MarketingException.newInstanceCode("005");//规则引擎返回的参数当中险种riskCode不能为空
		}
		//set itemID
		if(activityResultBOM.getItemID()!=null
			&&!activityResultBOM.getItemID().equals("")){
			queryRule.addEqual("itemID", activityResultBOM.getItemID());
		}else{
			MarketingException.newInstanceCode("006");//规则引擎返回的参数当中商品代码itemID不能为空
		}
		//set activityPattern
		queryRule.addEqual("activityPattern", activityResultBOM.getActivityPattern());
		//nValue is not null
		if(activityResultBOM.getnValue()==null||activityResultBOM.getnValue().equals("")){
			MarketingException.newInstanceCode("007");//规则引擎返回的参数当中活动类型面向前N名客户但是N的值为空
		}
		List<GeReturnRuleCount> geReturnRuleCountList = super.find(GeReturnRuleCount.class, queryRule);
		if(geReturnRuleCountList!=null&&geReturnRuleCountList.size()>0){
			//将数据查询出来，比较N的值然后 判断更新
			GeReturnRuleCount geReturnRuleCount = (GeReturnRuleCount)geReturnRuleCountList.get(0);
			String itemCountTemp = geReturnRuleCount.getItemCount();
			itemCountTemp = String.valueOf((Integer.parseInt(itemCountTemp)+1));
			//将规则引擎取到的n的值与数据库中的n的值进行比较
			String n = activityResultBOM.getnValue();
			if(Integer.parseInt(itemCountTemp)<=(Integer.parseInt(n))){
				geReturnRuleCount.setItemCount(itemCountTemp);
				//更新count++
				updateGeReturnRuleCount(geReturnRuleCount);
				return true;//属于面向前N名客户
			}else{
				return false;//不属于面向前N名客户
			}
		}else{//查询时 没有结果集，那么往GE_RETURNRULE_COUNT 插入该结果:即第一条
			GeReturnRuleCount geReturnRuleCount = new GeReturnRuleCount();
			geReturnRuleCount.setActivityStartDate(activityStartDate);
			geReturnRuleCount.setActivityEndDate(activityEndDate);
			geReturnRuleCount.setDeptID(activityResultBOM.getDeptID());
			geReturnRuleCount.setRiskCode(activityResultBOM.getRiskCode());
			geReturnRuleCount.setItemID(activityResultBOM.getItemID());
			geReturnRuleCount.setActivityPattern(activityResultBOM.getActivityPattern());
			geReturnRuleCount.setItemCount("1");//插入即是第一条
			//保存计数器
			addGeReturnRuleCount(geReturnRuleCount);
			return true;//属于面向前N名客户
		}
	}
	/**
	 * 免费抽奖 是否能中奖一个产品
	 */
	public List<GeThirdParterService>  findFreePrizeDraw(List<GeThirdParterService> geThirdParterServiceList,String userId){
		List<GeThirdParterService> dealList = new ArrayList<GeThirdParterService>();//要返回给调用者的List
		
		if(geThirdParterServiceList!=null&&geThirdParterServiceList.size()>0){
			for(GeThirdParterService geThirdParterServiceTemp :geThirdParterServiceList){
				int n=0;
				if(geThirdParterServiceTemp.getnValue()!=null&&!geThirdParterServiceTemp.equals("")){
					n=Integer.parseInt(geThirdParterServiceTemp.getnValue());
				}
				///n = 80;//暂时写死
				/**
				 * N%
				 * 
				 * 在 0-100 之间产生一个随机数
				 * 
				 * 看这个随机数是否小于N
				 * 
				 */
				Random random = new Random();
				int mun = random.nextInt(100)+1; 
				if(mun<=n){//中奖
					
					//生成商品流水号
					GeThirdParterSerialNumber geThirdParterSerialNumber = new GeThirdParterSerialNumber();
					//set User
					geThirdParterSerialNumber.setUserID(userId);
					//set thirdParterServer
					GeThirdParterService gethirdParterService = new GeThirdParterService();
					gethirdParterService = thirdParterService.findGeThirdParterServiceByItemID(geThirdParterServiceTemp.getItemID());
					geThirdParterSerialNumber.setGeThirdParterService(gethirdParterService);
					//
					geThirdParterSerialNumber.setProposalNo(geThirdParterServiceTemp.getProposalNo());//设置投保单号
					geThirdParterSerialNumber.setOpertionDate(new Date());//操作时间
					geThirdParterSerialNumber.setValidInd("0");//设置初始无效状态
					geThirdParterSerialNumber.setCount("1");//商品数量
					geThirdParterSerialNumber.setProposalArea("");//
					thirdParterService.addGeThirdParterSerialNumber(geThirdParterSerialNumber);//保存geThirdParterSerialNumber
					//直接将商品 添加进去
					dealList.add(gethirdParterService);
				}else{//没中奖
					//直接返回为空 
				}
			}
		}//end if(geThirdParterServiceList!=null&&geThirdParterServiceList.size()){
		return dealList;
	}

	/**
	 * 提供给长虎   图片的路径获取,一进页面就显示图片
	 * geActivitiesRuleList  里面只传这两个入参就行
	 * eid
	 * deptId
	 * 只有一个
	 * @throws Exception 
	 */
	//第一版
//	public List<GeActivitiesPicture> getGeActivitiesPictureList(List<GeActivitiesRule> geActivitiesRuleList) throws Exception{
//		//查询是否有增值服务优惠活动，若有了，则把前台要显示的图片给他
//		//要返回的结果
//		List<GeActivitiesPicture> geActivitiesPictureList = new ArrayList<GeActivitiesPicture>();//要返回的结果
//		
//		if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
//			for (GeActivitiesRule geActivitiesRuleNewTemp : geActivitiesRuleList) {
//				//根据地区，险种代码 去查询出 符合条件的活动
//				GeAddServiceActivity  geAddServiceActivityTemp = queryAddServiceActivityRecursion(geActivitiesRuleNewTemp);//一个产品对应的营销活动
//				if(geAddServiceActivityTemp!=null){//拿到了营销活动
//					//在看看这个归则是否在一个固定的保费范围内 若在则  组装归则
//					if(isPeremiumRange(geActivitiesRuleNewTemp,geAddServiceActivityTemp)){//在保费范围内则
//						GeAddServiceActivity geAddServiceActivityAgain = findAddGeAddServiceActivityByActivityId(geAddServiceActivityTemp.getActivityId());
//						List<GeActivitiesPicture> pictureTempList = geAddServiceActivityAgain.getGeActivitiesPictures();
//						for (GeActivitiesPicture geActivitiesPicture : pictureTempList) {
//							geActivitiesPictureList.add(geActivitiesPicture);   //可能会存在同一组的图片
//						}
//					}// end if
//				}
//			}//end for
//		}
//		return geActivitiesPictureList;
//	}
	
	/**
	 *第二版，第一个页面要返回的图片 
	 */
	public List<GeActivitiesPicture> getGeActivitiesPictureList(String eid,String deptId) throws Exception{
		//要返回的结果
		List<GeActivitiesPicture> geActivitiesPictureList = new ArrayList<GeActivitiesPicture>();//要返回的结果
		GeActivitiesRule geActivitiesRuleNewTemp = new GeActivitiesRule();
		geActivitiesRuleNewTemp.setEid(eid);//eid
		geActivitiesRuleNewTemp.setDeptID(deptId);
		
		List<GeAddServiceActivity>  geAddServiceActivityTemp = queryAddServiceActivityRecursion(geActivitiesRuleNewTemp);//一个产品对应的营销活动
		if(geAddServiceActivityTemp!=null){//拿到了营销活动
			//在看看这个归则是否在一个固定的保费范围内 若在则  组装归则
//			if(isPeremiumRange(geActivitiesRuleNewTemp,geAddServiceActivityTemp)){//在保费范围内则
				GeAddServiceActivity geAddServiceActivityAgain = findAddGeAddServiceActivityByActivityId(geAddServiceActivityTemp.get(0).getActivityId());
				List<GeActivitiesPicture> pictureTempList = geAddServiceActivityAgain.getGeActivitiesPictures();
				for (GeActivitiesPicture geActivitiesPicture : pictureTempList) {
					geActivitiesPictureList.add(geActivitiesPicture);   //可能会存在同一组的图片
				}
//			}// end if
		}
		return geActivitiesPictureList;
	}
	/**
	 * 删除增值服务活动
	 * 按活动主键
	 */
	public void deleteAddGeAddServiceActivity(String activityId){
		
		if(activityId!=null&&!activityId.equals("")){
			deleteAddGeactivitydept(activityId);
			String[] activityIds = activityId.split(",");
			for(int i=0;i<activityIds.length;i++){
				super.deleteByPK(activityIds[i]);
			}
		}
	}
	/**
	 * 删除增值服务活动机构
	 * 按活动主键
	 */
	public void deleteAddGeactivitydept(String activityId){
		if(activityId!=null&&!activityId.equals("")){
			Query query = getSession().createSQLQuery("delete from  ge_activity_dept where ACTIVITYID = ? ");
			query.setString(0, activityId);
			query.executeUpdate();		
		}
	}
	/**
	 * 查询规则,按查询条件来查,不分页
	 * @param geActivitiesRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<GeActivitiesRule> findGeActivitiesRule(GeActivitiesRule geActivitiesRule){
		QueryRule queryRule = QueryRule.getInstance();
		if(geActivitiesRule!=null){
			if(geActivitiesRule.getRuleId()!=null&&!geActivitiesRule.getRuleId().equals("")){
				queryRule.addEqual("ruleId", geActivitiesRule.getRuleId());
			}
			if(geActivitiesRule.getGeAddServiceActivity()!=null
				&&geActivitiesRule.getGeAddServiceActivity().getActivityId()!=null
				&&!"".equals(geActivitiesRule.getGeAddServiceActivity().getActivityId())){
				queryRule.addEqual("geAddServiceActivity", geActivitiesRule.getGeAddServiceActivity());
			}
			//活动时间
			
		}
		return null;
	}
	
	/**
	 * 查询GeReturnRuleCount
	 * @param queryRule
	 * @return
	 */
	public List<GeReturnRuleCount> findGeReturnRuleCount(QueryRule queryRule){
		if(queryRule!=null){
			List<GeReturnRuleCount> geReturnRuleCountList = super.find(GeReturnRuleCount.class, queryRule);
			return geReturnRuleCountList;
		}else{
			return null;
		}
	}
	/**
	 * 向规则返回商品计数器-GE_ReturnRule_Count 里增加数据
	 * @param geReturnRuleCount
	 */
	public void addGeReturnRuleCount(GeReturnRuleCount geReturnRuleCount){
		super.save(geReturnRuleCount);
	}
	/**
	 * 向规则返回商品计数器-GE_ReturnRule_Count 更新数据
	 * @param geReturnRuleCount
	 */
	public void updateGeReturnRuleCount(GeReturnRuleCount geReturnRuleCount){
		super.update(geReturnRuleCount);
	}
	//******************************************GeActivitiesConfig operator start*********************************
	//分页查询GeActivitiesConfig表中的值
	public Page queryGeActivitiesConfig(GeActivitiesConfig geActivitiesConfig,int pageNo,int pageSize){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("validInd", "1");//是否有效标志1：有效 0：无效
		if(geActivitiesConfig!=null){
			if(geActivitiesConfig.getProductid()!=null&&!geActivitiesConfig.getProductid().equals("")){
				queryRule.addEqual("productid", geActivitiesConfig.getProductid());
			}
			if(geActivitiesConfig.getProductName()!=null&&!geActivitiesConfig.getProductName().equals("")){
				queryRule.addLike("productName", "%"+geActivitiesConfig.getProductName().trim()+"%");
			}
			if(geActivitiesConfig.getPictureCount()!=null&&!geActivitiesConfig.getPictureCount().equals("")){
				queryRule.addEqual("pictureCount", geActivitiesConfig.getPictureCount());
			}
		}
		return super.find(GeActivitiesConfig.class, queryRule, pageNo, pageSize);
	}
	//只能主健来查询
	public GeActivitiesConfig queryGeActivitiesConfigByProductId(String productId){
		return super.get(GeActivitiesConfig.class, productId);
	}
	//******************************************GeActivitiesConfig operator end***********************************
	/**
	 * 2010-01-01 01:01这种形式 精确到分
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	private Date getToDateSecond(String date) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.parse(date);
	}
	private String getDateToDateString(Date date) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}
	//按后台的MIS操作员登录进去默认的机构权限来查询增值服务活动
	@Override
	public Page findAddGeAddServiceActivityByDefaultPermession(
			HttpSession session, GeAddServiceActivity geAddServiceActivity,
			int pageNo, int pageSize,String groupSql,String bizType) {
		//先得拿出默认公司的ID没有分页
		String authorityid="";
		Map map = (Map) session.getAttribute("permission");
		if(bizType!=null&&!"look".equals(bizType)){
			 authorityid = (String) map.get("ROLE_B_AAGA_S");//第三方最外层的权限名字
		}else{
			 authorityid = (String) map.get("ROLE_ROLE_B_AAGA_SEE");//第三方最外层的权限名字
		}
		if(groupSql==null||"".equals(groupSql)){
			groupSql =" and 1=1 ";
			
		}
		List<String> values = Arrays.asList(authorityid.split(","));
		StringBuffer sql = new StringBuffer("select distinct a.* from GE_ADDSERVICE_ACTIVITY a ,ge_activity_dept d   where a.activityid = d.activityid and ");
		if(geAddServiceActivity.getDeptID()!=null&&!geAddServiceActivity.getDeptID().equals("")){
			String []deptIds = geAddServiceActivity.getDeptID().split(",");
			String sqldept="(d.DEPTID in";
			if(deptIds!=null && deptIds.length > 0){
				int authorityIdSize = deptIds.length;
				int authorityIdNumber = (authorityIdSize-1)/1000+1;
				String nextParentIdString = "";
				for(int j = 0; j < authorityIdNumber; j++){
					if(nextParentIdString.length()>0){
						 nextParentIdString = "";
						 sqldept+="or d.DEPTID in";
					}
					int loopNum = 1000;
					if(j == authorityIdNumber-1){
						loopNum = authorityIdSize - (authorityIdNumber-1)*1000;
					}
					for (int i = 0; i < loopNum; i++) {
						String childDeptId =(String)deptIds[1000*j+i];
						if(i == 0){
							nextParentIdString += "'"+childDeptId+"'";
						}else{
							nextParentIdString += ",'"+childDeptId+"'";
						}
					}
					sqldept += "("+nextParentIdString+")";
				}
				sqldept+=")";
			}
			sql.append(sqldept);
			sql.append(groupSql);
		}else{
			if(values.size() >= 1000){//地区机构
				String sql1 = authorityid.substring(0,authorityid.indexOf(values.get(values.size()/2+1)));
				sql1 = sql1.substring(0,sql1.length()-1);
				String sql2 = authorityid.substring(authorityid.indexOf(values.get(values.size()/2+1)));
				String sql3 = " (d.DEPTID in ('" + sql1.replaceAll(",", "','") + "') or d.DEPTID in ('" + sql2.replaceAll(",", "','") + "')) ";
				//queryRule.addSql(sql3);
				sql.append(sql3);
				sql.append(groupSql);
			}else{
				
				//queryRule.addIn("deptID", values);
				sql.append("d.deptID in (");
				for(int i=0;i<values.size();i++){
					sql.append("'").append(values.get(i)).append("',");
				}
				sql =new StringBuffer(sql.substring(0,sql.length()-1));
				sql.append(")");
				sql.append(groupSql);
			}
		}
		
		if(geAddServiceActivity!=null){
			if(geAddServiceActivity.getActivityName()!=null&&!geAddServiceActivity.getActivityName().equals("")){//活动名称
				//queryRule.addLike("activityName", "%"+geAddServiceActivity.getActivityName().trim()+"%");
				sql.append(" and activityName like '%"+geAddServiceActivity.getActivityName().trim()+"%'");
			}
			if(geAddServiceActivity.getValidInd()!=null&&!geAddServiceActivity.getValidInd().equals("")){//是否有效
				sql.append(" and VALIDIND = '"+geAddServiceActivity.getValidInd().trim()+"'");
			}
			if(geAddServiceActivity.getStatus()!=null&&!geAddServiceActivity.getStatus().equals("")){//活动状态
//				queryRule.addEqual("status", geAddServiceActivity.getStatus().trim());
				if("all".equals(geAddServiceActivity.getStatus())){//全部查询撤销&待审核
					sql.append(" and status in ('0','5')");
				}else{
					sql.append(" and status = '"+geAddServiceActivity.getStatus().trim()+"'");
				}
			}
		}
		sql.append(" order by a.CREATEDATE  desc");
		String countSql = sql.toString().replace("a.*", "count(distinct a.activityid)");
		Object o = getSession().createSQLQuery(countSql).uniqueResult();
		int totalCount = 0;
		if(o!= null){
			totalCount = ((BigDecimal)o).intValue();
		}
		if (totalCount < 1L) {
			 return new Page();
		}
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		
		query.addEntity(GeAddServiceActivity.class);
		query.setFirstResult((pageNo-1)*pageSize);
		query.setMaxResults(pageSize);
		
		
		return new Page(startIndex, totalCount,pageSize, query.list());
//		return  super.find(queryRule, pageNo, pageSize);
	}
	
	
	public Page queryHasProcessed(int pageNo, int pageSize,String operatorId,String actName){
		String hql="";
		if(actName!=null&&!actName.equals("")){
			 hql = "select distinct t from GeAddServiceActivity t , GeAddServiceProcess p   where p.geAddServiceActivity = t  and t.activityName like '%"+actName+"%'  and p.operatorID =?";
		}else{
			 hql = "select distinct t from GeAddServiceActivity t , GeAddServiceProcess p   where p.geAddServiceActivity = t and p.operatorID =?";
		}
		return super.findByHql(hql, pageNo, pageSize, operatorId);//(pageNo-1)*pageSize分页
	}
	
	/*
	 * 保存工作流 
	 */
	public void addGeAddServiceProcess(GeAddServiceProcess geAddServiceProcess ){
		super.save(geAddServiceProcess);
	}
	
	
	//以下部分所有的功能都是用于工作流功能调用的方法***************************************
	//*************************************************************************************************************
	//*************************************************************************************************************
	//*************************************************************************************************************
	
	//开启一个工作流
	public void startTask(GeAddServiceProcess geAddServiceProcess ,String activityId,String taskId,String workflowId) throws IOException, Exception{
		//调用我自己的业务 //修改一下本地库的状态 
		GeAddServiceActivity geAddServiceActivityCondition = new GeAddServiceActivity();
		geAddServiceActivityCondition.setActivityId(activityId);//工作流状态
		geAddServiceActivityCondition.setStatus("1");//工作流状态 
		GeAddServiceActivity  geAddServiceActivity =  updateAddGeAddServiceActivity(geAddServiceActivityCondition);
		
		//保存本地库轨迹状态
		geAddServiceProcess.setGeAddServiceActivity(geAddServiceActivity);
		addGeAddServiceProcess(geAddServiceProcess);
		
		
		StringBuffer cityCodes = new StringBuffer("");
		Query query = getSession().createSQLQuery("select deptId from ge_activity_dept where activityid=?");
		query.setString(0, activityId);
		List<String> listDeptId = query.list();
		for(int i=0;i<listDeptId.size();i++){
			if(i==listDeptId.size()-1)
				cityCodes.append(listDeptId.get(i));
			else
				cityCodes.append(listDeptId.get(i)).append(",");
		}
		
		/*
		
		//调用工作流的处理状态
		//******************启动工作流
		MarktingWrokFlow<GeAddServiceActivity> cf  = new MarktingWrokFlow<GeAddServiceActivity>();
		//这个3是地区代码 
		cf.setWorkFlowID("marketingWorkFlow"+getArea(geAddServiceActivity));  // 业务领域动态拼接     comboAreaWorkFlow是你工作流的名字   3是业务领域 ，3暂时写死的
		cf.setEntity(geAddServiceActivity);
		*//**从ge_workflow表获取人员配置信息*//*
		//以下这个是写死的
		GeWorkflowId id = new GeWorkflowId();
		id.setFuncitontype("WORKFLOWCONFIG");
		id.setArea("CC");
		id.setFiletype("0");
		cf.setCity(geAddServiceActivity.getDeptID());
		GeWorkflow geWorkflow = geWorkFlowService.findGeWorkflowByPK(id);
		//cf.setCity(areaCode);
		markingWorkFlowService.startTask(geWorkflow,cf);*/
		
//		EntityWorkFlow<GeAddServiceActivity> wf = new EntityWorkFlow<GeAddServiceActivity>();
//		wf.setWorkFlowID("marketingWorkFlow"+getArea(geAddServiceActivity));  // 业务领域动态拼接     comboAreaWorkFlow是你工作流的名字   3是业务领域 ，3暂时写死的
//		Map<String,Object> params = new HashMap<String, Object>();
//		params.put("entity_Status", "1");
//		params.put("entity_activityName", geAddServiceActivity.getActivityName());
//		wf.setEntity(geAddServiceActivity);
//		String[] result = workFlowServiceImpl.startTask(wf,params,cityCodes.toString());
//		// 把节点id和工作流id存入主表
//		geAddServiceActivity.setRunintTaskId(result[0]);
//		geAddServiceActivity.setWorkFlowId(result[1]);
		// 入附表
//		List<GeWorkflowActivity> list = new ArrayList<GeWorkflowActivity>();
//		GeWorkflowActivity gw = new GeWorkflowActivity();
//		String allUserGroups = result[2];
//		if(allUserGroups!=null){
//			String [] cityCodes1=cityCodes.toString().split(",");
//			String[] groups = allUserGroups.split(",");
//			for (int j = 0; j < groups.length; j++) {
//			for (int i = 0; i < cityCodes1.length; i++) {
//				String city = groups[j] +cityCodes1[i].toString();//geAddServiceActivity.getDeptID()
//				gw = new GeWorkflowActivity();
//				gw.setGeAddserviceActivity(geAddServiceActivity);
//				gw.setCity(city);
//				gw.setCreatedate(new Date());
//				list.add(gw);
//			}
//			}
//		}
//		geAddServiceActivity.setGeWorkflowActivities(list);
		super.update(geAddServiceActivity);
	}
	
	//以下各方法均为action在组装数据时所要用到的方法
	private String  getArea(GeAddServiceActivity geAddServiceActivity){
		String deptId = geAddServiceActivity.getDeptID();
		deptId = deptId.substring(0, 1);
		return deptId;
	}
	
	//处理一个工作流
	public void completeTask(GeAddServiceProcess geAddServiceProcess, String activityId,String taskId,String workflowId) throws IOException, Exception{
		//我写我自己业务
		
		//修改本地库增值服务状态
		GeAddServiceActivity  geAddServiceActivityCondition  =super.get(activityId);
		geAddServiceActivityCondition.setActivityId(activityId);//
		geAddServiceActivityCondition.setStatus("1");//  工作流处理中状态 
		GeAddServiceActivity geAddServiceActivity = updateAddGeAddServiceActivity(geAddServiceActivityCondition);
		//保存本地库轨迹状态
		geAddServiceProcess.setGeAddServiceActivity(geAddServiceActivity);
		addGeAddServiceProcess(geAddServiceProcess);
		// 刷新的参数
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("entity_Status",geAddServiceActivity.getStatus());
		param.put("workFlow_entity",geAddServiceActivity);
//		String newTaskId = workFlowServiceImpl.completeTask(taskId, workflowId,geAddServiceActivity, param,geAddServiceActivity.getDeptID());
		// 刷新运行节点
//		geAddServiceActivity.setRunintTaskId(newTaskId);
		super.update(geAddServiceActivity);
		/*//调用工作流的功能
		MarktingWrokFlow<GeAddServiceActivity> c =	this.markingWorkFlowService.getSingleTask(taskId, workflowId);
		c.setWorkFlowID(workflowId);
		c.setEntity(geAddServiceActivity);
		markingWorkFlowService.completeTask(taskId, null, c);*/
	}
	
	//回退一个工作流
	public void doRoolBack(GeAddServiceProcess geAddServiceProcess ,String activityId,String taskId,String workflowId,String status) throws IOException, Exception{
		
		//修改增值服务活动表的状态
		//GeAddServiceActivity  geAddServiceActivityCondition  = new GeAddServiceActivity();
		GeAddServiceActivity geAddServiceActivityCondition =   super.get(activityId);
		geAddServiceActivityCondition.setActivityId(activityId);//
		geAddServiceActivityCondition.setStatus(status);//  工作流回退状态 
		GeAddServiceActivity geAddServiceActivity = updateAddGeAddServiceActivity(geAddServiceActivityCondition);
		//保存本地库轨迹状态
		geAddServiceProcess.setGeAddServiceActivity(geAddServiceActivity);
		addGeAddServiceProcess(geAddServiceProcess);
		
		//调用工作流进行处理功能
	/*	MarktingWrokFlow<GeAddServiceActivity> c =	this.markingWorkFlowService.getSingleTask(taskId, workflowId);
		c.setWorkFlowID(workflowId);
		c.setEntity(geAddServiceActivity);
		markingWorkFlowService.removeTask(taskId, null, c);*/
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("entity_Status", geAddServiceActivityCondition.getStatus());
		if(status!=null&&"2".equals(status)){
			StringBuffer cityCodes = new StringBuffer("");
			Query query = getSession().createSQLQuery("select deptId from ge_activity_dept where activityid=?");
			query.setString(0, activityId);
			List<String> listDeptId = query.list();
			for(int i=0;i<listDeptId.size();i++){
				if(i==listDeptId.size()-1)
					cityCodes.append(listDeptId.get(i));
				else
					cityCodes.append(listDeptId.get(i)).append(",");
			}
			param.put("workFlow_entity",geAddServiceActivityCondition);
//			workFlowServiceImpl.rollBackTaskID(taskId, param,cityCodes.toString());
		}else{
//			workFlowServiceImpl.rollBackTask(taskId);
		}
		super.update(geAddServiceActivityCondition);
	}
	
	//以下部分所有的功能都是用于工作流功能调用的方法***************************************
	//*************************************************************************************************************
	//*************************************************************************************************************
	//*************************************************************************************************************
	
	
	
	//set get method
	public ThirdParterService getThirdParterService() {
		return thirdParterService;
	}
	public void setThirdParterService(ThirdParterService thirdParterService) {
		this.thirdParterService = thirdParterService;
	}
	public void setActivityRuleService(ActivityRuleService activityRuleService) {
		this.activityRuleService = activityRuleService;
	}
	public void setGeDepartmentService(GeDepartmentService geDepartmentService) {
		this.geDepartmentService = geDepartmentService;
	}
	public void setGeDirectoryService(GeDirectoryService geDirectoryService) {
		this.geDirectoryService = geDirectoryService;
	}
	public static void main(String[] args) {
	    for(int i=0; i < 10000 ; i++){
	    	Random random = new Random();
			int mun = random.nextInt(100)+1; 
	      //System.out.println("Random Number ["+ (i+1) + "] : " + mun);
	    }
	  }

	@Override
	public void setGeDirectoryType(List<GeDirectory> geDirectorys) {
		// TODO Auto-generated method stub
		for(int i=0;i<geDirectorys.size();i++){
			GeDirectory geDirectory = geDirectorys.get(i);
			if(!setCardType(geDirectory)){//如果卡
				if(!setProductMainType(geDirectory)){//如果不是非车
					if(!setCarType(geDirectory)){//如果不是非车
						geDirectory.setExtra1("other");//借用扩展域字段
					}
				}
			}
		}
	}
	
	//设置车类型
	private boolean setCarType(GeDirectory geDirectory){
		if(geDirectory.getCoreProductCode()==null||"".equals(geDirectory.getCoreProductCode())){
			return false;
		}else{
			GeRisk geRisk =  bizCommonService.findGeRiskByCode(geDirectory.getCoreProductCode());
			if(geRisk==null){
				return false;
			}else{
				geDirectory.setExtra1(SelectType.Car.name());
				return true;
			}
		}
	}
	//设置非车类型
	private boolean setProductMainType(GeDirectory geDirectory){
		if(geDirectory.getCoreProductCode()==null||"".equals(geDirectory.getCoreProductCode())){
			return false;
		}else{
			GeProductMain geProductMain =  productManageService.findProductMainByCoreProductCode(geDirectory.getCoreProductCode());
			if(geProductMain==null){
				return false;
			}else{
				geDirectory.setExtra1(SelectType.ProductMain.name());
				return true;
			}
		}
	}
	//设置车类型
	private boolean setCardType(GeDirectory geDirectory){
		if(geDirectory.getCoreProductCode()==null||"".equals(geDirectory.getCoreProductCode())){
			return false;
		}else if(geDirectory.getCoreProductCode().split("_").length<=1){
			return false;
		}else{
			geDirectory.setExtra1(SelectType.Card.name());
			return true;
		}
		
	}
	@Override
	public List<String> getInquiryIdByGroupCity(String groupCity,String statusEquals,String[] statusNoEquals,String inquiryType) {
		if (groupCity == null || groupCity.equals(""))
			return null;

		List<String> result = new ArrayList<String>();
		String sql = "select distinct gw.ACTIVITYID from ge_workflow_activity gw inner join ge_addservice_activity inq on(gw.ACTIVITYID = inq.ACTIVITYID) where 1=1";
	
		if(statusEquals!=null)
			sql += " and inq.status ='"+statusEquals+"'";
		if(statusNoEquals!=null){
			for (String status : statusNoEquals) {
				sql += " and inq.status !='"+status+"'";
			}
		}
			
		if(inquiryType!=null)
			sql += " and inq.inquirytype='"+inquiryType+"'";

		sql += " and (gw.city in ";
		String[] authorityIdAll = groupCity.split(",");
		if (authorityIdAll != null && authorityIdAll.length > 0) {
			int authorityIdSize = authorityIdAll.length;
			int authorityIdNumber = (authorityIdSize - 1) / 1000 + 1;
			String nextParentIdString = "";
			for (int j = 0; j < authorityIdNumber; j++) {
				if (nextParentIdString.length() > 0) {
					nextParentIdString = "";
					sql += "or gw.city in";
				}
				int loopNum = 1000;
				if (j == authorityIdNumber - 1) {
					loopNum = authorityIdSize - (authorityIdNumber - 1) * 1000;
				}
				for (int i = 0; i < loopNum; i++) {
					String childDeptId = (String) authorityIdAll[1000 * j + i];
					if (i == 0) {
						nextParentIdString +=  childDeptId ;
					} else {
						nextParentIdString += "," + childDeptId ;
					}
				}
				sql += "(" + nextParentIdString + ")";
			}
			sql += ")";
		}
   //  System.out.println("--------kai shi sql---------"+sql);
		List list = super.findBySql(sql, null);
		for (int i = 0; i < list.size(); i++) {
			result.add((String) list.get(i));
		}
		return result;
	}
	public boolean activityBool(String dept,String eid){
		
		String sql = "select * from ge_addservice_activity l,ge_activities_rule o ,ge_activities_product u " +
				"where l.deptid='"+dept+"' and l.activityid=o.activityid and u.activityid= l.activityid and o.activitypattern='1' " +
				"and u.eid='"+eid+"' and  to_date(to_char(l.activitystartdate,'yyyy-MM-dd')||'00:00:00','yyyy-MM-dd hh24:mi:ss')<=sysdate " +
				"and to_date(to_char(l.activityenddate,'yyyy-MM-dd')||'00:00:00','yyyy-MM-dd hh24:mi:ss') >=sysdate and l.status='3'";
		List list = super.findBySql(sql, null);
		if(list!=null && list.size()>0){
			return true;
		}
		
		return false;
	}
	/**
	 * 根据机构代码和产品EID查询本活动中是否存在赠送商品，
	 * 返回活动图片URl
	 *  结束时间  to_date(to_char(sysdate, 'yyyy-mm-dd '), 'yyyy-mm-dd ')   必须要to_date，否则赠送活动配图在活动结束时间的前一天结束
	 * @param dept
	 * @param eid
	 * @param activitypattern 活动方式
	 * @return
	 */
	public Map activityBoola(String dept,String eid,String[] activitypattern){
		String sqlPttern="";
		if(activitypattern!=null&&activitypattern.length>0){
			for (int j = 0; j < activitypattern.length; j++) {
				if ( j== 0) {
					sqlPttern +=  "'"+activitypattern[j]+"'";
				} else {
					sqlPttern += ",'" + activitypattern[j] +"'";
				}
			}
		}else{
			sqlPttern="'1','2','3','4','5'";
		}
		java.util.Map map = new HashMap();//要返回的结果集
		String sql = "select l.activityid from ge_addservice_activity l,ge_activities_rule o ,ge_activities_product u " +
				"where l.deptid='"+dept+"' and l.activityid=o.activityid and u.activityid= l.activityid and o.activitypattern in ("+sqlPttern+") " +
				"and u.eid='"+eid+"' and  to_date(to_char(l.activitystartdate,'yyyy-MM-dd')||'00:00:00','yyyy-MM-dd hh24:mi:ss')<=sysdate " +
				"and to_date(to_char(l.activityenddate,'yyyy-MM-dd')||'00:00:00','yyyy-MM-dd hh24:mi:ss') >=to_date(to_char(sysdate, 'yyyy-mm-dd '), 'yyyy-mm-dd ') and l.status='3'";
		List list = super.findBySql(sql, null);
		if(list!=null && list.size()>0){
			map.put("falg", true);
				 String sqlPic="select serialno,pictureurl,jumpurl from ge_activities_picture  gp where gp.activityid='"+list.get(0)+"'";
				 List listMap=super.findBySql(sqlPic, null);
				 java.lang.Object[] objLstMap;
				 for (int i = 0; i < listMap.size(); i++) {
					objLstMap = (java.lang.Object[]) listMap.get(i);
					map.put("pic"+(String)objLstMap[0], (String) objLstMap[1]);
					map.put("jump"+(String)objLstMap[0], (String) objLstMap[2]);
				}
				 
		}else{
			map.put("falg", false);
		}
		
		return map;
	}
}
