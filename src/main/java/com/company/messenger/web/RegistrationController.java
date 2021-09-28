package com.company.messenger.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.messenger.data.entity.User;
import com.company.messenger.data.service.UserService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService service;

    @Autowired
    public RegistrationController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public String register(@RequestBody @Valid User user, BindingResult bindingResult) {
        //todo answer
        if (bindingResult.hasErrors()) {
            return "Error";
        }
        if (!service.register(user)) {
            return "User already exist";
        }
        return "OK";
    }
}
