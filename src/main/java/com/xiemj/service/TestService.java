package com.xiemj.service;

import com.xiemj.dao.TestDao;
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
}
