package cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.spring;

import ins.framework.dao.GenericDaoHibernate;

import java.util.List;


import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProWebFlowPageElement;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade.GeSaleProWebFlowPageElementService;

public class GeSaleProWebFlowPageElementServiceSpringImpl extends
		GenericDaoHibernate<GeSaleProWebFlowPageElement, String> implements
		GeSaleProWebFlowPageElementService {
	
	public List<String> getElementCodesByOrder(String productCode,String flowCode,String pageCode){
		String sql = "select tb3.elementcode from GE_SALE_PRO_WEBFLOW tb1,GE_SALE_PRO_WEBFLOWPAGE tb2,GE_SALE_PRO_WEBFLOWPAGEELEMENT tb3 "+
			" where tb1.coreproductcode = ? and tb1.flowcode = ? "+
			" and tb2.pagecode = ? and tb1.serialno = tb2.webflowsercialno "+
			" and tb1.coreproductcode = tb2.coreproductcode and tb2.serialno = tb3.flowpageserialno "+
			" and tb2.coreproductcode = tb3.coreproductcode order by tb3.seqindex";
		return super.findBySql(sql, productCode,flowCode,pageCode);
	}	
}
