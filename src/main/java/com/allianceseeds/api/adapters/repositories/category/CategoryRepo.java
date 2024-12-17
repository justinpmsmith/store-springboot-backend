package com.allianceseeds.api.adapters.repositories.category;

import com.allianceseeds.api.domain.entities.Category;
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
public class CategoryRepo implements CategoryRepoInt {

    @PersistenceContext
    private EntityManager em;

    JpaCategoryRepo repo;

    @Autowired
    public CategoryRepo(JpaCategoryRepo repo) {
        this.repo = repo;
    }

    @Override
    public void insert(List<Category> entityList) {
        repo.saveAll(entityList);
    }

    @Override
    public void delete(List<Category> entityList) {
        repo.deleteAll(entityList);
    }

    public List<Category> getAll() {
        return repo.findAll();
    }

    public List<Category> getCategoriesByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> root = cq.from(Category.class);

        cq.where(cb.equal(root.get(field), value));
        TypedQuery<Category> query = em.createQuery(cq);

        return query.getResultList();
    }
    public List<String> getCategoryNames() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Category> root = cq.from(Category.class);

        cq.select(root.get("name")).distinct(true);
        TypedQuery<String> query = em.createQuery(cq);

        return query.getResultList();
    }
}