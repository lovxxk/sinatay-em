
/**
 * CommitActiveApplyCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.0  Built on : May 17, 2011 (04:19:43 IST)
 */

    package cn.com.sinosoft.portalModule.interfacePortal.soap.stub;

    /**
     *  CommitActiveApplyCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class CommitActiveApplyCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public CommitActiveApplyCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public CommitActiveApplyCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getSequence method
            * override this method for handling normal response from getSequence operation
            */
           public void receiveResultgetSequence(
                    cn.com.sinosoft.portalModule.interfacePortal.soap.stub.CommitActiveApplyStub.GetSequenceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSequence operation
           */
            public void receiveErrorgetSequence(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for commitActiveApply method
            * override this method for handling normal response from commitActiveApply operation
            */
           public void receiveResultcommitActiveApply(
                    cn.com.sinosoft.portalModule.interfacePortal.soap.stub.CommitActiveApplyStub.CommitActiveApplyResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from commitActiveApply operation
           */
            public void receiveErrorcommitActiveApply(java.lang.Exception e) {
            }
                


    }
    
