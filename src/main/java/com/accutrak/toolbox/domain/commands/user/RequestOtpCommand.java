package com.accutrak.toolbox.domain.commands.user;

import com.accutrak.toolbox.domain.validation.Validation;
import lombok.Getter;

import java.util.Random;

@Getter
public class RequestOtpCommand implements UserCommand{
    String email;
    String deviceId;
    int ttlMin;

    public RequestOtpCommand(String email, String deviceId) {
        Validation.validateEmail(email);

        this.email = email;
        this.deviceId = deviceId;

        String ttlEnv = System.getenv("OTP_TTL_MIN");
        this.ttlMin = (ttlEnv != null) ? Integer.parseInt(ttlEnv) : 60; // Default to 60

    }

    public long getExpirationTimestamp() {
        long currentTimestamp = System.currentTimeMillis() / 1000;
        long futureTimestamp = currentTimestamp + (ttlMin * 60);
        return futureTimestamp;
    }

    public int generateOtp(){
        Random rand = new Random();
        int rand_int1 = rand.nextInt(9000) + 1000 ;
        return rand_int1;
    }


}
