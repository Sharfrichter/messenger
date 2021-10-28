package com.company.messenger.web.model;

import java.util.Collection;
import java.util.Set;

import lombok.Data;

@Data
public class ConversationWebModel {

    private Long id;

    private String name;

    private Collection<UserWebModel> users;

}
