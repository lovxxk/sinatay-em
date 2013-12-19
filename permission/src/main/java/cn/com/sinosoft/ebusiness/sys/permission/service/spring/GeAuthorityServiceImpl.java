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
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeRole;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeAuthorityService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeRoleService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.PermissionFactoryService;

public class GeAuthorityServiceImpl extends GenericDaoHibernate<GeAuthority, String> 
implements GeAuthorityService {
	@Autowired
	private GeRoleService geRoleService;
	@Autowired
	private PermissionFactoryService permissionFactoryService;

	private Map<String,List<Map<String,String>>> menu = new HashMap<String,List<Map<String,String>>>();
	
	public void setMenu(Map<String, List<Map<String, String>>> menu) {
		this.menu = menu;
	}

	@Override
	@LocusTrace(setDesc="获取菜单")
	public Map<String,List<Map<String,String>>> getMenu() {
		Map<String,List<Map<String,String>>> MenuHashMap = new HashMap<String,List<Map<String,String>>>();
		String MenuLv0Sql = "select t.AUTHORITYID,t.AUTHORITYNAME,t.AUTHORITYTYPE,t.AUTHORITYDESP,t.AUTHORITYLINK," +
				"t.AUTHORITYLEVEL,t.PARENTID,t.AUTHORITYORDER from GE_AUTHORITY t where t.authoritylevel='1' order by t.authorityorder ";
		String MenuLv1Sql = "select t.AUTHORITYID,t.AUTHORITYNAME,t.AUTHORITYTYPE,t.AUTHORITYDESP,t.AUTHORITYLINK," +
				"t.AUTHORITYLEVEL,t.PARENTID,t.AUTHORITYORDER from GE_AUTHORITY t where t.authoritylevel='2' order by t.parentid ,TO_NUMBER(t.authorityorder) ";
		String MenuLv2Sql = "select t.AUTHORITYID,t.AUTHORITYNAME,t.AUTHORITYTYPE,t.AUTHORITYDESP,t.AUTHORITYLINK," +
				"t.AUTHORITYLEVEL,t.PARENTID,t.AUTHORITYORDER from GE_AUTHORITY t where t.authoritylevel='3' order by t.parentid ,TO_NUMBER(t.authorityorder) ";
		List MenuLv0Obj = super.findBySql(MenuLv0Sql);//0级菜单
		List MenuLv1Obj = super.findBySql(MenuLv1Sql);//1级菜单
		List MenuLv2Obj = super.findBySql(MenuLv2Sql);//2级菜单
		//0级菜单
		Map<String,String> MenuLv0HashMap = new HashMap<String,String>();
		List<Map<String,String>> MenuLv0List = new ArrayList<Map<String,String>>();
		for(int i=0;MenuLv0Obj!=null&&i<MenuLv0Obj.size();i++){
			 Object[] obj = (Object[])MenuLv0Obj.get(i);
			 MenuLv0HashMap = new HashMap<String,String>();
			 MenuLv0HashMap.put("AUTHORITYID", (String)obj[0]);
			 MenuLv0HashMap.put("AUTHORITYNAME", (String)obj[1]);
			 MenuLv0HashMap.put("AUTHORITYTYPE", (String)obj[2]);
			 MenuLv0HashMap.put("AUTHORITYDESP", (String)obj[3]);
			 MenuLv0HashMap.put("AUTHORITYLINK", (String)obj[4]);
			 MenuLv0HashMap.put("AUTHORITYLEVEL", (String)obj[5]);
			 MenuLv0HashMap.put("PARENTID", (String)obj[6]);
			 MenuLv0HashMap.put("AUTHORITYORDER", (String)obj[7]);
			 MenuLv0List.add(MenuLv0HashMap);
		}
		MenuHashMap.put("ROOTMENU",MenuLv0List);
		//1级菜单
		Map<String,String> MenuLv1HashMap = new HashMap<String,String>();
		List<Map<String,String>> MenuLv1List = new ArrayList<Map<String,String>>();
		String parentid = "";
		for(int i=0;MenuLv1Obj!=null&&i<MenuLv1Obj.size();i++){
			 Object[] obj = (Object[])MenuLv1Obj.get(i);
			 MenuLv1HashMap = new HashMap<String,String>();
			 MenuLv1HashMap.put("AUTHORITYID", (String)obj[0]);
			 MenuLv1HashMap.put("AUTHORITYNAME", (String)obj[1]);
			 MenuLv1HashMap.put("AUTHORITYTYPE", (String)obj[2]);
			 MenuLv1HashMap.put("AUTHORITYDESP", (String)obj[3]);
			 MenuLv1HashMap.put("AUTHORITYLINK", (String)obj[4]);
			 MenuLv1HashMap.put("AUTHORITYLEVEL", (String)obj[5]);
			 MenuLv1HashMap.put("PARENTID", (String)obj[6]);
			 MenuLv1HashMap.put("AUTHORITYORDER", (String)obj[7]);
			 if(!"".equals(parentid)&&!parentid.equals(MenuLv1HashMap.get("PARENTID"))){
				 MenuHashMap.put(parentid,MenuLv1List);
				 MenuLv1List = new ArrayList<Map<String,String>>();
				 parentid = MenuLv1HashMap.get("PARENTID");
			 }
			 MenuLv1List.add(MenuLv1HashMap);
			 parentid = MenuLv1HashMap.get("PARENTID");
		}
		MenuHashMap.put(parentid,MenuLv1List);
		//2级菜单
		parentid = "";
		Map<String,String> MenuLv2HashMap = new HashMap<String,String>();
		List<Map<String,String>> MenuLv2List = new ArrayList<Map<String,String>>();
		for(int i=0;MenuLv2Obj!=null&&i<MenuLv2Obj.size();i++){
			 Object[] obj = (Object[])MenuLv2Obj.get(i);
			 MenuLv2HashMap = new HashMap<String,String>();
			 MenuLv2HashMap.put("AUTHORITYID", (String)obj[0]);
			 MenuLv2HashMap.put("AUTHORITYNAME", (String)obj[1]);
			 MenuLv2HashMap.put("AUTHORITYTYPE", (String)obj[2]);
			 MenuLv2HashMap.put("AUTHORITYDESP", (String)obj[3]);
			 MenuLv2HashMap.put("AUTHORITYLINK", (String)obj[4]);
			 MenuLv2HashMap.put("AUTHORITYLEVEL", (String)obj[5]);
			 MenuLv2HashMap.put("PARENTID", (String)obj[6]);
			 MenuLv2HashMap.put("AUTHORITYORDER", (String)obj[7]);
			 if(!"".equals(parentid)&&!parentid.equals(MenuLv2HashMap.get("PARENTID"))){
				 MenuHashMap.put(parentid,MenuLv2List);
				 MenuLv2List = new ArrayList<Map<String,String>>();
				 parentid = MenuLv2HashMap.get("PARENTID");
			 }
			 MenuLv2List.add(MenuLv2HashMap);
			 parentid = MenuLv2HashMap.get("PARENTID");
		}
		MenuHashMap.put(parentid,MenuLv2List);
		this.setMenu(MenuHashMap);
		return MenuHashMap;


	}
	
	@Override
	@LocusTrace(setDesc="根据条件查询区域集合")
	public List<GeAuthority> findAllAuthoritys(QueryRule queryRule){
		return super.find(queryRule);
	}
	
	@Override
	@LocusTrace(setDesc="保存权限")
	public boolean save(GeAuthority geAuthority){
		boolean flag = false;
		try {
			super.save(geAuthority);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	@LocusTrace(setDesc="更新权限")
	public boolean updates(GeAuthority geAuthority){
		try{
			super.update(geAuthority);
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	@Override
	@LocusTrace(setDesc="删除权限")
	public boolean delete(GeAuthority geAuthority){
		boolean flag = false;
		try {
			super.delete(geAuthority);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	@LocusTrace(setDesc="根据主键查询权限对象")
	public GeAuthority findGeAuthorityByPK(String pk){
		try {
			return super.findUnique("authorityID", pk);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@LocusTrace(setDesc="根据权限父节点查询其子权限id")
	public List getChildList(String pid){
		Session session = super.getSession();
		String querySql1 = "select t.AUTHORITYID from GE_AUTHORITY t where t.PARENTID = ?";
		List list  = session.createSQLQuery(querySql1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, pid).list();
		session.flush();
		if(list == null){
			return new ArrayList();
		}else{
			return list;	
		}
	}
	
	@Override
	@LocusTrace(setDesc="根据父节点判断其是否有子节点")
	public boolean isHasChild(String pid){
		Session session = super.getSession();
		String querySql1 = "select count(1) sum from GE_AUTHORITY t where t.PARENTID = ?";
		List list1  = session.createSQLQuery(querySql1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, pid).list();
		session.flush();
		Map map1 = (Map)list1.get(0);
		BigDecimal sum1 = (BigDecimal)map1.get("SUM");
		if( sum1.intValue() > 0){
			return true;
		}else{
			return false;
		}
	}
	@LocusTrace(setDesc="通过父节点判断其是否有子节点")
	public boolean isHasChildMenu(String pid){
		Session session = super.getSession();
		String querySql1 = "select count(1) sum from GE_AUTHORITY t where t.PARENTID = ? and t.ISMENU = 1 ";
		List list1  = session.createSQLQuery(querySql1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, pid).list();
		session.flush();
		Map map1 = (Map)list1.get(0);
		BigDecimal sum1 = (BigDecimal)map1.get("SUM");
		if( sum1.intValue() > 0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	@LocusTrace(setDesc="查询角色所拥有的权限")
	public List findRoleAuthoritys(String roleID){
		List<String> result = new ArrayList<String>();
		if(roleID!=null&&!"".equals(roleID)){
			String sql = "select t.authorityid from GE_ROLE_AUTHORITY t where t.roleid=?";
			result = super.findBySql(sql,roleID);//角色权限
		}
		return  result;
	}
	
	@Override
	@LocusTrace(setDesc="根据主键判断某角色是否被使用")
	public boolean existRoleAuthority(String pk){
		List<String> result = new ArrayList<String>();
		String sql = "select t.roleid from GE_ROLE_AUTHORITY t where t.authorityid=?";
		result = super.findBySql(sql,pk);//角色权限
		if(result.size() > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	@LocusTrace(setDesc="根据机构权限查询出所拥有的角色")
	public List<Map<String,String>> findAuthorityRole(String authoryid,String groupRoleAuthDeptId) {
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		if(authoryid!=null&&!"".equals(authoryid)){
			String sql="select  r.roleid,r.rolename,ged.deptname from("+
			"select * from ge_role g where g.roleid in("+
			"select t.roleid from GE_ROLE_AUTHORITY t where t.authorityid='"+authoryid+"'))r "+
			"inner join GE_DEPARTMENT ged on ged.deptid=r.deptid where r.deptid in ";
			String[] authorityIdAll = groupRoleAuthDeptId.split(",");
			if(authorityIdAll!=null && authorityIdAll.length > 0){
				int authorityIdSize = authorityIdAll.length;
				int authorityIdNumber = (authorityIdSize-1)/1000+1;
				String nextParentIdString = "";
				for(int j = 0; j < authorityIdNumber; j++){
					if(nextParentIdString.length()>0){
						 nextParentIdString = "";
						sql+="or r.deptid in";
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
			List objList = super.findBySql(sql);//角色权限
			for(int i=0;objList!=null&&i<objList.size();i++){
				 Object[] obj = (Object[])objList.get(i);
				 map = new HashMap<String,String>();
				 map.put("ROLEID", (String)obj[0]);
				 map.put("ROLENAME", (String)obj[1]);
				 map.put("DEPTNAME", (String)obj[2]);
				 result.add(map);
			}
		}
			return  result;
	}
	public Page findAuthorityRole(String authoryid,String groupRoleAuthDeptId,int pageNo,int pageSize) {
		List<GeRole> roleList = new ArrayList<GeRole>();
			String sql1 = "select  r.roleid,r.rolename,ged.deptname";
			String sql=" from("+
			"select * from ge_role g where g.roleid in("+
			"select t.roleid from GE_ROLE_AUTHORITY t where t.authorityid='"+authoryid+"'))r "+
			"inner join GE_DEPARTMENT ged on ged.deptid=r.deptid where r.deptid in ";
			String[] authorityIdAll = groupRoleAuthDeptId.split(",");
			if(authorityIdAll!=null && authorityIdAll.length > 0){
				int authorityIdSize = authorityIdAll.length;
				int authorityIdNumber = (authorityIdSize-1)/1000+1;
				String nextParentIdString = "";
				for(int j = 0; j < authorityIdNumber; j++){
					if(nextParentIdString.length()>0){
						 nextParentIdString = "";
						sql+="or r.deptid in";
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
			String sql2 = "select count(1) num ";
			Query sqlQuery  = super.getSession().createSQLQuery(sql1+sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			sqlQuery.setFirstResult(Page.getStartOfPage(pageNo, pageSize));
			sqlQuery.setMaxResults(pageSize);
			List<Map> objList = sqlQuery.list();
			
			Query sqlQuery2  = super.getSession().createSQLQuery(sql2+sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List totalList = sqlQuery2.list();
			for(int i=0;objList!=null&&i<objList.size();i++){
				Map map = objList.get(i);
				 GeRole geRole = new GeRole();
				 geRole.setRoleID((String)map.get("ROLEID"));
				 geRole.setRoleName((String)map.get("ROLENAME"));
				 geRole.setDeptName((String)map.get("DEPTNAME"));
				 roleList.add(geRole);
			}
			Map map2 = (Map)totalList.get(0);
			BigDecimal totalSize = (BigDecimal)map2.get("NUM");
			 return new Page(Page.getStartOfPage(pageNo, pageSize), totalSize.longValue(), pageSize, roleList);
	}
	@Override
	@LocusTrace(setDesc="查询出权限所对应的系统类型")
	public List findSystemType(Set<String> strings) {
		Session session = super.getSession();
		List systype = new ArrayList();
		String result = permissionFactoryService.transformSetToString(strings);
		if(!StringUtils.isEmpty(result)){
			String sql = "select distinct t.systype from ge_authority t where t.authorityid in('" + result.replaceAll(",", "','") + "') order by systype";
			systype  = super.findBySql(sql);;
		}
		return systype;
	}
	
	@LocusTrace(setDesc="通过角色权限查询出系统类型")
	public List findByGeCodeType(List<GeAuthority> rolsAuthoritys){
		List systype = new ArrayList();
		String List=rolsAuthoritys.toString();
		String subList=List.substring(1,List.length()-1);
		String rolsAuthorityList=StringUtils.absoluteTrim(subList);
		String sql="select distinct g.systype from ge_authority g where g.authorityid in ('"+rolsAuthorityList.replaceAll(",","','")+"')order by systype";
		systype  = super.findBySql(sql);
		return systype;
	}

}
