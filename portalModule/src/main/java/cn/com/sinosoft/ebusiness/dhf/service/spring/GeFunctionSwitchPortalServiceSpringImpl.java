/**
 * 
 */
package cn.com.sinosoft.ebusiness.dhf.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.dhf.domain.GeFunctionSwitchPortal;
import cn.com.sinosoft.ebusiness.dhf.service.fascade.GeFunctionSwitchPortalService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BizConfigCommonException;

/**
 *
 */
public class GeFunctionSwitchPortalServiceSpringImpl extends
		GenericDaoHibernate<GeFunctionSwitchPortal, Long>  implements GeFunctionSwitchPortalService {

	@Override
	@LocusTrace(setDesc="��ҳ��ѯ���ܿ���")
	public Page findGeFunctionSwitchPortal(QueryRule queryRule, int pageNo,int pageSize)  throws BizConfigCommonException{
		Page page = null;
		try {
			page = super.find(queryRule, pageNo, pageSize);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("��ҳ��ѯ���ܿ����쳣", e);
		}
		return page;
	}

	@Override
	@LocusTrace(setDesc="��ѯ���ܿ���")
	public GeFunctionSwitchPortal findGeFunctionSwitchPortal(QueryRule queryRule)throws BizConfigCommonException{
		GeFunctionSwitchPortal GeFunctionSwitchPortal = new GeFunctionSwitchPortal();
		try {
			GeFunctionSwitchPortal = super.findUnique(queryRule);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("��ѯ���ܿ����쳣", e);
		}
		return GeFunctionSwitchPortal;
	}

	@Override
	@LocusTrace(setDesc="�޸Ĺ��ܿ���")
	public boolean updateGeFunctionSwitchPortal(GeFunctionSwitchPortal GeFunctionSwitchPortal)
			 throws BizConfigCommonException{
		try {
			GeFunctionSwitchPortal update = super.findUnique("functiontId", GeFunctionSwitchPortal.getFunctiontId());
			BeanUtils.copyProperties(GeFunctionSwitchPortal, update, new String[]{"functiontId"});
			super.update(update);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("�޸Ĺ��ܿ����쳣", e);
		}
		
		return true;
	}

	@Override
	@LocusTrace(setDesc="���湦�ܿ���")
	public boolean save(GeFunctionSwitchPortal GeFunctionSwitchPortal)  throws BizConfigCommonException{
		try {
			super.save(GeFunctionSwitchPortal);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("���湦�ܿ����쳣", e);
		}
		
		return true;
	}

	@Override
	@LocusTrace(setDesc="ɾ�����ܿ���")
	public boolean delete(String functiontId)  throws BizConfigCommonException{
		try {
			super.deleteByPK(GeFunctionSwitchPortal.class, functiontId);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("ɾ�����ܿ����쳣", e);
		}
		
		return true;
	}

	@Override
	@LocusTrace(setDesc="����ɾ�����ܿ���")
	public boolean deleteAllGeFunctionSwitchPortal(String keys)  throws BizConfigCommonException{
		try {
			Session session = null;
			String deleteSql = "delete from GE_FUNCTION_SWITCH where functiontId in ('" + keys.replaceAll(",", "','") + "')";
			session = super.getSession();
			SQLQuery query = session.createSQLQuery(deleteSql);
			query.executeUpdate();
			session.flush();
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("����ɾ�����ܿ����쳣", e);
		}
		return true;
	}

	@Override
	@LocusTrace(setDesc="����ɾ�����ܿ���")
	public boolean deleteAllGeFunctionSwitchPortal(List<GeFunctionSwitchPortal> GeFunctionSwitchPortalList)  throws BizConfigCommonException{
		try {
			super.deleteAll(GeFunctionSwitchPortalList);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("����ɾ�����ܿ����쳣", e);
		}
		return true;
	}
	
	@Override
	@LocusTrace(setDesc="�жϸ������ܿ��صĿ���״̬")
	public boolean isSwitchOpen(String functiontId, String portalChannel){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("functiontId", functiontId);
		queryRule.addEqual("portalChannel", portalChannel);
		GeFunctionSwitchPortal GeFunctionSwitchPortal;
		try {
			GeFunctionSwitchPortal = findGeFunctionSwitchPortal(queryRule);
		} catch (Exception e) {
			return false;
		}
		if (GeFunctionSwitchPortal == null)
			return false;
		if ("1".equals(GeFunctionSwitchPortal.getStatus())) {
			/** ���Կ���״̬(0 ��Ч 1 ��Ч 2δ��ͨ) */
			return true;
		} else {
			return false;
		}
	}

}
