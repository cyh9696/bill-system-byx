package com.winter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping(value = "hello1")
    public String bill() {
        return "hello";
    }

}

