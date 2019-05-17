package com.wxj.exception;


/**
 * <p>FileName: SessionTimeOutException.java</p>
 * <p>Description: Session连接超时，需要重新登录</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author zhengzhibo
 * @date 2019年1月9日 上午10:52:12
 */
public class SessionTimeOutException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public SessionTimeOutException(String message){
		super("SessionTimeOut Exception: "+message);
	}
	
	public SessionTimeOutException(){
		super("SessionTimeOut Exception");
	}
	
	public SessionTimeOutException(String requestId, String message){
		super("SessionTimeOut Exception: "+message);
		this.msg = message;
		this.requestId = requestId;
	}
	

	@Override
	public String getCode(){
		return "E10_TIMEOUT";
	}

	@Override
	public String getMessage() {
		return "session超时，重新登录:"+ msg;
	}
}
