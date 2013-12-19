package cn.com.sinosoft.ebusiness.sys.permission.common;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;

public class SpringCtxLoader extends WebApplicationObjectSupport {  
    /** 
     * Logger for this class 
     */  
    private static final Logger logger = Logger.getLogger(SpringCtxLoader.class);  
  
    /** 
     * Web application 中的常量集合Map的名字. 
     */  
    private static final String SPRING_CONTEXT = "springctx";  
  
    /** 
     * initialize application context . 
     */  
    @SuppressWarnings("unchecked")  
    protected void initApplicationContext() {  
        try {  
            final WebApplicationContext ctx = this.getWebApplicationContext();  
            HashMap springServices = new HashMap() {  
                private static final long serialVersionUID = -1759893921358235848L;  
                public Object get(Object key) {  
                    return ctx.getBean((String) key);  
                };  
                public boolean containsKey(Object key) {  
                    return true;  
                }  
            };  
            logger.info("load code table (spring  beans");  
            // 放入到Web全局变量中，供页面使用.  
            this.getServletContext().setAttribute(SPRING_CONTEXT,springServices);  
        } catch (IllegalStateException e) {  
            logger.warn("not web app application context ,can't be load " + e.getLocalizedMessage());  
        }  
    }  
  
}
