package com.xiemj.service;

import com.xiemj.dao.TestDao;
import com.xiemj.dao1.TestDao1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestService {

    @Autowired
    private TestDao dao;

    @Autowired
    private TestDao1 dao1;

    public Map<String,Object> queryList()
    {

        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> resultData = new HashMap<>();
        List<Map<String,Object>> list = new ArrayList<>();
        resultMap.put("resultCode",true);
        list = dao.queryList();
        resultData.put("rows",list);
        resultData.put("total",list.size());
        resultMap.put("resultData",resultData);
        return  resultMap;
    }

    public Map<String,Object> queryList1()
    {

        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> resultData = new HashMap<>();
        List<Map<String,Object>> list = new ArrayList<>();
        resultMap.put("resultCode",true);
        list = dao1.queryList();
        resultData.put("rows",list);
        resultData.put("total",list.size());
        resultMap.put("resultData",resultData);
        return  resultMap;
    }

}
