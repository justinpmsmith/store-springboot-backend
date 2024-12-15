package com.accutrak.toolbox.domain.commands.site;

import com.accutrak.toolbox.domain.entities.Site;
import lombok.Getter;
import com.accutrak.toolbox.domain.validation.Validation;

@Getter
public class AddSiteCommand implements SiteCommand{
    Site site;

    public AddSiteCommand(Site site) {
        // validate alphanumeric, min len 2 chars  to 50
        this.site = site;
        this.site.name = site.name.toUpperCase();
    }

    private Boolean validateSiteName(String name){
        if(name.length() < 3 || name.length() > 100){
            return false;
        }
        if(!Validation.isAlphanumeric(name)){
            return false;
        }
        return true;

    }
}
