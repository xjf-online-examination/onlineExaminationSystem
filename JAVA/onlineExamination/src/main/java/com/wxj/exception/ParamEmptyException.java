package com.wxj.exception;

/**
 * <p>FileName: JsonFormatException.java</p>
 * <p>Description: 请求参数为空或者缺失异常</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author zhengzhibo
 * @date 2019年1月8日 下午4:31:02
 */
public class ParamEmptyException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public ParamEmptyException(String message){
		super("ParamEmpty Exception: "+message);
	}
	
	public ParamEmptyException(){
		super("ParamEmpty Exception");
	}
	
	public ParamEmptyException(String requestId, String message){
		super("ParamEmpty Exception: "+message);
		this.msg = message;
		this.requestId = requestId;
	}
	

	@Override
	public String getCode(){
		return "E11_ARG_IS_EMPTY";
	}

	@Override
	public String getMessage() {
		return "参数缺失:"+ msg;
	}
}
