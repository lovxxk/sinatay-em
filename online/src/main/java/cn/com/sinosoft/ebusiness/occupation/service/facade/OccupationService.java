package cn.com.sinosoft.ebusiness.occupation.service.facade;

import java.util.List;
import java.util.Map;

public interface OccupationService {
	
	/**
	 * 根据关键字模糊查询职业信息
	 * @param keyword 关键字
	 * @return 
	 */
	public abstract List<Map<String, Object>> findOccupationInfoByLikeName(String keyword);
}
