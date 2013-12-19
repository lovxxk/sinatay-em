package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.spring;

import ins.framework.dao.GenericDaoHibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;

import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.GePortalInterfaceExtraService;
import cn.com.sinosoft.portalModule.interfacePortal.domain.GePortalInterfaceExtra;
import cn.com.sinosoft.portalModule.interfacePortal.domain.GePortalInterfaceExtraId;

public class GePortalInterfaceExtraServiceSpringImpl extends GenericDaoHibernate<GePortalInterfaceExtra, GePortalInterfaceExtraId> implements
		GePortalInterfaceExtraService {

	public Map<String, String> findParamFromType(String transType) {
		String sqlString = "select g.paramname,g.paramvalue from ge_portal_interface_extra g where g.transtype = ?";
		SQLQuery sqlQuery = super.getSession().createSQLQuery(sqlString);
		sqlQuery.setString(0, transType);
		List list = sqlQuery.list();
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			Object[] arr = (Object[])list.get(i);
			map.put((String)arr[0], (String)arr[1]);
		}
		return map;
	}

	public String findParamValueFromId(String transType, String paramName) {
		String sqlString = "select g.paramvalue from ge_portal_interface_extra g where g.transtype = ? and g.paramname = ?";
		SQLQuery sqlQuery = super.getSession().createSQLQuery(sqlString);
		sqlQuery.setString(0, transType);
		sqlQuery.setString(1, paramName);
		List list = sqlQuery.list();
		if (list != null && list.size() > 0) {
			return (String)list.get(0);
		} else return null;
	}

}
