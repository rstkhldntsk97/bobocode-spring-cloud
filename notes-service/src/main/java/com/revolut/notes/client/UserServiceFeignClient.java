package com.revolut.notes.client;

import com.revolut.notes.dto.PersonDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "persons")
public interface UserServiceFeignClient {

    @GetMapping("/persons/{id}")
    @CircuitBreaker(name = "persons", fallbackMethod = "defaultPerson")
    PersonDto getById(@PathVariable  Long id);


    default PersonDto defaultPerson(Long id, Throwable throwable) {
        return new PersonDto(id, "HIDDEN");
    }
}
