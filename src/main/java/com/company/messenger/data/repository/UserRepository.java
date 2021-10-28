package com.company.messenger.data.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.company.messenger.data.entity.User;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public User findByUsername(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("username"), username));

        return entityManager.createQuery(query).getSingleResult();
    }

    public Optional<User> findById(Long id) {
        return Optional.of(entityManager.find(User.class, id));
    }

    @Transactional
    public void save(User user) {
        entityManager.merge(user);
    }

    public List<User> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }

}
