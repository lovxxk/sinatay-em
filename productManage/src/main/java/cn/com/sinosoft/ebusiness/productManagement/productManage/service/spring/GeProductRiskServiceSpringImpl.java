package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeDutyConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductDuty;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductMain;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductRisk;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeRiskConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeDutyConfigService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeProductDutyService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeProductRiskService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeRiskConfigService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.ProductManageService;

public class GeProductRiskServiceSpringImpl  extends
	GenericDaoHibernate<GeProductRisk, String> implements GeProductRiskService {
	//@Autowired
	//private GeProductRiskService geProductRiskService;
	
	@Autowired
	private ProductManageService productManageService;
	
	@Autowired
	private GeProductDutyService geProductDutyService;
	
	@Autowired
	private GeRiskConfigService geRiskConfigService;
	
	@Autowired
	private GeDutyConfigService geDutyConfigService;
	
	public String findGeProductRiskTree(String coreProductCode){
		StringBuffer str = new StringBuffer();
		str.append("<?xml version=\'1.0\' encoding=\'GBK\'?>");
		GeProductMain productMain = productManageService.findProductMainByCoreProductCode(coreProductCode);
		str.append("<tree id=\'0\' text=\'" +
				"\'>");
		if (productMain != null) {
			str.append("<item id=\'" + productMain.getCoreProductCode()
					+ "\' open=\'1\' itemType=\'product\' text=\'"
					+ productMain.getProductName() + "\'>");
			List<GeProductRisk> productRiskList = findGeProductRiskByCoreProductCode(coreProductCode); 
			for (int i = 0; i < productRiskList.size(); i++) {
				GeProductRisk productRisk = productRiskList.get(i);
				str.append("<item id=\'" + productRisk.getSerialNo()
						+ "\' open=\'1\' itemType=\'risk\' text=\'"
						+ productRisk.getProductRiskName() + "\'>");
				QueryRule queryRule = QueryRule.getInstance();
				queryRule.addEqual("geProductRisk.serialNo", productRisk.getSerialNo());
				queryRule.addEqual("geProductMain.coreProductCode",coreProductCode);
				queryRule.addAscOrder("seqIndex");
				List<GeProductDuty>  productDutyList = geProductDutyService.findGeProductDutyByQueryRule(queryRule);
				for (int j = 0; j < productDutyList.size(); j++) {
					GeProductDuty productDuty = productDutyList.get(j);
					str.append("<item id=\'" + productDuty.getSerialNo()
							+ "\' open=\'1\' itemType=\'duty\' text=\'"
							+ productDuty.getProductDutyName() + "(" + productDuty.getGeDutyConfig().getDutyCode() + ")\'>");
					str.append("</item>");
				} 
				str.append("</item>");
			}
			str.append("</item>");
		}
		
		str.append("</tree>");
		return str.toString();
	}
	
	public List<GeProductRisk> findGeProductRiskByCoreProductCode(String coreProductCode) {
		StringBuffer hql = new StringBuffer("from GeProductRisk pcomp  where 1 = 1 ");
		hql.append("and pcomp.geProductMain.coreProductCode = ? ");
		hql.append("order by pcomp.seqIndex");
		List<GeProductRisk>  productRiskList = super.findByHql(hql.toString(), new Object[]{coreProductCode});
		return productRiskList;
	}

	/**
	 * 根据业务领域
	 */
	public List findDutyRiskTree(String coreProductCode, String busAreaId) {
		List<Object> return_List = new ArrayList<Object>();
		
		GeProductMain productMain = productManageService.findProductMainByCoreProductCode(coreProductCode);
		
		QueryRule query = QueryRule.getInstance();
		query.addEqual("geProductMain.coreProductCode", coreProductCode);
		List<GeProductDuty> dutys = geProductDutyService.findGeProductDutyByQueryRule(query);
		List<GeProductRisk> risks = findGeProductRiskByCoreProductCode(coreProductCode);

		StringBuffer str = new StringBuffer();
		str.append("<?xml version=\'1.0\' encoding=\'GBK\'?>");
		str.append("<tree id=\'0\' text=\'" +"\'>");
		if (productMain != null) {
			str.append("<item id=\'#-" + productMain.getCoreProductCode()
					+ "\' open=\'1\' itemType=\'product\' text=\'"
					+ productMain.getProductName() + "\'>");
			
			QueryRule q = QueryRule.getInstance();
			q.addEqual("businessArea", busAreaId);
			
			// 根据领域得到全部险种
			List<GeRiskConfig> productRiskList = geRiskConfigService.findGeRiskConfigByQueryRule(q);
			
			Map map = new HashMap();
			
			// 根据产品id得到险种
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("geProductMain.coreProductCode",coreProductCode);
			List<GeProductRisk> allRisk = this.find(queryRule);
	
			for (GeProductRisk risk : allRisk) {
				
				// 根据产品id和险种id得到责任
				queryRule = QueryRule.getInstance();
				queryRule.addEqual("geProductMain.coreProductCode",coreProductCode);
				queryRule.addEqual("geProductRisk.serialNo",risk.getSerialNo() );
				List<GeProductDuty> alldutys = geProductDutyService.findGeProductDutyByQueryRule(queryRule);

				map.put(risk.getGeRiskConfig().getRiskCode(), alldutys);
			}
			
			for (int i = 0; i < productRiskList.size(); i++) {
				GeRiskConfig productRisk = productRiskList.get(i);
				
				str.append("<item id=\'" + productRisk.getRiskCode()
						+ "\' open=\'1\' text=\'"
						+ productRisk.getRiskName() + "\'>");
				
				// 险种下的责任
				List<GeDutyConfig>  productDutyList = productRisk.getGeDutyConfigs();
				
				for (int j = 0; j < productDutyList.size(); j++) {
					GeDutyConfig productDuty = productDutyList.get(j);
					
					String reskCode = productRisk.getRiskCode();
					List<GeProductDuty> curr = (List<GeProductDuty>)map.get(reskCode);
					if(curr==null)  curr = new ArrayList<GeProductDuty>();
					
					boolean exist = false;
					for (GeProductDuty d : curr) {
						if(d.getGeDutyConfig().getSerialNo().equals(productDuty.getSerialNo())){
							exist = true;
							break;
						}
					}
					
					// 选中
					if(exist){
						str.append("<item checked='1' id=\'" + productRisk.getRiskCode() +"@-" + productDuty.getSerialNo()
								+ "\' open=\'1\' text=\'"
								+ productDuty.getDutyName() + "(" + productDuty.getDutyCode() + ")\'>");
					}else{
						str.append("<item id=\'" + productRisk.getRiskCode() +"@-" + productDuty.getSerialNo()
								+ "\' open=\'1\' text=\'"
								+ productDuty.getDutyName() + "(" + productDuty.getDutyCode() + ")\'>");
					}
					str.append("</item>");
				} 
				str.append("</item>");
			}
			str.append("</item>");
		}
		
		str.append("</tree>");

		return_List.add(str.toString());
		return_List.add(dutys);
		return_List.add(risks);
		return return_List;
	}

	public GeProductRisk findByRiskCode(String code) {
		// TODO Auto-generated method stub
		QueryRule q = QueryRule.getInstance();
		q.addEqual("productRiskCode", code);
		return super.findUnique(q);
	}

	public void addGeProductRisk(GeProductRisk risk) {
		// TODO Auto-generated method stub
		super.save(risk);
	}

	public void deleteGeProductRisk(String code) {
		// TODO Auto-generated method stub
		GeProductRisk entity = findByRiskCode(code);
		super.delete(entity);
	}

	public List<GeProductRisk> findByQueryRule(QueryRule qu) {
		// TODO Auto-generated method stub
		return super.find(qu);
	}

	public void deleteGeProductRisk(GeProductRisk risk) {
		// TODO Auto-generated method stub
		super.delete(risk);
	}

	public void deleteByProductId(String productId) {
		// TODO Auto-generated method stub
		QueryRule q = QueryRule.getInstance();
		q.addEqual("geProductMain.coreProductCode", productId);
		List<GeProductRisk> list = find(q);
		
		for (GeProductRisk geProductRisk : list) {
			delete(geProductRisk);
		}
	}

	public void updateGeProductRisk(GeProductRisk risk) {
		// TODO Auto-generated method stub
		super.update(risk);
	}

	public GeProductRisk findByserialNo(String serialNo) {
		QueryRule q = QueryRule.getInstance();
		q.addEqual("serialNo", serialNo);
		List<GeProductRisk> list = findByQueryRule(q);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}
}
