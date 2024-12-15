package com.accutrak.toolbox.services.site;

import com.accutrak.toolbox.adapters.repositories.site.SiteRepo;
import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.commands.nfcMemory.AddNfcMemoryCommand;
import com.accutrak.toolbox.domain.commands.site.AddSiteCommand;
import com.accutrak.toolbox.domain.commands.site.DeleteSiteByNameCommand;
import com.accutrak.toolbox.domain.entities.NfcMemory;
import com.accutrak.toolbox.domain.entities.Site;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.UnitOfWork;
import com.accutrak.toolbox.services.UnitOfWorkInt;
import com.accutrak.toolbox.services.firmware.FirmwareTransformer;
import com.accutrak.toolbox.services.nfcMemory.NfcMemoryTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteHandler {

    SiteRepo siteRepo;

    UnitOfWork<Site> siteUOW;

    @Autowired
    public SiteHandler(SiteRepo siteRepo) {
        this.siteRepo = siteRepo;
        this.siteUOW = new UnitOfWork<Site>(this.siteRepo);
    }
    public Transformer addSite(Command command){
        Site site = ((AddSiteCommand) command).getSite();


        // check if site already exists
        Site existingSite = siteRepo.getSiteByStringField("name", site.getName());

        if (existingSite == null){
            siteUOW.registerRepoOperation(site, UnitOfWorkInt.UnitActions.INSERT);
            siteUOW.commit();
        }

        return new SiteTransformer(true, null);
    }
    public Transformer getAllSites(Command command){
        if(command == null){return null;}

        List<Site> sites = siteRepo.getAll();
        SiteTransformer transformer = new SiteTransformer(true, sites);
        return transformer;
    }

    public Transformer deleteSiteByName(Command command){
        String name = ((DeleteSiteByNameCommand) command).getName();
        SiteTransformer transformer;

        try{
            Site site = siteRepo.getSiteByStringField("name", name);
            if(site != null){
                siteUOW.registerRepoOperation(site, UnitOfWorkInt.UnitActions.DELETE);
                siteUOW.commit();
            }
            transformer = new SiteTransformer<>(true, null);
        } catch (EmptyResultDataAccessException e){
            transformer = new SiteTransformer<>(false, null);
            transformer.setMessage("No Site with this name in the database");
        }

        return transformer;
    }
    }


