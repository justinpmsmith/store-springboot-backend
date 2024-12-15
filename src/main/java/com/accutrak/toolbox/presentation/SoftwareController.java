package com.accutrak.toolbox.presentation;

import com.accutrak.toolbox.domain.entities.Software;
import com.accutrak.toolbox.presentation.resources.SoftwareResource;
import com.accutrak.toolbox.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // TODO: review cors impl
public class SoftwareController {

    private SoftwareResource softwareResource;

    @Autowired
    public SoftwareController(SoftwareResource softwareResource) {
        this.softwareResource = softwareResource;
    }

    @PostMapping("/admin/addSoftware")
    public Transformer addSoftware(@RequestBody Software software){

        return softwareResource.addSoftware(software);
    }
    @DeleteMapping("/admin/deleteSoftwareByFileName")
    public Transformer deleteSoftwareByFileName(@RequestParam String fileName){

        return softwareResource.deleteFirmwareByFileName(fileName);
    }
//    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/client/getLatestSoftwareInfo")
    public Transformer getLatestSoftwareName(@RequestParam String site){

        return softwareResource.getLatestSoftwareInfo(site);

    }

    @GetMapping("/client/getSoftwareByName")
    public Transformer getSoftwareByName(@RequestParam String fileName){

        return softwareResource.getSoftwareByFileName(fileName);

    }
}
