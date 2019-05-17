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
public class JsonFormatException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public JsonFormatException(String message){
		super("JsonFormat Exception: "+message);
	}
	
	public JsonFormatException(){
		super("JsonFormat Exception");
	}
	
	public JsonFormatException(String requestId, String message){
		super("JsonFormat Exception: "+message);
		this.msg = message;
		this.requestId = requestId;
	}
	

	@Override
	public String getCode(){
		return "E11_JSON_ERROR";
	}

	@Override
	public String getMessage() {
		return "请求JSON格式错误:"+ msg;
	}
}
