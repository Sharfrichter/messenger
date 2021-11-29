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

import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.company.messenger.data.entity.Conversation;
import com.company.messenger.data.entity.Message;
import com.company.messenger.data.entity.User;
import com.company.messenger.web.model.ConversationWebModel;

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
