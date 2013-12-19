package cn.com.sinosoft.ebusiness.mis.system.configManage.SMSConfig.web;

import java.util.Date;
import java.util.List;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeSmsConfig;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeSmsConfigService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

/**
 * �������ù���
 *  
 *
 */
public class GeSmsConfigAction extends Struts2Action {
	/**��������dto*/
	private GeSmsConfig geSmsConfig;
	/**�������÷�����*/
	private GeSmsConfigService geSmsConfigService;
	/**��ʾ��Ϣ*/
	private String message;
	/**��������list*/
	private List geSmsConfigList;
	/**ҳ�洫�������*/
	private Page page;
	/**��־�ֶ�,1-�ɹ���0-ʧ��*/
	private String flag;
	private GeCodeService geCodeService;
	public GeSmsConfig getGeSmsConfig() {
		return geSmsConfig;
	}
	public void setGeSmsConfig(GeSmsConfig geSmsConfig) {
		this.geSmsConfig = geSmsConfig;
	}
	public void setGeSmsConfigService(GeSmsConfigService geSmsConfigService) {
		this.geSmsConfigService = geSmsConfigService;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List getGeSmsConfigList() {
		return geSmsConfigList;
	}
	public void setGeSmsConfigList(List geSmsConfigList) {
		this.geSmsConfigList = geSmsConfigList;
	}
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
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
	/**
	 * ���Ӷ�������׼��
	 * @return
	 */
	@LocusTrace(setCode="GeSmsConfigAction_addSmsPrepare")
	public String addSmsPrepare(){
		List<GeCode> codeList = geCodeService.findAllByGeCodeType("SendSmsType");
		super.getRequest().setAttribute("SendSmsTypeList", codeList);
		return SUCCESS;
	}
	/**
	 * ���Ӷ�������
	 * @return
	 */
	@LocusTrace(setCode="GeSmsConfigAction_addSms")
	public String addSms(){
		if("1".equals(geSmsConfig.getValidInd())){//��Ч�Ķ�������
			List<GeSmsConfig> geSmsConfigList = geSmsConfigService.getSmsConfigByFunctionId(geSmsConfig.getFunctionID());
			if(geSmsConfigList!=null&&geSmsConfigList.size()>0){
				message = "����Ӧ�������Ѵ�����Ч�Ķ������ã�";
				flag = "0";
				return ERROR;
			}
		}
		try{
		   GeOperator geOperator =  (GeOperator)super.getSession().getAttribute("geOperator");
		   geSmsConfig.setCreater(geOperator.getOperatorid());
		   geSmsConfig.setCreateTime(new Date());
		   geSmsConfigService.addGeSmsConfig(geSmsConfig);
		}catch(Exception e){
			message = "��������ʧ��";
			flag = "0";
			return ERROR;
		}
		flag = "1";
		message = "�������óɹ�";
		return SUCCESS;
	}
	/**
	 * ����׼��
	 * @return
	 */
	@LocusTrace(setCode="GeSmsConfigAction_findSmsPrepare")
	public String findSmsPrepare(){
		List<GeCode> codeList = geCodeService.findAllByGeCodeType("SendSmsType");
		super.getRequest().setAttribute("SendSmsTypeList", codeList);
		return SUCCESS;
	}
	@LocusTrace(setCode="GeSmsConfigAction_findSms")
	public String findSms(){
		page = geSmsConfigService.getGeSmsConfigList(geSmsConfig,pageNo,pageSize);
		super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
		super.getRequest().setAttribute("pageSize", page.getPageSize());
		super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
		super.getRequest().setAttribute("totalCount", page.getTotalCount());
		return SUCCESS;
	}
	/**
	 * ���¶�������׼��
	 * @return
	 */
	@LocusTrace(setCode="GeSmsConfigAction_updatePrepareSms")
	public String updatePrepareSms(){
		geSmsConfig = geSmsConfigService.getGeSmsConfigByPK(geSmsConfig.getSmsId());
		return SUCCESS;
	}
	/**
	 * ���¶�������
	 * @return
	 */
	@LocusTrace(setCode="GeSmsConfigAction_updateSms")
	public String updateSms(){
		GeOperator geOperator =  (GeOperator)super.getSession().getAttribute("geOperator");
		geSmsConfig.setUpdater(geOperator.getOperatorid());
		geSmsConfig.setUpdateTime(new Date());
		if("1".equals(geSmsConfig.getValidInd())){//��Ч�Ķ�������
			List<GeSmsConfig> geSmsConfigList = geSmsConfigService.getSmsConfigByFunctionId(geSmsConfig.getFunctionID());
			if(geSmsConfigList!=null&&geSmsConfigList.size()>0&&!geSmsConfigList.get(0).getSmsId().equals(geSmsConfig.getSmsId())){
				message = "����Ӧ�������Ѵ�����Ч�Ķ������ã�";
				flag = "0";
				return SUCCESS;
			}
		}
		try{
		   geSmsConfigService.updateGeSmsConfig(geSmsConfig);
		   flag = "1";
		   message = "���¶������óɹ�";
		}catch(Exception e){
		   message = "���¶�������ʧ��";
		   flag = "0";
		   return SUCCESS;
		}
		return SUCCESS;
	}
	/**
	 * ������ɾ����������
	 * @return
	 */
	@LocusTrace(setCode="GeSmsConfigAction_deleteSms")
	public String deleteSms(){
		try{
		   geSmsConfigService.deleteGeSmsConfigByPK(geSmsConfig.getSmsId());
		}catch(Exception e){
		   message = "��������ɾ��ʧ��";
		   flag = "0";
		   return ERROR;
		}
		flag = "1";
		message = "��������ɾ���ɹ�";
		return SUCCESS;
	}
	/**
	 * �������鿴��������
	 * @return
	 */
	@LocusTrace(setCode="GeSmsConfigAction_viewSms")
	public String viewSms(){
		try{
		   geSmsConfig = geSmsConfigService.getGeSmsConfigByPK(geSmsConfig.getSmsId());
		}catch(Exception e){
		   message = "�鿴����ʧ��";
		   flag = "0";
		   return ERROR;
		}
		flag = "1";
		message = "success";
		return SUCCESS;
	}
}
