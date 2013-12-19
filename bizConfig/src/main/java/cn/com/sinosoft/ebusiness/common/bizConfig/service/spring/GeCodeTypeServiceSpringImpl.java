/**
 * 
 */
package cn.com.sinosoft.ebusiness.common.bizConfig.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCodeType;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeTypeService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BizConfigCommonException;

/**
 *
 */
public class GeCodeTypeServiceSpringImpl extends
		GenericDaoHibernate<GeCodeType, Long> implements GeCodeTypeService {
	
	@Override
	@LocusTrace(setDesc="查询数据字典类型列表")
	public List<GeCodeType> findAll()throws BizConfigCommonException{
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addAscOrder("codeType");
			return super.find(queryRule);
		}catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("查询数据字典类型列表异常",e);
		}
	}
	
	@Override
	@LocusTrace(setDesc="查询数据字典类型及描述")
	public Map<String, String> findAllCodeAndDesc()throws BizConfigCommonException{
		try{
			String sql = "select d.CODETYPE,d.CODETYPECDESC from GE_CODE_TYPE d ";
			List list = super.findBySql(sql);
			if (list == null || list.size() == 0) {
				return new HashMap<String, String>();
			}else{
				Map map = new HashMap();
				for(int i = 0; i < list.size(); i++){
					Object[] strs =  (Object[])list.get(i);
					map.put(strs[0], strs[1]);
				}
				return map;
			}
		}catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("查询数据字典类型及描述异常",e);
		}
	}
	
	@Override
	@LocusTrace(setDesc="分页查询数据字典类型")
	public Page findGeCodeType(QueryRule queryRule, int pageNo, int pageSize) throws BizConfigCommonException {
		try{
			return super.find(queryRule, pageNo, pageSize);
		}catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("查询数据字典类型列表异常",e);
		}
	}

	@Override
	@LocusTrace(setDesc="查询数据字典类型")
	public GeCodeType findGeCodeType(QueryRule queryRule) throws BizConfigCommonException {
		return super.findUnique(queryRule);
	}

	@Override
	@LocusTrace(setDesc="修改数据字典类型")
	public boolean updateGeCodeType(GeCodeType geCodeType) throws BizConfigCommonException {
		GeCodeType update = super.findUnique("codeType", geCodeType.getCodeType());
		BeanUtils.copyProperties(geCodeType, update, new String[]{"codeType","flag"});
		super.update(update);
		return true;
	}

	@Override
	@LocusTrace(setDesc="保存数据字典类型")
	public boolean save(GeCodeType geCodeType) throws BizConfigCommonException {
		super.save(geCodeType);
		return true;
	}

	@Override
	@LocusTrace(setDesc="删除数据字典类型")
	public boolean delete(String codeType) throws BizConfigCommonException {
		super.deleteByPK(GeCodeType.class, codeType);
		return true;
	}

	@Override
	@LocusTrace(setDesc="批量删除数据字典类型")
	public boolean deleteAllGeCodeType(String keys) throws BizConfigCommonException {
		Session session = null;
		String deleteSql = "delete from GE_CODE_TYPE where codeType in ('" + keys.replaceAll(",", "','") + "')";
		session = super.getSession();
		SQLQuery query = session.createSQLQuery(deleteSql);
		query.executeUpdate();
		session.flush();
		return true;
	}

	@Override
	@LocusTrace(setDesc="批量删除数据字典类型")
	public boolean deleteAllGeCodeType(List<GeCodeType> geCodeTypeList) throws BizConfigCommonException {
		super.deleteAll(geCodeTypeList);
		return true;
	}
}
