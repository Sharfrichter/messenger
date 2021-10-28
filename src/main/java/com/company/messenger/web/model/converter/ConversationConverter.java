package com.company.messenger.web.model.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.messenger.data.entity.Conversation;
import com.company.messenger.web.model.ConversationWebModel;

@Component
public class ConversationConverter {

    private final UserConverter userConverter;

    @Autowired
    public ConversationConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public List<ConversationWebModel> convert(Collection<Conversation> conversations) {
        return conversations.stream().map(this::convert).collect(Collectors.toList());
    }

    public ConversationWebModel convert(Conversation conversation) {
        ConversationWebModel conversationWebModel = new ConversationWebModel();

        conversationWebModel.setId(conversation.getId());
        conversationWebModel.setName(conversation.getName());
        conversationWebModel.setUsers(userConverter.convert(conversation.getUsers()));

        return conversationWebModel;
    }

}
