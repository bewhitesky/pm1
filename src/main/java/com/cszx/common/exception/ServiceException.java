package com.cszx.common.exception;

public class ServiceException extends RuntimeException{
	private static final long serialVersionUID = 1L;

    public ServiceException(Object obj) {
        super(obj.toString());
    }
    public ServiceException(Object obj ,String message,Throwable cause){
    	super(obj.toString()+message,cause);
    }
    public ServiceException(Object obj ,Throwable cause){
    	super(obj.toString(),cause);
    }
}
