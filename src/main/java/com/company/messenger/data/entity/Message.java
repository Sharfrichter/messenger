package com.company.messenger.data.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Message {

    private Integer id;

    private Integer conversationId;

    private Integer userId;

    private String text;

    private LocalDate date;

}
