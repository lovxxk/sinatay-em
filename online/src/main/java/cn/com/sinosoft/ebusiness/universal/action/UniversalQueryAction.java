package cn.com.sinosoft.ebusiness.universal.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.universalRate.domain.UniversalRate;
import cn.com.sinosoft.businessModule.universalRate.service.facade.UniversalRateService;

import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

public class UniversalQueryAction extends Struts2Action {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UniversalRateService universalRateService;
	
	private String riskName;
	private Date startDate;
	private Date endDate;
	private List<UniversalRate> listUniversalRate;
	private List<String> listRiskName;
	private UniversalRate newUniversalRate;
	
	public String queryUniversal(){
		QueryRule queryRule = QueryRule.getInstance();
		if(riskName != null && !"".equals(riskName)){
			System.out.println("==========riskName==========");
			System.out.println(riskName);
			queryRule.addEqual("riskName",riskName );
		}if(startDate != null){
			queryRule.addGreaterEqual("culDate", startDate);
		}if(endDate != null){
			queryRule.addLessEqual("culDate", endDate);
		}
		queryRule.addDescOrder("culDate");
		queryRule.addAscOrder("riskName");
		listUniversalRate = universalRateService.findUniversalByQueryRule(queryRule);
		return SUCCESS;
	}
	
	public String initUniversalQuery(){
		listUniversalRate = universalRateService.findNewUniversal();
		return SUCCESS;
	}
	
	public String initRiskName(){
		listRiskName = universalRateService.findUniqueRiskName();
		return SUCCESS;
	}
	
	public String loadNewUniversal(){
		newUniversalRate = universalRateService.findUniqNewUniversal();
		return SUCCESS;
	}
	
	public List<UniversalRate> getListUniversalRate() {
		return listUniversalRate;
	}

	public void setListUniversalRate(List<UniversalRate> listUniversalRate) {
		this.listUniversalRate = listUniversalRate;
	}

	public List<String> getListRiskName() {
		return listRiskName;
	}
	
	public void setListRiskName(List<String> listRiskName) {
		this.listRiskName = listRiskName;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public UniversalRate getNewUniversalRate() {
		return newUniversalRate;
	}

	public void setNewUniversalRate(UniversalRate newUniversalRate) {
		this.newUniversalRate = newUniversalRate;
	}
}