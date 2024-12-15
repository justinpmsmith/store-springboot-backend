package com.accutrak.toolbox.services.notifications;

import com.accutrak.toolbox.adapters.repositories.notifications.EmailAdapter;
import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.commands.notifications.SendEmailCommand;
import com.accutrak.toolbox.services.Transformer;
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
