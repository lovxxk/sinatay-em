package cn.com.sinosoft.portalModule.transport.axis;



/***
 * Axis2 ���������ر���ͷ����
 *  
 *
 */
public class AxisTransferData {
	
	/**
	 * ����ͷ��Ϣ����
	 */
	private AxisOMElement axisHeaderOMElement;
	
	/**
	 * ǰ׺
	 */
	private String prefix;
	
	/**
	 * �����ַ
	 */
	private String requestAddress;
	
	/**
	 * �����ռ�
	 */
	private String targetNamespace;
	
	/**
	 * ���ط���
	 */
	private String localPart;
	
	/**
	 * OptionAction
	 */
	private String optionAction;
	
	/**
	 * ���ݶ���ʵ����
	 */
	private Class<?>[] classes;
	
	/**
	 * ���ݶ���
	 */
	private Object[] sendObject;
	
	
	public AxisOMElement getAxisHeaderOMElement() {
		return axisHeaderOMElement;
	}

	public void setAxisHeaderOMElement(AxisOMElement axisHeaderOMElement) {
		this.axisHeaderOMElement = axisHeaderOMElement;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getRequestAddress() {
		return requestAddress;
	}

	public void setRequestAddress(String requestAddress) {
		this.requestAddress = requestAddress;
	}

	public String getTargetNamespace() {
		return targetNamespace;
	}

	public void setTargetNamespace(String targetNamespace) {
		this.targetNamespace = targetNamespace;
	}

	public String getLocalPart() {
		return localPart;
	}

	public void setLocalPart(String localPart) {
		this.localPart = localPart;
	}

	public String getOptionAction() {
		return optionAction;
	}

	public void setOptionAction(String optionAction) {
		this.optionAction = optionAction;
	}

	public Class<?>[] getClasses() {
		return classes;
	}

	public void setClasses(Class<?>[] classes) {
		this.classes = classes;
	}

	public Object[] getSendObject() {
		return sendObject;
	}

	public void setSendObject(Object[] sendObject) {
		this.sendObject = sendObject;
	}
	
	
}
