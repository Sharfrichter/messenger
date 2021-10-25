package com.company.messenger.data.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.company.messenger.data.entity.Message;

@Repository
public class MessageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Message> findByUserId(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Message> query = builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query.select(root).where(builder.equal(root.get("user_id"), id));

        return entityManager.createQuery(query).getResultStream().collect(Collectors.toList());
    }

    public List<Message> findByConversationId(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Message> query = builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query.select(root).where(builder.equal(root.get("conversation_id"), id));

        return entityManager.createQuery(query).getResultStream().collect(Collectors.toList());
    }

    public List<Message> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Message> query = builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query.select(root);

        return entityManager.createQuery(query).getResultStream().collect(Collectors.toList());
    }

    @Transactional
    public void save(Message message) {
        entityManager.merge(message);
    }



}
