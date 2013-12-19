package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import java.util.ArrayList;
import java.util.List;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductInformBook;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeProductInformBookService;

public class GeProductInformBookServiceSpringImpl extends
		GenericDaoHibernate<GeProductInformBook, String> implements
		GeProductInformBookService {

	public List<GeProductInformBook> findInformBooks(QueryRule queryRule) {
		// TODO Auto-generated method stub
		return super.find(queryRule);
	}
	
	public List findInformBooks(String productCode){
		String sql = "select t.informOrder,t.informcontent,t.informoption from ge_product_informbook t where t.coreproductcode = ? order by t.informorder ";
		return super.findBySql(sql, productCode);
	}

	public GeProductInformBook findById(String id) {
		QueryRule q = QueryRule.getInstance();
		q.addEqual("serialNo", id);
		List<GeProductInformBook> list = super.find(q);
		if(list.size()==1)
			return list.get(0);
		return null;
	}

}
