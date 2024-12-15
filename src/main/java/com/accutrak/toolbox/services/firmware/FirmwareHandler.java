package com.accutrak.toolbox.services.firmware;

import com.accutrak.toolbox.adapters.repositories.firmware.FirmwareRepo;
import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.commands.firmware.*;
import com.accutrak.toolbox.domain.commands.software.DeleteSoftwareByNameCommand;
import com.accutrak.toolbox.domain.entities.Firmware;
import com.accutrak.toolbox.domain.entities.Software;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.UnitOfWork;
import com.accutrak.toolbox.services.UnitOfWorkInt;
import com.accutrak.toolbox.services.software.SoftwareTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FirmwareHandler {

    private UnitOfWork fwUOW;

    private FirmwareRepo fwRepo;

    @Autowired
    public FirmwareHandler(FirmwareRepo fwRepo) {
        this.fwRepo = fwRepo;
        this.fwUOW = new UnitOfWork<Firmware>(fwRepo);
    }

    public Transformer addFirmware(Command command) {
        Firmware firmware = ((AddFirmwareCommand) command).getFirmware();
        FirmwareTransformer transformer;


            // check if hash/site combination already exists
            List<Firmware> existingFw = fwRepo.getFirmwareByHashAndSite(firmware.getHash(), firmware.getSite());

            if(existingFw.size() > 0){
                transformer = new FirmwareTransformer(false, null);
                transformer.setMessage("Firmware with this hash and site has already been uploaded");
                return transformer;
            }

            fwUOW.registerRepoOperation(firmware, UnitOfWorkInt.UnitActions.INSERT);
            fwUOW.commit();
            transformer = new FirmwareTransformer(true, null);

        return transformer;
    }
    public Transformer deleteFirmwareByFileName(Command command) {
        String fileName = ((DeleteFirmwareByFileNameCommand) command).getFileName();

        FirmwareTransformer transformer;

        try {
            List<Firmware> firmwareList = fwRepo.getFirmwareByStringField("fileName", fileName);
            if (firmwareList.size() != 0) {
                fwUOW.registerBulkOperation(firmwareList, UnitOfWorkInt.UnitActions.DELETE);
                fwUOW.commit();

            } else {System.out.println("fw does not exist");}
            transformer = new FirmwareTransformer<>(true, null);
        } catch (EmptyResultDataAccessException e) {

            transformer = new FirmwareTransformer<>(false, null);
            transformer.setMessage("No firmware with this name in the database");

        }

        return transformer;
    }

    public Transformer deleteFirmwareByFilenameAndSite(Command command){
        FirmwareTransformer transformer;

        try{
            String filename = ((DeleteFirmwareByFilenameAndSiteCommand) command).getFilename();
            String site = ((DeleteFirmwareByFilenameAndSiteCommand) command).getSite();

            List<Firmware> fwList = fwRepo.getFirmwareByNameAndSite(filename, site);

            if(fwList.size() > 0){

                fwUOW.registerBulkOperation(fwList, UnitOfWorkInt.UnitActions.DELETE);
                fwUOW.commit();
            } else {System.out.println("no fw with given name and site");}

            transformer = new FirmwareTransformer<>(true, null);
        } catch (EmptyResultDataAccessException e) {

            transformer = new FirmwareTransformer<>(false, null);
            transformer.setMessage("No firmware with this name in the database");

        }

        return transformer;




    }


    public Transformer getValidFirmware(Command command){
        String site = ((GetValidFirmwareCommand) command).getSite();
        List<Firmware> firmwareList = fwRepo.getValidFirmware();
//        List<Firmware> filteredFirmwareList = ((GetValidFirmwareCommand) command).filterBySite(firmwareList);

        FirmwareTransformer transformer = new FirmwareTransformer<>(true, firmwareList);
        transformer.filterBySite(site);

        return transformer;
//        return new FirmwareTransformer<>(true, filteredFirmwareList);


    }

    public Transformer getFirmwareByFileName(Command command){
        String fileName = ((GetFirmwareByFileNameCommand) command).getFileName();
        Firmware firmware = fwRepo.getSingleFirmwareByStringField("fileName", fileName);
        FirmwareTransformer transformer;

        if(firmware == null){
            transformer = new FirmwareTransformer<>(false, null);
            transformer.setMessage("No firmware in database for given filename");
            return transformer;
        }

        transformer = new FirmwareTransformer<>(true, firmware);
        return transformer;

    }


    public Transformer getAllFirmware(Command command){
        if(command == null){return null;}
        List<Firmware> firmwareList = fwRepo.getAllFirmware();
        return new FirmwareTransformer<>(true, firmwareList);


    }

}
