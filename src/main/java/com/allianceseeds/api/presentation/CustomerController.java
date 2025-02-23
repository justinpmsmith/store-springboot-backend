package com.allianceseeds.api.presentation;

import com.allianceseeds.api.domain.commands.customer.ContactUsCommand;
import com.allianceseeds.api.presentation.resources.CustomerResource;
import com.allianceseeds.api.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerResource customerResource;

    @Autowired
    public CustomerController(CustomerResource customerResource) {
        this.customerResource = customerResource;
    }

    @PostMapping("/client/contactUs")
    public Transformer contactUS(@RequestBody ContactUsCommand contactUsCommand){
        return customerResource.contactUS(contactUsCommand);
    }
}
