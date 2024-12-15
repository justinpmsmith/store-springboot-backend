package com.accutrak.toolbox.adapters.repositories.otp;

import com.accutrak.toolbox.domain.entities.Otp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OtpRepo implements OtpRepoInt{

    @PersistenceContext
    private EntityManager em;
    JpaOtpRepo repo;

    public OtpRepo(JpaOtpRepo repo) {
        this.repo = repo;
    }

    @Override
    public void insert(List<Otp> optList) {
        repo.saveAll(optList);

    }

    @Override
    public void delete(List<Otp> optList) {
        repo.deleteAll(optList);
    }

    @Override
    public Otp getOtpByEmail(String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Otp> cq = cb.createQuery(Otp.class);
        Root<Otp> otpRoot = cq.from(Otp.class);

        cq.where(cb.equal(otpRoot.get("email"), value));
        TypedQuery<Otp> query = em.createQuery(cq);


        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }


    }

}
