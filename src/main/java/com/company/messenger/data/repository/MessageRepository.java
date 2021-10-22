package com.company.messenger.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.messenger.data.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

}
