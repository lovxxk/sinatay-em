package cn.com.sinosoft.ebusiness.occupation.service.facade;

import java.util.List;
import java.util.Map;

public interface OccupationService {
	
	/**
	 * ���ݹؼ���ģ����ѯְҵ��Ϣ
	 * @param keyword �ؼ���
	 * @return 
	 */
	public abstract List<Map<String, Object>> findOccupationInfoByLikeName(String keyword);
}
