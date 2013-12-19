package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import java.util.List;
import java.util.Map;

public interface GeProductProDeptService {
	/**
	 * ���ݲ��պ��Ļ��������ѯ���Ż�������
	 * @param serviceId
	 * @return
	 */
	public String findDeptIdByServiceId(String serviceId);
	/**
	 * ����Ʒ��Ų�ѯ�����۵���
	 * @param coreProductCode
	 * @return �����۵����ַ���
	 */
	/**
	 * ��ȡ���⸸�ڵ�
	 * @param operatorDeptAuths
	 * @return
	 */
	public String getAllVirtualParent(String operatorDeptAuths);

	public abstract List findProductSaleDept(String coreProductCode);
	
	/**
	 * ��ѯ��Ʒ���õĻ���
	 * @param productCode
	 * @param deptlevel
	 * @param parentid
	 * @return
	 */
	public List<String[]> findProductSaleDepartmet(String productCode,String deptlevel,String parentid);
	
	/**
	 * ���ݺ��Ĳ�Ʒ�����ѯ���Ż�������
	 * @param orgDeptId
	 * @return
	 */
	public String findDeptIdByOrgId(String orgDeptId);
	/**
	 * ���ݺ��Ĳ�Ʒ�����ѯ���ŵĻ�������
	 */
	public String findDeptNameByOrgId(String orgDeptId);
	
	/**
	 * ���ݼ��Ż��������ѯ���Ļ�������
	 * @param orgId
	 * @return
	 */
	public String findOrgIdByDeptId(String orgId);
}
