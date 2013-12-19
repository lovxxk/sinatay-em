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
 * SOAP������
 */
public class SoapHelper {
	// Ӧ��ͷ(��Ҫ��ÿ��stub��������)
	private static Map<String,OMElement> headElement = new HashMap<String, OMElement>(); 
	
	/**
	 * ��������soap�汾
	 * @param stub
	 * @param interfaceInfo
	 * @return
	 * @throws XMLStreamException 
	 */
	public static void setSoapVersion(Stub stub ) {
		//soap1.1  axis2Ĭ����soap1.2  �����ռ�ָ��һ��schemas��׼ȥ����
		stub._getServiceClient().getOptions().setSoapVersionURI("http://schemas.xmlsoap.org/soap/envelope/");
	}
	
	
	/**
	 * ��������ͷ
	 * @param stub
	 * @param interfaceInfo
	 * @return
	 * @throws XMLStreamException 
	 */
	public static void setSoapHeader(Stub stub ,String soapHeaderStr) throws XMLStreamException{
		SoapHelper.addHeader(stub, soapHeaderStr);
	}
	
	/**
	 * ��ȡӦ��ͷ
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
     * ׷��Header
     * @param stub �ͻ���׮��
     * @param text header���ݡ�
     * @throws XMLStreamException �쳣��
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
