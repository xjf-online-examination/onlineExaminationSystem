package com.wxj.model.Bean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestBean <T> implements Serializable{

	private static final long serialVersionUID = 484604014474085210L;

	@JSONField(ordinal = 1)
	private String username;

	@JSONField(ordinal = 2)
	private String securityKey;
	
	@JSONField(ordinal = 3)
	private String timestamp;

	@JSONField(ordinal = 4)
	private T data;

}
