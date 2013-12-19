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

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGroup;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeOperatorService;

/**
 * 后台管理员服务实现类
 *  
 *
 */
public class GeOperatorServiceSpringImpl extends GenericDaoHibernate<GeOperator, String> implements GeOperatorService {
	
	@Override
	@LocusTrace(setDesc="判断该用户是否存在")
	public boolean exists(String pk) {
		return super.exists(GeOperator.class, pk);
	}
	
	@Override
	@LocusTrace(setDesc="主键查询出用户信息")
	public GeOperator findOperatorByPK(String pk){
		return super.findUnique("operatorid", pk);
	}
	
	@Override
	@LocusTrace(setDesc="保存该后台用户")
	public boolean save(GeOperator geOperator) {
		boolean flag = false;
		try {
			if(!super.exists(GeOperator.class, geOperator.getOperatorid())){
				super.save(geOperator);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	@LocusTrace(setDesc="查询后台用户")
	public GeOperator findGeOperator(QueryRule queryRule) {
		return super.findUnique(queryRule);
	}

	@Override
	@LocusTrace(setDesc="修改用户信息")
	public boolean updates(GeOperator geOperator) {
		boolean flag = false;
		try{
			GeOperator update = super.findUnique("operatorid", geOperator.getOperatorid());
			BeanUtils.copyProperties(geOperator, update,new String[]{"operatorid","pwd"});
			super.update(update);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@LocusTrace(setDesc="删除用户信息")
	public boolean delete(String pk) {
		boolean flag = false;
		Session session = null;
		try {
			if(!isUserUsed(pk)){
				String deleteSql = "delete from ge_operator where operatorid in ('" + pk.replaceAll(",", "','") + "')";
				session = super.getSession();
				SQLQuery query = session.createSQLQuery(deleteSql);
				query.executeUpdate();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@LocusTrace(setDesc="分页查询用户信息")
	public Page find(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}
	
	@LocusTrace(setDesc="查询用户所在组")
	public List findGroupId(String operatorid,String groupUserAuthDeptId) {
		Session session = super.getSession();
		String sql="select distinct g.groupid,j.groupname,d.deptid,d.deptname from ge_group_user g ,ge_group j ,ge_department d where  g.groupid=j.groupid  and j.deptid = d.deptid and operatorid=? and (j.deptid in ";
		String[] authorityIdAll = groupUserAuthDeptId.split(",");
		if(authorityIdAll!=null && authorityIdAll.length > 0){
			int authorityIdSize = authorityIdAll.length;
			int authorityIdNumber = (authorityIdSize-1)/1000+1;
			String nextParentIdString = "";
			for(int j = 0; j < authorityIdNumber; j++){
				if(nextParentIdString.length()>0){
					 nextParentIdString = "";
					sql+="or j.deptid in";
				}
				int loopNum = 1000;
				if(j == authorityIdNumber-1){
					loopNum = authorityIdSize - (authorityIdNumber-1)*1000;
				}
				for (int i = 0; i < loopNum; i++) {
					String childDeptId =(String)authorityIdAll[1000*j+i];
					if(i == 0){
						nextParentIdString += "'"+childDeptId+"'";
					}else{
						nextParentIdString += ",'"+childDeptId+"'";
					}
				}
				sql += "("+nextParentIdString+")";
			}
			sql+=")";
		}
		List groups=session.createSQLQuery(sql).setString(0, operatorid).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		if(groups == null || groups.size() == 0){
			return null;
		}else{
			return groups;
		}
	}
	
	@Override
	public Page findGroupId(String operatorid, String authorityChoose,
			int pageNo, int pageSize) {
		List<GeGroup> groupList = new ArrayList<GeGroup>();
		String sql1 = "select distinct g.groupid,j.groupname,d.deptid,d.deptname";
		String sql=" from ge_group_user g ,ge_group j ,ge_department d where  g.groupid=j.groupid  and j.deptid = d.deptid and operatorid=? and (j.deptid in ";
		String[] authorityIdAll = authorityChoose.split(",");
		if(authorityIdAll!=null && authorityIdAll.length > 0){
			int authorityIdSize = authorityIdAll.length;
			int authorityIdNumber = (authorityIdSize-1)/1000+1;
			String nextParentIdString = "";
			for(int j = 0; j < authorityIdNumber; j++){
				if(nextParentIdString.length()>0){
					 nextParentIdString = "";
					sql+="or j.deptid in";
				}
				int loopNum = 1000;
				if(j == authorityIdNumber-1){
					loopNum = authorityIdSize - (authorityIdNumber-1)*1000;
				}
				for (int i = 0; i < loopNum; i++) {
					String childDeptId =(String)authorityIdAll[1000*j+i];
					if(i == 0){
						nextParentIdString += "'"+childDeptId+"'";
					}else{
						nextParentIdString += ",'"+childDeptId+"'";
					}
				}
				sql += "("+nextParentIdString+")";
			}
			sql+=")";
		}
		String sql2 = "select count(1) num ";
		Query sqlQuery  = super.getSession().createSQLQuery(sql1+sql).setParameter(0, operatorid).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		sqlQuery.setFirstResult(Page.getStartOfPage(pageNo, pageSize));
		sqlQuery.setMaxResults(pageSize);
		List<Map> objList = sqlQuery.list();
		
		Query sqlQuery2  = super.getSession().createSQLQuery(sql2+sql).setParameter(0, operatorid).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List totalList = sqlQuery2.list();
		for(int i=0;objList!=null&&i<objList.size();i++){
			Map map = objList.get(i);
			 GeGroup geGroup = new GeGroup();
			 geGroup.setGroupid((String)map.get("GROUPID"));
			 geGroup.setGroupname((String)map.get("GROUPNAME"));
			 geGroup.setDeptname((String)map.get("DEPTNAME"));
			 geGroup.setDeptid((String)map.get("DEPTID"));
			 groupList.add(geGroup);
		}
		Map map2 = (Map)totalList.get(0);
		BigDecimal totalSize = (BigDecimal)map2.get("NUM");
		return new Page(Page.getStartOfPage(pageNo, pageSize), totalSize.longValue(), pageSize, groupList);
	}

	@Override
	@LocusTrace(setDesc="判断该用户是否加入到组中")
	public boolean isUserUsed(String id) {
		boolean flag=false;
		try {
			Session session = super.getSession();
			String querySql1 = "select count(1) count from ge_group_user where operatorid in ('" + id.replaceAll(",", "','") + "')";
			List list1  = session.createSQLQuery(querySql1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
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

	@Override
	public boolean updatePass(String operatorId, String newPassowrd) {
		try {
			String updateSql = "update ge_operator set PWD = ? where operatorid = ?";
			Session session = super.getSession();
			session.createSQLQuery(updateSql).setString(0, newPassowrd).setString(1, operatorId).executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
