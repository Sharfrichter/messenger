package com.company.messenger.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.messenger.data.repository.MessageRepository;
import com.company.messenger.web.model.converter.MessageConverter;
import com.company.messenger.web.model.MessageWebModel;

@RestController
public class MessageController {

    private MessageRepository messageRepository;

    private MessageConverter messageConverter;

    @Autowired
    public MessageController(MessageRepository messageRepository, MessageConverter messageConverter) {
        this.messageRepository = messageRepository;
        this.messageConverter = messageConverter;
    }

    @GetMapping("/messages")
    public List<MessageWebModel> findAll() {
        return messageConverter.convert(messageRepository.findAll());
    }

    @PostMapping("/messages")
    public void save(@RequestBody MessageWebModel message) {
        messageRepository.save(messageConverter.convert(message));
    }

}
