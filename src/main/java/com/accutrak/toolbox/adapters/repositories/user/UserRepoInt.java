package com.accutrak.toolbox.adapters.repositories.user;

import com.accutrak.toolbox.adapters.repositories.RepositoryInt;
import com.accutrak.toolbox.domain.entities.Otp;
import com.accutrak.toolbox.domain.entities.User;

import java.util.List;

public interface UserRepoInt extends RepositoryInt<User> {
    List<User> getUsersByStringField(String field, String value);
    String getUuidByEmail(String email);

    List<String> getUuidListByEmail(String email);

    List<String> getDeviceIdListByEmail(String email);

    User getUserpByEmail(String value);



}
