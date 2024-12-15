package com.accutrak.toolbox.services.firmware;

import com.accutrak.toolbox.domain.entities.Firmware;
import com.accutrak.toolbox.services.BaseTransformer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class FirmwareTransformer<T> extends BaseTransformer {
    public FirmwareTransformer(Boolean success, T data) {
        super();
        setSuccess(success);
        this.setData(data);
    }

    public void removeBlob(){
        List<Firmware> firmwareList = (List<Firmware>) getData();
        firmwareList.forEach(firmware -> firmware.setBlob(null));
        setData(firmwareList);
    }

    /**
     * Filters the data (firmwareList) based on the specified site.
     *
     * - If the input site is "*", all firmware entries are returned,
     *   excluding duplicates based on the 'hash' property.
     * - If entries matching the specified site are found, only those entries
     *   are returned.
     * - If no entries matching the specified site are found, entries with the
     *   wildcard site "*" are returned instead.
     *
     * @param site The site to filter by; use "*" to include all firmware entries,
     *             excluding duplicates based on 'hash'
     */
    public void filterBySite( String site) {
        List<Firmware> filteredFirmwareList;
        List<Firmware> firmwareList = ( List<Firmware>) data;

        if ("*".equals(site)) {
            // If site is "*", return all entities with no duplicates based on hash
            filteredFirmwareList = removeDuplicatesByHash(firmwareList);
            this.setData(filteredFirmwareList);
            return;
        }

        boolean hasSpecificSite = firmwareList.stream().anyMatch(firmware -> firmware.getSite().equals(site));
        if (hasSpecificSite) {
            filteredFirmwareList = firmwareList.stream()
                    .filter(firmware -> firmware.getSite().equals(site))
                    .collect(Collectors.toList());
        } else {
            // User wants software with specific site but none found, return all with "*"
            filteredFirmwareList = firmwareList.stream()
                    .filter(firmware -> firmware.getSite().equals("*"))
                    .collect(Collectors.toList());
        }

        this.setData(filteredFirmwareList);
    }

    /**
     * Helper method to remove Firmware duplicates based on the hash property.
     *
     * @param firmwareList List of Firmware objects to filter
     * @return List without duplicate hash values
     */
    private List<Firmware> removeDuplicatesByHash(List<Firmware> firmwareList) {
        return firmwareList.stream()
                .collect(Collectors.toMap(
                        Firmware::getHash, // Use hash as the key
                        firmware -> firmware, // Use Firmware object as the value
                        (existing, replacement) -> existing // Keep the first occurrence of each hash
                ))
                .values()
                .stream()
                .collect(Collectors.toList());
    }
}
