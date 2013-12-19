package cn.com.sinosoft.ebusiness.log;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.MDC;

/**
 * ϵͳ����ʱ��ӡ���������׷����Ϣ�Ĺ�����.
 * 
 * ʹ��MDC�洢traceID, һ��trace��������־���Զ����и�ID,
 * ���Է������grep��������־�ļ�����ȡ��trace��������־.
 * 
 * ��Ҫ��log4j.properties�н�ConversionPattern���%X{traceId},��:
 * log4j.appender.stdout.layout.ConversionPattern=%d [%c] %X{traceId}-%m%n
 * 
 *  
 */
public class TraceUtils {

	public static final String TRACE_ID_KEY = "traceId";
	public static final int TRACE_ID_LENGTH = 8;
	
	/**
	 * ��ʼTrace, Ĭ�����ɱ���Trace��ID(8�ַ���)������MDC.
	 */
	public static void beginTrace() {
		String traceId = RandomStringUtils.randomAlphanumeric(TRACE_ID_LENGTH);
		MDC.put(TRACE_ID_KEY, traceId);
	}

	/**
	 * ��ʼTrace, ��traceId����MDC.
	 */
	public static void beginTrace(String traceId) {
		MDC.put(TRACE_ID_KEY, traceId);
	}

	/**
	 * ����һ��Trace.
	 * ���traceId.
	 */
	public static void endTrace() {
		MDC.remove(TRACE_ID_KEY);
	}
	/**
	 * ��ȡTraceId
	 * @return
	 */
	public static String  getTraceId(){
		return (String) MDC.get(TRACE_ID_KEY);
	}
	
}
