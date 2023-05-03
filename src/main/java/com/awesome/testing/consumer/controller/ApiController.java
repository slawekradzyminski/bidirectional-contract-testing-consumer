package com.awesome.testing.consumer.controller;

import com.awesome.testing.consumer.client.TutorialClient;
import com.awesome.testing.consumer.dto.TutorialDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {
    @Autowired
    TutorialClient tutorialClient;

    @GetMapping("/all")
    public List<TutorialDto> getAll() {
        return tutorialClient.getAll();
    }

}
