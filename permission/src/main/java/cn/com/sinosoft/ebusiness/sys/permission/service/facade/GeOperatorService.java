package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGroup;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeOperator;

/**
 * ��̨����Ա����ӿ�
 *  
 *
 */
public interface GeOperatorService {
	
	/***
	 * ���������жϺ�̨����Ա�Ƿ����
	 * @param pk
	 * @return
	 */
	public boolean exists(String pk);
	
	/**
	 * ����������ѯ��̨����Ա
	 * @param pk
	 * @return
	 */
	public GeOperator findOperatorByPK(String pk);
	
	/**
	 * �����̨����Ա
	 * @param geOperator
	 * @return
	 */
	public boolean save(GeOperator geOperator);
	
	/**
	 * ����������ѯ����Ա
	 * @param queryRule
	 * @return
	 */
	public GeOperator findGeOperator(QueryRule queryRule);
	
	/**
	 * �޸Ĺ���Ա�����µ�¼�û��ĸ�����Ϣ���ݣ�
	 * @param geOperator
	 * @return
	 */
	public boolean updates(GeOperator geOperator);
	
	/**
	 * ����������ѯ����Ա
	 * @param pk
	 * @return 1-ɾ���ɹ�   2-ɾ��ʧ�� 
	 */
	public boolean delete(String pk);
	
	/**
	 * ����������ҳ��ѯ����Ա
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page find(QueryRule queryRule, int pageNo, int pageSize);
	
	/**
	 * �жϸ��û�ʱ����ʹ����
	 */
	public boolean isUserUsed(String id);
	/**
	 * �����û�ID��ѯ�û���
	 * @param operatorid
	 * @return
	 */
	public List findGroupId(String operatorid,String groupUserAuthDeptId);

	public Page findGroupId(String operatorid,String authorityChoose,int pageNo,int pageSize);
	
	/**
	 * �޸Ĳ�ѯ�б����û�����
	 * @param operatorId
	 * @param newPassowrd
	 * @return
	 */
	public boolean updatePass(String operatorId,String newPassowrd);
	
}
