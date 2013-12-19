package cn.com.sinosoft.portalModule.transport.chinapay;

public class Gzelink implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Info info;
	
	private Body body;

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
	
	

}
