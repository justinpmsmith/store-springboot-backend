package com.accutrak.toolbox.adapters.repositories.reportSubmissions;

import com.accutrak.toolbox.domain.entities.ReportSubmission;
import com.accutrak.toolbox.domain.entities.TagConfig;
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

@Repository
public class ReportSubmissionRepo implements ReportSubmissionRepoInt{

    @PersistenceContext
    private EntityManager em;

    private JpaReportSubmissionRepo repo;

    @Autowired
    public ReportSubmissionRepo(JpaReportSubmissionRepo repo) {
        this.repo = repo;

    }

    @Override
    public void insert(List<ReportSubmission> entityList) {
        repo.saveAll(entityList);
    }

    @Override
    public void delete(List<ReportSubmission> entityList) {
        repo.deleteAll(entityList);
    }

    public List<ReportSubmission> getReportSubmissionsByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ReportSubmission> cq = cb.createQuery(ReportSubmission.class);
        Root<ReportSubmission> submissionRoot = cq.from(ReportSubmission.class);

        cq.where(cb.equal(submissionRoot.get(field), value));
        TypedQuery<ReportSubmission> query = em.createQuery(cq);


        List<ReportSubmission> submissionList = query.getResultList();
        return submissionList;


    }


}
