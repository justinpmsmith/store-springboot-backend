package com.accutrak.toolbox.presentation;

import com.accutrak.toolbox.domain.entities.Log;
import com.accutrak.toolbox.presentation.resources.LogResource;
import com.accutrak.toolbox.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class LogController {

    private LogResource logResource;

    @Autowired
    public LogController(LogResource logResource) {
        this.logResource = logResource;
    }

    @PostMapping("/client/addLog")
    public Transformer addLog(@RequestBody Log log){
        return logResource.addLog(log);

    }

    @PostMapping("/client/BulkAddLogs")
    public Transformer bulkAddLog(@RequestBody List<Log> logList){
        return logResource.bulkAddLogs(logList);

    }
    @DeleteMapping("/client/deleteLogsByDeviceIdFromTo")
    public Transformer deleteLogsByDeviceIdFromTo(@RequestParam String deviceId, @RequestParam int from, @RequestParam int to){
        return logResource.deleteLogsByDeviceIdFromTo(deviceId, from, to);
    }

    @GetMapping("/admin/getLogsByEmail")
    public Transformer getLogsByEmail(@RequestParam String email){
        return logResource.getLogsByEmail(email);

    }
    @GetMapping("/admin/getLogsByDeviceId")
    public Transformer getLogsByDeviceId(@RequestParam String deviceId){
        return logResource.getLogsByDeviceId(deviceId);

    }
    @GetMapping("/client/getLogsInfoByDeviceId")
    public Transformer getLogsInfoByDeviceId(@RequestParam String deviceId){
        return logResource.getLogsInfoByDeviceId(deviceId);

    }

    @GetMapping("/client/getLogsByEmailToFrom")
    public Transformer getLogsByEmailToFrom(@RequestParam String email, @RequestParam int from , @RequestParam int to){
        return logResource.getLogsByEmailToFrom(email, from, to);
    }

    @GetMapping("/admin/getLogsByDeviceIdToFrom")
    public Transformer getLogsByDeviceIdToFrom(@RequestParam String deviceId, @RequestParam int from , @RequestParam int to){
        return logResource.getLogsByDeviceIdToFrom(deviceId, from, to);
    }

    @GetMapping("/client/getLastModifiedTsByDeviceId")
    public Transformer getLastModifiedTsByDeviceId(@RequestParam String deviceId){
        return logResource.getLastModifiedTsByDeviceId(deviceId);
    }

    @GetMapping("/admin/getAllLogs")
    public Transformer getAllLogs(){
        return logResource.getAll();
    }

}
