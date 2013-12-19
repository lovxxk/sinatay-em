package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDeptGroupRoleService;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.PermissionFactoryService;
import cn.com.sinosoft.lang.ObjectUtils;

public class PermissionFactoryServiceSpringImpl  extends GenericDaoHibernate implements PermissionFactoryService{
	private GeDeptGroupRoleService geDeptGroupRoleService;
	
	public GeDeptGroupRoleService getGeDeptGroupRoleService() {
		return geDeptGroupRoleService;
	}

	public void setGeDeptGroupRoleService(
			GeDeptGroupRoleService geDeptGroupRoleService) {
		this.geDeptGroupRoleService = geDeptGroupRoleService;
	}

	@Override
	public  List<String> transformSetToList(Set<String> set){
		List<String> result = new ArrayList<String>();
		try{
			result = new ArrayList<String>(set); 
		}catch(Exception e){
		}
		return result;
	}
	
	@Override
	public  String transformSetToString(Set<String> set){
		String result = "";
		try{
			Iterator<String> iteratorTemp=set.iterator();
			while(iteratorTemp.hasNext()){
				if(!"".equals(result)){
					result = result+","+iteratorTemp.next();
				}else{
					result = iteratorTemp.next();
				}
				
			}
		}catch(Exception e){
			//
		}
		return result;
	}
	
	@Override
	public  List<String> transformStringToList(String str){
		List<String> result = new ArrayList<String>(); 
		try{
			String[] tempArrays = str.split(",");
			result = Arrays.asList(tempArrays); 
		}catch(Exception e){
			//
		}
		return result;
	}
	
	/**
	 * 根据管理员的编码查询出拥有的权限及权限对应的机构权限
	 * 注：返回结果<权限ID,对应权限的机构权限的Set集合>
	 * @param operatorId
	 * @return
	 */
	private  Map<String,Set<String>> deptAuthId(String operatorId){
		Map<String, Map<String, Set<String>>> roleAuthorityDeptHashMap = geDeptGroupRoleService.findRoleAuthorityDept(operatorId);
		Map<String,Set<String>> result = new HashMap<String,Set<String>>();
		Iterator<String> raIterator=roleAuthorityDeptHashMap.get("ROLEAUTHORITY").keySet().iterator();//角色权限key,Iterator
		Set<String> raHashSetTemp=new HashSet<String>();//临时存放角色"权限"HashSet
		while(raIterator.hasNext()){
			String roleId = (String)raIterator.next();//角色id
			Set<String> depHashSetTemp=new HashSet<String>();//临时存放机构HashSet
			depHashSetTemp = roleAuthorityDeptHashMap.get("ROLEDEPT").get(roleId);//临时存放机构HashSet
			raHashSetTemp = roleAuthorityDeptHashMap.get("ROLEAUTHORITY").get(roleId);//角色对应的权限HashSet
			Iterator<String> raIteratorTemp=raHashSetTemp.iterator();//角色对应的权限Iterator
			while(raIteratorTemp.hasNext()){//权限
				String authorityId = (String)raIteratorTemp.next();//权限id
				if(result.get(authorityId)==null||result.get(authorityId).isEmpty()){//map中不存在key，新建
					result.put(authorityId, depHashSetTemp);
				}else{//map中存在key，更新
					try{
						Set<String> hash = (Set<String>)ObjectUtils.cloneObject(result.get(authorityId));
						hash.addAll(depHashSetTemp);
						result.put(authorityId, hash);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		}
		return result;
	}
	
	@Override
	@LocusTrace(setDesc="根据用户id获取其所拥有的功能和机构权限")
	public  Map<String,String> deptAuthIdString(String operatorId){
		Map<String, Set<String>> roleAuthorityDeptHashMap = this.deptAuthId(operatorId);
		Map<String,String> result = new HashMap<String,String>();
		Iterator<String> iteratorTemp=roleAuthorityDeptHashMap.keySet().iterator();
		while(iteratorTemp.hasNext()){
			String authorityId = iteratorTemp.next();
			result.put(authorityId, this.transformSetToString(roleAuthorityDeptHashMap.get(authorityId)));
		}
		return result;
	}
	
	@Override
	@LocusTrace(setDesc="查询超级管理员权限")
	public Map<String, String> alldeptAuthIdString(){
		Map<String, String> alldeptAuthIdMap = new HashMap<String, String>();
		Session session = super.getSession();
		String deptSql = "select t.deptid from GE_DEPARTMENT t";
		String authSql = "select t.authorityid from Ge_Authority t";
		List deptList  = session.createSQLQuery(deptSql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		String deptIds = "";
		if(deptList != null){
			for(int i = 0; i < deptList.size(); i++){
				String deptIdString = (String)((Map)deptList.get(i)).get("DEPTID");
				if(i == 0){
					deptIds = deptIdString;
				}else{
					deptIds += "," + deptIdString;
				}
			}
		}
		
		List authList  = session.createSQLQuery(authSql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		if(authList != null){
			for(int i = 0; i < authList.size(); i++){
				String authIdString = (String)((Map)authList.get(i)).get("AUTHORITYID");
				alldeptAuthIdMap.put(authIdString, deptIds);
			}
		}
		return alldeptAuthIdMap;
	}
	
	@Override
	public  List<String> findDeptAuthId(String authorityid, HttpSession session){
		Map hm = (HashMap) session.getAttribute("permission");
		return this.transformStringToList((String) hm.get(authorityid));
	}
	
	@Override
	public  List<String> findDeptAuthId(String authorityid, HttpSession session, List<String> DeptAuthId){
		Map hm  = (HashMap) session.getAttribute("permission");
		List<String> result = this.transformStringToList((String) hm.get(authorityid));
		result.removeAll(DeptAuthId);
		return result;
	}
	
	@Override
	public  List<String> findDeptAuthId(String authorityid, HttpSession session, String DeptAuthId){
		Map hm  = (HashMap) session.getAttribute("permission");
		List<String> result = this.transformStringToList((String) hm.get(authorityid));
		result.removeAll(this.transformStringToList(DeptAuthId));
		return result;
	}
}
