package com.wxj.exception;

/**
 * <p>Title: OperationException</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-11 00:16
 */
public class OperationException extends BusinessRuntimeException {

    public OperationException(String message){
        super("OperationError Exception: "+message);
    }

    public OperationException(){
        super("OperationError Exception");
    }

    public OperationException(String requestId, String message){
        super("OperationError Exception: "+message);
        this.msg = message;
        this.requestId = requestId;
    }


    @Override
    public String getCode(){
        return "400";
    }

    @Override
    public String getMessage() {
        return "操作失败:"+ msg;
    }
}