package cn.com.sinosoft.businessModule.partyRoleInPolicy.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.Insured;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.service.facade.InsuredService;

public class InsuredServiceSpringImpl extends GenericDaoHibernate<Insured, String> implements InsuredService {

	/**
	 * 添加被保险人
	 * @param insured
	 */
	public void addInsured(Insured insured) {
		super.save(insured);
	}
	
	/**
	 * 批量添加被保险人
	 * @param insured
	 */
	public void addBatchInsured(List<Insured> insured) {
		super.saveAll(insured);
	}
	
	/**
	 * 批量删除被保险人
	 * @param insured
	 */
	public void deleteBatchInsured(List<Insured> insured) {
		super.deleteAll(insured);
	}
	
	/**
	 * 更改被保险人
	 * @param insured
	 */
	public void updateInsured(Insured insured) {
		Insured update = super.get(insured.getSerialNo());
		List<String> ignorePropertyList = new ArrayList<String>();
		ignorePropertyList.add("createTime");
		String[] ignoreProperties = new String[ignorePropertyList.size()];
		BeanUtils.copyProperties(insured, update, ignorePropertyList.toArray(ignoreProperties));
		update.setUpdateTime(new Date());
		super.update(update);
	}
	/**
	 * 查找被保险人
	 * @param queryRule
	 */
	public List<Insured> findInsured(QueryRule queryRule) {
		return super.find(queryRule);
	}
	
	/**
	 * 删除被保险人
	 * @param lists
	 */
	public void deleteInsuredByKey(String id) {
		super.deleteByPK(id);
	}
}
