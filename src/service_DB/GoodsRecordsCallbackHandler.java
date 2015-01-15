
/**
 * GoodsRecordsCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package service_DB;

    /**
     *  GoodsRecordsCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class GoodsRecordsCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public GoodsRecordsCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public GoodsRecordsCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getLessorRecord method
            * override this method for handling normal response from getLessorRecord operation
            */
           public void receiveResultgetLessorRecord(
                    service_DB.GoodsRecordsStub.GetLessorRecordResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getLessorRecord operation
           */
            public void receiveErrorgetLessorRecord(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createNewDeal method
            * override this method for handling normal response from createNewDeal operation
            */
           public void receiveResultcreateNewDeal(
                    service_DB.GoodsRecordsStub.CreateNewDealResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createNewDeal operation
           */
            public void receiveErrorcreateNewDeal(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getStateCode method
            * override this method for handling normal response from getStateCode operation
            */
           public void receiveResultgetStateCode(
                    service_DB.GoodsRecordsStub.GetStateCodeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getStateCode operation
           */
            public void receiveErrorgetStateCode(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTenantRecord method
            * override this method for handling normal response from getTenantRecord operation
            */
           public void receiveResultgetTenantRecord(
                    service_DB.GoodsRecordsStub.GetTenantRecordResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTenantRecord operation
           */
            public void receiveErrorgetTenantRecord(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for isValid method
            * override this method for handling normal response from isValid operation
            */
           public void receiveResultisValid(
                    service_DB.GoodsRecordsStub.IsValidResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isValid operation
           */
            public void receiveErrorisValid(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for isOwner method
            * override this method for handling normal response from isOwner operation
            */
           public void receiveResultisOwner(
                    service_DB.GoodsRecordsStub.IsOwnerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isOwner operation
           */
            public void receiveErrorisOwner(java.lang.Exception e) {
            }
                


    }
    