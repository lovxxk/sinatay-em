package cn.com.sinosoft.ebusiness.hospital.service.facade;

import java.util.List;
import java.util.Map;

public interface HospitalService {
	
	/**
	 * 根据关键字模糊查询医院信息
	 * @param keyword 关键字
	 * @return 
	 */
	public abstract List<Map<String, Object>> findHospitalInfoByLikeName(String keyword);
}
