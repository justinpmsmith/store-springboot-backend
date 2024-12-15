package com.accutrak.toolbox.adapters.repositories.software;

import com.accutrak.toolbox.domain.entities.Software;
import com.accutrak.toolbox.domain.entities.TagConfig;
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

@Repository
public class SoftwareRepo implements SoftwareRepoInt {

    @PersistenceContext
    private EntityManager em;
    JpaSoftwareRepo repo;

    @Autowired
    public SoftwareRepo(JpaSoftwareRepo repo) {
        this.repo = repo;
    }

    @Override
    public void insert(List<Software> softwareList) {
        repo.saveAll(softwareList);
    }

    @Override
    public void delete(List<Software> software){
        repo.deleteAll(software);
    }
    @Override
    public List<Software> getValidSoftware(){
        long currentTimestamp = System.currentTimeMillis()/ 1000;


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Software> cq = cb.createQuery(Software.class);
        Root<Software> softwareRoot = cq.from(Software.class);

        Predicate effectiveFrom = cb.lessThanOrEqualTo(softwareRoot.get("effectiveFrom"), currentTimestamp);
        Predicate effectiveTo = cb.greaterThanOrEqualTo(softwareRoot.get("effectiveTo"), currentTimestamp);

        cq.where(cb.and(effectiveFrom, effectiveTo));
        TypedQuery<Software> query = em.createQuery(cq);
        return query.getResultList();
    }

    public Software getSoftwareByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Software> cq = cb.createQuery(Software.class);
        Root<Software> softwareRoot = cq.from(Software.class);

        cq.where(cb.equal(softwareRoot.get(field), value));
        TypedQuery<Software> query = em.createQuery(cq);


//        List<Software> softwareList = query.getResultList();
//        return softwareList;

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }


    }

    public Software getSingleSoftwareByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Software> cq = cb.createQuery(Software.class);
        Root<Software> softwareRoot = cq.from(Software.class);

        cq.where(cb.equal(softwareRoot.get(field), value));
        TypedQuery<Software> query = em.createQuery(cq);


        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
}
