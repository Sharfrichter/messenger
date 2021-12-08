package com.company.messenger.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public void createUser(@RequestBody UserWebModel user, HttpServletResponse response) {
        try {
            userService.save(userConverter.convert(user));
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @GetMapping("/user/{username}")
    public List<UserWebModel> getFriends(@PathVariable(value = "username") String username) {
        return userConverter.convert(userService.findFriends(username));
    }

    @GetMapping("/user/add/friend/{username}")
    public void addFriend(@PathVariable(value = "username") String username) {
        User friend = userService.findByUsername(username);
        if (friend != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = (User) authentication.getPrincipal();
            userService.addFriend(currentUser.getId(), friend.getId());
        }
    }


}
