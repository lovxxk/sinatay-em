/**  
 * @Title: GeBlackListService.java
 * @Package cn.com.sinosoft.ebusiness.service.user.personal.service.facade
 * @Description: TODO	黑名单管理接口类
 *    
 * @date 2011-8-29 下午05:18:39
 * @version V1.0  
 */
package cn.com.sinosoft.ebusiness.service.user.personal.service.facade;

import java.util.List;

import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeBlackList;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;

public interface GeBlackListService {

	/**
	 * 
	 * @Title: findList
	 * @Description: TODO 按规则查询黑名单信息
	 * @param queryRule	查询规则
	 * @param pageNo	第几页
	 * @param pageSize	一页行数
	 * @return Page
	 */
	public Page findList(QueryRule queryRule, int pageNo, int pageSize);
	

	/**
	 * 
	 * @Title: findByRule
	 * @Description: TODO 按规则查询单个黑名单信息
	 * @param queryRule	查询规则
	 * @return GeBlackList
	 */
	public GeBlackList findByRule(QueryRule queryRule);

	/**
	 * 
	 * @Title: findById
	 * @Description: TODO 根据主键ID查询黑名单信息
	 * @param id	主键id
	 * @return GeBlackList
	 */
	public GeBlackList findById(String id);

	/**
	 * 
	 * @Title: modify
	 * @Description: TODO 根据对象修改黑名单
	 * @param geBlackList	 黑名单信息
	 * @return void
	 */
	public void modify(GeBlackList geBlackList);

	/**
	 * 
	 * @Title: deleteById
	 * @Description: TODO 根据主键ID删除黑名单
	 * @param id	主键ID
	 * @return void
	 */
	public void deleteById(String id);

	/**
	 * 
	 * @Title: deleteByList
	 * @Description: TODO 根据实体类组成的集合删除相关黑名单记录
	 * @param list	实体类组成的集合
	 * @return void
	 */
	public void deleteByList(List<GeBlackList> list);
	
	/**
	 * 
	 * @Title: insert
	 * @Description: TODO	保存黑名单信息
	 * @param geBlackList	黑名单信息
	 * @return void
	 */
	public void insert(GeBlackList geBlackList);

}
