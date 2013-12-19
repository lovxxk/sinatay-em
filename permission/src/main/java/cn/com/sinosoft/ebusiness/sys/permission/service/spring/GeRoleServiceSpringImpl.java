package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGroup;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeRole;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeGroupService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeRoleService;

/**
 * 角色管理服务接口
 *  
 *
 */
public class GeRoleServiceSpringImpl extends GenericDaoHibernate<GeRole, String> implements GeRoleService {
	@Autowired
	private GeGroupService geGroupService;

	@Override
	@LocusTrace(setDesc="判断该角色是否存在")
	public boolean exists(String pk) {
		return super.exists(GeRole.class, pk);
	}

	@Override
	@LocusTrace(setDesc="保存该角色")
	public boolean save(GeRole obj) {
		boolean flag = false;
		try {
			if(!super.exists(GeRole.class, obj.getRoleID())){
				super.save(obj);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@LocusTrace(setDesc="条件查询该角色")
	public GeRole findGeRole(QueryRule queryRule) {
		return super.findUnique(queryRule);
	}

	@Override
	@LocusTrace(setDesc="更新角色信息")
	public boolean updates(GeRole geRole) {
		/**
		 * 1.删除该角色已有权限
		 * 2.保存GeRole和角色权限信息
		 */
		boolean flag = false;
		Session session = null;
		try{
			String pk = geRole.getRoleID();
			String deleteSql = "delete from ge_role_authority where roleid in ('" + pk.replaceAll(",", "','") + "')";
			session = super.getSession();
			SQLQuery query = session.createSQLQuery(deleteSql);
			query.executeUpdate();
			super.update(geRole);
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	@LocusTrace(setDesc="分页查询角色信息")
	public Page find(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}

	@Override
	@LocusTrace(setDesc="删除角色")
	public boolean delete(String pk) {
		boolean flag = false;
		Session session = null;
		try {
			if (!isRoleUsed(pk)) {
				String deleteSql1 = "delete from ge_role_authority where roleid in ('" + pk.replaceAll(",", "','") + "')";
				session = super.getSession();
				SQLQuery query = session.createSQLQuery(deleteSql1);
				query.executeUpdate();
				String deleteSql = "delete from ge_role where roleid in ('" + pk.replaceAll(",", "','") + "')";
				SQLQuery query1 = session.createSQLQuery(deleteSql);
				query1.executeUpdate();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@SuppressWarnings("rawtypes")
	private boolean isRoleUsed(String id) {
		boolean flag=false;
		try {
			Session session = super.getSession();
			String querySql1 = "select count(1) count from ge_dept_group_role where roleid in ('" + id.replaceAll(",", "','") + "')";
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
	@LocusTrace(setDesc="查询出某角色所用的的组")
	public List findRoleGroupID(String roleId,String authorityid) {
		List<GeGroup> geGroups = new ArrayList<GeGroup>();
		Session session = super.getSession();
		String sql = "select distinct g.groupid,g.groupname,d.deptname from" +
				" ge_group g inner join ge_dept_group_role gd on gd.groupid=g.groupid and gd.roleid=? left join ge_department d on d.deptid=g.deptid where (g.deptid in ";
		String[] authorityIdAll = authorityid.split(",");
		if(authorityIdAll!=null && authorityIdAll.length > 0){
			int authorityIdSize = authorityIdAll.length;
			int authorityIdNumber = (authorityIdSize-1)/1000+1;
			String nextParentIdString = "";
			for(int j = 0; j < authorityIdNumber; j++){
				if(nextParentIdString.length()>0){
					 nextParentIdString = "";
					sql+="or g.deptid in";
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
		List result=session.createSQLQuery(sql).setString(0, roleId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return result;
	}

	@Override
	public Page findAuthorityRroup(String groupRoleAuthDeptId,
			String roleId, int pageNo, int pageSize) {
		List<GeGroup> geGroups = new ArrayList<GeGroup>();
		String sql1 = "select distinct g.groupid,g.groupname,d.deptname,d.deptid";
		String sql=" from ge_group g inner join ge_dept_group_role gd on gd.groupid=g.groupid and gd.roleid=? left join ge_department d on d.deptid=g.deptid where (g.deptid in ";
		String[] authorityIdAll = groupRoleAuthDeptId.split(",");
		if(authorityIdAll!=null && authorityIdAll.length > 0){
			int authorityIdSize = authorityIdAll.length;
			int authorityIdNumber = (authorityIdSize-1)/1000+1;
			String nextParentIdString = "";
			for(int j = 0; j < authorityIdNumber; j++){
				if(nextParentIdString.length()>0){
					 nextParentIdString = "";
					sql+="or g.deptid in";
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
		String sql2 = "select count(1) num from";
		Query sqlQuery  = super.getSession().createSQLQuery(sql1+sql).setString(0, roleId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		sqlQuery.setFirstResult(Page.getStartOfPage(pageNo, pageSize));
		sqlQuery.setMaxResults(pageSize);
		List<Map> objList = sqlQuery.list();
		
		Query sqlQuery2  = super.getSession().createSQLQuery(sql2+"("+sql1+sql+")").setString(0, roleId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List totalList = sqlQuery2.list();
		
		for(int i=0;objList!=null&&i<objList.size();i++){
			Map map = objList.get(i);
			GeGroup geGroup=new GeGroup();
			geGroup.setGroupid((String)map.get("GROUPID"));
			geGroup.setGroupname((String)map.get("GROUPNAME"));
			geGroup.setDeptname((String)map.get("DEPTNAME"));
			geGroup.setDeptid((String)map.get("DEPTID"));
			geGroups.add(geGroup);
		}
		Map map2 = (Map)totalList.get(0);
		BigDecimal totalSize = (BigDecimal)map2.get("NUM"); 
		return new Page(Page.getStartOfPage(pageNo, pageSize), totalSize.longValue(), pageSize, geGroups);
	}

	@Override
	public List<GeRole> findGeRoles(QueryRule queryRule) {
		// TODO Auto-generated method stub
		return super.find(queryRule);
	}
}
