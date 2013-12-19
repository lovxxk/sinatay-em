package cn.com.sinosoft.businessModule.hospitalManage.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.businessModule.hospitalManage.domain.HospitalManage;

public interface HospitalManageService {
	public abstract String addHospital(HospitalManage hospitalManage);
	public abstract List<HospitalManage> findHospitalByQueryRule(
			QueryRule queryRule);
	public abstract void updateHospital(HospitalManage hospitalManage);
	public abstract void deleteHospital(HospitalManage hospitalManage);
	public abstract Page getHospitalPage(HospitalManage hospitalManage, int pageNo, int pageSize);
	List<String> findUniqueProvince();
	public abstract List findArea(String province);
}