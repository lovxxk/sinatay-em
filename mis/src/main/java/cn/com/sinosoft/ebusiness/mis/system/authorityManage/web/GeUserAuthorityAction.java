package cn.com.sinosoft.ebusiness.mis.system.authorityManage.web;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeUserAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeUserAuthorityService;

public class GeUserAuthorityAction extends Struts2Action{
	 private static final long serialVersionUID = 54765879094334L;
		private GeUserAuthorityService geUserAuthorityService;
		private GeUserAuthority geUserAuthority;
		private GeCodeService geCodeService;/**�����ֵ������*/
		private GeCode geCode;
		private String message = "";
		private int flag;
		private static final String GEPERSOALEVEL = "GePersonalUserLevel";/***����**/
		
		public GeCode getGeCode() {
			return geCode;
		}

		public void setGeCode(GeCode geCode) {
			this.geCode = geCode;
		}

		public GeCodeService getGeCodeService() {
			return geCodeService;
		}

		public void setGeCodeService(GeCodeService geCodeService) {
			this.geCodeService = geCodeService;
		}

		public GeUserAuthorityService getGeUserAuthorityService() {
			return geUserAuthorityService;
		}

		public void setGeUserAuthorityService(
				GeUserAuthorityService geUserAuthorityService) {
			this.geUserAuthorityService = geUserAuthorityService;
		}

		public GeUserAuthority getGeUserAuthority() {
			return geUserAuthority;
		}

		public void setGeUserAuthority(GeUserAuthority geUserAuthority) {
			this.geUserAuthority = geUserAuthority;
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
		@LocusTrace(setCode="GeUserAuthorityAction_findTreeData")
		public void findTreeData(){
			 String userLevel = super.getRequest().getParameter("userLevel");//�û��ȼ�
			 StringBuilder str = new StringBuilder();
			 str.append("<?xml version=\"1.0\" encoding=\"GBK\" ?>\n<tree id='0'>\n");
			 str.append("<item id=\"ROOT\" child=\"1\" nocheckbox=\"1\" text=\"ǰ̨�����û�Ȩ��\">");
			 List levelAuthoritys =  geUserAuthorityService.findLevelAuthoritys(userLevel);
			 getAuthorityItems("ROOT",str,levelAuthoritys);
			 str.append("</item></tree>");
			 
			 try{
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
		* һ���Լ���
		* @param geAuthorityList
		* @param s
		* @param check
		*/
		private void getAuthorityItems(String treeID,StringBuilder str,List levelAuthoritys){
			QueryRule queryRule = QueryRule.getInstance();
			if(!StringUtils.isBlank(treeID))queryRule.addEqual("parentID", treeID);
			List<GeUserAuthority> geAuthorityList =  geUserAuthorityService.findAllAuthoritys(queryRule);
			for(int i = 0; i < geAuthorityList.size(); i++){
				GeUserAuthority geUserAuth = (GeUserAuthority)geAuthorityList.get(i);
				if(geUserAuthorityService.isHasChild(geUserAuth.getAuthorityID())){
					str.append("<item id=\"" + geUserAuth.getAuthorityID() + "\" child=\"1\" text=\"" + geUserAuth.getAuthorityName() + "\">");
					getAuthorityItems(geUserAuth.getAuthorityID(),str,levelAuthoritys);
				}else{
					String checked = levelAuthoritys.contains(geUserAuth.getAuthorityID())?" checked=\"1\" ":"";
					str.append("<item id=\"" + geUserAuth.getAuthorityID() + "\" child=\"0\" "+checked+" text=\"" + geUserAuth.getAuthorityName() + "\">");
				}
				str.append("</item>\n");
			}
		}
		
		/**
		 * �޸��û������ӦȨ����Ϣ
		 */
		@LocusTrace(setCode="GeUserAuthorityAction_updateLevelAuthoritys")
		public String updateLevelAuthoritys(){
			 String authoritys = super.getRequest().getParameter("authoritys");//�û��ȼ���Ӧ��Ȩ��
			 String userLevel = super.getRequest().getParameter("userLevel");//�û��ȼ�
			 if(geUserAuthorityService.saveLevelAuthoritys(userLevel, authoritys)){
				 message = "����Ȩ�޳ɹ�";
			 }else{
				 message = "����Ȩ��ʧ��";
			 }
			 return SUCCESS;
		}
		 
		/**
		* ��ѯ������Ϣ����չʾ
		* @return
		*/
		@LocusTrace(setCode="GeUserAuthorityAction_findOnlineUserAuthorityInfo")
		public String findOnlineUserAuthorityInfo(){
			try {
				geUserAuthority =  geUserAuthorityService.findGeUserAuthorityByPK(geUserAuthority.getAuthorityID());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
		
		/**
		* ���뵽�½���������
		* @return
		*/
		@LocusTrace(setCode="GeUserAuthorityAction_frmCreateOnlineUserAuthority")
		public String frmCreateOnlineUserAuthority(){
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
		
		/**
		 * �½�Ȩ��
		 * @return
		 */
		@LocusTrace(setCode="GeUserAuthorityAction_createOnlineUserAuthority")
		public String createOnlineUserAuthority(){
			try {
				GeUserAuthority gea = geUserAuthorityService.findGeUserAuthorityByPK(geUserAuthority.getAuthorityID());
				if(gea != null){
					message = "��Ȩ���Ѿ�����";
				}else{
					if(geUserAuthorityService.save(geUserAuthority)){
						message = "�½�Ȩ�޳ɹ�";
						flag = 1;
					}else{
						message = "�½�Ȩ��ʧ��";
					}
				}
			} catch (Exception e) {
				message = "�½�Ȩ��ʧ��";
			}
			return SUCCESS;
		}
		
		/**
		 * ��ȡ�޸ĵ�Ȩ����Ϣ
		 * @return
		 */
		@LocusTrace(setCode="GeUserAuthorityAction_findOnlineUserAuthorityForUpdate")
		public String findOnlineUserAuthorityForUpdate() {
			try {
				geUserAuthority =  geUserAuthorityService.findGeUserAuthorityByPK(geUserAuthority.getAuthorityID());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
		
		/**
		 * ����Ȩ����Ϣ
		 * @return
		 */
		@LocusTrace(setCode="GeUserAuthorityAction_updatesOnlineUserAuthority")
		public String updatesOnlineUserAuthority(){
			try {
				if(geUserAuthorityService.updates(geUserAuthority)){
					flag = 1;
					message = "Ȩ���޸ĳɹ�";
				}else{
					message = "Ȩ���޸�ʧ��";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}

		/**
		 * ɾ��Ȩ��
		 * @return
		 */
		@LocusTrace(setCode="GeUserAuthorityAction_deleteOnlineUserAuthority")
		public String deleteOnlineUserAuthority(){
			try {
				if(geUserAuthorityService.existRoleAuthority(geUserAuthority.getAuthorityID())){
					message = "��Ȩ���ѱ���ɫʹ�ã����Ƚ��ʹ��";
				}else{
					GeUserAuthority geAuth = geUserAuthorityService.findGeUserAuthorityByPK(geUserAuthority.getAuthorityID());
					if(geUserAuthorityService.delete(geAuth)){
						message = "ɾ��Ȩ�޳ɹ�";
						flag = 1;
					}else{
						message = "ɾ��Ȩ��ʧ��";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
		 
		 /**
		 * ��ȡȨ����
		 * ע��û�и�ѡ��
		 * �����ص㣺1.�첽���� 2.�������ĸ����� �����š����ա����ա�������
		 * id:Ҫ��ѯ�Ľڵ��ID
		 * deptID�����ڲ����Ĺ��ܵ�Ȩ��id
		 * isFilterAuthority���Ƿ����û��Ȩ�޵Ļ��� 0-�����ˣ�1-����
		 */
		@LocusTrace(setCode="GeUserAuthorityAction_findOnlineUserAuthorityTreeUnChecked")
		public void findOnlineUserAuthorityTreeUnChecked(){
			String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
			StringBuffer str = new StringBuffer();
			str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			try {
				str.append("<tree id=\"" + treeID + "\">");
				if("0".equals(treeID)){
					str.append("<item id=\"ROOT\" child=\"1\" text=\"ǰ̨�����û�Ȩ��\"></item>");
				}else{
					str.append(getItemsForNoCheckBox(treeID));
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
			
		/**
		 * ���������ȡ�ڵ���ӽڵ��xml--�÷���Ϊû�и�ѡ����׼��
		 * @param isFilterAuthority �Ƿ���л���Ȩ�޹��� 0-������ 1-����
		 * @param treeID Ҫ��ѯ�ĸ��ڵ�ID
		 * @param area	����
		 * @param deptStrings ��¼�û�ӵ�еĻ���Ȩ�޼���
		 * @return
		 */
		private String getItemsForNoCheckBox(String treeID){
			StringBuffer str = new StringBuffer();
			QueryRule queryRule = QueryRule.getInstance();
			if(!StringUtils.isBlank(treeID))queryRule.addEqual("parentID", treeID);
			List<GeUserAuthority> list =  geUserAuthorityService.findAllAuthoritys(queryRule);
			if(list != null){
				for (int i=0; i < list.size(); i++) {
					GeUserAuthority geUserAuthority = (GeUserAuthority)list.get(i);
					
					if(geUserAuthorityService.isHasChild(geUserAuthority.getAuthorityID())){
						str.append("<item id=\"" + geUserAuthority.getAuthorityID() + "\" child=\"1\" text=\"" + geUserAuthority.getAuthorityName() + "\">");
					}else{
						str.append("<item id=\"" + geUserAuthority.getAuthorityID() + "\" child=\"0\" text=\"" + geUserAuthority.getAuthorityName() + "\">");
					}
					str.append("<userdata name=\"ud_block\">ud_data</userdata>");
					str.append("</item>");
				}
			}
			return str.toString();
		}
		
		/**
		 * ��ѯ�����ֵ��е��û��ȼ�
		 * @return
		 */
		@LocusTrace(setCode="GeUserAuthorityAction_searchGePersonalLevel")
		public String searchGePersonalLevel(){
			QueryRule queryRule = QueryRule.getInstance();
			if(geCode.getId() != null && !StringUtils.isBlank(geCode.getId().getCodeCode()))queryRule.addEqual("id.codeCode", geCode.getId().getCodeCode().trim());
			if(!StringUtils.isBlank(geCode.getCodeCName()))queryRule.addLike("codeCName", "%"+geCode.getCodeCName().trim()+"%");
			queryRule.addEqual("id.codeType",GEPERSOALEVEL);
			queryRule.addAscOrder("codeEName");
			Page page = geCodeService.findGeCode(queryRule,super.getPageNo(),super.getPageSize());
			super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
			super.getRequest().setAttribute("pageSize", page.getPageSize());
			super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
			super.getRequest().setAttribute("totalCount", page.getTotalCount());
			super.getRequest().setAttribute("levelList", page.getResult());
			return SUCCESS;
		}
}
