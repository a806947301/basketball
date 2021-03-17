package com.wt.basketball.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wut
 * @since 2021-03-17
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
