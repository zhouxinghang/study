package com.zhouxinghang.study.controller;

import com.google.common.collect.Maps;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by zhouxinghang on 2018/3/29.
 */

@Controller
public class HealthController {


    @RequestMapping("/health")
    public ResponseEntity<?> home() {
        Map<String, Object> result = Maps.newHashMap();
        result.put("status", "running");
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

}
