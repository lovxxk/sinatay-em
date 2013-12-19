package cn.com.sinosoft.util.cookie;

import javax.servlet.http.Cookie;

/**
 * cookie 自定义服务类
 */
public class CookieManager {

	/**
	 * 根据cookie的name获取对应的Value
	 */
	public static String getValue(String name, Cookie cookies[]) {
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			if (name.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}
}
