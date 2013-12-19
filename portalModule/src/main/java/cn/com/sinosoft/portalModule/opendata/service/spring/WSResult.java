package cn.com.sinosoft.portalModule.opendata.service.spring;

import java.util.HashMap;
import java.util.Map;

public class WSResult {
	
	Map<String, Object> resultFlag = new HashMap<String, Object>();
	
	private Object obj;

	

	public Map<String, Object> getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(Map<String, Object> resultFlag) {
		this.resultFlag = resultFlag;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
	
}
