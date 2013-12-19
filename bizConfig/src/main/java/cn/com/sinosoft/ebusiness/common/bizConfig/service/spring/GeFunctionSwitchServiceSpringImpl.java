/**
 * 
 */
package cn.com.sinosoft.ebusiness.common.bizConfig.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeFunctionSwitch;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeFunctionSwitchService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BizConfigCommonException;

/**
 *
 */
public class GeFunctionSwitchServiceSpringImpl extends
		GenericDaoHibernate<GeFunctionSwitch, Long>  implements GeFunctionSwitchService {

	@Override
	@LocusTrace(setDesc="分页查询功能开关")
	public Page findGeFunctionSwitch(QueryRule queryRule, int pageNo,int pageSize)  throws BizConfigCommonException{
		Page page = null;
		try {
			page = super.find(queryRule, pageNo, pageSize);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("分页查询功能开关异常", e);
		}
		return page;
	}

	@Override
	@LocusTrace(setDesc="查询功能开关")
	public GeFunctionSwitch findGeFunctionSwitch(QueryRule queryRule)throws BizConfigCommonException{
		GeFunctionSwitch geFunctionSwitch = new GeFunctionSwitch();
		try {
			geFunctionSwitch = super.findUnique(queryRule);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("查询功能开关异常", e);
		}
		return geFunctionSwitch;
	}

	@Override
	@LocusTrace(setDesc="修改功能开关")
	public boolean updateGeFunctionSwitch(GeFunctionSwitch geFunctionSwitch)
			 throws BizConfigCommonException{
		try {
			GeFunctionSwitch update = super.findUnique("functiontId", geFunctionSwitch.getFunctiontId());
			BeanUtils.copyProperties(geFunctionSwitch, update, new String[]{"functiontId"});
			super.update(update);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("修改功能开关异常", e);
		}
		
		return true;
	}

	@Override
	@LocusTrace(setDesc="保存功能开关")
	public boolean save(GeFunctionSwitch geFunctionSwitch)  throws BizConfigCommonException{
		try {
			super.save(geFunctionSwitch);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("保存功能开关异常", e);
		}
		
		return true;
	}

	@Override
	@LocusTrace(setDesc="删除功能开关")
	public boolean delete(String functiontId)  throws BizConfigCommonException{
		try {
			super.deleteByPK(GeFunctionSwitch.class, functiontId);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("删除功能开关异常", e);
		}
		
		return true;
	}

	@Override
	@LocusTrace(setDesc="批量删除功能开关")
	public boolean deleteAllGeFunctionSwitch(String keys)  throws BizConfigCommonException{
		try {
			Session session = null;
			String deleteSql = "delete from GE_FUNCTION_SWITCH where functiontId in ('" + keys.replaceAll(",", "','") + "')";
			session = super.getSession();
			SQLQuery query = session.createSQLQuery(deleteSql);
			query.executeUpdate();
			session.flush();
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("批量删除功能开关异常", e);
		}
		return true;
	}

	@Override
	@LocusTrace(setDesc="批量删除功能开关")
	public boolean deleteAllGeFunctionSwitch(List<GeFunctionSwitch> geFunctionSwitchList)  throws BizConfigCommonException{
		try {
			super.deleteAll(geFunctionSwitchList);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("批量删除功能开关异常", e);
		}
		return true;
	}
	
	@Override
	@LocusTrace(setDesc="判断给定功能开关的开关状态")
	public boolean isSwitchOpen(String functiontId){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("functiontId", functiontId);
		GeFunctionSwitch geFunctionSwitch;
		try {
			geFunctionSwitch = findGeFunctionSwitch(queryRule);
		} catch (Exception e) {
			return false;
		}
		if (geFunctionSwitch == null)
			return false;
		if ("1".equals(geFunctionSwitch.getStatus())) {
			/** 属性开关状态(0 无效 1 有效 2未开通) */
			return true;
		} else {
			return false;
		}
	}

}
