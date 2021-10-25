package com.company.messenger;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.company.messenger.data.entity.Conversation;
import com.company.messenger.data.entity.Message;
import com.company.messenger.data.entity.User;
import com.company.messenger.data.repository.ConversationRepository;
import com.company.messenger.data.repository.MessageRepository;
import com.company.messenger.data.service.UserService;

@SpringBootApplication
@ComponentScan("com.company.messenger")
public class MessengerApplication {

    private final MessageRepository messageRepository;

    private final ConversationRepository conversationRepository;

    private final UserService userService;

    public MessengerApplication(MessageRepository messageRepository, ConversationRepository conversationRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.userService = userService;
    }

    @Autowired

    public static void main(String[] args) {
        SpringApplication.run(MessengerApplication.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return (strings) -> {
            User user = new User(
                1l,
                "alex",
                "nevski",
                "alex",
                "123"
                , new HashSet<>());
            userService.save(user);

            Conversation conversation = new Conversation(
                1l,
                "conver",
                Set.of(user));
            conversationRepository.save(conversation);

            Message message = new Message(1l, "hi mark", LocalDate.now(), user, conversation);
            messageRepository.save(message);

        };
    }

}
