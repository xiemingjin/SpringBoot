package com.xiemj.common.base;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xiemj.common.utils.HandleXml;
import com.xiemj.common.utils.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    /**
     * 把对象转为Json对象
     * @param obj
     * @return
     */
    protected ResponseEntity<String> toJsonResponse(final  Object obj)
    {
        return getResponseEntity(obj);
    }

    private  ResponseEntity<String> getResponseEntity(Object obj)
    {
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("text","html", Charset.forName("UTF-8"));
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue),headers, HttpStatus.OK);
         return  responseEntity;
    }

    /**
     * 吧参数处理为Map对象
     * @param params
     * @return
     */
    protected Map<String,Object> dealParamsToMap(String params)
    {
        Map<String,Object> resultMap = new HashMap<>();
        if(StringUtils.isEmpty(params))
        {
            return  resultMap;
        }
        //参数是xml
        if(params.indexOf("?xml")!=-1)
        {
            try {
                resultMap = HandleXml.readXml(params);
            }catch (Exception e)
            {
                e.printStackTrace();
                return resultMap;
            }
        }
        else
        {
            try {
                    resultMap = JSONObject.parseObject(params);
            }catch (Exception e)
            {
                e.printStackTrace();
                return resultMap;
            }
        }
       return resultMap;
    }
    
    
}
