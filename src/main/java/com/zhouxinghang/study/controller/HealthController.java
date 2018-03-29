package com.zhouxinghang.study.controller;

import com.google.common.collect.Maps;
import com.zhouxinghang.study.service.BootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by zhouxinghang on 2018/3/29.
 */

@Controller
public class HealthController {
    @Autowired
    private BootService bootService;

    @RequestMapping("/health")
    public ResponseEntity<?> home() {
        Map<String, Object> result = Maps.newHashMap();
        result.put("status", "running");
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }


    @RequestMapping("/hello")
    @ResponseBody
    public String hello(String name) {
        return bootService.hello(name);
    }

}
