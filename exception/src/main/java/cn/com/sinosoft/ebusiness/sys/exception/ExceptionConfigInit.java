package cn.com.sinosoft.ebusiness.sys.exception;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异常初始化类
 * 
 */
public final class ExceptionConfigInit {

	private static boolean isFinishInit = false;
	private String exceptionConfigPath;

	/**
	 * 初始化
	 */
	public void init() {
//		parserXml(exceptionConfigPath);
	}

	/**
	 * 获取异常配置文件路径
	 */
	private String getFilePath(String exceptionConfigPath)
			throws UnsupportedEncodingException {
		String filePath = this.getClass().getClassLoader()
				.getResource(exceptionConfigPath).getPath();
		filePath = java.net.URLDecoder.decode(filePath, "utf-8");
		return filePath;
	}

	/**
	 * 解析异常配置文件
	 */
	@SuppressWarnings("rawtypes")
	private void parserXml(String exceptionConfigPath) {
		if (!isFinishInit) {
			try {

				String filePath = getFilePath(exceptionConfigPath);
				File file = new File(filePath);
				Document document = new SAXReader().read(file);
				/**
				 * 解析配置文件，初始化userExceptionMap
				 */
				Element root = document.getRootElement();
				Element userExceptions = root.element("UserExceptions");
//				Element handleException = root.element("HandleException");
				Element exceptionGrades = root.element("ExceptionGrades");
				
				boolean isShowExceptionCode = false;
				boolean isShowExceptionDesc = false;

				for (Iterator it_userException = userExceptions
						.elementIterator(); it_userException.hasNext();) {
					Element userException = (Element) it_userException.next();
					HashMap<String, Object> subUserExceptionMap = new HashMap<String, Object>();
					for (Iterator it_subUserException = userException
							.elementIterator(); it_subUserException.hasNext();) {
						Element subUserException = (Element) it_subUserException
								.next();
						HashMap<String, Object> concreteExceptionMap = new HashMap<String, Object>();
						for (Iterator it_concreteException = subUserException
								.elementIterator(); it_concreteException
								.hasNext();) {
							Element concreteException = (Element) it_concreteException
									.next();
							ExceptionGrade exceptiongrade = ExceptionGrade.UNSERIOUS;
							if (concreteException.attribute("Grade") != null) {
								String grade = concreteException
										.attributeValue("Grade");
								if (ExceptionGrade.SERIOUS.toString().equals(
										grade))
									exceptiongrade = ExceptionGrade.SERIOUS;
								if (ExceptionGrade.MORESERIOUS.toString()
										.equals(grade))
									exceptiongrade = ExceptionGrade.MORESERIOUS;
								if (ExceptionGrade.MOSTSERIOUS.toString()
										.equals(grade))
									exceptiongrade = ExceptionGrade.MOSTSERIOUS;
							}
							
							if(concreteException.attribute("isShowExceptionCode") != null){
								isShowExceptionCode = Boolean.valueOf(concreteException.attributeValue("isShowExceptionCode"));
								isShowExceptionDesc = Boolean.valueOf(concreteException.attributeValue("isShowExceptionDesc"));
							}
							
							String code = concreteException
									.elementText("ExceptionCode");
							String desc = concreteException
									.elementText("ExceptionDesc");
							XmlConcreteException xmlConcreteException = new XmlConcreteException(
									exceptiongrade, code, desc,isShowExceptionCode,isShowExceptionDesc);
							concreteExceptionMap
									.put(code, xmlConcreteException);
						}
						subUserExceptionMap.put(
								subUserException.attributeValue("Code"),
								concreteExceptionMap);
					}
					ExceptionConfig.putSubUserExceptionMap(
							userException.attributeValue("Code"),
							subUserExceptionMap);

				}
				/**
				 * 初始化手机号数组Tels和邮件数组Emails
				 */
//				for (Iterator it_telNums = handleException.element("TelNums")
//						.elementIterator(); it_telNums.hasNext();) {
//					Element telNum = (Element) it_telNums.next();
//					ExceptionConfig.addTel(telNum.getText());
//				}

//				for (Iterator it_emailAddresses = handleException.element(
//						"EmailAddresses").elementIterator(); it_emailAddresses
//						.hasNext();) {
//					Element emailAddress = (Element) it_emailAddresses.next();
//					ExceptionConfig.addEmail(emailAddress.getText());
//				}
				/**
				 * 初始化异常处理级别
				 */
				for (Iterator it_exceptionGrades = exceptionGrades
						.elementIterator(); it_exceptionGrades.hasNext();) {
					Element exceptionGrade = (Element) it_exceptionGrades
							.next();
					String grade = exceptionGrade.getText();
					String isSendSms = exceptionGrade
							.attributeValue("IsSendSms");
					String isSendEmail = exceptionGrade
							.attributeValue("IsSendEmail");
					String desc = exceptionGrade.attributeValue("Description");
					ExceptionGradeHandle gradeHandle = new ExceptionGradeHandle(
							grade, isSendSms, isSendEmail, desc);
					ExceptionConfig.putGradeMap(grade, gradeHandle);
				}
			} catch (UnsupportedEncodingException e) {
				Logger dbLogger = LoggerFactory.getLogger("DBLog");
				dbLogger.error("获取异常配置文件路径出错", e);
			} catch (DocumentException e) {
				Logger dbLogger = LoggerFactory.getLogger("DBLog");
				dbLogger.error("解析异常配置文件失败", e);
			} catch (RuntimeException re) {
				Logger dbLogger = LoggerFactory.getLogger("DBLog");
				dbLogger.error("解析异常配置文件失败,出现运行时异常", re);
			} finally {
				Logger logger = LoggerFactory
						.getLogger(ExceptionConfigInit.class);
				logger.info("异常配置文件解析完毕");
				isFinishInit = true;
			}
		} else {
			Logger logger = LoggerFactory.getLogger(ExceptionConfigInit.class);
			logger.info("异常配置文件已经被解析过");
		}
	}

	/**
	 * 是否完成初始化
	 * 
	 * @return boolean
	 */
	public static boolean isFinishInit() {
		return isFinishInit;
	}

	public String getExceptionConfigPath() {
		return exceptionConfigPath;
	}

	public void setExceptionConfigPath(String exceptionConfigPath) {
		this.exceptionConfigPath = exceptionConfigPath;
	}
}
