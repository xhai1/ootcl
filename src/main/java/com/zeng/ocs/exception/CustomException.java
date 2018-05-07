package com.zeng.ocs.exception;
/**
 * 自定义异常
 * @author cxs
 *
 */
public class CustomException extends Exception{
	public String message;
	public CustomException(String message){
		super(message);
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
