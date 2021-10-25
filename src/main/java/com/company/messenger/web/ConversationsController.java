package com.company.messenger.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.messenger.data.entity.Conversation;
import com.company.messenger.data.entity.User;
import com.company.messenger.data.repository.ConversationRepository;

@RestController
public class ConversationsController {

    private final ConversationRepository conversationRepository;

    @Autowired
    public ConversationsController(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @GetMapping("/conversations")
    public List<Conversation> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return null;
    }

}
