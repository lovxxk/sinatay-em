package cn.com.sinosoft.ebusiness.service.user.common.service.facade;

import ins.framework.common.Page;

import java.util.List;

import cn.com.sinosoft.ebusiness.service.user.common.domain.GeCustomerFavorite;
import cn.com.sinosoft.ebusiness.service.user.common.domain.GeCustomerFavoriteId;

/**
 * 个人客户收藏夹
 * @version 1.0
 *
 */

public interface FavoriteService {

	/**
	 * 添加信息到客户收藏夹
	 * 
	 * @param geChannel
	 */
	public void saveFavorite(GeCustomerFavorite geCustomerFavorite);
	
	/**
	 * 查询客户收藏夹信息
	 * 
	 * @param geChannel
	 */
	public Page findFavorite(String userID,int pageNo,int pageSize);
	/**
	 * 删除客户收藏夹信息
	 * 
	 * @param geChannel
	 */
	public void deleteFavorite(GeCustomerFavoriteId id);
	
	public void insertFavorite(String userID,String eid);
	
	public int findFavorite(String userID);
	
}
