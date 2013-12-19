package cn.com.sinosoft.ebusiness.log.webfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import cn.com.sinosoft.ebusiness.log.TraceUtils;

public class TraceFilter implements Filter  {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("-----beginTrace-------");
		TraceUtils.beginTrace();
		System.out.println("-----traceid-------"+TraceUtils.getTraceId());
		filterChain.doFilter(request, response);
		TraceUtils.endTrace();
		System.out.println("-----endTrace-------");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}




}
