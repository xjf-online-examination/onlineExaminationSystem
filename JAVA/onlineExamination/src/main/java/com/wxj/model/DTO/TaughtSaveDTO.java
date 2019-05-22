package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: TaughtSaveDTO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-22 23:07
 */
@Getter
@Setter
public class TaughtSaveDTO implements Serializable {

    private Integer teacherId;

    private List<Integer> classCourseIdList;
}