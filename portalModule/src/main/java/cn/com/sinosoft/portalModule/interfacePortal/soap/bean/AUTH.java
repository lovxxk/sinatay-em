/**
 * AUTH.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.sinosoft.portalModule.interfacePortal.soap.bean;

public class AUTH  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String BRANCHNO;

    private String USERID;

    private String TOKENID;

    public AUTH() {
    }

    public AUTH(
           java.lang.String BRANCHNO,
           java.lang.String USERID,
           java.lang.String TOKENID) {
           this.BRANCHNO = BRANCHNO;
           this.USERID = USERID;
           this.TOKENID = TOKENID;
    }

	public String getBRANCHNO() {
		return BRANCHNO;
	}

	public void setBRANCHNO(String bRANCHNO) {
		BRANCHNO = bRANCHNO;
	}

	public String getUSERID() {
		return USERID;
	}

	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}

	public String getTOKENID() {
		return TOKENID;
	}

	public void setTOKENID(String tOKENID) {
		TOKENID = tOKENID;
	}


   
  
}
