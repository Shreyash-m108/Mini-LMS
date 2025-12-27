package com.minilms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class Hello {
    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        int x;
        return "Shreyash Magdum";
    }
}
