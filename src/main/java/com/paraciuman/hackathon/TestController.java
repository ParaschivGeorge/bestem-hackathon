package com.paraciuman.hackathon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    String content = "test3";

    @GetMapping(path="/test")
    public String testString() {
        return content;
    }
}
