package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeOccupation;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeOccupationService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProInsuredOccupation;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductInsuredConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductRisk;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeProInsuredOccupationService;

public class GeProInsuredOccupationServiceSpringImpl extends GenericDaoHibernate<GeProductRisk, String> implements GeProInsuredOccupationService {
	
	@Autowired
	private GeOccupationService geOccupationService;
	
	/**
	 * �������б�����ְҵ����
	 * @param proInsuredOccupationList
	 */
	public void saveGeProInsuredOccupationAll (List<GeProInsuredOccupation> proInsuredOccupationList) {
		super.saveAll(proInsuredOccupationList);
	}
	
	/***
	 * ɾ�����б�����ְҵ
	 * @param proInsuredOccupationList
	 */
	public void delGeProInsuredOccupationAll (List<GeProInsuredOccupation> proInsuredOccupationList) {
		super.deleteAll(proInsuredOccupationList);
	}
	
	/***
	 * ��ѯ������ְҵ��
	 * @param geProductInsuredConfig
	 * @return
	 */
	public String findGeProInsuredOccupationTree(GeProductInsuredConfig geProductInsuredConfig) {
		StringBuffer str = new StringBuffer();
		str.append("<?xml version=\'1.0\' encoding=\'GBK\'?>");
		str.append("<tree id=\'0\' text=\'" + "������ְҵ����" +
		"\'>");
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addIsNull("parentOccupationCode");
		List<GeOccupation>  occupationList = geOccupationService.getGeOccupations(queryRule);
		List<GeProInsuredOccupation>  proInsuredOccupationList = findGeProInsuredOccupationByInsured(geProductInsuredConfig);
		for (int i = 0; i < occupationList.size(); i++) {
			GeOccupation occupation = occupationList.get(i);
				str.append("<item id=\'id_" + occupation.getOccupationCode() +
						"\' occupationCode=\'" + occupation.getOccupationCode() +
						"\' text=\'"
						+ occupation.getOccupationTypeName() + "\'>");
				str.append(findGeProInsuredOccupationTreeItem(occupation.getOccupationCode() ,proInsuredOccupationList));
				str.append("</item>");
		}
		str.append("</tree>");
		return str.toString();
	}
	
	/**
	 * ��ѯ������ְҵ��Item
	 * @param occupationCode
	 * @param proInsuredOccupationList
	 * @return
	 */
	public String findGeProInsuredOccupationTreeItem(String occupationCode, List proInsuredOccupationList) {
		StringBuffer str = new StringBuffer();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("parentOccupationCode", occupationCode);
		List<GeOccupation>  occupationList = geOccupationService.getGeOccupations(queryRule);
		for (int i = 0; i < occupationList.size(); i++) {
			GeOccupation occupation = occupationList.get(i);
			String childNodeXml = findGeProInsuredOccupationTreeItem(occupation.getOccupationCode() ,proInsuredOccupationList);
			if (childNodeXml.length() == 0 && proInsuredOccupationList.contains(occupation.getOccupationCode())) {
				str.append("<item id=\'id_" + occupation.getOccupationCode() +
					    "\' occupationCode=\'" + occupation.getOccupationCode() +
						"\' checked = \'1\' text=\'"
						+ occupation.getOccupationTypeName() + "\'>");
				str.append("</item>");
			} else {
				str.append("<item id=\'id_" + occupation.getOccupationCode() +
						"\' occupationCode=\'" + occupation.getOccupationCode() +
						"\' text=\'"
						+ occupation.getOccupationTypeName() + "\'>");
				str.append(childNodeXml);
				str.append("</item>");
			}
		}
		return str.toString();
	}
	
	/**
	 * ��ѯ�Ѿ����ñ�����ְҵ
	 * @param geProductInsuredConfig
	 * @return
	 */
	public List<GeProInsuredOccupation> findGeProInsuredOccupationByInsured(GeProductInsuredConfig geProductInsuredConfig) {
		StringBuffer hql = new StringBuffer("select insuredOccupation.occupationCode from GeProInsuredOccupation insuredOccupation where 1 = 1 ");
		hql.append("and insuredOccupation.geProductInsuredConfig.serialNo = ? ");
		return super.findByHql(hql.toString(), new String[]{geProductInsuredConfig.getSerialNo()});
	}
	
}
