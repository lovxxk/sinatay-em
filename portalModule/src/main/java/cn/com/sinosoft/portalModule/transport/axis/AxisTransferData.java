package cn.com.sinosoft.portalModule.transport.axis;



/***
 * Axis2 传输对象及相关报文头配置
 *  
 *
 */
public class AxisTransferData {
	
	/**
	 * 报文头信息配置
	 */
	private AxisOMElement axisHeaderOMElement;
	
	/**
	 * 前缀
	 */
	private String prefix;
	
	/**
	 * 请求地址
	 */
	private String requestAddress;
	
	/**
	 * 命名空间
	 */
	private String targetNamespace;
	
	/**
	 * 本地方法
	 */
	private String localPart;
	
	/**
	 * OptionAction
	 */
	private String optionAction;
	
	/**
	 * 数据对象实现类
	 */
	private Class<?>[] classes;
	
	/**
	 * 数据对象
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
