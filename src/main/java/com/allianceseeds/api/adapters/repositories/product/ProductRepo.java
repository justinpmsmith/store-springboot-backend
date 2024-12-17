package com.allianceseeds.api.adapters.repositories.product;

import com.allianceseeds.api.domain.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepo implements ProductRepoInt{

    @PersistenceContext
    private EntityManager em;

    JpaProductRepo repo;

    public ProductRepo(JpaProductRepo repo) {
        this.repo = repo;
    }

    @Override
    public void insert(List<Product> entityList) {
        repo.saveAll(entityList);
    }

    @Override
    public void delete(List<Product> entityList) {
        repo.deleteAll(entityList);

    }
    public List<Product> getProductsByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);

        cq.where(cb.equal(root.get(field), value));
        TypedQuery<Product> query = em.createQuery(cq);


        List<Product> list = query.getResultList();
        return list;
    }

    public List<String> getCategories() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Product> root = cq.from(Product.class);

        cq.select(root.get("category")).distinct(true);
        TypedQuery<String> query = em.createQuery(cq);

        return query.getResultList();
    }

    public List<Product> getAll(){
        List<Product> all = repo.findAll();
        return all;
    }
}
