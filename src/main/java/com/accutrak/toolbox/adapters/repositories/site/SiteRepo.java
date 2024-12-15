package com.accutrak.toolbox.adapters.repositories.site;

import com.accutrak.toolbox.adapters.repositories.user.JpaUserRepo;
import com.accutrak.toolbox.domain.entities.Log;
import com.accutrak.toolbox.domain.entities.Site;
import com.accutrak.toolbox.domain.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SiteRepo implements SiteRepoInt{

    @PersistenceContext
    private EntityManager em;

    JpaSiteRepo repo;

    @Autowired
    public SiteRepo(JpaSiteRepo repo) {
        this.repo = repo;
    }
    @Override
    public void insert(List<Site> siteList) {
        repo.saveAll(siteList);
    }

    @Override
    public void delete(List<Site> siteList) {
        repo.deleteAll(siteList);

    }

    @Override
    public List<Site> getAll(){
        List<Site> all = repo.findAll();
        return all;
    }

    public Site getSiteByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Site> cq = cb.createQuery(Site.class);
        Root<Site> siteRoot = cq.from(Site.class);

        cq.where(cb.equal(siteRoot.get(field), value));
        TypedQuery<Site> query = em.createQuery(cq);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }


    }
}
