package com.accutrak.toolbox.adapters.repositories.log;

import com.accutrak.toolbox.adapters.repositories.RepositoryInt;
import com.accutrak.toolbox.domain.entities.Firmware;
import com.accutrak.toolbox.domain.entities.Log;
import com.accutrak.toolbox.domain.entities.TagConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Transactional
@Repository
public class LogRepo implements LogRepoInt{

    JpaLogRepo repo;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public LogRepo(JpaLogRepo repo) {
        this.repo = repo;
    }

    @Override
    public void insert(List<Log> logs) {
        try {
            repo.saveAll(logs);
        } catch (DataIntegrityViolationException e) {
            // Log the exception or handle it based on your application's requirements
            System.out.println("Some logs could not be saved due to a unique constraint violation.");
        }
    }

    @Override
    public void delete(List<Log> logList){
        repo.deleteAll(logList);
        clearCache();
    }

    public void clearCache() {
//        em.flush();
        em.clear();
    }

    @Override
    public List<Log> getLogByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Log> cq = cb.createQuery(Log.class);
        Root<Log> logRoot = cq.from(Log.class);

        cq.where(cb.equal(logRoot.get(field), value));
        TypedQuery<Log> query = em.createQuery(cq);


        List<Log> logList = query.getResultList();
        return logList;
    }

    @Override
    public List<Log> getLogsByDeviceIdToFrom(String deviceId, int from, int to) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Log> cq = cb.createQuery(Log.class);
        Root<Log> logRoot = cq.from(Log.class);

        // Create predicates for each condition
        Predicate deviceIdPredicate = cb.equal(logRoot.get("deviceId"), deviceId);
        Predicate fromPredicate = cb.greaterThanOrEqualTo(logRoot.get("lastModified"), from);
        Predicate toPredicate = cb.lessThanOrEqualTo(logRoot.get("lastModified"), to);

        // Combine predicates using `and` to apply all conditions
        cq.where(cb.and(deviceIdPredicate, fromPredicate, toPredicate));

        TypedQuery<Log> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Log> getLogByNameAndDeviceId(String fileName, String deviceId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Log> cq = cb.createQuery(Log.class);
        Root<Log> logRoot = cq.from(Log.class);

        // Create predicates for each condition
        Predicate fileNamePredicate = cb.equal(logRoot.get("fileName"), fileName);
        Predicate devIdPredicate = cb.equal(logRoot.get("deviceId"), deviceId);

        // Combine predicates using `and` to apply both conditions
        cq.where(cb.and(fileNamePredicate, devIdPredicate));

        TypedQuery<Log> query = em.createQuery(cq);

        return query.getResultList();
    }



    @Override
    public List<Log> getAll(){
        List<Log> all = repo.findAll();
        return all;
    }
}
