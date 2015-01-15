
/**
 * EditMemberCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package service_DB;

    /**
     *  EditMemberCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class EditMemberCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public EditMemberCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public EditMemberCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for isCorrect method
            * override this method for handling normal response from isCorrect operation
            */
           public void receiveResultisCorrect(
                    service_DB.EditMemberStub.IsCorrectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isCorrect operation
           */
            public void receiveErrorisCorrect(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for editMemberData method
            * override this method for handling normal response from editMemberData operation
            */
           public void receiveResulteditMemberData(
                    service_DB.EditMemberStub.EditMemberDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from editMemberData operation
           */
            public void receiveErroreditMemberData(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for isValid method
            * override this method for handling normal response from isValid operation
            */
           public void receiveResultisValid(
                    service_DB.EditMemberStub.IsValidResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isValid operation
           */
            public void receiveErrorisValid(java.lang.Exception e) {
            }
                


    }
    