package com.zxl.nacoslatest.nacosclient.controller;

import com.zxl.nacoslatest.nacosclient.common.ResultForm;
import com.zxl.nacoslatest.nacosclient.feign.FeignClientDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//@RefreshScope   //动态替换最新的配置Bean
public class NacosController {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private FeignClientDemo feignClientDemo;


    @Value("${user.name}")
    private String userName;
    @Value("${user.age}")
    private String age;

    @GetMapping("/autoConfig")
    public String autoConfig(){
        return appName+": name = "+userName+"; age = "+age;
    }

    @GetMapping("/hiresttemplate")
    public String hiResttemplate(){
//        return restTemplate.getForObject("http://172.16.20.32:8001/hi?name=resttemplate",String.class);
        /**
         * nacos默认的负载均衡策略：ZoneAvoidanceRule--判断服务所在区域Zone性能和可用的服务,在没有区域Zone的环境下类似于轮询（RoundRobbinRule）
         *      相对于负载均衡策略，Nacos还增加了NacosRule--同集群中优先调用，基于随机权重（RandomRule）
         */
        return restTemplate.getForObject("http://nacos-provider/hi?name=resttemplate", String.class);
    }


    @GetMapping("hi")
    public String hiFeign(@RequestParam(value = "name",defaultValue = "archiz",required = false) String name) {
        System.out.println("name = " + name);
        /**
         * 1. pom.xml  引入包spring-cloud-starter-openfeign 版本号在父模块import
         * 2. 启动类增加@EnableFeignClients
         * 3. 编写声明式、模板化的HTTP客户端接口
         * 4. 请求日志、超时、GZIP压缩等配置实战配置
         */
        String result = feignClientDemo.hi(name);
        return result;
    }


    @GetMapping("hiSentinel")
//    @SentinelResource(value = "hiSentinel")  // 在controller层可以不用这个注解，但是如果在service层接口需要限流、降级就可以用这个注解
    public ResultForm hiSentinel(@RequestParam(value = "name",defaultValue = "archiz",required = false) String name){
        System.out.println("hi sentinel name = " + name);
        ResultForm result = feignClientDemo.hiSentinel(name);
//        String result = restTemplate.getForObject("http://nacos-provider/hiSentinel?name=hiSentinel", String.class);
        return result;
    }

}
