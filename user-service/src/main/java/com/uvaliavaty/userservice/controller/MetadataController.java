package com.uvaliavaty.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetadataController {

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello, k8s! It's user-service.";
    }
}
