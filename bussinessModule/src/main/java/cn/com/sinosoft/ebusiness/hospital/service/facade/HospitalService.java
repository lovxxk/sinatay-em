package cn.com.sinosoft.ebusiness.hospital.service.facade;

import java.util.List;
import java.util.Map;

public interface HospitalService {
	
	/**
	 * ���ݹؼ���ģ����ѯҽԺ��Ϣ
	 * @param keyword �ؼ���
	 * @return 
	 */
	public abstract List<Map<String, Object>> findHospitalInfoByLikeName(String keyword);
}
