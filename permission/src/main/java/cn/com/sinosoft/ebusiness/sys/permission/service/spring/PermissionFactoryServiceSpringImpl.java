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
	 * ���ݹ���Ա�ı����ѯ��ӵ�е�Ȩ�޼�Ȩ�޶�Ӧ�Ļ���Ȩ��
	 * ע�����ؽ��<Ȩ��ID,��ӦȨ�޵Ļ���Ȩ�޵�Set����>
	 * @param operatorId
	 * @return
	 */
	private  Map<String,Set<String>> deptAuthId(String operatorId){
		Map<String, Map<String, Set<String>>> roleAuthorityDeptHashMap = geDeptGroupRoleService.findRoleAuthorityDept(operatorId);
		Map<String,Set<String>> result = new HashMap<String,Set<String>>();
		Iterator<String> raIterator=roleAuthorityDeptHashMap.get("ROLEAUTHORITY").keySet().iterator();//��ɫȨ��key,Iterator
		Set<String> raHashSetTemp=new HashSet<String>();//��ʱ��Ž�ɫ"Ȩ��"HashSet
		while(raIterator.hasNext()){
			String roleId = (String)raIterator.next();//��ɫid
			Set<String> depHashSetTemp=new HashSet<String>();//��ʱ��Ż���HashSet
			depHashSetTemp = roleAuthorityDeptHashMap.get("ROLEDEPT").get(roleId);//��ʱ��Ż���HashSet
			raHashSetTemp = roleAuthorityDeptHashMap.get("ROLEAUTHORITY").get(roleId);//��ɫ��Ӧ��Ȩ��HashSet
			Iterator<String> raIteratorTemp=raHashSetTemp.iterator();//��ɫ��Ӧ��Ȩ��Iterator
			while(raIteratorTemp.hasNext()){//Ȩ��
				String authorityId = (String)raIteratorTemp.next();//Ȩ��id
				if(result.get(authorityId)==null||result.get(authorityId).isEmpty()){//map�в�����key���½�
					result.put(authorityId, depHashSetTemp);
				}else{//map�д���key������
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
	@LocusTrace(setDesc="�����û�id��ȡ����ӵ�еĹ��ܺͻ���Ȩ��")
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
	@LocusTrace(setDesc="��ѯ��������ԱȨ��")
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
