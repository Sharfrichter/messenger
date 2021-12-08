package com.company.messenger.web.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageWebModel {

    private Long id;

    private String text;

    private LocalDateTime date;

    private UserWebModel user;

    private Long conversationId;

}
