package com.company.messenger.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.messenger.data.entity.Message;
import com.company.messenger.data.repository.MessageRepository;
import com.company.messenger.web.model.MessageConverter;
import com.company.messenger.web.model.MessageWebModel;

@RestController
public class MessageController {

    private final MessageRepository messageRepository;

    private MessageConverter messageConverter;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired



    @GetMapping("/messages")
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @PostMapping("/messages")
    public void save(MessageWebModel message) {
        messageConverter.convert(message);
    }

}
