package com.accutrak.toolbox.adapters.repositories.reportScript;

import com.accutrak.toolbox.domain.entities.Firmware;
import com.accutrak.toolbox.domain.entities.ReportScript;
import jakarta.persistence.EntityManager;
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
public class ReportScriptRepo implements ReportScriptRepoInt {

    @PersistenceContext
    private EntityManager em;

    private JpaReportScriptRepo repo;

    @Autowired
    public ReportScriptRepo(JpaReportScriptRepo repo) {
        this.repo = repo;
    }

    @Override
    public void insert(List<ReportScript> scriptList) {
        repo.saveAll(scriptList);

    }

    @Override
    public void delete(List<ReportScript> scriptList) {
        repo.deleteAll(scriptList);
    }

    public List<ReportScript> getValidScripts(){
        long currentTimestamp = System.currentTimeMillis()/ 1000;


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ReportScript> cq = cb.createQuery(ReportScript.class);
        Root<ReportScript> reportScriptRoot = cq.from(ReportScript.class);

        Predicate effectiveFrom = cb.lessThanOrEqualTo(reportScriptRoot.get("effectiveFrom"), currentTimestamp);
        Predicate effectiveTo = cb.greaterThanOrEqualTo(reportScriptRoot.get("effectiveTo"), currentTimestamp);

        cq.where(cb.and(effectiveFrom, effectiveTo));
        TypedQuery<ReportScript> query = em.createQuery(cq);
        return query.getResultList();
    }

    public ReportScript getSingleScriptByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ReportScript> cq = cb.createQuery(ReportScript.class);
        Root<ReportScript> reportScriptRoot = cq.from(ReportScript.class);

        cq.where(cb.equal(reportScriptRoot.get(field), value));
        TypedQuery<ReportScript> query = em.createQuery(cq);


//        List<Firmware> firmwareList = query.getResultList();
        return Optional.ofNullable(em.createQuery(cq).getSingleResult()).orElse(null);



    }
}
