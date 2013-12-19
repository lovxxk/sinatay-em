package cn.com.sinosoft.ebusiness.mis.system.authorityManage.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeRole;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDepartmentService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDeptGroupRoleService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeGroupService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeGroupTypeService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeOperatorService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeRoleService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.PermissionFactoryService;
import cn.com.sinosoft.util.encode.JsonBinder;

@SuppressWarnings("serial")
public class GroupAction extends Struts2Action {
	
	/***常量**/
	private static final String BUSINESSAREA = "BusinessArea";
	
	private GeDepartmentService geDepartmentService;
	private GeGroupService geGroupService;
	private String roleid;
	private String deptidSave;

	private GeGroupTypeService geGroupTypeService;
	private GeCodeService geCodeService;/**数据字典服务类*/
	private GeDeptGroupRoleService geDeptGroupRoleService;
	private PermissionFactoryService permissionFactoryService;
	private List<GeOperator> geOperatorsList;
	private List<GeGrouptype> geGrouptypeList;
	private GeRoleService geRoleService;
	
	private GeOperatorService geOperatorService;
	private GeGroup geGroup;
	private String message = "";
	private int flag;
	private GeGrouptype geGrouptype;
	
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getDeptidSave() {
		return deptidSave;
	}

	public void setDeptidSave(String deptidSave) {
		this.deptidSave = deptidSave;
	}
	
	public GeGroupService getGeGroupService() {
		return geGroupService;
	}

	public GeDeptGroupRoleService getGeDeptGroupRoleService() {
		return geDeptGroupRoleService;
	}
	
	public GeRoleService getGeRoleService() {
		return geRoleService;
	}

	public void setGeRoleService(GeRoleService geRoleService) {
		this.geRoleService = geRoleService;
	}

	public List<GeGrouptype> getGeGrouptypeList() {
		return geGrouptypeList;
	}

	public void setGeGrouptypeList(List<GeGrouptype> geGrouptypeList) {
		this.geGrouptypeList = geGrouptypeList;
	}

	public GeGrouptype getGeGrouptype() {
		return geGrouptype;
	}

	public GeGroupTypeService getGeGroupTypeService() {
		return geGroupTypeService;
	}

	public void setGeGroupTypeService(GeGroupTypeService geGroupTypeService) {
		this.geGroupTypeService = geGroupTypeService;
	}

	public void setGeGrouptype(GeGrouptype geGrouptype) {
		this.geGrouptype = geGrouptype;
	}

	public GeDepartmentService getGeDepartmentService() {
		return geDepartmentService;
	}

	public void setGeDepartmentService(GeDepartmentService geDepartmentService) {
		this.geDepartmentService = geDepartmentService;
	}
	
	public PermissionFactoryService getPermissionFactoryService() {
		return permissionFactoryService;
	}

	public void setPermissionFactoryService(
			PermissionFactoryService permissionFactoryService) {
		this.permissionFactoryService = permissionFactoryService;
	}

	public GeGroup getGeGroup() {
		return geGroup;
	}

	public void setGeGroup(GeGroup geGroup) {
		this.geGroup = geGroup;
	}


	public GeOperatorService getGeOperatorService() {
		return geOperatorService;
	}

	public void setGeOperatorService(GeOperatorService geOperatorService) {
		this.geOperatorService = geOperatorService;
	}

	public List<GeOperator> getGeOperatorsList() {
		return geOperatorsList;
	}

	public void setGeOperatorsList(List<GeOperator> geOperatorsList) {
		this.geOperatorsList = geOperatorsList;
	}

	public void setGeDeptGroupRoleService(
			GeDeptGroupRoleService geDeptGroupRoleService) {
		this.geDeptGroupRoleService = geDeptGroupRoleService;
	}

	public void setGeGroupService(GeGroupService geGroupService) {
		this.geGroupService = geGroupService;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void clearMessage() {
		this.message = "";
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public GeCodeService getGeCodeService() {
		return geCodeService;
	}

	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}

	/**
	 * 新建用户组
	 * @return
	 */
	public void geGroupById(){

		Map<String, String> resultMap = new HashMap<String, String>();
		if(geGroupService.exists(geGroup.getGroupid())){
			resultMap.put("resultFlag", "success");
		}else{
			resultMap.put("resultFlag", "error");
		}
	
		JSONObject jsonObject = JSONObject.fromObject(resultMap);
		renderText(jsonObject.toString());
	}

	@LocusTrace(setCode="GroupAction_createGeGroup")
	public String createGeGroup(){
		Map map = (Map) super.getSession().getAttribute("permission");
		String operatorDeptAuths = (String) map.get("ROLE_S_GROUP_M");
		try {
			if(geGroupService.exists(geGroup.getGroupid())){
				message = "该用户组编号已存在！";
			}else{	
				geGroup.setCreatetime(new Date());
				geGroupService.save(geGroup);		//创建用户组
				flag = 1;
				message = "用户组创建成功！";
			}
			//将角色添加到组
			if(flag==1&& !StringUtils.isBlank(roleid)&& !StringUtils.isBlank(deptidSave)){
				geDeptGroupRoleService.saveGeGroupRoleDept(geGroup.getGroupid(),roleid,deptidSave,operatorDeptAuths);
				//flag = 1;
				message = "用户组创建成功！";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			message = "操作失败！";
		}
		super.getRequest().setAttribute("message", message);
		return SUCCESS;
	}
	
	/**
	 * 新建用户组类型
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_createType")
	public String createType(){
			if(geGroupTypeService.saveType(geGrouptype)){
				message = "新建用户组类型成功！";
				flag = 1;
			}else{
				message = "用户组创建类型失败！";
			}
		return SUCCESS;
	}

	/**
	 * 分页查询用户组
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_queryGeGroupByDisPage")
	public String queryGeGroupByDisPage(){
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		if (!StringUtils.isBlank(geGroup.getGroupid())) {
			queryRule.addLike("groupid", "%"+geGroup.getGroupid()+"%");// 增加groupid的查询条件
		}
		if (!StringUtils.isBlank(geGroup.getGroupname())) {
			queryRule.addLike("groupname", "%"+geGroup.getGroupname()+"%");// 增加groupname的查询条件
		}
		if (!StringUtils.isBlank(geGroup.getGrouptypeid())) {
			queryRule.addEqual("grouptypeid", geGroup.getGrouptypeid());// 增加grouptypeid的查询条件
		}
		String authorityid = super.getRequest().getParameter("authorityid");
		Map map = (Map) super.getSession().getAttribute("permission");
		//没有在页面选择机构权限，则从session中获取机构权限
		if(StringUtils.isBlank(authorityid)){
			authorityid = (String) map.get("ROLE_S_GROUP_M");
		}
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
		
		String sortId = super.getRequest().getParameter("sortId");
		String sortName = super.getRequest().getParameter("sortName");
		if(StringUtils.isNotBlank(sortName)){
			String sql = " 1=1 ";
			if(sortName.equalsIgnoreCase("asc"))
				sql += " order by nlssort(groupname,'NLS_SORT=SCHINESE_PINYIN_M') ASC";
			else
				sql += " order by nlssort(groupname,'NLS_SORT=SCHINESE_PINYIN_M')  DESC";
			queryRule.addSql(sql);
		} else if(StringUtils.isNotBlank(sortId)){
			if(sortId.equalsIgnoreCase("asc"))
				queryRule.addAscOrder("groupid");
			else
				queryRule.addDescOrder("groupid");
		} else {
			queryRule.addDescOrder("createtime");
		}
		
		Page geGroupPage = geGroupService.findGeGroup(queryRule, pageNo, pageSize);
		super.getRequest().setAttribute("geGroupList", geGroupPage.getResult());
		super.getRequest().setAttribute("pageNo", geGroupPage.getCurrentPageNo());
		super.getRequest().setAttribute("pageSize", geGroupPage.getPageSize());
		super.getRequest().setAttribute("totalPage", geGroupPage.getTotalPageCount());
		super.getRequest().setAttribute("totalCount", geGroupPage.getTotalCount());
		
		Map departmentMap = geDepartmentService.findAllDepartMent();
		super.getRequest().setAttribute("departmentMap", departmentMap);
		
		Map areaMap = geCodeService.findAllCodeAndName(BUSINESSAREA);
		super.getRequest().setAttribute("areaMap", areaMap);
		
		return SUCCESS;
	}
	
	/**
	 * 分页查询用户组类型
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_queryGeGroupTypeByDisPage")
	public String queryGeGroupTypeByDisPage(){
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		if (!StringUtils.isBlank(geGrouptype.getGrouptypename())) {
			queryRule.addLike("grouptypename", "%"+geGrouptype.getGrouptypename()+"%");// 增加grouptypename的查询条件
		}
		queryRule.addEqual("grouptypedeptid", super.getRequest().getParameter("deptid"));
				
		Page geGroupTypePage = geGroupTypeService.findGeGroupType(queryRule, pageNo, pageSize);
		super.getRequest().setAttribute("geGroupTypeList", geGroupTypePage.getResult());
		super.getRequest().setAttribute("pageNo", geGroupTypePage.getCurrentPageNo());
		super.getRequest().setAttribute("pageSize", geGroupTypePage.getPageSize());
		super.getRequest().setAttribute("totalPage", geGroupTypePage.getTotalPageCount());
		super.getRequest().setAttribute("totalCount", geGroupTypePage.getTotalCount());
		
		
		
		return SUCCESS;
	}
	
	/**
	 * 查询要修改的用户组信息
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_queryGeGroupForUpdate")
	public String queryGeGroupForUpdate(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("groupid", super.getRequest().getParameter("id"));
			geGroup= geGroupService.findGeGroup(queryRule);
			QueryRule queryRule2 = QueryRule.getInstance();
			queryRule2.addEqual("grouptypedeptid", super.getRequest().getParameter("deptid"));
			geGrouptypeList = geGroupTypeService.findGeGroupTypes(queryRule2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 预创建用户组
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_preCreate")
	public String preCreate(){
		try {
			GeOperator operator = (GeOperator) super.getSession().getAttribute("geOperator");
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("grouptypedeptid",operator.getDeptid());
			geGrouptypeList = geGroupTypeService.findGeGroupTypes(queryRule);
			getAllRolesByDept();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 预查询用户组
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_preSearch")
	public String preSearch(){
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
	
	/**
	 * 查询要修改的用户组类型信息
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_queryGeGroupTypeForUpdate")
	public String queryGeGroupTypeForUpdate(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("grouptypeid", super.getRequest().getParameter("id"));
			geGrouptype= geGroupTypeService.findGeGroupType(queryRule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询用户组详细
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_queryGeGroupDetail")
	public String queryGeGroupDetail(){
		try {
			Map map = (Map) super.getSession().getAttribute("permission");
			String groupid = super.getRequest().getParameter("id");
			
			/**查询组基本信息*/
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("groupid", groupid);
			geGroup= geGroupService.findGeGroup(queryRule);
			
			/**查询组用户信息*/
			String groupUserAuthDeptId = (String) map.get("ROLE_S_USER_M");
			List<Map<String,String>> operators = geGroupService.findGeGroupOperator(groupid,groupUserAuthDeptId);
			super.getRequest().setAttribute("geGroupOperators", operators);
			Map areaMap = geCodeService.findAllCodeAndName(BUSINESSAREA);
			super.getRequest().setAttribute("areaMap", areaMap);
			
			/**查询组角色信息*/
			String groupRoleAuthDeptId = (String) map.get("ROLE_S_ROLE_M");
			List<Map<String,String>> roleAllTemp = geGroupService.findAllRolesAndChecked(groupid,groupRoleAuthDeptId);
			List<Map<String,String>> roleExist = new ArrayList<Map<String,String>>();
			if(roleAllTemp!=null){
				for(int i = 0; i < roleAllTemp.size(); i++){
					if("0".equals(roleAllTemp.get(i).get("STATUS"))){
						roleExist.add(roleAllTemp.get(i));
					}
				}
			}
			super.getRequest().setAttribute("roleExist", roleExist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String roleByGroupDisPage(){
		try {
			Map map = (Map) super.getSession().getAttribute("permission");
			
			/**查询组角色信息*/
			String groupRoleAuthDeptId = super.getRequest().getParameter("authorityid");
			String groupid = super.getRequest().getParameter("groupid");
			if(StringUtils.isBlank(groupRoleAuthDeptId)){
				groupRoleAuthDeptId = (String) map.get("ROLE_S_ROLE_M");
			}
			Page page = geGroupService.findGroupRoles(groupid,groupRoleAuthDeptId,pageNo,pageSize);
			String pageStr = JsonBinder.buildNonNullBinder().toJson(page);
			super.render(pageStr, "application/json;charset=utf-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String roleByGroup(){
		try {
			Map map = (Map) super.getSession().getAttribute("permission");
			
			/**查询组角色信息*/
			String groupRoleAuthDeptId = (String) map.get("ROLE_S_ROLE_M");
			String groupid = super.getRequest().getParameter("groupid");
			List<GeRole> geRoleList = geGroupService.findAllGroupRoles(groupid,groupRoleAuthDeptId);
			String roleListStr = JsonBinder.buildNonNullBinder().toJson(geRoleList);
			super.render(roleListStr, "application/json;charset=utf-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String userByGroup(){
		try {
			Map map = (Map) super.getSession().getAttribute("permission");
			/**查询组用户信息*/
			String groupUserAuthDeptId = super.getRequest().getParameter("authorityid");
			String groupid = super.getRequest().getParameter("groupid");
			if(StringUtils.isBlank(groupUserAuthDeptId)){
				groupUserAuthDeptId = (String) map.get("ROLE_S_USER_M");
			}
			Page page = geGroupService.findGeGroupOperator(groupid,groupUserAuthDeptId,pageNo,pageSize);
			String pageStr = JsonBinder.buildNonNullBinder().toJson(page);
			super.render(pageStr, "application/json;charset=utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	/**
	 * 更新用户组
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_updateGeGroup")
	public String updateGeGroup(){
		try {
			Date currentTime=new Date();//获取系统当前时间
			geGroup.setCreatetime(currentTime);
			geGroupService.updatesGroup(geGroup);
			message = "修改用户组信息成功!";
			flag = 1;
		} catch (Exception e) {
			message="修改用户组信息失败";
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	/**
	 * 更新用户组类型
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_updateType")
	public String updateType(){
		try {
			geGroupTypeService.updateType(geGrouptype);
			message = "修改用户组类型成功!";
			flag = 1;
		} catch (Exception e) {
			message="修改用户组类型失败";
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 删除用户组信息
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_deleteGeGroup")
	public String deleteGeGroup(){
		try {
			String idStr = super.getRequest().getParameter("idStr");
			int a =geGroupService.delete(idStr);
			if(a==1){
				message = "该组已分配人员,不可以删除!";
			}else if(a==2){
				message = "该组已分配机构,不可以删除!";
			}else if(a==4){
				message = "该组已分配人员和机构,不可以删除!";
			}else{
				flag = 1;
				message = "删除用户组成功!";
			}
		} catch (Exception e) {
			message = "删除组异常";
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除用户组类型
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_deleteType")
	public String deleteType(){
		try {
			String typeid = super.getRequest().getParameter("typeid");
			int a =geGroupTypeService.delete(typeid);
			if(a==1){
				message = "该组类型正在被使用,不可以删除!";
			}else{
				flag = 1;
				message = "删除用户组类型成功!";
			}
		} catch (Exception e) {
			message = "删除组类型异常";
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 进入设置组内用户界面（查询组内所有的用户）
	 * 注：用户设置
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_queryGeGroupOperatorsForUpdate")
	public String queryGeGroupOperatorsForUpdate(){
		try {
			String groupid = super.getRequest().getParameter("id");
			Map areaMap = geCodeService.findAllCodeAndName(BUSINESSAREA);
			super.getRequest().setAttribute("groupid", groupid);
			super.getRequest().setAttribute("areaMap", areaMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询机构deptid下所有用户以及在用户组groupid下的用户
	 * 注：用户设置
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_queryGeGroupDeptOperators")
	public String queryGeGroupDeptOperators(){
		try {
			String groupid = super.getRequest().getParameter("groupid");
			String deptid = super.getRequest().getParameter("deptid");
			List<Map<String,String>> listOperators=geGroupService.findGeGroupDeptOperator(groupid, deptid);
			String geOperators = JsonBinder.buildNonNullBinder().toJson(listOperators);
			super.render(geOperators, "application/json;charset=utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 修改的用户组用户信息
	 * 注：用户设置
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_updateGeGroupOperators")
	public String updateGeGroupOperators(){
		try {
			try {
				String groupid = super.getRequest().getParameter("groupid");
				String selectCity = super.getRequest().getParameter("selectCity");
				String updateOperator = super.getRequest().getParameter("updateOperator");
				geGroupService.updateGeGroupGeOperators(groupid, selectCity, updateOperator);
				String message = JsonBinder.buildNonNullBinder().toJson("success");
				super.render(message, "application/json;charset=utf-8");
			} catch (Exception e) {
				e.printStackTrace();
				String message = JsonBinder.buildNonNullBinder().toJson("保存失败");
				super.render(message, "application/json;charset=utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 查询用户组角色
	 * 注：用户组权限设置
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_queryGeGroupAuthorityForUpdate")
	public String queryGeGroupAuthorityForUpdate(){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("groupid", super.getRequest().getParameter("id"));
		geGroup= geGroupService.findGeGroup(queryRule);
		
		
		QueryRule queryRule2 = QueryRule.getInstance();
		queryRule2.addEqual("grouptypedeptid", super.getRequest().getParameter("deptid"));
		geGrouptypeList = geGroupTypeService.findGeGroupTypes(queryRule2);
		
		String groupid = super.getRequest().getParameter("id");
		String odeptid = super.getRequest().getParameter("odeptid");
		try {
			List<Map<String,String>> roleAllTemp = geGroupService.findRolesAndChecked(groupid,odeptid);
			
			List<Map<String,String>> roleAll = new ArrayList<Map<String,String>>();
			List<Map<String,String>> roleExist = new ArrayList<Map<String,String>>();
			if(roleAllTemp!=null){
				for(int i = 0; i < roleAllTemp.size(); i++){
					if("0".equals(roleAllTemp.get(i).get("STATUS"))){
						roleExist.add(roleAllTemp.get(i));
					}
					if(true){
						roleAll.add(roleAllTemp.get(i));
					}
				}
			}
			
			String oldDeptids = geDeptGroupRoleService.findDeptIds(groupid);
			
			super.getRequest().setAttribute("groupid", groupid);
			super.getRequest().setAttribute("roleAll", roleAll);
			super.getRequest().setAttribute("roleExist", roleExist);
			super.getRequest().setAttribute("oldDeptids", oldDeptids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getAllRolesByDept(){
		GeOperator geOperator=(GeOperator) super.getSession().getAttribute("geOperator");
        QueryRule queryRule = QueryRule.getInstance();
	    queryRule.addEqual("deptID",geOperator.getDeptid());
	    queryRule.addAscOrder("roleID");
		List<GeRole> geRoleList = geRoleService.findGeRoles(queryRule);
		
		QueryRule queryRule2 = QueryRule.getInstance();
		queryRule2.addEqual("grouptypedeptid",geOperator.getDeptid());
		geGrouptypeList = geGroupTypeService.findGeGroupTypes(queryRule2);
		
		super.getRequest().setAttribute("geRoleList", geRoleList);

		return SUCCESS;
	}
	/**
	 * 保存用户组-角色-机构信息
	 * 注：用户组权限设置
	 * @return
	 */
	@LocusTrace(setCode="GroupAction_updateGroupRoleDept")
	public String updateGroupRoleDept(){
		String groupId = super.getRequest().getParameter("groupid");
		String roleId = super.getRequest().getParameter("roleid");
		String deptidSave = super.getRequest().getParameter("deptidSave");
		Map map = (Map) super.getSession().getAttribute("permission");
		String operatorDeptAuths = (String) map.get("ROLE_S_GROUP_M");
		
		String groupname = super.getRequest().getParameter("groupname");
		String groupdesp = super.getRequest().getParameter("groupdesp");
		String grouptypename = super.getRequest().getParameter("grouptypename"); 
		try {
			groupname = java.net.URLDecoder.decode(groupname,"UTF-8");
			groupdesp = java.net.URLDecoder.decode(groupdesp,"UTF-8");
			grouptypename = java.net.URLDecoder.decode(grouptypename,"UTF-8");
			geGroup.setGroupname(groupname);
			geGroup.setGroupdesp(groupdesp);
			geGroup.setGrouptypename(grouptypename);
			geGroup.setUpdatetime(new Date());
			boolean gl= geGroupService.updatesGroup(geGroup);
			boolean bl = geDeptGroupRoleService.saveGeGroupRoleDept(groupId, roleId,deptidSave,operatorDeptAuths);
			String pageStr = "";
			if(bl&&gl){
				pageStr = JsonBinder.buildNonNullBinder().toJson("success");
			}else{
				pageStr = JsonBinder.buildNonNullBinder().toJson("操作失败");
			}
			super.render(pageStr, "application/json;charset=utf-8");
		} catch (Exception e) {
			String pageStr = JsonBinder.buildNonNullBinder().toJson("操作失败！");
			super.render(pageStr, "application/json;charset=utf-8");
		}
		return NONE;
	}
	
	public String deleteUserFromGroup(){
		String groupId = super.getRequest().getParameter("groupId");
		String operatorId = super.getRequest().getParameter("operatorId");
		try {
			boolean bl =geGroupService.deleteUserFromGroup(operatorId,groupId);
			String pageStr = "";
			if(bl){
				pageStr = JsonBinder.buildNonNullBinder().toJson("success");
			}else{
				pageStr = JsonBinder.buildNonNullBinder().toJson("删除用户组用户失败");
			}
			super.render(pageStr, "application/json;charset=utf-8");
		} catch (Exception e) {
			String pageStr = JsonBinder.buildNonNullBinder().toJson("删除用户组用户发生异常！");
			super.render(pageStr, "application/json;charset=utf-8");
		}
		return NONE;
	}
	
	/**
	 * 查询组机构权限详细树
	 */
	@LocusTrace(setCode="GroupAction_groupDeptAuthDetailTree")
	public void groupDeptAuthDetailTree(){
		String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
		String groupId = super.getRequest().getParameter("groupId")== null?"":super.getRequest().getParameter("groupId").trim();
		
		/**查询组拥有的机构权限*/
		String groupDeptAuths = geDeptGroupRoleService.findGroupDeptAuth(groupId);
		
		String temp = geDeptGroupRoleService.getAllVirtualParent(groupDeptAuths);
		
		groupDeptAuths += "," + temp;
		/**机构树MAP*/
		Map map=(Map)super.getServletContext().getAttribute("orgTreeData");
		
		StringBuffer str = new StringBuffer();
		str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		try {
			str.append("<tree id=\"" + treeID + "\">");
			groupDeptAuthDetailItem(str,treeID,groupDeptAuths,map,"");
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
	 * 查询组机构权限详细树-xml
	 * @param treeID
	 * @param area
	 * @param groupDeptAuths
	 * @return
	 */
	private void groupDeptAuthDetailItem(StringBuffer str,String treeID,String groupDeptAuths,Map map,String deptName){
		List<GeDepartment> list =(List<GeDepartment>) map.get(treeID);
		if(list != null && list.size() > 0){
			/**虚拟本级节点*/
//			if(!"0".equals(treeID) && (","+groupDeptAuths+",").indexOf(","+treeID+",") != -1){
//				str.append("<item id=\"" + treeID + "\" child=\"0\"  text=\""+deptName+"\"></item>");
//			}
			for (int i=0; i < list.size(); i++) {
				GeDepartment geDepartment = (GeDepartment)list.get(i);
				String selfName = geDepartmentService.getSelfName(geDepartment);
				
				if(map.containsKey(geDepartment.getDeptid())){
					if((","+groupDeptAuths+",").indexOf(",parent_"+geDepartment.getDeptid()+",") == -1)continue;/**判断是否拥有该机构权限*/
					if("0".equals(treeID)){
						str.append("<item id=\"" + geDepartment.getDeptid() + "\" open=\"1\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
					}else{
						str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
					}
					groupDeptAuthDetailItem(str,geDepartment.getDeptid(),groupDeptAuths,map,selfName);
				}else{
					if((","+groupDeptAuths+",").indexOf(","+geDepartment.getDeptid()+",") == -1)continue;/**判断是否拥有该机构权限*/
					str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"0\" text=\"" + geDepartment.getDeptname() + "\">");
				}
				str.append("</item>");
			}
			
		}
	}
	
	/**
	 * 用户设置-机构权限树
	 */
	@LocusTrace(setCode="GroupAction_operatorSetDeptAuthTree")
	public void operatorSetDeptAuthTree(){
		String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
		
		/**获取机构权限*/
		Map map = (Map) super.getSession().getAttribute("permission");
		String operatorDeptAuths = (String) map.get("ROLE_S_GROUP_M");
		String temp = geDeptGroupRoleService.getAllVirtualParent(operatorDeptAuths);
		operatorDeptAuths += "," + temp;
		
		Map orgMap=(Map)super.getServletContext().getAttribute("orgTreeData");
		
		StringBuffer str = new StringBuffer();
		str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		try {
			str.append("<tree id=\"" + treeID + "\">");
			operatorSetDeptAuthItem(str,treeID, operatorDeptAuths,orgMap,"");
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
	 * 用户设置-机构权限树-xml
	 * @param treeID
	 * @param area
	 * @param groupDeptAuths
	 * @return
	 */
	private String operatorSetDeptAuthItem(StringBuffer str,String treeID,String operatorDeptAuths,Map orgMap,String deptName){
		List<GeDepartment> list =(List<GeDepartment>) orgMap.get(treeID);
		if(list != null && list.size() > 0){
			/**虚拟本级节点*/
//			if(!"0".equals(treeID) && (","+operatorDeptAuths+",").indexOf(","+treeID+",") != -1){
//				str.append("<item id=\"" + treeID + "\" child=\"0\"  text=\""+deptName+"\"></item>");
//			}
			for (int i=0; i < list.size(); i++) {
				GeDepartment geDepartment = (GeDepartment)list.get(i);
				String selfName = geDepartmentService.getSelfName(geDepartment);
				
				if(orgMap.containsKey(geDepartment.getDeptid())){
					if((","+operatorDeptAuths+",").indexOf(",parent_"+geDepartment.getDeptid()+",") == -1)continue;/**判断是否拥有该机构权限*/
					if("0".equals(treeID)){
						str.append("<item id=\"parent_" + geDepartment.getDeptid() + "\" open=\"1\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
					}else{
						str.append("<item id=\"parent_" + geDepartment.getDeptid() + "\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
					}
					operatorSetDeptAuthItem(str,geDepartment.getDeptid(),operatorDeptAuths,orgMap,selfName);
				}else{
					if((","+operatorDeptAuths+",").indexOf(","+geDepartment.getDeptid()+",") == -1)continue;/**判断是否拥有该机构权限*/
					str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"0\" text=\"" + geDepartment.getDeptname() + "\">");
				}
				str.append("</item>");
			}
			
		}
		return str.toString();
	}
	
	/**
	 * 权限设置-机构权限树
	 */
	public void authoritySetDeptAuthTree(){
		String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
		String groupId = super.getRequest().getParameter("groupId")== null?"":super.getRequest().getParameter("groupId").trim();
		/**获取机构权限*/
		Map map = (Map) super.getSession().getAttribute("permission");
		String operatorDeptAuths = (String) map.get("ROLE_S_GROUP_M");
		
		/**查询组拥有的机构权限*/
		String groupDeptAuths = geDeptGroupRoleService.findGroupDeptAuth(groupId);
		String temp = geDeptGroupRoleService.getAllVirtualParent(operatorDeptAuths);
		operatorDeptAuths += "," + temp;
		
		Map orgMap=(Map)super.getServletContext().getAttribute("orgTreeData");
		
		StringBuffer str = new StringBuffer();
		str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		try {
			str.append("<tree id=\"" + treeID + "\">");
			authoritySetDeptAuthItem(str,treeID, operatorDeptAuths, groupDeptAuths,orgMap,"");
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
	 * 权限设置-机构权限树-xml
	 * @param treeID
	 * @param area
	 * @param groupDeptAuths
	 * @return
	 */
	private void authoritySetDeptAuthItem(StringBuffer str,String treeID,String operatorDeptAuths,String groupDeptAuths,Map orgMap,String deptName){
		List<GeDepartment> list =(List<GeDepartment>) orgMap.get(treeID);
		if(list != null && list.size() > 0){
			/**虚拟本级节点*/
//			if(!"0".equals(treeID) && (","+operatorDeptAuths+",").indexOf(","+treeID+",") != -1){
//				String checked = "";/**判断是否选中*/
//				if(!StringUtils.isBlank(groupDeptAuths)){
//					if((","+groupDeptAuths+",").indexOf(","+treeID+",")!= -1)checked ="checked=\"1\"";
//				}
//				str.append("<item id=\"" + treeID + "\" child=\"0\"  "+checked+" text=\""+deptName+"\"></item>");
//			}
			for (int i=0; i < list.size(); i++) {
				GeDepartment geDepartment = (GeDepartment)list.get(i);
				String selfName = geDepartmentService.getSelfName(geDepartment);
				String deptIdString = geDepartment.getDeptid();
				
				/**判断是否选中*/
				String checked = "";
				if(!StringUtils.isBlank(groupDeptAuths)){
					if((","+groupDeptAuths+",").indexOf(","+deptIdString+",")!= -1)checked ="checked=\"1\"";
				}
				if(orgMap.containsKey(deptIdString)){
					if((","+operatorDeptAuths+",").indexOf(",parent_"+deptIdString+",") == -1)continue;/**判断是否拥有该机构权限*/
					if("0".equals(treeID)){
						str.append("<item id=\"parent_" + geDepartment.getDeptid() + "\" open=\"1\" child=\"1\"  text=\"" + geDepartment.getDeptname() + "\">");
					}else{
						str.append("<item id=\"parent_" + geDepartment.getDeptid() + "\" child=\"1\"  text=\"" + geDepartment.getDeptname() + "\">");
					}
					authoritySetDeptAuthItem(str,geDepartment.getDeptid(),operatorDeptAuths,groupDeptAuths,orgMap,selfName);
				}else{
					if((","+operatorDeptAuths+",").indexOf(","+deptIdString+",") == -1)continue;/**判断是否拥有该机构权限*/
					str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"0\" "+checked+" text=\"" + geDepartment.getDeptname() + "\">");
				}
				str.append("</item>");
			}
			
		}
	}
}
