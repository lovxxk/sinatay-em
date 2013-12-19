package cn.com.sinosoft.ruleEngine.util;

public class CheckMessage {
	
	private Object target;
	
	private String field;
	
	private String message;


	public CheckMessage() {
		super();
	}

	public CheckMessage(Object target, String field, String message) {
		super();
		this.target = target;
		this.field = field;
		this.message = message;
	}
	

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
