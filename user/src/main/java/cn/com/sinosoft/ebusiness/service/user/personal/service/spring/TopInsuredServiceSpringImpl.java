package cn.com.sinosoft.ebusiness.service.user.personal.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.TopInsured;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.TopInsuredService;

public class TopInsuredServiceSpringImpl extends GenericDaoHibernate<TopInsured, String> implements TopInsuredService {
	
	public GeUserPersonalService userPersonalService;
	
	@Autowired
	public GeUserPersonalService geUserPersonalService;

	/**
	 * 添加常用被保险人
	 * @param topInsured
	 */
	public void addTopInsured(TopInsured topInsured) {
		super.save(topInsured);
	}
	
	/**
	 * 批量添加常用被保险人
	 * @param topInsured
	 */
	public void addBatchTopInsured(List<TopInsured> topInsureds) {
		super.saveAll(topInsureds);
	}
	
	/**
	 * 批量删除常用被保险人
	 * @param topInsured
	 */
	public void deleteBatchTopInsured(List<TopInsured> topInsureds) {
		super.deleteAll(topInsureds);
	}
	
	public boolean saveInsued(TopInsured insured, GeUserPersonal userPersonal) {
//		TopInsured topInsured = checkTopInsured(insured, userPersonal.getUserID());
//		if (topInsured != null) {
//			return false;
//		}
//		
//		this.evict(topInsured);
		GeUserPersonal user = geUserPersonalService.getUserPersonalByUserId(userPersonal.getUserID());
		insured.setUserPersonal(user);
		
		super.getHibernateTemplate().merge(insured);
//		addTopInsured(insured);
		
		return true;
	}
	
	public boolean saveInsued(TopInsured insured, String userId) {
		TopInsured topInsured = checkTopInsured(insured, userId);
		if (topInsured != null) {
			return false;
		}
		
		this.evict(topInsured);
		GeUserPersonal user = geUserPersonalService.getUserPersonalByUserId(userId);
		if(user == null){
			return false;
		}
		insured.setUserPersonal(user);
		
		super.getHibernateTemplate().merge(insured);
//		addTopInsured(insured);
		
		return true;
	}
	
	/**
	 * 更改常用被保险人
	 * @param topInsured
	 */
	public void updateTopInsured(TopInsured topInsured) {
		TopInsured update = super.get(topInsured.getSerialNo());
		List<String> ignorePropertyList = new ArrayList<String>();
		ignorePropertyList.add("createTime");
		ignorePropertyList.add("acceptSMS");
		ignorePropertyList.add("customFlag");
		ignorePropertyList.add("smokerYears");
		ignorePropertyList.add("drinkerAmountByYear");
		ignorePropertyList.add("drinkerStatus");
		ignorePropertyList.add("drinkerYears");
		ignorePropertyList.add("smokerAmountByYear");
		ignorePropertyList.add("smokerStatus");
		ignorePropertyList.add("maritalStatus");
		String[] ignoreProperties = new String[ignorePropertyList.size()];
		BeanUtils.copyProperties(topInsured, update, ignorePropertyList.toArray(ignoreProperties));
		update.setUpdateTime(new Date());
		super.update(update);
	}
	/**
	 * 查找常用被保险人
	 * @param queryRule
	 */
	public List<TopInsured> findTopInsured(QueryRule queryRule) {
		return super.find(queryRule);
	}
	
	/**
	 * 删除常用被保险人
	 * @param lists
	 */
	public void deleteTopInsuredByKey(String id) {
		super.deleteByPK(id);
	}

	@Override
	public List<TopInsured> getInsuredDetails(String userID) {
		List<TopInsured> result = new ArrayList<TopInsured>();
		
		try {
			String hql = "from " + TopInsured.class.getName() + " where USERPERSONAL_ID = ?";
			
			result = findByHql(hql, userID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void setUserPersonalService(GeUserPersonalService userPersonalService) {
		this.userPersonalService = userPersonalService;
	}

	@Override
	public TopInsured getTopInsuredById(String id) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("serialNo", id);
		
		List<TopInsured> list = super.find(queryRule);
		
		return list.isEmpty() ? null : list.get(0);
	}

	/**
	 * 校验是否为同一被保险人
	 */
	@Override
	public TopInsured checkTopInsured(TopInsured insured, String userId) {
		String serialNo = insured.getSerialNo();
		String name = insured.getFullName();
		Integer idType = insured.getIdType();
		String idNum = insured.getIdNumber();
		List<TopInsured> list = null;
		String hql = "";
		if (serialNo != null && serialNo.trim().length() != 0) {
			hql = "from " + TopInsured.class.getName() + " where fullName = ? and idType = ? and idNumber = ? and userPersonal_id = ? and serialNo != ?";
			list = findByHql(hql, name, idType, idNum, userId, serialNo);
		} else {
			hql = "from " + TopInsured.class.getName() + " where fullName = ? and idType = ? and idNumber = ? and userPersonal_id = ?";
			list = findByHql(hql, name, idType, idNum, userId);
		}
		
		return list.isEmpty() ? null : list.get(0);
	}

	/**
	 * 更新之前的默认被保人标志为false
	 */
	@Override
	public void modifyOtherInsureds(String seriaNo, String userId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("userPersonal_id", userId);
		queryRule.addNotEqual("seriaNo", seriaNo);
		queryRule.addEqual("defaultInsured", true);
		
		List<TopInsured> list = this.find(queryRule);
		if (list.isEmpty())
			return;
		
		TopInsured oldDefaultTopInsured = list.get(0);
		oldDefaultTopInsured.setDefaultInsured(false);
		
		this.updateTopInsured(oldDefaultTopInsured);
	}
	
	/**
	 * 分页查询常用被保人
	 * @param queryRule	查询规则
	 * @param pageNo	第几页
	 * @param pageSize	一页行数
	 * @return Page
	 */
	public Page findTopInsured(QueryRule queryRule, int pageNo, int pageSize){
		return super.find(queryRule, pageNo, pageSize);
	}
}
