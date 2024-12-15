package com.accutrak.toolbox.adapters.repositories.tagConfig;

import com.accutrak.toolbox.domain.entities.Software;
import com.accutrak.toolbox.domain.entities.TagConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class TagConfigRepo implements TagConfigRepoInt{
    @PersistenceContext
    private EntityManager em;

    JpaTagConfigRepo repo;

    @Autowired
    public TagConfigRepo(JpaTagConfigRepo repo) {
        this.repo = repo;
    }


    @Override
    public void insert(List<TagConfig> tagConfigs) {
        repo.saveAll(tagConfigs);

    }

    @Override
    public void delete(List<TagConfig> tagConfig){
        repo.deleteAll(tagConfig);
    }

    @Override
    public TagConfig getLastTagSetBySerial(String serial){
        // filtering tagConfig by serial
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TagConfig> cq = cb.createQuery(TagConfig.class);
        Root<TagConfig> tagConfigRoot = cq.from(TagConfig.class);
        cq.where(cb.equal(tagConfigRoot.get("serial"), serial));
        TypedQuery<TagConfig> query = em.createQuery(cq);


        List<TagConfig> tagConfigList = query.getResultList();

        // get most recent
        Optional<TagConfig> mostRecent = tagConfigList.stream()
                .max(Comparator.comparing(TagConfig::getLastChanged));

        if (mostRecent.isPresent()) {
            return mostRecent.get();

        }
        return null;


    }

    @Override
    public List<TagConfig> getTagConfigsByUsuerId(String uuid) {
        // filtering tagConfig by uuid
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TagConfig> cq = cb.createQuery(TagConfig.class);
        Root<TagConfig> tagConfigRoot = cq.from(TagConfig.class);

        cq.where(cb.equal(tagConfigRoot.get("uuid"), uuid));
        TypedQuery<TagConfig> query = em.createQuery(cq);


        List<TagConfig> tagConfigList = query.getResultList();
        return tagConfigList;
    }

    @Override
    public List<TagConfig> getTagConfigsByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TagConfig> cq = cb.createQuery(TagConfig.class);
        Root<TagConfig> tagConfigRoot = cq.from(TagConfig.class);

        cq.where(cb.equal(tagConfigRoot.get(field), value));
        TypedQuery<TagConfig> query = em.createQuery(cq);


        List<TagConfig> tagConfigList = query.getResultList();
        return tagConfigList;


    }

    @Override
    public List<TagConfig> getAll(){
        List<TagConfig> all = repo.findAll();
        return  all;
    }
}
