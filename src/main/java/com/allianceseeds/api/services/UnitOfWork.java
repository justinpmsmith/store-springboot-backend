package com.allianceseeds.api.services;


import com.allianceseeds.api.adapters.repositories.RepositoryInt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class UnitOfWork<entityType> {

    private Map<String, List<entityType>> UOW;
    private RepositoryInt<entityType> repo;
    private static BlockingQueue<Map<String, Object>> messageQueue = new LinkedBlockingQueue<>();

    public UnitOfWork(RepositoryInt<entityType> repo) {
        this.repo = repo;
        this.UOW = new HashMap<>();
    }

    public void registerRepoOperation(entityType entity, String operation) {
        var entitiesToOperate = UOW.get(operation);
        if (entitiesToOperate == null) {
            entitiesToOperate = new ArrayList<>();
        }
        entitiesToOperate.add(entity);
        UOW.put(operation, entitiesToOperate);
    }

    public void registerBulkOperation(List<entityType> entityList, String operation) {
        var entitiesToOperate = UOW.get(operation);
        if (entitiesToOperate == null) {
            entitiesToOperate = new ArrayList<>();
        }
        entitiesToOperate.addAll(entityList);
        UOW.put(operation, entitiesToOperate);
    }

    public void commit() {
        if (UOW == null || UOW.size() == 0) {
            return;
        }

        if (UOW.containsKey(UnitOfWorkInt.UnitActions.DELETE)) {
            repo.delete(UOW.get(UnitOfWorkInt.UnitActions.DELETE));
        }
        if (UOW.containsKey(UnitOfWorkInt.UnitActions.INSERT)) {
            repo.insert(UOW.get(UnitOfWorkInt.UnitActions.INSERT));
        }

        // message queue implementation
//        try {
//            Map<String, Object> message = new HashMap<>();
//            message.put("UOW", new HashMap<>(UOW));
//            message.put("repo", repo);
//            messageQueue.put(message);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException("Failed to add UOW to message queue", e);
//        }
        ////////

        UOW.clear();
    }

    public static BlockingQueue<Map<String, Object>> getMessageQueue() {
        return messageQueue;
    }
}
