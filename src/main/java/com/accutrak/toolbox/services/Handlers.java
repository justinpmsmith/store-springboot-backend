package com.accutrak.toolbox.services;

import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.commands.device.AddDeviceCommand;
import com.accutrak.toolbox.domain.commands.firmware.*;
import com.accutrak.toolbox.domain.commands.log.*;
import com.accutrak.toolbox.domain.commands.nfcMemory.*;
import com.accutrak.toolbox.domain.commands.notifications.SendEmailCommand;
import com.accutrak.toolbox.domain.commands.reportScript.AddReportScriptCommand;
import com.accutrak.toolbox.domain.commands.reportScript.DeleteScriptByScriptIdCommand;
import com.accutrak.toolbox.domain.commands.reportScript.GetScriptByScriptIdCommand;
import com.accutrak.toolbox.domain.commands.reportScript.GetValidScriptsBySiteCommand;
import com.accutrak.toolbox.domain.commands.reportSubmission.AddReportSubmissionCommand;
import com.accutrak.toolbox.domain.commands.reportSubmission.BulkAddReportSubmissionCommand;
import com.accutrak.toolbox.domain.commands.reportSubmission.GetLastSubmissionTimestampByDeviceIdCommand;
import com.accutrak.toolbox.domain.commands.reportSubmission.GetReportSubmissionsByDeviceIdCommand;
import com.accutrak.toolbox.domain.commands.site.AddSiteCommand;
import com.accutrak.toolbox.domain.commands.site.DeleteSiteByNameCommand;
import com.accutrak.toolbox.domain.commands.site.GetAllSitesCommand;
import com.accutrak.toolbox.domain.commands.software.AddSoftwareCommand;
import com.accutrak.toolbox.domain.commands.software.DeleteSoftwareByNameCommand;
import com.accutrak.toolbox.domain.commands.software.GetLatestSoftwareCommand;
import com.accutrak.toolbox.domain.commands.software.GetSoftwareByInfoCommand;
import com.accutrak.toolbox.domain.commands.tagConfig.*;
import com.accutrak.toolbox.domain.commands.user.*;
import com.accutrak.toolbox.services.device.DeviceHandler;
import com.accutrak.toolbox.services.firmware.FirmwareHandler;
import com.accutrak.toolbox.services.log.LogHandler;
import com.accutrak.toolbox.services.nfcMemory.NfcMemoryHandler;
import com.accutrak.toolbox.services.notifications.NotificationHandler;
import com.accutrak.toolbox.services.reportScript.ReportScriptHandler;
import com.accutrak.toolbox.services.reportSubmission.ReportSubmissionHandler;
import com.accutrak.toolbox.services.site.SiteHandler;
import com.accutrak.toolbox.services.software.SoftwareHandler;
import com.accutrak.toolbox.services.tagConfig.TagConfigHandler;
import com.accutrak.toolbox.services.user.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class Handlers {

    private final Map<Class<? extends Command>, Function<Command, Transformer>> commandHandlers;

    private  final TagConfigHandler tagConfigHandler;

    private final NfcMemoryHandler nfcMemoryHandler;
    private final UserHandler userHandler;

    private  final LogHandler logHandler;

    private final SoftwareHandler softwareHandler;

    private final FirmwareHandler firmwareHandler;

    private final ReportScriptHandler scriptHandler;

    private final ReportSubmissionHandler submsissionHandler;

    private final NotificationHandler notificationHandler;

    private final SiteHandler siteHandler;

    private final DeviceHandler deviceHandler;


    @Autowired
    public Handlers(TagConfigHandler tagConfigHandler, NfcMemoryHandler nfcMemoryHandler, UserHandler userHandler, LogHandler logHandler, SoftwareHandler softwareHandler, FirmwareHandler firmwareHandler, ReportScriptHandler scriptHandler, ReportSubmissionHandler submsissionHandler, NotificationHandler notificationHandler, SiteHandler siteHandler, DeviceHandler deviceHandler) {
        this.tagConfigHandler = tagConfigHandler;
        this.nfcMemoryHandler = nfcMemoryHandler;
        this.userHandler = userHandler;
        this.logHandler = logHandler;
        this.softwareHandler = softwareHandler;
        this.firmwareHandler = firmwareHandler;
        this.scriptHandler = scriptHandler;
        this.submsissionHandler = submsissionHandler;
        this.notificationHandler = notificationHandler;
        this.siteHandler = siteHandler;
        this.deviceHandler = deviceHandler;

        commandHandlers = new HashMap<>();

        // tagConfig commands
        commandHandlers.put(AddTagConfigCommand.class, tagConfigHandler::addTagConfig);
        commandHandlers.put(BulkAddTagCommand.class, tagConfigHandler::bulkAddConfig);
        commandHandlers.put(GetLastSetBySerialCommand.class, tagConfigHandler::getLastSetBySerial);
        commandHandlers.put(GetTagConfigsByUuidCommand.class, tagConfigHandler::getTagConfigsByUuId);
        commandHandlers.put(GetTagConfigsByEmailCommand.class, tagConfigHandler::getTagConfigsByEmail);
        commandHandlers.put(GetAllTagConfigsCommand.class, tagConfigHandler::getAllTagConfig);
        commandHandlers.put(GetTagConfigBySerialCommand.class, tagConfigHandler::getTagConfigsBySerial);
        commandHandlers.put(GetLatestTagConfigByUuidCommand.class, tagConfigHandler::getTagConfigLastSetTimestampByUuid);
        commandHandlers.put(GetTagConfigLastSetTimestampByDeviceIdCommand.class, tagConfigHandler::getTagConfigLastSetTimestampByDeviceId);
        commandHandlers.put(GetTagConfigByDeviceIdCommand.class, tagConfigHandler::getTagConfigBysDeviceId);
        commandHandlers.put(DeleteTagConfigCommand.class, tagConfigHandler::deleteTagConfig);



        // nfcMemory commands
        commandHandlers.put(AddNfcMemoryCommand.class, nfcMemoryHandler::addNfcMemory);
        commandHandlers.put(BulkAddNfcMemoryCommand.class, nfcMemoryHandler::bulkAddNfcMemory);
        commandHandlers.put(GetLastReadBySerialCommand.class, nfcMemoryHandler::getLastReadBySerial);
        commandHandlers.put(GetNfcMemoryByUuidCommand.class, nfcMemoryHandler::getNfcMemoryByUuid);
        commandHandlers.put(GetNfcMemoryByEmailCommand.class, nfcMemoryHandler::getNfcMemoryByEmail);
        commandHandlers.put(GetNfcMemoryByDeviceIdCommand.class, nfcMemoryHandler::getNfcMemoryByDeviceId);
        commandHandlers.put(GetNfcMemoryLastReadByDeviceIdCommand.class, nfcMemoryHandler::getNfcMemoryLastReadByDeviceId);
        commandHandlers.put(GetNfcMemoryLastReadTimeStampByDeviceIdCommand.class, nfcMemoryHandler::getNfcMemoryLastReadTimestampByDeviceId);
        commandHandlers.put(GetNfcMemoryBySerialCommand.class, nfcMemoryHandler::getNfcMemoryBySerial);
        commandHandlers.put(GetAllNfcMemoryCommand.class, nfcMemoryHandler::getAllNfcMemory);

        // user commands
        commandHandlers.put(AddUserCommand.class, userHandler::addUser);
        commandHandlers.put(GetUuidByEmailCommand.class, userHandler::getUuidByEmail);
        commandHandlers.put(RequestOtpCommand.class, userHandler::sendOtp);
        commandHandlers.put(UpdateUserCommand.class, userHandler::updateUser);
        commandHandlers.put(MigrateUserCommand.class, userHandler::migrateUser);
        commandHandlers.put(GetTagConfigLevelByUuidCommand.class, userHandler::getTagConfigLevellByUuid);



        // log commands
        commandHandlers.put(AddLogCommand.class, this.logHandler::addLog);
        commandHandlers.put(BulkAddLogsCommand.class, this.logHandler::bulkAddLogs);
        commandHandlers.put(GetLogsByEmailCommand.class, this.logHandler::getLogsByEmail);
        commandHandlers.put(GetLogsByDeviceIdCommand.class, this.logHandler::getLogsByDeviceId);
        commandHandlers.put(GetLogsByEmailFromToCommand.class, this.logHandler::getLogsByEmailToFrom);
        commandHandlers.put(GetLogsByDeviceIdFromToCommand.class, this.logHandler::getLogsByDeviceIdToFrom);
        commandHandlers.put(GetLastModifiedTimestampByDeviceIdCommand.class, this.logHandler::getLastModifiedTsByDeviceId);
        commandHandlers.put(GetAllLogsCommand.class, this.logHandler::getAllLogs);
        commandHandlers.put(DeleteLogsByDeviceIdFromToCommand.class, this.logHandler::deleteLogsByDeviceIdToFrom);



        // software commands
        commandHandlers.put(AddSoftwareCommand.class, this.softwareHandler::addSoftware);
        commandHandlers.put(GetLatestSoftwareCommand.class, this.softwareHandler::getLatestSoftware);
        commandHandlers.put(GetSoftwareByInfoCommand.class, this.softwareHandler::getSoftwareByName);
        commandHandlers.put(DeleteSoftwareByNameCommand.class, this.softwareHandler::deleteSoftwareByFileName);



        // firmware commands
        commandHandlers.put(AddFirmwareCommand.class, this.firmwareHandler::addFirmware);
        commandHandlers.put(GetValidFirmwareCommand.class, this.firmwareHandler::getValidFirmware);
        commandHandlers.put(GetAllFirmwareCommand.class, this.firmwareHandler::getAllFirmware);
        commandHandlers.put(GetFirmwareByFileNameCommand.class, this.firmwareHandler::getFirmwareByFileName);
        commandHandlers.put(DeleteFirmwareByFileNameCommand.class, this.firmwareHandler::deleteFirmwareByFileName);
        commandHandlers.put(DeleteFirmwareByFilenameAndSiteCommand.class, this.firmwareHandler::deleteFirmwareByFilenameAndSite);


        // Report Scripts
        commandHandlers.put(AddReportScriptCommand.class, this.scriptHandler::addReportScript);
        commandHandlers.put(DeleteScriptByScriptIdCommand.class, this.scriptHandler::deleteScriptByScriptId);
        commandHandlers.put(GetScriptByScriptIdCommand.class, this.scriptHandler::getScriptByScriptId);
        commandHandlers.put(GetValidScriptsBySiteCommand.class, this.scriptHandler::getValidScriptsInfo);


        // report submissions
        commandHandlers.put(AddReportSubmissionCommand.class, this.submsissionHandler::addSubmission);
        commandHandlers.put(BulkAddReportSubmissionCommand.class, this.submsissionHandler::bulkAddSubmission);
        commandHandlers.put(GetReportSubmissionsByDeviceIdCommand.class, this.submsissionHandler::getSubmissionsByDeviceId);
        commandHandlers.put(GetLastSubmissionTimestampByDeviceIdCommand.class, this.submsissionHandler::getLastSubmissionTimestampByDeviceId);

        // notifications
        commandHandlers.put(SendEmailCommand.class, this.notificationHandler::sendEmail);

        // site
        commandHandlers.put(AddSiteCommand.class, this.siteHandler::addSite);
        commandHandlers.put(GetAllSitesCommand.class, this.siteHandler::getAllSites);
        commandHandlers.put(DeleteSiteByNameCommand.class, this.siteHandler::deleteSiteByName);

        // Device
        commandHandlers.put(AddDeviceCommand.class, this.deviceHandler::addDeviceInfo);







    }

    public Map<Class<? extends Command>, Function<Command, Transformer>> getCmdHandlers(){
        return commandHandlers;
    }


}
