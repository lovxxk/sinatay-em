package cn.com.sinosoft.portalModule.transport.transactionObject;

import java.io.Serializable;

public class BusinessResultDatum implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4868715497753804309L;
	
	/**
	 * 业务处理结果
	 */
	private BusinessResult businessResult;
	
	/**
	 * 业务对象
	 */
	private Object businessObject;

	public BusinessResult getBusinessResult() {
		return businessResult;
	}

	public void setBusinessResult(BusinessResult businessResult) {
		this.businessResult = businessResult;
	}

	public Object getBusinessObject() {
		return businessObject;
	}

	public void setBusinessObject(Object businessObject) {
		this.businessObject = businessObject;
	}
	
	
}
