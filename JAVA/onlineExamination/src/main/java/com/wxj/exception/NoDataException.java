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
public class NoDataException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public NoDataException(String message){
		super("NoData Exception: "+message);
	}
	
	public NoDataException(){
		super("NoData Exception");
	}
	
	public NoDataException(String requestId, String message){
		super("NoData Exception: "+message);
		this.msg = message;
		this.requestId = requestId;
	}
	
	@Override
	public String getCode(){
		return "E99_NO_DATA";
	}

	@Override
	public String getMessage() {
		return "没有数据:"+ msg;
	}
}
