/**
 * 
 */
package cn.com.sinosoft.ebusiness.common.bizConfig.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCodeType;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BizConfigCommonException;

/**
 *
 */
public interface GeCodeTypeService {
	/**
	 * ��ѯĳ�������ֵ�
	 * @return
	 * @throws Exception
	 */
	public List<GeCodeType> findAll()throws BizConfigCommonException;
	
	/**
	 * ��ѯĳ�������ֵ�
	 * @param geCodeType
	 * @return <�����ֵ�ID,�����ֵ�����>
	 * @throws BizConfigCommonException
	 */
	public Map<String, String> findAllCodeAndDesc()throws BizConfigCommonException;
	
	/**
	 *  ���ݲ�ѯ�����ȡPage����������ֵ�������Ϣ������ά��B2Cϵͳ�еĴ������ͣ�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeCodeType(QueryRule queryRule, int pageNo, int pageSize) throws BizConfigCommonException ;

	/**
	 * ���ݹ����ѯһ�������ֵ�������Ϣ
	 * @param queryRule
	 * @return
	 */
	public GeCodeType findGeCodeType(QueryRule queryRule) throws BizConfigCommonException ;
	
	/**
	 * ���������ֵ�������Ϣ
	 * @param GeCodeType
	 */
	public boolean updateGeCodeType(GeCodeType geCodeType) throws BizConfigCommonException ;
	
	/**
	 * ���������ֵ�������Ϣ
	 * @param GeCodeType
	 */
	public boolean save(GeCodeType geCodeType) throws BizConfigCommonException ;

	/**
	 * �h�������ֵ�������Ϣ
	 * @param id
	 */
	public boolean delete(String codeType) throws BizConfigCommonException ;
	
	/**
	 * �h��String������key����Ӧ�Ķ���
	 * @param keys
	 */
	public boolean deleteAllGeCodeType(String keys) throws BizConfigCommonException ;
	
	/**
	 * �h��list�����е�GeCodeType����
	 * @param userCode
	 */
	public boolean deleteAllGeCodeType(List<GeCodeType> geCodeTypeList) throws BizConfigCommonException ;
}
