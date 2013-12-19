<%@page import="cn.com.sinosoft.ebusiness.sys.notification.NotificationModule"%>
<%@ page contentType="text/html;charset=gbk" isErrorPage="true"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ page
	import="org.slf4j.Logger,org.slf4j.LoggerFactory,cn.com.sinosoft.ebusiness.sys.exception.UserException,cn.com.sinosoft.ebusiness.sys.notification.NotificationEvent,cn.com.sinosoft.ebusiness.sys.exception.aspect.ExceptionEvent,cn.com.sinosoft.ebusiness.sys.exception.aspect.ExceptionEventWrapper,java.util.concurrent.BlockingQueue,java.util.Date,cn.com.sinosoft.util.queue.QueuesHolder,cn.com.sinosoft.ebusiness.sys.exception.ExceptionConfig"%>

<%
	if (exception != null) {
		exception.printStackTrace();
	} else {
		System.out.println("----1----");
	}
	System.out.println("------------------");

	BlockingQueue<ExceptionEvent> exceptionQueue = null;
	BlockingQueue<NotificationEvent> notificationQueue = null;
	Throwable ex = null;
	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request
				.getAttribute("javax.servlet.error.exception");
	String title = "wer";
	String content = "xxx";
	//非UserException异常写入数据库和发短息通知
	if (ex != null && !(ex instanceof UserException)) {
		ExceptionEvent ee = new ExceptionEvent(ex, new Date());
		NotificationEvent ne = new NotificationEvent(
				"1", null, title,
				content, NotificationModule.EXCEPTION);
		
		System.out.println("-----jsp---catch-----begin-----");
		//异常放入队列exceptionQueue
		if (exceptionQueue == null) {
			exceptionQueue = QueuesHolder.getQueue("exception");
		}
		boolean sucess_1 = exceptionQueue.offer(ee);
		


	if (sucess_1) {
			logger.debug("put exception to exceptionQueue success:"
					+ new ExceptionEventWrapper(ee).convertToString());
		} else {
			logger.error("Put exception to exceptionQueue fail:"
					+ new ExceptionEventWrapper(ee).convertToString());
		}
		//异常放入队列notificationQueue
		if (notificationQueue == null) {
			notificationQueue = QueuesHolder.getQueue("notification");
		}
		boolean sucess_2 = notificationQueue.offer(ne);
		if (sucess_2) {
			logger.debug("put exception to notificationQueue success:"
					+ ne.getContent());
		} else {
			logger.error("Put exception to notificationQueue fail:"
					+ ne.getContent());
		}
		
		
		

	}
	System.out.println("-----jsp---catch-----end-----");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>系统发生内部错误！</title>
<style type="text/css">
.prompt_warpper{width:470px; height:240px; clear:both; margin:0px auto; padding-top:185px;}
</style>
</head>

<body>
<div align="center">
	<div class="prompt_warpper"><img src="${ctx }/global/images/prompt_img1.jpg" border="0" /></div>
</div>
</body>
</html>
