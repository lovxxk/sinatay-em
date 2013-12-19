package cn.com.sinosoft.ebusiness.mis.system.authorityManage.web;

import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.PermissionFactoryService;
import cn.com.sinosoft.ebusiness.sys.permission.service.spring.MisUser;
import cn.com.sinosoft.util.security.springSecurity.SpringSecurityUtils;

public class LoginAction extends Struts2Action {
	private static final long serialVersionUID = 64645778873423243L;
	private PermissionFactoryService permissionFactoryService;

	public PermissionFactoryService getPermissionFactoryService() {
		return permissionFactoryService;
	}

	public void setPermissionFactoryService(
			PermissionFactoryService permissionFactoryService) {
		this.permissionFactoryService = permissionFactoryService;
	}

	/**
	 * 个人用户登录
	 * @return
	 * @throws Exception
	 */
	@LocusTrace(setCode="LoginAction_login")
	public String login() {
		MisUser misUser = SpringSecurityUtils.getCurrentUser();
		GeOperator geOperator = misUser.getGeOperator();
		super.getSession().setAttribute("geOperator", geOperator);
		Map<String,String> permission = new HashMap<String, String>();
		if("1".equals(geOperator.getFlag())){/**用于超级大权限*/
			permission = permissionFactoryService.alldeptAuthIdString();
		}else{
			permission = permissionFactoryService.deptAuthIdString(geOperator.getOperatorid());
		}
		super.getSession().setAttribute("permission",permission);
		return "success";
	}
	
	/**
	 * 校验验证码
	 * @return
	 */
	@LocusTrace(setCode="LoginAction_checkAjax")
	public String checkAjax() {
		try {
			String code = (String) super.getSession().getAttribute("getImg");
			String inputCode = (String) super.getRequest().getParameter("checkCode");
			JSONObject backJson = new JSONObject();
			if(inputCode.equalsIgnoreCase(code)) {
				backJson.put("flg", "success");
			} else {
				backJson.put("flg", "false");
			}
			super.render(backJson.toString(), "application/json;charset=GBK");
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return NONE;
	}

}
