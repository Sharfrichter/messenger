package com.company.messenger.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.messenger.data.entity.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

}
