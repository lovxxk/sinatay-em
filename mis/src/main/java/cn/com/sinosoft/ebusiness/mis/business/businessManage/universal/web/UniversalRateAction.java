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
	
	private String flag;//��־
	private String message;//��Ϣ
	
	public String addUniversal(){
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("riskName", universal.getRiskName());
			queryRule.addEqual("culDate", universal.getCulDate());
			universalList = universalRateService.findUniversalByQueryRule(queryRule);
			if(universalList.size() != 0){
				flag = "0";
				message = "�ò�Ʒ������ͬ�����յ�����";
				return SUCCESS;
			}
			flag =universalRateService.addUniversal(universal);
			if("1".equals(flag)){
				flag = "1";
				message = "�����������½��ɹ�";
			}else if("2".equals(flag)){
				flag = "0";
				message = "�Ѵ��ڸ����������ʣ��½�ʧ��";
			}
		}catch(Exception e){
			flag = "0";
			message = "���ʴ���ʧ��";
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
			message = "���ʸ��³ɹ�";
		}catch(Exception e){
			flag = "0";
			message = "���ʸ���ʧ��";
		}
		return SUCCESS;
	}
	public String deleteUniversal(){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("serialNo", universal.getSerialNo());
		if(universalRateService.findUniversalByQueryRule(queryRule).size() == 0){
			flag = "0";
			message = "ɾ��ʧ��";
		}else{
			universalRateService.deleteUniversal(universal);
			flag = "1";
			message = "ɾ���ɹ�";
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
