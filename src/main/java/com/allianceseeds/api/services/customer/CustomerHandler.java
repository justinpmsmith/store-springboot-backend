package com.allianceseeds.api.services.customer;

import com.allianceseeds.api.adapters.notifications.EmailAdapter;
import com.allianceseeds.api.domain.commands.Command;
import com.allianceseeds.api.domain.commands.customer.ContactUsCommand;
import com.allianceseeds.api.domain.commands.customer.SellSomethingCommand;
import com.allianceseeds.api.domain.commands.notifications.SendEmailCommand;
import com.allianceseeds.api.services.Transformer;
import com.allianceseeds.api.services.notifications.NotificationHandler;
import com.allianceseeds.api.services.notifications.NotificationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomerHandler {


    private final EmailAdapter emailAdapter;

    @Value("${env.ADMIN_EMAIL}")
    private String adminEmail;
    @Value("${env.BACKUP_ADMIN_EMAIL}")
    private String backupAdminEmail;

    @Autowired
    public CustomerHandler(EmailAdapter emailAdapter) {
        this.emailAdapter = emailAdapter;
    }

    public Transformer contactUs(Command command){
        ContactUsCommand contactUsCommand = (ContactUsCommand) command;

        String name = contactUsCommand.getName();
        String surname = contactUsCommand.getSurname();
        String  cell = contactUsCommand.getCell();
        String email = contactUsCommand.getEmail();
        String message = contactUsCommand.getMessage();

        String subject = "Contact us message from " + name + " " + surname;

        String body = "Name: " + name + " " + surname+
                "\nEmail: " + email +
                "\nCell: " + cell +
                "\n\nMessage: " + message;

        // TODO: use env variable for admin email addresses
//        String[] recipients = {"cherise.nel22@gmail.com"};
        String[] recipients = {adminEmail, backupAdminEmail};

        Boolean emailSent = emailAdapter.sendEmail(recipients, subject, body );

        return new CustomerTransformer<>(emailSent, null);
    }

    public Transformer sellSomething(Command command) {
        SellSomethingCommand sellSomethingCommand = (SellSomethingCommand) command;

        String name = sellSomethingCommand.getName();
        String surname = sellSomethingCommand.getSurname();
        String  cell = sellSomethingCommand.getCell();
        String email = sellSomethingCommand.getEmail();
        String description = sellSomethingCommand.getDescription();
        String[] images = sellSomethingCommand.getImages();
        String price = sellSomethingCommand.getPrice();

        String body = "Name: " + name + " " + surname+
                "\nEmail: " + email +
                "\nCell: " + cell +
                "\nPrice: " + price +
                "\n\nDescription: " + description;

        String[] recipients = {adminEmail, backupAdminEmail};
        String subject = "Sell Something";

        Boolean emailSent = emailAdapter.sendEmailWithAttachments(recipients, subject, body, images );

        return new CustomerTransformer<>(emailSent, null);

    }
}


