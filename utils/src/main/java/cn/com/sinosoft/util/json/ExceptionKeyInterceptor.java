package cn.com.sinosoft.util.json;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings({"rawtypes"})
public class ExceptionKeyInterceptor implements Interceptor {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2380001601417285292L;

	public void destroy() {

	}

	public void init() {

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		Object o =  ((HashMap)invocation.getInvocationContext().get("parameters")).get("temporary.quoteMain.geQuoteCars[0].licenseNo");
		beforeInvoke(o);
		String result = invocation.invoke();
		afterInvoke();
		return result;
	}
	
	private void beforeInvoke(Object o){
	}
	
	private void afterInvoke(){
	}

}
