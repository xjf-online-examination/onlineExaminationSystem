package com.wxj.model.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageBean <T> implements Serializable{

	private static final long serialVersionUID = 7854485654364601274L;

	@JSONField(ordinal = 1)
	private Long count;

	@JSONField(ordinal = 2)
	private Integer currentPage;

	@JSONField(ordinal = 3)
	private Integer pageSize;
	
	@JSONField(ordinal = 4)
	private List<T> list = new ArrayList<T>();

	public PageBean() {
		
	}
	
	public PageBean(long count, Integer currentPage, Integer pageSize, List<T> list) {
		this.count = count;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.list = list;
	}
}
