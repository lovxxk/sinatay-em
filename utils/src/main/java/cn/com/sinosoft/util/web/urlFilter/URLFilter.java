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
import javax.servlet.http.HttpSession;

@SuppressWarnings({"unchecked", "rawtypes"})
public class URLFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		if(null != session && !"".equals(session.getId())){
			String sessionid = session.getId();
		    ((HttpServletResponse)response).setHeader("SET-COOKIE", "JSESSIONID=" + sessionid + ";HttpOnly;Path=/storename;secure=true");
		}
		
        HashMap<String, String[]> m = new HashMap(httpRequest.getParameterMap());  
        HashMap<String, String[]> mm = new HashMap<String, String[]>();
        Enumeration<String> enu = httpRequest.getParameterNames();
        if(m.size() > 0 && processParameters(m, enu, mm)) {  
 
            ParameterRequestWrapper wrapRequest = new ParameterRequestWrapper(httpRequest, mm);  
 
            chain.doFilter(wrapRequest, response);  
 
        } else {  
 
            chain.doFilter(request, response);  
 
        }
        
	}

	public void destroy() {

	}
	
	public boolean processParameters(HashMap<String, String[]> m, Enumeration enu, HashMap<String, String[]> mm) {  
		 
        System.out.println("[SpecialCharacterFilter] : processParameters *************");  
 
        if (m != null && enu != null) {  
 
            while (enu.hasMoreElements()) {  
 
                String key = (String)enu.nextElement();  
 
                String [] values = (String [])m.get(key);   
                for(int i = 0; i < values.length; i ++) {  
                    if (values[i] != null && 
                    		(values[i].indexOf("orderSerialNo")<0 && 
                    				values[i].indexOf("orderSerialNumber")<0 && 
                    					values[i].indexOf("insurancePolicySerialNo")<0 && 
                    						values[i].indexOf("gePersonalUserSerialNo")<0)) {
                    	//System.out.println("values[i]=="+values[i]);
                        values[i] = values[i].trim(); 
                        values[i] = values[i].replaceAll("<", "");
                        values[i] = values[i].replaceAll(">", "");
						values[i] = values[i].replaceAll("%", "%");
                    }
                }
                mm.put(key, values);  
            }  
        }  
 
        return true;  
 
    }  
  
}
