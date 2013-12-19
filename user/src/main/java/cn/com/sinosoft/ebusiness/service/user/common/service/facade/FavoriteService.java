package cn.com.sinosoft.ebusiness.service.user.common.service.facade;

import ins.framework.common.Page;

import java.util.List;

import cn.com.sinosoft.ebusiness.service.user.common.domain.GeCustomerFavorite;
import cn.com.sinosoft.ebusiness.service.user.common.domain.GeCustomerFavoriteId;

/**
 * ���˿ͻ��ղؼ�
 * @version 1.0
 *
 */

public interface FavoriteService {

	/**
	 * �����Ϣ���ͻ��ղؼ�
	 * 
	 * @param geChannel
	 */
	public void saveFavorite(GeCustomerFavorite geCustomerFavorite);
	
	/**
	 * ��ѯ�ͻ��ղؼ���Ϣ
	 * 
	 * @param geChannel
	 */
	public Page findFavorite(String userID,int pageNo,int pageSize);
	/**
	 * ɾ���ͻ��ղؼ���Ϣ
	 * 
	 * @param geChannel
	 */
	public void deleteFavorite(GeCustomerFavoriteId id);
	
	public void insertFavorite(String userID,String eid);
	
	public int findFavorite(String userID);
	
}
