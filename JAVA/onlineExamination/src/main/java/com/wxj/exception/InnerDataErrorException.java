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
public class InnerDataErrorException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public InnerDataErrorException(String message){
		super("InnerDataError Exception: "+message);
	}
	
	public InnerDataErrorException(){
		super("InnerDataError Exception");
	}
	
	public InnerDataErrorException(String requestId, String message){
		super("InnerDataError Exception: "+message);
		this.msg = message;
		this.requestId = requestId;
	}
	
	@Override
	public String getCode(){
		return "404";
	}

	@Override
	public String getMessage() {
		return "内部数据错误:"+ msg;
	}
}
