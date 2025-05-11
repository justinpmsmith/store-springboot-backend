package com.allianceseeds.api.adapters.repositories.pendingsale;

import com.allianceseeds.api.domain.entities.PendingSale;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PendingSaleRepo implements PendingSaleRepoInt {

    @PersistenceContext
    private EntityManager em;

    private final JpaPendingSaleRepo repo;

    public PendingSaleRepo(JpaPendingSaleRepo repo) {
        this.repo = repo;
    }

    @Override
    public void insert(List<PendingSale> entityList) {
        repo.saveAll(entityList);
    }

    @Override
    public void delete(List<PendingSale> entityList) {
        repo.deleteAll(entityList);
    }

    public PendingSale findByPaymentId(String paymentId) {
        Optional<PendingSale> pendingSale = repo.findByPaymentId(paymentId);
        return pendingSale.orElse(null);
    }

    public List<PendingSale> getAll() {
        return repo.findAll();
    }
}