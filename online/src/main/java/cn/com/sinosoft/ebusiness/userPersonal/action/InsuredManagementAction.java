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
	
	//��ӳ��ñ�������
	public String addInsured() {
		LoginUserInfo loginUserInfo = SpringSecurityUtils.getCurrentUser();
		
		//��ǰ��¼�ͻ���Ϣ
		userPersonal = geUserPersonalService.getUserPersonalByUserId(loginUserInfo.getCustomer().getUserID());
		
		if (userPersonal != null)
			insureds = new ArrayList<TopInsured>(userPersonal.getTopInsureds());
		
		//�����ݿ��л�ȡ֤������
		idTypes = geUserPersonalService.getIdTypes();
		
		//�����ݿ��л�ȡͶ�����˹�ϵ����
		relatedToApplicants = geUserPersonalService.getRelatedToApplicants();
		
		//��ӳ��ñ������˶�����Ϊ��
		insured = null;
		
		//��ӱ�������
		editFlag = "add";
		
		message = "";
		
		return SUCCESS;
	}
	
	public String getInsuredDetails() {
		LoginUserInfo loginUserInfo = SpringSecurityUtils.getCurrentUser();
		
		//��ǰ��¼�ͻ���Ϣ
		userPersonal = geUserPersonalService.getUserPersonalByUserId(loginUserInfo.getCustomer().getUserID());
		
		if (userPersonal != null)
			insureds = new ArrayList<TopInsured>(userPersonal.getTopInsureds());
		
		//�����ݿ��л�ȡ֤������
		idTypes = geUserPersonalService.getIdTypes();
		
		//�����ݿ��л�ȡͶ�����˹�ϵ����
		relatedToApplicants = geUserPersonalService.getRelatedToApplicants();
		
		//��ӳ��ñ������˶�����Ϊ��
		insured = null;
		
		//��ӱ�������
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
				outPut("1");//��������ظ���������
			}
			GeUserPersonal user = geUserPersonalService.getUserPersonalByUserId(userID);
			if (user.getTopInsureds().size() >= 10) {
				flag = false;
				outPut("2");//��ӳ��ñ�������ʧ�ܣ����ֻ�����10����������
			}
		} else {
			TopInsured insured = topInsuredService.checkTopInsured(ins, userID);
			if (insured != null) {
				flag = false;
				outPut("3");//�Ѵ�����ͬ�ı������ˣ��޸�ʧ��
			}
		}
		if (flag) {
			if ("add".equals(editFlag)) {
				outPut("0");//��ӱ������˳ɹ�
			} else {
				outPut("4");//���±���������Ϣ�ɹ�
			}
		}
		return NONE;
	}
	
	/**
	 * ��ӳ��ñ�������
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
	 * �޸ı���������Ϣ
	 * @return
	 */
	public String editInsured() {
		insured = topInsuredService.getTopInsuredById(getRequest().getParameter("id"));
		LoginUserInfo loginUserInfo = SpringSecurityUtils.getCurrentUser();
		
		//��ǰ��¼�ͻ���Ϣ
		userPersonal = geUserPersonalService.getUserPersonalByUserId(loginUserInfo.getCustomer().getUserID());
				
		if (userPersonal != null)
			insureds = new ArrayList<TopInsured>(userPersonal.getTopInsureds());
		
		//�����ݿ��л�ȡ֤������
		idTypes = geUserPersonalService.getIdTypes();
		
		//�����ݿ��л�ȡͶ�����˹�ϵ����
		relatedToApplicants = geUserPersonalService.getRelatedToApplicants();
		
		editFlag = "edit";
		//��մ�����ʾ��Ϣ
		message = "";
		
		return SUCCESS;
	}
	
	/**
	 * ɾ������������Ϣ
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
	
	/**����Ĭ�ϱ�����*/
	public String defaultInsured() {
		String seriaNo = insured.getSerialNo();
		LoginUserInfo user = SpringSecurityUtils.getCurrentUser();
		String userId = user.getCustomer().getUserID();
		
		TopInsured defaultInsured = topInsuredService.getTopInsuredById(seriaNo);
		defaultInsured.setDefaultInsured(true);
		topInsuredService.updateTopInsured(defaultInsured);
		
		//����֮ǰ��Ĭ�ϱ����˱�־Ϊfalse
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
