package com.revolut.persons.controller;

import com.revolut.persons.domain.User;
import com.revolut.persons.repository.UserRepository;
import com.revolut.persons.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserRepository repository;

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @PatchMapping("/updateUserV2/{id}")
    public User updateUserV2(@PathVariable Long id, @RequestBody User user) {
        return service.updateUserV2(id, user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        return service.deleteUser(id);
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with id " + id + " doesn't exist"));
    }

    @GetMapping
    public List<User> getAll() {
        return repository.findAll();
    }

//    @GetMapping("/getPersonNotes/{personId}")
//    public UserDto getUserWithNotes(@PathVariable Long personId) {
//        return service.getPersonNotesById(personId);
//    }

}
