package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeEnterpriseAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeEnterpriseAuthorityService;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.StringUtils;

public class GeEnterpriseAuthorityServiceSpringImpl extends GenericDaoHibernate<GeEnterpriseAuthority, String> implements GeEnterpriseAuthorityService{

	private Map<String,List<Map<String,String>>> menu = new HashMap<String,List<Map<String,String>>>();
	
	public void setMenu(Map<String, List<Map<String, String>>> menu) {
		this.menu = menu;
	}

	
	@Override
	public Map<String, List<Map<String, String>>> getMenu() {
		Map<String,List<Map<String,String>>> MenuHashMap = new HashMap<String,List<Map<String,String>>>();
		String MenuLv0Sql = "select t.AUTHORITYID,t.AUTHORITYNAME,t.AUTHORITYTYPE,t.AUTHORITYDESP,t.AUTHORITYLINK," +
				"t.AUTHORITYLEVEL,t.PARENTID,t.AUTHORITYORDER from GE_ENTERPRISE_AUTHORITY t where t.authorityLevel='1' order by TO_NUMBER(t.authorityorder) ";
		String MenuLv1Sql = "select t.AUTHORITYID,t.AUTHORITYNAME,t.AUTHORITYTYPE,t.AUTHORITYDESP,t.AUTHORITYLINK," +
				"t.AUTHORITYLEVEL,t.PARENTID,t.AUTHORITYORDER from GE_ENTERPRISE_AUTHORITY t where t.authoritylevel='2' order by t.parentid ,TO_NUMBER(t.authorityorder) ";
		List MenuLv0Obj = super.findBySql(MenuLv0Sql);//0级菜单
		List MenuLv1Obj = super.findBySql(MenuLv1Sql);//1级菜单
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
		this.setMenu(MenuHashMap);
		return MenuHashMap;
	}

	@Override
	@LocusTrace(setDesc="条件查询企业功能权限")
	public List<GeEnterpriseAuthority> findAllAuthoritys(QueryRule queryRule) {
		return super.find(queryRule);
	}

	@Override
	public boolean save(GeEnterpriseAuthority geEnterpriseAuthority) {
		boolean flag = false;
		try {
			super.save(geEnterpriseAuthority);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@LocusTrace(setDesc="更新企业权限")
	public boolean updates(GeEnterpriseAuthority geEnterpriseAuthority) {
		try{
			super.update(geEnterpriseAuthority);
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	@Override
	@LocusTrace(setDesc="删除企业权限")
	public boolean delete(GeEnterpriseAuthority geEnterpriseAuthority) {
		boolean flag = false;
		try {
			super.delete(geEnterpriseAuthority);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@LocusTrace(setDesc="主键查询企业权限")
	public GeEnterpriseAuthority findGeEnterpriseAuthorityByPK(String pk) {
		try {
			return super.findUnique("authorityID", pk);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@LocusTrace(setDesc="判断企业权限父节点下是否存在子节点")
	public boolean isHasChild(String pid) {
		Session session = super.getSession();
		String querySql1 = "select count(1) sum from GE_ENTERPRISE_AUTHORITY t where t.PARENTID = ?";
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
	@LocusTrace(setDesc="根据父节点查询出所有其子节点")
	public List getChildList(String pid) {
		Session session = super.getSession();
		String querySql1 = "select t.AUTHORITYID from GE_ENTERPRISE_AUTHORITY t where t.PARENTID = ?";
		List list  = session.createSQLQuery(querySql1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, pid).list();
		session.flush();
		if(list == null){
			return new ArrayList();
		}else{
			return list;	
		}
	}

	@Override
	@LocusTrace(setDesc="根据企业用户等级查询其功能权限")
	public List findLevelAuthoritys(String enperpriseLevel) {
		List<String> result = new ArrayList<String>();
		if(enperpriseLevel!=null&&!"".equals(enperpriseLevel)){
			String sql = "select t.authorityid from GE_ENTERPRISELEVEL_AUTHORITY t where t.USERLEVEL=?";
			result = super.findBySql(sql,enperpriseLevel);//用户等级权限
		}
		return  result;
	}
	@Override
	@LocusTrace(setDesc="判断企业权限是否存在某权限")
	public boolean existRoleAuthority(String pk) {
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
	@LocusTrace(setDesc="保存权限设置")
	public boolean saveLevelAuthoritys(String enterpriseLevel, String authoritys) {
		Session session = null;
		/**删除在USERLEVEL下的所有权限*/
		String deleteSql = "delete from GE_ENTERPRISELEVEL_AUTHORITY t where t.USERLEVEL=?";
		session = super.getSession();
		session.createSQLQuery(deleteSql).setString(0, enterpriseLevel).executeUpdate();
		
		String[] authorityArr = authoritys.split(",");
		if(!StringUtils.isBlank(authorityArr[0])){
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT all ");
			for(int i = 0; i < authorityArr.length; i++){
				sql.append("INTO GE_ENTERPRISELEVEL_AUTHORITY VALUES('"+enterpriseLevel+"','"+authorityArr[i]+"') ");
			}
			sql.append("select sysdate from dual");
			session.createSQLQuery(sql.toString()).executeUpdate();
		}
		return true;
	}

	@Override
	@LocusTrace(setDesc="根据等级查询出企业权限")
	public List getEnterpriseAuthoritysByLevel(String level) {
		String sql = "select AUTHORITYID from GE_ENTERPRISELEVEL_AUTHORITY where USERLEVEL=?";
		List<String> result = super.findBySql(sql,level);//角色权限
		if(result == null){
			return new ArrayList();
		}else{
			return result;
		}
	}
	@LocusTrace(setDesc="根据权限查询出其等级")
	public boolean findLevelByAuthorityid(String authorityid){
		List<String> result = new ArrayList<String>();
		String sql = "select t.USERLEVEL from GE_ENTERPRISELEVEL_AUTHORITY t where t.authorityid=?";
		result = super.findBySql(sql,authorityid);
		if(result.size() > 0){
			return true;
		}else{
			return false;
		}
	}


}
