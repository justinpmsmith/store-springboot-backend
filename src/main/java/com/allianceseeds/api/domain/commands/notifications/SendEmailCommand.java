package com.allianceseeds.api.domain.commands.notifications;

import lombok.Getter;

@Getter
public class SendEmailCommand implements NotificationCommand{
    String to;
    String subject;
    String body;

    public SendEmailCommand(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
