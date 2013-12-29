package cn.com.sinosoft.ebusiness.service.insvalidator;

public class ValidationResult {
	
	private String flag;
	private String desc;
	
	public ValidationResult() {
		super();
	}
	public ValidationResult(String flag, String desc) {
		super();
		this.flag = flag;
		this.desc = desc;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public boolean isSuccess(){
		return "1".equals(flag);
	}
}
