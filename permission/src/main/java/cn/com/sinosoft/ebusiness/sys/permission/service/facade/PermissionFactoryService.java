package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;

public interface PermissionFactoryService {
	/**
	 * ���ݹ���Ա�ı����ѯ��ӵ�е�Ȩ�޼�Ȩ�޶�Ӧ�Ļ���Ȩ��(����ӿڷ���)
	 * @param operatorId	����Ա���
	 * @return
	 */
	public  Map<String,String> deptAuthIdString(String operatorId);
	
	/**
	 * ��ȡ��������ԱȨ��
	 * @param operatorId
	 * @return
	 */
	public Map<String, String> alldeptAuthIdString();
	
	/**
	 * ͨ����ѯsession���Ȩ��authorityid��Ӧ�Ļ���Ȩ��
	 * @param authorityid
	 * @param session
	 * @return
	 */
	public  List<String> findDeptAuthId(String authorityid, HttpSession session);
	
	/**
	 * ͨ����ѯsession���Ȩ��authorityid��Ӧ�Ļ���Ȩ�ޣ����˴���DeptAuthId��
	 * @param authorityid
	 * @param session
	 * @param DeptAuthId
	 * @return
	 */
	public  List<String> findDeptAuthId(String authorityid, HttpSession session, List<String> DeptAuthId);
	
	/**
	 * ͨ����ѯsession���Ȩ��authorityid��Ӧ�Ļ���Ȩ�ޣ����˴���DeptAuthId��
	 * @param authorityid
	 * @param session
	 * @param DeptAuthId
	 * @return
	 */
	public  List<String> findDeptAuthId(String authorityid, HttpSession session, String DeptAuthId);

	/**
	 * SetתList
	 * @param set	
	 * @return
	 */
	public  List<String> transformSetToList(Set<String> set);
	
	/**
	 * SetתString
	 * @param set	
	 * @return
	 */
	public  String transformSetToString(Set<String> set);
	
	/**
	 * StringתList
	 * @param set	
	 * @return
	 */
	public  List<String> transformStringToList(String str);
}
