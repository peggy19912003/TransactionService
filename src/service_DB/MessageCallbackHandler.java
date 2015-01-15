
/**
 * MessageCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package service_DB;

    /**
     *  MessageCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class MessageCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public MessageCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public MessageCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for inputMessage method
            * override this method for handling normal response from inputMessage operation
            */
           public void receiveResultinputMessage(
                    service_DB.MessageStub.InputMessageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from inputMessage operation
           */
            public void receiveErrorinputMessage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMessage method
            * override this method for handling normal response from getMessage operation
            */
           public void receiveResultgetMessage(
                    service_DB.MessageStub.GetMessageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMessage operation
           */
            public void receiveErrorgetMessage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getItemownerID method
            * override this method for handling normal response from getItemownerID operation
            */
           public void receiveResultgetItemownerID(
                    service_DB.MessageStub.GetItemownerIDResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getItemownerID operation
           */
            public void receiveErrorgetItemownerID(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for isValid method
            * override this method for handling normal response from isValid operation
            */
           public void receiveResultisValid(
                    service_DB.MessageStub.IsValidResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isValid operation
           */
            public void receiveErrorisValid(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAccount method
            * override this method for handling normal response from getAccount operation
            */
           public void receiveResultgetAccount(
                    service_DB.MessageStub.GetAccountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAccount operation
           */
            public void receiveErrorgetAccount(java.lang.Exception e) {
            }
                


    }
    