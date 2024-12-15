package com.accutrak.toolbox.presentation.resources;

import com.accutrak.toolbox.domain.commands.log.*;
import com.accutrak.toolbox.domain.entities.Log;
import com.accutrak.toolbox.services.MessageBus;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.log.LogTransformer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class LogResource extends BaseResource{
    public LogResource(MessageBus messageBus) {
        super(messageBus);
    }

    public Transformer addLog(Log log){
        AddLogCommand command = new AddLogCommand(log);

        return messageBus.publishCommand(command);
    }

    public Transformer bulkAddLogs(List<Log> logList){
        BulkAddLogsCommand command = new BulkAddLogsCommand(logList);
        return messageBus.publishCommand(command);
    }

    public Transformer deleteLogsByDeviceIdFromTo(String deviceId, int from, int to){
        DeleteLogsByDeviceIdFromToCommand command = new DeleteLogsByDeviceIdFromToCommand(deviceId, from, to);
        return messageBus.publishCommand(command);
    }

    public Transformer getLogsByEmail(String email){
        GetLogsByEmailCommand command = new GetLogsByEmailCommand(email);
        return messageBus.publishCommand(command);

    }

    public Transformer getLogsByDeviceId(String deviceId){
        GetLogsByDeviceIdCommand command = new GetLogsByDeviceIdCommand(deviceId);
        return messageBus.publishCommand(command);
    }

    public Transformer getLogsInfoByDeviceId(String deviceId){
        GetLogsByDeviceIdCommand command = new GetLogsByDeviceIdCommand(deviceId);
        Transformer result =  messageBus.publishCommand(command);
        ((LogTransformer<?>) result).removeLogData();
        return result;
    }

    public Transformer getLogsByEmailToFrom(String email, int from, int to){
        GetLogsByEmailFromToCommand command = new GetLogsByEmailFromToCommand(email, to, from);
        return messageBus.publishCommand(command);
    }

    public Transformer getLogsByDeviceIdToFrom(String deviceId, int from, int to){
        GetLogsByDeviceIdFromToCommand command = new GetLogsByDeviceIdFromToCommand(deviceId, from, to);
        return messageBus.publishCommand(command);
    }

    public Transformer getLastModifiedTsByDeviceId(String deviceId){
        GetLastModifiedTimestampByDeviceIdCommand command = new GetLastModifiedTimestampByDeviceIdCommand(deviceId);
        return messageBus.publishCommand(command);
    }

    public Transformer getAll(){
        GetAllLogsCommand command = new GetAllLogsCommand();
        return messageBus.publishCommand(command);
    }
}
