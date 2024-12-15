package com.accutrak.toolbox.Log;

import com.accutrak.toolbox.domain.entities.Log;
import com.accutrak.toolbox.presentation.resources.LogResource;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.log.LogTransformer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class Log_Test {

    private LogResource logResource;

    private String validSerial = "123456";
    private String validDeviceId = "someOtherDeviceId";
    private String validEmail = "jack@accutrak.co.za";

    private String validUuid = "8a8e1213-e3cf-4be9-8833-2faed8597889";

    private  int validTimestamp = 1704067200; // 01/01/2024

    private String validFileName = "Test_01_01_2024";

    private String logData = "Test some log data";


    private Log log = Log.builder().
            deviceId(validDeviceId).
            fileName(validFileName).
            data(logData).
            lastModified(validTimestamp).
            build();

    @Autowired
    public Log_Test(LogResource logResource) {
        this.logResource = logResource;
    }

    @Test
    void addDeleteLog_Test(){
        Transformer addResult = logResource.addLog(log);

        Transformer deleteResult = logResource.deleteLogsByDeviceIdFromTo(validDeviceId, validTimestamp, validTimestamp);

        assertEquals(((LogTransformer<?>)addResult).getMeta().getSuccess(), true);
        assertEquals(((LogTransformer<?>)deleteResult).getMeta().getSuccess(), true);

    }

    @Test
    void getLogsByDeviceIdToFrom_Test(){
        logResource.addLog(log);
        LogTransformer<?> result = (LogTransformer<?>) logResource.getLogsByDeviceIdToFrom(validDeviceId, validTimestamp, validTimestamp);
        List<Log> logList = (List<Log>) result.getData();
        logResource.deleteLogsByDeviceIdFromTo(validDeviceId, validTimestamp, validTimestamp);
        assertNotNull(logList.size());

    }
    @Test
    void getLogsByEmailToFrom_Test(){
        logResource.addLog(log);
        LogTransformer<?> result = (LogTransformer<?>) (logResource.getLogsByEmailToFrom(validEmail, validTimestamp, validTimestamp));
        Map<String, List<Log>> logLists = (Map<String, List<Log>>) result.getData();


        logResource.deleteLogsByDeviceIdFromTo(validDeviceId, validTimestamp, validTimestamp);
        assertNotNull(logLists);
    }

    @Test
    void getAllLogs_Test(){
        logResource.addLog(log);
        LogTransformer<?> result = (LogTransformer<?>) logResource.getAll();
        Map<String, List<Log>> logList = (Map<String, List<Log>>) result.getData();

        logResource.deleteLogsByDeviceIdFromTo(validDeviceId, validTimestamp, validTimestamp);
        assertNotNull(logList);

    }
//    Map<String, List<Log>>

    @Test
    void getLastModifiedTsByDeviceId_Test(){
        logResource.addLog(log);
        LogTransformer<?> result = (LogTransformer<?>)logResource.getLastModifiedTsByDeviceId(validDeviceId);

        int ts = (int) result.getData();
        logResource.deleteLogsByDeviceIdFromTo(validDeviceId, validTimestamp, validTimestamp);
        assertNotNull(ts);

    }

}
