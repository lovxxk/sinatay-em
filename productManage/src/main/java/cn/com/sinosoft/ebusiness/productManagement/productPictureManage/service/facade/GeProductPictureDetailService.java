/**  
 * @Title: GeProductPictureDetailService.java
 * @Package cn.com.sinosoft.ebusiness.productManagement.productPictureManage.service.facade
 * @Description: TODO	��ƷͼƬ����ӿ���
 *    
 * @date 2012-7-20 ����05:55:49
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
	 * @Description: TODO �������ѯ��ƷͼƬ��Ϣ
	 * @param queryRule	��ѯ����
	 * @param pageNo	�ڼ�ҳ
	 * @param pageSize	һҳ����
	 * @return Page
	 */
	public Page findList(QueryRule queryRule, int pageNo, int pageSize);
	

	/**
	 * 
	 * @Title: findByRule
	 * @Description: TODO �������ѯ������ƷͼƬ��Ϣ
	 * @param queryRule	��ѯ����
	 * @return GeBlackList
	 */
	public GeProductPictureDetail findByRule(QueryRule queryRule);

	/**
	 * 
	 * @Title: findById
	 * @Description: TODO ��������ID��ѯ��ƷͼƬ��Ϣ
	 * @param id	����id
	 * @return GeProductPictureDetail
	 */
	public GeProductPictureDetail findById(String id);

	/**
	 * 
	 * @Title: modify
	 * @Description: TODO ���ݶ����ͼƬ���к��޸Ĳ�ƷͼƬ��Ϣ
	 * @param geProductPictureDetail  ��ƷͼƬ��Ϣ
	 * @param uploadPictureSerialNos  ��ƷͼƬ���к�
	 * @return void
	 */
	public void modify(GeProductPictureDetail geProductPictureDetail,String uploadPictureSerialNos);

	/**
	 * 
	 * @Title: deleteById
	 * @Description: TODO ��������IDɾ����ƷͼƬ
	 * @param id	����ID
	 * @return void
	 */
	public void deleteById(String id);

	/**
	 * 
	 * @Title: deleteByList
	 * @Description: TODO ����ʵ������ɵļ���ɾ����ز�ƷͼƬ��¼
	 * @param list	ʵ������ɵļ���
	 * @return void
	 */
	public void deleteByList(List<GeProductPictureDetail> list);
	
	/**
	 * 
	 * @Title: insert
	 * @Description: TODO	�����ƷͼƬ��Ϣ
	 * @param geProductPictureDetail	��ƷͼƬ��Ϣ
	 * @return void
	 */
	public void insert(GeProductPictureDetail geProductPictureDetail);
	
	/**
	 * 
	 * @Title: getProductPictures
	 * @Description: TODO	��ȡһ�����²������ò�ƷͼƬ��Ϣ
	 * @param 
	 * @return Map<String,GeProductPicture>
	 */
	public Map<Integer,GeProductPicture> getProductPictures();

}
