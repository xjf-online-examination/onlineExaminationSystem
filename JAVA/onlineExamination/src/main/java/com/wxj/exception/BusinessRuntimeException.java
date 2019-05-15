package com.wxj.exception;

/**
 * <p>FileName: BusinessException.java</p>
 * <p>Description: 陈列所有异常的父类</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author zhengzhibo
 * @date 2019年1月9日 上午10:12:42
 */
public abstract class BusinessRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 5756040338167914965L;

	protected String msg;

	protected String requestId;

	public BusinessRuntimeException(){
		super("Business Exception");
	}

	public BusinessRuntimeException(String message){
		super("Business Exception: "+message);
		this.msg = message;
	}

	public BusinessRuntimeException(String requestId, String message){
		super("Business Exception: "+message);
		this.msg = message;
		this.requestId = requestId;
	}

	
	public String getRequestId() {
		return requestId;
	}

	/**
	 * <p>Title: getCode</p>
	 * <p>Description: 错误码</p>
	 * @return
	 */
	public abstract String getCode();
	
	/**
	 * <p>异常信息</p>
	 * @return
	 */
	@Override
	public abstract String getMessage();

}
