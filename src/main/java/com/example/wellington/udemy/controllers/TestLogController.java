package com.example.wellington.udemy.controllers;

import com.example.wellington.udemy.service.PersonServices;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

@RestController
public class TestLogController {
    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @GetMapping("/test")
    public String testLog(){
        logger.debug("This is an debug log");
        logger.info("This is an info log");
        logger.warn("This is an warn log");
        logger.error("This is an error log");

        return "logs generated succefully";
    }
}
