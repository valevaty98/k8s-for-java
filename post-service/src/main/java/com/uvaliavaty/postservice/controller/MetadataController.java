package com.uvaliavaty.postservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetadataController {

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello, k8s! It's post-service.";
    }
}
