package com.company.messenger.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.messenger.data.entity.Message;
import com.company.messenger.data.entity.User;
import com.company.messenger.data.repository.ConversationRepository;
import com.company.messenger.data.repository.MessageRepository;
import com.company.messenger.web.model.ConversationWebModel;
import com.company.messenger.web.model.MessageWebModel;
import com.company.messenger.web.model.converter.ConversationConverter;
import com.company.messenger.web.model.converter.MessageConverter;

@RestController
public class ConversationsController {

    private final ConversationRepository conversationRepository;

    private final MessageRepository messageRepository;

    private final ConversationConverter conversationConverter;

    private final MessageConverter messageConverter;

    @Autowired
    public ConversationsController(ConversationRepository conversationRepository, MessageRepository messageRepository,
        ConversationConverter conversationConverter, MessageConverter messageConverter) {

        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.conversationConverter = conversationConverter;
        this.messageConverter = messageConverter;
    }

    //todo receive conversations from db not from user
    @GetMapping("/conversations")
    public List<ConversationWebModel> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return conversationConverter.convert(user.getConversations());
    }

    @GetMapping("/conversations/{id}")
    public List<MessageWebModel> getConversationMessages(@PathVariable Long id) {
        return messageConverter.convert(conversationRepository.findById(id).get().getMessages());
    }

    @PostMapping("/conversations/{id}")
    public void saveMessage(@RequestBody String text, @PathVariable(name = "id") Long conversationId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        MessageWebModel message = new MessageWebModel();

        message.setConversationId(conversationId);
        message.setUserId(user.getId());
        message.setText(text);
        message.setDate(LocalDate.now());

        messageRepository.save(messageConverter.convert(message));
    }

}
