package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptGroupRole;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptGroupRoleId;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDeptGroupRoleService;

public class GeDeptGroupRoleServiceSpringImpl  
	extends GenericDaoHibernate<GeDeptGroupRole, GeDeptGroupRoleId> implements GeDeptGroupRoleService {
	@Override
	public List<GeDeptGroupRole> findGeDeptGroupRoles(QueryRule queryRule) {
		return super.find(queryRule);
	}

	@Override
	@LocusTrace(setDesc="根据组角色查询出机构编号")
	public String findGeDeptGroupRoleStr(String groupid,String roleId){
		StringBuffer resultBuffer = new StringBuffer("");
		try {
			Session session = super.getSession();
			String querySql1 = "select deptid from ge_dept_group_role where roleid=? and groupid=?";
			List geDeptGroupRoleList  = session.createSQLQuery(querySql1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, roleId).setString(1, groupid).list();
			
			for(int i = 0; i < geDeptGroupRoleList.size(); i++){
				Map  map = (Map)geDeptGroupRoleList.get(i);
				resultBuffer.append(map.get("DEPTID"));
				if(i != geDeptGroupRoleList.size()-1)resultBuffer.append(",");
			}
			return resultBuffer.toString();
		} catch (Exception e) {
			return "";
		}
	}
	
	@Override
	@LocusTrace(setDesc="根据组查询所拥有的机构")
	public String findGeDeptGroupRoleStr(String groupid){
		StringBuffer resultBuffer = new StringBuffer("");
		try {
			Session session = super.getSession();
			String querySql1 = "select deptid from ge_dept_group_role where groupid=?";
			List geDeptGroupRoleList  = session.createSQLQuery(querySql1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, groupid).list();
			
			for(int i = 0; i < geDeptGroupRoleList.size(); i++){
				Map  map = (Map)geDeptGroupRoleList.get(i);
				resultBuffer.append(map.get("DEPTID"));
				if(i != geDeptGroupRoleList.size()-1)resultBuffer.append(",");
			}
			return resultBuffer.toString();
		} catch (Exception e) {
			return "";
		}
	}
	
	@Override
	@LocusTrace(setDesc="保存机构-角色-权限")
	public boolean insertGeGroupRoleDept(String groupId, String roleId,String depts) {
		try {
			/**保存机构-角色-权限*/
			String[] deptIdArr = depts.split(",");
			List<GeDeptGroupRole> geDeptGroupRoleList = new ArrayList<GeDeptGroupRole>();
			for(int i = 0; i < deptIdArr.length; i++){
				if(StringUtils.isBlank(deptIdArr[i]))continue;
				GeDeptGroupRole geDeptGroupRole = new GeDeptGroupRole();
				GeDeptGroupRoleId geDeptGroupRoleId = new GeDeptGroupRoleId();
				geDeptGroupRoleId.setGroupid(groupId);
				geDeptGroupRoleId.setRoleid(roleId);
				geDeptGroupRoleId.setDeptid(deptIdArr[i]);
				geDeptGroupRole.setId(geDeptGroupRoleId);
				geDeptGroupRoleList.add(geDeptGroupRole);
			}
			super.saveAll(geDeptGroupRoleList);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	@LocusTrace(setDesc="保存机构-角色-权限")
	public boolean insertGeGroupRoleDepts(String groupId, String roleIds,String depts) {
		try {
			/**保存机构-角色-权限*/
			String[] deptIdArr = depts.split(",");
			List<GeDeptGroupRole> geDeptGroupRoleList = new ArrayList<GeDeptGroupRole>();
			String[] roleIdArr = roleIds.split(",");
			for(int j=0;j<roleIdArr.length;j++){
				if(StringUtils.isBlank(roleIdArr[j]))continue;
				for(int i = 0; i < deptIdArr.length; i++){
					if(StringUtils.isBlank(deptIdArr[i]))continue;
					GeDeptGroupRole geDeptGroupRole = new GeDeptGroupRole();
					GeDeptGroupRoleId geDeptGroupRoleId = new GeDeptGroupRoleId();
					geDeptGroupRoleId.setGroupid(groupId);
					geDeptGroupRoleId.setRoleid(roleIdArr[j]);
					geDeptGroupRoleId.setDeptid(deptIdArr[i]);
					geDeptGroupRole.setId(geDeptGroupRoleId);
					geDeptGroupRoleList.add(geDeptGroupRole);
				}
			}
			
			
			
			super.saveAll(geDeptGroupRoleList);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	@LocusTrace(setDesc="删除机构-角色-权限")
	public boolean deleteGeGroupGeRoleDept(String groupId, String roleId) {
		try {
			String deleteSql1 = "delete from ge_dept_group_role where groupid=? and roleid=?";
			super.getSession().createSQLQuery(deleteSql1).setString(0,groupId).setString(1, roleId).executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	@LocusTrace(setDesc="删除机构-角色-权限")
	public boolean deleteGeGroupGeRoleDept(String groupId, String roleId, String depts) {
		boolean flag = false;
		Session session = null;
		try {
			String deleteSql = "delete from ge_dept_group_role g where g.roleid='"+roleId+"' and g.groupid='"+groupId+"' and g.deptid in ('" + depts.replaceAll(",", "','") + "')";
			session = super.getSession();
			SQLQuery query = session.createSQLQuery(deleteSql);
			query.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	@LocusTrace(setDesc="删除机构-角色-权限")
	public boolean deleteGeGroupGeRoleDepts(String groupId, String roleIds, String depts) {
		boolean flag = false;
		Session session = null;
		try {
			String deleteSql1 = "delete from ge_dept_group_role g where g.groupid='"+groupId+"' and g.roleid not in ('" + roleIds.replaceAll(",", "','") + "')";
			String deleteSql2 = "delete from ge_dept_group_role g where g.groupid='"+groupId+"' and g.roleid in ('" + roleIds.replaceAll(",", "','") + "') and g.deptid in ('" + depts.replaceAll(",", "','") + "')";
			String deleteSql3 = "delete from ge_dept_group_role g where g.groupid='"+groupId+"'";
			session = super.getSession();
			if(roleIds.length()==0||depts.length()==0){
				SQLQuery query3 = session.createSQLQuery(deleteSql3);
				query3.executeUpdate();
			}else{
				SQLQuery query = session.createSQLQuery(deleteSql1);
				query.executeUpdate();
				SQLQuery query2 = session.createSQLQuery(deleteSql2);
				query2.executeUpdate();
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 查询	角色---权限；角色---机构（用于PermissionFactory权限工厂类）
	 * 
	 * @param operatorId	操作员编号
	 * @return
	 */
	@Override
	@LocusTrace(setDesc="根据用户id获取其所拥有的功能和机构权限")
	public Map<String, Map<String, Set<String>>> findRoleAuthorityDept(
			String operatorId) {
		String roleAuthoritySql =	"select ra.roleid, ra.authorityid                           "+
								"  from ge_role_authority ra                                   	"+
								" where ra.roleid in (select dgr.roleid                         "+
								"                       from ge_dept_group_role dgr             "+
								"                      where dgr.groupid in                     "+
								"                            (select gu.groupid                 "+
								"                               from ge_group_user gu           "+
								"                              where gu.operatorid = ?))    	";
		String roleDeptSql = 		"select dgr.roleid, dgr.deptid                           	"+
								"  from ge_dept_group_role dgr                             		"+
								" where dgr.groupid in (select gu.groupid                  		"+
								"                         from ge_group_user gu            		"+
								"                        where gu.operatorid = ?)    			"+
								" group by dgr.roleid, dgr.deptid                          		";
		List roleAuthorityList = super.findBySql(roleAuthoritySql, operatorId);//角色权限
		Map<String,Set<String>> roleAuthorityHashMap = new HashMap<String,Set<String>>();
		for(int i=0;roleAuthorityList!=null&&i<roleAuthorityList.size();i++){
			 Object[] obj = (Object[])roleAuthorityList.get(i);
			if(roleAuthorityHashMap.get((String)obj[0])==null||roleAuthorityHashMap.get((String)obj[0]).isEmpty()){//map中不存在key，新建
				Set<String> roleAuthorityHashSet = new HashSet<String>();
				roleAuthorityHashSet.add((String)obj[1]);
				roleAuthorityHashMap.put((String)obj[0], roleAuthorityHashSet);
			}else{//map中存在key，更新
				roleAuthorityHashMap.get((String)obj[0]).add((String)obj[1]);
			}
		}
		List roleDeptList = super.findBySql(roleDeptSql, operatorId);//角色机构
		Map<String,Set<String>> roleDeptHashMap = new HashMap<String,Set<String>>();
		for(int i=0;roleDeptList!=null&&i<roleDeptList.size();i++){
			 Object[] obj = (Object[])roleDeptList.get(i);
			if(roleDeptHashMap.get((String)obj[0])==null||roleDeptHashMap.get((String)obj[0]).isEmpty()){//map中不存在key，新建
				Set<String> roleDeptHashSet = new HashSet<String>();
				roleDeptHashSet.add((String)obj[1]);
				roleDeptHashMap.put((String)obj[0], roleDeptHashSet);
			}else{//map中存在key，更新
				roleDeptHashMap.get((String)obj[0]).add((String)obj[1]);
			}
		}
		Map<String, Map<String, Set<String>>> result = new HashMap<String, Map<String, Set<String>>>();
		result.put("ROLEAUTHORITY", roleAuthorityHashMap);
		result.put("ROLEDEPT", roleDeptHashMap);
		return result;
	}

	@Override
	public List<String> findGeGroupRoleDept(String groupid, String roleid) {
		List<String> result = new ArrayList<String>();
		if(!StringUtils.isBlank(groupid)&&!StringUtils.isBlank(roleid)){
			String sql = 	"select gdgr.deptid from GE_DEPT_GROUP_ROLE gdgr where gdgr.groupid = ? and gdgr.roleid = ?";
			result = super.findBySql(sql,groupid,roleid);//角色权限
		}
		return  result;
	}
	
	@Override
	public List findAllAuthorityIds(){
		String sql = "select ra.authorityid from ge_authority ra";
		List authorityList = super.findBySql(sql);//角色权限
		return authorityList;
	}
	
	@Override
	public List findAuthoritysByOId(String operatorId){
		String sql = "select distinct ra.authorityid from ge_role_authority ra where ra.roleid in " +
				"(select distinct dgr.roleid from ge_dept_group_role dgr where dgr.groupid in " +
					"(select gu.groupid from ge_group_user gu where gu.operatorid = ?)" +
				")";
		List authorityList = super.findBySql(sql, operatorId);//角色权限
		return authorityList;
	}

	@Override
	public String findDeptIds(String groupid) {
		StringBuffer resultBuffer = new StringBuffer("");
		try {
			Session session = super.getSession();
			String querySql1 = "select distinct deptid from ge_dept_group_role where groupid=?";
			List geDeptGroupRoleList  = session.createSQLQuery(querySql1).setString(0, groupid).list();
			
			for(int i = 0; i < geDeptGroupRoleList.size(); i++){
				resultBuffer.append(geDeptGroupRoleList.get(i));
				if(i != geDeptGroupRoleList.size()-1)resultBuffer.append(",");
			}
		
			return resultBuffer.toString();
		} catch (Exception e) {
			return "";
		}
	}
	
	@Override
	@LocusTrace(setDesc="查询出组内所用机构")
	public String findGroupDeptAuth(String groupid){
		StringBuffer resultBuffer = new StringBuffer("");
		try {
			Session session = super.getSession();
			String querySql1 = "select distinct deptid from ge_dept_group_role where groupid=?";
			List geDeptGroupRoleList  = session.createSQLQuery(querySql1).setString(0, groupid).list();
			
			for(int i = 0; i < geDeptGroupRoleList.size(); i++){
				resultBuffer.append(geDeptGroupRoleList.get(i));
				if(i != geDeptGroupRoleList.size()-1)resultBuffer.append(",");
			}
		
			return resultBuffer.toString();
		} catch (Exception e) {
			return "";
		}
	}
	
	@Override
	@LocusTrace(setDesc="保存用户组内角色机构")
	public boolean  saveGeGroupRoleDept(String groupId, String roleId,String depts,String operatorDeptAuths){
		try {
			/**删除原有的信息*/
			String deleteSql1 = "delete from ge_dept_group_role where groupid=? and (deptid in";
			String[] authorityIdAll = operatorDeptAuths.split(",");
			if(authorityIdAll!=null && authorityIdAll.length > 0){
				int authorityIdSize = authorityIdAll.length;
				int authorityIdNumber = (authorityIdSize-1)/1000+1;
				String nextParentIdString = "";
				for(int j = 0; j < authorityIdNumber; j++){
					if(nextParentIdString.length()>0){
						 nextParentIdString = "";
						 deleteSql1+="or deptid in";
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
					deleteSql1 += "("+nextParentIdString+")";
				}
				deleteSql1+=")";
			}
			super.getSession().createSQLQuery(deleteSql1).setString(0,groupId).executeUpdate();
			
			/**保存机构-角色-权限*/
			String[] roleIdArr = roleId.split(",");
			String[] deptIdArr = depts.split(",");
			List<GeDeptGroupRole> geDeptGroupRoleList = new ArrayList<GeDeptGroupRole>();
			for(int j = 0; j < roleIdArr.length; j++){
				if(StringUtils.isBlank(roleIdArr[j]))continue;
				for(int i = 0; i < deptIdArr.length; i++){
					if(StringUtils.isBlank(deptIdArr[i]))continue;
					String[] deptFlag = deptIdArr[i].split("sp");
					String deptIDString = deptFlag[0];
					String flagString = deptFlag.length == 2?deptFlag[1]:"";
					GeDeptGroupRole geDeptGroupRole = new GeDeptGroupRole();
					GeDeptGroupRoleId geDeptGroupRoleId = new GeDeptGroupRoleId();
					geDeptGroupRoleId.setGroupid(groupId);
					geDeptGroupRoleId.setRoleid(roleIdArr[j]);
					geDeptGroupRoleId.setDeptid(deptIDString);
					geDeptGroupRole.setId(geDeptGroupRoleId);
					geDeptGroupRole.setFlag(flagString);
					geDeptGroupRoleList.add(geDeptGroupRole);
				}
			}
			
			super.saveAll(geDeptGroupRoleList);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String getAllVirtualParent(String groupDeptAuths) {
		List tempList = new ArrayList();
		String[] tempArr = groupDeptAuths.split(",");
		for(int i = 0; i < tempArr.length; i++){
			tempList.add(tempArr[i]);
		}
		String childIdString = getListStrSplit(tempList);
		List allParentDepts = getAllParentDept(null,childIdString);
		
		/**查找作为父节点的节点*/
		String tId = getListStrSplit(allParentDepts);
		String sql = "select distinct t.parentid from ge_department t where ";
		if(tId.indexOf("sp") == -1){
			sql += " t.parentid in ( "+tId+" ) ";
		}else{
			String[] childIdArr = tId.split("sp");
			for(int i = 0; i < childIdArr.length; i++){
				if(i != 0)sql += " or ";
				sql += " t.parentid in ( "+childIdArr[i]+" ) ";
			}
		}
		List parentlList = super.findBySql(sql);
		
		/**合并*/
		for(int i = 0; i < tempArr.length; i++){
			parentlList.add(tempArr[i]);
		}
		
		/**转化为字符串返回*/
		StringBuffer resultBuffer = new StringBuffer("");
		for(int i = 0; i < parentlList.size(); i++){
			resultBuffer.append("parent_"+parentlList.get(i));
			if(i != parentlList.size()-1)resultBuffer.append(",");
		}
		return resultBuffer.toString();
	}
	
	private List getAllParentDept(List allParentDepts,String parentId){
		if(allParentDepts == null)allParentDepts = new ArrayList();
		
		String sqlString = " select t.parentid from ge_department t where ";
		if(parentId.indexOf("sp") == -1){
			sqlString += " t.deptid in ( "+parentId+" ) ";
		}else{
			String[] parentIdArr = parentId.split("sp");
			for(int i = 0; i < parentIdArr.length; i++){
				if(i != 0)sqlString += " or ";
				sqlString += " t.deptid in ( "+parentIdArr[i]+" ) ";
			}
		}
		
		List parentlList = super.findBySql(sqlString);
		if(parentlList!=null && parentlList.size() > 0){
			for(int i = 0; i < parentlList.size(); i++){
				if(!allParentDepts.contains(parentlList.get(i)))allParentDepts.add(parentlList.get(i));
			}
			String nextParentIdString = getListStrSplit(parentlList);
			getAllParentDept(allParentDepts,nextParentIdString);
		}
		return allParentDepts;
	}
	
	private String getListStrSplit(List list){
		String str = "";
		if(list!=null && list.size() > 0){
			int childlListSize = list.size();
			int thousandNumber = (childlListSize-1)/1000+1;
			for(int j = 0; j < thousandNumber; j++){
				int loopNum = 1000;
				if(j == thousandNumber-1){
					loopNum = childlListSize - (thousandNumber-1)*1000;
				}
				for (int i = 0; i < loopNum; i++) {
					String childDeptId =(String)list.get(1000*j+i);
					if(i == 0){
						str += "'"+childDeptId+"'";
					}else{
						str += ",'"+childDeptId+"'";
					}
				}
				if(j != thousandNumber-1)str += "sp";
			}
		}
		
		return str;
	}

	@Override
	public HashMap<String, String> findGroupDeptAuthFlag(String groupId) {
		HashMap flagmap = new HashMap<String,String>();
		try {
			Session session = super.getSession();
			String querySql = "select deptid,flag from ge_dept_group_role where groupid=?";
			List list  = session.createSQLQuery(querySql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, groupId).list();
			for(int i = 0; i < list.size(); i++){
				Map  map = (Map)list.get(i);
				flagmap.put(map.get("DEPTID"), map.get("FLAG"));
			}		
			return flagmap;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List getAllChildDept(String parentId,List allChildDepts){
		if(allChildDepts == null){
			allChildDepts = new ArrayList();
			allChildDepts.add(parentId);
			parentId = "'"+parentId+"'";
		}
		
		String sqlString = " select deptid from ge_department where ";
		if(parentId.indexOf("sp") == -1){
			sqlString += " parentid in ( "+parentId+" ) ";
		}else{
			String[] parentIdArr = parentId.split("sp");
			for(int i = 0; i < parentIdArr.length; i++){
				if(i != 0)sqlString += " or ";
				sqlString += " parentid in ( "+parentIdArr[i]+" ) ";
			}
		}
		List childlList = super.findBySql(sqlString);
		
		String nextParentIdString = "";
		if(childlList!=null && childlList.size() > 0){
			int childlListSize = childlList.size();
			int thousandNumber = (childlListSize-1)/1000+1;
			for(int j = 0; j < thousandNumber; j++){
				int loopNum = 1000;
				if(j == thousandNumber-1){
					loopNum = childlListSize - (thousandNumber-1)*1000;
				}
				for (int i = 0; i < loopNum; i++) {
					String childDeptId =(String)childlList.get(1000*j+i);
					if(i == 0){
						nextParentIdString += "'"+childDeptId+"'";
					}else{
						nextParentIdString += ",'"+childDeptId+"'";
					}
					allChildDepts.add(childDeptId);
				}
				if(j != thousandNumber-1)nextParentIdString += "sp";
			}
			getAllChildDept(nextParentIdString,allChildDepts);
		}
		return allChildDepts;
	}
}
