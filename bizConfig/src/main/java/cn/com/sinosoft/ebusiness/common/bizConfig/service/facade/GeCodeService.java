/**
 * 
 */
package cn.com.sinosoft.ebusiness.common.bizConfig.service.facade;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCodeId;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BizConfigCommonException;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;

/**
 *
 */
public interface GeCodeService {
	
	/**
	 * ��ѯ������ѯ�������������������ֵ�
	 * @param queryRule
	 * @return
	 */
	public List<GeCode> findAll(QueryRule queryRule) throws BizConfigCommonException;
	
	/**
	 * ��ѯĳ�������ֵ�
	 * @return
	 * @throws Exception
	 */
	public List<GeCode> findAllByGeCodeType(String geCodeType)throws BizConfigCommonException;
	
	/**
	 * ��ѯĳ�������ֵ�
	 * @return
	 * @throws Exception
	 */
	public List<GeCode> findAllByGeCodeTypeAndValidInd(String geCodeType, String validInd)throws BizConfigCommonException;
	
	/**
	 * ��ѯĳ�������ֵ�
	 * @param geCodeType
	 * @return <�����ֵ�ID,�����ֵ�����>
	 * @throws BizConfigCommonException
	 */
	public Map<String, String> findAllCodeAndName(String geCodeType)throws BizConfigCommonException;
	
	/**
	 * ��ѯĳ�������ֵ�
	 * @param geCodeType
	 * @return ������ϣ�ÿ��Ԫ��Ϊ�������ֵ�ID,�����ֵ�������ɳ���Ϊ2������
	 * @throws BizConfigCommonException
	 */
	public List<String[]> findCodeAndNameByType(String geCodeType)throws BizConfigCommonException;
	
	/**
	 * ���������ֵ�����ѯ��������
	 * @param codeCode
	 * @return
	 * @throws Exception
	 */
	public String findCodeCName(String codeCode,String geCodeType)throws BizConfigCommonException;
	
	/**
	 * ���������ֵ�����ѯӢ������
	 * @param codeCode
	 * @return
	 * @throws Exception
	 */
	public String findCodeEName(String codeCode,String geCodeType)throws BizConfigCommonException;
	
	/**
	 * �����������ƻ�ȡ�����ֵ����
	 * @param codeCName
	 * @param geCodeType
	 * @return
	 * @throws Exception
	 */
	public String findCodeCode(String codeCName,String geCodeType)throws BizConfigCommonException;

	/**
	 *  ���ݲ�ѯ�����ȡPage����������ֵ��ӱ���Ϣ�����ڼ�¼B2Cϵͳ�еĻ������룩
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeCode(QueryRule queryRule, int pageNo, int pageSize) throws BizConfigCommonException;

	/**
	 * ���ݹ����ѯһ�������ֵ��ӱ���Ϣ
	 * @param queryRule
	 * @return
	 */
	public GeCode findGeCode(QueryRule queryRule) throws BizConfigCommonException ;
	
	/**
	 * ���������ֵ��ӱ���Ϣ
	 * @param GeCode
	 */
	public boolean updateGeCode(GeCode geCode,String[] notUpdateColumns) throws BizConfigCommonException ;
	
	/**
	 * ���������ֵ��ӱ���Ϣ
	 * @param GeCode
	 */
	public boolean save(GeCode geCode) throws BizConfigCommonException ;

	/**
	 * �h�������ֵ��ӱ���Ϣ
	 * @param id
	 */
	public boolean delete(GeCodeId id) throws BizConfigCommonException ;
		
	/**
	 * �h��String������key����Ӧ�Ķ���
	 * @param keys
	 */
	public boolean deleteAllGeCodeByIds(List<GeCodeId> ids) throws BizConfigCommonException ;
	
	/**
	 * �h��list�����е�GeCode����
	 * @param userCode
	 */
	public boolean deleteAllGeCode(List<GeCode> geCodeList) throws BizConfigCommonException ;
}
