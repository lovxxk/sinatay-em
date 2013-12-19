package cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.spring;

import java.util.List;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProWebFlowPageElement;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProduct;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade.GeSaleProductService;

public class GeSaleProductServiceSpringImpl extends GenericDaoHibernate<GeSaleProduct, String> 
	implements GeSaleProductService{

	public void addGeSaleProduct() {
		
	}

	public GeSaleProduct findByCode(String code) {
		QueryRule q = QueryRule.getInstance();
		q.addEqual("coreProductCode", code);
		List<GeSaleProduct> l = super.find(q);
		if(l!=null && l.size()==1)
			return l.get(0);
		return null;
	}

	public void deleteByCode(String code) {
		// TODO Auto-generated method stub
		GeSaleProduct p = findByCode(code);
		if(p!=null)
		super.delete(p);
	}

	public List<GeSaleProduct> findByQuery(QueryRule query) {
		// TODO Auto-generated method stub
		return super.find(query);
	}

	public boolean getStatusBycode(String productCode) {
		String sql="select t.status from ge_sale_pro_webflowpageelement t where t.coreproductcode ='"+productCode+"'and t.elementcode = 'productRule'";
		List list=super.getSession().createSQLQuery(sql).list();
		if(list.get(0).equals("1")){
			return true;
		}else{
			return false;
		}
	}
}
