package com.accutrak.toolbox.services.software;

import com.accutrak.toolbox.adapters.repositories.software.SoftwareRepo;
import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.commands.software.AddSoftwareCommand;
import com.accutrak.toolbox.domain.commands.software.DeleteSoftwareByNameCommand;
import com.accutrak.toolbox.domain.commands.software.GetLatestSoftwareCommand;
import com.accutrak.toolbox.domain.commands.software.GetSoftwareByInfoCommand;
import com.accutrak.toolbox.domain.entities.Software;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.UnitOfWork;
import com.accutrak.toolbox.services.UnitOfWorkInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoftwareHandler {

    private UnitOfWork<Software> softwareUOW;

    private SoftwareRepo swRepo;

    @Autowired
    public SoftwareHandler(SoftwareRepo swRepo) {
        this.swRepo = swRepo;
        this.softwareUOW = new UnitOfWork<Software>(swRepo);
    }

    public Transformer addSoftware(Command command){
        Software software = ((AddSoftwareCommand) command).getSoftware();

        // check for duplicates
        Software existingSw = swRepo.getSoftwareByStringField("hash",software.getHash());

        if(existingSw == null) {
            softwareUOW.registerRepoOperation(software, UnitOfWorkInt.UnitActions.INSERT);
            softwareUOW.commit();
        }

        return new SoftwareTransformer<>(true, null);
    }

    public Transformer getSoftwareByName(Command command){
        String fileName = ((GetSoftwareByInfoCommand) command).getFileName();
        Software software = swRepo.getSingleSoftwareByStringField("fileName", fileName);

        if(software != null) {
            return new SoftwareTransformer<>(true, software);

        }

        SoftwareTransformer result = new SoftwareTransformer<>(false, null);
        result.setMessage(fileName + " software not in database");

        return result;
    }

    public Transformer getLatestSoftware(Command command){
        String site = ((GetLatestSoftwareCommand) command).getSite();

        List<Software> softwareList = swRepo.getValidSoftware();

        List<Software> filteredSoftwareList = ((GetLatestSoftwareCommand) command).filterBySite(softwareList, site);
        Software latest = ((GetLatestSoftwareCommand) command).findMostRecentSoftware(filteredSoftwareList);

        return new SoftwareTransformer<>(true, latest);


    }

    public Transformer deleteSoftwareByFileName(Command command){
        String fileName = ((DeleteSoftwareByNameCommand) command).getFileName();

        Software software = swRepo.getSingleSoftwareByStringField("fileName", fileName);


        if(software != null){
            // software already not in db
            softwareUOW.registerRepoOperation(software, UnitOfWorkInt.UnitActions.DELETE);
            softwareUOW.commit();
        }

        return new SoftwareTransformer<>(true, null);
    }



}

