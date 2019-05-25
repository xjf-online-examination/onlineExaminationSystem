package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Title: SubjectOneVO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-25 15:13
 */
@Getter
@Setter
public class SubjectOneVO implements Serializable {
    /**
     * 科目代码
     */
    private String code;

    /**
     * 科目名称
     */
    private String name;
}