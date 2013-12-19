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
	 * 根据序号查询流程页面元素
	 * @param serialNo 序号
	 * 
	 */
	public GeWebFlowPageElement findGeWebFlowPageElementBySerialNo(String serialNo) {
		return super.get(serialNo);
	}
	
	/***
	 * 根据产品代码查询于该产品关联的页面元素
	 * @param coreProductCode 产品代码
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
	 * 根据产品代码查询于该产品关联的页面元素
	 * @param coreProductCode 产品代码
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
	 * 更新产品页面流程元素处理状态
	 * @param coreProductCode 产品代码
	 * @param elementCode 页面元素代码
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
	 * 更新产品页面流程元素处理状态
	 * @param status 处理状态
	 * @param coreProductCode 产品代码
	 * @param elementCode 页面元素代码
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
	 * 更新产品下所有页面流程元素处理状态
	 * @param status 处理状态
	 * @param coreProductCode 产品代码
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
	 * 根据查询条件查询产品页面元素
	 * @param queryRule 查询条件
	 * 
	 */
	public List<GeWebFlowPageElement> findGeWebFlowPageElementByQueryRule(QueryRule queryRule) {
		return super.find(queryRule);
	}
	
	/***
	 * 添加产品页面元素
	 * @param geWebFlowPageElement 页面元素
	 * 
	 */
	public void addGeWebFlowPageElement(GeWebFlowPageElement geWebFlowPageElement) {
		super.save(geWebFlowPageElement);
	}
	
	/***
	 * 删除数组中所有的产品页面元素
	 * @param geWebFlowPageElementList 产品页面元素
	 * 
	 */
	public void deleteAllGeWebFlowPageElement(List geWebFlowPageElementList){
		super.deleteAll(geWebFlowPageElementList);
	}
	
	/**
	 * 添加产品所有页面的页面元素
	 * @param geWebFlowPageElementList 页面元素
	 * @param coreProductCode 产品代码
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
	 * 删除页面元素
	 * @param geWebFlowPageElement 页面元素
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
