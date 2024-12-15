package com.accutrak.toolbox.user;

import com.accutrak.toolbox.domain.entities.User;
import com.accutrak.toolbox.domain.exceptions.InvalidDeviceIdException;
import com.accutrak.toolbox.domain.exceptions.InvalidEmailException;
import com.accutrak.toolbox.presentation.resources.UserResource;
import com.accutrak.toolbox.services.Transformer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class User_Test {

    private final UserResource userResource;

    @Autowired
    public User_Test(UserResource userResource) {
        this.userResource = userResource;
    }

//    @Test
//    void addValidUser_Test(){
//        User user =  User_TestCases.validUser;
//        Transformer result = userResource.addUser(user);
//        assertNotNull(result);
//    }

    @Test
    void migrateValidUser_Test(){
        User user =  User_TestCases.validUser;
        Transformer result = userResource.migrateUser(user);
        assertNotNull(result);
    }

//    @Test
//    void addInValidUser_Test(){
//        User user = User_TestCases.userInvalidDeviceId;
//        assertThrows(InvalidDeviceIdException.class, ()->{userResource.addUser(user);});
//    }

    @Test
    void getUuidByEmail_ValidEmail_Test(){
        Transformer result = userResource.getUuidByEmail(User_TestCases.validEmail);
        assertNotNull(result);
    }
    @Test
    void getUuidByEmail_inValidEmail_Test(){
        assertThrows(InvalidEmailException.class, ()->{userResource.getUuidByEmail(User_TestCases.inValidEmail);});
    }
}
