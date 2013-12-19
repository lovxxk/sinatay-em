package cn.com.sinosoft.ebusiness.log;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.sql.Timestamp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ClassUtils;

import cn.com.sinosoft.util.encode.JsonBinder;

/**
 * 控制InterfaceTrace的InterfaceTraceAspect.
 * 
 *  
 */
@Aspect
public class InterfaceTraceAspect {

	// db记录日志
	Logger dbLogger = LoggerFactory.getLogger("DBLog");
	private static JsonBinder binder = JsonBinder.buildNonDefaultBinder();

	/**
	 * 根据@InterfaceTraced标记来记录日志
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */

	@Around("execution(public * cn.com.sinosoft.ebusiness..*.*(..))")
	public Object logAgroundClassAndInterface(ProceedingJoinPoint pjp)
			throws Throwable {
		System.out.println("Trace   "+pjp.getSignature().getName());
		Class<?> sourceClass = pjp.getSignature().getDeclaringType();
		Class<?> targetClass = pjp.getTarget().getClass();
		Method method = getMethod(pjp, sourceClass);
		// 获取cglib代理对象
		Class<?> userClass = ClassUtils.getUserClass(targetClass);
		Method specificMethod = ClassUtils.getMostSpecificMethod(method,
				userClass);
		specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
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
			// @Interfacetrace检查
			if (parseInterfaceTraced(specificMethod) ? true : checkInfTrace(
					method, userClass)) {
				dbLogger.info(
						"[@MethodTrace]interface在 -- {} -- 调用 -- {} -- 传入参数 -- {} -- 返回 -- {} -- ,在 -- {} -- 结束,经历时常为 -- {} -- ",
						new Object[] { new Timestamp(begin),
								pjp.getSignature().toShortString(),
								binder.toJson(pjp.getArgs()),
								binder.toJson(result), new Timestamp(end),
								new Long(time) });
			}
		}
	}

	private boolean checkInfTrace(Method method, Class<?> userClass) {
		Class<?>[] inf = ClassUtils.getAllInterfacesForClass(userClass);
		for (Class<?> c : inf) {
			Method m = AopUtils.getMostSpecificMethod(method, c);
			if (parseInterfaceTraced(m))
				return true;
		}
		return false;
	}

	/**
	 * 解析@InterfaceTrace声明
	 * 
	 * @param ae
	 * @return
	 */
	private boolean parseInterfaceTraced(AnnotatedElement ae) {
		InterfaceTraced t = ae.getAnnotation(InterfaceTraced.class);
		if (t == null) {
			for (Annotation metaAnn : ae.getAnnotations()) {
				t = metaAnn.annotationType().getAnnotation(
						InterfaceTraced.class);
				if (t != null) {
					break;
				}
			}
		}
		if (t != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获得执行方法.
	 * 
	 * @param pjp
	 *            ProceedingJoinPoint
	 * @return 执行方法
	 */
	private Method getMethod(ProceedingJoinPoint pjp, Class<?> targetClass) {
		Method method = null;
		Method[] ms = targetClass.getMethods();
		for (Method m : ms) {
			if (m.getName().equals(pjp.getSignature().getName())) {
				method = m;
				break;
			}
		}
		return method;
	}

//	@AfterThrowing(pointcut = "execution (* com.sinosoft.ebusiness.proposal.ProposalService.*(..))", throwing = "ex")
//	public void inWebLayer(BusinessException ex) {
//		dbLogger.error(ex.getMessage(), ex);
//	}
}
