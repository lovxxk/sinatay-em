package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

public class TransEcSealMessageResponse extends TransMessageResponse {
	
	private Object returnExtensionInfo;
	
	private TransEcSealResponseExtension ecSealResponseExtension;
	
	public Object getReturnExtensionInfo() {
		return returnExtensionInfo;
	}

	public void setReturnExtensionInfo(Object returnExtensionInfo) {
		this.returnExtensionInfo = returnExtensionInfo;
	}

	public TransEcSealResponseExtension getEcSealResponseExtension() {
		return ecSealResponseExtension;
	}

	public void setEcSealResponseExtension(
			TransEcSealResponseExtension ecSealResponseExtension) {
		this.ecSealResponseExtension = ecSealResponseExtension;
	}
	
	
}
