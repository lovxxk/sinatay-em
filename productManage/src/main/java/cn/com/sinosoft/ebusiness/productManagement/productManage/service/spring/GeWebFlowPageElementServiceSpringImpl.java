package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePageElementConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeWebFlowPageElement;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeWebFlowPageElementService;

public class GeWebFlowPageElementServiceSpringImpl extends
		GenericDaoHibernate<GeWebFlowPageElement, String> implements
		GeWebFlowPageElementService {
	
	/***
	 * 
	 * ������Ų�ѯ����ҳ��Ԫ��
	 * @param serialNo ���
	 * 
	 */
	public GeWebFlowPageElement findGeWebFlowPageElementBySerialNo(String serialNo) {
		return super.get(serialNo);
	}
	
	/***
	 * ���ݲ�Ʒ�����ѯ�ڸò�Ʒ������ҳ��Ԫ��
	 * @param coreProductCode ��Ʒ����
	 */
	@SuppressWarnings("unchecked")
	public List<GeWebFlowPageElement> findGeWebFlowPageCoreProductCode(String coreProductCode, String serialNo) {
		StringBuffer hql = new StringBuffer("from GeWebFlowPageElement webFlowPageElement where 1 = 1 ");
		hql.append("and webFlowPageElement.geProductMain.coreProductCode = ? ");
		hql.append("and webFlowPageElement.geWebFlowPage.serialNo = ? ");
		hql.append("order by webFlowPageElement.seqIndex ");
		List<GeWebFlowPageElement> webFlowPageElementList = super.findByHql(hql.toString(), new Object[]{coreProductCode,serialNo});
		return webFlowPageElementList;
	}
	
	
	
	/***
	 * ���ݲ�Ʒ�����ѯ�ڸò�Ʒ������ҳ��Ԫ��
	 * @param coreProductCode ��Ʒ����
	 */
	@SuppressWarnings("unchecked")
	public List<GePageElementConfig> findPageElementConfigByCoreProductCode(String coreProductCode) {
		StringBuffer hql = new StringBuffer("select pageElementConfig from GeWebFlowPageElement webFlowPageElement inner join webFlowPageElement.gePageElementConfig pageElementConfig where 1 = 1 ");
		hql.append("and webFlowPageElement.geProductMain.coreProductCode = ? ");
		hql.append("order by webFlowPageElement.seqIndex ");
		List<GePageElementConfig> pageElementConfigList = super.findByHql(hql.toString(), new Object[]{coreProductCode});
		return pageElementConfigList;
	}
	
	/***
	 * ���²�Ʒҳ������Ԫ�ش���״̬
	 * @param coreProductCode ��Ʒ����
	 * @param elementCode ҳ��Ԫ�ش���
	 */
	public void updateGeWebFlowPageElementStatus(String coreProductCode, String elementCode) {
		StringBuffer hql = new StringBuffer("from GeWebFlowPageElement webFlowPageElement where 1 = 1 ");
		hql.append("and webFlowPageElement.geProductMain.coreProductCode = ? ");
		hql.append("and webFlowPageElement.gePageElementConfig.elementCode = ? ");
		hql.append("order by webFlowPageElement.seqIndex ");
		List<GeWebFlowPageElement> webFlowPageElementList = super.findByHql(hql.toString(), new Object[]{coreProductCode,elementCode});
		for (GeWebFlowPageElement webFlowPageElement:webFlowPageElementList){
			webFlowPageElement.setStatus("1");
			super.update(webFlowPageElement);
		}
	}
	
	/***
	 * ���²�Ʒҳ������Ԫ�ش���״̬
	 * @param status ����״̬
	 * @param coreProductCode ��Ʒ����
	 * @param elementCode ҳ��Ԫ�ش���
	 * 
	 */
	public void updateGeWebFlowPageElementStatus(String status,String coreProductCode, String elementCode) {
		StringBuffer hql = new StringBuffer("from GeWebFlowPageElement webFlowPageElement where 1 = 1 ");
		hql.append("and webFlowPageElement.geProductMain.coreProductCode = ? ");
		hql.append("and webFlowPageElement.gePageElementConfig.elementCode = ? ");
		hql.append("order by webFlowPageElement.seqIndex ");
		List<GeWebFlowPageElement> webFlowPageElementList = super.findByHql(hql.toString(), new Object[]{coreProductCode,elementCode});
		for (GeWebFlowPageElement webFlowPageElement:webFlowPageElementList){
			webFlowPageElement.setStatus(status);
			super.update(webFlowPageElement);
		}
	}
	
	/***
	 * ���²�Ʒ������ҳ������Ԫ�ش���״̬
	 * @param status ����״̬
	 * @param coreProductCode ��Ʒ����
	 * 
	 */
	public void updateGeWebFlowPageElementStatus_all(String status,String coreProductCode){
		StringBuffer hql = new StringBuffer("from GeWebFlowPageElement webFlowPageElement where 1 = 1 ");
		hql.append("and webFlowPageElement.geProductMain.coreProductCode = ? ");
		hql.append("order by webFlowPageElement.seqIndex ");
		List<GeWebFlowPageElement> webFlowPageElementList = super.findByHql(hql.toString(), new Object[]{coreProductCode});
		for (GeWebFlowPageElement webFlowPageElement:webFlowPageElementList){
			webFlowPageElement.setStatus(status);
			super.update(webFlowPageElement);
		}
	}
	
	/***
	 * ���ݲ�ѯ������ѯ��Ʒҳ��Ԫ��
	 * @param queryRule ��ѯ����
	 * 
	 */
	public List<GeWebFlowPageElement> findGeWebFlowPageElementByQueryRule(QueryRule queryRule) {
		return super.find(queryRule);
	}
	
	/***
	 * ��Ӳ�Ʒҳ��Ԫ��
	 * @param geWebFlowPageElement ҳ��Ԫ��
	 * 
	 */
	public void addGeWebFlowPageElement(GeWebFlowPageElement geWebFlowPageElement) {
		super.save(geWebFlowPageElement);
	}
	
	/***
	 * ɾ�����������еĲ�Ʒҳ��Ԫ��
	 * @param geWebFlowPageElementList ��Ʒҳ��Ԫ��
	 * 
	 */
	public void deleteAllGeWebFlowPageElement(List geWebFlowPageElementList){
		super.deleteAll(geWebFlowPageElementList);
	}
	
	/**
	 * ��Ӳ�Ʒ����ҳ���ҳ��Ԫ��
	 * @param geWebFlowPageElementList ҳ��Ԫ��
	 * @param coreProductCode ��Ʒ����
	 */
	public void addAllGeWebFlowPageElement(List<GeWebFlowPageElement> geWebFlowPageElementList, String coreProductCode) {
		try {
			StringBuffer hql = new StringBuffer("from GeWebFlowPageElement webFlowPageElement where 1 = 1 ");
			hql.append("and webFlowPageElement.geProductMain.coreProductCode = ? ");
			hql.append("and webFlowPageElement.geWebFlowPage.isShow = ? ");
			List<GeWebFlowPageElement> oldGePageElementConfigList =  super.findByHql(hql.toString(), new Object[]{coreProductCode, "1"});
			
			for (GeWebFlowPageElement oldGePageElementConfig:oldGePageElementConfigList) {
				for (GeWebFlowPageElement gePageElementConfig:geWebFlowPageElementList) {
					if(gePageElementConfig!=null){
						if (oldGePageElementConfig.getSerialNo().equals(gePageElementConfig.getSerialNo())) {
							gePageElementConfig.setStatus(oldGePageElementConfig.getStatus());
						}
					}
				}
			}
			
			for (GeWebFlowPageElement oldGePageElementConfig:oldGePageElementConfigList) {
				boolean isExist = false;
				for (GeWebFlowPageElement gePageElementConfig:geWebFlowPageElementList) {
					if(gePageElementConfig!=null){
						
						if (oldGePageElementConfig.getSerialNo().equals(gePageElementConfig.getSerialNo())) {
							isExist = true;
							break;
						}
					}
				}
				if (!isExist) {
					delete(oldGePageElementConfig);
				}
			}
			for (GeWebFlowPageElement webFlowPageElement:geWebFlowPageElementList) {
				if(webFlowPageElement!=null){
					if(webFlowPageElement.getSerialNo()==null || "".equals(webFlowPageElement.getSerialNo()))
						webFlowPageElement.setStatus("0");
					
					if (StringUtils.isNotBlank(webFlowPageElement.getSerialNo())) {
						GeWebFlowPageElement update = super.get(webFlowPageElement.getSerialNo());
						BeanUtils.copyProperties(webFlowPageElement, update);
						super.update(update);
					} else {
						super.save(webFlowPageElement);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ��ҳ��Ԫ��
	 * @param geWebFlowPageElement ҳ��Ԫ��
	 * 
	 */
	public void delete(GeWebFlowPageElement geWebFlowPageElement) {
		super.delete(geWebFlowPageElement);
	}
	
	public List<String> getElementCodesByOrder(String productCode,String flowCode,String pageCode){
		String sql = "select tb3.elementcode from ge_webflow tb1,ge_webflowpage tb2,ge_webflowpageelement tb3 "+
			" where tb1.coreproductcode = ? and tb1.flowcode = ? "+
			" and tb2.pagecode = ? and tb1.serialno = tb2.webflowsercialno "+
			" and tb1.coreproductcode = tb2.coreproductcode and tb2.serialno = tb3.flowpageserialno "+
			" and tb2.coreproductcode = tb3.coreproductcode order by tb3.seqindex";
		return super.findBySql(sql, productCode,flowCode,pageCode);
	}	
}
