package com.xiemj.controller;

import com.xiemj.common.annotation.Log;
import com.xiemj.common.base.BaseController;
import com.xiemj.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private TestService testService;

    @RequestMapping("/add")
    @Log("调用的是add")
    public ResponseEntity<String> add( ){
        Map<String,Object> reusltMap = testService.queryList();
        return super.toJsonResponse(reusltMap);
    }

    @RequestMapping("/add1")
    @Log("调用的是add1")
    public ResponseEntity<String> add1( ){
        Map<String,Object> reusltMap = testService.queryList1();
        return super.toJsonResponse(reusltMap);
    }

}