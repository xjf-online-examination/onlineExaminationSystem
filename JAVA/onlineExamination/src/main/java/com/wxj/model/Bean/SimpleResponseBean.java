package com.wxj.model.Bean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class SimpleResponseBean implements Serializable{

	private static final long serialVersionUID = -5055755846173938895L;

	@JSONField(ordinal = 1)
	private String responseCode = "";

	@JSONField(ordinal = 2)
	private String responseMsg = "";

	@JSONField(ordinal = 4)
	private String timestamp = "";

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
