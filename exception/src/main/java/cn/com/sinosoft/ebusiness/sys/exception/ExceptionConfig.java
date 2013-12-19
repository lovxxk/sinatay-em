package cn.com.sinosoft.ebusiness.sys.exception;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * �쳣���ô洢��
 * 
 */
public class ExceptionConfig {
	private static HashMap<String, Object> userExceptionMap = new HashMap<String, Object>();
	private static HashMap<String, Object> grades = new HashMap<String, Object>();

	/**
	 * ��ȡ�û��쳣map
	 */
	private static HashMap<String, Object> getUserExceptionMap() {
		return userExceptionMap;
	}

	/**
	 * ����û��쳣
	 */
	public static void putSubUserExceptionMap(String key, Object obj) {
		if (!ExceptionConfigInit.isFinishInit()
				&& !ExceptionConfig.getUserExceptionMap().containsKey(key)) {
			ExceptionConfig.getUserExceptionMap().put(key, obj);
		}
	}

	/**
	 * ��ȡ�����ļ��еĵ��ļ��쳣�������쳣��
	 */
	@SuppressWarnings("unchecked")
	public static XmlConcreteException getXmlConcreteException(
			String exceptionKind, String userExceptionCode,
			String subUserExceptionCode, String concreteExceptionCode) {
		XmlConcreteException xmlConcreteException = null;
		try {
			if (ExceptionConfig.getUserExceptionMap().containsKey(
					userExceptionCode)) {
				HashMap<String, Object> subUserExceptionMap = (HashMap<String, Object>) ExceptionConfig
						.getUserExceptionMap().get(userExceptionCode);
				if (subUserExceptionMap.containsKey(subUserExceptionCode)) {
					HashMap<String, Object> concreteExceptionMap = (HashMap<String, Object>) subUserExceptionMap
							.get(subUserExceptionCode);
					if (concreteExceptionMap.containsKey(concreteExceptionCode)) {
						xmlConcreteException = (XmlConcreteException) concreteExceptionMap
								.get(concreteExceptionCode);
					}
				}
			}
		} catch (RuntimeException re) {
			Logger dbLogger = LoggerFactory.getLogger("DBLog");
			dbLogger.error("���쳣map�л�ȡ�쳣��Ϣʧ��", re);
		}
		return xmlConcreteException;
	}

	/**
	 * ����쳣����
	 */
	public static void putGradeMap(String key, Object obj) {
		if (!ExceptionConfigInit.isFinishInit()
				&& !ExceptionConfig.grades.containsKey(key)) {
			ExceptionConfig.grades.put(key, obj);
		}
	}

	/**
	 * ��ȡ�쳣����
	 */
	public static ExceptionGradeHandle getGradeHandle(String key) {
		ExceptionGradeHandle gradeHandle = null;
		if (ExceptionConfig.grades.containsKey(key)) {
			gradeHandle = (ExceptionGradeHandle) ExceptionConfig.grades
					.get(key);
		}
		return gradeHandle;
	}

}
