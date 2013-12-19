package cn.com.sinosoft.ebusiness.service.user.personal.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPolicy;

public interface GeUserPolicyService {

	/**
	 * 保存给定GeUserPolicy对象到本地库的用户保单绑定表中
	 * @param geUserPolicy
	 */
	public void insert(GeUserPolicy geUserPolicy);
	
	/**
	 * 根据给定GeUserPolicy对象修改本地库的中的相关记录,根据主键查找不到相关记录则不做任何修改
	 * @param geUserPolicy
	 */
	public void modify(GeUserPolicy geUserPolicy);
	
	/**
	 * 根据规则在本地库的用户保单绑定表中查询唯一记录，没符合条件的记录返回null
	 * @param queryRule 查询规则
	 * @return 查询到的GeUserPolicy对象
	 */
	public GeUserPolicy search(QueryRule queryRule);
	/**
	 * 根据条件查询全部数据
	 * @param queryRule
	 * @return
	 */
	public List<GeUserPolicy> searchListAll(QueryRule queryRule);
	/**
	 * 根据规则进行分页查询
	 * @param queryRule 查询规则
	 * @param pageNo 第几页
	 * @param pageSize 每页记录数
	 * @return
	 */
	public Page searchList(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * 根据主键在本地库中删除相关记录，没有记录也不报异常
	 * @param id 主键
	 */
	public void deleteById(String  serialNo);
	
	/**
	 * 根据给定对象组成的集合在本地库中删除所有相关记录
	 * @param list 待删除对象组成的集合
	 */
	public void deleteByList(List<GeUserPolicy> list);

	public int deleteByFK(String userID);
	
}
