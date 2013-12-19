package cn.com.sinosoft.ebusiness.mis.system.authorityManage.web;


import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptAttribute;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDeptAttributeService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

public class GeDeptAttributeAction extends Struts2Action{
	private GeDeptAttribute geDeptAttribute;
	private GeDeptAttributeService geDeptAttributeService;
	private int flag;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	private String message;
	public GeDeptAttribute getGeDeptAttribute() {
		return geDeptAttribute;
	}
	public void setGeDeptAttribute(GeDeptAttribute geDeptAttribute) {
		this.geDeptAttribute = geDeptAttribute;
	}
	public GeDeptAttributeService getGeDeptAttributeService() {
		return geDeptAttributeService;
	}
	public void setGeDeptAttributeService(
			GeDeptAttributeService geDeptAttributeService) {
		this.geDeptAttributeService = geDeptAttributeService;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String createDeptAttribute(){
		try {
			geDeptAttributeService.save(geDeptAttribute);
			flag = 1;
			message = "机构关联成功";
		} catch (Exception e) {
			e.printStackTrace();
			message = "机构关联失败";
		}
		return SUCCESS;
	}
	public String queryDeptAttributeByDisPage(){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geDepartment.deptid", geDeptAttribute.getGeDepartment().getDeptid());
		if (!StringUtils.isBlank(geDeptAttribute.getAttrValue())) {
			queryRule.addLike("attrValue", "%"+geDeptAttribute.getAttrValue()+"%");
		}
		Page page = geDeptAttributeService.findAllDeptAttributeByDisPage(queryRule, pageNo, pageSize);
		super.getRequest().setAttribute("geDeptAttributeList", page.getResult());
		super.getRequest().setAttribute("pageNo", page.getCurrentPageNo());
		super.getRequest().setAttribute("pageSize", page.getPageSize());
		super.getRequest().setAttribute("totalPage", page.getTotalPageCount());
		super.getRequest().setAttribute("totalCount", page.getTotalCount());
		
		return SUCCESS;
	}
	
	public String findDeptAttributeForDetail(){
		geDeptAttribute = geDeptAttributeService.findGeDeptAttributeByPk(geDeptAttribute.getDeptattrid());
		return SUCCESS;
	}
	public String updateDeptAttribute(){
		if (geDeptAttributeService.updateGeDeptAttribute(geDeptAttribute)) {
			flag = 1;
			message="更新机构关联成功！";
		}else {
			message="更新机构关联失败！";
		}
		return SUCCESS;
	}
	public String deleteDeptAttribute(){
		try {
			geDeptAttributeService.delete(geDeptAttribute.getDeptattrid());
			flag = 1;
			message = "删除机构关联成功";
		} catch (Exception e) {
			message = "删除机构关联成功"; 
		}
		return SUCCESS;
	}

}
