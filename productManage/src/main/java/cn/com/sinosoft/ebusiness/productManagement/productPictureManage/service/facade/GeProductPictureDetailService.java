/**  
 * @Title: GeProductPictureDetailService.java
 * @Package cn.com.sinosoft.ebusiness.productManagement.productPictureManage.service.facade
 * @Description: TODO	产品图片管理接口类
 *    
 * @date 2012-7-20 下午05:55:49
 * @version V1.0  
 */
package cn.com.sinosoft.ebusiness.productManagement.productPictureManage.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.productManagement.productPictureManage.domain.GeProductPicture;
import cn.com.sinosoft.ebusiness.productManagement.productPictureManage.domain.GeProductPictureDetail;

public interface GeProductPictureDetailService {

	/**
	 * 
	 * @Title: findList
	 * @Description: TODO 按规则查询产品图片信息
	 * @param queryRule	查询规则
	 * @param pageNo	第几页
	 * @param pageSize	一页行数
	 * @return Page
	 */
	public Page findList(QueryRule queryRule, int pageNo, int pageSize);
	

	/**
	 * 
	 * @Title: findByRule
	 * @Description: TODO 按规则查询单个产品图片信息
	 * @param queryRule	查询规则
	 * @return GeBlackList
	 */
	public GeProductPictureDetail findByRule(QueryRule queryRule);

	/**
	 * 
	 * @Title: findById
	 * @Description: TODO 根据主键ID查询产品图片信息
	 * @param id	主键id
	 * @return GeProductPictureDetail
	 */
	public GeProductPictureDetail findById(String id);

	/**
	 * 
	 * @Title: modify
	 * @Description: TODO 根据对象和图片序列号修改产品图片信息
	 * @param geProductPictureDetail  产品图片信息
	 * @param uploadPictureSerialNos  产品图片序列号
	 * @return void
	 */
	public void modify(GeProductPictureDetail geProductPictureDetail,String uploadPictureSerialNos);

	/**
	 * 
	 * @Title: deleteById
	 * @Description: TODO 根据主键ID删除产品图片
	 * @param id	主键ID
	 * @return void
	 */
	public void deleteById(String id);

	/**
	 * 
	 * @Title: deleteByList
	 * @Description: TODO 根据实体类组成的集合删除相关产品图片记录
	 * @param list	实体类组成的集合
	 * @return void
	 */
	public void deleteByList(List<GeProductPictureDetail> list);
	
	/**
	 * 
	 * @Title: insert
	 * @Description: TODO	保存产品图片信息
	 * @param geProductPictureDetail	产品图片信息
	 * @return void
	 */
	public void insert(GeProductPictureDetail geProductPictureDetail);
	
	/**
	 * 
	 * @Title: getProductPictures
	 * @Description: TODO	获取一条最新并且启用产品图片信息
	 * @param 
	 * @return Map<String,GeProductPicture>
	 */
	public Map<Integer,GeProductPicture> getProductPictures();

}
