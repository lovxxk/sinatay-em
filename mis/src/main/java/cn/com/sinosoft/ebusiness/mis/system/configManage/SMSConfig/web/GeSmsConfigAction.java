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
 * 短信配置管理
 *  
 *
 */
public class GeSmsConfigAction extends Struts2Action {
	/**短信配置dto*/
	private GeSmsConfig geSmsConfig;
	/**短信配置服务类*/
	private GeSmsConfigService geSmsConfigService;
	/**提示信息*/
	private String message;
	/**短信配置list*/
	private List geSmsConfigList;
	/**页面传输对象类*/
	private Page page;
	/**标志字段,1-成功，0-失败*/
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
	 * 增加短信配置准备
	 * @return
	 */
	@LocusTrace(setCode="GeSmsConfigAction_addSmsPrepare")
	public String addSmsPrepare(){
		List<GeCode> codeList = geCodeService.findAllByGeCodeType("SendSmsType");
		super.getRequest().setAttribute("SendSmsTypeList", codeList);
		return SUCCESS;
	}
	/**
	 * 增加短信配置
	 * @return
	 */
	@LocusTrace(setCode="GeSmsConfigAction_addSms")
	public String addSms(){
		if("1".equals(geSmsConfig.getValidInd())){//有效的短信配置
			List<GeSmsConfig> geSmsConfigList = geSmsConfigService.getSmsConfigByFunctionId(geSmsConfig.getFunctionID());
			if(geSmsConfigList!=null&&geSmsConfigList.size()>0){
				message = "该适应功能下已存在有效的短信配置！";
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
			message = "短信配置失败";
			flag = "0";
			return ERROR;
		}
		flag = "1";
		message = "短信配置成功";
		return SUCCESS;
	}
	/**
	 * 查找准备
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
	 * 更新短信配置准备
	 * @return
	 */
	@LocusTrace(setCode="GeSmsConfigAction_updatePrepareSms")
	public String updatePrepareSms(){
		geSmsConfig = geSmsConfigService.getGeSmsConfigByPK(geSmsConfig.getSmsId());
		return SUCCESS;
	}
	/**
	 * 更新短信配置
	 * @return
	 */
	@LocusTrace(setCode="GeSmsConfigAction_updateSms")
	public String updateSms(){
		GeOperator geOperator =  (GeOperator)super.getSession().getAttribute("geOperator");
		geSmsConfig.setUpdater(geOperator.getOperatorid());
		geSmsConfig.setUpdateTime(new Date());
		if("1".equals(geSmsConfig.getValidInd())){//有效的短信配置
			List<GeSmsConfig> geSmsConfigList = geSmsConfigService.getSmsConfigByFunctionId(geSmsConfig.getFunctionID());
			if(geSmsConfigList!=null&&geSmsConfigList.size()>0&&!geSmsConfigList.get(0).getSmsId().equals(geSmsConfig.getSmsId())){
				message = "该适应功能下已存在有效的短信配置！";
				flag = "0";
				return SUCCESS;
			}
		}
		try{
		   geSmsConfigService.updateGeSmsConfig(geSmsConfig);
		   flag = "1";
		   message = "更新短信配置成功";
		}catch(Exception e){
		   message = "更新短信配置失败";
		   flag = "0";
		   return SUCCESS;
		}
		return SUCCESS;
	}
	/**
	 * 按主键删除短信配置
	 * @return
	 */
	@LocusTrace(setCode="GeSmsConfigAction_deleteSms")
	public String deleteSms(){
		try{
		   geSmsConfigService.deleteGeSmsConfigByPK(geSmsConfig.getSmsId());
		}catch(Exception e){
		   message = "短信配置删除失败";
		   flag = "0";
		   return ERROR;
		}
		flag = "1";
		message = "短信配置删除成功";
		return SUCCESS;
	}
	/**
	 * 按主键查看短信配置
	 * @return
	 */
	@LocusTrace(setCode="GeSmsConfigAction_viewSms")
	public String viewSms(){
		try{
		   geSmsConfig = geSmsConfigService.getGeSmsConfigByPK(geSmsConfig.getSmsId());
		}catch(Exception e){
		   message = "查看短信失败";
		   flag = "0";
		   return ERROR;
		}
		flag = "1";
		message = "success";
		return SUCCESS;
	}
}
