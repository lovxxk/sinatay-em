package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductProDept;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeProductProDeptService;

public class GeProductProDeptServiceSrpingImpl extends
		GenericDaoHibernate<GeProductProDept, String> implements
		GeProductProDeptService {

	public List findProductSaleDept(String coreProductCode) {
		StringBuffer resultBuffer = new StringBuffer("");
		try {
			String querySql1 = "select deptid from ge_product_prodept where coreProductCode=?";
			List productSaleDept = super.findBySql(querySql1, new Object[]{coreProductCode});
			return productSaleDept;
		} catch (Exception e) {
		}
		return null;
	}
	
	public String getAllVirtualParent(String operatorDeptAuths) {
		List tempList = new ArrayList();
		String[] tempArr = operatorDeptAuths.split(",");
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

	public List<String[]> findProductSaleDepartmet(String productCode,String deptlevel,String parentid) {
		try {
			String querySql1 = "select f.orgid,t.gname ,f.deptid" +
					" from ge_department t,ge_func_dept_rev f " +
					"where f.deptid = t.deptid " +
					"and f.deptid in(select p.deptid from GE_SALE_PRO_PRODEPT p where p.coreproductcode=? ) " +
					"and f.orgid is not null "+
					"and t.parentid=? "+
					"and t.deptlevel = ? order by f.deptid";
			List productSaleDept = super.findBySql(querySql1, new Object[]{productCode,parentid,deptlevel});
			if (productSaleDept == null || productSaleDept.size() == 0) {
				return new ArrayList<String[]>();
			}else{
				ArrayList<String[]> resultList = new ArrayList<String[]>();
				for(int i = 0; i < productSaleDept.size(); i++){
					Object[] strs =  (Object[])productSaleDept.get(i);
					resultList.add(new String[]{(String)strs[0], (String)strs[1]});
				}
				return resultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 根据财险核心机构编码查询集团机构编码
	 * @param serviceId
	 * @return
	 */
	public String findDeptIdByServiceId(String serviceId) {
		String resultSql = "";
			String querySql = "select g.deptid from ge_func_dept_rev g where g.serviceid = ?";
			SQLQuery sqlQuery = super.getSession().createSQLQuery(querySql);
			sqlQuery.setString(0, serviceId);
            resultSql=(String)sqlQuery.uniqueResult();
            return resultSql;
	}
	
	/**
	 * 根据寿险核心机构编码查询集团机构编码
	 */
	public String findDeptIdByOrgId(String orgDeptId){
		String result = "";
		try {
			String querySql = "select f.deptid from ge_func_dept_rev f where f.orgid is not null and f.orgid = ?";
			List resultList = super.findBySql(querySql, new Object[]{orgDeptId});
			if (resultList != null && resultList.size() > 0) {
				for(int i = 0; i < resultList.size(); i++){
					result = (String)resultList.get(i);
					break;
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 根据集团机构编码查询寿险核心机构编码
	 */
	public String findOrgIdByDeptId(String orgId){
		String result = "";
		try {
			String querySql = "select f.orgid from ge_func_dept_rev f where f.orgid is not null and f.deptid = ?";
			List resultList = super.findBySql(querySql, new Object[]{orgId});
			if (resultList != null && resultList.size() > 0) {
				for(int i = 0; i < resultList.size(); i++){
					result = (String)resultList.get(i);
					break;
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String findDeptNameByOrgId(String orgDeptId) {
		String result = "";
		try {
			String querySql = "select k.deptname from ge_department k where k.deptid = (select t.deptid from ge_func_dept_rev t where t.orgid = ?)";
			List resultList = super.findBySql(querySql, new Object[]{orgDeptId});
			result = (String)resultList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


}
