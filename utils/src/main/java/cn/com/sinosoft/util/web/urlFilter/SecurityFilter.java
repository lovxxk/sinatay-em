package cn.com.sinosoft.util.web.urlFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter {
	
	private FilterConfig filterConfig;
	
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		List<String> filterUrl = new ArrayList<String>();
		filterUrl.add(req.getContextPath() + "/online/web/user/usercenter/index.jsp");
		filterUrl.add(req.getContextPath() + "/online/web/user/usercenter/modifyPassword.jsp");
		filterUrl.add(req.getContextPath() + "/online/web/sale/order/order.jsp");
		filterUrl.add(req.getContextPath() + "/online/order/findOrderByPersonalUser.do");
		filterUrl.add(req.getContextPath() + "/online/order/downloadEPolicy.do");
		if (filterUrl.contains(req.getRequestURI())) {
			if (session.getAttribute("userPersonal") != null) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + "/online/index.jsp?login=noLogin");
			}
		} else {
			chain.doFilter(request, response);
		}
		
	}


	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	

	public void destroy() {
		// TODO Auto-generated method stub
		this.filterConfig = null;
	}
}
