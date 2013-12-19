package cn.com.sinosoft.ebusiness.mis.system.authorityManage.web;

import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeAreaAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeAreaService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDepartmentService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDeptGroupRoleService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.PermissionFactoryService;
import cn.com.sinosoft.util.encode.JsonBinder;
import cn.com.sinosoft.util.io.PathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;

@SuppressWarnings("serial")
public class GeDepartmentAction  extends Struts2Action{
	private GeDepartmentService geDepartmentService;
	private GeCodeService geCodeService;/**数据字典服务类*/
	private PermissionFactoryService permissionFactoryService;
	private GeDeptGroupRoleService geDeptGroupRoleService;
	private GeDepartment geDepartment;
	private List<GeDepartment> deptList;
	private String message = "";
	private int flag;
	private String isHidden;
	private String city;
	private String cityName;
	private String province;
	private String provinceName;
	
	static {
		PropertyFileUtils.init(PathUtil.getClassBuildPath() + "/config/companyInfo.properties");
	}
	
	private static String companyName = PropertyFileUtils.getConfig("companyName");
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	
	public String getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}
	/***常量**/
	private static final String MEMBERCOMPANY = "MemberCompany";
	private static final String BUSINESSAREA = "BusinessArea";
	private static final String AREATOCOMPANY = "AreaToCompany";
	/**
	 * @param geDepartmentService the geDepartmentService to set
	 */
	public void setGeDepartmentService(GeDepartmentService geDepartmentService) {
		this.geDepartmentService = geDepartmentService;
	}
	
	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}



	/**
	 * @return the deptList
	 */
	public List<GeDepartment> getDeptList() {
		return deptList;
	}

	/**
	 * @param deptList the deptList to set
	 */
	public void setDeptList(List<GeDepartment> deptList) {
		this.deptList = deptList;
	}

	public void setPermissionFactoryService(
			PermissionFactoryService permissionFactoryService) {
		this.permissionFactoryService = permissionFactoryService;
	}

	public void setGeDeptGroupRoleService(
			GeDeptGroupRoleService geDeptGroupRoleService) {
		this.geDeptGroupRoleService = geDeptGroupRoleService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public GeDepartment getGeDepartment() {
		return geDepartment;
	}

	public void setGeDepartment(GeDepartment geDepartment) {
		this.geDepartment = geDepartment;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	private GeAreaAuthority geArea;
	private GeAreaService geAreaService;
	public GeAreaAuthority getGeArea() {
		return geArea;
	}
	public void setGeArea(GeAreaAuthority geArea) {
		this.geArea = geArea;
	}
	public GeAreaService getGeAreaService() {
		return geAreaService;
	}
	public void setGeAreaService(GeAreaService geAreaService) {
		this.geAreaService = geAreaService;
	}
	
	
	/**
	 * 查询机构信息用于展示
	 * @return
	 */
	@LocusTrace(setCode="GeDepartmentAction_findGeDepartmentInfo")
	public String findGeDepartmentInfo(){
		try {
			geDepartment =  geDepartmentService.findGeDepartmentByPK(geDepartment.getDeptid());
			/**查询数据字典的业务领域*/
			List businessAreaList = geCodeService.findAllByGeCodeType(BUSINESSAREA);
			for(int i = 0; i < businessAreaList.size(); i++){
				GeCode geCode = (GeCode)businessAreaList.get(i);
				String codeCode = geCode.getId().getCodeCode();
				if(geDepartment.getBusinessarea().equals(codeCode)){
					super.getRequest().setAttribute("depAreaName", geCode.getCodeCName());
					
					/**根据业务领域找到对应的成员单位信息*/
					String depttype = geCodeService.findCodeCName(codeCode,AREATOCOMPANY);
					String memberCompanyCNName = geCodeService.findCodeCName(depttype,MEMBERCOMPANY);
					super.getRequest().setAttribute("depTypeName",memberCompanyCNName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 进入到新建机构界面
	 * @return
	 */
	@LocusTrace(setCode="GeDepartmentAction_frmCreateGeDepartment")
	public String frmCreateGeDepartment(){
		try {
			String parentId = geDepartment.getParentid().replace("area", "");
			String area = parentId.substring(0,1);
			super.getRequest().setAttribute("area", area);
			
			/**查询数据字典的业务领域*/
			List businessAreaList = geCodeService.findAllByGeCodeType(BUSINESSAREA);
			for(int i = 0; i < businessAreaList.size(); i++){
				GeCode geCode = (GeCode)businessAreaList.get(i);
				String codeCode = geCode.getId().getCodeCode();
				if(area.equals(codeCode)){
					geDepartment.setBusinessarea(area);
					super.getRequest().setAttribute("depAreaName", geCode.getCodeCName());
					
					/**根据业务领域找到对应的成员单位信息*/
					String depttype = geCodeService.findCodeCName(codeCode,AREATOCOMPANY);
					String memberCompanyCNName = geCodeService.findCodeCName(depttype,MEMBERCOMPANY);
					geDepartment.setDepttype(depttype);
					super.getRequest().setAttribute("depTypeName",memberCompanyCNName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	@LocusTrace(setCode="GeDepartmentAction_findGeAreaActionTree")
	public void findGeAreaActionTree() {

		String treeID = super.getRequest().getParameter("id") == null ? ""
				: super.getRequest().getParameter("id").trim();
		StringBuffer str = new StringBuffer();
		str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		try {
			str.append("<tree id=\"" + treeID + "\">");
			if ("0".equals(treeID)) {
				str.append(getItemsForArea("0"));
			} else {
				str.append(getItemsForArea(treeID));
			}
			str.append("</tree>");
			System.out.println(str);
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

	private String getItemsForArea(String treeID) {
		StringBuffer str = new StringBuffer();
		QueryRule queryRule = QueryRule.getInstance();
		if (!StringUtils.isBlank(treeID)) queryRule.addEqual("pgid", treeID);
		queryRule.addAscOrder("gid");
		List list = geAreaService.findGeAreas(queryRule);
		for (int i = 0; i < list.size(); i++) {
			GeAreaAuthority geAreaAuthority1 = (GeAreaAuthority) list.get(i);

			List childList = geAreaService.getChildList(geAreaAuthority1.getGid());
			/** 找到节点下所有子节点 */
			if (childList.size() > 0) {
					str.append("<item id=\"" + geAreaAuthority1.getGid()
							+ "\" child=\"1\" text=\""
							+ geAreaAuthority1.getGname() + "\">");
				
			} else {
					str.append("<item id=\"" + geAreaAuthority1.getGid()
							+ "\" child=\"0\" text=\""
							+ geAreaAuthority1.getGname() + "\">");
			}
			str.append("<userdata name=\"ud_block\">ud_data</userdata>");
			str.append("</item>");
		}
		return str.toString();
	}
	
	/**
	 * 新建机构
	 * @return
	 */
	@LocusTrace(setCode="GeDepartmentAction_createGeDepartment")
	public String createGeDepartment(){
		try {
			if(geDepartmentService.exists(geDepartment.getDeptid())){
				message = "该机构已经存在";
			}else{
				if(geDepartment.getParentid().startsWith("area"))geDepartment.setParentid("1");
				if(geDepartmentService.save(geDepartment)){
					String parentIdString = geDepartment.getParentid();
					Map map=(Map)super.getServletContext().getAttribute("orgTreeData");
					List geDeptList = (List)map.get(parentIdString);
					if(geDeptList == null)geDeptList = new ArrayList();
					geDeptList.add(geDepartment);
					map.put(geDepartment.getParentid(), geDeptList);
					message = "新建机构成功";
					flag = 1;
				}else{
					message = "新建机构失败";
				}
			}
		} catch (Exception e) {
			message = "新建机构失败";
		}
		return SUCCESS;
	}
	
	/**
	 * 获取修改的机构信息
	 * @return
	 */
	@LocusTrace(setCode="GeDepartmentAction_findGeDepartmentForUpdate")
	public String findGeDepartmentForUpdate() {
		try {
			String parentId = geDepartment.getParentid().replace("area", "");
			String area = parentId.substring(0,1);
			super.getRequest().setAttribute("area", area);
			
			geDepartment =  geDepartmentService.findGeDepartmentByPK(geDepartment.getDeptid());
			super.getRequest().setAttribute("geDepartment", geDepartment);
			
			/**查询数据字典的业务领域*/
			List businessAreaList = geCodeService.findAllByGeCodeType(BUSINESSAREA);
			for(int i = 0; i < businessAreaList.size(); i++){
				GeCode geCode = (GeCode)businessAreaList.get(i);
				String codeCode = geCode.getId().getCodeCode();
				if(geDepartment.getBusinessarea().equals(codeCode)){
					super.getRequest().setAttribute("depAreaName", geCode.getCodeCName());
					
					/**根据业务领域找到对应的成员单位信息*/
					String depttype = geCodeService.findCodeCName(codeCode,AREATOCOMPANY);
					String memberCompanyCNName = geCodeService.findCodeCName(depttype,MEMBERCOMPANY);
					super.getRequest().setAttribute("depTypeName",memberCompanyCNName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 更新机构信息
	 * @return
	 */
	@LocusTrace(setCode="GeDepartmentAction_updatesGeDepartment")
	public String updatesGeDepartment(){
		try {
			GeDepartment obj = new GeDepartment();
			if(geDepartmentService.updates(geDepartment)){
				Map map=(Map)super.getServletContext().getAttribute("orgTreeData");
				List geDeptList = (List)map.get(geDepartment.getParentid());
				String deptIdString = geDepartment.getDeptid();
				for (int i = 0; i < geDeptList.size(); i++) {
					GeDepartment geDept =(GeDepartment)geDeptList.get(i);
					if(deptIdString.equals(geDept.getDeptid())){
						geDeptList.remove(i);
						geDeptList.add(i, geDepartment);
						break;
					}
				}
				flag = 1;
				message = "机构修改成功";
			}else{
				message = "机构修改失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除机构
	 * @return
	 */
	@LocusTrace(setCode="GeDepartmentAction_deleteGeDepartment")
	public String deleteGeDepartment(){
		try {
			if(geDepartmentService.hasChild(geDepartment.getDeptid())){
				message = "该机构下存在子机构，请先删除其子机构";
			}else if(geDepartmentService.hasOperator(geDepartment.getDeptid())){
				message = "该机构下存在用户，请先删除机构下用户";
			}else if(geDepartmentService.hasDeptGroupRole(geDepartment.getDeptid())){
				message = "该机构被角色机构权限使用，请先从角色机构权限中移除该机构";
			}else{
				geDepartment = geDepartmentService.findGeDepartmentByPK(geDepartment.getDeptid());
				if(geDepartmentService.delete(geDepartment)){
					String deptIdString = geDepartment.getDeptid();
					String parentIdString = geDepartment.getParentid();
					Map map=(Map)super.getServletContext().getAttribute("orgTreeData");
					List geDeptList = (List)map.get(parentIdString);
					for (int i = 0; i < geDeptList.size(); i++) {
						GeDepartment geDept =(GeDepartment)geDeptList.get(i);
						if(deptIdString.equals(geDept.getDeptid())){
							geDeptList.remove(i);
							if(geDeptList.size() == 0){
								map.remove(parentIdString);
							}
							break;
						}
					}
					message = "机构删除成功";
					flag = 1;
				}else{
					message = "删除机构失败";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "机构可能被使用，导致删除操作发生异常";
		}
		return SUCCESS;
	}
	
	/**
	 * 获取机构树
	 * 注：没有复选框
	 * 该树特点：1.异步加载 2.增加了四个领域 ：集团、寿险、财险、养老险
	 * id:要查询的节点的ID
	 * deptID：正在操作的功能的权限id
	 * isFilterAuthority：是否过滤没有权限的机构 0-不过滤，1-过滤
	 */
	@LocusTrace(setCode="GeDepartmentAction_findGeDepartmentTreeUnChecked")
	public void findGeDepartmentTreeUnChecked(){
		String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
		String deptID = super.getRequest().getParameter("deptID")== null?"":super.getRequest().getParameter("deptID").trim();
		String isFilterAuthority = super.getRequest().getParameter("isFilterAuthority")== null?"0":super.getRequest().getParameter("isFilterAuthority").trim();
		String isValidateLastCity = super.getRequest().getParameter("isValidateLastCity")== null?"":super.getRequest().getParameter("isValidateLastCity");
		Map map = (Map) super.getSession().getAttribute("permission");
		String deptStrings = (String) map.get(deptID);
		
		//String temp = geDepartmentService.findParentIds(deptStrings);
		//deptStrings += "," + temp;
		List<String> deptList = permissionFactoryService.transformStringToList(deptStrings);
		if(deptStrings != null){
			StringBuffer str = new StringBuffer();
			str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			try {
				str.append("<tree id=\"" + treeID + "\">");
				if("0".equals(treeID)){
					str.append(getItemsForNoCheckBox(isFilterAuthority,"0","", deptList,isValidateLastCity));
				}else{
					str.append(getItemsForNoCheckBox(isFilterAuthority,treeID,"", deptList,isValidateLastCity));
				}
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
	}  

	/**
	 * 获取机构树
	 * 注：有复选框
	 * 该树特点：1.异步加载 2.增加了四个领域 ：集团、寿险、财险、养老险
	 * id:要查询的节点的ID
	 * deptID：正在操作的功能的权限id
	 * isFilterAuthority：是否过滤没有权限的机构 0-不过滤，1-过滤
	 * receivedObj：原始权限
	 * isValidateLastCity是否校验叶子节点
	 */
	@LocusTrace(setCode="GeDepartmentAction_findGeDepartmentTreeWithChecked")
	public void findGeDepartmentTreeWithChecked(){
		String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
		String deptID = super.getRequest().getParameter("deptID")== null?"":super.getRequest().getParameter("deptID").trim();
		String isFilterAuthority = super.getRequest().getParameter("isFilterAuthority")== null?"0":super.getRequest().getParameter("isFilterAuthority").trim();
		String isValidateLastCity = super.getRequest().getParameter("isValidateLastCity")== null?"":super.getRequest().getParameter("isValidateLastCity");
		String receivedObj = super.getRequest().getParameter("receivedObj")==null?"":super.getRequest().getParameter("receivedObj").trim();//权限id
		List<String> deptStrings = permissionFactoryService.findDeptAuthId(deptID, super.getSession());
		if(deptStrings != null){
			StringBuffer str = new StringBuffer();
			str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			try {
				str.append("<tree id=\"" + treeID + "\">");
				if("0".equals(treeID)){
					str.append("<item id=\"1\" child=\"1\" 	nocheckbox=\"1\" open=\"1\" text=\""+companyName+"系统\">");
					if(isAreaShow(isFilterAuthority,"1",deptStrings)){
						str.append("<item id=\"area1\" child=\"1\" 	nocheckbox=\"1\" text=\"集团\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"2",deptStrings)){
						str.append("<item id=\"area2\" child=\"1\" nocheckbox=\"1\" text=\"寿险\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"3",deptStrings)){
						str.append("<item id=\"area3\" child=\"1\" nocheckbox=\"1\" text=\"财险\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"4",deptStrings)){
						str.append("<item id=\"area4\" child=\"1\" nocheckbox=\"1\" text=\"养老险\"></item>");
					}
					str.append("</item>");
				}else if(treeID.startsWith("area")){
					str.append(getItemsForCheckBox(isFilterAuthority,"1", treeID.substring(treeID.length()-1), deptStrings,receivedObj,isValidateLastCity));
				}else{
					str.append(getItemsForCheckBox(isFilterAuthority,treeID,treeID.substring(0,1), deptStrings,receivedObj,isValidateLastCity));
				}
				
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
	}
	
	/**
	 * 根据组ID和角色ID查询用户组-角色-机构信息
	 * 注：该方法特别为用户组权限设置准备(带复选框)
	 * 该树特点：1.异步加载 2.增加了四个领域 ：集团、寿险、财险、养老险
	 * id:要查询的节点的ID
	 * deptID：正在操作的功能的权限id
	 * isFilterAuthority：是否过滤没有权限的机构 0-不过滤，1-过滤
	 * receivedObj：原始权限
	 * isValidateLastCity是否校验叶子节点
	 */
	@LocusTrace(setCode="GeDepartmentAction_findGeDepartmentTreeForGroupRoleDep")
	public void findGeDepartmentTreeForGroupRoleDep(){
		String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
		String deptID = super.getRequest().getParameter("deptID")== null?"":super.getRequest().getParameter("deptID").trim();
		String isFilterAuthority = super.getRequest().getParameter("isFilterAuthority")== null?"0":super.getRequest().getParameter("isFilterAuthority").trim();
		String isValidateLastCity = super.getRequest().getParameter("isValidateLastCity")== null?"":super.getRequest().getParameter("isValidateLastCity");
		
		/**获取xml*/
		List<String> deptStrings = permissionFactoryService.findDeptAuthId(deptID, super.getSession()); 
		if(deptStrings != null){
			StringBuffer str = new StringBuffer();
			str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			try {
				str.append("<tree id=\"" + treeID + "\">");
				if("0".equals(treeID)){
					str.append("<item id=\"1\" child=\"1\" 	nocheckbox=\"1\" open=\"1\" text=\""+companyName+"系统\">");
					if(isAreaShow(isFilterAuthority,"1",deptStrings)){
						str.append("<item id=\"area1\" child=\"1\" 	nocheckbox=\"1\" text=\"集团\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"2",deptStrings)){
						str.append("<item id=\"area2\" child=\"1\" nocheckbox=\"1\" text=\"寿险\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"3",deptStrings)){
						str.append("<item id=\"area3\" child=\"1\" nocheckbox=\"1\" text=\"财险\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"4",deptStrings)){
						str.append("<item id=\"area4\" child=\"1\" nocheckbox=\"1\" text=\"养老险\"></item>");
					}
					str.append("</item>");
				}else {
					/**获取已有机构*/
					String groupid = super.getRequest().getParameter("groupid") == null?"":super.getRequest().getParameter("groupid").trim();
					String roleId = super.getRequest().getParameter("roleId")== null?"":super.getRequest().getParameter("roleId").trim();
					String receivedObj = geDeptGroupRoleService.findGeDeptGroupRoleStr(groupid, roleId);
					
					if(treeID.startsWith("area")){
						str.append(getItemsForCheckBox(isFilterAuthority,"1", treeID.substring(treeID.length()-1), deptStrings,receivedObj,isValidateLastCity));
					}else{
						str.append(getItemsForCheckBox(isFilterAuthority,treeID,treeID.substring(0,1), deptStrings,receivedObj,isValidateLastCity));
					}
				}
				
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
	}
	
	/**
	 * 根据组ID查询用户组-角色-机构信息
	 * 注：该方法特别为用户组权限设置准备(带复选框)
	 * 该树特点：1.异步加载 2.增加了四个领域 ：集团、寿险、财险、养老险
	 * id:要查询的节点的ID
	 * deptID：正在操作的功能的权限id
	 * isFilterAuthority：是否过滤没有权限的机构 0-不过滤，1-过滤
	 * receivedObj：原始权限
	 * isValidateLastCity是否校验叶子节点
	 */
	@LocusTrace(setCode="GeDepartmentAction_findGeDepartmentTreeForGroupRoleDepByGroupId")
	public void findGeDepartmentTreeForGroupRoleDepByGroupId(){
		String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
		String authorityid = super.getRequest().getParameter("authorityid")== null?"":super.getRequest().getParameter("authorityid").trim();
		String isFilterAuthority = super.getRequest().getParameter("isFilterAuthority")== null?"0":super.getRequest().getParameter("isFilterAuthority").trim();
		String isValidateLastCity = super.getRequest().getParameter("isValidateLastCity")== null?"":super.getRequest().getParameter("isValidateLastCity");
		/**获取xml*/
		List<String> deptStrings = permissionFactoryService.findDeptAuthId(authorityid, super.getSession()); 
		if(deptStrings != null){
			StringBuffer str = new StringBuffer();
			str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			try {
				str.append("<tree id=\"" + treeID + "\">");
				if("0".equals(treeID)){
					str.append("<item id=\"1\" child=\"1\" 	nocheckbox=\"1\" open=\"1\" text=\""+companyName+"系统\">");
					if(isAreaShow(isFilterAuthority,"1",deptStrings)){
						str.append("<item id=\"area1\" child=\"1\" 	nocheckbox=\"1\" text=\"集团\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"2",deptStrings)){
						str.append("<item id=\"area2\" child=\"1\" nocheckbox=\"1\" text=\"寿险\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"3",deptStrings)){
						str.append("<item id=\"area3\" child=\"1\" nocheckbox=\"1\" text=\"财险\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"4",deptStrings)){
						str.append("<item id=\"area4\" child=\"1\" nocheckbox=\"1\" text=\"养老险\"></item>");
					}
					str.append("</item>");
				}else {
					/**获取已有机构*/
					String groupid = super.getRequest().getParameter("groupid") == null?"":super.getRequest().getParameter("groupid").trim();
					String receivedObj = geDeptGroupRoleService.findGeDeptGroupRoleStr(groupid);
					
					if(treeID.startsWith("area")){
						str.append(getItemsForCheckBox(isFilterAuthority,"1", treeID.substring(treeID.length()-1), deptStrings,receivedObj,isValidateLastCity));
					}else{
						str.append(getItemsForCheckBox(isFilterAuthority,treeID,treeID.substring(0,1), deptStrings,receivedObj,isValidateLastCity));
					}
				}
				
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
	}
	
	/**
	 * 根据领域获取节点的子节点的xml--该方法为没有复选框树准备
	 * @param isFilterAuthority 是否进行机构权限过滤 0-不过滤 1-过滤
	 * @param treeID 要查询的父节点ID
	 * @param area	领域
	 * @param deptStrings 登录用户拥有的机构权限集合
	 * @return
	 */
	private String getItemsForNoCheckBox(String isFilterAuthority,String treeID,String area,List deptStrings,String isValidateLastCity){
		StringBuffer str = new StringBuffer();
		QueryRule queryRule = QueryRule.getInstance();
		if(!StringUtils.isBlank(treeID))queryRule.addEqual("parentid", treeID);
		if(!StringUtils.isBlank(area))queryRule.addEqual("businessarea", area);
		queryRule.addAscOrder("deptid");
		List<GeDepartment> list =  geDepartmentService.findAllDepartments(queryRule);
		for (int i=0; i < list.size(); i++) {
			GeDepartment geDepartment = (GeDepartment)list.get(i);
			
			List childList = geDepartmentService.getChildList(geDepartment.getDeptid());/**找到节点下所有子节点*/
			if(childList.size() > 0){
				if(!isExistAuthorityDepartment(isFilterAuthority,childList,deptStrings))continue;/**判断节点下是否有权限的节点*/
				str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
			}else{
				/**判断是否拥有机构权限*/
				if("1".equals(isFilterAuthority)&&!deptStrings.contains(geDepartment.getDeptid()))continue;
				str.append("<item id=\""+isValidateLastCity+"" + geDepartment.getDeptid() + "\" child=\"0\" text=\"" + geDepartment.getDeptname() + "\">");
			}
			str.append("<userdata name=\"ud_block\">ud_data</userdata>");
			str.append("</item>");
		}
		return str.toString();
	}
	
	/**
	 * 该方法为用户组设置机构权限服务
	 * 获取节点的子节点的xml(该方法为复选框树准备)
	 * @param treeID 要查询的父节点ID
	 * @param area	领域
	 * @param deptStrings 登录用户拥有的机构权限集合
	 * @param receivedObj 原来已经选中的值的集合
	 * @param isLastChild 是否区分叶子节点（city---区分叶子节点）
	 * @return
	 */
	private String getItemsForCheckBox(String isFilterAuthority,String treeID,String area,List deptStrings,String receivedObj,String isValidateLastCity){
		StringBuffer str = new StringBuffer();
		QueryRule queryRule = QueryRule.getInstance();
		if(!StringUtils.isBlank(treeID))queryRule.addEqual("parentid", treeID);
		if(!StringUtils.isBlank(area))queryRule.addEqual("businessarea", area);
		queryRule.addAscOrder("deptid");
		List<GeDepartment> list =  geDepartmentService.findAllDepartments(queryRule);
		for (int i=0; i < list.size(); i++) {
			GeDepartment geDepartment = (GeDepartment)list.get(i);
			
			/**判断是否拥有机构权限*/
			if("1".equals(isFilterAuthority)&&!deptStrings.contains(geDepartment.getDeptid()))continue;
			String checked = "";
			if(!StringUtils.isBlank(receivedObj)){
				if((","+receivedObj+",").indexOf(","+geDepartment.getDeptid()+",")!= -1)checked ="checked=\"1\"";
			}
			
			List childList = geDepartmentService.getChildList(geDepartment.getDeptid());/**找到节点下所有子节点*/
			if(childList.size() > 0){
				//if(!isExistAuthorityDepartment(isFilterAuthority,childList,deptStrings))continue;/**判断节点下是否有权限的节点*/
				str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"1\"  "+checked+" text=\"" + geDepartment.getDeptname() + "\">");
			}else{
				str.append("<item id=\""+isValidateLastCity+"" + geDepartment.getDeptid() + "\" child=\"0\" "+checked+" text=\"" + geDepartment.getDeptname() + "\">");
			}
			str.append("<userdata name=\"ud_block\">ud_data</userdata>");
			str.append("</item>");
		}
		return str.toString();
	}
	
	/**
	 * 判断该机构下是否存在权限机构
	 * @param isFilterAuthority 是否进行机构权限过滤 0-不过滤 1-过滤
	 * @param childList 某机构下所有的子机构
	 * @param deptStrings 机构权限集合
	 * @param isLastChild 是否区分叶子节点（city---区分叶子节点）
	 * @return
	 */
	private boolean isExistAuthorityDepartment(String isFilterAuthority,List childList,List deptStrings){
		if("1".equals(isFilterAuthority)){
			boolean bl = false;
			for(int i = 0; i < childList.size(); i++){
				Map map = (Map)childList.get(i);
				if(deptStrings.contains(map.get("DEPTID"))){
					bl =true;
					break;
				}
			}
			return bl;
		}else{
			return true;
		}
	}
	
	/**
	 * 根据权限对二级（及领域）进行过滤--展示与否
	 * @param isFilterAuthority 是否进行机构权限过滤 0-不过滤 1-过滤
	 * @param area 领域
	 * @param deptStrings 机构权限集合
	 * @return
	 */
	private boolean isAreaShow(String isFilterAuthority,String area,List deptStrings){
		if("1".equals(isFilterAuthority)){
			for(int i = 0; i < deptStrings.size(); i++){
				String deptString = (String)deptStrings.get(i);
				if(deptString.startsWith(area)){
					return true;
				}
			}
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 
	 * 查找领域内所对应的区域
	 */
	@LocusTrace(setCode="GeDepartmentAction_getBussAreas")
	public String getBussAreas(){
		String isOrnot = "0";
		String idString=super.getRequest().getParameter("obid");
		String areaTypeString=super.getRequest().getParameter("areaT");
		List areas = geAreaService.getBussAreas(areaTypeString);
		System.out.println(areas.contains(idString));
		if(areas.contains(idString)){
			isOrnot = "1";
		}
		String flags = JsonBinder.buildNonNullBinder().toJson(isOrnot);
		super.render(flags, "application/json;charset=utf-8");
		return NONE;
	}
	
	
	/**
	 * 查询组机构权限详细树
	 */
	@LocusTrace(setCode="geDepartmentAction_findDeptmentTree")
	public void findDeptmentTree(){
		PrintWriter pw = null;
		try {
			Map permission = (Map)super.getSession().getAttribute("permission");
			String userAuthDeptId = (String) permission.get("ROLE_S_GROUP_M");
			String ancestor = geDeptGroupRoleService.getAllVirtualParent(userAuthDeptId);
			userAuthDeptId += "," + ancestor;
			
			Map map=(Map)super.getServletContext().getAttribute("orgTreeData");/**机构树MAP*/
			
			StringBuffer str = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			str.append("<tree id=\"" + "0" + "\">");
			this.groupDeptAuthDetailItem(str,"0", userAuthDeptId, map,"");
			str.append("</tree>");
			
			HttpServletResponse response = super.getResponse();
			response.setContentType("text/xml; charset=GBK");
		    response.setHeader("Cache-Control", "no-cache");
		    pw = response.getWriter();  
		    pw.write(str.toString());  
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pw != null){
				pw.flush();
			    pw.close();
			}
		}
	}
	
	
	/**
	 * 查询组机构权限详细树-xml
	 * @param treeID
	 * @param area
	 * @param groupDeptAuths
	 * @return
	 */
	private void groupDeptAuthDetailItem(StringBuffer str, String treeID, String groupDeptAuths, Map map, String deptName){
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
}
