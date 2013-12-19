package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProInsuredOccupation;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductInsuredConfig;

public interface GeProInsuredOccupationService {

	/**
	 * �������б�����ְҵ����
	 * @param proInsuredOccupationList
	 */
	public abstract void saveGeProInsuredOccupationAll(
			List<GeProInsuredOccupation> proInsuredOccupationList);

	/***
	 * ɾ�����б�����ְҵ
	 * @param proInsuredOccupationList
	 */
	public abstract void delGeProInsuredOccupationAll(
			List<GeProInsuredOccupation> proInsuredOccupationList);

	/***
	 * ��ѯ������ְҵ��
	 * @param geProductInsuredConfig
	 * @return
	 */
	public abstract String findGeProInsuredOccupationTree(
			GeProductInsuredConfig geProductInsuredConfig);

	/**
	 * ��ѯ�Ѿ����ñ�����ְҵ
	 * @param geProductInsuredConfig
	 * @return
	 */
	public abstract List<GeProInsuredOccupation> findGeProInsuredOccupationByInsured(
			GeProductInsuredConfig geProductInsuredConfig);

}
