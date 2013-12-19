package cn.com.sinosoft.ebusiness.marketing.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.com.sinosoft.ebusiness.common.party.domain.GeThirdParterService;
import cn.com.sinosoft.ebusiness.marketing.domain.GeActivitiesConfig;
import cn.com.sinosoft.ebusiness.marketing.domain.GeActivitiesPicture;
import cn.com.sinosoft.ebusiness.marketing.domain.GeActivitiesRule;
import cn.com.sinosoft.ebusiness.marketing.domain.GeAddServiceActivity;
import cn.com.sinosoft.ebusiness.marketing.domain.GeAddServiceProcess;
import cn.com.sinosoft.ebusiness.marketing.domain.GeAddserviceConditionDto;
import cn.com.sinosoft.ebusiness.marketing.domain.GeCustomAddServiceActivity;
import cn.com.sinosoft.ebusiness.marketing.domain.GeReturnRuleCount;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectory;
/**
 *��ֵ������
 *���ά��
 *�����ά��
 */
public interface AddServiceActivityService {
	public void setGeDirectoryType(List<GeDirectory> geDirectorys);
	//����ָ����ѯ�������в�ѯ�
	public List<GeAddServiceActivity> queryAddServiceActivityBySQL(GeActivitiesRule geActivitiesRule) throws ParseException;
	public List<GeAddServiceActivity> queryAddServiceActivityRecursion(GeActivitiesRule geActivitiesRule) throws ParseException;
	
	//public List<GeAddServiceActivity> queryAddServiceActivityRecursion(GeAddServiceActivity geAddServiceActivity);
	public List<GeCustomAddServiceActivity>  isRepeatAddServiceActivity(GeAddServiceActivity geAddServiceActivity);
	public void addAddGeAddServiceActivityAndRule(GeAddServiceActivity geAddServiceActivity);
	//����̨��MIS����Ա��¼��ȥĬ�ϵĻ���Ȩ������ѯ��ֵ����
	public Page findAddGeAddServiceActivityByDefaultPermession(HttpSession session ,GeAddServiceActivity geAddServiceActivity,int pageNo,int pageSize,String groupSql,String bizType);
	/**
	 * ��ѯ�
	 * ��������ѯ
	 * @return
	 * @throws Exception 
	 */
	public GeAddServiceActivity findAddGeAddServiceActivityByActivityId(String activityId) throws Exception;
	
//	/**
//	 * ��ȡ��������
//	 * @param taskID
//	 * @param workFlowID
//	 * @return
//	 */
//	public MarktingWrokFlow findAddGeAddServiceByTaskId(String taskID,String workFlowID);
	
	/**
	 * ���ͨ��
	 * @param taskID
	 * @param userID
	 * @param obj
	 */
	public void auditAcvity(String taskID,String workFlowId,String userID);
	
	
	/**
	 * ��� 
	 * @param taskID
	 * @param workFlowId
	 * @param userID
	 * @param type
	 */
	public void failureAutit(String taskID,String workFlowId,String userID);
	/**
	 * ɾ����ֵ����
	 * �������
	 */
	public void deleteAddGeAddServiceActivity(String activityId);
	/**
	 * ���²�ѯ�
	 * ���¹���
	 * @param geAddServiceActivity
	 * @throws Exception 
	 * @throws IOException 
	 */
	public void updateAddGeAddServiceActivityAndGeActivitiesRule(GeAddServiceActivity geAddServiceActivity ,String uploadPictureSerialNos,String rulds);
	/**
	 * ��ѯ��ֵ��������������������ѯ
	 * ����һ����Ʒ
	 * ֱ�ӷ���Ϊ�գ���ôû�����͵���Ʒ
	 * �����Ϊ�գ��ÿ���������ж���ֱ����ʾ����Ʒ
	 * ����ֱ��ȥ�齱����Ʒ
	 * 
	 * systemFlow  01 ����ȫ����  ����
	 * systemFlow  02 ����ȫ����  С��
	 * @throws ParseException 
	 * 
	 */
	//public List<GeThirdParterService> findAddGeAddServiceActivityCheckRule(List<GeActivitiesRule> geActivitiesRuleList,String userId) throws ParseException;
	public java.util.Map findAddGeAddServiceActivityCheckRule(List<GeActivitiesRule> geActivitiesRuleList,GeAddserviceConditionDto geAddserviceConditionDto) throws ParseException;
	
	/**
	 * �ṩ������ҪѰ�� ��Ӫ�����ͼƬ
	 * @param geActivitiesRuleList
	 * @return
	 * @throws ParseException
	 * @throws Exception 
	 */
	//public List<GeActivitiesPicture> getGeActivitiesPictureList(List<GeActivitiesRule> geActivitiesRuleList) throws ParseException, Exception;
	public List<GeActivitiesPicture> getGeActivitiesPictureList(String eid,String deptId) throws Exception;
	/**
	 * ��ѳ齱 �Ƿ����н�һ����Ʒ
	 */
	public List<GeThirdParterService>  findFreePrizeDraw(List<GeThirdParterService> geThirdParterServiceList,String userId);
	/**
	 * ����򷵻���Ʒ������-GE_ReturnRule_Count ����������
	 * @param geReturnRuleCount
	 */
	public void addGeReturnRuleCount(GeReturnRuleCount geReturnRuleCount);
	/**
	 * ����򷵻���Ʒ������-GE_ReturnRule_Count ��������
	 * @param geReturnRuleCount
	 */
	public void updateGeReturnRuleCount(GeReturnRuleCount geReturnRuleCount);
	public String getRiskCodeToEid(String riskCode);
	/**
	 * ��ѯGeReturnRuleCount
	 * @param queryRule
	 * @return
	 */
	public List<GeReturnRuleCount> findGeReturnRuleCount(QueryRule queryRule);
	//************************************GeActivitiesConfig operator start****************
	//��ҳ��ѯGeActivitiesConfig���е�ֵ
	public Page queryGeActivitiesConfig(GeActivitiesConfig geActivitiesConfig,int pageNo,int pageSize);
	public GeAddServiceActivity updateAddGeAddServiceActivity(GeAddServiceActivity geAddServiceActivity) throws IOException, Exception;
	public GeAddServiceActivity updateAddGeAddServiceActivity(GeAddServiceActivity geAddServiceActivity,boolean finialFlag) throws IOException, Exception;
	//************************************GeActivitiesConfig operator end****************
	
	
	//���²������еĹ��ܶ������ڹ��������ܵ��õķ���***************************************
	//*************************************************************************************************************
	//*************************************************************************************************************
	//*************************************************************************************************************
	public void startTask(GeAddServiceProcess geAddServiceProcess ,String activityId,String taskId,String workflowId) throws IOException, Exception;
	
	//����һ��������
	public void completeTask(GeAddServiceProcess geAddServiceProcess, String activityId,String taskId,String workflowId) throws IOException, Exception;
	//���˲���
	public void doRoolBack(GeAddServiceProcess geAddServiceProcess ,String activityId,String taskId,String workflowId,String status) throws IOException, Exception;
	public List<String> getInquiryIdByGroupCity(String groupCity,String statusEquals,String[] statusNoEquals,String inquiryType);
	
	/**
	 * �����û�id��ѯ�Ѵ���
	 * @param pageNo
	 * @param pageSize
	 * @param operatorId
	 * @return
	 */
	public Page queryHasProcessed(int pageNo, int pageSize,String operatorId,String actName);
	/**
	 * ���ݻ��������ѯ����
	 * @param deptId
	 * @return
	 */
	public String getDeptName(String deptId);
	/**
	 * ���ݻ�������Ͳ�ƷEID��ѯ������Ƿ����������Ʒ��
	 * ���ػͼƬURl
	 * @param dept
	 * @param eid
	 * @return
	 */
	public Map activityBoola(String dept,String eid,String[] activitypattern);
	public boolean activityBool(String dept,String eid);
}
