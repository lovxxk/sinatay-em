package cn.com.sinosoft.ebusiness.sys.permission.common;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;



public class ShowView extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		if(isEval()){
			return this.EVAL_BODY_INCLUDE;
		} else{
			return this.SKIP_BODY;
		}
	}
	
	@Override
	public int doEndTag() throws JspException {
		try{
			sole.remove();
		}catch(Exception e){}
		return this.doAfterBody();
	}

	private ThreadLocal<String> sole = new ThreadLocal<String>();

	public String getSource() {
		return sole.get();
	}

	public void setSource(String source) {
		sole.set(source);
	}
	
	private boolean isEval(){
		HttpSession currentSession = pageContext.getSession();
		try{
			Map permission = (Map)currentSession.getAttribute("permission");
			String sourceString = getSource().trim();
			if(sourceString.contains("||")){
				boolean bl = false;
				String[] sArr = sourceString.split("\\|\\|");
				for(int i = 0; i < sArr.length; i++){
					String s = sArr[i];
					if(!isEmpty(permission.get(s))){
						bl = true;
						break;
					}
				}
				return bl;
			}else if(sourceString.contains("&&")){
				boolean bl = true;
				String[] sArr = sourceString.split("&&");
				for(int i = 0; i < sArr.length; i++){
					String s = sArr[i];
					if(isEmpty(permission.get(s))){
						bl = false;
						break;
					}
				}
				return bl;
			}else{
				return !isEmpty(permission.get(getSource()));
			}
		} catch(Exception e){
			throw new RuntimeException("PermissionCollection Not found in HttpSession"); 
		}
	}
	
	private boolean isEmpty(Object obj){
	   if(obj == null || "".equals(obj.toString())){
		   return true;
	   }
	   return false;	
	}

}
