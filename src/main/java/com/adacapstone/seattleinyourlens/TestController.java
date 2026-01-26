package com.adacapstone.seattleinyourlens;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/") // This maps the root URL
    public String hello() {
        return "Hello, Seattle In Your Lens or what not!";
    }
}
