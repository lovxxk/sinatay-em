package cn.com.sinosoft.ebusiness.mis.system.configManage.emailConfig.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeDeptMail;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeSmsConfig;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeDeptMailService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeSmsConfigService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDepartmentService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

/**
 * ������������
 *  
 *
 */
public class GeDeptMailAction extends Struts2Action {
	/**��������dto*/
	private GeDeptMail geDeptMail;
	/**�������÷�����*/
	private GeDeptMailService geDeptMailService;
	/**��ʾ��Ϣ*/
	private String message;
	/**������������list*/
	private List geDeptMailList;
	/**ҳ�洫�������*/
	private Page page;
	/**��־�ֶ�,1-�ɹ���0-ʧ��*/
	private String flag;
	private GeCodeService geCodeService;
	private GeDepartmentService geDepartmentService;
	public GeDeptMail getGeDeptMail() {
		return geDeptMail;
	}
	public void setGeDeptMail(GeDeptMail geDeptMail) {
		this.geDeptMail = geDeptMail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List getGeDeptMailList() {
		return geDeptMailList;
	}
	public void setGeDeptMailList(List geDeptMailList) {
		this.geDeptMailList = geDeptMailList;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public void setGeDeptMailService(GeDeptMailService geDeptMailService) {
		this.geDeptMailService = geDeptMailService;
	}
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}
	
	public void setGeDepartmentService(GeDepartmentService geDepartmentService) {
		this.geDepartmentService = geDepartmentService;
	}
	/**
	 * ���ӻ�����������׼��
	 * @return
	 */
	@LocusTrace(setCode="GeDeptMailAction_addDeptMailPrepare")
	public String addDeptMailPrepare(){
		//���ò�ѯ�����������
//		List<GeCode> codeList = geCodeService.findAllByGeCodeType("BusinessArea");
		List<GeCode> sendMailTypeList = geCodeService.findAllByGeCodeType("SendMailType");
//		if(codeList!=null&&codeList.size()>0){
//			List<GeDepartment> geDepartmentList = geDepartmentService.findDepartment(codeList.get(0).getId().getCodeCode(), "2", "1");
//			super.getRequest().setAttribute("geDepartmentList", geDepartmentList);
//		}
//		super.getRequest().setAttribute("businessAreaList", codeList);
		super.getRequest().setAttribute("sendMailTypeList", sendMailTypeList);
		return SUCCESS;
	}
	/**
	 * ���ӻ�����������
	 * @return
	 */
	@LocusTrace(setCode="GeDeptMailAction_addDeptMail")
	public String addDeptMail(){
		try{
		   GeOperator geOperator =  (GeOperator)super.getSession().getAttribute("geOperator");
		  // geDeptMail.setMobile(geDeptMail.getMobile());
		   geDeptMail.setCreater(geOperator.getOperatorid());
		   geDeptMail.setCreateTime(new Date());
		
		   geDeptMailService.addGeDeptMail(geDeptMail);
		}catch(Exception e){
			message = "������������ʧ��";
			flag = "0";
			return ERROR;
		}
		flag = "1";
		message = "�����������óɹ�";
		return SUCCESS;
	}
	/**
	 * ��ѯ��������׼��
	 * @return
	 */
	@LocusTrace(setCode="GeDeptMailAction_findDeptMailPrepare")
	public String findDeptMailPrepare(){
		List<GeCode> sendMailTypeList = geCodeService.findAllByGeCodeType("SendMailType");
		super.getRequest().setAttribute("sendMailTypeList", sendMailTypeList);
		return SUCCESS;
	}
	/**
	 * ��ѯ�����������õ�LIST
	 * @return
	 */
	@LocusTrace(setCode="GeDeptMailAction_findDeptMail")
	public String findDeptMail(){
		Map map = (Map) super.getSession().getAttribute("permission");
		String authorityid = (String) map.get("ROLE_S_ECON_M");
		if(geDeptMail.getDeptID()!=null&&!"".equals(geDeptMail.getDeptID())){
			authorityid = geDeptMail.getDeptID();
		}
		page = geDeptMailService.getGeDeptMailList(geDeptMail, pageNo, pageSize,"1",authorityid);
		super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
		super.getRequest().setAttribute("pageSize", page.getPageSize());
		super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
		super.getRequest().setAttribute("totalCount", page.getTotalCount());
		return SUCCESS;
	}
	/**
	 * ���»�����������׼��
	 * @return
	 */
	@LocusTrace(setCode="GeDeptMailAction_updatePrepareDeptMail")
	public String updatePrepareDeptMail(){
		geDeptMail = geDeptMailService.getGeDeptMailByPK(geDeptMail.getDeptMailID());
		return SUCCESS;
	}
	/**
	 * ���»�����������
	 * @return
	 */
	@LocusTrace(setCode="GeDeptMailAction_updateDeptMail")
	public String updateDeptMail(){
		GeOperator geOperator =  (GeOperator)super.getSession().getAttribute("geOperator");
		geDeptMail.setUpdater(geOperator.getOperatorid());
		geDeptMail.setUpdateTime(new Date());
		try{
		   geDeptMailService.updateGeDeptMail(geDeptMail);
		   flag = "1";
		   message = "���»����������óɹ�";
		}catch(Exception e){
			message = "���»�����������ʧ��";
			flag = "0";
		}
		return SUCCESS;
	}
	/**
	 * ������ɾ��������������
	 * @return
	 */
	@LocusTrace(setCode="GeDeptMailAction_deleteDeptMail")
	public String deleteDeptMail(){
		try{
		   geDeptMailService.deleteGeDeptMailByPK(geDeptMail.getDeptMailID());
		   flag = "1";
		   message = "��������ɾ���ɹ�";
		}catch(Exception e){
		   message = "��������ɾ��ʧ��";
		   flag = "0";
		   return ERROR;
		}
		return SUCCESS;
	}
	/**
	 * �������鿴������������
	 * @return
	 */
	@LocusTrace(setCode="GeDeptMailAction_viewDeptMail")
	public String viewDeptMail(){
		try{
		   geDeptMail = geDeptMailService.getGeDeptMailByPK(geDeptMail.getDeptMailID());
		}catch(Exception e){
		   message = "�鿴������������ʧ��";
		   flag = "0";
		   return ERROR;
		}
		return SUCCESS;
	}
}
