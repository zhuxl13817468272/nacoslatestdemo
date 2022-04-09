package com.zxl.nacoslatest.nacosclient.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxl.nacoslatest.nacosclient.common.ResultForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        log.info("nacosclient BlockExceptionHandler BlockException================"+e.getRule());
        ResultForm resultForm = null;

        if (e instanceof FlowException) {
            resultForm = new ResultForm(100,"nacosclient接口限流了");

        } else if (e instanceof DegradeException) {
            resultForm = new ResultForm(101,"nacosclient服务降级了");

        } else if (e instanceof ParamFlowException) {
            resultForm = new ResultForm(102,"nacosclient热点参数限流了");

        } else if (e instanceof SystemBlockException) {
            resultForm = new ResultForm(103,"nacosclient触发系统保护规则了");

        } else if (e instanceof AuthorityException) {
            resultForm = new ResultForm(104,"nacosclient授权规则不通过");
        }

        //返回json数据
        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), resultForm);

    }
}
