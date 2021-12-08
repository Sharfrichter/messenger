package com.company.messenger.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.messenger.data.entity.Conversation;
import com.company.messenger.data.entity.Message;
import com.company.messenger.data.entity.User;
import com.company.messenger.data.repository.ConversationRepository;
import com.company.messenger.data.repository.MessageRepository;
import com.company.messenger.data.service.UserService;
import com.company.messenger.web.model.ConversationWebModel;
import com.company.messenger.web.model.MessageWebModel;
import com.company.messenger.web.model.converter.ConversationConverter;
import com.company.messenger.web.model.converter.MessageConverter;
import com.company.messenger.web.model.converter.UserConverter;

@RestController
public class ConversationsController {

    private final ConversationRepository conversationRepository;

    private final MessageRepository messageRepository;

    private final ConversationConverter conversationConverter;

    private final MessageConverter messageConverter;

    private final UserService userService;

    private final UserConverter userConverter;

    @Autowired
    public ConversationsController(ConversationRepository conversationRepository, MessageRepository messageRepository,
        ConversationConverter conversationConverter, MessageConverter messageConverter, UserService userService, UserConverter userConverter) {

        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.conversationConverter = conversationConverter;
        this.messageConverter = messageConverter;
        this.userService = userService;
        this.userConverter = userConverter;
    }

    //todo receive conversations from db not from user
    @GetMapping("/conversations")
    public List<ConversationWebModel> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return conversationConverter.convert(user.getConversations());
    }

    @GetMapping("/conversations/start/{username}")
    public Long startConversation(@PathVariable(value = "username") String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Conversation conversation = new Conversation();
        conversation.setUsers(Set.of(user, userService.findByUsername(username)));
        conversation.setName(user.getUsername() + " and " + username);
        return conversationRepository.save(conversation).getId();
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
        message.setUser(userConverter.convert(user));
        message.setText(text);
        message.setDate(LocalDateTime.now());

        messageRepository.save(messageConverter.convert(message));
    }

}
