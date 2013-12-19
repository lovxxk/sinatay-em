package cn.com.sinosoft.portalModule.transport.sinatay;

public class SMSResult implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String flag;
	
	private String desc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
}
