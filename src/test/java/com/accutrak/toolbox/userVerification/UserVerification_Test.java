package com.accutrak.toolbox.userVerification;

import com.accutrak.toolbox.domain.entities.Otp;
import com.accutrak.toolbox.domain.entities.User;

import com.accutrak.toolbox.domain.exceptions.InvalidEmailException;
import com.accutrak.toolbox.presentation.resources.UserResource;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.user.UserTransformer;
import com.accutrak.toolbox.user.User_TestCases;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserVerification_Test {

    private final UserResource userResource;

    @Autowired
    public UserVerification_Test(UserResource userResource) {
        this.userResource = userResource;
    }

    @Test
    void requestOtp_valid(){
        Otp otp = UserVerification_TestCases.validOtp;

        Boolean result = ((UserTransformer) userResource.requestOtp(otp)).getMeta().getSuccess();

        assertEquals(result, true);
    }
    @Test
    void requestOtp_invalid(){
        Otp otp = UserVerification_TestCases.inValidOtp;
        assertThrows(InvalidEmailException.class, ()-> {userResource.requestOtp(otp);});
    }
//    @Test
//    void AddUser_invalidOtp(){
//        User user =  UserVerification_TestCases.validUser;
//        assertThrows(InvalidEmailException.class, (Executable) userResource.addUser(user));
////        assertEquals(result.getMeta().getSuccess(), false);
//    }
}
