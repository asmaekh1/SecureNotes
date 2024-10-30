package com.secure.notes.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hello")
    String hello() {
        return "Hello World";
    }
    @GetMapping("/public/contact")
    String contact() {
        return "Hello contact";
    }  @GetMapping("/admin")
    String admin() {
        return "Hello admin";
    }
}
