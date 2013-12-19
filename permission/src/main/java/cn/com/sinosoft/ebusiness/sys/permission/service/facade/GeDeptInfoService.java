package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptInfo;

/**
 * ����������Ϣ
 */
public interface GeDeptInfoService {
	/**
	 * �������������Ϣ
	 * @param geDeptInfo
	 * @return
	 */
	public boolean save(GeDeptInfo geDeptInfo);
	
	/**
	 * ����������ѯ����������Ϣ
	 * @param pk
	 * @return
	 */
	public GeDeptInfo findGeDeptInfoByPk(String pk);
	
	/**
	 * ��ѯ���еĻ���������Ϣ
	 * @param queryRule
	 * @return
	 */
	public List<GeDeptInfo> findAllGeDeptInfo(QueryRule queryRule);
	
	/**
	 * ��ҳ��ѯ����������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllGeDeptInfoByDisPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * ������ɾ������������Ϣ
	 * @param pk
	 * @return
	 */
	public boolean delete(String pk);
	
	/**
	 * �޸Ļ���������Ϣ
	 * @param geDeptInfo
	 * @return
	 */
	public boolean updateGeDeptInfo(GeDeptInfo geDeptInfo);
	
	/**
	 * �ж������Ƿ����
	 */
	public boolean isExitId(String id);
}
