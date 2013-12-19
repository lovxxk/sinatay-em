package cn.com.sinosoft.portalModule.transport.axis;

import java.util.List;

public class AxisOMElement {
	
	private String elementName;
	
	private String elementValue;
	
	private List<AxisOMElement> axisChildOMElement;

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getElementValue() {
		return elementValue;
	}

	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}

	public List<AxisOMElement> getAxisChildOMElement() {
		return axisChildOMElement;
	}

	public void setAxisChildOMElement(List<AxisOMElement> axisChildOMElement) {
		this.axisChildOMElement = axisChildOMElement;
	}
	
	
}
