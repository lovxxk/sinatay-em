/**
 * 
 */
package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGroup;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGrouptype;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeGroupTypeService;

/**
 * �û������ͷ�����
 *  
 *
 */
public class GeGroupTypeServiceSpringImpl extends GenericDaoHibernate<GeGrouptype, String> implements GeGroupTypeService {

	@Override
	@LocusTrace(setDesc="�жϸ������Ƿ����")
	public boolean existsType(String pk) {
		boolean flag = false;
		try {
			String sql = "select * from ge_grouptype where grouptypeid =?";
			Session session = super.getSession();
			List list  = session.createSQLQuery(sql).setParameter(0, pk).list();
			if(list.size() > 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	@LocusTrace(setDesc="�����û�������")
	public boolean saveType(GeGrouptype geGrouptype) {
		boolean flag = false;
		try {
			Session session = super.getSession();
			session.save(geGrouptype);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@LocusTrace(setDesc="��ҳ��ѯ�û�������")
	public Page findGeGroupType(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}
	
	@Override
	@LocusTrace(setDesc="������ѯ�û�������")
	public GeGrouptype findGeGroupType(QueryRule queryRule) {
		return super.findUnique(queryRule);
	}
	
	@Override
	@LocusTrace(setDesc="�����û�������")
	public boolean updateType(GeGrouptype obj) {
		try{
			super.update(obj);
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	@Override
	@LocusTrace(setDesc="������ѯ�û�������")
	public List<GeGrouptype> findGeGroupTypes(QueryRule queryRule) {
		List<GeGrouptype> list = new ArrayList<GeGrouptype>();
		try {
			list = super.find(queryRule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@LocusTrace(setDesc="ɾ���û�������")
	public int delete(String key) {
		int flag = 0;
		try {
			if(isTypeUsed(key)){
				flag = 1;//�����������ڱ�ʹ��
			}else{
			Session session = null;
			String deleteSql = "delete from ge_grouptype where grouptypeid = '" + key +"'";
			session = super.getSession();
			SQLQuery query = session.createSQLQuery(deleteSql);
			query.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	private boolean isTypeUsed(String key){
		boolean flag = false;
		try {
			String sql = "select count(1) count from ge_group where grouptypeid = '" + key +"'";
			Session session = super.getSession();
			List list1  = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			Map map1 = (Map)list1.get(0);
			BigDecimal sum1 = (BigDecimal)map1.get("COUNT");
			if( sum1.intValue() > 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
