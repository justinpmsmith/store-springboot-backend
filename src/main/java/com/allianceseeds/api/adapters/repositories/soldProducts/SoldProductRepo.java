package com.allianceseeds.api.adapters.repositories.soldProducts;


import com.allianceseeds.api.domain.entities.SoldProduct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SoldProductRepo implements SoldProductRepoInt {

    @PersistenceContext
    private EntityManager em;

    JpaSoldProductRepo repo;


    public SoldProductRepo(JpaSoldProductRepo repo) {
        this.repo = repo;
    }

    @Override
    public void insert(List<SoldProduct> entityList) {
        repo.saveAll(entityList);
    }

    @Override
    public void delete(List<SoldProduct> entityList) {
        repo.deleteAll(entityList);
    }

    public List<SoldProduct> getSoldProductsByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SoldProduct> cq = cb.createQuery(SoldProduct.class);
        Root<SoldProduct> root = cq.from(SoldProduct.class);

        cq.where(cb.equal(root.get(field), value));
        TypedQuery<SoldProduct> query = em.createQuery(cq);

        List<SoldProduct> list = query.getResultList();
        return list;
    }

    public List<SoldProduct> getAll() {
        List<SoldProduct> all = repo.findAll();
        return all;
    }

    public SoldProduct getSoldProductById(Long id) {
        return repo.findById(id).orElse(null);
    }
}