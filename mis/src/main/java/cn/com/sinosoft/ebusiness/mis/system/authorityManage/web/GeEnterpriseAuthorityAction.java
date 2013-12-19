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
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeEnterpriseAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeEnterpriseAuthorityService;

public class GeEnterpriseAuthorityAction extends Struts2Action{
	 private static final long serialVersionUID = 54765879094334L;
		private GeEnterpriseAuthorityService geEnterpriseAuthorityService;
		private GeEnterpriseAuthority geEnterpriseAuthority;
		private GeCodeService geCodeService;/**�����ֵ������*/
		private GeCode geCode;
		private String message = "";
		private int flag;
		private static final String GEENTERPRISEUSERLEVEL = "GeEnterpriseUserLevel";/***����**/
		
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

		public GeEnterpriseAuthorityService getGeEnterpriseAuthorityService() {
			return geEnterpriseAuthorityService;
		}

		public void setGeEnterpriseAuthorityService(
				GeEnterpriseAuthorityService geEnterpriseAuthorityService) {
			this.geEnterpriseAuthorityService = geEnterpriseAuthorityService;
		}

		public GeEnterpriseAuthority getGeEnterpriseAuthority() {
			return geEnterpriseAuthority;
		}

		public void setGeEnterpriseAuthority(GeEnterpriseAuthority geEnterpriseAuthority) {
			this.geEnterpriseAuthority = geEnterpriseAuthority;
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
		
		@LocusTrace(setCode="GeEnterpriseAuthorityAction_findTreeData")
		public void findTreeData(){
			 String userLevel = super.getRequest().getParameter("userLevel");//�û��ȼ�
			 StringBuilder str = new StringBuilder();
			 str.append("<?xml version=\"1.0\" encoding=\"GBK\" ?>\n<tree id='0'>\n");
			 str.append("<item id=\"ROOT\" child=\"1\" nocheckbox=\"1\" text=\"ǰ̨��ҵ�û�Ȩ��\">");
			 List levelAuthoritys =  geEnterpriseAuthorityService.findLevelAuthoritys(userLevel);
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
			List<GeEnterpriseAuthority> geAuthorityList =  geEnterpriseAuthorityService.findAllAuthoritys(queryRule);
			for(int i = 0; i < geAuthorityList.size(); i++){
				GeEnterpriseAuthority geEnterpriseAuth = (GeEnterpriseAuthority)geAuthorityList.get(i);
				if(geEnterpriseAuthorityService.isHasChild(geEnterpriseAuth.getAuthorityID())){
					str.append("<item id=\"" + geEnterpriseAuth.getAuthorityID() + "\" child=\"1\" text=\"" + geEnterpriseAuth.getAuthorityName() + "\">");
					getAuthorityItems(geEnterpriseAuth.getAuthorityID(),str,levelAuthoritys);
				}else{
					String checked = levelAuthoritys.contains(geEnterpriseAuth.getAuthorityID())?" checked=\"1\" ":"";
					str.append("<item id=\"" + geEnterpriseAuth.getAuthorityID() + "\" child=\"0\" "+checked+" text=\"" + geEnterpriseAuth.getAuthorityName() + "\">");
				}
				str.append("</item>\n");
			}
		}
		
		/**
		 * �޸��û������ӦȨ����Ϣ
		 */
		@LocusTrace(setCode="GeEnterpriseAuthorityAction_updateLevelAuthoritys")
		public String updateLevelAuthoritys(){
			 String authoritys = super.getRequest().getParameter("authoritys");//�û��ȼ���Ӧ��Ȩ��
			 String userLevel = super.getRequest().getParameter("userLevel");//�û��ȼ�
			 if(geEnterpriseAuthorityService.saveLevelAuthoritys(userLevel, authoritys)){
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
		@LocusTrace(setCode="GeEnterpriseAuthorityAction_findOnlineEnterpriseAuthorityInfo")
		public String findOnlineEnterpriseAuthorityInfo(){
			try {
				geEnterpriseAuthority =  geEnterpriseAuthorityService.findGeEnterpriseAuthorityByPK(geEnterpriseAuthority.getAuthorityID());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
		
		/**
		* ���뵽�½���������
		* @return
		*/
		@LocusTrace(setCode="GeEnterpriseAuthorityAction_frmCreateOnlineEnterpriseAuthority")
		public String frmCreateOnlineEnterpriseAuthority(){
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
		@LocusTrace(setCode="GeEnterpriseAuthorityAction_createOnlineEnterpriseAuthority")
		public String createOnlineEnterpriseAuthority(){
			try {
				GeEnterpriseAuthority gea = geEnterpriseAuthorityService.findGeEnterpriseAuthorityByPK(geEnterpriseAuthority.getAuthorityID());
				if(gea != null){
					message = "��Ȩ���Ѿ�����";
				}else{
					if(geEnterpriseAuthorityService.save(geEnterpriseAuthority)){
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
		@LocusTrace(setCode="GeEnterpriseAuthorityAction_findOnlineEnterpriseAuthorityForUpdate")
		public String findOnlineEnterpriseAuthorityForUpdate() {
			try {
				geEnterpriseAuthority =  geEnterpriseAuthorityService.findGeEnterpriseAuthorityByPK(geEnterpriseAuthority.getAuthorityID());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
		
		/**
		 * ����Ȩ����Ϣ
		 * @return
		 */
		@LocusTrace(setCode="GeEnterpriseAuthorityAction_updatesOnlineEnterpriseAuthority")
		public String updatesOnlineEnterpriseAuthority(){
			try {
				if(geEnterpriseAuthorityService.updates(geEnterpriseAuthority)){
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
		@LocusTrace(setCode="GeEnterpriseAuthorityAction_deleteOnlineEnterpriseAuthority")
		public String deleteOnlineEnterpriseAuthority(){
			try {
				if(geEnterpriseAuthorityService.existRoleAuthority(geEnterpriseAuthority.getAuthorityID())){
					message = "��Ȩ���ѱ���ɫʹ�ã����Ƚ��ʹ��";
				}else if(geEnterpriseAuthorityService.isHasChild(geEnterpriseAuthority.getAuthorityID())){
					message="����ɾ������Ȩ�޵�Ȩ��";
				}else if(geEnterpriseAuthorityService.findLevelByAuthorityid(geEnterpriseAuthority.getAuthorityID())){
					message="��Ȩ���ѱ�ʹ��";
				}else{
					GeEnterpriseAuthority geAuth = geEnterpriseAuthorityService.findGeEnterpriseAuthorityByPK(geEnterpriseAuthority.getAuthorityID());
					if(geEnterpriseAuthorityService.delete(geAuth)){
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
		@LocusTrace(setCode="GeEnterpriseAuthorityAction_findOnlineEnterpriseAuthorityTreeUnChecked")
		public void findOnlineEnterpriseAuthorityTreeUnChecked(){
			String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
			StringBuffer str = new StringBuffer();
			str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			try {
				str.append("<tree id=\"" + treeID + "\">");
				if("0".equals(treeID)){
					str.append("<item id=\"ROOT\" child=\"1\" text=\"ǰ̨��ҵ�û�Ȩ��\"></item>");
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
			List<GeEnterpriseAuthority> list =  geEnterpriseAuthorityService.findAllAuthoritys(queryRule);
			if(list != null){
				for (int i=0; i < list.size(); i++) {
					GeEnterpriseAuthority geEnterpriseAuthority = (GeEnterpriseAuthority)list.get(i);
					
					if(geEnterpriseAuthorityService.isHasChild(geEnterpriseAuthority.getAuthorityID())){
						str.append("<item id=\"" + geEnterpriseAuthority.getAuthorityID() + "\" child=\"1\" text=\"" + geEnterpriseAuthority.getAuthorityName() + "\">");
					}else{
						str.append("<item id=\"" + geEnterpriseAuthority.getAuthorityID() + "\" child=\"0\" text=\"" + geEnterpriseAuthority.getAuthorityName() + "\">");
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
		@LocusTrace(setCode="GeEnterpriseAuthorityAction_searchGePersonalLevel")
		public String searchGePersonalLevel(){
			QueryRule queryRule = QueryRule.getInstance();
			if(geCode.getId() != null && !StringUtils.isBlank(geCode.getId().getCodeCode()))queryRule.addEqual("id.codeCode", geCode.getId().getCodeCode().trim());
			if(!StringUtils.isBlank(geCode.getCodeCName()))queryRule.addLike("codeCName", "%"+geCode.getCodeCName().trim()+"%");
			queryRule.addEqual("id.codeType",GEENTERPRISEUSERLEVEL);
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
