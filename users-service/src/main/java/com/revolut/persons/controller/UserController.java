package com.revolut.persons.controller;

import com.revolut.persons.dto.UserDto;
import com.revolut.persons.entity.UserEntity;
import com.revolut.persons.model.CreateUserRequestModel;
import com.revolut.persons.model.CreateUserResponseModel;
import com.revolut.persons.repository.UserRepository;
import com.revolut.persons.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final Environment environment;
    private final UserService service;
    private final UserRepository repository;
    private final ModelMapper mapper;

//    @PostMapping("/createUser")
//    public User createUser(@RequestBody User user) {
//        return service.createUser(user);
//    }

    @PostMapping(value = "/createUser",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody @Valid CreateUserRequestModel userDetails) {
        var userDto = mapper.map(userDetails, UserDto.class);
        UserDto createdUser = service.createUser(userDto);
        CreateUserResponseModel responseModel = mapper.map(createdUser, CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }

    @GetMapping("/status/check")
    public String status() {
        return "Port " + environment.getProperty("local.server.port") + ", with token " + environment.getProperty("token.secret");
    }

//    @PutMapping("/updateUser/{id}")
//    public User updateUser(@PathVariable Long id, @RequestBody User user) {
//        return service.updateUser(id, user);
//    }
//
//    @PatchMapping("/updateUserV2/{id}")
//    public User updateUserV2(@PathVariable Long id, @RequestBody User user) {
//        return service.updateUserV2(id, user);
//    }
//
//    @DeleteMapping("/deleteUser/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        return service.deleteUser(id);
//    }
//
//    @GetMapping("/getUser/{id}")
//    public User getUser(@PathVariable Long id) {
//        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with id " + id + " doesn't exist"));
//    }
//
    @GetMapping
    public List<UserEntity> getAll() {
        return repository.findAll();
    }
//
//    @GetMapping("/getPersonNotes/{personId}")
//    public UserDto getUserWithNotes(@PathVariable Long personId) {
//        return service.getPersonNotesById(personId);
//    }

}
