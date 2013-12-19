package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;

public interface PermissionFactoryService {
	/**
	 * 根据管理员的编码查询出拥有的权限及权限对应的机构权限(对外接口方法)
	 * @param operatorId	操作员编号
	 * @return
	 */
	public  Map<String,String> deptAuthIdString(String operatorId);
	
	/**
	 * 获取超级管理员权限
	 * @param operatorId
	 * @return
	 */
	public Map<String, String> alldeptAuthIdString();
	
	/**
	 * 通过查询session获得权限authorityid对应的机构权限
	 * @param authorityid
	 * @param session
	 * @return
	 */
	public  List<String> findDeptAuthId(String authorityid, HttpSession session);
	
	/**
	 * 通过查询session获得权限authorityid对应的机构权限（过滤传入DeptAuthId）
	 * @param authorityid
	 * @param session
	 * @param DeptAuthId
	 * @return
	 */
	public  List<String> findDeptAuthId(String authorityid, HttpSession session, List<String> DeptAuthId);
	
	/**
	 * 通过查询session获得权限authorityid对应的机构权限（过滤传入DeptAuthId）
	 * @param authorityid
	 * @param session
	 * @param DeptAuthId
	 * @return
	 */
	public  List<String> findDeptAuthId(String authorityid, HttpSession session, String DeptAuthId);

	/**
	 * Set转List
	 * @param set	
	 * @return
	 */
	public  List<String> transformSetToList(Set<String> set);
	
	/**
	 * Set转String
	 * @param set	
	 * @return
	 */
	public  String transformSetToString(Set<String> set);
	
	/**
	 * String转List
	 * @param set	
	 * @return
	 */
	public  List<String> transformStringToList(String str);
}
