package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeAreaAuthority;


public interface GeAreaService {
	
	/**
	 * 获取二级菜单
	 */
	public List<GeAreaService> getGeAeraByGlevel();
	
	/**
	 * 查询所需区域信息
	 * @param pgid
	 * @param glevel
	 * @return
	 */
	public Object[] findGeAreas(String pgid,String glevel);
	
	/**
	 * 根据条件查询所需区域信息
	 */
	/**
	 * 查询所有机构
	 * @param queryRule
	 * @return
	 */
	public List findGeAreas(QueryRule queryRule);
	
	/**
	 * 查询子区域
	 */
	public List getChildList(String pgid);
	/**
	 * 查询相关领域下的区域
	 */
	public List getBussAreas(String buss);
	/**
	 * 保存区域信息
	 * @param geAuthority
	 * @return
	 */
	public boolean save(GeAreaAuthority geAreaAuthority);
	
	/**
	 * 修改区域信息
	 * @param geAuthority
	 * @return
	 */
	public boolean updates(GeAreaAuthority geAreaAuthority);
	
	/**
	 * 按主键删除区域信息
	 * @param geAuthority
	 * @return
	 */
	public boolean delete(GeAreaAuthority geAreaAuthority);
	
	/**
	 * 按主键查询区域信息
	 * @param pk
	 * @return
	 */
	public GeAreaAuthority findGeAuthorityByPK(String pk);
	/**
	 * 根据主键判断对象是否存在
	 * 
	 * @param pk
	 * @return
	 */
	public boolean exists(String pk);
	/**
	 * 查看该区域中是否被机构所属
	 */
	public boolean isUsedDep(String gid);
	
	/**
	 * 根据下级地区代码查询上级地区
	 * @param childId
	 * @return
	 */
	public GeAreaAuthority findGeAuthorityByChildId(String childId);
}
