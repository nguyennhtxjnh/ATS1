package com.ats.exception;

public class BusinessException extends AbstractException {

	private static final long serialVersionUID = -9204704389580477755L;
	
   /**
    * BusinessException
    * 
    * @param e
    *            BusinessException
    */
   public BusinessException(BusinessException e) {
       super(e.getStatus(), e.getCause());

   }

   /**
    * BusinessException Constructor
    * 
    * @param status
    *            status
    * @param cause
    *            cause
    */
   public BusinessException(final String status, final Throwable cause) {
       super(status, cause);
   }

}
