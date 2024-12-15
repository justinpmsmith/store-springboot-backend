package com.accutrak.toolbox.adapters.repositories.nfcMemory;

import com.accutrak.toolbox.domain.entities.NfcMemory;
import com.accutrak.toolbox.domain.entities.TagConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNfcMemoryRepo extends JpaRepository<NfcMemory, Long> {
}
