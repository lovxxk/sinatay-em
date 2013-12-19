package cn.com.sinosoft.ebusiness.log;

import java.sql.Timestamp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.sinosoft.util.encode.JsonBinder;

/**
 * ����ImplTrace��ImplTraceAspect.
 * 
 *  
 */
@Aspect
public class ImplTraceAspect {

	// db��¼��־
	private static Logger dbLogger = LoggerFactory.getLogger("DBLog");
	private static JsonBinder binder = JsonBinder.buildNonDefaultBinder();
	// ����
	private String environment;

	// private String test="* cn.com.sinosoft.ebusiness..*.*(..))";
	/**
	 * ����@ImplTraced(enableId=true,configEnv=Environment.TEST)��ǵķ���,
	 * ��¼��ִ�в��������ؽ��. ֻ֧�־���class
	 */
	@Around("execution(@ImplTraced(configEnv=Environment.TEST) * cn.com.sinosoft.ebusiness..*.*(..))")
	public Object traceTest(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("---------traceTest-----aspect");
		// �жϵ�ǰ�Ļ��� �Ƿ���test
		if (Environment.TEST.toString().equals(environment))
			return logAgroundClass(pjp, true);
		else
			return logAgroundClass(pjp, false);
	}

	/**
	 * ����@ImplTraced(enableId=true,configEnv=Environment.DEVLEOP)��ǵķ���,
	 * ��¼��ִ�в��������ؽ��. ֻ֧�־���class
	 */
	@Around("execution(@ImplTraced(configEnv=Environment.DEVLEOP) * cn.com.sinosoft.ebusiness..*.*(..))")
	public Object traceDevelop(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("---------traceDevelop-----aspect");
		// �жϵ�ǰ�Ļ��� �Ƿ���test ��development
		if (Environment.DEVLEOP.toString().equals(environment)
				|| Environment.TEST.toString().equals(environment))
			return logAgroundClass(pjp, true);
		else
			return logAgroundClass(pjp, false);
	}

	/**
	 * ����@ImplTraced(enableId=true,configEnv=Environment.PRODUCT)��ǵķ���,
	 * ��¼��ִ�в��������ؽ��. ֻ֧�־���class
	 */
	@Around("execution(@ImplTraced(configEnv=Environment.PRODUCT) * cn.com.sinosoft.ebusiness..*.*(..))")
	public Object traceProduct(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("---------traceProduct-----aspect");
		// �жϵ�ǰ�Ļ��� �Ƿ���test ��development ��PRODUCT
		return logAgroundClass(pjp, true);
	}

	/**
	 * ���غ�����
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	// @Around("@annotation(cn.com.sinosoft.ebusiness.log.ImplTraced)")
	public Object logAgroundClass(ProceedingJoinPoint pjp, boolean isTrace)
			throws Throwable {
		if (isTrace == false)
			return pjp.proceed();
		Object result = null;
		long begin = 0;
		long end = 0;
		long time = 0;
		try {
			begin = System.currentTimeMillis();
			result = pjp.proceed();
			end = System.currentTimeMillis();
			time = end - begin;
			return result;
		} finally {
			String traceId = TraceUtils.getTraceId();
			if (traceId == null || "".equals(traceId))
				traceId = "noId";
			dbLogger.info(
					"[@MethodTrace][@traceId="
							+ traceId
							+ "]�� -- {} -- ���� -- {} -- ������� -- {} -- ���� -- {} -- ,�� -- {} -- ����,����ʱ��Ϊ -- {} -- ",
					new Object[] { new Timestamp(begin),
							pjp.getSignature().toShortString(),
							binder.toJson(pjp.getArgs()),
							binder.toJson(result), new Timestamp(end),
							new Long(time) });
		}
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

}
