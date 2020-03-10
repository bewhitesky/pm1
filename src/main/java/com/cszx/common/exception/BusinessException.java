package com.cszx.common.exception;

public class BusinessException extends RuntimeException{
	private static final long serialVersionUID = 1L;

    public BusinessException(Object obj) {
        super(obj.toString());
    }
    public BusinessException(Object obj ,String message,Throwable cause){
    	super(obj.toString()+message,cause);
    }
    public BusinessException(Object obj ,Throwable cause){
    	super(obj.toString(),cause);
    }
}
