package com.allianceseeds.api.services.notifications;

import com.allianceseeds.api.adapters.notifications.EmailAdapter;
import com.allianceseeds.api.domain.commands.Command;
import com.allianceseeds.api.domain.commands.notifications.SendEmailCommand;
import com.allianceseeds.api.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationHandler {
    private final EmailAdapter emailAdapter;

    @Autowired
    public NotificationHandler(EmailAdapter emailAdapter) {
        this.emailAdapter = emailAdapter;
    }

    public Transformer sendEmail(Command command){
        String to = ((SendEmailCommand) command).getTo();
        String subject = ((SendEmailCommand) command).getSubject();
        String body = ((SendEmailCommand) command).getBody();

        Boolean result = emailAdapter.sendEmail(to, subject, body );

        return new NotificationTransformer(result);

    }
}
