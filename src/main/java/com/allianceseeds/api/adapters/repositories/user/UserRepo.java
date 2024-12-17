package com.allianceseeds.api.adapters.repositories.user;

import com.allianceseeds.api.domain.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo implements UserRepoInt {

    @PersistenceContext
    private EntityManager em;

    private final JpaUserRepo repo;

    @Autowired
    public UserRepo(JpaUserRepo repo) {
        this.repo = repo;
    }

    @Override
    public void insert(List<User> entityList) {
        repo.saveAll(entityList);
    }

    @Override
    public void delete(List<User> entityList) {
        repo.deleteAll(entityList);
    }

    @Override
    public List<User> getUserByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        cq.where(cb.equal(root.get(field), value));
        TypedQuery<User> query = em.createQuery(cq);

        return query.getResultList();
    }
}