package org.kaiLearn.spring.core.controller;


import org.kaiLearn.spring.core.service.DemoService;
import org.springframework.stereotype.Controller;

@Controller
public class DemoController {

    private DemoService demoService;
    public String hello(){
        return "Hello Controller";
    }
}
