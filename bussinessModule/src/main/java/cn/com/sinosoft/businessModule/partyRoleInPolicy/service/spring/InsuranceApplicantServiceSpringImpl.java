package cn.com.sinosoft.businessModule.partyRoleInPolicy.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.InsuranceApplicant;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.service.facade.InsuranceApplicantService;

public class InsuranceApplicantServiceSpringImpl extends GenericDaoHibernate<InsuranceApplicant, String> implements InsuranceApplicantService {

	/**
	 * 添加投保人
	 * @param insuranceApplicant
	 */
	public void addInsuranceApplicant(InsuranceApplicant insuranceApplicant) {
		super.save(insuranceApplicant);
	}
	
	/**
	 * 更改投保人
	 * @param insuranceApplicant
	 */
	public void updateInsuranceApplicant(InsuranceApplicant insuranceApplicant) {
		InsuranceApplicant update = super.get(insuranceApplicant.getSerialNo());
		List<String> ignorePropertyList = new ArrayList<String>();
		ignorePropertyList.add("createTime");
		String[] ignoreProperties = new String[ignorePropertyList.size()];
		BeanUtils.copyProperties(insuranceApplicant, update, ignorePropertyList.toArray(ignoreProperties));
		update.setUpdateTime(new Date());
		super.update(update);
	}
	
	/**
	 * 查找投保人
	 * @param queryRule
	 */
	public List<InsuranceApplicant> findInsuranceApplicant(QueryRule queryRule) {
		return super.find(queryRule);
	}
	
	/**
	 * 删除投保人
	 * @param lists
	 */	
	public void deleteInsuranceApplicantByKey(String id) {
		super.deleteByPK(id);
	}
	
	/***
	 * 分页查询投保人信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findInsuranceApplicantByQueryRule(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/**
	 * 根据唯一属性查询投保人信息
	 * @param propertyMap
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public InsuranceApplicant findInsuranceApplicantByQueryMap(Map propertyMap) {
		return super.findUnique(propertyMap);
	}
	
}
