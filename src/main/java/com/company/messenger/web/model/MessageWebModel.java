package com.company.messenger.web.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MessageWebModel {

    private Long id;

    private String text;

    private LocalDate date;

    private Long userId;

    private Long conversationId;

}
