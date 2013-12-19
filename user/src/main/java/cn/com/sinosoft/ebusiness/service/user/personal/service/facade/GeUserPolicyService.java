package cn.com.sinosoft.ebusiness.service.user.personal.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPolicy;

public interface GeUserPolicyService {

	/**
	 * �������GeUserPolicy���󵽱��ؿ���û������󶨱���
	 * @param geUserPolicy
	 */
	public void insert(GeUserPolicy geUserPolicy);
	
	/**
	 * ���ݸ���GeUserPolicy�����޸ı��ؿ���е���ؼ�¼,�����������Ҳ�����ؼ�¼�����κ��޸�
	 * @param geUserPolicy
	 */
	public void modify(GeUserPolicy geUserPolicy);
	
	/**
	 * ���ݹ����ڱ��ؿ���û������󶨱��в�ѯΨһ��¼��û���������ļ�¼����null
	 * @param queryRule ��ѯ����
	 * @return ��ѯ����GeUserPolicy����
	 */
	public GeUserPolicy search(QueryRule queryRule);
	/**
	 * ����������ѯȫ������
	 * @param queryRule
	 * @return
	 */
	public List<GeUserPolicy> searchListAll(QueryRule queryRule);
	/**
	 * ���ݹ�����з�ҳ��ѯ
	 * @param queryRule ��ѯ����
	 * @param pageNo �ڼ�ҳ
	 * @param pageSize ÿҳ��¼��
	 * @return
	 */
	public Page searchList(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * ���������ڱ��ؿ���ɾ����ؼ�¼��û�м�¼Ҳ�����쳣
	 * @param id ����
	 */
	public void deleteById(String  serialNo);
	
	/**
	 * ���ݸ���������ɵļ����ڱ��ؿ���ɾ��������ؼ�¼
	 * @param list ��ɾ��������ɵļ���
	 */
	public void deleteByList(List<GeUserPolicy> list);

	public int deleteByFK(String userID);
	
}
