package cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.spring;

import java.util.List;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProInformbook;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade.GeSaleProInformBookService;

public class GeSaleProInformBookServiceSpringImpl extends
		GenericDaoHibernate<GeSaleProInformbook, String> implements
		GeSaleProInformBookService {

	public List<GeSaleProInformbook> findInformBooks(QueryRule queryRule) {
		// TODO Auto-generated method stub
		return super.find(queryRule);
	}
	
	public List findInformBooks(String productCode){
		String sql = "select t.informOrder,t.informcontent,t.informoption from GE_SALE_PRO_INFORMBOOK t where t.coreproductcode = ? order by t.informorder ";
		return super.findBySql(sql, productCode);
	}

}
