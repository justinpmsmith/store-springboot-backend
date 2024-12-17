package com.allianceseeds.api.services;

import com.allianceseeds.api.adapters.repositories.RepositoryInt;

import java.util.List;
import java.util.Map;

public class UOWConsumer implements Runnable {

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        while (true) {
            try {
                Map<String, Object> message = UnitOfWork.getMessageQueue().take();
                RepositoryInt<Object> repo = (RepositoryInt<Object>) message.get("repo");

                Map<String, List<Object>> uow = (Map<String, List<Object>>) message.get("UOW");
                processUOW(repo, uow);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            catch (Exception e) {
                System.out.println("UOW Consumer error: " + e);
            }
        }
    }

    private void processUOW(RepositoryInt<Object> repo, Map<String, List<Object>> uow) {
        if (uow.containsKey(UnitOfWorkInt.UnitActions.DELETE)) {
            repo.delete(uow.get(UnitOfWorkInt.UnitActions.DELETE));
        }
        if (uow.containsKey(UnitOfWorkInt.UnitActions.INSERT)) {
            repo.insert(uow.get(UnitOfWorkInt.UnitActions.INSERT));
        }
        // Add other operations as needed
    }
}
