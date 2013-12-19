package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeDutyConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeRiskConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeDutyConfigService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeRiskConfigService;

public class GeRiskConfigServiceSpringImpl extends
GenericDaoHibernate<GeRiskConfig, String> implements GeRiskConfigService  {
	
	@Autowired
	private GeDutyConfigService geDutyConfigService;
	/**
	 * 添加险种
	 * @param geRiskConfig
	 */
	public void addGeRiskConfig(GeRiskConfig geRiskConfig) {
		super.save(geRiskConfig);
	}
	
	/**
	 * 更新险种
	 * @param geRiskConfig
	 */
	public void updateGeRiskConfig(GeRiskConfig geRiskConfig) {
		try {
			GeRiskConfig update = super.findUnique("riskCode", geRiskConfig.getRiskCode());
			List<GeDutyConfig> geDutyConfigList = geRiskConfig.getGeDutyConfigs();
			List<GeDutyConfig> existDutyConfigList = update.getGeDutyConfigs();
			
			for (GeDutyConfig existDutyConfig:existDutyConfigList) {
				
				boolean existFlag = false;
				
				for (GeDutyConfig geDutyConfig:geDutyConfigList) {
					if (StringUtils.isNotBlank(geDutyConfig.getSerialNo())) {
						if (existDutyConfig.getSerialNo().equals(geDutyConfig.getSerialNo())) {
							existFlag = true;
							break;
						}
					}
				}
				
				if (!existFlag) {
					geDutyConfigService.delete(existDutyConfig);
				}
				
			}
			
			for (GeDutyConfig geDutyConfig:geDutyConfigList) {
				geDutyConfig.setBusinessArea(geRiskConfig.getBusinessArea());
				geDutyConfig.setGeRiskConfig(geRiskConfig);
				geDutyConfig.setOperatorID(geRiskConfig.getOperatorID());
				if (StringUtils.isNotBlank(geDutyConfig.getSerialNo())) { 
					geDutyConfigService.updateDutyConfig(geDutyConfig);
				} else {
					geDutyConfig.setCreateTime(new Date());
					geDutyConfigService.addDutyKindConfig(geDutyConfig);
				}
				System.out.println(geDutyConfig.getSerialNo());
			}
			BeanUtils.copyProperties(geRiskConfig, update, new String[]{"createTime"});
			update.getGeDutyConfigs().clear();
			super.update(update);
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据险种代码查询险种
	 * @param riskCode
	 * @return
	 */
	public GeRiskConfig  findGeRiskConfigByRiskCode(String riskCode) {
		return super.get(riskCode);
	}
	/**
	 * 根据条件查询险种
	 * @param queryRule
	 * @return
	 */
	public List<GeRiskConfig>  findGeRiskConfigByQueryRule(QueryRule queryRule) {
		List<GeRiskConfig> riskConfigList = super.find(queryRule);
		return riskConfigList;
	}
	
	/**
	 * 分页形式查询险种
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeRiskConfig(QueryRule queryRule ,int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}
}
