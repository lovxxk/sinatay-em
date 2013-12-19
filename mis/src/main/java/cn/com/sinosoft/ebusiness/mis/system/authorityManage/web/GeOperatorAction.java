package cn.com.sinosoft.ebusiness.mis.system.authorityManage.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGroup;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGrouptype;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDepartmentService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDeptGroupRoleService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeGroupService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeGroupTypeService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeOperatorService;
import cn.com.sinosoft.util.encode.JsonBinder;
import cn.com.sinosoft.util.encode.Md5;

@SuppressWarnings("serial")
public class GeOperatorAction extends Struts2Action {

	private GeOperatorService geOperatorService;
	private GeDepartmentService geDepartmentService;
	private GeDeptGroupRoleService geDeptGroupRoleService;
	private GeCodeService geCodeService;
	private GeOperator geOperator;
	private String message="";
	private int flag;/**1-成功*/
	private GeGroupService geGroupService;
	private GeGroupTypeService geGroupTypeService;
	
	private List<GeGrouptype> geGrouptypeList;
	
	public GeGroupService getGeGroupService() {
		return geGroupService;
	}

	private GeGroup geGroup;

	public GeGroup getGeGroup() {
		return geGroup;
	}

	public void setGeGroup(GeGroup geGroup) {
		this.geGroup = geGroup;
	}

	public void setGeGroupService(GeGroupService geGroupService) {
		this.geGroupService = geGroupService;
	}

	/***常量**/
	private static final String BUSINESSAREA = "BusinessArea";
	public GeDepartmentService getGeDepartmentService() {
		return geDepartmentService;
	}

	public void setGeDepartmentService(GeDepartmentService geDepartmentService) {
		this.geDepartmentService = geDepartmentService;
	}

	public GeOperatorService getGeOperatorService() {
		return geOperatorService;
	}

	public void setGeOperatorService(GeOperatorService geOperatorService) {
		this.geOperatorService = geOperatorService;
	}

	public GeCodeService getGeCodeService() {
		return geCodeService;
	}

	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}

	public GeDeptGroupRoleService getGeDeptGroupRoleService() {
		return geDeptGroupRoleService;
	}

	public void setGeDeptGroupRoleService(
			GeDeptGroupRoleService geDeptGroupRoleService) {
		this.geDeptGroupRoleService = geDeptGroupRoleService;
	}

	public GeOperator getGeOperator() {
		return geOperator;
	}

	public void setGeOperator(GeOperator geOperator) {
		this.geOperator = geOperator;
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

	public GeGroupTypeService getGeGroupTypeService() {
		return geGroupTypeService;
	}

	public void setGeGroupTypeService(GeGroupTypeService geGroupTypeService) {
		this.geGroupTypeService = geGroupTypeService;
	}

	private String oldCheckGeGroup;
	public String getOldCheckGeGroup() {
		return oldCheckGeGroup;
	}

	public void setOldCheckGeGroup(String oldCheckGeGroup) {
		this.oldCheckGeGroup = oldCheckGeGroup;
	}

	private String checkGeGroups;

	public String getCheckGeGroups() {
		return checkGeGroups;
	}

	public void setCheckGeGroups(String checkGeGroups) {
		this.checkGeGroups = checkGeGroups;
	}

	public List<GeGrouptype> getGeGrouptypeList() {
		return geGrouptypeList;
	}

	public void setGeGrouptypeList(List<GeGrouptype> geGrouptypeList) {
		this.geGrouptypeList = geGrouptypeList;
	}

	
	/**
	 * 新建用户
	 * @return
	 */
	
	public void getOperatorbyId(){
		Map<String, String> resultMap = new HashMap<String, String>();
		if(geOperatorService.exists(geOperator.getOperatorid())){
			resultMap.put("resultFlag", "success");
		}else{
			resultMap.put("resultFlag", "error");
		}
	
		JSONObject jsonObject = JSONObject.fromObject(resultMap);
		renderText(jsonObject.toString());
	}
	
	/**
	 * 预创建用户
	 * @return
	 */
	@LocusTrace(setCode="GeOperatorAction_preCreate")
	public String preCreate(){
		try {
			GeOperator operator = (GeOperator) super.getSession().getAttribute("geOperator");
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("grouptypedeptid",operator.getDeptid());
			geGrouptypeList = geGroupTypeService.findGeGroupTypes(queryRule);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@LocusTrace(setCode="GeOperatorAction_createGeOperator")
	public String createGeOperator(){
		try {
			if(geOperatorService.exists(geOperator.getOperatorid())){
				message = "该员工编号已存在！";
			}else{
				geOperator.setPwd( new Md5().toMD5(geOperator.getPwd()));
				geOperator.setCreatetime(new Date());
				geOperator.setFlag("0");
				geOperatorService.save(geOperator);
				flag = 1;
				message = "用户新建成功！";
			}

			if(flag == 1 && !StringUtils.isBlank(checkGeGroups)){
				geGroupService.saveUserToGroups(geOperator.getOperatorid(), checkGeGroups);	
				message = "用户新建成功！";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			message = "操作失败！";
		}
		
		super.getRequest().setAttribute("message", message);
		return SUCCESS;
	}
	
	/**
	 * 进入用户组设置界面
	 * @return
	 */
	public String toAddUserToGroup(){
		GeOperator geOperator=(GeOperator) super.getSession().getAttribute("geOperator");

		/**找到该机构的用户组列表信息*/
        QueryRule queryRule = QueryRule.getInstance();
	    queryRule.addEqual("deptid", geOperator.getDeptid());
	    queryRule.addAscOrder("groupid");
		List<GeGroup> geGroupList = geGroupService.findGeGroups(queryRule);
		super.getRequest().setAttribute("geGroupList", geGroupList);
		
		/**查询该机构的用户组类型列表信息*/
		QueryRule qRuleGroupType = QueryRule.getInstance();
		qRuleGroupType.addEqual("grouptypedeptid", geOperator.getDeptid());
		List<GeGrouptype> geGroupTypeList = geGroupTypeService.findGeGroupTypes(qRuleGroupType);
		super.getRequest().setAttribute("geGroupTypeList", geGroupTypeList);
		super.getRequest().setAttribute("createUserId",super.getRequest().getParameter("createUserId"));
		
		return SUCCESS;
	}
	
	/**
	 * 查询所有本机构的用户组
	 * @return
	 */
	public void queryGroups(){
		GeOperator geOperator=(GeOperator) super.getSession().getAttribute("geOperator");
		super.getRequest().setAttribute("operatorId", super.getRequest().getParameter("operatorId"));
		
//		String groupid = super.getRequest().getParameter("groupid");
//		String groupname = super.getRequest().getParameter("groupname");
		String grouptypeid = super.getRequest().getParameter("grouptypeid");
		/**找到该机构的用户组列表信息*/
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.addAscOrder("groupid");
	    queryRule.addEqual("deptid", geOperator.getDeptid());
//	    if(!StringUtils.isBlank(groupid))queryRule.addEqual("groupid", groupid);
//	    if(!StringUtils.isBlank(groupname))queryRule.addLike("groupname","%"+groupname+"%");
	    if(!StringUtils.isBlank(grouptypeid))
	    	queryRule.addEqual("grouptypeid",grouptypeid);
		List<GeGroup> geGroupList = geGroupService.findGeGroups(queryRule);
		
		if(geGroupList != null && geGroupList.size() > 0){
			for (GeGroup geGroup : geGroupList) {
				geGroup.setGeDeptGroupRoles(null);
				geGroup.setGeOperators(null);
			}
		}
		super.render(JsonBinder.buildNonNullBinder().toJson(geGroupList), "application/json;charset=utf-8");
	//	return SUCCESS;
	}
	
	/**
	 * 添加用户到用户组
	 * @return
	 */
	public String addUserToGroup(){
		String checkGeGroup=super.getRequest().getParameter("checkGeGroup");
		String createUserId=super.getRequest().getParameter("createUserId");
		geGroupService.saveUserToGroups(createUserId, checkGeGroup);
		super.render(JsonBinder.buildNonNullBinder().toJson("success"), "application/json;charset=utf-8");
		return NONE;
	}
	
	/**
	 * 进入到成功提示页面
	 * @return
	 */
	public String createResult(){
		flag = 1;
		message = "用户新建成功";
		return SUCCESS;
	}
	/**
	 * 载入用户选择组界面信息，修改用户所在组
	 * @return
	 */
	public String queryGeGroupForUser(){
		GeOperator geOperator=(GeOperator) super.getSession().getAttribute("geOperator");
		
		/**找到该机构的用户组列表信息*/
        QueryRule queryRule = QueryRule.getInstance();
	    queryRule.addEqual("deptid", geOperator.getDeptid());
	    queryRule.addAscOrder("groupid");
		List<GeGroup> geGroupList = geGroupService.findGeGroups(queryRule);	
		
		/**查询该机构的用户组类型列表信息*/
		QueryRule qRuleGroupType = QueryRule.getInstance();
		qRuleGroupType.addEqual("grouptypedeptid", geOperator.getDeptid());
		List<GeGrouptype> geGroupTypeList = geGroupTypeService.findGeGroupTypes(qRuleGroupType);
		super.getRequest().setAttribute("geGroupTypeList", geGroupTypeList);
		
		/**查询用户已加入的组*/
		String userId=super.getRequest().getParameter("userId");
		List<Map> oldGroups=geOperatorService.findGroupId(userId,geOperator.getDeptid());
		
		List<Object[]> geOperatorGroupList = new ArrayList<Object[]>(); 
		Object[] geObj;
		if(geGroupList!=null && geGroupList.size()>0){
			for (GeGroup geGroup : geGroupList) {
				if(containGeGroup(geGroup,oldGroups)){
					geObj = new Object[]{geGroup,"1"};
				}else{
					geObj = new Object[]{geGroup,"0"};
				}
				geOperatorGroupList.add(geObj);
			}
		}

		super.getRequest().setAttribute("geOperatorGroupList", geOperatorGroupList);
		super.getRequest().setAttribute("userId", userId);
		return SUCCESS;
	}
	
	/**
	 *判断list是否存在geGroup
	 * @param geGroup
	 * @param list
	 * @return
	 */
	private boolean containGeGroup(GeGroup geGroup,List<Map> list){
		boolean bl = false;
		if(list!=null && list.size()>0){
			for (Map groupMap : list) {
				if(geGroup.getGroupid().equals(groupMap.get("GROUPID"))){
					bl = true;
					break;
				}
			}
		}
		return bl;
	}
	
	public String queryContainUserGroups(){
		GeOperator geOperator=(GeOperator) super.getSession().getAttribute("geOperator");
		String groupid = super.getRequest().getParameter("groupid");
		String groupname = super.getRequest().getParameter("groupname");
		String grouptypeid = super.getRequest().getParameter("grouptypeid");
		
		/**查询用户已加入的组*/
		String userId=super.getRequest().getParameter("userId");
		List<Map> oldGroups=geOperatorService.findGroupId(userId,geOperator.getDeptid());
		
		/**找到该机构的用户组列表信息*/
        QueryRule queryRule = QueryRule.getInstance();
	    queryRule.addEqual("deptid", geOperator.getDeptid());
	    if(!StringUtils.isBlank(groupid))queryRule.addEqual("groupid", groupid);
	    if(!StringUtils.isBlank(groupname))queryRule.addLike("groupname","%"+groupname+"%");
	    if(!StringUtils.isBlank(grouptypeid))queryRule.addEqual("grouptypeid",grouptypeid);
		List<GeGroup> geGroupList = geGroupService.findGeGroups(queryRule);
		
		if(geGroupList != null && geGroupList.size() > 0){
			for (GeGroup geGroup : geGroupList) {
				geGroup.setGeDeptGroupRoles(null);
				geGroup.setGeOperators(null);
			}
		}
		
		List<Object[]> geOperatorGroupList = new ArrayList<Object[]>(); 
		Object[] geObj;
		if(geGroupList!=null && geGroupList.size()>0){
			for (GeGroup geGroup : geGroupList) {
				if(containGeGroup(geGroup,oldGroups)){
					geObj = new Object[]{geGroup,"1"};
				}else{
					geObj = new Object[]{geGroup,"0"};
				}
				geOperatorGroupList.add(geObj);
			}
		}
		super.render(JsonBinder.buildNonNullBinder().toJson(geOperatorGroupList), "application/json;charset=utf-8");
		return NONE;
	}
	
	/**
	 * 为用户更新用户组信息
	 * @return
	 */
	public String updateGroupForUser(){
		String checkedUpdateGroups = super.getRequest().getParameter("checkedUpdateGroups");
		String allUpdateGroups = super.getRequest().getParameter("allUpdateGroups");
		String createUserId=super.getRequest().getParameter("userId");
		try {
			geGroupService.updateUserToGroups(createUserId, checkedUpdateGroups,allUpdateGroups);
			super.render(JsonBinder.buildNonNullBinder().toJson("success"), "application/json;charset=utf-8");
		} catch (Exception e) {
			super.render(JsonBinder.buildNonNullBinder().toJson("false"), "application/json;charset=utf-8");
		}
		return NONE;
	}
	
	/**
	 * 查询要修改的用户
	 * @return
	 */
	@LocusTrace(setCode="GeOperatorAction_queryGeOperatorForUpdate")
	public String queryGeOperatorForUpdate(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("operatorid",geOperator.getOperatorid());
			geOperator = geOperatorService.findGeOperator(queryRule);
			List<GeDepartment> geDepartments = geDepartmentService.findDepartmentByDepId(null,geOperator.getDeptid());
			
			StringBuffer cityOld = new StringBuffer("");
			if(geDepartments != null && geDepartments.size() > 0){
				for(int i=geDepartments.size()-1;i >= 0;i--){
					GeDepartment geDepartment = geDepartments.get(i);
					cityOld.append(geDepartment.getDeptname());
					if(i != 0){
						cityOld.append("/");
					}
				}
			}

			String authorityChoose = super.getRequest().getParameter("authorityid");
			Map map = (Map) super.getSession().getAttribute("permission");
			if(StringUtils.isBlank(authorityChoose)){
				authorityChoose = (String) map.get("ROLE_S_GROUP_M");
			}
			
			List<GeGroup> geGroupUserList = geOperatorService.findGroupId(geOperator.getOperatorid(),authorityChoose);

			
			GeOperator geOperator=(GeOperator) super.getSession().getAttribute("geOperator");

			/**找到该机构的用户组列表信息*/
	        QueryRule queryRule1 = QueryRule.getInstance();
		    queryRule1.addEqual("deptid", geOperator.getDeptid());
		    queryRule1.addAscOrder("groupid");
			List<GeGroup> geGroupList = geGroupService.findGeGroups(queryRule1);
			System.out.println(geGroupList);
			super.getRequest().setAttribute("geGroupList", geGroupList);
			
			super.getRequest().setAttribute("geGroupUserList", geGroupUserList);
			super.getRequest().setAttribute("createUserId",super.getRequest().getParameter("createUserId"));
			super.getRequest().setAttribute("cityOld", cityOld);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 查看用户详细
	 * @return
	 */
	@LocusTrace(setCode="GeOperatorAction_queryGeOperatorForDetail")
	public String queryGeOperatorForDetail(){
		try {
			Map map = (Map) super.getSession().getAttribute("permission");
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("operatorid",geOperator.getOperatorid());
			geOperator = geOperatorService.findGeOperator(queryRule);
			String groupUserAuthDeptId = (String) map.get("ROLE_S_GROUP_M");
			List groupOperators=geOperatorService.findGroupId(geOperator.getOperatorid(),groupUserAuthDeptId);
			List<GeDepartment> geDepartments = geDepartmentService.findDepartmentByDepId(null,geOperator.getDeptid());
			
			StringBuffer cityOld = new StringBuffer("");
			if(geDepartments != null && geDepartments.size() > 0){
				for(int i=geDepartments.size()-1;i >= 0;i--){
					GeDepartment geDepartment = geDepartments.get(i);
					cityOld.append(geDepartment.getDeptname());
					if(i != 0){
						cityOld.append("/");
					}
				}
			}
			super.getRequest().setAttribute("cityOld", cityOld);
			super.getRequest().setAttribute("groupOperators", groupOperators);
			Map areaMap = geCodeService.findAllCodeAndName(BUSINESSAREA);
			super.getRequest().setAttribute("areaMap", areaMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String findGroupByAuth() {
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("operatorid",geOperator.getOperatorid());
			geOperator = geOperatorService.findGeOperator(queryRule);
			String authorityChoose = super.getRequest().getParameter("authorityid");
			Map map = (Map) super.getSession().getAttribute("permission");
			if(StringUtils.isBlank(authorityChoose)){
				authorityChoose = (String) map.get("ROLE_S_GROUP_M");
			}
			Page page= geOperatorService
					.findGroupId(geOperator.getOperatorid(),authorityChoose,pageNo,pageSize);
			String pageStr = JsonBinder.buildNonNullBinder().toJson(page);
			super.render(pageStr, "application/json;charset=utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 修改用户
	 * @return
	 */
	@LocusTrace(setCode="GeOperatorAction_updateGeOperator")
	public String updateGeOperator(){
		try{
			geOperator.setCreatetime(new Date());
			if(geOperatorService.updates(geOperator)){
				message = "修改用户成功!";
				flag = 1;
			}else {
				message = "修改用户失败!";
			}
			
		if(flag==1 && !oldCheckGeGroup.equals(checkGeGroups)){
			String createUserId=geOperator.getOperatorid();//需添加用户组的用户id
			String groupsId=checkGeGroups;				   //用户需新加入的用户组
			String allGroupsIds=oldCheckGeGroup;		   //旧的用户组，需删除
			geGroupService.updateUserToGroups(createUserId,groupsId,allGroupsIds);
			message = "修改用户成功！";
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		message = "操作失败！";
	}
		return SUCCESS;
	}
	
	/**
	 * 分页查询用户
	 * @return
	 */
	@LocusTrace(setCode="GeOperatorAction_queryGeOperatorByDisPage")
	public String queryGeOperatorByDisPage(){
		QueryRule queryRule = QueryRule.getInstance();
		String operatorId = super.getRequest().getParameter("operatorId");
		String operatorName = super.getRequest().getParameter("operatorName");
		String authorityid = super.getRequest().getParameter("authorityid");
		if (!StringUtils.isBlank(operatorId)) {
			queryRule.addLike("operatorid", "%"+operatorId+"%");
		}
		if (!StringUtils.isBlank(operatorName)) {
			queryRule.addLike("operatorname", "%"+operatorName+"%");
		}
		//queryRule.addDescOrder("createtime");
		Map map = (Map) super.getSession().getAttribute("permission");
		//没有在页面选择机构权限，则从session中获取机构权限
		if(StringUtils.isBlank(authorityid)){
			authorityid = (String) map.get("ROLE_S_USER_M");
		}
		String sortId = super.getRequest().getParameter("sortId");
		String sortName = super.getRequest().getParameter("sortName");
		
		
		List<String> values = Arrays.asList(authorityid.split(","));
		String[] authorityIdAll = authorityid.split(",");
		if(values.size() >= 1000){
			String sql="(DEPTID in";
			if(authorityIdAll!=null && authorityIdAll.length > 0){
				int authorityIdSize = authorityIdAll.length;
				int authorityIdNumber = (authorityIdSize-1)/1000+1;
				String nextParentIdString = "";
				for(int j = 0; j < authorityIdNumber; j++){
					if(nextParentIdString.length()>0){
						 nextParentIdString = "";
						sql+="or deptid in";
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
			queryRule.addSql(sql);
		}else{
			queryRule.addIn("deptid", values);
		}
		
		String sql = " 1=1 ";
		
		if(StringUtils.isNotBlank(sortName)){
			if(sortName.equalsIgnoreCase("asc"))
				sql += " order by nlssort(operatorName,'NLS_SORT=SCHINESE_PINYIN_M') ASC";
			else
				sql += " order by nlssort(operatorName,'NLS_SORT=SCHINESE_PINYIN_M')  DESC";
			queryRule.addSql(sql);
		} else if(StringUtils.isNotBlank(sortId)){
			if(sortId.equalsIgnoreCase("asc"))
				queryRule.addAscOrder("operatorid");
			else
				queryRule.addDescOrder("operatorid");
		} else {
			queryRule.addDescOrder("createtime");
		}
		
		Page page = geOperatorService.find(queryRule, pageNo, pageSize);
		super.getRequest().setAttribute("geOperatorList", page.getResult());
		super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
		super.getRequest().setAttribute("pageSize", page.getPageSize());
		super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
		super.getRequest().setAttribute("totalCount", page.getTotalCount());
		
		Map departmentMap = geDepartmentService.findAllDepartMent();
		super.getRequest().setAttribute("departmentMap", departmentMap);
		
		Map areaMap = geCodeService.findAllCodeAndName(BUSINESSAREA);
		super.getRequest().setAttribute("areaMap", areaMap);
		return SUCCESS;
	}
	
	/**
	 * 删除多个用户
	 * @return
	 */
	@LocusTrace(setCode="GeOperatorAction_deleteGeOperator")
	public String deleteGeOperator(){
		try {
			String idStr = super.getRequest().getParameter("idStr");
			if(geOperatorService.delete(idStr)){
				message = "删除成功!";
				flag = 1;
			}else{
				message = "所删除用户正在使用中，不可以删除!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 修改查询列表中用户密码
	 * @return
	 */
	@LocusTrace(setCode="GeOperatorAction_updateGeOpPassword")
	public String updateOpPassword(){
		String operatorId = super.getRequest().getParameter("operatorid");
		String newPass = geCodeService.findCodeEName("04", "GeneralCode");
		if(StringUtils.isBlank(newPass)){
			super.render(JsonBinder.buildNonNullBinder().toJson(new Object[]{"0","数据字典暂未设置重置密码"}), "application/json;charset=utf-8");
		}
		if(geOperatorService.updatePass(operatorId, new Md5().toMD5(newPass))){
			super.render(JsonBinder.buildNonNullBinder().toJson(new Object[]{"1",newPass}), "application/json;charset=utf-8");
		}else{
			super.render(JsonBinder.buildNonNullBinder().toJson(new Object[]{"0",newPass}), "application/json;charset=utf-8");
		}
		return NONE;
	}
	
	/**
	 * 修改登陆用户密码
	 * @return
	 */
	@LocusTrace(setCode="GeOperatorAction_updatePassword")
	public String updatePassword(){
		String oldPassword = super.getRequest().getParameter("oldPassword");
		String newPassword = super.getRequest().getParameter("newPassword");
		String operatorid = super.getRequest().getParameter("operatorid");
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("pwd",new Md5().toMD5(oldPassword));
		queryRule.addEqual("operatorid",operatorid);
		GeOperator geOpera = geOperatorService.findGeOperator(queryRule);
		if(geOpera == null){
			message = "原密码输入有误";
		}else{
			geOpera.setPwd(new Md5().toMD5(newPassword));
			if(geOperatorService.updates(geOpera)){
				flag = 1;
				message = "密码修改成功";
			}else{
				message = "密码修改失败";
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 	用户管理机构树
	 */
	@LocusTrace(setCode="GeOperatorAction_operatorManageDeptTree")
	public void operatorManageDeptTree(){
		String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
		Map deptMap = (Map)super.getServletContext().getAttribute("orgTreeData");
		
		/**获取机构权限*/
		Map map = (Map) super.getSession().getAttribute("permission");
		String operatorDeptAuths = (String) map.get("ROLE_S_USER_I");
		
		String temp = geDeptGroupRoleService.getAllVirtualParent(operatorDeptAuths);
		operatorDeptAuths += "," + temp;
		
		StringBuffer str = new StringBuffer();
		str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		try {
			str.append("<tree id=\"" + treeID + "\">");
			operatorManageDeptItem(treeID, operatorDeptAuths,deptMap,str,"");
			str.append("</tree>");
			HttpServletResponse response = ServletActionContext.getResponse();  
			response.setContentType("text/xml; charset=GBK");  
		    response.setHeader("Cache-Control", "no-cache");  
		  
		    PrintWriter pw=response.getWriter();  
		    pw.write(str.toString());  
		    pw.flush();  
		    pw.close();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  
	
	/**
	 * 用户管理机构树-xml
	 * @param treeID
	 * @param area
	 * @param groupDeptAuths
	 * @return
	 */
	private String operatorManageDeptItem(String treeID,String operatorDeptAuths,Map deptMap,StringBuffer str,String deptName){
		List<GeDepartment> list =  (List)deptMap.get(treeID);

		/**虚拟本级节点*/
//		if(!"0".equals(treeID) && (","+operatorDeptAuths+",").indexOf(","+treeID+",") != -1){
//			str.append("<item id=\"" + treeID + "\" child=\"0\"  text=\""+deptName+"\"></item>");
//		}
		for (int i=0; i < list.size(); i++) {
			GeDepartment geDepartment = (GeDepartment)list.get(i);
			String deptIdString = geDepartment.getDeptid();
			String selfName = geDepartmentService.getSelfName(geDepartment);
			
			if(deptMap.containsKey(deptIdString)){
				if((","+operatorDeptAuths+",").indexOf(",parent_"+deptIdString+",") == -1)continue;/**判断是否拥有该机构权限*/
				if("0".equals(treeID)){
					str.append("<item id=\"parent_" + geDepartment.getDeptid() + "\" open=\"1\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
				}else{
					str.append("<item id=\"parent_" + geDepartment.getDeptid() + "\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
				}
				operatorManageDeptItem(deptIdString,operatorDeptAuths,deptMap,str,selfName);
			}else{
				if((","+operatorDeptAuths+",").indexOf(","+deptIdString+",") == -1)continue;/**判断是否拥有该机构权限*/
				str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"0\" text=\"" + geDepartment.getDeptname() + "\">");
			}
			str.append("</item>");
		}
		
		return str.toString();
	}
	
	public String geUserDept(){
		//添加用户，用于跳转选择机构的方法
		return SUCCESS;
	}
}
