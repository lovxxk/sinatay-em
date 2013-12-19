package cn.com.sinosoft.ebusiness.service.user.common.service.spring;



import java.util.Calendar;
import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.service.user.common.domain.GeCustomerFavorite;
import cn.com.sinosoft.ebusiness.service.user.common.domain.GeCustomerFavoriteId;
import cn.com.sinosoft.ebusiness.service.user.common.service.facade.FavoriteService;

public class FavoriteServiceSpringImpl extends
GenericDaoHibernate<GeCustomerFavorite,GeCustomerFavoriteId> implements FavoriteService {
	
	//private GeCustomerFavorite geCustomerFavorite ;
	/**
	 * 添加信息到客户收藏夹
	 * 
	 * @param favorite
	 */
	@Override
	public void saveFavorite(GeCustomerFavorite geCustomerFavorite) {
		// TODO Auto-generated method stub
			super.save(geCustomerFavorite);
	}

	public void insertFavorite(String userID,String eid){
		GeCustomerFavoriteId geCustomerFavoriteId = new GeCustomerFavoriteId();
		geCustomerFavoriteId.setEid(eid);
		geCustomerFavoriteId.setUserID(userID);
		GeCustomerFavorite geCustomerFavorite = new GeCustomerFavorite();
		geCustomerFavorite.setId(geCustomerFavoriteId);
		Calendar c = Calendar.getInstance(); 
		
		geCustomerFavorite.setSubmitDate(c.getTime());
		geCustomerFavorite.setUserType("01");
		super.save(geCustomerFavorite);
	}
	/**
	 * 查询客户收藏夹信息
	 * 
	 * @param favorite
	 */
	@Override
	public Page findFavorite(String userID,int pageNo,int pageSize){  
			QueryRule rule = QueryRule.getInstance();
			rule.addEqual("id.userID", userID);
			rule.addSql(" exists (select 1 from ge_directory d where d.eid = this_.eid and d.isproductshelf ='01') ");
			return super.find(rule, pageNo, pageSize);
	}

	
	/**
	 * 删除客户收藏夹信息
	 * 
	 * @param favorite
	 */
	@Override
	public void deleteFavorite(GeCustomerFavoriteId id){
		// TODO Auto-generated method stub
		super.deleteByPK(GeCustomerFavorite.class,id);
	}

	@Override
	public int findFavorite(String userID) {
		int m= 0 ;
		QueryRule rule = QueryRule.getInstance();

		rule.addEqual("id.userID", userID);
		rule.addSql(" exists (select 1 from ge_directory d where d.eid = this_.eid and d.isproductshelf ='01') ");
		List<GeCustomerFavorite> favorites = super.find(rule);
		m = favorites.size();
		return m;
	}
}
