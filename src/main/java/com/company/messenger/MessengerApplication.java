package com.company.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.company.messenger.data.Repository;
import com.company.messenger.data.entity.User;

@SpringBootApplication
public class MessengerApplication {

    @Autowired
    Repository repository;

    public static void main(String[] args) {
        SpringApplication.run(MessengerApplication.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return (strings) -> {
        };
    }


}
