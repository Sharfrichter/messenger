package com.company.messenger.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.messenger.data.entity.User;
import com.company.messenger.data.repository.ConversationRepository;
import com.company.messenger.web.model.ConversationWebModel;
import com.company.messenger.web.model.converter.ConversationConverter;

@RestController
public class ConversationsController {

    private final ConversationRepository conversationRepository;

    private final ConversationConverter conversationConverter;

    @Autowired
    public ConversationsController(ConversationRepository conversationRepository, ConversationConverter conversationConverter) {
        this.conversationRepository = conversationRepository;
        this.conversationConverter = conversationConverter;
    }

    @GetMapping("/conversations")
    public List<ConversationWebModel> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return conversationConverter.convert(user.getConversations());
    }

}
