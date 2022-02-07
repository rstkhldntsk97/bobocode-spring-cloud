package com.revolut.notes.client;

import com.revolut.notes.dto.PersonDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-server")
public interface UserServiceFeignClient {

    @GetMapping("/users/{id}")
    @CircuitBreaker(name = "users-server", fallbackMethod = "defaultUser")
    PersonDto getById(@PathVariable  Long id);


    default PersonDto defaultUser(Long id, Throwable throwable) {
        return new PersonDto(id, "HIDDEN");
    }
}
