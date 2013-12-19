package cn.com.sinosoft.businessModule.bankAccount.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.common.QueryRule.Rule;
import ins.framework.dao.GenericDaoHibernate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.sinosoft.businessModule.bankAccount.domain.FundsTransfer;
import cn.com.sinosoft.businessModule.bankAccount.service.facade.FundsTransferService;
import cn.com.sinosoft.businessModule.enums.dictionary.FundsTransferStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.PolicyStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.TransferType;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.util.reflect.FieldUtils;


public class FundsTransferServiceSpringImpl extends GenericDaoHibernate<FundsTransfer, String> implements FundsTransferService  {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/***
	 * 添加资金划拨
	 * @param frundsTransfer
	 */
	@Override
	public void addFundsTransfer(FundsTransfer frundsTransfer) {
		super.save(frundsTransfer);
	}
	

	/***
	 * 分页查询资金划拨信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page findFundsTransferByQueryRule(QueryRule queryRule, int pageNo, int pageSize) {
		StringBuffer hql = new StringBuffer("from " + FundsTransfer.class.getName() + " where 1 = 1 ");
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
		return super.findByHql(hql.toString(), pageNo, pageSize, objectList.toArray());
	}

	/**
	 * 根据资金划拨订单号更新信息
	 * @param fundsTransfer 资金划拨信息
	 * @param copyProperties 需要拷贝的属性
	 */
	@Override
	public void updateFundsTransferByFundsTransferOrderNumber(FundsTransfer fundsTransfer, List<String> copyProperties) {
		FundsTransfer update = super.findUnique("fundsTransferOrderNumber", fundsTransfer.getFundsTransferOrderNumber());
		
		boolean updateFlag = true;
			
		if (FundsTransferStatus.TRANSFER_AUDIT_SUCCESS.getValue().equals(fundsTransfer.getFundsTransferStatus())) {
			if (FundsTransferStatus.TRANSFER_AUDIT_SUCCESS.getValue().equals(update.getFundsTransferStatus()) ||
					FundsTransferStatus.HANLDER_TRANSFER.getValue().equals(update.getFundsTransferStatus()) ||
					FundsTransferStatus.HANLDER_TRANSFER_SUCCESS.getValue().equals(update.getFundsTransferStatus()) ||
					FundsTransferStatus.HANLDER_TRANSFER_FAILURE.getValue().equals(update.getFundsTransferStatus()) ||
					FundsTransferStatus.TRANSFER_AUDIT_FAILURE.getValue().equals(update.getFundsTransferStatus())) {
				updateFlag = true;
			} else {
				updateFlag = false;
			}
		} else if (FundsTransferStatus.TRANSFER_AUDIT_FAILURE.getValue().equals(fundsTransfer.getFundsTransferStatus())) {
			if (FundsTransferStatus.HANLDER_TRANSFER.getValue().equals(update.getFundsTransferStatus()) ||
					FundsTransferStatus.HANLDER_TRANSFER_SUCCESS.getValue().equals(update.getFundsTransferStatus()) ||
					FundsTransferStatus.HANLDER_TRANSFER_FAILURE.getValue().equals(update.getFundsTransferStatus()) ||
					FundsTransferStatus.TRANSFER_AUDIT_FAILURE.getValue().equals(update.getFundsTransferStatus())) {
				updateFlag = true;
			} else {
				updateFlag = false;
			}
		}
		
		List<String> allDeclaredField = FieldUtils.getAllDeclaredFieldName(FundsTransfer.class);
		allDeclaredField.removeAll(copyProperties);
		String[] ignoreProperties = new String[allDeclaredField.size()];
		BeanUtils.copyProperties(fundsTransfer, update, allDeclaredField.toArray(ignoreProperties));
		update.setUpdateTime(new Date());
		if (TransferType.SURRENDER.getValue().equals(fundsTransfer.getTransferType()) || 
				TransferType.ALL_RECIPIENTS.getValue().equals(fundsTransfer.getTransferType())) {
			if (FundsTransferStatus.TRANSFER_NOTIFY_CORE_SUCCESS.getValue().equals(fundsTransfer.getFundsTransferStatus())){
				InsurancePolicy insurancePolicy = update.getInsurancePolicy();
				insurancePolicy.setEnumPolicyStatus(PolicyStatus.POLICY_INVALID);
				insurancePolicy.setPolicyStatusName(PolicyStatus.POLICY_INVALID.getDataName());
				insurancePolicy.setPolicyStatusDesc(PolicyStatus.POLICY_INVALID.getDataName());
				insurancePolicy.setUpdateTime(new Date());
			}
		}
		
		if (updateFlag) {
			super.update(update);
		}
		
	}
	
	/**
	 * 批量更新请求核心处理资金划拨
	 * @param insurancePolicyAll
	 * @param syncStatus
	 */
	public void updateAllRequestCoreHandlerTransfer(final List fundsTransferAll, final FundsTransferStatus fundsTransferStatus) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ");
		sql.append("FUNDSTRANSFER ");
		sql.append("SET FUNDSTRANSFERSTATUS = ? ");
		sql.append(", ");
		sql.append("FUNDSTRANSFERSTATUSDESC =  ? ");
		sql.append(", ");
		sql.append("TRANSFERTIME = ? ");
		sql.append(", ");
		sql.append("UPDATETIME = ? ");
		sql.append("WHERE ");
		sql.append("SERIALNO = ? ");
		jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				FundsTransfer fundsTransfer = (FundsTransfer) fundsTransferAll.get(i);
				ps.setInt(1, fundsTransferStatus.getValue());
				ps.setString(2, fundsTransferStatus.getDataName());
				Timestamp transferTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
				ps.setTimestamp(3, transferTime);
				ps.setTimestamp(4, transferTime);
				ps.setString(5, fundsTransfer.getSerialNo());
			}

			@Override
			public int getBatchSize() {
				return fundsTransferAll.size();
			}
			
		});
	}
	
	/***
	 * 根据属性查询资金划拨信息
	 * @param propertyMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public FundsTransfer findFundsTransferByQueryMap(Map propertyMap){
		super.setOptimizeFind(true);
		return super.findUnique(propertyMap);
	}
	
	/***
	 * 根据主键查询资金划拨信息
	 * @param serialNo 逻辑主键序号
	 * @return
	 */
	public FundsTransfer findFundsTransferBySerialNo(String serialNo){
		return super.get(serialNo);
	}
	
	/**
	 * 根据资金划拨订单号查询资金划拨信息
	 * @param fundsTransferOrderNumber
	 * @return
	 */
	public FundsTransfer findFundsTransferByfundsTransferOrderNumber(String fundsTransferOrderNumber){
		return super.findUnique("fundsTransferOrderNumber", fundsTransferOrderNumber);
	}
	
}
