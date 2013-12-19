package cn.com.sinosoft.ebusiness.common.bizConfig.service.spring;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeOccupation;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeOccupationService;

/**
 * 职业类别service实现类
 * @since 2011-09-08
 */
public class GeOccupationServiceSpringImpl extends GenericDaoHibernate<GeOccupation, String> implements GeOccupationService {

	/* (non-Javadoc)
	 * @see cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeOccupationService#getGeOccupations(ins.framework.common.QueryRule)
	 */
	@Override
	public List<GeOccupation> getGeOccupations(QueryRule queryRule) {
		try {
			List<GeOccupation> geOccupations = null;
			geOccupations = super.find(queryRule);
			return geOccupations;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public GeOccupation getGeOccupationsByCode(String occupationCode) {
		// TODO Auto-generated method stub
		try {
			 QueryRule queryRule = QueryRule.getInstance();
			 queryRule.addEqual("occupationCode", occupationCode);
			 GeOccupation geOccupation = super.findUnique(queryRule);
			return geOccupation;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
