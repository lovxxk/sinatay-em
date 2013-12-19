package cn.com.sinosoft.ebusiness.occupation.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.sinosoft.ebusiness.occupation.service.facade.OccupationService;

public class OccupationServiceSpringImpl implements OccupationService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String schema = "";
	
	List<Object> objectList = new ArrayList<Object>();
	
	/**
	 * ���ݹؼ���ģ����ѯְҵ��Ϣ
	 * @param name �ؼ���
	 * @return 
	 */
	public List<Map<String, Object>> findOccupationInfoByLikeName(String keyword) {
		StringBuffer sql = new StringBuffer("select ");
		sql.append("t.OCCUPATIONCODE,");
		sql.append("t.OCCUPATIONNAME");
		sql.append(" from ");
//		sql.append(schema);
		sql.append("LDOCCUPATION t where t.OCCUPATIONNAME like");
		sql.append(" '%"+keyword+"%'");
		
		return jdbcTemplate.queryForList(sql.toString(), objectList.toArray());
	}

}
