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
import org.hibernate.query.NativeQuery;
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

    public List<User> findFriends(String username) {
        String sql =
            "select *\n" +
            "from USER\n" +
            "where id in (select SECOND_PERSON_ID\n" +
            "             from FRIEND\n" +
            "             where FIRST_PERSON_ID = (select id\n" +
            "                                      from USER\n" +
            "                                      where USERNAME = ?));";

        Session session = entityManager.unwrap(Session.class);
        NativeQuery<User> query = session.createSQLQuery(sql).setParameter(1, username).addEntity(User.class);
        return query.list();
    }

    @Transactional
    public void addFriend(Long firstId, Long secondId) {
        String sql = "insert into friend(FIRST_PERSON_ID, SECOND_PERSON_ID) values (?, ?)";
        entityManager.createNativeQuery(sql).setParameter(1, firstId).setParameter(2, secondId).executeUpdate();
    }

}
