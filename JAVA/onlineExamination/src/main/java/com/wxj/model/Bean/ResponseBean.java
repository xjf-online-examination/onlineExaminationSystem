package com.wxj.model.Bean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBean <T> implements Serializable{

	private static final long serialVersionUID = -314405499007749011L;

	@JSONField(ordinal = 1)
	private String responseCode = "";

	@JSONField(ordinal = 2)
	private String responseMsg = "";
	
	@JSONField(ordinal = 3)
	private String timestamp = "";

	@JSONField(ordinal = 4)
	private T data;

	public ResponseBean(String responseCode, String responseMsg) {
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
	}
}
