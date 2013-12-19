package cn.com.sinosoft.ebusiness.init.listener;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;

public class OAuthFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String requestURI = request.getRequestURI();
		if (requestURI.contains("qqoauth")) {
			response.setContentType("text/html;charset=GBK");
			try {
				response.sendRedirect(new Oauth().getAuthorizeURL(request));
			} catch (QQConnectException e) {
				e.printStackTrace();
			}
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}