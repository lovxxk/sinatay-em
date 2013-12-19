
/**
 * QueryACDefStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.0  Built on : May 17, 2011 (04:19:43 IST)
 */
        package cn.com.sinosoft.portalModule.interfacePortal.soap.stub;

import cn.com.sinosoft.portalModule.interfacePortal.soap.util.SoapHelper;

        

        /*
        *  QueryACDefStub java implementation
        */

        
        public class QueryACDefStub extends org.apache.axis2.client.Stub
        {
        protected org.apache.axis2.description.AxisOperation[] _operations;

        //hashmaps to keep the fault mapping
        private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
        private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
        private java.util.HashMap faultMessageMap = new java.util.HashMap();

        private static int counter = 0;

        private static synchronized java.lang.String getUniqueSuffix(){
            // reset the counter if it is greater than 99999
            if (counter > 99999){
                counter = 0;
            }
            counter = counter + 1; 
            return java.lang.Long.toString(java.lang.System.currentTimeMillis()) + "_" + counter;
        }

    
    private void populateAxisService() throws org.apache.axis2.AxisFault {

     //creating the Service with a unique name
     _service = new org.apache.axis2.description.AxisService("QueryACDef" + getUniqueSuffix());
     addAnonymousOperations();

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[1];
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://www.e-chinalife.com/soa/", "queryACDef"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[0]=__operation;
            
        
        }

    //populates the faults
    private void populateFaults(){
         


    }

    /**
      *Constructor that takes in a configContext
      */

    public QueryACDefStub(org.apache.axis2.context.ConfigurationContext configurationContext,
       java.lang.String targetEndpoint)
       throws org.apache.axis2.AxisFault {
         this(configurationContext,targetEndpoint,false);
   }


   /**
     * Constructor that takes in a configContext  and useseperate listner
     */
   public QueryACDefStub(org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint, boolean useSeparateListener)
        throws org.apache.axis2.AxisFault {
         //To populate AxisService
         populateAxisService();
         populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,_service);
        
	
        _serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);
        
            //Set the soap version
            _serviceClient.getOptions().setSoapVersionURI(org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
        
    
    }

    /**
     * Default Constructor
     */
    public QueryACDefStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {
        
                    this(configurationContext,"http://192.168.1.107:8080/e-selfcard/services/QueryACDef.QueryACDefHttpSoap12Endpoint/" );
                
    }

    /**
     * Default Constructor
     */
    public QueryACDefStub() throws org.apache.axis2.AxisFault {
        
                    this("http://192.168.1.107:8080/e-selfcard/services/QueryACDef.QueryACDefHttpSoap12Endpoint/" );
                
    }

    /**
     * Constructor taking the target endpoint
     */
    public QueryACDefStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null,targetEndpoint);
    }



        
                    /**
                     * Auto generated method signature
                     * 
                     * @see cn.com.sinosoft.ebusiness.sys.interfacePortal.soap.stub.QueryACDef#queryACDef
                     * @param queryACDef0
                    
                     */

                    

                            public  cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefResponseE queryACDef(

                            cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefE queryACDef0,String tranNumber)
                        

                    throws java.rmi.RemoteException
                    
                    {
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
              _operationClient.getOptions().setAction("urn:queryACDef");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    queryACDef0,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.e-chinalife.com/soa/",
                                                    "queryACDef")), new javax.xml.namespace.QName("http://www.e-chinalife.com/soa/",
                                                    "queryACDef"));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                // …Ë÷√∑µªÿÕ∑
                if(_returnEnv.getHeader() != null &&_returnEnv.getHeader().getFirstElement() !=null)
                	SoapHelper.getHeadElement().put(tranNumber, _returnEnv.getHeader().getFirstElement());
                
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefResponseE.class,
                                              getEnvelopeNamespaces(_returnEnv));

                               
                                        return (cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefResponseE)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"queryACDef"))){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"queryACDef"));
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"queryACDef"));
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                if (_messageContext.getTransportOut() != null) {
                      _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                }
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see cn.com.sinosoft.ebusiness.sys.interfacePortal.soap.stub.QueryACDef#startqueryACDef
                    * @param queryACDef0
                
                */
                public  void startqueryACDef(

                 cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefE queryACDef0,

                  final cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
             _operationClient.getOptions().setAction("urn:queryACDef");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    queryACDef0,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.e-chinalife.com/soa/",
                                                    "queryACDef")), new javax.xml.namespace.QName("http://www.e-chinalife.com/soa/",
                                                    "queryACDef"));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                            try {
                                org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();
                                
                                        java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                                                                         cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefResponseE.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultqueryACDef(
                                        (cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefResponseE)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorqueryACDef(e);
                            }
                            }

                            public void onError(java.lang.Exception error) {
								if (error instanceof org.apache.axis2.AxisFault) {
									org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
									org.apache.axiom.om.OMElement faultElt = f.getDetail();
									if (faultElt!=null){
										if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"queryACDef"))){
											//make the fault by reflection
											try{
													java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"queryACDef"));
													java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
													java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();
													//message class
													java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),"queryACDef"));
														java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
													java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
													java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[]{messageClass});
													m.invoke(ex,new java.lang.Object[]{messageObject});
													
					
										            callback.receiveErrorqueryACDef(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorqueryACDef(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorqueryACDef(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorqueryACDef(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorqueryACDef(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorqueryACDef(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorqueryACDef(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorqueryACDef(f);
                                            }
									    } else {
										    callback.receiveErrorqueryACDef(f);
									    }
									} else {
									    callback.receiveErrorqueryACDef(f);
									}
								} else {
								    callback.receiveErrorqueryACDef(error);
								}
                            }

                            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                                onError(fault);
                            }

                            public void onComplete() {
                                try {
                                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                                } catch (org.apache.axis2.AxisFault axisFault) {
                                    callback.receiveErrorqueryACDef(axisFault);
                                }
                            }
                });
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[0].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[0].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                


       /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
       private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
            returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
       return returnMap;
    }

    
    
    private javax.xml.namespace.QName[] opNameArray = null;
    private boolean optimizeContent(javax.xml.namespace.QName opName) {
        

        if (opNameArray == null) {
            return false;
        }
        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;   
            }
        }
        return false;
    }
     //http://192.168.1.107:8080/e-selfcard/services/QueryACDef.QueryACDefHttpSoap12Endpoint/
        public static class QueryACDef
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = QueryACDef
                Namespace URI = http://QueryACDef.server.soa.com/xsd
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for CARDNO
                        */

                        
                                    protected java.lang.String localCARDNO ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCARDNOTracker = false ;

                           public boolean isCARDNOSpecified(){
                               return localCARDNOTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCARDNO(){
                               return localCARDNO;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CARDNO
                               */
                               public void setCARDNO(java.lang.String param){
                            localCARDNOTracker = true;
                                   
                                            this.localCARDNO=param;
                                    

                               }
                            

                        /**
                        * field for PASSWORD
                        */

                        
                                    protected java.lang.String localPASSWORD ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPASSWORDTracker = false ;

                           public boolean isPASSWORDSpecified(){
                               return localPASSWORDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPASSWORD(){
                               return localPASSWORD;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PASSWORD
                               */
                               public void setPASSWORD(java.lang.String param){
                            localPASSWORDTracker = true;
                                   
                                            this.localPASSWORD=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://QueryACDef.server.soa.com/xsd");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":QueryACDef",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "QueryACDef",
                           xmlWriter);
                   }

               
                   }
                if (localCARDNOTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "CARDNO", xmlWriter);
                             

                                          if (localCARDNO==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCARDNO);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPASSWORDTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "PASSWORD", xmlWriter);
                             

                                          if (localPASSWORD==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPASSWORD);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://QueryACDef.server.soa.com/xsd")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localCARDNOTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "CARDNO"));
                                 
                                         elementList.add(localCARDNO==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCARDNO));
                                    } if (localPASSWORDTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "PASSWORD"));
                                 
                                         elementList.add(localPASSWORD==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPASSWORD));
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static QueryACDef parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            QueryACDef object =
                new QueryACDef();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"QueryACDef".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (QueryACDef)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","CARDNO").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCARDNO(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","PASSWORD").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPASSWORD(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class POLICY_PERIOD_INFO
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = POLICY_PERIOD_INFO
                Namespace URI = http://QueryACDef.server.soa.com/xsd
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for ISSPECIAL_AGREEMENT
                        */

                        
                                    protected java.lang.String localISSPECIAL_AGREEMENT ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISSPECIAL_AGREEMENTTracker = false ;

                           public boolean isISSPECIAL_AGREEMENTSpecified(){
                               return localISSPECIAL_AGREEMENTTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISSPECIAL_AGREEMENT(){
                               return localISSPECIAL_AGREEMENT;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISSPECIAL_AGREEMENT
                               */
                               public void setISSPECIAL_AGREEMENT(java.lang.String param){
                            localISSPECIAL_AGREEMENTTracker = true;
                                   
                                            this.localISSPECIAL_AGREEMENT=param;
                                    

                               }
                            

                        /**
                        * field for POLICY_PERIOD
                        */

                        
                                    protected java.lang.String localPOLICY_PERIOD ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPOLICY_PERIODTracker = false ;

                           public boolean isPOLICY_PERIODSpecified(){
                               return localPOLICY_PERIODTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPOLICY_PERIOD(){
                               return localPOLICY_PERIOD;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param POLICY_PERIOD
                               */
                               public void setPOLICY_PERIOD(java.lang.String param){
                            localPOLICY_PERIODTracker = true;
                                   
                                            this.localPOLICY_PERIOD=param;
                                    

                               }
                            

                        /**
                        * field for SPECIAL_AGREEMENT
                        */

                        
                                    protected java.lang.String localSPECIAL_AGREEMENT ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSPECIAL_AGREEMENTTracker = false ;

                           public boolean isSPECIAL_AGREEMENTSpecified(){
                               return localSPECIAL_AGREEMENTTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSPECIAL_AGREEMENT(){
                               return localSPECIAL_AGREEMENT;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SPECIAL_AGREEMENT
                               */
                               public void setSPECIAL_AGREEMENT(java.lang.String param){
                            localSPECIAL_AGREEMENTTracker = true;
                                   
                                            this.localSPECIAL_AGREEMENT=param;
                                    

                               }
                            

                        /**
                        * field for UNIT
                        */

                        
                                    protected java.lang.String localUNIT ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localUNITTracker = false ;

                           public boolean isUNITSpecified(){
                               return localUNITTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getUNIT(){
                               return localUNIT;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param UNIT
                               */
                               public void setUNIT(java.lang.String param){
                            localUNITTracker = true;
                                   
                                            this.localUNIT=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://QueryACDef.server.soa.com/xsd");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":POLICY_PERIOD_INFO",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "POLICY_PERIOD_INFO",
                           xmlWriter);
                   }

               
                   }
                if (localISSPECIAL_AGREEMENTTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISSPECIAL_AGREEMENT", xmlWriter);
                             

                                          if (localISSPECIAL_AGREEMENT==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISSPECIAL_AGREEMENT);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPOLICY_PERIODTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "POLICY_PERIOD", xmlWriter);
                             

                                          if (localPOLICY_PERIOD==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPOLICY_PERIOD);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localSPECIAL_AGREEMENTTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "SPECIAL_AGREEMENT", xmlWriter);
                             

                                          if (localSPECIAL_AGREEMENT==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSPECIAL_AGREEMENT);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localUNITTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "UNIT", xmlWriter);
                             

                                          if (localUNIT==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localUNIT);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://QueryACDef.server.soa.com/xsd")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localISSPECIAL_AGREEMENTTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISSPECIAL_AGREEMENT"));
                                 
                                         elementList.add(localISSPECIAL_AGREEMENT==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISSPECIAL_AGREEMENT));
                                    } if (localPOLICY_PERIODTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "POLICY_PERIOD"));
                                 
                                         elementList.add(localPOLICY_PERIOD==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPOLICY_PERIOD));
                                    } if (localSPECIAL_AGREEMENTTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "SPECIAL_AGREEMENT"));
                                 
                                         elementList.add(localSPECIAL_AGREEMENT==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSPECIAL_AGREEMENT));
                                    } if (localUNITTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "UNIT"));
                                 
                                         elementList.add(localUNIT==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUNIT));
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static POLICY_PERIOD_INFO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            POLICY_PERIOD_INFO object =
                new POLICY_PERIOD_INFO();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"POLICY_PERIOD_INFO".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (POLICY_PERIOD_INFO)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISSPECIAL_AGREEMENT").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISSPECIAL_AGREEMENT(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","POLICY_PERIOD").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPOLICY_PERIOD(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","SPECIAL_AGREEMENT").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSPECIAL_AGREEMENT(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","UNIT").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setUNIT(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class REMARK_NAMES
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = REMARK_NAMES
                Namespace URI = http://QueryACDef.server.soa.com/xsd
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for REMARK_NAME
                        * This was an Array!
                        */

                        
                                    protected REMARK_NAME[] localREMARK_NAME ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localREMARK_NAMETracker = false ;

                           public boolean isREMARK_NAMESpecified(){
                               return localREMARK_NAMETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return REMARK_NAME[]
                           */
                           public  REMARK_NAME[] getREMARK_NAME(){
                               return localREMARK_NAME;
                           }

                           
                        


                               
                              /**
                               * validate the array for REMARK_NAME
                               */
                              protected void validateREMARK_NAME(REMARK_NAME[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param REMARK_NAME
                              */
                              public void setREMARK_NAME(REMARK_NAME[] param){
                              
                                   validateREMARK_NAME(param);

                               localREMARK_NAMETracker = true;
                                      
                                      this.localREMARK_NAME=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param REMARK_NAME
                             */
                             public void addREMARK_NAME(REMARK_NAME param){
                                   if (localREMARK_NAME == null){
                                   localREMARK_NAME = new REMARK_NAME[]{};
                                   }

                            
                                 //update the setting tracker
                                localREMARK_NAMETracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localREMARK_NAME);
                               list.add(param);
                               this.localREMARK_NAME =
                             (REMARK_NAME[])list.toArray(
                            new REMARK_NAME[list.size()]);

                             }
                             

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://QueryACDef.server.soa.com/xsd");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":REMARK_NAMES",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "REMARK_NAMES",
                           xmlWriter);
                   }

               
                   }
                if (localREMARK_NAMETracker){
                                       if (localREMARK_NAME!=null){
                                            for (int i = 0;i < localREMARK_NAME.length;i++){
                                                if (localREMARK_NAME[i] != null){
                                                 localREMARK_NAME[i].serialize(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","REMARK_NAME"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                            writeStartElement(null, "http://QueryACDef.server.soa.com/xsd", "REMARK_NAME", xmlWriter);

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                writeStartElement(null, "http://QueryACDef.server.soa.com/xsd", "REMARK_NAME", xmlWriter);

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                 }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://QueryACDef.server.soa.com/xsd")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localREMARK_NAMETracker){
                             if (localREMARK_NAME!=null) {
                                 for (int i = 0;i < localREMARK_NAME.length;i++){

                                    if (localREMARK_NAME[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                          "REMARK_NAME"));
                                         elementList.add(localREMARK_NAME[i]);
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                          "REMARK_NAME"));
                                                elementList.add(null);
                                            
                                    }

                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                          "REMARK_NAME"));
                                        elementList.add(localREMARK_NAME);
                                    
                             }

                        }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static REMARK_NAMES parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            REMARK_NAMES object =
                new REMARK_NAMES();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"REMARK_NAMES".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (REMARK_NAMES)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list1 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","REMARK_NAME").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list1.add(null);
                                                              reader.next();
                                                          } else {
                                                        list1.add(REMARK_NAME.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone1 = false;
                                                        while(!loopDone1){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone1 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","REMARK_NAME").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list1.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list1.add(REMARK_NAME.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone1 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setREMARK_NAME((REMARK_NAME[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                REMARK_NAME.class,
                                                                list1));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class REMARK_NAME
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = REMARK_NAME
                Namespace URI = http://QueryACDef.server.soa.com/xsd
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for CARD_REMARK_ID
                        */

                        
                                    protected java.lang.String localCARD_REMARK_ID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCARD_REMARK_IDTracker = false ;

                           public boolean isCARD_REMARK_IDSpecified(){
                               return localCARD_REMARK_IDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCARD_REMARK_ID(){
                               return localCARD_REMARK_ID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CARD_REMARK_ID
                               */
                               public void setCARD_REMARK_ID(java.lang.String param){
                            localCARD_REMARK_IDTracker = true;
                                   
                                            this.localCARD_REMARK_ID=param;
                                    

                               }
                            

                        /**
                        * field for NAME
                        */

                        
                                    protected java.lang.String localNAME ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNAMETracker = false ;

                           public boolean isNAMESpecified(){
                               return localNAMETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNAME(){
                               return localNAME;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param NAME
                               */
                               public void setNAME(java.lang.String param){
                            localNAMETracker = true;
                                   
                                            this.localNAME=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://QueryACDef.server.soa.com/xsd");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":REMARK_NAME",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "REMARK_NAME",
                           xmlWriter);
                   }

               
                   }
                if (localCARD_REMARK_IDTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "CARD_REMARK_ID", xmlWriter);
                             

                                          if (localCARD_REMARK_ID==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCARD_REMARK_ID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNAMETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "NAME", xmlWriter);
                             

                                          if (localNAME==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNAME);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://QueryACDef.server.soa.com/xsd")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localCARD_REMARK_IDTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "CARD_REMARK_ID"));
                                 
                                         elementList.add(localCARD_REMARK_ID==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCARD_REMARK_ID));
                                    } if (localNAMETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "NAME"));
                                 
                                         elementList.add(localNAME==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNAME));
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static REMARK_NAME parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            REMARK_NAME object =
                new REMARK_NAME();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"REMARK_NAME".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (REMARK_NAME)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","CARD_REMARK_ID").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCARD_REMARK_ID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","NAME").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNAME(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://QueryACDef.server.soa.com/xsd".equals(namespaceURI) &&
                  "QueryACDef".equals(typeName)){
                   
                            return  QueryACDef.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://QueryACDef.server.soa.com/xsd".equals(namespaceURI) &&
                  "REMARK_NAMES".equals(typeName)){
                   
                            return  REMARK_NAMES.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://QueryACDef.server.soa.com/xsd".equals(namespaceURI) &&
                  "POLICY_PERIOD_INFO".equals(typeName)){
                   
                            return  POLICY_PERIOD_INFO.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://QueryACDef.server.soa.com/xsd".equals(namespaceURI) &&
                  "REMARK_NAME".equals(typeName)){
                   
                            return  REMARK_NAME.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://QueryACDef.server.soa.com/xsd".equals(namespaceURI) &&
                  "QueryACDefResponse".equals(typeName)){
                   
                            return  QueryACDefResponse.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    
        public static class QueryACDefE
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://www.e-chinalife.com/soa/",
                "queryACDef",
                "ns2");

            

                        /**
                        * field for QueryACDef
                        */

                        
                                    protected QueryACDef localQueryACDef ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localQueryACDefTracker = false ;

                           public boolean isQueryACDefSpecified(){
                               return localQueryACDefTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return QueryACDef
                           */
                           public  QueryACDef getQueryACDef(){
                               return localQueryACDef;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param QueryACDef
                               */
                               public void setQueryACDef(QueryACDef param){
                            localQueryACDefTracker = true;
                                   
                                            this.localQueryACDef=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME);
               return factory.createOMElement(dataSource,MY_QNAME);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.e-chinalife.com/soa/");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":queryACDef",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "queryACDef",
                           xmlWriter);
                   }

               
                   }
                if (localQueryACDefTracker){
                                    if (localQueryACDef==null){

                                        writeStartElement(null, "http://www.e-chinalife.com/soa/", "queryACDef", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localQueryACDef.serialize(new javax.xml.namespace.QName("http://www.e-chinalife.com/soa/","queryACDef"),
                                        xmlWriter);
                                    }
                                }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.e-chinalife.com/soa/")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localQueryACDefTracker){
                            elementList.add(new javax.xml.namespace.QName("http://www.e-chinalife.com/soa/",
                                                                      "queryACDef"));
                            
                            
                                    elementList.add(localQueryACDef==null?null:
                                    localQueryACDef);
                                }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static QueryACDefE parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            QueryACDefE object =
                new QueryACDefE();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                   nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                   if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                         // Skip the element and report the null value.  It cannot have subelements.
                         while (!reader.isEndElement())
                             reader.next();
                         
                                 return null;
                             

                   }
                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"queryACDef".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (QueryACDefE)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.e-chinalife.com/soa/","queryACDef").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setQueryACDef(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setQueryACDef(QueryACDef.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class QueryACDefResponse
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = QueryACDefResponse
                Namespace URI = http://QueryACDef.server.soa.com/xsd
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for ACTIVE_CHANNEL
                        */

                        
                                    protected java.lang.String localACTIVE_CHANNEL ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localACTIVE_CHANNELTracker = false ;

                           public boolean isACTIVE_CHANNELSpecified(){
                               return localACTIVE_CHANNELTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getACTIVE_CHANNEL(){
                               return localACTIVE_CHANNEL;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ACTIVE_CHANNEL
                               */
                               public void setACTIVE_CHANNEL(java.lang.String param){
                            localACTIVE_CHANNELTracker = true;
                                   
                                            this.localACTIVE_CHANNEL=param;
                                    

                               }
                            

                        /**
                        * field for BRANCHNO
                        */

                        
                                    protected java.lang.String localBRANCHNO ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBRANCHNOTracker = false ;

                           public boolean isBRANCHNOSpecified(){
                               return localBRANCHNOTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBRANCHNO(){
                               return localBRANCHNO;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BRANCHNO
                               */
                               public void setBRANCHNO(java.lang.String param){
                            localBRANCHNOTracker = true;
                                   
                                            this.localBRANCHNO=param;
                                    

                               }
                            

                        /**
                        * field for CARDNAME
                        */

                        
                                    protected java.lang.String localCARDNAME ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCARDNAMETracker = false ;

                           public boolean isCARDNAMESpecified(){
                               return localCARDNAMETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCARDNAME(){
                               return localCARDNAME;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CARDNAME
                               */
                               public void setCARDNAME(java.lang.String param){
                            localCARDNAMETracker = true;
                                   
                                            this.localCARDNAME=param;
                                    

                               }
                            

                        /**
                        * field for CARD_TYPE
                        */

                        
                                    protected java.lang.String localCARD_TYPE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCARD_TYPETracker = false ;

                           public boolean isCARD_TYPESpecified(){
                               return localCARD_TYPETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCARD_TYPE(){
                               return localCARD_TYPE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CARD_TYPE
                               */
                               public void setCARD_TYPE(java.lang.String param){
                            localCARD_TYPETracker = true;
                                   
                                            this.localCARD_TYPE=param;
                                    

                               }
                            

                        /**
                        * field for CHANGE_FLAG
                        */

                        
                                    protected java.lang.String localCHANGE_FLAG ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCHANGE_FLAGTracker = false ;

                           public boolean isCHANGE_FLAGSpecified(){
                               return localCHANGE_FLAGTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCHANGE_FLAG(){
                               return localCHANGE_FLAG;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CHANGE_FLAG
                               */
                               public void setCHANGE_FLAG(java.lang.String param){
                            localCHANGE_FLAGTracker = true;
                                   
                                            this.localCHANGE_FLAG=param;
                                    

                               }
                            

                        /**
                        * field for CREATE_DATE
                        */

                        
                                    protected java.lang.String localCREATE_DATE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCREATE_DATETracker = false ;

                           public boolean isCREATE_DATESpecified(){
                               return localCREATE_DATETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCREATE_DATE(){
                               return localCREATE_DATE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CREATE_DATE
                               */
                               public void setCREATE_DATE(java.lang.String param){
                            localCREATE_DATETracker = true;
                                   
                                            this.localCREATE_DATE=param;
                                    

                               }
                            

                        /**
                        * field for EFFECTIVE_DATE_FLAG
                        */

                        
                                    protected java.lang.String localEFFECTIVE_DATE_FLAG ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEFFECTIVE_DATE_FLAGTracker = false ;

                           public boolean isEFFECTIVE_DATE_FLAGSpecified(){
                               return localEFFECTIVE_DATE_FLAGTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getEFFECTIVE_DATE_FLAG(){
                               return localEFFECTIVE_DATE_FLAG;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EFFECTIVE_DATE_FLAG
                               */
                               public void setEFFECTIVE_DATE_FLAG(java.lang.String param){
                            localEFFECTIVE_DATE_FLAGTracker = true;
                                   
                                            this.localEFFECTIVE_DATE_FLAG=param;
                                    

                               }
                            

                        /**
                        * field for EFFECTIVE_DATE_INTERVAL
                        */

                        
                                    protected java.lang.String localEFFECTIVE_DATE_INTERVAL ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEFFECTIVE_DATE_INTERVALTracker = false ;

                           public boolean isEFFECTIVE_DATE_INTERVALSpecified(){
                               return localEFFECTIVE_DATE_INTERVALTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getEFFECTIVE_DATE_INTERVAL(){
                               return localEFFECTIVE_DATE_INTERVAL;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EFFECTIVE_DATE_INTERVAL
                               */
                               public void setEFFECTIVE_DATE_INTERVAL(java.lang.String param){
                            localEFFECTIVE_DATE_INTERVALTracker = true;
                                   
                                            this.localEFFECTIVE_DATE_INTERVAL=param;
                                    

                               }
                            

                        /**
                        * field for EFFECTIVE_TYPE
                        */

                        
                                    protected java.lang.String localEFFECTIVE_TYPE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEFFECTIVE_TYPETracker = false ;

                           public boolean isEFFECTIVE_TYPESpecified(){
                               return localEFFECTIVE_TYPETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getEFFECTIVE_TYPE(){
                               return localEFFECTIVE_TYPE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EFFECTIVE_TYPE
                               */
                               public void setEFFECTIVE_TYPE(java.lang.String param){
                            localEFFECTIVE_TYPETracker = true;
                                   
                                            this.localEFFECTIVE_TYPE=param;
                                    

                               }
                            

                        /**
                        * field for EXPIRE_DATE
                        */

                        
                                    protected java.lang.String localEXPIRE_DATE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEXPIRE_DATETracker = false ;

                           public boolean isEXPIRE_DATESpecified(){
                               return localEXPIRE_DATETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getEXPIRE_DATE(){
                               return localEXPIRE_DATE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EXPIRE_DATE
                               */
                               public void setEXPIRE_DATE(java.lang.String param){
                            localEXPIRE_DATETracker = true;
                                   
                                            this.localEXPIRE_DATE=param;
                                    

                               }
                            

                        /**
                        * field for IDDIF_MINOR_AMOUNT
                        */

                        
                                    protected java.lang.String localIDDIF_MINOR_AMOUNT ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIDDIF_MINOR_AMOUNTTracker = false ;

                           public boolean isIDDIF_MINOR_AMOUNTSpecified(){
                               return localIDDIF_MINOR_AMOUNTTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getIDDIF_MINOR_AMOUNT(){
                               return localIDDIF_MINOR_AMOUNT;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IDDIF_MINOR_AMOUNT
                               */
                               public void setIDDIF_MINOR_AMOUNT(java.lang.String param){
                            localIDDIF_MINOR_AMOUNTTracker = true;
                                   
                                            this.localIDDIF_MINOR_AMOUNT=param;
                                    

                               }
                            

                        /**
                        * field for ISBEN
                        */

                        
                                    protected java.lang.String localISBEN ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISBENTracker = false ;

                           public boolean isISBENSpecified(){
                               return localISBENTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISBEN(){
                               return localISBEN;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISBEN
                               */
                               public void setISBEN(java.lang.String param){
                            localISBENTracker = true;
                                   
                                            this.localISBEN=param;
                                    

                               }
                            

                        /**
                        * field for ISCHECK_AMOUNT
                        */

                        
                                    protected java.lang.String localISCHECK_AMOUNT ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISCHECK_AMOUNTTracker = false ;

                           public boolean isISCHECK_AMOUNTSpecified(){
                               return localISCHECK_AMOUNTTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISCHECK_AMOUNT(){
                               return localISCHECK_AMOUNT;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISCHECK_AMOUNT
                               */
                               public void setISCHECK_AMOUNT(java.lang.String param){
                            localISCHECK_AMOUNTTracker = true;
                                   
                                            this.localISCHECK_AMOUNT=param;
                                    

                               }
                            

                        /**
                        * field for ISCONTACT_INFO
                        */

                        
                                    protected java.lang.String localISCONTACT_INFO ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISCONTACT_INFOTracker = false ;

                           public boolean isISCONTACT_INFOSpecified(){
                               return localISCONTACT_INFOTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISCONTACT_INFO(){
                               return localISCONTACT_INFO;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISCONTACT_INFO
                               */
                               public void setISCONTACT_INFO(java.lang.String param){
                            localISCONTACT_INFOTracker = true;
                                   
                                            this.localISCONTACT_INFO=param;
                                    

                               }
                            

                        /**
                        * field for ISCORE
                        */

                        
                                    protected java.lang.String localISCORE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISCORETracker = false ;

                           public boolean isISCORESpecified(){
                               return localISCORETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISCORE(){
                               return localISCORE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISCORE
                               */
                               public void setISCORE(java.lang.String param){
                            localISCORETracker = true;
                                   
                                            this.localISCORE=param;
                                    

                               }
                            

                        /**
                        * field for ISEFFECTIVE_DATE
                        */

                        
                                    protected java.lang.String localISEFFECTIVE_DATE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISEFFECTIVE_DATETracker = false ;

                           public boolean isISEFFECTIVE_DATESpecified(){
                               return localISEFFECTIVE_DATETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISEFFECTIVE_DATE(){
                               return localISEFFECTIVE_DATE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISEFFECTIVE_DATE
                               */
                               public void setISEFFECTIVE_DATE(java.lang.String param){
                            localISEFFECTIVE_DATETracker = true;
                                   
                                            this.localISEFFECTIVE_DATE=param;
                                    

                               }
                            

                        /**
                        * field for ISIDCARD
                        */

                        
                                    protected java.lang.String localISIDCARD ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISIDCARDTracker = false ;

                           public boolean isISIDCARDSpecified(){
                               return localISIDCARDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISIDCARD(){
                               return localISIDCARD;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISIDCARD
                               */
                               public void setISIDCARD(java.lang.String param){
                            localISIDCARDTracker = true;
                                   
                                            this.localISIDCARD=param;
                                    

                               }
                            

                        /**
                        * field for ISMINOR
                        */

                        
                                    protected java.lang.String localISMINOR ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISMINORTracker = false ;

                           public boolean isISMINORSpecified(){
                               return localISMINORTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISMINOR(){
                               return localISMINOR;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISMINOR
                               */
                               public void setISMINOR(java.lang.String param){
                            localISMINORTracker = true;
                                   
                                            this.localISMINOR=param;
                                    

                               }
                            

                        /**
                        * field for ISMORE_INSURED
                        */

                        
                                    protected java.lang.String localISMORE_INSURED ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISMORE_INSUREDTracker = false ;

                           public boolean isISMORE_INSUREDSpecified(){
                               return localISMORE_INSUREDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISMORE_INSURED(){
                               return localISMORE_INSURED;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISMORE_INSURED
                               */
                               public void setISMORE_INSURED(java.lang.String param){
                            localISMORE_INSUREDTracker = true;
                                   
                                            this.localISMORE_INSURED=param;
                                    

                               }
                            

                        /**
                        * field for ISNAME
                        */

                        
                                    protected java.lang.String localISNAME ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISNAMETracker = false ;

                           public boolean isISNAMESpecified(){
                               return localISNAMETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISNAME(){
                               return localISNAME;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISNAME
                               */
                               public void setISNAME(java.lang.String param){
                            localISNAMETracker = true;
                                   
                                            this.localISNAME=param;
                                    

                               }
                            

                        /**
                        * field for ISPOLICY
                        */

                        
                                    protected java.lang.String localISPOLICY ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISPOLICYTracker = false ;

                           public boolean isISPOLICYSpecified(){
                               return localISPOLICYTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISPOLICY(){
                               return localISPOLICY;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISPOLICY
                               */
                               public void setISPOLICY(java.lang.String param){
                            localISPOLICYTracker = true;
                                   
                                            this.localISPOLICY=param;
                                    

                               }
                            

                        /**
                        * field for ISPREMIUM
                        */

                        
                                    protected java.lang.String localISPREMIUM ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISPREMIUMTracker = false ;

                           public boolean isISPREMIUMSpecified(){
                               return localISPREMIUMTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISPREMIUM(){
                               return localISPREMIUM;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISPREMIUM
                               */
                               public void setISPREMIUM(java.lang.String param){
                            localISPREMIUMTracker = true;
                                   
                                            this.localISPREMIUM=param;
                                    

                               }
                            

                        /**
                        * field for ISREALTIME_INFORCE
                        */

                        
                                    protected java.lang.String localISREALTIME_INFORCE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISREALTIME_INFORCETracker = false ;

                           public boolean isISREALTIME_INFORCESpecified(){
                               return localISREALTIME_INFORCETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISREALTIME_INFORCE(){
                               return localISREALTIME_INFORCE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISREALTIME_INFORCE
                               */
                               public void setISREALTIME_INFORCE(java.lang.String param){
                            localISREALTIME_INFORCETracker = true;
                                   
                                            this.localISREALTIME_INFORCE=param;
                                    

                               }
                            

                        /**
                        * field for ISREMARKS
                        */

                        
                                    protected java.lang.String localISREMARKS ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISREMARKSTracker = false ;

                           public boolean isISREMARKSSpecified(){
                               return localISREMARKSTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISREMARKS(){
                               return localISREMARKS;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISREMARKS
                               */
                               public void setISREMARKS(java.lang.String param){
                            localISREMARKSTracker = true;
                                   
                                            this.localISREMARKS=param;
                                    

                               }
                            

                        /**
                        * field for ISRENEWAL
                        */

                        
                                    protected java.lang.String localISRENEWAL ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localISRENEWALTracker = false ;

                           public boolean isISRENEWALSpecified(){
                               return localISRENEWALTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getISRENEWAL(){
                               return localISRENEWAL;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ISRENEWAL
                               */
                               public void setISRENEWAL(java.lang.String param){
                            localISRENEWALTracker = true;
                                   
                                            this.localISRENEWAL=param;
                                    

                               }
                            

                        /**
                        * field for MAX_HOLDERTYPE
                        */

                        
                                    protected java.lang.String localMAX_HOLDERTYPE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMAX_HOLDERTYPETracker = false ;

                           public boolean isMAX_HOLDERTYPESpecified(){
                               return localMAX_HOLDERTYPETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMAX_HOLDERTYPE(){
                               return localMAX_HOLDERTYPE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MAX_HOLDERTYPE
                               */
                               public void setMAX_HOLDERTYPE(java.lang.String param){
                            localMAX_HOLDERTYPETracker = true;
                                   
                                            this.localMAX_HOLDERTYPE=param;
                                    

                               }
                            

                        /**
                        * field for MAX_HOLDER_AGE
                        */

                        
                                    protected java.lang.String localMAX_HOLDER_AGE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMAX_HOLDER_AGETracker = false ;

                           public boolean isMAX_HOLDER_AGESpecified(){
                               return localMAX_HOLDER_AGETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMAX_HOLDER_AGE(){
                               return localMAX_HOLDER_AGE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MAX_HOLDER_AGE
                               */
                               public void setMAX_HOLDER_AGE(java.lang.String param){
                            localMAX_HOLDER_AGETracker = true;
                                   
                                            this.localMAX_HOLDER_AGE=param;
                                    

                               }
                            

                        /**
                        * field for MAX_INSURED_AGE
                        */

                        
                                    protected java.lang.String localMAX_INSURED_AGE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMAX_INSURED_AGETracker = false ;

                           public boolean isMAX_INSURED_AGESpecified(){
                               return localMAX_INSURED_AGETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMAX_INSURED_AGE(){
                               return localMAX_INSURED_AGE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MAX_INSURED_AGE
                               */
                               public void setMAX_INSURED_AGE(java.lang.String param){
                            localMAX_INSURED_AGETracker = true;
                                   
                                            this.localMAX_INSURED_AGE=param;
                                    

                               }
                            

                        /**
                        * field for MAX_INSURED_COUNT
                        */

                        
                                    protected java.lang.String localMAX_INSURED_COUNT ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMAX_INSURED_COUNTTracker = false ;

                           public boolean isMAX_INSURED_COUNTSpecified(){
                               return localMAX_INSURED_COUNTTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMAX_INSURED_COUNT(){
                               return localMAX_INSURED_COUNT;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MAX_INSURED_COUNT
                               */
                               public void setMAX_INSURED_COUNT(java.lang.String param){
                            localMAX_INSURED_COUNTTracker = true;
                                   
                                            this.localMAX_INSURED_COUNT=param;
                                    

                               }
                            

                        /**
                        * field for MAX_INSURED_TYPE
                        */

                        
                                    protected java.lang.String localMAX_INSURED_TYPE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMAX_INSURED_TYPETracker = false ;

                           public boolean isMAX_INSURED_TYPESpecified(){
                               return localMAX_INSURED_TYPETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMAX_INSURED_TYPE(){
                               return localMAX_INSURED_TYPE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MAX_INSURED_TYPE
                               */
                               public void setMAX_INSURED_TYPE(java.lang.String param){
                            localMAX_INSURED_TYPETracker = true;
                                   
                                            this.localMAX_INSURED_TYPE=param;
                                    

                               }
                            

                        /**
                        * field for MAX_SHARE
                        */

                        
                                    protected java.lang.String localMAX_SHARE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMAX_SHARETracker = false ;

                           public boolean isMAX_SHARESpecified(){
                               return localMAX_SHARETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMAX_SHARE(){
                               return localMAX_SHARE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MAX_SHARE
                               */
                               public void setMAX_SHARE(java.lang.String param){
                            localMAX_SHARETracker = true;
                                   
                                            this.localMAX_SHARE=param;
                                    

                               }
                            

                        /**
                        * field for MINOR_CARD_TYPE
                        */

                        
                                    protected java.lang.String localMINOR_CARD_TYPE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMINOR_CARD_TYPETracker = false ;

                           public boolean isMINOR_CARD_TYPESpecified(){
                               return localMINOR_CARD_TYPETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMINOR_CARD_TYPE(){
                               return localMINOR_CARD_TYPE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MINOR_CARD_TYPE
                               */
                               public void setMINOR_CARD_TYPE(java.lang.String param){
                            localMINOR_CARD_TYPETracker = true;
                                   
                                            this.localMINOR_CARD_TYPE=param;
                                    

                               }
                            

                        /**
                        * field for MINOR_MAXSHARE
                        */

                        
                                    protected java.lang.String localMINOR_MAXSHARE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMINOR_MAXSHARETracker = false ;

                           public boolean isMINOR_MAXSHARESpecified(){
                               return localMINOR_MAXSHARETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMINOR_MAXSHARE(){
                               return localMINOR_MAXSHARE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MINOR_MAXSHARE
                               */
                               public void setMINOR_MAXSHARE(java.lang.String param){
                            localMINOR_MAXSHARETracker = true;
                                   
                                            this.localMINOR_MAXSHARE=param;
                                    

                               }
                            

                        /**
                        * field for MIN_HOLDERTYPE
                        */

                        
                                    protected java.lang.String localMIN_HOLDERTYPE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMIN_HOLDERTYPETracker = false ;

                           public boolean isMIN_HOLDERTYPESpecified(){
                               return localMIN_HOLDERTYPETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMIN_HOLDERTYPE(){
                               return localMIN_HOLDERTYPE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MIN_HOLDERTYPE
                               */
                               public void setMIN_HOLDERTYPE(java.lang.String param){
                            localMIN_HOLDERTYPETracker = true;
                                   
                                            this.localMIN_HOLDERTYPE=param;
                                    

                               }
                            

                        /**
                        * field for MIN_HOLDER_AGE
                        */

                        
                                    protected java.lang.String localMIN_HOLDER_AGE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMIN_HOLDER_AGETracker = false ;

                           public boolean isMIN_HOLDER_AGESpecified(){
                               return localMIN_HOLDER_AGETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMIN_HOLDER_AGE(){
                               return localMIN_HOLDER_AGE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MIN_HOLDER_AGE
                               */
                               public void setMIN_HOLDER_AGE(java.lang.String param){
                            localMIN_HOLDER_AGETracker = true;
                                   
                                            this.localMIN_HOLDER_AGE=param;
                                    

                               }
                            

                        /**
                        * field for MIN_INSURED_AGE
                        */

                        
                                    protected java.lang.String localMIN_INSURED_AGE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMIN_INSURED_AGETracker = false ;

                           public boolean isMIN_INSURED_AGESpecified(){
                               return localMIN_INSURED_AGETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMIN_INSURED_AGE(){
                               return localMIN_INSURED_AGE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MIN_INSURED_AGE
                               */
                               public void setMIN_INSURED_AGE(java.lang.String param){
                            localMIN_INSURED_AGETracker = true;
                                   
                                            this.localMIN_INSURED_AGE=param;
                                    

                               }
                            

                        /**
                        * field for MIN_INSURED_TYPE
                        */

                        
                                    protected java.lang.String localMIN_INSURED_TYPE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMIN_INSURED_TYPETracker = false ;

                           public boolean isMIN_INSURED_TYPESpecified(){
                               return localMIN_INSURED_TYPETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMIN_INSURED_TYPE(){
                               return localMIN_INSURED_TYPE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MIN_INSURED_TYPE
                               */
                               public void setMIN_INSURED_TYPE(java.lang.String param){
                            localMIN_INSURED_TYPETracker = true;
                                   
                                            this.localMIN_INSURED_TYPE=param;
                                    

                               }
                            

                        /**
                        * field for PDFNAME
                        */

                        
                                    protected java.lang.String localPDFNAME ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPDFNAMETracker = false ;

                           public boolean isPDFNAMESpecified(){
                               return localPDFNAMETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPDFNAME(){
                               return localPDFNAME;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PDFNAME
                               */
                               public void setPDFNAME(java.lang.String param){
                            localPDFNAMETracker = true;
                                   
                                            this.localPDFNAME=param;
                                    

                               }
                            

                        /**
                        * field for POLICY_PERIOD_INFO
                        */

                        
                                    protected POLICY_PERIOD_INFO localPOLICY_PERIOD_INFO ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPOLICY_PERIOD_INFOTracker = false ;

                           public boolean isPOLICY_PERIOD_INFOSpecified(){
                               return localPOLICY_PERIOD_INFOTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return POLICY_PERIOD_INFO
                           */
                           public  POLICY_PERIOD_INFO getPOLICY_PERIOD_INFO(){
                               return localPOLICY_PERIOD_INFO;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param POLICY_PERIOD_INFO
                               */
                               public void setPOLICY_PERIOD_INFO(POLICY_PERIOD_INFO param){
                            localPOLICY_PERIOD_INFOTracker = true;
                                   
                                            this.localPOLICY_PERIOD_INFO=param;
                                    

                               }
                            

                        /**
                        * field for PREMIUM
                        */

                        
                                    protected java.lang.String localPREMIUM ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPREMIUMTracker = false ;

                           public boolean isPREMIUMSpecified(){
                               return localPREMIUMTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPREMIUM(){
                               return localPREMIUM;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PREMIUM
                               */
                               public void setPREMIUM(java.lang.String param){
                            localPREMIUMTracker = true;
                                   
                                            this.localPREMIUM=param;
                                    

                               }
                            

                        /**
                        * field for PRODUCTID
                        */

                        
                                    protected java.lang.String localPRODUCTID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPRODUCTIDTracker = false ;

                           public boolean isPRODUCTIDSpecified(){
                               return localPRODUCTIDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPRODUCTID(){
                               return localPRODUCTID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PRODUCTID
                               */
                               public void setPRODUCTID(java.lang.String param){
                            localPRODUCTIDTracker = true;
                                   
                                            this.localPRODUCTID=param;
                                    

                               }
                            

                        /**
                        * field for REL_BNFR_TO_HLDR
                        */

                        
                                    protected java.lang.String localREL_BNFR_TO_HLDR ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localREL_BNFR_TO_HLDRTracker = false ;

                           public boolean isREL_BNFR_TO_HLDRSpecified(){
                               return localREL_BNFR_TO_HLDRTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getREL_BNFR_TO_HLDR(){
                               return localREL_BNFR_TO_HLDR;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param REL_BNFR_TO_HLDR
                               */
                               public void setREL_BNFR_TO_HLDR(java.lang.String param){
                            localREL_BNFR_TO_HLDRTracker = true;
                                   
                                            this.localREL_BNFR_TO_HLDR=param;
                                    

                               }
                            

                        /**
                        * field for REL_CONTACT_TO_HLDR
                        */

                        
                                    protected java.lang.String localREL_CONTACT_TO_HLDR ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localREL_CONTACT_TO_HLDRTracker = false ;

                           public boolean isREL_CONTACT_TO_HLDRSpecified(){
                               return localREL_CONTACT_TO_HLDRTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getREL_CONTACT_TO_HLDR(){
                               return localREL_CONTACT_TO_HLDR;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param REL_CONTACT_TO_HLDR
                               */
                               public void setREL_CONTACT_TO_HLDR(java.lang.String param){
                            localREL_CONTACT_TO_HLDRTracker = true;
                                   
                                            this.localREL_CONTACT_TO_HLDR=param;
                                    

                               }
                            

                        /**
                        * field for REL_INSURED_TO_HLDR
                        */

                        
                                    protected java.lang.String localREL_INSURED_TO_HLDR ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localREL_INSURED_TO_HLDRTracker = false ;

                           public boolean isREL_INSURED_TO_HLDRSpecified(){
                               return localREL_INSURED_TO_HLDRTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getREL_INSURED_TO_HLDR(){
                               return localREL_INSURED_TO_HLDR;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param REL_INSURED_TO_HLDR
                               */
                               public void setREL_INSURED_TO_HLDR(java.lang.String param){
                            localREL_INSURED_TO_HLDRTracker = true;
                                   
                                            this.localREL_INSURED_TO_HLDR=param;
                                    

                               }
                            

                        /**
                        * field for REMARK
                        */

                        
                                    protected java.lang.String localREMARK ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localREMARKTracker = false ;

                           public boolean isREMARKSpecified(){
                               return localREMARKTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getREMARK(){
                               return localREMARK;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param REMARK
                               */
                               public void setREMARK(java.lang.String param){
                            localREMARKTracker = true;
                                   
                                            this.localREMARK=param;
                                    

                               }
                            

                        /**
                        * field for REMARK_NAMES
                        */

                        
                                    protected REMARK_NAMES localREMARK_NAMES ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localREMARK_NAMESTracker = false ;

                           public boolean isREMARK_NAMESSpecified(){
                               return localREMARK_NAMESTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return REMARK_NAMES
                           */
                           public  REMARK_NAMES getREMARK_NAMES(){
                               return localREMARK_NAMES;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param REMARK_NAMES
                               */
                               public void setREMARK_NAMES(REMARK_NAMES param){
                            localREMARK_NAMESTracker = true;
                                   
                                            this.localREMARK_NAMES=param;
                                    

                               }
                            

                        /**
                        * field for RISK_AMOUNT
                        */

                        
                                    protected java.lang.String localRISK_AMOUNT ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localRISK_AMOUNTTracker = false ;

                           public boolean isRISK_AMOUNTSpecified(){
                               return localRISK_AMOUNTTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getRISK_AMOUNT(){
                               return localRISK_AMOUNT;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RISK_AMOUNT
                               */
                               public void setRISK_AMOUNT(java.lang.String param){
                            localRISK_AMOUNTTracker = true;
                                   
                                            this.localRISK_AMOUNT=param;
                                    

                               }
                            

                        /**
                        * field for RISK_AMOUNT_ALLOT_WAY
                        */

                        
                                    protected java.lang.String localRISK_AMOUNT_ALLOT_WAY ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localRISK_AMOUNT_ALLOT_WAYTracker = false ;

                           public boolean isRISK_AMOUNT_ALLOT_WAYSpecified(){
                               return localRISK_AMOUNT_ALLOT_WAYTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getRISK_AMOUNT_ALLOT_WAY(){
                               return localRISK_AMOUNT_ALLOT_WAY;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RISK_AMOUNT_ALLOT_WAY
                               */
                               public void setRISK_AMOUNT_ALLOT_WAY(java.lang.String param){
                            localRISK_AMOUNT_ALLOT_WAYTracker = true;
                                   
                                            this.localRISK_AMOUNT_ALLOT_WAY=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://QueryACDef.server.soa.com/xsd");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":QueryACDefResponse",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "QueryACDefResponse",
                           xmlWriter);
                   }

               
                   }
                if (localACTIVE_CHANNELTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ACTIVE_CHANNEL", xmlWriter);
                             

                                          if (localACTIVE_CHANNEL==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localACTIVE_CHANNEL);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBRANCHNOTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "BRANCHNO", xmlWriter);
                             

                                          if (localBRANCHNO==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBRANCHNO);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCARDNAMETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "CARDNAME", xmlWriter);
                             

                                          if (localCARDNAME==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCARDNAME);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCARD_TYPETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "CARD_TYPE", xmlWriter);
                             

                                          if (localCARD_TYPE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCARD_TYPE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCHANGE_FLAGTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "CHANGE_FLAG", xmlWriter);
                             

                                          if (localCHANGE_FLAG==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCHANGE_FLAG);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCREATE_DATETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "CREATE_DATE", xmlWriter);
                             

                                          if (localCREATE_DATE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCREATE_DATE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localEFFECTIVE_DATE_FLAGTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "EFFECTIVE_DATE_FLAG", xmlWriter);
                             

                                          if (localEFFECTIVE_DATE_FLAG==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localEFFECTIVE_DATE_FLAG);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localEFFECTIVE_DATE_INTERVALTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "EFFECTIVE_DATE_INTERVAL", xmlWriter);
                             

                                          if (localEFFECTIVE_DATE_INTERVAL==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localEFFECTIVE_DATE_INTERVAL);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localEFFECTIVE_TYPETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "EFFECTIVE_TYPE", xmlWriter);
                             

                                          if (localEFFECTIVE_TYPE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localEFFECTIVE_TYPE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localEXPIRE_DATETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "EXPIRE_DATE", xmlWriter);
                             

                                          if (localEXPIRE_DATE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localEXPIRE_DATE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localIDDIF_MINOR_AMOUNTTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "IDDIF_MINOR_AMOUNT", xmlWriter);
                             

                                          if (localIDDIF_MINOR_AMOUNT==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localIDDIF_MINOR_AMOUNT);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localISBENTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISBEN", xmlWriter);
                             

                                          if (localISBEN==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISBEN);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localISCHECK_AMOUNTTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISCHECK_AMOUNT", xmlWriter);
                             

                                          if (localISCHECK_AMOUNT==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISCHECK_AMOUNT);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localISCONTACT_INFOTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISCONTACT_INFO", xmlWriter);
                             

                                          if (localISCONTACT_INFO==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISCONTACT_INFO);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localISCORETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISCORE", xmlWriter);
                             

                                          if (localISCORE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISCORE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localISEFFECTIVE_DATETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISEFFECTIVE_DATE", xmlWriter);
                             

                                          if (localISEFFECTIVE_DATE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISEFFECTIVE_DATE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localISIDCARDTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISIDCARD", xmlWriter);
                             

                                          if (localISIDCARD==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISIDCARD);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localISMINORTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISMINOR", xmlWriter);
                             

                                          if (localISMINOR==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISMINOR);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localISMORE_INSUREDTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISMORE_INSURED", xmlWriter);
                             

                                          if (localISMORE_INSURED==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISMORE_INSURED);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localISNAMETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISNAME", xmlWriter);
                             

                                          if (localISNAME==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISNAME);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localISPOLICYTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISPOLICY", xmlWriter);
                             

                                          if (localISPOLICY==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISPOLICY);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localISPREMIUMTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISPREMIUM", xmlWriter);
                             

                                          if (localISPREMIUM==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISPREMIUM);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localISREALTIME_INFORCETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISREALTIME_INFORCE", xmlWriter);
                             

                                          if (localISREALTIME_INFORCE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISREALTIME_INFORCE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localISREMARKSTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISREMARKS", xmlWriter);
                             

                                          if (localISREMARKS==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISREMARKS);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localISRENEWALTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "ISRENEWAL", xmlWriter);
                             

                                          if (localISRENEWAL==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localISRENEWAL);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localMAX_HOLDERTYPETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "MAX_HOLDERTYPE", xmlWriter);
                             

                                          if (localMAX_HOLDERTYPE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMAX_HOLDERTYPE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localMAX_HOLDER_AGETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "MAX_HOLDER_AGE", xmlWriter);
                             

                                          if (localMAX_HOLDER_AGE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMAX_HOLDER_AGE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localMAX_INSURED_AGETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "MAX_INSURED_AGE", xmlWriter);
                             

                                          if (localMAX_INSURED_AGE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMAX_INSURED_AGE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localMAX_INSURED_COUNTTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "MAX_INSURED_COUNT", xmlWriter);
                             

                                          if (localMAX_INSURED_COUNT==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMAX_INSURED_COUNT);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localMAX_INSURED_TYPETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "MAX_INSURED_TYPE", xmlWriter);
                             

                                          if (localMAX_INSURED_TYPE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMAX_INSURED_TYPE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localMAX_SHARETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "MAX_SHARE", xmlWriter);
                             

                                          if (localMAX_SHARE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMAX_SHARE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localMINOR_CARD_TYPETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "MINOR_CARD_TYPE", xmlWriter);
                             

                                          if (localMINOR_CARD_TYPE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMINOR_CARD_TYPE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localMINOR_MAXSHARETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "MINOR_MAXSHARE", xmlWriter);
                             

                                          if (localMINOR_MAXSHARE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMINOR_MAXSHARE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localMIN_HOLDERTYPETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "MIN_HOLDERTYPE", xmlWriter);
                             

                                          if (localMIN_HOLDERTYPE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMIN_HOLDERTYPE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localMIN_HOLDER_AGETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "MIN_HOLDER_AGE", xmlWriter);
                             

                                          if (localMIN_HOLDER_AGE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMIN_HOLDER_AGE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localMIN_INSURED_AGETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "MIN_INSURED_AGE", xmlWriter);
                             

                                          if (localMIN_INSURED_AGE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMIN_INSURED_AGE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localMIN_INSURED_TYPETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "MIN_INSURED_TYPE", xmlWriter);
                             

                                          if (localMIN_INSURED_TYPE==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMIN_INSURED_TYPE);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPDFNAMETracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "PDFNAME", xmlWriter);
                             

                                          if (localPDFNAME==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPDFNAME);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPOLICY_PERIOD_INFOTracker){
                                    if (localPOLICY_PERIOD_INFO==null){

                                        writeStartElement(null, "http://QueryACDef.server.soa.com/xsd", "POLICY_PERIOD_INFO", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localPOLICY_PERIOD_INFO.serialize(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","POLICY_PERIOD_INFO"),
                                        xmlWriter);
                                    }
                                } if (localPREMIUMTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "PREMIUM", xmlWriter);
                             

                                          if (localPREMIUM==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPREMIUM);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPRODUCTIDTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "PRODUCTID", xmlWriter);
                             

                                          if (localPRODUCTID==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPRODUCTID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localREL_BNFR_TO_HLDRTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "REL_BNFR_TO_HLDR", xmlWriter);
                             

                                          if (localREL_BNFR_TO_HLDR==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localREL_BNFR_TO_HLDR);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localREL_CONTACT_TO_HLDRTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "REL_CONTACT_TO_HLDR", xmlWriter);
                             

                                          if (localREL_CONTACT_TO_HLDR==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localREL_CONTACT_TO_HLDR);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localREL_INSURED_TO_HLDRTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "REL_INSURED_TO_HLDR", xmlWriter);
                             

                                          if (localREL_INSURED_TO_HLDR==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localREL_INSURED_TO_HLDR);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localREMARKTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "REMARK", xmlWriter);
                             

                                          if (localREMARK==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localREMARK);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localREMARK_NAMESTracker){
                                    if (localREMARK_NAMES==null){

                                        writeStartElement(null, "http://QueryACDef.server.soa.com/xsd", "REMARK_NAMES", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localREMARK_NAMES.serialize(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","REMARK_NAMES"),
                                        xmlWriter);
                                    }
                                } if (localRISK_AMOUNTTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "RISK_AMOUNT", xmlWriter);
                             

                                          if (localRISK_AMOUNT==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localRISK_AMOUNT);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localRISK_AMOUNT_ALLOT_WAYTracker){
                                    namespace = "http://QueryACDef.server.soa.com/xsd";
                                    writeStartElement(null, namespace, "RISK_AMOUNT_ALLOT_WAY", xmlWriter);
                             

                                          if (localRISK_AMOUNT_ALLOT_WAY==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localRISK_AMOUNT_ALLOT_WAY);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://QueryACDef.server.soa.com/xsd")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localACTIVE_CHANNELTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ACTIVE_CHANNEL"));
                                 
                                         elementList.add(localACTIVE_CHANNEL==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localACTIVE_CHANNEL));
                                    } if (localBRANCHNOTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "BRANCHNO"));
                                 
                                         elementList.add(localBRANCHNO==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBRANCHNO));
                                    } if (localCARDNAMETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "CARDNAME"));
                                 
                                         elementList.add(localCARDNAME==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCARDNAME));
                                    } if (localCARD_TYPETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "CARD_TYPE"));
                                 
                                         elementList.add(localCARD_TYPE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCARD_TYPE));
                                    } if (localCHANGE_FLAGTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "CHANGE_FLAG"));
                                 
                                         elementList.add(localCHANGE_FLAG==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCHANGE_FLAG));
                                    } if (localCREATE_DATETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "CREATE_DATE"));
                                 
                                         elementList.add(localCREATE_DATE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCREATE_DATE));
                                    } if (localEFFECTIVE_DATE_FLAGTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "EFFECTIVE_DATE_FLAG"));
                                 
                                         elementList.add(localEFFECTIVE_DATE_FLAG==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEFFECTIVE_DATE_FLAG));
                                    } if (localEFFECTIVE_DATE_INTERVALTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "EFFECTIVE_DATE_INTERVAL"));
                                 
                                         elementList.add(localEFFECTIVE_DATE_INTERVAL==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEFFECTIVE_DATE_INTERVAL));
                                    } if (localEFFECTIVE_TYPETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "EFFECTIVE_TYPE"));
                                 
                                         elementList.add(localEFFECTIVE_TYPE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEFFECTIVE_TYPE));
                                    } if (localEXPIRE_DATETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "EXPIRE_DATE"));
                                 
                                         elementList.add(localEXPIRE_DATE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEXPIRE_DATE));
                                    } if (localIDDIF_MINOR_AMOUNTTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "IDDIF_MINOR_AMOUNT"));
                                 
                                         elementList.add(localIDDIF_MINOR_AMOUNT==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIDDIF_MINOR_AMOUNT));
                                    } if (localISBENTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISBEN"));
                                 
                                         elementList.add(localISBEN==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISBEN));
                                    } if (localISCHECK_AMOUNTTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISCHECK_AMOUNT"));
                                 
                                         elementList.add(localISCHECK_AMOUNT==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISCHECK_AMOUNT));
                                    } if (localISCONTACT_INFOTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISCONTACT_INFO"));
                                 
                                         elementList.add(localISCONTACT_INFO==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISCONTACT_INFO));
                                    } if (localISCORETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISCORE"));
                                 
                                         elementList.add(localISCORE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISCORE));
                                    } if (localISEFFECTIVE_DATETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISEFFECTIVE_DATE"));
                                 
                                         elementList.add(localISEFFECTIVE_DATE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISEFFECTIVE_DATE));
                                    } if (localISIDCARDTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISIDCARD"));
                                 
                                         elementList.add(localISIDCARD==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISIDCARD));
                                    } if (localISMINORTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISMINOR"));
                                 
                                         elementList.add(localISMINOR==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISMINOR));
                                    } if (localISMORE_INSUREDTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISMORE_INSURED"));
                                 
                                         elementList.add(localISMORE_INSURED==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISMORE_INSURED));
                                    } if (localISNAMETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISNAME"));
                                 
                                         elementList.add(localISNAME==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISNAME));
                                    } if (localISPOLICYTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISPOLICY"));
                                 
                                         elementList.add(localISPOLICY==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISPOLICY));
                                    } if (localISPREMIUMTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISPREMIUM"));
                                 
                                         elementList.add(localISPREMIUM==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISPREMIUM));
                                    } if (localISREALTIME_INFORCETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISREALTIME_INFORCE"));
                                 
                                         elementList.add(localISREALTIME_INFORCE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISREALTIME_INFORCE));
                                    } if (localISREMARKSTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISREMARKS"));
                                 
                                         elementList.add(localISREMARKS==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISREMARKS));
                                    } if (localISRENEWALTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "ISRENEWAL"));
                                 
                                         elementList.add(localISRENEWAL==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localISRENEWAL));
                                    } if (localMAX_HOLDERTYPETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "MAX_HOLDERTYPE"));
                                 
                                         elementList.add(localMAX_HOLDERTYPE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMAX_HOLDERTYPE));
                                    } if (localMAX_HOLDER_AGETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "MAX_HOLDER_AGE"));
                                 
                                         elementList.add(localMAX_HOLDER_AGE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMAX_HOLDER_AGE));
                                    } if (localMAX_INSURED_AGETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "MAX_INSURED_AGE"));
                                 
                                         elementList.add(localMAX_INSURED_AGE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMAX_INSURED_AGE));
                                    } if (localMAX_INSURED_COUNTTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "MAX_INSURED_COUNT"));
                                 
                                         elementList.add(localMAX_INSURED_COUNT==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMAX_INSURED_COUNT));
                                    } if (localMAX_INSURED_TYPETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "MAX_INSURED_TYPE"));
                                 
                                         elementList.add(localMAX_INSURED_TYPE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMAX_INSURED_TYPE));
                                    } if (localMAX_SHARETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "MAX_SHARE"));
                                 
                                         elementList.add(localMAX_SHARE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMAX_SHARE));
                                    } if (localMINOR_CARD_TYPETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "MINOR_CARD_TYPE"));
                                 
                                         elementList.add(localMINOR_CARD_TYPE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMINOR_CARD_TYPE));
                                    } if (localMINOR_MAXSHARETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "MINOR_MAXSHARE"));
                                 
                                         elementList.add(localMINOR_MAXSHARE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMINOR_MAXSHARE));
                                    } if (localMIN_HOLDERTYPETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "MIN_HOLDERTYPE"));
                                 
                                         elementList.add(localMIN_HOLDERTYPE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMIN_HOLDERTYPE));
                                    } if (localMIN_HOLDER_AGETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "MIN_HOLDER_AGE"));
                                 
                                         elementList.add(localMIN_HOLDER_AGE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMIN_HOLDER_AGE));
                                    } if (localMIN_INSURED_AGETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "MIN_INSURED_AGE"));
                                 
                                         elementList.add(localMIN_INSURED_AGE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMIN_INSURED_AGE));
                                    } if (localMIN_INSURED_TYPETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "MIN_INSURED_TYPE"));
                                 
                                         elementList.add(localMIN_INSURED_TYPE==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMIN_INSURED_TYPE));
                                    } if (localPDFNAMETracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "PDFNAME"));
                                 
                                         elementList.add(localPDFNAME==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPDFNAME));
                                    } if (localPOLICY_PERIOD_INFOTracker){
                            elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "POLICY_PERIOD_INFO"));
                            
                            
                                    elementList.add(localPOLICY_PERIOD_INFO==null?null:
                                    localPOLICY_PERIOD_INFO);
                                } if (localPREMIUMTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "PREMIUM"));
                                 
                                         elementList.add(localPREMIUM==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPREMIUM));
                                    } if (localPRODUCTIDTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "PRODUCTID"));
                                 
                                         elementList.add(localPRODUCTID==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPRODUCTID));
                                    } if (localREL_BNFR_TO_HLDRTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "REL_BNFR_TO_HLDR"));
                                 
                                         elementList.add(localREL_BNFR_TO_HLDR==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localREL_BNFR_TO_HLDR));
                                    } if (localREL_CONTACT_TO_HLDRTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "REL_CONTACT_TO_HLDR"));
                                 
                                         elementList.add(localREL_CONTACT_TO_HLDR==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localREL_CONTACT_TO_HLDR));
                                    } if (localREL_INSURED_TO_HLDRTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "REL_INSURED_TO_HLDR"));
                                 
                                         elementList.add(localREL_INSURED_TO_HLDR==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localREL_INSURED_TO_HLDR));
                                    } if (localREMARKTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "REMARK"));
                                 
                                         elementList.add(localREMARK==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localREMARK));
                                    } if (localREMARK_NAMESTracker){
                            elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "REMARK_NAMES"));
                            
                            
                                    elementList.add(localREMARK_NAMES==null?null:
                                    localREMARK_NAMES);
                                } if (localRISK_AMOUNTTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "RISK_AMOUNT"));
                                 
                                         elementList.add(localRISK_AMOUNT==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRISK_AMOUNT));
                                    } if (localRISK_AMOUNT_ALLOT_WAYTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd",
                                                                      "RISK_AMOUNT_ALLOT_WAY"));
                                 
                                         elementList.add(localRISK_AMOUNT_ALLOT_WAY==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRISK_AMOUNT_ALLOT_WAY));
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static QueryACDefResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            QueryACDefResponse object =
                new QueryACDefResponse();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"QueryACDefResponse".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (QueryACDefResponse)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ACTIVE_CHANNEL").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setACTIVE_CHANNEL(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","BRANCHNO").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBRANCHNO(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","CARDNAME").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCARDNAME(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","CARD_TYPE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCARD_TYPE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","CHANGE_FLAG").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCHANGE_FLAG(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","CREATE_DATE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCREATE_DATE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","EFFECTIVE_DATE_FLAG").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEFFECTIVE_DATE_FLAG(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","EFFECTIVE_DATE_INTERVAL").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEFFECTIVE_DATE_INTERVAL(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","EFFECTIVE_TYPE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEFFECTIVE_TYPE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","EXPIRE_DATE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEXPIRE_DATE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","IDDIF_MINOR_AMOUNT").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setIDDIF_MINOR_AMOUNT(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISBEN").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISBEN(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISCHECK_AMOUNT").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISCHECK_AMOUNT(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISCONTACT_INFO").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISCONTACT_INFO(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISCORE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISCORE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISEFFECTIVE_DATE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISEFFECTIVE_DATE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISIDCARD").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISIDCARD(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISMINOR").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISMINOR(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISMORE_INSURED").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISMORE_INSURED(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISNAME").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISNAME(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISPOLICY").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISPOLICY(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISPREMIUM").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISPREMIUM(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISREALTIME_INFORCE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISREALTIME_INFORCE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISREMARKS").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISREMARKS(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","ISRENEWAL").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setISRENEWAL(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","MAX_HOLDERTYPE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMAX_HOLDERTYPE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","MAX_HOLDER_AGE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMAX_HOLDER_AGE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","MAX_INSURED_AGE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMAX_INSURED_AGE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","MAX_INSURED_COUNT").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMAX_INSURED_COUNT(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","MAX_INSURED_TYPE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMAX_INSURED_TYPE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","MAX_SHARE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMAX_SHARE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","MINOR_CARD_TYPE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMINOR_CARD_TYPE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","MINOR_MAXSHARE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMINOR_MAXSHARE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","MIN_HOLDERTYPE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMIN_HOLDERTYPE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","MIN_HOLDER_AGE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMIN_HOLDER_AGE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","MIN_INSURED_AGE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMIN_INSURED_AGE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","MIN_INSURED_TYPE").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMIN_INSURED_TYPE(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","PDFNAME").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPDFNAME(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","POLICY_PERIOD_INFO").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setPOLICY_PERIOD_INFO(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setPOLICY_PERIOD_INFO(POLICY_PERIOD_INFO.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","PREMIUM").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPREMIUM(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","PRODUCTID").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPRODUCTID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","REL_BNFR_TO_HLDR").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setREL_BNFR_TO_HLDR(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","REL_CONTACT_TO_HLDR").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setREL_CONTACT_TO_HLDR(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","REL_INSURED_TO_HLDR").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setREL_INSURED_TO_HLDR(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","REMARK").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setREMARK(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","REMARK_NAMES").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setREMARK_NAMES(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setREMARK_NAMES(REMARK_NAMES.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","RISK_AMOUNT").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setRISK_AMOUNT(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://QueryACDef.server.soa.com/xsd","RISK_AMOUNT_ALLOT_WAY").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setRISK_AMOUNT_ALLOT_WAY(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
        public static class QueryACDefResponseE
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://www.e-chinalife.com/soa/",
                "queryACDefResponse",
                "ns2");

            

                        /**
                        * field for _return
                        */

                        
                                    protected QueryACDefResponse local_return ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean local_returnTracker = false ;

                           public boolean is_returnSpecified(){
                               return local_returnTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return QueryACDefResponse
                           */
                           public  QueryACDefResponse get_return(){
                               return local_return;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param _return
                               */
                               public void set_return(QueryACDefResponse param){
                            local_returnTracker = true;
                                   
                                            this.local_return=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME);
               return factory.createOMElement(dataSource,MY_QNAME);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.e-chinalife.com/soa/");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":queryACDefResponse",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "queryACDefResponse",
                           xmlWriter);
                   }

               
                   }
                if (local_returnTracker){
                                    if (local_return==null){

                                        writeStartElement(null, "http://www.e-chinalife.com/soa/", "return", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     local_return.serialize(new javax.xml.namespace.QName("http://www.e-chinalife.com/soa/","return"),
                                        xmlWriter);
                                    }
                                }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.e-chinalife.com/soa/")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (local_returnTracker){
                            elementList.add(new javax.xml.namespace.QName("http://www.e-chinalife.com/soa/",
                                                                      "return"));
                            
                            
                                    elementList.add(local_return==null?null:
                                    local_return);
                                }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static QueryACDefResponseE parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            QueryACDefResponseE object =
                new QueryACDefResponseE();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"queryACDefResponse".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (QueryACDefResponseE)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.e-chinalife.com/soa/","return").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.set_return(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.set_return(QueryACDefResponse.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    
            private  org.apache.axiom.om.OMElement  toOM(cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefE param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefE.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefE.class.equals(type)){
                
                           return cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefResponseE.class.equals(type)){
                
                           return cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    
   }
   
