package cn.com.sinosoft.ebusiness.service.user.personal.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.TopInsured;

public interface TopInsuredService extends GenericDao<TopInsured, String>{

	public abstract void addTopInsured(TopInsured topInsured);
	
	public abstract void addBatchTopInsured(List<TopInsured> topInsureds);
	
	public abstract void deleteBatchTopInsured(List<TopInsured> topInsureds);
	
	public abstract void updateTopInsured(TopInsured topInsured);
	
	public abstract List<TopInsured> findTopInsured(QueryRule queryRule);
	
	public abstract void deleteTopInsuredByKey(String id);

	public abstract List<TopInsured> getInsuredDetails(String userID);

	public abstract TopInsured getTopInsuredById(String id);

	public abstract TopInsured checkTopInsured(TopInsured insured, String userID);

	public abstract void modifyOtherInsureds(String seriaNo, String userId);
	
	public Page findTopInsured(QueryRule queryRule, int pageNo, int pageSize);

	public boolean saveInsued(TopInsured insured, GeUserPersonal userPersonal);
	
	public boolean saveInsued(TopInsured insured, String userId);
}
