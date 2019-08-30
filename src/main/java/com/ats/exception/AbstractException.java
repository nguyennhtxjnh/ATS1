/**
 * 
 */
package com.ats.exception;

public class AbstractException extends Exception {

	private static final long serialVersionUID = 7961244392470778938L;
	
	/**
     * status
     */
    private String status;

    /**
     * cause
     */
    private Throwable cause;

    /**
     * @param cause
     *            cause
     */
    public AbstractException(final Throwable cause) {
        this.cause = cause;
    }

    /**
     * AbstractException Constructor
     * 
     * @param status
     *            status
     * @param cause
     *            cause
     */
    public AbstractException(final String status, final Throwable cause) {
        this.status = status;
        this.cause = cause;
    }


    @Override
    public Throwable getCause() {
        return cause;
    }

    /**
     * @param cause
     *            cause
     */
    public void setCause(final Throwable cause) {
        this.cause = cause;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
