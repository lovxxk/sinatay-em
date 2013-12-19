package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.moduleException.permissionException.PermissionSysException;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.service.facade.GeDepartmentService;

/**
 *  
 *
 */
public class GeDepartmentServiceSpringImpl extends GenericDaoHibernate<GeDepartment, String> implements GeDepartmentService {
	@Override
	@LocusTrace(setDesc="保存机构")
	public boolean save(GeDepartment geDepartment) {
		boolean flag = false;
		try {
			super.save(geDepartment);
			flag = true;
		} catch (Exception e) {
			throw PermissionSysException.newInstanceCode("001");
		}
		return flag;
	}

	@Override
	@LocusTrace(setDesc="更新机构信息")
	public boolean updates(GeDepartment geDepartment) {
		try{
			super.update(geDepartment);
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	@Override
	@LocusTrace(setDesc="删除机构信息")
	public boolean delete(GeDepartment geDepartment) {
		boolean flag = false;
		try {
			super.delete(geDepartment);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	@LocusTrace(setDesc="通过主键查询出机构对象")
	public GeDepartment findGeDepartmentByPK(String pk){
		try {
			return super.findUnique("deptid", pk);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Page find(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/**
	 * 根据主键判断对象是否存在
	 * 
	 * @param pk
	 * @return
	 */
	@Override
	@LocusTrace(setDesc="查询出该机构是否存在")
	public boolean exists(String pk) {
		return super.exists(GeDepartment.class, pk);
	}

	@Override
	@LocusTrace(setDesc="根据条件查询机构集合")
	public List findAllDepartments(QueryRule queryRule) {
		return super.find(queryRule);
	}
	
	@Override
	@LocusTrace(setDesc="判断机构下是否存在子机构")
	public boolean hasChild(String id){
		Session session = super.getSession();
		String querySql1 = "select count(1) sum from GE_DEPARTMENT t where t.PARENTID = ?";
		List list1  = session.createSQLQuery(querySql1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, id).list();
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
	@LocusTrace(setDesc="判断该机构下是否存在用户")
	public boolean hasOperator(String deptId){
		Session session = super.getSession();
		String querySql1 = "select count(1) sum from GE_OPERATOR t where t.DEPTID = ?";
		List list1  = session.createSQLQuery(querySql1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, deptId).list();
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
	@LocusTrace(setDesc="判断该机构下是否有角色机构权限使用")
	public boolean hasDeptGroupRole(String deptId){
		Session session = super.getSession();
		String querySql1 = "select count(1) sum from GE_DEPT_GROUP_ROLE t where t.DEPTID = ?";
		List list1  = session.createSQLQuery(querySql1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, deptId).list();
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
	@LocusTrace(setDesc="根据父节点查询其子节点机构编号")
	public List getChildList(String pid){
		Session session = super.getSession();
		String querySql1 = "select t.deptid from GE_DEPARTMENT t where t.PARENTID = ?";
		List list  = session.createSQLQuery(querySql1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString(0, pid).list();
		session.flush();
		if(list == null){
			return new ArrayList();
		}else{
			return list;	
		}
	}
	
	@Override
	@LocusTrace(setDesc="根据机构id查询出其所有子节点")
	public List<GeDepartment> findDepartmentByDepId(List list,String deptid){
		if(list == null)list = new ArrayList<GeDepartment>();
		GeDepartment geDepartment = findGeDepartmentByPK(deptid);
		list.add(geDepartment);
		if(!"0".equals(geDepartment.getParentid())){
			findDepartmentByDepId(list,geDepartment.getParentid());
		}
		return list;
	}
	
	@LocusTrace(setDesc="查询机构对象")
	public List<GeDepartment> findDepartment(String businessArea, String deptLevel, String parentId){
		String sql = "select DEPTID,GNAME from GE_DEPARTMENT t where t.businessarea=? and t.DEPTLEVEL=? and t.parentid=? and t.deptid not like '%9999' order by t.deptid";
		List<GeDepartment> list = super.findBySql(sql, new Object[]{businessArea, deptLevel, parentId});
		return list;
	}
	
	@Override
	public List<GeDepartment> findSiblingDepartments(String deptId){
		String sql = "select d.deptid,d.deptname,d.deptlevel,d.depttype,d.businessarea,d.parentid from ge_department d where d.parentid = ( select t.parentid from ge_department t where t.deptid = ? ) and d.deptid not like '%9999'";
		List list =  super.findBySql(sql, deptId);
		if(list == null || list.size() == 0) return null;
		GeDepartment geDepartment = null;
		List<GeDepartment> geDepartments = new ArrayList<GeDepartment>();
		for (int i = 0; i < list.size(); i++) {
			Object[] strs = (Object[])list.get(i);
			geDepartment = new GeDepartment();
			geDepartment.setDeptid((String)strs[0]);
			geDepartment.setDeptname((String)strs[1]);
			geDepartment.setDeptlevel((String)strs[2]);
			geDepartment.setDepttype((String)strs[3]);
			geDepartment.setBusinessarea((String)strs[4]);
			geDepartment.setParentid((String)strs[5]);
			geDepartments.add(geDepartment);
		}
		return geDepartments;
	}
	
	@Override
	public GeDepartment findGeDepartmentByChildId(String deptId){
		String sql = "select d.deptid,d.deptname,d.deptlevel,d.depttype,d.businessarea,d.parentid from ge_department d where d.deptid = ( select t.parentid from ge_department t where t.deptid = ? )";
		List list = super.findBySql(sql, deptId);
		if (list == null || list.size() == 0) {
			return null;
		}
		Object[] strs =  (Object[])list.get(0);
		GeDepartment geDepartment = new GeDepartment();
		geDepartment.setDeptid((String)strs[0]);
		geDepartment.setDeptname((String)strs[1]);
		geDepartment.setDeptlevel((String)strs[2]);
		geDepartment.setDepttype((String)strs[3]);
		geDepartment.setBusinessarea((String)strs[4]);
		geDepartment.setParentid((String)strs[5]);
		return geDepartment;
	}
	
	@Override
	@LocusTrace(setDesc="查询所有机构")
	public Map<String, String> findAllDepartMent(){
		String sql = "select d.deptid,d.deptname from ge_department d ";
		List list = super.findBySql(sql);
		if (list == null || list.size() == 0) {
			return new HashMap<String, String>();
		}else{
			Map map = new HashMap();
			for(int i = 0; i < list.size(); i++){
				Object[] strs =  (Object[])list.get(i);
				map.put(strs[0], strs[1]);
			}
			return map;
		}
	}
	
	@LocusTrace(setDesc="更新机构")
	public void updateDeptName(){
		try {
			QueryRule queryRule = QueryRule.getInstance();
			List<GeDepartment> list = super.find(queryRule);
			for(int i = 0; i < list.size(); i++){
				GeDepartment geDepartment = list.get(i);
				String geDeptId = geDepartment.getDeptid();
				geDepartment.setGid(geDeptId.substring(1));
			}
		} catch (Exception e) {
		}
	}

	@Override
	public List<String[]> getAllChildAuthDept(String parentId, String auth,boolean isReturnAll) {
		List<String[]> list = new ArrayList<String[]>();

		/** 获取所有的父节点 */
		if (!StringUtils.isBlank(findParentIds(auth)))
			auth += "," + findParentIds(auth);

		/** 查看是否存在全部选项 */
		if(isReturnAll){
			List<String> paraList = new ArrayList<String>();
			paraList.add(parentId);
			if (isExistAllAuth(paraList, auth, true))list.add(new String[] { parentId, "全部" });
		}

		/** 查询符合条件的部门列表 */
		if (!StringUtils.isBlank(auth)) {
			StringBuffer sql = new StringBuffer();
			sql.append("select j.deptid,j.deptname from ge_department j where j.parentid=? and (j.deptid in ");
			String[] allAuthDeptId = auth.split(",");
			if (allAuthDeptId != null && allAuthDeptId.length > 0) {
				int allAuthDeptIdSize = allAuthDeptId.length;
				int allAuthDeptIdSizeNumber = (allAuthDeptIdSize - 1) / 1000 + 1;
				String nextAuthDeptIdString = "";
				for (int j = 0; j < allAuthDeptIdSizeNumber; j++) {
					if (nextAuthDeptIdString.length() > 0) {
						nextAuthDeptIdString = "";
						sql.append("or j.deptid in");
					}
					int loopNum = 1000;
					if (j == allAuthDeptIdSizeNumber - 1) {
						loopNum = allAuthDeptIdSize - (allAuthDeptIdSizeNumber - 1)
								* 1000;
					}
					for (int i = 0; i < loopNum; i++) {
						String childDeptId = (String) allAuthDeptId[1000 * j + i];
						if (i == 0) {
							nextAuthDeptIdString += "'" + childDeptId + "'";
						} else {
							nextAuthDeptIdString += ",'" + childDeptId + "'";
						}
					}
					sql.append("(" + nextAuthDeptIdString + ")");
				}
				sql.append(")");
			}
			List suitList = super.findBySql(sql.toString(),new Object[] { parentId });
			if (suitList != null && suitList.size() > 0) {
				for (int i = 0; i < suitList.size(); i++) {
					Object[] o = (Object[]) suitList.get(i);
					String[] department = new String[] { o[0].toString(),o[1].toString() };
					list.add(department);
				}
			}
		}
		return list;
	}
	
	/**         
	 * 判断是否存在全部子节点
	 * @param parentIdList
	 * @param authId
	 * @return
	 */
	public boolean isExistAllAuth(List<String> parentIdList, String authId,
			boolean isInit) {
		boolean bl = true;

		/** 查询子节点 */
		String sql = "select j.deptid from ge_department j where j.parentid in ( ? )";
		StringBuffer parentIdsStr = new StringBuffer();
		if (parentIdList != null && parentIdList.size() > 0) {
			for (int i = 0; i < parentIdList.size(); i++) {
				if (i == 0) {
					parentIdsStr.append(parentIdList.get(i).toString());
				} else {
					parentIdsStr.append(",");
					parentIdsStr.append(parentIdList.get(i).toString());
				}
			}
		}
		/**取得的为object类型*/
		List childList= super.findBySql(sql.toString(),new Object[] { parentIdsStr.toString() });
		
		if (childList != null && childList.size() > 0) {
			for (int i = 0; i < childList.size(); i++) {
				if (authId.indexOf(childList.get(i).toString()) == -1) {
					bl = false;
					break;
				}
			}

			/** 是否接着回调判断 */
			if (bl)isExistAllAuth(childList, authId, false);
		}else{
			if (isInit)bl = false;
		}
		return bl;
	}
	/**
	 * 根据权限子节点，查询符合条件的父节点
	 * @param auth
	 * @return
	 */
	public String findParentIds(String auth){
		List list = new ArrayList();
		StringBuffer parentIds=new StringBuffer();
		if (!StringUtils.isBlank(auth)) {
			StringBuffer sql = new StringBuffer();
			sql.append("select distinct j.parentid from ge_department j where (j.deptid in ");
			String[] allAuthChildDeptId = auth.split(",");
			if (allAuthChildDeptId != null && allAuthChildDeptId.length > 0) {
				int allAuthChildDeptIdSize = allAuthChildDeptId.length;
				int allAuthChildDeptIdNumber = (allAuthChildDeptIdSize - 1) / 1000 + 1;
				String nextChildAuthDeptIdString = "";
				for (int j = 0; j < allAuthChildDeptIdNumber; j++) {
					if (nextChildAuthDeptIdString.length() > 0) {
						nextChildAuthDeptIdString = "";
						sql.append("or j.deptid in");
					}
					int loopNum = 1000;
					if (j == allAuthChildDeptIdNumber - 1) {
						loopNum = allAuthChildDeptIdSize - (allAuthChildDeptIdNumber - 1)
								* 1000;
					}
					for (int i = 0; i < loopNum; i++) {
						String childDeptId = (String) allAuthChildDeptId[1000 * j + i];
						if (i == 0) {
							nextChildAuthDeptIdString += "'" + childDeptId + "'";
						} else {
							nextChildAuthDeptIdString += ",'" + childDeptId + "'";
						}
					}
					sql.append("(" + nextChildAuthDeptIdString + ")");
				}
				sql.append(")");
			}
			list = super.findBySql(sql.toString());
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					if(i==0){
						parentIds.append(list.get(i).toString());
					}else{
						parentIds.append(",");
						parentIds.append(list.get(i).toString());
					}
				}
			}
		}
		return parentIds.toString();
	}

	@Override
	public String getSelfName(GeDepartment geDepartment) {
		if(geDepartment == null)return "";
		String level = geDepartment.getDeptlevel();
		String suffix = "";

		if("1".equals(level)){
			suffix = "总公司";
		}else{
			suffix = "公司";
		}
		return geDepartment.getDeptname();
	}
}
