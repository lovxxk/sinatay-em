package cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.spring;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProInsuredOccupation;
import cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade.GeSaleProInsuredOccupationService;

/**
 * 职业类别service实现类
 *  
 * @since 2011-09-08
 */
public class GeSaleProInsuredOccupationServiceSpringImpl 
		extends GenericDaoHibernate<GeSaleProInsuredOccupation, String> 
		implements GeSaleProInsuredOccupationService {


	public List<GeSaleProInsuredOccupation> findGeOccupations(String insuredCode) {
		String querySql1 = "select * from ge_occupation m where m.parentoccupationcode " +
				"is null and m.occupationcode in (select p.occupationcode from  GE_SALE_PRO_INSURED_OCCUPATION p " +
				"where p.insuredserialno =? ) order by m.occupationcode";
		List<GeSaleProInsuredOccupation> geOccupations = super.findBySql(querySql1, new Object[]{insuredCode});
		return geOccupations;
	}

	public List<GeSaleProInsuredOccupation> findGeOccupationsM(
			String insuredCode,String occupationCode) {
		String querySql1 = "select * from ge_occupation m where m.parentoccupationcode " +
				"= ? and m.occupationcode in (select p.occupationcode from  GE_SALE_PRO_INSURED_OCCUPATION p " +
				"where p.insuredserialno =? ) order by m.occupationcode";
		List<GeSaleProInsuredOccupation> geOccupations = super.findBySql(querySql1, new Object[]{occupationCode,insuredCode});
		return geOccupations;
	}

}
