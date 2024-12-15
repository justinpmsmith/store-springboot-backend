package com.accutrak.toolbox.adapters.repositories.device;

import com.accutrak.toolbox.domain.entities.Device;
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

@Repository
public class DeviceRepo implements DeviceRepoInt{

    @PersistenceContext
    private EntityManager em;

    JpaDeviceRepo repo;

    @Autowired
    public DeviceRepo(JpaDeviceRepo repo) {
        this.repo = repo;
    }

    @Override
    public void insert(List<Device> deviceList) {

        repo.saveAll(deviceList);
    }

    @Override
    public void delete(List<Device> deviceList) {
        repo.deleteAll(deviceList);
    }

    public Device getDeviceByID(String id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Device> cq = cb.createQuery(Device.class);
        Root<Device> deviceRoot = cq.from(Device.class);

        cq.where(cb.equal(deviceRoot.get("id"), id));
        TypedQuery<Device> query = em.createQuery(cq);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }


    }
}
