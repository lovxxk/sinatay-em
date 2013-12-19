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
	private GeCodeService geCodeService;/**�����ֵ������*/
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
	/***����**/
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
	 * ��ѯ������Ϣ����չʾ
	 * @return
	 */
	@LocusTrace(setCode="GeDepartmentAction_findGeDepartmentInfo")
	public String findGeDepartmentInfo(){
		try {
			geDepartment =  geDepartmentService.findGeDepartmentByPK(geDepartment.getDeptid());
			/**��ѯ�����ֵ��ҵ������*/
			List businessAreaList = geCodeService.findAllByGeCodeType(BUSINESSAREA);
			for(int i = 0; i < businessAreaList.size(); i++){
				GeCode geCode = (GeCode)businessAreaList.get(i);
				String codeCode = geCode.getId().getCodeCode();
				if(geDepartment.getBusinessarea().equals(codeCode)){
					super.getRequest().setAttribute("depAreaName", geCode.getCodeCName());
					
					/**����ҵ�������ҵ���Ӧ�ĳ�Ա��λ��Ϣ*/
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
	 * ���뵽�½���������
	 * @return
	 */
	@LocusTrace(setCode="GeDepartmentAction_frmCreateGeDepartment")
	public String frmCreateGeDepartment(){
		try {
			String parentId = geDepartment.getParentid().replace("area", "");
			String area = parentId.substring(0,1);
			super.getRequest().setAttribute("area", area);
			
			/**��ѯ�����ֵ��ҵ������*/
			List businessAreaList = geCodeService.findAllByGeCodeType(BUSINESSAREA);
			for(int i = 0; i < businessAreaList.size(); i++){
				GeCode geCode = (GeCode)businessAreaList.get(i);
				String codeCode = geCode.getId().getCodeCode();
				if(area.equals(codeCode)){
					geDepartment.setBusinessarea(area);
					super.getRequest().setAttribute("depAreaName", geCode.getCodeCName());
					
					/**����ҵ�������ҵ���Ӧ�ĳ�Ա��λ��Ϣ*/
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
			/** �ҵ��ڵ��������ӽڵ� */
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
	 * �½�����
	 * @return
	 */
	@LocusTrace(setCode="GeDepartmentAction_createGeDepartment")
	public String createGeDepartment(){
		try {
			if(geDepartmentService.exists(geDepartment.getDeptid())){
				message = "�û����Ѿ�����";
			}else{
				if(geDepartment.getParentid().startsWith("area"))geDepartment.setParentid("1");
				if(geDepartmentService.save(geDepartment)){
					String parentIdString = geDepartment.getParentid();
					Map map=(Map)super.getServletContext().getAttribute("orgTreeData");
					List geDeptList = (List)map.get(parentIdString);
					if(geDeptList == null)geDeptList = new ArrayList();
					geDeptList.add(geDepartment);
					map.put(geDepartment.getParentid(), geDeptList);
					message = "�½������ɹ�";
					flag = 1;
				}else{
					message = "�½�����ʧ��";
				}
			}
		} catch (Exception e) {
			message = "�½�����ʧ��";
		}
		return SUCCESS;
	}
	
	/**
	 * ��ȡ�޸ĵĻ�����Ϣ
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
			
			/**��ѯ�����ֵ��ҵ������*/
			List businessAreaList = geCodeService.findAllByGeCodeType(BUSINESSAREA);
			for(int i = 0; i < businessAreaList.size(); i++){
				GeCode geCode = (GeCode)businessAreaList.get(i);
				String codeCode = geCode.getId().getCodeCode();
				if(geDepartment.getBusinessarea().equals(codeCode)){
					super.getRequest().setAttribute("depAreaName", geCode.getCodeCName());
					
					/**����ҵ�������ҵ���Ӧ�ĳ�Ա��λ��Ϣ*/
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
	 * ���»�����Ϣ
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
				message = "�����޸ĳɹ�";
			}else{
				message = "�����޸�ʧ��";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * ɾ������
	 * @return
	 */
	@LocusTrace(setCode="GeDepartmentAction_deleteGeDepartment")
	public String deleteGeDepartment(){
		try {
			if(geDepartmentService.hasChild(geDepartment.getDeptid())){
				message = "�û����´����ӻ���������ɾ�����ӻ���";
			}else if(geDepartmentService.hasOperator(geDepartment.getDeptid())){
				message = "�û����´����û�������ɾ���������û�";
			}else if(geDepartmentService.hasDeptGroupRole(geDepartment.getDeptid())){
				message = "�û�������ɫ����Ȩ��ʹ�ã����ȴӽ�ɫ����Ȩ�����Ƴ��û���";
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
					message = "����ɾ���ɹ�";
					flag = 1;
				}else{
					message = "ɾ������ʧ��";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "�������ܱ�ʹ�ã�����ɾ�����������쳣";
		}
		return SUCCESS;
	}
	
	/**
	 * ��ȡ������
	 * ע��û�и�ѡ��
	 * �����ص㣺1.�첽���� 2.�������ĸ����� �����š����ա����ա�������
	 * id:Ҫ��ѯ�Ľڵ��ID
	 * deptID�����ڲ����Ĺ��ܵ�Ȩ��id
	 * isFilterAuthority���Ƿ����û��Ȩ�޵Ļ��� 0-�����ˣ�1-����
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
	 * ��ȡ������
	 * ע���и�ѡ��
	 * �����ص㣺1.�첽���� 2.�������ĸ����� �����š����ա����ա�������
	 * id:Ҫ��ѯ�Ľڵ��ID
	 * deptID�����ڲ����Ĺ��ܵ�Ȩ��id
	 * isFilterAuthority���Ƿ����û��Ȩ�޵Ļ��� 0-�����ˣ�1-����
	 * receivedObj��ԭʼȨ��
	 * isValidateLastCity�Ƿ�У��Ҷ�ӽڵ�
	 */
	@LocusTrace(setCode="GeDepartmentAction_findGeDepartmentTreeWithChecked")
	public void findGeDepartmentTreeWithChecked(){
		String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
		String deptID = super.getRequest().getParameter("deptID")== null?"":super.getRequest().getParameter("deptID").trim();
		String isFilterAuthority = super.getRequest().getParameter("isFilterAuthority")== null?"0":super.getRequest().getParameter("isFilterAuthority").trim();
		String isValidateLastCity = super.getRequest().getParameter("isValidateLastCity")== null?"":super.getRequest().getParameter("isValidateLastCity");
		String receivedObj = super.getRequest().getParameter("receivedObj")==null?"":super.getRequest().getParameter("receivedObj").trim();//Ȩ��id
		List<String> deptStrings = permissionFactoryService.findDeptAuthId(deptID, super.getSession());
		if(deptStrings != null){
			StringBuffer str = new StringBuffer();
			str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			try {
				str.append("<tree id=\"" + treeID + "\">");
				if("0".equals(treeID)){
					str.append("<item id=\"1\" child=\"1\" 	nocheckbox=\"1\" open=\"1\" text=\""+companyName+"ϵͳ\">");
					if(isAreaShow(isFilterAuthority,"1",deptStrings)){
						str.append("<item id=\"area1\" child=\"1\" 	nocheckbox=\"1\" text=\"����\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"2",deptStrings)){
						str.append("<item id=\"area2\" child=\"1\" nocheckbox=\"1\" text=\"����\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"3",deptStrings)){
						str.append("<item id=\"area3\" child=\"1\" nocheckbox=\"1\" text=\"����\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"4",deptStrings)){
						str.append("<item id=\"area4\" child=\"1\" nocheckbox=\"1\" text=\"������\"></item>");
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
	 * ������ID�ͽ�ɫID��ѯ�û���-��ɫ-������Ϣ
	 * ע���÷����ر�Ϊ�û���Ȩ������׼��(����ѡ��)
	 * �����ص㣺1.�첽���� 2.�������ĸ����� �����š����ա����ա�������
	 * id:Ҫ��ѯ�Ľڵ��ID
	 * deptID�����ڲ����Ĺ��ܵ�Ȩ��id
	 * isFilterAuthority���Ƿ����û��Ȩ�޵Ļ��� 0-�����ˣ�1-����
	 * receivedObj��ԭʼȨ��
	 * isValidateLastCity�Ƿ�У��Ҷ�ӽڵ�
	 */
	@LocusTrace(setCode="GeDepartmentAction_findGeDepartmentTreeForGroupRoleDep")
	public void findGeDepartmentTreeForGroupRoleDep(){
		String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
		String deptID = super.getRequest().getParameter("deptID")== null?"":super.getRequest().getParameter("deptID").trim();
		String isFilterAuthority = super.getRequest().getParameter("isFilterAuthority")== null?"0":super.getRequest().getParameter("isFilterAuthority").trim();
		String isValidateLastCity = super.getRequest().getParameter("isValidateLastCity")== null?"":super.getRequest().getParameter("isValidateLastCity");
		
		/**��ȡxml*/
		List<String> deptStrings = permissionFactoryService.findDeptAuthId(deptID, super.getSession()); 
		if(deptStrings != null){
			StringBuffer str = new StringBuffer();
			str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			try {
				str.append("<tree id=\"" + treeID + "\">");
				if("0".equals(treeID)){
					str.append("<item id=\"1\" child=\"1\" 	nocheckbox=\"1\" open=\"1\" text=\""+companyName+"ϵͳ\">");
					if(isAreaShow(isFilterAuthority,"1",deptStrings)){
						str.append("<item id=\"area1\" child=\"1\" 	nocheckbox=\"1\" text=\"����\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"2",deptStrings)){
						str.append("<item id=\"area2\" child=\"1\" nocheckbox=\"1\" text=\"����\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"3",deptStrings)){
						str.append("<item id=\"area3\" child=\"1\" nocheckbox=\"1\" text=\"����\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"4",deptStrings)){
						str.append("<item id=\"area4\" child=\"1\" nocheckbox=\"1\" text=\"������\"></item>");
					}
					str.append("</item>");
				}else {
					/**��ȡ���л���*/
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
	 * ������ID��ѯ�û���-��ɫ-������Ϣ
	 * ע���÷����ر�Ϊ�û���Ȩ������׼��(����ѡ��)
	 * �����ص㣺1.�첽���� 2.�������ĸ����� �����š����ա����ա�������
	 * id:Ҫ��ѯ�Ľڵ��ID
	 * deptID�����ڲ����Ĺ��ܵ�Ȩ��id
	 * isFilterAuthority���Ƿ����û��Ȩ�޵Ļ��� 0-�����ˣ�1-����
	 * receivedObj��ԭʼȨ��
	 * isValidateLastCity�Ƿ�У��Ҷ�ӽڵ�
	 */
	@LocusTrace(setCode="GeDepartmentAction_findGeDepartmentTreeForGroupRoleDepByGroupId")
	public void findGeDepartmentTreeForGroupRoleDepByGroupId(){
		String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
		String authorityid = super.getRequest().getParameter("authorityid")== null?"":super.getRequest().getParameter("authorityid").trim();
		String isFilterAuthority = super.getRequest().getParameter("isFilterAuthority")== null?"0":super.getRequest().getParameter("isFilterAuthority").trim();
		String isValidateLastCity = super.getRequest().getParameter("isValidateLastCity")== null?"":super.getRequest().getParameter("isValidateLastCity");
		/**��ȡxml*/
		List<String> deptStrings = permissionFactoryService.findDeptAuthId(authorityid, super.getSession()); 
		if(deptStrings != null){
			StringBuffer str = new StringBuffer();
			str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			try {
				str.append("<tree id=\"" + treeID + "\">");
				if("0".equals(treeID)){
					str.append("<item id=\"1\" child=\"1\" 	nocheckbox=\"1\" open=\"1\" text=\""+companyName+"ϵͳ\">");
					if(isAreaShow(isFilterAuthority,"1",deptStrings)){
						str.append("<item id=\"area1\" child=\"1\" 	nocheckbox=\"1\" text=\"����\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"2",deptStrings)){
						str.append("<item id=\"area2\" child=\"1\" nocheckbox=\"1\" text=\"����\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"3",deptStrings)){
						str.append("<item id=\"area3\" child=\"1\" nocheckbox=\"1\" text=\"����\"></item>");
					}
					if(isAreaShow(isFilterAuthority,"4",deptStrings)){
						str.append("<item id=\"area4\" child=\"1\" nocheckbox=\"1\" text=\"������\"></item>");
					}
					str.append("</item>");
				}else {
					/**��ȡ���л���*/
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
	 * ���������ȡ�ڵ���ӽڵ��xml--�÷���Ϊû�и�ѡ����׼��
	 * @param isFilterAuthority �Ƿ���л���Ȩ�޹��� 0-������ 1-����
	 * @param treeID Ҫ��ѯ�ĸ��ڵ�ID
	 * @param area	����
	 * @param deptStrings ��¼�û�ӵ�еĻ���Ȩ�޼���
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
			
			List childList = geDepartmentService.getChildList(geDepartment.getDeptid());/**�ҵ��ڵ��������ӽڵ�*/
			if(childList.size() > 0){
				if(!isExistAuthorityDepartment(isFilterAuthority,childList,deptStrings))continue;/**�жϽڵ����Ƿ���Ȩ�޵Ľڵ�*/
				str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
			}else{
				/**�ж��Ƿ�ӵ�л���Ȩ��*/
				if("1".equals(isFilterAuthority)&&!deptStrings.contains(geDepartment.getDeptid()))continue;
				str.append("<item id=\""+isValidateLastCity+"" + geDepartment.getDeptid() + "\" child=\"0\" text=\"" + geDepartment.getDeptname() + "\">");
			}
			str.append("<userdata name=\"ud_block\">ud_data</userdata>");
			str.append("</item>");
		}
		return str.toString();
	}
	
	/**
	 * �÷���Ϊ�û������û���Ȩ�޷���
	 * ��ȡ�ڵ���ӽڵ��xml(�÷���Ϊ��ѡ����׼��)
	 * @param treeID Ҫ��ѯ�ĸ��ڵ�ID
	 * @param area	����
	 * @param deptStrings ��¼�û�ӵ�еĻ���Ȩ�޼���
	 * @param receivedObj ԭ���Ѿ�ѡ�е�ֵ�ļ���
	 * @param isLastChild �Ƿ�����Ҷ�ӽڵ㣨city---����Ҷ�ӽڵ㣩
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
			
			/**�ж��Ƿ�ӵ�л���Ȩ��*/
			if("1".equals(isFilterAuthority)&&!deptStrings.contains(geDepartment.getDeptid()))continue;
			String checked = "";
			if(!StringUtils.isBlank(receivedObj)){
				if((","+receivedObj+",").indexOf(","+geDepartment.getDeptid()+",")!= -1)checked ="checked=\"1\"";
			}
			
			List childList = geDepartmentService.getChildList(geDepartment.getDeptid());/**�ҵ��ڵ��������ӽڵ�*/
			if(childList.size() > 0){
				//if(!isExistAuthorityDepartment(isFilterAuthority,childList,deptStrings))continue;/**�жϽڵ����Ƿ���Ȩ�޵Ľڵ�*/
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
	 * �жϸû������Ƿ����Ȩ�޻���
	 * @param isFilterAuthority �Ƿ���л���Ȩ�޹��� 0-������ 1-����
	 * @param childList ĳ���������е��ӻ���
	 * @param deptStrings ����Ȩ�޼���
	 * @param isLastChild �Ƿ�����Ҷ�ӽڵ㣨city---����Ҷ�ӽڵ㣩
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
	 * ����Ȩ�޶Զ����������򣩽��й���--չʾ���
	 * @param isFilterAuthority �Ƿ���л���Ȩ�޹��� 0-������ 1-����
	 * @param area ����
	 * @param deptStrings ����Ȩ�޼���
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
	 * ��������������Ӧ������
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
	 * ��ѯ�����Ȩ����ϸ��
	 */
	@LocusTrace(setCode="geDepartmentAction_findDeptmentTree")
	public void findDeptmentTree(){
		PrintWriter pw = null;
		try {
			Map permission = (Map)super.getSession().getAttribute("permission");
			String userAuthDeptId = (String) permission.get("ROLE_S_GROUP_M");
			String ancestor = geDeptGroupRoleService.getAllVirtualParent(userAuthDeptId);
			userAuthDeptId += "," + ancestor;
			
			Map map=(Map)super.getServletContext().getAttribute("orgTreeData");/**������MAP*/
			
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
	 * ��ѯ�����Ȩ����ϸ��-xml
	 * @param treeID
	 * @param area
	 * @param groupDeptAuths
	 * @return
	 */
	private void groupDeptAuthDetailItem(StringBuffer str, String treeID, String groupDeptAuths, Map map, String deptName){
		List<GeDepartment> list =(List<GeDepartment>) map.get(treeID);
		if(list != null && list.size() > 0){
			/**���Ȿ���ڵ�*/
//			if(!"0".equals(treeID) && (","+groupDeptAuths+",").indexOf(","+treeID+",") != -1){
//				str.append("<item id=\"" + treeID + "\" child=\"0\"  text=\""+deptName+"\"></item>");
//			}
			for (int i=0; i < list.size(); i++) {
				GeDepartment geDepartment = (GeDepartment)list.get(i);
				String selfName = geDepartmentService.getSelfName(geDepartment);
				
				if(map.containsKey(geDepartment.getDeptid())){
					if((","+groupDeptAuths+",").indexOf(",parent_"+geDepartment.getDeptid()+",") == -1)continue;/**�ж��Ƿ�ӵ�иû���Ȩ��*/
					if("0".equals(treeID)){
						str.append("<item id=\"" + geDepartment.getDeptid() + "\" open=\"1\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
					}else{
						str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"1\" text=\"" + geDepartment.getDeptname() + "\">");
					}
					groupDeptAuthDetailItem(str,geDepartment.getDeptid(),groupDeptAuths,map,selfName);
				}else{
					if((","+groupDeptAuths+",").indexOf(","+geDepartment.getDeptid()+",") == -1)continue;/**�ж��Ƿ�ӵ�иû���Ȩ��*/
					str.append("<item id=\"" + geDepartment.getDeptid() + "\" child=\"0\" text=\"" + geDepartment.getDeptname() + "\">");
				}
				str.append("</item>");
			}
		}
	}
}
