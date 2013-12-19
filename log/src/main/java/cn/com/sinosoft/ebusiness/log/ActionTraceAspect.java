package cn.com.sinosoft.ebusiness.log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import cn.com.sinosoft.ebusiness.log.domain.LogTraceDto;

@Aspect
public class ActionTraceAspect {


	@Around("execution(@LocusTrace * cn.com.sinosoft.ebusiness..spring.*Impl.*(..))")
	public Object traceAction(ProceedingJoinPoint pjp) throws IllegalAccessException, InvocationTargetException{
		MethodSignature ms = (MethodSignature)pjp.getSignature();
		LocusTrace lt = ms.getMethod().getAnnotation(LocusTrace.class);
		LogTraceDto ltdto = FootprintUtils.get();
		Object o = null;
		if(ltdto != null){
			LogTraceDto actionltdto = new LogTraceDto();
			actionltdto.setIp(ltdto.getIp());
			actionltdto.setUserId(ltdto.getUserId());
			actionltdto.setUserType(ltdto.getUserType());
			actionltdto.setOperation(lt.setDesc());
			actionltdto.setOperationType(lt.setCode());
			actionltdto.setMakeDate(new Date());
			ltdto.getChildren().add(actionltdto);
			try {
				o = pjp.proceed();
				actionltdto.setResult("success");
			} catch (Throwable e) {
				StringWriter sw = new StringWriter();
				e.printStackTrace();
				e.printStackTrace(new PrintWriter(sw));
				sw.flush();
				try {
					sw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				actionltdto.setResult("error");
			}
			if(!"".equals(lt.setCode())){
				actionltdto.setOperationType(lt.setCode());
			}else{
				actionltdto.setOperation(lt.setDesc());
			}
		}else{
			try {
				o = pjp.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return o;
	}
}
