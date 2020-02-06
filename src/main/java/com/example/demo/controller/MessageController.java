package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("message")
public class MessageController {

    @GetMapping("{message}")
    public Mono<String> getHello(@PathVariable("message") final String message) {
        return Mono.just("Hello, " + message);
    }

}
