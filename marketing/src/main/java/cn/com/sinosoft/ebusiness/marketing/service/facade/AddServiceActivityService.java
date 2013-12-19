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
 *增值服务功能
 *活动的维护
 *规则的维护
 */
public interface AddServiceActivityService {
	public void setGeDirectoryType(List<GeDirectory> geDirectorys);
	//按照指定查询条件进行查询活动
	public List<GeAddServiceActivity> queryAddServiceActivityBySQL(GeActivitiesRule geActivitiesRule) throws ParseException;
	public List<GeAddServiceActivity> queryAddServiceActivityRecursion(GeActivitiesRule geActivitiesRule) throws ParseException;
	
	//public List<GeAddServiceActivity> queryAddServiceActivityRecursion(GeAddServiceActivity geAddServiceActivity);
	public List<GeCustomAddServiceActivity>  isRepeatAddServiceActivity(GeAddServiceActivity geAddServiceActivity);
	public void addAddGeAddServiceActivityAndRule(GeAddServiceActivity geAddServiceActivity);
	//按后台的MIS操作员登录进去默认的机构权限来查询增值服务活动
	public Page findAddGeAddServiceActivityByDefaultPermession(HttpSession session ,GeAddServiceActivity geAddServiceActivity,int pageNo,int pageSize,String groupSql,String bizType);
	/**
	 * 查询活动
	 * 按主键查询
	 * @return
	 * @throws Exception 
	 */
	public GeAddServiceActivity findAddGeAddServiceActivityByActivityId(String activityId) throws Exception;
	
//	/**
//	 * 获取工作任务
//	 * @param taskID
//	 * @param workFlowID
//	 * @return
//	 */
//	public MarktingWrokFlow findAddGeAddServiceByTaskId(String taskID,String workFlowID);
	
	/**
	 * 审核通过
	 * @param taskID
	 * @param userID
	 * @param obj
	 */
	public void auditAcvity(String taskID,String workFlowId,String userID);
	
	
	/**
	 * 打回 
	 * @param taskID
	 * @param workFlowId
	 * @param userID
	 * @param type
	 */
	public void failureAutit(String taskID,String workFlowId,String userID);
	/**
	 * 删除增值服务活动
	 * 按活动主键
	 */
	public void deleteAddGeAddServiceActivity(String activityId);
	/**
	 * 更新查询活动
	 * 更新规则
	 * @param geAddServiceActivity
	 * @throws Exception 
	 * @throws IOException 
	 */
	public void updateAddGeAddServiceActivityAndGeActivitiesRule(GeAddServiceActivity geAddServiceActivity ,String uploadPictureSerialNos,String rulds);
	/**
	 * 查询增值服务活动，按规则引擎来查询
	 * 返回一个商品
	 * 直接返回为空，那么没有赠送的商品
	 * 如果不为空，得看活动类型来判断是直接显示该商品
	 * 还是直接去抽奖该商品
	 * 
	 * systemFlow  01 车险全流程  长虎
	 * systemFlow  02 寿险全流程  小彬
	 * @throws ParseException 
	 * 
	 */
	//public List<GeThirdParterService> findAddGeAddServiceActivityCheckRule(List<GeActivitiesRule> geActivitiesRuleList,String userId) throws ParseException;
	public java.util.Map findAddGeAddServiceActivityCheckRule(List<GeActivitiesRule> geActivitiesRuleList,GeAddserviceConditionDto geAddserviceConditionDto) throws ParseException;
	
	/**
	 * 提供给长虎要寻找 的营销活动的图片
	 * @param geActivitiesRuleList
	 * @return
	 * @throws ParseException
	 * @throws Exception 
	 */
	//public List<GeActivitiesPicture> getGeActivitiesPictureList(List<GeActivitiesRule> geActivitiesRuleList) throws ParseException, Exception;
	public List<GeActivitiesPicture> getGeActivitiesPictureList(String eid,String deptId) throws Exception;
	/**
	 * 免费抽奖 是否能中奖一个产品
	 */
	public List<GeThirdParterService>  findFreePrizeDraw(List<GeThirdParterService> geThirdParterServiceList,String userId);
	/**
	 * 向规则返回商品计数器-GE_ReturnRule_Count 里增加数据
	 * @param geReturnRuleCount
	 */
	public void addGeReturnRuleCount(GeReturnRuleCount geReturnRuleCount);
	/**
	 * 向规则返回商品计数器-GE_ReturnRule_Count 更新数据
	 * @param geReturnRuleCount
	 */
	public void updateGeReturnRuleCount(GeReturnRuleCount geReturnRuleCount);
	public String getRiskCodeToEid(String riskCode);
	/**
	 * 查询GeReturnRuleCount
	 * @param queryRule
	 * @return
	 */
	public List<GeReturnRuleCount> findGeReturnRuleCount(QueryRule queryRule);
	//************************************GeActivitiesConfig operator start****************
	//分页查询GeActivitiesConfig表中的值
	public Page queryGeActivitiesConfig(GeActivitiesConfig geActivitiesConfig,int pageNo,int pageSize);
	public GeAddServiceActivity updateAddGeAddServiceActivity(GeAddServiceActivity geAddServiceActivity) throws IOException, Exception;
	public GeAddServiceActivity updateAddGeAddServiceActivity(GeAddServiceActivity geAddServiceActivity,boolean finialFlag) throws IOException, Exception;
	//************************************GeActivitiesConfig operator end****************
	
	
	//以下部分所有的功能都是用于工作流功能调用的方法***************************************
	//*************************************************************************************************************
	//*************************************************************************************************************
	//*************************************************************************************************************
	public void startTask(GeAddServiceProcess geAddServiceProcess ,String activityId,String taskId,String workflowId) throws IOException, Exception;
	
	//处理一个工作流
	public void completeTask(GeAddServiceProcess geAddServiceProcess, String activityId,String taskId,String workflowId) throws IOException, Exception;
	//回退操作
	public void doRoolBack(GeAddServiceProcess geAddServiceProcess ,String activityId,String taskId,String workflowId,String status) throws IOException, Exception;
	public List<String> getInquiryIdByGroupCity(String groupCity,String statusEquals,String[] statusNoEquals,String inquiryType);
	
	/**
	 * 根据用户id查询已处理
	 * @param pageNo
	 * @param pageSize
	 * @param operatorId
	 * @return
	 */
	public Page queryHasProcessed(int pageNo, int pageSize,String operatorId,String actName);
	/**
	 * 根据机构代码查询机构
	 * @param deptId
	 * @return
	 */
	public String getDeptName(String deptId);
	/**
	 * 根据机构代码和产品EID查询本活动中是否存在赠送商品，
	 * 返回活动图片URl
	 * @param dept
	 * @param eid
	 * @return
	 */
	public Map activityBoola(String dept,String eid,String[] activitypattern);
	public boolean activityBool(String dept,String eid);
}
