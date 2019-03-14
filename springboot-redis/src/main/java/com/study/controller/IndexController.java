package com.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 东东 on 2019/3/14.
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "欢迎光临";
    }

}
