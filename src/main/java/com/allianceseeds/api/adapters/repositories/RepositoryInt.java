package com.allianceseeds.api.adapters.repositories;

import java.util.List;

public interface RepositoryInt<T> {
    public void insert(List<T> entityList);

    public void delete(List<T> entityList);


//    public Boolean insertList(List<T>entityList);
}
