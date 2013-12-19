/**
 * Copyright (c) 2005-2009 sinosoft.com.cn
 *
 */
package cn.com.sinosoft.ebusiness.sys.exception.aspect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import cn.com.sinosoft.util.queue.BlockingConsumer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * ��Queue�е�eventд�����ݿ������������.
 * 
 * ��ʱ�����Ķ�ȡQueue�е��¼�,�ﵽ�������޺�ʹ��Jdbc����д��ģʽ. ���軻Ϊ��ʱ��ȡģʽ,�̳���PeriodConsumer�ԼӸ��켴��.
 * 
 * @see BlockingConsumer
 * 
 */
public class JdbcExceptionWriter extends BlockingConsumer {

	protected String sql;
	protected String appName;
	protected int batchSize = 10;

	protected List<ExceptionEvent> eventsBuffer = Lists.newArrayList();
	protected SimpleJdbcTemplate jdbcTemplate;
	protected TransactionTemplate transactionTemplate;

	/**
	 * ��Named Parameter��insert sql.
	 * 
	 * Named Parameter�����Ƽ�AppenderUtils�еĳ�������.
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * ������ȡ�¼�����, Ĭ��Ϊ10.
	 */
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	/**
	 * ����ע���MonitorDataSource����jdbcTemplate.
	 */
	@Resource
	public void setMonitorDataSource(DataSource monitorDataSource) {
		jdbcTemplate = new SimpleJdbcTemplate(monitorDataSource);
	}

	/**
	 * ����ע���MonitorTransactionManager����transactionTemplate.
	 */
	@Resource
	public void setMonitorTransactionManager(PlatformTransactionManager monitorTransactionManager) {
		transactionTemplate = new TransactionTemplate(monitorTransactionManager);
	}

	/**
	 * ��Ϣ������,����Ϣ����buffer,��buffer�ﵽbatchSizeʱִ���������º���.
	 */
	@Override
	protected void processMessage(Object message) {
		ExceptionEvent event = (ExceptionEvent) message;
		eventsBuffer.add(event);
		if (logger.isDebugEnabled()) {
			logger.debug("get event: {}", new ExceptionEventWrapper(event).convertToString());
		}

		// �ѵ���BufferSize��ִ�������������
		if (eventsBuffer.size() >= batchSize) {
			updateBatch();
		}
	}

	/**
	 * ��Buffer�е��¼��б������������ݿ�.
	 */
	@SuppressWarnings("rawtypes")
	public void updateBatch() {
		try {
			// �����¼��б�, ת��Ϊjdbc���������.
			int i = 0;
			Map[] paramMapArray = new HashMap[eventsBuffer.size()];
			for (ExceptionEvent event : eventsBuffer) {
				paramMapArray[i++] = parseEvent(event);
			}
			final SqlParameterSource[] batchParams = SqlParameterSourceUtils.createBatch(paramMapArray);

			// ִ����������,���ʧ�ܵ���ʧ�ܴ�����.
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					try {
						jdbcTemplate.batchUpdate(getActualSql(), batchParams);
						if (logger.isDebugEnabled()) {
							for (ExceptionEvent event : eventsBuffer) {
								logger.debug("saved event: {}", new ExceptionEventWrapper(event).convertToString());
							}
						}
					} catch (DataAccessException e) {
						status.setRollbackOnly();
						handleDataAccessException(e, eventsBuffer);
					}
				}
			});

			// �������ɵ�Buffer
			eventsBuffer.clear();
		} catch (Exception e) {
			logger.error("�����ύ����ʱ��������.", e);
		}
	}

	/**
	 * �˳�������,���buffer��δ��ɵ���Ϣ.
	 */
	@Override
	protected void clean() {
		if (!eventsBuffer.isEmpty()) {
			updateBatch();
		}
		logger.debug("cleaned task {}", this);
	}

	/**
	 * ����Event, ����Parameter Map, ���ڰ�sql�е�Named Parameter.
	 */
	protected Map<String, Object> parseEvent(ExceptionEvent event) {
		Map<String, Object> parameterMap = Maps.newHashMap();
		ExceptionEventWrapper eventWrapper = new ExceptionEventWrapper(event);

		parameterMap.put("serialNo", eventWrapper.getSerialNo());
		parameterMap.put("appName", this.getAppName());
		parameterMap.put("exceptionKind", eventWrapper.getExceptionKind());
		parameterMap.put("userExceptionCode", eventWrapper.getUserExceptionCode());
		parameterMap.put("subUserExceptionCode", eventWrapper.getSubUserExceptionCode());
		parameterMap.put("concreteExceptionCode", eventWrapper.getConcreteExceptionCode());
		parameterMap.put("exceptionDesc", eventWrapper.getExceptionDesc());
		parameterMap.put("exceptionReason", eventWrapper.getExceptionReason());
		parameterMap.put("exceptionTime", eventWrapper.getExceptionTime());
		parameterMap.put("exceptionGrade", eventWrapper.getExceptionGrade());
		parameterMap.put("identifyFlag", eventWrapper.getIdentifyFlag());
		return parameterMap;
	}

	/**
	 * �ɱ��������ص����ݷ��ʴ�������,�罫������¼��־û����ļ�.
	 */
	protected void handleDataAccessException(DataAccessException e, List<ExceptionEvent> errorEventBatch) {
		if (e instanceof DataAccessResourceFailureException) {
			logger.error("database connection error", e);
		} else {
			logger.error("other database error", e);
		}

		for (ExceptionEvent event : errorEventBatch) {
			logger.error("event insert to database error, ignore it: " + new ExceptionEventWrapper(event).convertToString(), e);
		}
	}

	/**
	 * �ɱ��������ص�sql�ṩ����,�ɶ�sql���������⴦������־��ı����ɴ����ں�׺ LOG_2009_02_31.
	 */
	protected String getActualSql() {
		return sql;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
