package com.rdas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class Api {
    private static Logger log = LoggerFactory.getLogger(Api.class);

    @GetMapping(path = "/health", produces = "application/json")
    public ResponseEntity<?> checkHealth() {
        LocalDateTime timePoint = LocalDateTime.now();
        return new ResponseEntity<Object>(String.format("Pinging on %s/%s/%s. Health OK", timePoint.getYear(), timePoint.getMonth(), timePoint.getDayOfMonth()), HttpStatus.OK);
    }

}
