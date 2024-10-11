package com.ecommerce.commerce.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/")
    public Map<String, Boolean> healtCheck() {
        Map<String, Boolean> response = new HashMap<>();
        response.put("Running", true);

        return response;
    }
}
