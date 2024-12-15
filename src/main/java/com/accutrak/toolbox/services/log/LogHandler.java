package com.accutrak.toolbox.services.log;

import com.accutrak.toolbox.adapters.repositories.log.LogRepo;
import com.accutrak.toolbox.adapters.repositories.log.LogRepoInt;
import com.accutrak.toolbox.adapters.repositories.user.UserRepoInt;
import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.commands.log.*;
import com.accutrak.toolbox.domain.entities.Log;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.UnitOfWork;
import com.accutrak.toolbox.services.UnitOfWorkInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LogHandler {

    private final LogRepo logRepo;

    private final UserRepoInt userRepo;
    private final UnitOfWork<Log> logUOW;

    @Autowired
    public LogHandler(LogRepo logRepo, UserRepoInt userRepo) {
        this.logRepo = logRepo;
        this.userRepo = userRepo;

        this.logUOW = new UnitOfWork<Log>(this.logRepo);
    }

    public Transformer addLog(Command command){
        Log log = ((AddLogCommand) command).getLog();

        // check for duplicate filename/devId pair like in bulkAddLogs

        logUOW.registerRepoOperation(log, UnitOfWorkInt.UnitActions.INSERT);
        logUOW.commit();

        return new LogTransformer<>(true, null);
    }

    public Transformer bulkAddLogs(Command command){
        List<Log> logList = ((BulkAddLogsCommand) command).getLogList();

        for(Log log : logList){
            List<Log> dbLogs = logRepo.getLogByNameAndDeviceId(log.getFileName(), log.getDeviceId());

            // if log exists => update
            if (dbLogs.size() > 0){
                log.setId(dbLogs.get(0).getId());

                // delete previous entries

            }

            logUOW.registerRepoOperation(log, UnitOfWorkInt.UnitActions.INSERT);
        }
        logUOW.commit();

        return new LogTransformer<>(true, null);
    }

    public Transformer getLogsByEmail(Command command){
        String email = ((GetLogsByEmailCommand) command).getEmail();
        List<String> deviceIdList = userRepo.getDeviceIdListByEmail(email);

        Map<String, List<Log>> logMap = new HashMap<>();
        for (String deviceId : deviceIdList) {
            List<Log> logList = logRepo.getLogByStringField("deviceId", deviceId);
            logMap.put(deviceId, logList);
        }

        return new LogTransformer<>(true, logMap);
    }

    public Transformer getLogsByDeviceId(Command command){
        String deviceId = ((GetLogsByDeviceIdCommand) command).getDeviceId();

        List<Log> logList = logRepo.getLogByStringField("deviceId", deviceId);

        return new LogTransformer<>(true, logList);
    }

    public Transformer getLogsByEmailToFrom(Command command){
        String email = ((GetLogsByEmailFromToCommand) command).getEmail();
        int from = ((GetLogsByEmailFromToCommand) command).getFrom();
        int to = ((GetLogsByEmailFromToCommand) command).getTo();

        List<String> deviceIdList = userRepo.getDeviceIdListByEmail(email);

        Map<String, List<Log>> logMap = new HashMap<>();
        for (String deviceId : deviceIdList) {
            List<Log> logList = logRepo.getLogsByDeviceIdToFrom(deviceId, from, to);
            logMap.put(deviceId, logList);
        }

//        List<Log> logList = logRepo.getLogsByDeviceIdToFrom(deviceId, from, to);

        return new LogTransformer<>(true, logMap);


    }

    public Transformer getLogsByDeviceIdToFrom(Command command){
        String deviceId = ((GetLogsByDeviceIdFromToCommand) command).getDeviceId();
        int from = ((GetLogsByDeviceIdFromToCommand) command).getFrom();
        int to = ((GetLogsByDeviceIdFromToCommand) command).getTo();

        List<Log> logList = logRepo.getLogsByDeviceIdToFrom(deviceId, from, to);

        return new LogTransformer<>(true, logList);
    }

    public Transformer deleteLogsByDeviceIdToFrom(Command command){
        String deviceId = ((DeleteLogsByDeviceIdFromToCommand) command).getDeviceId();
        int from = ((DeleteLogsByDeviceIdFromToCommand) command).getFrom();
        int to = ((DeleteLogsByDeviceIdFromToCommand) command).getTo();

        List<Log> logList = logRepo.getLogsByDeviceIdToFrom(deviceId, from, to);

        if(logList.size() > 0){
            logUOW.registerBulkOperation(logList, UnitOfWorkInt.UnitActions.DELETE);
            logUOW.commit();
        }

        return new LogTransformer<>(true, null);
    }

    public Transformer getLastModifiedTsByDeviceId(Command command){
        String deviceId = ((GetLastModifiedTimestampByDeviceIdCommand) command).getDeviceId();

        List<Log> logList = logRepo.getLogByStringField("deviceId", deviceId);
        int timestamp = ((GetLastModifiedTimestampByDeviceIdCommand) command).findLatestModifiedTs(logList);

        return new LogTransformer<>(true, timestamp);
    }

    public Transformer getAllLogs(Command command){
        List<Log> logList = logRepo.getAll();
        Map<String, List<Log>> logListGrouped = ((GetAllLogsCommand)command).groupLogsByDeviceId(logList);

        return new LogTransformer<>(true, logListGrouped);
    }




}
