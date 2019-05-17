package com.wxj.utils;

import com.wxj.exception.BusinessException;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.model.Bean.ResponseBean;
import com.wxj.model.Bean.SimpleResponseBean;

/**
 * <p>FileName: ResponseUtils.java</p>
 * <p>Description: 返回参数的工具类</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author zhengzhibo
 * @date 2019年1月10日 上午10:19:53
 */
public class ResponseUtils {

	/**
	 * <p>Title: success</p>
	 * <p>Description: 返回成功实体</p>
	 * @param obj
	 * @return
	 */
	public static <T> ResponseBean<T> success(String code, T obj){
		ResponseBean<T> resBody = new ResponseBean<T>();
		resBody.setResponseCode(code);
		resBody.setResponseMsg("SUCCESS");
		resBody.setTimestamp(System.currentTimeMillis()+"");
		resBody.setData(obj);
		return resBody;
	}

	/**
	 * <p>Title: success</p>
	 * <p>Description: 返回成功实体</p>
	 * @return
	 */
	public static SimpleResponseBean success(String code){
		SimpleResponseBean resBody = new SimpleResponseBean();
		resBody.setResponseCode(code);
		resBody.setResponseMsg("SUCCESS");
		resBody.setTimestamp(System.currentTimeMillis()+"");
		return resBody;
	}

	/**
	 * <p>Title: error</p>
	 * <p>Description: 返回失败实体</p>
	 * @param e
	 * @return
	 */
	public static SimpleResponseBean error(BusinessException e){
		return assembling(e.getCode(), e.getMessage());
	}

	/**
	 * <p>Title: error</p>
	 * <p>Description: 返回失败实体</p>
	 * @param e
	 * @param obj
	 * @return
	 */
	public static <T> ResponseBean<T> error(BusinessException e, T obj){
		return assembling(e.getCode(), e.getMessage(), obj);
	}

	/**
	 * <p>Title: error</p>
	 * <p>Description: 返回失败实体</p>
	 * @param e
	 * @return
	 */
	public static SimpleResponseBean error(BusinessRuntimeException e){
		return assembling(e.getCode(), e.getMessage());
	}

	/**
	 * <p>Title: error</p>
	 * <p>Description: 返回失败实体</p>
	 * @param e
	 * @param obj
	 * @return
	 */
	public static <T> ResponseBean<T> error(BusinessRuntimeException e, T obj){
		return assembling(e.getCode(), e.getMessage(), obj);
	}

	private static  <T> ResponseBean<T> assembling(String code, String message, T obj) {
		ResponseBean<T> resBody = new ResponseBean<T>();
		resBody.setResponseCode(code);
		resBody.setResponseMsg(message);
		resBody.setTimestamp(System.currentTimeMillis()+"");
		resBody.setData(obj);
		return resBody;
	}

	private static SimpleResponseBean assembling(String code, String message) {
		SimpleResponseBean resBody = new SimpleResponseBean();
		resBody.setResponseCode(code);
		resBody.setResponseMsg(message);
		resBody.setTimestamp(System.currentTimeMillis()+"");
		return resBody;
	}

}


