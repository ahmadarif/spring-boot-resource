package com.ahmadarif.resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/admin")
    public Map haloApi(){
        Map<String, Object> data = new HashMap<>();
        data.put("api", "ADMIN");
        data.put("waktu", new Date().toString());
        return data;
    }

    @GetMapping("/user")
    public Map waktu(){
        Map<String, Object> data = new HashMap<>();
        data.put("api", "USER");
        data.put("waktu", new Date().toString());
        return data;
    }

}