package cn.com.sinosoft.ebusiness.mis.system.authorityManage.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.util.List;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptInfo;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDeptAttributeService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDeptInfoService;
import cn.com.sinosoft.util.encode.JsonBinder;

public class GeDeptInfoAction extends Struts2Action{
	private GeDeptInfo geDeptInfo;
	private GeDeptInfoService geDeptInfoService;
	private GeDeptAttributeService geDeptAttributeService;
	
	public GeDeptAttributeService getGeDeptAttributeService() {
		return geDeptAttributeService;
	}
	public void setGeDeptAttributeService(
			GeDeptAttributeService geDeptAttributeService) {
		this.geDeptAttributeService = geDeptAttributeService;
	}
	private String message;
	private int flag;
	public GeDeptInfo getGeDeptInfo() {
		return geDeptInfo;
	}
	public void setGeDeptInfo(GeDeptInfo geDeptInfo) {
		this.geDeptInfo = geDeptInfo;
	}
	public GeDeptInfoService getGeDeptInfoService() {
		return geDeptInfoService;
	}
	public void setGeDeptInfoService(GeDeptInfoService geDeptInfoService) {
		this.geDeptInfoService = geDeptInfoService;
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
	
	public String findDeptInfoById(){
		String isExit = "0";
		if(geDeptInfoService.isExitId(geDeptInfo.getAttrID())){
			isExit = "1";
		}
		String attrid = JsonBinder.buildNonNullBinder().toJson(isExit);
		super.render(attrid, "application/json;charset=utf-8");
		return NONE;
	}
	
	public String createDeptInfo(){
		if(geDeptInfoService.save(geDeptInfo)){
			message = "新建机构属性成功";
			flag = 1;
		}else{
			message = "新建机构属性失败";
		}
		return SUCCESS;
	}
	
	public String queryDeptInfoByDisPage(){
		QueryRule queryRule = QueryRule.getInstance();
		if (!StringUtils.isBlank(geDeptInfo.getAttrID())) {
			queryRule.addEqual("attrID", geDeptInfo.getAttrID());
		}
		if (!StringUtils.isBlank(geDeptInfo.getAttrName())) {
			queryRule.addLike("attrName", "%"+geDeptInfo.getAttrName()+"%");
		}
		Page page = geDeptInfoService.findAllGeDeptInfoByDisPage(queryRule, pageNo, pageSize);
		super.getRequest().setAttribute("geDeptInfoList", page.getResult());
		super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
		super.getRequest().setAttribute("pageSize", page.getPageSize());
		super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
		super.getRequest().setAttribute("totalCount", page.getTotalCount());
		
		return SUCCESS;
	}
	
	public String detail(){
		geDeptInfo = geDeptInfoService.findGeDeptInfoByPk(geDeptInfo.getAttrID());
		return SUCCESS;
	}
	public String updateDeptInfo(){
		try {
			geDeptInfoService.updateGeDeptInfo(geDeptInfo);
			flag = 1;
			message = "更新机构属性成功！";
		} catch (Exception e) {
			message = "更新机构属性失败！";
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String deleteDeptInfo(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("geDeptInfo.attrID", geDeptInfo.getAttrID());
			List geList = geDeptAttributeService.findAllGeDeptAttribute(queryRule);
			if(geList!=null&&geList.size()!=0){
				message="该机构属性正在使用中，不可以删除";
			}else{
				geDeptInfoService.delete(geDeptInfo.getAttrID());
				flag = 1;
				message = "删除机构属性成功！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "删除机构属性失败";
		}
		return SUCCESS;
	}
}
