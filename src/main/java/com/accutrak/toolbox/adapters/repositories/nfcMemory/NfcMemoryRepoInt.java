package com.accutrak.toolbox.adapters.repositories.nfcMemory;

import com.accutrak.toolbox.adapters.repositories.RepositoryInt;
import com.accutrak.toolbox.domain.entities.NfcMemory;

import java.util.List;


public interface NfcMemoryRepoInt extends RepositoryInt<NfcMemory> {
    NfcMemory getLastReadBySerial(String serial);
    List<NfcMemory> getNfcMemorysByUsuerId(String uuid);
    List<NfcMemory> getNfcMemorysByStringField(String field, String value);
    NfcMemory getLastReadByStringField(String field, String value);
    List<NfcMemory> getAll();

}
