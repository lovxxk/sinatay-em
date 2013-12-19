package cn.com.sinosoft.util.web.urlFilter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前段请求资源缓存Filter
 * 主要对前段请求部分资源进行缓存
 * 文件类型在 web.xml 中配置
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class BusiCacheFilter implements Filter {
	private FilterConfig config = null;
	private HashMap expiresMap = new HashMap();

	public void init(FilterConfig filterConfig) {
		this.config = filterConfig;
		expiresMap.clear();
		Enumeration names = config.getInitParameterNames();
		while (names.hasMoreElements()) {
			try {
				String name = (String) names.nextElement();
				String value = config.getInitParameter(name);
				Integer expire = Integer.valueOf(value);
				expiresMap.put(name, expire);
			} catch (Exception ex) {
			}
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		String ext = null;
		int dot = uri.lastIndexOf(".");
		if (dot != -1) {
			ext = uri.substring(dot + 1);
		}

		setResponseHeader(res, uri, ext);
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

	protected FilterConfig getFilterConfig() {
		return (config);
	}

	private void setResponseHeader(HttpServletResponse response, String uri, String ext) {
		if (ext != null && ext.length() > 0) {
			Integer expires = (Integer) expiresMap.get(ext);
			if (expires != null) {
				if (expires.intValue() < 0) { //>0 为开启缓存处理，反之则关闭
					System.out.println(uri + " .Expires: " + expires.intValue());
					response.setHeader("Cache-Control", "max-age=" + expires.intValue()); //HTTP 1.1
				} else {
					response.setHeader("Cache-Control", "no-cache");
					response.setHeader("Pragma", "no-cache"); //HTTP 1.0
					response.setDateHeader("Expires", 0);
				}
			}
		}
	}
}
