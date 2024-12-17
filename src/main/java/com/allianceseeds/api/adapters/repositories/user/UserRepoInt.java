package com.allianceseeds.api.adapters.repositories.user;

import com.allianceseeds.api.adapters.repositories.RepositoryInt;
import com.allianceseeds.api.domain.entities.User;

import java.util.List;


public interface UserRepoInt extends RepositoryInt<User> {
    List<User> getUserByStringField(String field, String value);
}