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
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeRole;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeRoleAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeRoleAuthorityId;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeAuthorityService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDepartmentService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDeptGroupRoleService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeRoleService;
import cn.com.sinosoft.util.encode.JsonBinder;
import cn.com.sinosoft.util.io.PathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;

@SuppressWarnings("serial")
public class GeRoleAction extends Struts2Action {

	private GeRoleService geRoleService;
	private GeDepartmentService geDepartmentService;
	private GeDeptGroupRoleService geDeptGroupRoleService;
	private GeAuthorityService geAuthorityService;
	private GeCodeService geCodeService;
	private GeRole geRole;
	private GeOperator geOperator;
	private String message="";
	private int flag;/**1-�ɹ�*/
	/***����**/
	private static final String BUSINESSAREA = "BusinessArea";
	
	static {
		PropertyFileUtils.init(PathUtil.getClassBuildPath() + "/config/companyInfo.properties");
	}
	
	private static String companyName = PropertyFileUtils.getConfig("companyName");
	
	public GeDeptGroupRoleService getGeDeptGroupRoleService() {
		return geDeptGroupRoleService;
	}

	public void setGeDeptGroupRoleService(
			GeDeptGroupRoleService geDeptGroupRoleService) {
		this.geDeptGroupRoleService = geDeptGroupRoleService;
	}

	public GeDepartmentService getGeDepartmentService() {
		return geDepartmentService;
	}

	public void setGeDepartmentService(GeDepartmentService geDepartmentService) {
		this.geDepartmentService = geDepartmentService;
	}

	public GeOperator getGeOperator() {
		return geOperator;
	}

	public void setGeOperator(GeOperator geOperator) {
		this.geOperator = geOperator;
	}
	
	public GeAuthorityService getGeAuthorityService() {
		return geAuthorityService;
	}

	public void setGeAuthorityService(GeAuthorityService geAuthorityService) {
		this.geAuthorityService = geAuthorityService;
	}

	public GeCodeService getGeCodeService() {
		return geCodeService;
	}

	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}

	public GeRole getGeRole() {
		return geRole;
	}

	public void setGeRole(GeRole geRole) {
		this.geRole = geRole;
	}

	public GeRoleService getGeRoleService() {
		return geRoleService;
	}

	public void setGeRoleService(GeRoleService geRoleService) {
		this.geRoleService = geRoleService;
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
	/**��ѯ��ɫid�Ƿ��ظ�
	 * 
	 */
	public void getRolebyId(){
		Map<String, String> resultMap = new HashMap<String, String>();
		if(geRoleService.exists(geRole.getRoleID())){
			resultMap.put("resultFlag", "success");
		}else{
			resultMap.put("resultFlag", "error");
		}
	
		JSONObject jsonObject = JSONObject.fromObject(resultMap);
		renderText(jsonObject.toString());
	}
	/**
	 * �½���ɫ
	 * @return
	 */
	
	@LocusTrace(setCode="GeRoleAction_createGeRole")
	public String createGeRole(){
		String authoritys = super.getRequest().getParameter("authoritys");
		geOperator=(GeOperator)super.getSession().getAttribute("geOperator");
		if(geRoleService.exists(geRole.getRoleID())){
			message = "�ý�ɫ����Ѵ��ڣ�";
		}else{
			if(!StringUtils.isBlank(authoritys)){
				String[] str = authoritys.split(",");
				List<GeRoleAuthority> geRoleAuthorities = new ArrayList<GeRoleAuthority>();
				for(int i = 0; i < str.length; i++){
					GeRoleAuthority eRoleAuthority = new GeRoleAuthority();
					GeRoleAuthorityId eRoleAuthorityId = new GeRoleAuthorityId();
					eRoleAuthorityId.setAuthorityID(str[i]);
					eRoleAuthorityId.setRoleID(geRole.getRoleID());
					eRoleAuthority.setId(eRoleAuthorityId);
					geRoleAuthorities.add(eRoleAuthority);
				}
				geRole.setGeRoleAuthorities(geRoleAuthorities);
			}
			geRole.setDeptID(geOperator.getDeptid());
			geRole.setCreatetime(new Date()); //�������ݿ⵱ǰʱ��
			if(geRoleService.save(geRole)){
				flag = 1;
				message = "�½���ɫ�ɹ�";
			}else{		
				message = "�½���ɫʧ�ܣ�";
			}
		}
		return SUCCESS;
	}
	
	/**
	 * ��ѯҪ�޸ĵĽ�ɫ
	 * @return
	 */
	@LocusTrace(setCode="GeRoleAction_queryGeRoleForUpdate")
	public String queryGeRoleForUpdate(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("roleID", geRole.getRoleID());
			geRole = geRoleService.findGeRole(queryRule);
			
			//List<Integer> groupidlist = geRoleService.findRoleGroupID(geRole.getRoleID());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * �޸Ľ�ɫ
	 * @return
	 */
	@LocusTrace(setCode="GeRoleAction_updateGeRole")
	public String updateGeRole(){
		String authoritys = super.getRequest().getParameter("authoritys");
		geOperator=(GeOperator)super.getSession().getAttribute("geOperator");
		if(!StringUtils.isBlank(authoritys)){
			String[] str = authoritys.split(",");
			List<GeRoleAuthority> geRoleAuthorities = new ArrayList<GeRoleAuthority>();
			for(int i = 0; i < str.length; i++){
				GeRoleAuthority eRoleAuthority = new GeRoleAuthority();
				GeRoleAuthorityId eRoleAuthorityId = new GeRoleAuthorityId();
				eRoleAuthorityId.setAuthorityID(str[i]);
				eRoleAuthorityId.setRoleID(geRole.getRoleID());
				eRoleAuthority.setId(eRoleAuthorityId);
				geRoleAuthorities.add(eRoleAuthority);
			}
			geRole.setGeRoleAuthorities(geRoleAuthorities);
		}
		geRole.setDeptID(geOperator.getDeptid());
		geRole.setCreatetime(new Date()); //�޸�ʱ����ʱ��
		if (geRoleService.updates(geRole)) {
			message = "�޸Ľ�ɫ�ɹ�!";
			flag = 1;
		}else{
			message = "�޸Ľ�ɫʧ��!";
		}
		return SUCCESS;
	}
	
	/**
	 * ��ҳ��ѯ��ɫ
	 * @return
	 */
	@LocusTrace(setCode="GeRoleAction_queryGeRoleByDisPage")
	public String queryGeRoleByDisPage(){
		QueryRule queryRule = QueryRule.getInstance();
		String roleid = super.getRequest().getParameter("roleid");
		String rolename = super.getRequest().getParameter("rolename");
		String authorityid = super.getRequest().getParameter("authorityid");
		if (!StringUtils.isBlank(roleid)) {
			queryRule.addLike("roleID", "%"+roleid+"%");
		}
		if (!StringUtils.isBlank(rolename)) {
			queryRule.addLike("roleName", "%"+rolename+"%");
		}
		String sortId = super.getRequest().getParameter("sortId");
		String sortName = super.getRequest().getParameter("sortName");
		if(!StringUtils.isBlank(sortName)){
			if(sortName.equalsIgnoreCase("asc"))
				queryRule.addAscOrder("nlssort('roleName','NLS_SORT=SCHINESE_PINYIN_M')");
			else
				queryRule.addDescOrder("nlssort('roleName','NLS_SORT=SCHINESE_PINYIN_M')");
		}
		if(!StringUtils.isBlank(sortId)){
			if(sortId.equalsIgnoreCase("asc"))
				queryRule.addAscOrder("roleID");
			else
				queryRule.addDescOrder("roleID");
		}
		
		queryRule.addDescOrder("createtime");
		Map map = (Map) super.getSession().getAttribute("permission");
		//û����ҳ��ѡ�����Ȩ�ޣ����session�л�ȡ����Ȩ��
		if(StringUtils.isBlank(authorityid)){
			authorityid = (String) map.get("ROLE_S_ROLE_M");
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
			queryRule.addIn("deptID", values);
		}
		Page page = geRoleService.find(queryRule, pageNo, pageSize);
		super.getRequest().setAttribute("geRoleList", page.getResult());
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
	 * ɾ������û�
	 * @return
	 */
	@LocusTrace(setCode="GeRoleAction_deleteGeRole")
	public String deleteGeRole(){
		String idStr = super.getRequest().getParameter("idStr");
		if(geRoleService.delete(idStr)){
			message = "ɾ����ɫ�ɹ�!";
			flag = 1;
		}else{
			message = "��ɾ����ɫ����ʹ����,������ɾ��!";
		}
		return SUCCESS;
	}
	/**
	 * �鿴��ɫ����Ӧ����
	 */
	@LocusTrace(setCode="GeRoleAction_detail")
	public String detail(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("roleID", geRole.getRoleID());
			geRole = geRoleService.findGeRole(queryRule);
			Map map = (Map) super.getSession().getAttribute("permission");
		    String authorityid = (String) map.get("ROLE_S_GROUP_M");
			List groupRoles = geRoleService.findRoleGroupID(geRole.getRoleID(),authorityid);
			super.getRequest().setAttribute("groupRoles", groupRoles);
			Map areaMap = geCodeService.findAllCodeAndName(BUSINESSAREA);
			super.getRequest().setAttribute("areaMap", areaMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String findGroupByAuth(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			String roleId = super.getRequest().getParameter("roleId");
			String groupRoleAuthDeptId = super.getRequest().getParameter("authorityid");
			Map map = (Map) super.getSession().getAttribute("permission");
			if(StringUtils.isBlank(groupRoleAuthDeptId)){
				groupRoleAuthDeptId = (String) map.get("ROLE_S_ROLE_M");
			}
			Page page= geRoleService
					.findAuthorityRroup(groupRoleAuthDeptId,roleId,pageNo,pageSize);
			String pageStr = JsonBinder.buildNonNullBinder().toJson(page);
			super.render(pageStr, "application/json;charset=utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	
	/**
	 * ��ɫȨ����Ϣ
	 * һ���Լ���Ȩ��������ѡ��/����ӵ��Ȩ�ޣ�
	 */
	@LocusTrace(setCode="GeRoleAction_findRoleAuthority")
	public void findRoleAuthority() {
		String roleID = super.getRequest().getParameter("roleID");// ��ɫid
		
		/**��ѯ��ɫӵ�е�Ȩ��*/
		List<GeAuthority> rolsAuthoritys = new ArrayList<GeAuthority>();
		if(!StringUtils.isBlank(roleID)){
			rolsAuthoritys = geAuthorityService.findRoleAuthoritys(roleID);
		} 
		
		/**�������ֵ��ȡϵͳ���͵�map*/
		Map systemTypeMap = (Map)super.getSession().getAttribute("SystemType");
		if(systemTypeMap == null){
			systemTypeMap = geCodeService.findAllCodeAndName("SystemType");
			super.getSession().setAttribute("SystemType", systemTypeMap);
		}
		
		Map permission = (Map) super.getSession().getAttribute("permission");
		
		StringBuilder str = new StringBuilder();
		str.append("<?xml version=\"1.0\" encoding=\"GBK\" ?>\n<tree id='0'>\n");
		str.append("<item id=\"ROOT\" child=\"1\"  open=\"1\" nocheckbox=\"1\" text=\""+companyName+"Ȩ��\">");
		
		geOperator=(GeOperator)super.getSession().getAttribute("geOperator");
		List systype = geAuthorityService.findSystemType(permission.keySet());
		for (int i = 0; i < systype.size(); i++) {
			String codecodeString = (String)systype.get(i);
			str.append("<item id=\"" + codecodeString+ "\" child=\"1\" checkbox=\"1\" text=\""+ systemTypeMap.get(codecodeString) + "\">");
			findRoleAuthorityItems("ROOT", str, rolsAuthoritys,codecodeString,permission);
			str.append("</item>");
		}
		
		str.append("</item></tree>");
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/xml; charset=GBK");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter pw = response.getWriter();
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
	 * ��ɫȨ����Ϣ
	 * һ���Լ���Ȩ��������ѡ��/����ӵ��Ȩ�ޣ�
	 */
	private void findRoleAuthorityItems(String treeID, StringBuilder str,List roleAuthoritys, String systype,Map permission) {
		/**��ȡ���ڵ��µ�ֱ���ӽڵ�---�л���*/
		Map authorityMap=(Map)super.getServletContext().getAttribute("AuthorityTreeData");
		List<GeAuthority> geAuthorityList =(List<GeAuthority>) authorityMap.get(treeID);

		for (int i = 0; i < geAuthorityList.size(); i++) {
			GeAuthority geAuthority = (GeAuthority) geAuthorityList.get(i);
			if(permission != null){
				if (!permission.containsKey(geAuthority.getAuthorityID()))continue;
			}
			if(!geAuthority.getSystype().equals(systype))continue;
			if (authorityMap.containsKey(geAuthority.getAuthorityID())) {
				str.append("<item id=\"" + geAuthority.getAuthorityID()+ "\" child=\"1\" text=\""+ geAuthority.getAuthorityName() + "\">");
				findRoleAuthorityItems(geAuthority.getAuthorityID(), str,roleAuthoritys, systype,permission);
			} else {
				String checked = roleAuthoritys.contains(geAuthority.getAuthorityID()) ? " checked=\"1\" " : "";
				str.append("<item id=\"" + geAuthority.getAuthorityID()+ "\" child=\"0\" " + checked + " text=\""+ geAuthority.getAuthorityName() + "\">");
			}
			str.append("</item>\n");
		}
	}
	
	/**
	 * ��ɫȨ����ϸ��Ϣ
	 * һ���Լ���Ȩ����������ӵ��Ȩ�ޣ�
	 */
	@LocusTrace(setCode="GeRoleAction_findRoleAuthorityDetail")
	public void findRoleAuthorityDetail() {
		String roleID = super.getRequest().getParameter("roleID");// ��ɫid
		
		/**��ѯ��ɫӵ�е�Ȩ��*/
		List<GeAuthority> rolsAuthoritys = new ArrayList<GeAuthority>();
		if(!StringUtils.isBlank(roleID)){
			rolsAuthoritys = geAuthorityService.findRoleAuthoritys(roleID);
		}
		
		/**�������ֵ��ȡϵͳ���͵�map*/
		Map systemTypeMap = (Map)super.getSession().getAttribute("SystemType");
		if(systemTypeMap == null){
			systemTypeMap = geCodeService.findAllCodeAndName("SystemType");
			super.getSession().setAttribute("SystemType", systemTypeMap);
		}
		/**��������ӵ�е�ϵͳ*/
		List systype = geAuthorityService.findByGeCodeType(rolsAuthoritys);
		
		Map permission = (Map) super.getSession().getAttribute("permission");
		
		StringBuilder str = new StringBuilder();
		str.append("<?xml version=\"1.0\" encoding=\"GBK\" ?>\n<tree id='0'>\n");
		str.append("<item id=\"ROOT\" child=\"1\"  open=\"1\" nocheckbox=\"1\" text=\""+companyName+"Ȩ��\">");
		
		for (int i = 0; i < systype.size(); i++) {
			String codecodeString = (String)systype.get(i);
			str.append("<item id=\"" + codecodeString+ "\" child=\"1\" checkbox=\"1\" text=\""+ systemTypeMap.get(codecodeString) + "\">");
			findRoleAuthorityDetailItem("ROOT", str, rolsAuthoritys,codecodeString,permission);
			str.append("</item>");
		}
		str.append("</item></tree>");
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/xml; charset=GBK");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter pw = response.getWriter();
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
	 * ��ɫȨ����ϸ��Ϣ
	 * һ���Լ���Ȩ����������ӵ��Ȩ�ޣ�
	 */
	private void findRoleAuthorityDetailItem(String treeID, StringBuilder str,List roleAuthoritys, String systype,Map permission) {
		/**��ȡ���ڵ��µ�ֱ���ӽڵ�---�л���*/
		Map authorityMap=(Map)super.getServletContext().getAttribute("AuthorityTreeData");
		List<GeAuthority> geAuthorityList =(List<GeAuthority>) authorityMap.get(treeID);
		
		for (int i = 0; i < geAuthorityList.size(); i++) {
			GeAuthority geAuthority = (GeAuthority) geAuthorityList.get(i);
			if(permission != null){
				//if (!permission.containsKey(geAuthority.getAuthorityID()))continue;
			}
			if(!geAuthority.getSystype().equals(systype))continue;
			if(roleAuthoritys.contains(geAuthority.getAuthorityID())){
				if (authorityMap.containsKey(geAuthority.getAuthorityID())) {
					str.append("<item id=\"" + geAuthority.getAuthorityID()+ "\" child=\"1\" text=\""+ geAuthority.getAuthorityName() + "\">");
					findRoleAuthorityDetailItem(geAuthority.getAuthorityID(), str,roleAuthoritys, systype,permission);
					str.append("</item>\n");
				} else {
					str.append("<item id=\"" + geAuthority.getAuthorityID()+ "\" child=\"0\"  text=\""+ geAuthority.getAuthorityName() + "\"></item>\n");
				}
			}
		}
	}
}
