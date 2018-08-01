package com.xiemj.common.utils;

/**
 * 字符串处理工具类
 */
public class StringUtils {

    /**
     * 字符串是否为空 yes==》true  no ==》false
     * @param params
     * @return
     */
    public   static  boolean isEmpty(String params)
    {
        boolean result = false;
        if(null == params || "".equals(params) || "".equals(params.trim()))
        {
            result =  true;
        }
        return  result;
    }
}
