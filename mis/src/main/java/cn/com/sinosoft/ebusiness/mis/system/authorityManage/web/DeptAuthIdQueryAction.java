package cn.com.sinosoft.ebusiness.mis.system.authorityManage.web;

import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDepartmentService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDeptGroupRoleService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.PermissionFactoryService;

public class DeptAuthIdQueryAction extends Struts2Action {
	private static final long serialVersionUID = 4353556787890032222L;
	
	private GeDepartmentService geDepartmentService;
	
	private PermissionFactoryService permissionFactoryService;
	
	private String authorityid;/**正在操作功能的权限编码*/
	
	private String type;/**2-input赋值  不填-js赋值*/
	
	private String openType;/**iframe-iframe 不填-window*/
	
	private String checkType;/**单选多选*/
	
	private GeDeptGroupRoleService geDeptGroupRoleService;
	
	private String parentFilterId;/**过滤父节点ID*/
	
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
	
	public PermissionFactoryService getPermissionFactoryService() {
		return permissionFactoryService;
	}

	public void setPermissionFactoryService(
			PermissionFactoryService permissionFactoryService) {
		this.permissionFactoryService = permissionFactoryService;
	}

	public String getAuthorityid() {
		return authorityid;
	}

	public void setAuthorityid(String authorityid) {
		this.authorityid = authorityid;
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

	public String selectDeptAuthId(){
		return SUCCESS;
	}
	
	public String selectDeptAuthIdForMailConfig(){
		return SUCCESS;
	}
	
	

	public String getParentFilterId() {
		return parentFilterId;
	}

	public void setParentFilterId(String parentFilterId) {
		this.parentFilterId = parentFilterId;
	}

	@LocusTrace(setCode="DeptAuthIdQueryAction_findQueryDeptAuthId")
	public void findQueryDeptAuthId(){
//		geDepartmentService.updateDeptName();
		String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
		String deptID = super.getRequest().getParameter("deptID")== null?"":super.getRequest().getParameter("deptID").trim();
		String isFilterAuthority = super.getRequest().getParameter("isFilterAuthority")== null?"1":super.getRequest().getParameter("isFilterAuthority").trim();
		
		/**为展示某节点一下节点服务*/
		List allParentDeptList = null;
		int allParentDeptListIndex = 0;
		if(!"0".equals(treeID)){
			allParentDeptList = geDepartmentService.findDepartmentByDepId(null,treeID);
			allParentDeptListIndex = allParentDeptList.size()-1;
		}
			
		Map map = (Map) super.getSession().getAttribute("permission");
		String deptStrings = (String) map.get(deptID);
		
		String temp = geDeptGroupRoleService.getAllVirtualParent(deptStrings);
		deptStrings += "," + temp;
		
		Map deptMap = (Map)super.getServletContext().getAttribute("orgTreeData");
		if(deptStrings != null){
			StringBuffer str = new StringBuffer();
			str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			try {
				str.append("<tree id=\"0\">");
				getItemsForCheckBox(str,isFilterAuthority,treeID,deptStrings,deptMap,allParentDeptList,allParentDeptListIndex,"");
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
	 * 获取节点的子节点的xml(该方法为复选框树准备)
	 * @param isFilterAuthority
	 * @param treeID
	 * @param deptStrings
	 * @param deptMap
	 * @return
	 */
	private void getItemsForCheckBox(StringBuffer str,String isFilterAuthority,String treeID,String deptStrings,Map deptMap,List allParentDeptList,int allParentDeptListIndex,String deptName){
		if(allParentDeptList != null && allParentDeptList.size() > 0){
			GeDepartment geDepartment = (GeDepartment)allParentDeptList.get(allParentDeptListIndex);
			String deptId = geDepartment.getDeptid();
			if(!"1".equals(isFilterAuthority)||(","+deptStrings+",").indexOf(",parent_"+deptId+",") != -1){
				if("0".equals(deptId)){
					str.append("<item id=\"parent_" + geDepartment.getDeptid() + "\" open=\"1\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
				}else{
					str.append("<item id=\"parent_" + geDepartment.getDeptid() + "\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
				}
				
				if(treeID.equals(deptId)){
					getItemsForCheckBox(str,isFilterAuthority,treeID,deptStrings,deptMap,null,0,"");
				}else{
					getItemsForCheckBox(str,isFilterAuthority,treeID,deptStrings,deptMap,allParentDeptList,allParentDeptListIndex-1,"");
				}
				str.append("</item>");
			}
		}else{
			List<GeDepartment> list =  (List)deptMap.get(treeID);
			if(list != null && list.size() > 0){
				/**虚拟本级节点*/
//				if(!"0".equals(treeID) && (","+deptStrings+",").indexOf(","+treeID+",") != -1){
//					str.append("<item id=\"" + treeID + "\" child=\"0\"  text=\""+deptName+"\"></item>");
//				}
				for (int i=0; i < list.size(); i++) {
					GeDepartment geDepartment = (GeDepartment)list.get(i);
					String deptId = geDepartment.getDeptid();
					String selfName = geDepartmentService.getSelfName(geDepartment);
					
					if(deptMap.containsKey(deptId)){
						if("1".equals(isFilterAuthority)&&(","+deptStrings+",").indexOf(",parent_"+deptId+",") == -1)continue;
						if("0".equals(treeID)){
							str.append("<item id=\"parent_" + geDepartment.getDeptid() + "\" open=\"1\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
						}else{
							str.append("<item id=\"parent_" + geDepartment.getDeptid() + "\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
						}
						getItemsForCheckBox(str,isFilterAuthority,deptId,deptStrings,deptMap,null,0,selfName);
					}else{
						if("1".equals(isFilterAuthority)&&(","+deptStrings+",").indexOf(","+deptId+",") == -1)continue;
						str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"0\" text=\"" + geDepartment.getDeptname() + "\">");
					}
					str.append("</item>");
				}
			}
		}
	}
}
