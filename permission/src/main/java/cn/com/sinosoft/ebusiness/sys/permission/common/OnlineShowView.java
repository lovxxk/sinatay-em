package cn.com.sinosoft.ebusiness.sys.permission.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class OnlineShowView extends TagSupport {

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
			List permission = (List)currentSession.getAttribute("permission");
			if(permission != null){
				String sourceString = getSource().trim();
				if(sourceString.contains("||")){
					boolean bl = false;
					String[] sArr = sourceString.split("\\|\\|");
					for(int i = 0; i < sArr.length; i++){
						String s = sArr[i];
						if(permission.contains(s)){
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
						if(!permission.contains(s)){
							bl = false;
							break;
						}
					}
					return bl;
				}else{
					return permission.contains(sourceString);
				}
			}else{
				return false;
			}
		} catch(Exception e){
			throw new RuntimeException("PermissionCollection Not found in HttpSession"); 
		}
	}
}
