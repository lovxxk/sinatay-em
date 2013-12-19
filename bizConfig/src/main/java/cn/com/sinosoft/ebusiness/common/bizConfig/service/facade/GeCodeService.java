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
	 * 查询条件查询符合条件的所有数据字典
	 * @param queryRule
	 * @return
	 */
	public List<GeCode> findAll(QueryRule queryRule) throws BizConfigCommonException;
	
	/**
	 * 查询某类数据字典
	 * @return
	 * @throws Exception
	 */
	public List<GeCode> findAllByGeCodeType(String geCodeType)throws BizConfigCommonException;
	
	/**
	 * 查询某类数据字典
	 * @return
	 * @throws Exception
	 */
	public List<GeCode> findAllByGeCodeTypeAndValidInd(String geCodeType, String validInd)throws BizConfigCommonException;
	
	/**
	 * 查询某类数据字典
	 * @param geCodeType
	 * @return <数据字典ID,数据字典名称>
	 * @throws BizConfigCommonException
	 */
	public Map<String, String> findAllCodeAndName(String geCodeType)throws BizConfigCommonException;
	
	/**
	 * 查询某类数据字典
	 * @param geCodeType
	 * @return 结果集合，每个元素为由数据字典ID,数据字典名称组成长度为2的数组
	 * @throws BizConfigCommonException
	 */
	public List<String[]> findCodeAndNameByType(String geCodeType)throws BizConfigCommonException;
	
	/**
	 * 根据数据字典编码查询中文名称
	 * @param codeCode
	 * @return
	 * @throws Exception
	 */
	public String findCodeCName(String codeCode,String geCodeType)throws BizConfigCommonException;
	
	/**
	 * 根据数据字典编码查询英文名称
	 * @param codeCode
	 * @return
	 * @throws Exception
	 */
	public String findCodeEName(String codeCode,String geCodeType)throws BizConfigCommonException;
	
	/**
	 * 根据中文名称获取数据字典编码
	 * @param codeCName
	 * @param geCodeType
	 * @return
	 * @throws Exception
	 */
	public String findCodeCode(String codeCName,String geCodeType)throws BizConfigCommonException;

	/**
	 *  根据查询对象获取Page对象的数据字典子表信息（用于记录B2C系统中的基础代码）
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeCode(QueryRule queryRule, int pageNo, int pageSize) throws BizConfigCommonException;

	/**
	 * 根据规则查询一条数据字典子表信息
	 * @param queryRule
	 * @return
	 */
	public GeCode findGeCode(QueryRule queryRule) throws BizConfigCommonException ;
	
	/**
	 * 更新数据字典子表信息
	 * @param GeCode
	 */
	public boolean updateGeCode(GeCode geCode,String[] notUpdateColumns) throws BizConfigCommonException ;
	
	/**
	 * 保存数据字典子表信息
	 * @param GeCode
	 */
	public boolean save(GeCode geCode) throws BizConfigCommonException ;

	/**
	 * 刪除数据字典子表信息
	 * @param id
	 */
	public boolean delete(GeCodeId id) throws BizConfigCommonException ;
		
	/**
	 * 刪除String对象中key所对应的对象
	 * @param keys
	 */
	public boolean deleteAllGeCodeByIds(List<GeCodeId> ids) throws BizConfigCommonException ;
	
	/**
	 * 刪除list集合中的GeCode对象
	 * @param userCode
	 */
	public boolean deleteAllGeCode(List<GeCode> geCodeList) throws BizConfigCommonException ;
}
