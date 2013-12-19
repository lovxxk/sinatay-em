package cn.com.sinosoft.ebusiness.mis.business.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import cn.com.sinosoft.ebusiness.log.FootprintUtils;
import cn.com.sinosoft.ebusiness.log.LocusTrace;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class FootTraceInterceptor implements Interceptor {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation)throws Exception{
		String method = invocation.getProxy().getMethod();
		@SuppressWarnings("rawtypes")
		Class c[] = null;
		Method currentMethod = invocation.getAction().getClass().getMethod(method, c);
		LocusTrace lt = currentMethod.getAnnotation(LocusTrace.class);
		String result = "";
		if(lt != null){//有需要获取轨迹的
			FootprintUtils.beginLog(lt.setCode());
			try {
				result = invocation.invoke();
				FootprintUtils.endLog("success", "");
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				e.printStackTrace();
				e.printStackTrace(new PrintWriter(sw));
				sw.flush();
				try {
					sw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				FootprintUtils.endLog("error", sw.toString());
				throw e;
				
			}
		}else{
			result = invocation.invoke();
		}
		return result;
	}

}
