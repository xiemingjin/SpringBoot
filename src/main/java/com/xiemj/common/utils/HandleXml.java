package com.xiemj.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
public class HandleXml {
    /**
     *
     * <p>说明：读取xml，封装成JSON对象</p>
     * <p>时间：2015-9-26 下午3:43:48</p>
     * @param xmlStr
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static Map<String,Object> readXml(String xmlStr) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        Document document = DocumentHelper.parseText(xmlStr);
        Element root = document.getRootElement();
        Iterator it = root.elementIterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            if ("Params".equals(element.getName()))
            {
                Iterator eleIt = element.elementIterator();
                while (eleIt.hasNext())
                {
                    Element elementChild = (Element) eleIt.next();
                    if ("Param".equals(elementChild.getName()))
                    {
                        Iterator attrItc = elementChild.attributeIterator();
                        while (attrItc.hasNext())
                        {
                            Attribute a = (Attribute) attrItc.next();
                            if ("name".equals(a.getName()))
                            {
                                map.put(a.getValue(), elementChild.getTextTrim());
                            }
                        }
                    }
                }
            }
        }
        return map;
    }
}
