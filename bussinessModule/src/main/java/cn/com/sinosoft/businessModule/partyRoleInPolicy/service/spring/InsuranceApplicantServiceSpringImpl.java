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
	 * ���Ͷ����
	 * @param insuranceApplicant
	 */
	public void addInsuranceApplicant(InsuranceApplicant insuranceApplicant) {
		super.save(insuranceApplicant);
	}
	
	/**
	 * ����Ͷ����
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
	 * ����Ͷ����
	 * @param queryRule
	 */
	public List<InsuranceApplicant> findInsuranceApplicant(QueryRule queryRule) {
		return super.find(queryRule);
	}
	
	/**
	 * ɾ��Ͷ����
	 * @param lists
	 */	
	public void deleteInsuranceApplicantByKey(String id) {
		super.deleteByPK(id);
	}
	
	/***
	 * ��ҳ��ѯͶ������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findInsuranceApplicantByQueryRule(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/**
	 * ����Ψһ���Բ�ѯͶ������Ϣ
	 * @param propertyMap
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public InsuranceApplicant findInsuranceApplicantByQueryMap(Map propertyMap) {
		return super.findUnique(propertyMap);
	}
	
}
