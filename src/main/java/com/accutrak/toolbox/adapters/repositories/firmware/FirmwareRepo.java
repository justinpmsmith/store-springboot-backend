package com.accutrak.toolbox.adapters.repositories.firmware;

import com.accutrak.toolbox.domain.entities.Firmware;
import com.accutrak.toolbox.domain.entities.Log;
import com.accutrak.toolbox.domain.entities.Software;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FirmwareRepo implements FirmwareRepoInt {

    @PersistenceContext
    private EntityManager em;

    private JpaFirmwareRepo repo;


    @Autowired
    public FirmwareRepo(JpaFirmwareRepo repo) {
        this.repo = repo;
    }

    @Override
    public void insert(List<Firmware> firmwareList) {
        repo.saveAll(firmwareList);

    }


    @Override
    public void delete(List<Firmware> firmwareList) {
        repo.deleteAll(firmwareList);
    }

    //    public Firmware getSingleFirmwareByStringField(String field, String value) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Firmware> cq = cb.createQuery(Firmware.class);
//        Root<Firmware> firmwareRoot = cq.from(Firmware.class);
//
//        cq.where(cb.equal(firmwareRoot.get(field), value));
//        TypedQuery<Firmware> query = em.createQuery(cq);
//
//        try {
//            return query.getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }
    public Firmware getSingleFirmwareByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Firmware> cq = cb.createQuery(Firmware.class);
        Root<Firmware> firmwareRoot = cq.from(Firmware.class);

        cq.where(cb.equal(firmwareRoot.get(field), value));
        TypedQuery<Firmware> query = em.createQuery(cq);

        List<Firmware> results = query.getResultList();
        if (results.isEmpty()) {
            return null; // No matching results
        } else {
            return results.get(0); // Exactly one result found
        }
    }

    public List<Firmware> getFirmwareByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Firmware> cq = cb.createQuery(Firmware.class);
        Root<Firmware> firmwareRoot = cq.from(Firmware.class);

        cq.where(cb.equal(firmwareRoot.get(field), value));
        TypedQuery<Firmware> query = em.createQuery(cq);

        List<Firmware> results = query.getResultList();
        return results;
    }


    public List<Firmware> getValidFirmware() {
        long currentTimestamp = System.currentTimeMillis() / 1000;


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Firmware> cq = cb.createQuery(Firmware.class);
        Root<Firmware> firmwareRoot = cq.from(Firmware.class);

        Predicate effectiveFrom = cb.lessThanOrEqualTo(firmwareRoot.get("effectiveFrom"), currentTimestamp);
        Predicate effectiveTo = cb.greaterThanOrEqualTo(firmwareRoot.get("effectiveTo"), currentTimestamp);

        cq.where(cb.and(effectiveFrom, effectiveTo));
        TypedQuery<Firmware> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Firmware> getAllFirmware() {
        List<Firmware> all = repo.findAll();
        return all;
    }


    public List<Firmware> getFirmwareByNameAndSite(String fileName, String site) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Firmware> cq = cb.createQuery(Firmware.class);
        Root<Firmware> fwRoot = cq.from(Firmware.class);

        // Create predicates for each condition
        Predicate fileNamePredicate = cb.equal(fwRoot.get("fileName"), fileName);
        Predicate sitePredicate = cb.equal(fwRoot.get("site"), site);

        // Combine predicates using `and` to apply both conditions
        cq.where(cb.and(fileNamePredicate, sitePredicate));

        TypedQuery<Firmware> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Firmware> getFirmwareByHashAndSite(String hash, String site) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Firmware> cq = cb.createQuery(Firmware.class);
        Root<Firmware> fwRoot = cq.from(Firmware.class);

        // Create predicates for each condition
        Predicate hashPredicate = cb.equal(fwRoot.get("hash"), hash);
        Predicate sitePredicate = cb.equal(fwRoot.get("site"), site);

        // Combine predicates using `and` to apply both conditions
        cq.where(cb.and(hashPredicate, sitePredicate));

        TypedQuery<Firmware> query = em.createQuery(cq);
        return query.getResultList();
    }
}