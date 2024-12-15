package com.accutrak.toolbox.adapters.repositories.log;

import com.accutrak.toolbox.adapters.repositories.RepositoryInt;
import com.accutrak.toolbox.domain.entities.Log;

import java.util.List;

public interface LogRepoInt extends RepositoryInt<Log> {

    List<Log> getLogByStringField(String field, String value);

    List<Log> getLogsByDeviceIdToFrom(String deviceId, int from, int to );

    public List<Log> getAll();


}
