package com.xiemj.service;

import com.xiemj.dao.SysDao;
import com.xiemj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysService {



    @Autowired
    private SysDao dao;

    public User findUserByName (String username )
    {

        User user = new User();
        Map<String,Object> resultMap = new HashMap<>();
       /* String username = (String) paramsMap.get("username");*/
        resultMap.put("resultCode",true);
        List<Map<String,Object>> resultList = new ArrayList<>();
        user = dao.queryUserByName(username);
     /*   resultMap.put("resultData",resultList);*/
        return  user;
    }
}
