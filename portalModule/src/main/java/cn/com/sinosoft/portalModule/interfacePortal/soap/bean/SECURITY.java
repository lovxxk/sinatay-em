/**
 * SECURITY.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.sinosoft.portalModule.interfacePortal.soap.bean;

public class SECURITY  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String SIGNATURE;

    public SECURITY() {
    }

    public SECURITY(
           java.lang.String SIGNATURE) {
           this.SIGNATURE = SIGNATURE;
    }

	public String getSIGNATURE() {
		return SIGNATURE;
	}

	public void setSIGNATURE(String sIGNATURE) {
		SIGNATURE = sIGNATURE;
	}


    
}
