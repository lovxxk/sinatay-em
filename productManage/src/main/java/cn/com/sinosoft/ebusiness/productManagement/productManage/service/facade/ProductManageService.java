package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeAddresseeConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductApplicantConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductBeneficiaryConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductConfigMain;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductEmergencyConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductExtraInfo;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductInformBook;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductInsuredConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductMain;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductPolicyDisplayConfig;

/**
 * 寿险产品定义
 * 
 *  
 * @version 1.0
 */
public interface ProductManageService {

	
	void saveEntity(Object entity);
	
	List findEntitys(Class classes,QueryRule query);
	
	void updatePro(GeProductMain entity);
	
	void savePro(GeProductMain entity);

	/**
	 * 产品同步(寿险) 
	 * @param coreProductCode 产品代码
	 */
	public String LifeProductSynchronous(String coreProductCode);

	/**
	 * 产品同步(寿险核心) 从中科软核心同步险种+责任
	 * @param riskCode 险种代码
	 */
	public String LifeCoreProductSynchronous(String riskCode);

	/**
	 * 产品同步(财险核心)
	 * @param coreProductCode 产品代码
	 */
	public String FinancialProductSynchronous(String coreProductCode);

	/**
	 * 创建产品
	 * @param geProductMain 产品对象
	 */
	public void createProduct(GeProductMain geProductMain);

	/**
	 * 查询所有产品
	 * @param queryRule 查询条件对象
	 * @param pageNo 第几页
	 * @param pageSize 每页数据
	 */
	public Page searchProduct(QueryRule queryRule, int pageNo, int pageSize);

	/**
	 * 删除产品
	 * 如果产品状态为"已审核"或者"已发布",则此款产品不能进行删除
	 * @param coreProductCode 产品代码
	 */
	public void deleteProduct(String coreProductCode) throws Exception;

	/**
	 * 查询某一款产品
	 * @param coreProductCode 产品代码
	 * @return {@link GeProductMain} 产品对象
	 */
	public GeProductMain searchProductByProductCode(String coreProductCode);
	
	
	
	/**
	 * 查询某一款产品的销售流程(new)
	 * @param coreProductCode
	 * @return 销售流程字符串
	 */
	public String searchProductSaleFlow(String coreProductCode);
	
	/**
	 * 配置产品销售流程(保存/更新)
	 * @param coreProductCode 产品代码
	 * @param productFlow 销售流程
	 */
	public void configProductSaleFlow(String coreProductCode, String productFlow);
	
	/**
	 * 初始化产品总览配置项(new)
	 * @param productFlow
	 */
	public GeProductConfigMain initProductConfigMain(GeProductConfigMain geProductConfigMain,String productFlow);
	
	/**
	 * 判断产品是否配置了销售流程
	 * @param coreProductCode
	 * @return "true"=以配置 "false"未配置
	 */
	public boolean isConfigProductSaleFlow(String coreProductCode);

	
	/**
	 * 配置产品基本信息(更新)
	 * @param geProductMain 产品对象 
	 * @return
	 */
	public void configProductDetail(GeProductMain geProductMain);

	/**
	 * 查询某一款产品的法律声明
	 * @param coreProductCode 产品代码
	 * @return GeProductExtraInfo 产品额外信息对象
	 */
	public GeProductExtraInfo searchLegalNotices(String coreProductCode);
	
	/**
	 * 配置法律声明(保存/更新)
	 * @param eid
	 * @return
	 */
	public void configLegalNotices(String coreProductCode, String legalNotices);

	/**
	 * 查询投保告知书
	 * @param coreProductCode 产品编码
	 * @return
	 */
	public List<GeProductInformBook> searchInformBook(String coreProductCode);
	
	/**
	 * 配置投保告知(保存/更新)
	 * @return
	 */
	public void configInformBook(String coreProductCode,
			List<GeProductInformBook> geProductInformBookList);

	/**
	 * 配置保单信息(保存/更新)
	 * @param coreProductCode 产品代码
	 * @return
	 */
	public void configPolicy(String coreProductCode,
			GeProductApplicantConfig geProductApplicantConfig,
			List<GeProductInsuredConfig> geProductInsuredConfigList,
			GeProductBeneficiaryConfig geProductBeneficiaryConfig,
			GeProductEmergencyConfig geProductEmergencyConfig,
			GeAddresseeConfig geAddresseeConfig,
			GeProductPolicyDisplayConfig geProductPolicyDisplayConfig);

	/**
	 * 查询某一个产品下的投保声明
	 * @param coreProductCode 产品代码
	 * @return
	 */
	public GeProductExtraInfo searchProposalNotices(String coreProductCode);
	
	/**
	 * 配置投保声明(保存/更新)
	 * @param coreProductCode
	 */
	public void configProposalNotices(String coreProductCode,String proposalNotices);

	/**
	 * 更新产品总览信息(更新)
	 * @param geProductMain
	 */
	public void configProductMainConfig(GeProductConfigMain geProductConfigMain);

	/**
	 * 产品状态更新
	 * @param coreProductCode 产品代码
	 * @param productStatus 产品状态
	 */
	public String updateProductStatus(String coreProductCode, String productStatus);

	/**
	 * 查询产品状态
	 * @param coreProductCode 产品代码
	 * @return
	 */
	public String searchProductStatus(String coreProductCode);
	
	/**
	 * 查询某个产品下的投保人配置项
	 * @param coreProductCode 产品代码
	 * @return
	 */
	public GeProductApplicantConfig searchProductApplicantConfig(
			String coreProductCode);

	/**
	 * 查询某个产品下的保单显示配置项
	 * @param coreProductCode 产品代码
	 * @return
	 */
	public GeProductPolicyDisplayConfig searchProductPolicyDisplayConfig(
			String coreProductCode);
	/**
	 * 查询某个产品下被保人配置项
	 * @param coreProductCode 产品代码
	 * @return
	 */
	public List<GeProductInsuredConfig> searchProductInsuredConfig(
			String coreProductCode);

	/**
	 * 查询某个产品下受益人配置项
	 * @param coreProductCode 产品代码
	 * @return
	 */
	public GeProductBeneficiaryConfig searchProductBeneficiaryConfig(
			String coreProductCode);
	
	
	/**
	 * 查询某一个产品下紧急联系人配置项
	 * @param coreProductCode
	 * @return
	 */
	public GeProductEmergencyConfig searchProductEmergencyConfig(String coreProductCode);

	/**
	 * 查询某一个产品下收件人配置项
	 * @param coreProductCode
	 * @return
	 */
	public GeAddresseeConfig searchProductAddresseeConfig(String coreProductCode);

	
	/**
	 * 根据页面被保人配置项个数,创建被保人对象集合
	 * @param count
	 * @return
	 */
	public List<GeProductInsuredConfig> createGeProductInsuredConfig(int count);
	
	/**
	 * 删除某个产品下的所有投保告知书
	 * @param coreProductCode 产品代码
	 */
	public void deleteAllInformBooks(String coreProductCode);
	
	/**
	 * 保存某一个产品下的所有投保告知书
	 * @param coreProductCode
	 * @param geProductInformBookList
	 */
	public void saveALLInformBooks(String coreProductCode,List<GeProductInformBook> geProductInformBookList);
	
	/**
	 * 保存机构
	 * @param coreProductCode
	 * @param deptID
	 */
	public void saveDept(String coreProductCode,String deptID);
	
	/**
	 * 根据产品代码删除投保人配置信息
	 * @param coreProductCode
	 */
	public void deleteProductApplicantConfigByProductCode(String coreProductCode);
	
	/**
	 * 根据产品代码删除受益人配置信息
	 * @param coreProductCode
	 */
	public void deleteProductBeneficiaryConfigByProductCode(String coreProductCode);
	
	/**
	 * 根据产品代码删除被保人配置信息
	 * @param coreProductCode
	 */
	public void deleteProductInsuredConfigByProductCode(String coreProductCode);
	/**
	 * 根据产品代码删除收件人配置信息
	 * @param coreProductCode
	 */
	public void deleteProductAddresseeConfigByProductCode(String coreProductCode);
	
	/**
	 * 根据产品条件查询产品
	 * @param queryRule
	 * @return
	 */
	public ArrayList<GeProductMain> searchProductMains(QueryRule queryRule);

	public abstract GeProductMain findProductMainByCoreProductCode(String coreProductCode);
	/**
	 * 通过销售地区来查询产品表并且还能够带出eid的值
	 * @param geProductMain
	 * @param isParentFlag
	 * @param pageNo
	 * @param pageSize
	 * @param deptId
	 * @return
	 */
	public Page findProductMainBySaleDept(GeProductMain geProductMain,String sqlForward ,int pageNo,int pageSize,String deptId);
	public List<GeProductMain> findProductMainWithEidList(GeProductMain geProductMain,String sqlForward,String deptId);
}
