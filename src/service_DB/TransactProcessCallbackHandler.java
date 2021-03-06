
/**
 * TransactProcessCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package service_DB;

    /**
     *  TransactProcessCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class TransactProcessCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public TransactProcessCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public TransactProcessCallbackHandler(){
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
            * auto generated Axis2 call back method for isDued method
            * override this method for handling normal response from isDued operation
            */
           public void receiveResultisDued(
                    service_DB.TransactProcessStub.IsDuedResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isDued operation
           */
            public void receiveErrorisDued(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getStateCode method
            * override this method for handling normal response from getStateCode operation
            */
           public void receiveResultgetStateCode(
                    service_DB.TransactProcessStub.GetStateCodeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getStateCode operation
           */
            public void receiveErrorgetStateCode(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for isValid method
            * override this method for handling normal response from isValid operation
            */
           public void receiveResultisValid(
                    service_DB.TransactProcessStub.IsValidResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isValid operation
           */
            public void receiveErrorisValid(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateRecords method
            * override this method for handling normal response from updateRecords operation
            */
           public void receiveResultupdateRecords(
                    service_DB.TransactProcessStub.UpdateRecordsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateRecords operation
           */
            public void receiveErrorupdateRecords(java.lang.Exception e) {
            }
                


    }
    