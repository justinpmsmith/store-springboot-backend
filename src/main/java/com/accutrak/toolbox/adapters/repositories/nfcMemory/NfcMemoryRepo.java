package com.accutrak.toolbox.adapters.repositories.nfcMemory;

import com.accutrak.toolbox.domain.entities.NfcMemory;
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
public class NfcMemoryRepo implements NfcMemoryRepoInt{

    @PersistenceContext
    private EntityManager em;

    JpaNfcMemoryRepo repo;

    @Autowired
    public NfcMemoryRepo(JpaNfcMemoryRepo repo) {
        this.repo = repo;
    }

    @Override
    public void insert(List<NfcMemory> nfcMem) {

        repo.saveAll(nfcMem);

    }

    @Override
    public void delete(List<NfcMemory> nfcMemoryList){
        repo.deleteAll(nfcMemoryList);
    }

    @Override
    public NfcMemory getLastReadBySerial(String serial){
        // filtering NfcMemory by serial
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<NfcMemory> cq = cb.createQuery(NfcMemory.class);
        Root<NfcMemory> nfcMemoryRoot = cq.from(NfcMemory.class);
        cq.where(cb.equal(nfcMemoryRoot.get("serial"), serial));
        TypedQuery<NfcMemory> query = em.createQuery(cq);


        List<NfcMemory> nfcMemoryList = query.getResultList();

        // get most recent
        Optional<NfcMemory> mostRecent = nfcMemoryList.stream()
                .max(Comparator.comparing(NfcMemory::getScanTimestamp));

        if (mostRecent.isPresent()) {
            return mostRecent.get();

        }
        return null;


    }

    @Override
    public NfcMemory getLastReadByStringField(String field, String value){
        // filtering NfcMemory by serial
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<NfcMemory> cq = cb.createQuery(NfcMemory.class);
        Root<NfcMemory> nfcMemoryRoot = cq.from(NfcMemory.class);
        cq.where(cb.equal(nfcMemoryRoot.get(field), value));
        TypedQuery<NfcMemory> query = em.createQuery(cq);


        List<NfcMemory> nfcMemoryList = query.getResultList();

        // get most recent
        Optional<NfcMemory> mostRecent = nfcMemoryList.stream()
                .max(Comparator.comparing(NfcMemory::getScanTimestamp));

        if (mostRecent.isPresent()) {
            return mostRecent.get();
        }
        return null;


    }

    @Override
    public List<NfcMemory> getNfcMemorysByUsuerId(String uuid) {
        // filtering NfcMemory by uuid
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<NfcMemory> cq = cb.createQuery(NfcMemory.class);
        Root<NfcMemory> nfcMemoryRoot = cq.from(NfcMemory.class);

        cq.where(cb.equal(nfcMemoryRoot.get("uuid"), uuid));
        TypedQuery<NfcMemory> query = em.createQuery(cq);


        List<NfcMemory> nfcMemoryList = query.getResultList();
        return nfcMemoryList;
    }

    @Override
    public List<NfcMemory> getNfcMemorysByStringField(String field, String value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<NfcMemory> cq = cb.createQuery(NfcMemory.class);
        Root<NfcMemory> nfcMemoryRoot = cq.from(NfcMemory.class);

        cq.where(cb.equal(nfcMemoryRoot.get(field), value));
        TypedQuery<NfcMemory> query = em.createQuery(cq);


        List<NfcMemory> nfcMemoryList = query.getResultList();
        return nfcMemoryList;


    }

    public List<NfcMemory> getAll(){
        List<NfcMemory> all = repo.findAll();
        return  all;
    }

}
