package com.allianceseeds.api.services.customer;

import com.allianceseeds.api.domain.commands.Command;
import com.allianceseeds.api.domain.commands.customer.ContactUsCommand;
import com.allianceseeds.api.domain.commands.notifications.SendEmailCommand;
import com.allianceseeds.api.services.Transformer;
import com.allianceseeds.api.services.notifications.NotificationHandler;
import com.allianceseeds.api.services.notifications.NotificationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerHandler {

    private NotificationHandler notificationHandler;

    @Autowired
    public CustomerHandler(NotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
    }

    public Transformer contactUs(Command command){
        ContactUsCommand contactUsCommand = (ContactUsCommand) command;

        String name = contactUsCommand.getName();
        String surname = contactUsCommand.getSurname();
        String  cell = contactUsCommand.getCell();
        String email = contactUsCommand.getEmail();
        String message = contactUsCommand.getMessage();

        String subject = "Contact us message from " + name + " " + surname;

        String body = "Name: " + name +
                "\nEmail: " + email +
                "\nCell: " + cell +
                "\n\nMessage: " + message;

        // TODO: use env variable for admin email addresses
//        String[] recipients = {"cherise.nel22@gmail.com"};
        String[] recipients = {"justinpmsmith@outlook.com"};

        SendEmailCommand sendEmailCommand = new SendEmailCommand(recipients, subject, body);
        NotificationTransformer transformer = (NotificationTransformer) notificationHandler.sendEmail(sendEmailCommand);

        return transformer;
    }
}


