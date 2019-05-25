package com.wxj.utils;

import com.wxj.exception.BusinessException;
import com.wxj.exception.ParamEmptyException;
import com.wxj.exception.ParamInvalidException;
import com.wxj.model.Bean.ResponseBean;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>Title: ValidateParamsUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019/1/5 10:18
 */
public class ValidateParamsUtil<T> {
    private static Logger logger = LoggerFactory.getLogger(ValidateParamsUtil.class);

    public void vaildParams(T obj, String ...args) throws BusinessException {
        try {
            for (int i=0, length = args.length; i<length; i++) {
                Field field = obj.getClass().getDeclaredField(args[i]);
                field.setAccessible(true);
                String type = field.getType().getSimpleName();
                if ("String".equals(type)) {
                    String str = (String) field.get(obj);
                    if(StringUtil.isEmptyTrim(str)) {
                        ResponseBean responseBean = new ResponseBean("422", "请求参数不能为空" + field.getName());
                        throw new ParamEmptyException(JSONObject.fromObject(responseBean).toString());
                    }
                }
                if ("Integer".equals(type)) {
                    Integer integer = (Integer) field.get(obj);
                    if (null == integer) {
                        ResponseBean responseBean = new ResponseBean("422", "请求参数不能为空" + field.getName());
                        throw new ParamEmptyException(JSONObject.fromObject(responseBean).toString());
                    }
                }
                if ("List".equals(type)) {
                	List list = (List) field.get(obj);
                    if (null == list) {
                        ResponseBean responseBean = new ResponseBean("422", "请求参数不能为空" + field.getName());
                        throw new ParamEmptyException(JSONObject.fromObject(responseBean).toString());
                    }
                }

            }
        } catch (NoSuchFieldException e) {
            logger.error(e.getMessage());
            throw new ParamInvalidException(e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
            throw new ParamInvalidException(e.getMessage());
        }
    }

}
