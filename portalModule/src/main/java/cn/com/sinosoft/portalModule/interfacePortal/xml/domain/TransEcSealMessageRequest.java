package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

public class TransEcSealMessageRequest extends TransMessageRequest{
	
	private Object extensionInfo;

	private TransEcSealRequestExtension ecSealRequestExtension;
	
	public Object getExtensionInfo() {
		return extensionInfo;
	}

	public void setExtensionInfo(Object extensionInfo) {
		this.extensionInfo = extensionInfo;
	}

	public TransEcSealRequestExtension getEcSealRequestExtension() {
		return ecSealRequestExtension;
	}

	public void setEcSealRequestExtension(
			TransEcSealRequestExtension ecSealRequestExtension) {
		this.ecSealRequestExtension = ecSealRequestExtension;
	}
	
	
}
