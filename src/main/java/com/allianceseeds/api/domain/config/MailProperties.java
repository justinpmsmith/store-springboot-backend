package com.allianceseeds.api.domain.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "env.mail")
@Getter
public class MailProperties {
//    @Value("${env.mail.subject}")
//    private final String subject;
//
//    @Value("${env.mail.header}")
//    private final String header;
//
//    @Value("${env.mail.footer}")
//    private final String footer;
//
//    @Value("${env.mail.greeting}")
//    private final String greeting;
//
//    @Value("${env.mail.from}")
//    private final String from;


}
