/**  
 * @Title: GeBlackListServiceSpringImpl.java
 * @Package cn.com.sinosoft.ebusiness.service.user.personal.service.spring
 * @Description: TODO	黑名单管理接口实现类
 *   
 * @date 2011-8-29 下午05:30:56
 * @version V1.0  
 */ 
package cn.com.sinosoft.ebusiness.service.user.personal.service.spring;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeBlackList;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService;

public class GeBlackListServiceSpringImpl extends GenericDaoHibernate<GeBlackList, String>
		implements GeBlackListService {

	/* (非 Javadoc)
	 * Title: findList
	 * Description: 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @see cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService#findList(ins.framework.common.QueryRule, int, int)
	 */
	@Override
	@LocusTrace(setDesc="黑名单分页查询")
	public Page findList(QueryRule queryRule, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/* (非 Javadoc)
	 * Title: findByRule
	 * Description: 
	 * @param queryRule
	 * @return
	 * @see cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService#findByRule(ins.framework.common.QueryRule)
	 */
	@Override
	public GeBlackList findByRule(QueryRule queryRule) {
		// TODO Auto-generated method stub
		return super.findUnique(queryRule);
	}

	/* (非 Javadoc)
	 * Title: findById
	 * Description: 
	 * @param id
	 * @return
	 * @see cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService#findById(java.lang.String)
	 */
	@Override
	@LocusTrace(setDesc="根据主键查询黑名单")
	public GeBlackList findById(String id) {
		// TODO Auto-generated method stub
		return super.get(id);
	}

	/* (非 Javadoc)
	 * Title: modify
	 * Description: 
	 * @param geBlackList
	 * @see cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService#modify(cn.com.sinosoft.ebusiness.service.user.personal.domain.GeBlackList)
	 */
	@Override
	@LocusTrace(setDesc="修改黑名单")
	public void modify(GeBlackList geBlackList) {
		// TODO Auto-generated method stub
		super.update(geBlackList);
	}

	/* (非 Javadoc)
	 * Title: deleteById
	 * Description: 
	 * @param id
	 * @see cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService#deleteById(java.lang.String)
	 */
	@Override
	@LocusTrace(setDesc="删除单个黑名单")
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		super.deleteByPK(id);

	}

	/* (非 Javadoc)
	 * Title: deleteByList
	 * Description: 
	 * @param list
	 * @see cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService#deleteByList(java.util.List)
	 */
	@LocusTrace(setDesc="批量删除黑名单")
	public void deleteByList(List<GeBlackList> list){
		// TODO Auto-generated method stub
		super.deleteAll(list);
	}
	
	/* (非 Javadoc)
	 * Title: insert
	 * Description: 
	 * @param geBlackList
	 * @see cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService#insert(cn.com.sinosoft.ebusiness.service.user.personal.domain.GeBlackList)
	 */
	@Override
	@LocusTrace(setDesc="增加黑名单")
	public void insert(GeBlackList geBlackList) {
		// TODO Auto-generated method stub
		super.save(geBlackList);

	}

}
