package cn.com.sinosoft.ebusiness.dhf.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.sinosoft.ebusiness.dhf.domain.TakeATaxi;
import cn.com.sinosoft.ebusiness.dhf.service.fascade.TakeATaxiService;
import ins.framework.dao.GenericDaoHibernate;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TakeATaxiServiceImpl  extends GenericDaoHibernate<TakeATaxi, String> implements TakeATaxiService{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 添加打的记录
	 * @param insurancePolicy
	 */
	public String addTakeATaxiService(TakeATaxi takeATaxi) {
		super.save(takeATaxi);
		return takeATaxi.getSerialNo();
	}
}
