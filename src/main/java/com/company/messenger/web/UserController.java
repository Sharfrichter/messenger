package com.company.messenger.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.messenger.data.entity.User;
import com.company.messenger.data.service.UserService;
import com.company.messenger.web.model.UserWebModel;
import com.company.messenger.web.model.converter.UserConverter;

@RestController
public class UserController {

    private final UserService userService;

    private final UserConverter userConverter;

    @Autowired
    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping("/user/create")
    public void createUser(@RequestBody UserWebModel user) {
        userService.save(userConverter.convert(user));
    }

    @GetMapping("/user/{username}")
    public List<UserWebModel> getFriends(@PathVariable(value = "username") String username) {
        return userConverter.convert(userService.findFriends(username));
    }

    @GetMapping("/user/addFriend/{username}")
    public void addFriend(@PathVariable(value = "username") String username) {
        User user = userService.findByUsername(username);
    }

}
