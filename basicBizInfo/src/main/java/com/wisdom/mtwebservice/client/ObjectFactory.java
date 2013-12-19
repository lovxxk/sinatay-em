
package com.wisdom.mtwebservice.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.wisdom.mtwebservice.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _QuerySM_QNAME = new QName("http://mtwebservice.wisdom.com/", "querySM");
    private final static QName _SendSMResponse_QNAME = new QName("http://mtwebservice.wisdom.com/", "sendSMResponse");
    private final static QName _QueryFailSMResponse_QNAME = new QName("http://mtwebservice.wisdom.com/", "queryFailSMResponse");
    private final static QName _QuerySMResponse_QNAME = new QName("http://mtwebservice.wisdom.com/", "querySMResponse");
    private final static QName _QueryFailSM_QNAME = new QName("http://mtwebservice.wisdom.com/", "queryFailSM");
    private final static QName _SendSM_QNAME = new QName("http://mtwebservice.wisdom.com/", "sendSM");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.wisdom.mtwebservice.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QuerySMResponse }
     * 
     */
    public QuerySMResponse createQuerySMResponse() {
        return new QuerySMResponse();
    }

    /**
     * Create an instance of {@link QuerySM }
     * 
     */
    public QuerySM createQuerySM() {
        return new QuerySM();
    }

    /**
     * Create an instance of {@link QueryFailSMResponse }
     * 
     */
    public QueryFailSMResponse createQueryFailSMResponse() {
        return new QueryFailSMResponse();
    }

    /**
     * Create an instance of {@link SendSMResponse }
     * 
     */
    public SendSMResponse createSendSMResponse() {
        return new SendSMResponse();
    }

    /**
     * Create an instance of {@link QueryFailSM }
     * 
     */
    public QueryFailSM createQueryFailSM() {
        return new QueryFailSM();
    }

    /**
     * Create an instance of {@link SendSM }
     * 
     */
    public SendSM createSendSM() {
        return new SendSM();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuerySM }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mtwebservice.wisdom.com/", name = "querySM")
    public JAXBElement<QuerySM> createQuerySM(QuerySM value) {
        return new JAXBElement<QuerySM>(_QuerySM_QNAME, QuerySM.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSMResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mtwebservice.wisdom.com/", name = "sendSMResponse")
    public JAXBElement<SendSMResponse> createSendSMResponse(SendSMResponse value) {
        return new JAXBElement<SendSMResponse>(_SendSMResponse_QNAME, SendSMResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryFailSMResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mtwebservice.wisdom.com/", name = "queryFailSMResponse")
    public JAXBElement<QueryFailSMResponse> createQueryFailSMResponse(QueryFailSMResponse value) {
        return new JAXBElement<QueryFailSMResponse>(_QueryFailSMResponse_QNAME, QueryFailSMResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuerySMResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mtwebservice.wisdom.com/", name = "querySMResponse")
    public JAXBElement<QuerySMResponse> createQuerySMResponse(QuerySMResponse value) {
        return new JAXBElement<QuerySMResponse>(_QuerySMResponse_QNAME, QuerySMResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryFailSM }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mtwebservice.wisdom.com/", name = "queryFailSM")
    public JAXBElement<QueryFailSM> createQueryFailSM(QueryFailSM value) {
        return new JAXBElement<QueryFailSM>(_QueryFailSM_QNAME, QueryFailSM.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSM }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mtwebservice.wisdom.com/", name = "sendSM")
    public JAXBElement<SendSM> createSendSM(SendSM value) {
        return new JAXBElement<SendSM>(_SendSM_QNAME, SendSM.class, null, value);
    }

}
