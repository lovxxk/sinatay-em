package cn.com.sinosoft.ebusiness.hospital.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.sinosoft.ebusiness.hospital.service.facade.HospitalService;

public class HospitalServiceSpringImpl implements HospitalService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String schema = "";
	
	List<Object> objectList = new ArrayList<Object>();
	
	/**
	 * 根据关键字模糊查询职业信息
	 * @param name 关键字
	 * @return 
	 */
	public List<Map<String, Object>> findHospitalInfoByLikeName(String keyword) {
		StringBuffer sql = new StringBuffer("select ");
		sql.append("t.hospitalcode,");
		sql.append("t.hospitalname");
		sql.append(" from ");
		sql.append("GE_HOSPITAL t where t.hospitalname like");
		sql.append(" '%"+keyword+"%'");
		
		return jdbcTemplate.queryForList(sql.toString(), objectList.toArray());
	}

}
