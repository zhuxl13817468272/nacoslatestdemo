package com.zxl.nacoslatest.nacosclient.feign;

import com.zxl.nacoslatest.nacosclient.common.ResultForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "nacos-provider")
public interface FeignClientDemo {

    @RequestMapping(value = "hi",method = RequestMethod.GET)
    String hi(@RequestParam(value = "name",defaultValue = "archiz",required = false) String name);

    @RequestMapping(value = "hiSentinel",method = RequestMethod.GET)
    ResultForm hiSentinel(@RequestParam(value = "name",defaultValue = "archiz",required = false) String name);



}
