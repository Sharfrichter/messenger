package com.company.messenger.data;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Repository {

    private final SessionFactory sessionFactory;

    @Autowired
    public Repository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> List<T> findAll(Class<T> entityClass) {
        return doAction(session -> {
            return session.createQuery(getSelectQuery(entityClass), entityClass).getResultList();
        });
    }

    public <T, I> T findById(Class<T> entityClass, I id) {
        return doAction(session -> {
            return session.find(entityClass, id);
        });
    }

    public <T> void save(T entity) {
        doAction(session -> {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        });
    }

    public <T> void delete(T entity) {
        doAction(session -> {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        });
    }

    public <T> T findByColumn(Class<T> entityClass, String columnName, String value) {
        return doAction(session -> {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);

            Root<T> root = criteriaQuery.from(entityClass);
            criteriaQuery.select(root).where(builder.like(root.get(columnName), value));

            Query<T> query = session.createQuery(criteriaQuery);

            try {
                return query.getSingleResult();
            }
            catch (NoResultException e) {
                return null;
            }
        });
    }

    private <T> T doAction(Function<Session, T> function) {
        try (Session session = sessionFactory.openSession()) {
            return function.apply(session);
        }
    }

    private void doAction(Consumer<Session> function) {
        try (Session session = sessionFactory.openSession()) {
            function.accept(session);
        }
    }

    private <T> String getSelectQuery(Class<T> entityClass) {
        return String.format("SELECT a FROM %s a", entityClass.getSimpleName());
    }


}
