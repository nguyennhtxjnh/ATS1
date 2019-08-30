package com.ats.util;


public class RestResponse {
    private final boolean success;
    private final String message;
    private final Object data;

    public RestResponse(boolean success, String message, Object data) {
        super();
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

}
