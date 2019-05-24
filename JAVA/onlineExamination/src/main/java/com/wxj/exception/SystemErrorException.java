package com.wxj.exception;


/**
 * <p>FileName: InnerDataErrorException.java</p>
 * <p>Description: 内部数据错误</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author zhengzhibo
 * @date 2019年1月9日 上午10:54:56
 */
public class SystemErrorException extends BusinessRuntimeException{

	public SystemErrorException(String message){
		super("SystemError Exception: "+message);
	}
	
	public SystemErrorException(){
		super("SystemError Exception");
	}
	
	public SystemErrorException(String requestId, String message){
		super("SystemError Exception: "+message);
		this.msg = message;
		this.requestId = requestId;
	}
	

	@Override
	public String getCode(){
		return "500";
	}

	@Override
	public String getMessage() {
		return "系统错误:"+ msg;
	}
}
