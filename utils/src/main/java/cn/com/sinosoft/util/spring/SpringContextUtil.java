package cn.com.sinosoft.util.spring;

import org.springframework.web.context.WebApplicationContext;

public class SpringContextUtil {
	
	private static WebApplicationContext ac;

	public static void setAc(WebApplicationContext ac) {
		if(ac != null){
			SpringContextUtil.ac = ac;
		}
	}
	
	/**
	 * 同伙name获取操作对象
	 * @param name
	 * @return
	 */
	public static Object getBean(String name){
		return ac.getBean(name);
	}
	
	/**
	 * 通过类型获取bean
	 * @param <T>
	 * @param cla
	 * @return
	 */
	public static <T> Object getBean(Class<T> cla){
		return ac.getBean(cla);
	} 
	
}
