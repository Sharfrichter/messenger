package com.company.messenger.entity;

import java.util.List;

import lombok.Data;

@Data
public class Conversation {

    private Integer id;

    private List<User> users;

}
