package cn.com.sinosoft.ebusiness.mis.system.interfacePortal.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.ClientUserService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.ExternalSysInfoService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.ExternalSysUserInterfaceInfoService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.ExternalSystemsUserService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.InterfaceInfoService;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ClientUser;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSysInfo;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSysUserInterfaceInfo;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSystemsUser;
import cn.com.sinosoft.portalModule.interfacePortal.domain.InterfaceInfo;
import cn.com.sinosoft.portalModule.interfacePortal.webService.service.WebService;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TXInsuranceXMLRequest;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TXInsuranceXMLResponse;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TransactionMessage;
import cn.com.sinosoft.portalModule.interfacePortal.xml.service.facade.TransactionMessageService;
import cn.com.sinosoft.util.encode.JsonBinder;

public class PortalModuleAction extends Struts2Action {

	/**
	 * ע���ʵ����
	 */
	private Page page;
	private TransactionMessage transactionMessage;
	private Page transactionMessageList;
	private ClientUser clientUser;
	private Page clientUserList;
	private ExternalSysInfo externalSysInfo;
	private Page externalSysInfoList;
	private ExternalSystemsUser externalSystemsUser;
	private Page externalSystemsUserList;
	private ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo;
	private Page externalSysUserInterfaceInfoList;
	private InterfaceInfo interfaceInfo;
	private Page interfaceInfoList;
	private List<ExternalSysInfo> externalSysInfoListNew;
	
	private int flag;//ҳ����ʾѡ����
	private String message;//����ҳ�����ʾ��Ϣ

	/**
	 * ע���service
	 */
	@Autowired
	private TransactionMessageService transactionMessageService;
	@Autowired
	private ClientUserService clientUserService;
	@Autowired
	private ExternalSysInfoService externalSysInfoService;
	@Autowired
	private ExternalSystemsUserService externalSystemsUserService;
	@Autowired
	private ExternalSysUserInterfaceInfoService externalSysUserInterfaceInfoService;
	@Autowired
	private InterfaceInfoService interfaceInfoService;
	@Autowired
	private GeCodeService geCodeService;
	@Autowired
	private WebService webService;


	/**
	 * �Զ������
	 */
	private String addResult;


	/***
	 * ��ҳ��ѯ���ױ���
	 * 
	 * @return
	 */
	public String searchTransactionMessagePage() {
		QueryRule queryRule = QueryRule.getInstance();
		String beginDate = this.getRequest().getParameter("beginDate");
		String endDate = this.getRequest().getParameter("endDate");
		if (StringUtils.isNotEmpty(transactionMessage.getTransRefGuid())
				&& StringUtils.isNotBlank(transactionMessage.getTransRefGuid())) {
			queryRule.addLike(
					"transRefGuid","%" + transactionMessage.getTransRefGuid().replaceAll(
							"\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(transactionMessage.getTransCode())
				&& StringUtils.isNotBlank(transactionMessage.getTransCode())) {
			queryRule.addLike("transCode", "%"
					+ transactionMessage.getTransCode().replaceAll("\\s", "%")
					+ "%");
		}
		if ( StringUtils.isNotBlank(beginDate)) {
			queryRule.addGreaterEqual("transTime", getDateToDate(beginDate));
		}
		if ( StringUtils.isNotBlank(endDate)) {
			queryRule.addLessEqual("transTime", getDateToEndDate(endDate));
		}
		if (StringUtils.isNotEmpty(transactionMessage.getRequestMessage())
				&& StringUtils.isNotBlank(transactionMessage
						.getRequestMessage())) {
			queryRule.addLike(
					"requestMessage",
					"%"
							+ transactionMessage.getRequestMessage()
									.replaceAll("\\s", "%") + "%");
		}
		page = transactionMessageService.findTransactionMessage(queryRule,
				pageNo, pageSize);
		return SUCCESS;
	}

	private Date getDateToDate(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	private Date getDateToEndDate(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = simpleDateFormat.parse(date.trim() + " 23:59:59");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

	/*
	 * private String getDateToDateString(Date date) { try { SimpleDateFormat
	 * simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); return
	 * simpleDateFormat.format(date); } catch (Exception e) {
	 * e.printStackTrace(); } return null ; }
	 */

	/***
	 * ͨ��������ˮ�Ų�ѯһ�����ױ���
	 * 
	 * @return
	 */
	public String searchTransactionMessage() {
		try{
			String transRefGuid = transactionMessage.getTransRefGuid();
			transactionMessage = transactionMessageService.findTransactionMessageByTransRefGuid(transRefGuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * ���ݵ�¼����ѯ�ͻ����û���Ϣ
	 * 
	 * @return
	 */
	public String searchClientUser() {
		try{
			String loginName = clientUser.getLoginName();
			clientUser = clientUserService.findClientUserByLoginName(loginName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * ���ݵ�¼�����ⲿϵͳID��ѯΨһ�Ŀͻ����û���Ϣ
	 * 
	 * @return
	 */
	public String searchUniqueClientUser() {
		try{
			QueryRule queryRule = QueryRule.getInstance();
			String id = clientUser.getId();
			if (StringUtils.isNotEmpty(id)
					&& StringUtils.isNotBlank(id)) {
				queryRule.addEqual("id", id);
			}
			page = clientUserService.findClientUser(queryRule, pageNo,pageSize);
			if(page.getResult() != null && page.getResult().size() > 0){
				clientUser = (ClientUser)page.getResult().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * ����Ψһ�Ե�У�飺clientUser.loginName+clientUser.externalSysInfo.externalSysId
	 */
	public void isHaveExIdAndName(){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			QueryRule queryRule = QueryRule.getInstance();
			String loginName = super.getRequest().getParameter("loginName");
			String externalSysId = super.getRequest().getParameter("externalSysId");
			queryRule.addEqual("loginName", loginName);
			queryRule.addEqual("externalSysInfo.externalSysId", externalSysId);
			page = clientUserService.findClientUser(queryRule, pageNo,pageSize); 
			if (page.getResult() != null && page.getResult().size() > 0) {
				resultMap.put("resultFlag", "success");
			} else {
				resultMap.put("resultFlag", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage����Ŀͻ����û���Ϣ�б�
	 * 
	 * @return
	 */
	public String searchClientUserPage() {
		QueryRule queryRule = QueryRule.getInstance();
		String beginDate = this.getRequest().getParameter("beginDate");
		String endDate = this.getRequest().getParameter("endDate");

		pageNo = pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == 0 ? 20 : pageSize;
		if (StringUtils.isNotEmpty(clientUser.getLoginName())
				&& StringUtils.isNotBlank(clientUser.getLoginName())) {
			queryRule.addLike("loginName","%" + clientUser.getLoginName().replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(clientUser.getStatus())
				&& StringUtils.isNotBlank(clientUser.getStatus())) {
			queryRule.addEqual("status", clientUser.getStatus());
		}
		if ( StringUtils.isNotBlank(beginDate)) {
			queryRule.addGreaterEqual("createDate", getDateToDate(beginDate));
		}
		if ( StringUtils.isNotBlank(endDate)) {
			queryRule.addLessEqual("createDate", getDateToEndDate(endDate));
		}		
		page = clientUserService.findClientUser(queryRule, pageNo,pageSize);
		getRequest().setAttribute("totalCount", page.getTotalCount());
		return SUCCESS;
	}

	/**
	 * �����½��ͻ����û���Ϣ
	 * 
	 * @return
	 */
	public String saveClientUser() {
		try{
			clientUser.setCreateDate(new Date());
			clientUser.setUpdateDate(new Date());
			clientUserService.addClientUser(clientUser);
			super.getRequest().setAttribute("addResult", "success");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("addResult", "failure");
		}
		return SUCCESS;
	}

	/**
	 * ɾ���ͻ����û���Ϣ
	 * 
	 * @return
	 */
	public String deleteClientUser() {
		try{
			QueryRule queryRule = QueryRule.getInstance();
			String id = super.getRequest().getParameter("id");
			if (StringUtils.isNotEmpty(id)
					&& StringUtils.isNotBlank(id)) {
				queryRule.addEqual("id", id);
			}
			page = clientUserService.findClientUser(queryRule, pageNo,pageSize);
			if(page.getResult() != null && page.getResult().size() > 0){
				clientUser = (ClientUser)page.getResult().get(0);
			}
			clientUserService.deleteClientUser(clientUser);
			flag = 1;
			message = "ɾ���ͻ����û���Ϣ�ɹ���";
		} catch (Exception e) {
			e.printStackTrace();
			message = "ɾ���ͻ����û���Ϣʧ�ܣ�";
		}
		return SUCCESS;
	}

	/**
	 * ���¿ͻ����û���Ϣ
	 * 
	 * @return
	 */
	public String updateClientUser() {
  		try{
  			clientUser.setUpdateDate(new Date());
  			clientUserService.updateClientUser(clientUser);
			super.getRequest().setAttribute("flag", "1");
			super.getRequest().setAttribute("message", "���¿ͻ����û���Ϣ�����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("flag", "0");
			super.getRequest().setAttribute("message", "���¿ͻ����û���Ϣ����ʧ�ܣ�");
		}
		return SUCCESS;
	}

	/**
	 * ͨ���ⲿϵͳID��ѯ�ⲿϵͳ��Ϣ
	 * 
	 * @return
	 */
	public String searchExternalSysInfo() {
		try{
			String externalSysID = externalSysInfo.getExternalSysId();
			externalSysInfo = externalSysInfoService.findExternalSysInfoByExternalSysID(externalSysID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * ��ѯȫ���ⲿϵͳ��Ϣ
	 */
	public void findExternalSysInfoList(){
		QueryRule queryRule = QueryRule.getInstance();
		pageNo = pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == 0 ? 200 : pageSize;
		try {
			externalSysInfoListNew = externalSysInfoService.findExternalSysInfo(queryRule, pageNo,pageSize).getResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			for (int i = 0; i < externalSysInfoListNew.size(); i++) {
				externalSysInfoListNew.get(i).setClientUsers(null);
				externalSysInfoListNew.get(i).setTransactionMessages(null);
				externalSysInfoListNew.get(i).setExternalSystemsUsers(null);
			}
			super.render(JsonBinder.buildNonNullBinder().toJson(externalSysInfoListNew), "application/json;charset=GBK");
		}
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage������ⲿϵͳ��Ϣ�б�-���ڿͻ����û������ⲿϵͳ��Ϣ
	 * 
	 * @return
	 */
	public String findExternalSysInfo() {
		QueryRule queryRule = QueryRule.getInstance();
		pageNo = pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == 0 ? 20 : pageSize;
		String externalSysId = super.getRequest().getParameter("externalSysId");
		String externalSysType = super.getRequest().getParameter("externalSysType");
		String externalSysName = super.getRequest().getParameter("externalSysName");
		String remark = super.getRequest().getParameter("remark");
		if (StringUtils.isNotEmpty(externalSysId)
				&& StringUtils.isNotBlank(externalSysId)) {
			queryRule.addLike("externalSysId","%" + externalSysId.replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(externalSysName)
				&& StringUtils.isNotBlank(externalSysName)) {
			queryRule.addLike("externalSysName", "%" + externalSysName.replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(remark)
				&& StringUtils.isNotBlank(remark)) {
			queryRule.addLike("remark", "%" + remark.replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(externalSysType)
				&& StringUtils.isNotBlank(externalSysType)) {
			queryRule.addEqual("externalSysType", externalSysType);
		}
		page = externalSysInfoService.findExternalSysInfo(queryRule, pageNo, pageSize);
		getRequest().setAttribute("totalCount", page.getTotalCount());
		return SUCCESS;
	}

	/**
	 * ���ݲ�ѯ�����ȡPage������ⲿϵͳ��Ϣ�б�
	 * 
	 * @return
	 */
	public String searchExternalSysInfoPage() {
		QueryRule queryRule = QueryRule.getInstance();
		pageNo = pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == 0 ? 20 : pageSize;
		if (StringUtils.isNotEmpty(externalSysInfo.getExternalSysId())
				&& StringUtils.isNotBlank(externalSysInfo.getExternalSysId())) {
			queryRule.addLike("externalSysId","%" + externalSysInfo.getExternalSysId().replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(externalSysInfo.getExternalSysName())
				&& StringUtils.isNotBlank(externalSysInfo.getExternalSysName())) {
			queryRule.addLike("externalSysName", "%" + externalSysInfo.getExternalSysName().replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(externalSysInfo.getRemark())
				&& StringUtils.isNotBlank(externalSysInfo.getRemark())) {
			queryRule.addLike("remark", "%" + externalSysInfo.getRemark().replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(externalSysInfo.getExternalSysType())
				&& StringUtils.isNotBlank(externalSysInfo.getExternalSysType())) {
			queryRule.addEqual("externalSysType", externalSysInfo.getExternalSysType());
		}
		page = externalSysInfoService.findExternalSysInfo(queryRule, pageNo, pageSize);
		getRequest().setAttribute("totalCount", page.getTotalCount());
		return SUCCESS;
	}

	/**
	 * ����ⲿϵͳ��Ϣ
	 * 
	 * @return
	 */
	public String saveExternalSysInfo() {
		try{
			externalSysInfoService.addExternalSysInfo(externalSysInfo);
			super.getRequest().setAttribute("addResult", "success");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("addResult", "failure");
		}
		return SUCCESS;
	}

	/**
	 * Ϊ�ⲿϵͳID����Ψһ��У��
	 */
	public void isHaveExternalSysId(){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String externalSysID = super.getRequest().getParameter("externalSysId");
			if (externalSysInfoService.findExternalSysInfoByExternalSysID(externalSysID) == null) {
				resultMap.put("resultFlag", "error");
			} else {
				resultMap.put("resultFlag", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
	}
	
	/**
	 * Ϊ�ⲿϵͳ���ƽ���Ψһ��У��
	 */
	public void isHaveExternalSysName(){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			QueryRule queryRule = QueryRule.getInstance();
			String externalSysName = super.getRequest().getParameter("externalSysName");
			queryRule.addEqual("externalSysName", externalSysName);
			page = externalSysInfoService.findExternalSysInfo(queryRule,pageNo,pageSize); 
			if (page.getResult() != null && page.getResult().size() > 0) {
				resultMap.put("resultFlag", "success");
			} else {
				resultMap.put("resultFlag", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
	}
	
	/**
	 * ͨ���ⲿϵͳIDɾ���ⲿϵͳ��Ϣ����
	 * 
	 * @return
	 */
	public String deleteExternalSysInfo() {
		try{
			String externalSysId = super.getRequest().getParameter("externalSysId");// �����ⲿϵͳID
			externalSysInfoService.deleteExternalSysInfo(externalSysId);
			flag = 1;
			message = "ɾ���ⲿϵͳ��Ϣ�ɹ���";
		} catch (Exception e) {
			e.printStackTrace();
			message = "ɾ���ⲿϵͳ��Ϣʧ�ܣ�";
		}
		return SUCCESS;
	}

	/**
	 * ����ID�����ⲿϵͳ��Ϣ
	 * 
	 * @return
	 */
	public String updateExternalSysInfo() {
		try{
			externalSysInfoService.updateExternalSysInfo(externalSysInfo);
			super.getRequest().setAttribute("flag", "1");
			super.getRequest().setAttribute("message", "�����ⲿϵͳ��Ϣ�����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("flag", "0");
			super.getRequest().setAttribute("message", "�����ⲿϵͳ��Ϣ����ʧ�ܣ�");
		}
		return SUCCESS;
	}

	/**
	 * ����Ψһ�Ե�У�飺loginName+ipAddress
	 */
	public void isHaveIpAddressAndName(){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			QueryRule queryRule = QueryRule.getInstance();
			String loginName = super.getRequest().getParameter("loginName");
			String ipAddress = super.getRequest().getParameter("ipAddress");
			queryRule.addEqual("loginName", loginName);
			queryRule.addEqual("ipAddress", ipAddress);
			page = externalSystemsUserService.findExternalSysUserInterfaceInfo(queryRule, pageNo,pageSize); 
			if (page.getResult() != null && page.getResult().size() > 0) {
				resultMap.put("resultFlag", "success");
			} else {
				resultMap.put("resultFlag", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
	}
	
	/**
	 * ���ݵ�¼�����û�ID��ַ��ѯΨһ�Ŀͻ����û���Ϣ
	 * 
	 * @return
	 */
	public String searchUniqueExternalSystemsUser() {
		try{
			QueryRule queryRule = QueryRule.getInstance();
			String loginName = externalSystemsUser.getLoginName();
			String ipAddress = externalSystemsUser.getIpAddress();
			if (StringUtils.isNotEmpty(loginName)
					&& StringUtils.isNotBlank(loginName)) {
				queryRule.addEqual("loginName", loginName);
			}
			if (StringUtils.isNotEmpty(ipAddress)
					&& StringUtils.isNotBlank(ipAddress)) {
				queryRule.addEqual("ipAddress", ipAddress);
			}
			page = externalSystemsUserService.findExternalSysUserInterfaceInfo(queryRule, pageNo,pageSize);
			if(page.getResult() != null && page.getResult().size() > 0){
				externalSystemsUser = (ExternalSystemsUser)page.getResult().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * ���ݵ�¼����ѯ�ⲿϵͳ�û���Ϣ
	 * 
	 * @return
	 */
	public String searchExternalSystemsUser() {
		try{
			String loginName = externalSystemsUser.getLoginName();
			externalSystemsUser = externalSystemsUserService.findExternalSystemsUserByLoginName(loginName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * ���ݲ�ѯ�����ȡPage������ⲿϵͳ�û���Ϣ�б�
	 * 
	 * @return
	 */
	public String searchExternalSystemsUserPage() {
		QueryRule queryRule = QueryRule.getInstance();
		pageNo = pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == 0 ? 20 : pageSize;
		String beginDate = this.getRequest().getParameter("beginDate");
		String endDate = this.getRequest().getParameter("endDate");
		if (StringUtils.isNotEmpty(externalSystemsUser.getLoginName())
				&& StringUtils.isNotBlank(externalSystemsUser.getLoginName())) {
			queryRule.addLike("loginName","%" + externalSystemsUser.getLoginName().replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(externalSystemsUser.getIpAddress())
				&& StringUtils.isNotBlank(externalSystemsUser.getIpAddress())) {
			queryRule.addLike("ipAddress","%" + externalSystemsUser.getIpAddress().replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(externalSystemsUser.getStatus())
				&& StringUtils.isNotBlank(externalSystemsUser.getStatus())) {
			queryRule.addEqual("status", externalSystemsUser.getStatus());
		}
		if ( StringUtils.isNotBlank(beginDate)) {
			queryRule.addGreaterEqual("createDate", getDateToDate(beginDate));
		}
		if ( StringUtils.isNotBlank(endDate)) {
			queryRule.addLessEqual("createDate",getDateToEndDate(endDate));
		}
		page = externalSystemsUserService.findExternalSysUserInterfaceInfo(queryRule, pageNo, pageSize);
		getRequest().setAttribute("totalCount", page.getTotalCount());
		return SUCCESS;
	}

	/**
	 * ����Ψһ�Ե�У�飺clientUser.loginName+clientUser.externalSysInfo.externalSysId
	 */
	public void isHaveIpAndName(){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			QueryRule queryRule = QueryRule.getInstance();
			String loginName = super.getRequest().getParameter("loginName");
			String ipAddress = super.getRequest().getParameter("ipAddress");
			queryRule.addEqual("loginName", loginName);
			queryRule.addEqual("ipAddress", ipAddress);
			page = externalSystemsUserService.findExternalSysUserInterfaceInfo(queryRule, pageNo,pageSize); 
			if (page.getResult() != null && page.getResult().size() > 0) {
				resultMap.put("resultFlag", "success");
			} else {
				resultMap.put("resultFlag", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
	}

	/**
	 * ����ⲿϵͳ�û�
	 * 
	 * @return
	 */
	public String saveExternalSystemsUser() {
		try{
			externalSystemsUser.setCreateDate(new Date());
			externalSystemsUser.setUpdateDate(new Date());
			externalSystemsUserService.addExternalSystemsUser(externalSystemsUser);
			super.getRequest().setAttribute("addResult", "success");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("addResult", "failure");
		}
		return SUCCESS;
	}

	/**
	 * ���ݵ�¼��ɾ���ⲿ�û���Ϣ
	 * 
	 * @return
	 */
	public String deleteExternalSystemsUser() {
		try{
			String loginName = super.getRequest().getParameter("loginName");// �����ⲿϵͳID
			externalSystemsUserService.deleteExternalSystemsUserByLoginName(loginName);
			flag = 1;
			message = "ɾ���ⲿϵͳ�û���Ϣ�ɹ���";
		} catch (Exception e) {
			e.printStackTrace();
			message = "ɾ���ⲿϵͳ�û���Ϣʧ�ܣ�";
		}
		return SUCCESS;
	}

	/**
	 * �����ⲿϵͳ�û�
	 * 
	 * @return
	 */
	public String updateExternalSystemsUser() {
		try{
			externalSystemsUser.setUpdateDate(new Date());
			externalSystemsUserService.updateExternalSystemsUser(externalSystemsUser);
			super.getRequest().setAttribute("flag", "1");
			super.getRequest().setAttribute("message", "�����ⲿϵͳ�û���Ϣ�����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("flag", "0");
			super.getRequest().setAttribute("message", "�����ⲿϵͳ�û���Ϣ����ʧ�ܣ�");
		}
		return SUCCESS;
	}

	/**
	 * ���ݲ�ѯ�����ȡPage������ⲿϵͳ�û��ͽӿڹ�ϵ�б�
	 * 
	 * @return
	 */
	public String searchExternalSysUserInterfaceInfoPage() {
		// ���Ӳ�ѯ����
		QueryRule queryRule = QueryRule.getInstance();
		pageNo = pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == 0 ? 20 : pageSize;

		String id = externalSysUserInterfaceInfo.getId();
		String ExternalSystemsUserId = externalSysUserInterfaceInfo.getExternalSystemsUser().getId();
		String transCode = externalSysUserInterfaceInfo.getInterfaceInfo().getTransCode();
		if (StringUtils.isNotEmpty(id)
				&& StringUtils.isNotBlank(id)) {
			queryRule.addLike("id","%" + id.replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(ExternalSystemsUserId)
				&& StringUtils.isNotBlank(ExternalSystemsUserId)) {
			queryRule.addLike("externalSystemsUser.id", "%" + ExternalSystemsUserId.replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(transCode)
				&& StringUtils.isNotBlank(transCode)) {
			queryRule.addLike("interfaceInfo.transCode", "%" + transCode.replaceAll("\\s", "%") + "%");
		}
		page = externalSysUserInterfaceInfoService.findExternalSysUserInterfaceInfo(queryRule, pageNo, pageSize);
		getRequest().setAttribute("totalCount", page.getTotalCount());
		return SUCCESS;
	}
	
	/**
	 * ����id�ⲿϵͳ�û��ͽӿڹ�ϵ�б���Ϣ
	 * 
	 * @return
	 */
	public String searchExternalSysUserInterfaceInfoById() {
 		try{
 			QueryRule queryRule = QueryRule.getInstance();
 			pageNo = pageNo == 0 ? 1 : pageNo;
 			pageSize = pageSize == 0 ? 20 : pageSize;

 			String id = externalSysUserInterfaceInfo.getId();
 			if (StringUtils.isNotEmpty(id)
 					&& StringUtils.isNotBlank(id)) {
 				queryRule.addEqual("id", id);
 			}
 			page = externalSysUserInterfaceInfoService.findExternalSysUserInterfaceInfo(queryRule, pageNo, pageSize);
 			externalSysUserInterfaceInfo = (ExternalSysUserInterfaceInfo)page.getResult().get(0);
 			getRequest().setAttribute("totalCount", page.getTotalCount());
 		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

  	/**
	 * ����Ψһ�Ե�У�飺loginName+ipAddress
	 */
	public void isHaveIdAndTransCode(){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			QueryRule queryRule = QueryRule.getInstance();
			String id = super.getRequest().getParameter("id");
			String transCode = super.getRequest().getParameter("transCode");
			queryRule.addEqual("externalSystemsUser.id", id);
			queryRule.addEqual("interfaceInfo.transCode", transCode);
			page = externalSysUserInterfaceInfoService.findExternalSysUserInterfaceInfo(queryRule, pageNo,pageSize); 
			if (page.getResult() != null && page.getResult().size() > 0) {
				resultMap.put("resultFlag", "success");
			} else {
				resultMap.put("resultFlag", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
	}
	
	/**
	 * ����ⲿϵͳ�û��ͽӿ�֮��Ĺ�ϵ
	 * 
	 * @return
	 */
	public String saveExternalSysUserInterfaceInfo() {
		try{
			QueryRule queryRule = QueryRule.getInstance();
			String id = externalSysUserInterfaceInfo.getExternalSystemsUser().getId();
			String transCode = externalSysUserInterfaceInfo.getInterfaceInfo().getTransCode();
			queryRule.addEqual("externalSystemsUser.id", id);
			queryRule.addEqual("interfaceInfo.transCode", transCode);
			page = externalSysUserInterfaceInfoService.findExternalSysUserInterfaceInfo(queryRule, pageNo,pageSize); 
			if (page.getResult() != null && page.getResult().size() > 0) {
				super.getRequest().setAttribute("addResult", "isNotUnique");
			}else{
				externalSysUserInterfaceInfoService.addExternalSysUserInterfaceInfo(externalSysUserInterfaceInfo);
				super.getRequest().setAttribute("addResult", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("addResult", "failure");
		}
		return SUCCESS;
	}

	/**
	 * �����ⲿϵͳ�û��ͽӿ�֮��Ĺ�ϵ
	 * 
	 * @return
	 */
	public String updateExternalSysUserInterfaceInfo() {
		try{
			externalSysUserInterfaceInfoService.updateExternalSysUserInterfaceInfo(externalSysUserInterfaceInfo);
			super.getRequest().setAttribute("flag", "1");
			super.getRequest().setAttribute("message", "�����ⲿϵͳ�û���Ϣ�����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("flag", "0");
			super.getRequest().setAttribute("message", "�����ⲿϵͳ�û���Ϣ����ʧ�ܣ�");
		}
		return SUCCESS;
	}
	
	/**
	 * ɾ���ⲿϵͳ�û��ͽӿ�֮��Ĺ�ϵ
	 * 
	 * @return
	 */
	public String deleteExternalSysUserInterfaceInfo() {
		try{
 			QueryRule queryRule = QueryRule.getInstance();
 			pageNo = pageNo == 0 ? 1 : pageNo;
 			pageSize = pageSize == 0 ? 20 : pageSize;

 			String id = externalSysUserInterfaceInfo.getId();
 			if (StringUtils.isNotEmpty(id)
 					&& StringUtils.isNotBlank(id)) {
 				queryRule.addEqual("id", id);
 			}
 			page = externalSysUserInterfaceInfoService.findExternalSysUserInterfaceInfo(queryRule, pageNo, pageSize);
 			externalSysUserInterfaceInfo = (ExternalSysUserInterfaceInfo)page.getResult().get(0);
			externalSysUserInterfaceInfoService.deleteExternalSysUserInterfaceInfo(externalSysUserInterfaceInfo);
			flag = 1;
			message = "ɾ��ϵͳ�û��ͽӿ�֮��Ĺ�ϵ��Ϣ�ɹ���";
		} catch (Exception e) {
			e.printStackTrace();
			message = "ɾ��ϵͳ�û��ͽӿ�֮��Ĺ�ϵ��Ϣʧ�ܣ�";
		}
		return SUCCESS;
	}

	/**
	 * ͨ�����״����ȡ�ӿ���Ϣ
	 * 
	 * @return
	 */
	public String searchInterfaceInfo() {
		try{
			String transCode = interfaceInfo.getTransCode();
			interfaceInfo = interfaceInfoService.findInterfaceInfoByTransCode(transCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * �ӿڵ�Ψһ��У��
	 */
	public void isHaveTransCode(){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String transCode = super.getRequest().getParameter("transCode");
			if (interfaceInfoService.findInterfaceInfoByTransCode(transCode) != null) {
				resultMap.put("resultFlag", "success");
			} else {
				resultMap.put("resultFlag", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿ���Ϣ�б�
	 * 
	 * @return
	 */
	public String searchInterfaceInfoPage() {
		QueryRule queryRule = QueryRule.getInstance();
		pageNo = pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == 0 ? 20 : pageSize;
		String transCode = interfaceInfo.getTransCode();
		String transType = interfaceInfo.getTransType();
		String status = interfaceInfo.getStatus();
		String handleMessageType = interfaceInfo.getHandleMessageType();
		String transName = interfaceInfo.getTransName();
		String requestURL = interfaceInfo.getRequestURL();
		String id = interfaceInfo.getClientUser().getId();
		if (StringUtils.isNotEmpty(id)
				&& StringUtils.isNotBlank(id)) {
			queryRule.addLike("clientUser.id","%" + id.replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(transCode)
				&& StringUtils.isNotBlank(transCode)) {
			queryRule.addLike("transCode","%" + transCode.replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(transCode)
				&& StringUtils.isNotBlank(transCode)) {
			queryRule.addLike("transCode","%" + transCode.replaceAll("\\s", "%") + "%");
		}
		
		if (StringUtils.isNotEmpty(transType)
				&& StringUtils.isNotBlank(transType)) {
			queryRule.addEqual("transType", transType);
		}
		if (StringUtils.isNotEmpty(status)
				&& StringUtils.isNotBlank(status)) {
			queryRule.addEqual("status", status);
		}
		if (StringUtils.isNotEmpty(handleMessageType)
				&& StringUtils.isNotBlank(handleMessageType)) {
			queryRule.addEqual("handleMessageType", handleMessageType);
		}
		if (StringUtils.isNotEmpty(transName)
				&& StringUtils.isNotBlank(transName)) {
			queryRule.addLike("transName", "%" + transName.replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(requestURL)
				&& StringUtils.isNotBlank(requestURL)) {
			queryRule.addLike("requestURL", "%" + requestURL.replaceAll("\\s", "%") + "%");
		}
		page = interfaceInfoService.findInterfaceInfo(queryRule,pageNo, pageSize);
		getRequest().setAttribute("totalCount", page.getTotalCount());
		return SUCCESS;
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿ���Ϣ�б�
	 * 
	 * @return
	 */
	public String searchInterfaceInfoPageNew() {
		QueryRule queryRule = QueryRule.getInstance();
		pageNo = pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == 0 ? 20 : pageSize;
		String transCode = interfaceInfo.getTransCode();
		String transType = interfaceInfo.getTransType();
		String status = interfaceInfo.getStatus();
		String handleMessageType = interfaceInfo.getHandleMessageType();
		String transName = interfaceInfo.getTransName();
		String requestURL = interfaceInfo.getRequestURL();
		String id = interfaceInfo.getClientUser().getId();
		if (StringUtils.isNotEmpty(id)
				&& StringUtils.isNotBlank(id)) {
			queryRule.addLike("clientUser.id","%" + id.replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(transCode)
				&& StringUtils.isNotBlank(transCode)) {
			queryRule.addLike("transCode","%" + transCode.replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(transCode)
				&& StringUtils.isNotBlank(transCode)) {
			queryRule.addLike("transCode","%" + transCode.replaceAll("\\s", "%") + "%");
		}
		
		if (StringUtils.isNotEmpty(transType)
				&& StringUtils.isNotBlank(transType)) {
			queryRule.addEqual("transType", transType);
		}
		if (StringUtils.isNotEmpty(status)
				&& StringUtils.isNotBlank(status)) {
			queryRule.addEqual("status", status);
		}
		if (StringUtils.isNotEmpty(handleMessageType)
				&& StringUtils.isNotBlank(handleMessageType)) {
			queryRule.addEqual("handleMessageType", handleMessageType);
		}
		if (StringUtils.isNotEmpty(transName)
				&& StringUtils.isNotBlank(transName)) {
			queryRule.addLike("transName", "%" + transName.replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotEmpty(requestURL)
				&& StringUtils.isNotBlank(requestURL)) {
			queryRule.addLike("requestURL", "%" + requestURL.replaceAll("\\s", "%") + "%");
		}
		queryRule.addIsNotNull("clientUser.id");
		page = interfaceInfoService.findInterfaceInfo(queryRule,pageNo, pageSize);
		getRequest().setAttribute("totalCount", page.getTotalCount());
		return SUCCESS;
	}

	/**
	 * ��ӽӿ���Ϣ
	 * 
	 * @return
	 */
	public String saveInterfaceInfo() {
		try{
			interfaceInfo.setCreateDate(new Date());
			interfaceInfo.setUpdateDate(new Date());
			interfaceInfoService.addInterfaceInfo(interfaceInfo);
			super.getRequest().setAttribute("addResult", "success");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("addResult", "failure");
		}
		return SUCCESS;
	}

	/**
	 * ͨ�����״���ɾ���ӿ���Ϣ
	 * 
	 * @return
	 */
	public String deleteInterfaceInfo() {
		try{
			String transCode = interfaceInfo.getTransCode();
			interfaceInfoService.deleteInterfaceInfoByTransCode(transCode);
			flag = 1;
			message = "ɾ���ӿ���Ϣ�ɹ���";
		} catch (Exception e) {
			e.printStackTrace();
			message = "ɾ���ӿ���Ϣʧ�ܣ�";
		}
		return SUCCESS;
	}

	/**
	 * ���½ӿ���Ϣ
	 * 
	 * @return
	 */
	public String updateInterfaceInfo() {
		try{
			interfaceInfo.setUpdateDate(new Date());
			interfaceInfoService.updateInterfaceInfo(interfaceInfo);
			super.getRequest().setAttribute("flag", "1");
			super.getRequest().setAttribute("message", "���½ӿ���Ϣ�����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("flag", "0");
			super.getRequest().setAttribute("message", "���½ӿ���Ϣ����ʧ�ܣ�");
		}
		return SUCCESS;
	}
	
	/**
	 * ���¿ͻ����û��󶨵Ľӿ���Ϣ
	 * 
	 * @return
	 */
	public String updateClientInterfaceInfo() {
		try{
			interfaceInfo.setUpdateDate(new Date());
			String newId = interfaceInfo.getClientUser().getId();
			interfaceInfo = interfaceInfoService.findInterfaceInfoByTransCode(interfaceInfo.getTransCode());
			QueryRule queryRule = QueryRule.getInstance();
			if (StringUtils.isNotEmpty(newId)
					&& StringUtils.isNotBlank(newId)) {
				queryRule.addEqual("id", newId);
			}
			page = clientUserService.findClientUser(queryRule, pageNo, pageSize);
			if( page != null){
				interfaceInfo.setClientUser((ClientUser)page.getResult().get(0));
				interfaceInfoService.updateInterfaceInfo(interfaceInfo);
				super.getRequest().setAttribute("addResult", "success");
				super.getRequest().setAttribute("message", "���¿ͻ����û��ͽӿڼ��ϵ�����ɹ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("addResult", "failure");
			super.getRequest().setAttribute("message", "���¿ͻ����û��ͽӿڼ��ϵ����ʧ�ܣ�");
		}
		return SUCCESS;
	}
	
	/**
	 * ɾ���ͻ����û��󶨵Ľӿ���Ϣ�����½ӿ���Ϣ��
	 * 
	 * @return
	 */
	public String deleteInterfaceInfoNew() {
		try{
			interfaceInfo = interfaceInfoService.findInterfaceInfoByTransCode(interfaceInfo.getTransCode());
			interfaceInfo.setUpdateDate(new Date());
			interfaceInfo.setClientUser(null);
			interfaceInfo.setTransType("");
			interfaceInfoService.updateInterfaceInfo(interfaceInfo);
			super.getRequest().setAttribute("flag", "1");
			super.getRequest().setAttribute("message", "ɾ���ͻ����û��ͽӿڹ�ϵ�����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("flag", "0");
			super.getRequest().setAttribute("message", "ɾ���ͻ����û��ͽӿڹ�ϵ����ʧ�ܣ�");
		}
		return SUCCESS;
	}

	//�������������������ֵ�
	public void findExternalGeCodeAjax(){
		String type = super.getRequest().getParameter("codeType");
		List<String[]> ExternalSysTypeList = geCodeService.findCodeAndNameByType(type);
		super.render(JsonBinder.buildNonNullBinder().toJson(ExternalSysTypeList), "application/json;charset=GBK");
		
	}
	
	/**
	 * online���������ˣ����½ӿ���Ϣ 
	 */
	public String changeInterface() {
		try{
			TXInsuranceXMLRequest txXMLReq = new TXInsuranceXMLRequest();
			TXInsuranceXMLResponse txXMLRes = webService.sendRPCRequestXML(txXMLReq, "EB00001");
			super.getRequest().setAttribute("flag", "1");
			super.getRequest().setAttribute("message", "���½ӿ���Ϣ�ɹ���");
		} catch (Exception e) {
			super.getRequest().setAttribute("flag", "0");
			super.getRequest().setAttribute("message", "���½ӿ���Ϣʧ�ܣ�");
		}
		return SUCCESS;
	}
	
	public Page getTransactionMessageList() {
		return transactionMessageList;
	}

	public void setTransactionMessageList(Page transactionMessageList) {
		this.transactionMessageList = transactionMessageList;
	}

	public TransactionMessage getTransactionMessage() {
		return transactionMessage;
	}

	public void setTransactionMessage(TransactionMessage transactionMessage) {
		this.transactionMessage = transactionMessage;
	}

	public TransactionMessageService getTransactionMessageService() {
		return transactionMessageService;
	}

	public void setTransactionMessageService(
			TransactionMessageService transactionMessageService) {
		this.transactionMessageService = transactionMessageService;
	}

	public ClientUser getClientUser() {
		return clientUser;
	}

	public void setClientUser(ClientUser clientUser) {
		this.clientUser = clientUser;
	}

	public ExternalSysInfo getExternalSysInfo() {
		return externalSysInfo;
	}

	public void setExternalSysInfo(ExternalSysInfo externalSysInfo) {
		this.externalSysInfo = externalSysInfo;
	}

	public ExternalSystemsUser getExternalSystemsUser() {
		return externalSystemsUser;
	}

	public void setExternalSystemsUser(ExternalSystemsUser externalSystemsUser) {
		this.externalSystemsUser = externalSystemsUser;
	}

	public ExternalSysUserInterfaceInfo getExternalSysUserInterfaceInfo() {
		return externalSysUserInterfaceInfo;
	}

	public void setExternalSysUserInterfaceInfo(
			ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo) {
		this.externalSysUserInterfaceInfo = externalSysUserInterfaceInfo;
	}

	public InterfaceInfo getInterfaceInfo() {
		return interfaceInfo;
	}

	public void setInterfaceInfo(InterfaceInfo interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}

	public Page getClientUserList() {
		return clientUserList;
	}

	public void setClientUserList(Page clientUserList) {
		this.clientUserList = clientUserList;
	}

	public Page getExternalSysInfoList() {
		return externalSysInfoList;
	}

	public void setExternalSysInfoList(Page externalSysInfoList) {
		this.externalSysInfoList = externalSysInfoList;
	}

	public Page getExternalSystemsUserList() {
		return externalSystemsUserList;
	}

	public void setExternalSystemsUserList(Page externalSystemsUserList) {
		this.externalSystemsUserList = externalSystemsUserList;
	}

	public Page getExternalSysUserInterfaceInfoList() {
		return externalSysUserInterfaceInfoList;
	}

	public void setExternalSysUserInterfaceInfoList(
			Page externalSysUserInterfaceInfoList) {
		this.externalSysUserInterfaceInfoList = externalSysUserInterfaceInfoList;
	}

	public Page getInterfaceInfoList() {
		return interfaceInfoList;
	}

	public void setInterfaceInfoList(Page interfaceInfoList) {
		this.interfaceInfoList = interfaceInfoList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	public String getAddResult() {
		return addResult;
	}

	public void setAddResult(String addResult) {
		this.addResult = addResult;
	}
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ExternalSysInfo> getExternalSysInfoListNew() {
		return externalSysInfoListNew;
	}

	public void setExternalSysInfoListNew(List<ExternalSysInfo> externalSysInfoListNew) {
		this.externalSysInfoListNew = externalSysInfoListNew;
	}


	public GeCodeService getGeCodeService() {
		return geCodeService;
	}

	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}

}
