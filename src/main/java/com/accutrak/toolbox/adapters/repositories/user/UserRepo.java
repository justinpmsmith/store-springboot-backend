package com.accutrak.toolbox.adapters.repositories.user;


import com.accutrak.toolbox.domain.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepo implements UserRepoInt{

    @PersistenceContext
    private EntityManager em;
    JpaUserRepo repo;


    @Autowired
    public UserRepo(JpaUserRepo repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public void insert(List<User> users) {
        repo.saveAll(users);
    }
    @Override
    public void delete(List<User> user){
        repo.deleteAll(user);
    }

    @Override
    public User getUserpByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);

        cq.where(cb.equal(userRoot.get("email"), email));
        TypedQuery<User> query = em.createQuery(cq);


        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }


    }

    @Override
    public List<User> getUsersByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);

        cq.where(cb.equal(userRoot.get(field), value));
        TypedQuery<User> query = em.createQuery(cq);


        List<User> userList = query.getResultList();
        return userList;


    }

    @Override
    public String getUuidByEmail(String email){ // deprecate
        List<User> userList = getUsersByStringField("email", email);

        if(userList.isEmpty()){
            return null;
        }
        String uuid = userList.get(0).getUuid();

        return  uuid;
    }

    @Override
    public List<String> getUuidListByEmail(String email){
        List<User> userList = getUsersByStringField("email", email);

        List<String> uniqueUuids = userList.stream()
                .map(User::getUuid) // Extract email from each User
                .distinct() // Remove duplicates
                .collect(Collectors.toList()); // Convert to a new List

        return  uniqueUuids;
    }

    public List<String> getDeviceIdListByEmail(String email){
        List<User> userList = getUsersByStringField("email", email);

        List<String> uniqueUuids = userList.stream()
                .map(User::getDeviceId) // Extract email from each User
                .distinct() // Remove duplicates
                .collect(Collectors.toList()); // Convert to a new List

        return  uniqueUuids;
    }


}
