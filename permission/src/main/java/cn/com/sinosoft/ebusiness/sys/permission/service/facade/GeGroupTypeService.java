/**
 * 
 */
package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGroup;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGrouptype;

/**
 * 用户组类型服务接口
 *  
 *
 */
public interface GeGroupTypeService {

	/**
	 * 根据主键判断对象是否存在
	 * @param pk
	 * @return
	 */
	public boolean existsType(String pk);

	/**
	 * 保存用户组类型
	 * @param geGroup
	 * @return
	 */
	public boolean saveType(GeGrouptype geGrouptype);
	
	/**
	 * 分页查询用户组类型
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeGroupType(QueryRule queryRule, int pageNo, int pageSize);
	
	/**
	 * 根据条件查询单个用户组类型
	 * @param queryRule
	 * @return
	 */
	public GeGrouptype findGeGroupType(QueryRule queryRule);
	
	/**
	 * 修改组类型
	 */
	public boolean updateType(GeGrouptype obj);
	/**
	 * 根据机构编号查询用户组类型
	 * @param deptid
	 * @return
	 */
	public List<GeGrouptype> findGeGroupTypes(QueryRule queryRule);
	
	/**
	 * 刪除String对象中key所对应的对象
	 * @param keys
	 * @return
	 */
	public int delete(String keys);
}
