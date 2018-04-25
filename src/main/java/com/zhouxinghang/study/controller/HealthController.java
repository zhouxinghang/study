package com.zhouxinghang.study.controller;

import com.google.common.collect.Maps;
import com.zhouxinghang.study.service.BootService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(HealthController.class);

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
        LOG.info("hello running==========>");
        return bootService.hello(name);
    }

    @RequestMapping("/notok")
    @ResponseBody
    public String error(String name) {
        int a = 1/0;
        return "OK";
    }

    @RequestMapping("/ok")
    @ResponseBody
    public String ok(String name) {
        return "OK";
    }

    @RequestMapping("/redirect")
    public String redirect(String name) {
        LOG.info("redirect running==========>");
        return "redirect:/redirect2";
    }

    @RequestMapping("/redirect2")
    public String redirect2(String name) {
        LOG.info("redirect2 running==========>");
        return "redirect:/hello";
    }

}
