
/**
 * QueryACDefCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.0  Built on : May 17, 2011 (04:19:43 IST)
 */

    package cn.com.sinosoft.portalModule.interfacePortal.soap.stub;

    /**
     *  QueryACDefCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class QueryACDefCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public QueryACDefCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public QueryACDefCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for queryACDef method
            * override this method for handling normal response from queryACDef operation
            */
           public void receiveResultqueryACDef(
                    cn.com.sinosoft.portalModule.interfacePortal.soap.stub.QueryACDefStub.QueryACDefResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from queryACDef operation
           */
            public void receiveErrorqueryACDef(java.lang.Exception e) {
            }
                


    }
    
