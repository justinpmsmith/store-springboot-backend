package com.accutrak.toolbox.presentation;

import com.accutrak.toolbox.domain.entities.Otp;
import com.accutrak.toolbox.domain.entities.User;
import com.accutrak.toolbox.presentation.resources.UserResource;
import com.accutrak.toolbox.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserResource userResource;


    @Autowired
    public UserController(UserResource userResource) {
        this.userResource = userResource;
    }

    @PreAuthorize("permitAll")
    @PostMapping("/client/requestOpt")
    public Transformer requestOpt(@RequestBody Otp otp) {
        return userResource.requestOtp(otp);
    }

    @PostMapping("/client/addUser")
    public Transformer addUser(@RequestBody User user){


        return userResource.addUser(user);
    }

    @PostMapping("/client/updateUser")
    public Transformer updateUser(@RequestBody User user){
        return userResource.updateUser(user);
    }

    @PostMapping("/client/migrateUser")
    public Transformer migrateUser(@RequestBody User user){
        return userResource.migrateUser(user);
    }

    @GetMapping("/admin/getUuidByEmail")
    public Transformer getUuidByEmail(@RequestParam String email){return userResource.getUuidByEmail(email);}


    @GetMapping("/client/getTagConfigLevelByUuid")
    public Transformer getTagConfigLevelByUuid(@RequestParam String uuid){return userResource.getTagConfigLevelByUuid(uuid);}

}
