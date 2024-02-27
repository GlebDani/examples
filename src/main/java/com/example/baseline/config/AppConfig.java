package com.example.baseline.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class AppConfig {

    @Value("${spring.mail.host}")
    private String spring_mail_host;

    @Value("${spring.mail.port}")
    private String spring_mail_port;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String spring_mail_properties_mail_smtp_auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String spring_mail_properties_mail_smtp_starttls_enable;

    @Value("${spring.mail.smtp.ssl.trust}")
    private String spring_mail_smtp_ssl_trust;

    @Value("${mail.transport.protocol}")
    private String mail_transport_protocol;

    @Value("${spring.mail.username}")
    private String spring_mail_username;

    @Value("${spring.mail.password}")
    private String spring_mail_password;


    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(spring_mail_host);
        mailSender.setPort(Integer.parseInt(spring_mail_port));

        mailSender.setUsername(spring_mail_username);
        mailSender.setPassword(spring_mail_password);

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol",mail_transport_protocol);
        properties.put("spring.smtp.auth",spring_mail_properties_mail_smtp_auth);
        properties.put("mail.smtp.starttls.enable",spring_mail_properties_mail_smtp_starttls_enable);
        properties.put("mail.debug", true);
        properties.put("mail.smtp.ssl.trust", spring_mail_smtp_ssl_trust);

        return mailSender;

    }
}
