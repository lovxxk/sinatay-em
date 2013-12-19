package cn.com.sinosoft.ebusiness.hospital.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.sinosoft.businessModule.hospitalManage.domain.HospitalManage;
import cn.com.sinosoft.businessModule.hospitalManage.service.facade.HospitalManageService;

import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

public class HospitalQueryAction extends Struts2Action {
	private static final long serialVersionUID = 5175257479015031807L;
	@Autowired
	private HospitalManageService hospitalManageService;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String province;
	private String city;
	private List<String> listProvince;
	private List<String> listCity;
	private String hosName;
	private List<HospitalManage> listHospitalManage;
	
	public String initProvince(){
		String sql = "select distinct province from hospitalmanage";
		
		List<Object> objectList = new ArrayList<Object>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, objectList.toArray());
		
		listProvince = new ArrayList<String>();
		for(int i = 0; i < list.size(); i++){
			listProvince.add(list.get(i).get("province").toString());
		}
		
		return SUCCESS;
	}
	
	public String initCity(){
		String sql = "select distinct city from hospitalmanage where province='"+province+"'";
		
		List<Object> objectList = new ArrayList<Object>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, objectList.toArray());
		
		listCity = new ArrayList<String>();
		for(int i = 0; i < list.size(); i++){
			listCity.add(list.get(i).get("city").toString());
		}
		
		return SUCCESS;
	}
	
	public String queryHospital(){
		QueryRule queryRule = QueryRule.getInstance();
		if(province != null && !"".equals(province)){
			queryRule.addEqual("province",province );
		}if(city != null && !"".equals(city)){
			queryRule.addEqual("city",city );
		}if(hosName != null && !"".equals(hosName)){
			queryRule.addLike("hosName", ("%"+hosName+"%"));
		}
		listHospitalManage = hospitalManageService.findHospitalByQueryRule(queryRule);
		return SUCCESS;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHosName() {
		return hosName;
	}

	public void setHosName(String hosName) {
		this.hosName = hosName;
	}

	public List<HospitalManage> getListHospitalManage() {
		return listHospitalManage;
	}

	public void setListHospitalManage(List<HospitalManage> listHospitalManage) {
		this.listHospitalManage = listHospitalManage;
	}

	public List<String> getListProvince() {
		return listProvince;
	}

	public void setListProvince(List<String> listProvince) {
		this.listProvince = listProvince;
	}

	public List<String> getListCity() {
		return listCity;
	}

	public void setListCity(List<String> listCity) {
		this.listCity = listCity;
	}
}