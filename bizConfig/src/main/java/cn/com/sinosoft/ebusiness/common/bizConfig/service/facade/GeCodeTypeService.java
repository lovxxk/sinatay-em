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
	 * 查询某类数据字典
	 * @return
	 * @throws Exception
	 */
	public List<GeCodeType> findAll()throws BizConfigCommonException;
	
	/**
	 * 查询某类数据字典
	 * @param geCodeType
	 * @return <数据字典ID,数据字典名称>
	 * @throws BizConfigCommonException
	 */
	public Map<String, String> findAllCodeAndDesc()throws BizConfigCommonException;
	
	/**
	 *  根据查询对象获取Page对象的数据字典主表信息（用于维护B2C系统中的代码类型）
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeCodeType(QueryRule queryRule, int pageNo, int pageSize) throws BizConfigCommonException ;

	/**
	 * 根据规则查询一条数据字典主表信息
	 * @param queryRule
	 * @return
	 */
	public GeCodeType findGeCodeType(QueryRule queryRule) throws BizConfigCommonException ;
	
	/**
	 * 更新数据字典主表信息
	 * @param GeCodeType
	 */
	public boolean updateGeCodeType(GeCodeType geCodeType) throws BizConfigCommonException ;
	
	/**
	 * 保存数据字典主表信息
	 * @param GeCodeType
	 */
	public boolean save(GeCodeType geCodeType) throws BizConfigCommonException ;

	/**
	 * 刪除数据字典主表信息
	 * @param id
	 */
	public boolean delete(String codeType) throws BizConfigCommonException ;
	
	/**
	 * 刪除String对象中key所对应的对象
	 * @param keys
	 */
	public boolean deleteAllGeCodeType(String keys) throws BizConfigCommonException ;
	
	/**
	 * 刪除list集合中的GeCodeType对象
	 * @param userCode
	 */
	public boolean deleteAllGeCodeType(List<GeCodeType> geCodeTypeList) throws BizConfigCommonException ;
}
