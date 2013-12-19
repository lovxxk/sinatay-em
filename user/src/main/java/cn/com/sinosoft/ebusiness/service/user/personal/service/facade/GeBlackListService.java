/**  
 * @Title: GeBlackListService.java
 * @Package cn.com.sinosoft.ebusiness.service.user.personal.service.facade
 * @Description: TODO	����������ӿ���
 *    
 * @date 2011-8-29 ����05:18:39
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
	 * @Description: TODO �������ѯ��������Ϣ
	 * @param queryRule	��ѯ����
	 * @param pageNo	�ڼ�ҳ
	 * @param pageSize	һҳ����
	 * @return Page
	 */
	public Page findList(QueryRule queryRule, int pageNo, int pageSize);
	

	/**
	 * 
	 * @Title: findByRule
	 * @Description: TODO �������ѯ������������Ϣ
	 * @param queryRule	��ѯ����
	 * @return GeBlackList
	 */
	public GeBlackList findByRule(QueryRule queryRule);

	/**
	 * 
	 * @Title: findById
	 * @Description: TODO ��������ID��ѯ��������Ϣ
	 * @param id	����id
	 * @return GeBlackList
	 */
	public GeBlackList findById(String id);

	/**
	 * 
	 * @Title: modify
	 * @Description: TODO ���ݶ����޸ĺ�����
	 * @param geBlackList	 ��������Ϣ
	 * @return void
	 */
	public void modify(GeBlackList geBlackList);

	/**
	 * 
	 * @Title: deleteById
	 * @Description: TODO ��������IDɾ��������
	 * @param id	����ID
	 * @return void
	 */
	public void deleteById(String id);

	/**
	 * 
	 * @Title: deleteByList
	 * @Description: TODO ����ʵ������ɵļ���ɾ����غ�������¼
	 * @param list	ʵ������ɵļ���
	 * @return void
	 */
	public void deleteByList(List<GeBlackList> list);
	
	/**
	 * 
	 * @Title: insert
	 * @Description: TODO	�����������Ϣ
	 * @param geBlackList	��������Ϣ
	 * @return void
	 */
	public void insert(GeBlackList geBlackList);

}
