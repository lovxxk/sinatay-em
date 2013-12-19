package cn.com.sinosoft.ebusiness.userPersonal.action;

import ins.framework.web.Struts2Action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.sinosoft.ebusiness.service.user.common.domain.LoginUserInfo;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.TopInsured;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.TopInsuredService;
import cn.com.sinosoft.util.security.springSecurity.SpringSecurityUtils;

public class InsuredManagementAction extends Struts2Action {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(InsuredManagementAction.class);

	public GeUserPersonal userPersonal;
	
	public TopInsured insured;

	public TopInsuredService topInsuredService;
	
	public GeUserPersonalService geUserPersonalService;
	
	public List<TopInsured> insureds;
	
	public String idTypes;
	
	public String relatedToApplicants;
	
	public String message;
	
	public String editFlag;
	
	//添加常用被保险人
	public String addInsured() {
		LoginUserInfo loginUserInfo = SpringSecurityUtils.getCurrentUser();
		
		//当前登录客户信息
		userPersonal = geUserPersonalService.getUserPersonalByUserId(loginUserInfo.getCustomer().getUserID());
		
		if (userPersonal != null)
			insureds = new ArrayList<TopInsured>(userPersonal.getTopInsureds());
		
		//从数据库中获取证件类型
		idTypes = geUserPersonalService.getIdTypes();
		
		//从数据库中获取投被保人关系类型
		relatedToApplicants = geUserPersonalService.getRelatedToApplicants();
		
		//添加常用被保险人对象设为空
		insured = null;
		
		//添加被保险人
		editFlag = "add";
		
		message = "";
		
		return SUCCESS;
	}
	
	public String getInsuredDetails() {
		LoginUserInfo loginUserInfo = SpringSecurityUtils.getCurrentUser();
		
		//当前登录客户信息
		userPersonal = geUserPersonalService.getUserPersonalByUserId(loginUserInfo.getCustomer().getUserID());
		
		if (userPersonal != null)
			insureds = new ArrayList<TopInsured>(userPersonal.getTopInsureds());
		
		//从数据库中获取证件类型
		idTypes = geUserPersonalService.getIdTypes();
		
		//从数据库中获取投被保人关系类型
		relatedToApplicants = geUserPersonalService.getRelatedToApplicants();
		
		//添加常用被保险人对象设为空
		insured = null;
		
		//添加被保险人
		editFlag = "add";
		
		return SUCCESS;
	}
	
	public String checkInsured() {
		boolean flag = true;
		String editFlag = getRequest().getParameter("editFlag");
		TopInsured ins = new TopInsured();
		String userID = getRequest().getParameter("userID");
		String serialNo = getRequest().getParameter("serialNo");
		String name = getRequest().getParameter("insName");
		String idType = getRequest().getParameter("insIdType");
		String idNum = getRequest().getParameter("insIdNo");
		
		ins.setSerialNo(serialNo);
		ins.setFullName(name);
		ins.setIdType(Integer.valueOf(idType));
		ins.setIdNumber(idNum);
		if ("add".equals(editFlag)) {
			TopInsured topInsured = topInsuredService.checkTopInsured(ins, userID);
			if (topInsured != null) {
				flag = false;
				outPut("1");//不能添加重复被保险人
			}
			GeUserPersonal user = geUserPersonalService.getUserPersonalByUserId(userID);
			if (user.getTopInsureds().size() >= 10) {
				flag = false;
				outPut("2");//添加常用被保险人失败，最多只可添加10个被保险人
			}
		} else {
			TopInsured insured = topInsuredService.checkTopInsured(ins, userID);
			if (insured != null) {
				flag = false;
				outPut("3");//已存在相同的被保险人，修改失败
			}
		}
		if (flag) {
			if ("add".equals(editFlag)) {
				outPut("0");//添加被保险人成功
			} else {
				outPut("4");//更新被保险人信息成功
			}
		}
		return NONE;
	}
	
	/**
	 * 添加常用被保险人
	 * @return
	 */
	public String saveInsured() {
		if (editFlag.equals("add")) {
			GeUserPersonal user = geUserPersonalService.getUserPersonalByUserId(userPersonal.getUserID());
			insured.setUserPersonal(user);
			insured.setSerialNo(null);
			
			TopInsured topInsured = insured;
			topInsuredService.addTopInsured(topInsured);
		} else {
			if (!topInsuredService.saveInsued(insured, userPersonal)) {
				return ERROR;
			}
		}
		return SUCCESS;
	}
	
	public void outPut(String val) {
		try {
			PrintWriter writer = getResponse().getWriter();
			writer.write(val);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 修改被保险人信息
	 * @return
	 */
	public String editInsured() {
		insured = topInsuredService.getTopInsuredById(getRequest().getParameter("id"));
		LoginUserInfo loginUserInfo = SpringSecurityUtils.getCurrentUser();
		
		//当前登录客户信息
		userPersonal = geUserPersonalService.getUserPersonalByUserId(loginUserInfo.getCustomer().getUserID());
				
		if (userPersonal != null)
			insureds = new ArrayList<TopInsured>(userPersonal.getTopInsureds());
		
		//从数据库中获取证件类型
		idTypes = geUserPersonalService.getIdTypes();
		
		//从数据库中获取投被保人关系类型
		relatedToApplicants = geUserPersonalService.getRelatedToApplicants();
		
		editFlag = "edit";
		//清空错误提示信息
		message = "";
		
		return SUCCESS;
	}
	
	/**
	 * 删除被保险人信息
	 * @return
	 */
	public String deleteInsured() {
		try {
			topInsuredService.deleteTopInsuredByKey(getRequest().getParameter("id"));
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**设置默认被保人*/
	public String defaultInsured() {
		String seriaNo = insured.getSerialNo();
		LoginUserInfo user = SpringSecurityUtils.getCurrentUser();
		String userId = user.getCustomer().getUserID();
		
		TopInsured defaultInsured = topInsuredService.getTopInsuredById(seriaNo);
		defaultInsured.setDefaultInsured(true);
		topInsuredService.updateTopInsured(defaultInsured);
		
		//更新之前的默认被保人标志为false
		topInsuredService.modifyOtherInsureds(seriaNo, userId);
		
		return SUCCESS;
	}
	public void setUserPersonal(GeUserPersonal userPersonal) {
		this.userPersonal = userPersonal;
	}

	public void setInsured(TopInsured insured) {
		this.insured = insured;
	}

	public void setTopInsuredService(TopInsuredService topInsuredService) {
		this.topInsuredService = topInsuredService;
	}


	public GeUserPersonalService getGeUserPersonalService() {
		return geUserPersonalService;
	}


	public void setGeUserPersonalService(GeUserPersonalService geUserPersonalService) {
		this.geUserPersonalService = geUserPersonalService;
	}

	public void setInsureds(List<TopInsured> insureds) {
		this.insureds = insureds;
	}

	public void setIdTypes(String idTypes) {
		this.idTypes = idTypes;
	}

	public void setRelatedToApplicants(String relatedToApplicants) {
		this.relatedToApplicants = relatedToApplicants;
	}

	public static void setLog(Logger log) {
		InsuredManagementAction.log = log;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
}
