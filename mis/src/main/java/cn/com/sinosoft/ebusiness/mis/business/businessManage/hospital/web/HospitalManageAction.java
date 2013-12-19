package cn.com.sinosoft.ebusiness.mis.business.businessManage.hospital.web;

import java.util.Date;
import java.util.List;

import cn.com.sinosoft.businessModule.hospitalManage.domain.HospitalManage;
import cn.com.sinosoft.businessModule.hospitalManage.service.facade.HospitalManageService;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;
import cn.com.sinosoft.util.encode.JsonBinder;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

public class HospitalManageAction extends Struts2Action{
	
	private static final long serialVersionUID = 1L;
	
	private Page page;
	private HospitalManageService hospitalManageService;
	private HospitalManage hospital;
	private List<HospitalManage> hospitalList;
	private List<String> listProvince;
	
	private String flag;//标志
	private String message;//信息
	
	public String addHospital(){
		try{
			flag =hospitalManageService.addHospital(hospital);
			if("1".equals(flag)){
				flag = "1";
				message = "定点医院新建成功";
			}else if("2".equals(flag)){
				flag = "0";
				message = "已存在该定点医院，新建失败";
			}
		}catch(Exception e){
			flag = "0";
			message = "医院创建失败";
		}
		
		return SUCCESS;
	}
	
	public String findHospital() {
		//QueryRule queryRule = QueryRule.getInstance();
		//hospitalList = hospitalManageService.findHospitalByQueryRule(queryRule);
		listProvince = hospitalManageService.findUniqueProvince();
		return SUCCESS;
	}
	
	public String findHospital2() {
		pageNo = (pageNo == 0 ? 1 : pageNo);
		pageSize = (pageSize == 0 ? 20 : pageSize);

		page = hospitalManageService.getHospitalPage(hospital, pageNo, pageSize);
		
		return SUCCESS;
	}
	
	public String viewHospital(){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("serialNo", hospital.getSerialNo());
		hospital = hospitalManageService.findHospitalByQueryRule(queryRule).get(0);
		
		return SUCCESS;
	}
	
	public String prepareUpdateHospital(){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("serialNo", hospital.getSerialNo());
		hospital = hospitalManageService.findHospitalByQueryRule(queryRule).get(0);
		
		return SUCCESS;
	}
	
	public String updateHospital(){	
		try{
			hospitalManageService.updateHospital(hospital);
			flag = "1";
			message = "医院更新成功";
		}catch(Exception e){
			flag = "0";
			message = "医院更新失败";
		}
		return SUCCESS;
	}
	public String deleteHospital(){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("serialNo", hospital.getSerialNo());
		if(hospitalManageService.findHospitalByQueryRule(queryRule).size() == 0){
			flag = "0";
			message = "删除失败";
		}else{
			hospitalManageService.deleteHospital(hospital);
			flag = "1";
			message = "删除成功";
		}
		return SUCCESS;
	}
	
	/**
	 * 查询省市
	 * @return
	 */
	public String loadArea(){
		String province = super.getRequest().getParameter("province");
		List hospitals = hospitalManageService.findArea(province);
		super.render(JsonBinder.buildNonNullBinder().toJson(hospitals), "application/json;charset=GBK");
		return NONE;
	}

	public HospitalManageService getHospitalManageService() {
		return hospitalManageService;
	}

	public void setHospitalManageService(HospitalManageService hospitalManageService) {
		this.hospitalManageService = hospitalManageService;
	}

	public HospitalManage getHospital() {
		return hospital;
	}

	public void setHospital(HospitalManage hospital) {
		this.hospital = hospital;
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

	public List<HospitalManage> getHospitalList() {
		return hospitalList;
	}

	public void setHospitalList(List<HospitalManage> hospitalList) {
		this.hospitalList = hospitalList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<String> getListProvince() {
		return listProvince;
	}

	public void setListProvince(List<String> listProvince) {
		this.listProvince = listProvince;
	}
}
