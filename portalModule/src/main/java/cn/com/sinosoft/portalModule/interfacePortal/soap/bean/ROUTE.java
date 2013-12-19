/**
 * ROUTE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.sinosoft.portalModule.interfacePortal.soap.bean;

public class ROUTE  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String BRANCHNO;

    private String DESTSYS;

    public ROUTE() {
    }

    public ROUTE(
           java.lang.String BRANCHNO,
           java.lang.String DESTSYS) {
           this.BRANCHNO = BRANCHNO;
           this.DESTSYS = DESTSYS;
    }

	public String getBRANCHNO() {
		return BRANCHNO;
	}

	public void setBRANCHNO(String bRANCHNO) {
		BRANCHNO = bRANCHNO;
	}

	public String getDESTSYS() {
		return DESTSYS;
	}

	public void setDESTSYS(String dESTSYS) {
		DESTSYS = dESTSYS;
	}

	

 
}
