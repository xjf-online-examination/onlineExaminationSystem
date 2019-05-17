package com.wxj.exception;

/**
 * <p>FileName: JsonFormatException.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author zhengzhibo
 * @date 2019年1月8日 下午4:31:02
 */
public class PermissionException extends BusinessRuntimeException{

	private static final long serialVersionUID = 1L;

	public PermissionException(String message){
		super("Permission Exception: "+message);
	}
	
	public PermissionException(){
		super("Permission Exception");
	}
	
	public PermissionException(String requestId, String message){
		super("Permission Exception: "+message);
		this.msg = message;
		this.requestId = requestId;
	}
	

	@Override
	public String getCode(){
		return "401";
	}

	@Override
	public String getMessage() {
		return "权限禁止:"+ msg;
	}
}
