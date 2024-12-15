package com.accutrak.toolbox.domain.commands.reportScript;

import com.accutrak.toolbox.domain.entities.ReportScript;
import com.accutrak.toolbox.domain.validation.Validation;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetValidScriptsBySiteCommand implements ReportScriptCommand{
    String site;

    public GetValidScriptsBySiteCommand(String site) {
        this.site = site;
    }

    public List<ReportScript> filterBySite(List<ReportScript> scriptList, String site ){
        List<ReportScript> filteredScriptList;

        boolean hasSpecificSite = scriptList.stream().anyMatch(software -> software.getSite().equals(site));

        if (hasSpecificSite) {
            filteredScriptList = scriptList.stream()
                    .filter(software -> software.getSite().equals(site))
                    .collect(Collectors.toList());
        } else {
            // User wants software with specific site but none found, return all with "*"
            filteredScriptList = scriptList.stream()
                    .filter(software -> software.getSite().equals("*"))
                    .collect(Collectors.toList());
        }

        return filteredScriptList;
    }
}
