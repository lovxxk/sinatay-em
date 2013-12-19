package cn.com.sinosoft.portalModule.transport.chinapay;

import java.util.ArrayList;
import java.util.List;

public class Body implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private TransSum transsum;
	
	private List<TransDetail> transDetails  = new ArrayList<TransDetail>();

	public TransSum getTranssum() {
		return transsum;
	}

	public void setTranssum(TransSum transsum) {
		this.transsum = transsum;
	}

	public List<TransDetail> getTransDetails() {
		return transDetails;
	}

	public void setTransDetails(List<TransDetail> transDetails) {
		this.transDetails = transDetails;
	}

	
	

}
