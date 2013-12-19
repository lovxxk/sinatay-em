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
		private GeCodeService geCodeService;/**数据字典服务类*/
		private GeCode geCode;
		private String message = "";
		private int flag;
		private static final String GEPERSOALEVEL = "GePersonalUserLevel";/***常量**/
		
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
			 String userLevel = super.getRequest().getParameter("userLevel");//用户等级
			 StringBuilder str = new StringBuilder();
			 str.append("<?xml version=\"1.0\" encoding=\"GBK\" ?>\n<tree id='0'>\n");
			 str.append("<item id=\"ROOT\" child=\"1\" nocheckbox=\"1\" text=\"前台个人用户权限\">");
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
		* 一次性加载
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
		 * 修改用户级别对应权限信息
		 */
		@LocusTrace(setCode="GeUserAuthorityAction_updateLevelAuthoritys")
		public String updateLevelAuthoritys(){
			 String authoritys = super.getRequest().getParameter("authoritys");//用户等级对应的权限
			 String userLevel = super.getRequest().getParameter("userLevel");//用户等级
			 if(geUserAuthorityService.saveLevelAuthoritys(userLevel, authoritys)){
				 message = "设置权限成功";
			 }else{
				 message = "设置权限失败";
			 }
			 return SUCCESS;
		}
		 
		/**
		* 查询机构信息用于展示
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
		* 进入到新建机构界面
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
		 * 新建权限
		 * @return
		 */
		@LocusTrace(setCode="GeUserAuthorityAction_createOnlineUserAuthority")
		public String createOnlineUserAuthority(){
			try {
				GeUserAuthority gea = geUserAuthorityService.findGeUserAuthorityByPK(geUserAuthority.getAuthorityID());
				if(gea != null){
					message = "该权限已经存在";
				}else{
					if(geUserAuthorityService.save(geUserAuthority)){
						message = "新建权限成功";
						flag = 1;
					}else{
						message = "新建权限失败";
					}
				}
			} catch (Exception e) {
				message = "新建权限失败";
			}
			return SUCCESS;
		}
		
		/**
		 * 获取修改的权限信息
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
		 * 更新权限信息
		 * @return
		 */
		@LocusTrace(setCode="GeUserAuthorityAction_updatesOnlineUserAuthority")
		public String updatesOnlineUserAuthority(){
			try {
				if(geUserAuthorityService.updates(geUserAuthority)){
					flag = 1;
					message = "权限修改成功";
				}else{
					message = "权限修改失败";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}

		/**
		 * 删除权限
		 * @return
		 */
		@LocusTrace(setCode="GeUserAuthorityAction_deleteOnlineUserAuthority")
		public String deleteOnlineUserAuthority(){
			try {
				if(geUserAuthorityService.existRoleAuthority(geUserAuthority.getAuthorityID())){
					message = "该权限已被角色使用，请先解除使用";
				}else{
					GeUserAuthority geAuth = geUserAuthorityService.findGeUserAuthorityByPK(geUserAuthority.getAuthorityID());
					if(geUserAuthorityService.delete(geAuth)){
						message = "删除权限成功";
						flag = 1;
					}else{
						message = "删除权限失败";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
		 
		 /**
		 * 获取权限树
		 * 注：没有复选框
		 * 该树特点：1.异步加载 2.增加了四个领域 ：集团、寿险、财险、养老险
		 * id:要查询的节点的ID
		 * deptID：正在操作的功能的权限id
		 * isFilterAuthority：是否过滤没有权限的机构 0-不过滤，1-过滤
		 */
		@LocusTrace(setCode="GeUserAuthorityAction_findOnlineUserAuthorityTreeUnChecked")
		public void findOnlineUserAuthorityTreeUnChecked(){
			String treeID = super.getRequest().getParameter("id") == null?"":super.getRequest().getParameter("id").trim();
			StringBuffer str = new StringBuffer();
			str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			try {
				str.append("<tree id=\"" + treeID + "\">");
				if("0".equals(treeID)){
					str.append("<item id=\"ROOT\" child=\"1\" text=\"前台个人用户权限\"></item>");
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
		 * 根据领域获取节点的子节点的xml--该方法为没有复选框树准备
		 * @param isFilterAuthority 是否进行机构权限过滤 0-不过滤 1-过滤
		 * @param treeID 要查询的父节点ID
		 * @param area	领域
		 * @param deptStrings 登录用户拥有的机构权限集合
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
		 * 查询数据字典中的用户等级
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
