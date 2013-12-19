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
 * ���ղ�Ʒ����
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
	 * ��Ʒͬ��(����) 
	 * @param coreProductCode ��Ʒ����
	 */
	public String LifeProductSynchronous(String coreProductCode);

	/**
	 * ��Ʒͬ��(���պ���) ���п������ͬ������+����
	 * @param riskCode ���ִ���
	 */
	public String LifeCoreProductSynchronous(String riskCode);

	/**
	 * ��Ʒͬ��(���պ���)
	 * @param coreProductCode ��Ʒ����
	 */
	public String FinancialProductSynchronous(String coreProductCode);

	/**
	 * ������Ʒ
	 * @param geProductMain ��Ʒ����
	 */
	public void createProduct(GeProductMain geProductMain);

	/**
	 * ��ѯ���в�Ʒ
	 * @param queryRule ��ѯ��������
	 * @param pageNo �ڼ�ҳ
	 * @param pageSize ÿҳ����
	 */
	public Page searchProduct(QueryRule queryRule, int pageNo, int pageSize);

	/**
	 * ɾ����Ʒ
	 * �����Ʒ״̬Ϊ"�����"����"�ѷ���",��˿��Ʒ���ܽ���ɾ��
	 * @param coreProductCode ��Ʒ����
	 */
	public void deleteProduct(String coreProductCode) throws Exception;

	/**
	 * ��ѯĳһ���Ʒ
	 * @param coreProductCode ��Ʒ����
	 * @return {@link GeProductMain} ��Ʒ����
	 */
	public GeProductMain searchProductByProductCode(String coreProductCode);
	
	
	
	/**
	 * ��ѯĳһ���Ʒ����������(new)
	 * @param coreProductCode
	 * @return ���������ַ���
	 */
	public String searchProductSaleFlow(String coreProductCode);
	
	/**
	 * ���ò�Ʒ��������(����/����)
	 * @param coreProductCode ��Ʒ����
	 * @param productFlow ��������
	 */
	public void configProductSaleFlow(String coreProductCode, String productFlow);
	
	/**
	 * ��ʼ����Ʒ����������(new)
	 * @param productFlow
	 */
	public GeProductConfigMain initProductConfigMain(GeProductConfigMain geProductConfigMain,String productFlow);
	
	/**
	 * �жϲ�Ʒ�Ƿ���������������
	 * @param coreProductCode
	 * @return "true"=������ "false"δ����
	 */
	public boolean isConfigProductSaleFlow(String coreProductCode);

	
	/**
	 * ���ò�Ʒ������Ϣ(����)
	 * @param geProductMain ��Ʒ���� 
	 * @return
	 */
	public void configProductDetail(GeProductMain geProductMain);

	/**
	 * ��ѯĳһ���Ʒ�ķ�������
	 * @param coreProductCode ��Ʒ����
	 * @return GeProductExtraInfo ��Ʒ������Ϣ����
	 */
	public GeProductExtraInfo searchLegalNotices(String coreProductCode);
	
	/**
	 * ���÷�������(����/����)
	 * @param eid
	 * @return
	 */
	public void configLegalNotices(String coreProductCode, String legalNotices);

	/**
	 * ��ѯͶ����֪��
	 * @param coreProductCode ��Ʒ����
	 * @return
	 */
	public List<GeProductInformBook> searchInformBook(String coreProductCode);
	
	/**
	 * ����Ͷ����֪(����/����)
	 * @return
	 */
	public void configInformBook(String coreProductCode,
			List<GeProductInformBook> geProductInformBookList);

	/**
	 * ���ñ�����Ϣ(����/����)
	 * @param coreProductCode ��Ʒ����
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
	 * ��ѯĳһ����Ʒ�µ�Ͷ������
	 * @param coreProductCode ��Ʒ����
	 * @return
	 */
	public GeProductExtraInfo searchProposalNotices(String coreProductCode);
	
	/**
	 * ����Ͷ������(����/����)
	 * @param coreProductCode
	 */
	public void configProposalNotices(String coreProductCode,String proposalNotices);

	/**
	 * ���²�Ʒ������Ϣ(����)
	 * @param geProductMain
	 */
	public void configProductMainConfig(GeProductConfigMain geProductConfigMain);

	/**
	 * ��Ʒ״̬����
	 * @param coreProductCode ��Ʒ����
	 * @param productStatus ��Ʒ״̬
	 */
	public String updateProductStatus(String coreProductCode, String productStatus);

	/**
	 * ��ѯ��Ʒ״̬
	 * @param coreProductCode ��Ʒ����
	 * @return
	 */
	public String searchProductStatus(String coreProductCode);
	
	/**
	 * ��ѯĳ����Ʒ�µ�Ͷ����������
	 * @param coreProductCode ��Ʒ����
	 * @return
	 */
	public GeProductApplicantConfig searchProductApplicantConfig(
			String coreProductCode);

	/**
	 * ��ѯĳ����Ʒ�µı�����ʾ������
	 * @param coreProductCode ��Ʒ����
	 * @return
	 */
	public GeProductPolicyDisplayConfig searchProductPolicyDisplayConfig(
			String coreProductCode);
	/**
	 * ��ѯĳ����Ʒ�±�����������
	 * @param coreProductCode ��Ʒ����
	 * @return
	 */
	public List<GeProductInsuredConfig> searchProductInsuredConfig(
			String coreProductCode);

	/**
	 * ��ѯĳ����Ʒ��������������
	 * @param coreProductCode ��Ʒ����
	 * @return
	 */
	public GeProductBeneficiaryConfig searchProductBeneficiaryConfig(
			String coreProductCode);
	
	
	/**
	 * ��ѯĳһ����Ʒ�½�����ϵ��������
	 * @param coreProductCode
	 * @return
	 */
	public GeProductEmergencyConfig searchProductEmergencyConfig(String coreProductCode);

	/**
	 * ��ѯĳһ����Ʒ���ռ���������
	 * @param coreProductCode
	 * @return
	 */
	public GeAddresseeConfig searchProductAddresseeConfig(String coreProductCode);

	
	/**
	 * ����ҳ�汻�������������,���������˶��󼯺�
	 * @param count
	 * @return
	 */
	public List<GeProductInsuredConfig> createGeProductInsuredConfig(int count);
	
	/**
	 * ɾ��ĳ����Ʒ�µ�����Ͷ����֪��
	 * @param coreProductCode ��Ʒ����
	 */
	public void deleteAllInformBooks(String coreProductCode);
	
	/**
	 * ����ĳһ����Ʒ�µ�����Ͷ����֪��
	 * @param coreProductCode
	 * @param geProductInformBookList
	 */
	public void saveALLInformBooks(String coreProductCode,List<GeProductInformBook> geProductInformBookList);
	
	/**
	 * �������
	 * @param coreProductCode
	 * @param deptID
	 */
	public void saveDept(String coreProductCode,String deptID);
	
	/**
	 * ���ݲ�Ʒ����ɾ��Ͷ����������Ϣ
	 * @param coreProductCode
	 */
	public void deleteProductApplicantConfigByProductCode(String coreProductCode);
	
	/**
	 * ���ݲ�Ʒ����ɾ��������������Ϣ
	 * @param coreProductCode
	 */
	public void deleteProductBeneficiaryConfigByProductCode(String coreProductCode);
	
	/**
	 * ���ݲ�Ʒ����ɾ��������������Ϣ
	 * @param coreProductCode
	 */
	public void deleteProductInsuredConfigByProductCode(String coreProductCode);
	/**
	 * ���ݲ�Ʒ����ɾ���ռ���������Ϣ
	 * @param coreProductCode
	 */
	public void deleteProductAddresseeConfigByProductCode(String coreProductCode);
	
	/**
	 * ���ݲ�Ʒ������ѯ��Ʒ
	 * @param queryRule
	 * @return
	 */
	public ArrayList<GeProductMain> searchProductMains(QueryRule queryRule);

	public abstract GeProductMain findProductMainByCoreProductCode(String coreProductCode);
	/**
	 * ͨ�����۵�������ѯ��Ʒ���һ��ܹ�����eid��ֵ
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
