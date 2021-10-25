package com.company.messenger.web.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.messenger.data.entity.Message;
import com.company.messenger.data.repository.ConversationRepository;
import com.company.messenger.data.repository.UserRepository;

@Component
public class MessageConverter {

    private UserRepository userRepository;

    private ConversationRepository conversationRepository;

    @Autowired
    public MessageConverter(UserRepository userRepository, ConversationRepository conversationRepository) {
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
    }

    public MessageWebModel convert(Message message) {
        MessageWebModel model = new MessageWebModel();

        model.setId(message.getId());
        model.setText(message.getText());
        model.setDate(message.getDate());
        model.setUserId(message.getId());
        model.setConversationId(model.getConversationId());

        return model;
    }

    public Message convert(MessageWebModel model) {
        Message message = new Message();

        message.setId(model.getId());
        message.setText(model.getText());
        message.setDate(model.getDate());
        message.setUser(userRepository.findById(model.getId())
            .orElseThrow(() -> new IllegalArgumentException("wrong id of user")));
        message.setConversation(conversationRepository.findById(model.getId())
            .orElseThrow(() -> new IllegalArgumentException("wrong id of conversation")));

        return message;
    }

}
