package com.accutrak.toolbox.domain.commands.software;

import com.accutrak.toolbox.domain.entities.Software;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GetLatestSoftwareCommand implements SoftwareCommand{
    String site;

    public GetLatestSoftwareCommand(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public List<Software> filterBySite(List<Software> softwareList, String site ){
        List<Software> filteredSoftwareList;

        boolean hasSpecificSite = softwareList.stream().anyMatch(software -> software.getSite().equals(site));

        if (hasSpecificSite) {
            filteredSoftwareList = softwareList.stream()
                    .filter(software -> software.getSite().equals(site))
                    .collect(Collectors.toList());
        } else {
            // User wants software with specific site but none found, return all with "*"
            filteredSoftwareList = softwareList.stream()
                    .filter(software -> software.getSite().equals("*"))
                    .collect(Collectors.toList());
        }

        return filteredSoftwareList;
    }

    public Software findMostRecentSoftware(List<Software> softwareList) {
        return softwareList.stream()
                .max(Comparator.comparingInt(Software::getVersionCode)).orElse(null);
    }
}
