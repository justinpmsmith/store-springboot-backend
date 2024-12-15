package com.accutrak.toolbox.adapters.repositories.otp;

import com.accutrak.toolbox.adapters.repositories.RepositoryInt;
import com.accutrak.toolbox.domain.entities.Otp;

public interface OtpRepoInt extends RepositoryInt<Otp> {
    public Otp getOtpByEmail(String value);
}
