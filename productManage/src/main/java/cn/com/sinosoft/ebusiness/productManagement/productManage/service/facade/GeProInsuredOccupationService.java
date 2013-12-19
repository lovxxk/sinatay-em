package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProInsuredOccupation;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductInsuredConfig;

public interface GeProInsuredOccupationService {

	/**
	 * 保存所有被保人职业配置
	 * @param proInsuredOccupationList
	 */
	public abstract void saveGeProInsuredOccupationAll(
			List<GeProInsuredOccupation> proInsuredOccupationList);

	/***
	 * 删除所有被保人职业
	 * @param proInsuredOccupationList
	 */
	public abstract void delGeProInsuredOccupationAll(
			List<GeProInsuredOccupation> proInsuredOccupationList);

	/***
	 * 查询被保人职业树
	 * @param geProductInsuredConfig
	 * @return
	 */
	public abstract String findGeProInsuredOccupationTree(
			GeProductInsuredConfig geProductInsuredConfig);

	/**
	 * 查询已经配置被保人职业
	 * @param geProductInsuredConfig
	 * @return
	 */
	public abstract List<GeProInsuredOccupation> findGeProInsuredOccupationByInsured(
			GeProductInsuredConfig geProductInsuredConfig);

}
