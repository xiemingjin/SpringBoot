package com.xiemj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SampleController {


    @RequestMapping("/hello")
    public String getListaUtentiView(ModelMap map){
        map.put("name", "Spring Boot");
        return "test";
    }


    @RequestMapping("/list")
    public String  listUser(Model model) {
        List<Map<String,Object>> userList = new ArrayList<>();
        for (int i = 0; i <10; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",i);
            map.put("name","张三"+i);
            map.put("age","张三"+i);
            map.put("address","张三"+i);
            userList.add(map);
            /*userList.add(new HashMap<i,"张三"+i,20+i,"中国广州"));*/
        }

        model.addAttribute("users", userList);
        return "thymeleaf";
    }
}
