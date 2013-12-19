package cn.com.sinosoft.portalModule.interfacePortal.soap.util;
/**
 * Copyright 2003-2010 UFIDA Software Engineering Co., Ltd. 
 */


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.OMElementImpl;
import org.apache.axiom.om.impl.llom.OMTextImpl;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.client.Stub;

/**
 * SOAP助手类
 */
public class SoapHelper {
	// 应答头(需要在每个stub类里设置)
	private static Map<String,OMElement> headElement = new HashMap<String, OMElement>(); 
	
	/**
	 * 设置请求soap版本
	 * @param stub
	 * @param interfaceInfo
	 * @return
	 * @throws XMLStreamException 
	 */
	public static void setSoapVersion(Stub stub ) {
		//soap1.1  axis2默认是soap1.2  命名空间指定一个schemas标准去解析
		stub._getServiceClient().getOptions().setSoapVersionURI("http://schemas.xmlsoap.org/soap/envelope/");
	}
	
	
	/**
	 * 设置请求头
	 * @param stub
	 * @param interfaceInfo
	 * @return
	 * @throws XMLStreamException 
	 */
	public static void setSoapHeader(Stub stub ,String soapHeaderStr) throws XMLStreamException{
		SoapHelper.addHeader(stub, soapHeaderStr);
	}
	
	/**
	 * 获取应答头
	 * @return
	 * @throws Exception 
	 */
	public static Map<String,Object> getReturnHead(String tranNumber){
		Map<String, Object> map = new HashMap<String, Object>();
		if(getHeadElement().get(tranNumber) == null)
			return map;
		
		SoapHelper.getHeader(getHeadElement().get(tranNumber),map);
		return map;
	}
	
    /**
     * 追加Header
     * @param stub 客户端桩。
     * @param text header内容。
     * @throws XMLStreamException 异常。
     */
    public static void addHeader(Stub stub, String text) throws XMLStreamException {
        OMElement header = AXIOMUtil.stringToOM(text);
        stub._getServiceClient().addHeader(header);
    }
    


    
    private static void getHeader(OMElement omElement ,Map<String, Object> map) {
		Iterator iterator = omElement.getChildElements();
		while (iterator.hasNext()) {
			Object element = iterator.next();
			if (element instanceof org.apache.axiom.om.impl.llom.OMTextImpl) {
				org.apache.axiom.om.impl.llom.OMTextImpl elementText = (org.apache.axiom.om.impl.llom.OMTextImpl) element;
				//System.out.println("Text" + elementText.getText());
			} else if (element instanceof org.apache.axiom.om.impl.llom.OMElementImpl) {
				org.apache.axiom.om.impl.llom.OMElementImpl elementImpl = (org.apache.axiom.om.impl.llom.OMElementImpl) element;
				getHeaderChildren(elementImpl, map);
			}
		}
		
	}

    public static void getHeaderChildren(OMElementImpl elementImpl,Map<String, Object> map){
		Iterator iterator = elementImpl.getChildren();
		while (iterator.hasNext()) {
			Object element = iterator.next();
			if(element instanceof org.apache.axiom.om.impl.llom.OMTextImpl){
				OMTextImpl elementText = (OMTextImpl) element;
				OMElementImpl impl = (OMElementImpl) elementText.getParent();
				
				map.put(impl.getLocalName(), elementText.getText());
				//System.out.println(impl.getLocalName() + ":" + elementText.getText());
			}
			else if(element instanceof org.apache.axiom.om.impl.llom.OMElementImpl){
				getHeaderChildren((OMElementImpl)element,map);
			}
		} 
		
	}


    public static void clear(String tranNumber){
    	getHeadElement().remove(tranNumber);
    }
    
	

	public static Map<String, OMElement> getHeadElement() {
		return headElement;
	}


	public static void setHeadElement(Map<String, OMElement> headElement) {
		SoapHelper.headElement = headElement;
	}


}
