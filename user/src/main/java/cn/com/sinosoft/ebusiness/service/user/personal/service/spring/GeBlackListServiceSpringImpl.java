/**  
 * @Title: GeBlackListServiceSpringImpl.java
 * @Package cn.com.sinosoft.ebusiness.service.user.personal.service.spring
 * @Description: TODO	����������ӿ�ʵ����
 *   
 * @date 2011-8-29 ����05:30:56
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

	/* (�� Javadoc)
	 * Title: findList
	 * Description: 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @see cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService#findList(ins.framework.common.QueryRule, int, int)
	 */
	@Override
	@LocusTrace(setDesc="��������ҳ��ѯ")
	public Page findList(QueryRule queryRule, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/* (�� Javadoc)
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

	/* (�� Javadoc)
	 * Title: findById
	 * Description: 
	 * @param id
	 * @return
	 * @see cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService#findById(java.lang.String)
	 */
	@Override
	@LocusTrace(setDesc="����������ѯ������")
	public GeBlackList findById(String id) {
		// TODO Auto-generated method stub
		return super.get(id);
	}

	/* (�� Javadoc)
	 * Title: modify
	 * Description: 
	 * @param geBlackList
	 * @see cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService#modify(cn.com.sinosoft.ebusiness.service.user.personal.domain.GeBlackList)
	 */
	@Override
	@LocusTrace(setDesc="�޸ĺ�����")
	public void modify(GeBlackList geBlackList) {
		// TODO Auto-generated method stub
		super.update(geBlackList);
	}

	/* (�� Javadoc)
	 * Title: deleteById
	 * Description: 
	 * @param id
	 * @see cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService#deleteById(java.lang.String)
	 */
	@Override
	@LocusTrace(setDesc="ɾ������������")
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		super.deleteByPK(id);

	}

	/* (�� Javadoc)
	 * Title: deleteByList
	 * Description: 
	 * @param list
	 * @see cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService#deleteByList(java.util.List)
	 */
	@LocusTrace(setDesc="����ɾ��������")
	public void deleteByList(List<GeBlackList> list){
		// TODO Auto-generated method stub
		super.deleteAll(list);
	}
	
	/* (�� Javadoc)
	 * Title: insert
	 * Description: 
	 * @param geBlackList
	 * @see cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeBlackListService#insert(cn.com.sinosoft.ebusiness.service.user.personal.domain.GeBlackList)
	 */
	@Override
	@LocusTrace(setDesc="���Ӻ�����")
	public void insert(GeBlackList geBlackList) {
		// TODO Auto-generated method stub
		super.save(geBlackList);

	}

}
