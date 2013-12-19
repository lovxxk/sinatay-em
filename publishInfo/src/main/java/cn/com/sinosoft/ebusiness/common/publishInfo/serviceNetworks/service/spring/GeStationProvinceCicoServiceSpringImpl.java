package cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.domain.GeStationInfo;
import cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.domain.GeStationProvinceCico;
import cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.service.facade.GeStationInfoService;
import cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.service.facade.GeStationProvinceCicoService;


public class GeStationProvinceCicoServiceSpringImpl 
	extends GenericDaoHibernate<GeStationProvinceCico, String> implements GeStationProvinceCicoService{
	
	private GeStationInfoService geStationInfoService;
	
	public GeStationInfoService getGeStationInfoService() {
		return geStationInfoService;
	}

	public void setGeStationInfoService(GeStationInfoService geStationInfoService) {
		this.geStationInfoService = geStationInfoService;
	}

	@Override
	public String getServiceNetworkXMLTree(String id) {
		StringBuffer treeXML = new StringBuffer();
		treeXML.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		treeXML.append("<tree id=\""+id+"\">");
		/**对传进来的ID进行过滤*/
		String parentId;
		String businessArea = null;
		if (id.contains("_"))  {
			String[] ids = id.split("_");
			if (StringUtils.isBlank(businessArea)) {
				businessArea = ids[0];
			}
			parentId = ids[1].replace("lastCity","").replace("serviceNetWork", "");
		} else {
			parentId = id.replace("lastCity","").replace("serviceNetWork", "");
		}
		
		
		/**查询子节点*/
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("parentID", parentId);
		queryRule.addAscOrder("parentID");
		queryRule.addAscOrder("obid");
		List<GeStationProvinceCico> list = super.find(queryRule);
//		StringBuffer propertyInsurance = new StringBuffer("<item id=\"CX\" child=\"1\" text=\"财险\">");
		StringBuffer lifeInsurance = new StringBuffer("<item id=\"SX\" child=\"1\" text=\"寿险\">");
//		StringBuffer pension = new StringBuffer("<item id=\"JG\" child=\"1\" text=\"养老\" >");
		StringBuffer str = new StringBuffer();
		/**生成xml*/
		if(list.size() > 0){
			/**遍历所属城市信息*/
			for (int i = 0; i < list.size(); i++) {
				GeStationProvinceCico geStationProvinceCico = (GeStationProvinceCico) list.get(i);
				int hcNum = hasChild(geStationProvinceCico.getObid(), businessArea);
				if ("0".equals(id)) {
					if(hcNum != 2){
						if(hcNum == 0){
							str.append("<item id=\"" + geStationProvinceCico.getObid() + "\" child=\"1\" text=\"" + geStationProvinceCico.getName() + "\">");
						}else if(hcNum == 1){
							str.append("<item id=\"lastCity" + geStationProvinceCico.getObid() + "\" child=\"1\" text=\"" + geStationProvinceCico.getName() + "\">");
						}
					}else{
						str.append("<item id=\"lastCity" + geStationProvinceCico.getObid() + "\" child=\"0\" text=\"" + geStationProvinceCico.getName() + "\">");
					}
				} else {
					if(hcNum != 2){
						if(hcNum == 0){
							str.append("<item id=\"" + businessArea + "_" + geStationProvinceCico.getObid() + "\" child=\"1\" text=\"" + geStationProvinceCico.getName() + "\">");
						}else if(hcNum == 1){
							str.append("<item id=\"" + businessArea + "_lastCity"  + geStationProvinceCico.getObid() + "\" child=\"1\" text=\"" + geStationProvinceCico.getName() + "\">");
						}
					}else{
						str.append("<item id=\"" + businessArea + "_lastCity" + geStationProvinceCico.getObid() + "\" child=\"0\" text=\"" + geStationProvinceCico.getName() + "\">");
					}
					
				}
				
				str.append("<userdata name=\"ud_block\">ud_data</userdata>");
				str.append("</item>");
			}
		} else {
			/**查询该直属城市的服务网点*/
			QueryRule qRule = QueryRule.getInstance();
			if (StringUtils.isNotBlank(businessArea)) {
				qRule.addSql(" type='" + businessArea + "' and (PROVINCE = '" + parentId + "' or CITY = '" + parentId + "' or TOWN = '" + parentId + "') ");
			} else {
				qRule.addSql(" PROVINCE = '" + parentId + "' or CITY = '" + parentId + "' or TOWN = '" + parentId + "' ");
			}
			qRule.addAscOrder("province");
			qRule.addAscOrder("city");
			qRule.addAscOrder("town");
			qRule.addAscOrder("obid");
			List<GeStationInfo> geStationInfoList = geStationInfoService.findAllGeStationInfo(qRule);
			for (int i = 0; i < geStationInfoList.size(); i++) {
				if ("0".equals(id)) {
					GeStationInfo geStationInfo = (GeStationInfo) geStationInfoList.get(i);
					str.append("<item id=\"serviceNetWork" + geStationInfo.getObid() + "\" child=\"0\" text=\"" + geStationInfo.getUnitName() + "\">");
					str.append("<userdata name=\"ud_block\">ud_data</userdata>");
					str.append("</item>");
				} else {
					GeStationInfo geStationInfo = (GeStationInfo) geStationInfoList.get(i);
					str.append("<item id=\""+ businessArea + "_serviceNetWork" + geStationInfo.getObid() + "\" child=\"0\" text=\"" + geStationInfo.getUnitName() + "\">");
					str.append("<userdata name=\"ud_block\">ud_data</userdata>");
					str.append("</item>");
				}
			
			}
		}
		if ("0".equals(id)) {
//			propertyInsurance.append(str.toString().replaceAll("id=\"", "id=\"CX_"));
			lifeInsurance.append(str.toString().replaceAll("id=\"", "id=\"SX_"));
//			pension.append(str.toString().replaceAll("id=\"", "id=\"JG_"));
//			propertyInsurance.append("</item>");
			lifeInsurance.append("</item>");
//			pension.append("</item>");
//			treeXML.append(propertyInsurance);
			treeXML.append(lifeInsurance);
//			treeXML.append(pension);
		} else {
			treeXML.append(str);
		} 
		treeXML.append("</tree>");
		return treeXML.toString();
	}
	
	/**
	 * 判断节点是否有子节点
	 * @param id
	 * @param businessArea TODO
	 * @return 0-城市中的父节点，1-城市中叶子节点且有服务网点，2-城市中叶子节点且没有服务网点
	 */
	private int hasChild(String id, String businessArea){
		Session session = super.getSession();
		String querySql1 = "select count(1) sum from GE_STATION_PROVINCE_CICO t where t.PARENT_ID = ?";
		List list1  = session.createSQLQuery(querySql1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, id).list();
		session.flush();
		Map map1 = (Map)list1.get(0);
		BigDecimal sum1 = (BigDecimal)map1.get("SUM");
		if( sum1.intValue() > 0){
			return 0;
		}else{
			/**查服务网点*/
			
			String querySql2 = "";
			if (StringUtils.isNotBlank(businessArea)) {
				querySql2 = "select count(1) sum from GE_STATION_INFO t where type ='" + businessArea +  "' and (PROVINCE = ? or CITY = ? or TOWN = ? )";
			} else {
				querySql2 = "select count(1) sum from GE_STATION_INFO t where PROVINCE = ? or CITY = ? or TOWN = ?";
			}
			List list2  = session.createSQLQuery(querySql2).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
							.setString(0, id).setString(1, id).setString(2, id).list();
			session.flush();
			Map map2 = (Map)list2.get(0);
			BigDecimal sum2 = (BigDecimal)map2.get("SUM");
			if( sum2.intValue() > 0){
				return 1;
			}else{
				return 2;
			}
		}
	}
	
	public GeStationProvinceCico findgeGeStationProvinceCico(QueryRule queryRule){
		return super.findUnique(queryRule);
	}

	@Override
	public List<GeStationProvinceCico> findProvinceList(QueryRule queryRule) {
		// TODO Auto-generated method stub
		return super.find(queryRule);
	}
}
