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
 * ����ֵ��������ά��
 * 
 */
public class AddServiceActivityServiceSpringImpl extends
		GenericDaoHibernate<GeAddServiceActivity,String> implements AddServiceActivityService {
	
	private static final Log logger = LogFactory.getLog(AddServiceActivityServiceSpringImpl.class);
	//service
	private ActivityRuleService  activityRuleService ; //��������
	private ThirdParterService thirdParterService; // ����������
//	private WorkFlowService workFlowService;   //������
//	private GeWorkFlowService geWorkFlowService; //��������
	private GeDepartmentService geDepartmentService; //�������
	private GeDirectoryService  geDirectoryService ;//��Ʒ
	@Autowired
	private ProductManageService productManageService;
	@Autowired
	private BizCommonService bizCommonService;
//	@Autowired
//	private MarktingWorkFlowServiceImpl  markingWorkFlowService;
//	@Autowired
//	private WorkFlowNewService<EntityWorkFlow> workFlowServiceImpl;
	
	//һ��������Щ��
	//��������  GeAddServiceActivity
	//�����ӱ� geActivitiesRules
	//�����ӱ� geActivitiesProducts
	//�����ӱ��е��ӱ�  geActivitiesPictures
	public void addAddGeAddServiceActivityAndRule(GeAddServiceActivity geAddServiceActivity){
		logger.info("*****************addAddGeAddServiceActivityAndRule method start**********************");
		//��ͼƬ��������   ��������
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
		geAddServiceActivity.setCreateDate(new Date());//��������
//		geAddServiceActivity.setStatus("4");//�״̬0:��ʼ 1������ 2δͨ�� 3ͨ�� 4�ѷ���
		super.save(geAddServiceActivity);
		
		//�����ϴ�ͼƬ
		try {
			uploadPicture(geAddServiceActivity);//�����ϴ�ͼƬ
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info("*****************Picture Error IOException="+sw.toString()+"**********************");
			//��Ҫ��marketģ�����쳣
			throw MarketingException.newInstanceMsg("Picture Error IOException="+sw.toString());
		} catch (Exception e) {				
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info("*****************Picture Error Exception="+sw.toString()+"**********************");
			throw MarketingException.newInstanceMsg("Picture Error Exception="+sw.toString());
			//��Ҫ��marketģ�����쳣
		}//����ҳ���ռ����ϴ�ͼƬ����Ϣ������ͼƬ���Ƿ�����Ϣ���洢
		
		//����UUID���ӱ�ͨ 
		//���ӱ�geActivitiesRules
		List<GeActivitiesRule> geActivitiesRuleList= geAddServiceActivity.getGeActivitiesRules();
		if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
			for (GeActivitiesRule geActivitiesRuleTemp : geActivitiesRuleList) {
				geActivitiesRuleTemp.setGeAddServiceActivity(geAddServiceActivity);
				if(geActivitiesRuleTemp.getActivityPattern()!=null
						&&!geActivitiesRuleTemp.getActivityPattern().equals("")){
					if(geActivitiesRuleTemp.getActivityPattern().equals("1")){//�������пͻ�
						geActivitiesRuleTemp.setNvalue(null);
					}
					if(geActivitiesRuleTemp.getActivityPattern().equals("5")){//�ӹ���Ʒ
						List<GeActivitiesShoppingProduct> geActivitiesShoppingProductList = geActivitiesRuleTemp.getGeActivitiesShoppingProducts();
						if(geActivitiesShoppingProductList!=null&&geActivitiesShoppingProductList.size()>0){
							for (GeActivitiesShoppingProduct geActivitiesShoppingProduct : geActivitiesShoppingProductList) {
								geActivitiesShoppingProduct.setGeActivitiesRule(geActivitiesRuleTemp);
							}
						}
					}
					
				}
				//�ӱ�ӹ���Ʒ
				
			}
		}
		//���ӱ�geActivitiesProducts
		List<GeActivitiesProduct> geActivitiesProductList=  geAddServiceActivity.getGeActivitiesProducts();
		if(geActivitiesProductList!=null&&geActivitiesProductList.size()>0){
			for (GeActivitiesProduct geActivitiesProductTemp : geActivitiesProductList) {
				geActivitiesProductTemp.setGeAddServiceActivity(geAddServiceActivity);
			}
		}
		
		//�洢������
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
				&&geAddServiceActivity.getUploadPicture().size()>0){//֤������ͼƬ��
			
			List<GeActivitiesPicture> geActivitiesPictures = new ArrayList<GeActivitiesPicture>();
			List<String> uploadSerialNoList= geAddServiceActivity.getUploadSerialNoList();
			//��ʼ�ϴ��ļ�
			logger.info("*****************addServiceActivities upload**********************");
			List<File> uploadPicture = geAddServiceActivity.getUploadPicture();
			List<String> uploadPictureFileName = geAddServiceActivity.getUploadPictureFileName();
			for(int i =0;i<uploadPicture.size();i++){
				//�ϴ�ͼƬ�Ĵ���
				String attrPictureName = geAddServiceActivity.getActivityId()+"_"+uploadSerialNoList.get(i)+"_"+uploadPictureFileName.get(i);//�ļ����֣�eid+���+�ļ�����
				logger.info("*****************attrPictureName="+attrPictureName+"**********************");
				String uploadImageDirectoryPath = geAddServiceActivity.getUploadImagePath()+java.io.File.separator+attrPictureName;
				logger.info("*****************attrPictureName upload Path="+uploadImageDirectoryPath+"**********************");
				//uploadImageDirectoryPaths.add(uploadImageDirectoryPath);//�ռ�ÿ��ͼƬҪ�ϴ���λ��
				File wantUploadPictureFile = new File(uploadImageDirectoryPath);//Ҫ�ϴ���ͼƬ������λ��
				FileUtils.write(FileUtils.readBytes(geAddServiceActivity.getUploadPicture().get(i)), wantUploadPictureFile);//��������һ����File���ͣ�һ����ͼƬ�ϴ�λ��
				logger.info("*****************picture upload success**********************");
				
				//Ȼ��ʼ��װGeActivitiesProduct����ӱ��ƷͼƬ��
				String savePath  = geAddServiceActivity.getUploadSavePath()+attrPictureName;
				logger.info("*****************want save picture path savePath="+savePath+"**********************");
				geAddServiceActivity.getGeActivitiesPictures().get(i).setPictureUrl(savePath);//ͼƬ��URL
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
	//�ж��Ƿ����ظ��Ļ�����û��ͬһ��Ʒ����ʼʱ����ڽ�������ô����Ϊ�Ǵ��ڻ
	public List<GeCustomAddServiceActivity> isRepeatAddServiceActivity(GeAddServiceActivity geAddServiceActivity){
		List<GeCustomAddServiceActivity> geAddServiceActivitys = new ArrayList<GeCustomAddServiceActivity>();//Ҫ���صĽ����
		String eid = getEid(geAddServiceActivity);
		//��Ĳ�Ʒ 
		//�����ʼʱ��
		//��Ľ��ʱ��
		//super.getHibernateTemplate().getSessionFactory().
		StringBuilder sb = new StringBuilder();
		sb.append("   select distinct addservice.activityId   as     activityId,        ");//�ID
		sb.append("          addservice.activityName   as activityName,        ");//�����
		sb.append("          addservice.activityStartDate as startDate,        ");//��ʼʱ��
	    sb.append("          addservice.activityEndDate as endDate,            ");//����ʱ��
	    sb.append("          product.eid as eid,                               ");//��Ʒ����
	    sb.append("          geDirectory.productName as productName,           ");//��Ʒ����
	    sb.append("          addservice.status as addStatus                    ");//�״̬
//	    sb.append("          activitiesRule.activityPattern as activityPattern ");//���ʽ
	    sb.append("   from   GeAddServiceActivity addservice,                  ");//��Ʒ��
	    sb.append("          GeActivitiesProduct product,                      ");//���   //geActivitiesRule rule  rule.activitparter in('','')
	    sb.append("          GeActivitiesRule activitiesRule,                  ");//�����  
	    sb.append("          GeDirectory geDirectory                           ");//��Ʒ��
	    sb.append("   where  product.eid in("+eid+")                           ");//��ƷID  //in('','','');
        sb.append("   and    addservice.activityId = product.geAddServiceActivity.activityId            ");//��ѯ����
        sb.append("   and    addservice.activityId = activitiesRule.geAddServiceActivity.activityId     ");//��ѯ����
        sb.append("   and    product.eid = geDirectory.eid           ");//��ѯ����
        sb.append("   and    addservice.status not in('2') ");//�״̬������δͨ����
        sb.append("   and    addservice.deptID = '"+geAddServiceActivity.getDeptID()+"' ");//�������
        sb.append("   and    addservice.validInd ='1' ");//�Ƿ���Ч��־λ ������Ч��
        if(geAddServiceActivity.getFlag()!=null&&!"".equals(geAddServiceActivity.getFlag())&&"update".equals(geAddServiceActivity.getFlag())){
        sb.append("   and    addservice.activityId!='"+geAddServiceActivity.getActivityId()+"' ");//����ȥ���Լ���
        }
        if(geAddServiceActivity.getFlag()!=null&&!"".equals(geAddServiceActivity.getFlag())&&"finishPage".equals(geAddServiceActivity.getFlag())){
        sb.append("   and    addservice.activityId!='"+geAddServiceActivity.getActivityId()+"' ");//����ȥ���Լ���
        sb.append("   and    addservice.status ='3' ");//�����ʱ���Ƿ����ͬ���ͻ
        }
        
		Query query = (Query)getSession().createQuery(sb.toString());
		//query.setString(0, geAddServiceActivity.getDeptID());//�������.��ֵ���ɹ����ѻ�������ֱ��ƴ����
		List list = query.list();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				if(obj!=null&&obj.length>0){
					if(isMixed((Date)obj[2],(Date)obj[3],geAddServiceActivity)){//����1��ʼʱ��   //����2����ʱ��  �Ƿ���ڽ���
						//������ڽ��� ��ô���ظ��Ļ����
						GeCustomAddServiceActivity geAddServiceActivityTemp = new GeCustomAddServiceActivity();
						geAddServiceActivityTemp.setActivityId((String)obj[0]);//�ID
						geAddServiceActivityTemp.setActivityName((String)obj[1]);//�����
						geAddServiceActivityTemp.setEid((String)obj[4]);//��Ʒ����
						geAddServiceActivityTemp.setProductName((String)obj[5]);//��Ʒ���� 
//						geAddServiceActivityTemp.setActivitypattern((String)obj[6]);//���ʽ
						geAddServiceActivityTemp.setStartDate(getDateToString((Date)obj[2]));//startDate
						geAddServiceActivityTemp.setEndDate(getDateToString((Date)obj[3]));//endDate
						geAddServiceActivityTemp.setStatus((String)obj[6]);
						geAddServiceActivitys.add(geAddServiceActivityTemp);//��ӽ�ȥ
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
	//�Ƿ���ڽ���
	private boolean isMixed(Date startDate,Date endDate,GeAddServiceActivity geAddServiceActivity){
		boolean flag = false;
		//���в�ѯ�����ĸ����ֶ�Ӧ�Ļ����ʼ����
		long dbStartTime  = startDate.getTime();
		long dbEndTime = endDate.getTime();
		
		long pageStartTime = geAddServiceActivity.getActivityStartDate().getTime();
		long pageEndTime = geAddServiceActivity.getActivityEndDate().getTime();
		
		//
		//�ж��Ƿ���ڽ��� ��һ�����
		if(dbStartTime<=pageStartTime&&dbEndTime>=pageEndTime){
			flag = true;
		}
		//�ж��Ƿ���ڽ��� �ڶ������
		if(dbStartTime<=pageStartTime&&dbEndTime<=pageEndTime&&dbEndTime>=pageStartTime){
			flag = true;
		}
		//�ж��Ƿ���ڽ��� ���������
		if(pageStartTime<=dbStartTime&&pageEndTime<=dbEndTime&&pageEndTime>=dbStartTime){
			flag = true;
		}
		//�ж��Ƿ���ڽ��� ���������
		if(pageStartTime<=dbStartTime&&pageEndTime>=dbEndTime){
			flag = true;
		}
		return flag;
	}
	
	
	
	
	
	/**
	 * ��ѯ�
	 * ��������ѯ
	 * @return
	 * @throws Exception 
	 */
	public GeAddServiceActivity findAddGeAddServiceActivityByActivityId(String activityId) throws Exception{
		
		GeAddServiceActivity geAddServiceActivity = super.get(activityId);
		//ͨ�������������������
		geAddServiceActivity.setDeptName(getDeptName(geAddServiceActivity.getDeptID()));
		//���ù���
		List<GeActivitiesRule> geActivitiesRuleList=  geAddServiceActivity.getGeActivitiesRules();
		if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
			for (GeActivitiesRule geActivitiesRuleTemp : geActivitiesRuleList) {
				if(geActivitiesRuleTemp.getPremiumType()!=null){
				if(geActivitiesRuleTemp.getPremiumType().equals("6")){//����֮��
					String peremiumValue = geActivitiesRuleTemp.getPeremiumValue();
					String[] temps = peremiumValue.split("@");
					geActivitiesRuleTemp.setPremiumRange1(temps[0]);
					geActivitiesRuleTemp.setPremiumRange2(temps[1]);
				}
				}
				if(geActivitiesRuleTemp.getActivityPattern()!=null){
				if(geActivitiesRuleTemp.getActivityPattern().equals("5")){//���ù���   �ӹ���Ʒ
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
		//ͨ����Ʒ�����ѯ����Ʒ����
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
		//ͨ����ƷID��ѯ����Ʒ����
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
	 * ��ѯ�
	 * ��������ѯ
	 * @return
	 */
//	public MarktingWrokFlow findAddGeAddServiceByTaskId(String taskID,String workFlowID){
////		GeAddServiceActivity geAddServiceActivity = super.get(activityId);
//		MarktingWrokFlow marktingWorkFlow = (MarktingWrokFlow)workFlowService.getSingleTask(taskID, workFlowID);
//		marktingWorkFlow.setWorkFlowID(workFlowID);
//		GeAddServiceActivity geAddServiceActivity = (GeAddServiceActivity)marktingWorkFlow.getEntity();
//		if(geAddServiceActivity!=null&&geAddServiceActivity.getGeActivitiesRules()!=null){
//			//���Ҳ�ѯ��ÿ����Ӧ�̶�Ӧ����Ʒ��Ϣ
//			List<GeActivitiesRule> geActivitiesRuleList = geAddServiceActivity.getGeActivitiesRules();
//			//���if�����ǵ���Ϊ�˻���µ�����ҵ����д�ģ���������ǰ���������ѯһ��GeAddServiceActivity��ǲ���Ҫ���´����
//			if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
//				for(GeActivitiesRule geActivitiesRule:geActivitiesRuleList){
//					if(geActivitiesRule.getThirdParterID()!=null&&!geActivitiesRule.getThirdParterID().equals("")){
//						//ͨ����Ӧ��thirdParterID����ѯ��Ӧ����Ʒ
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
			if(uploadSerialNos[k].equals(i+"")){//���
				count = k+"";
			}
		}
		return count;
	}
	private GeActivitiesPicture getGeActivitiesPictureForUpdate(List<GeActivitiesPicture> geActivitiesPictureTempList,int i){
		if(geActivitiesPictureTempList!=null&&geActivitiesPictureTempList.size()>0){
			for (GeActivitiesPicture geActivitiesPictureTemp: geActivitiesPictureTempList) {
				if(geActivitiesPictureTemp.getSerialNo().equals(i+"")){//��ǰ����
					geActivitiesPictureTemp.setAutoPictureId(null);//�Զ����ɵ�ͼƬID��ʱ����Ϊ��
					return geActivitiesPictureTemp;
				}
			}// end for 
		}// end if
		return null;
	}
	
	//Ϊ�˸��·�������֯���ϴ�ͼƬ
	private List<GeActivitiesPicture> getGeActivitiesPictureUpdate(String uploadPictureSerialNos,GeAddServiceActivity geAddServiceActivity,List<GeActivitiesPicture> geActivitiesPictureList) throws IOException, Exception{
		List<GeActivitiesPicture> updatePicture = new ArrayList<GeActivitiesPicture>();
		for(int i=1;i<=5;i++){
			//ͼƬ1
			if(uploadPictureSerialNos!=null
					&&!uploadPictureSerialNos.equals("")
					&&uploadPictureSerialNos.contains(i+"")){//һ����Ҫ�ϴ���ͼƬ
				//��ʼ�ϴ��ļ�
				logger.info("*****************addServiceActivities upload i="+i+ "**********************");
				String count = getUploadPictureSize(uploadPictureSerialNos,i);//���õ�List�����
				File file = geAddServiceActivity.getUploadPicture().get(Integer.parseInt(count));//����ܹ�ȡ��Ҫ�ϴ���ͼƬ
				String attrPictureName = geAddServiceActivity.getActivityId()+"_"+i+"_"+geAddServiceActivity.getUploadPictureFileName().get(Integer.parseInt(count));//�ļ����֣�eid+���+�ļ�����
				logger.info("*****************attrPictureName="+attrPictureName+"**********************");
				String uploadImageDirectoryPath = geAddServiceActivity.getUploadImagePath()+java.io.File.separator+attrPictureName;//�ļ��ϴ�����·��
				logger.info("*****************attrPictureName upload Path="+uploadImageDirectoryPath+"**********************");
				File wantUploadPictureFile = new File(uploadImageDirectoryPath);//Ҫ�ϴ���ͼƬ������λ��
				FileUtils.write(FileUtils.readBytes(file), wantUploadPictureFile);//��������һ����File���ͣ�һ����ͼƬ�ϴ�λ��
				logger.info("*****************picture upload success**********************");
				//��װList
				GeActivitiesPicture geActivitiesPictureUpdate = new GeActivitiesPicture();
				geActivitiesPictureUpdate.setGeAddServiceActivity(geAddServiceActivity);//�����
				geActivitiesPictureUpdate.setSerialNo(String.valueOf(i));//ͼƬ���
				String savePath  = geAddServiceActivity.getUploadSavePath()+attrPictureName;
				logger.info("*****************want save picture path savePath="+savePath+"**********************");
				geActivitiesPictureUpdate.setPictureUrl(savePath);
				updatePicture.add(geActivitiesPictureUpdate);
				
			}else{//û��Ҫ�ϴ���ͼƬ ����ô�������ݿ���ԭ�� �Ƿ����
				GeActivitiesPicture geActivitiesPictureUpdate = getGeActivitiesPictureForUpdate(geActivitiesPictureList,i);
				if(geActivitiesPictureUpdate!=null){//��ǰ���ݿ������ͼƬ
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
	//ɾ���ӱ�
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
	//���»
	/**
	 *  �ڶ�������ΪҪ�޸ĵ�ͼƬ����
	 *  
	 *  //���壬���е�ͼƬ��Ŷ�����1Ϊ��ʼ��
	 *  
	 * ���ڶ��ĸ��µ� ����˼·���Ȱ��ӱ�ɾ����Ȼ�� �ٽ��в������
	 * ���������ֱ�Ӹ��¾Ϳ�����
	 * @throws Exception 
	 * @throws IOException 
	 */
	public void updateAddGeAddServiceActivityAndGeActivitiesRule(GeAddServiceActivity geAddServiceActivity,String uploadPictureSerialNos,String rulds){
		GeAddServiceActivity geActPic=new GeAddServiceActivity();
		geActPic=geAddServiceActivity;
		
//		geActPic.getGeActivitiesPictures().get(0).
		//ͨ���ID ����ѯͼƬ
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geAddServiceActivity", geAddServiceActivity);
		List<GeActivitiesPicture> geActivitiesPictureList = super.find(GeActivitiesPicture.class, queryRule);
		try{
			// һ����5��ͼƬ
			//Ҫ����ͼƬ��List ��������װ
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
			//��Ҫ��marketģ�����쳣
			throw MarketingException.newInstanceMsg("(update)Picture Error IOException="+sw.toString());
		} catch (Exception e) {				
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info("*****************(update)Picture Error Exception="+sw.toString()+"**********************");
			throw MarketingException.newInstanceMsg("(update)Picture Error Exception="+sw.toString());
			//��Ҫ��marketģ�����쳣
		}
		//ɾ���ӱ�GeActivityPicture 
		deleteGeActivitPicture(geActivitiesPictureList);
		//ɾ���ӱ� GeActivitiesRule GeActivitiesShoppingProduct
		deleteGeActivitiesRule(rulds);
		//ɾ���ӱ�   GeActivitiesProduct
		deleteGeActivitiesProduct(geAddServiceActivity.getActivityId());
		//ɾ����ֵ�������� ������
		deleteAddGeactivitydept(geAddServiceActivity.getActivityId());
		//Ȼ���ڽ��и���
		//�洢������
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
		
		//���� UUID
		setGeActivitiesProductUUID(geAddServiceActivity);
		super.save(geAddServiceActivity);
		//����GeActivitiesRule GeActivitiesShoppingProduct���UUID
		setGeActivitiesRuleAndGeActivitiesShoppingProductUUID(geAddServiceActivity);

	}
	
	private void setGeActivitiesRuleAndGeActivitiesShoppingProductUUID(GeAddServiceActivity geAddServiceActivity){
		List<GeActivitiesRule> geActivitiesRuleList = geAddServiceActivity.getGeActivitiesRules();
		if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
			for (GeActivitiesRule geActivitiesRule : geActivitiesRuleList) {
				geActivitiesRule.setGeAddServiceActivity(geAddServiceActivity);
				String  activityPattern = geActivitiesRule.getActivityPattern();
				if((activityPattern!=null&&!activityPattern.equals(""))
						&&(activityPattern.equals("1")||activityPattern.equals("5"))){//���������û�  �ӹ�
					geActivitiesRule.setNvalue(null);
					
				}
				if(activityPattern!=null&&!activityPattern.equals("")&&activityPattern.equals("5")){//�ӹ���Ʒ 
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
	//�Ƚ�������е�����ɾ��,Ŀ�����ڸ��µ�ʱ������²���
	public void deleteGeActivitiesRulesByActivityId(GeAddServiceActivity geAddServiceActivity){
		//���ջ��ID��������е����ݸ�ɾ����
		String deleteSql = "delete GeActivitiesRule as geActivitiesRule where geActivitiesRule.geAddServiceActivity.activityId=:activityId";
		Query   query2 = getSession().createQuery(deleteSql);
		query2.setParameter("activityId", geAddServiceActivity.getActivityId()); 
		query2.executeUpdate();
		//
	}
	//����ͼƬ��
	public void updateGeActivitiesPictures(GeAddServiceActivity geAddServiceActivity){
		if(!geAddServiceActivity.isSameProductFlag()){//����ͬһ���Ʒ
			//��ԭ�����Ǹ���Ʒ��ˮ�Ŷ�Ӧ��ͼƬ��ɾ����
			String deleteSql = "delete GeActivitiesPicture as geActivitiesPicture where geActivitiesPicture.geActivitiesProduct.activitiesProductId=:activitiesProductId";
			Query   query2 = getSession().createQuery(deleteSql);
			//��Ϊ�����ǵ��  ����Ʒ ����ֱ����geAddServiceActivity.getGeActivitiesProducts().get(0)
			query2.setParameter("activitiesProductId", geAddServiceActivity.getGeActivitiesProducts().get(0).getAutoActivitiesProductId()); 
			query2.executeUpdate();
		}
	}
	
	//����״̬ 
	public GeAddServiceActivity updateAddGeAddServiceActivity(GeAddServiceActivity geAddServiceActivity) throws IOException, Exception{
		GeAddServiceActivity update =   super.get(geAddServiceActivity.getActivityId());
		update.setStatus(geAddServiceActivity.getStatus());//�޸�״̬
		
		//
		super.update(update);
		return update;
	}
	
	//����״̬   ���� ���Ľ���   �����޸�   �� Finial(������)  ʹ�õ�
	public GeAddServiceActivity updateAddGeAddServiceActivity(GeAddServiceActivity geAddServiceActivity,boolean finialFlag) throws IOException, Exception{
		if(finialFlag){
			GeAddServiceActivity update =   super.get(geAddServiceActivity.getActivityId());
			update.setStatus(geAddServiceActivity.getStatus());//�޸�״̬
			List<GeAddServiceProcess>  geAddServiceProcessList = geAddServiceActivity.getGeAddServiceProcesses();
			if(geAddServiceProcessList!=null){
				for (GeAddServiceProcess geAddServiceProcess : geAddServiceProcessList) {
					geAddServiceProcess.setHandleStatus("3");  //�ѷ���
				}
			}
			super.update(update);
			return update;
		}else{
			return null;
		}
	}

	//�ڸ���֮ǰ�ж��Ƿ��������ĻҲ�и�����
	public GeAddServiceActivity isRepeatForUpdate(GeAddServiceActivity geAddServiceActivity){
		StringBuilder sb = new StringBuilder();
		sb.append("   select addservice.activityId   as     activityId,  ");//�ID
		sb.append("          addservice.activityName   as activityName,  ");//�����
		sb.append("          addservice.activityStartDate as startDate,  ");//��ʼʱ��
	    sb.append("          addservice.activityEndDate as endDate,      ");//����ʱ��
	    sb.append("          product.eid as eid                          ");//��Ʒ����
	    sb.append("   from   GeAddServiceActivity addservice,            ");//��Ʒ��
	    sb.append("          GeActivitiesProduct product                 ");//���
	    sb.append("   where  product.eid = ?                             ");//��ƷID
        sb.append("   and    addservice.activityId = product.geAddServiceActivity.activityId  ");//��ѯ����	   
        sb.append("   and    addservice.status not in('2') ");//�״̬������δͨ����
        sb.append("   and    addservice.deptID =? ");//�������
		Query query = (Query)getSession().createQuery(sb.toString());
		query.setString(0, geAddServiceActivity.getGeActivitiesProducts().get(0).getEid());//��Ʒ��
		query.setString(1, geAddServiceActivity.getDeptID());//�������
		List list = query.list();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				if(obj!=null&&obj.length>0){
					if(!((String)obj[0]).equals(geAddServiceActivity.getActivityId())){//��ѯ�Ļ�뱾�εĻ��һ��
						if(isMixed((Date)obj[2],(Date)obj[3],geAddServiceActivity)){//����1��ʼʱ��   //����2����ʱ��
							//������ڽ��� ��ô���ظ��Ļ����
							GeAddServiceActivity geAddServiceActivityTemp = new GeAddServiceActivity();
							geAddServiceActivityTemp.setActivityId((String)obj[0]);//�ID
							geAddServiceActivityTemp.setActivityName((String)obj[1]);//�����
							return geAddServiceActivityTemp;//�����ظ���
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
	 * ���ͨ��
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
//		//������
//		geAddServiceActivity.setStatus("2");
//		super.update(geAddServiceActivity);
	}
	
	/**
	 * ��� 
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
	 * ����֧���ص�������Ʒ״̬��Ϊ��Ч
	 */
	public List updateGeThirdParterServiceValidInd(){
		return null;
	}
	
	public List<GeAddServiceActivity> queryAddServiceActivityBySQL(GeActivitiesRule geActivitiesRule) throws ParseException{
		StringBuilder sb = new StringBuilder();
		sb.append("   select distinct addservice.activityId,             ");//�ID
		sb.append("          addservice.activityStartDate as startDate,  ");//��ʼʱ��
	    sb.append("          addservice.activityEndDate as endDate,      ");//����ʱ��
	    sb.append("          addservice.deptID                    ,      ");//����
	    
	    sb.append("          geActivitiesRule.premiumType         ,      ");//��������
	    sb.append("          geActivitiesRule.peremiumValue       ,      ");//���ѵ�ֵ
	    sb.append("          geActivitiesRule.nvalue                     ");//n��ֵ
	    
	    sb.append("   from   GeAddServiceActivity addservice,            ");//���
	    sb.append("          GeActivitiesProduct product,                 ");//��Ʒ��
	    sb.append("          GeActivitiesRule geActivitiesRule           ");//�����
	    sb.append("   where  addservice.activityStartDate <= to_date('"+getDateToDateString(new Date())+"','yyyy-MM-dd')");//�� ʼ����С�ڵ��ڵ�ǰ����
	    sb.append("   and    addservice.activityEndDate >= to_date('"+getDateToDateString(new Date())+"','yyyy-mm-dd')");//�������ڴ��ڵ��ڵ�ǰ����
        sb.append("   and    addservice.deptID = ?");//��ѯ��������
        sb.append("   and    addservice.status = '3'");//��ѯ����״̬Ϊ3Ϊ�Ѵ���
        sb.append("   and    addservice.validInd = '1'");//��ѯ����״̬Ϊ1Ϊ��Ч
        sb.append("   and    geActivitiesRule.activityPattern in ('1','2','3','4','5')");//��ѯ�������ʽΪ1��2��3�ļ�Ϊ�׸����͵Ļ�齱���ۼӹ�
       
        sb.append("   and    addservice.activityId = geActivitiesRule.geAddServiceActivity.activityId");
        sb.append("   and    addservice.activityId = product.geAddServiceActivity.activityId");
        sb.append("   and    product.eid = ?");//��ѯ�������ִ���
        
        Query query = getSession().createQuery(sb.toString());
        query.setString(0, geActivitiesRule.getDeptID());//��������
        query.setString(1, geActivitiesRule.getEid());//���ִ���   Ӧ�ñ�Ϊeid
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
        }else{//û�л��
        	return null;
        }
        
		
	}
	//ͨ��������ѯ��ֵ���������������û�У���ѯ���ĸ������������ڵݹ��ѯ��� 
	public List<GeAddServiceActivity> queryAddServiceActivityRecursion(GeActivitiesRule geActivitiesRule) throws ParseException{
		//geActivitiesRule.setEid(getRiskCodeToEid(geActivitiesRule.getRiskCode()));
		List<GeAddServiceActivity>  geAddServiceActivity = queryAddServiceActivityBySQL(geActivitiesRule);
		if(geAddServiceActivity==null){//�û�����û���ҵ���Ӧ�Ļ����ôȡ������һ����
			GeDepartment geDepartment= geDepartmentService.findGeDepartmentByPK(geActivitiesRule.getDeptID());//��ǰ�ù���
			if(!"0".equals(geDepartment.getParentid())){//
				GeDepartment geDepartmentTemp= geDepartmentService.findGeDepartmentByPK(geDepartment.getParentid());//��ǰ�ù���
				geActivitiesRule.setDeptID(geDepartmentTemp.getDeptid());
				geAddServiceActivity = (List<GeAddServiceActivity>) queryAddServiceActivityRecursion(geActivitiesRule);//������������ݹ�
			}
		}
		return  geAddServiceActivity;
	}
	//ͨ�����ִ���   ��ѯ ��Ʒ��eid
	public String getRiskCodeToEid(String riskCode){
		//���յ����ִ���  ȥeid�в�ѯ
		StringBuffer sb = new StringBuffer();
		sb.append("select    g.eid                         ");//��Ʒ��eid  
		sb.append("from      ge_directory g                ");//��ƷĿ¼��
		sb.append("where     g.coreproductcode = ?         ");//���ִ���
		sb.append("and       g.salechannel in ('01', '04') ");//�������� Ϊ���� �������
		sb.append("and       g.isproductshelf = '01'       ");//�Ƿ��ϼ�   01Ϊ�ϼ�
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
	//ȥ��ѯ�Ƿ��л�������ڣ�����װ�ɹ�������List<GeActivitiesRule>
	public List<GeActivitiesRule> queryAddServiceActivityForProduct(List<GeActivitiesRule> geActivitiesRuleList) throws ParseException{
		List<GeActivitiesRule> geActivitiesRuleTempList = new ArrayList<GeActivitiesRule>();//Ҫ���ص�List
		//
		if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
			GeActivitiesRule geActivitiesRuleTemp = new GeActivitiesRule();
			
			for (GeActivitiesRule geActivitiesRuleNewTemp : geActivitiesRuleList) {
				//���ݵ��������ִ��� ȥ��ѯ�� ���������Ļ
				List<GeAddServiceActivity>  geAddServiceActivityTemp = queryAddServiceActivityRecursion(geActivitiesRuleNewTemp);//һ����Ʒ��Ӧ��Ӫ���
				if(geAddServiceActivityTemp!=null){//�õ���Ӫ���
					//�ڿ�����������Ƿ���һ���̶��ı��ѷ�Χ�� ������  ��װ����
					if(isPeremiumRange(geActivitiesRuleNewTemp,geAddServiceActivityTemp)){//�ڱ��ѷ�Χ����
						//���صĹ���
						//ͨ����ֵ��
						List<GeActivitiesRule> geActivitiesRules= queryGeActivitiesRuleList(geAddServiceActivityTemp.get(0).getActivityId());
						if(geActivitiesRules!=null&&geActivitiesRules.size()>0){
							for (GeActivitiesRule geActivitiesRule : geActivitiesRules) {
								geActivitiesRule.setDeptID(geAddServiceActivityTemp.get(0).getDeptID());//Temp�Ǵӿ����ѯ������
								geActivitiesRule.setEid(geActivitiesRuleNewTemp.getEid());//NewTemp�Ǵ�ҳ�洫������ֵ
								geActivitiesRule.setProposalNo(geActivitiesRuleNewTemp.getProposalNo());//NewTemp�Ǵ�ҳ�洫������ֵ
								geActivitiesRule.setProposalArea(geActivitiesRuleNewTemp.getProposalArea());//NewTemp�Ǵ�ҳ�洫������ֵ
							}
							return geActivitiesRules;
						}
						
						
//						GeActivitiesRule geActivitiesRuleReturn = new GeActivitiesRule();
//						BeanUtils.copyProperties(geActivitiesRuleNewTemp, geActivitiesRuleReturn);
//						geActivitiesRuleReturn.setDeptID(geAddServiceActivityTemp.getDeptID());//���ڹ�������Ļ�������
//						geActivitiesRuleTempList.add(geActivitiesRuleReturn);//����LIST��ʼ����װֵ
					}
				}
			}
		}
		//
		//return geActivitiesRuleTempList;
		return null;
	}
	private List<GeActivitiesRule> queryGeActivitiesRuleList(String activityId){
		//ͨ��
		//List<GeActivitiesRule> geActivitiesRules  =  new ArrayList<GeActivitiesRule>();
		GeAddServiceActivity geAddServiceActivity = new GeAddServiceActivity();
		geAddServiceActivity.setActivityId(activityId);
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geAddServiceActivity", geAddServiceActivity);
		
		List<GeActivitiesRule> geActivitiesRuleList = super.find(GeActivitiesRule.class, queryRule);
		return geActivitiesRuleList;
	}
	
	/**
	 * @param geAddServiceActivityFromPage ��һ�������� ������ҳ�����Ĵ�����������
	 * @param geAddServiceActivity  �ڶ��������Ǵ����ݿ����ѯ������
	 * @return
	 * 
	 * Ȼ��Ƚ� ������ҳ����������� �Ƿ��� ���ݿ���������õķ�Χ
	 */
	/*
	<option value="1">����</option>
	<option value="2">С��</option>
	<option value="3">С�ڵ���</option>
	<option value="4">����</option>
	<option value="5">���ڵ���</option>
	<option value="6">����֮��</option>
	*/
	private boolean isPeremiumRange(GeActivitiesRule geAddServiceActivityFromPage,List<GeAddServiceActivity> geAddServiceActivity){
	
		if(geAddServiceActivity.get(0).getPremiumType()!=null&&geAddServiceActivity.get(0).getPremiumType().equals("1")){//����
			return true;
		}
		//Ӫ�����̨���������˱��ѣ�ǰ̨δ��������ֵ��ֱ��return false;
		if(geAddServiceActivity.get(0).getPremiumType().equals("2")||geAddServiceActivity.get(0).getPremiumType().equals("3")||geAddServiceActivity.get(0).getPremiumType().equals("4")||geAddServiceActivity.get(0).getPremiumType().equals("5")||geAddServiceActivity.get(0).getPremiumType().equals("6")){
			if(geAddServiceActivityFromPage.getPeremiumValue()==null||geAddServiceActivityFromPage.getPeremiumValue().equals("")){
				return false;
			}
		}
		if(geAddServiceActivity.get(0).getPremiumType()!=null&&geAddServiceActivity.get(0).getPremiumType().equals("2")){//С��
			String premiumFromPage = geAddServiceActivityFromPage.getPeremiumValue();
			String premiumFromDB  = geAddServiceActivity.get(0).getPeremiumValue();
			if(Double.parseDouble(premiumFromPage)<Double.parseDouble(premiumFromDB)){//С��
				return true;
			}
		}
		if(geAddServiceActivity.get(0).getPremiumType()!=null&&geAddServiceActivity.get(0).getPremiumType().equals("3")){//С�ڵ���
			String premiumFromPage = geAddServiceActivityFromPage.getPeremiumValue();
			String premiumFromDB  = geAddServiceActivity.get(0).getPeremiumValue();
			if(Double.parseDouble(premiumFromPage)<=Double.parseDouble(premiumFromDB)){//С�ڵ���
				return true;
			}
		}
		if(geAddServiceActivity.get(0).getPremiumType()!=null&&geAddServiceActivity.get(0).getPremiumType().equals("4")){//����
			String premiumFromPage = geAddServiceActivityFromPage.getPeremiumValue();
			String premiumFromDB  = geAddServiceActivity.get(0).getPeremiumValue();
			if(Double.parseDouble(premiumFromPage)>Double.parseDouble(premiumFromDB)){//����
				return true;
			}
		}
		if(geAddServiceActivity.get(0).getPremiumType()!=null&&geAddServiceActivity.get(0).getPremiumType().equals("5")){//���ڵ���
			String premiumFromPage = geAddServiceActivityFromPage.getPeremiumValue();
			String premiumFromDB  = geAddServiceActivity.get(0).getPeremiumValue();
			if(Double.parseDouble(premiumFromPage)>=Double.parseDouble(premiumFromDB)){//���ڵ���
				return true;
			}
		}
		if(geAddServiceActivity.get(0).getPremiumType()!=null&&geAddServiceActivity.get(0).getPremiumType().equals("6")){//����֮��
			
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
	 * ��Ҫ����ֵΪ
	 * geActivitiesRuleList  eid Ͷ������  geAddServiceActivityTemp.getDeptID()
	 *  
	 * 
	 * �������ͣ��齱��1  �߹��� ����  ��Ʒ��List
	 *                ����   3 
	 *                
	 * �ӹ�   2 �ӹ���ƷList 
	 * 
	 * ͼƬ   4 ͼƬList  ���׳����п��ܳ����������֣�����������ʾ����ͼƬ
	 * 
	 * ��ѯ��ֵ��������������������ѯ
	 * ����һ����Ʒ
	 * ֱ�ӷ���Ϊ�գ���ôû�����͵���Ʒ
	 * �����Ϊ�գ��ÿ���������ж���ֱ����ʾ����Ʒ
	 * ����ֱ��ȥ�齱����Ʒ
	 * 
	 * Map  key="GeThirdParterServices"   ���ذ��͵���Ʒ�ͳ齱����Ʒ   ��Ҫ�߹�������
	 * Map  key="GeDirectorys"   ���ط���Ҫ�ӹ��Ĳ�ƷĿ¼
	 * 
	 * 
	 * ����ֱ�ӵ��õ�
	 * GeActivitiesRule
	 * @throws ParseException 
	 */
	///public List<GeThirdParterService> findAddGeAddServiceActivityCheckRule(List<GeActivitiesRule> geActivitiesRuleList,String userId) throws ParseException{
	public java.util.Map findAddGeAddServiceActivityCheckRule(List<GeActivitiesRule> geActivitiesRuleList,GeAddserviceConditionDto geAddserviceConditionDto) throws ParseException{
//		//��������ε���װ
//		List<GeActivitiesRule> geActivitiesRules = new ArrayList<GeActivitiesRule>();
//		GeActivitiesRule geActivitiesRule = new GeActivitiesRule();
//		geActivitiesRule.setDeptID(deptID);
//		geActivitiesRule.setEid("");
//		geActivitiesRules.add(geActivitiesRule);
//		//
//		GeAddserviceConditionDto geAddserviceConditionDto = new  GeAddserviceConditionDto();
//		geAddserviceConditionDto.setUserId("");
//		geAddserviceConditionDto.setPictureFlag(false);//��ҪͼƬ
//		geAddserviceConditionDto.setSystemFlowId("01");//����ҳ��
		//Ҫ���صĽ����    
		java.util.Map map = new HashMap();//Ҫ���صĽ����
		List<GeDirectory> geDirectorys  = new ArrayList<GeDirectory>();//Ҫ�ӹ��Ľ����
		List<GeThirdParterService> returnGeThirdParterServiceList = new ArrayList<GeThirdParterService>();//���ڷ��صİ׸����ͳ齱 �Ľ���� 
		List<GeActivitiesPicture> geActivitiesPictures = new ArrayList<GeActivitiesPicture>();//���ڷ���Ӫ����Ĺ��ͼƬ
		String isDiscountFlag = "false";//����:���ڸ��� �Ƿ���ù�������
		String discountID = "";//���յ��ۿ�ID    �ϵ�
		String newDiscountID = "";//���յ��ۿ�ID    �ϵ�
		
		//����Ĳ�ѯ����
		String systemFlowId = geAddserviceConditionDto.getSystemFlowId();//����ID ϵͳ��ˮID 01 ���� 02 ���� 
		String userId = geAddserviceConditionDto.getUserId();//userId  �͸�˭ �û�ID
		boolean isPictureFlag = geAddserviceConditionDto.isPictureFlag(); //�Ƿ�ҪͼƬ
		List<String>  wantedActivityPatterns = geAddserviceConditionDto.getWantedActivityPatterns(); //ѡ����ʽ
		
		GeActivitiesPicture	pictureJumpUrl=null;
		java.util.Map discountAndJumpUrl = new HashMap();//������ת��url��ַ��ͼƬ
		
		//setProposalAreaProperty(geActivitiesRuleList);   //����һ���򵥵ĸ�ֵ���� ͨ��deptId ����proposalArea
		List<GeActivitiesRule> geActivitiesRuleListForRule = queryAddServiceActivityForProduct(geActivitiesRuleList);//�ܵõ����е�Ҫ���õĹ���  ��ȡ�����
		
		if(geActivitiesRuleListForRule!=null&&geActivitiesRuleListForRule.size()>0){
			//�Ƿ�Ҫ�������ڷ���Ӫ����Ĺ��ͼƬ
			geActivitiesPictures = isPictureFlag ?   geActivitiesRuleListForRule.get(0).getGeAddServiceActivity().getGeActivitiesPictures()  :   null;
			
			//���ʽ 
			List<ActivityInputBOM> activityInputBOMList = new ArrayList<ActivityInputBOM>();//��������Ҫ�õ�DTO
			for(GeActivitiesRule geActivitiesRuleTemp:geActivitiesRuleListForRule){
				//activityPattern = 1,2,3 �߹�������   5�Ǽӹ���Ʒ
				String activityPattern = geActivitiesRuleTemp.getActivityPattern();
				if(activityPattern!=null&&!activityPattern.equals("")){

					//����  ǰN��  �齱 
					//System.out.println("ǰ̨�������ѣ�"+geActivitiesRuleList.get(0).getPeremiumValue());
					if(activityPattern.equals("1")||activityPattern.equals("2")||activityPattern.equals("3")){//1��2��3���׸���Ʒ��
						
						if(systemFlowId.equals("01")){//���ڱ�������ҳ������
//							if(geActivitiesRuleTemp.getPremiumType().equals("6")){
//								setActivityInputBOMProperty(geActivitiesRuleTemp,activityInputBOMList,geActivitiesRuleList.get(0).getPeremiumValue());
//							}else{
							setActivityInputBOMProperty(geActivitiesRuleTemp,activityInputBOMList,geActivitiesRuleList.get(0).getPeremiumValue());//��һ���ǻ����  �ڶ����ǹ�������
//							}
						}
						
						if(systemFlowId.equals("02")){//����ҳ������
							if(wanted(activityPattern,wantedActivityPatterns)){//����Ҫ  1:����  2:ǰN�� 3�齱
								setActivityInputBOMProperty(geActivitiesRuleTemp,activityInputBOMList,null);
							}
						}
					}//���� ǰN�� N%����� 
					
					
					
					//����
					if(activityPattern.equals("4")){//����
						if(systemFlowId.equals("02")){//���ղ�Ʒҳ������
							
							if(wanted(activityPattern,wantedActivityPatterns)){//����  �벻��Ҫ����
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
								isDiscountFlag = "true";//����С��
							}
						}
						//�ƻ�û����
						
					}//����  ���� 
					
					
					
					//�ӹ�
					if(activityPattern.equals("5")){//�ӹ���Ʒ
						
						
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
										//����List���Ƿ��Ѵ����ظ��ļӹ���Ʒ
										if(geDirectorys!=null&&geDirectorys.size()>0){//�������˼ӹ���Ʒ���жϸò�Ʒ�Ƿ�����List�д�����
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
											geDirectorys.add(geDirectory);//��һ�μӹ���Ʒ 
										}
									}
								}// end if(geDirectoryTemps!=null&&geDirectoryTemps.size()>0){..
								
							}
						}
						
						
						
					}// end if(activityPattern.equals("5")){  �ӹ���Ʒ
				}//end if(activityPattern!=null&&!activityPattern.equals("")){
			}//end for
			
			
			
			for(int i=0; i<geActivitiesRuleList.size();i++){
				if(!"".equals(geActivitiesRuleList.get(i).getConnectFlag())&& null != geActivitiesRuleList.get(i).getKindCode() ){
					activityInputBOMList.get(i).setConnectFlag(geActivitiesRuleList.get(i).getConnectFlag());
					activityInputBOMList.get(i).setKindCode(geActivitiesRuleList.get(i).getKindCode());
				}
			}
			//���ù�������
			List<ActivityResultBOM> activityResultBOMList = activityRuleService.getActivityInfo(activityInputBOMList);
			
			//����ʹ��
//			List<ActivityResultBOM> activityResultBOMList = new ArrayList<ActivityResultBOM>();
			//for update
//			ActivityResultBOM activityResultBOM1 = new ActivityResultBOM();
//			activityResultBOM1.setDeptID("3210000");//����
//			activityResultBOM1.setStartDate("2012-01-01 00:01:02");
//			activityResultBOM1.setEndDate("2012-01-10 00:01:02");
//			activityResultBOM1.setRiskCode("0510");
//			activityResultBOM1.setItemID("8a80c4f7349d617b01349d6976e40001");
//			activityResultBOM1.setActivityPattern("1");
//			activityResultBOM1.setProposalNo("testssss");
//			activityResultBOM1.setProposalArea("3210600");//����
//			activityResultBOM1.setnValue("3");
//			activityResultBOMList.add(activityResultBOM1);
//			
//			ActivityResultBOM activityResultBOM2 = new ActivityResultBOM();
//			activityResultBOM2.setDeptID("3210000");//����
//			activityResultBOM2.setStartDate("2012-01-01 00:01:02");
//			activityResultBOM2.setEndDate("2012-01-10 00:01:02");
//			activityResultBOM2.setRiskCode("0507");
//			activityResultBOM2.setItemID("8a80c4f7349d617b01349d6976e40001");
//			activityResultBOM2.setActivityPattern("1");
//			activityResultBOM2.setProposalNo("testssss1");
//			activityResultBOM2.setProposalArea("3210100");//����
//			activityResultBOM2.setnValue("3");
//			activityResultBOMList.add(activityResultBOM2);
			
			
			if(activityResultBOMList!=null&&activityResultBOMList.size()>0){//����ӹ������淵�ص�����
				//���ǵ�DTO����װ
				for(ActivityResultBOM activityResultBOMTemp:activityResultBOMList){
					
					if(activityResultBOMTemp.getActivityPattern()!=null
						&&!"".equals(activityResultBOMTemp.getActivityPattern())){//���ʽ 
						
						
						//��һ�ֻ��ʽ
						if(activityResultBOMTemp.getActivityPattern().equals("1")){//�������пͻ�
							//��������Ǳ�����ˮ�����Ϣ ͬʱ ��ҳ������Ҫ����Ʒ������
							GeThirdParterService geThirdParterService = saveGeThirdParterSerialNumberAndReturnGethirdParterService(userId,activityResultBOMTemp);
							returnGeThirdParterServiceList.add(geThirdParterService);
						}
						
						
						
						//�ڶ��ֻ��ʽ
						if(activityResultBOMTemp.getActivityPattern().equals("2")){//����ǰN���ͻ�
							if(activityResultBOMTemp.getnValue()!=null
								&&!activityResultBOMTemp.getnValue().equals("")){
								
								if(findItecmCount(activityResultBOMTemp)){//����ǰN���ͻ�
									//��������Ǳ�����ˮ�����Ϣ ͬʱ ��ҳ������Ҫ����Ʒ������
									GeThirdParterService geThirdParterService = saveGeThirdParterSerialNumberAndReturnGethirdParterService(userId,activityResultBOMTemp);
									returnGeThirdParterServiceList.add(geThirdParterService);
								}else{
									//������ǰN���ͻ�     ��������Ʒ��ˮ��
									//return null;
								}
							}else{
								throw MarketingException.newInstanceCode("002");//�����ʽtActivityPattern=2,����ǰN���ͻ�ʱ��N��ֵ����Ϊ��
							}
						}//�ڶ��ֻ end
						
						
						//�����ֻ��ʽ 
						if(activityResultBOMTemp.getActivityPattern().equals("3")){//����N%�ͻ�
							//������Ʒ�Ļ��ʽ �������û����г齱
							//�������������Ҫ�ظ�����
							GeThirdParterService gethirdParterService = new GeThirdParterService();
							gethirdParterService.setActivityPattern(activityResultBOMTemp.getActivityPattern());
							gethirdParterService.setItemID(activityResultBOMTemp.getItemID());
							gethirdParterService.setnValue(activityResultBOMTemp.getnValue());
							gethirdParterService.setStartDate(activityResultBOMTemp.getStartDate());
							gethirdParterService.setEndDate(activityResultBOMTemp.getEndDate());
							gethirdParterService.setDeptID(activityResultBOMTemp.getDeptID());//�����������ڵĻ�������
							gethirdParterService.setRiskCode(activityResultBOMTemp.getRiskCode());
							gethirdParterService.setProposalNo(activityResultBOMTemp.getProposalNo());
							gethirdParterService.setProposalArea(activityResultBOMTemp.getProposalArea());//Ͷ������
							returnGeThirdParterServiceList.add(gethirdParterService);
						}
					}else{
						throw MarketingException.newInstanceCode("001");//���������з��ص������У������Ϊ��
					}//if(activityResultBOMTemp.getActivityPattern()!=null end
				}//for(ActivityResultBOM activityResultBOMTemp:activityResultBOMList){  end
			}else{
				//���ӹ�������δ�����κζ�������ôֱ�ӷ���NULL
			}
		}
		
		map.put("GeDirectorys", (geDirectorys!=null||geDirectorys.size()>0)?geDirectorys:null);
		map.put("returnGeThirdParterServiceList", returnGeThirdParterServiceList);
		map.put("GeActivitiesPictures", geActivitiesPictures);
		map.put("isDiscountFlag", isDiscountFlag);
		map.put("discountID", discountID);//�����õ��ۿ�ID
		map.put("discountAndJumpUrl", discountAndJumpUrl); //�����õ��ۿ�ID��ͼƬ��ת��URL��ַ<map>
		return map;
	}
	
	/**
	 * ��װ ��������DTO
	 * @param geActivitiesRuleTemp
	 * @param activityInputBOMList
	 */
	public void setActivityInputBOMProperty(GeActivitiesRule geActivitiesRuleTemp,List<ActivityInputBOM> activityInputBOMList,String Premium ){
	
		String deptId = geActivitiesRuleTemp.getDeptID();
		String eid = geActivitiesRuleTemp.getEid();
		
		if(activityInputBOMList!=null&&activityInputBOMList.size()>0){//�ȿ��Ƿ����ظ���  DeptID,RiskCode
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
				activityInputBOM.setDeptID(geActivitiesRuleTemp.getDeptID());//����
				activityInputBOM.setRiskCode(geActivitiesRuleTemp.getEid());//���ڱ�Ϊ��Ʒ����Eid��
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
			activityInputBOM.setDeptID(geActivitiesRuleTemp.getDeptID());//����
			activityInputBOM.setRiskCode(geActivitiesRuleTemp.getEid());//���ڱ�Ϊ��Ʒ����Eid��
			activityInputBOM.setProposalNo(geActivitiesRuleTemp.getProposalNo());
			activityInputBOM.setProposalArea(geActivitiesRuleTemp.getProposalArea());
			if(Premium!=null){
				activityInputBOM.setPeremiumValue(Double.parseDouble(Premium));
			}
			activityInputBOMList.add(activityInputBOM);
		}
		
		
		
	}
	
	/**
	 * Ҫ�����ֻ��ʽ 
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
	//����Ͷ������
	private void setProposalAreaProperty(List<GeActivitiesRule> geActivitiesRuleList){
		if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
			for (GeActivitiesRule geActivitiesRule : geActivitiesRuleList) {
				geActivitiesRule.setProposalArea(geActivitiesRule.getDeptID());//Ͷ������
			}
		}
	}
	//������Ʒ����ˮ ��Ȼ�󷵻��Ǹ��������Ʒ
	private GeThirdParterService saveGeThirdParterSerialNumberAndReturnGethirdParterService(String userId,ActivityResultBOM activityResultBOMTemp){
		//set thirdParterServer
		GeThirdParterService gethirdParterService = new GeThirdParterService();
		gethirdParterService = thirdParterService.findGeThirdParterServiceByItemID(activityResultBOMTemp.getItemID());
		
		GeThirdParterSerialNumber geThirdParterSerialNumber = new GeThirdParterSerialNumber();
		geThirdParterSerialNumber.setGeThirdParterService(gethirdParterService);
		geThirdParterSerialNumber.setUserID(userId);//set User
		geThirdParterSerialNumber.setProposalNo(activityResultBOMTemp.getProposalNo());//����Ͷ������
		geThirdParterSerialNumber.setOpertionDate(new Date());//����ʱ��
		geThirdParterSerialNumber.setValidInd("0");//���ó�ʼ��Ч״̬
		geThirdParterSerialNumber.setCount("1");//��Ʒ����
		geThirdParterSerialNumber.setProposalArea(activityResultBOMTemp.getProposalArea());
		thirdParterService.addGeThirdParterSerialNumber(geThirdParterSerialNumber);//����geThirdParterSerialNumber
		
		//ֱ�ӽ���Ʒ ��ӽ�ȥ
		gethirdParterService.setActivityPattern(activityResultBOMTemp.getActivityPattern());
		return gethirdParterService;
	}
	/**
	 * �������Ϊ2���������
	 * �ж��Ƿ���ǰN��
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
			MarketingException.newInstanceCode("003");//�ַ�������ת��ΪDate���ڴ���
		}
		//set deptID
		if(activityResultBOM.getDeptID()!=null
			&&!activityResultBOM.getDeptID().equals("")){
			queryRule.addEqual("deptID", activityResultBOM.getDeptID());
		}else{
			MarketingException.newInstanceCode("004");//�������淵�صĲ������е���deptID����Ϊ��
		}
		//set riskCode
		if(activityResultBOM.getRiskCode()!=null
			&&!"".equals(activityResultBOM.getRiskCode())){
			queryRule.addEqual("riskCode", activityResultBOM.getRiskCode());
		}else{
			MarketingException.newInstanceCode("005");//�������淵�صĲ�����������riskCode����Ϊ��
		}
		//set itemID
		if(activityResultBOM.getItemID()!=null
			&&!activityResultBOM.getItemID().equals("")){
			queryRule.addEqual("itemID", activityResultBOM.getItemID());
		}else{
			MarketingException.newInstanceCode("006");//�������淵�صĲ���������Ʒ����itemID����Ϊ��
		}
		//set activityPattern
		queryRule.addEqual("activityPattern", activityResultBOM.getActivityPattern());
		//nValue is not null
		if(activityResultBOM.getnValue()==null||activityResultBOM.getnValue().equals("")){
			MarketingException.newInstanceCode("007");//�������淵�صĲ������л��������ǰN���ͻ�����N��ֵΪ��
		}
		List<GeReturnRuleCount> geReturnRuleCountList = super.find(GeReturnRuleCount.class, queryRule);
		if(geReturnRuleCountList!=null&&geReturnRuleCountList.size()>0){
			//�����ݲ�ѯ�������Ƚ�N��ֵȻ�� �жϸ���
			GeReturnRuleCount geReturnRuleCount = (GeReturnRuleCount)geReturnRuleCountList.get(0);
			String itemCountTemp = geReturnRuleCount.getItemCount();
			itemCountTemp = String.valueOf((Integer.parseInt(itemCountTemp)+1));
			//����������ȡ����n��ֵ�����ݿ��е�n��ֵ���бȽ�
			String n = activityResultBOM.getnValue();
			if(Integer.parseInt(itemCountTemp)<=(Integer.parseInt(n))){
				geReturnRuleCount.setItemCount(itemCountTemp);
				//����count++
				updateGeReturnRuleCount(geReturnRuleCount);
				return true;//��������ǰN���ͻ�
			}else{
				return false;//����������ǰN���ͻ�
			}
		}else{//��ѯʱ û�н��������ô��GE_RETURNRULE_COUNT ����ý��:����һ��
			GeReturnRuleCount geReturnRuleCount = new GeReturnRuleCount();
			geReturnRuleCount.setActivityStartDate(activityStartDate);
			geReturnRuleCount.setActivityEndDate(activityEndDate);
			geReturnRuleCount.setDeptID(activityResultBOM.getDeptID());
			geReturnRuleCount.setRiskCode(activityResultBOM.getRiskCode());
			geReturnRuleCount.setItemID(activityResultBOM.getItemID());
			geReturnRuleCount.setActivityPattern(activityResultBOM.getActivityPattern());
			geReturnRuleCount.setItemCount("1");//���뼴�ǵ�һ��
			//���������
			addGeReturnRuleCount(geReturnRuleCount);
			return true;//��������ǰN���ͻ�
		}
	}
	/**
	 * ��ѳ齱 �Ƿ����н�һ����Ʒ
	 */
	public List<GeThirdParterService>  findFreePrizeDraw(List<GeThirdParterService> geThirdParterServiceList,String userId){
		List<GeThirdParterService> dealList = new ArrayList<GeThirdParterService>();//Ҫ���ظ������ߵ�List
		
		if(geThirdParterServiceList!=null&&geThirdParterServiceList.size()>0){
			for(GeThirdParterService geThirdParterServiceTemp :geThirdParterServiceList){
				int n=0;
				if(geThirdParterServiceTemp.getnValue()!=null&&!geThirdParterServiceTemp.equals("")){
					n=Integer.parseInt(geThirdParterServiceTemp.getnValue());
				}
				///n = 80;//��ʱд��
				/**
				 * N%
				 * 
				 * �� 0-100 ֮�����һ�������
				 * 
				 * �����������Ƿ�С��N
				 * 
				 */
				Random random = new Random();
				int mun = random.nextInt(100)+1; 
				if(mun<=n){//�н�
					
					//������Ʒ��ˮ��
					GeThirdParterSerialNumber geThirdParterSerialNumber = new GeThirdParterSerialNumber();
					//set User
					geThirdParterSerialNumber.setUserID(userId);
					//set thirdParterServer
					GeThirdParterService gethirdParterService = new GeThirdParterService();
					gethirdParterService = thirdParterService.findGeThirdParterServiceByItemID(geThirdParterServiceTemp.getItemID());
					geThirdParterSerialNumber.setGeThirdParterService(gethirdParterService);
					//
					geThirdParterSerialNumber.setProposalNo(geThirdParterServiceTemp.getProposalNo());//����Ͷ������
					geThirdParterSerialNumber.setOpertionDate(new Date());//����ʱ��
					geThirdParterSerialNumber.setValidInd("0");//���ó�ʼ��Ч״̬
					geThirdParterSerialNumber.setCount("1");//��Ʒ����
					geThirdParterSerialNumber.setProposalArea("");//
					thirdParterService.addGeThirdParterSerialNumber(geThirdParterSerialNumber);//����geThirdParterSerialNumber
					//ֱ�ӽ���Ʒ ��ӽ�ȥ
					dealList.add(gethirdParterService);
				}else{//û�н�
					//ֱ�ӷ���Ϊ�� 
				}
			}
		}//end if(geThirdParterServiceList!=null&&geThirdParterServiceList.size()){
		return dealList;
	}

	/**
	 * �ṩ������   ͼƬ��·����ȡ,һ��ҳ�����ʾͼƬ
	 * geActivitiesRuleList  ����ֻ����������ξ���
	 * eid
	 * deptId
	 * ֻ��һ��
	 * @throws Exception 
	 */
	//��һ��
//	public List<GeActivitiesPicture> getGeActivitiesPictureList(List<GeActivitiesRule> geActivitiesRuleList) throws Exception{
//		//��ѯ�Ƿ�����ֵ�����Żݻ�������ˣ����ǰ̨Ҫ��ʾ��ͼƬ����
//		//Ҫ���صĽ��
//		List<GeActivitiesPicture> geActivitiesPictureList = new ArrayList<GeActivitiesPicture>();//Ҫ���صĽ��
//		
//		if(geActivitiesRuleList!=null&&geActivitiesRuleList.size()>0){
//			for (GeActivitiesRule geActivitiesRuleNewTemp : geActivitiesRuleList) {
//				//���ݵ��������ִ��� ȥ��ѯ�� ���������Ļ
//				GeAddServiceActivity  geAddServiceActivityTemp = queryAddServiceActivityRecursion(geActivitiesRuleNewTemp);//һ����Ʒ��Ӧ��Ӫ���
//				if(geAddServiceActivityTemp!=null){//�õ���Ӫ���
//					//�ڿ�����������Ƿ���һ���̶��ı��ѷ�Χ�� ������  ��װ����
//					if(isPeremiumRange(geActivitiesRuleNewTemp,geAddServiceActivityTemp)){//�ڱ��ѷ�Χ����
//						GeAddServiceActivity geAddServiceActivityAgain = findAddGeAddServiceActivityByActivityId(geAddServiceActivityTemp.getActivityId());
//						List<GeActivitiesPicture> pictureTempList = geAddServiceActivityAgain.getGeActivitiesPictures();
//						for (GeActivitiesPicture geActivitiesPicture : pictureTempList) {
//							geActivitiesPictureList.add(geActivitiesPicture);   //���ܻ����ͬһ���ͼƬ
//						}
//					}// end if
//				}
//			}//end for
//		}
//		return geActivitiesPictureList;
//	}
	
	/**
	 *�ڶ��棬��һ��ҳ��Ҫ���ص�ͼƬ 
	 */
	public List<GeActivitiesPicture> getGeActivitiesPictureList(String eid,String deptId) throws Exception{
		//Ҫ���صĽ��
		List<GeActivitiesPicture> geActivitiesPictureList = new ArrayList<GeActivitiesPicture>();//Ҫ���صĽ��
		GeActivitiesRule geActivitiesRuleNewTemp = new GeActivitiesRule();
		geActivitiesRuleNewTemp.setEid(eid);//eid
		geActivitiesRuleNewTemp.setDeptID(deptId);
		
		List<GeAddServiceActivity>  geAddServiceActivityTemp = queryAddServiceActivityRecursion(geActivitiesRuleNewTemp);//һ����Ʒ��Ӧ��Ӫ���
		if(geAddServiceActivityTemp!=null){//�õ���Ӫ���
			//�ڿ�����������Ƿ���һ���̶��ı��ѷ�Χ�� ������  ��װ����
//			if(isPeremiumRange(geActivitiesRuleNewTemp,geAddServiceActivityTemp)){//�ڱ��ѷ�Χ����
				GeAddServiceActivity geAddServiceActivityAgain = findAddGeAddServiceActivityByActivityId(geAddServiceActivityTemp.get(0).getActivityId());
				List<GeActivitiesPicture> pictureTempList = geAddServiceActivityAgain.getGeActivitiesPictures();
				for (GeActivitiesPicture geActivitiesPicture : pictureTempList) {
					geActivitiesPictureList.add(geActivitiesPicture);   //���ܻ����ͬһ���ͼƬ
				}
//			}// end if
		}
		return geActivitiesPictureList;
	}
	/**
	 * ɾ����ֵ����
	 * �������
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
	 * ɾ����ֵ��������
	 * �������
	 */
	public void deleteAddGeactivitydept(String activityId){
		if(activityId!=null&&!activityId.equals("")){
			Query query = getSession().createSQLQuery("delete from  ge_activity_dept where ACTIVITYID = ? ");
			query.setString(0, activityId);
			query.executeUpdate();		
		}
	}
	/**
	 * ��ѯ����,����ѯ��������,����ҳ
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
			//�ʱ��
			
		}
		return null;
	}
	
	/**
	 * ��ѯGeReturnRuleCount
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
	 * ����򷵻���Ʒ������-GE_ReturnRule_Count ����������
	 * @param geReturnRuleCount
	 */
	public void addGeReturnRuleCount(GeReturnRuleCount geReturnRuleCount){
		super.save(geReturnRuleCount);
	}
	/**
	 * ����򷵻���Ʒ������-GE_ReturnRule_Count ��������
	 * @param geReturnRuleCount
	 */
	public void updateGeReturnRuleCount(GeReturnRuleCount geReturnRuleCount){
		super.update(geReturnRuleCount);
	}
	//******************************************GeActivitiesConfig operator start*********************************
	//��ҳ��ѯGeActivitiesConfig���е�ֵ
	public Page queryGeActivitiesConfig(GeActivitiesConfig geActivitiesConfig,int pageNo,int pageSize){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("validInd", "1");//�Ƿ���Ч��־1����Ч 0����Ч
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
	//ֻ����������ѯ
	public GeActivitiesConfig queryGeActivitiesConfigByProductId(String productId){
		return super.get(GeActivitiesConfig.class, productId);
	}
	//******************************************GeActivitiesConfig operator end***********************************
	/**
	 * 2010-01-01 01:01������ʽ ��ȷ����
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
	//����̨��MIS����Ա��¼��ȥĬ�ϵĻ���Ȩ������ѯ��ֵ����
	@Override
	public Page findAddGeAddServiceActivityByDefaultPermession(
			HttpSession session, GeAddServiceActivity geAddServiceActivity,
			int pageNo, int pageSize,String groupSql,String bizType) {
		//�ȵ��ó�Ĭ�Ϲ�˾��IDû�з�ҳ
		String authorityid="";
		Map map = (Map) session.getAttribute("permission");
		if(bizType!=null&&!"look".equals(bizType)){
			 authorityid = (String) map.get("ROLE_B_AAGA_S");//������������Ȩ������
		}else{
			 authorityid = (String) map.get("ROLE_ROLE_B_AAGA_SEE");//������������Ȩ������
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
			if(values.size() >= 1000){//��������
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
			if(geAddServiceActivity.getActivityName()!=null&&!geAddServiceActivity.getActivityName().equals("")){//�����
				//queryRule.addLike("activityName", "%"+geAddServiceActivity.getActivityName().trim()+"%");
				sql.append(" and activityName like '%"+geAddServiceActivity.getActivityName().trim()+"%'");
			}
			if(geAddServiceActivity.getValidInd()!=null&&!geAddServiceActivity.getValidInd().equals("")){//�Ƿ���Ч
				sql.append(" and VALIDIND = '"+geAddServiceActivity.getValidInd().trim()+"'");
			}
			if(geAddServiceActivity.getStatus()!=null&&!geAddServiceActivity.getStatus().equals("")){//�״̬
//				queryRule.addEqual("status", geAddServiceActivity.getStatus().trim());
				if("all".equals(geAddServiceActivity.getStatus())){//ȫ����ѯ����&�����
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
		return super.findByHql(hql, pageNo, pageSize, operatorId);//(pageNo-1)*pageSize��ҳ
	}
	
	/*
	 * ���湤���� 
	 */
	public void addGeAddServiceProcess(GeAddServiceProcess geAddServiceProcess ){
		super.save(geAddServiceProcess);
	}
	
	
	//���²������еĹ��ܶ������ڹ��������ܵ��õķ���***************************************
	//*************************************************************************************************************
	//*************************************************************************************************************
	//*************************************************************************************************************
	
	//����һ��������
	public void startTask(GeAddServiceProcess geAddServiceProcess ,String activityId,String taskId,String workflowId) throws IOException, Exception{
		//�������Լ���ҵ�� //�޸�һ�±��ؿ��״̬ 
		GeAddServiceActivity geAddServiceActivityCondition = new GeAddServiceActivity();
		geAddServiceActivityCondition.setActivityId(activityId);//������״̬
		geAddServiceActivityCondition.setStatus("1");//������״̬ 
		GeAddServiceActivity  geAddServiceActivity =  updateAddGeAddServiceActivity(geAddServiceActivityCondition);
		
		//���汾�ؿ�켣״̬
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
		
		//���ù������Ĵ���״̬
		//******************����������
		MarktingWrokFlow<GeAddServiceActivity> cf  = new MarktingWrokFlow<GeAddServiceActivity>();
		//���3�ǵ������� 
		cf.setWorkFlowID("marketingWorkFlow"+getArea(geAddServiceActivity));  // ҵ������̬ƴ��     comboAreaWorkFlow���㹤����������   3��ҵ������ ��3��ʱд����
		cf.setEntity(geAddServiceActivity);
		*//**��ge_workflow���ȡ��Ա������Ϣ*//*
		//���������д����
		GeWorkflowId id = new GeWorkflowId();
		id.setFuncitontype("WORKFLOWCONFIG");
		id.setArea("CC");
		id.setFiletype("0");
		cf.setCity(geAddServiceActivity.getDeptID());
		GeWorkflow geWorkflow = geWorkFlowService.findGeWorkflowByPK(id);
		//cf.setCity(areaCode);
		markingWorkFlowService.startTask(geWorkflow,cf);*/
		
//		EntityWorkFlow<GeAddServiceActivity> wf = new EntityWorkFlow<GeAddServiceActivity>();
//		wf.setWorkFlowID("marketingWorkFlow"+getArea(geAddServiceActivity));  // ҵ������̬ƴ��     comboAreaWorkFlow���㹤����������   3��ҵ������ ��3��ʱд����
//		Map<String,Object> params = new HashMap<String, Object>();
//		params.put("entity_Status", "1");
//		params.put("entity_activityName", geAddServiceActivity.getActivityName());
//		wf.setEntity(geAddServiceActivity);
//		String[] result = workFlowServiceImpl.startTask(wf,params,cityCodes.toString());
//		// �ѽڵ�id�͹�����id��������
//		geAddServiceActivity.setRunintTaskId(result[0]);
//		geAddServiceActivity.setWorkFlowId(result[1]);
		// �븽��
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
	
	//���¸�������Ϊaction����װ����ʱ��Ҫ�õ��ķ���
	private String  getArea(GeAddServiceActivity geAddServiceActivity){
		String deptId = geAddServiceActivity.getDeptID();
		deptId = deptId.substring(0, 1);
		return deptId;
	}
	
	//����һ��������
	public void completeTask(GeAddServiceProcess geAddServiceProcess, String activityId,String taskId,String workflowId) throws IOException, Exception{
		//��д���Լ�ҵ��
		
		//�޸ı��ؿ���ֵ����״̬
		GeAddServiceActivity  geAddServiceActivityCondition  =super.get(activityId);
		geAddServiceActivityCondition.setActivityId(activityId);//
		geAddServiceActivityCondition.setStatus("1");//  ������������״̬ 
		GeAddServiceActivity geAddServiceActivity = updateAddGeAddServiceActivity(geAddServiceActivityCondition);
		//���汾�ؿ�켣״̬
		geAddServiceProcess.setGeAddServiceActivity(geAddServiceActivity);
		addGeAddServiceProcess(geAddServiceProcess);
		// ˢ�µĲ���
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("entity_Status",geAddServiceActivity.getStatus());
		param.put("workFlow_entity",geAddServiceActivity);
//		String newTaskId = workFlowServiceImpl.completeTask(taskId, workflowId,geAddServiceActivity, param,geAddServiceActivity.getDeptID());
		// ˢ�����нڵ�
//		geAddServiceActivity.setRunintTaskId(newTaskId);
		super.update(geAddServiceActivity);
		/*//���ù������Ĺ���
		MarktingWrokFlow<GeAddServiceActivity> c =	this.markingWorkFlowService.getSingleTask(taskId, workflowId);
		c.setWorkFlowID(workflowId);
		c.setEntity(geAddServiceActivity);
		markingWorkFlowService.completeTask(taskId, null, c);*/
	}
	
	//����һ��������
	public void doRoolBack(GeAddServiceProcess geAddServiceProcess ,String activityId,String taskId,String workflowId,String status) throws IOException, Exception{
		
		//�޸���ֵ�������״̬
		//GeAddServiceActivity  geAddServiceActivityCondition  = new GeAddServiceActivity();
		GeAddServiceActivity geAddServiceActivityCondition =   super.get(activityId);
		geAddServiceActivityCondition.setActivityId(activityId);//
		geAddServiceActivityCondition.setStatus(status);//  ����������״̬ 
		GeAddServiceActivity geAddServiceActivity = updateAddGeAddServiceActivity(geAddServiceActivityCondition);
		//���汾�ؿ�켣״̬
		geAddServiceProcess.setGeAddServiceActivity(geAddServiceActivity);
		addGeAddServiceProcess(geAddServiceProcess);
		
		//���ù��������д�����
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
	
	//���²������еĹ��ܶ������ڹ��������ܵ��õķ���***************************************
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
			if(!setCardType(geDirectory)){//�����
				if(!setProductMainType(geDirectory)){//������Ƿǳ�
					if(!setCarType(geDirectory)){//������Ƿǳ�
						geDirectory.setExtra1("other");//������չ���ֶ�
					}
				}
			}
		}
	}
	
	//���ó�����
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
	//���÷ǳ�����
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
	//���ó�����
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
	 * ���ݻ�������Ͳ�ƷEID��ѯ������Ƿ����������Ʒ��
	 * ���ػͼƬURl
	 *  ����ʱ��  to_date(to_char(sysdate, 'yyyy-mm-dd '), 'yyyy-mm-dd ')   ����Ҫto_date���������ͻ��ͼ�ڻ����ʱ���ǰһ�����
	 * @param dept
	 * @param eid
	 * @param activitypattern ���ʽ
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
		java.util.Map map = new HashMap();//Ҫ���صĽ����
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
