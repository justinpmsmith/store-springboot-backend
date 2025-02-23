package com.allianceseeds.api.presentation;

import com.allianceseeds.api.presentation.resources.AuthResource;
import com.allianceseeds.api.services.Transformer;
import com.allianceseeds.api.services.authentication.AuthTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthResource authResource;

    @Autowired
    public AuthController(AuthResource authResource) {
        this.authResource = authResource;
    }

    @GetMapping("/admin/amAuth")
    public Transformer amAuth(){ return new AuthTransformer(true, null);}

}
