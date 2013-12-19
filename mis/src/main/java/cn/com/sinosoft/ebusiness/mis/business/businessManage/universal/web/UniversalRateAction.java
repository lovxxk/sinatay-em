package cn.com.sinosoft.ebusiness.mis.business.businessManage.universal.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.universalRate.domain.UniversalRate;
import cn.com.sinosoft.businessModule.universalRate.service.facade.UniversalRateService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

public class UniversalRateAction extends Struts2Action{
	
	private static final long serialVersionUID = 1L;
	
	private Page page;
	@Autowired
	private UniversalRateService universalRateService;
	private UniversalRate universal;
	private List<UniversalRate> universalList;
	
	private String flag;//标志
	private String message;//信息
	
	public String addUniversal(){
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("riskName", universal.getRiskName());
			queryRule.addEqual("culDate", universal.getCulDate());
			universalList = universalRateService.findUniversalByQueryRule(queryRule);
			if(universalList.size() != 0){
				flag = "0";
				message = "该产品已有相同结算日的利率";
				return SUCCESS;
			}
			flag =universalRateService.addUniversal(universal);
			if("1".equals(flag)){
				flag = "1";
				message = "万能险利率新建成功";
			}else if("2".equals(flag)){
				flag = "0";
				message = "已存在该万能险利率，新建失败";
			}
		}catch(Exception e){
			flag = "0";
			message = "利率创建失败";
		}
		
		return SUCCESS;
	}
	
	public String findUniversal() {
		return SUCCESS;
	}
	
	public String findUniversal2() {
		pageNo = (pageNo == 0 ? 1 : pageNo);
		pageSize = (pageSize == 0 ? 20 : pageSize);

		page = universalRateService.getUniversalPage(universal, pageNo, pageSize);
		
		return SUCCESS;
	}
	
	public String viewUniversal(){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("serialNo", universal.getSerialNo());
		universal = universalRateService.findUniversalByQueryRule(queryRule).get(0);
		
		return SUCCESS;
	}
	
	public String prepareUpdateUniversal(){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("serialNo", universal.getSerialNo());
		universal = universalRateService.findUniversalByQueryRule(queryRule).get(0);
		
		return SUCCESS;
	}
	
	public String updateUniversal(){	
		try{
			universalRateService.updateUniversal(universal);
			flag = "1";
			message = "利率更新成功";
		}catch(Exception e){
			flag = "0";
			message = "利率更新失败";
		}
		return SUCCESS;
	}
	public String deleteUniversal(){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("serialNo", universal.getSerialNo());
		if(universalRateService.findUniversalByQueryRule(queryRule).size() == 0){
			flag = "0";
			message = "删除失败";
		}else{
			universalRateService.deleteUniversal(universal);
			flag = "1";
			message = "删除成功";
		}
		return SUCCESS;
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public UniversalRate getUniversal() {
		return universal;
	}

	public void setUniversal(UniversalRate universal) {
		this.universal = universal;
	}

	public List<UniversalRate> getUniversalList() {
		return universalList;
	}

	public void setUniversalList(List<UniversalRate> universalList) {
		this.universalList = universalList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
