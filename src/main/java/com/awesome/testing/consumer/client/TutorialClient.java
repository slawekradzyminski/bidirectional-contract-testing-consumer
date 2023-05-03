package com.awesome.testing.consumer.client;

import com.awesome.testing.consumer.dto.TutorialDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "Swagger3ExampleApp", url = "http://localhost:8080/api")
public interface TutorialClient {

    @RequestMapping(value = "/tutorials", method = GET)
    List<TutorialDto> getAll();
}
