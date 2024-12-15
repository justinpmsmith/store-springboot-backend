package com.accutrak.toolbox.presentation.resources;

import com.accutrak.toolbox.domain.commands.log.AddLogCommand;
import com.accutrak.toolbox.domain.commands.log.BulkAddLogsCommand;
import com.accutrak.toolbox.domain.commands.log.GetAllLogsCommand;
import com.accutrak.toolbox.domain.commands.site.AddSiteCommand;
import com.accutrak.toolbox.domain.commands.site.DeleteSiteByNameCommand;
import com.accutrak.toolbox.domain.commands.site.GetAllSitesCommand;
import com.accutrak.toolbox.domain.entities.Log;
import com.accutrak.toolbox.domain.entities.Site;
import com.accutrak.toolbox.services.MessageBus;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.log.LogTransformer;
import com.accutrak.toolbox.services.site.SiteTransformer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SiteResource extends BaseResource{
    public SiteResource(MessageBus messageBus) {
        super(messageBus);
    }

    public SiteTransformer addSite(Site site){
        AddSiteCommand command = new AddSiteCommand(site);
        Transformer result = messageBus.publishCommand(command);

        return  ((SiteTransformer) result);

    }


    public SiteTransformer getAll(){
        GetAllSitesCommand command = new GetAllSitesCommand();
        Transformer result = messageBus.publishCommand(command);
        return ((SiteTransformer) result);
    }

    public SiteTransformer deleteSite(String name){
        DeleteSiteByNameCommand command = new DeleteSiteByNameCommand(name);
        Transformer result = messageBus.publishCommand(command);

        return  ((SiteTransformer) result);

    }
}


