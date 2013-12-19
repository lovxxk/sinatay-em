package cn.com.sinosoft.businessModule.policy.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.common.QueryRule.Rule;
import ins.framework.dao.GenericDaoHibernate;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.com.sinosoft.businessModule.bankAccount.domain.PaymentAccount;
import cn.com.sinosoft.businessModule.enums.dictionary.ElectronicPolicyStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.PolicyStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.SyncStatus;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.InsuranceApplicant;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyService;
import cn.com.sinosoft.util.encode.GeneratePolicySerialNumberUtils;
import cn.com.sinosoft.util.reflect.FieldUtils;
import cn.com.sinosoft.util.spring.BeanUtils;
import cn.com.sinosoft.util.string.StringUtils;
import cn.com.sinosoft.util.time.DateUtils;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class InsurancePolicyServiceSpringImpl extends GenericDaoHibernate<InsurancePolicy, String> implements InsurancePolicyService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 添加投保单
	 * @param insurancePolicy
	 */
	public String addInsurancePolicy(InsurancePolicy insurancePolicy) {
		super.save(insurancePolicy);
		return insurancePolicy.getSerialNo();
	}
	
	/**
	 * 创建投保单
	 * @param insurancePolicy
	 */
	public InsurancePolicy createInsurancePolicy(InsurancePolicy insurancePolicy) {
		super.save(insurancePolicy);
		return findInsurancePolicyBySerialNo(insurancePolicy.getSerialNo());
	}

	/***
	 * 根据属性查询投保单
	 * @param propertyMap
	 * @return
	 */
	public InsurancePolicy findInsurancePolicyByQueryMap(Map propertyMap) {
		super.setOptimizeFind(true);
		return super.findUnique(propertyMap);
	}
	
	/***
	 * 根据主键查询保单信息
	 * @param serialNo 逻辑主键序号
	 * @return
	 */
	public InsurancePolicy findInsurancePolicyBySerialNo(String serialNo) {
		return super.get(serialNo);
	}
	
	/***
	 * 根据保单号查询保单信息
	 * @param serialNo 逻辑主键序号
	 * @return
	 */
	public InsurancePolicy findInsurancePolicyByPolicySerialNumber(String policySerialNumber) {
		return super.findUnique("policySerialNumber", policySerialNumber);
	}
	
	/***
	 * 分页查询投保单信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findInsurancePolicyByQueryRule(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}

	
	/**
	 * 更新投保单
	 * @param insurancePolicy
	 */
	public void updateInsurancePolicy(InsurancePolicy insurancePolicy) {
		InsurancePolicy update = super.get(insurancePolicy.getSerialNo());
		List<String> ignorePropertyList = new ArrayList<String>();
		ignorePropertyList.add("createTime");
		String[] ignoreProperties = new String[ignorePropertyList.size()];
		
		BeanUtils.copyProperties(insurancePolicy, update, ignorePropertyList.toArray(ignoreProperties));
		update.setUpdateTime(new Date());
		super.update(update);
	}
	
	/**
	 * 批量更新同步开始保单同步状态
	 * @param insurancePolicyAll
	 * @param syncStatus
	 */
	public void updateAllGenerationElectronic(final List insurancePolicyAll, final ElectronicPolicyStatus electronicPolicyStatus) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ");
		sql.append("INSURANCEPOLICY ");
		sql.append("set GENERATEELECTRONICPOLICYSTATUS = ? ");
		sql.append(", ");
		sql.append("UPDATETIME = ? ");
		sql.append("WHERE ");
		sql.append("SERIALNO = ? ");
		jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				InsurancePolicy insurancePolicy = (InsurancePolicy) insurancePolicyAll.get(i);
				ps.setInt(1, electronicPolicyStatus.getValue());
				Timestamp generationTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
				ps.setTimestamp(2, generationTime);
				ps.setString(3, insurancePolicy.getSerialNo());
			}

			@Override
			public int getBatchSize() {
				return insurancePolicyAll.size();
			}
			
		});
	}
	
	/**
	 * 批量跟新提醒次数
	 * @param insurancePolicyAll
	 */
	public void updateAllRemindCount(final List insurancePolicyAll) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ");
		sql.append("INSURANCEPOLICY ");
		sql.append("set REMINDCOUNT = ? ");
//		sql.append(", ");
//		sql.append("UPDATETIME = ? ");
		sql.append("WHERE ");
		sql.append("SERIALNO = ? ");
		jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				InsurancePolicy insurancePolicy = (InsurancePolicy) insurancePolicyAll.get(i);
				System.out.println(insurancePolicy.getSerialNo());
				ps.setInt(1, (insurancePolicy.getRemindCount()==null?0:insurancePolicy.getRemindCount())+1);
//				Timestamp generationTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
//				ps.setTimestamp(2, generationTime);
				ps.setString(2, insurancePolicy.getSerialNo());
			}

			@Override
			public int getBatchSize() {
				return insurancePolicyAll.size();
			}
			
		});
	}
	
	
	/**
	 * 批量更新同步开始保单同步状态
	 * @param insurancePolicyAll
	 * @param syncStatus
	 */
	public void updateGenerationElectronic(final InsurancePolicy insurancePolicy) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ");
		sql.append("INSURANCEPOLICY ");
		sql.append("set GENERATEELECTRONICPOLICYSTATUS = ? ");
		sql.append(", ");
		sql.append("UPDATETIME = ? ");
		sql.append("WHERE ");
		sql.append("SERIALNO = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps)
					throws SQLException {
				ps.setInt(1, insurancePolicy.getGenerateElectronicPolicyStatus());
				Timestamp generationTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
				ps.setTimestamp(2, generationTime);
				ps.setString(3, insurancePolicy.getSerialNo());
			}
		});
	}
	
	
	/**
	 * 批量更新同步开始保单同步状态
	 * @param insurancePolicyAll
	 * @param syncStatus
	 */
	public void updateAllSyncStartInsurancePolicy(final List insurancePolicyAll, final SyncStatus syncStatus) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ");
		sql.append("INSURANCEPOLICY ");
		sql.append("set SYNCSTATUS = ? ");
		sql.append(", ");
		sql.append("SYNCSTATUSDESC =  ? ");
		sql.append(", ");
		sql.append("SYNCSTARTTIME = ? ");
		sql.append(", ");
		sql.append("UPDATETIME = ? ");
		sql.append("WHERE ");
		sql.append("SERIALNO = ? ");
		jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				InsurancePolicy insurancePolicy = (InsurancePolicy) insurancePolicyAll.get(i);
				ps.setInt(1, syncStatus.getValue());
				ps.setString(2, syncStatus.getDataName());
				Timestamp syncStartTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
				ps.setTimestamp(3, syncStartTime);
				ps.setTimestamp(4, syncStartTime);
				ps.setString(5, insurancePolicy.getSerialNo());
			}

			@Override
			public int getBatchSize() {
				return insurancePolicyAll.size();
			}
			
		});
	}
	
	/**
	 * 根据保单号更新同步结束状态
	 * @param insurancePolicyAll
	 * @param syncStatus
	 */
	public void updateSyncEndByPolicySerialNumber(final InsurancePolicy insurancePolicy) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ");
		sql.append("INSURANCEPOLICY ");
		sql.append("SET SYNCSTATUS = ? ");
		sql.append(", ");
		sql.append("SYNCSTATUSDESC =  ? ");
		sql.append(", ");
		sql.append("SYNCENDTIME = ? ");
		sql.append(", ");
		sql.append("UPDATETIME = ? ");
		sql.append("WHERE ");
		sql.append("POLICYSERIALNUMBER = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps)
					throws SQLException {
				ps.setInt(1, insurancePolicy.getSyncStatus());
				ps.setString(2, insurancePolicy.getSyncStatusDesc());
				Timestamp syncEndTime = new Timestamp(insurancePolicy.getSyncEndTime().getTime());
				ps.setTimestamp(3, syncEndTime);
				ps.setTimestamp(4, syncEndTime);
				ps.setString(5, insurancePolicy.getPolicySerialNumber());
			}
			
		});
	}
	
	/**
	 * 更新投保单
	 * @param insurancePolicy
	 */
	
	public void updateInsurancePolicyByPolicySerialNo(InsurancePolicy insurancePolicy, List<String> copyProperties) {
		InsurancePolicy update = super.findUnique("SERIALNO", insurancePolicy.getSerialNo());
		List<String> allDeclaredField = FieldUtils.getAllDeclaredFieldName(InsurancePolicy.class);
		if (insurancePolicy.getPaymentAccount() != null) {
			allDeclaredField.addAll(FieldUtils.getAllDeclaredFieldName(PaymentAccount.class));
		}
		allDeclaredField.removeAll(copyProperties);
		String[] ignoreProperties = new String[allDeclaredField.size()];
		allDeclaredField.toArray(ignoreProperties);
		cn.com.sinosoft.util.spring.BeanUtils.copyProperties(insurancePolicy, update, allDeclaredField.toArray(ignoreProperties));
//		if (insurancePolicy.getPaymentAccount() != null && update.getPaymentAccount() != null) {
//			update.getPaymentAccount().setUpdateTime(new Date());
//			cn.com.sinosoft.util.spring.BeanUtils.copyProperties(insurancePolicy.getPaymentAccount(), update.getPaymentAccount(), allDeclaredField.toArray(ignoreProperties));
//		}else if (insurancePolicy.getPaymentAccount() != null && update.getPaymentAccount() == null) {
//			PaymentAccount paymentAccount = new PaymentAccount();
//			cn.com.sinosoft.util.spring.BeanUtils.copyProperties(insurancePolicy.getPaymentAccount(), paymentAccount);
//			paymentAccount.addInsurancePolicy(update);
//			update.setPaymentAccount(paymentAccount);
//		}
		update.setUpdateTime(new Date());
		super.update(update);
	}
	
	public List<InsurancePolicy> findInsurancePolicyByQueryRule(QueryRule queryRule) {
		StringBuffer hql = new StringBuffer("from " + InsurancePolicy.class.getName() + " where 1 = 1 ");
		List<Rule> ruleList = queryRule.getRuleList();
		List objectList = new ArrayList();
		for (int i = 0; i < ruleList.size(); i++) {
			Rule rule = ruleList.get(i);
			switch (rule.getType()) {
			
				case 1 :
					hql.append("and " + rule.getPropertyName() + " like ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 2 :
					Object ruleObj = rule.getValues()[0];
					if (ruleObj instanceof List) {
						List ruleObjList = (List)ruleObj;
						
						hql.append("and " + rule.getPropertyName() + " in (");
						for (int j = 0; j < ruleObjList.size(); j++) {
							if (j == 0) {
								hql.append("?");
							} else {
								hql.append(", ?");
							}
						}
						hql.append(") ");
						objectList.addAll(ruleObjList);
					}
					
					break;
				case 3 :
					if (rule.getValues().length < 2) {
						break;
					}
					hql.append("and " + rule.getPropertyName() + " between ? and ? ");
					objectList.add(rule.getValues()[0]);
					objectList.add(rule.getValues()[1]);
					break;
				case 4 :
					hql.append("and " + rule.getPropertyName() + " = ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 5 :
					hql.append("and " + rule.getPropertyName() + " <> ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 6 :
					hql.append("and " + rule.getPropertyName() + " > ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 7 :
					hql.append("and " + rule.getPropertyName() + " >= ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 8 :
					hql.append("and " + rule.getPropertyName() + " < ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 9 :
					hql.append("and " + rule.getPropertyName() + " <= ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 11:
					hql.append("and " + rule.getPropertyName() + " is null ");
			        break;
				case 12:
					hql.append("and " + rule.getPropertyName() + " is not null ");
			        break;
			}
		}
		List<org.hibernate.criterion.Order> orderList = super.getOrderFromQueryRule(queryRule);
		if (orderList.size() > 0 ) {
			hql.append("order by ");
			for (int i = 0; i < orderList.size(); i++ ) {
				org.hibernate.criterion.Order order = orderList.get(i);
				if (i  == 0) {
					hql.append(order.toString());
				} else {
					hql.append(", " + order.toString());
				}
				
			}	
		}
		
		return super.findByHql(hql.toString(), objectList.toArray());
	}
	
	/**
	 * 当天累计保费
	 * @param insurancePolicy
	 * @return
	 */
	public BigDecimal findCurrentDayTotalPreminum(InsurancePolicy insurancePolicy){
		InsuranceApplicant insuranceApplicant = insurancePolicy.getInsuranceApplicant();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("SUM(PL.GROSSPREMIUM) ");
		sql.append("FROM ");
		sql.append("INSURANCEPOLICY PL ");
		sql.append("INNER JOIN ");
		sql.append("PARTYROLEINPOLICY APPLICANT ");
		sql.append("ON ");
		sql.append("PL.SERIALNO = APPLICANT.POLICYSERIALNO ");
		sql.append("AND ");
		sql.append("APPLICANT.ROLEKIND = 'InsuranceApplicant' ");
		sql.append("WHERE ");
		sql.append("APPLICANT.FULLNAME = ? ");
		sql.append("AND ");
		sql.append("APPLICANT.GENDER = ? ");
		sql.append("AND ");
		sql.append("APPLICANT.IDTYPE = ? ");
		sql.append("AND ");
		sql.append("APPLICANT.IDNUMBER = ? ");
		sql.append("AND ");
		sql.append("APPLICANT.BIRTHDATE = ? ");
		sql.append("AND ");
		sql.append("PL.POLICYSTATUS = ? ");
		sql.append("AND ");
		sql.append("PL.SUBMISSIONDATE = ? ");
		List param = new ArrayList();
		param.add(insuranceApplicant.getFullName());
		param.add(insuranceApplicant.getGender());
		param.add(insuranceApplicant.getIdType());
		param.add(insuranceApplicant.getIdNumber());
		param.add(insuranceApplicant.getBirthDate());
		param.add(PolicyStatus.POLICY_SUCC.getValue());
		param.add(DateUtils.startTimeDate(new Date()));
		System.out.println(sql.toString());
		int[] paramTypes = new int[] {Types.VARCHAR,Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.DATE, Types.INTEGER, Types.DATE};
		BigDecimal currentDayTotalPreminum = jdbcTemplate.queryForObject(sql.toString(), param.toArray(), paramTypes, BigDecimal.class);
		return currentDayTotalPreminum;
	}

	/**
	 * 生成保单号
	 */
	public String generatePolicySerialNumber() {
		return GeneratePolicySerialNumberUtils.generatePolicySerialNumber(getSequence("POLICYSERIALNUMBERSEQUENCE"), 1688);
	}
	
	/**
	 * 
	 * @param sequenceName
	 * 
	 */
	public Long getSequence(String sequenceName)  {
		return getSequence(sequenceName, null);
	}
	 
	/**
	 * 
	 * @param sequenceName
	 * @param tableName
	 * @return
	 * 保单号生成方法，对应的Sequence要建立
	 */
	public Long getSequence(final String sequenceName, final String tableName)  {
		Long seq = (Long)getHibernateTemplate().execute(new HibernateCallback() {
				public Long doInHibernate(Session session) throws HibernateException {
					StringBuffer sqlQuery = new StringBuffer();
					sqlQuery.append("select ");
					sqlQuery.append(sequenceName);
					sqlQuery.append(".nextval ");
					sqlQuery.append("from ");
					if (StringUtils.isNotBlank(tableName)) {
						sqlQuery.append(tableName);	
					} else {
						sqlQuery.append("dual");
					}
					SQLQuery query = session.createSQLQuery(sqlQuery.toString());
			        List list = query.list();
			        return Long.valueOf("" + list.get(0));
				}
			});
	  
		return seq;
	}
	
	public void updateInsurancePolicyByMerchantOrderNumber(InsurancePolicy insurancePolicy) {
		InsurancePolicy update = super.findUnique("merchantOrderNumber", insurancePolicy.getMerchantOrderNumber()==null?"0":insurancePolicy.getMerchantOrderNumber());
		if(update != null){
			updateInsurancePolicy(update);
		}else{
			addInsurancePolicy(insurancePolicy);
		}
		
	}

	/**
	 * 删除投保单
	 * @param insurancePolicy
	 */
	public void deleteInsurancePolicy(InsurancePolicy insurancePolicy) {
		try {
			super.delete(insurancePolicy);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 根据多投保单序号查询投保单
	 * @param proposalSIDs 投保单序号由','连接
	 * @return
	 */
	public List<InsurancePolicy> findInsurancePoliciesByIds(String proposalSIDs) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addIn("SERIALNO", (Object[])proposalSIDs.split(","));
		return super.find(queryRule);
	}
	
	/**
	 * 承保后更新保单号
	 */
	public void updatePolicySerialNumber(final InsurancePolicy insurancePolicy) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ");
		sql.append("INSURANCEPOLICY ");
		sql.append("set POLICYSERIALNUMBER = ? ");
		sql.append(", ");
		sql.append("POLICYSTATUS = ? ");
		sql.append(", ");
		sql.append("PRECHECKDATE = ? ");
		sql.append(", ");
		sql.append("REASON = ? ");
		sql.append(", ");
		sql.append("UPDATETIME = ? ");
		sql.append("WHERE ");
		sql.append("SERIALNO = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps)
					throws SQLException {
				ps.setString(1, insurancePolicy.getPolicySerialNumber());
				ps.setInt(2, insurancePolicy.getPolicyStatus());
				ps.setString(3, insurancePolicy.getPrecheckDate());
				ps.setString(4, insurancePolicy.getReason());
				Timestamp generationTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
				ps.setTimestamp(5, generationTime);
				ps.setString(6, insurancePolicy.getSerialNo());
			}
		});
	}

	/**
	 * 根据试算单查询保单对象
	 */
	@Override
	public InsurancePolicy getInsurancePolicyByQuoteNo(String quoteNo) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("quoteNo", quoteNo);
		
		List list = this.find(queryRule);
		
		return list.isEmpty() ? null : (InsurancePolicy)list.get(0);
	}
}
