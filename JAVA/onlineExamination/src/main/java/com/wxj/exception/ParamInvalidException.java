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
public class ParamInvalidException extends BusinessRuntimeException{

	private static final long serialVersionUID = 1L;

	public ParamInvalidException(String message){
		super("ParamInvalid Exception: "+message);
	}
	
	public ParamInvalidException(){
		super("ParamInvalid Exception");
	}
	
	public ParamInvalidException(String requestId, String message){
		super("ParamInvalid Exception: "+message);
		this.msg = message;
		this.requestId = requestId;
	}
	

	@Override
	public String getCode(){
		return "E11_ARG_IS_INVALID";
	}

	@Override
	public String getMessage() {
		return "参数格式非法:"+ msg;
	}
}
