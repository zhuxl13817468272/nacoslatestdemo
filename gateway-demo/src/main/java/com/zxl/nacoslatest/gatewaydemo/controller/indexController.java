package com.zxl.nacoslatest.gatewaydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {

    @GetMapping("/demo")
    public String hi(@RequestParam(value = "name",defaultValue = "archiz",required = false) String name){
        System.out.println("name = " + name);
        return "hi, demo "+name;
    }



}
