package com.company.messenger.web.model.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.company.messenger.data.entity.User;
import com.company.messenger.web.model.UserWebModel;

@Component
public class UserConverter {

    public List<UserWebModel> convert(Collection<User> users) {
        return users.stream().map(this::convert).collect(Collectors.toList());
    }

    public UserWebModel convert(User user) {
        UserWebModel userWebModel = new UserWebModel();

        userWebModel.setId(user.getId());
        userWebModel.setFirstName(user.getFirstName());
        userWebModel.setLastName(user.getLastName());
        userWebModel.setPassword(user.getPassword());
        userWebModel.setUsername(user.getUsername());

        return userWebModel;
    }

    public User convert(UserWebModel userModel) {
        User user = new User();

        user.setId(userModel.getId());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setPassword(userModel.getPassword());
        user.setUsername(userModel.getUsername());

        return user;
    }

}
