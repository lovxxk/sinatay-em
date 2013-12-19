package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Transformer;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptGroupRole;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptGroupRoleId;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeProposalareaService;

public class GeProposalareaServiceImpl   extends 
 GenericDaoHibernate<GeDeptGroupRole, GeDeptGroupRoleId> implements GeProposalareaService{
	
	
	public String getAllVirtualProParent(String groupDeptAuths) {
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
	
	public String getProSelfName(GeDepartment geDepartment) {
		if(geDepartment == null)return "";
		String level = geDepartment.getDeptlevel();
		/*String suffix = "";

		if("2".equals(level)){
			suffix = "总公司";
		}else{
			suffix = "公司";
		}*/
		return geDepartment.getDeptname();
	}
	
	/**
	 * 查询销售区域表以及deptment表交集id
	 * @return
	 */
	public Map<String,List> findPropoAreaDept(){
		Map<String,List> tempMap = new HashMap<String,List>();
		StringBuffer bf = new StringBuffer();
		bf.append(" select k.deptid, k.deptname ,k.deptlevel,k.parentid from ge_proposalarea t ");
		bf.append(" left join ge_department k on t.deptid = k.deptid ");
		bf.append("  start with k.parentid = '0' connect by prior k.deptid = k.parentid  ");
		bf.append(" and k.businessarea='3'  order by k.deptid asc ");
		Query query = super.getSession().createSQLQuery(bf.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//.addEntity(GeDepartment.class)
		 List templist = query.list();
		 List list = new ArrayList();
		 for(int i=0;i<templist.size();i++){
			 Map map = (Map)templist.get(i);
			 GeDepartment ge = new GeDepartment();
			 ge.setDeptid((String)map.get("DEPTID"));
			 ge.setDeptname((String)map.get("DEPTNAME"));
			 ge.setDeptlevel((String)map.get("DEPTLEVEL"));
			 ge.setParentid((String)map.get("PARENTID"));
			 list.add(ge);
		 }
		 for(int j=0;j<list.size();j++){
			 if(!tempMap.containsKey(((GeDepartment)list.get(j)).getParentid())){
				 List tempChild = new ArrayList();
				 tempChild.add((GeDepartment)list.get(j));
				 tempMap.put(((GeDepartment)list.get(j)).getParentid(),tempChild);
			 }else{
				List l =  tempMap.get(((GeDepartment)list.get(j)).getParentid());
				l.add((GeDepartment)list.get(j));
			 }
		 }
		return tempMap;
	}
	
	/**
	 * 权限deptid
	 * @param query
	 * @return
	 */
	public String findDeptString(String areaCode){
		QueryRule query = QueryRule.getInstance();
        List<String> values = Arrays.asList(areaCode.split(","));
			String[] authorityIdAll = areaCode.split(",");
			String sql="(DEPTID in";
			if(authorityIdAll!=null && authorityIdAll.length > 0){
				int authorityIdSize = authorityIdAll.length;
				int authorityIdNumber = (authorityIdSize-1)/1000+1;
				String nextParentIdString = "";
				for(int j = 0; j < authorityIdNumber; j++){
					if(nextParentIdString.length()>0){
						 nextParentIdString = "";
						sql+="or DEPTID in";
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
		query.addSql(sql);
		List tempList = super.find(query);
		return tempList.toString() ;
	}
}
