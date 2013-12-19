/**
 * 
 */
package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.StringUtils;

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
import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGroup;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeRole;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeGroupService;

/**
 * 用户组服务类
 *  
 *
 */
public class GeGroupServiceSpringImpl extends GenericDaoHibernate<GeGroup, String> implements GeGroupService {

	@Override
	@LocusTrace(setDesc="保存该用户组")
	public boolean save(GeGroup geGroup) {
		boolean flag = false;
		try {
			super.save(geGroup);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@LocusTrace(setDesc="更新用户组")
	public boolean updatesGroup(GeGroup obj) {
		try{
			GeGroup update = super.findUnique("groupid", obj.getGroupid());
			List<String> ignorePropertyList = new ArrayList<String>();
			ignorePropertyList.add("createtime");
			ignorePropertyList.add("groupid");
			ignorePropertyList.add("deptid");
			ignorePropertyList.add("geOperators");
			ignorePropertyList.add("geDeptGroupRoles");
			String[] ignoreProperties = new String[ignorePropertyList.size()];
			BeanUtils.copyProperties(obj, update, ignorePropertyList.toArray(ignoreProperties));
			super.update(update);
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	@Override
	public boolean updates(GeGroup obj) {
		try{
			super.update(obj);
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	@Override
	@LocusTrace(setDesc="删除用户组")
	public int delete(String keys) {
		int flag = 3;
		try {
			if(isGroupExistUser(keys)&&isDeptUsed(keys)){
				flag = 4;//该组既分配人员又分配机构
			}else if(isDeptUsed(keys)){
				flag = 2;//该组已分配机构
			}else if(isGroupExistUser(keys)){
				flag = 1;//该组已分配人员
			}else{
			Session session = null;
			String deleteSql = "delete from ge_group where groupid in ('" + keys.replaceAll(",", "','") + "')";
			session = super.getSession();
			SQLQuery query = session.createSQLQuery(deleteSql);
			query.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	private boolean isDeptUsed(String keys){
		boolean flag = false;
		try {
			String sql = "select count(1) count from ge_dept_group_role where groupid in ('" + keys.replaceAll(",", "','") + "')";
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

	private boolean isGroupExistUser(String keys){
		boolean flag = false;
		try {
			String sql = "select count(1) count from ge_group_user where groupid in ('" + keys.replaceAll(",", "','") + "')";
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
	
	@Override
	@LocusTrace(setDesc="更新用户组内用户")
	public boolean updateGeGroupGeOperators(String groupid, String selectCity, String updateOperator) throws Exception{
		Session session = null;
		/**删除在组groupid下属于selectCity的用户*/
		String deleteSql = "delete from ge_group_user where groupid=? and operatorid in (select operatorid from ge_operator where deptid = ? )";
		session = super.getSession();
		session.createSQLQuery(deleteSql).setString(0, groupid).setString(1, selectCity).executeUpdate();
		
		String[] groupUserArr = updateOperator.split(",");
		if(!StringUtils.isBlank(groupUserArr[0])){
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT all ");
			for(int i = 0; i < groupUserArr.length; i++){
				sql.append("INTO ge_group_user VALUES('"+groupid+"','"+groupUserArr[i]+"') ");
			}
			sql.append("select sysdate from dual");
			session.createSQLQuery(sql.toString()).executeUpdate();
		}
		return true;
	}
	
	@Override
	@LocusTrace(setDesc="分页查询用户组")
	public Page findGeGroup(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}
	
	@Override
	@LocusTrace(setDesc="查找多个用户组")
	public List<GeGroup> findGeGroups(QueryRule queryRule){
		return super.find(queryRule);
	}
	
	@Override
	@LocusTrace(setDesc="条件查询用户组")
	public GeGroup findGeGroup(QueryRule queryRule) {
		return super.findUnique(queryRule);
	}
	
	@Override
	@LocusTrace(setDesc="查询用户组下成员")
	public List<Map<String,String>> findGeGroupOperator(String groupid,String groupUserAuthDeptId) {
		
		List<String> values = Arrays.asList(groupUserAuthDeptId.split(","));
		String groupUserAuthDeptId1 = groupUserAuthDeptId.substring(0,groupUserAuthDeptId.indexOf(values.get(values.size()/2+1)));
		groupUserAuthDeptId1 = groupUserAuthDeptId1.substring(0,groupUserAuthDeptId1.length()-1);
		String groupUserAuthDeptId2 = groupUserAuthDeptId.substring(groupUserAuthDeptId.indexOf(values.get(values.size()/2+1)));
		groupUserAuthDeptId1 = "'" + groupUserAuthDeptId1.replaceAll(",", "','") + "'";
		groupUserAuthDeptId2 = "'" + groupUserAuthDeptId2.replaceAll(",", "','") + "'";
		
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		String sql = 	"select distinct geo.operatorid,geo.operatorname,geg.groupid,ged.deptid,ged.deptname,ged.businessarea "+
						"from GE_OPERATOR geo inner join GE_DEPARTMENT ged on geo.deptid = ged.deptid "+
						"left join ge_group_user geg on geo.operatorid = geg.operatorid "+
						" where geg.groupid = ? and (geo.deptid in (" +groupUserAuthDeptId1+ ") or geo.deptid in (" +groupUserAuthDeptId2+ "))";
		List objList = super.findBySql(sql, groupid);//角色权限
		for(int i=0;objList!=null&&i<objList.size();i++){
			 Object[] obj = (Object[])objList.get(i);
			 map = new HashMap<String,String>();
			 map.put("OPERATORID", (String)obj[0]);
			 map.put("OPERATORNAME", (String)obj[1]);
			 map.put("GROUPID", (String)obj[2]);
			 map.put("DEPTID", (String)obj[3]);
			 map.put("DEPTNAME", (String)obj[4]);
			 map.put("BUSINESSAREA", (String)obj[5]);
			 result.add(map);
		}
		return  result;
	}
	
	public Page findGeGroupOperator(String groupid,String groupUserAuthDeptId,int pageNo,int pageSize) {
		List<GeOperator> geOperators = new ArrayList<GeOperator>();
		
		String sql1 = "select distinct geo.operatorid,geo.operatorname,geg.groupid,ged.deptid,ged.deptname,ged.businessarea";
		
		String sql = 	" from GE_OPERATOR geo inner join GE_DEPARTMENT ged on geo.deptid = ged.deptid "+
						"left join ge_group_user geg on geo.operatorid = geg.operatorid "+
						" where geg.groupid = ? and (geo.deptid in ";
		String[] authorityIdAll = groupUserAuthDeptId.split(",");
		if(authorityIdAll!=null && authorityIdAll.length > 0){
			int authorityIdSize = authorityIdAll.length;
			int authorityIdNumber = (authorityIdSize-1)/1000+1;
			String nextParentIdString = "";
			for(int j = 0; j < authorityIdNumber; j++){
				if(nextParentIdString.length()>0){
					 nextParentIdString = "";
					sql+="or geo.deptid in";
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
		
		Query sqlQuery  = super.getSession().createSQLQuery(sql1+sql).setParameter(0, groupid).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		sqlQuery.setFirstResult(Page.getStartOfPage(pageNo, pageSize));
		sqlQuery.setMaxResults(pageSize);
		List<Map> objList = sqlQuery.list();
		
		Query sqlQuery2  = super.getSession().createSQLQuery(sql2+sql).setParameter(0, groupid).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List totalList = sqlQuery2.list();
		
		for(int i=0;objList!=null&&i<objList.size();i++){
			Map map = objList.get(i);
			GeOperator geOperator = new GeOperator();
			 geOperator.setOperatorid((String)map.get("OPERATORID"));
			 geOperator.setOperatorname((String)map.get("OPERATORNAME"));
			 geOperator.setGroupid((String)map.get("GROUPID"));
			 geOperator.setDeptid((String)map.get("DEPTID"));
			 geOperator.setDeptname((String)map.get("DEPTNAME"));
			 geOperator.setBusinessarea((String)map.get("BUSINESSAREA"));
			 geOperators.add(geOperator);
			
		}
		Map map2 = (Map)totalList.get(0);
		BigDecimal totalSize = (BigDecimal)map2.get("NUM");
		return new Page(Page.getStartOfPage(pageNo, pageSize), totalSize.longValue(), pageSize, geOperators);
	}

	@Override
	@LocusTrace(setDesc="判断该用户组是否存在")
	public boolean exists(String pk) {
		return super.exists(GeGroup.class, pk);
	}
	
	@Override
	@LocusTrace(setDesc="查询机构deptid下所有用户以及在用户组groupid下的用户")
	public List<Map<String,String>> findGeGroupDeptOperator(String groupid, String deptid){
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		String sql = 	"select distinct geo.operatorid,geo.operatorname,decode((select count(operatorid)                                            "+
						"                         from ge_group_user gegsub                                          "+
						"                        where gegsub.operatorid = geo.operatorid                            "+
						"                          and gegsub.groupid = ?),                                  		 "+
						"                       0,                                                                   "+
						"                       '',                                                                  "+
						"                       ?) groupid,                                                  		 "+
						"                ged.deptid,                                                                 "+
						"                ged.deptname,                                                               "+
						"                ged.businessarea                                                            "+
						"  from GE_OPERATOR geo                                                                      "+
						" inner join GE_DEPARTMENT ged                                                               "+
						"    on geo.deptid = ged.deptid                                                              "+
						"  left join ge_group_user geg                                                               "+
						"    on geo.operatorid = geg.operatorid                                                      "+
						" where ged.deptid =?                                                                        ";
		List objList = super.findBySql(sql, groupid, groupid, deptid);//角色权限
		for(int i=0;objList!=null&&i<objList.size();i++){
			 Object[] obj = (Object[])objList.get(i);
			 map = new HashMap<String,String>();
			 map.put("OPERATORID", (String)obj[0]);
			 map.put("OPERATORNAME", (String)obj[1]);
			 map.put("GROUPID", (String)obj[2]);
			 map.put("DEPTID", (String)obj[3]);
			 map.put("DEPTNAME", (String)obj[4]);
			 map.put("BUSINESSAREA", (String)obj[5]);
			 result.add(map);
		}
		return  result;
	}
	
	/**
	 * 查找所有的角色信息
	 */
	@Override
	@LocusTrace(setDesc="查找所有的角色信息")
	public List<Map<String,String>> findAllRolesAndChecked(String id,String groupRoleAuthDeptId){
		List<String> values = Arrays.asList(groupRoleAuthDeptId.split(","));
		String groupRoleAuthDeptId1 = groupRoleAuthDeptId.substring(0,groupRoleAuthDeptId.indexOf(values.get(values.size()/2+1)));
		groupRoleAuthDeptId1 = groupRoleAuthDeptId1.substring(0,groupRoleAuthDeptId1.length()-1);
		String groupRoleAuthDeptId2 = groupRoleAuthDeptId.substring(groupRoleAuthDeptId.indexOf(values.get(values.size()/2+1)));
		groupRoleAuthDeptId1 = "'" + groupRoleAuthDeptId1.replaceAll(",", "','") + "'";
		groupRoleAuthDeptId2 = "'" + groupRoleAuthDeptId2.replaceAll(",", "','") + "'";
		
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		String sql = 	"select m.roleid,m.rolename,ged.deptname,                                   "+
						"decode((select distinct k.roleid from ge_dept_group_role k                                                    "+
						"where k.roleid = m.roleid and k.groupid="+"'"+id+"'"+
						"),null,'1','0') as status                                        "+
						"from ge_role m inner join GE_DEPARTMENT ged on m.deptid=ged.deptid "+
						"where m.deptid in (" +groupRoleAuthDeptId1+ ") or m.deptid in (" +groupRoleAuthDeptId2+ ")";
		List objList = super.findBySql(sql);//角色权限
		for(int i=0;objList!=null&&i<objList.size();i++){
			 Object[] obj = (Object[])objList.get(i);
			 map = new HashMap<String,String>();
			 map.put("ROLEID", (String)obj[0]);
			 map.put("ROLENAME", (String)obj[1]);
			 map.put("DEPTNAME", (String)obj[2]);
			 map.put("STATUS", (String)obj[3]);
			 result.add(map);
		}
		return  result;
	}
	public Page findGroupRoles(String id,String groupRoleAuthDeptId,int pageNo,int pageSize){
 		List<GeRole> roleList = new ArrayList<GeRole>();
		String sql1 = "select distinct m.roleid,m.rolename,ged.deptname,ged.deptid ";
		String sql = " from ge_dept_group_role dgr,ge_role m,GE_DEPARTMENT ged where dgr.groupid=? and m.roleid=dgr.roleid and m.deptid=ged.deptid "+
						"and (m.deptid in ";
		String[] authorityIdAll = groupRoleAuthDeptId.split(",");
		if(authorityIdAll!=null && authorityIdAll.length > 0){
			int authorityIdSize = authorityIdAll.length;
			int authorityIdNumber = (authorityIdSize-1)/1000+1;
			String nextParentIdString = "";
			for(int j = 0; j < authorityIdNumber; j++){
				if(nextParentIdString.length()>0){
					 nextParentIdString = "";
					sql+=" or m.deptid in ";
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
		}
		sql += ")";
		String sql2 = "select count(1) num from ("+sql1+sql+")";
		Query sqlQuery  = super.getSession().createSQLQuery(sql1+sql).setParameter(0, id).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		sqlQuery.setFirstResult(Page.getStartOfPage(pageNo, pageSize));
		sqlQuery.setMaxResults(pageSize);
		List<Map> objList = sqlQuery.list();
		
		Query sqlQuery2  = super.getSession().createSQLQuery(sql2).setParameter(0, id).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List totalList = sqlQuery2.list();
		
		for(int i=0;objList!=null&&i<objList.size();i++){
			 Map map = objList.get(i);
			 GeRole geRole = new GeRole();
			 geRole.setRoleID((String)map.get("ROLEID"));
			 geRole.setRoleName((String)map.get("ROLENAME"));
			 geRole.setDeptName((String)map.get("DEPTNAME"));
			 geRole.setDeptID((String)map.get("DEPTID"));
			 roleList.add(geRole);
		}
		Map map2 = (Map)totalList.get(0);
		BigDecimal totalSize = (BigDecimal)map2.get("NUM");
		return new Page(Page.getStartOfPage(pageNo, pageSize), totalSize.longValue(), pageSize, roleList);
	}
	
	public List<GeRole> findAllGroupRoles(String groupId,String groupRoleAuthDeptId){
		List<GeRole> roleList = new ArrayList<GeRole>();
		String sql1 = "select distinct m.roleid,m.rolename ";
		String sql = " from ge_dept_group_role dgr,ge_role m where dgr.groupid=? and m.roleid=dgr.roleid "+
						"and (m.deptid in ";
		String[] authorityIdAll = groupRoleAuthDeptId.split(",");
		if(authorityIdAll!=null && authorityIdAll.length > 0){
			int authorityIdSize = authorityIdAll.length;
			int authorityIdNumber = (authorityIdSize-1)/1000+1;
			String nextParentIdString = "";
			for(int j = 0; j < authorityIdNumber; j++){
				if(nextParentIdString.length()>0){
					 nextParentIdString = "";
					sql+=" or m.deptid in ";
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
		}
		sql += ")";
		Query sqlQuery  = super.getSession().createSQLQuery(sql1+sql).setParameter(0, groupId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map> objList = sqlQuery.list();
		
		for(int i=0;objList!=null&&i<objList.size();i++){
			 Map map = objList.get(i);
			 GeRole geRole = new GeRole();
			 geRole.setRoleID((String)map.get("ROLEID"));
			 geRole.setRoleName((String)map.get("ROLENAME"));
			 roleList.add(geRole);
		}
		return roleList;
	}

	@Override
	public boolean isExistUser(String userID, String groupID){
		boolean flag = false;
		try {
			String sql = "select * from ge_group_user where groupid =? and operatorid=?";
			List list = super.findBySql(sql, new String[]{groupID, userID});
			if(list.size() > 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@LocusTrace(setDesc="查询组内所用到的角色")
	public List<Map<String, String>> findRolesAndChecked(String id,
			String odeptid) {
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		String sql = "select m.roleid,m.rolename, decode((select distinct k.roleid from ge_dept_group_role k "+
					 "where k.roleid=m.roleid and k.groupid='"+id+"'), null,'1','0') as status from ge_role " +
					 "m where m.deptid= "+"'"+odeptid+"' order by m.roleid";
		List objList = super.findBySql(sql);//角色权限
		for(int i=0;objList!=null&&i<objList.size();i++){
			 Object[] obj = (Object[])objList.get(i);
			 map = new HashMap<String,String>();
			 map.put("ROLEID", (String)obj[0]);
			 map.put("ROLENAME", (String)obj[1]);
			 map.put("STATUS", (String)obj[2]);
			 result.add(map);
		}
		return  result;
	}
	
	@Override
	@LocusTrace(setDesc="删除用户组用户")
	public boolean deleteUserFromGroup(String operatorId,String groupId){
		if(StringUtils.isBlank(operatorId) || StringUtils.isBlank(groupId)){
			return false;
		}
		try {
			String deleteSql = "delete from ge_group_user where groupid=? and operatorid = ?";
			Session session = super.getSession();
			session.createSQLQuery(deleteSql).setString(0, groupId).setString(1, operatorId).executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean saveUserToGroups(String createUserId, String groupsId) {
		Session session = null;
		session = super.getSession();
		if(!StringUtils.isBlank(groupsId)){
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT all ");
			
			String[]groupsIdArr = groupsId.split(",");
			for(int i = 0; i < groupsIdArr.length; i++){
				sql.append("INTO ge_group_user VALUES('"+groupsIdArr[i]+"','"+createUserId+"') ");
			}
			sql.append("select sysdate from dual");
			session.createSQLQuery(sql.toString()).executeUpdate();
		}
		return true;
		
	}

	@Override
	public boolean updateUserToGroups(String createUserId, String groupsId,String allGroupsIds) {
		Session session = null;
		session = super.getSession();
	
		/**先删除原来的用户组*/
		if(!StringUtils.isBlank(allGroupsIds)){
			StringBuffer sql = new StringBuffer();
			sql.append("delete from ge_group_user  j where j.operatorid= ? and (j.groupid in ");
			
			String[] allGroupArr = allGroupsIds.split(",");
			if(allGroupArr!=null && allGroupArr.length > 0){
				int authorityIdSize = allGroupArr.length;
				int authorityIdNumber = (authorityIdSize-1)/1000+1;
				String nextParentIdString = "";
				for(int j = 0; j < authorityIdNumber; j++){
					if(nextParentIdString.length()>0){
						 nextParentIdString = "";
						 sql.append("or j.groupid in");
					}
					int loopNum = 1000;
					if(j == authorityIdNumber-1){
						loopNum = authorityIdSize - (authorityIdNumber-1)*1000;
					}
					for (int i = 0; i < loopNum; i++) {
						String childDeptId =(String)allGroupArr[1000*j+i];
						if(i == 0){
							nextParentIdString += "'"+childDeptId+"'";
						}else{
							nextParentIdString += ",'"+childDeptId+"'";
						}
					}
					sql.append("("+nextParentIdString+")");
				}
				sql.append(")");
			}
			session.createSQLQuery(sql.toString()).setParameter(0, createUserId).executeUpdate();
		}
		
		/**添加新的用户组*/
		if(!StringUtils.isBlank(groupsId)){
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT all ");
			
			String[] checkedGroupArr = groupsId.split(",");
			for(int i = 0; i < checkedGroupArr.length; i++){
				sql.append("INTO ge_group_user VALUES('"+checkedGroupArr[i]+"','"+createUserId+"') ");
			}
			sql.append("select sysdate from dual");
			session.createSQLQuery(sql.toString()).executeUpdate();
		}
		return true;
	}
}
