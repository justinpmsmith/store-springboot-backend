package com.accutrak.toolbox.presentation;

import com.accutrak.toolbox.domain.entities.NfcMemory;
import com.accutrak.toolbox.domain.entities.Site;
import com.accutrak.toolbox.presentation.resources.SiteResource;
import com.accutrak.toolbox.services.site.SiteTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class SiteController {

    private SiteResource siteResource;

    @Autowired
    public SiteController(SiteResource siteResource) {
        this.siteResource = siteResource;
    }

    @PostMapping("/admin/addSite")
    public SiteTransformer addSite(@RequestBody Site site){
        return siteResource.addSite(site);
    }

    @GetMapping("/client/getAllSites")
    public SiteTransformer getAllSites() {
        return siteResource.getAll();

    }

    @DeleteMapping("/admin/deleteSite")
    public SiteTransformer addNfcSite(@RequestParam String name ){
        return siteResource.deleteSite(name);
    }

}
