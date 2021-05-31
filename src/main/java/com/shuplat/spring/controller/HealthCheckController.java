package com.shuplat.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {
        @GetMapping
        public ResponseEntity<String> getStatus() {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
}
