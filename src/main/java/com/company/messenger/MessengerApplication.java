package com.company.messenger;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.company.messenger.data.entity.Message;
import com.company.messenger.data.entity.User;
import com.company.messenger.data.repository.MessageRepository;
import com.company.messenger.data.service.UserService;

@SpringBootApplication
public class MessengerApplication {

    private MessageRepository messageRepository;

    private UserService userService;

    @Autowired
    public MessengerApplication(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MessengerApplication.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return (strings) -> {
            messageRepository.save(new Message(1l, 3, "himaek", LocalDate.now()));
            userService.save(new User("alex", "123"));
        };
    }

}
