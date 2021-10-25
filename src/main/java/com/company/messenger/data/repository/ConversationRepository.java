package com.company.messenger.data.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.company.messenger.data.entity.Conversation;

@Repository
public class ConversationRepository {

    @PersistenceContext
    private EntityManager entityManager;

   @Transactional
    public void save(Conversation conversation) {
        entityManager.merge(conversation);
    }

    public Optional<Conversation> findById(Long id) {
        return Optional.of(entityManager.find(Conversation.class, id));
    }

}
