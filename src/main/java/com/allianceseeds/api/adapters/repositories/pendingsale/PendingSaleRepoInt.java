package com.allianceseeds.api.adapters.repositories.pendingsale;

import com.allianceseeds.api.adapters.repositories.RepositoryInt;
import com.allianceseeds.api.domain.entities.PendingSale;

public interface PendingSaleRepoInt extends RepositoryInt<PendingSale> {
    PendingSale findByPaymentId(String paymentId);
}
