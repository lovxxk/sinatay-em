package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.StringUtils;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeUserAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeUserAuthorityService;

public class GeUserAuthorityServiceSpringImpl extends GenericDaoHibernate<GeUserAuthority,String> implements GeUserAuthorityService {

	private Map<String,List<Map<String,String>>> menu = new HashMap<String,List<Map<String,String>>>();
	
	public void setMenu(Map<String, List<Map<String, String>>> menu) {
		this.menu = menu;
	}
	
	@Override
	public Map<String, List<Map<String, String>>> getMenu() {
		Map<String,List<Map<String,String>>> MenuHashMap = new HashMap<String,List<Map<String,String>>>();
		String MenuLv0Sql = "select t.AUTHORITYID,t.AUTHORITYNAME,t.AUTHORITYTYPE,t.AUTHORITYDESP,t.AUTHORITYLINK," +
				"t.AUTHORITYLEVEL,t.PARENTID,t.AUTHORITYORDER from ge_user_authority t where t.authorityLevel='1' order by TO_NUMBER(t.authorityorder) ";
		String MenuLv1Sql = "select t.AUTHORITYID,t.AUTHORITYNAME,t.AUTHORITYTYPE,t.AUTHORITYDESP,t.AUTHORITYLINK," +
				"t.AUTHORITYLEVEL,t.PARENTID,t.AUTHORITYORDER from ge_user_authority t where t.authoritylevel='2' order by t.parentid ,TO_NUMBER(t.authorityorder) ";
		List MenuLv0Obj = super.findBySql(MenuLv0Sql);//0���˵�
		List MenuLv1Obj = super.findBySql(MenuLv1Sql);//1���˵�
		//0���˵�
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
		//1���˵�
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
	@LocusTrace(setDesc="������ѯ��ǰ̨�û�Ȩ�޼���")
	public List<GeUserAuthority> findAllAuthoritys(QueryRule queryRule) {
		return super.find(queryRule);
	}

	@Override
	@LocusTrace(setDesc="����ǰ̨�û�Ȩ��")
	public boolean save(GeUserAuthority geUserAuthority) {
		boolean flag = false;
		try {
			super.save(geUserAuthority);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@LocusTrace(setDesc="����ǰ̨�û�Ȩ��")
	public boolean updates(GeUserAuthority geUserAuthority) {
		try{
			super.update(geUserAuthority);
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	@Override
	@LocusTrace(setDesc="ɾ��ǰ̨�û�Ȩ��")
	public boolean delete(GeUserAuthority geUserAuthority) {
		boolean flag = false;
		try {
			super.delete(geUserAuthority);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@LocusTrace(setDesc="������ѯǰ̨�û�Ȩ��")
	public GeUserAuthority findGeUserAuthorityByPK(String pk) {
		try {
			return super.findUnique("authorityID", pk);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@LocusTrace(setDesc="�ж�ǰ̨�û�Ȩ�޸��ڵ����Ƿ����ӽڵ�")
	public boolean isHasChild(String pid){
		Session session = super.getSession();
		String querySql1 = "select count(1) sum from GE_USER_AUTHORITY t where t.PARENTID = ?";
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
	@LocusTrace(setDesc="��ȡǰ̨�û�Ȩ�޸��ڵ��µ������ӽڵ�")
	public List getChildList(String pid) {
		Session session = super.getSession();
		String querySql1 = "select t.AUTHORITYID from GE_USER_AUTHORITY t where t.PARENTID = ?";
		List list  = session.createSQLQuery(querySql1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, pid).list();
		session.flush();
		if(list == null){
			return new ArrayList();
		}else{
			return list;	
		}
	}

	@Override
	@LocusTrace(setDesc="�����û��ȼ���ѯ��ǰ̨�û���ӵ�е�Ȩ��")
	public List findLevelAuthoritys(String userLevel) {
		List<String> result = new ArrayList<String>();
		if(userLevel!=null&&!"".equals(userLevel)){
			String sql = "select t.authorityid from GE_USERLEVEL_AUTHORITY t where t.USERLEVEL=?";
			result = super.findBySql(sql,userLevel);//�û��ȼ�Ȩ��
		}
		return  result;
	}

	@Override
	@LocusTrace(setDesc="�ж��Ƿ���ڴ�Ȩ��")
	public boolean existRoleAuthority(String pk) {
		List<String> result = new ArrayList<String>();
		String sql = "select t.userlevel from GE_USERLEVEL_AUTHORITY t where t.authorityid=?";
		result = super.findBySql(sql,pk);//��ɫȨ��
		if(result.size() > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	@LocusTrace(setDesc="��������Ȩ��")
	public boolean saveLevelAuthoritys(String userLevel,String authoritys){
		Session session = null;
		/**ɾ����USERLEVEL�µ�����Ȩ��*/
		String deleteSql = "delete from GE_USERLEVEL_AUTHORITY t where t.USERLEVEL=?";
		session = super.getSession();
		session.createSQLQuery(deleteSql).setString(0, userLevel).executeUpdate();
		
		String[] authorityArr = authoritys.split(",");
		if(!StringUtils.isBlank(authorityArr[0])){
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT all ");
			for(int i = 0; i < authorityArr.length; i++){
				sql.append("INTO GE_USERLEVEL_AUTHORITY VALUES('"+userLevel+"','"+authorityArr[i]+"') ");
			}
			sql.append("select sysdate from dual");
			session.createSQLQuery(sql.toString()).executeUpdate();
		}
		return true;
	}
	@LocusTrace(setDesc="��ȡĳ�ȼ��µ�����ǰ̨�û�Ȩ��")
	public List getUserAuthoritysByLevel(String level){
		String sql = "select AUTHORITYID from GE_USERLEVEL_AUTHORITY where USERLEVEL=?";
		List<String> result = super.findBySql(sql,level);//��ɫȨ��
		if(result == null){
			return new ArrayList();
		}else{
			return result;
		}
	}
}
