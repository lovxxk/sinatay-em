package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGroup;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;

/**
 * 后台管理员服务接口
 *  
 *
 */
public interface GeOperatorService {
	
	/***
	 * 根据主键判断后台管理员是否存在
	 * @param pk
	 * @return
	 */
	public boolean exists(String pk);
	
	/**
	 * 根据主键查询后台管理员
	 * @param pk
	 * @return
	 */
	public GeOperator findOperatorByPK(String pk);
	
	/**
	 * 保存后台管理员
	 * @param geOperator
	 * @return
	 */
	public boolean save(GeOperator geOperator);
	
	/**
	 * 根据条件查询管理员
	 * @param queryRule
	 * @return
	 */
	public GeOperator findGeOperator(QueryRule queryRule);
	
	/**
	 * 修改管理员（更新登录用户的个人信息内容）
	 * @param geOperator
	 * @return
	 */
	public boolean updates(GeOperator geOperator);
	
	/**
	 * 根据主键查询管理员
	 * @param pk
	 * @return 1-删除成功   2-删除失败 
	 */
	public boolean delete(String pk);
	
	/**
	 * 根据条件分页查询管理员
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page find(QueryRule queryRule, int pageNo, int pageSize);
	
	/**
	 * 判断该用户时候在使用中
	 */
	public boolean isUserUsed(String id);
	/**
	 * 根据用户ID查询用户组
	 * @param operatorid
	 * @return
	 */
	public List findGroupId(String operatorid,String groupUserAuthDeptId);

	public Page findGroupId(String operatorid,String authorityChoose,int pageNo,int pageSize);
	
	/**
	 * 修改查询列表中用户密码
	 * @param operatorId
	 * @param newPassowrd
	 * @return
	 */
	public boolean updatePass(String operatorId,String newPassowrd);
	
}
