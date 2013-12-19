package cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade;


import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import cn.com.sinosoft.businessModule.network.domain.Network;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeArea;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKind;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKindRelate;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeKindRelateId;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeRisk;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeRiskClassCodeAndRiskCode;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.RiskAndKind;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.RiskClauseKindData;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.RiskInputCoreDto;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;


/**
 * 公用Service接口,提供代码翻译,获取最大号等通用功能
 *
 */
public interface BizCommonService {
	/**
	 * 根据代码类型,代码获得代码名称
	 * @param codeType 代码类型
	 * @param codeCode 代码
	 * @return 代码名称
	 */
	public String findCodeName(String codeType, String codeCode);
	/**
	 * 根据代码类型,获得该类型全部代码集合
	 * @param codeType 代码类型
	 * @return 代码集合
	 */
	public List<GeCode> findCodeList(String codeType);
	/**
	 * 获取当前业务的序列号
	 * 
	 * @param typeCode 业务代码
	 * @param date     业务日期
	 * @param clazz    调用时传入,为调用该方法要插入的对应的hibernate实体类
	 * 01 --代表订单号
	 * 02 --代表试算单号
	 * 03 --企业ID流水号
	 * 在调用这个 流水号  的时候  一定要问清楚 谁用了这个流水的第一个参数.
	 * @return (当序列号连续时,返回最大号,不连续时,返回缺失的最小号)
	 */
	public  String takeSerialNo(String typeCode, Date dateTemp,Class<?> clazz);
	/**
	 * 当业务表中删除对应的序列号时()需要调用该方法,将删除的序列号送回最大号码表
	 * @param serialNo 被删除的序号
	 */
	public void revertMaxNo(String serialNo);
	/**
	 * 获得省份列表
	 * @return 省份列表集合
	 */
	public List<GeArea> findAllProvince();
	
	/**
	 * 根据省份代码获得该省份下城市列表集合
	 * 如果upperareacode为null,"","   "将返回全国所有城市列表
	 * @param upperareacode 省份代码
	 * @return 城市集合
	 */
	public List<GeArea> findCityListByProvince(String upperareacode);
	
	
	/**
	 * 根据险种代码获得险种对象
	 * @param riskCode 险种代码
	 * @return 险种对象
	 */
	public GeRisk findGeRiskByCode(String riskCode);
	
	


	/**
	 * 根据地区代码获得地区对象
	 * @param areacode 地区代码
	 * @return 地区对象or NULL
	 */
	public GeArea findAreaByAreacode(String areacode);
	
	/**
	 * 查询所有的险种
	 * @return
	 */
	public List<GeRisk> findAllRiskCode(QueryRule queryRule);
	/**
	 * 查询险种，按条件查询 分页查询
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeRiskList(QueryRule queryRule,int pageNo,int pageSize);
	
	public List<GeRisk> findGeRiskListWithEid(GeRisk geRisk);
	public Page findGeRiskListWithEid(GeRisk geRisk,int pageNo,int pageSize);
	/**
	 * 查询指定险种代码下所有险别
	 * @param riskCode
	 * @return
	 */
	public List<GeKind> findKindByRiskCode(String riskCode);
	
	//翻译代码名称
	public String findEidName(String riskCode);
	/**
	 * 根据险别代码获取有效的险别
	 * @param kindCodes
	 * @return
	 */
	public List<GeKind> findValidateKind(List<String> kindCodes);
	/**
	 * 套餐展示，获取无效险别
	 * @return
	 */
	public List<GeKind> findInvalidateKind();
	
	/**
	 * 
	 * 查询用于套餐展示的主险险别列表
	 * @param isFreeCombo true 是自由定制；false 是固定套餐
	 * @return
	 */
	public List<GeKind> findValidateMainKind(boolean isFreeCombo);
	/**
	 * 
	 * 查询用于套餐展示的主险险别列表
	 * @param isFreeCombo true 是自由定制；false 是固定套餐
	 * @return
	 */
	public List<GeKind> findValidateMainKind(boolean isFreeCombo,String riskcode);
	/**
	 * 
	 * 查询用于套餐展示的附加险险别列表
	 * @param isFreeCombo true 是自由定制；false 是固定套餐
	 * @return
	 */
	public List<GeKind> findValidateAttachKind(boolean isFreeCombo);
	/**
	 * 
	 * 查询用于套餐展示的附加险险别列表
	 * @param isFreeCombo true 是自由定制；false 是固定套餐
	 * @return
	 */
	public List<GeKind> findValidateAttachKind(boolean isFreeCombo,String riskCode);
	/**
	 * 
	 * 查询用于套餐展示的不计免赔险别列表
	 * @param isFreeCombo true 是自由定制；false 是固定套餐
	 * @return
	 */
	public List<GeKind> findValidateDeductKind(boolean isFreeCombo);
	/**
	 * 
	 * 查询用于套餐展示的不计免赔险别列表
	 * @param isFreeCombo true 是自由定制；false 是固定套餐
	 * @return
	 */
	public List<GeKind> findValidateDeductKind(boolean isFreeCombo,String riskCode);
	
	/**
	 * 根据险种代码,主险险别代码获取该主险下所有的附加险
	 * @param riskCode
	 * @param kindCode
	 * @return
	 */
	public List<GeKind> findKindByMain(String riskCode, String kindCode);
	/**
	 * 得到 核心的险种险别接口
	 */
	public void getCoreRiskAndKind(List<GeRiskClassCodeAndRiskCode> riskCodeList ,java.util.Map<String,RiskAndKind> kindMap);
	/**
	 * 同步核心的险种险别接口
	 * @param riskInputCoreDto
	 * @return
	 * @throws AxisFault 
	 */
	public List<RiskClauseKindData> synchCoreRiskAndKind(List<RiskInputCoreDto> riskInputCoreDtoList);
	
	public Map<String,List<GeKindRelate>> getKindRelateByRiskCode(String riskCode);
	
	//短信网关的接口
	//public boolean sendMessage(String mobile,String content);
	public boolean sendMessage(String functionId,String[] params,String mobile);
	
	/**
	 * 寿险发送短信接口（批量发送）
	 * @param phones 手机号组成的集合
	 * @param functionId 
	 * @param params
	 * @param beginTime 格式："yyyyMMdd HHmmss"
	 * @param endTime 格式："yyyyMMdd HHmmss"
	 * @return
	 */
	public boolean sendSMFromLife(List<String> phones,String functionId,String[] params,String beginTime,String endTime);
	
	/**
	 * 寿险发送短信接口(单个发送)
	 * @param phone 手机号
	 * @param functionId
	 * @param params
	 * @param beginTime 格式："yyyyMMdd HHmmss"
	 * @param endTime 格式："yyyyMMdd HHmmss"
	 * @return
	 */
	public boolean sendSMFromLife(String phone,String functionId,String[] params,String beginTime,String endTime);
	
	/**
	 * 寿险即时发送短信（批量发送）
	 * @param phones 手机号组成的集合
	 * @param functionId 
	 * @param params
	 * @return
	 */
	public boolean sendSMFromLife(List<String> phones,String functionId,String[] params);
	
	
	/**
	 * 寿险即时发送短信
	 * @param phone
	 * @param functionId
	 * @param params
	 * @return
	 */
	public boolean sendSMFromLife(String phone,String functionId,String[] params);
	
	/**
	 * 寿险即时发送短信（批量发送）
	 * @param phones 手机号组成的集合
	 * @param content 短信内容
	 * @return
	 */
	public boolean sendSMFromLife(List<String> phones,String content);
	
	/**
	 * 寿险即时发送短信
	 * @param phone
	 * @param content
	 * @return
	 */
	public boolean sendSMFromLife(String phone,String content);
	
	public void addGeRisk(GeRisk geRisk);

	
	/**
	 * 添加险种险别
	 * @param geKind
	 * @return 
	 */
	public String addKind(GeKind geKind);
	
	/**
	 * 查询险种代码，按条件查询 分页查询
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeKindList(QueryRule queryRule,int pageNo,int pageSize);
    /**
     * 
     * @param riskCode
     * @param kindCode
     * @return GeKind
     * 根据险种编码与险别编码查询
     */
	public GeKind findGeKindByCode(String riskCode,String kindCode);
	/**
	 * 
	 * @param riskCode
	 * @param kindCode
	 * @return GeKind
	 * 修改险别信息前的查询
	 */
	public GeKind prepareUpdateGeKind(String riskCode,String kindCode);
	
	/**
	 * 修改险别信息
	 */
	public String updateGeEnKind(GeKind geKind);
	/**
	 * 
	 * @param list
	 *  删除多条
	 */
	public void deleteByList(List<GeKind> list);
	/**
	 * @param riskCode
	 * @param kindCode
	 *  根据险别code删除一条
	 */
	public void deleteById(String riskCode,String kindCode);
	

	
	public String updateGeRisk(GeRisk geRisk);
	
	public void deleteGeRiskListById(String riskCode);
	
	public String saveGeRisk(GeRisk geRisk);
	
	public List<GeRisk> findGeRiskListBy(String riskCode,String riskCName);
	public Page getGeCardProductList(GeRisk geRisk,int pageNo,int pageSize);
	
	public List<String> findEid(String riskCode);
	
	/**
	 * 
	 * @param kindCode
	 * @return 返回条数
	 *  校验该险别是否在套餐上面使用
	 */
	public String checkKindCodeUnique(String kindCode,String riskCode);
	
	/**
	 * 
	 * @param gekind
	 * @return 返回条数
	 * 校验orderno是否重复
	 */
	public String checkOrderNo(GeKind gekind);
	
	/**
	 *  添加主险附加险关系
	 */
	public String addKindRelate(GeKindRelate geKindRelate);
	/**
	 * 查询险别关系列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findKindRelateList(QueryRule queryRule,int pageNo,int pageSize);
	/**
	 * 险别关系维护修改
	 * @param geKindRelate
	 * @return
	 */
	public String modifyKindRelate(GeKindRelate geKindRelate);
	public GeKindRelate viewKindRelate(GeKindRelate geKindRelate);
	
	/**
	 * 校验险别关系是否重复
	 * @return
	 */
	public Map<String,Object> checkRelate(GeKindRelateId relateId);
	public String checkAddFlag(GeKind ge);
	public String checkFlag(GeKindRelate geRelate,String flag);
	/**
	 * 
	 * @param riskCode
	 * @param relateKindCode
	 * @return 传入附加险查询主险码
	 */
	public List<GeKindRelate> findRelateKindCode(String riskCode, String relateKindCode);
	
	/**
	 *  判断该附加险是否有主险关系 
	 * @param riskCode
	 * @param kindCode
	 * @param kindFlag
	 * @return
	 */
	public Map<String,Object> vaildateRelateCode(String riskCode,String kindCode);
	/**
	 *  更新险种orderNO排序 
	 * @param riskCode
	 * @param kindCode
	 */
	public void updateGeEnKindOrderNo(GeKind geKind);
}
