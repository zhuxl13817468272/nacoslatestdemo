package com.zxl.nacoslatest.nacosprovider.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zxl.nacoslatest.nacosprovider.common.ResultForm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {

    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name",defaultValue = "archiz",required = false) String name){
        System.out.println("name = " + name);

        //测试调用超时
//        try{
//            Thread.sleep(10000);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }

        return "hi, genius "+name;
    }


    @GetMapping("/hiSentinel")
//    @SentinelResource(value = "hiSentinel")  // 在controller层可以不用这个注解，但是如果在service层接口需要限流、降级就可以用这个注解
    public ResultForm hiSentinel(@RequestParam(value = "name",defaultValue = "archiz",required = false) String name){
        System.out.println("sentinel name = " + name);

        //测试调用超时
//        try{
//            Thread.sleep(10000);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }

        return ResultForm.success("hi, sentinel "+name);
    }

}
