package com.company.basketservice.client;

import com.company.basketservice.dto.TestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service", path = "/user-service/test")
public interface UserClient {
    @GetMapping(value = "/get")
    TestResponse getValue();
}
